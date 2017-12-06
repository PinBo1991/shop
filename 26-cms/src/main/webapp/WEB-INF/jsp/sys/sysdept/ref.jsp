<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>部门列表<%--author:高振中,DATE:2014-07-29 16:49:42--%></title>
</head>
<body>
	<form:form id="form1" method="post" commandName="cond">
		<div class="ti">部门查询</div>
		<table class="SearchTab">
			<tr>
				<td align="right">名称</td>
				<td><form:input path="name_c" class="text-m" /></td>
				<td align="right">父结点</td>
				<td><form:input path="parent_id_c" class="text-m" /></td>
				<td align="right">是否叶子</td>
				<td><form:input path="is_leaf_c" class="text-m" /></td>
				<td align="right">排序编号</td>
				<td><form:input path="order_code_c" class="text-m" /></td>
			</tr>
			<tr>
				<td align="right">备注</td>
				<td><form:input path="remark_c" class="text-m" /></td>
				<td align="right"></td>
				<td></td>
				<td align="right"></td>
				<td></td>
				<td></td>
				<td>
					<input type="button" class="bnt" value="查询" onclick="toAction('${webPath}/sysdept/list');" /> 
					<input type="button" class="bnt" value="清空"	onclick="toClear();" />
				</td>
			</tr>
		</table>
		<table class="titleTab">
			<tr>
				<td class="ti">部门列表</td>
				<td class="bu">
				<input type="button" value="确定" onclick="toValue();" />
				</td>
			</tr>
		</table>
		<table class="dataTab">
			<tr>
				<th>序号</th>
				<th>名称</th>
				<th>父结点</th>
				<th>是否叶子</th>
				<th>排序编号</th>
				<th>备注</th>
 
			</tr>
			<c:forEach items="${dataList}" var="wz" varStatus="vs">
				<tr>
					<td class="sqe_w" ><input type="radio" name="chk" value="${wz.id}" title="${wz.name}" /> <u:sequence index="${vs.count}" /></td>
					<td>${wz['name']}</td>
					<td>${wz['parent_id']}</td>
					<td>${wz['is_leaf']}</td>
					<td>${wz['order_code']}</td>
					<td>${wz['remark']}</td>
 
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="/WEB-INF/jsp/page.jsp" />
	</form:form>
</body>
<script type="text/javascript">
	function toClear() {//清空查询条件
		clearCond(['name_c','parent_id_c','is_leaf_c','order_code_c','remark_c']);
	}
	function toQuery() {//按条件查询
		toAction("${webPath}/point/ref?rname=${param.rname}&rvalue=${param.rvalue}");
	}
	function toValue() {//反回值
		var id = $("input:radio[name='chk']:checked").val();
		var ename = $("input:radio[name='chk']:checked").attr('title');
		if (typeof (id) == "undefined") {
			alert("请选择记录!");
			return false;
		}
		$(parent.frames["center"].document).find('#${param.rvalue}').val(id);
		$(parent.frames["center"].document).find('#${param.rname}').val(ename);
		top.dhxWins.window("win001").close();
	}
</script>
</html>