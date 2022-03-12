<template>
	<div class="post-container">
		<div class="photo-container">
			<div class="post-preview-wrapper">
				<i
					v-if="postLength && photoIdx != 0"
					class="fas fa-caret-left fa-2x"
					@click="goLeft()"
				></i>
				<i
					v-show="postLength && photoIdx != postLength - 1"
					class="fas fa-caret-right fa-2x"
					@click="goRight()"
				></i>
				<img :src="require(`/assets/${post.image[photoIdx].filePath}`)" />
			</div>
		</div>
		<div class="review-container">
			<div class="title">
				<img
					class="my-picture"
					:src="post.profileImageUrl"
					@click="goUserPage(post.userId)"
				/>

				<span>{{ post.userName }}</span>
				<i
					v-if="isMySelf()"
					class="fas fa-solid fa-bars"
					@click="openEditModal()"
				></i>
			</div>
			<div class="line"></div>
			<div class="store-title">
				<h3>{{ postTitle }}</h3>
			</div>
			<div class="store-address">
				<label> {{ getAddress }}</label>
				<i class="fas fa-map fa-1x"></i>
			</div>
			<div class="line"></div>
			<div class="store-contents">
				<div v-if="isEditMode">
					<textarea
						id="contents"
						v-model="post.contents"
						type="text"
						rows="5"
						placeholder="가게의 후기를 남겨주세요. 글자 수는 최대 200자 이내입니다."
					/>
					<p
						v-if="!isContentsValid"
						class="validation-text warning isContentTooLong"
					>
						가게의 후기를 남겨주셔야 합니다. 글자 수는 최대 200자 이내입니다.
					</p>
				</div>

				<div v-else>
					<label>
						{{ post.contents }}
					</label>
				</div>
			</div>
			<button v-if="isValid()" class="edt-btn" @click="fetchEditPost()">
				수정하기
			</button>
		</div>
		<modal-view v-if="isModalOpen" @onCloseModal="isModalOpen = false">
			<div slot="body">
				<modal-post-edit
					@onDeletePost="deletePost"
					@onEditPost="EditPost"
				></modal-post-edit>
			</div>
		</modal-view>
	</div>
</template>
<script>
import { mapGetters } from 'vuex';
import ModalView from '@/components/common/modal/ModalView.vue';
import ModalPostEdit from '@/components/common/modal/PostEditModal.vue';
import { deletePost } from '@/api/posts';

export default {
	components: { ModalView, ModalPostEdit },
	data() {
		return {
			photoIdx: 0,
			showLeft: '',
			showRight: '',
			isModalOpen: false,
			isEditMode: false,
		};
	},
	computed: {
		...mapGetters(['post', 'user']),

		postTitle() {
			return this.post.title;
		},
		getAddress() {
			return (
				this.post.address.postcode +
				' ' +
				this.post.address.street +
				' ' +
				this.post.address.detailStreet
			);
		},
		postLength() {
			return this.post.image.length;
		},
		isContentsValid() {
			return this.post.contents.length <= 200 && this.post.contents.length > 0;
		},
	},
	methods: {
		goRight() {
			const leng = this.post.image.length;
			if (leng && this.photoIdx < leng - 1) this.photoIdx++;
		},

		goLeft() {
			const leng = this.post.image.length;
			if (leng && this.photoIdx > 0) this.photoIdx--;
		},

		goUserPage(userId) {
			if (!this.isEditMode) {
				if (!this.user && this.user.userId == userId) {
					this.$router.go(this.$router.currentRoute);
				} else {
					this.$router.push('/user/' + userId);
				}
			}
		},
		openEditModal() {
			this.isModalOpen = true;
		},
		deletePost() {
			deletePost(this.post.postSeq);
			this.isModalOpen = false;
			this.$emit('onClosePost');
		},
		EditPost() {
			this.isEditMode = true;
			this.isModalOpen = false;
		},
		isMySelf() {
			if (this.user) {
				if (this.post.userId == this.user.userId) {
					return true;
				}
			}
		},
		isValid() {
			if (this.isEditMode && this.isContentsValid) {
				return true;
			} else {
				return false;
			}
		},
		fetchEditPost() {
			const formData = new FormData();
			formData.append('postSeq', this.post.postSeq);
			formData.append('contents', this.post.contents);
			this.$store.dispatch('fetchEditPost', formData);
			this.isEditMode = false;
		},
	},
};
</script>

<style scoped>
* {
	box-sizing: border-box;
}

.post-container {
	display: flex;
	width: 100%;
	height: 100%;
	justify-content: center;
}
.photo-container {
	display: flex;
	flex-direction: column;
	justify-content: center;
	position: relative;
	width: 65%;
	background: #000;
}

.post-preview-wrapper {
	position: relative;
}

.review-container {
	position: relative;
	padding: 2% 10px;
	display: flex;
	width: 50%;
	flex-direction: column;
	background: #f5f5f5;
}

.fa-caret-left {
	left: 0;
	margin-top: 33%;

	cursor: pointer;
}

.fa-caret-right {
	right: 0;
	margin-top: 33%;
	cursor: pointer;
}

.fa-location-check {
	color: #000;
	margin-left: 8px;
	position: relative;
	z-index: 0;
}

.line {
	border-top: 1px solid #d3cfcf;
	margin-bottom: 10px;
}

.title {
	display: flex;
}

.title img {
	display: flex;
	justify-content: flex-end;
	align-items: center;
	width: 15%;
	height: 75%;
	cursor: pointer;
	margin-top: 2px;
	border-radius: 50%;
}

.store-title {
	margin-bottom: 10px;
}

.store-address {
	margin-bottom: 15px;
}

.stroe-contents {
	height: 250px;
}

.fa-bars {
	position: absolute;
	color: #000;
	margin-top: 13px;
	margin-right: 15px;
	right: 0%;
	cursor: pointer;
}

.fa-map {
	color: #a6a6a6;
	margin-left: 15px;
	margin-top: 3px;
}

.fa-map:hover {
	cursor: pointer;
	transform: translate(0, -3px);
	color: #000;
}

.edt-btn {
	position: absolute;
	right: 0;
	bottom: 0;
	margin-right: 4px;
}

span {
	margin-left: 15px;
	margin-top: 10px;
	font: 0.5rem;
}

i {
	position: absolute;
	z-index: 2;
	color: #fff;
}
img {
	position: relative;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
}
textarea {
	width: 100%;
	height: 6.25em;
	border: none;
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
