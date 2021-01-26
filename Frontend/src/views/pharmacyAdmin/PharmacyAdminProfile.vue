<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
             <span style="float: left; margin: 15px;">
                <a  class = "btn btn-secondary" href= "/isaHomePage">Home</a>
                <b class="tab"></b>    
                <a  class = "btn btn-secondary" href = "/pharmacyAdminProfile">My profile</a>
                <b class="tab"></b>    
                <a  class = "btn btn-secondary" href = "/myPharmacy">My Pharmacy</a>
                 <b class="tab"></b>    
                 <a  class = "btn btn-secondary" href = "/phAdminProfileUpdate">Update profile</a>
                 
                <b class="tab"></b>    
                <a  class = "btn btn-secondary" href = "/addPharmacist">Add pharmacist</a>    
                <b class="tab"></b> 
                 <a  class = "btn btn-secondary" href = "/pharmacyPharmacists">Our pharmacists</a> 
                  <b class="tab"></b>  
                <a  class = "btn btn-secondary" href = "/pharmacyDermatologists">Our dermatologists</a>      
                <b class="tab"></b> 
                <a   class = "btn btn-secondary" href = "/pharmacyMedications">Medications</a>
                        <b class="tab"></b>    
                <a  class = "btn btn-secondary" href = "/actionsAndBenefits">Actions and benefits</a>
                        <b class="tab"></b>    
                <a   class = "btn btn-secondary" href="/order">Orders</a>
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
       showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      showMyProfile: function(){

      },
       hideModal() {
        this.$refs['my-modal'].hide()
      },
       showOrderForm : function(){
          window.location.href = "/order";

      },
       logOut : function(){
           window.location.href = "/login";
      },
      sendComplaint : function(){

      },
      showMyPharmacy : function (){
          window.location.href = "/myPharmacy"
      },
      addNewPharmacist : function(){
        window.location.href = "/addNewPharmacist";
      }
}
}
</script>

<style>

</style>


  