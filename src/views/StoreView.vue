<template>
  <div class ="body">

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
          <i class="fas fa-crosshairs fa-3x" id="icon"></i>
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
        level: 4 //지도의 레벨(확대, 축소 정도)
      };
      this.map = new kakao.maps.Map(container, this.options);
      bus.$emit('end:spinner')
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

.body{
    display: flex;
    flex-direction: column;
    align-content: space-between;
    justify-content: center;
    width: 100%;
    height: 700px;
    padding: 24px 6%;
}

.search{
  display: flex;
  width: 100%;
  margin-bottom: 20px;
}

.search-input{
   margin-right:12px;
   width: 100%;
   border: 3px solid whitesmoke;
}

.search-input:focus{
   outline: none;
   border-color: rgba(91, 199, 70, 0.8);

}

.search button{
  width:80px;
  padding: 9px 18px;
  background-color: #fff;
  border: 1px solid rgba(91, 199, 70, 0.8) ;
  border-radius: 50px;
  cursor:pointer;
}

.search button:hover{
  background-color: #65be6a;
}


.map_wrap{
  width: 100%;
  height:100%;
  position:relative;
  overflow:hidden; 
}

.map {
  width: 100%;
  height: 100%;
  padding: 10%;
}


.map_wrap .custom_typecontrol{
  background-color: #fff;
  position:absolute;
  top:10px;
  right: 23px;
  align-content: flex-end;
  overflow:hidden;
  width:auto;
  height:auto;
  margin:0;
  padding:0;
  z-index:1;
  border: none;
  font-size:0.6rem;
  cursor: pointer;
  box-shadow: 10px 10px 10px -5px rgba(25, 42, 70, .2);

  transition: 0.2s;
}

.map_wrap .custom_typecontrol:hover{
  transform: translate(0, -3px);
}
</style>