<template>
	<div></div>
</template>

<script>
import { mapActions, mapMutations } from 'vuex';

export default {
	created() {
		const token = this.$route.query.token;
		if (token) {
			console.log(token);
			this.setToken(token);
			this.fetchUser();
		}
		this.$router.replace('/map');
	},

	methods: {
		...mapActions(['fetchUser']),
		...mapMutations(['setToken']),
	},
};
</script>
