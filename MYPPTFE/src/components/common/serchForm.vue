<template>
	<div class="search">
		<input
			v-model="form.keyword"
			class="search-input"
			type="text"
			placeholder="검색어를 입력하세요"
		/>

		<div class="search-input-options">
			<select v-model="form.category" class="search-select">
				<option selected>카테고리</option>
				<option v-for="(category, index) in categories" :key="index">
					{{ category }}
				</option>
			</select>

			<button @click.self.prevent="doSearch">검색</button>
		</div>
	</div>
</template>

<script>
export default {
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
	methods: {
		doSearch() {
			this.$store.dispatch('fetchSearch', this.form);
		},
	},
};
</script>

<style scoped>
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
</style>
