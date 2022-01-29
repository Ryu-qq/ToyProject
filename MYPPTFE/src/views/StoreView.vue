<template>
  <div class ="body">

      <div class ="search">
          <input class="search-input" 
                  type="text" placeholder="검색어를 입력하세요"/>
                  <!-- @keyup.enter="search"/> -->
            
            <div class ="search-input-options">
              <select v-model="form.category" class="search-select">
                <option selected>카테고리</option>
                <option
                  v-for="(category, index) in categories"
                  :key="index">
                  {{ category }}
                </option>
              </select>

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
      customMap:{},
      categories: [
        "한식",
        "중식",
        "일식",
        "양식",
        "분식",
        "구이",
        "회/초밥",
        "포차/가맥",
        "기타",
      ],
      form: {
        keyword: "",
        category: "카테고리",
      },
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
      });
    
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
      let container = document.getElementById("map"); 
      this.options = {
        
        center: new kakao.maps.LatLng(37.570137, 126.977018), 
        level: 6
      };
      
      this.customMap = new kakao.maps.Map(container, this.options);
    },

    setCurrentPos(){
      
      let moveLatLon = new kakao.maps.LatLng(this.gps_lat  , this.gps_lng);

      return this.customMap.setCenter(moveLatLon);
    },
  },

      

}
</script>


<style scoped>

.body{
    display: flex;
    flex-direction: column;
    align-content: space-between;
    justify-content: center;
    width: 100%;
    height: 700px;
    padding: 70px 6%
}

.search{
  display: flex;
  width: 100%;
  margin-bottom: 20px;
}

.search-input{
   margin-right:12px;
   width: 100%;
   border: 3px solid #f5f5f5;
   border-radius: 10px;
}

.search-input:focus{
   outline: none;
   border-color: #A6A6A6;

}

.search-input-options{
  display: flex;
  justify-content: space-between;
}

.search button{
  width:80px;
  padding: 7px 18px;
  background-color: #fff;
  border: 3px solid #f5f5f5 ;
  border-radius: 10px;
  cursor:pointer;
}

.search button:hover{
  background-color: #A6A6A6;
}

.search-select{
  border: 3px solid #f5f5f5;
  border-radius: 10px;
  margin-right: 10px;
  padding: 0 6px;
  
}

.search-select:hover{
  border: 3px solid #A6A6A6;
}

.search-select:focus{
  border: 3px solid #A6A6A6;
  outline:none;
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