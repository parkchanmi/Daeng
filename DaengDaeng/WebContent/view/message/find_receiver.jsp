<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>ID중복확인</title>
<link href="style/style.css" rel="stylesheet" type="text/css">
<% request.setCharacterEncoding("utf-8"); %>

</head>
<body>
<c:if test="${memcode==-1}"><!-- 없는 회원 -->
<script>
opener.document.write_message.find_receiver.value="f";
</script>
<table width="270" border="1" cellspacing="0" cellpadding="5">
<tr><td height="39">회원이 존재하지 않습니다.</td></tr>
<tr><td><input type="button" value="닫기" onclick="javascript:self.close();"></td></tr>
</table>
</c:if>
<c:if test="${memcode!=-1}">
<script>
opener.document.write_message.find_receiver.value="t";
</script>
<table width="270" border="1" cellspacing="0" cellpadding="5">
<tr><td align="center">존재하는 회원입니다.</td></tr>
<tr><td><input type="button" value="닫기" onclick="javascript:self.close();"></td></tr>
</table>
</c:if>
</body>
</html>
