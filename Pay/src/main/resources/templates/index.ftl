<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/layui/css/layui.css">
<title>${title}</title>
</head>
<body>
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
					<legend>欢迎使用</legend>
				</fieldset>

				<div style="padding: 20px; background-color: #F2F2F2;">
					<div class="layui-row layui-col-space15">
						<div class="layui-col-md12">
							<div class="layui-card">
								<div class="layui-card-header">使用说明</div>
								<div class="layui-card-body"  style="height: 350px;">在线支付案例，支付宝支付Demo，微信支付Demo</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<script src="/layui/layui.js"></script>
	<script>
		layui.use('element', function() {
			var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

			//监听导航点击
			element.on('nav(demo)', function(elem) {
				//console.log(elem)
				layer.msg(elem.text());
			});
		});
	</script>

</body>
</html>
</body>
</html>