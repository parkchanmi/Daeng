<%--------------------------------------------------------------------------------------------- --%>
<%-- 					                                                                        --%>
<%--------------------------------------------------------------------------------------------- --%>
<%@ page language="java" %>
<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page errorPage="Error.jsp"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%--
	Request객체로부터 전달받은 값들을 인코딩할때 사용
	request.setCharacterEncoding("UTF-8"); 코드와 동일
--%>
<fmt:requestEncoding value="UTF-8"/>
 
<%--------------------------------------------------------------------------------------------- --%>
<%-- DataSource Setting                                                                      	--%>
<%--------------------------------------------------------------------------------------------- --%>
<sql:setDataSource
    var="dataSource"
    driver="oracle.jdbc.driver.OracleDriver"    
    url="jdbc:oracle:thin:@192.168.40.50:1521:XE"
    user="dang"
    password="dang"/> 


<%--------------------------------------------------------------------------------------------- --%>
<%-- Parameter Setting                                                                       	--%>
<%--------------------------------------------------------------------------------------------- --%>
<c:set var="type" 		value="${param.type}"/>
<c:set var="curYear" 	value="${param.curYear}"/>
<c:set var="curMonth" 	value="${param.curMonth}"/>
<c:set var="curDay" 	value="${param.curDay}"/>


<%--------------------------------------------------------------------------------------------- --%>
<%-- Base info setting(for db, calendar)	                                               		--%>
<%--------------------------------------------------------------------------------------------- --%>

<%  
Calendar calendar = Calendar.getInstance();

String strType = (String)request.getParameter("type");

if(strType != null && !strType.equals("")) {
	int intYear 	= Integer.parseInt(request.getParameter("curYear"));
	int intMonth 	= Integer.parseInt(request.getParameter("curMonth"));
	int intDay 		= Integer.parseInt(request.getParameter("curDay"));
	

	if(intMonth > 12) {
		intYear += 1;
		intMonth = 1;
	}
	if(intMonth < 1) {
		intYear -= 1;
		intMonth = 12;
	}

	calendar.set(intYear, intMonth-1, intDay);
}

//today 정보
pageContext.setAttribute("today", 		calendar.getTime());
pageContext.setAttribute("curYear", 	calendar.get(Calendar.YEAR));
pageContext.setAttribute("curMonth", 	(calendar.get(Calendar.MONTH) + 1));
pageContext.setAttribute("curDay", 		calendar.get(Calendar.DATE));
pageContext.setAttribute("sch_kind", 	request.getParameter("sch_kind"));
System.out.println(session.getAttribute("sch_kind"));


//해당월의 1일로 캘린더 설정.
calendar.set(Calendar.DATE, 1); 
request.setAttribute("firstDayOfMonth", calendar.getTime());
System.out.println("firstDayOfMonth:"+String.format("%1$tF", calendar.getTime()));

session.setAttribute("firstDayOfWeek", calendar.get(Calendar.DAY_OF_WEEK));
session.setAttribute("lastDayOfMonth", 	calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

//해당월의 마지막일로 캘린더 설정.
calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
session.setAttribute("lastDayOfLastWeek", calendar.get(Calendar.DAY_OF_WEEK));

//다음달의 1일로 설정.
calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
calendar.set(Calendar.DATE, 1);
request.setAttribute("firstDayOfNextMonth", calendar.getTime());
System.out.println("firstDayOfNextMonth:"+String.format("%1$tF", calendar.getTime()));

%>
 
<%--------------------------------------------------------------------------------------------- --%>
<%-- Do Logic                                                                        			--%>
<%--------------------------------------------------------------------------------------------- --%>
<c:if test="${sch_kind=='club'}">
<sql:query var="month_query" dataSource="${dataSource}" scope="session">
select * from club_schedule, schedule where club_schedule.csch_schcode=schedule.sch_code and csch_cicode=?
	 and to_char(sch_sdate,'mm') >= ? and to_char(sch_sdate,'mm') < ? ORDER BY sch_sdate

	<%--
	<sql:dateParam value="${firstDayOfMonth}"/> 
	<sql:dateParam value="${firstDayOfNextMonth}"/> <-- 주석처리 홀랭..
	--%>
	<sql:param value="${clubinfo.ci_code}"/>
	<sql:param value="${curMonth}"/>
	<sql:param value="${curMonth+1}"/>
</sql:query>	
</c:if>
<!-- 날짜 정보의 파라미터를 전달할때 sql:dateParam 사용-->
<%
																
																
%>
															
<c:set var="action_target" 	value="/DaengDaeng/view/club/detail/club_schedule.jsp"/>	

<%--------------------------------------------------------------------------------------------- --%>
<%-- Redirect                                                                                   --%>
<%--------------------------------------------------------------------------------------------- --%>
<FORM METHOD="POST" NAME="theForm" ACTION='<c:out value="${action_target}"/>'>
	<INPUT TYPE="hidden" NAME='type' VALUE='<c:out value="${type}"/>'>
	<INPUT TYPE="hidden" NAME='curYear' VALUE='<c:out value="${curYear}"/>'>
	<INPUT TYPE="hidden" NAME='curMonth' VALUE='<c:out value="${curMonth}"/>'>
	<INPUT TYPE="hidden" NAME='curDay' VALUE='<c:out value="${curDay}"/>'>
</FORM>
<SCRIPT LANGUAGE="JavaScript">
	document.theForm.submit();
</SCRIPT>
