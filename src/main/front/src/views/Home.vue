<template>
  <div style="background-image: url(https://previews.123rf.com/images/mongstock/mongstock1712/mongstock171200069/91420426-colorful-medicine-pharmacy-on-pink-background-.jpg);background-repeat: no-repeat;
  background-size: 125% 80%;  height: 800px">
    <div class="container text-danger">
     <div class = "row"  align = "center">
       
            
            <h3 align = "center" style="font-size: 35px;font-weight:bold" > Pharmaceutical information system</h3>
        
      </div>
      
      
      <div class = "row text-danger  w-25 p-3">
        

            <router-link to="/pswregistration" v-slot="{href, navigate}">
            
              <button class = "btn btn-danger" :href="href" @click="navigate"  elevation="1">Get your API key</button>
              
            </router-link>
            <router-link to="/pharmacyVue" v-slot="{href, navigate}">
            
             <button class = "btn btn-danger" :href="href" @click="navigate"  elevation="1">Pharmacy</button>
             
            </router-link>
      </div>    
   </div>
  </div>
</template>

<script>
export default {

  data() {
    return {
    }
  }

}
</script>

<style scoped>
  
  
</style>