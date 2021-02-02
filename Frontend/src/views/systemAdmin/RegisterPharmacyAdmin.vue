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
                  <strong class="tab"></strong>  
                 <button class = "btn btn-link btn-lg" v-on:click = "addMedicine">Add medication</button>
                  <strong class="tab"></strong>  
                 <button class = "btn btn-link btn-lg" v-on:click = "medicationSearch">Medications</button>
                  <strong class="tab"></strong>  
                 <button class = "btn btn-link btn-lg" v-on:click = "defineLoyaltyProgram">Loyalty program</button>
                  <strong class="tab"></strong>  
                 <button class = "btn btn-link btn-lg" v-on:click = "showComplaints">Complaints</button>
            </span>
            <span  style="float:right;margin:15px">
                  <strong class="tab"></strong>  
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
             </span>
        </div>
        <div style="background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
            <h3 style="color: #0D184F">Register new pharmacy admin</h3>
                
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Choose pharmacy for pharmacy admin:</label>
                            <div class="form-row">
                                 <div class="form-group col-md-6">
                                    <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose pharmacy"
                                        class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                                            <b-dropdown-item v-for="pharmacy in this.pharmacies"  v-on:click = "pharmacyIsSelected($event, pharmacy)" v-bind:key="pharmacy.id"> {{ pharmacy.pharmacyName }}</b-dropdown-item>
                                    </b-dropdown> 
                                 </div>
                                <div class="form-group col-md-6 ">
                                    <label>{{this.pharmacyName}}</label>
                                </div>
                                    
                            </div>
                        </div>
                        
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Name:</label>
                        <input type="text" class="form-control" v-model="name2" placeholder="Enter name">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Surname:</label>
                        <input type="text" class="form-control" v-model = "surname" placeholder="Enter surname">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Email:</label>
                        <input type="email" class="form-control" v-model="email" placeholder="Enter email">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Phone number:</label>
                        <input type="text" class="form-control" v-model="phoneNumber" placeholder="Enter phone number">
                        </div>
                    </div>
                      <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Country:</label>
                        <input type="text" class="form-control" v-model="country" placeholder="Enter country">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Town:</label>
                        <input type="text" class="form-control" v-model="town" placeholder="Enter town">
                        </div>
                    </div>
                     <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Street:</label>
                        <input type="text" class="form-control" v-model="street" placeholder="Enter street">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Number:</label>
                        <input type="number" class="form-control" v-model="number" placeholder="Enter number">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Postal code:</label>
                        <input type="text" class="form-control" v-model="postalCode" placeholder="Enter postal code">
                        </div>
                       
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Password:</label>
                        <input type="password" class="form-control" v-model="password" placeholder="Enter password">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Repeat password:</label>
                        <input type="password" class="form-control" v-model="repeatPassword" placeholder="Repeat password">
                        </div>
                    </div>
                   
                    <button class="btn btn-primary btn-lg" v-on:click = "register">Register</button>
                    <div style="height:30px;"></div>
        </div>
    </div>
</template>

<script>
export default {

  data() {
    return {
          name2 : "",
          surname : "",
          email : "",
          password : "",
          repeatPassword : "",
          phoneNumber : "",
          town : "",
          street : "",
          number : "",
          postalCode : "",
          country : "",
          accountInformation :null,
          allowSystemAdminRegistration : false,
          notallowSystemAdminRegistration : false,
          pharmacies : [],
          selectedPharmacy : null,
          pharmacyName : ""
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
           window.location.href = "/systemAdminProfile";
      },
      addMedicine : function() {
           window.location.href = "/addMedicine";
      },
      medicationSearch : function() {
          window.location.href = "/systemAdminMedicationSearch";
      },
      register : function(){
            const addressInfo ={
              town : this.town,
              street : this.street,
              number : this.number,
              postalCode : this.postalCode,
              country : this.country
          }
            const userInfo ={
                email : this.email,
                password : this.password,
                firstname : this.name2,
                surname : this.surname,
                phonenumber : this.phoneNumber,
                address : addressInfo,
                pharmacy : this.selectedPharmacy
            }
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

            this.axios.post('/pharmacyAdmin/register',userInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Pharmacy admin is successfully registred!");
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
      },
      pharmacyIsSelected : function(event, pharmacy) {
            this.selectedPharmacy = pharmacy;
            this.pharmacyName = this.selectedPharmacy.pharmacyName;
            console.log(this.selectedPharmacy)
      },
      defineLoyaltyProgram : function(){
          window.location.href = "/loyaltyProgram";
      },
       showComplaints : function() {
            window.location.href = "/complaints";
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

        this.axios.get('/pharmacy/all',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.pharmacies=response.data;
         }).catch(res => {
                       alert("Please try again later.");
                        console.log(res);
                 });

        
    }
}
</script>

<style>

</style>


  