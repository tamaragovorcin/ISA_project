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

<template>
              <div class="row">
                 <div> <label> Sort by </label></div>
              <div class="custom-control custom-radio form-group col ">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample1" name="groupOfDefaultRadios" v-on:click="pricelowest">
                   <label class="custom-control-label" for="defaultGroupExample1">Price - lowest</label>
              </div>

                    <div class="custom-control custom-radio form-group col ">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample2" name="groupOfDefaultRadios" v-on:click="pricehighest">
                   <label class="custom-control-label" for="defaultGroupExample2">Price - highest</label>

                  </div>
                
                   <div class="custom-control custom-radio form-group col ">  
                  <input type="radio" class="custom-control-input" id="defaultGroupExample3" name="groupOfDefaultRadios" v-on:click="dermmarklowest">
                 <label class="custom-control-label" for="defaultGroupExample3">Dermatologist mark - lowest</label>
                   </div>
        
                 
                   <div class="custom-control custom-radio form-group col ">  
                  <input type="radio" class="custom-control-input" id="defaultGroupExample4" name="groupOfDefaultRadios" v-on:click="dermmarkhighest" >
                 <label class="custom-control-label" for="defaultGroupExample4" >Dermatologist mark - highest</label>
                   </div>
                 
                   
              </div>
             </template>
      

 <div  v-for="dermatologistAppointment in this.dermatologistAppointments"  v-bind:key="dermatologistAppointment.id">
       <div id="customers" v-if="showTable"  style="background: whitesmoke; border: 3px solid #0D184F; height: 350px; width:1000px; margin-left:300px; margin-top: 20px">
          

<table style="" class="table table-dark">
 
 
    <tbody>
    <tr>
    
      <td>Dermatologists name:</td>
       <th scope="row"></th>
      <td>{{dermatologistAppointment.dermatologistFirst}} {{dermatologistAppointment.dermatologistLast}}</td>
    
    </tr>
    <tr>
     
      <td>Pharmacy name:</td>
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
     <td><button class = "btn btn-outline-primary" style="color:white; col-span =2 " v-on:click = "reserve($event, dermatologistAppointment)">Make a reservation</button></td></tr>
  </tbody>

  
</table>
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
       dermatologistAppointment: null,
       dermatologistAppointments : [],
       patient: null,
       showTable : true

    }
  },
mounted() {

    this.axios.get('/pharmacy/ExaminationSchedule')
          .then(response => {
                this.dermatologistAppointments= response.data;
               console.log(this.dermatologistAppointments);
              
          }).catch(res => {
                       alert(res.response.data.message);
                
                 });

          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/patient/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,

             }
         }).then(response => {
                this.patient = response.data;
         
         }).catch(res => {
                       alert("Please log in first!");
                                 window.location.href = "/login";
                                 console.log(res);
                
                 });


    
  },
  methods:{
     
       myDermatologistAppointments: function(){
            window.location.href = "/myDermatologistAppointments";

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
    
       showPharmacies : function(){
            window.location.href = "/showPharmaciesPatient";

      },
    

         reserve : function(event, dermatologistAppointment) {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
          this.dermatologistAppointment = dermatologistAppointment
         
          const examination = {
              patient: this.patient,
              cancelled : false,
              showedUp: false,
              examinationId: this.dermatologistAppointment.id,
              information: null



          }
            this.axios.post('/pharmacy/addExamination', examination,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }   }).then(response => {
                        alert(response.data)
                          window.location.href = "/dermatologistAppointments";
                         }).catch(res => {
                       alert(res.data);
                               
                                 console.log(res);
                
                 });



      },

   pricelowest: function(){
          
      this.axios.get('/examination/pricelowest')
          .then(response => {
               console.log(response.data);
              this.dermatologistAppointment = response.data;
              this.showTable = false;
              this.showTable = true;
               })
                .catch(res => {
                       alert(res.response.data.message)
                        console.log(res);
                    })


      },
       pricehighest: function(){
          
      this.axios.get('/examination/pricehighest')
          .then(response => {
               console.log(response.data);
              this.dermatologistAppointment = response.data;
              this.showTable = false;
              this.showTable = true;
               })
                .catch(res => {
                       alert(res.response.data.message)
                        console.log(res);
                    })


      },

        dermmarklowest: function(){
          
      this.axios.get('/examination/dermmarklowest')
          .then(response => {
               console.log(response.data);
              this.dermatologistAppointment = response.data;
              this.showTable = false;
              this.showTable = true;
               })
                .catch(res => {
                       alert(res.response.data.message)
                        console.log(res);
                    })


      },

        dermmarkhighest: function(){
          
      this.axios.get('/examination/dermmarkhighest')
          .then(response => {
               console.log(response.data);
              this.dermatologistAppointment = response.data;
              this.showTable = false;
              this.showTable = true;
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
