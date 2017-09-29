<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import = "model.member.MemberDao" %>

<html>
<head><title>email중복확인</title>


<% request.setCharacterEncoding("utf-8");%>

<%
    String mem_email = request.getParameter("mem_email");
    MemberDao manager = MemberDao.getInstance();
    int check= manager.confirmmem_email(mem_email);
 
%>




<%
    if(check == 1) {
%>
<table width="270" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td height="39" ><%=mem_email%>이미 사용중인 아이디입니다.</td>
  </tr>
</table>

<form name="checkForm" method="post" action="confirmmem_email.do">
<table width="270" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td  align="center">
       다른 이메일을 선택하세요.<p>
       <input type="text" size="10" maxlength="12" name="mem_email">
       <input type="submit" value="email중복확인">
    </td>
  </tr>
</table>
</form>
<%
    } else {
%>
<table width="270" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td align="center">
      <p>입력하신 <%=mem_email%> 는 사용하실 수 있는 email입니다. </p>
      <input type="button" value="닫기" onclick="setmem_email()">
    </td>
  </tr>
</table>
<%
    }
%>
</body>
</html>
<script>

  function setmem_email()
    {
    opener.document.userinput.mem_email.value="<%=mem_email%>"; //opener:새로운 창을 연다.
    opener.document.userinput.mem_emailcheck.value="1"; //중복확인을 위해 넣어줌
    
self.close();
}

</script>