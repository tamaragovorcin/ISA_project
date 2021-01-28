<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                    <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Home</button>
                    <b class="tab"></b>  
                    <b class="tab"></b>  

                    <button class = "btn btn-link btn-lg" v-on:click = "showMyProfile">My profile</button>

                    <b class="tab"></b>     
                    <b class="tab"></b>             
                   

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "showClients">My patients</button>
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

    <div>
        <div class="d-flex justify-content-center md-2 p-2">
             <b class="tab"></b>   

			<button class = "btn btn-info btn-lg" v-on:click = "writeExamination">My examination</button>
             <b class="tab"></b>   
 
            <button class = "btn btn-info btn-lg" v-on:click = "schedule">Schedule new examination</button>
        </div>
    </div>
 <div>
     <b-modal ref="my-modal" hide-footer scrollable title="Record the consulting" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                    <div class="modal-header">
                                              
                                        <label>Record consulting</label>
                        
                                    </div>
                                    <div class="modal-body">
                                         <input type="text" id="name" name="name">
                                     </div>
                                                                    
                                         <div class="modal-footer">
                                            <button class="btn btn-secondary" block @click="hideModal">Close</button>
                                            <button class="btn btn-primary" @click="sendComplaint">Send complaint</button>
                                         </div>
                            </div>
                                       


                    </div>

    </b-modal>
  </div>

 <div>
     <b-modal ref="my-modal1" hide-footer scrollable title="Add medicine for patient" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                    <div class="modal-header">
                                              
                                        <label>Sell medicine for patient</label>
                        
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
      writeConsulting() {
        this.$refs['my-modal'].show()
      },
       hideModal() {
        this.$refs['my-modal'].hide()
      },
       hideModal1() {
        this.$refs['my-modal1'].hide()
      },
      workCalendar : function(){

      },
      vacation: function(){

      },
      scheduleConsulting : function(){

      },
      addMedicine : function(){
          this.$refs['my-modal1'].show()

      },
      sendComplaint : function(){

      }
}
}
</script>

<style>

</style>


  