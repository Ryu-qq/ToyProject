<template>
	<div class="container">
		<serch-form></serch-form>

		<map-form class="map-container"></map-form>
		<spinner :loading="loadingStatus"></spinner>
	</div>
</template>

<script>
import serchForm from '@/components/common/serchForm.vue';
import MapForm from '@/components/MapForm.vue';
import Spinner from '@/components/common/Spinner.vue';
import bus from '@/utils/bus.js';

export default {
	components: {
		MapForm,
		Spinner,
		serchForm,
	},
	data() {
		return {
			loadingStatus: false,
			customMap: {},
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
