import axios from 'axios';
import store from '../store/index.js';
import handler from './res-handler';

const BACKEND_DOMAINPORT = process.env.VUE_APP_API_URL;
const URI_PREPENDER = '/api/v1';
const wrap = url => `${BACKEND_DOMAINPORT}${URI_PREPENDER}${url}`;
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
	get(url, success, config) {
		axios
			.get(wrap(url), appendAuth(config))
			.then(handler.handle(success))
			.catch(err => {
				if (err.response.data.header.code == 401) {
					console.log('111');
					axios.get('http://localhost:8080/api/v1/refresh', appendAuth);
				}
			});
	},
	post(url, body, success, fail = err => err.response.data.message, config) {
		axios
			.post(wrap(url), body, appendAuth(config))
			.then(handler.handle(success))
			.catch(fail);
	},

	postFollow(
		url,
		body,
		success,
		fail = err => err.response.data.message,
		config,
	) {
		axios
			.post(wrap(url), body, appendAuth(config))
			.then(handler.handle(success))
			.catch(fail);
	},

	getList(url, success, fail = err => err.response.data.message, config) {
		axios
			.get(wrap(url), appendAuth(config))
			.then(handler.postHandler(success))
			.catch(fail);
	},

	getPost(url, success, fail = err => err.response.data.message, config) {
		axios
			.get(wrap(url), appendAuth(config))
			.then(handler.handle(success))
			.catch(fail);
	},
};
