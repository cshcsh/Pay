package com.java5200.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.java5200.entity.Order;
import com.java5200.properties.AlipayProperties;
import com.java5200.service.OrderService;
import com.java5200.util.DateUtil;
import com.java5200.util.StringUtil;

/**
 * 支付宝支付Controller
 * @author CSH
 *
 */
@Controller
@RequestMapping("/alipay")
public class AlipayController {

	@Resource
	private AlipayProperties alipayProperties;
	
	@Resource
	private OrderService orderService;
	
	private static Logger logger=Logger.getLogger(AlipayController.class);
	
	/**
	 * 支付请求
	 * @param order
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/pay")
	public void pay(Order order,HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		String orderNo=DateUtil.getCurrentDateStr(); // 生成订单号
		
		String totalAmount=""; // 支付总金额
		String subject=""; // 订单名称
		String body=""; // 商品描述
		switch(order.getProductId()){
			case 1:
				totalAmount="5";
				subject="打赏一杯奶茶";
				body="05元-打赏一杯奶茶";
				break;
			case 2:
				totalAmount="10";
				subject="打赏一顿快餐";
				body="10元-打赏一顿快餐";
				break;
			case 3:
				totalAmount="20";
				subject="打赏一顿自助";
				body="20元-打赏一顿自助";
				break; 
			default:
				totalAmount="20";
				subject="打赏一顿自助";
				body="20元-打赏一顿自助";
				break;
		}
		order.setOrderNo(orderNo);
		order.setSubject(subject);
		order.setTotalAmount(totalAmount);
		order.setBody(body);
		
		orderService.save(order);
		
		// 封装请求客户端
		AlipayClient client=new DefaultAlipayClient(alipayProperties.getUrl(), alipayProperties.getAppid(), alipayProperties.getRsa_private_key(), alipayProperties.getFormat(), alipayProperties.getCharset(), alipayProperties.getAlipay_public_key(), alipayProperties.getSigntype());
		
		// 支付请求
		AlipayTradePagePayRequest alipayRequest=new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(alipayProperties.getReturn_url());
		alipayRequest.setNotifyUrl(alipayProperties.getNotify_url());
		AlipayTradePayModel model=new AlipayTradePayModel();
		model.setProductCode("FAST_INSTANT_TRADE_PAY"); // 设置销售产品码
		model.setOutTradeNo(orderNo); // 设置订单号
		model.setSubject(subject); // 订单名称
		model.setTotalAmount(totalAmount); // 支付总金额
		
		model.setBody(body); // 设置商品描述
		alipayRequest.setBizModel(model);
		
		String form=client.pageExecute(alipayRequest).getBody(); // 生成表单
		
		response.setContentType("text/html;charset=" + alipayProperties.getCharset()); 
		response.getWriter().write(form); // 直接将完整的表单html输出到页面 
		response.getWriter().flush(); 
		response.getWriter().close();
	}
	
	@RequestMapping("/notifyUrl")
	public void notifyUrl(HttpServletRequest request)throws Exception{
		logger.info("异步通知notifyUrl");
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			
			params.put(name, valueStr);
			logger.info("name:"+name+",valueStr:"+valueStr);
		}
		
		boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayProperties.getAlipay_public_key(), alipayProperties.getCharset(), alipayProperties.getSigntype()); //调用SDK验证签名
		//商户订单号
		String out_trade_no = request.getParameter("out_trade_no");
		//交易状态
		String trade_status = request.getParameter("trade_status");
		
		if(signVerified){ // 验证成功 更新订单信息
			if(trade_status.equals("TRADE_FINISHED")){
				logger.info("TRADE_FINISHED");
			}
			if(trade_status.equals("TRADE_SUCCESS")){
				logger.info("TRADE_SUCCESS");
			}
			if(StringUtil.isNotEmpty(out_trade_no)){
				Order order=orderService.getByOrderNo(out_trade_no);
				if(order!=null){
					order.setBuyTime(new Date()); // 支付时间
					order.setIsPay(1); // 支付支付状态 已经支付
					orderService.save(order);
				}
			}
		}else{
			logger.error("验证未通过");
		}
	}
	
	
	/**
	 * 同步跳转
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/returnUrl")
	public ModelAndView returnUrl(HttpServletRequest request)throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("title", "同步通知地址_Java项目分享网");
		
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
			logger.info("name:"+name+",valueStr:"+valueStr);
		}
		
		boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayProperties.getAlipay_public_key(), alipayProperties.getCharset(), alipayProperties.getSigntype()); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——
		if(signVerified) {
			mav.addObject("message", "非常感谢，祝您生活愉快！");
		}else {
			mav.addObject("message", "验签失败");
		}
		mav.setViewName("returnUrl");
		return mav;
	}
}
