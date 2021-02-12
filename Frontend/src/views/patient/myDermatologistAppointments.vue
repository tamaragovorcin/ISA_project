ines (39 sloc)  1.61 KB
  
<template>
  <div vif="auth" id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
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

<template>
              <div class="row">
                 <div> <label> Sort by </label></div>
              <div class="custom-control custom-radio form-group col ">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample1" name="groupOfDefaultRadios" v-on:click="from1to5">
                   <label class="custom-control-label" for="defaultGroupExample1">Date closest</label>
              </div>

                    <div class="custom-control custom-radio form-group col ">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample2" name="groupOfDefaultRadios" v-on:click="from5to1">
                   <label class="custom-control-label" for="defaultGroupExample2">Date furthest</label>

                  </div>
                
                   <div class="custom-control custom-radio form-group col ">  
                  <input type="radio" class="custom-control-input" id="defaultGroupExample3" name="groupOfDefaultRadios" v-on:click="cityfromatoz">
                 <label class="custom-control-label" for="defaultGroupExample3">Cheapest</label>
                   </div>
        
                 
                   <div class="custom-control custom-radio form-group col ">  
                  <input type="radio" class="custom-control-input" id="defaultGroupExample4" name="groupOfDefaultRadios" v-on:click="cityfromztoa" >
                 <label class="custom-control-label" for="defaultGroupExample4" >Most expensive</label>
                   </div>
                 
                   
                   <div class="custom-control custom-radio form-group col ">  
                  <input type="radio" class="custom-control-input" id="defaultGroupExample5" name="groupOfDefaultRadios" v-on:click="namefromatoz" >
                 <label class="custom-control-label" for="defaultGroupExample5" >Duration - short first</label>
                   </div>

                    <div class="custom-control custom-radio form-group col ">  
                  <input type="radio" class="custom-control-input" id="defaultGroupExample6" name="groupOfDefaultRadios" v-on:click="namefromztoa" >
                 <label class="custom-control-label" for="defaultGroupExample6" >Duration - long first</label>
                   </div>

              </div>
             </template>


      <div style="background: whitesmoke; border: 3px solid #0D184F; height: 400px; width:1000px; margin-left:300px; margin-top: 20px"  v-for="dermatologistAppointment in this.dermatologistAppointments"  v-bind:key="dermatologistAppointment.id">
       

