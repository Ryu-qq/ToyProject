import { loginUser } from '@/api/auth';

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
		//토큰 값  받아오기
		async fetchToken({ commit }, params) {
			commit('setToken', params);
		},
		//로그인한 유저 정보 받아오기
		async fetchUser({ commit }) {
			const { data } = await loginUser();
			commit('setUser', data.body.user);
		},
		//팔로우 한 사람의 정보 및 포스트 받아오기
		// async fetchFollow({ commit }, params) {
		// 	const { data } = await getFollow(params);
		// 	commit('setFollow', data.body.follow);
		// },
		//유저페이지 정보 가져오기
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
