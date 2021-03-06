<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>功能菜单列表<%--author:高振中,DATE:2014-07-28 11:06:18--%></title>
</head>
<script type="text/javascript">
	function toDelete(ids) {//删除记录
		if(checkDel(ids)){
			toAction("${webPath}/sysfunc/delete?id=" + ids);
		}
	}
	function toClear() {//清空查询条件
		clearCond(['name_c','type_c','is_leaf_c','url_c','parent_id_c','order_code_c','remark_c' ]);
	}
	function toDetail(id) {//查看单条记录详细
		top.ShowWin('详细页面','${webPath}/sysfunc/detail?id='+id,'${webPath}',600,600,'win001');
	}
</script>
<body>
	<form:form id="form1" method="post" commandName="cond">
		<div class="ti">功能菜单查询</div>
		<table class="SearchTab">
			<tr>
				<td align="right">名称</td>
				<td><form:input path="name_c" class="text-m" /></td>
				<td align="right">类型</td>
				<td><form:input path="type_c" class="text-m" /></td>
				<td align="right">是否叶子</td>
				<td><form:input path="is_leaf_c" class="text-m" /></td>
				<td align="right">地址或按钮键</td>
				<td><form:input path="url_c" class="text-m" /></td>
			</tr>
			<tr>
				<td align="right">父结点</td>
				<td><form:input path="parent_id_c" class="text-m" /></td>
				<td align="right">排序编号</td>
				<td><form:input path="order_code_c" class="text-m" /></td>
				<td align="right">备注</td>
				<td><form:input path="remark_c" class="text-m" /></td>
				<td></td>
				<td>
					<input type="button" class="bnt" value="查询" onclick="toAction('${webPath}/sysfunc/list');" /> 
					<input type="button" class="bnt" value="清空"	onclick="toClear();" />
				</td>
			</tr>
		</table>
		<table class="titleTab">
			<tr>
				<td class="ti">功能菜单列表</td>
				<td class="bu">
					<input type="button" class="bnt" value="新增" onclick="toAction('${webPath}/sysfunc/toAdd?id=${cond.parent_id_c}');" />
					<input type="button" class="bnt" value="删除" onclick="toDelete(getIds());" />
				</td>
			</tr>
		</table>
		<table class="dataTab">
			<tr>
				<th><input type="checkbox" id="chkAll" onclick="checkAll();">序号</th>
				<th>名称</th>
				<th>类型</th>
				<th>是否叶子</th>
				<th>地址或按钮键</th>
				<th>父结点</th>
				<th>排序编号</th>
				<th>备注</th>
				<th>删除</th>
				<th>修改</th>
				<th>详细</th>
			</tr>
			<c:forEach items="${dataList}" var="wz" varStatus="vs">
				<tr>
					<td class="sqe_w" ><input type="checkbox" name="chk" value="${wz['func_id']}" /> <u:sequence index="${vs.count}" /></td>
					<td>${wz['name']}</td>
					<td>${wz['type']}</td>
					<td>${wz['is_leaf']}</td>
					<td>${wz['url']}</td>
					<td>${wz['parent_id']}</td>
					<td>${wz['order_code']}</td>
					<td>${wz['remark']}</td>
					<td class="td-del" onclick="toDelete('${wz['func_id']}');"></td>
					<td class="td-upd" onclick="toAction('${webPath}/sysfunc/toUpdate?id=${wz['func_id']}');"></td>
					<td class="td-det" onclick="toDetail('${wz['func_id']}');"></td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="/WEB-INF/jsp/page.jsp" />
	</form:form>
</body>
</html>