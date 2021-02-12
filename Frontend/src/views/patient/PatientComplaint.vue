ines (39 sloc)  1.61 KB
  
<template>
  <div v-if="auth" id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
          <span style="float: left; margin: 15px;">
                               
                    <a  class = "btn btn-link btn-lg" href= "/patientProfile">Home page</a>
                    <a  class = "btn btn-link btn-lg" href= "/showPharmaciesPatient">Pharmacies</a>
                    <a  class = "btn btn-link btn-lg" href= "/eRecipes">ERecipes</a>
                    <a  class = "btn btn-link btn-lg" href= "/subscriptionsToPharmacies">My subscriptions</a>
                    <a  class = "btn btn-link btn-lg" href= "/patientComplaint">Write complaint</a>
                     <a  class = "btn btn-link btn-lg" href= "/updateProfilePatient">Change my profile</a>
                         <a  class = "btn btn-link btn-lg" href= "/medicationReservation">Reserve a medication</a>
                   

             
                  

            </span>
            <span  style="float:right;margin:15px">
               <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
            </span> 
        </div>
       <div>
    <div style = "background-color:lightgray; margin: auto; width: 40%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
                 
            <div class="modal-header">
                <div class="row d-flex justify-content-center">
                    <div class="col">
                        <button class = "btn btn-primary" v-on:click = "complainAboutPharmacy">Pharmacy</button>
                    </div>
                        <div class="col">
                    <button class = "btn btn-primary" v-on:click = "complainAboutPharmacist">Pharmacist</button>
                        </div>
                    <div class="col">
                         <button class = "btn btn-primary" v-on:click = "complainAboutDermatologist">Dermatologist</button>
                    </div>
                </div>
            </div>
            <div class="modal-body">
                <div v-if = "showPharmacyComplaint">
                    <div class="row  ">
                        <div class="col d-flex justify-content-center">
                            <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose pharmacy" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                                <b-dropdown-item v-for="pharmacy in this.pharmacies"  v-on:click ="pharmacyIsSelected($event, pharmacy)" v-bind:key="pharmacy.pharmacyId"> {{pharmacy.pharmacyName }}</b-dropdown-item>
                            </b-dropdown> 
                         </div>
                    </div>
                    <hr />
                    <div class="row">
                        <div class="col">
                            <label for="name">Enter your complaint:</label>
                        </div> 
                    </div>
                        <div class="row d-flex justify-content-center">
                        <input type="textarea" style="height:300px; width: 90%;background-color:white;" v-model="complaintText" class="form-control">
                    </div>
                        <div class="modal-footer d-flex justify-content-center">
                        <button class="btn btn-primary" @click="sendComplaintPharmacy">Send complaint</button>
                    </div>
                </div>
                <div v-if = "showPharmacistComplaint">
                    <div class="row">
                        <div class="col d-flex justify-content-center">
                            <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose pharmacist" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                                <b-dropdown-item v-for="pharmacist in this.pharmacists"  v-on:click ="pharmacistIsSelected($event, pharmacist)" v-bind:key="pharmacist.email"> {{pharmacist.fullName }}</b-dropdown-item>
                            </b-dropdown> 
                        </div>
                    </div>
                    <hr />
                    <div class="row">
                        <div class="col">
                            <label for="name">Enter your complaint:</label>
                        </div> 
                    </div>
                    <div class="row d-flex justify-content-center">
                        <input type="textarea" style="height:300px; width: 90%;background-color:white;" v-model="complaintText" class="form-control">
                    </div>
                    <div class="modal-footer d-flex justify-content-center">
                        <button class="btn btn-primary" @click="sendComplaintPharmacist">Send complaint</button>                                                                     </div>
                    </div>
                </div>
               <div v-if = "showDermatologistComplaint">
                    <div class="row">
                        <div class="col d-flex justify-content-center">
                            <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose dermatologists" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                                <b-dropdown-item v-for="derma in this.dermatologists"  v-on:click ="dermatologisttIsSelected($event, derma)" v-bind:key="derma.email"> {{derma.fullName }}</b-dropdown-item>
                            </b-dropdown> 
                        </div>
                    </div>
                    <hr />
                    <div class="row">
                        <div class="col">
                             <label for="name">Enter your complaint:</label>
                        </div> 
                    </div>
                    <div class="row d-flex justify-content-center">
                        <input type="textarea" style="height:300px; width: 90%;background-color:white;" v-model="complaintText" class="form-control">
                    </div>
                    <div class="modal-footer d-flex justify-content-center">
                         <button class="btn btn-primary" @click="sendComplaintDermatologist">Send complaint</button>
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
        patientInfo : null,
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
       complaintText : "",
       auth : false

    }
  },
  
  methods:{
     
     
      logOut : function(){
          localStorage.removeItem('token');
          window.location.href = "/login";

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
      sendComplaintPharmacy : function(){
        
          const complaint= {
            answered : false,
            massage : this.complaintText,
            answer : "",
            pharmacyName : this.pharmacy,
            dermatologist : null,
            pharmacist : null,
            subject : "PHARMACY"
          }
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            
          this.axios.post('/complaint/add',complaint,{ 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }}).then(response => {
                    alert("Complaint is successfully sent!.");
                    console.log(response);                
                }).catch(res => {
                     alert(res.response.data.message);
                 });                
               
         
      },
      sendComplaintDermatologist : function() {
     
          const complaint= {
            answered : false,
            massage : this.complaintText,
            answer : "",
            pharmacyName : null,
            dermatologist : this.dermatologist,
            pharmacist : null,
            subject : "DERMATOLOGIST"
          }
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

          this.axios.post('/complaint/add',complaint,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                    alert("Complaint is successfully sent!.");

                    console.log(response);                
                }).catch(res => {
                    alert(res.response.data.message);

                });
      },
      sendComplaintPharmacist : function() {
        const complaint= {
            answered : false,
            massage : this.complaintText,
            answer : "",
            pharmacyName : null,
            dermatologist : null,
            pharmacist : this.pharmacist,
            subject : "PHARMACIST"
          }
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

          this.axios.post('/complaint/add',complaint,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                    alert("Complaint is successfully sent!.");

                    console.log(response);                
                }).catch(res => {
                     alert(res.response.data.message);
                });
      },
     
      pharmacyIsSelected : function(event, pharmacy) {
          this.complaintText ="";
          this.pharmacy = pharmacy;
      },
      dermatologisttIsSelected : function(event, derm) {
          this.complaintText ="";
          this.dermatologist = derm;
      },
      pharmacistIsSelected : function(event, pharmacist){
          this.complaintText ="";
          this.pharmacist = pharmacist;
      },
    
},
    mounted() {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/patient/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
             this.auth = true;
                this.patientInfo = response.data;
         }).catch(res => {
                       alert("Please, log in first.");
                       
                       this.auth = false;
                         window.location.href = "/login";
                        console.log(res);
                 });

        this.axios.get('/pharmacy/allNames2',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
            }).then(response => { 
                this.pharmacies=response.data;
            }).catch(res => {
                        alert("Please try again later.");
                        console.log(res);});

        this.axios.get('/pharmacist/basicInfo',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
            }).then(response => { 
                this.pharmacists=response.data;
            }).catch(res => {
                        alert("Please try again later.");
                        console.log(res);});

        this.axios.get('/dermatologist/basicInfo',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
            }).then(response => { 
                this.dermatologists=response.data;
            }).catch(res => {
                        alert("Please try again later.");
                        console.log(res);});
    }
}
</script>