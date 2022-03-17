<template>
	<div>
		<div class="mypage-container">
			<div class="mypage-header">
				<div class="mypage-content">
					<img class="my-picture" :src="member.profileImageUrl" alt="photo" />
				</div>
				<section>
					<div class="mypage-info">
						<div>
							<span>{{ member.username }} 님</span>
						</div>

						<div v-if="isMySelf">
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
									:class="[follow.id > 0 ? 'followcancel-btn' : 'follow-btn']"
									@click="doFollow()"
								>
									{{ followBox }}
								</button>
							</div>
						</div>
					</div>

					<ul class="mypage-tap">
						<li>게시물 {{ postCnt }}</li>
						<li class="follow" @click="fetchFollow('follower')">
							팔로워 {{ follow.followerCnt }}
						</li>
						<li class="follow" @click="fetchFollow('following')">
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
					v-for="(fol, index) in followlist"
					:key="index"
					class="follow-wrapper"
				>
					<li>
						<img :src="fol.profileImageUrl" @click="goUserPage(fol.userId)" />
						<p>{{ fol.username }}</p>
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
		member: {
			type: Object,
			default: () => ({}),
		},
		postitems: {
			type: Array,
			default: () => [],
		},
		follow: {
			type: Object,
			default: () => ({}),
		},
		followlist: {
			type: Array,
			default: () => [],
		},
		isfollow: {
			type: Number,
			default: 0,
		},
	},
	data() {
		return {
			followModalOpen: false,
			followMsg: '',
		};
	},

	computed: {
		...mapGetters(['user']),

		postCnt() {
			return this.postitems.length;
		},

		isMySelf() {
			return this.user
				? this.user.userId == this.member.userId
					? true
					: false
				: false;
		},
		followCnt() {
			return this.follow.followerCnt;
		},
		followBox() {
			return this.follow
				? this.follow.id > 0
					? '팔로잉'
					: '팔로우'
				: '팔로잉';
		},
	},
	watch: {
		isfollow() {
			this.changeInfo();
		},
	},

	methods: {
		...mapMutations(['setToken', 'setUser']),

		fetchFollow(type) {
			this.$emit('fetchFollow', {
				fromUserSeq: this.member.id,
				type: type,
			});
			if (this.followlist) {
				this.followModalOpen = true;
				this.followMsg = type == 'follower' ? '팔로워' : '팔로잉';
			}
		},

		doFollow() {
			if (!this.user) {
				this.$emit('notPermit');
				return;
			}

			this.$emit('doFollow', {
				toUserSeq: this.member.id,
				fromUserSeq: this.user.id,
			});
		},

		goUserPage(userId) {
			if (this.$route.path !== '/user/' + userId) {
				this.$router.push('/user/' + userId);
			}
		},
		changeInfo() {
			this.follow.id = this.isfollow;
			this.isfollow > 0 ? this.follow.followerCnt++ : this.follow.followerCnt--;
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

.modal-body ul {
	overflow: auto;
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
