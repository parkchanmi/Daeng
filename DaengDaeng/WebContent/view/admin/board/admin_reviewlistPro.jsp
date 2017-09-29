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
alert('삭제완료');</script>
	<meta http-equiv="Refresh" content="0;url=/DaengDaeng/admin/board/admin_reviewlistForm.do" >
</c:if>

</body>
</html>