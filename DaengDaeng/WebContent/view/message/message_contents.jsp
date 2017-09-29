<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>반려견 커뮤니티</title>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
<link href="/DaengDaeng/css/msg_write.css" rel="stylesheet"
	type="text/css">

</head>
<body>
	<div id="wrapper">
		<jsp:include page="/menu.jsp"></jsp:include>
		<div id="contents_msg_write">
			<div class="msg_write">
				<div class="msg_write_title02">
					<h2>Contents</h2>
				</div>
				<div class="msg_write_list">
					<ul class="msg_contents_listul">
						<li><label>받는 사람</label>
							<h3>${msgData.msg_receiver_name}</h3></li>

						<li><label>보낸 날짜</label>
							<h3>${msgData.msg_date}</h3></li>

						<li><label>보낸 사람</label>
							<h3>${msgData.msg_sender_name}</h3></li>
						<li><label>제목</label>
							<h3>${msgData.msg_title}</h3></li>
						<li class="contents_msg_box">
							<h3>${msgData.msg_contents}</h3>
						</li>
					</ul>
					<div class="btn_msg_contents">
						<button onclick="javascript:location.href='/DaengDaeng/message/message_writeForm.do?sender=${msgData.msg_receiver}&receiver=${msgData.msg_sender}'" id="msg_rep">답장</button>
						<button onclick="javascript:alert('삭제하시겠습니까?')" id="msg_delete">삭제</button>
						<button onclick="javascript:history.go(-1)" id="msg_list">목록</button>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="/footer.jsp"></jsp:include>

	</div>

</body>
</html>
