<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>반려견 커뮤니티</title>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
<link href="/DaengDaeng/css/myPageForm.css" rel="stylesheet" type="text/css">


<script>
	function getThumbnailPrivew(html, $target) {
		if (html.files && html.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$target.css('display', '');
				//$target.css('background-image', 'url(\"' + e.target.result + '\")'); // 배경으로 지정시
				$target
						.html('<img src="' + e.target.result + '" border="0" alt="" />');
			}
			reader.readAsDataURL(html.files[0]);
		}
	}
	
</script>
<script>
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

</head>
<body>
	<div id="wrapper">
		<jsp:include page="/menu.jsp"></jsp:include>
		<script type="text/javascript">
			$(function(){
				calendarEvent();
			});
			function calendarEvent(eventData){
				$("#calender").html("");
				var date = new Date();
				var d = date.getDate();
				var m = date.getMonth();
				var y = date.getFullYear();
				var calendar = $('#calendar').fullCalendar({
					header: {
						left:"",
						center:"title",
						//right : "month, basicWeek, basicDay"
						right:"today prev, next"
					}, 
					editable:true,
					titleFormat: {
						month:"yyyy년 MMMM",
						week:"[yyyy] MMMM dd일{[yyyy] MMM dd일}",
						day:"yyyy년 MMM d일 dddd"
					},
					allDayDefault: false,
					defaultView : "month",
					editable:false,
					monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
					monthNamesShort:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
					dayNames:["일요일","월요일","화요일","수요일","목요일","금요일","토요일"],
					dayNamesShort:["일","월","화","수","목","금","토"],
					buttonText :{
						today:"오늘",
						month:"월별",
						week:"주별",
						day:"일별",
					},
					events:eventData,
					timeFormat : "HH:mm",
				});			
		</script>

		<!--mypage main -->
		<div id="contents_mypage">
			<div class="mypageform">
				<div class="mypage_title">
					<h2>My Page</h2>
					<!-- <div id="calendar"></div> -->
					<!-- <div class="cal_img"><a href="/DaengDaeng/member/mypage/mp_calendarForm.do"><img src="/DaengDaeng/images/cal_img.png"></a></div> -->
				</div>
				<div class="owner_form">
					<ul>
						<li><label>등급</label>
							<h3>${memberDTO.mem_type}</h3></li>
						<li><label>이메일 주소</label>
							<h3>${memberDTO.mem_email}</h3></li>
						<li><label>닉네임</label>
							<h3>${memberDTO.mem_name}</h3></li>
						<li><label>성별 / 연령대</label>
							<h3>${memberDTO.mem_gender} / ${memberDTO.mem_age}</h3></li>
						<li><label>핸드폰번호</label>
							<h3>${memberDTO.mem_hp}</h3></li>
						<li><label>주소</label>
							<h3>${memberDTO.mem_loc}</h3></li>	
					</ul>
					<div class="btn_area">
						<div class="btn_modify">
							<a href="/DaengDaeng/member/mypage/mp_modify_pwForm.do">정보수정</a>
						</div>
					</div>
				</div>
				<c:if test="${memberDTO.mem_type!='일반'}">
				<div class="dog_area">
				<c:forEach var="dogdata" items="${DogList}">
					<div class="dogs_form">
						<div class="dogs_photo">
								<img src="${dogdata.dog_filepath}" style="width:140px;height:150px;">
								
						</div>  
						<div class="dogs_info">
							<ul>
								<li><label>이름</label>
									<h3>${dogdata.dog_name}</h3></li>
								<li><label>견종</label>
									<h3>${dogdata.dog_kind}</h3></li>
								<li><label>성별</label>
									<h3>${dogdata.dog_gender}</h3></li>
								<li><label>나이</label>
									<h3>${dogdata.dog_age}</h3></li>
								
							</ul>
						</div>
						<div class="btn_area">
							<div class="btn_dogs_modify">
								<a href="/DaengDaeng/member/mypage/mp_modify_dogsForm.do?dog_code=${dogdata.dog_code}">수정</a>
							</div>
							<div class="btn_dogs_delete">
								<a href="/DaengDaeng/member/mypage/mp_modify_dogsdeleteForm.do?dog_code=${dogdata.dog_code}">삭제</a>
							</div>
						</div>
					</div>			
				</c:forEach>
				<div class="btn_add">
					<a href="/DaengDaeng/member/mypage/mp_modify_dogsaddForm.do">
						<img src="/DaengDaeng/images/btn_add.png" width="40" height="40">
					</a>
				</div>
				</div>
				</c:if>
			</div>
			</div>
		
		<jsp:include page="/footer.jsp"></jsp:include>
		</div>
</body>
</html>