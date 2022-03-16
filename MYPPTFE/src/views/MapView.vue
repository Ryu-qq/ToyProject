<template>
	<div class="container">
		<serch-form @doSearch="doSearch"></serch-form>
		<spinner :loading="loadingStatus" class="spinner"></spinner>
		<div class="search-result">
			<p>검색 결과:</p>
			<p>{{ searchCnt }} 개</p>
		</div>
		<map-form class="map-container" :postitems="postItems"></map-form>
	</div>
</template>

<script>
import serchForm from '@/components/common/serchForm.vue';
import MapForm from '@/components/MapForm.vue';
import Spinner from '@/components/common/Spinner.vue';
import { getSearch } from '@/api/search';

export default {
	components: {
		MapForm,
		serchForm,
		Spinner,
	},
	data() {
		return {
			loadingStatus: false,
			postItems: [],
		};
	},
	computed: {
		searchCnt() {
			return this.postItems ? this.postItems.length : 0;
		},
	},

	methods: {
		async doSearch(payload) {
			this.loadingStatus = true;
			const data = await getSearch(payload);
			this.postItems = data.data.content;
			this.loadingStatus = false;
		},
	},
};
</script>

<style scoped>
.container {
	display: flex;
	flex-direction: column;
	align-content: space-between;
	width: 100%;
	height: 850px;
	padding: 70px 0;
}

.map-container {
	height: 100%;
}

.spinner {
	margin-top: -10%;
}

.search-result {
	display: flex;
}

p {
	margin-right: 5px;
	font: 1.5rem;
}
</style>
