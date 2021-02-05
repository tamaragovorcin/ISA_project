ines (39 sloc)  1.61 KB
  
<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                               
                    <a  class = "btn btn-link btn-lg" href= "/showPharmaciesPatient">Pharmacies</a>
                    <b class="tab"></b>     
                    <a  class = "btn btn-link btn-lg" href= "/myProfilePatient">My profile</a>
                    <b class="tab"></b>                
                    <a  class = "btn btn-link btn-lg" href= "/patientComplaint">Write complaint</a>
                    <b class="tab"></b>   
                    <a  class = "btn btn-link btn-lg" href= "/subscriptionsToPharmacies">My subscriptions</a>
                    <b class="tab"></b>   


                       <button class = "btn btn-link btn-lg" style="margin-right:20px;" v-on:click = "leaveAMark">Leave a mark</button>
                    <strong class="tab"></strong>   

                    <a  class = "btn btn-link btn-lg" href= "/eRecipes">ERecipes</a>
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
                                                                        <label for="name">Select a mark:</label>
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











    <div>
     <b-modal ref="my-modalMark" hide-footer scrollable title="Write complaint" size="lg" modal-class="b-modal">
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

                  <input type="radio" class="custom-control-input" id="defaultGroupExample4" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'5')" checked>
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

                  <input type="radio" class="custom-control-input" id="defaultGroupExample4" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'5')" checked>
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

                  <input type="radio" class="custom-control-input" id="defaultGroupExample4" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'5')" checked>
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

                  <input type="radio" class="custom-control-input" id="defaultGroupExample4" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'5')" checked>
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
      sendComplaint : function(){


 
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

                        const markk = {
                  patient: this.patient,
                  pharmacy: this.pharmacy,
                  mark: this.filter,
                 
                };
         this.axios.post('/pharmacy/leaveAMark',markk)
        
                    .then(res => {
                       
                        console.log(res);
                    })
                    .catch(res => {
                     
                        console.log(res);
                    })

            },

             sendDermatologist : function() {

                        const markk = {
                  patient: this.patient,
                  pharmacy: this.pharmacy,
                  mark: this.filter,
                 
                };
         this.axios.post('/pharmacy/leaveAMark',markk)
        
                    .then(res => {
                       
                        console.log(res);
                    })
                    .catch(res => {
                     
                        console.log(res);
                    })

            },

          sendMedication : function() {
                console.log(this.medication)
                
                        const markk = {
                  patient: this.patient,
                  medication: this.medication,
                  mark: this.filter,
                 
                };
         this.axios.post('/medication/leaveAMark',markk)
        
                    .then(res => {
                       
                        console.log(res);
                    })
                    .catch(res => {
                     
                        console.log(res);
                    })

            },

          sendPharmacist : function() {

                        const markk = {
                  patient: this.patient,
                  pharmacist: this.pharmacist,
                  mark: this.filter,
                 
                };
         this.axios.post('/pharmacist/leaveAMark',markk)
        
                    .then(res => {
                       
                        console.log(res);
                    })
                    .catch(res => {
                     
                        console.log(res);
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
                console.log(response.data);
                this.patient = response.data;
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });


            this.axios.get('/pharmacy/all')
          .then(response => {

                this.pharmacies= response.data;
               console.log(this.pharmacies);
              
          })

             this.axios.get('/dermatologist')
          .then(response => {

                this.dermatologists= response.data;
               console.log(this.dermatologists);
              
          })

          this.axios.get('/medication')
          .then(response => {

                this.medications= response.data;
               console.log(this.medications);
              
          })

                this.axios.get('/pharmacist')
          .then(response => {

                this.pharmacists= response.data;
               console.log(this.pharmacists);
              
          })
    
    }
}
</script>

<style>

</style>


  