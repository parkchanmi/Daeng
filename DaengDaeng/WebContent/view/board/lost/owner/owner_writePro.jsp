<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${result==0 }">
실패
</c:if>
<c:if test="${result==1 }">
<meta http-equiv="Refresh" content="0;url=/DaengDaeng/board/lost/owner/owner_list.do">
</c:if>
