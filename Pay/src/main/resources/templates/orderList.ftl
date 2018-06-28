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
					<legend>订单列表</legend>
				</fieldset>
			</div>
		</div>

		<div class="layui-row">
			<div class="layui-col-md12">
				<table class="layui-hide" id="orderListTable"></table>
			</div>
		</div>
	</div>
	<script src="/layui/layui.js"></script>

	<script>
		layui.use('table', function() {
			var table = layui.table;

			table.render({
				elem : '#orderListTable',
				url : '/order/orderList',
				cellMinWidth: 80,
				cols : [ [ {
					field : 'nickName',
					//width : 100,
					title : '昵称'
				}, {
					field : 'qq',
					//width : 120,
					title : 'QQ'
				}, {
					field : 'buyTime',
					//width : 180,
					title : '支付时间',
					sort : true,
					align : 'center'
				}, {
					field : 'way',
					//width : 100,
					title : '支付方式',
					align : 'center'
				}, {
					field : 'body',
					//width : 200,
					title : '订单详情'
				}, {
					field : 'message',
					title : '留言',
					//minWidth : 150
				} ] ],
				page : true
			});
		});
	</script>

</body>
</html>