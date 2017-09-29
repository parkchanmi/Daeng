<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
insert처리
<!--아이디있으면 메인으로 //없으면 로그인폼으로  -->
<c:if test="${result==0}">
	<a href="javascript:history.go(-1)">[돌아가기]</a>
</c:if>
<c:if test="${result==1}">
	<meta http-equiv="Refresh" content="0;url=/DaengDaeng/member/mypage/myPageForm.do" >
</c:if>

</body>
</html>