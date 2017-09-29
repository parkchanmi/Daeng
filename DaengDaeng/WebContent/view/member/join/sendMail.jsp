<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>로그인</title>
</head>
<script>
function check_auth(realauth){
	var userinput = eval("document.auth_form");
	var userauth=userinput.acc.value;
	if(realauth==userauth){
		opener.document.userinput.email_auth.value="1";//hidden값 변경
		var con = opener.document.getElementById("auth_ok");
		con.style.display = 'inline';//인증성공메시지
		opener.document.getElementById("auth_confirm").disabled = 'true';//버튼비활성화
		opener.document.getElementById("mem_email").readOnly  = 'true';//입력비활성 -> 폼전송가능
		opener.document.getElementById("confirm_email").disabled = 'true';//버튼비활성화
		self.close();
		return false;
		
	}else{
		alert('인증실패');
		return true;
	}

}
</script>
<body>
<!--아이디있으면 메인으로 //없으면 로그인폼으로  -->
<c:choose>
    <c:when test="${result=='Send Mail Failed..'}">
       	인증 오류! 다시 시도하세요.
       	 <a href="javascript:history.go(-1)">[돌아가기]</a>
    </c:when>
    <c:otherwise>
    <form name="auth_form" action="/DaengDaeng/member/join/auth_email.do?authcode=${authcode}" onsubmit="return check_auth(${authcode})">
    	<div>인증번호 : <input type="text" name="acc" id="acc"  style="width: 60px" ><input type="hidden" name="authcode" value="${authcode}" >
					<input type="submit" value="확인"></div> 
	</form>
    </c:otherwise>
</c:choose>
</body>
</html>