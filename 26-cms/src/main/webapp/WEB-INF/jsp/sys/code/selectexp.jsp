<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			url : "${webPath}/exp/jsonList",
			data : {},
			success : function(data) {
				$.each(data["jsonList"], function() {//遍历List选出合适的列构建Select列表
					$("#tel").append($("<option/>").text(this.name).val(this.id));
				});
				$.each(data["jsonMap"], function(key, value) {//遍历Map用key,value构建Select列表
					$("#remark").append($("<option/>").text(value).val(key));
				});
				$("#remark").val("");
			}
		});
	});
</script>
<body>
	<form:form name="form1" method="post" commandName="sysuser">
		<div class="ti">下拉列表示例页</div>
		<table class="dataTab">
			<tr>
				<td class="right30">Map(key-value)类型--数据字典</td>
				<td class="left30"><form:select path="name" items="${item_map}" class="sel" /></td>
				<td></td>
			</tr>
			<tr>
				<td class="right30">List或Map中有多列</td>
				<td class="left30">
					<form:select path="name"  class="sel">
						<c:forEach var="user" items="${user_list}">
							<option value="${user.id}">${user.name}</option>
						</c:forEach>
					</form:select>
				</td>
				<td class="left30_red" id="result_td"></td>
			</tr>
			<tr>
				<td class="right30">Ajax-Map(key-value)类型</td>
				<td class="left30"><form:select path="remark" class="sel" /></td>
				<td class="left30_red"></td>
			</tr>
			<tr>
				<td class="right30">Ajax-List或Map多列</td>
				<td class="left30"><form:select path="tel" class="sel" /></td>
				<td class="left30_red"></td>
			</tr>
		</table>
		<center class="btn_div">
			<input type="button" icon="icon-retry" onclick="alert('你好!')" value="示例按钮" />
		</center>
	</form:form>
</body>
</html>