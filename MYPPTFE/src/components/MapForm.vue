<template>
	<div>
		<div class="map_wrap">
			<div id="map" class="map"></div>
			<button class="custom_typecontrol" @click="setCurrentPos()">
				<i id="icon" class="fas fa-crosshairs fa-3x"></i>
			</button>
		</div>
	</div>
</template>

<script>
import bus from '@/utils/bus.js';
import { mapGetters } from 'vuex';

export default {
	data() {
		return {
			map: null,
		};
	},
	computed: {
		...mapGetters(['userInfo']),
	},
	mounted() {
		bus.$emit('start:spinner');
		if (!('geolocation' in navigator)) {
			return;
		}

		navigator.geolocation.getCurrentPosition(
			pos => {
				this.gps_lat = pos.coords.latitude;
				this.gps_lng = pos.coords.longitude;

				window.kakao && window.kakao.maps
					? this.initMap()
					: this.addKakaoMapScript();
			},
			err => {
				console.log(err.message);
			},
		);
	},
	methods: {
		addKakaoMapScript() {
			const script = document.createElement('script');
			/* global kakao */
			script.onload = () => kakao.maps.load(this.initMap);
			script.src =
				'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=dfa31e0183999d6d13b92c75a089bb75';
			document.head.appendChild(script);
		},

		initMap() {
			let container = document.getElementById('map');
			this.options = {
				center: new kakao.maps.LatLng(37.570137, 126.977018),
				level: 6,
			};

			this.map = new kakao.maps.Map(container, this.options);
			this.makeMaker();
			bus.$emit('end:spinner');
		},

		setCurrentPos() {
			let moveLatLon = new kakao.maps.LatLng(this.gps_lat, this.gps_lng);
			return this.map.setCenter(moveLatLon);
		},
		makeMaker() {
			if (this.userInfo.userPostList.length > 0) {
				const position = [];
				for (var i = 0; i < this.userInfo.userPostList.length; i++) {
					position.push({
						title: this.userInfo.posts[i].title,
						latlng: new kakao.maps.LatLng(
							this.userInfo.posts[i].ypos,
							this.userInfo.posts[i].xpos,
						),
					});
				}

				var markerPosition = position;
				var imageSrc =
					'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png';

				for (var j = 0; j < markerPosition.length; j++) {
					// 마커 이미지의 이미지 크기 입니다
					var imageSize = new kakao.maps.Size(24, 35);

					// 마커 이미지를 생성합니다
					var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

					// 마커를 생성합니다
					var marker = new kakao.maps.Marker({
						map: this.map, // 마커를 표시할 지도

						title: markerPosition[j].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
						position: markerPosition[j].latlng, // 마커를 표시할 위치
						image: markerImage, // 마커 이미지
					});
					marker.setMap(this.map);
				}
			}
		},
	},
};
</script>

<style scoped>
.map_wrap {
	width: 100%;
	height: 500px;
	position: relative;
	overflow: hidden;
}

.map {
	width: 100%;
	height: 100%;
	padding: 10%;
}

.map_wrap .custom_typecontrol {
	background-color: #fff;
	position: absolute;
	top: 10px;
	right: 23px;
	align-content: flex-end;
	overflow: hidden;
	width: auto;
	height: auto;
	margin: 0;
	padding: 0;
	z-index: 1;
	border: none;
	font-size: 0.6rem;
	cursor: pointer;
	box-shadow: 10px 10px 10px -5px rgba(25, 42, 70, 0.2);

	transition: 0.2s;
}

.map_wrap .custom_typecontrol:hover {
	transform: translate(0, -3px);
}
</style>
