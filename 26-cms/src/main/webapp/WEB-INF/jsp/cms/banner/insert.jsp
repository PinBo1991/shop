<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<form:form name="form1" id="form1" method="post" modelAttribute="banner" enctype="multipart/form-data" >
		<div class="ti">轮播图新增</div>
		<table class="dataTab">
			<tr>
				<td class="right30">轮播图名称</td>
				<td class="left30"><form:input path="name" maxlength="50" class="text-m" /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="name_td"><form:errors path="name" /></td>
			</tr>
			<tr>
				<td class="right30">顺序</td>
				<td class="left30"><form:input path="order_num" maxlength="50" class="text-m" /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="order_num_td"></td>
			</tr>
			<tr>
				<td class="right30">跳转地址</td>
				<td class="left30"><form:input path="jump_url" maxlength="50" class="text-m" /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="jump_url_td"></td>
			</tr>
			<tr>
				<td class="right30">所属终端</td>
				<td class="left30"><form:select path="type"  class="sel"  items="${typeMap}" /></td>
				<td class="left30_red"></td>
			</tr>
			<tr>
				<td class="right30">上传图片</td>
				<td class="left30"><input type="file" name="image" /></td>
				<td class="left30_red"></td>
			</tr>			
			<tr>
				<td class="right30">备注</td>
				<td class="left30"><form:textarea path="remark" maxlength="50" class="tarea" /></td>
				<td class="left30_red"></td>
			</tr>								
		</table>
		<center class="btn_div">
			<input type="button" class="bnt" onclick="insert_onclick()" value="保存"/>
			<input type="button" class="bnt" onclick="toAction('${webPath}/banner/list');"	value="返回"/>
		</center>
	</form:form>
</body>
<script type="text/javascript">
realTimeCheck([[1,'name','轮播图名称'],[1,'order_num','顺序'],[1,'jump_url','跳转地址']]);//实时非空验证
function insert_onclick() {//保存记录
	//notBlank('name','轮播图名称');
	checkBlank([['name','轮播图名称'],['order_num','顺序'],['jump_url','跳转地址']]);//非空验证方法
	if (count > 0) {//存在验证未通过的项
		count = 0;
		return false;
	}
	toAction('${webPath}/banner/insert');
}
</script>
</html>