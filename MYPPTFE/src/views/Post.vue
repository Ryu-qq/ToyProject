<template>
	<div>
		<post-form @onClosePost="onCloseModal">></post-form>
	</div>
</template>

<script>
import PostForm from '@/components/posts/PostForm.vue';
export default {
	components: { PostForm },
	methods: {
		onCloseModal() {
			this.$emit('onCloseModal');
		},
	},
};
</script>

<style scoped>
* {
	box-sizing: border-box;
	display: flex;
	width: 100%;
	height: 650px;
}
</style>
