<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
<link href="/DaengDaeng/css/myinfo_find.css" rel="stylesheet"
	type="text/css">
<title>이메일찾기</title>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="/menu.jsp"></jsp:include>
		<!--로그인 main -->
		<div id="contents_myinfo_find">
			<div class="myinfo_find_box">
				<div class="myinfo_find_title">
					<h2>E-mail&nbsp;/&nbsp; pw 찾기</h2>
				</div>

				<div class="myinfo_find_area">

					<c:choose>
						<c:when test="${memberEmail==null}">
					       	<h3>회원정보가 없습니다.</h3> 
					    </c:when>

						<c:otherwise>
					    	<h3>회원님의 이메일은
					    	${memberEmail}
					    	입니다.</h3>
					    </c:otherwise>
					</c:choose>

					<div class="btn_myinfo_next_email">
						<input type="button" value="로그인"
							onClick="javascript:location.href='/DaengDaeng/member/login/member_loginForm.do';">
						<input type="button" value="PW찾기"
							onClick="javascript:location.href='/DaengDaeng/member/login/member_changepw.do';">
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/footer.jsp"></jsp:include>
	</div>
</body>
</html>