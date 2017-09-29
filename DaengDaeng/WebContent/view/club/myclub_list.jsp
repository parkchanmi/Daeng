<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>반려견 커뮤니티</title>

<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
<link href="/DaengDaeng/css/club_list_main.css" rel="stylesheet" type="text/css">

</head>
<body>

	<div id="wrapper">
		<jsp:include page="/menu.jsp"></jsp:include>
		<!--로그인 main -->
		<div id="contents_clublist">
			<div class="clublist_form">
				<div class="clublist_title">
					<h2>가입한 소모임</h2>
				</div>
				<div class="club_list">
					<table width="965" cellpadding="0" cellspacing="0" align="center">
						<thead style="background-color: #EE8300; color: #fff;">
							<tr height="35">
								<th align="center" width="30">리더</th>
								<th align="center" width="30">분류</th>
								<th align="center" width="50">이름</th>
								<th align="center" width="30">소개</th>
								<th align="center" width="50">개설날짜</th>
							</tr>
						</thead>
						<c:forEach var="clublist" items="${cList}">
							<tr height="35">
								<td align="center" width="50">${clublist.leader_name}</td>
								<td>${clublist.ci_type}</td>
								<td><a
									href="/DaengDaeng/club/detail/club_board.do?cicode=${clublist.ci_code}">${clublist.ci_name}</a></td>
								<td width="100">${clublist.ci_intro}</td>
								<td align="center" width="50">${clublist.ci_date}</td>
							</tr>
						</c:forEach>
					</table>
					<div class="btn_clublist">
						<button
							onclick="javascript:location.href='/DaengDaeng/club/myclub_list.do'"
							id="join_cl">가입한소모임</button>
						<button
							onclick="javascript:location.href='/DaengDaeng/club/create_clubForm.do'"
							id="make_cl">소모임만들기</button>
						<button
							onclick="javascript:location.href='/DaengDaeng/club/club_list.do'"
							id="totallist_cl">전체목록</button>
					</div>
				</div>
			</div>

		</div>

		<jsp:include page="/footer.jsp"></jsp:include>


	</div>

</body>
</html>



