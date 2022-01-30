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
						<li>게시물 1</li>
						<li>팔로워 2</li>
						<li>팔로잉 3</li>
					</ul>
				</section>
			</div>

			<div class="tappage">
				<a href=""
					><span
						><i class="fas fa-th fa-1x" style="color: #a6a6a6"> 게시물</i></span
					></a
				>
				<a href=""
					><span
						><i class="fas fa-map fa-1x" style="color: #a6a6a6"> 지도</i></span
					></a
				>
			</div>

			<div class="mypage-postlist">
				<div clsss="mypage-postrow">
					<div class="post-container"></div>
				</div>
				<div></div>
			</div>
		</div>
	</div>
</template>

<script>
import { mapMutations, mapGetters } from 'vuex';
export default {
	components: {},

	created() {},

	mounted() {},

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

ul {
	margin-left: 8px;
	padding: 0;
	display: flex;
	list-style: none;
	justify-content: space-between;
}

section {
	margin-top: 15px;
}

section .mypage-info {
	display: flex;
}

.mypage-info button {
	font-size: 0.6rem;
	margin: 8px 5px;
	border: 1px solid #fff;
	border-radius: 10px;
	background-color: #f8f8f8;
	text-decoration: none;
}

.mypage-info a {
	color: #000;
}

button:hover {
	cursor: pointer;
}
</style>
