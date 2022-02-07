<template>
	<div>
		<label> 주소 </label>
		<!-- <input v-model="postcode" type="text" placeholder="우편번호" /> -->
		<button class="btn" @click="sample5_execDaumPostcode">우편번호</button>
		<input id="address" type="text" placeholder="주소" />
		<!-- <input id="detailAddress" type="text" placeholder="상세주소" />
		<input id="extraAddress" type="text" placeholder="참고항목" /> -->
		<div
			id="map"
			style="width: 300px; height: 300px; margin-top: 10px; display: none"
		></div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			title: '',
			postcode: '',
			address: '',
			extraAddress: '',
		};
	},

	methods: {
		openDaumPostcode() {
			new window.daum.Postcode({
				oncomplete: data => {
					if (this.extraAddress !== '') {
						this.extraAddress = '';
					}
					if (data.userSelectedType === 'R') {
						// 사용자가 도로명 주소를 선택했을 경우
						this.address = data.roadAddress;
					} else {
						// 사용자가 지번 주소를 선택했을 경우(J)
						this.address = data.jibunAddress;
					}

					// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
					if (data.userSelectedType === 'R') {
						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
							this.extraAddress += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== '' && data.apartment === 'Y') {
							this.extraAddress +=
								this.extraAddress !== ''
									? `, ${data.buildingName}`
									: data.buildingName;
						}
						// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (this.extraAddress !== '') {
							this.extraAddress = `(${this.extraAddress})`;
						}
					} else {
						this.extraAddress = '';
					}
					// 우편번호를 입력한다.
					this.postcode = data.zonecode;
				},
			}).open();
		},
		sample5_execDaumPostcode() {
			new daum.Postcode({
				oncomplete: function (data) {
					var addr = data.address; // 최종 주소 변수

					// 주소 정보를 해당 필드에 넣는다.
					document.getElementById('sample5_address').value = addr;
					// 주소로 상세 정보를 검색
					geocoder.addressSearch(data.address, function (results, status) {
						// 정상적으로 검색이 완료됐으면
						if (status === daum.maps.services.Status.OK) {
							var result = results[0]; //첫번째 결과의 값을 활용

							// 해당 주소에 대한 좌표를 받아서
							var coords = new daum.maps.LatLng(result.y, result.x);
							// 지도를 보여준다.
							mapContainer.style.display = 'block';
							map.relayout();
							// 지도 중심을 변경한다.
							map.setCenter(coords);
							// 마커를 결과값으로 받은 위치로 옮긴다.
							marker.setPosition(coords);
						}
					});
				},
			}).open();
		},
	},
};
</script>

<style>
* {
	box-sizing: border-box;
}

.address {
	display: flex;
	flex-direction: column;
	justify-content: center;
}
</style>
