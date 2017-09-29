<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>강아지 찾기 삭제</title>
</head>
<body>
	<br>
	<form method="POST" name="owner_deleteForm"
		action="/DaengDaeng/board/lost/owner/owner_deletePro.do?pageNum=${pageNum}">
		<input type="hidden" name="lost_code" value="${article.lost_code}">
		<table width="400" border="1" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td width="70" align="center">작성자</td>
			<td>${article.lost_writer_name}</td>
		</tr>
		<tr>
			<td>loc1</td>
			<td><select name="lost_loc1"><option values="서울">서울</option></select></td>
		</tr>
		<tr>
			<td>loc2</td>
			<td><select name="lost_loc2"><option values="서울">서울</option></select></td>
		</tr>
		<tr>
			<td width="70" align="center">내 용</td>
			<td align="left" width="330"><textarea name="lost_contents"
					rows="13" cols="40">${article.lost_contents}</textarea></td>
		</tr>

		<tr height="30">
			<td align=center"><input type="submit" value="글삭제"> <input
				type="button" value="글목록"
				onclick="document.location.href='/DaengDaeng/board/lost/owner/owner_list.do?pageNum=${pageNum}'">
			</td>
		</tr>
		</table>
	</form>
</body>
</html>