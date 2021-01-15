<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Home</button>
                                        <b class="tab"></b>    

                    <button class = "btn btn-link btn-lg" v-on:click = "showMyProfile">My profile</button>

                    <b class="tab"></b>    
                    <button class = "btn btn-link btn-lg" style="margin-right:20px;" v-on:click = "showActionsAndBenefitsForm">Actions/benefits</button>
                    
                     <b class="tab"></b>    
                    <button class = "btn btn-link btn-lg" style="margin-right:20px;" v-on:click = "showOrderForm">Orders</button>
                     <b class="tab"></b>    
                    <button class = "btn btn-link btn-lg" style="margin-right:20px;" v-on:click = "showMyPharmacy">My pharmacy</button>
                  
            </span>
              <span  style="float:right;margin:15px">
                     <b class="tab"></b>    
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
                </span>

        </div>

        <b-modal ref="my-modal" hide-footer scrollable title="Write action or benefit" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                    <div class="modal-header">
                                        
                                  <h3>Share actions and benefits</h3>
                                         
                        
                                    </div>
                                    <div class="modal-body">
                                         <div class="row">
                                           <div class="col">
                                                 <label for="name">Describe action or benefit:</label>
                                            </div> 
                                         </div>
                                           <div class="row">
                                              <input type="textarea" style="height:300px;width:750px;background-color:white;" class="form-control">
                                            </div>
                                     
                                    </div>
                    <div class="modal-footer">
                                        <button class="btn btn-secondary" block @click="hideModal">Close</button>
                                        <button class="btn btn-primary" v-on:click="sendComplaint">Send complaint</button>
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
       
    }
  },

  methods:{
       showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      showMyProfile: function(){

      },
       showActionsAndBenefitsForm : function(){
              this.$refs['my-modal'].show()
      },
       hideModal() {
        this.$refs['my-modal'].hide()
      },
       showOrderForm : function(){
          window.location.href = "/order";

      },
       logOut : function(){
           window.location.href = "/login";
      },
      sendComplaint : function(){

      },
      showMyPharmacy : function (){
        
      } 
}
}
</script>

<style>

</style>


  