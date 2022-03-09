<template>
	<div class="edit-container">
		<div class="edit-wrapper">
			<span class="cancel" @click="$emit('onDeletePost')">삭제하기</span>
			<div class="line"></div>
			<span @click="$emit('onEditPost')">수정하기</span>
		</div>
	</div>
</template>

<script>
export default {};
</script>

<style scoped>
.edit-wrapper {
	display: flex;
	flex-direction: column;
	align-content: center;
	align-items: center;
}
.cancel {
	color: rgb(250, 0, 0);
}
.line {
	width: 100%;
	border-top: 1px solid #dee2e6;
}
span {
	margin: 2% 0;
	cursor: pointer;
}
</style>
