<template>
	<div class="header">
		<div>
			<div class="header-container">
				<nav>
					<ul class="nav_links">
						<router-link to="/" class="logo">
							<span> 맛잘알 </span>
						</router-link>
						<li><router-link to="/">가게 찾기</router-link></li>
						<li><router-link to="/search">둘러보기</router-link></li>
					</ul>
				</nav>

				<div class="my-info">
					<div v-if="isLoggedIn">
						<a> <button @click="$emit('onOpenLoginModal')">로그인</button> </a>
					</div>
					<div v-else class="my-picture">
						<img :src="profileImageUrl" @click="goUserPage()" />
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex';

export default {
	data() {
		return {
			isLoginModeOpen: false,
		};
	},
	computed: {
		...mapGetters(['token', 'user']),

		isLoggedIn() {
			return !this.token;
		},
		isAdmin() {
			return this.user && this.user.roleType === 'ADMIN';
		},
		roleType() {
			if (!this.user) return '';
			return this.user.roleType;
		},
		profileImageUrl() {
			if (!this.user) return '';
			return this.user.profileImageUrl;
		},
	},

	methods: {
		...mapMutations(['setToken', 'setUser']),

		goUserPage() {
			const userId = this.user.userId;
			if (this.$route.path !== '/userinfo/' + userId) {
				this.$router.push('/userinfo/' + userId);
			}
		},
	},
};
</script>

<style scoped>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
	z-index: 100;
}

.header {
	position: fixed;
	width: 100%;
	top: 0;
	left: 0;
	background: #fffefc;
}

.header-container {
	padding: 9px 7%;
	display: flex;
	justify-content: space-between;
	align-items: center;
	border-bottom: 1px solid #dee2e6;
}

.logo span {
	font-size: 1.6rem;
	border-radius: 3px;
	cursor: pointer;
	transition: 0.2s;
}

.logo span:hover {
	color: #a6a6a6;
	transform: translate(0, -3px);
}

.nav_links {
	list-style: none;
}

.nav_links li {
	display: inline-block;

	padding: 0px 15px;
	transition: 0.2s;
}
.nav_links li:hover {
	transform: translate(0, -3px);
}

.nav_links li a:hover {
	color: #a6a6a6;
}

.my-info {
	align-items: center;
}

.my-info span {
	margin-right: 9px;
}

.my-picture {
	display: flex;
	justify-content: flex-end;
	align-items: center;
}

.my-picture img {
	cursor: pointer;
}

li,
a {
	font-weight: 500;
	font-size: 16px;
	color: black;
	text-decoration: none;
}

button {
	padding: 9px 18px;
	background-color: #fff;
	border: 1px solid #f5f5f5;
	border-radius: 10px;
	cursor: pointer;
}

button:hover {
	background-color: #a6a6a6;
}

img {
	border-radius: 50%;
	width: 40%;
	height: 40%;
}
</style>
