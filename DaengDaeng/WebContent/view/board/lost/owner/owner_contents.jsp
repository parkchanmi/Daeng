<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>강아지 찾기</title>
<link href="/DaengDaeng/css/myPageForm.css" rel="stylesheet"
	type="text/css">

</head>



<body>
	<div id="wrapper">
		<jsp:include page="/menu.jsp"></jsp:include>
		<div id="contents_qnaRev">
			<div class="contents_qna_area">
				<form>
					<input type="hidden" name="lost_type" value="강아지 찾기">
					<ul class="qnaRev_ul">
						<li class="qnaRev_li"><label>글번호</label>
							<h3>${article.lost_code}</h3></li>
						<li class="qnaRev_li"><label>지역</label>
							<h3>
								${article.lost_loc1}
							</h3></li>
						<li class="qnaRev_li"><label>작성자 </label>
							<h3>${article.lost_writer_name}</h3></li>
						<li class="qnaRev_li"><label>작성일</label>
							<h3>${article.lost_date}</h3></li>
						<li class="qnaRev_li" style="width:100%"><label style="height:82px;">글내용</label>
							<h3>${article.lost_contents}</h3></li>
					</ul>

					<div class="btn_qnaRev">
					<c:if test="${memberDTO.mem_code==article.lost_writer }">
						<input type="button" value="글수정"
							onclick="document.location.href='/DaengDaeng/board/lost/owner/owner_updateForm.do?num=${article.lost_code}&pageNum=${pageNum}'">
						<input type="button" value="글삭제"
							onclick="document.location.href='/DaengDaeng/board/lost/owner/owner_deletePro.do?num=${article.lost_code}&pageNum=${pageNum}'">
						</c:if>
						<input type="button" value="글목록"
							onclick="document.location.href='/DaengDaeng/board/lost/owner/owner_list.do?pageNum=${pageNum}'">
							<a href="#" onclick="javascript:window.open('https://twitter.com/intent/tweet?text=[%EA%B3%B5%EC%9C%A0]%20' +encodeURIComponent(document.URL)+'%20-%20'+encodeURIComponent(document.title), 'twittersharedialog', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=300,width=600');return false;" target="_blank" alt="Share on Twitter" ><img src="./images/트위터.png">트위터</a>
                  <a href="#" onclick="javascript:window.open('https://www.facebook.com/sharer/sharer.php?u=' +encodeURIComponent(document.URL)+'&t='+encodeURIComponent(document.title), 'facebooksharedialog', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=300,width=600');return false;" target="_blank" alt="Share on Facebook" ><img src="./images/페이스북.png">페이스북</a>
                  <a href="#" onclick="javascript:window.open('https://story.kakao.com/s/share?url=' +encodeURIComponent(document.URL), 'kakaostorysharedialog', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes, height=400,width=600');return false;" target="_blank" alt="Share on kakaostory"> <img src="./images/카스.png">카카오스토리</a>
					</div>
				</form>
				<div class="contents_review_area">
					<table>
						<form method="post" name="review"
							action="/DaengDaeng/board/lost/owner/ownerReviewPro.do?num=${article.lost_code}&pageNum=${pageNum}">
							<div class="rev_title">
								<h2 class="line">작성자</h2>
								<span>${memberDTO.mem_name}</span>
							</div>
							<div class="rev_commbox">
								<textarea name="rev_contents" rows="10" cols="60"></textarea>
								<input type="submit" align=center value=리뷰작성>
								<input type=hidden name="rev_typenum" value="${article.lost_code}">
								<input type=hidden name="rev_typecode" value="강아지 찾기">
							</div>
						</form>
					</table>
					<table width="500" cellpadding="0" cellspacing="0" align="center">
					<thead style="background-color: #EE8300; color: #fff;">
						<tr height="23">
							<th align=center  width="50">작성자</th>
							<th align=center width="50">작성일</th>
							<th>댓글</th>
							<th>삭제</th>
						</tr>
					</thead>
					<c:forEach var="review_finder" items="${articleList}">
						<tr height="23">
							<td>${review_finder.rev_writer_name}</td>
							<td>${review_finder.rev_date}</td>
							<td>${review_finder.rev_contents}</td>
							<c:if test="${memberDTO.mem_code==review_finder.rev_memcode }">
							<td><input type="button" value="삭제"
								onclick="document.location.href='/DaengDaeng/board/lost/owner/ownerReviewDelete.do?num=${article.lost_code}&pageNum=${pageNum}&rev_num=${review_finder.rev_num}'"></td>
						</c:if>
						<c:if test="${memberDTO.mem_code!=review_finder.rev_memcode }">
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