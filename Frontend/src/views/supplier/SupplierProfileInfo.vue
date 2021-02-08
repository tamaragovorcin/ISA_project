<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
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
        <div style="background-color:lightgray; margin: auto; width: 30%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
            <h3 style="color: #0D184F">Change profile information</h3>
                    <div class="form-group d-flex justify-content-center">
                        <label>My email: {{supplierInfo.email }} </label>
                        </div>
                    <div class="form-group">
                        <label>Name:</label>
                        <input type="text" class="form-control" v-model="supplierInfo.name" placeholder="Enter name">
                    </div>
                   
                    <div class="form-group">
                        <label>Surname:</label>
                        <input type="text" class="form-control" v-model = "supplierInfo.surname" placeholder="Enter surname">
                    </div>
                    <div class="form-row">
                       
                        <div class="form-group col-md-6">
                            <label>Phone number:</label>
                            <input type="text" class="form-control" v-model="supplierInfo.phoneNumber" placeholder="Enter phone number">
                        </div>
                         <div class="form-group col-md-6">
                            <label>Country:</label>
                            <input type="text" class="form-control" v-model="supplierInfo.address.country" placeholder="Enter country">
                        </div>
                    </div>
                      <div class="form-row">
                         <div class="form-group col-md-6">
                            <label>Postal code:</label>
                            <input type="text" class="form-control" v-model="supplierInfo.address.postalCode" placeholder="Enter postal code">
                        </div>
                       
                        <div class="form-group col-md-6">
                            <label>Town:</label>
                            <input type="text" class="form-control" v-model="supplierInfo.address.town" placeholder="Enter town">
                        </div>
                    </div>
                     <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Street:</label>
                        <input type="text" class="form-control" v-model="supplierInfo.address.street" placeholder="Enter street">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Number:</label>
                        <input type="number" class="form-control" v-model="supplierInfo.address.number" placeholder="Enter number">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                          <button class="btn btn-primary btn-lg" v-on:click = "changePassword">Click to change password</button>
                        </div>
                        <div class="form-group col-md-6">
                              <button class="btn btn-primary btn-lg" v-on:click = "changeInformation">Confirm information</button>
                        </div>
                    </div>
            
        </div>
         <b-modal ref="my-modal" hide-footer scrollable title="Password change" size="lg" modal-class="b-modal">
            <div modal-class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content" style="background-color:whitesmoke">
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Current Password:</label>
                            <input type="password" class="form-control" v-model="currentPassword" placeholder="Password">
                        </div>
                        <div class="form-group">
                            <label>New password:</label>
                            <input type="password" class="form-control" v-model="newPassword" placeholder=" New Password">
                        </div>
                        <div class="form-group">
                            <label>Repeat new password:</label>
                            <input type="password" class="form-control" v-model="newPasswordRepeat" placeholder="Repeat new Password">
                        </div>
                        <button v-on:click = "changePasswordConfirm" class="btn btn-primary">Confirm</button>        
                    </div>                
                </div>
            </div>
        </b-modal>
    </div>
</template>

<script>
export default {

  data() {
    return {
      supplierInfo: {
                email : "",
                password : "",
                firstname : "",
                surname : "",
                phonenumber : "",
                address : {
                    town : "",
                    street : "",
                    number : "",
                    postalCode : "",
                    country : ""
                }
                },
                currentPassword : "",
                newPassword : "",
                newPasswordRepeat : ""
    }
  },

  methods:{
      logOut : function(){
           localStorage.removeItem('token');
           window.location.href = "/login";
      },
     
      changeInformation : function() {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/supplier/update',this.supplierInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Profile is successfully changed!!");
                        console.log(response.data);
                })
                .catch(response => {
                       alert("Please try later.");
                        console.log(response);
                 });    
      },
      changePassword : function() {
            this.$refs['my-modal'].show();
      },
      changePasswordConfirm : function() {
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
        this.axios.post('/change-password',changePasswordInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                    console.log(response);
                    alert("Successfully changed password!.");
                    this.$refs['my-modal'].hide();
                }).catch(res => {
                     alert(res.response.data.message);
                });
      }
      
},
mounted() {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/supplier/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
                this.supplierInfo = response.data;
                console.log(response.data);
         }).catch(res => {
                alert("Please log in again or try later.");
                console.log(res);
        });
    }
}
</script>

<style>

</style>


  