import { getSearch } from '@/api/search';

export default {
	state: {
		searchList: {},
	},
	getters: {
		searchList: state => state.searchList,
	},
	actions: {
		async fetchSearch({ commit }, param) {
			const { keyword, category } = param;
			if (category === '카테고리') {
				const { data } = getSearch({ keyword: keyword, category: '' });
				commit('setSearch', data);
			} else {
				const { data } = getSearch(param);
				commit('setSearch', data);
			}
		},
	},
	setSearch: {
		setSearch(state, searchList) {
			state.searchList = searchList;
		},
	},
};
