<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Home page</button>
                  
            </span>
              <span  style="float:right;margin:15px">
                    <button class = "btn btn-warning btn-lg" v-on:click = "showLoginForm">&nbsp;&nbsp;Login&nbsp;&nbsp;</button>

                    <b class="tab"></b>                
                   

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "showRegistrationForm">Register</button>
                </span>

        </div>
    </div>
</template>

<script>
export default {

  data() {
    return {
       
    }
  },

  methods:{
      showRegistrationForm : function(){
        window.location.href = "/registration";

      },
      showLoginForm: function(){
        window.location.href = "/pharmacistHomePage";

      },
      showHomePage : function(){
          window.location.href = "/isaHomePage";
      }
}
}
</script>

<style>

</style>


  