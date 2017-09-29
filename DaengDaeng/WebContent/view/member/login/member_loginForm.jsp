<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
<link href="/DaengDaeng/css/myPageForm.css" rel="stylesheet" type="text/css">

<title>로그인</title>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="/menu.jsp"></jsp:include>
		<!--로그인 main -->
		<div id="contents_mypage">
			<div class="mypageform">
				<div class="mypage_title">
					<h2>Login</h2>
				</div>
				<div class="owner_form">
					<form name="loginform" action="/DaengDaeng/member/login/member_loginPro.do">
						<ul class="mp_modify_pwForm">
							<li><label for="mem_email">ID</label>
								<input type="text" name="mem_email" size="15" maxlength="20"></li>
							<li><label for="password">PW</label>
								<input type="password" name="mem_pw" size="15" maxlength="15">
							</li>
						</ul>
						<div class="btn_area_loginbox">
							<input type="submit" value="로그인" >
							<input type="reset" value="다시입력"> 
							<input type="button" value="E-mail &nbsp;/&nbsp; PW찾기" onClick="javascript:location.href='/DaengDaeng/member/login/member_findemail.do';">
						</div>
					</form>
				</div>	
			</div>
		</div>
		<jsp:include page="/footer.jsp"></jsp:include>
	</div>
</body>

</body>
</html>