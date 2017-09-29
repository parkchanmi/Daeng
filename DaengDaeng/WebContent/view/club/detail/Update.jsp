<%--------------------------------------------------------------------------------------------- --%>
<%--                                                                             				--%>
<%--------------------------------------------------------------------------------------------- --%>
<%@ page language="java" %>
<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page errorPage="Error.jsp"%>
<%@ page import="javax.servlet.jsp.jstl.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<fmt:requestEncoding value="UTF-8"/>

<%--------------------------------------------------------------------------------------------- --%>
<%-- DateSource Setting                                                                      	--%>
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
<c:set var="type" value="${param.type}"/>
<c:set var="curYear" value="${param.curYear}"/>
<c:set var="curMonth" value="${param.curMonth}"/>
<c:set var="curDay" value="${param.curDay}"/>

<c:set var="sch_code" value="${param.sch_code}"/>
<c:set var="sch_title" value="${param.sch_title}"/>
<c:set var="sch_contents" value="${param.sch_contents}"/>
<c:set var="sch_sdate" value="${param.sch_sdate}"/>


<%--------------------------------------------------------------------------------------------- --%>
<%-- Do Logic                                                                          			--%>
<%--------------------------------------------------------------------------------------------- --%>
<c:choose>
	<%-- SELECT --%>
	<c:when test="${type == 'SELECT'}">		
		<sql:query var="select" dataSource="${dataSource}">
			SELECT * FROM schedule WHERE sch_code=?
			<sql:param value="${sch_code}"/>
		</sql:query>
		<c:set var="sch_code" value="${select.rows[0].sch_code}"/>
		<c:set var="sch_title" value="${select.rows[0].sch_title}"/>
		<c:set var="sch_contents" value="${select.rows[0].sch_contents}"/>
		<c:set var="sch_sdate" value="${select.rows[0].sch_sdate}"/>
		
		<fmt:formatDate var="curYear" value="${sch_sdate}" pattern="yyyy"/>
		<fmt:formatDate var="curMonth" value="${sch_sdate}" pattern="M"/>
		<fmt:formatDate var="curDay" value="${sch_sdate}" pattern="d"/>
		
	</c:when>
	<%-- UPDATE --%>
	<c:when test="${type == 'UPDATE'}">
	<script>alert('update');</script>
		<sql:update var="update" dataSource="${dataSource}">
			UPDATE schedule SET sch_sdate=?, sch_title=?, sch_contents=? WHERE sch_code=?
			<sql:param value="${sch_sdate}"/>
			<sql:param value="${sch_title}"/>			
			<sql:param value="${sch_contents}"/>
 			<sql:param value="${sch_code}"/>
		</sql:update>
		<SCRIPT LANGUAGE="JavaScript">
			opener.location.href='/DaengDaeng/view/club/detail/View.jsp?curYear=<c:out value="${curYear}"/>&curMonth=<c:out value="${curMonth}"/>&curDay=<c:out value="${curDay}"/>';
			self.close();
		</SCRIPT>
	</c:when>
	<%-- INSERT --%>
	<c:when test="${type == 'INSERT'}">
	<script>alert('insert');</script>

		<sql:update var="insert"  dataSource="${dataSource}">
			INSERT  INTO schedule values(schedule_sequence.nextval,?,?,?)
			<sql:param value="${sch_title}"/>
			<sql:param value="${sch_contents}"/>
			<sql:param value="${sch_sdate}"/>
		</sql:update>
		<sql:query var="nextval" dataSource="${dataSource}">
		select (last_number+1) as sch_code from user_sequences where sequence_name='SCHEDULE_SEQUENCE'
		</sql:query>
		
		<c:set var="sch_code" value="${nextval.rows[0].sch_code}"/>
	
		<c:if test="${sch_kind=='club'}">
			<sql:update var="insert"  dataSource="${dataSource}">
				INSERT  INTO club_schedule values(club_schedule_sequence.nextval,?,schedule_sequence.nextval-1,?)
				<sql:param value="${clubinfo.ci_code}"/>
				<sql:param value="${memberDTO.mem_code}"/>
			</sql:update>
		</c:if>
		<c:if test="${sch_kind=='member'}">
			<sql:update var="insert"  dataSource="${dataSource}">
			
			</sql:update>
		</c:if>
		
		
		
		
		<SCRIPT LANGUAGE="JavaScript">
			opener.location.href='/DaengDaeng/view/club/detail/View.jsp?curYear=<c:out value="${curYear}"/>&curMonth=<c:out value="${curMonth}"/>&curDay=<c:out value="${curDay}"/>';
			self.close();
		</SCRIPT>
	</c:when>
	<%-- DELETE --%>
	<c:when test="${type == 'DELETE'}">
	<script>alert('delete');</script>
		<sql:update var="delete" dataSource="${dataSource}">
			DELETE FROM schedule WHERE sch_code=?
			<sql:param value="${sch_code}"/>
		</sql:update>
		<SCRIPT LANGUAGE="JavaScript">
			opener.location.href='/DaengDaeng/view/club/detail/View.jsp?curYear=<c:out value="${curYear}"/>&curMonth=<c:out value="${curMonth}"/>&curDay=<c:out value="${curDay}"/>';
			self.close();
		</SCRIPT>
	</c:when>
	<c:otherwise>
		<c:set var="type" value="INIT"/>
	</c:otherwise>
</c:choose>

<%--------------------------------------------------------------------------------------------- --%>
<%-- Redirect                                                                                   --%>
<%--------------------------------------------------------------------------------------------- --%>

<FORM METHOD=POST NAME='theForm' ACTION='/DaengDaeng/view/club/detail/UpdateForm.jsp'>
	<INPUT TYPE="hidden" NAME='type'  VALUE='<c:out value="${type}"/>'>

	<INPUT TYPE="hidden" NAME='sch_code'  VALUE='<c:out value="${sch_code}"/>'>
	<INPUT TYPE="hidden" NAME='sch_title'  	VALUE='<c:out value="${sch_title}"/>'>
	<INPUT TYPE="hidden" NAME='sch_contents'  VALUE='<c:out value="${sch_contents}"/>'>
	<INPUT TYPE="hidden" NAME='sch_sdate'  VALUE='<c:out value="${sch_sdate}"/>'>
	<INPUT TYPE="hidden" NAME='curYear'  VALUE='<c:out value="${curYear}"/>'>
	<INPUT TYPE="hidden" NAME='curMonth'  VALUE='<c:out value="${curMonth}"/>'>
	<INPUT TYPE="hidden" NAME='curDay'  VALUE='<c:out value="${curDay}"/>'>
	
</FORM>

<SCRIPT LANGUAGE="JavaScript">
	document.theForm.submit();
</SCRIPT>