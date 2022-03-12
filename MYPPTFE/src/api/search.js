import { search } from './index.js';
import store from '@/store/index';

function getSearch(payload) {
	const { keyword, category, path } = payload;
	if (store.getters.user && path == 'map') {
		return search.get('', {
			params: {
				keyword: keyword,
				category: category,
				userId: store.getters.user.userId,
				userName: store.getters.user.username,
			},
		});
	} else {
		return search.get('', {
			params: {
				keyword: keyword,
				category: category,
			},
		});
	}
}

export { getSearch };
