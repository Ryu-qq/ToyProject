<template>
	<ul class="news-list">
		<li v-for="item in listItems" :key="item.id" class="post">
			<!-- 점수 영역 -->
			<div class="point">
				{{ item.points }}
			</div>

			<div>
				<p class="review-title">
					<!-- 해당 페이지로 연결 -->
					<template v-if="item.domain">
						<a :href="item.url"> {{ item.title }} </a>
					</template>
				</p>
				<small class="link-text">
					{{ item.time_ago }} by
					<!-- item.user 가 있으면 밑에 링크  -->
					<router-link
						v-if="item.user"
						:to="`/user/${item.user}`"
						class="link-text"
					>
						{{ item.user }}
					</router-link>

					<!-- 없으면 링크 도메인으로 -->
					<a v-else :href="item.url"> {{ item.domain }} </a>
				</small>
			</div>
		</li>
	</ul>
</template>

<script>
export default {};
</script>

<style></style>
