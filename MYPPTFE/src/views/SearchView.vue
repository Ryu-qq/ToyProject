<template>
	<div class="container">
		<serch-form @doSearch="doSearch"></serch-form>
		<search-list
			:search="search"
			@onOpenPostModal="openPostModal"
		></search-list>
		<spinner :loading="loadingStatus"></spinner>

		<modal-view
			v-if="isModalOpen"
			class="post-modal"
			@onCloseModal="isModalOpen = false"
		>
			<div slot="body">
				<post-view></post-view>
			</div>
		</modal-view>
	</div>
</template>

<script>
import serchForm from '@/components/common/serchForm.vue';
import searchList from '@/components/searchListForm.vue';
import Spinner from '@/components/common/Spinner.vue';
import bus from '@/utils/bus.js';
import PostView from '@/views/PostView.vue';
import ModalView from '@/components/common/modal/PostModal.vue';
import { getSearch } from '@/api/search';

export default {
	components: { serchForm, searchList, Spinner, PostView, ModalView },
	data() {
		return {
			loadingStatus: false,
			isModalOpen: false,
			search: [],
			pageable: {},
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
		async doSearch(payload) {
			const data = await getSearch(payload);
			this.search = data.data.content;
		},

		startSpinner() {
			this.loadingStatus = true;
		},
		endSpinner() {
			this.loadingStatus = false;
		},
		async openPostModal(endpoint) {
			this.endpoint = endpoint;
			await this.$store.dispatch('fetchPost', endpoint);
			this.isModalOpen = true;
		},
	},
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
