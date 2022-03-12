import { users } from './index';

// 로그인 API
function loginUser() {
	return users.get('/login');
}

//마이페이지 유저정보 가져오기
function getUserInfo(payload) {
	return users.post('', payload);
}

export { loginUser, getUserInfo };
