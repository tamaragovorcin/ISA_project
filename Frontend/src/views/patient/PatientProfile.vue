ines (39 sloc)  1.61 KB
  
<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                    <a  class = "btn btn-secondary" href= "/isaHomePage">Home</a>
                    <b class="tab"></b>                
                    <a  class = "btn btn-secondary" href= "/showPharmaciesPatient">Pharmacies</a>
                    <b class="tab"></b>     
                    <a  class = "btn btn-secondary" href= "/myProfilePatient">My profile</a>
                    <b class="tab"></b>                
                    <a  class = "btn btn-secondary" href= "/patientComplaint">Write complaint</a>
                    <b class="tab"></b>   
                    <a  class = "btn btn-secondary" href= "/subscriptionsToPharmacies">My subscriptions</a>
                    <b class="tab"></b>   
                    <a  class = "btn btn-secondary" href= "/eRecipes">ERecipes</a>
                    <b class="tab"></b> 
            </span>
              <span  style="float:right;margin:15px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

                </span>

        </div>
</div>
</template>

<script>
export default {

  data() {
    return {
     
    }
  },
  
  methods:{
      logOut : function(){
          localStorage.removeItem('token');
          window.location.href = "/login";
      },
},
    mounted() {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/patient/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
                this.patientInfo = response.data;
                console.log(response.data);
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });

    }
}
</script>

<style>

</style>


  