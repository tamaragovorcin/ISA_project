<template>
  <div  id="home">
    <div id="header">
      <div id="header-text">
        <div id="header-title">
          <p>Choose pharmacy </p>
           
          <div v-for="ph in listaPharmacys"   v-bind:key="ph"> 
            
            <p v-on:click="showPharmacy($event, ph)"> {{ph.name}} </p>


          </div>

          <div>
              <p id="paragraph"></p>
          </div>


           <div>
             <label>Name: {{pharmacy.name}} </label>
           </div>
            
           <div>
             <label>Town:{{pharmacy.town}} </label>
           </div>
             
           <div>
             <label>Api key:{{pharmacy.apiKey}} </label>
           </div>



           
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
       listaPharmacys: [],
       pharmacy: {
         name : "",
         town : "",
         apiKey: ""

       }

    }
  },

beforeMount() {
      this.axios.get('/pharmacy')
      .then(response => {
            this.listaPharmacys= response.data;

      })

},
     
methods:{
  showPharmacy : function(event, p){
    this.pharmacy = p;
  }

}




}
</script>
<style scoped>
  #mainbtn {
    color: #fff;
    background-color: #0000CD
  }

 
  #title {
    font-weight: 500;
    font-size: 3.5rem;
    color: #fff;
  }
 
 
  #header-desc {
    font-size: 1.5rem;
    color: #fff;
  }
  #paragraph{
    height: 10vh;
  }
  
</style>