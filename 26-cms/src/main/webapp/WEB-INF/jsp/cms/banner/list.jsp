<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<form:form name="form1" id="form1" method="post" modelAttribute="cond">
		<div class="ti">轮播图查询</div>
		<table class="SearchTab">
			<tr>
				<td align="right">轮播图名称</td>
				<td><form:input path="name_c"   class="text-m" /></td>
				<td align="right">状态</td>
				<td><form:select path="status_c"  items="${statusMap}"  class="sel"  /></td>
				<td align="right">终端类型</td>
				<td><form:select path="type_c"  items="${typeMap}"  class="sel" /></td>
				<td align="right">顺序</td>
				<td><form:input path="order_num_c"  class="text-m" /></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>
					<input type="button" class="bnt" value="查询" onclick="toAction('${webPath}/banner/list');" /> 
					<input type="button" class="bnt" value="清空"	onclick="query_clean()" />
				</td>
			</tr>
		</table>
		<table class="titleTab">
			<tr>
				<td class="ti">系统参数列表</td>
				<td class="bu">
					<input type="button" class="bnt" value="新增" onclick="toAction('${webPath}/banner/toinsert');" />
					<input type="button" class="bnt" value="删除" onclick="delete_onclick()" />
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
				<th>启用 /禁用</th>
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
					 <td>
	<input type="button" value="<c:if test="${wz.status==1}">禁</c:if><c:if test="${wz.status==0}">启</c:if>用"  onclick="toAction('${webPath}/banner/updatestatus?id=${wz.id}&status=${1-wz.status}&type=${wz.type}');" >
					</td>			 
					<td class="td-del" onclick="toAction('${webPath}/banner/delete?id=${wz.id}');"></td>
					<td class="td-upd" onclick="toAction('${webPath}/banner/toupdate?id=${wz.id}');"></td>
					<td class="td-det" onclick="detail_onclick('${wz.id}')"></td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="/WEB-INF/jsp/page.jsp" />
	</form:form>
</body>
<script type="text/javascript">
function query_clean(){//清空查询条件
	clearCond(["name_c","type_c","status_c","order_num_c"]);
	toAction('${webPath}/banner/list');//清空之后再查询 
}
function delete_onclick(){//删除数据
	var ids=getIds();//取到选中ID
	if(ids==""){
		alert("请选择要删除的记录");
		return false;
	}
	if(confirm("确认要删除这些记录码?")){
		toAction('${webPath}/banner/delete?id='+ids); 
	}
}
function detail_onclick(id) {//查看单条记录详细
	var text, url, webpath, winWidth, winHeight, widId;
	text = "";
	webpath = "${webPath}";
	url = "${webPath}/banner/detail?id=" + id;
	winHeight = 550;
	winWidth = 500;
	widId = 'win001';
	top.ShowWin(text, url, webpath, winWidth, winHeight, widId);
}

</script>
</html>