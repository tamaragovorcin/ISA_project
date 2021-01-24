<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                    <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Home</button>
                    <b class="tab"></b>  
                    <b class="tab"></b>  
                    <b class="tab"></b>  

                    <button class = "btn btn-link btn-lg" v-on:click = "showMyProfile">My profile</button>

                    <b class="tab"></b>     
                    <b class="tab"></b>             
                   

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "showClients">My clients</button>
                    <b class="tab"></b>  
                    <b class="tab"></b>      

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "workCalendar">Work calendar</button>
                    <b class="tab"></b>
                    <b class="tab"></b>  

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "vacation">Create a vacation</button>
                    <b class="tab"></b>
                    

                
            </span>
              <span  style="float:right;margin:15px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

             </span>

        </div>
            <div class="d-flex justify-content-center md-2 p-2">
                <h2 class="text-dark" style="font-weight: bold">Your profile</h2>
                <b class="tab"></b>
                <b class="tab"></b>
                <b class="tab"></b>
                <b class="tab"></b>
                <button class="btn btn-success btn-lg" style="margin-right:20px;">Change your personal data</button>
            </div>

            <div  class="blog-box d-flex justify-content-start md-4 p-5">
                <div class="blog-desc">

                    <b class="tab"></b>

                    <h4>Name:  </h4>
                    <b class="tab"></b>

                    <h4>Surname: </h4>
                    <b class="tab"></b>

                    <h4>Email:  </h4>
                    <b class="tab"></b>

                    <h4>Phone number:  </h4>   
                    <b class="tab"></b>

                    <h4>Country: </h4>
                    <b class="tab"></b>

                    <h4>Town: </h4>
                    <b class="tab"></b>

                    <h4>Street: </h4>
                    <b class="tab"></b>

                    <h4>Number: </h4>
                    <b class="tab"></b>

                    <h4>Postal code: </h4>
                    <b class="tab"></b>

                    <h4>Password: </h4>
                    <b class="tab"></b>





                </div>
            </div>
        
</div>
</template>

<script>
export default {

  data() {
    return {
      
       pharmacies : [],
      

    }
  },

  methods:{
      showclients : function(){

      },
      showMyProfile: function(){
         
      },
      showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      logOut : function(){
          window.location.href = "/login";

      },
      sendComplaint : function(){

      }
  }
}

</script>

<style>

</style>