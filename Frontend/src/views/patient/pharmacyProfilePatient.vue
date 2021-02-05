<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        
        <div style="background: #0D184F; height: 90px;">
            
             <span style="float: left; margin: 15px;">
                        

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
  <div class = "col md-8">
        <button class = "btn btn-info btn-lg" style = "float:right;margin-right:20px;" @click = "subscribe">Subscribe</button>


        <div class = "container" v-if="welcomePageShow">
                  <h1 style="color:#0D184F;font-suze:75px;font-weight:bold;" align = "center"><u>{{pharmacy.pharmacyName}}</u></h1>

                <h3 style="color:#0D184F;font-suze:55px;font-weight:bold;margin-top:30px;" align = "center">About us:</h3>
            <div style="color:#0D184F;font-suze:40px;font-style:italic;font-weight:bold;">{{pharmacy.description}}</div>
             <h3 style="color:green;font-suze:55px;font-weight:bold;margin-top:30px;" align = "center">Visit us at address:</h3>

            <div style="color:green;font-suze:40px;font-style:italic;font-weight:bold;">{{pharmacy.address.street}}&nbsp; {{pharmacy.address.number}},&nbsp; {{pharmacy.address.town}}</div>
      </div>
        <div v-if="dermatologistsShow" class="container justify-content-center" id ="table">

                          <h3 style="color:white;">Dermatologists of&nbsp; {{pharmacy.pharmacyName}}</h3>

                      <table class="table table-striped table-light">
                      <thead class="thead-light">
                        <tr>
                          <th scope="col"></th>
                          <th scope="col">Name</th>
                          <th scope="col">Surname</th>
                          <th scope="col">Phone number</th>
                          <th scope="col">Email</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="dermatologist in ourDermatologists" :key="dermatologist.id">
                                                                        <td></td>
                                                                        <td>{{dermatologist.name}}</td>
                                                                        <td>{{dermatologist.surname}}</td>
                                                                        <td>{{dermatologist.phoneNumber}}</td>
                                                                        <td>{{dermatologist.email}}</td>
                                                                          
                                                                    </tr>
                      
                      </tbody>
                    </table>

      </div>
       <div v-if="pharmacistsShow" class="container justify-content-center" id ="table" align="center">

                          <h3 style="color:white;">Pharmacists of&nbsp; {{pharmacy.pharmacyName}}</h3>
                          <h5 style="color:white;float:left;color:red;">Consulting price:&nbsp; {{pharmacy.consultingPrice}}&nbsp; din</h5>

                      <table class="table table-striped table-light">
                      <thead class="thead-light">
                        <tr>
                          <th scope="col"></th>
                          <th scope="col">Name</th>
                          <th scope="col">Surname</th>
                          <th scope="col">Phone number</th>
                          <th scope="col">Email</th>
                          <th scope="col">Mark</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="pharmacist in ourPharmacists" :key="pharmacist.id">
                                                                        <td></td>
                                                                        <td>{{pharmacist.name}}</td>
                                                                        <td>{{pharmacist.surname}}</td>
                                                                        <td>{{pharmacist.phoneNumber}}</td>
                                                                        <td>{{pharmacist.email}}</td>
                                                                        <td>{{pharmacist.markPharmacist}}</td>
                                                                    </tr>
                      
                      </tbody>
                    </table>

      </div>
        <div v-if="termsShow" class="container justify-content-center" id ="table">

                          <h3 style="color:white;">Available appointments with dermatologist  in&nbsp; {{pharmacy.pharmacyName}}</h3>

                      <table class="table table-striped table-light">
                      <thead class="thead-light">
                        <tr>
                          <th scope="col"></th>
                          <th scope="col">Dermatologists name</th>
                          <th scope="col">Dermatologists surname</th>
                          <th scope="col">Dermatologists mark</th>
                          <th scope="col">Date</th>
                          <th scope="col">Time</th>
                          <th scope="col">Duration</th>
                          <th scope="col">Price</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="term in ourTerms" :key="term.id">
                                                                        <td></td>
                                                                        <td>{{term.dermatologistName}}</td>
                                                                        <td>{{term.dermatologistSurname}}</td>
                                                                        <td>{{term.dermatologisMark}}</td>
                                                                        <td>{{term.date}}</td>
                                                                        <td>{{term.startTime}}</td>
                                                                        <td>{{term.duration}}</td>
                                                                        <td>{{term.price}}</td>
                                                                        <td><button class = "btn btn-info" v-click = "schedule(term)">Schedule</button></td>
                                                                    </tr>
                      
                      </tbody>
                    </table>

      </div>

       <div v-if="medicationShow" class="container" id ="table">

                          <h3 style="color:white;">Medication in&nbsp; {{pharmacy.pharmacyName}}</h3>

                      <table class="table table-striped table-light">
                      <thead class="thead-light">
                        <tr>
                          <th scope="col"></th>
                          <th scope="col">Medication name</th>
                          <th scope="col">Manufacturer</th>
                          <th scope="col">Form</th>
                          <th scope="col">Type</th>
                          <th scope="col">Issusance regime</th>
                          <th scope="col">Mark</th>
                          <th scope="col">Loyalty points</th>
                          <th scope="col">Price</th>
                          <th scope="col">Reservation</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="med in ourMedications" :key="med.id">
                                                                        <td></td>
                                                                        <td>{{med.name}}</td>
                                                                        <td>{{med.manufacturer}}</td>
                                                                        <td>{{med.form}}</td>
                                                                        <td>{{med.type}}</td>
                                                                        <td>{{med.issuanceRegime}}</td>
                                                                        <td>{{med.loyaltyPoints}}</td>
                                                                        <td>{{med.mark}}</td>
                                                                        <td>{{med.price}}din</td>
                                                                        <td><button class ="btn btn-info" @click = "reserve($event,med)">Reserve</button></td>
                                                                    </tr>
                      
                      </tbody>
                    </table>

                     <b-modal ref="my-modal2" hide-footer scrollable title="Complete medication reservation" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content" style="background-color:whitesmoke">
                            <div class="modal-body">
                                <div class="form-group">
                                    <label>Medication  {{selectedMedication.name}}</label>
                                </div>
                            
                               <div class="form-row">
                                  <label>Enter a pick-up day:</label>
                                  <input type="date" name = "pickUpDay" class="form-control" v-model="pickUpDay" placeholder="Enter a pick-up date">
                                </div>
                                 <p class="tab2"></p>     
                                  <p><button class="btn btn-primary btn-lg" v-on:click = "reserveMedication">Reserve a medication</button></p>       
                            </div>                
                        </div>
                    </div>
          </b-modal>

      </div>

      <div v-if="availabilityShow">
      
         <div style = "background-color:whitesmoke; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
                       <h3 style="color: #0D184F;margin-bottom:20px">Upload QR code</h3>
                <div class="form-group">
                   <div class="row">
                       
                        <div class="col">
                             <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
                        </div> 
                          <div class="col">
                              <button  class="btn btn-primary" v-on:click="submitFile()">Check availability</button>

                        </div>
                         <h4 style="color:green;margin:20px" v-if="availabe">Requested medicine is available in our pharmacy</h4>
                         <h4 style="color:red;margin:20px" v-if="notAvailabe">Requested medicine not is available in our pharmacy</h4>

                   </div>
                </div>
        </div>
      
      
      
      
      
      </div>


