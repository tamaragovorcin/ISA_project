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
                    <a  class = "btn btn-link btn-lg" href= "/patientComplaint">Write complaint</a>
                     <a  class = "btn btn-link btn-lg" href= "/updateProfilePatient">Change my profile</a>
                    <a  class = "btn btn-link btn-lg" href= "/logOut">Collect a medication</a>
                      <a  class = "btn btn-link btn-lg" href= "/medicationReservation">Reserve a medication</a>
                   

             
                  

            </span>
              <span  style="float:right;margin:15px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

                </span>

        </div>

        
        <div style="background: lightgray; height: 60px;">
            
            <span style="float: left; margin: 15px;">

                 <a  class = "btn btn-link btn-lg" style=" color:black; float:left; margin-left:20px; margin-top:0px;" href= "/showPharmaciesPatient">Show all pharmacies</a>
                    <a  class = "btn btn-link btn-lg" style=" color:black; float:left; margin-left:20px; margin-top:0px;" href= "/dermatologistAppointments">Make a new dermatologist appointmen</a>
                    <a  class = "btn btn-link btn-lg" style=" color:black; float:left; margin-left:20px; margin-top:0px;" href= "/myDermatologistAppointments">My dermatologist appointments</a>
                    <a  class = "btn btn-link btn-lg" style=" color:black; float:left; margin-left:20px; margin-top:0px;" href= "/PharmacistConsultation">Pharmacist consultation</a>
                    <a  class = "btn btn-link btn-lg" style=" color:black; float:left; margin-left:20px; margin-top:0px;" href= "/myPharmacistsConsultation">My pharmacist consultations</a>
 
                             
                   
            </span>
             

        </div>

        <div style="background: white; height: 60px; margin-top: 20px">
            
          
             
            
        </div>


      <div style="background: whitesmoke; border: 3px solid #0D184F; height: 350px; width:1000px; margin-left:300px; margin-top: 20px">

           <div class="form-row">
                       
                        <label>Choose a date:</label>
                        
                        <input type="date" v-model = "date" class="form-control" placeholder = "01/01/2021">
                        
                       
                        <label>Choose the time:</label>
                        <input type="time" v-model = "startTime" class="form-control" placeholder = "01/01/2021">
                       
                    </div>

       
        
<button class="button3" v-on:click="showAvailability(date,startTime)">Show pharmacies</button>

      

      </div>  

        <div id="customers" v-if="showTable" style="background: whitesmoke; border: 3px solid #0D184F; height: 350px; width:1000px; margin-left:300px; margin-top: 20px">

<template>
              <div class="row">
                
              <div class="custom-control custom-radio form-group col ">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample1" name="groupOfDefaultRadios" v-on:click="marklowest">
                   <label class="custom-control-label" for="defaultGroupExample1">Sort by pharmacy mark - lowest first</label>
              </div>

                    <div class="custom-control custom-radio form-group col ">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample2" name="groupOfDefaultRadios" v-on:click="markhighest">
                   <label class="custom-control-label" for="defaultGroupExample2">Sort by pharmacy mark - highest first</label>

                  </div>
                  <div class="custom-control custom-radio form-group col ">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample3" name="groupOfDefaultRadios" v-on:click="pricelowest">
                   <label class="custom-control-label" for="defaultGroupExample3">Sort by consultation price - lowest first</label>
              </div>

                    <div class="custom-control custom-radio form-group col ">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample4" name="groupOfDefaultRadios" v-on:click="pricehighest">
                   <label class="custom-control-label" for="defaultGroupExample4">Sort by consultation price - highest first</label>

                  </div>
                
              </div>
             </template>
    <table style = "" id="table1" class="table">
                    <thead  class="thead-dark">
                        <tr style="font-weight: bold; ">
                            <th style="width: 200px; height: 80px;" scope="col">Name of pharmacy</th>
                            <th style="width: 200px; height: 80px;" scope="col">Location</th>

                            <th style="width: 200px; height: 80px;" scope="col">Pharmacy Mark</th>
                            <th style="width: 200px; height: 80px;" scope="col">Consultation price</th>
                       

                        </tr>
                    </thead>
                    <tbody>
                      
                        <tr  v-for="pharmacy in pharmacies" :key="pharmacy.id" v-on:click="goToBlabla(pharmacy)">
                            <td>{{pharmacy.pharmacyName}}</td>
                            <td>{{pharmacy.city}}</td>
                            <td>{{pharmacy.mark}}</td>
                            <td>{{pharmacy.price}}</td>
                        </tr>
                      
                    </tbody>
                </table>


        </div>



         <div id="customers" v-if="showSecondTable" style="background: whitesmoke; border: 3px solid #0D184F; height: 350px; width:1000px; margin-left:300px; margin-top: 20px">


