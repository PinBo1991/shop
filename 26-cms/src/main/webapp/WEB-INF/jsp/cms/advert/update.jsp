<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<form:form name="form1" id="form1" method="post" modelAttribute="advert" enctype="multipart/form-data" >
		<form:input path="id"/>
		<form:input path="status"/>
		<form:input path="picture_path"/>
		<form:input path="picture_url"/>
		<form:input path="type"/>
		<div class="ti">广告位修改</div>
		<table class="dataTab">
			<tr>
				<td class="right30">广告位名称</td>
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
				<td class="left30">${type_name}</td>
				<td class="left30_red"></td>
			</tr>
			<tr>
				<td class="right30">图片预览</td>
				<td class="left30"><img id="imgid" src="${advert.picture_url}" style="width: 200px;height: 120px"></td>
				<td class="left30_red"></td>
			</tr>			
			<tr>
				<td class="right30">上传图片</td>
				<td class="left30"><input type="file" name="image"  onchange="showImg(this,'imgid')" /></td>
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
			<input type="button" class="bnt" onclick="toAction('${webPath}/advert/list');"	value="返回"/>
		</center>
	</form:form>
</body>
<script type="text/javascript">
realTimeCheck([[1,'name','广告位名称'],[1,'order_num','顺序'],[1,'jump_url','跳转地址']]);//实时非空验证
function insert_onclick() {//保存记录
	//notBlank('name','轮播图名称');
	checkBlank([['name','广告位名称'],['order_num','顺序'],['jump_url','跳转地址']]);//非空验证方法
	if(!isNumber($("#order_num").val())){
		count++;
		$("#order_num_td").append("顺序号只能为数字");
	}
	if (count > 0) {//存在验证未通过的项
		count = 0;
		return false;
	}
	toAction('${webPath}/advert/update');
}
</script>
</html>