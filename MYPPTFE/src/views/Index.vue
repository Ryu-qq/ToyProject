<template>
	<div>
		<div>
			<nav-bar @onOpenLoginModal="openLoginModal"> </nav-bar>
			<router-view></router-view>
		</div>

		<modal-view
			v-if="isLoginModalOpen"
			@onCloseModal="isLoginModalOpen = false"
		>
			<div slot="body">
				<login-modal></login-modal>
			</div>
		</modal-view>

		<modal-view v-if="isLogout" @onCloseModal="isLogout = false">
			<div slot="body">
				<div class="alert-msg">
					<p>팔로우 기능을 이용하시려면 로그인해야 합니다!</p>
				</div>
			</div>
		</modal-view>
	</div>
</template>

<script>
import NavBar from '@/components/common/NavBar.vue';
import LoginModal from '@/components/common/modal/LoginModal.vue';
import ModalView from '@/components/common/modal/ModalView.vue';
import { mapGetters, mapMutations } from 'vuex';

export default {
	components: { NavBar, LoginModal, ModalView },
	data() {
		return {
			isLoginModalOpen: false,
			loadingStatus: false,
			isLogout: false,
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
	},
};
</script>

<style scoped></style>
