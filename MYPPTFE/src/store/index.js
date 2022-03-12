import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import user from './modules/user.js';
import post from './modules/post.js';

Vue.use(Vuex);

export default new Vuex.Store({
	plugins: [createPersistedState({ storage: window.seessionStorage })],
	modules: { user, post },
});
