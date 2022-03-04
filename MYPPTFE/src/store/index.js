import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import account from './modules/account.js';
import post from './modules/post.js';
import search from './modules/search.js';

Vue.use(Vuex);

export default new Vuex.Store({
	plugins: [createPersistedState({ storage: window.seessionStorage })],
	modules: { account, post, search },
});
