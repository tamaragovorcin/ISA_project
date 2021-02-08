<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                    <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Home</button>
                    <strong class="tab"></strong>  
                    <strong class="tab"></strong> 

                    <button class = "btn btn-link btn-lg" v-on:click = "showMyProfile">My profile</button>

                    <strong class="tab"></strong>  
                    <strong class="tab"></strong>           
                   

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "showClients">My patients</button>
                    <strong class="tab"></strong>  
                    <strong class="tab"></strong>  

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "workCalendar">Work calendar</button>
                    <strong class="tab"></strong>  
                    <strong class="tab"></strong>

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "vacation">Create a vacation</button>
                    <strong class="tab"></strong>  
                    <strong class="tab"></strong>
                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "showMedications">Medications</button>
                    <strong class="tab"></strong>
                    <button class = "btn btn-info btn-lg" v-on:click = "writeExamination">My examination</button>
             <b class="tab"></b>   
 
            <button class = "btn btn-info btn-lg" v-on:click = "schedule">Schedule new examination</button>

                
            </span>
              <span  style="float:right;margin:15px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

                </span>

        </div>

    <div>
        <div class="d-flex justify-content-center md-2 p-2">
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
      showClients : function(){
            window.location.href = "/dermatologistClients";
      },
      showMyProfile: function(){
           window.location.href = "/dermatologistProfile";
      },
      showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      logOut : function(){
          window.location.href = "/login";

      },
       vacation: function(){
          window.location.href = "/dermatologistHoliday";

      },
       workCalendar : function(){
            window.location.href = "/workingHoursDermatologist";
      },
     
      writeExamination() {
       window.location.href = "/noteExamination";
      },
     schedule : function(){
              window.location.href = "/schedulingExamination";
      },

     
      scheduleConsulting : function(){
         
      },
   
      sendComplaint : function(){

      },
       
       showMedications : function() {
          window.location.href = "/dermatologistMedicationSearch";
      },

}
}
</script>

<style>

</style>


  