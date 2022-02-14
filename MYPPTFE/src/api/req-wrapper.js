import axios from 'axios';
import store from '../store/index.js';
import handler from './res-handler';

const BACKEND_DOMAINPORT = process.env.VUE_APP_API_URL;
const URI_PREPENDER = '/api/v1';
const wrap = url => `${BACKEND_DOMAINPORT}${URI_PREPENDER}${url}`;
const wrapUserId = url =>
	`${BACKEND_DOMAINPORT}${URI_PREPENDER}${url}?userId=${store.getters.user.userId}`;
const appendAuth = config => {
	const token = store.getters.token;
	if (token) {
		if (!config) config = { headers: {} };
		if (!config.headers) config.headers = {};
		config.headers.Authorization = `Bearer ${store.getters.token}`;
	}
	return config;
};

export default {
	get(url, success, fail = err => err.response.data.message, config) {
		axios
			.get(wrap(url), appendAuth(config))
			.then(handler.handle(success))
			.catch(fail);
	},
	post(url, body, success, fail = err => err.response.data.message, config) {
		axios
			.post(wrap(url), body, appendAuth(config))
			.then(handler.handle(success))
			.catch(fail);
	},

	getList(url, success, fail = err => err.response.data.message, config) {
		axios
			.get(wrapUserId(url), appendAuth(config))
			.then(handler.handle(success))
			.catch(fail);
	},
};
