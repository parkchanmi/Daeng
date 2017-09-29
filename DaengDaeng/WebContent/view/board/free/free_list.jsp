<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
<link href="/DaengDaeng/css/myPageForm.css" rel="stylesheet"
	type="text/css">
<link href="/DaengDaeng/css/board_qna_list.css" rel="stylesheet"
	type="text/css">

<title>자유게시판</title>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="/menu.jsp"></jsp:include>
		<div id="contents_qna">
			<div class="board_list_qna">
				<center><h3>자유게시판</h3></center>
				<div class="write_btn_qna">
					<a href="/DaengDaeng/board/free/free_writeForm.do">글쓰기</a> <input
						type="hidden" name="board_type" value="자유">
				</div>

				<c:if test="${count == 0}">
					<table width="700" border="1" cellpadding="0" cellspacing="0">
						<tr>
							<td align="center">게시판에 저장된 글이 없습니다.</td>
						</tr>
					</table>
				</c:if>

				<c:if test="${count > 0}">
					<table width="990" cellpadding="0" cellspacing="0" align="center">
						<thead style="background-color:#EE8300;  color:#fff;">	
							<<tr height="30">
							<td align="center" width="50">번호</td>
							<td align="center" width="250">글제목</td>
							<td align="center" width="150">작성자</td>
							<td align="center" width="50">작성일</td>
							<td align="center" width="50">조회수</td>
							</tr>
						</thead>
							<c:forEach var="article" items="${articleList}">
								<tr height="30">
									<td align="center" width="50"><c:out value="${number}" />
										<c:set var="number" value="${number - 1}" /></td>
									<td width="250"><a
										href="/DaengDaeng/board/free/free_content.do?num=${article.board_num}&pageNum=${currentPage}">${article.board_title}</a></td>
									<td>${article.board_writer_name}</td>
									<td align="center" width="150"><fmt:formatDate
											value="${article.board_date}" type="date"
											pattern="yyyy-MM-dd" /></td>
									<td align="center" width="50">${article.board_rdcount}</td>
								</tr>
							</c:forEach>
					</table>
				</c:if>
				<div class="paging_qna">
				<c:if test="${count > 0}">
					<c:set var="pageCount"
						value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}" />
					<c:set var="pageBlock" value="${10}" />
					<fmt:parseNumber var="result" value="${currentPage / 10}"
						integerOnly="true" />
					<c:set var="startPage" value="${result * 10 + 1}" />
					<c:set var="endPage" value="${startPage + pageBlock-1}" />
					<c:if test="${endPage > pageCount}">
						<c:set var="endPage" value="${pageCount}" />
					</c:if>

					<c:if test="${startPage > 10}">
						<a
							href=/DaengDaeng/board/free/free_list.do?pageNum=${startPage-10}">[이전]</a>
					</c:if>

					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<a href="/DaengDaeng/board/free/free_list.do?pageNum=${i}">[${i}]</a>
					</c:forEach>

					<c:if test="${endPage < pageCount}">
						<a
							href="/DaengDaeng/board/free/free_list.do?pageNum=${startPage + 10}">[다음]</a>
					</c:if>
				</c:if>
				<form>
					<form method="get" name="search" action="/DaengDaeng/board/free/free_list.do">
						<select name="searchn">
							<option value="0">작성자</option>
							<option value="1">제목</option>
							<option value="2">내용</option>
						</select> <input type="text" name="search" size="15" maxlength="50" /> <input
							type="submit" value="검색" />
					</form>
			</form>
			</div>
			</div>
		</div>
			<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>