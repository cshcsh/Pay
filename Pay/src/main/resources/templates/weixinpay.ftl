<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<link rel="stylesheet" href="/layui/css/layui.css">
</head>
<body>
	<div class="layui-container">

		<#include "/common/head.ftl">

		<div class="layui-row">
			<div class="layui-col-md12">
				<fieldset class="layui-elem-field layui-field-title"
					style="margin-top: 30px;">
					<legend>微信扫码在线支付</legend>
				</fieldset>
				<table>
					<tr>
						<td>商品名称：</td>
						<td>${order.subject}</td>
					</tr>
					<tr>
						<td>商品描述：</td>
						<td>${order.body}</td>
					</tr>
					<tr>
						<td>交易金额：</td>
						<td>${order.totalAmount}(元)</td>
					</tr>
				</table>
				<img src="/weixinpay/loadPayImage?id=${order.id?c}"></img>
			</div>
		</div>
	</div>
	<script src="/layui/layui.js"></script>
	<script
		src="http://blog.java5200.com/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript">

	function orderStatus(){
      	$.post("/weixinpay/loadPayState",{  
            id:${order.id?c}
        },  
        function(data,status){ 
            if(data==1){  
                location.href = "/weixinpay/returnUrl";  
            }  
        });
      }
	      
	  setInterval("orderStatus()",3000);
	  
</script>

</body>
</html>