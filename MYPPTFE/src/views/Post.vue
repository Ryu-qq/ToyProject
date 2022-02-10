<template>
	<div>
		<div class="container">
			<post-add-form></post-add-form>
		</div>
	</div>
</template>

<script>
import PostAddForm from '../components/posts/PostAddForm.vue';

export default {
	components: {
		PostAddForm,
	},
};
</script>

<style scoped>
* {
	box-sizing: border-box;
}
.container {
	width: 100%;
	height: 100%;
	padding: 120px 0;
	display: flex;
	justify-content: center;
}
</style>
