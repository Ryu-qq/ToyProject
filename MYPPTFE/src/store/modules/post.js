import { uploadPost, getPost, getFeedList } from '@/api/posts';

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
		setPost(state, post) {
			state.post = post;
		},
		setFeedList(state, feedList) {
			state.feedList = feedList;
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
