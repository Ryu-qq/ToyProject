import { search } from './index.js';

function getSearch(param) {
	return search.post('', param);
}

export { getSearch };
