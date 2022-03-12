import { loginUser } from '@/api/auth';

export default {
	state: {
		user: {},
		token: {},
	},
	getters: {
		user: state => state.user,
		token: state => state.token,
		follow: state => state.follow,
	},
	actions: {
		//토큰 값  받아오기
		async fetchToken({ commit }, params) {
			commit('setToken', params);
		},
		//로그인한 유저 정보 받아오기
		async fetchUser({ commit }) {
			const { data } = await loginUser();
			commit('setUser', data.body.user);
		},
	},
	mutations: {
		setToken(state, token) {
			state.token = token;
		},

		setUser(state, user) {
			state.user = user;
		},
	},
};
