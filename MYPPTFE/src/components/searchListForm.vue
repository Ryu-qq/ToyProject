<template>
	<div class="search-container">
		<div v-for="(review, index) in search" :key="index" class="review-wrapper">
			<div class="review-photo">
				<img :src="require(`/assets/${review.image[0].filePath}`)" />
			</div>
			<div class="review-text">
				<div class="review-header">
					<p @click="goPost(`${review.postSeq}`)">{{ review.title }}</p>
					<div class="my-picture">
						<img :src="review.profileUrl" @click="goUserPage(review.userId)" />
					</div>
				</div>
				<div class="top-line"></div>

				<h6>{{ review.address.street }}</h6>
				<div class="line"></div>
				<h6 class="text-area">{{ review.contents }}</h6>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	props: {
		search: {
			type: Array,
			default: () => ({}),
		},
	},

	methods: {
		async goPost(endpoint) {
			this.$emit('onOpenPostModal', endpoint);
		},
		goUserPage(userId) {
			if (this.$route.path !== '/user/' + userId) {
				this.$router.push('/user/' + userId);
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
	grid-template-columns: 1fr 1fr;
	grid-template-rows: 1fr 1fr 1fr 1fr;

	overflow: auto;
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
	width: 50%;
}

.review-header {
	display: flex;
}

.my-picture {
	display: flex;
	justify-content: flex-end;
	align-items: center;
	margin-left: 15px;
	width: 30px;
	height: 30px;
	padding-top: px;
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
.text-area {
	overflow: hidden;
	margin: 0%;
	padding: 0%;
}

img {
	width: 95%;
	height: 100%;
}

h6 {
	margin-bottom: 15px;
}
p {
	font-size: 1.4rem;
	cursor: pointer;
}

p:hover {
	transform: translate(0, -3px);
	color: #a6a6a6;
}
</style>
