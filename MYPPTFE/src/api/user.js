import { user } from './index';

// 로그인 API
function loginUser() {
	return user.get('/login');
}

//마이페이지 유저정보 가져오기
function getUserInfo(payload) {
	return user.get(`${payload}`);
}

export { loginUser, getUserInfo };
