<template>
	<div class="contents">
		<h1 class="page-header">후기 작성하기</h1>
		<div class="form-wrapper">
			<form class="form">
				<div class="title">
					<label> 상호명 </label>
					<input id="title" v-model="title" type="text" />
				</div>
				<div class="address">
					<div class="zipcode">
						<label> 주소 </label>
						<input
							v-model="postcode"
							type="text"
							placeholder="우편번호"
							style="width: 110px"
							maxlength="5"
						/>
						<button class="zipcodebtn" @click="openDaumPostcode">
							우편번호
						</button>
					</div>

					<div class="detail-address">
						<input type="text" placeholder="주소" style="width: 280px" />
						<input
							type="text"
							placeholder="상세주소"
							style="margin-left: 15px"
						/>
					</div>
				</div>
				<div class="photo-upload">
					<button>불러오기<i class="fas fa-camera"></i></button>

					<ul class="photo-list">
						<li>1</li>
						<li>2</li>
						<li>3</li>
					</ul>
				</div>
				<div>
					<textarea
						id="contents"
						v-model="contents"
						type="text"
						rows="5"
						placeholder="가게의 후기를 남겨주세요"
					/>
				</div>
				<div class="bottom">
					<button>저장하기</button>
				</div>
			</form>
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			title: '',
			postcode: '',
			address: '',
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
	},
};
</script>

<style scoped>
* {
	box-sizing: border-box;
}

.contents {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	border-top: 1px solid #dee2e6;
	border-bottom: 1px solid #dee2e6;
}

.form-wrapper {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

.page-header {
	border-bottom: 1px solid #dee2e6;
	padding: 15px 0;
}

.form {
	margin: 0;
	padding: 0;
}

.form div {
	padding-top: 20px;
}

.title label {
	margin-right: 20px;
}

.address label {
	margin-right: 34px;
}

.address .detail-address {
	margin-left: 62px;
	width: 500px;
}
.detail-address .detailaddress {
	margin-left: 30px;
}

.zipcodebtn {
	margin-left: 15px;
}

.form-wrapper {
	width: 680px;
}

.address {
	display: flex;
	flex-direction: column;
	justify-content: center;
}

.photo-upload {
	display: flex;
}

.photo-upload ul {
	display: flex;
}

.photo-upload li {
	padding-left: 20px;
}

.bottom {
	display: flex;
	justify-content: right;
}
input,
select,
textarea {
	border: 0;
	padding: 0;
	margin: 0;
	border: 1px solid #d1d4d6;
	background: transparent;
}

textarea {
	width: 680px;
	height: 200px;
}

button {
	font-size: 0.8rem;
	margin: 8px 0;
	border: 1px solid #fff;
	border-radius: 10px;
	background-color: #d1d4d6;
	text-decoration: none;
}
</style>