</div>
<div class="col md-2"></div>


    <div class = "col md-4 align-top container-fluid">

                <div class="sidenav">
                <hr/>
                <a  v-on:click = "showWelcomePage">Home</a>
                <hr/>
                <a  v-on:click = "showDermatologists"> Dermatologists</a>
                <hr/>
                <a @click = "showPharmacists">Pharmacists</a>
                <hr/>
                <a @click = "showMedication">Medications</a>
                <hr/>
                <a v-on:click = "showTerms">Appointments with a dermatologists </a>
                <hr/>
                <a href = "/">Pricelist</a>
                <hr/>
                <a v-on:click = "showAvailability">Check medication availability </a>

                </div>
    </div>
    </div>
</template>

<script>
export default {
  data() {
    return {
       id : this.$route.params.id,
       pharmacy : {
         address:{
           town : "",
           street : "",
           number : ""
         }
       },
       dermatologistsShow : false,
       pharmacistsShow : false,
       termsShow : false,
       medicationShow : false,
       availabilityShow : false,
       ourDermatologists : [],
       ourMedications : [],
       ourTerms : [],
       ourPharmacists : [],
       welcomePageShow : true,
       availabe : false,
       notAvailabe : false,
       pickUpDay : "",
       patient : null,
       selectedMedication : {},
       reservationPage: false,
       term: null

       
       
    }
  },
  mounted() {
       let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
         this.axios.get('/patient/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,

             }
         }).then(response => {
                this.patient = response.data;
         
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });
  
        this.axios.get('/pharmacy/'+this.id,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
                this.pharmacy = response.data;
                 this.axios.get('pharmacy/dermatologists/'+this.id,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.ourDermatologists=response.data;
         }).catch(res => {
                       alert("Please try again later.");
                        console.log(res);
                 });
                  this.axios.get('pharmacy/pharmacists/'+this.id,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.ourPharmacists=response.data;
         }).catch(res => {
                       alert("Please try again later.");
                        console.log(res);
                 });
       this.axios.get('pharmacy/freeExaminationTerms/'+this.id,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.ourTerms=response.data;
         }).catch(res => {
                       alert("Please try again later.");
                        console.log(res);
                 });
             this.axios.get('pharmacy/medication/'+this.id,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.ourMedications=response.data;
         }).catch(res => {
                       alert("Please try again later.");
                        console.log(res);
                 });
        
        
        
               
         }).catch(res => {
                alert("NOT OK");
                console.log(res);
        });
       
        
    }
     ,
  methods:{

      showModal() {
        this.$refs['my-modal'].show()
      },
      hideModal() {
        this.$refs['my-modal'].hide()
      },
      showDermatologists : function(){
        this.welcomePageShow = false;
            this.dermatologistsShow = true;
            this.pharmacistsShow = false;
            this.termsShow = false;
            this.medicationShow = false;
            this.availabilityShow = false;
      },
      showPharmacists : function(){
        this.welcomePageShow = false;
          this.dermatologistsShow = false;
          this.pharmacistsShow = true;
          this.termsShow = false;
          this.medicationShow = false;
          this.availabilityShow = false;

     },
        showTerms : function(){
          this.welcomePageShow = false;
          this.dermatologistsShow = false;
          this.pharmacistsShow = false;
          this.termsShow = true;
          this.medicationShow = false;
          this.availabilityShow = false;

     }
      ,
        showMedication : function(){
          this.welcomePageShow = false;
          this.dermatologistsShow = false;
          this.pharmacistsShow = false;
          this.termsShow = false;
          this.medicationShow = true;
          this.availabilityShow = false;
          
      },
      showWelcomePage : function(){
          this.dermatologistsShow = false;
          this.pharmacistsShow = false;
          this.termsShow = false;
          this.medicationShow = false;
          this.welcomePageShow = true;
          this.availabilityShow = false;
      },
      showAvailability : function(){
          this.dermatologistsShow = false;
          this.pharmacistsShow = false;
          this.termsShow = false;
          this.medicationShow = false;
          this.welcomePageShow = false;
          this.availabilityShow = true;

      },
      subscribe :  function(){
         let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            const data = {
              pharmacyId : this.pharmacy.id
            }
            this.axios.post('/patient/subscribeToPharmacy',data,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {

                    alert("Successfully subrsribed.")
                    console.log(response)
                }).catch(res => {
                       alert("Please log in.");
                       window.location.href="/login"
                       console.log(res);
                });
      },
      reserve : function(event,medication){
        this.selectedMedication  = medication;
        this.$refs['my-modal2'].show()

      },
      reserveMedication: function(){
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/patient/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
                this.patient = response.data;
                const med = {
                  patient : this.patient,
                  pharmacyId: this.pharmacy.id,
                  medicationId: this.selectedMedication.id,
                  dateOfTakeOver: this.pickUpDay 
                };
         this.axios.post('/medicationReservation/add',med)
        
                    .then(res => {
                       alert("You successfully reserved "+this.selectedMedication.name);
                       window.location.href="/pharmacyProfile/"+this.pharmacy.id;
                        console.log(res);
                    })
                    .catch(res => {
                      alert("Please, try later.");
                        console.log(res);
                    })
                
          }).catch(res => {
                       alert("Please log in!");
                       window.location.href="/login"
                       console.log(res);
                 });
        
              
      },
       submitFile(){
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

            let formData = new FormData();

            formData.append('file', this.file);

            this.axios.post( '/erecipes/file/noAuthentication', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                     'Authorization': 'Bearer ' + token
                }
              }). then(response => {
                    const data = {
                      pharmacy : this.pharmacy,
                      listMedications : response.data
                    }
                    this.axios.post( '/erecipes/availability/pharmacy', data,{
                          headers: {
                              'Authorization': 'Bearer ' + token
                          }
                        }). then(response => {
                          this.availabe = true;
                          this.notAvailabe = false;

                          console.log(response);
                        }).catch(response => {
                          this.notAvailabe = true;
                          this.availabe = false;
                          console.log(response);
                        });     

                }).catch(res => {
                     alert("Please upload correct QR code!");
                    console.log(res);
                });     
      },

      
      handleFileUpload(){
        this.file = this.$refs.file.files[0];
      },
      schedule  : function( term) {
          this.term = term;
          alert(this.term)
          const examination = {
              patient: this.patient,
              cancelled : false,
              showedUp: false,
              examinationId: this.term.id,
              information: null



          }
            this.axios.post('/pharmacy/addExamination', examination)


    

      },
        sendComplaint : function(){
 
        },
       showPharmacies : function(){
            window.location.href = "/showPharmaciesPatient";

      },
 showMyProfile: function(){
        window.location.href = "/myProfilePatient";
      },logOut : function(){
          window.location.href = "/login";
      },writeComplaint() {
        this.$refs['my-modal'].show()
      },
   
}
}
</script>

<style>
@media (min-width: 992px){}
 .modal-lg {
    max-width: auto !important;
   }
@media (min-width: 992px){}
 .modal-dialog {
    margin: 100px !important;
   }
@media (min-width: 800px){}
  .modal-dialog {
    max-width: auto !important;
  }
#table {
    margin: 50px;
    float : right;
    background : #0D184F;
}
</style>