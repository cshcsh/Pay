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
					<legend>关于项目</legend>
				</fieldset>

				<div style="padding: 20px; background-color: #F2F2F2;">
					<div class="layui-row layui-col-space15">
						<div class="layui-col-md12">
							<div class="layui-card">
								<div class="layui-card-header">项目说明</div>
								<div class="layui-card-body"  style="height: 350px;">一个Java实现的支付宝微信在线支付在线案例</div>
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