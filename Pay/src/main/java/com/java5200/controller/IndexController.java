package com.java5200.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 首页请求
 * @author CSH
 *
 */
@Controller
public class IndexController {
	
	
	/**
	 * 首页请求
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView root(){
		ModelAndView mav=new ModelAndView();
		mav.addObject("title", "首页_Java项目分享网");
		mav.setViewName("index");
		return mav;
	}
	
	/**
	 * 在线支付页面请求
	 * @return
	 */
	@RequestMapping("/onlinePay")
	public ModelAndView onlinePay(){
		ModelAndView mav=new ModelAndView();
		mav.addObject("title", "在线支付_Java项目分享网");
		mav.setViewName("onlinePay");
		return mav;
	}
	
	/**
	 * 订单查询页面请求
	 * @return
	 */
	@RequestMapping("/toOrderListPage")
	public ModelAndView toOrderListPage(){
		ModelAndView mav=new ModelAndView();
		mav.addObject("title", "订单查询_Java项目分享网");
		mav.setViewName("orderList");
		return mav;
	}
	
	/**
	 * 关于项目页面请求
	 * @return
	 */
	@RequestMapping("/about")
	public ModelAndView about(){
		ModelAndView mav=new ModelAndView();
		mav.addObject("title", "关于项目_Java项目分享网");
		mav.setViewName("about");
		return mav;
	}
}
