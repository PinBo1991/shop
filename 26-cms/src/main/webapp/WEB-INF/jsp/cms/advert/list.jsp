<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<form:form name="form1" id="form1" method="post" modelAttribute="cond">
		<div class="ti">广告位查询</div>
		<table class="SearchTab">
			<tr>
				<td align="right">广告位名称</td>
				<td><form:input path="name_c"   class="text-m" /></td>
				<td align="right">状态</td>
				<td><form:select path="status_c"  items="${statusMap}"  class="sel"  /></td>
				<td align="right">终端类型</td>
				<td><form:select path="type_c"  items="${typeMap}"  class="sel" /></td>
				<td align="right">顺序</td>
				<td><form:input path="order_num_c"  class="text-m" /></td>
			</tr>
			<tr>
				<td align="right">时间戳</td>
				<td>从<form:input path="startTime" format="yyyy-MM-dd HH:mm:ss" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  class="text-m" /></td>
				<td></td>
				<td>到<form:input path="endTime" format="yyyy-MM-dd HH:mm:ss" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  class="text-m" /></td>
				<td></td>
				<td></td>
				<td></td>
				<td>
					<input type="button" class="bnt" value="查询" onclick="toAction('${webPath}/advert/list');" /> 
					<input type="button" class="bnt" value="清空"	onclick="query_clean()" />
				</td>
			</tr>
		</table>
		<table class="titleTab">
			<tr>
				<td class="ti">系统参数列表</td>
				<td class="bu">
					<input type="button" class="bnt" value="新增" onclick="toAction('${webPath}/advert/toinsert');" />
					<input type="button" class="bnt" value="删除" onclick="delete_onclick(getIds())" />
				</td>
			</tr>
		</table>
		<table class="dataTab">
			<tr>
				<th><input type="checkbox" id="chkAll" onclick="checkAll()">序号</th>
				<th>轮播图名称</th>
				<th>顺序</th>
				<th>跳转地址</th>
				<th>备注</th>
				<th>状态 </th>
				<th>所属终端 </th>
				<th>时间戳</th>
				<th>启用/禁用</th>
				<th>删除</th>
				<th>修改</th>
				<th>详情</th>
			</tr>
			<c:forEach items="${dataList}" var="wz" varStatus="vs">
				<tr>
					<td class="sqe_w" ><input type="checkbox" name="chk" value="${wz.id}" /> <u:sequence index="${vs.count}" /></td>
					<td>${wz.name}</td>
					 <td>${wz.order_num}</td>
					 <td>${wz.jump_url}</td>
					 <td>${wz.remark}</td>
					 <td>${wz.status_name}</td>
					 <td>${wz.type_name}</td>
					 <td><fmt:formatDate value="${wz.ts}" type="both" /> </td>
					 <td class="td-shou-70">
<c:if test="${wz.status==1}">
	<input type="button" value="禁用"  onclick="toAction('${webPath}/advert/updateStatus?id=${wz.id}&status=0&type=${wz.type}');" >
</c:if>
<c:if test="${wz.status==0}">
	<input type="button" value="启用"  onclick="toAction('${webPath}/advert/updateStatus?id=${wz.id}&status=1&type=${wz.type}');" >
</c:if>
					</td>			 
					<td class="td-del" onclick="delete_onclick('${wz.id}');"></td>
					<td class="td-upd" onclick="toAction('${webPath}/advert/toupdate?id=${wz.id}');"></td>
					<td class="td-det" onclick="detail_onclick('${wz.id}')"></td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="/WEB-INF/jsp/page.jsp" />
	</form:form>
</body>
<script type="text/javascript">
function query_clean(){//清空查询条件
	clearCond(["name_c","type_c","status_c","order_num_c","startTime","endTime"]);
	toAction('${webPath}/advert/list');//清空之后再查询 
}
function delete_onclick(ids){//删除数据
	if(ids==""){
		alert("请选择要删除的记录");
		return false;
	}
	if(confirm("确认要删除这些记录码?")){
		 $.ajax({
			 data:{"ids":eval("["+ids+"]")},
			 url:"${webPath}/advert/delete",
			 success:function(data){
				 if(data>0){
					 toAction('${webPath}/advert/list');
				 }
			 }
		 });
	}
}
function detail_onclick(id) {//查看单条记录详细
	var text, url, webpath, winWidth, winHeight, widId;
	text = "";
	webpath = "${webPath}";
	url = "${webPath}/advert/detail?id=" + id;
	winHeight = 550;
	winWidth = 500;
	widId = 'win001';
	top.ShowWin(text, url, webpath, winWidth, winHeight, widId);
}

</script>
</html>