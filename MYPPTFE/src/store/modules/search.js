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
			const { data } = getSearch(param);
			commit('setSearch', data);
		},
	},
	setSearch: {
		setSearch(state, searchList) {
			state.searchList = searchList;
		},
	},
};
