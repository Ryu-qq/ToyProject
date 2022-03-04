import axios from 'axios';
import { setInterceptors } from './common/interceptors';

const BACKEND_DOMAINPORT = process.env.VUE_APP_API_URL;
const URI_PREPENDER = '/api/v1/';

// 액시오스 초기화 함수
function createInstanceWithAuth(url) {
	const instance = axios.create({
		baseURL: `${BACKEND_DOMAINPORT}${URI_PREPENDER}${url}`,
	});
	return setInterceptors(instance);
}

function createInstanceWithMultiPart(url) {
	const instance = axios.create({
		baseURL: `${BACKEND_DOMAINPORT}${URI_PREPENDER}${url}`,
		headers: {
			'Content-Type': 'multipart/form-data',
		},
	});
	return setInterceptors(instance);
}
export const search = createInstanceWithAuth('search');
export const auth = createInstanceWithAuth('users');
export const posts = createInstanceWithAuth('posts');
export const multipart = createInstanceWithMultiPart('posts');
export const userinfo = createInstanceWithAuth('userinfo');
export const follow = createInstanceWithAuth('follow');
