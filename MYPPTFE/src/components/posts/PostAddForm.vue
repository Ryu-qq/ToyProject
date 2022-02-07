<template>
	<div class="contents">
		<div class="form wrapper">
			<form class="form">
				<div>
					<label for="title" class="title"> 상호명 </label>
					<input id="title" v-model="title" type="text" />
				</div>
				<div class="address">
					<find-address></find-address>
				</div>
				<div class="photo-upload">
					<label for="title" class="title"> 사진 올리기 </label>
					<button class="btn">불러오기</button>
				</div>
				<div>
					<label for="contents">Contents:</label>
					<textarea id="contents" v-model="contents" type="text" rows="3" />
				</div>
				<button class="btn">저장하기</button>
			</form>
		</div>
	</div>
</template>

<script>
import FindAddress from '@/components/common/FindAddress.vue';

export default {
	components: { FindAddress },
};
</script>

<style>
* {
	box-sizing: border-box;
}
</style>
