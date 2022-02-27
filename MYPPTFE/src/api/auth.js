import { auth } from './index';

// 로그인 API
function loginUser() {
	return auth.get();
}

export { loginUser };
