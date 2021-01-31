ines (39 sloc)  1.61 KB
  
<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                 <a  class = "btn btn-secondary" href= "/isaHomePage">Home</a>
                    <b class="tab"></b>                
                    <a  class = "btn btn-secondary" href= "/showPharmaciesPatient">Pharmacies</a>
                    <b class="tab"></b>     
                    <a  class = "btn btn-secondary" href= "/myProfilePatient">My profile</a>
                    <b class="tab"></b>                
                    <a  class = "btn btn-secondary" href= "/patientComplaint">Write complaint</a>
                    <b class="tab"></b>   
                    <a  class = "btn btn-secondary" href= "/subscriptionsToPharmacies">My subscriptions</a>
                    <b class="tab"></b>   
                    <a  class = "btn btn-secondary" href= "/eRecipes">ERecipes</a>
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

  <div>
       <div class="modal-content" style="background-color:whitesmoke">
                                    <div class="modal-header">
                                        

                                         <div class="row"  style="height: 60px;">
                                                    <div class = "tab"></div><div class = "tab"></div>
                                                  <button class = "btn btn-primary" v-on:click = "complainAboutPharmacy">Pharmacy</button>
                                                  <div class = "tab"></div>
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
      logOut : function(){
          window.location.href = "/login";
      },
      

}
}
</script>

<style>

</style>


  