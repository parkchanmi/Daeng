<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${result==0}"><!-- 전송실패 -->
생성실패
<a href="javascript:history.go(-1)">[생성폼으로 돌아가기]</a>
</c:if>
<c:if test="${result==1}">
<meta http-equiv="Refresh" content="0;url=/DaengDaeng/club/club_list.do" >
</c:if>
