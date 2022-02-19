<template>
	<div class="post-container">
		<div class="photo-container">
			<div
				v-for="(file, index) in post.image"
				:key="index"
				class="post-preview-wrapper"
			>
				<img :src="require(`/assets/${file.filePath}`)" />
			</div>
			<i class="fas fa-caret-left fa-2x"></i>
			<i class="fas fa-caret-right fa-2x"></i>
		</div>
		<div class="review-container">
			<div></div>
		</div>
	</div>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
	computed: {
		...mapGetters(['post', 'user']),

		postTitle() {
			return this.post.title;
		},
	},
};
</script>

<style scoped>
* {
	box-sizing: border-box;
}

.post-container {
	padding: 70px 0;
	display: flex;
	width: 100%;
	height: 100%;
	justify-content: center;
}
.photo-container {
	position: relative;
	width: 50%;
	height: 0;
	padding-top: calc(500 / 1000 * 100%);
}
.review-container {
	display: flex;
	flex-direction: column;
}

.fa-caret-left {
	position: absolute;
	z-index: 2;
	color: #fff;
}
.fa-caret-right {
	position: absolute;
	z-index: 2;
	color: #fff;
}
img {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	cursor: pointer;
}
</style>
