ines (39 sloc)  1.61 KB
  
<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                               
                    <a  class = "btn btn-link btn-lg" href= "/patientProfile">Home page</a>
                    <a  class = "btn btn-link btn-lg" href= "/showPharmaciesPatient">Pharmacies</a>
                    <a  class = "btn btn-link btn-lg" href= "/eRecipes">ERecipes</a>
                    <a  class = "btn btn-link btn-lg" href= "/subscriptionsToPharmacies">My subscriptions</a>
                     <button class = "btn btn-link btn-lg"  v-on:click = "leaveAMark">Leave a mark</button>
                   
            
                    <a  class = "btn btn-link btn-lg" href= "/patientComplaint">Write complaint</a>
               
                    
                    
                     <a  class = "btn btn-link btn-lg" href= "/updateProfilePatient">Change my profile</a>
                    <a  class = "btn btn-link btn-lg" href= "/logOut">Collect a medication</a>
                         <a  class = "btn btn-link btn-lg" href= "/medicationReservation">Reserve a medication</a>
                   

             
                  

            </span>
              <span  style="float:right;margin:15px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

                </span>

        </div>
  <div style="background-color:lightgray;  width: 100%; margin-top:30px;">
        <div style="background-color:white;  margin: auto; width: 80%;padding: 30px;">
            <h4 style="color: #0D184F"><strong>POINTS</strong></h4>
             <h5 style="color: #0D184F">{{this.patient.points}}</h5>
        </div>
        <div style="background-color:white; margin: auto; width: 80%;padding: 30px;margin-top:30px;">
            <strong><h4 style="color: #0D184F"><strong> CATEGORY</strong></h4></strong>
             <h5 style="color: #0D184F">{{this.patient.loyaltyCategory}}</h5>
        </div>
        <div style="background-color:white; margin: auto; width: 80%; padding:30px;margin-top:30px;">
            <strong><h4 style="color: #0D184F"><strong>BENEFITS</strong></h4></strong>
             <h5 style="color: #0D184F">{{this.patient.discount}}</h5>
        </div>
         <div style="background-color:white; margin: auto; width: 80%; padding:30px;margin-top:30px;">
            <strong><h4 style="color: #0D184F"><strong>PENALTIES</strong></h4></strong>
             <h5 style="color: #0D184F">{{this.patient.penalties}}</h5>
        </div>
        </div>

  

    <div>
     <b-modal ref="my-modalMark" hide-footer scrollable title="Leave a mark" size="lg" modal-class="b-modal" style=" vertical-align: middle;" >
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                    <div class="modal-header">
                                        

                                         <div class="row">
                                                   
                                                  <button class = "btn btn-primary" v-on:click = "markAboutPharmacy">Pharmacy</button>
                                                  <div class = "tab"></div>
                                                  <button class = "btn btn-primary" v-on:click = "markAboutPharmacist">Pharmacist</button>
                                                  <div class = "tab"></div>
                                                   <button class = "btn btn-primary" v-on:click = "markAboutDermatologist">Dermatologist</button>
                                                  <div class = "tab"></div>
                                                   <button class = "btn btn-primary" v-on:click = "markAboutMedication">Medication</button>
                                        </div>
                        
                                    </div>
                                    <div class="modal-body">
                                        <div v-if = "showPharmacyMark">
                                                                    <div class="row">

                                                                           
                                                                            <div class="col">
                                                                                   <div class="form-group col-md-6">
                                    <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose pharmacy"
                                        class = "btn btn-secondary dropdown-toggle" style=" width: 680px; float:left;margin-left:20px;">
                                            <b-dropdown-item v-for="pharmacy in this.pharmacies"  v-on:click = "pharmacySelect($event, pharmacy)" v-bind:key="pharmacy.id"> {{ pharmacy.pharmacyName }}</b-dropdown-item>
                                    </b-dropdown> 
                          </div>
                                                                            </div>

                                                                    </div>
                                                                    
                                                                    <hr />
                                                                   
                                                                       


            <div class = "row">
            <template>
                <p class="tab"></p>
              <div class="custom-control custom-radio form-group col ">
         
                  <input type="radio" class="custom-control-input" id="defaultGroupExample1" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'1')">
                   <label class="custom-control-label" for="defaultGroupExample1">1</label>
                
              </div>  
              <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample2" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'2')">
                   <label class="custom-control-label" for="defaultGroupExample2">2</label>
                
              </div> 
              <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample3" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'3')">
                 <label class="custom-control-label" for="defaultGroupExample3">3</label>
                
              </div> 
              <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample4" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'4')" >
                 <label class="custom-control-label" for="defaultGroupExample4" >4</label>
                
              </div> 

                <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample5" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'5')">
                 <label class="custom-control-label" for="defaultGroupExample5" >5</label>
                
              </div> 
             </template>
            </div>
                                                                 
                                                                        
                                                                    
                                                                 
                                                                       
                                                              
                                                                     <div class="modal-footer">
                                  
                                        <button class="btn btn-primary" @click="sendMark">Send</button>
                                    </div>
                                        </div>
                                        <div v-if = "showPharmacistMark">
                                                                    <div class="row">

                                                                            
                                                                            <div class="col">

                                                                                                                                <div class="form-group col-md-6">
                                    <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose pharmacist"
                                        class = "btn btn-secondary dropdown-toggle" style=" width: 680px; float:left;margin-left:20px;">
                                            <b-dropdown-item v-for="pharmacist in this.pharmacists"  v-on:click = "pharmacistSelect($event, pharmacist)" v-bind:key="pharmacist.id">    {{pharmacist.name}}    {{pharmacist.surname}}</b-dropdown-item>
                                    </b-dropdown> 
                          </div>
                                                                            </div>

                                                                    </div>
                                                                    
                                                                    <hr />
                                                                    <div class = "row">
                                                                 <template>
                                                                      <p class="tab"></p>
              <div class="custom-control custom-radio form-group col " >
     
                  <input type="radio" class="custom-control-input" id="defaultGroupExample1" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'1')">
                   <label class="custom-control-label" for="defaultGroupExample1">1</label>
                
              </div>  
              <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample2" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'2')">
                   <label class="custom-control-label" for="defaultGroupExample2">2</label>
                
              </div> 
              <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample3" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'3')">
                 <label class="custom-control-label" for="defaultGroupExample3">3</label>
                
              </div> 
              <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample4" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'4')" >
                 <label class="custom-control-label" for="defaultGroupExample4" >4</label>
                
              </div> 

                <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample5" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'5')" >
                 <label class="custom-control-label" for="defaultGroupExample5" >5</label>
                
              </div> 
             </template>
                                                                    </div>
                                 
                                                                     <div class="modal-footer">
                               
                                        <button class="btn btn-primary" @click="sendPharmacist">Send</button>
                                    </div>
                                        </div>
                                        <div v-if = "showDermatologistMark">
                                                                    <div class="row">

                                                                           
                                                                            <div class="col">

                                                                                                                                <div class="form-group col-md-6">
                                    <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose dermatologist"
                                        class = "btn btn-secondary dropdown-toggle" style=" width: 680px; float:left;margin-left:20px;">
                                            <b-dropdown-item v-for="dermatologist in this.dermatologists"  v-on:click = "dermatologistSelect($event, dermatologist)" v-bind:key="dermatologist.id">    {{dermatologist.name}}    {{dermatologist.surname}}</b-dropdown-item>
                                    </b-dropdown> 
                          </div>
                                                                            </div>

                                                                    </div>
                                                                    
                                                                    <hr />
              <div class = "row">                                                     
            <template>
                 <p class="tab"></p>
              <div class="custom-control custom-radio form-group col "  >
                  <input type="radio" class="custom-control-input" id="defaultGroupExample1" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'1')">
                   <label class="custom-control-label" for="defaultGroupExample1">1</label>
                
              </div>  
              <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample2" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'2')">
                   <label class="custom-control-label" for="defaultGroupExample2">2</label>
                
              </div> 
              <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample3" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'3')">
                 <label class="custom-control-label" for="defaultGroupExample3">3</label>
                
              </div> 
              <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample4" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'4')" >
                 <label class="custom-control-label" for="defaultGroupExample4" >4</label>
                
              </div> 

                <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample5" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'5')" >
                 <label class="custom-control-label" for="defaultGroupExample5" >5</label>
                
              </div> 
             </template>
              </div>
                                                                 
                                                                        
                             
                                                                     <div class="modal-footer">

                                        <button class="btn btn-primary" @click="sendDermatologist">Send</button>
                                    </div>
                                        </div>
                                        <div v-if = "showMedicationMark">
                                                                    <div class="row">

                                                                        
                                                                         

                                                                                                                                <div class="form-group col-md-6">
                                    <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose medication"
                                        class = "btn btn-secondary dropdown-toggle" style=" width: 680px; float:left;margin-left:20px;">
                                            <b-dropdown-item v-for="medication in this.medications"  v-on:click = "medicationSelect($event, medication)" v-bind:key="medication.id">    {{medication.name}}  </b-dropdown-item>
                                    </b-dropdown> 
                          </div>

                                                                    </div>
                                                                    
                                                                    <hr />
                                                                    <div class = "row">
                                                                   <template>
                                                                        <p class="tab"></p>
              <div class="custom-control custom-radio form-group col "  >
                  <input type="radio" class="custom-control-input" id="defaultGroupExample1" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'1')">
                   <label class="custom-control-label" for="defaultGroupExample1">1</label>
                
              </div> 
              <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample2" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'2')">
                   <label class="custom-control-label" for="defaultGroupExample2">2</label>
                
              </div> 
              <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample3" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'3')">
                 <label class="custom-control-label" for="defaultGroupExample3">3</label>
                
              </div> 
              <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample4" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'4')" >
                 <label class="custom-control-label" for="defaultGroupExample4" >4</label>
                
              </div> 

                <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample5" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'5')">
                 <label class="custom-control-label" for="defaultGroupExample5" >5</label>
                
              </div> 
             </template>
                                                                    </div>
                                                                     <div class="modal-footer">

                                        <button class="btn btn-primary" @click="sendMedication">Send </button>
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
       patient: null,
       pharmacy : null,
       pharmacists : [],
       pharmacist : null,
       dermatologists : [],
       dermatologist : null,
       showPharmacyComplaint : false,
       showPharmacistComplaint : false,
       showDermatologistComplaint : false,
         showPharmacyMark : false,
       showPharmacistMark : false,
       showDermatologistMark : false,
         showMedicationMark : false,
         filter: null,
         medication: null,
         medications: []

     
    }
  },
  
  methods:{
      logOut : function(){
          localStorage.removeItem('token');
          window.location.href = "/login";
      },

       leaveAMark() {
        this.$refs['my-modalMark'].show()
      },
       hideModal() {
        this.$refs['my-modal'].hide()
      },
       hideModalMark() {
        this.$refs['my-modalMark'].hide()
      },
     
       markAboutPharmacy : function(){
          this.showPharmacyMark = true;
          this.showPharmacistMark = false;
          this.showDermatologistMark = false;
          this.showMedicationMark = false;
      },
        markAboutPharmacist : function(){
         this.showPharmacyMark = false;
          this.showPharmacistMark = true;
          this.showDermatologistMark = false;
          this.showMedicationMark = false;
      },
         markAboutDermatologist : function(){
           this.showPharmacyMark = false;
          this.showPharmacistMark = false;
          this.showDermatologistMark = true;
          this.showMedicationMark = false;
      },
          markAboutMedication : function(){
            this.showPharmacyMark = false;
          this.showPharmacistMark = false;
          this.showDermatologistMark = false;
          this.showMedicationMark = true;
      },


        pharmacySelect: function(event, pharmacy){

            this.pharmacy = pharmacy;
            console.log(this.pharmacy)
 
        },

        
        pharmacistSelect: function(event, pharmacist){

            this.pharmacist = pharmacist;
            console.log(this.pharmacist)
 
        },

            
        dermatologistSelect: function(event, dermatologist){

            this.dermatologist = dermatologist;
            console.log(this.dermatologist)
 
        },

          medicationSelect: function(event, medication){

            this.medication = medication;
            console.log(this.medication)
 
        },
       showPharmacies : function(){
            window.location.href = "/showPharmaciesPatient";

      },

              updateFiler : function(event, filter) {
               this.filter = filter;
            },

             sendMark : function() {
  let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
                        const markk = {
                  patient: this.patient,
                  pharmacy: this.pharmacy,
                  mark: this.filter,
                 
                };
         this.axios.post('/pharmacy/leaveAMark',markk,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
        
        
        
                    .then(res => {
                        console.log(res.data)
                       alert(res.data);
                    })
                    .catch(res => {
                     
                         alert(res.data);
                    })

            },

             sendDermatologist : function() {
  let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
                        const markk = {
                  patient: this.patient,
                  dermatologist: this.dermatologist.id,
                  mark: this.filter,
                 
                };
         this.axios.post('/dermatologist/leaveAMark',markk,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
        
        
                    .then(res => {
                       
                         alert(res.data);
                         console.log(res.data)
                    })
                    .catch(res => {
                     
                       alert(res.data);
                    })

            },

          sendMedication : function() {
               let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
                console.log(this.medication)
                
                        const markk = {
                  patient: this.patient,
                  medication: this.medication,
                  mark: this.filter,
                 
                };
         this.axios.post('/medication/leaveAMark',markk,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
        
                    .then(res => {
                        console.log(res.data)
                          alert(res.data);
                    })
                    .catch(res => {
                     
                          alert(res.data);
                    })

            },

          sendPharmacist : function() {

                        const markk = {
                  patient: this.patient,
                  pharmacist: this.pharmacist,
                  mark: this.filter,
                 
                };
              
                let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
         this.axios.post('/pharmacist/leaveAMark',markk,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
        
                    .then(res => {
                        console.log(res.data)
                         alert(res.data);
                    })
                    .catch(res => {
                      alert(res.data);
                    })

            },
             


  

},
    mounted() {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        
        this.axios.get('/patient/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
                this.patientInfo = response.data;
                this.patient = response.data;
         }).catch(res => {
                       alert("Please, log in first!");
                        window.location.href = "/login";
                        console.log(res);
                 });


            this.axios.get('/pharmacy/all')
          .then(response => {

                this.pharmacies= response.data;
               console.log(this.pharmacies);
              
          }).catch(res => {
                       alert("List of pharmacies is not available at the moment!");
                        console.log(res);
                 });

             this.axios.get('/dermatologist')
          .then(response => {

                this.dermatologists= response.data;
               console.log(this.dermatologists);
              
              }).catch(res => {
                       alert("List of dermatologists is not available at the moment!");
                        console.log(res);
                 });

          this.axios.get('/medication')
          .then(response => {

                this.medications= response.data;
               console.log(this.medications);
              
               }).catch(res => {
                       alert("List of medications is not available at the moment!");
                        console.log(res);
                 });

                this.axios.get('/pharmacist')
          .then(response => {

                this.pharmacists= response.data;
               console.log(this.pharmacists);
              
              }).catch(res => {
                       alert("List of pharmacists is not available at the moment!");
                        console.log(res);
                 });
    
    }
}
</script>

<style>

</style>


  