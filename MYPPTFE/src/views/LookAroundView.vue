<template>
  <div class = "container">



  </div>
</template>

<script>
import bus from '../utils/bus.js'


export default {

  created(){
    bus.$emit('start:spinner');
    bus.$emit('end:spinner');

  },
  mounted(){
    
  },

  computed:{

  },

}
</script>

<style>

.container{
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px 10%;
}

</style>