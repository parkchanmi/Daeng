<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<title>반려견 커뮤니티 - 관리자 페이지</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script
  src="https://code.jquery.com/jquery-1.9.0.min.js"
  integrity="sha256-f6DVw/U4x2+HjgEqw5BZf67Kq/5vudRZuRkljnbF344="
  crossorigin="anonymous"></script>
<script type="text/javascript" src="/DaengDaeng/jqplot/js/jquery.jqplot.js"></script>
<script type="text/javascript" src="/DaengDaeng/jqplot/js/plugins/jqplot.pieRenderer.js"></script>
<script type="text/javascript" src="/DaengDaeng/jqplot/js/plugins/jqplot.enhancedPieLegendRenderer.js"></script>
<link rel="stylesheet" type="text/css" href="/DaengDaeng/jqplot/css/jquery.jqplot.css"/>
<script type="text/javascript" src="/DaengDaeng/jqplot/js/plugins/jqplot.pieRenderer.js"></script>
<script type="text/javascript" src="/DaengDaeng/jqplot/js/plugins/jqplot.enhancedPieLegendRenderer.js"></script>

<script type="text/javascript" src="/DaengDaeng/jqplot/js/plugins/jqplot.barRenderer.js"></script>      
<script type="text/javascript" src="/DaengDaeng/jqplot/js/plugins/jqplot.pointLabels.js"></script>    
<script type="text/javascript" src="/DaengDaeng/jqplot/js/plugins/jqplot.categoryAxisRenderer.js"></script>
<style>
<style>
html, body, h1, h2, h3, h4, h5 {
	font-family: "Raleway", sans-serif
}
</style>
<body class="w3-light-grey">

	<!-- Top container -->
	<div class="w3-bar w3-top w3-black w3-large" style="z-index: 4">
		<button
			class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey"
			onclick="w3_open();">
			<i class="fa fa-bars"></i>Menu
		</button>
		<span class="w3-bar-item w3-left"><i class="fa fa-paw fa-2x"
			aria-hidden="true"></i></span>
	</div>

	<!-- Sidebar/menu -->
	<nav class="w3-sidebar w3-collapse w3-white w3-animate-left"
		style="z-index: 3; width: 300px;" id="mySidebar">
		<br>
		<div class="w3-container">
			<h5>관리자</h5>
		</div>
		<div class="w3-bar-block">
			<a href="#"
				class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black"
				onclick="w3_close()" title="close menu"><i
				class="fa fa-remove fa-fw"></i>  Close Menu</a> 
				<a href="/DaengDaeng/admin/stats/admin_statuser_visit.do"
				class="w3-bar-item w3-button w3-padding w3-blue">
				<i class="fa fa-pie-chart fa-fw"></i>통계</a> 
				<a href="/DaengDaeng/admin/member/admin_memberlistForm.do"
				class="w3-bar-item w3-button w3-padding"><i class="fa fa-user-circle-o fa-fw"></i>회원관리</a> 
				<a href="/DaengDaeng/admin/lost/admin_lostlistForm.do" class="w3-bar-item w3-button w3-padding">
				<i class="fa fa-bell fa-fw"></i>실종신고</a>
				<!-- <a href="/DaengDaeng/main.do" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bell fa-fw"></i>메인</a> -->
			<script>
				// Accordion 
				function myAccFunc() {
					var x = document.getElementById("demoAcc");
					if (x.className.indexOf("w3-show") == -1) {
						x.className += " w3-show";
					} else {
						x.className = x.className.replace(" w3-show", "");
					}
				}
			</script>
			<a onclick="myAccFunc()" href="javascript:void(0)"
				class="w3-button w3-block w3-white w3-left-align" id="myBtn"> <i
				class="fa fa-pencil fa-fw"></i>게시글관리</i>
			</a>
			<div id="demoAcc"
				class="w3-bar-block w3-hide w3-padding-large w3-medium">
				<a href="/DaengDaeng/admin/board/admin_boardlistForm.do" class="w3-bar-item w3-button">글 관리</a> 
				<a href="/DaengDaeng/admin/board/admin_reviewlistForm.do"
					class="w3-bar-item w3-button">댓글 관리</a>
			</div>
			<script>
				// Accordion 
				function myAccFunc01() {
					var x = document.getElementById("demoAcc01");
					if (x.className.indexOf("w3-show") == -1) {
						x.className += " w3-show";
					} else {
						x.className = x.className.replace(" w3-show", "");   
					}
				}
			</script>
			
		</div>
	</nav>


	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"
		id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 300px; margin-top: 43px;">

		<!-- Header -->
		<header class="w3-container" style="padding-top: 22px">
			<!-- <h5><b><i class="fa fa-dashboard"></i>통계</b></h5> -->
		</header>

		<div class="w3-row-padding">
			<div class="w3-panel" style="float: left; width: 25%">
				<div class="w3-third" style="float: none; width: 100%;">
					<div id="chart1" style="width:360px;height:300px;"></div>
						<script type="text/javascript">
							$(document).ready(function() {
							  var chart_data = [
							    [['일반', ${re01}], ['견주', ${re02}], ['전문가',${re03}]]
							  ];
							  var chart_opt = {
							    title:'회원타입 통계',
							    seriesDefaults:{
							      renderer:$.jqplot.PieRenderer,
							       rendererOptions: {
							      startAngle: 180,
							      sliceMargin: 4,
							      showDataLabels: true } 
							    },
							    legend: {
							      show: true,
							      location: 'e'
							    },
							  };
						
							  $.jqplot('chart1', chart_data, chart_opt);
							});
						</script>
				</div>
				<div class="w3-container w3-third" style="width: 100%;">
					<div class="w3-twothird" style="width: 100%;">
						<table class="w3-table w3-striped w3-white">
							<td>회원구분</td>
								<td><i>비율</i></td>
							</tr>
							<tr>
								<td><i class="fa fa-bell w3-text-red w3-large"></i></td>
								<td>일반회원</td>
								<td><i>${re01}%</i></td>
							</tr>
							<tr>
								<td><i class="fa fa-users w3-text-yellow w3-large"></i></td>
								<td>견주</td>
								<td><i>${re02}%</i></td>
							</tr>
							<tr>
								<td><i class="fa fa-comment w3-text-red w3-large"></i></td>
								<td>전문가</td>
								<td><i>${re03}%</i></td>
							</tr>    
						</table>
					</div>
				</div>
			</div>
			<div class="w3-panel" style="float: left; width: 25%">
				<div class="w3-third" style="float: none;">
					<div id="piechart2" style="width:360px;height:300px;"></div>
						<script type="text/javascript">
						$(document).ready(function() {
							  var chart_data = [
							    [['검색', ${vi01}], ['지인추천', ${vi02}], ['광고',${vi03}] ,['sns',${vi04}],['기타',${vi05}]]
							  ];
							  var chart_opt = {
							    title:'유입경로 통계',
							    seriesDefaults:{
							      renderer:$.jqplot.PieRenderer,
							       rendererOptions: {
							      startAngle: 180,
							      sliceMargin: 4,
							      showDataLabels: true } 
							    },
							    legend: {
							      show: true,
							      location: 'e'
							    },
							  };
						
							  $.jqplot('piechart2', chart_data, chart_opt);
							});
						</script>
  				</div>
				<div class="w3-container w3-third" style="width: 100%;">
					<div class="w3-twothird" style="width: 100%;">
						<table class="w3-table w3-striped w3-white">
							<tr>
								<td><i class="fa fa-user w3-text-blue w3-large"></i></td>
								<td>유입경로</td>
								<td><i>비율</i></td>
							</tr>
							<tr>
								<td><i class="fa fa-bell w3-text-red w3-large"></i></td>
								<td>검색</td>
								<td><i>${vi01}%</i></td>
							</tr>
							<tr>
								<td><i class="fa fa-users w3-text-yellow w3-large"></i></td>
								<td>지인추천</td>
								<td><i>${vi02}%</i></td>
							</tr>
							<tr>
								<td><i class="fa fa-comment w3-text-red w3-large"></i></td>
								<td>광고</td>
								<td><i>${vi03}%</i></td>
							</tr>
							<tr>
								<td><i class="fa fa-bookmark w3-text-blue w3-large"></i></td>
								<td>SNS</td>
								<td><i>${vi04}%</i></td>
							</tr>
							<tr>
								<td><i class="fa fa-laptop w3-text-red w3-large"></i></td>
								<td>기타</td>
								<td><i>${vi05}%</i></td>
							</tr>  
						</table>
					</div>
				</div>
			</div>
			<div class="w3-panel" style="float: left; width: 50%"">
				<div class="w3-third" style="float: none;  width : 100%">   
					<div id="barchart" style="width:750px;height:324px;">
						<script type="text/javascript">     
						jQuery(document).ready(function () {
							    var member = [${uinfo01}, ${uinfo03}, ${uinfo05}, ${uinfo07}, ${uinfo09}];
							    var album = [${uinfo02}, ${uinfo04}, ${uinfo06}, ${uinfo08}, ${uinfo010}];
							    jQuery("#barchart").jqplot([member, album], {
							          title:"연령&성별 통계"
							        , seriesColors:['#00CC99', '#FF9933']    // 두개 이상의 색을 적어도 최상의 2개의 색만 반영됨
							        , stackSeries : false
							        , seriesDefaults:{
							              renderer:jQuery.jqplot.BarRenderer
							        }
							        , axes:{
							            xaxis:{
							                  renderer:jQuery.jqplot.CategoryAxisRenderer
							                , ticks:['10대', '20대', '30대', '40대', '50대이상']
							            }
							        }
							    });
							});
						</script>
				</div>
				<div class="w3-container w3-third" style="width: 100%;">
					<div class="w3-twothird" style="width: 100%;">
						<table class="w3-table w3-striped w3-white">
							<tr>
								<td><i class="fa fa-user w3-text-blue w3-large"></i></td>
								<td>연령</td>
								<td>성별</td>
								<td><i>비율</i></td>
							</tr>
							<tr>
								<td><i class="fa fa-bell w3-text-red w3-large"></i></td>
								<td >10대</td>
								<td>남</td>
								<td><i>${uinfo01}%</i></td>
							</tr>
							<tr>
								<td><i class="fa fa-users w3-text-yellow w3-large"></i></td>
								<td>10대</td>
								<td>여</td>
								<td><i>${uinfo02}%</i></td>
							</tr>
							<tr>
								<td><i class="fa fa-comment w3-text-red w3-large"></i></td>
								<td>20대</td>
								<td>남</td>
								<td><i>${uinfo03}%</i></td>
							</tr>
							<tr>
								<td><i class="fa fa-bookmark w3-text-blue w3-large"></i></td>
								<td>20대</td>
								<td>여</td>
								<td><i>${uinfo04}%</i></td>
							</tr>
							<tr>
								<td><i class="fa fa-laptop w3-text-red w3-large"></i></td>
								<td>30대</td>
								<td>남</td>
								<td><i>${uinfo05}%</i></td>
							</tr>
							<tr>
								<td><i class="fa fa-share-alt w3-text-green w3-large"></i></td>
								<td>30대</td>
								<td>여</td>
								<td><i>${uinfo06}%</i></td>
							</tr>
							<tr>
								<td><i class="fa fa-share-alt w3-text-green w3-large"></i></td>
								<td>40대</td>
								<td>남</td>
								<td><i>${uinfo07}%</i></td>
							</tr>
							<tr>
								<td><i class="fa fa-share-alt w3-text-green w3-large"></i></td>
								<td>40대</td>
								<td>여</td>
								<td><i>${uinfo08}%</i></td>
							</tr>
							<tr>
								<td><i class="fa fa-share-alt w3-text-green w3-large"></i></td>
								<td>50대이상</td>
								<td>남</td>
								<td><i>${uinfo09}%</i></td>
							</tr>
							<tr>
								<td><i class="fa fa-share-alt w3-text-green w3-large"></i></td>
								<td>50대이상</td>
								<td>여</td>
								<td><i>${uinfo010}%</i></td>
							</tr>   
						</table>
					</div>
				</div>
			</div>
		</div>
		

		</div>
		
		
	<!-- Footer -->
	<footer class="w3-container w3-padding-16 w3-light-grey">
		<h4>FOOTER</h4>
		<p>
			Powered by <a href="https://www.w3schools.com/w3css/default.asp"
				target="_blank">w3.css</a>
		</p>
	</footer>
	</div>


	<script>
		// Get the Sidebar
		var mySidebar = document.getElementById("mySidebar");

		// Get the DIV with overlay effect
		var overlayBg = document.getElementById("myOverlay");

		// Toggle between showing and hiding the sidebar, and add overlay effect
		function w3_open() {
			if (mySidebar.style.display === 'block') {
				mySidebar.style.display = 'none';
				overlayBg.style.display = "none";
			} else {
				mySidebar.style.display = 'block';
				overlayBg.style.display = "block";
			}
		}

		// Close the sidebar with the close button
		function w3_close() {
			mySidebar.style.display = "none";
			overlayBg.style.display = "none";
		}
	</script>

</body>
</html>


