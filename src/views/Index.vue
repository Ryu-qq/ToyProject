<template>
    <div>
        
        <nav-bar
            @onOpenLoginModal ='openLoginModal'
            @onLogout = 'logout'>
        </nav-bar>

        <router-view></router-view>
        
        <!-- <transition name="page">
        </transition> -->
       

        <sign-in-modal  v-if="isLoginModalOpen"
                @onCloseModal="isLoginModalOpen = false">
        
            <div slot = 'header'>
                <h4 slot> 로그인 </h4>
            </div>
            
            <div slot ="body">
                <login-modal></login-modal>
            </div>
        </sign-in-modal>
        
    </div>
</template>

<script>

import NavBar from '../components/NavBar.vue'
import LoginModal from '../components/common/LoginModal.vue'
import SignInModal from '../components/common/ModalView.vue'

import {mapGetters, mapMutations} from 'vuex'

export default {
    components:{NavBar, LoginModal, SignInModal},
    data(){
        return{
            isLoginModalOpen: false,
        }
    },
    methods: {

         ...mapMutations(['setToken', 'setUser']),
        
        openLoginModal(){
            this.isLoginModalOpen = true
        },

        logout(){
            this.setToken(null)
            this.setUser(null)
            alert('로그아웃되었습니다.')
            if (this.$route.path !== '/store') this.$router.push('/store')
        },
    },

    computed:{
        ...mapGetters(['token']),
    },



}
</script>

<style scoped>



</style>