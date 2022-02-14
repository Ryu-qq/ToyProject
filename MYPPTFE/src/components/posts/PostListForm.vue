<template>
	<div>
		<div v-if="!postList" class="post-container">게시물이 없습니다.</div>

		<div v-else>
			<spinner v-if="isLoading"></spinner>
			<div class="post-preview-container">
				<div
					v-for="(file, index) in files"
					:key="index"
					class="post-preview-wrapper"
				>
					<img :src="file.preview" />
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
			files: [],
			isLoading: false,
		};
	},
	computed: {
		...mapGetters(['token', 'user']),
		postList() {
			return this.$store.state.posts;
		},
	},
	created() {
		this.getPostList();
	},
	methods: {
		async getPostList() {
			this.isLoading = true;
			await this.$store.dispatch('fetchPostList', {
				params: {
					userId: this.user.userId,
				},
			});
			this.isLoading = false;
		},
	},
};
</script>

<style>
.post-preview-content-container {
	height: 100%;
}

.post-preview-container {
	height: 100%;
	display: flex;
	flex-wrap: wrap;
}
</style>
