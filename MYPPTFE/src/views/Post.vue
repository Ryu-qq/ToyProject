<template>
	<div class="photo-card">
		<div class="container">
			<div class="head">
				제목
				<input
					v-model="title"
					class="search-input"
					type="text"
					placeholder="제목을 입력하세요"
				/>
			</div>
			<div class="body">
				내용
				<input
					v-model="content"
					class="search-input"
					type="text"
					placeholder="내용을 입력하세요"
				/>

				이미지URL
				<input
					v-model="postImageUrl"
					class="search-input"
					type="text"
					placeholder="이미지URL을 입력하세요"
				/>

				<button @click="upload()">저장하기</button>
			</div>
		</div>
	</div>
</template>

<script>
import postApi from '../api/post.js';

import { mapGetters } from 'vuex';

export default {
	data() {
		return {
			title: '',
			content: '',
			postImageUrl: '',
			userId: '',
		};
	},
	computed: {
		...mapGetters(['user']),
	},
	methods: {
		upload() {
			postApi.Uploadpost(
				{
					title: this.title,
					content: this.content,
					postImageUrl: this.postImageUrl,
					userId: this.user.userId,
				},
				body => {
					this.setToken(body.token);
				},
			);
		},
	},
};
</script>

<style scoped>
.container {
	width: 100%;
	height: 100%;
	padding-top: 175px;
	display: flex;
	flex-direction: column;
	justify-content: center;
}
</style>
