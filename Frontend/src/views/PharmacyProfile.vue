<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                <button class = "btn btn-link btn-lg" style="float:left;color:white" href = "/isaHomePage">←</button>
            </span>
              <span  style="float:right;margin:15px">
                    <a class = "btn btn-warning btn-lg" href = "/login">&nbsp;&nbsp;Login&nbsp;&nbsp;</a>
                    <strong class="tab"></strong>    
                    <a class = "btn btn-warning btn-lg" style="margin-right:20px;" href = "/registration">Register</a>
                </span>

        </div>
  <div class = "col md-8">
        <button class = "btn btn-info btn-lg" style = "float:right;margin-right:20px;" @click = "subscribe">Subscribe</button>


        <div class = "container" v-if="welcomePageShow">
                          <h1 style="color:#0D184F;font-size:55px;font-weight:bold;" align = "center">{{pharmacy.pharmacyName}}</h1>

      <div class="row" style = "background-color:whitesmoke;margin: auto;width: 60%;border: 3px solid gray;padding: 10px;margin-top:30px;">
                    <div style="color:#0D184F;font-size:25px;font-style:italic;font-weight:bold;" class="row">Our mark: {{pharmacy.mark}}</div>
        </div>
        <div class="row" style = "background-color:whitesmoke;margin: auto;width: 60%;border: 3px solid gray;padding: 10px;margin-top:30px;">
                        <h3 style="color:#0D184F;font-size:25px;font-weight:bold;margin-top:30px;" align = "center">About us:</h3>
                    <div style="color:#0D184F;font-size:25px;font-style:italic;font-weight:bold;">{{pharmacy.description}}</div>
        </div>
              <div class="row" style = "background-color:whitesmoke;margin: auto;width: 60%;border: 3px solid gray;padding: 10px;margin-top:30px;">

                    <div style="color:#0D184F;font-size:25px;font-style:italic;font-weight:bold;">Visit us at: {{pharmacy.address.street}}&nbsp; {{pharmacy.address.number}},&nbsp; {{pharmacy.address.town}}</div>
             <router-link :to="{ path: '/mapa/'+pharmacy.id}" v-slot="{href, navigate}">
                                  <button class = "btn btn-success" style="margin-left:25px;" :href="href" @click="navigate"  elevation="1">Show on map</button>
                              </router-link>

         </div>
        </div>



        <div v-if="dermatologistsShow" class="container justify-content-center" style="width:70%" id ="table">

                          <h3 style="color:white;">Dermatologists of&nbsp; {{pharmacy.pharmacyName}}</h3>

                      <table style = "" class="table table-striped table-light">
                      <thead class="thead-light">
                        <tr>
                          <th scope="col"></th>
                          <th scope="col">Name</th>
                          <th scope="col">Surname</th>
                          <th scope="col">Mark</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="dermatologist in ourDermatologists" :key="dermatologist.id">
                                                                        <td></td>
                                                                        <td>{{dermatologist.firstname}}</td>
                                                                        <td>{{dermatologist.surname}}</td>
                                                                        <td>{{dermatologist.markDermatologist}}</td>
                                                                    </tr>
                      
                      </tbody>
                    </table>

      </div>
       <div v-if="pharmacistsShow" class="container justify-content-center" style="width:70%" id ="table" align="center">

                          <h3 style="color:white;">Pharmacists of&nbsp; {{pharmacy.pharmacyName}}</h3>
                          <h5 style="float:left;color:red;">Consulting price:&nbsp; {{pharmacy.consultingPrice}}&nbsp; din</h5>

                      <table style="" class="table table-striped table-light">
                      <thead class="thead-light">
                        <tr>
                          <th scope="col"></th>
                          <th scope="col">Name</th>
                          <th scope="col">Surname</th>
                          <th scope="col">Mark</th>
                          <th scope="col">Schedule consulting</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="pharmacist in ourPharmacists" :key="pharmacist.id">
                                                                        <td></td>
                                                                        <td>{{pharmacist.name}}</td>
                                                                        <td>{{pharmacist.surname}}</td>
                                                                        <td>{{pharmacist.markPharmacist}}</td>
                                                                        <td><button class ="btn btn-info" @click = "consulting($event,pharmacist)">Schedule</button></td>

                                                                    </tr>
                      
                      </tbody>
                    </table>
                     <b-modal ref="my-modal3" hide-footer scrollable title="Schedule consulting" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content" style="background-color:whitesmoke">
                            <div class="modal-body">
                               
                            
                               <div class="form-row">
                                  <label>Date:</label>
                                  <input type="date" class="form-control" v-model="dateConsulting" placeholder="Enter a date">
                                </div>
                                <div class="form-row">
                                  <label>Time:</label>
                                  <input type="time" class="form-control" v-model="timeConsulting" placeholder="Enter time">
                                </div>
                                 <p class="tab2"></p>     
                                  <p><button class="btn btn-primary btn-lg" v-on:click = "scheduleConsulting">Schedule</button></p>       
                            </div>                
                        </div>
                    </div>
          </b-modal>

      </div>
        <div v-if="termsShow" class="container justify-content-center" style="width:70%" id ="table">

                          <h3 style="color:white;">Available appointments with dermatologist  in&nbsp; {{pharmacy.pharmacyName}}</h3>

                      <table  style="" class="table table-striped table-light">
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
                          <th scope="col">Scheduling</th>
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
                                                                        <td>{{term.duration}} min</td>
                                                                        <td>{{term.price}} din</td>
                                                                        <td><button class ="btn btn-info" @click = "schedule($event,term)">Schedule</button></td>
                                                                    </tr>
                      
                      </tbody>
                    </table>

      </div>

       <div v-if="medicationShow" class="container" id ="table" style="width:70%">

                          <h3 style="color:white;">Medication in&nbsp; {{pharmacy.pharmacyName}}</h3>

                      <table  style="" class="table table-striped table-light">
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

