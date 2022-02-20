<template>
	<div class="room-deal-information-container">
		<div class="room-file-upload-wrapper">
			<div v-if="!files.length" class="room-file-upload-example-container">
				<div class="room-file-upload-example">
					<div class="room-file-notice-item">
						실사진 최소 3장 이상 등록하셔야 하며, 가로사진을 권장합니다.
					</div>

					<div class="room-file-notice-item room-file-notice-item-red">
						음식 사진을 제외한 타인에게 피해를 줄 수 있는 불필요한 정보가 있는
						사진은 <br />
						추후에 서비스 이용에 제한이 있을 수 있습니다.
					</div>

					<div class="room-file-notice-item room-file-upload-button">
						<div class="image-box">
							<label for="file"><i class="fas fa-camera"></i></label>

							<input
								id="file"
								ref="files"
								type="file"
								multiple
								@change="imageUpload"
							/>
						</div>
					</div>
				</div>
			</div>

			<div v-else class="file-preview-content-container">
				<div class="file-preview-container">
					<div
						v-for="(file, index) in files"
						:key="index"
						class="file-preview-wrapper"
					>
						<div
							class="file-close-button"
							:name="file.number"
							@click="fileDeleteButton"
						>
							x
						</div>
						<img :src="file.preview" />
					</div>

					<div v-show="files.length < 3" class="file-preview-wrapper-upload">
						<div class="image-box">
							<label for="file"><i class="fas fa-camera"></i></label>
							<!-- <label for="file">추가 사진 등록</label> -->
							<input
								id="file"
								ref="files"
								type="file"
								multiple
								@change="imageAddUpload"
							/>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			files: [], //업로드용 파일
			filesPreview: [],
			uploadImageIndex: 0, // 이미지 업로드를 위한 변수
			isTure: false,
		};
	},
	methods: {
		imageUpload() {
			//console.log(this.$refs.files.files);

			//하나의 배열로 넣기
			let num = -1;
			for (let i = 0; i < this.$refs.files.files.length; i++) {
				this.files = [
					...this.files,
					//이미지 업로드
					{
						//실제 파일
						file: this.$refs.files.files[i],
						//이미지 프리뷰
						preview: URL.createObjectURL(this.$refs.files.files[i]),
						//삭제및 관리를 위한 number
						number: i,
					},
				];
				this.$store.dispatch('fetchImageUpload', this.files);
				num = i;
			}

			this.uploadImageIndex = num + 1; //이미지 index의 마지막 값 + 1 저장
		},

		imageAddUpload() {
			//하나의 배열로 넣기
			let num = -1;
			for (let i = 0; i < this.$refs.files.files.length; i++) {
				this.files = [
					...this.files,
					//이미지 업로드
					{
						//실제 파일
						file: this.$refs.files.files[i],
						//이미지 프리뷰
						preview: URL.createObjectURL(this.$refs.files.files[i]),
						//삭제및 관리를 위한 number
						number: i + this.uploadImageIndex,
					},
				];
				this.$store.dispatch('fetchImageUpload', this.files);

				num = i;
			}
			this.uploadImageIndex = this.uploadImageIndex + num + 1;

			//console.log(this.files);
		},
		fileDeleteButton(e) {
			const name = e.target.getAttribute('name');
			this.files = this.files.filter(data => data.number !== Number(name));
			this.$store.dispatch('fetchImagedelete', name);
		},
	},
};
</script>

<style scoped>
.main-container {
	width: 1200px;
	height: 400px;
	margin: 0 auto;
}

.room-deal-information-container {
	width: 100%;
	padding-top: 20px;
	color: #222222;
}

.room-deal-information-content-wrapper {
	min-height: 50px;
	display: flex;
}

.room-deal-informtaion-content-title {
	font-size: 15px;
	display: flex;
	align-items: center;
	justify-content: center;
	width: 150px;
	background-color: #f9f9f9;
}

.room-deal-information-content {
	width: 100%;
	font-size: 14px;
}

.room-deal-option-selector {
	display: flex;
	align-items: center;
	padding: 15px;
}

.room-deal-option-item {
	width: 100px;
	height: 40px;
	display: flex;
	align-items: center;
	justify-content: center;
	border: 1px solid #cccccc;
	border-radius: 5px;
	cursor: pointer;
}

.room-deal-option-item:last-child {
	margin-left: 10px;
}

