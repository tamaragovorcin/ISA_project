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

      

    </div>
</template>

<script>
export default {

  data() {
    return {
        email : "",
        password : "",
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
                            var authority = response.data.authorities[0].authority;
                            if(authority==="ROLE_PATIENT") 
                                window.location.href = '/patientProfile';
                            else if(authority==="ROLE_DERMATOLOGIST")
                                alert("Dermatologist is logged in :)");
                            else if(authority==="ROLE_SUPPLIER")
                                alert("Supplier is logged in :)");   
                            else if(authority==="ROLE_SYSTEM_ADMIN")
                                window.location.href = '/systemAdminProfile';
                            else if(authority==="ROLE_PHARMACY_ADMIN")
                                alert("Pharmacy admin is logged in :)");   
                            else if(authority==="ROLE_PHARMACIST")
                                alert("Pharmacist is logged in :)"); 
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
}
}
</script>

<style>

</style>


  