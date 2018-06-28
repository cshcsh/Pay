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
					<legend>同步地址</legend>
				</fieldset>
				${message}
			</div>
		</div>
	</div>
	<script src="/layui/layui.js"></script>
</body>
</html>