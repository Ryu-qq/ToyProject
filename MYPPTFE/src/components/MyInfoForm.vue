<template>
	<div>
		<div class="mypage-container">
			<div class="mypage-header">
				<div class="mypage-content">
					<img class="my-picture" :src="profileImageUrl" alt="photo" />
				</div>
				<section>
					<div class="mypage-info">
						<div v-if="isMySelf">
							<span> {{ username }} 님</span>
						</div>
						<div v-else>
							<span>{{ username }} 님</span>
						</div>

						<div v-if="isMySelf">
							<button class="setting-btn">프로필 편집</button>
							<button class="post-btn">
								<router-link to="/post">게시물 등록하기</router-link>
							</button>
							<button @click="logout()">
								<i class="fas fa-sign-out-alt"></i>
							</button>
						</div>

						<div v-else>
							<div>
								<button
									:class="[isFollow > 0 ? 'followcancel-btn' : 'follow-btn']"
									@click="getFollowInfo()"
								>
									{{ statusMsg }}
								</button>
							</div>
						</div>
					</div>

					<ul class="mypage-tap">
						<li>게시물 {{ postCnt }}</li>
						<li class="follow">팔로워 {{ followerCnt }}</li>
						<li class="follow">팔로잉 {{ followingCnt }}</li>
					</ul>
				</section>
			</div>
		</div>
	</div>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex';

export default {
	data() {
		return {
			followStatus: false,
			statusMsg: '팔로잉',
		};
	},

	computed: {
		...mapGetters(['user', 'token', 'userInfo', 'follow']),

		isMySelf() {
			if (this.userInfo.userId == this.user.userId) {
				return true;
			} else {
				return false;
			}
		},

		username() {
			if (this.isMySelf) {
				return this.user.username;
			} else {
				return this.userInfo.username;
			}
		},

		profileImageUrl() {
			if (this.isMySelf) {
				return this.user.profileImageUrl;
			} else {
				return this.userInfo.profileImageUrl;
			}
		},
		postCnt() {
			return this.userInfo.postCnt;
		},
		followerCnt() {
			return this.follow.followerCnt;
		},
		followingCnt() {
			return this.follow.followingCnt;
		},
		isFollow() {
			return this.follow.followStatus;
		},
	},

	created() {
		this.fetchUserInfo();
		//this.getFollowInfo();
	},

	methods: {
		...mapMutations(['setToken', 'setUser', 'setUserInfo']),

		logout() {
			this.setToken(null);
			this.setUser(null);
			alert('로그아웃되었습니다.');
			if (this.$route.path !== '/map') this.$router.push('/map');
		},

		async getFollowInfo() {
			const payLoad = {
				toUserId: this.userInfo.userId,
				fromUserId: this.user.userId,
			};
			await this.$store.dispatch('fetchFollow', payLoad);
		},

		async fetchUserInfo() {
			const formData = new FormData();
			formData.append('fromUserId', this.user.userId);
			formData.append('toUserId', this.$route.params.userId);
			await this.$store.dispatch('fetchUserInfo', formData);
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
.mypage-info .follow-btn {
	margin: 0 31px;
	border: 1px solid #fff;
	border-radius: 10px;
	text-decoration: none;
}

.mypage-info .followcancel-btn {
	background-color: #0095f6;
	margin: 0 31px;
	border: 1px solid #fff;
	border-radius: 10px;
	text-decoration: none;
}

.post-btn .setting-btn {
	margin: 0 4px;
	border: 1px solid #fff;
	border-radius: 10px;
	background-color: #f8f8f8;
	text-decoration: none;
}

.follow {
	cursor: pointer;
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
