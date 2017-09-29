<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>이메일찾기</title>

<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
<link href="/DaengDaeng/css/myinfo_find.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<script>
		function checkIt() {

			var userinput = eval("document.userinput");

			if (!userinput.mem_name.value) {
				alert("닉네임을 입력하세요");
				return false;
			}

			if (!userinput.hp1.value || !userinput.hp2.value
					|| !userinput.hp3.value) {
				alert("핸드폰번호를 입력하세요");
				return false;
			}
			return true;
		}

		function onlyNumber() {
			if ((event.keyCode < 48) || (event.keyCode > 57)) {
				event.returnValue = false;
			}

		}
	</script>
<body>
	<div id="wrapper">
		<jsp:include page="/menu.jsp"></jsp:include>
		<!--로그인 main -->
		<div id="contents_myinfo_find">
			<div class="myinfo_find_box">
				<div class="myinfo_find_title">
						<h2>E-mail&nbsp;/&nbsp; pw 찾기</h2>
				</div>
				<div class="myinfo_find_type">
					<input type="button" value="이메일찾기" class="find_email" onClick="javascript:location.href='/DaengDaeng/member/login/member_findemail.do';">
					<input type="button" value="PW찾기" onClick="javascript:location.href='/DaengDaeng/member/login/member_changepw.do';">
				</div>
				<div class="myinfo_find_area">
					<form method="post"
						action="/DaengDaeng/member/login/member_findemailPro.do"
						name="userinput" onsubmit="return checkIt()">
						<ul class="mp_modify_pwForm">
							<li><label for="mem_email">닉네임</label> <input type="text"
								name="mem_name"></li>
							<li><label for="hp">핸드폰</label> <select name="hp1">
									<option value="010">010</option>    
									<option value="011">011</option>
							</select> - <input type="text" name="hp2" size="4" maxlength="4"
								onkeypress="onlyNumber()"> - <input type="text"
								name="hp3" size="4" maxlength="4" onkeypress="onlyNumber()">
	
							</li>
						</ul>
						<div class="btn_myinfo_next">
							<input type="submit" name="confirm" value="찾기">
						</div>
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="/footer.jsp"></jsp:include>
	</div>
</body>
</html>