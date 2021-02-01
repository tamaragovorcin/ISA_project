<template>
  <div style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
    background-size: 125% 80%;  height: 1500px">

        
      

          <div style="background: #0D184F; height: 3cm;">
            
    <h3 style="color: white; font-size: 35px;font-weight:bold;margin:25px;">URGENT ORDERS</h3>

                  
          </div>
          
          <div style=" height: 3cm;">            

                  
          </div>
         
    <div style="background: lightBlue; height: 7cm;">
            
    <h3 style="color: #0D184F; font-size: 35px;font-weight:bold;margin:25px;">New urgent orders has arrived from: <b>Health clinic</b></h3>
          <table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Requsted medicine</th>
      <th scope="col">Quantity</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row"></th>
      <td>Panadol Forte</td>
      <td>10</td>
      <td></td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Paracetamol</td>
      <td>7</td>
      <td></td>
    </tr>
    <tr v-if="!odobreno" style="background-color:lightGreen">
      <th scope="row">3</th>
      <td>Defrinol</td>
      <td>15</td>
      <td><button class = "btn btn-warning" v-on:click="odobri">Recieved</button></td>

    </tr>
    <tr v-if="odobreno" style="background-color:lightBlue">
      <th scope="row">3</th>
      <td>Defrinol</td>
      <td>15</td>

    </tr>
  </tbody>
</table>
                  
          </div>

   
      <div style=" height: 3cm;">            

                  
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
         apiKey: "",

       },
          odobreno : false


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
  },
  prikazi : function(){
     this.axios.get('/probni')
                .then(response => {
                   alert("PROBNI");
                   console.log(response);
                })
                .catch(response => {
                       alert("Nije prosao probni");
                        console.log(response);
                 });
  },
  odobri : function(){
    this.odobreno = true;
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