import accountApi from '../../api/account.js';

export default {
	state: {
		user: {},
		token: {},
		follow: {},
	},
	getters: {
		user: state => state.user,
		token: state => state.token,
		follow: state => state.follow,
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
	},
};
