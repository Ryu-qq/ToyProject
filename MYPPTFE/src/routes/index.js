import Vue from 'vue';
import VueRouter from 'vue-router';

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
			component: () => import('@/views/MapView'),
		},
		{
			path: '/search',
			name: 'search',
			component: () => import('@/views/SearchView'),
		},
		{
			path: '/userinfo/:userId',
			name: 'UserPage',
			component: () => import('@/views/UserPage'),
		},
		{
			path: '/post',
			name: 'post',
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
