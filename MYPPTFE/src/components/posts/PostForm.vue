<template>
	<div class="post-container">
		<div class="photo-container">
			<div class="post-preview-wrapper">
				<i v-show="post.image.length > 1" class="fas fa-caret-left fa-2x"></i>
				<i v-show="post.image.length > 1" class="fas fa-caret-right fa-2x"></i>
				<img :src="require(`/assets/${post.image[0].filePath}`)" />
			</div>
		</div>
		<div class="review-container">
			<div class="title">
				<h1>{{ post.title }}</h1>
			</div>
			<div class="address">
				<label>
					주소: <br />
					{{ getAddress }}</label
				>
			</div>
			<div class="address">
				<label>
					후기: <br />
					{{ post.contents }}
				</label>
			</div>
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
		getAddress() {
			return (
				this.post.address.postcode +
				' ' +
				this.post.address.street +
				' ' +
				this.post.address.detailStreet
				//padding-top: calc(500 / 1000 * 100%);
			);
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
	margin-right: 25px;
}

.post-preview-wrapper {
	position: relative;
}

.review-container {
	display: flex;
	width: 50%;
	flex-direction: column;
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
	cursor: pointer;
}

.review-container div {
	margin-bottom: 28px;
}
</style>
