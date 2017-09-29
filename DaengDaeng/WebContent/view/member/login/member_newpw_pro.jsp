<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>새로운 비밀번호 변경 </title>
</head>
<body>

<c:if test="">

</c:if>

<c:choose>
    <c:when test="${result==0}">
       	비밀번호변경 안됨.
    </c:when>
    
    <c:otherwise>
    	비밀번호 변경 완료
    </c:otherwise>
     
   
 </c:choose>   
   <input type="button" value="로그인" onClick="javascript:location.href='/DaengDaeng/member/login/member_loginForm.do';">

</body>
</html>