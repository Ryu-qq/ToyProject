<template>
	<div class="container">
		<my-info-form
			:users="users"
			:follow="follow"
			:postitems="postItems"
			@doFollow="doFollow"
		></my-info-form>

		<div class="tappage">
			<a @click="onClickTab(0)">
				<span :class="[selectedTab === tabs[0] ? 'tap-selected' : 'tap-none']"
					><i class="fas fa-th fa-1x"> 게시물</i></span
				>
			</a>
			<a @click="onClickTab(1)"
				><span :class="[selectedTab === tabs[1] ? 'tap-selected' : 'tap-none']"
					><i class="fas fa-map fa-1x"> 지도</i></span
				>
			</a>
		</div>

		<div v-if="selectedTab === tabs[0]" class="mypage-postlist">
			<post-list-form
				:postitems="postItems"
				@onOpenPostModal="openPostModal"
			></post-list-form>
		</div>
		<div v-else class="mypage-mapview">
			<map-form :postitems="postItems"></map-form>
		</div>

		<post-modal
			v-if="isModalOpen"
			class="post-modal"
			@onCloseModal="onCloseModal"
		>
			<div slot="body">
				<post-view @onCloseModal="onCloseModal"></post-view>
			</div>
		</post-modal>
	</div>
</template>

<script>
import { mapMutations, mapGetters } from 'vuex';
import MyInfoForm from '../components/MyInfoForm.vue';
import PostListForm from '@/components/posts/PostListForm.vue';
import MapForm from '@/components/MapForm.vue';
import PostView from '@/views/PostView.vue';
import PostModal from '@/components/common/modal/PostModal.vue';
import { doFollow } from '@/api/follow';
import { getUserInfo } from '@/api/user';

export default {
	components: { MyInfoForm, PostListForm, MapForm, PostView, PostModal },
	data() {
		return {
			tabs: [PostListForm, MapForm],
			selectedTab: '',
			isModalOpen: false,
			endpoint: '',
			postItems: [],
			users: {},
			follow: {},
			postcnt: '',
		};
	},

	computed: {
		...mapGetters(['token', 'user']),

		isLoggedIn() {
			return this.token != null;
		},
	},

	created() {
		this.selectedTab = this.tabs[0];
		this.fetchUserInfo();
	},

	methods: {
		...mapMutations(['setToken', 'setUser']),

		onClickTab(i) {
			this.selectedTab = this.tabs[i];
		},
		onCloseModal() {
			this.isModalOpen = false;
		},

		logout() {
			this.setToken(null);
			this.setUser(null);
			alert('로그아웃되었습니다.');
			if (this.$route.path !== '/map') this.$router.push('/map');
		},

		async fetchUserInfo() {
			//const formData = new FormData();
			//formData.append('fromUserId', this.user.userId);
			//formData.append('toUserId', this.$route.params.userId);
			const { data } = await getUserInfo(this.$route.params.userId);
			this.postItems = data.body.userInfo.userPostList;
			this.users = data.body.userInfo.userInfo;
			this.follow = data.body.userInfo.followInfo;
			this.postcnt = data.body.userInfo.postCnt;
		},

		async openPostModal(endpoint) {
			this.endpoint = endpoint;
			await this.$store.dispatch('fetchPost', endpoint);
			this.isModalOpen = true;
		},

		async doFollow(payload) {
			const { data } = await doFollow(payload);
			this.follow = data.body.followInfo;
		},
	},
};
</script>

<style scoped>
* {
	box-sizing: border-box;
}
.container {
	display: flex;
	flex-direction: column;
	padding: 70px 0;
}
.highLight {
	color: #000;
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

.tap-selected {
	color: rgb(59, 55, 55);
}

.tap-none {
	color: #a6a6a6;
}

.post-modal {
	width: 800px;
}
</style>
