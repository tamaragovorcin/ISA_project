<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                <button class = "btn btn-secondary btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Home page</button>
                  
            </span>
              <span  style="float:right;margin:15px">
                    <button class = "btn btn-secondary btn-lg" v-on:click = "showLoginForm">&nbsp;&nbsp;Login&nbsp;&nbsp;</button>

                    <b class="tab"></b>                
                   

                    <button class = "btn btn-secondary btn-lg" style="margin-right:20px;" v-on:click = "showRegistrationForm">Register</button>
                </span>

        </div>

        <div style = "background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
                       <h3 style="color: #0D184F;margin-bottom:20px">Logging in</h3>
                <div class="form-group">
                    <label>Email address:</label>
                    <input type="email" class="form-control" v-model="email" aria-describedby="emailHelp" placeholder="Enter email">
                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                </div>
                <div class="form-group">
                    <label>Password:</label>
                    <input type="password" class="form-control" v-model="password" placeholder="Password">
                </div>
                <button v-on:click = "login" class="btn btn-primary">Login</button>
           
        </div>
        <b-modal ref="my-modal" hide-footer scrollable title="Please change password to continue" size="lg" modal-class="b-modal">
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
        email : "",
        password : "",
        currentPassword : "",
        newPassword : "",
        newPasswordRepeat : "",
        authority :""
    }
  },

  methods:{
      login : function() {
            const loginInfo ={
                email : this.email,
                password : this.password,
            }
            localStorage.removeItem('token');

            this.axios.post('/login',loginInfo)
                .then(response => {
                    localStorage.setItem('token', JSON.stringify(response.data.accessToken));
                    console.log(response.data.token);
                    let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
                    this.axios.get('/authority',{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }
                    }).then(response => {
                            this.authority = response.data.authorities[0].authority;
                            if(this.authority==="ROLE_PATIENT") 
                             if(response.data.firstLogged) {
                                    this.$refs['my-modal'].show();
                                }
                                else {
                                    window.location.href = '/patientProfile';
                                }
                        
                            else if(this.authority==="ROLE_DERMATOLOGIST")
                                if(response.data.firstLogged) {
                                    this.$refs['my-modal'].show();
                                }
                                else {
                                    window.location.href = '/dermatologistHomePage';
                                }
                                
                            else if(this.authority==="ROLE_SUPPLIER")
                                if(response.data.firstLogged) {
                                    this.$refs['my-modal'].show();
                                }
                                else {
                                    window.location.href = '/supplierProfile';
                                }
                            else if(this.authority==="ROLE_SYSTEM_ADMIN")
                                if(response.data.firstLogged) {
                                     this.$refs['my-modal'].show();
                                }
                                else {
                                     window.location.href = '/systemAdminProfile';
                                }
                            else if(this.authority==="ROLE_PHARMACY_ADMIN")
                                if(response.data.firstLogged) {
                                       this.$refs['my-modal'].show();
                                }
                                else {
                                    window.location.href = '/pharmacyAdminProfile';   
                                }
                            else if(this.authority==="ROLE_PHARMACIST")
                             if(response.data.firstLogged) {
                                    this.$refs['my-modal'].show();
                                }
                                else {
                                    window.location.href = '/pharmacistHomePage';
                                }
                               
                            else alert("Error has occured."); 

                    }).catch(res => {
                                alert("NOT OK");
                                    console.log(res);
                            });

                            })
                .catch(response => {
                       alert("Please enter valid data!");
                        console.log(response);
                 });    
      },

      showRegistrationForm : function(){
          window.location.href = '/registration'
      },
      showLoginForm: function(){
         window.location.href = '/login'

      },
      showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      changePassword : function() {

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
                    if(this.authority==="ROLE_SYSTEM_ADMIN")
                        window.location.href = '/systemAdminProfile';
                    else if(this.authority==="ROLE_PATIENT") 
                        window.location.href = '/patientProfile';
                    else if(this.authority==="ROLE_DERMATOLOGIST")
                       window.location.href = '/dermatologistHomePage';
                    else if(this.authority==="ROLE_SUPPLIER")
                        window.location.href = '/supplierProfile';
                    else if(this.authority==="ROLE_PHARMACY_ADMIN")
                        window.location.href = '/pharmacyAdminProfile';   
                    else if(this.authority==="ROLE_PHARMACIST")
                         window.location.href = '/pharmacistHomePage';
                    else alert("Error has occured."); 
                }).catch(res => {
                    alert(res.response.data.message);
                });

      }
}
}
</script>