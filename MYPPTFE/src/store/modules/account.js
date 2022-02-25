import accountApi from '../../api/account.js';

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
		fetchUser({ commit }) {
			accountApi.getUser(res => {
				commit('setUser', res.user);
			});
		},
		fetchFollow({ commit }, data) {
			accountApi.getFollow(data, res => {
				commit('setFollow', res.follow);
			});
		},
		fetchUserInfo({ commit }, data) {
			accountApi.getUserInfo(data, res => {
				commit('setUserInfo', res.userInfo);
			});
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
