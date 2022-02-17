<template>
	<div>
		<my-info-form></my-info-form>

		<div class="tappage">
			<a @click="onClickTab(0)">
				<span
					><i class="fas fa-th fa-1x" style="color: #a6a6a6"> 게시물</i></span
				>
			</a>
			<a @click="onClickTab(1)"
				><span
					><i class="fas fa-map fa-1x" style="color: #a6a6a6"> 지도</i></span
				>
			</a>
		</div>

		<div v-show="selectedTab === tabs[0]" class="mypage-postlist">
			<post-list-form></post-list-form>
		</div>
		<div v-show="selectedTab === tabs[1]" class="mypage-mapview">
			<map-form></map-form>
		</div>
		<!-- v
				<div class="tappage">
			<ul>
				<li
					v-for="tab in tabs"
					:key="tab.index"
					:class="{ active: tab === selectedTab }"
					
				></li>
			</ul>

		</div>
	</div> -->
	</div>
</template>

<script>
import { mapMutations, mapGetters } from 'vuex';
import MyInfoForm from '../components/MyInfoForm.vue';
import PostListForm from '@/components/posts/PostListForm.vue';
import MapForm from '@/components/MapForm.vue';

export default {
	components: { MyInfoForm, PostListForm, MapForm },
	data() {
		return {
			tabs: [PostListForm, MapForm],
			selectedTab: '',
		};
	},

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
	},

	created() {
		this.selectedTab = this.tabs[0];
	},

	methods: {
		...mapMutations(['setToken', 'setUser']),

		onClickTab(i) {
			this.selectedTab = this.tabs[i];
		},

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
