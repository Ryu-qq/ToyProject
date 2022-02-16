import req from './req-wrapper';

const BE_URI = {
	POST: `/posts`,
	POSTLIST: `/mypage`,
};

export default {
	uploadPost(params, success) {
		req.post(BE_URI.POST, params, success);
	},

	getPostList(params, success) {
		req.getList(BE_URI.POSTLIST, params, success);
	},
};
