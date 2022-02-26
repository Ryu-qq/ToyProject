import store from '@/store/index';

export function setInterceptors(instance) {
	// Add a request interceptor
	instance.interceptors.request.use(
		function (config) {
			// Do something before request is sent
			// console.log(config);
			const token = store.getters.token;
			if (token) {
				if (!config) config = { headers: {} };
				if (!config.headers) config.headers = {};
				config.headers.Authorization = `Bearer ${store.getters.token}`;
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
			// Any status codes that falls outside the range of 2xx cause this function to trigger
			// Do something with response error
			return Promise.reject(error);
		},
	);
	return instance;
}
