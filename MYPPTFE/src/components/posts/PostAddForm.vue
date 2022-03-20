<template>
	<div class="contents">
		<h1 class="page-header">후기 작성하기</h1>
		<div class="form-wrapper">
			<form class="form" @submit.prevent="submitForm">
				<div class="title">
					<label> 상호명 </label>
					<input
						id="title"
						v-model="title"
						type="text"
						placeholder="가게이름을 입력해주세요"
					/>
					<select v-model="form.category">
						<option selected>카테고리</option>
						<option v-for="(category, index) in categories" :key="index">
							{{ category }}
						</option>
					</select>
					<p
						v-if="(!isTitleValid || !isCategoryValid) && isSubmit"
						class="validation-text warning isContentTooLong"
					>
						상호명과 카테고리를 고르셔야 합니다.
					</p>
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
						<input
							v-model="street"
							type="text"
							placeholder="주소"
							style="width: 280px"
						/>
						<input
							v-model="detailStreet"
							type="text"
							placeholder="상세주소"
							style="margin-left: 15px"
						/>
					</div>
					<p
						v-if="!isStreetValid && isSubmit"
						class="validation-text warning isContentTooLong"
					>
						주소를 입력하셔야 합니다.
					</p>
				</div>
				<div class="photo-upload">
					<ImageUpload @ImageUpload="imgUpload"></ImageUpload>
					<p
						v-if="!isImgaeValid && isSubmit"
						class="validation-text warning isContentTooLong warningPhoto"
					>
						사진은 최소 한장이상 올려주셔야 합니다.
					</p>
				</div>
				<div>
					<textarea
						id="contents"
						v-model="contents"
						type="text"
						rows="5"
						placeholder="가게의 후기를 남겨주세요. 글자 수는 최대 120자 이내입니다."
					/>
					<p
						v-if="!isContentsValid"
						class="validation-text warning isContentTooLong"
					>
						글자 수는 최대 120자 이내입니다.
					</p>
				</div>

				<div type="submit" class="bottom">
					<button>저장하기</button>
				</div>
			</form>
		</div>
	</div>
</template>

<script>
import ImageUpload from '../common/ImageUpload.vue';
import { mapGetters } from 'vuex';
import { uploadPost } from '@/api/posts';
export default {
	components: {
		ImageUpload,
	},
	data() {
		return {
			title: '',
			postcode: '',
			street: '',
			detailStreet: '',
			images: [],
			contents: '',
			categories: ['한식', '중식', '일식', '양식', '분식', '포차', '기타'],
			form: {
				keyword: '',
				category: '카테고리',
			},
			isSubmit: false,
		};
	},
	computed: {
		...mapGetters(['token', 'user']),

		isContentsValid() {
			return this.contents.length <= 120;
		},
		isStreetValid() {
			return this.street.length > 0;
		},
		isTitleValid() {
			return this.title.length > 0;
		},
		isCategoryValid() {
			return this.form.category == '카테고리' ? false : true;
		},
		isImgaeValid() {
			return this.images ? (this.images.length > 0 ? true : false) : false;
		},
	},

	methods: {
		imgUpload(imageList) {
			this.images = imageList;
		},
		isValid() {
			if (
				this.title.length < 0 ||
				this.form.category == '카테고리' ||
				this.postcode.length < 0 ||
				this.street.length < 0 ||
				this.images.length < 0
			)
				return false;
			else {
				return true;
			}
		},
		openDaumPostcode() {
			new window.daum.Postcode({
				oncomplete: data => {
					if (data.userSelectedType === 'R') {
						// 사용자가 도로명 주소를 선택했을 경우
						this.street = data.roadAddress;
					} else {
						// 사용자가 지번 주소를 선택했을 경우(J)
						this.street = data.jibunAddress;
					}
					// 우편번호를 입력한다.
					this.postcode = data.zonecode;
				},
			}).open();
		},
		async submitForm() {
			this.isSubmit = true;
			if (!this.isValid()) {
				return;
			}
			try {
				const formData = new FormData();
				if (this.images && this.images.length > 0) {
					for (let i = 0; i < this.images.length; i++) {
						const imageForm = this.images[i];
						formData.append('files', imageForm.file);
					}
				}

				formData.append('userId', this.user.userId);
				formData.append('title', this.title);
				formData.append('category', this.form.category);
				formData.append('contents', this.contents);
				formData.append('postcode', this.postcode);
				formData.append('street', this.street);
				formData.append('detailStreet', this.detailStreet);
				await uploadPost(formData);
				this.isSubmit = false;
				const userId = this.user.userId;
				if (this.$route.path !== '/user/' + userId) {
					this.$router.push('/user/' + userId);
				}
			} catch (error) {
				console.log(error);
			}
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
	flex-direction: column;
}

.bottom {
	display: flex;
	justify-content: right;
}

.warningPhoto {
	padding-top: 10px;
}
input,
textarea {
	border: 0;
	padding: 0;
	margin: 0;
	border-bottom: 1px solid #d1d4d6;
	background: transparent;
}

select {
	border: 0;
	padding: 0;
	margin-left: 10px;
	background: #d1d4d6;
	border-radius: 10px;
	margin-right: 10px;
	padding: 0 6px;
}

textarea {
	width: 680px;
	height: 10%;
	border: 1px solid #d1d4d6;
}

button {
	font-size: 1rem;
	margin: 8px 0;
	border: 1px solid #fff;
	border-radius: 10px;
	background-color: #d1d4d6;
	text-decoration: none;
}
</style>
