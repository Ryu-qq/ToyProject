<template>
	<div class="body">
		<div class="search">
			<input
				class="search-input"
				type="text"
				placeholder="검색어를 입력하세요"
			/>
			<!-- @keyup.enter="search"/> -->

			<div class="search-input-options">
				<select v-model="form.category" class="search-select">
					<option selected>카테고리</option>
					<option v-for="(category, index) in categories" :key="index">
						{{ category }}
					</option>
				</select>

				<button>검색</button>
			</div>
		</div>

		<spinner :loading="loadingStatus"></spinner>
		<map-form class="map-container"></map-form>
	</div>
</template>

<script>
import MapForm from '@/components/MapForm.vue';
import Spinner from '@/components/common/Spinner.vue';
import bus from '@/utils/bus.js';

export default {
	components: {
		MapForm,
		Spinner,
	},
	data() {
		return {
			loadingStatus: true,
			customMap: {},
			categories: ['한식', '중식', '일식', '양식', '분식', '포차', '기타'],
			form: {
				keyword: '',
				category: '카테고리',
			},
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
.body {
	display: flex;
	flex-direction: column;
	align-content: space-between;
	width: 100%;
	height: 850px;
	padding: 80px 6%;
}

.search {
	display: flex;
	width: 100%;
	margin-bottom: 20px;
}

.search-input {
	margin-right: 12px;
	width: 100%;
	border: 3px solid #f5f5f5;
	border-radius: 10px;
}

.search-input:focus {
	outline: none;
	border-color: #a6a6a6;
}

.search-input-options {
	display: flex;
	justify-content: space-between;
}

.search button {
	width: 80px;
	padding: 7px 18px;
	background-color: #fff;
	border: 3px solid #f5f5f5;
	border-radius: 10px;
	cursor: pointer;
}

.search button:hover {
	background-color: #a6a6a6;
}

.search-select {
	border: 3px solid #f5f5f5;
	border-radius: 10px;
	margin-right: 10px;
	padding: 0 6px;
}

.search-select:hover {
	border: 3px solid #a6a6a6;
}

.search-select:focus {
	border: 3px solid #a6a6a6;
	outline: none;
}
.map-container {
	height: 100%;
}
</style>
