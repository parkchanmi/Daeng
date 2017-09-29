<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>로그인</title>
</head>
<body>
<!--아이디있으면 메인으로 //없으면 로그인폼으로  -->
<c:choose>
    <c:when test="${memberDTO==null}">
       	 회원정보가 없습니다.
       	 <a href="javascript:history.go(-1)">[로그인폼으로 돌아가기]</a>
    </c:when>
    <c:otherwise>
    	<meta http-equiv="Refresh" content="0;url=/DaengDaeng/main.do" >
    </c:otherwise>
</c:choose>
</body>
</html>