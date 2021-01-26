<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">

                <b-dropdown id="ddCommodity" name="ddCommodity" text="New registration" 
                               class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                    <b-dropdown-item v-on:click = "registerPharmacy">Pharmacy</b-dropdown-item>
                    <b-dropdown-item v-on:click = "registerPharmacyAdmin">Pharmacy admin</b-dropdown-item>
                    <b-dropdown-item v-on:click = "registerDermatologist">Dermatologist</b-dropdown-item> 
                    <b-dropdown-item v-on:click = "registerSupplier">Supplier</b-dropdown-item>
                    <div v-if = "allowSystemAdminRegistration">
                         <b-dropdown-item v-on:click = "registerSystemAdmin">System admin</b-dropdown-item>
                    </div>
                     <div v-if = "notallowSystemAdminRegistration">
                         <b-dropdown-item v-on:click = "registerSystemAdmin" disabled>System admin</b-dropdown-item>
                    </div>                    
                 </b-dropdown> 
                 <b class="tab"></b>   
                 <button class = "btn btn-link btn-lg" v-on:click = "addMedicine">Add medicine</button>
                 <b class="tab"></b>   
                 <button class = "btn btn-link btn-lg" v-on:click = "defineLoyaltyProgram">Loyalty program</button>
            </span>
              <span  style="float:right;margin:15px">
                     <b class="tab"></b>    
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
                </span>
        </div>
       
    </div>
</template>

<script>
export default {

  data() {
    return {
      accountInformation :null,
      allowSystemAdminRegistration : false,
      notallowSystemAdminRegistration : false
    }
  },

  methods:{
      registerPharmacy: function() {
            window.location.href = "/registerPharmacy";
      },
      registerPharmacyAdmin: function() {
            window.location.href = "/registerPharmacyAdmin";
      },
      registerDermatologist: function() {
           window.location.href = "/registerDermatologist";
      },
      registerSupplier: function() {
           window.location.href = "/registerSupplier";
      },
      registerSystemAdmin: function() {
           window.location.href = "/registerSystemAdmin";
      },
      logOut : function(){
          localStorage.removeItem('token');
          window.location.href = "/login";
      },
      addMedicine : function() {
           window.location.href = "/addMedicine";
      },
      defineLoyaltyProgram : function(){
          window.location.href = "/loyaltyProgram";
      }
      
},
 mounted() {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/systemAdmin/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.accountInformation=response.data;
               if(this.accountInformation.mainAdmin) {
                      this.allowSystemAdminRegistration = true;
                      this.notallowSystemAdminRegistration = false;
               }
               else{
                    this.allowSystemAdminRegistration = false;
                    this.notallowSystemAdminRegistration = true;
               }
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });
    }
}
</script>

<style>

</style>


  