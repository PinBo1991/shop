<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
	function add_onclick() {
		var validate = $('input:checkbox[name="validate"]');
		var isCond = $('input:checkbox[name="isCond"]');
		var isShow = $('input:checkbox[name="isShow"]');
		var comments = $('input[name="comments"]');
		var recordStr = "";
		var i = 0;
		$('input:hidden').each(
				function() {
					recordStr += isCond[i].checked + '★' + isShow[i].checked + '★' + comments[i].value + '★' + this.value + '★' + this.name + '★' + this.alt
							+ '★' + validate[i].checked + '★' + $('input:radio[name="' + this.name + 'text"]:checked').val() + "▲";
					i++;
				});

		$(parent.frames["center"].document).find('#div_${param.tname}').html("<font color=\"blue\">自定义</font>");
		var obj = $(parent.frames["center"].document).find('input[name="tab_hidden"]');
		obj[parseInt("${param.index}") - 1].value = recordStr;
		//alert(recordStr);
		top.dhxWins.window("win001").close();
	}
</script>
</head>
<body>
	<table class="titleTab">
		<tr>
			<td class="ti">表参数设置<font color="red">(自定义中文名时不能有英文逗号,否则无法生成代码;textarea前台非空验证对无效,只对长度进行检测.)</font></td>
			<td class="bu"><input type="button" icon="icon-add" value="确定" onclick="add_onclick()" /></td>
		</tr>
	</table>
	<table class="dataTab">
		<tr>
			<th>序号</th>
			<th>类属性名</th>
			<th>注释</th>
			<th>显示名称</th>
			<th>输入类型</th>
			<th>查询条件</th>
			<th>列表显示</th>
			<th>前台非空验证</th>
		</tr>
		<c:forEach items="${dataList}" var="wz" varStatus="vs">
			<tr>
				<td class="sqe">${vs.count}</td>
				<td>${wz['name']}<input type="hidden" name="${wz['name']}" value="${wz['data_length']}" alt="${wz['type']}"></td>
				<td>${wz['comments']}</td>
				<td><input type="text" name="comments" value="${wz['comments']}" /></td>
				<td><input name="${wz['name']}text" type="radio" value="1" checked="checked" />文本框 <input name="${wz['name']}text" type="radio" value="2" />文本域 <input
					name="${wz['name']}text" type="radio" value="3" />下拉列表 <%--单选按钮<input name="${wz['name']}" type="radio" value="4" /> 
					复选按钮<input name="${wz['name']}" type="radio" value="5" /> --%></td>
				<td><input type="checkbox" name="isCond" checked="checked" /></td>
				<td><input type="checkbox" name="isShow" <c:if test="${vs.count==1}">disabled="disabled"</c:if> <c:if test="${vs.count>1}"> checked="checked"</c:if> /></td>
				<td><input type="checkbox" name="validate" <c:if test="${vs.count==1}">disabled="disabled"</c:if> <c:if test="${vs.count>1}"> checked="checked"</c:if> /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
