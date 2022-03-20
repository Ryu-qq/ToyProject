import Vue from 'vue';
import VueRouter from 'vue-router';
import store from '@/store/index';

Vue.use(VueRouter);

export const router = new VueRouter({
	mode: 'history',
	routes: [
		{
			path: '/',
			redirect: '/map',
		},
		{
			path: '/map',
			name: 'map',
			component: () => import('@/views/Map'),
		},
		{
			path: '/search',
			name: 'search',
			component: () => import('@/views/Search'),
		},
		{
			path: '/user/:userId',
			name: 'userPage',
			component: () => import('@/views/UserPage'),
		},
		{
			path: '/post',
			name: 'postAdd',
			component: () => import('@/views/PostAdd'),
			meta: { auth: true },
		},
		{
			path: '/post/:postSeq',
			name: 'post',
			component: () => import('@/views/Post'),
		},
		{
			path: '/oauth/redirect',
			name: 'redirect',
			component: () => import('@/views/oauth/Redirect'),
		},
		{
			path: '*',
			component: () => import('@/views/NotFound'),
		},
	],
});

router.beforeEach((to, from, next) => {
	if (to.meta.auth && !store.getters.user) {
		console.log('인증이 필요합니다');
		next('/map');
		return;
	}
	next();
});
