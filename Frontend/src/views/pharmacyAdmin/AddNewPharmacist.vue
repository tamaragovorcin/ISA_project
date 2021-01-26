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

                <a  class = "btn btn-secondary" href = "/pharmacyDermatologists">Dermatologists</a>
                        <b class="tab"></b>    
                <a class = "btn btn-secondary" href = "/pharmacyPharmacists">Pharmacists</a>
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

      

        <div style="background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
            <h3 style="color: #0D184F">Registration</h3>
                
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Name:</label>
                        <input type="text" class="form-control" v-model="name" placeholder="Enter name">
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
        name : "",
        surname : "",
        email : "",
        password : "",
        repeatPassword : "",
        phoneNumber : "",
        town : "",
        street : "",
        number : "",
        postalCode : "",
        country : ""
    }
  },

  methods:{
       showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      showMyProfile: function(){

      },
       showActionsAndBenefitsForm : function(){
              this.$refs['my-modal'].show()
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
        window.location.href = "/myPharmacy";
      },
      addNewPharmacist : function(){
        window.location.href = "/addNewPharmacist";
      },
      add : function(){
          
      }
}
}
</script>

<style>

</style>


  