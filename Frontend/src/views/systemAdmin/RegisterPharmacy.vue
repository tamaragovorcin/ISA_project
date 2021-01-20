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
            </span>
              <span  style="float:right;margin:15px">
                     <b class="tab"></b>    
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
                </span>
        </div>
        <div style = "background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
                       <h3 style="color: #0D184F;margin-bottom:20px">Register new pharmacy</h3>

                <div class="form-group">
                    <label>Pharmacy name:</label>
                    <input type="email" class="form-control" v-model="pharmacyName" placeholder="Enter name">
                </div>
                <div class="form-group">
                    <label>Address:</label>
                </div>
                <div class="form-group">
                    <label>Country:</label>
                    <input type="text" class="form-control" v-model="country" placeholder="Enter country">
                </div>
                <div class="form-group">
                    <label>Town:</label>
                    <input type="text" class="form-control" v-model="town" placeholder="Enter town">
               </div>
                <div class="form-group">
                    <label>Street:</label>
                    <input type="text" class="form-control" v-model="street" placeholder="Enter street">
               </div>
                <div class="form-group">
                    <label>Number:</label>
                    <input type="number" class="form-control" v-model="number" placeholder="Enter number">
               </div>
                <div class="form-group">
                    <label>Postal code:</label>
                    <input type="text" class="form-control" v-model="postalCode" placeholder="Enter postal code">
               </div>
                <div class="form-group">
                    <label>Consulting price:</label>
                    <input type="text" class="form-control" v-model="consultingPrice" placeholder="Enter consulting price">
               </div>
                <button v-on:click = "register" class="btn btn-primary">Register</button>
           
        </div>

    </div>
</template>

<script>
export default {

  data() {
    return {
        pharmacyName : "",
        town : "",
        street : "",
        number : "",
        postalCode : "",
        country : "",
        consultingPrice : 0,
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
      register : function(){
          const addressInfo ={
              town : this.town,
              street : this.street,
              number : this.number,
              postalcode : this.postalCode,
              country : this.country
          }
            const pharmacyInfo ={
                
                pharmacyName : this.pharmacyName,
                address : addressInfo,
                consultingPrice : this.consultingPrice

            }
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

            this.axios.post('/pharmacy/register',pharmacyInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Pharmacy is successfully registred!");
                        console.log(response.data);
                })
                .catch(response => {
                       alert("Please try later.");
                        console.log(response);
                 });    
      },
      logOut : function(){
            localStorage.removeItem('token');
           window.location.href = "/login";
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


  