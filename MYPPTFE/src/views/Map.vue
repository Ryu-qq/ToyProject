<template>
	<div class="container">
		<search-form @doSearch="doSearch"></search-form>
		<spinner :loading="loadingStatus" class="spinner"></spinner>
		<div class="search-result">
			<p>검색 결과:</p>
			<p>{{ searchCnt }} 개</p>
		</div>
		<map-form class="map-container" :postitems="postItems"></map-form>
	</div>
</template>

<script>
import MapForm from '@/components/MapForm.vue';
import Spinner from '@/components/common/Spinner.vue';
import { getSearch } from '@/api/search';
import SearchForm from '@/components/common/SearchForm.vue';

export default {
	components: {
		MapForm,
		Spinner,
		SearchForm,
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
