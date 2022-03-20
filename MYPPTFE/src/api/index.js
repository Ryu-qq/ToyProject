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
export const user = createInstanceWithAuth('user');
export const posts = createInstanceWithAuth('post');
export const multipart = createInstanceWithMultiPart('post');
export const follow = createInstanceWithAuth('follow');
export const map = createInstanceWithAuth('map');
