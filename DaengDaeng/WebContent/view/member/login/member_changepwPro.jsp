<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
<link href="/DaengDaeng/css/myinfo_find.css" rel="stylesheet"
	type="text/css">
<title>로그인</title>
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
						<c:when test="${memberCode==-1}">
					       	 회원정보가 없습니다.
					    </c:when>

						<c:otherwise>
							<div class="myinfo_change_area">
								<form method="post" action="member_newpwpro.do" name="userinput"onsubmit="return checkIt()">
									<input type="hidden" name="mem_code"
										value="${memberCode}">
									<ul class="mp_modify_pwForm">
										<li><label>새로운 비밀번호</label><input type="password"
											name="mem_pw" size="15" maxlength="12"></li>
										<li><label>비밀번호 확인</label><input type="password"
											name="mem_pw2" size="15" maxlength="12"></li>
									</ul>
									<div class="btn_myinfo_next">
										<input type="submit" name="confirm" value="찾기">
									</div>

								</form>
							</div>
						</c:otherwise>

					</c:choose>
				</div>
			</div>
		</div>

		<jsp:include page="/footer.jsp"></jsp:include>
	</div>



</body>
</html>