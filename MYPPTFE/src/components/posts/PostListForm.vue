<template>
	<div>
		<div v-if="postitems.length < 1" class="post-container">
			<p>게시물이 없습니다.</p>
		</div>

		<div class="post-preview-container">
			<div
				v-for="(file, index) in postitems"
				:key="index"
				class="post-preview-wrapper"
			>
				<i v-show="file.image.length > 1" class="fas fa-clone"></i>
				<div class="post-preview-item">
					<a @click="goPost(`${file.postSeq}`)">
						<img :src="require(`/assets/${file.image[0].filePath}`)" />
					</a>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	props: {
		postitems: {
			type: Array,
			default: () => [],
		},
	},
	data() {
		return {
			preview: '',
		};
	},
	methods: {
		async goPost(endpoint) {
			this.$emit('onOpenPostModal', endpoint);
		},
	},
};
</script>

<style scoped>
.post-preview-container {
	display: grid;
	padding: 0;
	margin: 0;
	grid-template-columns: 1fr 1fr 1fr;
	grid-template-rows: 1fr 1fr 1fr;
	grid-template-columns: repeat(auto-fill, minmax(30%, auto));

	row-gap: 28px;
	column-gap: 28px;
	width: 100%;
}

.post-container p {
	margin-top: 20px;
	font-size: 1.5rem;
	text-align: center;
}

img {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	cursor: pointer;
}
.post-preview-wrapper {
	position: relative;
}

i {
	position: absolute;
	z-index: 2;
	color: #fff;
	font-size: 24px;
	text-align: right;
	width: 99%;
	padding-right: 5px;
	padding-top: 5px;
}

.post-preview-item {
	position: relative;
	width: 100%;
	height: 0;
	padding-top: calc(1000 / 1000 * 100%);
}
</style>
