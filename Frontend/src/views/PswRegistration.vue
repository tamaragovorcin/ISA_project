<template>
<div style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
  background-size: 125% 80%;  height: 800px">

    <div style="background: #D8D8DC; height: 3cm;"></div>   
    <h3 style="color: red; font-size: 35px;font-weight:bold">Choose pharmacy</h3>

    <div style="background: #0D184F; height: 3cm;">

      <nav class="navbar navbarB" >
        <div class="container-fluid">
          <div v-for="ph in listaPharmacys"   v-bind:key="ph">
          <ul class="nav navbar-nav">
            <li> <p style="color: #DED7D7;  margin-top: 0.7cm; font-size: 30px;" v-on:click="showPharmacy($event, ph)"> {{ph.name}} </p> </li>
            <li class="divider-vertical"></li>
          </ul>
          </div> 
        </div>
	</nav>   
    </div>
    <div style=" height: 2cm;"></div>

    <div v-if = "showTable">

    <table style=" border-radius: 5%; border:1;  align: center; width: 15cm; background: #FFFFFF; margin: 0;position: absolute;top: 50%;left: 50%;transform: translate(-50%, -50%);">
      <th style="width: 7cm"><div class="form-group">
          <label style="font-size: 35px; font-weight:bold;color:red"><span>Pharmacy's name</span></label>
        </div>
      </th>
      <th style="width: 7cm"><div class="form-group"><label style="font-size: 35px; font-weight:bold;color:green"><span>City</span></label></div>
      </th>
      <th style="width: 7cm">
        <div class="form-group"><label style="font-size: 35px; font-weight:bold"><span>Api key</span></label>	</div>
      </th>

      <tr><td><div class="form-group">
        <label style="font-size: 30px"><span>{{pharmacy.name}} </span></label></div></td>
           <td><div class="form-group">
               <label style="font-size: 30px"><span>{{pharmacy.town}} </span></label>	</div></td>
           <td><div class="form-group">
               <label style="font-size: 30px"><span>{{pharmacy.apiKey}} </span></label></div></td></tr>
      </table>
    </div>


</div>
</template>

<script>
export default {
  data() {
    return {
       listaPharmacys: [],
       showTable: false,
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
    this.showTable = true;
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

<style>
  .tab {
          margin-left: 2cm
          }
</style>