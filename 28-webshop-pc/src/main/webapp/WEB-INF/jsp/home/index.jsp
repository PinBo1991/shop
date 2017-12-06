<%@ page language="java" pageEncoding="UTF-8"%>
<% request.setAttribute("webPath", request.getContextPath());%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>动力商城</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!--needstatic@css--><link rel="stylesheet" href="${webPath}/resources/css/style.css" /><!--needstatic-->
<link rel="stylesheet" href="${webPath}/resources/css/luara.top.css" />
<script src="${webPath}/resources/js/jquery-1.8.3.min.js"></script>
<script src="${webPath}/resources/js/jquery.luara.0.0.1.min.js"></script>
</head>
<body>
	<div class="com_head">
		<table>
			<tr>
				<td class="com_logo"></td>
				<td class="td150" onclick="goHome()">首页</td>
				<td class="td150" onclick="goHome()">商家中心</td>
				<td class="td150" onclick="goHome()">平台政策</td>
				<td class="td150" onclick="goHome()">手机版</td>
				<td class="td150" onclick="goHome()">个人设置</td>
				<td class="td150" onclick="goHome()">我的订单</td>
				<td class="td150" onclick="goHome()">购物车</td>
				<td class="td150" onclick="goHome()">登录</td>
				<td class="td150" onclick="goHome()">注册</td>
			</tr>
		</table>
	</div>
	<div class="com_banner">
		<ul>
			<c:forEach items="${bannerList}" var="banner" varStatus="vs" >
				<li><img src="${banner.picture_url}" alt="${vs.count}" /></li>
			</c:forEach>
		</ul>
		<ol>
			<c:forEach items="${bannerList}" var="banner">
				<li></li>
			</c:forEach>
		</ol>
	</div>
	<script>
		$(function() {
			<!--调用Luara示例-->
			$(".com_banner").luara({
				width : "1200",
				height : "380",
				interval : 5000,
				selected : "seleted",
				deriction : "top"
			});
		});
	</script>
	<div class="com_point">
		<ul>
			<c:forEach items="${pointList}" var="point">
				<li><i><img src="${point.picture_url}"></i><span>${point.title}</span></li>
	 		</c:forEach>
		</ul>
	</div>
	<div class="com_ad">
		<c:forEach items="${advertList}" var="advert">
			<a target="_blank" href="${advert.jump_url}"><img src="${advert.picture_url}"></a> 
		</c:forEach>
	</div>
	<div class="com_footer">
		<table align="center">
			<tr>
				<td class="td150" onclick="goHome()">关于动力结点</td>
				<td class="td150" onclick="goHome()">加入动力结点</td>
				<td class="td150" onclick="goHome()">联系我们</td>
				<td class="td150" onclick="goHome()">官方微博</td>
			</tr>
		</table>
		<p>
			◎POWERNODE.COM 2016 版权所有 <br>
		</p>
	</div>
</body>
<script>
	function goHome() {
		window.open("http://www.bjpowernode.com");
	}
</script>
</html>