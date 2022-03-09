import { getSearch } from '@/api/search';
import bus from '@/utils/bus.js';

export default {
	state: {
		searchList: {},
	},
	getters: {
		searchList: state => state.searchList,
	},
	actions: {
		async fetchSearch({ commit }, param) {
			const { name, userId, keyword, category } = param;
			bus.$emit('start:spinner');
			if (category === '카테고리') {
				const { data } = await getSearch({
					userId: userId,
					name: name,
					keyword: keyword,
					category: '',
				});
				bus.$emit('end:spinner');

				commit('setSearch', data);
			} else {
				const { data } = await getSearch(param);
				bus.$emit('end:spinner');

				commit('setSearch', data);
			}
		},
		initSearchList({ commit }) {
			commit('initData');
		},
	},
	mutations: {
		setSearch(state, searchList) {
			state.searchList = searchList;
		},
		initData(state) {
			state.searchList = '';
		},
	},
};
