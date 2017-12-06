<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<div class="ti">广告位详细</div>
	<table class="dataTab">
		<tr>
			<td class="right30">广告位名称</td>
			<td>${advert.name}</td>
		</tr>
		<tr>
			<td class="right30">顺序</td>
			<td>${advert.order_num}</td>
		</tr>
		<tr>
			<td class="right30">存储路径</td>
			<td>${advert.picture_path}</td>
		</tr>
		<tr>
			<td class="right30"> 访问地址</td>
			<td>${advert.picture_url}</td>
		</tr>
		<tr>
			<td class="right30">图片展示</td>
			<td><img src="${advert.picture_url}" style="width: 200px;height: 100px" /></td>
		</tr>		
		<tr>
			<td class="right30">跳转地址</td>
			<td>${advert.jump_url}</td>
		</tr>
		<tr>
			<td class="right30">状态</td>
			<td>${advert.status_name}</td>
		</tr>
		<tr>
			<td class="right30">所属终端</td>
			<td>${advert.type_name}</td>
		</tr>
				<tr>
			<td class="right30">时间戳</td>
			<td><fmt:formatDate value="${advert.ts}" type="both"/></td>
		</tr>
		<tr>
			<td class="right30">备注</td>
			<td>${advert.remark}</td>
		</tr>
	</table>
</body>
</html>