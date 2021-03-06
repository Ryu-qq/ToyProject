<template>
	<div class="container">
		<user-info-form
			:member="member"
			:postitems="postItems"
			:follow="follow"
			:followlist="followList"
			:isfollow="isfollow"
			@doFollow="doFollow"
			@fetchFollow="fetchFollow"
			@notPermit="notPermit"
			@logout="logout"
		></user-info-form>

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
			@onCloseModal="isModalOpen = false"
		>
			<div slot="body">
				<post @onCloseModal="isModalOpen = false"></post>
			</div>
		</post-modal>
		<modal-view v-if="alertModalOpen" @onCloseModal="alertModalOpen = false">
			<div slot="body">
				<div class="alert-msg">
					<p>팔로우 기능을 이용하시려면 로그인해야 합니다!</p>
				</div>
			</div>
		</modal-view>

		<modal-view v-if="isLogout" @onCloseModal="doLogout">
			<div slot="body">
				<div class="alert-msg">
					<p>로그아웃 되었습니다</p>
				</div>
			</div>
		</modal-view>
	</div>
</template>

<script>
import { mapMutations, mapGetters } from 'vuex';
import PostListForm from '@/components/posts/PostListForm.vue';
import MapForm from '@/components/MapForm.vue';
import Post from '@/views/Post.vue';
import PostModal from '@/components/common/modal/PostModal.vue';
import ModalView from '@/components/common/modal/ModalView.vue';
import { doFollow, fetchFollow } from '@/api/follow';
import { getUserInfo } from '@/api/user';
import UserInfoForm from '../components/UserInfoForm.vue';

export default {
	components: {
		PostListForm,
		MapForm,
		Post,
		PostModal,
		ModalView,
		UserInfoForm,
	},
	data() {
		return {
			tabs: [PostListForm, MapForm],
			selectedTab: '',
			isModalOpen: false,
			alertModalOpen: false,
			isLogout: false,
			postItems: [],
			member: {},
			follow: {},
			followList: [],
			isfollow: 0,
		};
	},

	computed: {
		...mapGetters(['token']),

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

		notPermit() {
			this.alertModalOpen = true;
			//TEST
		},

		logout() {
			this.isLogout = true;
		},
		doLogout() {
			this.setToken(null);
			this.setUser(null);
			this.isLogout = false;
			this.$router.push('/map');
		},

		async fetchUserInfo() {
			const { data } = await getUserInfo(this.$route.params.userId);
			this.member = data.body.user.userInfo;
			this.postItems = data.body.user.userPostList;
			this.follow = data.body.user.followInfo;
		},

		async openPostModal(endpoint) {
			await this.$store.dispatch('fetchPost', endpoint);
			this.isModalOpen = true;
		},

		async doFollow(payload) {
			const { data } = await doFollow(payload);
			this.isfollow = data.body.follow;
		},

		async fetchFollow(payload) {
			const { data } = await fetchFollow(payload);
			this.followList = data.body.followList;
		},
	},
};
</script>

<style scoped>
* {
	box-sizing: border-box;
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

.alert-msg {
	height: 50px;
	align-items: center;
	display: flex;
}

.alert-msg p {
	width: 100%;
	text-align: center;
	padding-top: 15px;
}
</style>
