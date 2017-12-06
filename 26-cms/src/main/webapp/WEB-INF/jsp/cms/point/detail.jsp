<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>卖点图详细<%--author:gzz_gzz@163.com,DATE:2016-10-18 14:48:24--%></title>
</head>
<body>
	<div class="ti">卖点图详细</div>
	<table class="dataTab">
		<tr>
			<td class="right30_blue">名称</td>
			<td>${point.name}</td>
		</tr>
		<tr>
			<td class="right30_blue">顺序</td>
			<td>${point.order_num}</td>
		</tr>
		<tr>
			<td class="right30_blue">标题</td>
			<td>${point.title}</td>
		</tr>
		<tr>
			<td class="right30_blue">存储路径</td>
			<td>${point.picture_path}</td>
		</tr>
		<tr>
			<td class="right30_blue">访问地址</td>
			<td>${point.picture_url}</td>
		</tr>
		<tr>
			<td class="right30_blue">图片</td>
			<td><img  src="${point.picture_url}" ></td>
		</tr>		
		<tr>
			<td class="right30_blue">跳转地址</td>
			<td>${point.jump_url}</td>
		</tr>
		<tr>
			<td class="right30_blue">备注</td>
			<td>${point.remark}</td>
		</tr>
		<tr>
			<td class="right30_blue">状态</td>
			<td>${point.status_text}</td>
		</tr>
		<tr>
			<td class="right30_blue">所属终端</td>
			<td>${point.type_text}</td>
		</tr>
		<tr>
			<td class="right30_blue">时间戳</td>
			<td><fmt:formatDate value="${point.ts}" type="both" /></td>
			<%--type取值还有date,time,中文年月日时分秒等--%>
		</tr>
	</table>
</body>
</html>