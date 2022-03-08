<template>
	<div class="search-container">
		<div
			v-for="(review, index) in searchList.content"
			:key="index"
			class="review-wrapper"
		>
			<div class="review-photo">
				<img :src="require(`/assets/${review.image[0].filePath}`)" />
			</div>
			<div class="review-text">
				<div class="review-header">
					<h1 @click="goPost(`${review.postSeq}`)">{{ review.title }}</h1>
					<div class="my-picture">
						<img :src="review.profileUrl" @click="goUserPage(review.userId)" />
					</div>
				</div>

				<div class="top-line"></div>

				<h6>{{ review.address.street }}</h6>
				<div class="line"></div>
				<h6>{{ review.contents }}</h6>
			</div>
			<h6 class="review-writer">by {{ review.userName }}</h6>
		</div>
	</div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
	computed: {
		...mapGetters(['searchList']),
	},
	methods: {
		async goPost(endpoint) {
			await this.$store.dispatch('fetchPost', endpoint);
			this.$router.push('/post/' + endpoint);
		},
		goUserPage(userId) {
			if (this.$route.path !== '/userinfo/' + userId) {
				this.$router.push('/userinfo/' + userId);
			}
		},
	},
};
</script>

<style scoped>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}
.search-container {
	display: grid;
	padding: 0;
	margin: 0;
	grid-template-columns: 1fr 1fr 1fr;
	grid-template-rows: 1fr 1fr 1fr;
	grid-template-columns: repeat(auto-fill, minmax(36%, auto));

	row-gap: 28px;
	column-gap: 28px;
	width: 100%;
}

.review-wrapper {
	display: flex;
	position: relative;
	background: #f5f5f5;
	height: 180px;
}

.review-text {
	display: flex;
	position: relative;
	flex-direction: column;
}

.review-header {
	display: flex;
}
.review-writer {
	position: absolute;
	bottom: 0;
	right: 0;
}
.my-picture {
	display: flex;
	justify-content: flex-end;
	align-items: center;
	margin-left: 15px;
	width: 20%;
	height: 75%;
}

.my-picture img {
	cursor: pointer;
	margin-top: 2px;
	border-radius: 50%;
}

.review-photo {
	width: 40%;
}

.top-line {
	border-top: 1px solid #473535;
	margin-bottom: 15px;
}

.line {
	border-top: 1px solid #473535;
	margin-bottom: 10px;
}

img {
	width: 95%;
	height: 100%;
}

h1 {
	cursor: pointer;
	margin-bottom: 3px;
}
h1:hover {
	transform: translate(0, -3px);
	color: #a6a6a6;
}
h6 {
	margin-bottom: 15px;
}
</style>
