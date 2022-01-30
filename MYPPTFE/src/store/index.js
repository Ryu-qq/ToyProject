import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import account from './modules/account.js';

Vue.use(Vuex);

export default new Vuex.Store({
	plugins: [createPersistedState({ storage: window.seessionStorage })],
	modules: { account },
});
