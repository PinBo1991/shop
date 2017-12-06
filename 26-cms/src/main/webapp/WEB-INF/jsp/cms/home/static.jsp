<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<form:form name="form1" id="form1" method="post" modelAttribute="banner" enctype="multipart/form-data" >
		<input type="button" class="bnt" onclick="runStatic()" value="执行静态化"/>
 	</form:form>
</body>
<script type="text/javascript">
function runStatic() {//保存记录
	toAction('${webPath}/home/runStatic');
}
</script>
</html>