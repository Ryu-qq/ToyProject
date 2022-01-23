<template>
  <div>store
      <p v-if="isLoggedIn"> username : {{username}}</p>
      <p v-if="isLoggedIn"> role : {{roleType}}</p>
      <p v-if="isLoggedIn"> token : {{toekn}} </p>
      <img :src="profileImageUrl"/>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
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

}
</script>

<style>

</style>