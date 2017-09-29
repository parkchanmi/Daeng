<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
<link href="/DaengDaeng/css/myPageForm.css" rel="stylesheet" type="text/css">
<link href="/DaengDaeng/css/board_qna_list.css" rel="stylesheet" type="text/css">

<title>QnA게시판</title>

</head>
<body>
	<div id="wrapper">
		<jsp:include page="/menu.jsp"></jsp:include>
		<div id="contents_qna">
			<div class="board_list_qna">
						<div class="write_btn_qna">
						<center><h3>Q&A게시판</h3></center>
							<a href="/DaengDaeng/board/qna/writeForm.do?pageNum=${currentPage}">글쓰기</a>
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
							<tr height="35">
								<th align="center" width="30">번호</th>
								<th align="center" width="200">제 목</th>
								<th align="center" width="200">작성자</th>
								<th align="center" width="50">작성일</th>
								<th align="center" width="30">조회수</th>
							</tr>
						</thead>
						<c:forEach var="article" items="${articleList}">
				<tr height="30">
					<td align="center" width="50"><c:out value="${number}" /> <c:set var="number" value="${number - 1}" /></td>
					
					<td width="250"><c:if test="${article.board_level > 0}">
								<img src="images/level.gif" width="${10 * article.board_level}"
									height="16">
								<img src="images/re.gif">
							</c:if> <c:if test="${article.board_level == 0}">
								<img src="images/level.gif" width="${10 * article.board_level}"
									height="16">
							</c:if>
					<a href="/DaengDaeng/board/qna/qna_content.do?num=${article.board_num}&pageNum=${currentPage}">${article.board_title}</a></td>
					<td>${article.board_writer_name}</td>
					<td align="center" width="150"><fmt:formatDate value="${article.board_date}" type="date" pattern="yyyy-MM-dd" /></td>
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
							<a href=/DaengDaeng/board/qna/qna_list.do?pageNum=${startPage- 10 }">[이전]</a>
						</c:if>
			
						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<div class="page_num_qna">
								<a href="/DaengDaeng/board/qna/qna_list.do?pageNum=${i}">${i}</a>
							</div>
						</c:forEach>
			
						<c:if test="${endPage < pageCount}">
							<a
								href="/DaengDaeng/board/qna/qna_list.do?pageNum=${startPage + 10}">[다음]</a>
						</c:if>
					</c:if>
			<br/>
			<form method="get" name="search_form" action="/DaengDaeng/board/qna/qna_list.do">
				<select name="searchn">
					<option value="0">작성자</option>
					<option value="1">제목</option>
					<option value="2">내용</option>
				</select> <input type="text" name="search" size="15" maxlength="50" /> <input
					type="submit" value="검색" />
			</form>
				</div>
			</div>
		</div>
	
		<jsp:include page="/footer.jsp"></jsp:include>
	</div>
</body>
</html>
	