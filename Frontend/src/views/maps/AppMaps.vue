<template>
  <div id="location">
    <div class="cell cell-map">
      <MapContainer :geojson="geojson" v-on:select="selected = $event"></MapContainer>
    </div>
    <div class="cell cell-edit">
      <Edit :geojson="geojson" v-on:change="geojson = $event"></Edit>
    </div>
    <div class="cell cell-inspect">
      <Inspect :feature="selected"></Inspect>
    </div>
  </div>
</template>

<script>
  import MapContainer from './MapContainer'
  import Edit from './Edit'
  import Inspect from './Inspect'
  export default {
    name: 'App',
    components: {
      Inspect,
      Edit,
      MapContainer
    },
    data: () => ({
      selected: undefined,
      geojson: {
        type: 'Feature',
        properties: {
          name: 'default object',
          quality: 'top'
        },
        geometry: {
          type: 'Polygon',
          coordinates: [
            [
              [
                2192888.870569583,
                5580331.9034303995,
              ],
              [
                2192889.870569583,
                5580332.9034303995,
              ],
              
            ]
          ]
        }
      }
    }),
    mounted(){
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

        this.axios.get('/map',{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
           var first = response.data.first;
           this.geometry.coordinates.push(first);

           var second = response.data.second;
           this.dataset2.push(second);

           var third = response.data.third;
           this.dataset2.push(third);

           var fourth = response.data.fourth;
           this.dataset2.push(fourth);
       
         }).catch(res => {
                alert("You are not logged in.");
                console.log(res);
        });

    }  
    
    }
</script>

<style>
  html, body {
    height: 100%;
    margin: 0;
  }
  #location {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    height: 100%;
    display: grid;
    grid-template-columns: 100vh;
    grid-auto-rows: 1fr;
    grid-gap: 1rem;
    padding: 1rem;
    box-sizing: border-box;
  }
  .cell {
    border-radius: 4px;
    background-color: lightgrey;
  }
  .cell-map {
    grid-column: 1;
    grid-row-start: 5;
    grid-row-end: 40;
  }
  .cell-edit {
    grid-column: 2;
    grid-row-start: 5;
    grid-row-end: 25;
  }
  .cell-inspect {
    grid-column: 2;
    grid-row-start: 25;
    grid-row-end: 40;
  }
</style>