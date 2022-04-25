<template>
	<div class="post-container">
		<div class="photo-container">
			<i
				v-if="postLength && photoIdx != 0"
				class="fas fa-caret-left fa-2x"
				@click="goLeft()"
			></i>
			<i
				v-if="postLength && photoIdx != postLength - 1"
				class="fas fa-caret-right fa-2x"
				@click="goRight()"
			></i>
			<div v-if="postLength" class="post-preview-wrapper">
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
					v-if="isMySelf && !isEditMode"
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
						placeholder="가게의 후기를 남겨주세요. 글자 수는 최대 120자 이내입니다."
					/>
					<p
						v-if="!isContentsValid"
						class="validation-text warning isContentTooLong"
					>
						가게의 후기를 남겨주셔야 합니다. 글자 수는 최대 120자 이내입니다.
					</p>
				</div>

				<div v-else>
					<label>
						{{ post.contents }}
					</label>
				</div>
			</div>
			<div v-if="isValid">
				<button class="edt-btn" @click="fetchEditPost()">수정하기</button>
				<button class="cancel-btn" @click="cancel()">취소하기</button>
			</div>
		</div>
		<modal-view v-if="isModalOpen" @onCloseModal="isModalOpen = false">
			<div slot="body">
				<post-edit
					@onDeletePost="deleteConfirm"
					@onEditPost="EditPost"
				></post-edit>
			</div>
		</modal-view>
		<modal-view
			v-if="isDeleteModalOpen"
			@onCloseModal="isDeleteModalOpen = false"
		>
			<div slot="body" class="delete-body">
				<span>정말 지우시겠습니까?</span>
			</div>
			<div slot="footer" class="delete-footer">
				<button class="del-btn" @click="deletePost()">삭제하기</button>
				<button @click="canceldelete()">취소하기</button>
			</div>
		</modal-view>
	</div>
</template>

<script>
import { mapGetters } from 'vuex';
import ModalView from '@/components/common/modal/ModalView.vue';
import PostEdit from '@/components/common/modal/PostEditModal.vue';
import { deletePost } from '@/api/posts';

export default {
	components: { ModalView, PostEdit },
	data() {
		return {
			photoIdx: 0,
			showLeft: '',
			showRight: '',
			isModalOpen: false,
			isDeleteModalOpen: false,
			isEditMode: false,
		};
	},
	computed: {
		...mapGetters(['post', 'user']),

		postTitle() {
			return this.post.title;
		},
		getAddress() {
			return this.post.address
				? this.post.address.postcode +
						' ' +
						this.post.address.street +
						' ' +
						this.post.address.detailStreet
				: false;
		},
		postLength() {
			return this.post.image ? this.post.image.length : false;
		},
		isContentsValid() {
			return this.post.contents
				? this.post.contents.length <= 120 && this.post.contents.length > 0
				: false;
		},
		isValid() {
			return this.isEditMode && this.isContentsValid ? true : false;
		},
		isMySelf() {
			return this.user
				? this.user.userId == this.post.userId
					? true
					: false
				: false;
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
				this.$router.push('/user/' + userId);
			}
		},
		openEditModal() {
			this.isModalOpen = true;
		},
		deleteConfirm() {
			this.isDeleteModalOpen = true;
		},
		async deletePost() {
			await deletePost(this.post.postSeq);
			this.isDeleteModalOpen = false;
			this.isModalOpen = false;
			this.$emit('onClosePost');
			this.$emit('refresh');
		},
		async fetchRepostPost() {
			console.log(1);
		},
		EditPost() {
			this.isEditMode = true;
			this.isModalOpen = false;
		},

		fetchEditPost() {
			const formData = new FormData();
			formData.append('postSeq', this.post.postSeq);
			formData.append('contents', this.post.contents);
			this.$store.dispatch('fetchEditPost', formData);
			this.isEditMode = false;
		},

		canceldelete() {
			this.isDeleteModalOpen = false;
		},
		cancel() {
			this.isModalOpen = false;
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
	max-width: 50%;
	height: 100%;
	background: #000;
}

.post-preview-wrapper {
	position: relative;
	overflow: hidden;
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
	position: absolute;

	cursor: pointer;
}

.fa-caret-right {
	right: 0;
	position: absolute;
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

.edt-btn {
	position: absolute;
	right: 80px;
	bottom: 0;
	margin-right: 4px;
}

.cancel-btn {
	position: absolute;
	right: 0;
	bottom: 0;
	margin-right: 4px;
}

.delete-body {
	height: 50px;
	text-align: center;
	padding-top: 13px;
}

.delete-footer .del-btn {
	color: #fd0000;
}

.delete-footer button:hover {
	background-color: #a6a6a6;
}

.fa-bell-on {
	color: #000;
	position: absolute;
	right: 10px;
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
