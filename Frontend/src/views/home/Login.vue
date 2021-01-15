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

           <form>
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
            </form>
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
      showRegistrationForm : function(){
          window.location.href = '/registration'
      },
      showLoginForm: function(){
         window.location.href = '/login'

      },
      showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      login : function(){
          
      }
}
}
</script>

<style>

</style>


  