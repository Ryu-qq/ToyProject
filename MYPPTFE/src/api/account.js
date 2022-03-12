import { follow } from './index';

// 팔로우 기능
function doFollow(payload) {
	return follow.post('', payload);
}

export { doFollow };
