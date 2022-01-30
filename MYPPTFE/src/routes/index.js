import Vue from 'vue';
import VueRouter from 'vue-router';

import LookAround from '../views/LookAroundView';
import MyPage from '../views/MyPageView';
import Store from '../views/StoreView';
import RedirectView from '../views/oauth/RedirectView';
import IndexPage from '../views/Index.vue';
import Post from '../views/Post.vue';

Vue.use(VueRouter);

export const router = new VueRouter({
	mode: 'history',
	routes: [
		{
			path: '/',
			redirect: '/store',
			component: IndexPage,
			children: [
				{
					path: '/store',
					name: 'Store',
					component: Store,
				},
				{
					path: '/lookaround',
					name: 'LookAround',
					component: LookAround,
				},
				{
					path: '/mypage/:userId',
					name: 'MyPage',
					component: MyPage,
				},
				{
					path: '/post',
					name: 'Post',
					component: Post,
				},
			],
		},
		{
			path: '/oauth/redirect',
			name: 'RedirectView',
			component: RedirectView,
		},
	],
});
