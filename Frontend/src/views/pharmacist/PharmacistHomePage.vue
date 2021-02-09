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

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "showclients">My clients</button>
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
                    <strong class="tab"></strong>
                    
                        <button class = "btn btn-info btn-lg" v-on:click = "writeConsulting">Add consultings</button>
                        <b class="tab"></b>   

                        <button class = "btn btn-info btn-lg" v-on:click = "addMedicine">Sell reserved medicines</button>
                        <b class="tab"></b>   

                        <button class = "btn btn-info btn-lg" v-on:click = "scheduleConsulting">Schedule new consulting</button>
                    
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
 

 <div>
     <b-modal ref="my-modal1" hide-footer scrollable title="Sell reserved medicines" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                    <div class="modal-header">
                                              
                                        <label>Sell reserved medicines</label>
                        
                                    </div>
                                    <div class="modal-body">
                                         <label>Enter a unique number of reservation</label><b class="tab"></b>  
                                          <input type="uniqueNumber" id="unique" name="unique"  placeholder="Unigue number of reservation...">
                                     </div>
                                                                    
                                         <div class="modal-footer">
                                            <button class="btn btn-secondary" block @click="hideModal1">Close</button>
                                            <button class="btn btn-success" @click="sendComplaint">Sell medicine</button>
                                         </div>
                            </div>
                                       


                    </div>

    </b-modal>
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
         window.location.href = "/pharmacistClients";
      },
      showMyProfile: function(){
         window.location.href = "/pharmacistProfile";
      },
      showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      workCalendar : function(){
       window.location.href = "/workingHoursPharmacist";


      },
      vacation: function(){
           window.location.href = "/createRequestVacation";
      },
     
      logOut : function(){
        localStorage.removeItem('token');
          window.location.href = "/login";

      },
      writeConsulting() {
        window.location.href = "/noteConsultig";
      },
        scheduleConsulting : function(){
             window.location.href = "/schedulingConsulting";
      },
      addMedicine : function(){
          this.$refs['my-modal1'].show()

      },
       hideModal1() {
        this.$refs['my-modal1'].hide()
      },
      
      sendComplaint : function(){

      },
      showMedications : function() {
          window.location.href = "/pharmacistMedicationSearch";
      },
  }
}

</script>

<style>

</style>


  