<template>
	<div class="container">
		<div class="search-header">
			<p>사람들이 남긴 후기를 찾아보세요.</p>
		</div>
		<search-form @doSearch="doSearch"></search-form>
		<div v-show="searchCnt < 1 && isSearch" class="search-msg">
			<p>검색 결과가 없습니다. 다시 입력해 주세요.</p>
		</div>
		<search-list-form
			:search="search"
			@onOpenPostModal="openPostModal"
		></search-list-form>
		<spinner :loading="loadingStatus" class="spinner"></spinner>

		<modal-view
			v-if="isModalOpen"
			class="post-modal"
			@onCloseModal="isModalOpen = false"
		>
			<div slot="body">
				<post></post>
			</div>
		</modal-view>
	</div>
</template>

<script>
import Spinner from '@/components/common/Spinner.vue';
import Post from '@/views/Post.vue';
import ModalView from '@/components/common/modal/PostModal.vue';
import { getSearch } from '@/api/search';
import SearchListForm from '@/components/SearchListForm.vue';
import SearchForm from '../components/common/SearchForm.vue';

export default {
	components: { Spinner, Post, ModalView, SearchListForm, SearchForm },
	data() {
		return {
			isSearch: false,
			loadingStatus: false,
			isModalOpen: false,
			search: [],
			pageable: {},
		};
	},

	computed: {
		searchCnt() {
			return this.search ? this.search.length : 0;
		},
	},

	methods: {
		async doSearch(payload) {
			this.isSearch = false;
			this.loadingStatus = true;
			const data = await getSearch(payload);
			this.search = data.data.content;
			this.isSearch = true;
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

.spinner {
	margin-top: -10%;
}
.search-header {
	display: flex;
	flex-direction: column;
}
.search-result {
	display: flex;
}

.search-msg {
	display: flex;
	text-align: center;
	margin-top: 10%;
}

.search-msg p {
	width: 100%;
	text-align: center;
}

h3 {
	color: #858282;
}
</style>
