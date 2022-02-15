import req from './req-wrapper';

const BE_URI = {
	POST: `/posts`,
	POSTLIST: `/mypage`,
};

export default {
	uploadPost(body, success) {
		console.log(body);
		req.post(BE_URI.POST, body, success);
	},

	getPostList(params, success) {
		req.getList(BE_URI.POSTLIST, params, success);
	},
};
