<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            
             
              <span  style="float:right;margin:10px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

                </span>
        </div>

      

<b-button class = "btn btn-warning btn-lg" @click="showModal">Change password</b-button>
        <div style="background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
            <h3 style="color: #0D184F">Update your personal informations</h3>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Name:</label>
                        <input type="text" class="form-control" v-model = "patient.name"  placeholder="Enter name">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Surname:</label>
                        <input type="text"  class="form-control" v-model = "patient.surname" placeholder="Enter surname">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Email:</label>
                        
                        <p><input v-model = "patient.email" readonly></p>
                        </div>
                        <div class="form-group col-md-6">
                        <label>Phone number:</label>
                        <input type="text"  class="form-control" v-model="patient.phoneNumber" placeholder="Enter phone number">
                        </div>
                    </div>
                      <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Country:</label>
                        <input type="text"  class="form-control" v-model="patient.address.country" placeholder="Enter country">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Town:</label>
                        <input type="text" class="form-control" v-model="patient.address.town" placeholder="Enter town">
                        </div>
                    </div>
                     <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Street:</label>
                        <input type="text"  class="form-control" v-model="patient.address.street" placeholder="Enter street">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Number:</label>
                        <input type="number" class="form-control" v-model="patient.address.number" placeholder="Enter number">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Postal code:</label>
                        <input type="text"  class="form-control" v-model="patient.address.postalCode" placeholder="Enter postal code">
                        </div>

                        
                       
                    </div>
                                        <button class="btn btn-primary btn-lg" v-on:click = "update">Update</button>

            </div>
                   
                    <div style="height:30px;"></div>



     <b-modal ref="my-modal" hide-footer scrollable title="Change your password" size="lg" modal-class="b-modal">
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
                        <button v-on:click = "changePassword" class="btn btn-primary">Confirm</button>        
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
        newPasswordRepeat : ""
    }
  },
mounted() {
 let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/dermatologist/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }})
             .then(response => {
                this.patient = response.data;
                console.log(response.data);
             
            
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });
   
  
     
},
  methods:{
    
      previousUpdateProfile : function(){
      },
    logOut : function(){
         localStorage.removeItem('token');
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
           const ad = {
                   
                    country: this.patient.address.country,
                    town: this.patient.address.town,
                    street: this.patient.address.street,
                    number:this.patient.address.number,
                    postalCode: this.patient.address.postalCode,
                };
            const p = {
                    id: this.patient.id,
                    email:this.patient.email,
                    firstname: this.patient.name,
                    surname : this.patient.surname,
                    phonenumber: this.patient.phoneNumber,
                    address: ad,
                };
       
                this.axios.post('/dermatologist/update',p, { 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                    .then(res => {
                        alert("Successfully updated info.")
                        window.location.href = "/dermatologistProfile";
                        console.log(res);
                    })
                    .catch(res => {
                        alert("Try later.")
                        console.log(res);
                    })
            
      },
      changePassword : function(){
          
        if(this.newPassword != this.newPasswordRepeat) {
            alert("New passwords must be equal.")
            return;
        }
        const changePasswordInfo ={
                oldPassword : this.currentPassword,
                newPassword : this.newPassword,
            } 
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.post('/passwordFirstLogin',changePasswordInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                  alert("Password successfully changed");
                  console.log(response);
                }).catch(res => {
                       alert("Please try later.");
                        console.log(res);
                });
      }
}
}
</script>

<style>
</style>