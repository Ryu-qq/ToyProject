import { follow } from './index';

// 팔로우 기능
function doFollow(payload) {
	return follow.post('', payload);
}

function fetchFollow(payload) {
	const { type, userId } = payload;
	return follow.get('', {
		params: {
			type: type,
			userId: userId,
		},
	});
}

export { doFollow, fetchFollow };
