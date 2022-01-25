<template>
    <div class ="header">
        <div class ="header-container">
            <nav>
                <ul class ="nav_links">
                    <router-link  to="/" class ="logo"> 
                        <span> 맛잘알 </span>
                     </router-link>
                    <li><router-link to="/">가게 찾기</router-link></li>
                    <li><router-link to="/lookaround">둘러보기</router-link></li>
                </ul>
            </nav>
            
            <div class="my-info">
                 <div v-if="!isLoggedIn" >
                    <a> <button  @click ='$emit("onOpenLoginModal")'>로그인</button> </a>
                </div>
                <div v-else >
                    <div>
                        <span class> {{username}} 님 환영합니다!</span>
                        <img class ="my-picture"  :src="profileImageUrl" alt = photo @click="goMyPage()"/>
                    </div>
                     <!-- <a> <button @click="logout()"> 로그아웃 </button> </a> -->
                </div>
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

.header-container{
    padding: 9px 6%;
    display: flex;
    justify-content: space-between;
    align-items: center;
   
}

.logo span{
    font-size: 1.6rem;
    border-radius: 3px;
    cursor: pointer;
    transition: 0.2s;
}

.logo span:hover{
    color: rgb(110, 173, 81);
    transform: translate(0, -3px);
}

.nav_links{
    list-style: none;
    
}

.nav_links li{
    display: inline-block;
    
    padding: 0px 15px;
    transition: 0.2s;
    
    
}
.nav_links li:hover{
    transform: translate(0, -3px);

}

.nav_links li a:hover{
    color: #65be6a;
}

.my-info{
    text-align-last: right;
    align-items: center;
    padding: 7px 0;
}

.my-info span{
    margin-right: 9px;
}

.my-picture{
    width: 15%;
    height: 15%;
    cursor:pointer;
}



li, a{
    font-weight: 500;
    font-size: 16px;
    color:black;
    text-decoration: none;
}

button{
    padding: 9px 18px;
    background-color: #fff;
    border: 1px solid rgba(91, 199, 70, 0.8) ;
    border-radius: 50px;
    cursor:pointer;
}

button:hover{
    background-color: #65be6a;
}

img {
    border-radius: 50%;
    width: 15%;
    height: 15%;
}

</style>