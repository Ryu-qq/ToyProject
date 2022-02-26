import { follow, userinfo } from './index';

// 팔로우 기능
function getFollow(params) {
	const { toUserId, fromUserId } = params;
	return follow.post('', { fromUser: fromUserId, toUser: toUserId });
}

function getUserInfo(params) {
	return userinfo.post('', params);
}

export { getFollow, getUserInfo };
