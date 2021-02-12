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
                       <a   class = "btn btn-link" href="/updatePharmacyProfile">Update pharmacy</a>


                                    <router-link :to="{ path: '/pharmacyProfile/'+pharmacy.id}" v-slot="{href, navigate}">
                                                <button class = "btn btn-link" :href="href" @click="navigate"  elevation="1">My pharmacy</button>
                                    </router-link>
                   

                                  <b-dropdown id="ddCommodity" name="ddCommodity" text="Pharmacists" 
                                                class = "btn btn-link btn-lg">
                                      <b-dropdown-item href = "/pharmacyPharmacists">Our pharmacists</b-dropdown-item>
                                      <b-dropdown-item href = "/addPharmacist">Add new pharmacist</b-dropdown-item>      
                                  </b-dropdown> 
                                  <b-dropdown id="ddCommodity" name="ddCommodity" text="Dermatologists" 
                                                  class = "btn btn-link btn-lg">
                                        <b-dropdown-item href = "/pharmacyDermatologists">Our dermatologists(Add new)</b-dropdown-item>
                                        <b-dropdown-item href = "/examinationTerms">Examination terms</b-dropdown-item>      
                                    </b-dropdown>                 
                            <a   class = "btn btn-link" href = "/pharmacyMedications">Medications</a>
                            <a   class = "btn btn-link" href = "/pharmacyAdminMedicationSearch">Medications in system</a>
                            <a  class = "btn btn-link" href = "/actionsAndBenefits">Actions and benefits</a>
                            <b-dropdown id="ddCommodity" name="ddCommodity" text="Orders" 
                                                  class = "btn btn-link btn-lg">
                                        <b-dropdown-item href = "/order">Preview orders and offers(Add new)</b-dropdown-item>
                                        <b-dropdown-item href = "/editOrder">Edit/remove order</b-dropdown-item>      
                                    </b-dropdown>                             
                            <a   class = "btn btn-link" href="/holidayRequests">Holiday/absence requests</a>
                            <b-dropdown id="ddCommodity" name="ddCommodity" text="Graphical reviews" 
                                                  class = "btn btn-link btn-lg">
                                        <b-dropdown-item href = "/examinationGraphics">Examinations</b-dropdown-item>
                                        <b-dropdown-item href = "/medicationGraphics">Medication consumption</b-dropdown-item> 
                                        <b-dropdown-item href = "/incomeGraphics">Income</b-dropdown-item>      
                                    </b-dropdown>     
                             <a   class = "btn btn-link" href="/medicationInquires">Inquires for medication</a>

            </span>
              <span  style="float:right;margin:15px">
                   
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
                </span>
        </div>

      <b-button class = "btn btn-warning btn-lg" @click="showModal">Change password</b-button>

    
         <div style = "background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
                       <h3 style="color: #0D184F;margin-bottom:20px">Update your pharmacy data</h3>

                <div class="form-group">
                    <label>Pharmacy name:</label>
                    <input type="email" class="form-control" v-model="pharmacy.pharmacyName" placeholder="Enter name">
                </div>
                <div class="form-group">
                    <label>Address:</label>
                </div>
                <div class="form-group">
                    <label>Country:</label>
                    <input type="text" class="form-control" v-model="pharmacy.country" placeholder="Enter country">
                </div>
                <div class="form-group">
                    <label>Town:</label>
                    <input type="text" class="form-control" v-model="pharmacy.city" placeholder="Enter town">
               </div>
                <div class="form-group">
                    <label>Street:</label>
                    <input type="text" class="form-control" v-model="pharmacy.street" placeholder="Enter street">
               </div>
                <div class="form-group">
                    <label>Number:</label>
                    <input type="number" class="form-control" v-model="pharmacy.number" placeholder="Enter number">
               </div>
                <div class="form-group">
                    <label>Postal code:</label>
                    <input type="number" class="form-control" v-model="pharmacy.postalCode" placeholder="Enter postal code">
               </div>
                <div class="form-group">
                    <label>Consulting price:</label>
                    <input type="number" class="form-control" v-model="pharmacy.price" placeholder="Enter consulting price">
               </div>
                <div class="form-group">
                    <label>Pharmacy descriprion:</label>
                    <input type="text" class="form-control" v-model="pharmacy.description" placeholder="Enter description">
               </div>
                <button v-on:click = "update" class="btn btn-primary">Update</button>
           
        </div>
                   
                    <div style="height:30px;"></div>



    
  


        </div>
         
  
</template>

<script>
export default {
  data() {
    return {
        patient: {},
        name : "",
        surname : "",
        email : "",
        phoneNumber : "",
        town : "",
        street : "",
        number : "",
        postalCode : "",
        country : "",
        showTable: true,
        medicine: null,
        a: null,
        alergies: [],
        pharmacy : {},
        showEdit : false,
        currentPassword : "",
        newPassword : "",
        newPasswordRepeat : "",
        loggedIn : false,

    }
  },
mounted() {
 let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/pharmacyAdmin/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }})
             .then(response => {
                this.loggedIn = true;
                this.patient = response.data;
                console.log(response.data);
             
                     this.axios.get('/pharmacyAdmin/myPharmacyFront',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.pharmacy = response.data;
                            console.log(this.pharmacy);
                             
                    }).catch(res => {
                            alert("NOT OK");
                            console.log(res);
                    });

            
         }).catch(res => {
                       alert("Please, log in.");
                       window.location.href="/login";
                        console.log(res);
                 });



   
  
     
},
  methods:{
    
      previousUpdateProfile : function(){
      },
    logOut : function(){
        localStorage.remove.item('token');
        window.location.href = "/login";
      },
        hideModal() {
        this.$refs['my-modal'].hide()
      },
      showModal() {
        this.$refs['my-modal'].show()
      },
      edit : function(){
          this.showEdit = true;
      },

       update : function(){
         let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            const p = {
                    id: this.pharmacy.id,
                    pharmacyName : this.pharmacy.pharmacyName,
                    country: this.pharmacy.country,
                    city: this.pharmacy.city,
                    street: this.pharmacy.street,
                    number:this.pharmacy.number,
                    postalCode: this.pharmacy.postalCode,
                    description : this.pharmacy.description,
                    price : this.pharmacy.price
                };

       
                this.axios.post('/pharmacy/updatePharmacy',p,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                    }}).then(response => {
                    alert("Pharmacy info is successfully updated.");
                    console.log(response);
                }).catch(response => {
                    alert(response.response.data.message);
                    
                });

            
      },
      changePassword : function(){
          
        
        if(this.newPassword != this.newPasswordRepeat) {
            alert("New passwords must be equal.")
            return;
        }

        const changePasswordInfo ={
                oldPassword : this.currentPassword,
                newPassword : this.newPassword,
                rewriteNewPassword : this.newPasswordRepeat
            } 
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.post('/passwordFirstLogin',changePasswordInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                    console.log(response);
                    alert("Password successfully changed.") 
                    this.hideModal()
                }).catch(res => {
                    alert(res.response.data.message);
                });

      }
}
}
</script>

<style>
</style>
