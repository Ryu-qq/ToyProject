import { getSearch } from '@/api/search';
import bus from '@/utils/bus.js';

export default {
	state: {
		searchList: {},
		mapList: {},
	},
	getters: {
		searchList: state => state.searchList,
		mapList: state => state.mapList,
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
		async fetchMapList({ commit }, param) {
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

				commit('setMapList', data);
			} else {
				const { data } = await getSearch(param);
				bus.$emit('end:spinner');
				commit('setMapList', data);
			}
		},
	},
	mutations: {
		setSearch(state, searchList) {
			state.searchList = searchList;
		},
		setMapList(state, mapList) {
			state.mapList = mapList;
		},
	},
};
