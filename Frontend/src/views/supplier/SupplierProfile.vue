<template>
  <div v-if="isAuthorized" id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
             <span style="float: left; margin: 15px;">
                  <a  class = "btn btn-secondary" href = "/supplierProfileInfo">My profile</a>
                  <strong class="tab"></strong>  
                  <a  class = "btn btn-secondary" href = "/supplierReviewsActiveTenders">Active tenders</a>
                  <strong class="tab"></strong>  
                  <a  class = "btn btn-secondary" href = "/myOffers">My offers</a>   
                  <strong class="tab"></strong>  
                  <a  class = "btn btn-secondary" href = "/supplierAddMedication">Add medication</a>   
                  <strong class="tab"></strong>  
                  <a  class = "btn btn-secondary" href = "/supplierMyMedications">My medications</a>   
            </span>
            <span  style="float:right;margin:15px">
                    <strong class="tab"></strong>  
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
            </span>
        </div>
       
    </div>
</template>

<script>
export default {

  data() {
    return {
      isAuthorized : false
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
        this.axios.get('/supplier/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
                this.patient = response.data;
                this.isAuthorized = true;
         }).catch(res => {
                      this.isAuthorized =  false;
                       alert("Please log in first!");
                                 window.location.href = "/login";
                                 console.log(res);
                
                 });
    }
}
</script>