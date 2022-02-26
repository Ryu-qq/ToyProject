import axios from 'axios';
import { setInterceptors } from './common/interceptors';

const BACKEND_DOMAINPORT = process.env.VUE_APP_API_URL;
const URI_PREPENDER = '/api/v1/';

function createInstance() {
	return axios.create({
		baseURL: process.env.VUE_APP_API_URL,
	});
}

// 액시오스 초기화 함수
function createInstanceWithAuth(url) {
	const instance = axios.create({
		baseURL: `${BACKEND_DOMAINPORT}${URI_PREPENDER}${url}`,
	});
	return setInterceptors(instance);
}

export const instance = createInstance();
export const user = createInstanceWithAuth('users');
export const posts = createInstanceWithAuth('posts');
export const userinfo = createInstanceWithAuth('userinfo');
export const follow = createInstanceWithAuth('follow');
