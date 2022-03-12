<template>
	<div class="container">
		<serch-form @doSearch="doSearch"></serch-form>
		<spinner :loading="loadingStatus"></spinner>
		<map-form class="map-container" :postitems="postitems"></map-form>
	</div>
</template>

<script>
import serchForm from '@/components/common/serchForm.vue';
import MapForm from '@/components/MapForm.vue';
import Spinner from '@/components/common/Spinner.vue';
import bus from '@/utils/bus.js';
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
	created() {
		bus.$on('start:spinner', this.startSpinner);
		bus.$on('end:spinner', this.endSpinner);
	},

	beforeDestroy() {
		bus.$off('start:spinner', this.startSpinner);
		bus.$off('end:spinner', this.endSpinner);
	},
	methods: {
		startSpinner() {
			this.loadingStatus = true;
		},
		endSpinner() {
			this.loadingStatus = false;
		},

		async doSearch(payload) {
			const data = await getSearch(payload);
			this.postitems = data.data.content;
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
</style>
