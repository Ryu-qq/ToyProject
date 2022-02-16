<template>
	<div>
		<div v-if="!posts.length" class="post-container">게시물이 없습니다.</div>

		<div>
			<spinner v-if="isLoading"></spinner>
			<div class="post-preview-container">
				<div
					v-for="file in posts"
					:key="file.postSeq"
					class="post-preview-wrapper"
				>
					<a :href="link">
						<img :src="require(`/assets/${file.image[0].filePath}`)" />
					</a>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { mapGetters } from 'vuex';
import Spinner from '@/components/common/Spinner.vue';
//import axios from 'axios';

export default {
	components: {
		Spinner,
	},
	data() {
		return {
			isLoading: false,
			preview: '',
		};
	},
	computed: {
		...mapGetters(['token', 'user', 'posts']),
	},
	created() {
		this.fetchPostList();
	},

	methods: {
		async fetchPostList() {
			//this.isLoading = true;

			// const { data } = await axios.get('http://localhost:8080/api/v1/mypage', {
			// 	params: {
			// 		userId: this.user.userId,
			// 	},
			// 	headers: {
			// 		'Content-Type': 'multipart/form-data',
			// 		Authorization: `Bearer ${this.token}`,
			// 	},
			// });
			// console.log(data);
			//this.postList = data.body.posts;
			this.$store.dispatch('fetchPostList');
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
