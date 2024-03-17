<template>
    <div>
      <p v-if="location">Latitude: {{ location.latitude }}, Longitude: {{ location.longitude }}</p>
      <p v-else>Loading...</p>
    </div>
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
  