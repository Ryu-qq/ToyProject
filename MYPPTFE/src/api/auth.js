import { user } from './index';

// 로그인 API
function loginUser() {
	return user.get();
}

export { loginUser };
