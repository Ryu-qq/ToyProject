<template>
	<div>
		<post-form></post-form>
	</div>
</template>

<script>
import PostForm from '@/components/posts/PostForm.vue';
export default {
	components: { PostForm },
};
</script>

<style scoped>
* {
	box-sizing: border-box;
	padding: 80px 6%;
	display: flex;
	width: 100%;
	height: 850px;
}
</style>
