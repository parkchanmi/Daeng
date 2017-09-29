<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>qna질문삭제</title>
</head>
<body>
	<form method="post" name="writeform"
		action="/DaengDaeng/board/qna/deletePro.do?pageNum=${pageNum}">
		<input type="hidden" name="board_num" value="${article.board_num}">
		<input type="hidden" name="board_ref" value="${article.board_ref}">
		<input type="hidden" name="board_step" value="${article.board_step}">
		<input type="hidden" name="board_level" value="${article.board_level}">
		<table width="400" border="1" cellspacing="0" cellpadding="0"
			align="center">
			<tr>
				<td align="left">분류</td>
				<td>${article.board_type}</td>
			</tr>
			<tr>
				<td width="70" align="center">작성자</td>
				<td>${article.board_writer_name}</td>
			</tr>
			<tr>
				<td width="70" align="center">제 목</td>
				<td align="left" width="330"><input type="text" size="40"
					maxlength="50" name="board_title" value="${article.board_title}"></td>
			</tr>
			<tr>
				<td width="70" align="center">내 용</td>
				<td align="left" width="330"><textarea name="board_contents"
						rows="13" cols="40">${article.board_contents}</textarea></td>
			</tr>
			<tr height="30">
				<td align=center"><input type="submit" value="글삭제">
				<td align=center"><input type="button" value="글목록"
					onclick="document.location.href='/DaengDaeng/board/qna/qna_list.do?pageNum=${pageNum}'">
				</td>
			</tr>
		</table>
</body>
</html>