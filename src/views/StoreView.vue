<template>
  <div class ="container">
    
    <div class ="search">
         <input class="search-input" 
                type="text" placeholder="검색어를 입력하세요"/>
                <!-- @keyup.enter="search"/> -->
          
          <div class ="options">
            <button>검색</button>
          </div>
    </div>
    <div class ="map_wrap">
      <div id="map" class="map"></div>
      <button class="custom_typecontrol" @click="setCurrentPos()">
        <i class="fas fa-crosshairs fa-2x" id="icon"></i>
      </button>
    </div>
  </div>
</template>

<script>
import bus from '../utils/bus.js'

export default {
  data() {
    return {
      gps_lat: null,
      gps_lng: null,
      map:''
    }
  },
  created(){
    bus.$emit('start:spinner');

  },

  mounted(){
    if(!("geolocation" in navigator)){
      return;
    }

      navigator.geolocation.getCurrentPosition(pos =>{
        this.gps_lat = pos.coords.latitude;
        this.gps_lng = pos.coords.longitude;

      window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addKakaoMapScript();
      
    }, err =>{
      console.log(err.message);
    })
    bus.$emit('end:spinner')
  },

  methods: {
    addKakaoMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
          "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=dfa31e0183999d6d13b92c75a089bb75";
      document.head.appendChild(script);
    },

    initMap() {
      var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      //this.getMyPosition()
      this.options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(37.570137, 126.977018), //지도의 중심좌표.
        level: 3 //지도의 레벨(확대, 축소 정도)
      };
      this.map = new kakao.maps.Map(container, this.options);
      return this.map;
  
      //지도 생성 및 객체 리턴
    },
    setCurrentPos(){
      var moveLatLon = new kakao.maps.LatLng(this.gps_lat  , this.gps_lng);
      // 지도 중심을 이동 시킵니다
      return this.map.setCenter(moveLatLon);
    },
  },
      

}
</script>


<style>

.container{
    display: flex;
    flex-direction: column;
    align-content: space-between;
    justify-content: center;
    width: 100%;
    padding: 20px 5%;
}

.search{
  display: flex;
  width: 100%;
  margin-bottom: 20px;
}

.search-input{
   margin-right:10px;
   width: 100%;
   border: 3px solid whitesmoke;
}

.search-input:focus{
   outline: none;
   border-color: rgba(91, 199, 70, 0.8);

}

button{
  width:80px;
  flex-shrink:  0;
  padding: 9px 25px;
  background-color: rgba(60, 184, 35, 0.8);
  border: none;
  cursor:pointer;
  transition: all 0.3 ease 0s;
}

button:hover{
  background-color: rgba(124, 218, 106, 0.8);
}

.map_wrap{
  width: 100%;
  height:600px;
  position:relative;
  overflow:hidden; 
}
.map {
  width: 100%;
  height: 100%;
  align-items: center;
  padding: 10%;
}
.custom_typecontrol{
  position:absolute;
  top:10px;
  right: 5px;
  align-content: flex-end;
  overflow:hidden;
  width:auto;
  height:auto;
  margin:0;
  padding:0;
  z-index:1;
  font-size:12px;
  cursor: pointer;
}
</style>