<template>
  <div style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
    background-size: 125% 80%;  height: 800px">

        
      <h3 style="color: #0D184F; font-size: 35px;font-weight:bold">Choose pharmacy</h3>

          <div style="background: #0D184F; height: 3cm;">
            

            <nav class="navbar navbarB" style="margin-top:250px">
                <div class="container-fluid">
                  <div v-for="ph in listaPharmacys"   v-bind:key="ph">
                      <ul class="nav navbar-nav">
                        <li>  <router-link :to="{ path: '/concretePharmacy/'+ph.apiKey}" v-slot="{href, navigate}">
                                    <button class = "button1" :href="href" @click="navigate"  elevation="1">{{ph.name}}</button>
                              </router-link>
                        </li>
                        <li class="divider-vertical"></li>



                      </ul>
                  </div>
                  


                </div>
            </nav>

                  
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
 
 .klasa{
    position: absolute;
    top: 50%;
    left: 50%;
    -moz-transform: translateX(-50%) translateY(-50%);
    -webkit-transform: translateX(-50%) translateY(-50%);
    transform: translateX(-50%) translateY(-50%);
 }
  #header-desc {
    font-size: 1.5rem;
    color: #fff;
  }
  #paragraph{
    height: 10vh;
  }
  
  .button {
        background-color: #0D184F;
        border: double;
        color: white;
        padding: 15px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 25px;
        margin: 4px 2px;
        cursor: pointer;
        color : white;
    }
  .button1 {
        background: #939393;
        color: #ffffff;
        border: 8px;
        border-radius: 10%;
        font-size: 18px;
        height: 1.5cm;
        width: 150px;
        font: bold;}
  
    
</style>

<style>
  .tab {
          margin-left: 2cm
          }
</style>