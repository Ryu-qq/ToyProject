<template>
  <div>  <a class ="cta"> <button @click="logout()"> 로그아웃 </button> </a></div>
</template>



<script>
import bus from '../utils/bus.js'
import { mapMutations } from 'vuex'

export default {

  created(){
    bus.$emit('start:spinner');

  },
  mounted(){
    bus.$emit('end:spinner')
  },
  methods: {
   ...mapMutations(['setToken', 'setUser']),

  logout(){
            this.setToken(null)
            this.setUser(null)
            alert('로그아웃되었습니다.')
            if (this.$route.path !== '/store') this.$router.push('/store')
        }
  },

}
</script>




<style>

</style>