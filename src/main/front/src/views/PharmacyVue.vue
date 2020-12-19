<template>
<div style="background-image: url(https://previews.123rf.com/images/mongstock/mongstock1712/mongstock171200069/91420426-colorful-medicine-pharmacy-on-pink-background-.jpg);background-repeat: no-repeat;
  background-size: 125% 80%;  height: 800px">
 <div class="container">
  <div class="row">
    <div class="col text-dark">
        <h4 class="p-3 mb-2 text-danger" >Choose your pharmacy</h4>
    </div>
    <div class="col text-dark"  v-for="ph in listaPharmacys" v-bind:key="ph"> 
              <router-link :to="{ path: '/concretePharmacy/'+ph.apiKey}" v-slot="{href, navigate}">
                <button class = "btn btn-danger" :href="href" @click="navigate"  elevation="1">{{ph.name}}</button>
               
              </router-link>
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