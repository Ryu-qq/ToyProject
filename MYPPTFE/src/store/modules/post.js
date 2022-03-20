import { getPost, editPost } from '@/api/posts';

export default {
	state: {
		post: {},
	},

	getters: {
		post: state => state.post,
	},
	actions: {
		async fetchPost({ commit }, params) {
			const { data } = await getPost(params);
			commit('setPost', data.body.post);
		},

		async fetchEditPost({ commit }, params) {
			const { data } = await editPost(params);
			if (data.body.post) {
				const payload = params.get('contents');
				commit('setPostContents', payload);
			}
		},
	},
	mutations: {
		setPost(state, post) {
			state.post = post;
		},

		setPostContents(state, payload) {
			state.post.contents = payload;
		},
	},
};
