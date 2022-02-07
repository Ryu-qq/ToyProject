import postsApi from '../../api/posts.js';

export default {
	state: {
		posts: [],
	},

	getters: {
		posts: state => state.posts,
	},
	actions: {
		fetchPostList({ commit }) {
			postsApi.getPostList(res => {
				commit('setPost', res.posts);
			});
		},

		fetchPostUpload({ commit }, postData) {
			postsApi.uploadPost(postData);
			commit('setPost');
		},
	},
	mutations: {
		setPost(state, posts) {
			state.posts = posts;
		},
	},
};
