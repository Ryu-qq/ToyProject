<template>
	<div>
		<div>
			<nav-bar @onOpenLoginModal="openLoginModal" @onLogout="logout"> </nav-bar>

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

import { mapGetters, mapMutations } from 'vuex';

export default {
	components: { NavBar, LoginModal, SignInModal },
	data() {
		return {
			isLoginModalOpen: false,
		};
	},

	computed: {
		...mapGetters(['token']),
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
	},
};
</script>

<style scoped></style>
