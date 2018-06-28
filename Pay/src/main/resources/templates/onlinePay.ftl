<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/layui/css/layui.css">
<title>${title}</title>
</head>
<body>
	<div class="layui-container">

		<#include "/common/head.ftl">

		<div class="layui-row">
			<div class="layui-col-md12">
				<fieldset class="layui-elem-field layui-field-title"
					style="margin-top: 30px;">
					<legend>在线支付</legend>
				</fieldset>


				<form class="layui-form" action="" method="post">

					<div class="layui-form-item">
						<label class="layui-form-label">支付金额:</label>
						<div class="layui-input-block">
							<input type="radio" name="productId" value="1" title="05元-打赏一杯奶茶"
								checked="">
						</div>
						<div class="layui-input-block">
							<input type="radio" name="productId" value="2" title="10元-打赏一顿快餐">
						</div>
						<div class="layui-input-block">
							<input type="radio" name="productId" value="3" title="20元-打赏一顿自助">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">支付人:</label>
						<div class="layui-input-block">
							<input type="text" name="nickName" lay-verify="required"
								placeholder="请输入您的昵称" autocomplete="off" class="layui-input">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">QQ:</label>
						<div class="layui-input-block">
							<input type="text" name="qq" lay-verify="required|number"
								placeholder="请输入您的QQ号" autocomplete="off" class="layui-input">
						</div>
					</div>

					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">留言:</label>
						<div class="layui-input-block">
							<textarea name="message" placeholder="请输入留言内容"
								class="layui-textarea"></textarea>
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">支付方式:</label>
						<div class="layui-input-block">
							<input type="radio" name="way" value="支付宝" title="支付宝" checked="">
							<input type="radio" name="way" value="微信" title="微信"  disabled="disabled">
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn" lay-submit="" lay-filter="paySubmit">支付提交</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
<script src="/layui/layui.js"></script> <script>
	layui.use('element', function() {
		var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

		//监听导航点击
		element.on('nav(demo)', function(elem) {
			//console.log(elem)
			layer.msg(elem.text());
		});
	});
</script>

<script>
layui.use(['form'], function(){
  var form = layui.form;
 
  //监听提交
  form.on('submit(paySubmit)', function(data){
    if(data.field.way=='支付宝'){
    	data.form.action="/alipay/pay";
    }else if(data.field.way=='微信'){
    	data.form.action="/weixinpay/pay";
    }
    return true;
  });
});
</script>
</body>
</html>