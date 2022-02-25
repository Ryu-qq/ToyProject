import req from './req-wrapper';

const BE_URI = {
	USER: '/users',
	FOLLOW: '/follow',
	USERINFO: '/userInfo',
};

export default {
	getUser(success) {
		req.get(BE_URI.USER, success);
	},
	getFollow(params, success) {
		const { toUserId, fromUserId } = params;

		req.postFollow(
			BE_URI.FOLLOW,
			{ fromUser: fromUserId, toUser: toUserId },
			success,
		);
	},

	getUserInfo(params, success) {
		req.post(BE_URI.USERINFO, params, success);
	},
};
