<template>
	<div></div>
</template>

<script>
import { mapActions, mapMutations } from 'vuex';

export default {
	created() {
		const token = this.$route.query.token;
		console.log('token', token);
		if (token) {
			this.setToken(token);
			this.fetchUser();
		}
		this.$router.replace('/store');
	},

	methods: {
		...mapActions(['fetchUser']),
		...mapMutations(['setToken']),
	},
};
</script>