<div v-if="mapShow">
                <div class="d-flex justify-content-center mt-3">
                    <h2>Lokacija na mapi:</h2>
                </div>

 
       





</div>
    <div class ="container-fluid" style="height:100%;width:20%;vertical-align:top;">

                <div class="sidenav">
                <hr/>
                <a style="color:white"  v-on:click = "showWelcomePage">Home</a>
                <hr/>
                <a style="color:white"  v-on:click = "showDermatologists"> Dermatologists</a>
                <hr/>
                <a style="color:white"  @click = "showPharmacists">Pharmacists(Schedule consulting)</a>
                <hr/>
                <a style="color:white"  @click = "showMedication">Medications(Prices and reservation)</a>
                <hr/>
                <a style="color:white"  v-on:click = "showTerms">Appointments with a dermatologists </a>
                <hr/>
                <a style="color:white"  v-on:click = "showAvailability">Check medication availability </a>
                <hr/>
                
                </div>
    </div>
    </div>
</template>

<script>


export default {
  data() {
    return {
       olMap: null,
      vectorLayer: null,
      selectedFeature: null,
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
       xCoordinate : 0,
       yCoordinate : 0,
       map : null,
       selectedPharmacist : {},
       timeConsulting : "",
       dateConsulting : "",
       mapShow : false,

       
       
    }
  },
  mounted() {
        this.axios.get('/pharmacy/'+this.id)
        .then(response => {
                this.pharmacy = response.data;
                this.$nextTick(function() {
                    this.initMap();
                })
                 this.axios.get('pharmacy/dermatologistsFront/'+this.id)
         .then(response => {
               this.ourDermatologists=response.data;
         }).catch(res => {
                       alert("Please try again later.");
                        console.log(res);
                 });
                  this.axios.get('pharmacy/pharmacists/'+this.id)
                  .then(response => {
               this.ourPharmacists=response.data;
         }).catch(res => {
                       alert("Please try again later.");
                        console.log(res);
                 });
       this.axios.get('pharmacy/freeExaminationTerms/'+this.id)
             
         .then(response => {
               this.ourTerms=response.data;
         }).catch(res => {
                       alert("Please try again later.");
                        console.log(res);
                 });
             this.axios.get('pharmacy/medication/'+this.id)
            
         .then(response => {
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
            this.mapShow = false;
      },
      showPharmacists : function(){
        this.welcomePageShow = false;
          this.dermatologistsShow = false;
          this.pharmacistsShow = true;
          this.termsShow = false;
          this.medicationShow = false;
          this.availabilityShow = false;
          this.mapShow = false;

     },
        showTerms : function(){
          this.welcomePageShow = false;
          this.dermatologistsShow = false;
          this.pharmacistsShow = false;
          this.termsShow = true;
          this.medicationShow = false;
          this.availabilityShow = false;
          this.mapShow = false;

     }
      ,
        showMedication : function(){
          this.welcomePageShow = false;
          this.dermatologistsShow = false;
          this.pharmacistsShow = false;
          this.termsShow = false;
          this.medicationShow = true;
          this.availabilityShow = false;
          this.mapShow = false;
          
      },
      showWelcomePage : function(){
          this.dermatologistsShow = false;
          this.pharmacistsShow = false;
          this.termsShow = false;
          this.medicationShow = false;
          this.welcomePageShow = true;
          this.availabilityShow = false;
          this.mapShow = false;
      },
      showAvailability : function(){
          this.dermatologistsShow = false;
          this.pharmacistsShow = false;
          this.termsShow = false;
          this.medicationShow = false;
          this.welcomePageShow = false;
          this.availabilityShow = true;
          this.mapShow = false;

      },
      showMap : function(){
        this.mapShow = true;
        this.dermatologistsShow = false;
        this.pharmacistsShow = false;
        this.termsShow = false;
        this.medicationShow = false;
        this.welcomePageShow = false;
        this.availabilityShow = false;
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
                       alert("You must be logged in as patient.");
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
                      pharmacy : this.pharmacy.id,
                      listMedications : response.data
                    }
                    this.axios.post( '/erecipes/availability/pharmacy', data,{
                          headers: {
                              'Authorization': 'Bearer ' + token
                          }
                        }). then(response => {
                          alert(response.data);

                          console.log(response);
                        }).catch(response => {
                          alert(response.data);
                        });     

                }).catch(res => {
                     alert("Please upload correct QR code!");
                    console.log(res);
                });     
      },

      
      handleFileUpload(){
        this.file = this.$refs.file.files[0];
      },
      schedule(event,term) {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/patient/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
                this.patient = response.data;
        

          const examination = {
              patient: this.patient,
              cancelled : false,
              showedUp: false,
              examinationId: term.id,
              information: null
          }
            this.axios.post('/pharmacy/addExamination', examination,{
                          headers: {
                              'Authorization': 'Bearer ' + token
                          }
                        }). then(response => {
                          alert(response.data);

                          console.log(response);
                        }).catch(response => {
                          alert("You have to be logged in as patient.");
                          console.log(response);
                        });     

      });
      },

      consulting(event,pharmacist){
        this.selectedPharmacist  = pharmacist;
        this.$refs['my-modal3'].show()
      },
      scheduleConsulting : function(){
         let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/patient/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
                this.patient = response.data;
                
          const consulting = {
            pharmacist : this.selectedPharmacist.id,
            patient : this.patient.id,
            date : this.dateConsulting,
            startTime : this.timeConsulting,
          }
           this.axios.post('/consulting/scheduleFromPharmacyProfile',consulting,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Consulting is successfully created!");
                        console.log(response.data);
                })
                .catch(response => {
                       alert("Please try later.");
                        console.log(response);
                 });    
                
          }).catch(res => {
                       alert("Please log in!");
                       window.location.href="/login"
                       console.log(res);
                 });

      }
   
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