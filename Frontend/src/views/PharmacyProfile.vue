<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" href = "/isaHomePage">Home page</button>
            </span>
              <span  style="float:right;margin:15px">
                    <button class = "btn btn-warning btn-lg" href = "/login">&nbsp;&nbsp;Login&nbsp;&nbsp;</button>
                    <b class="tab"></b>    
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" href = "/registration">Register</button>
                </span>

        </div>
  <div class = "col md-8">
          <h1 style="color:#0D184F;font-suze:75px;font-weight:bold;"><u>{{pharmacy.pharmacyName}}</u></h1>

        <div class = "container" v-if="welcomePageShow">
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
                                                                        
                                                                    </tr>
                      
                      </tbody>
                    </table>

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
                <a href = "/actionsAndBenefits">Pricelist</a>
                <hr/>
                <a href="/order"></a>
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
       ourDermatologists : [],
       ourMedications : [],
       ourTerms : [],
       ourPharmacists : [],
       welcomePageShow : true
       
       

    }
  },
  mounted() {
       let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
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
      },
      showPharmacists : function(){
        this.welcomePageShow = false;
          this.dermatologistsShow = false;
          this.pharmacistsShow = true;
          this.termsShow = false;
          this.medicationShow = false;

      },
        showTerms : function(){
          this.welcomePageShow = false;
          this.dermatologistsShow = false;
          this.pharmacistsShow = false;
          this.termsShow = true;
          this.medicationShow = false;

      }
      ,
        showMedication : function(){
          this.welcomePageShow = false;
          this.dermatologistsShow = false;
          this.pharmacistsShow = false;
          this.termsShow = false;
          this.medicationShow = true;
          
      },
      showWelcomePage : function(){
        this.dermatologistsShow = false;
          this.pharmacistsShow = false;
          this.termsShow = false;
          this.medicationShow = false;
          this.welcomePageShow = true;
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
