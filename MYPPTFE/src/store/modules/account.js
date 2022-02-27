import { loginUser } from '@/api/auth';
import { getFollow, getUserInfo } from '@/api/account';

export default {
	state: {
		user: {},
		token: {},
		follow: {},
		userInfo: {},
	},
	getters: {
		user: state => state.user,
		token: state => state.token,
		follow: state => state.follow,
		userInfo: state => state.userInfo,
	},
	actions: {
		async fetchToken({ commit }, params) {
			commit('setToken', params);
		},
		async fetchUser({ commit }) {
			const { data } = await loginUser();
			commit('setUser', data.body.user);
		},
		async fetchFollow({ commit }, params) {
			const { data } = await getFollow(params);
			commit('setFollow', data.body.follow);
		},
		async fetchUserInfo({ commit }, params) {
			const { data } = await getUserInfo(params);
			commit('setUserInfo', data.body.userInfo);
		},
	},
	mutations: {
		setToken(state, token) {
			state.token = token;
		},

		setUser(state, user) {
			state.user = user;
		},

		setFollow(state, follow) {
			state.follow = follow;
		},
		setUserInfo(state, userInfo) {
			state.userInfo = userInfo;
		},
	},
};
