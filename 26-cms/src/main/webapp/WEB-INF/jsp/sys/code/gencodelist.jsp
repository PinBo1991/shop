<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<form:form name="form1" id="form1" method="post" action="" commandName="para">
		<div class="ti">
			代码生成初始参数<font color="blue">标准版:action,service与接口,dao与接口.简化版:action,service,dao.其它类与JSP各版相同</font>
		</div>
		<input type="hidden" id="modelhid" value="${para.model}"> <input type="hidden" id="prjhid" value="${para.prj}">
		<input type="hidden" id="companyhid" value="${para.company}">
		<table class="SearchTab">
			<tr>
				<td align="right">开发者</td>
				<td><form:input path="author" class="text-b" /></td>
				<td align="right">项目名</td>
				<td><form:input path="prj" class="text-150" onblur="renamePack(this.id,'项目名')" /></td>
				<td align="right">模块名</td>
				<td><form:input path="model" class="text-150" onblur="renamePack(this.id,'模块名')" /></td>
				<td align="right">公司名</td>
				<td><form:input path="company" class="text-150" onblur="renamePack(this.id,'公司名')"  /></td>
			</tr>
			<tr>
				<td align="right">基本包名</td>
				<td><form:input path="packname" class="text-b" readonly="true" /></td>
				<td align="right">数据库类型</td>
				<td><form:input path="dbtype" type="text" class="text-150" readonly="true" /></td>
				<td align="right">代码分层</td>
				<td><form:radiobutton path="edition" value="1" />标准 <form:radiobutton path="edition" value="2" />简化</td>
				<td align="right">国际化</td>
				<td><form:radiobutton path="i18n" value="0" />否 <form:radiobutton path="i18n" value="1" />是</td>
			</tr>
			<tr>
				<td align="right">主键</td>
				<td><form:radiobutton path="PKey" value="1" />自增涨 <form:radiobutton path="PKey" value="2" />UUID<form:radiobutton path="PKey" value="3" />Oracle序列</td>
				<td align="right">代码类型</td>
				<td><form:radiobutton path="codeType" value="list" />列表 <form:radiobutton path="codeType" value="tree" />树型</td>
				<td align="right">WEB工程</td>
				<td> <form:radiobutton path="isWeb" value="1" />是<form:radiobutton path="isWeb" value="0" />否</td>
				<td align="right"><input type="button" value="查询" onclick="toAction('${webPath}/code/list')" /></td>
				<td><form:input path="table_name" class="text-150" /></td>
			</tr>
		</table>
		<table class="titleTab">
			<tr>
				<td class="ti">用户表列表</td>
				<td class="bu">
				<input type="button" value="清空自定义" onclick="clear_onclick()" />
				<input type="button" value="生成代码" onclick="add_onclick(getIds())" /></td>
			</tr>
		</table>
		<table class="dataTab">
			<tr>
				<th><input type="checkbox" id="chkAll" onclick="checkAll()">序号</th>
				<th>表名</th>
				<th>注释</th>
				<th>类名</th>
				<th>类中文名</th>
				<th>字段设置</th>
			</tr>
			<c:forEach items="${dataList}" var="wz" varStatus="vs">
				<tr>
					<td class="sqe_w"><input type="checkbox" name="chk" value="${vs.count}" />${vs.count}</td>
					<td><form:input type="hidden" path="tab_name" value="${wz['table_name']}" />${wz['table_name']}</td>
					<td>${wz['comments']}</td>
					<td><form:input type="text" path="class_name" value="${wz['class_name']}" class="text-b" /></td>
					<td><form:input type="text" path="cn_name" value="${wz['comments']}" class="text-b" /></td>
					<td onclick="detail_onclick('${wz['table_name']}','${vs.count}')"><form:input type="hidden" path="tab_hidden" />
						<div id="div_${wz['table_name']}">默认</div></td>
				</tr>
			</c:forEach>
		</table>
	</form:form>
</body>
<script type="text/javascript">
	function add_onclick(ids) {
		if (ids.length == 0) {
			alert("请至少选一张表！");
			return false;
		}
		toAction('${webPath}/code/gen')
	}
	function detail_onclick(table_name,idex) {
  		var  url,winHeight ;
		url = "${webPath}/code/listclo?tname=" + table_name+"&index="+idex;
		winHeight = top.document.documentElement.clientHeight-20;
		top.ShowWin("", url, "${webPath}", 1000, winHeight, 'win001');  
	}
	function clear_onclick(table_name,idex) {
		$("[id^=div_]").each(function(){
			 $(this).html("默认");
		});
		$('input:hidden[name="tab_hidden"]').each(function(){
			 $(this).val("");
		});
	}
	function renamePack(id,name){
		if($("#"+id).val()==""||$("#"+id).val().length < 3){
			alert(name+"不小于3个字符!");
			$("#"+id).val($("#"+id+"hid").val());	
			return false;
		}
		$("#packname").val("com."+$("#company").val()+"."+$("#prj").val()+"."+$("#model").val()+"." );
		$("#"+id+"hid").val($("#"+id).val());	
	}
	$("#packname").val("com."+$("#company").val()+"."+$("#prj").val()+"."+$("#model").val()+"." );
</script>
</html>
