<template>
<div>
<router-link :to="{ path: '/pharmacyProfile/'+id}" v-slot="{href, navigate}">
                                    <button class = "btn btn-info" :href="href" @click="navigate"  elevation="1">← Go back to pharmacy profile</button>
                              </router-link>
  <div id="map">
    <div id="mapContainer" style="height:700px;width:100%" ref="hereMap"></div>
  </div>
</div>
</template>

<script>
export default {
  name: "HereMap",
  props: {
    center: Object
    // center object { lat: 40.730610, lng: -73.935242 }
  },
  data() {
    return {
      platform: null,
      apikey: "sDjdDihEOSrkRErqE-olId_Mt6uMwB35dpNuycSBCO4",
      address : "Mise Dimitrijevica 5 Novi Sad",
      addressInfo : "",
      lat :0,
      long : 0,
      id : this.$route.params.id,
      // You can get the API KEY from developer.here.com
    };
  },
  async mounted() {
    // Initialize the platform object:
    const platform = new window.H.service.Platform({
      apikey: this.apikey,
    });
    this.platform = platform;

        this.axios.get('/pharmacy/address/'+this.id)
        .then(response => {
                this.addressInfo = response.data;
                this.address = this.addressInfo.country+" "+this.addressInfo.town+" "+this.addressInfo.street+" "+this.addressInfo.number;
                this.axios.get('https://geocoder.api.here.com/6.2/geocode.json?app_id=aJx1PxrXFwpMDT0M30rJ&app_code=am23BxvdgXkXf2c15NUZgw&searchtext='+this.address)
         .then(response => {
             console.log(response.data);
                    this.lat =  response.data.Response.View[0].Result[0].Location.DisplayPosition.Latitude;
                    this.long = response.data.Response.View[0].Result[0].Location.DisplayPosition.Longitude;
                    this.initializeHereMap();
                })
         }).catch(res => {
                alert("NOT OK");
                console.log(res);
        });
       
                  

    
       
    
  },
  methods: {
    initializeHereMap() { // rendering map

      const mapContainer = this.$refs.hereMap;
      const H = window.H;
      // Obtain the default map types from the platform object
      var maptypes = this.platform.createDefaultLayers();

      // Instantiate (and display) a map object:
      var map = new H.Map(mapContainer, maptypes.vector.normal.map, {
        zoom: 15,
        center: { lat: this.lat, lng: this.long }
        //center object { lat: this.lat, lng: this.long }
      });

      addEventListener("resize", () => map.getViewPort().resize());

      // add behavior control
      new H.mapevents.Behavior(new H.mapevents.MapEvents(map));
    let marker = new H.map.Marker({ lat: this.lat, lng: this.long })
    map.addObject(marker);
      // add UI
      H.ui.UI.createDefault(map, maptypes);
      // End rendering the initial map
    }
  }
};
</script>

<style scoped>
#map {
  width: 60vw;
  min-width: 360px;
  text-align: center;
  margin: 5% auto;
  background-color: #ccc;
}
</style>