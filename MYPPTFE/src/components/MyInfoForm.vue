<template>
	<div>
		<div class="mypage-container">
			<div class="mypage-header">
				<div class="mypage-content">
					<img class="my-picture" :src="users.profileImageUrl" alt="photo" />
				</div>
				<section>
					<div class="mypage-info">
						<div>
							<span>{{ users.username }} 님</span>
						</div>

						<div v-if="isLogin">
							<button class="post-btn">
								<router-link to="/post">게시물 등록하기</router-link>
							</button>
							<button @click="$emit('logout')">
								<i class="fas fa-sign-out-alt" @click="logout()"></i>
							</button>
						</div>

						<div v-else>
							<div>
								<button
									:class="[
										!follow || follow.followSeq > 0
											? 'followcancel-btn'
											: 'follow-btn',
									]"
									@click="doFollow()"
								>
									{{ statusMsg }}
								</button>
							</div>
						</div>
					</div>

					<ul class="mypage-tap">
						<li>게시물 {{ postCnt }}</li>
						<li class="follow" @click="fetchFollower()">
							팔로워 {{ follow.followerCnt }}
						</li>
						<li class="follow" @click="fetchFollow()">
							팔로잉 {{ follow.followingCnt }}
						</li>
					</ul>
				</section>
			</div>
		</div>
		<modal-view v-if="followModalOpen" @onCloseModal="followModalOpen = false">
			<div slot="body" class="modal-body">
				<div class="header">
					<p>{{ followMsg }}</p>
				</div>
				<ul
					v-for="(member, index) in followlist"
					:key="index"
					class="follow-wrapper"
				>
					<li>
						<img
							:src="member.profileImageUrl"
							@click="goUserPage(member.userId)"
						/>
						<p>{{ member.username }}</p>
						<div class="line"></div>
					</li>
				</ul>
			</div>
		</modal-view>
	</div>
</template>

<script>
import { mapMutations, mapGetters } from 'vuex';
import ModalView from '@/components/common/modal/ModalView.vue';

export default {
	components: { ModalView },
	props: {
		users: {
			type: Object,
			required: true,
		},
		follow: {
			type: Object,
			required: true,
		},
		postitems: {
			type: Array,
			default: () => ({}),
		},
		followlist: {
			type: Array,
			default: () => [],
		},
	},
	data() {
		return {
			statusMsg: '팔로잉',
			followModalOpen: false,
			followMsg: '',
		};
	},

	computed: {
		...mapGetters(['user']),

		postCnt() {
			return this.postitems.length;
		},
		isLogin() {
			return this.user;
		},
	},
	created() {},

	methods: {
		...mapMutations(['setToken', 'setUser']),

		//팔로우 팔로잉 리스트 뽑아오는거
		fetchFollow() {
			this.$emit('fetchFollow', {
				userId: this.user.userId,
				type: 'follow',
			});
			if (this.followlist) {
				this.followModalOpen = true;
				this.followMsg = '팔로잉';
			}
		},
		fetchFollower() {
			this.$emit('fetchFollower', {
				userId: this.user.userId,
				type: 'follower',
			});
			if (this.followlist) {
				this.followModalOpen = true;
				this.followMsg = '팔로워';
			}
		},

		doFollow() {
			if (!this.user) {
				this.$emit('notPermit');
				return;
			}
			this.$emit('doFollow', {
				toUser: this.users.id,
				fromUser: this.user.id,
			});
		},
		goUserPage(userId) {
			if (this.$route.path !== '/user/' + userId) {
				this.$router.push('/user/' + userId);
			}
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

.modal-body {
	height: 450px;
}

.follow-wrapper li {
	width: 100%;
	display: flex;
	align-items: center;
}

.follow-wrapper img {
	width: 13%;
	border-radius: 50%;
}

.follow-wrapper p {
	padding-left: 15px;
	padding-top: 11px;
}

.modal-body .header {
	border-bottom: 1px solid;
	display: flex;
	align-items: center;
	text-align: center;
	margin-bottom: 15px;
}

.modal-body .header p {
	width: 100%;
	font-size: 1.2rem;
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

li img {
	cursor: pointer;
}
</style>
