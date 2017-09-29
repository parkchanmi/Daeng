<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
<link href="/DaengDaeng/css/myPageForm.css" rel="stylesheet" type="text/css">

<title>Insert title here</title>
</head>


<script>
	
function checkIt(){
	
	var userinput = eval("document.userinput");
	
	if(!userinput.mem_email.value){
		alert("Email을 입력하세요");
		return false
	}
	var regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;   
	 var email = userinput.mem_email.value;
	if(regex.test(email) === false) {  
	    alert("잘못된 이메일 형식입니다.");  
	    return false;  
	} 

	
	if(!userinput.mem_pw.value){
		alert("비밀번호를 입력하세요");
		return false;
	}
	if(userinput.mem_pw.value != userinput.mem_pw2.value){
		alert("비밀번호를 동일하게 입력하세요");
		return false;
	}

	if(!userinput.mem_name.value){
		alert("닉네임을 입력하세요");
		return false;
	}

	if(!userinput.mem_gender.value){
		alert("성별을 선택하세요");
		return false;
	}

	if(!userinput.mem_age.value){
		alert("연령대를 선택하세요");
		return false;
	}
	if(!userinput.hp1.value  || !userinput.hp2.value || !userinput.hp3.value ){
		alert("핸드폰번호를 입력하세요");
		return false;
	}
	if(!userinput.mem_loc.value){
		alert("지역을 선택하세요");
		return false;
	}

	if(!userinput.mem_visit.value){
		alert("유입경로를 선택하세요");
		return false;

	}
	if(userinput.mem_emailcheck.value == 0){
		alert("이메일중복 확인하세요");
		return false;
	}

	if(userinput.email_auth.value == 0){
		alert("이메일인증을 완료하세요");
		return false;
	}
	return true;
	}
	

	//닉네임중복여부 판단
    function openConfirmmem_name(userinput) {//userinput에 this.form 저장되어있다.
        // 이메일을 입력했는지 검사
        if (userinput.mem_name.value == "") {//빈문자열이랑 같다, 입력안했다.
            alert("닉네임을 입력하세요");
            return;
        }
        // url과 사용자 입력 id를 조합합니다.
        url = "/DaengDaeng/member/join/confirmmem_name.do?mem_name=" + userinput.mem_name.value ;
       
        // 새로운 윈도우를 엽니다.
        open(url, "confirm", "toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300,height=200");
    }
	
	
    function openConfirmmem_email(userinput) {//userinput에 this.form 저장되어있다.
        // 이메일을 입력했는지 검사
        if (userinput.mem_email.value == "") {//빈문자열이랑 같다, 입력안했다.
            alert("이메일을 입력하세요");
            return;
        }
        // url과 사용자 입력 id를 조합합니다.
        url = "/DaengDaeng/member/join/confirmmem_email.do?mem_email=" + userinput.mem_email.value ;
       
        // 새로운 윈도우를 엽니다.
        open(url, "confirm", "toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300,height=200");
    }
	
	//숫자만 입력가능
    function onlyNumber()
    {
      if((event.keyCode < 48)||(event.keyCode > 57))
      {
         event.returnValue=false;
      }
    }
    function email_send(userinput){
		var auth="";
		var email=userinput.mem_email.value;
		if(userinput.mem_emailcheck.value==0){
			alert("이메일 중복확인을 하세요");  
		    return;
		}
		for(var i=0;i<5;i++){
			auth += parseInt(Math.random()*(9+1));
		} //인증번호생성
		alert(auth);
		
		var popUrl = "/DaengDaeng/member/join/auth_email.do?authcode="+auth+"&to_email="+email;	//팝업창에 출력될 페이지 URL
		var popOption = "width=400, height=300, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)

		window.open(popUrl,"",popOption);

	}

	

</script>
<body>

