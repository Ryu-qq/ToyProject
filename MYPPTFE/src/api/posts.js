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
	getPost(params, success) {
		const { endpoint } = params;

		req.getPost(BE_URI.POST + '/' + endpoint, success);
	},
};
