import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

export const router = new VueRouter({
	mode: 'history',
	routes: [
		{
			path: '/',
			redirect: '/storeview',
		},
		{
			path: '/storeview',
			name: 'StoreView',
			component: () => import('@/views/StoreView'),
		},
		{
			path: '/lookaround',
			name: 'LookAround',
			component: () => import('@/views/LookAroundView'),
		},
		{
			path: '/mypage/:userId',
			name: 'MyPage',
			component: () => import('@/views/MyPage'),
		},
		{
			path: '/postAdd',
			name: 'PostAdd',
			component: () => import('@/views/PostAddView'),
		},
		{
			path: '/post/:postSeq',
			name: 'PostView',
			component: () => import('@/views/PostView'),
		},
		{
			path: '/oauth/redirect',
			name: 'RedirectView',
			component: () => import('@/views/oauth/RedirectView'),
		},
		{
			path: '*',
			component: () => import('@/views/NotFoundPageView'),
		},
	],
});
