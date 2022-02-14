import postsApi from '../../api/posts.js';

export default {
	state: {
		posts: [],
		imageFiles: [],
	},

	getters: {
		posts: state => state.posts,
		imageFiles: state => state.imageFiles,
	},
	actions: {
		fetchPostList({ commit }, payload) {
			postsApi.getPostList(payload, res => {
				commit('setPost', res.posts);
			});
		},

		fetchPostUpload(postData) {
			postsApi.uploadPost(postData);
		},

		fetchImageUpload({ commit }, data) {
			commit('setImageFiles', data);
		},

		fetchImagedelete({ commit }, data) {
			commit('deleteImageFiles', data);
		},
	},
	mutations: {
		setPost(state, posts) {
			state.posts = posts;
		},
		setImageFiles(state, imageFiles) {
			state.imageFiles = imageFiles;
		},
		deleteImageFiles(state, imageFiles) {
			state.imageFiles = state.imageFiles.filter(
				data => data.number !== Number(imageFiles),
			);
		},
	},
};
