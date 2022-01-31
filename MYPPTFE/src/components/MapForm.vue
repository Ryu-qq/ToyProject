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

export default {
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

			this.customMap = new kakao.maps.Map(container, this.options);
			bus.$emit('end:spinner');
		},

		setCurrentPos() {
			let moveLatLon = new kakao.maps.LatLng(this.gps_lat, this.gps_lng);

			return this.customMap.setCenter(moveLatLon);
		},
	},
};
</script>

<style>
.map_wrap {
	width: 100%;
	height: 100%;
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
