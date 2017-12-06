<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 
</head>
<body>
	<form:form id="form1" method="post" commandName="point" enctype="multipart/form-data">
	
		<form:input path="id"/>
		<form:input path="status"/>
		<form:input path="picture_path"/>
		<form:input path="picture_url"/>
		<form:input path="type"/>
		<div class="ti">卖点图修改</div>
		<table class="dataTab">
			<tr>
				<td class="right30">名称</td>
				<td class="left30"><form:input path="name" maxlength="255"  class="text-m" /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="name_td"><form:errors path="name" /></td>
			</tr>
			<tr>
				<td class="right30">顺序</td>
				<td class="left30"><form:input path="order_num" maxlength="10"  class="text-m" /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="order_num_td"><form:errors path="order_num" /></td>
			</tr>
			<tr>
				<td class="right30">标题</td>
				<td class="left30"><form:input path="title" maxlength="255"  class="text-m" /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="title_td"><form:errors path="title" /></td>
			</tr>

			<tr>
				<td class="right30">跳转地址</td>
				<td class="left30"><form:input path="jump_url" maxlength="255"  class="text-m" /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="jump_url_td"><form:errors path="jump_url" /></td>
			</tr>
			<tr>
				<td class="right30">所属终端</td>
				<td class="left30">${point.type_text}</td>
				<td class="left30_red"></td>
			</tr>
			<tr>
				<td class="right30">图片预览</td>
				<td class="left30"><img id="imgid" src="${point.picture_url}"></td>
				<td class="left30_red"> </td>
			</tr>
			<tr>
				<td class="right30">上传图片</td>
				<td class="left30"><input type="file" name="image" onchange="showImg(this,'imgid',38,38)"  /></td>
				<td class="left30_red"></td>
			</tr>
			<tr>
				<td class="right30">备注</td>
				<td class="left30"><form:textarea path="remark" maxlength="512"  class="tarea" />
					<div id="remark_div">最大长度为512个字节</div></td>
				<td class="left30_red" id="remark_td"></td>
			</tr>
		</table>
		<center class="btn_div">
			<input type="button" onclick="toSave();" value="保存"/>
			<input type="button" onclick="toAction('${webPath}/point/list?pageSize=${param.page_Size}&curPage=${param.cur_Page}');" value="返回"/>
		</center>
	</form:form>
</body>
<script type="text/javascript">
	function toSave() {//保存记录
		//请修改或替换如下验证方法
		checkBlank([['name', '名称'],['order_num', '顺序'],['title', '标题'],['jump_url', '跳转地址']]);
		if(!isNumber($("#order_num").val())){
			count++;
			$("#order_num_td").append("顺序号只能为数字");
		}
		if (count > 0) {
			count = 0;
			return false;
		}
		popupMasker();//弹出蔗遮罩层防止重复提交
		toAction("${webPath}/point/update");
	}
	//输入实时验证;1:文本框,下拉框2:日期框3:文本域
	realTimeCheck([[1,'name', '名称'],[1,'order_num', '顺序'],[1,'title', '标题'],[1,'jump_url', '跳转地址'],[3,'remark']	]);
</script>
</html>