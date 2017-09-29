<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="/DaengDaeng/css/myPageForm.css" rel="stylesheet"
	type="text/css">
<title>자유게시판</title>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="/menu.jsp"></jsp:include>
		<div id="contents_qnaRev">
			<div class="contents_qna_area">
				<form style="overflow: hidden">
					<ul class="qnaRev_ul">
						<li class="qnaRev_li"><label>글분류 </label>
							<h3>${article.board_type}</h3></li>
						<li class="qnaRev_li"><label>글번호</label>
							<h3>${article.board_num}</h3></li>
						<li class="qnaRev_li"><label>조회수</label>
							<h3>${article.board_rdcount}</h3></li>
						<li class="qnaRev_li"><label>작성자 </label>
							<h3>${article.board_writer_name}</h3></li>
						<li class="qnaRev_li"><label>작성일</label>
							<h3>${article.board_date}</h3></li>
						<li class="qnaRev_li"><label>글제목</label>
							<h3>${article.board_title}</h3></li>
						<li class="qnaRev_li"><label>글내용</label>
							<h3>${article.board_contents}</h3></li>
					</ul>
					<div class="btn_qnaRev">
					<c:if test="${memberDTO.mem_code==article.board_writer }">
						<input type="button" value="글수정"
							onclick="document.location.href='/DaengDaeng/board/free/free_updateForm.do?num=${article.board_num}&pageNum=${pageNum}'">
						&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="글삭제"
							onclick="document.location.href='/DaengDaeng/board/free/free_deletePro.do?num=${article.board_num}&pageNum=${pageNum}'">
					</c:if>
						&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="글목록"
							onclick="document.location.href='/DaengDaeng/board/free/free_list.do?pageNum=${pageNum}'">
					</div>
				</form>
				<div class="contents_review_area">
					<table>
						<form method="post" name="review"
							action="/DaengDaeng/board/free/free_reviewPro.do?num=${article.board_num}&pageNum=${pageNum}">
							<div class="rev_title">
								<h2 class="line">작성자</h2>
								<span>${memberDTO.mem_name}</span>
							</div>
							<div class="rev_commbox">
								<textarea name="rev_contents" rows="10" cols="60"></textarea>
								<input type=submit align=center value=리뷰작성> <input
									type=hidden name="rev_typenum" value="${article.board_num}">
								<input type=hidden name="rev_typecode" value="자유">
							</div>
						</form>
					</table>
					<table width="500" cellpadding="0" cellspacing="0" align="center">
						<thead style="background-color: #EE8300; color: #fff;">
							<tr height="23">
								<th align=center width="50">작성자</th>
								<th align=center width="50">작성일</th>
								<th>댓글</th>
								<th>삭제</th>
							</tr>
						</thead>
						<c:forEach var="review" items="${articleList}">

							<tr height="23">
								<td>${review.rev_writer_name}</td>
								<td>${review.rev_date}</td>
								<td>${review.rev_contents}</td>
								<c:if test="${memberDTO.mem_code==review.rev_memcode}">
								<td><input type="button" value="삭제"
									onclick="document.location.href='/DaengDaeng/board/free/free_reviewdeletePro.do?num=${article.board_num}&pageNum=${pageNum}&rev_num=${review.rev_num}'">
								</td>
								</c:if>
								<c:if test="${memberDTO.mem_code!=review.rev_memcode}">
								<td>&nbsp;</td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<jsp:include page="/footer.jsp"></jsp:include>
	</div>
</body>
</html>