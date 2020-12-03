<template>
<div style="background-image: url(https://previews.123rf.com/images/mongstock/mongstock1712/mongstock171200069/91420426-colorful-medicine-pharmacy-on-pink-background-.jpg);background-repeat: no-repeat;
  background-size: 125% 80%;  height: 800px">
    <div class = "container w-25 p-3 text-danger">
        <div>
            <h3 style="font-size: 35px;font-weight:bold">Choose pharmacy</h3>
        </div>
        <div class="container w-100 mb-3">

            <div class="row d-flex flex-row">
                
                <div class="col-md-6">
                    <div v-for="ph in listaPharmacys"   v-bind:key="ph"> 
            
                    <p v-on:click="showPharmacy($event, ph)"> {{ph.name}} </p>
                </div>
            </div>
            <div class="row">
                <div class="col-6 col-md-4">
                   <label style = "font-family: Arial">Name:    {{pharmacy.name}} </label>
                </div>
                <div class="col-6 col-md-4">
                    <label>Town:   {{pharmacy.town}} </label>
                </div>
                <div class="col-md-4">
                     <label>Api key:  {{pharmacy.apiKey}} </label>
                </div>
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