<template>
    <div class ="header"> 
            <nav>
                <ul class ="nav_links">
                    <router-link  to="/" class ="logo"> 
                        <span  class = "nav_links"> 맛잘알 </span>
                     </router-link>
                    <li><router-link to="/">가게 찾기</router-link></li>
                    <li><router-link to="/lookaround">둘러보기</router-link></li>
                </ul>
            </nav>
            
            <div v-if="!isLoggedIn" >
                    <a class ="cta"> <button  @click ='$emit("onOpenLoginModal")'>로그인</button> </a>
            </div>
            <div v-else >
                <div class ="myInfo" >
                     <span> {{username}} 님 환영합니다!        </span>
                     <img class ="avator"  :src="profileImageUrl" alt = photo @click="goMyPage()"/>
                     <!-- <a class ="cta"> <button @click="logout()"> 로그아웃 </button> </a> -->
                </div>
            </div>
    </div>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex'


export default {
    data(){
        return{
            isLoginModeOpen:false
        }
    },
    computed:{

        ...mapGetters(['token' ,'user']),

        isLoggedIn () {
            return this.token != null
        },

        isAdmin () {
            return this.user && this.user.roleType === 'ADMIN'
        },
        username () {
            if (!this.user) return ''
            return this.user.username
        },

        roleType () {
            if (!this.user) return ''
            return this.user.roleType
        },
        profileImageUrl(){
            if(!this.user) return ''
            return this.user.profileImageUrl
        },
        toekn(){
          if(!this.token) return ''
          return this.token;
        }

    },
    
    methods: {
        ...mapMutations(['setToken', 'setUser']),

        goMyPage(){
            if (this.$route.path !== '/mypage') this.$router.push('/mypage')

        },

        logout(){
            this.setToken(null)
            this.setUser(null)
            alert('로그아웃되었습니다.')
            if (this.$route.path !== '/store') this.$router.push('/store')
        }
    },

}
</script>



<style scoped>
*{
    box-sizing: border-box;
    margin:0;
    padding:0;
    background-color: whitesmoke;
}

.header{
    display: flex;
    justify-content: space-between;
    align-items:  center;
    padding: 20px 6%;
}

li, a{
    font-weight: 500;
    font-size: 16px;
    color:black;
    text-decoration: none;
}

.logo{
    cursor: pointer;
}

.logo .nav_links:hover{
    color: rgb(110, 173, 81);
}

.avator{
    cursor:pointer;
}



.nav_links{
    list-style: none;
}

.nav_links li{
    display: inline-block;
    padding: 0px 20px;
}

.nav_links li a{
    transition: all 0.3 ease 0s;
}

.nav_links li a:hover{
    color: rgb(110, 173, 81);
}

.myInfo{
    text-align-last: right;
}

.avarter{
    width: 20px;
    height: 20px;
}

button{
    padding: 9px 15px;
    background-color: rgb(66, 184, 11);
    border: none;
    border-radius: 50px;
    cursor:pointer;
    transition: all 0.3 ease 0s;
}

button:hover{
    background-color: rgba(91, 199, 70, 0.8);
}

img {
    border-radius: 50%;
    width: 15%;
    height: 15%;
}

.logo {
  font-family: "TmonMonsori", sans-serif;
  font-size: 28px;
  text-decoration: none;
  padding: 5px;
  border-radius: 3px;
}





</style>