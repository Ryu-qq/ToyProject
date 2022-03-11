import { follow, userinfo } from './index';

// 팔로우 기능
function doFollow(payload) {
	return follow.post('', payload);
}

function getUserInfo(payload) {
	return userinfo.post('', payload);
}

export { doFollow, getUserInfo };
