<template>
	<div class="container">
		<serch-form></serch-form>
		<search-list></search-list>
	</div>
</template>

<script>
import serchForm from '@/components/common/serchForm.vue';
import searchList from '@/components/searchListForm.vue';
export default {
	components: { serchForm, searchList },
};
</script>

<style scoped>
* {
	box-sizing: border-box;
}

.container {
	display: flex;
	flex-direction: column;
	padding: 70px 0;
}
</style>
