<template>
    <div class ="header"> 
      <router-link to="/store">가게 찾기</router-link> |
      <router-link to="/LookAround">둘러보기</router-link> |
      <a :href ='socialLoginUrl()'> 123</a>
    </div>
</template>

<script>
//import { mapGetters } from 'vuex';
import $ from '../utils.js';

export default ({
    // computed:{

    //     ...mapGetters(['token', 'user']),
        
    //     isLoggedIn () {
    //         return this.token != null
    //     },

    //     isAdmin () {
    //         return this.user && this.user.role === 'ADMIN'
    //     },
    //     username () {
    //         if (!this.user) return ''
    //         return this.user.username
    //     },

    //     role () {
    //         if (!this.user) return ''
    //         return this.user.role
    //     },
    //     profileImageUrl(){
    //         if(!this.user) return ''
    //         return this.user.profileImageUrl
    //     }

    // },
    methods: {
        socialLoginUrl() {
            console.log($.getSocialLoginUrl('google'))
            return $.getSocialLoginUrl('google')
        }
    },

})
</script>



<style scoped>
.header{
    color: white;
    background-color: #42b833;
    display: flex;
    padding: 8px;
}
.header .router-link-exact-active {
    color : #35495e;
}
.header a{
    color: white;
}


</style>