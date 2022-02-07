<template>
	<div>
		<div class="container">
			<post-add-form></post-add-form>
			<!-- <div class="head">
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
			</div> -->
		</div>
	</div>
</template>

<script>
import { mapGetters } from 'vuex';
import PostAddForm from '../components/posts/PostAddForm.vue';

export default {
	components: {
		PostAddForm,
	},
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
		async upload() {
			try {
				const postData = {
					title: this.title,
					content: this.content,
					postImageUrl: this.postImageUrl,
					userId: this.user.userId,
				};

				await this.$store.dispatch('fetchPostUpload', postData);
				this.$router.push('/mypage/`${this.user.userId}`');
			} catch (error) {
				console.log(error.response.data);
			}
		},
	},
};
</script>

<style scoped>
* {
	box-sizing: border-box;
}
.container {
	width: 100%;
	height: 100%;
	padding: 120px 0;
	display: flex;
	justify-content: center;
}
</style>
