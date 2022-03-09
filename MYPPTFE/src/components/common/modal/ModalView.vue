<template>
	<div>
		<transition name="modal">
			<div class="modal-mask">
				<div class="modal-wrapper">
					<div class="modal-container">
						<div class="modal-body">
							<slot name="body"> default body </slot>
						</div>

						<div class="modal-footer">
							<slot name="footer">
								<button @click="$emit('onCloseModal')">닫기</button>
							</slot>
						</div>
					</div>
				</div>
			</div>
		</transition>
	</div>
</template>

<script>
export default {};
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
	width: 400px;
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

.modal-footer {
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

button {
	padding: 3px 14px;
	background-color: #fff;
	border: 1px solid #f5f5f5;
	border-radius: 10px;
	cursor: pointer;
}

button:hover {
	background-color: #a6a6a6;
}
</style>
