<template>
	<div>
		<transition name="modal">
			<div class="modal-mask">
				<div class="modal-wrapper">
					<div class="modal-container">
						<div class="modal-body">
							<div class="photo-container">
								<i
									v-if="reviewLength && reviewIdx != 0"
									class="fas fa-caret-left fa-2x"
									@click="goLeftR()"
								></i>

								<i
									v-if="reviewLength && reviewIdx != reviewLength - 1"
									class="fas fa-caret-right fa-2x"
									@click="goRightR()"
								></i>

								<span @click="$emit('onCloseModal')">X</span>
								<div class="post-preview-wrapper">
									<div class="post-preview-item">
										<div
											class="my-picture"
											@click="goUserPage(postitems[reviewIdx].userId)"
										>
											<img :src="postitems[reviewIdx].profileUrl" />
											<p>{{ postitems[reviewIdx].userName }}</p>
										</div>
										<i
											v-if="photoLength && photoIdx != 0"
											class="fas fa-caret-left fa-2x"
											@click="goLeftP()"
										></i>
										<i
											v-if="photoLength && photoIdx != photoLength - 1"
											class="fas fa-caret-right fa-2x"
											@click="goRightP()"
										></i>

										<img
											:src="
												require(`/assets/${postitems[reviewIdx].image[photoIdx].filePath}`)
											"
											class="photo"
										/>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</transition>
	</div>
</template>

<script>
export default {
	props: {
		postitems: {
			type: Array,
			default: () => ({}),
		},
	},
	data() {
		return {
			reviewIdx: 0,
			photoIdx: 0,
			showLeft: '',
			showRight: '',
		};
	},
	computed: {
		reviewLength() {
			return this.postitems.length;
		},

		photoLength() {
			return this.postitems[this.reviewIdx].image.length;
		},
	},
	methods: {
		goUserPage(userId) {
			if (this.$route.path !== '/user/' + userId) {
				this.$router.push('/user/' + userId);
			}
		},
		goRightR() {
			const leng = this.postitems.length;
			this.photoIdx = 0;
			if (leng && this.reviewIdx < leng - 1) this.reviewIdx++;
		},

		goLeftR() {
			const leng = this.postitems.length;
			this.photoIdx = 0;
			if (leng && this.reviewIdx > 0) this.reviewIdx--;
		},
		goRightP() {
			const leng = this.postitems[this.reviewIdx].image.length;

			if (leng && this.photoIdx < leng - 1) this.photoIdx++;
		},

		goLeftP() {
			const leng = this.postitems[this.reviewIdx].image.length;
			if (leng && this.photoIdx > 0) this.photoIdx--;
		},
	},
};
</script>

<style scoped>
.modal-mask {
	position: fixed;
	z-index: 9998;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	display: table;
	transition: opacity 0.3s ease;
}

.modal-wrapper {
	display: table-cell;
	vertical-align: middle;
}

.modal-container {
	width: 60%;
	margin: 0px auto;
	background-color: #fff;
	border-radius: 10px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
	transition: all 0.3s ease;
}

.modal-body {
	margin: 0;
	padding: 0;
}

.modal-default-button {
	float: right;
}

.modal-enter {
	opacity: 0;
}

.modal-leave-active {
	opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
	-webkit-transform: scale(1.1);
	transform: scale(1.1);
}
.photo-container {
	position: relative;
}

.fa-caret-left {
	position: absolute;
	top: 50%;
	left: -35px;
	color: #fff;
}

.fa-caret-right {
	position: absolute;
	top: 50%;
	right: -35px;
	color: #fff;
}

.post-preview-item .fa-caret-right {
	position: absolute;
	top: 50%;
	right: 20px;
	z-index: 999;
	color: #fff;
}

.post-preview-item .fa-caret-left {
	position: absolute;
	top: 50%;
	left: 20px;
	z-index: 999;

	color: #fff;
}

.fa-circle-arrow-right {
	position: absolute;
	top: 50%;
	right: 0;
	color: #fff;
}

.my-picture {
	display: flex;
	z-index: 999;
	cursor: pointer;
	position: absolute;
	top: 0%;
	left: 0;
}

.my-picture span {
	left: 20px;
}
.my-picture img {
	width: 40%;
	border-radius: 50px;
}

.my-picture p {
	margin-left: 10px;
	margin-top: 13px;
	color: #fff;
	font-weight: bold;
	font-size: 1.3rem;
}

i:hover {
	cursor: pointer;
}

.photo {
	max-height: 480px;
	width: 100%;
	position: relative;
}

span {
	z-index: 999;
	color: #fff;
	padding: 9px 25px;
	border-radius: 10px;
	cursor: pointer;
	position: absolute;
	right: 0%;
	top: 0%;
}

span:hover {
	background-color: #a6a6a6;
}
</style>
