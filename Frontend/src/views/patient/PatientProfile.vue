ines (39 sloc)  1.61 KB
  
<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Home</button>
                     <b class="tab"></b>                

                   <button class = "btn btn-link btn-lg" v-on:click = "showMyProfile">My profile</button>

                    <b class="tab"></b>                
                   

                    <button class = "btn btn-link btn-lg" style="margin-right:20px;" v-on:click = "showLoyalty">My loyalty program</button>
                    <b class="tab"></b>    

                    <button class = "btn btn-link btn-lg" style="margin-right:20px;" v-on:click = "writeComplaint">Write complaint</button>
                    <b class="tab"></b>   
            </span>
              <span  style="float:right;margin:15px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

                </span>

        </div>


       <div>
     <b-modal ref="my-modal" hide-footer scrollable title="Write complaint" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                    <div class="modal-header">
                                        

                                         <div class="row">
                                                    <div class = "tab"></div><div class = "tab"></div>
                                                  <button class = "btn btn-primary" v-on:click = "complainAboutPharmacy">Pharmacy</button>
                                                  <div class = "tab"></div>
                                                  <button class = "btn btn-primary" v-on:click = "complainAboutPharmacist">Pharmacist</button>
                                                  <div class = "tab"></div>
                                                   <button class = "btn btn-primary" v-on:click = "complainAboutDermatologist">Dermatologist</button>
                                        </div>
                        
                                    </div>
                                    <div class="modal-body">
                                        <div v-if = "showPharmacyComplaint">
                                                                    <div class="row">

                                                                            <div class="col">
                                                                                <label>Choose pharmacy:</label>
                                                                            </div>
                                                                            <div class="col">
                                                                                  <select v-model="pharmacy">
                                                                                        <option v-for="ph in pharmacies" :key="ph.id">
                                                                                            {{ph.name}}
                                                                                        </option>
                                                                                   </select>
                                                                            </div>

                                                                    </div>
                                                                    
                                                                    <hr />
                                                                    <div class="row">
                                                                        <div class="col">
                                                                        <label for="name">Enter your complaint:</label>
                                                                    </div> 
                                                                        
                                                                    </div>
                                                                    <div class="row">
                                                                    <input type="textarea" style="height:300px;width:750px;background-color:white;" class="form-control">
                                                                       
                                                                    </div>
                                                                     <div class="modal-footer">
                                        <button class="btn btn-secondary" block @click="hideModal">Close</button>
                                        <button class="btn btn-primary" @click="sendComplaint">Send complaint</button>
                                    </div>
                                        </div>
                                        <div v-if = "showPharmacistComplaint">
                                                                    <div class="row">

                                                                            <div class="col">
                                                                                <label>Choose pharmacist:</label>
                                                                            </div>
                                                                            <div class="col">
                                                                                  <select v-model="pharmacist">
                                                                                        <option v-for="ph in pharmacists" :key="ph.id">
                                                                                            {{ph.name}}
                                                                                        </option>
                                                                                   </select>
                                                                            </div>

                                                                    </div>
                                                                    
                                                                    <hr />
                                                                    <div class="row">
                                                                        <div class="col">
                                                                        <label for="name">Enter your complaint:</label>
                                                                    </div> 
                                                                    </div>
                                                                    <div class="row">
                                                                    <input type="textarea" style="height:300px;width:750px;background-color:white;" class="form-control">
                                                                       
                                                                    </div>
                                                                     <div class="modal-footer">
                                        <button class="btn btn-secondary" block @click="hideModal">Close</button>
                                        <button class="btn btn-primary" @click="sendComplaint">Send complaint</button>
                                    </div>
                                        </div>
                                        <div v-if = "showDermatologistComplaint">
                                                                    <div class="row">

                                                                            <div class="col">
                                                                                <label>Choose dermatologist:</label>
                                                                            </div>
                                                                            <div class="col">
                                                                                  <select v-model="dermatologist">
                                                                                        <option v-for="ph in dermatologists" :key="ph.id">
                                                                                            {{ph.name}}
                                                                                        </option>
                                                                                   </select>
                                                                            </div>

                                                                    </div>
                                                                    
                                                                    <hr />
                                                                    <div class="row">
                                                                    <div class="col">
                                                                        <label for="name">Enter your complaint:</label>
                                                                    </div> 
                                                                    </div>
                                                                    <div class="row">
                                                                    <input type="textarea" style="height:300px;width:750px;background-color:white;" class="form-control">
                                                                       
                                                                    </div>
                                                                     <div class="modal-footer">
                                        <button class="btn btn-secondary" block @click="hideModal">Close</button>
                                        <button class="btn btn-primary" @click="sendComplaint">Send complaint</button>
                                    </div>
                                        </div>
                                       


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
       showComplaintForm : false,
       pharmacies : [],
       pharmacy : null,
       pharmacists : [],
       pharmacist : null,
       dermatologists : [],
       dermatologist : null,
       showPharmacyComplaint : false,
       showPharmacistComplaint : false,
       showDermatologistComplaint : false,

    }
  },

  methods:{
      showLoyalty : function(){

      },
      showMyProfile: function(){

      },
      showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      logOut : function(){
          window.location.href = "/login";

      },
      writeComplaint() {
        this.$refs['my-modal'].show()
      },
       hideModal() {
        this.$refs['my-modal'].hide()
      },
      complainAboutPharmacy : function(){
          this.showPharmacyComplaint = true;
          this.showPharmacistComplaint = false;
          this.showDermatologistComplaint = false;
      },
      complainAboutPharmacist : function(){
          this.showPharmacyComplaint = false;
          this.showPharmacistComplaint = true;
          this.showDermatologistComplaint = false;
      },
       complainAboutDermatologist : function(){
          this.showPharmacyComplaint = false;
          this.showPharmacistComplaint = false;
          this.showDermatologistComplaint = true;
      },
      sendComplaint : function(){

      }
}
}
</script>

<style>

</style>


  