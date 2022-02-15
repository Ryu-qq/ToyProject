<template>
	<div>
		<!-- <div v-if="!postList.length" class="post-container">게시물이 없습니다.</div> -->

		<div>
			<spinner v-if="isLoading"></spinner>
			<div class="post-preview-container">
				<div
					v-for="file in postList"
					:key="file.postSeq"
					class="post-preview-wrapper"
				>
					<img :src="require(`/public/${file.image[0].filePath}`)" />
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { mapGetters } from 'vuex';
import Spinner from '@/components/common/Spinner.vue';
import axios from 'axios';

export default {
	components: {
		Spinner,
	},
	data() {
		return {
			postList: [],
			isLoading: false,
			preview: '',
		};
	},
	computed: {
		...mapGetters(['token', 'user']),
	},
	created() {
		this.fetchPostList();
	},

	methods: {
		async fetchPostList() {
			//this.isLoading = true;

			const { data } = await axios.get('http://localhost:8080/api/v1/mypage', {
				params: {
					userId: this.user.userId,
				},
				headers: {
					'Content-Type': 'multipart/form-data',
					Authorization: `Bearer ${this.token}`,
				},
			});
			console.log(data);
			this.postList = data.body.posts;

			// const { data } = await postsApi.getPostList(
			// this.$store.dispatch('fetchPostList', {
			// 	params: {
			// 		userId: this.user.userId,
			// 	},
			// });
			// console.log(data);
			//this.isLoading = false;
		},
	},
};
</script>

<style scoped>
.post-preview-content-container {
	height: 100%;
}

.post-preview-container {
	height: 100%;
	display: flex;
	flex-wrap: wrap;
}

img {
	width: 250px;
	height: 250px;
}
</style>
