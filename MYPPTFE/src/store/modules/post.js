import { getPost, getFeedList, editPost } from '@/api/posts';

export default {
	state: {
		feedList: [],
		imageFiles: [],
		post: {},
	},

	getters: {
		post: state => state.post,
		feedList: state => state.feedList,
		imageFiles: state => state.imageFiles,
	},
	actions: {
		async fetchPost({ commit }, params) {
			const { data } = await getPost(params);
			commit('setPost', data.body.post);
		},

		async fetchFeedList({ commit }, params) {
			const { data } = await getFeedList(params);
			commit('setFeedList', data.feedList);
		},

		async fetchEditPost({ commit }, params) {
			const { data } = await editPost(params);
			commit('setPost', data.body.post);
		},

		fetchImageUpload({ commit }, data) {
			commit('setImageFiles', data);
		},

		fetchImagedelete({ commit }, data) {
			commit('deleteImageFiles', data);
		},
	},
	mutations: {
		setPost(state, post) {
			state.post = post;
		},
		setFeedList(state, feedList) {
			state.feedList = feedList;
		},
		setImageFiles(state, imageFiles) {
			state.imageFiles = imageFiles;
		},
	},
};
