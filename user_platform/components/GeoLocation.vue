<template>
  <div class="bg-white rounded-lg px-6 py-3">
    <div class="font-semibold text-xl text-left py-3">현재 검색 위치</div>

    <div class="flex max-sm:flex-col items-center">
      <div id="map" style="width:100%; height: 40vh;"></div>
      <div>{{ strLoc.road_address?.address_name }}</div>
    </div>

  </div>
</template>

<script setup>
const location = ref(null);
const strLoc = ref({})

const cordToAddress = (lon, lat) => {
  $fetch(`https://dapi.kakao.com/v2/local/geo/coord2address.json?x=${lon}&y=${lat}`, {
    method: 'GET',
    headers: {
      'Authorization': `KakaoAK ${useRuntimeConfig().public.kakaoRestApiKey}`
    },
  }).then((res) => {
    console.log(res.documents)
    strLoc.value = res.documents[0]
  })
};

const getLocation = () => {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(
      position => {
        location.value = {
          latitude: position.coords.latitude,
          longitude: position.coords.longitude
        };
        var container = document.getElementById('map');
        var options = {
          center: new kakao.maps.LatLng(location.value.latitude, location.value.longitude),
          level: 3,
          draggable: false
        };

        var map = new kakao.maps.Map(container, options);
        cordToAddress(location.value.longitude, location.value.latitude)
      },
      error => {
        console.error('Error getting location:', error.message);
      }
    );
  } else {
    console.error('Geolocation is not supported by this browser.');
  }
};

onMounted(getLocation);
</script>
