<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>반려견 커뮤니티</title>
<link href="../../css/main.css" rel="stylesheet" type="text/css">
<link href="../../css/myPageForm.css" rel="stylesheet" type="text/css">

<script>


	$(document).ready(function() {
		$(".btn_area").click(function(event) {
			//	비밀번호 유효성 체크
			var pwd = $("#mem_pw").val();
			var pwd1 = $("#mem_pw02").val();

			if (pwd != pwd1) { //	비밀번호 와 비밀번호 확인이 다르다면 ... 
				alert("비밀번호 잘못 입력");
				return false;
			} else {
				alert("입력성공");
			}
		});
	});
</script>

</head>
<body>
	<div id="wrapper">
		<jsp:include page="/menu.jsp"></jsp:include>

		<div id="contents_mypage">
			<div class="mypageform">
				<div class="mypage_title">
					<h2>My Page</h2>
				</div>
				<div class="owner_form">
					<form method="post" enctype="multipart/form-data" name="writeform" action="/DaengDaeng/member/mypage/mp_modify_dogsaddpro.do">
						<ul>
						<%-- <input type="hidden" name="dog_memcode" value="${dog_data.dog_memcode}"> --%>
							<li><label>이 름</label>
								<input type="text" name="dog_name" size="15" maxlength="15" />
							</li>
							<li><label>견 종</label>
								<input type="text" name="dog_kind" size="15" maxlength="15" />
							</li>
							<li><label>성 별</label>
								<input type="text" name="dog_gender" size="15" maxlength="15" />
							</li>
							<li><label>나 이</label> 
								<input type="text" name="dog_age" size="15" maxlength="15" />
							</li>
							<li><label>사 진</label>
								<input type="file" name="dog_file" /></li>
						</ul>
						<input type="submit" value="수정" class="btn_area02" onSubmit="return checkIt()">
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="/footer.jsp"></jsp:include>
	</div>
</body>
</html>