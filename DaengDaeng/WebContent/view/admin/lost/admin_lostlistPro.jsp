<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<c:if test="${result==0}">
	<a href="javascript:history.go(-1)">[돌아가기]</a>
</c:if>
<c:if test="${result==1}">
	<script>
	alert('쪽지발송완료!');
	</script>
	<meta http-equiv="Refresh" content="0;url=/DaengDaeng/admin/lost/admin_lostlistForm.do" >
	
</c:if>
</body>
</html>