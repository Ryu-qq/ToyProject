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
	data() {
		return {
			photoIdx: 0,
			showLeft: '',
			showRight: '',
		};
	},
	computed: {
		...mapGetters(['post', 'user', 'userInfo']),

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
		postLength() {
			return this.post.image.length;
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
	display: flex;
	flex-direction: column;
	justify-content: center;
	position: relative;
	width: 50%;
	background: #000;
}

.post-preview-wrapper {
	position: relative;
}

.review-container {
	padding: 0 5%;
	display: flex;
	width: 50%;
	flex-direction: column;
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

.review-container div {
	margin-bottom: 28px;
}
</style>