.room-deal-option-notice {
	margin-left: auto;
	font-size: 14px;
	color: #888888;
}

.room-deal-option-item-deposit {
	margin-left: 10px;
}

.room-deal-information-wrapper {
	display: flex;
	flex-direction: column;
}

.room-deal-information-option {
	padding: 10px;
	display: flex;
	align-items: center;
}

.room-deal-information-option:last-child {
	border-bottom: 1px solid #dddddd;
}

.room-deal-information-item-type {
	font-size: 13px;
	color: #fff;
	background-color: #61b6e5;
	min-width: 50px;
	height: 26px;
	display: flex;
	justify-content: center;
	align-items: center;
	border-radius: 3px;
}

.room-deal-information-item-wrapper {
	display: flex;
	align-items: center;
	margin-left: 10px;
	height: 46px;
	width: 100%;
}

.room-deal-information-item-wrapper > input {
	border: 1px solid #dddddd;
	width: 140px;
	height: 100%;
	padding: 0 15px;
	font-size: 15px;
}

.room-deal-inforamtion-won {
	margin: 0 10px;
}

.room-deal-information-example {
	color: #888888;
}

.room-deal-information-option:not(:first-child) {
	margin-top: 10px;
}

.room-deal-inforamtion-divide {
	font-size: 22px;
	margin: 0 8px;
	color: #222222;
	font-weight: 100;
}

.room-deal-close-button-wrapper {
	margin-left: auto;
	cursor: pointer;
}

.room-deal-close-button {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 22px;
	height: 22px;
	background-color: #666666;
	color: rgb(220, 220, 220);
}

.room-deal-cliked {
	background-color: rgb(235, 235, 235);
	color: rgb(170, 170, 170);
}

.room-file-upload-example {
	height: 100%;
}

.room-write-content-container {
	border-top: 1px solid #dddddd;
	min-height: 260px;
}

.room-picture-notice {
	margin: 20px;
	padding: 20px 40px;
	border: 1px solid #dddddd;
}

.file-preview-content-container {
	height: 100%;
}

.room-file-upload-wrapper {
	background-color: #f4f4f4;
	min-height: 210px;
	font-size: 0.9rem;
	color: #888888;
	display: flex;
	align-items: center;
	justify-content: center;
}

.room-file-upload-example-container {
	display: flex;
	align-items: center;
	justify-content: center;
}

.room-file-image-example-wrapper {
	text-align: center;
}

.room-file-notice-item {
	margin-top: 5px;
	text-align: center;
}

.room-file-notice-item-red {
	color: #ef4351;
}

.image-box {
	margin-top: 30px;
	padding-bottom: 20px;
	text-align: center;
}

.image-box input[type='file'] {
	position: absolute;
	width: 0;
	height: 0;
	padding: 0;
	overflow: hidden;
	border: 0;
}

.image-box label {
	display: inline-block;
	padding: 10px 20px;
	background-color: #232d4a;
	color: #fff;
	vertical-align: middle;
	font-size: 15px;
	cursor: pointer;
	border-radius: 5px;
}

.file-preview-wrapper {
	padding: 10px;
	position: relative;
}

.file-preview-wrapper > img {
	position: relative;
	width: 190px;
	height: 130px;
	z-index: 10;
}

.file-close-button {
	position: absolute;
	/* align-items: center; */
	line-height: 18px;
	z-index: 99;
	font-size: 18px;
	right: 5px;
	top: 10px;
	color: #fff;
	font-weight: bold;
	background-color: #666666;
	width: 20px;
	height: 20px;
	text-align: center;
	cursor: pointer;
}

.file-preview-container {
	height: 100%;
	display: flex;
	flex-wrap: wrap;
}

.file-preview-wrapper-upload {
	margin: 10px;
	padding-top: 20px;
	width: 190px;
	height: 130px;
}

.room-write-button-wrapper {
	margin-top: 20px;
	display: flex;
	justify-content: center;
	align-items: center;
	color: #222222;
}

.room-write-button-wrapper > div {
	width: 160px;
	height: 50px;
	border: 1px solid #dddddd;
	display: flex;
	justify-content: center;
	align-items: center;
	font-size: 15px;
	cursor: pointer;
}

.room-write-button {
	margin-left: 15px;
	color: #fff;
	background-color: #1564f9;
}

.room-write-button:hover {
	opacity: 0.8;
}
</style>
