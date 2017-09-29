<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>지도게시판 업체정보</title>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

	<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>지도 생성하기</title>

</head>
<body>
	<!-- 지도를 표시할 div 입니다 -->
	<div class="map_info">
		<div
			style="border: 1px; float: left; width: 50%; height: 300px; padding: 10px;"
			id="map"></div>
		<script type="text/javascript"
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04a3a1aeb1d505c665a885a9355aa762"></script>
		<script>
			/* //DB목록받아옴
			var list = new Array();
			<c:forEach items="${detailList}" var="item">
			list.push({
				map_code : "${item.map_code}",
				map_corp : "${item.map_corp}",
				map_lat : "${item.map_lat}",
				map_lng : "${item.map_lng}"
			});
			</c:forEach> */
	
			//화면에 나오는 지도의 옵션
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			mapOption = {
				center : new daum.maps.LatLng(37.566535, 126.977969), // 지도의 중심좌표
				level : 13
			// 지도의 확대 레벨
			};
			// 마커 이미지의 이미지 주소입니다
			var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
			// 마커 이미지의 이미지 크기 입니다
			var imageSize = new daum.maps.Size(24, 35);
	
			// 마커 이미지를 생성합니다    
			var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize);
			var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
			var lat=${map.map_lat};
			var lng=${map.map_lng};
			// 마커가 표시될 위치입니다 
			var markerPosition  = new daum.maps.LatLng(lat, lng); 
	
			// 마커를 생성합니다
			var marker=new daum.maps.Marker({
					map : map, // 마커를 표시할 지도
					position : markerPosition, // 마커를 표시할 위치
					title : "${map.map_corp}", // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
					image : markerImage,
					clickable: true
				// 마커 이미지 
				});
	
			// 마커가 지도 위에 표시되도록 설정합니다
			marker.setMap(map);
	
		</script>

		<table border="1" width="300px" height="320px">
			<tr>
				<td>업체명 : ${map.map_corp} <br> <br> 업체주소 :
					${map.map_address} <br> <br> 전화번호 : ${map.map_contents} <br>
					<br>
				</td>
			</tr>
		</table>
	</div>
	<br>
	<div class="contents_review_area_map">
		<table>
			<form method="post" name="review"
				action="/DaengDaeng/board/map/reviewPro.do?map_code=${map.map_code}">
				<div class="rev_title">
					<h2 class="line">작성자</h2>
					<span>${memberDTO.mem_name}</span>
				</div>
				<div class="rev_commbox">
					<textarea name="rev_contents" rows="10" cols="60">후기를 입력해 주세요.</textarea>
					<input type="submit" align="center" value="코멘트달기" /> <input
						type="hidden" name="rev_typenum" value="${map.map_code}">
					<input type="hidden" name="rev_typecode" value="지도">
				</div>
			</form>
		</table>
		<table width="800" cellpadding="0" cellspacing="0" align="center">
			<thead style="background-color: #EE8300; color: #fff;">
				<tr height="23">
					<th align=center width="50">작성자</th>
					<th align=center width="80">작성일</th>
					<th>댓글</th>
					<th align=center width="80">삭제</th>
				</tr>
			</thead>
			<c:if test ="${map_review_list!=null}">
			<c:forEach items="${map_review_list}" var="review_data">
				<tr height="23">
					<td>${review_data.rev_writer_name}</td>
					<td>${review_data.rev_date}</td>
					<td>${review_data.rev_contents}</td>
					<c:if test="${memberDTO.mem_code==review_data.rev_memcode}">
					<td><button onclick="javascript:location.href='/DaengDaeng/board/map/mapreviewdeletePro.do?rev_num=${review_data.rev_num}&map_code=${map.map_code }'">삭제</button></td>
				</c:if>
				<c:if test="${memberDTO.mem_code!=review_data.rev_memcode }">
				&nbsp;
				</c:if>
				</tr>
			</c:forEach>
			</c:if>
		</table>
	</div>
</body>
</html>
</body>
</html>