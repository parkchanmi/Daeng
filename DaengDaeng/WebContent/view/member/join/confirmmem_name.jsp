<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import = "model.member.MemberDao" %>

<html>
<head><title>name중복확인</title>


<% request.setCharacterEncoding("utf-8");%>

<%
	String mem_name = request.getParameter("mem_name");
	MemberDao manager = MemberDao.getInstance();
	int check= manager.confirmmem_name(mem_name);  
%>




<%
    if(check == 1) {
%>
<table width="270" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td height="39" ><%=mem_name%>이미 사용중인 닉네임입니다.</td>
  </tr>
</table>

<form name="checkForm" method="post" action="confirmmem_name.do">
<table width="270" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td  align="center">
       다른 닉네임을 선택하세요.<p>
       <input type="text" size="10" maxlength="12" name="mem_name">
       <input type="submit" value="닉네임중복확인">
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
      <p>입력하신 <%=mem_name%> 는 사용하실 수 있는 닉네임입니다. </p>
      <input type="button" value="닫기" onclick="setmem_name()">
    </td>
  </tr>
</table>
<%
    }
%>
</body>
</html>
<script>

  function setmem_name()
    {
    opener.document.userinput.mem_name.value="<%=mem_name%>"; //opener:새로운 창을 연다.
    opener.document.userinput.mem_emailcheck.value="1"; //중복확인을 위해 넣어줌
    
self.close();
}

</script>