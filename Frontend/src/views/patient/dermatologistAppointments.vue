<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Home</button>
                     <b class="tab"></b>                

                    <button class = "btn btn-link btn-lg" v-on:click = "showPharmacies">Pharmacies</button>

                    <b class="tab"></b>     
                   <button class = "btn btn-link btn-lg" v-on:click = "showMyProfile">My profile</button>

                    <b class="tab"></b>                
                   

   

                    <button class = "btn btn-link btn-lg" style="margin-right:20px;" v-on:click = "writeComplaint">Write complaint</button>
                    <b class="tab"></b>   
            </span>
              <span  style="float:right;margin:15px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

                </span>

        </div>

        
        <div style="background: lightgray; height: 60px;">
            
            <span style="float: left; margin: 15px;">
                <b><button class = "btn btn-link btn-lg" style=" color:black; float:left; margin-left:20px; margin-top:0px;" v-on:click = "allPharmacies">Show all pharmacies</button></b>
                     <b class="tab"></b>                

                    <button class = "btn btn-link btn-lg" style="color:black; " v-on:click = "allDermatologists">Dermatologist appointments</button>

                    <b class="tab"></b>     
                   <button class = "btn btn-link btn-lg" style="color:black; " v-on:click = "allPharmacists">Pharmacist consultation</button>

                    <b class="tab"></b>                
                   
            </span>
             

        </div>

        <div style="background: white; height: 60px; margin-top: 20px">
            
           <span  style="float:right;margin:15px">
                    
                    <input type="textarea" style="height:20;width:150;background-color:white;" placeholder="Search pharmacy by name">
                     
                   <button class = "btn btn-link btn" style="color:black; " v-on:click = "searchName">Search</button>

            
                </span>
             
            
        </div>

<div style="background: white; height: 60px; width: 1000px; margin-top: 50px">
                      

                                                                            <div class="col">
                                                                                <label>Add new appointment:</label>
                                                                            </div>
                                                                            <div class="col">
                                                                                  <select v-model="pharmacy">
                                                                                        <option v-for="ph in pharmacies" :key="ph.id">
                                                                                            {{ph.name}}
                                                                                        </option>
                                                                                   </select>
                                                                            </div>

                                                                  
                                                                   
                                                                    <div class="row">
                                                                        <div class="col">
                                                                        <label for="name">Enter your complaint:</label>
                                                                    </div> 
                                                                        
                                                                    </div>
                                                                    <div class="row">
                                                                    <input type="textarea" style="height:300px;width:750px;background-color:white;" class="form-control">
                                                                       
                                                                    </div>
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
     
         allPharmacies: function(){
        window.location.href = "/myProfilePatient";
      },
      allDermatologists : function(){
           window.location.href = "/dermatologistAppointments";
      },
      allPharmacists : function(){
          window.location.href = "/login";
      },
      showMyProfile: function(){
        window.location.href = "/myProfilePatient";
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
        searchName() {
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
 
        },
       showPharmacies : function(){
            window.location.href = "/showPharmaciesPatient";
      }
}
}
</script>

<style>
</style>
