<template>
	<div>
		<my-info-form></my-info-form>

		<div class="tappage">
			<a href="">
				<span
					><i class="fas fa-th fa-1x" style="color: #a6a6a6"> 게시물</i></span
				>
			</a>
			<a href=""
				><span
					><i class="fas fa-map fa-1x" style="color: #a6a6a6"> 지도</i></span
				>
			</a>
		</div>

		<div class="mypage-postlist">
			<div clsss="mypage-postrow">
				<div class="post-container"></div>
			</div>
			<div></div>
		</div>
	</div>
</template>

<script>
import { mapMutations, mapGetters } from 'vuex';
import MyInfoForm from '../components/MyInfoForm.vue';

export default {
	components: { MyInfoForm },

	computed: {
		...mapGetters(['token', 'user']),

		isLoggedIn() {
			return this.token != null;
		},

		username() {
			if (!this.user) return '';
			return this.user.username;
		},

		profileImageUrl() {
			if (!this.user) return '';
			return this.user.profileImageUrl;
		},
		toekn() {
			if (!this.token) return '';
			return this.token;
		},
	},

	methods: {
		...mapMutations(['setToken', 'setUser']),

		logout() {
			this.setToken(null);
			this.setUser(null);
			alert('로그아웃되었습니다.');
			if (this.$route.path !== '/store') this.$router.push('/store');
		},
	},
};
</script>

<style scoped>
* {
	box-sizing: border-box;
}

.tappage {
	display: flex;
	justify-content: space-evenly;
	margin-top: 20px;
	padding: 7px 6%;
	border-top: 1px solid #dee2e6;
}

.tappage i {
	cursor: pointer;
}

.mypage-postlist {
	display: flex;
	flex-direction: column;
	margin: 10px 6%;
}

.post-container {
	display: flex;
	flex-direction: row;
	margin-bottom: 28px;
	align-items: stretch;
	position: relative;
	flex-shrink: 0;
}

.post-container {
	align-items: stretch;
}
</style>
