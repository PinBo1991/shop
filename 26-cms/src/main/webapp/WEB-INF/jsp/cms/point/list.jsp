<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>卖点图列表<%--author:gzz_gzz@163.com,DATE:2016-10-18 14:48:24--%></title>
</head>
<body>
	<form:form id="form1" method="post" commandName="cond">
		<div class="ti">卖点图查询</div>
		<table class="SearchTab">
			<tr>
				<td align="right">名称</td>
				<td><form:input path="name_c" class="text-m" /></td>
				<td align="right">顺序</td>
				<td><form:input path="order_num_c" class="text-m" /></td>
				<td align="right">标题</td>
				<td><form:input path="title_c" class="text-m" /></td>
				<td align="right">状态</td>
				<td><form:select path="status_c" class="text-m" items="${statusMap }" /></td>
			</tr>
			<tr>
				<td align="right">所属终端</td>
				<td><form:select path="type_c" class="text-m" items="${typeMap }" /></td>
 				<td align="right">时间戳从</td>
				<td><form:input path="startTime" format="yyyy-MM-dd HH:mm:ss" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  class="text-m" /></td>
				<td align="right">到</td>
				<td><form:input path="endTime" format="yyyy-MM-dd HH:mm:ss" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  class="text-m" /></td>
				<td></td>
				<td>
					<input type="button" value="查询" onclick="toAction('${webPath}/point/list');" /> 
					<input type="button" value="清空"	onclick="toClear();" />
				</td>
			</tr>
		</table>
		<table class="titleTab">
			<tr>
				<td class="ti">卖点图列表</td>
				<td class="bu">
<%-- 权限按钮 <u:limit key="point_insert"/> --%>
					<input type="button" value="新增" onclick="toAction('${webPath}/point/toInsert');" />
					<input type="button" value="删除" onclick="toDelete(getIds());" />
				</td>
			</tr>
		</table>
		<table class="dataTab">
			<tr>
				<th><input type="checkbox" id="chkAll" onclick="checkAll();">序号</th>
				<th>名称</th>
				<th>顺序</th>
				<th>标题</th>
				<th>备注</th>
				<th>状态</th>
				<th>所属终端</th>
				<th>时间戳</th>
				<th>操作</th>
				<th>删除</th>
				<th>修改</th>
				<th>详细</th>
			</tr>
			<c:forEach items="${dataList}" var="wz" varStatus="vs">
				<tr>
					<td class="sqe_w" ><input type="checkbox" name="chk" value="${wz.id}" /> <u:sequence index="${vs.count}" /></td>
					<td>${wz.name}</td>
					<td>${wz.order_num}</td>
					<td>${wz.title}</td>
					<td>${wz.remark}</td>
					<td>${wz.status_text}</td>
					<td>${wz.type_text}</td>
					<td><fmt:formatDate value="${wz.ts}" type="both" /></td>
					<td>
						<c:if test="${wz.status==0}">
							<input type="button" value="启用" onclick="toAction('${webPath}/point/updateStatus?id=${wz.id}&status=1&type=${wz.type}');" />
						</c:if>
						<c:if test="${wz.status==1}">
							<input type="button" value="禁用" onclick="toAction('${webPath}/point/updateStatus?id=${wz.id}&status=0&type=${wz.type}');" />
						</c:if>
					</td>
					<td class="td-del" onclick="toDelete('${wz.id}');"></td>
					<td class="td-upd" onclick="toAction('${webPath}/point/toUpdate?id=${wz.id}&cur_Page=${cond.curPage}&page_Size=${cond.pageSize}');"></td>
					<td class="td-det" onclick="toDetail('${wz.id}');"></td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="/WEB-INF/jsp/page.jsp" />
	</form:form>
</body>
<script type="text/javascript">
	function toDelete(ids) {//删除记录
		if(checkDel(ids)){
			toAction("${webPath}/point/delete?id=" + ids);
		}
	}
	function toClear() {//清空查询条件
		clearCond(['name_c','order_num_c','title_c','status_c','type_c','startTime','endTime']);
		toAction('${webPath}/point/list');//清空后直接查询全部记录
	}
	function toDetail(id) {//查看单条记录详细
		top.ShowWin('详细页面','${webPath}/point/detail?id='+id,'${webPath}',600,600,'win001');
	}
</script>
</html>