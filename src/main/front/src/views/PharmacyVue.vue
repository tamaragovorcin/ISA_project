<template>
  <div  id="home">
    <div id="header">
      <div id="header-text">
        <div id="header-title">
          <p>Choose your pharmacy </p>
            <div v-for="ph in listaPharmacys" v-bind:key="ph"> 
              <router-link :to="{ path: '/concretePharmacy/'+ph.apiKey}" v-slot="{href, navigate}">
                <v-btn  class="btn btn-success" :href="href" @click="navigate"  elevation="1"> {{ph.name}} </v-btn>
              </router-link>
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
 
  #header {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    height: 100vh;
    background: none;
    background-color: #87CEEB;
    
  }  
  #header-desc {
    font-size: 1.5rem;
    color: #fff;
  }
  
</style>