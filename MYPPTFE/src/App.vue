<template>
	<div id="app">
		<index />
	</div>
</template>

<script>
import Index from '@/views/Index.vue';

export default {
	name: 'App',
	components: { Index },
	data() {
		return {};
	},
};
</script>

<style>
#app {
	font-family: 'Montserrat', sans-serif;
	font-weight: 500;
	font-size: 16px;
	color: black;
	text-decoration: none;
	margin: 0 7%;
}

.page-enter-active,
.page-leave-active {
	transition: opacity 0.5s;
}
.page-enter,
.page-leave-to {
	opacity: 0;
}

@import './css/common.css';
</style>
