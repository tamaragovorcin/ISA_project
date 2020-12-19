<template>
<div style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
  background-size: 125% 80%;  height: 800px">

    <div style="background: #D8D8DC; height: 3cm;">
	    
     <h3 style="color: #0D184F; font-size: 35px;font-weight:bold">Choose pharmacy</h3>

    <div style="background: #0D184F; height: 3cm;">
	    
		     <nav class="navbar navbarB" >
		      <div class="container-fluid">

			        <ul class="nav navbar-nav">
                <div v-for="ph in listaPharmacys"   v-bind:key="ph">
				        <li> <p v-on:click="showPharmacy($event, ph)"> {{ph.name}} </p></li>
				        <li class="divider-vertical"></li>

		        	</ul>

		      </div>
	        </nav>
		
    </div>

    <div>
      <form>

        	<div class="form-group">
   						<label><span>Pharmacy's name: </span></label>
               <label><span>{{pharmacy.name}} </span></label>
 					</div>
      </form>



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