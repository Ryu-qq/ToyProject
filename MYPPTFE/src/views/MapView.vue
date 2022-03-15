<template>
	<div class="container">
		<serch-form @doSearch="doSearch"></serch-form>
		<spinner :loading="loadingStatus" class="spinner"></spinner>
		<map-form class="map-container" :postitems="postitems"></map-form>
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
			postitems: [],
		};
	},
	methods: {
		async doSearch(payload) {
			this.loadingStatus = true;
			const data = await getSearch(payload);
			this.postitems = data.data.content;
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
</style>
