import store from '@/store/index';
import axios from 'axios';

let isTokenRefreshing = false;

export function setInterceptors(instance) {
	// Add a request interceptor
	instance.interceptors.request.use(
		function (config) {
			const token = store.getters.token;
			if (token) {
				if (!config) config = { headers: {} };
				if (!config.headers) config.headers = {};
				config.headers.Authorization = `Bearer ${store.getters.token}`;
			}
			if (!config.headers['Content-Type']) {
				config.headers['Content-Type'] = 'application/json; charset=utf-8';
			}
			return config;
		},
		function (error) {
			// Do something with request error
			return Promise.reject(error);
		},
	);

	// Add a response interceptor
	instance.interceptors.response.use(
		function (response) {
			// Any status code that lie within the range of 2xx cause this function to trigger
			// Do something with response data
			return response;
		},
		function (error) {
			console.log(error.request);
			if (error.response.status === 401) {
				if (!isTokenRefreshing) {
					isTokenRefreshing = true;
					const token = store.getters.token;

					if (token) {
						getRefreshToken(token);
					}
					isTokenRefreshing = false;
				}
				//const redirectUrl = error.request.responseURL;
			}
			return Promise.reject(error);
		},
	);
	return instance;
}

async function getRefreshToken(token) {
	const refreshToken = getCookie('refresh_token');
	const config = {
		headers: {
			Authorization: `Bearer ${token}`,
		},
	};
	const { data } = await axios.post(
		`http://localhost:8080/api/v1/auth/refresh`,
		{ refreshToken: refreshToken },
		config,
	);
	console.log('refreshtoekn===' + data.body.token);
	store.dispatch('fetchToken', data.body.token);
}

function getCookie(key) {
	var result = null;
	var cookie = document.cookie.split(';');
	cookie.some(function (item) {
		// 공백을 제거
		item = item.replace(' ', '');

		var dic = item.split('=');

		if (key === dic[0]) {
			result = dic[1];
			return true; // break;
		}
	});
	return result;
}
