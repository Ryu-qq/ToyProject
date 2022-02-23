import req from './req-wrapper';

const BE_URI = {
	USER: '/users',
	FOLLOW: '/follow',
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
};