<body>
	<div id="wrapper">
		<jsp:include page="/menu.jsp"></jsp:include>
		<!--로그인 main -->
		<div id="contents_mypage">
			<div class="mypageform">
				<div class="mypage_title">
					<h2>일반회원</h2>
				</div>
				<div class="member_choice">
					<form method="post" action="member_normal_pro.do" name="userinput" onsubmit="return checkIt()">
						<ul class="mp_modify_pwForm">
							<li><label>E-mail</label>
								 <input type="email" id="mem_email" name="mem_email" size="15" maxlength="30">
								<div class="btn_area03">
									<input type="button" name="confirm_email" value="중복확인"   class="btn_confirm01" OnClick="openConfirmmem_email(this.form)">	
									<input type="hidden" name="mem_emailcheck" value="0">
								 </div>
							</li>
							<li><label>인증번호</label>
							
								<input type="button" id="auth_confirm" value="인증번호 전송" onclick="email_send(this.form)">
        						<span id="auth_ok" style="display:none;">인증성공</span>
        						<input type="hidden" name="email_auth" value="0">
        						
							</li>
							<li><label for="password">비밀번호</label>
								<input type="password" name="mem_pw"
									size="15" maxlength="12">
							</li>
							<li><label for="password">비밀번호 확인</label>
								<input type="password" name="mem_pw2" size="15" maxlength="12">
							</li>
							<li><label>닉네임</label>
								<input type="text" name="mem_name" size="15" maxlength="10"> 
								<div class="btn_area03">
									<input type="button" name="confirm_name" value="중복확인"  class="btn_confirm" OnClick="openConfirmmem_name(this.form)">
									<input type="hidden" name="mem_namecheck" value="0">
								</div>
							</li>
							<li><label>성별</label>
								<input type="radio" id="radio"name="mem_gender" value="남">
								<span>남</span>
								<input type="radio" name="mem_gender" value="여">
								<span>여</span>
							</li>
							<li><label>연령대</label>
								<input type="radio" name="mem_age" value="10대">
								<span>10대</span>
								<input type="radio" name="mem_age" value="20대">
								<span>20대</span>. 
								<input type="radio" name="mem_age" value="30대">
								<span>30대</span> 
								<input type="radio" name="mem_age" value="40대">
								<span>40대</span> 
								<input type="radio" name="mem_age" value="50대이상">
								<span>50대이상</span>
							</li>
							<li><label>핸드폰</label>
								<select name="hp1" id="hp_1">
									<option value="010">010</option>    
									<option value="011">011</option>
							</select> - <input type="text_num" name="hp2" size="4" maxlength="4"
								onkeypress="onlyNumber()"> - <input type="text_num" name="hp3"
								size="4" maxlength="4" onkeypress="onlyNumber()">
							</li>
							<li><label>지역</label>
								<select name="mem_loc">
										<option value="서울특별시">서울특별시</option>    
										<option value="인천광역시">인천광역시</option>
										<option value="경기도">경기도</option>
										<option value="강원도">강원도</option>
										<option value="경상북도">경상북도</option>
										<option value="경상남도">경상남도</option>
										<option value="광주광역시">광주광역시</option>
										<option value="대구광역시">대구광역시</option>
										<option value="대전광역시">대전광역시</option>
										<option value="부산광역시">부산광역시</option>
										<option value="울산광역시">울산광역시</option>
										<option value="전라북도">전라북도</option>
										<option value="전라남도">전라남도</option>
										<option value="제주도">제주도</option>
										<option value="충청북도">충청북도</option>
										<option value="충청남도">충청남도</option>
									</select>
							</li>
							<li><label>유입경로</label>
								<input type="radio" name="mem_visit" value="검색">
								<span>검색</span>
								<input type="radio" name="mem_visit" value="지인추천">
								<span>지인추천</span>
								<input type="radio" name="mem_visit" value="광고">
								<span>광고</span>
								<input type="radio" name="mem_visit" value="SNS"> 
								<span>SNS</span>
								<input type="radio" name="mem_visit" value="기타">
								<span>기타</span>
							</li>
						</ul>
						<div class="btn_area_join">
							<input type="submit" name="confirm" value="가  입" class="btn_dogs_modify"> 
							<input type="reset" name="reset" value="다시입력" class="btn_dogs_delete">
						</div>
						
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="/footer.jsp"></jsp:include>
	</div>
</body>
</html>