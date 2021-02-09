<template>
  <div  v-if="loggedIn"  id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
             <span style="float: left; margin-top: 15px;">
                              <b-dropdown id="ddCommodity" name="ddCommodity" text="My profile" 
                                              class = "btn btn-link btn-lg">
                                    <b-dropdown-item href = "/pharmacyAdminProfile">Profile</b-dropdown-item>
                                    <b-dropdown-item href = "/phAdminProfileUpdate">Update profile</b-dropdown-item>      
                                </b-dropdown>        
                       <strong class="tab"></strong>  

                                    <router-link :to="{ path: '/pharmacyProfile/'+pharmacy.id}" v-slot="{href, navigate}">
                                                <button class = "btn btn-secondary" :href="href" @click="navigate"  elevation="1">My pharmacy profile</button>
                                    </router-link>
                   
                          <strong class="tab"></strong>  

                                  <b-dropdown id="ddCommodity" name="ddCommodity" text="Pharmacists" 
                                                class = "btn btn-link btn-lg">
                                      <b-dropdown-item href = "/pharmacyPharmacists">Our pharmacists</b-dropdown-item>
                                      <b-dropdown-item href = "/addPharmacist">Add new pharmacist</b-dropdown-item>      
                                  </b-dropdown> 
                          <strong class="tab"></strong>  
                                  <b-dropdown id="ddCommodity" name="ddCommodity" text="Dermatologists" 
                                                  class = "btn btn-link btn-lg">
                                        <b-dropdown-item href = "/pharmacyDermatologists">Our dermatologists(Add new)</b-dropdown-item>
                                        <b-dropdown-item href = "/examinationTerms">Examination terms</b-dropdown-item>      
                                    </b-dropdown>                 
                            <strong class="tab"></strong>  
                            <a   class = "btn btn-secondary" href = "/pharmacyMedications">Medications</a>
                            <strong class="tab"></strong>  
                            <a   class = "btn btn-secondary" href = "/pharmacyAdminMedicationSearch">Medications in system</a>
                            <strong class="tab"></strong>  
                            <a  class = "btn btn-secondary" href = "/actionsAndBenefits">Actions and benefits</a>
                            <strong class="tab"></strong>  
                            <b-dropdown id="ddCommodity" name="ddCommodity" text="Orders" 
                                                  class = "btn btn-link btn-lg">
                                        <b-dropdown-item href = "/order">Preview orders and offers(Add new)</b-dropdown-item>
                                        <b-dropdown-item href = "/editOrder">Edit/remove order</b-dropdown-item>      
                                    </b-dropdown>                             
                                    <strong class="tab"></strong>  
                            <a   class = "btn btn-secondary" href="/holidayRequests">Holiday/absence requests</a>
                             <strong class="tab"></strong>  
                            <b-dropdown id="ddCommodity" name="ddCommodity" text="Graphical reviews" 
                                                  class = "btn btn-link btn-lg">
                                        <b-dropdown-item href = "/examinationGraphics">Examinations</b-dropdown-item>
                                        <b-dropdown-item href = "/medicationGraphics">Medication consumption</b-dropdown-item> 
                                        <b-dropdown-item href = "/incomeGraphics">Income</b-dropdown-item>      
                                    </b-dropdown>     
            </span>
              <span  style="float:right;margin:15px">
                   
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
                </span>
        </div>
       <h5 style="color:#0D184F;font-size:25px;font-weight:bold;background:whitesmoke;width:15%;" align = "left">Mark of your pharmacy:<div style="font-size:35px;"> {{pharmacy.mark}}</div></h5>
        <h3 style="color:#0D184F;font-size:45px;font-weight:bold;" align = "center"><u>Admin of {{pharmacy.pharmacyName}}</u></h3>


        <div style="background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
            <h3 style="color: #0D184F"></h3>
                <form>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Name:</label>
                        <p style="font-size:25px;font-weight:bold;">{{admin.name}}</p>
                        </div>
                        <div class="form-group col-md-6">
                        <label>Surname:</label>
                        <p style="font-size:25px;font-weight:bold;">{{admin.surname}}</p>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Email:</label>
                        <p style="font-size:25px;font-weight:bold;">{{admin.email}}</p>
                        </div>
                        <div class="form-group col-md-6">
                        <label>Phone number:</label>
                        <p style="font-size:25px;font-weight:bold;">{{admin.phoneNumber}}</p>
                        </div>
                    </div>
                      <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Country:</label>
                        <p style="font-size:25px;font-weight:bold;">{{admin.address.country}}</p>
                        </div>
                        <div class="form-group col-md-6">
                        <label>Town:</label>
                        <p style="font-size:25px;font-weight:bold;">{{admin.address.town}}</p>
                        </div>
                    </div>
                     <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Street:</label>
                        <p style="font-size:25px;font-weight:bold;">{{admin.address.street}}</p>
                        </div>
                        <div class="form-group col-md-6">
                        <label>Number:</label>
                        <p style="font-size:25px;font-weight:bold;">{{admin.address.number}}</p>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Postal code:</label>
                        <p style="font-size:25px;font-weight:bold;">{{admin.address.postalCode}}</p>
                        </div>

                        
                       
                    </div>
                    
                   
                   
                    <div style="height:30px;"></div>
                </form>






        </div>

       

       

    </div>
</template>

<script>
export default {

  data() {
    return {
        admin: {
                email : "",
                firstname : "",
                surname : "",
                phonenumber : "",
                password : "",
                address : {
                    town : "",
                    street : "",
                    number : "",
                    postalCode : "",
                    country : ""
                }
    },
    pharmacy : {},
    loggedIn : false
    }
  },
  mounted() {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/pharmacyAdmin/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.loggedIn = true;
               this.admin=response.data;
                this.axios.get('/pharmacyAdmin/myPharmacy',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.pharmacy = response.data;
                            console.log(this.pharmacyAdminPharmacy);
                    }).catch(res => {
                            alert("NOT OK");
                            console.log(res);
                    });
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });
    

                            
                 
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


  