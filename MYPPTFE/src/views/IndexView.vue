<template>
	<div>
		<div>
			<nav-bar @onOpenLoginModal="openLoginModal" @onLogout="logout"> </nav-bar>
			<spinner :loading="loadingStatus"></spinner>
			<router-view></router-view>
		</div>

		<sign-in-modal
			v-if="isLoginModalOpen"
			@onCloseModal="isLoginModalOpen = false"
		>
			<div slot="body">
				<login-modal></login-modal>
			</div>
		</sign-in-modal>
	</div>
</template>

<script>
import NavBar from '@/components/NavBar.vue';
import LoginModal from '@/components/common/modal/LoginModal.vue';
import SignInModal from '@/components/common/modal/ModalView.vue';
import Spinner from '@/components/common/Spinner.vue';
import bus from '@/utils/bus.js';
import { mapGetters, mapMutations } from 'vuex';

export default {
	components: { NavBar, LoginModal, SignInModal, Spinner },
	data() {
		return {
			isLoginModalOpen: false,
			loadingStatus: false,
		};
	},
	computed: {
		...mapGetters(['token']),
	},

	created() {
		bus.$on('start:spinner', this.startSpinner);
		bus.$on('end:spinner', this.endSpinner);
	},

	beforeDestroy() {
		bus.$off('start:spinner', this.startSpinner);
		bus.$off('end:spinner', this.endSpinner);
	},

	methods: {
		...mapMutations(['setToken', 'setUser']),

		openLoginModal() {
			this.isLoginModalOpen = true;
		},

		logout() {
			this.setToken(null);
			this.setUser(null);
			alert('로그아웃되었습니다.');
			if (this.$route.path !== '/store') this.$router.push('/store');
		},

		startSpinner() {
			this.loadingStatus = true;
		},
		endSpinner() {
			this.loadingStatus = false;
		},
	},
};
</script>

<style scoped></style>
