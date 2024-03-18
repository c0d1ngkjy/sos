<template>
  <div>
    <!-- <p v-if="location">Latitude: {{ location.latitude }}, Longitude: {{ location.longitude }}</p> -->
  </div>
  <div id="map" style="width:30%; height: 30vh;"></div>

</template>

<script>
export default {
  data() {
    return {
      location: null
    };
  },
  mounted() {
    this.getLocation();

  },
  methods: {
    getLocation() {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          position => {
            this.location = {
              latitude: position.coords.latitude,
              longitude: position.coords.longitude
            };
            var container = document.getElementById('map');
            var options = {
              center: new kakao.maps.LatLng(this.location.latitude, this.location.longitude),
              level: 3
            };

            var map = new kakao.maps.Map(container, options);
          },
          error => {
            console.error('Error getting location:', error.message);
          }
        );
      } else {
        console.error('Geolocation is not supported by this browser.');
      }
    }
  }
};


</script>