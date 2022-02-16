<template>
	<div>
		<div class="mypage-container">
			<div class="mypage-header">
				<div class="mypage-content">
					<img class="my-picture" :src="profileImageUrl" alt="photo" />
				</div>
				<section>
					<div class="mypage-info">
						<span> {{ username }} 님 환영합니다!</span>

						<button class="setting-btn">프로필 편집</button>
						<button class="post-btn">
							<router-link to="/post">게시물 등록하기</router-link>
						</button>
						<button @click="logout()">
							<i class="fas fa-sign-out-alt"></i>
						</button>
					</div>

					<ul class="mypage-tap">
						<li>게시물 {{ postCnt }}</li>
						<li>팔로워 2</li>
						<li>팔로잉 3</li>
					</ul>
				</section>
			</div>
		</div>
	</div>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex';

export default {
	computed: {
		...mapGetters(['user', 'token', 'posts']),

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

		postCnt() {
			return this.posts.length;
		},
	},
	methods: {
		...mapMutations(['setToken', 'setUser']),

		logout() {
			this.setToken(null);
			this.setUser(null);
			alert('로그아웃되었습니다.');
			if (this.$route.path !== '/storeview') this.$router.push('/storeview');
		},
	},
};
</script>

<style scoped>
* {
	box-sizing: border-box;
}

.mypage-container {
	display: flex;
	flex-direction: column;
	padding-top: 60px;
}

.mypage-header {
	display: flex;
	justify-content: flex-start;
	padding: 20px 6%;
}

.mypage-content {
	margin-right: 74px;
}

.mypage-setting {
	margin: 10px 0;
	display: flex;
	align-items: center;
	justify-content: center;
}

.mypage-info span {
	padding: 10px 0;
	margin: 0 8px;
	display: flex;
	justify-content: space-between;
	align-content: center;
}

.my-picture {
	width: 100%;
	height: 100%;
	border-radius: 50%;
}

.mypage-info button {
	font-size: 0.6rem;
	margin: 8px 0;
	border: 1px solid #fff;
	border-radius: 10px;
	background-color: #f8f8f8;
	text-decoration: none;
}

.mypage-info a {
	color: #000;
	text-decoration: none;
}

section {
	margin-top: 15px;
}

section .mypage-info {
	display: flex;
}

button:hover {
	cursor: pointer;
}

ul {
	margin-left: 8px;
	padding: 0;
	display: flex;
	list-style: none;
	justify-content: space-between;
}
</style>
