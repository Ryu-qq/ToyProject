import { uploadPost, getPost } from '@/api/posts';

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
		async fetchPost({ commit }, params) {
			const { data } = await getPost(params);
			commit('setPost', data.body.post);
		},

		async fetchPostList({ commit }, params) {
			commit('setPostList', params);
		},

		async fetchPostUpload(postData) {
			await uploadPost(postData);
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
