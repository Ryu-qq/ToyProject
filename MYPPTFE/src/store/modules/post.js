import { getPost, editPost } from '@/api/posts';

export default {
	state: {
		imageFiles: [],
		post: {},
	},

	getters: {
		post: state => state.post,
		imageFiles: state => state.imageFiles,
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

		fetchImageUpload({ commit }, data) {
			commit('setImageFiles', data);
		},
	},
	mutations: {
		setPost(state, post) {
			state.post = post;
		},
		setImageFiles(state, imageFiles) {
			state.imageFiles = imageFiles;
		},
		setPostContents(state, payload) {
			state.post.contents = payload;
		},
	},
};