<table style = "" class="table table-dark">
 
 
    <tbody>
      <tr>
       <div v-if="dermatologistAppointment.finished" >
          <th scope="row" style="col-span =2; color:red"> FINISHED </th>
        </div>

         <div v-else >
          <th scope="row" style="col-span =2; color:green"> UPCOMING </th>
        </div>
      </tr>

    <tr>
    
      <td>Dermatologists name:</td>
       <th scope="row"></th>
      <td>{{dermatologistAppointment.dermatologistFirst}} {{dermatologistAppointment.dermatologistLast}}</td>
    
    </tr>
    <tr>
     
      <td>Pharmacy:</td>
       <th scope="row"></th>
      <td>{{dermatologistAppointment.pharmacy}} </td>

    </tr>
    <tr>
     
      <td>Date:</td>
       <th scope="row"></th>
      <td>{{dermatologistAppointment.date}} </td>
     
    </tr>
        <tr>
   
      <td>Start time:</td>
       <th scope="row"></th>
      <td>{{dermatologistAppointment.startTime}} </td>
     
    </tr>
        <tr>
      
      <td>Duration:</td>
       <th scope="row"></th>
      <td>{{dermatologistAppointment.duration}} </td>
     
    </tr>
        <tr>
   
      <td>Price:</td>
       <th scope="row"></th>
      <td>{{dermatologistAppointment.price}} </td>
     
  
    </tr>
     <tr>   <th scope="row"></th>
     <td>
        <div v-if="dermatologistAppointment.finished" >
     
     </div>
      <div v-else>
         <button class = "btn btn-outline-primary" style="color:white; col-span =2 " v-on:click = "reserve($event, dermatologistAppointment)">Cancel this reservation</button>
      </div>
      </td></tr>
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
       dermatologistAppointment: null,
       dermatologistAppointments : [],
       patient: null,
       auth:false

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
                 console.log( this.patient);
                 this.auth = true;
                 this.axios.get('/pharmacy/cancelExamination/'+this.patient.id,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,}

             })
                    .then(response => {
                this.dermatologistAppointments= response.data;
               console.log(this.dermatologistAppointments);
              
                          }).catch(res => {
                                      alert(res.data.message);
                                              
                                                console.log(res);
                
               
                          })
         }).catch(res => {
                       alert("Please log in first!");
                       this.auth = false;
                                 window.location.href = "/login";
                                 console.log(res);
                
                 })
                 


      

},
  methods:{
      from1to5: function(){
      this.axios.get('/pharmacy/dateClosest/' + this.patient.id)
          .then(response => {
               console.log(response.data);
              this.dermatologistAppointments = response.data;
              this.showTable = false;
              this.showTable = true;
               })
                .catch(res => {
                     
                        console.log(res);
                    })


      },

      
      from5to1: function(){
   
      this.axios.get('/pharmacy/dateFurthest/' + this.patient.id)
          .then(response => {
               console.log(response.data);
                this.dermatologistAppointments = response.data;
              this.showTable = false;
              this.showTable = true;
               })
                .catch(res => {
                     
                        console.log(res);
                    })


      },

           cityfromatoz: function(){
   
      this.axios.get('/pharmacy/sortpricelowest/' + this.patient.id)
          .then(response => {
               console.log(response.data);
                   this.dermatologistAppointments = response.data;
              this.showTable = false;
              this.showTable = true;
               })
                .catch(res => {
                     
                        console.log(res);
                    })


      },
  
        cityfromztoa: function(){
   
      this.axios.get('/pharmacy/sortpricehighest/' + this.patient.id)
          .then(response => {
               console.log(response.data);
                   this.dermatologistAppointments = response.data;
              this.showTable = false;
              this.showTable = true;
               })
                .catch(res => {
                     
                        console.log(res);
                    })


      },
      namefromatoz: function(){
   
      this.axios.get('/pharmacy/durationShortest/' + this.patient.id)
          .then(response => {
               console.log(response.data);
                  this.dermatologistAppointments = response.data;
              this.showTable = false;
              this.showTable = true;
               })
                .catch(res => {
                     
                        console.log(res);
                    })


      },

            namefromztoa: function(){
   
      this.axios.get('/pharmacy/durationLongest/' + this.patient.id)
          .then(response => {
               console.log(response.data);
                  this.dermatologistAppointments = response.data;
              this.showTable = false;
              this.showTable = true;
               })
                .catch(res => {
                     
                        console.log(res);
                    })


      },
    
         allPharmacies: function(){
        window.location.href = "/myProfilePatient";
      },
      allDermatologists : function(){
           window.location.href = "/dermatologistAppointments";
      },
      allPharmacists : function(){
          window.location.href = "/PharmacistConsultation";

      },
      showMyProfile: function(){
        window.location.href = "/myProfilePatient";
      },
      showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      logOut : function(){
           localStorage.removeItem('token');
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

      },
      myDermatologistAppointments: function(){
            window.location.href = "/myDermatologistAppointments";

      },

         reserve : function(event, dermatologistAppointment) {
                     let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
          this.dermatologistAppointment = dermatologistAppointment
        
            this.axios.get('/pharmacy/cancel/'+dermatologistAppointment.id,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,}

             }).then(response => {
               alert(response.data);
                  window.location.href = "/myDermatologistAppointments";
               })
                .catch(res => {
                     
                       alert(res.data);
                    })


      },
}
}
</script>

<style>

</style>


  