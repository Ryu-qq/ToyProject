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
		...mapGetters(['user', 'token', 'postList']),

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
			if (!this.postList) return 0;
			return this.postList.length;
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

		// getSomeoneInfo(){
		// 	if(this.$route.path !== '/mypage/' + userId){
		// 		this.$store.dispatch('fetchUser', payLoad);

		// 	}
		// }
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
}

.mypage-header {
	display: flex;
	justify-content: flex-start;
	padding: 20px 6%;
}

.mypage-content {
	width: 15%;
	margin-right: 121px;
}

.mypage-setting {
	margin: 10px 0;
	display: flex;
	align-items: center;
	justify-content: center;
}

.mypage-info {
	font-size: 0.9rem;
	margin-bottom: 20px;
	align-items: center;
}

.mypage-info span {
	padding: 0;
	margin: 0;
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
	margin: 0 4px;
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
	width: 100%;
}

section .mypage-info {
	display: flex;
}

button:hover {
	cursor: pointer;
}

ul {
	margin: 0px;
	padding: 0;
	display: flex;
	list-style: none;
}
li {
	font-size: 0.9rem;
	margin-right: 28px;
}
</style>
