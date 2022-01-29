<template>
  <div id="app">

    <index-view></index-view>

  </div>
</template>

<script>

import IndexView from './views/Index.vue';


export default {
  components: { IndexView },

  name: 'App',
  data(){
    return{
     
    };
  },
  // methods: {
  //   startSpinner(){
  //     this.loadingStatus = true
  //   },
  //   endSpinner(){
  //     this.loadingStatus = false
  //   }
  // },
  // create(){
  //   bus.$on('start:spinner', this.startSpinner);
  //   bus.$on('end:spinner', this.endSpinner);

  // },
  // beforeDestroy(){
  //   bus.$off('start:spinner', this.startSpinner);
  //   bus.$off('end:spinner', this.endSpinner);

  // },
}
</script>

<style>
#app{
    font-family: "Montserrat", sans-serif;
    font-weight: 500;
    font-size: 16px;
    color:black;
    text-decoration: none;
}

.page-enter-active, .page-leave-active{
  transition: opacity .5s;
}
.page-enter, .page-leave-to{
  opacity: 0;
}

</style>
