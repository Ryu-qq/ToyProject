import postsApi from '../../api/posts.js';

export default {
	state: {
		postList: [],
		imageFiles: [],
		userInfo: {},
		post: {},
	},

	getters: {
		post: state => state.post,
		postList: state => state.postList,
		imageFiles: state => state.imageFiles,
		userInfo: state => state.userInfo,
	},
	actions: {
		fetchPost({ commit }, data) {
			postsApi.getPost(data, res => {
				commit('setPost', res.post);
			});
		},

		fetchPostList({ commit }, data) {
			postsApi.getPostList(data, res => {
				commit('setPostList', res.userInfo.posts);
				commit('setUserInfo', res.userInfo);
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
		setUserInfo(state, userInfo) {
			state.userInfo = userInfo;
		},
		setPost(state, post) {
			state.post = post;
		},
		setPostList(state, postList) {
			state.postList = postList;
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
