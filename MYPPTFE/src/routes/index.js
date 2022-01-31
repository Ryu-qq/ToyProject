import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

export const router = new VueRouter({
	mode: 'history',
	routes: [
		{
			path: '/',
			redirect: '/map',
			component: () => import('@/views/Index'),
			children: [
				{
					path: '/map',
					name: 'Map',
					component: () => import('@/views/Map'),
				},
				{
					path: '/lookaround',
					name: 'LookAround',
					component: () => import('@/views/LookAround'),
				},
				{
					path: '/mypage/:userId',
					name: 'MyPage',
					component: () => import('@/views/MyPage'),
				},
				{
					path: '/post',
					name: 'Post',
					component: () => import('@/views/Post'),
				},
			],
		},
		{
			path: '/oauth/redirect',
			name: 'RedirectView',
			component: () => import('@/views/oauth/RedirectView'),
		},
		{
			path: '*',
			component: () => import('@/views/NotFoundPage'),
		},
	],
});
