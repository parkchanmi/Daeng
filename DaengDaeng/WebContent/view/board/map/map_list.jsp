<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>지도게시판</title>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">

</head>
<body>
	<div id="wrapper">
		<jsp:include page="/menu.jsp"></jsp:include>
		<div id="contents_map">
			<div class="map_list">
				<div class="map_list_title">
					<h2>Map</h2>
				</div>
				<div class="btn_map">
					<input type="button" id="cafe" value="카페"
						onclick="document.location.href='/DaengDaeng/board/map/map_list.do?type=카페'">
					<input type="button" id="house" value="펜션"
						onclick="document.location.href='/DaengDaeng/board/map/map_list.do?type=펜션'">
					<input type="button" id="hospital" value="병원"
						onclick="document.location.href='/DaengDaeng/board/map/map_list.do?type=병원'">
				</div>
				<div class="map_area">
					<!-- 지도를 표시할 div 입니다 -->
					<div id="map" style="width: 600px; height: 600px;"></div>

					<script type="text/javascript"
						src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04a3a1aeb1d505c665a885a9355aa762"></script>
					<script>
						var list = new Array();
						<c:forEach items="${maplist}" var="item">
						list.push({
							map_code : "${item.map_code}",
							map_corp : "${item.map_corp}",
							map_lat : "${item.map_lat}",
							map_lng : "${item.map_lng}"
						});
						//alert(${item.map_lat});
						</c:forEach>
						//DB에서목록뽑아옴

						var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
						mapOption = {
							center : new daum.maps.LatLng(37.566535, 126.977969), // 지도의 중심좌표
							level : 13
						// 지도의 확대 레벨
						};

						// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
						var map = new daum.maps.Map(mapContainer, mapOption);

						var positions = new Array();
						for (var i = 0; i < list.length; i++) {
							positions.push({
								title : list[i].map_corp,
								lat : list[i].map_lat,
								lng : list[i].map_lng,
								code : list[i].map_code

							});
						}
						//db에서 뽑아온 목록
						for (var i = 0; i < positions.length; i++) {
							addMarker(positions[i]);
						}
						function addMarker(position) {
							// 마커 이미지의 이미지 주소입니다
							var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
							// 마커 이미지의 이미지 크기 입니다
							var imageSize = new daum.maps.Size(24, 35);

							// 마커 이미지를 생성합니다    
							var markerImage = new daum.maps.MarkerImage(
									imageSrc, imageSize);

							var marker = new daum.maps.Marker({
								map : map, // 마커를 표시할 지도
								position : new daum.maps.LatLng(position.lat,
										position.lng), // 마커를 표시할 위치
								title : position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
								image : markerImage,
								clickable : true
							// 마커 이미지 
							});

							// 마커 객체에 마커아이디와 마커의 기본 이미지를 추가합니다
							marker.code = position.code;
							marker.lat = position.lat;
							marker.lng = position.lng;

							// 마커에 click 이벤트를 등록합니다
							daum.maps.event
									.addListener(
											marker,
											'click',
											function() {
												var code = marker.code;
												var lat = marker.lat;
												var lng = marker.lng;
												// alert(code);
												var popUrl = "/DaengDaeng/board/map/map_detail.do?map_code="
														+ code
														+ "&lat="
														+ lat
														+ "&lng=" + lng; //팝업창에 출력될 페이지 URL
												var popOption = "width=1000, height=1000, resizable=no, scrollbars=no, status=no;"; //팝업창 옵션(optoin)
												window.open(popUrl, "",
														popOption);
											});
						}
					</script>
				</div>
			</div>
		</div>
	
		<jsp:include page="/footer.jsp"></jsp:include>
	</div>
</body>
</html>