<template>
              <div class="row">
                
              <div class="custom-control custom-radio form-group col ">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample5" name="groupOfDefaultRadios" v-on:click="marklowestpharmacist">
                   <label class="custom-control-label" for="defaultGroupExample5">Sort by pharmacist mark - lowest first</label>
              </div>

                    <div class="custom-control custom-radio form-group col ">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample6" name="groupOfDefaultRadios" v-on:click="markhighestpharmacist">
                   <label class="custom-control-label" for="defaultGroupExample6">Sort by pharmacist mark - highest first</label>

                    </div>
              </div>
             </template>
    <table style = "" id="table2"  class="table">
                    <thead  class="thead-dark">
                        <tr style="font-weight: bold; ">
                            <th style="width: 200px;" scope="col">Pharmacist first name</th>
                            <th style="width: 200px;" scope="col">Pharmacist last name</th>

                            <th style="width: 200px;" scope="col">Pharmacist Mark</th>
                          
                       

                        </tr>
                    </thead>
                    <tbody>
                      
                        <tr  v-for="pharmacist in pharmacists" :key="pharmacist.id" v-on:click="reserve(pharmacist)">
                            <td>{{pharmacist.firstname}}</td>
                            <td>{{pharmacist.surname}}</td>
                            <td>{{pharmacist.mark}}</td>
                      
                        </tr>
                      
                    </tbody>
                </table>


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
       patient: null,
       date: null,
       startTime: null,
        showTable: false,
                showTableAvailability : false,
      showSecondTable : false

    }
  },

mounted() {
 let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/patient/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }})
             .then(response => {
                this.patient = response.data;
                console.log(response.data);
                  
            
         }).catch(res => {
                       alert("Please, log in first!");
                        console.log(res);
                                  window.location.href = "/login";
                 });
},

  methods:{
      myDermatologistAppointments: function(){
            window.location.href = "/myDermatologistAppointments";

      },
       myPharmacistsConsultation: function(){
            window.location.href = "/myPharmacistsConsultation";

      },
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
  
      showAvailability: function (date, startTime) {
                   let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
                  
          this.date = date;
          this.startTime = startTime;

            const datum = {
                date : this.date,
                time : this.startTime

           }
            console.log(datum)
            this.axios.post('/consulting/getPharmacies',datum,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }}
                ).then(response => {
                    this.pharmacies= response.data;
                    console.log(response);                
                }).catch(res => {
                    alert(res.response.data.message)
                   
                        console.log(res);
                });

        this.showTable = true;
          
              
            }, 

    goToBlabla: function (pharmacy) {
                 let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
         this.pharmacy = pharmacy;
         const datum = {
                date : this.date,
                time : this.startTime,
                pharmacyId: pharmacy.id

           }
   this.axios.post('/consulting/getPharmacists',datum,{ 
       
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }}
                ).then(response => {
                  
                    this.pharmacists= response.data;
                  
                    console.log(response);                
                }).catch(res => {
                         alert(res.response.data.message)
                        console.log(res);
                });


                 this.showTable = false;
           this.showSecondTable = true;
              
            }, 

    reserve: function (pharmacist) {
             
         const datum = {
                date : this.date,
                time : this.startTime,
                pharmacyId: this.pharmacy.id,
                pharmacist: pharmacist,
                patient: this.patient

           }

  let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
   this.axios.post('/consulting/reserveConsultation',datum,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }}
                ).then(response => {
                    alert("Successfully reserved consultation!.");
                    console.log(response);                
                }).catch(res => {
                       alert(res.response.data.message)
                        console.log(res);
                });


                 this.showTable = false;
           this.showSecondTable = true;
              
            }, 

              
              
        marklowest: function(){
            const datum = {
                date : this.date,
                time : this.startTime

           }
                    this.axios.post('/consulting/marklowest', datum)
                        .then(response => {
                            console.log(response.data);
                            this.pharmacies = response.data;
                            this.showTable = false;
                            this.showTable = true;
                            })
                                .catch(res => {
                                      alert(res.response.data.message)
                                        console.log(res);
                                    })


      },

       markhighest: function(){
            const datum = {
                date : this.date,
                time : this.startTime

           }

      this.axios.post('/consulting/markhighest', datum)
          .then(response => {
               console.log(response.data);
              this.pharmacies = response.data;
              this.showTable = false;
              this.showTable = true;
               })
                .catch(res => {
                       alert(res.response.data.message)
                        console.log(res);
                    })


      },

       pricelowest: function(){
           
            const datum = {
                date : this.date,
                time : this.startTime

           }

      this.axios.post('/consulting/pricelowest', datum)
          .then(response => {
               console.log(response.data);
              this.pharmacies = response.data;
              this.showTable = false;
              this.showTable = true;
               })
                .catch(res => {
                       alert(res.response.data.message)
                        console.log(res);
                    })


      },

       pricehighest: function(){
           
            const datum = {
                date : this.date,
                time : this.startTime

           }

      this.axios.post('/consulting/pricehighest', datum)
          .then(response => {
               console.log(response.data);
              this.pharmacies = response.data;
              this.showTable = false;
              this.showTable = true;
               })
                .catch(res => {
                       alert(res.response.data.message)
                        console.log(res);
                    })


      },


       marklowestpharmacist: function(){
            const datum = {
                date : this.date,
                time : this.startTime,
                pharmacyId: this.pharmacy.id

           }

      this.axios.post('/consulting/marklowestpharmacist', datum)
          .then(response => {
               console.log(response.data);
              this.pharmacies = response.data;
              this.showTable = false;
           this.showSecondTable = false;
            this.showSecondTable = true;

               })
                .catch(res => {
                       alert(res.response.data.message)
                        console.log(res);
                    })

      },

      
       markhighestpharmacist: function(){
           
             const datum = {
                date : this.date,
                time : this.startTime,
                pharmacyId: this.pharmacy.id

           }

      this.axios.post('/consulting/markhighestpharmacist', datum)
          .then(response => {
               console.log(response.data);
              this.pharmacies = response.data;
             this.showTable = false;
           this.showSecondTable = false;
            this.showSecondTable = true;

               })
                .catch(res => {
                       alert(res.response.data.message)
                        console.log(res);
                    })


      },

}
}
</script>

<style>

</style>


  