<template>
  <div>
    <div>                    
      <li v-for='social in socials' v-bind:key ="social.socialType">
        <a class='social_btn' :href ='socialLoginUrl(social.socialType)'>
            <img class ='social_login' src= social.src :style='{width: social.width, height: social.height}'>
               {{social.comment}}
        </a>
       </li>
     </div>
  </div>
</template>

<script>
import $ from '../../utils/utils'
import {mapActions, mapMutations} from 'vuex'

export default {
    props:['isOpen'],
    data(){
        return{
          socials:[
            {
              socialType: 'google',
              src: $.getSocialImage('google'),
              width: '32px',
              height: '32px',
              comment: '구글 로그인'
            },
          ]
        }
    },
    methods: {
        ...mapActions(['fetchUser']),
        ...mapMutations(['setToken']),

        socialLoginUrl (socialType) {
            return $.getSocialLoginUrl(socialType)
        }
        
    },


}
</script>

<style>

li{
    list-style:none;
    text-align: center;
}

.social_btn {
  margin-bottom: 15px;
  font-weight: 400;
  font-size: 16px;
  display: block;
  width: 100%;
  height: 45px;
  line-height: 45px;
  margin-bottom: 15px;
  border-radius: 4px;
  border: 1px solid #e8e8e8;
  cursor: pointer;
  color: black;
  text-decoration: none;
}

.social_btn:hover {
  color: #42b983;
}

.social_login {
    height: 32px;
    float: left;
    margin-top: 5.2px;
    margin-left: 10px;
    
}

.socal_login_container {
  text-align: center
}

</style>