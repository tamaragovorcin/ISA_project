<template>
  <div  v-if="loggedIn"  id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
      <span style="float: left; margin: 15px;">
                              <b-dropdown id="ddCommodity" name="ddCommodity" text="My profile" 
                                              class = "btn btn-link btn-lg">
                                    <b-dropdown-item href = "/pharmacyAdminProfile">Our pharmacists</b-dropdown-item>
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
                            <a   class = "btn btn-secondary" href="/order">Orders</a>
                            <strong class="tab"></strong>  
                            <a   class = "btn btn-secondary" href="/holidayRequests">Holiday/absence requests</a>
            </span>
              <span  style="float:right;margin:15px">
                   
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
                </span>
        </div>
        <div style="background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
            <h3 style="color: #0D184F">Add new pharmacist</h3>
                
                
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
                   
                    <button class="btn btn-primary btn-lg" v-on:click = "add">Add</button>
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
          pharmacies : [],
          pharmacy : {},
          pharmacyName : "",
          loggedIn : false
           }
  },

  methods:{
      add : function(){
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
                pharmacyID : this.pharmacy.id
            }
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

            this.axios.post('/pharmacist/register',userInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Pharmacist is successfully registred!");
                        console.log(response.data);
                        window.location.href = "/pharmacyPharmacists";
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
     
},
  mounted() {
       let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/pharmacyAdmin/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
                this.admin = response.data;
                this.loggedIn = true;
                console.log(this.admin);
                this.axios.get('/pharmacyAdmin/myPharmacy',{ 
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
                alert("NOT OK");
                console.log(res);
        });
        
    }
     
}
</script>

<style>

</style>


  