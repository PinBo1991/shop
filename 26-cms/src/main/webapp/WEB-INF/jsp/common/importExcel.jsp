<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>EXCEL导入</title>
<%--author:yw-GZZ,DATE:2014-01-30 03:07:58--%>
</head>
<script type="text/javascript">
	var is_submit=false;
	function insert_onclick() {//保存记录
		form1.action = "${webPath}/${param.action}?title=${param.title}&action=${param.action}";
		notBlank("file", "文件名");//非空验证
		extension();//扩展名验证
		if (count > 0) {
			count = 0;
			return false;
		}
		if(is_submit==false){
			is_submit=true;
			$("#file_td").html("正在导入,请耐心等待!");
			form1.submit();
		}else{
			$("#file_td").html("正在导入,请耐心等待!");
			alert("别急!!!");
		}
	}
	function extension(){//扩展名验证
	    var file= $("#file").val();
	    var extension = file.substr(file.indexOf("."));
		if (extension.toLowerCase()!=".xls") {
			$("#file_td").html($("#file_td").html()+" 文件格式只能是Excel97-2003工作簿,扩展名为[.xls]!");
			count++;
		}	
	}
</script>
<body>
	<form name="form1" method="post" enctype="multipart/form-data">
		<div class="ti">${param.title}</div>
		<table class="dataTab">
			<tr>
				<td align="right" >文件名</td>
				<td><input type="file" id="file" name="file" maxlength="50" size="70"/><font color="#CE0000">*</font></td>
			</tr>
			<tr>
				<td></td>
				<td style="color:#CE0000;" id="file_td">${msg}</td>
			</tr>
		</table>
		<center class="btn_div">
			<input type="button" icon="icon-save" onclick="insert_onclick()" value="导入" />
			<input type="button" icon="icon-retry" onclick="top.dhxWins.window('win001').close();" value="关闭" />
		</center>
	</form>
</body>
</html>