import req from './req-wrapper';

const BE_URI = {
	USER: '/users',
};

export default {
	getUser(success) {
		req.get(BE_URI.USER, success);
	},
};
