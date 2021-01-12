<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                <button class = "btn btn-secondary btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Home</button>
                  
            </span>
              <span  style="float:right;margin:15px">
                    <button class = "btn btn-secondary btn-lg" v-on:click = "showMyProfile">My profile</button>

                    <b class="tab"></b>    
                    <button class = "btn btn-secondary btn-lg" style="margin-right:20px;" v-on:click = "showActionsAndBenefitsForm">Actions/benefits</button>
                    
                     <b class="tab"></b>    
                    <button class = "btn btn-secondary btn-lg" style="margin-right:20px;" v-on:click = "showOrderForm">Orders</button>
                     <b class="tab"></b>    
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
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
       showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      showMyProfile: function(){

      },
       showActionsAndBenefitsForm : function(){

      },
       showOrderForm : function(){
          window.location.href = "/order";

      },
       logOut : function(){
           window.location.href = "/login";
      },
      
}
}
</script>

<style>

</style>


  