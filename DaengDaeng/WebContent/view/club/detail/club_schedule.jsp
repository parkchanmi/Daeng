<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page errorPage="Error.jsp"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>반려견 커뮤니티</title>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
<link href="/DaengDaeng/css/club_schedule.css" rel="stylesheet"
	type="text/css">
	
<script src="https://code.jquery.com/jquery-1.9.0.min.js"
	integrity="sha256-f6DVw/U4x2+HjgEqw5BZf67Kq/5vudRZuRkljnbF344="
	crossorigin="anonymous"></script>

<fmt:requestEncoding value="UTF-8" />

<c:set var="type" value="${param.type}" />
<c:set var="curYear" value="${param.curYear}" />
<c:set var="curMonth" value="${param.curMonth}" />
<c:set var="curDay" value="${param.curDay}" />


</head>


<style>
		tr { text-align : center; }
tr:nth-child(even) {background-color:#EAEAEA;  cursor: pointer; color:#505050;}
tbody tr:hover {  cursor: pointer; }
td{ border-right: 2px solid #fff;  } 
tbody tr { font-size:14px;}
td:last-child { border-right:none;}
th {border-right:2px solid #fff;}
	
	</style>
<script type="text/javascript">

	function join_confirm() {
		if (confirm("소모임에 가입하시겠습니까?") == true) { //확인
			location.href = "/DaengDaeng/club/detail/club_join.do";
		} else {
			return;
		}
	}
	function out_confirm() {
		if (confirm("탈퇴하시겠습니까?") == true) { //확인
			location.href = "/DaengDaeng/club/detail/club_out.do";
		} else {
			return;
		}
	}
</script>

</head>
<body>
	<div id="wrapper">
		<jsp:include page="/menu.jsp"></jsp:include>
		<!--로그인 main -->
		<div id="contents_clubboard">
			<div class="clubboard_form">
				<div class="clubboard_title">
					<h2>소모임 정보</h2>
				</div>
				<div class="clubboard">
					<div class="clubboard_left">
						<ul>
							<div class="clubboard_photo">
								<img src="${clubinfo.ci_filepath}" style="width:150px;height:160px;">							
							</div>
							<li class="clubboard_name"><label>소모임이름 : </label>
								<h3>${clubinfo.ci_name}</h3></li>
							<li class="clubboard_leader"><label>개설자 : </label>
								<h3>${clubinfo.leader_name}</h3></li>
							<div class="btn_club_board">
								<c:if test="${joinData==null}">
									<button onclick="join_confirm();">가입하기</button>
								</c:if>
								<c:if test="${leader==true}">
									<button onclick="javascript:location.href='/DaengDaeng/club/detail/club_member.do'">관리하기</button>
								</c:if>
								<c:if test="${leader==false}">
									<button onclick="out_confirm();">탈퇴하기</button>
								</c:if>
							</div>
						</ul>
					</div>
					<div class="clubboard_right">
						<div class="btn_club_board_menu">
							<button onclick="javascript:location.href='/DaengDaeng/club/detail/club_board.do'" id="totallist">전체글</button><!-- 
							<button onclick="javascript:location.href='/DaengDaeng/club/detail/club_picture.do'" id="photo">사진</button> -->
							<button onclick="javascript:location.href='/DaengDaeng/club/detail/club_schedule.do'" id="schedule">일정</button>
							<button onclick="javascript:location.href='/DaengDaeng/club/detail/club_member.do'" id="schedule">멤버</button>
						</div>
						<div class="clubboard_file">
							<c:if test="${joinData!=null}">
								<FORM name="theForm">
									<%-- base table --%>
									<TABLE cellpadding="0" cellspacing="0" border="0"
										bgcolor="#ffffff" width="620" height="665">
										<TR>
											<TD align="right" width="365"><A
												href="/DaengDaeng/view/club/detail/View.jsp?type=MONTH&curYear=<c:out value="${curYear}"/>&curMonth=<c:out value="${curMonth-1}"/>&curDay=<c:out value="${curDay}"/>">◀</a>
												<c:out value="${curYear}" /> 年 &nbsp;&nbsp; <c:out
													value="${curMonth}" /> 月 <A
												href="/DaengDaeng/view/club/detail/View.jsp?type=MONTH&curYear=<c:out value="${curYear}"/>&curMonth=<c:out value="${curMonth+1}"/>&curDay=<c:out value="${curDay}"/>">▶</a>
											</TD>
											<TD align="left"><IMG src="images/monthly.gif" border=0>
											</TD>
										</TR>
										<TR height="3">
											<TD colspan="2"></TD>
										</TR>
										<TR>
											<TD align="center" colspan="3" valign="top">
												<%-- body table --%>
												<TABLE border="0" cellspacing="0" cellpadding="0">
													<TR>
														<TD valign="top"
															style="border: #666666 1px solid; padding: 5px"
															align="center">
															<%-- month outline table --%>
															<TABLE border="0" cellspacing="0" cellpadding="0">
																<TR height="30">
																	<TD align=center><FONT color=red>일요일</FONT></TD>
																	<TD align=center>월요일</TD>
																	<TD align=center>화요일</TD>
																	<TD align=center>수요일</TD>
																	<TD align=center>목요일</TD>
																	<TD align=center>금요일</TD>
																	<TD align=center>토요일</TD>
																</TR>
																<TR>
																	<TD colspan=7 bgcolor=#888888 height=1></TD>
																</TR>
																<TR>
																	<TD colspan=7 bgcolor=#ffffff height=5></TD>
																</TR>
																<TR>
																	<TD colspan=7>
																		<%-- month content table --%>
																		<TABLE border='0' cellspacing='1' cellpadding='0'
																			bgcolor=#dddddd>
																			<TR>
																				<c:if test="${firstDayOfWeek != '1'}">
																					<%-- 해당 월의 가장 첫째줄에 있는 공백부분을 셈해서 처리한다.--%>
																					<c:forEach var="i" begin="1"
																						end="${firstDayOfWeek-1}">
																						<TD width="70" height="78" class="uline"
																							valign="top" align="right" style="padding: 5">
																						</TD>
																					</c:forEach>
																				</c:if>

																				<%-- 이 달의 끝날까지 메모의 제목과 날짜(숫자)를 출력한다 --%>
																				<c:set var="dbIndex" value="0" />
																				<c:forEach var="currentDay" begin="1"
																					end="${lastDayOfMonth}">
																					<TD bgcolor="#ffffff" style="padding: 5">
																						<TABLE cellpadding="0" cellsping="0" border="0"
																							width="70">
																							<TR>
																								<TD height="10" width="70" class="uline"
																									valign="top" align="right"><A
																									href='javascript:dWrite("${curYear}","${curMonth}","${currentDay}")'>
																										<!-- 일요일 --> <%-- <c:if test="${((currentDay-(8-firstDayOfWeek)) % 7) == 1}">
																											<FONT color="red">													
																												<c:out value="${currentDay}"/>
																											</FONT>
																										</c:if>
																										<c:if test="${((currentDay-(8-firstDayOfWeek)) % 7) != 1}">
																											<c:out value="${currentDay}"/>
																										</c:if> --%> <c:choose>
																											<c:when
																												test="${((currentDay-(8-firstDayOfWeek)) % 7) == 1}">
																												<!-- 일요일 -->
																												<FONT color="red"> <c:out
																														value="${currentDay}" />
																												</FONT>
																											</c:when>
																											<c:otherwise>
																												<c:out value="${currentDay}" />
																											</c:otherwise>
																										</c:choose>
																								</A></TD>
																							</TR>
																							<TR>
																								<TD height="68" width="70" valign="top">
																									<TABLE>
																										<c:forEach var="dayIndex"
																											items="${month_query.rows}">
																											<fmt:formatDate var="db_day"
																												value="${dayIndex.sch_sdate}" pattern="d" />
																											<c:if test="${currentDay == db_day}">
																												<TR>
																													<TD><A
																														href="javascript:view('${dayIndex.sch_code}')">
																															${dayIndex.sch_title} </A> <c:set
																															var="dbIndex" value='${dbIndex + 1}' /></TD>
																												</TR>
																											</c:if>
																										</c:forEach>
																									</TABLE>
																								</TD>
																							</TR>
																						</TABLE>
																					</TD>
																					<%-- 만약 한주의 마지막날(토요일)이고 이 달의 마지막 날이 아니라면 다음 줄로 넘어간다. --%>
																					<c:if
																						test="${((currentDay-(8-firstDayOfWeek)) % 7) == 0}">
																			</TR>
																			<TR>
																				</c:if>
																				</c:forEach>

																				<%-- 해당 월의 가장 마지막 줄에 있는 공백부분을 셈해서 처리한다.--%>
																				<c:if test="${lastDayOfLastWeek != '7'}">
																					<c:forEach var="i" begin="1"
																						end="${7-lastDayOfLastWeek}">
																						<TD width=70 height=78 class=uline valign=top
																							align=right style='padding: 5'></TD>
																					</c:forEach>
																				</c:if>
																			</TR>
																		</TABLE> <%-- end month content table --%>
																	</TD>
																</TR>
															</TABLE> <%-- end month outline table --%>
														</TD>
													</TR>
												</TABLE> <%-- end body table --%>
											</TD>
										</TR>
										<TR height=10>
											<TD></TD>
										</TR>
										<TR>
											<TD align=right></TD>
										</TR>
									</table>
									<%-- end base table --%>
								</FORM>
							</c:if>
							<c:if test="${joinData==null}">
								<tr>
									<td>권한이 없습니다.</td>
								</tr>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	
		<jsp:include page="/footer.jsp"></jsp:include>

	</div>
</body>

<SCRIPT type="text/javascript">
	function view(str) {
		OpenWin("/DaengDaeng/view/club/detail/Update.jsp?type=SELECT&sch_code="
				+ str, 480, 360);
	}

	function dWrite(y, m, d) {
		OpenWin("/DaengDaeng/view/club/detail/Update.jsp?curYear=" + y
				+ "&curMonth=" + m + "&curDay=" + d, 470, 320);
	}
	function OpenWin(URL, width, height) {
		var str, width, height;
		str = "'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,";
		str = str + "width=" + width;
		str = str + ",height=" + height + "',top=50,left=50";
		window.open(URL, 'remoteSchedule', str);
	}
</SCRIPT>
</html>



