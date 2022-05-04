<template>
	<div>
		<div class="map_wrap">
			<div id="map" class="map"></div>

			<map-modal
				v-if="isModalOpen"
				class="post-modal"
				:postitems="overlap"
				@onCloseModal="onCloseModal"
			>
			</map-modal>
		</div>
	</div>
</template>

<script>
import MapModal from '@/components/common/modal/MapModal.vue';

export default {
	components: { MapModal },

	props: {
		postitems: {
			type: Array,
			default: () => ({}),
		},
	},
	data() {
		return {
			map: null,
			markerList: [],
			overlap: [],
			isModalOpen: false,
		};
	},

	watch: {
		postitems() {
			this.makeMaker();
		},
	},

	mounted() {
		window.kakao && window.kakao.maps
			? this.initMap()
			: this.addKakaoMapScript();
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
		},

		setCurrentPos() {
			navigator.geolocation.getCurrentPosition(position => {
				console.log(position.coords.latitude);
				console.log(position.coords.longitude);
			});
			// navigator.geolocation.watchPosition(position => {
			// 	const latitude = position.coords.latitude;
			// 	const longitude = position.coords.longitude;
			// 	console.log(latitude);
			// 	console.log(longitude);
			// });
			// navigator.geolocation.watchPosition(position => {
			// 	this.panTo(
			// 		this.map,
			// 		new kakao.maps.LatLng(
			// 			position.coords.latitude,
			// 			position.coords.longitude,
			// 		),
			// 	);
			// });
		},

		makeMaker() {
			this.initMarkers();
			if (this.postitems) {
				for (var i = 0; i < this.postitems.length; i++) {
					var imageSrc =
						'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png';

					var imageSize = new kakao.maps.Size(24, 35);
					var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

					const latlng = new kakao.maps.LatLng(
						this.postitems[i].address.ypos,
						this.postitems[i].address.xpos,
					);

					var marker = new kakao.maps.Marker({
						map: this.map, // 마커를 표시할 지도
						position: latlng, // 마커를 표시할 위치
						image: markerImage, // 마커 이미지
						title: this.postitems[i].title,
					});

					//커스텀 오버레이
					var content = `<div class="custom_overlay" style="background: #f5f5f5; border-radius: 6px; margin-bottom: 25px;" >
										${this.postitems[i].title}
									</div>`;

					var customOverlay = new kakao.maps.CustomOverlay({
						clickable: true,
						content: content,
						position: latlng,
						xAnchor: 0.5,
						yAnchor: 1.25,
						zIndex: 3,
					});

					//마커 클릭시 모달창 표시
					kakao.maps.event.addListener(
						marker,
						'click',
						this.markerClickListener(this.map, marker, this.postitems),
					);
					//마커 이동시 customOverlay 표시
					kakao.maps.event.addListener(
						marker,
						'mouseout',
						this.markerOutListener(this.map, customOverlay),
					);
					//마커 이동시 customOverlay 표시
					kakao.maps.event.addListener(
						marker,
						'mouseover',
						this.markerOverListener(this.map, marker, customOverlay),
					);

					marker.setMap(this.map);
					this.markerList.push(marker);
				}
			}
		},
		initMarkers() {
			this.markerList.forEach(marker => {
				marker.setMap(null);
			});
			this.markerList = [];
		},

		markerClickListener(map, marker, posts) {
			return () => {
				this.panTo(map, marker.getPosition());
				posts.filter(p => {
					if (p.title == marker.getTitle()) {
						this.overlap.push(p);
					}
				});
				if (this.overlap) {
					this.isModalOpen = true;
				}
			};
		},
		markerOverListener(map, marker, customOverlay) {
			return () => {
				customOverlay.setMap(map); // 지도에 올림
				customOverlay.setVisible(true); // 지도에서 보이게 함
			};
		},
		markerOutListener(map, customOverlay) {
			return () => {
				customOverlay.setMap(null); // 지도에서 보이지 않게 함

				customOverlay.setVisible(null); // 지도에서 보이지 않게 함
			};
		},
		panTo(map, latlng) {
			map.panTo(latlng);
		},
		onCloseModal() {
			this.overlap = [];

			this.isModalOpen = false;
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
	background: transparent;
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

	transition: 0.2s;
}

.map_wrap .custom_typecontrol:hover {
	transform: translate(0, -3px);
}

.customoverlay {
	position: relative;
	bottom: 85px;
	border-radius: 6px;
	border: 1px solid #ccc;
	border-bottom: 2px solid #ddd;
	float: left;
}

@media only screen and (max-width: 480px) {
	.map_wrap {
		height: 400px;
	}
}
</style>
