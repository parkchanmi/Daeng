<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>반려견 커뮤니티</title>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
<link href="/DaengDaeng/css/msg_rec_list.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript"
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>

<script type="text/javascript">
	$(function() { //전체선택 체크박스 클릭 
		$('input[name=_selected_all_]').on('change', function() {
			$('input[name=_selected_]').prop('checked', this.checked);
		});
	})
</script>
<body>

	<div id="wrapper">
		<jsp:include page="/menu.jsp"></jsp:include>
		<div id="contents_msg_rec">
			<div class="msg_rec_list">
				<div class="msg_rec_list_title">
					<h2>Message</h2>
				</div>
				<div class="btn_msg">
					<button
						onclick="javascript:location.href='/DaengDaeng/message/message_receive.do'"
						id="msg_resc">받은 쪽지</button>
					<button
						onclick="javascript:location.href='/DaengDaeng/message/message_send.do'"
						id="msg_send">보낸 쪽지</button>
					<button
						onclick="javascript:location.href='/DaengDaeng/message/message_writeForm.do'"
						id="msg_write">쪽지 쓰기</button>
				</div>
				<div class="msg_list">
					<form>
						<!-- <input type="checkbox" />전체선택&nbsp;<input type="checkbox" />전체해체 -->
						<table width="640" cellpadding="0" cellspacing="0" align="center">
							<c:if test="${message_list==null }">
								<tr>
									<td>받은 쪽지가 없습니다.</td>
								</tr>

							</c:if>
							<c:if test="${message_list!=null }">
								<thead style="background-color: #FF7171 !important; color: #fff;">
									<tr height="35">
										<th align="center" width="20"><input type="checkbox" name="_selected_all_" /></th>
										<th align="center" width="50">보낸날짜</th>
										<th align="center" width="40">보낸사람</th>
										<th align="center" width="100">제목</th>
										<th align="center" width="30">읽음</th>
									</tr>
								</thead>
								<c:forEach var="msglist" items="${message_list}">
									<tr>
										<td align="center" width="50"><input type="checkbox"
											name="_selected_" /></td>
										<td><c:out value="${msglist.msg_date}" /></td>
										<td width="250">${msglist.msg_sender_name}</td>
										<td align="center" width="150"><a
											href="/DaengDaeng/message/message_contents.do?type=1&msgID=${msglist.msg_code}">${msglist.msg_title}</a></td>
										<td align="center" width="50">${msglist.msg_read_ok}</td>
									</tr>
								</c:forEach>
							</c:if>
						</table>
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="/footer.jsp"></jsp:include>
	</div>
</body>
</html>



