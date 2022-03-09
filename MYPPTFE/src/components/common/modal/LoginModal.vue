<template>
	<div>
		<div>
			<li v-for="social in socials" :key="social.socialType">
				<a class="social_btn" :href="socialLoginUrl(social.socialType)">
					<img
						class="social_login"
						:src="social.src"
						:style="{ width: social.width, height: social.height }"
					/>
					{{ social.comment }}
				</a>
			</li>
		</div>
	</div>
</template>

<script>
import $ from '@/utils/utils';
import { mapActions, mapMutations } from 'vuex';

export default {
	data() {
		return {
			socials: [
				{
					socialType: 'google',
					src: require('@/assets/btn_google.png'),
					width: '39px',
					height: '39px',
					comment: '구글 로그인',
				},
			],
		};
	},
	methods: {
		...mapActions(['fetchUser']),
		...mapMutations(['setToken']),

		socialLoginUrl(socialType) {
			return $.getSocialLoginUrl(socialType);
		},
	},
};
</script>

<style scoped>
li {
	padding: 2% 0;
	list-style: none;
	text-align: center;
}

img {
	margin-right: 10px;
}

.social_btn {
	margin-bottom: 15px;
	font-weight: 400;
	font-size: 16px;
	line-height: 45px;
	cursor: pointer;
	color: black;
	text-decoration: none;
}

.social_btn:hover {
	color: #a6a6a6;
}

.social_login {
	height: 32px;
}

.socal_login_container {
	text-align: center;
}
</style>
