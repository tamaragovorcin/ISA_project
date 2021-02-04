ines (39 sloc)  1.61 KB
  
<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Home</button>
                   <strong class="tab"></strong>               

                    <button class = "btn btn-link btn-lg" v-on:click = "showPharmacies">Pharmacies</button>

                  <strong class="tab"></strong>     
                   <button class = "btn btn-link btn-lg" v-on:click = "showMyProfile">My profile</button>

                  <strong class="tab"></strong>                   
                   

   

                    <button class = "btn btn-link btn-lg" style="margin-right:20px;" v-on:click = "writeComplaint">Write complaint</button>
                    <strong class="tab"></strong>    
            </span>
              <span  style="float:right;margin:15px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

                </span>

        </div>

        
        <div style="background: lightgray; height: 60px;">
            
            <span style="float: left; margin: 15px;">
                <strong><button class = "btn btn-link btn-lg" style=" color:black; float:left; margin-left:20px; margin-top:0px;" v-on:click = "allPharmacies">Show all pharmacies</button></strong>
                        

                    <button class = "btn btn-link btn-lg" style="color:black; " v-on:click = "allDermatologists">Make a new dermatologist appointment</button>

                        
                     <button class = "btn btn-link btn-lg" style="color:black; " v-on:click = "myDermatologistAppointments">My dermatologist appointments</button>

                             
                   <button class = "btn btn-link btn-lg" style="color:black; " v-on:click = "allPharmacists">Pharmacist consultation</button>
              
                   <button class = "btn btn-link btn-lg" style="color:black; " v-on:click = "myPharmacistsConsultation">My pharmacist consultation</button>
                     
                   
            </span>
             

        </div>

        <div style="background: white; height: 60px; margin-top: 20px">
            
           <span  style="float:right;margin:15px">
                    
                    <input type="textarea" style="height:20;width:150;background-color:white;" placeholder="Search pharmacy by name">
                     
                   <button class = "btn btn-link btn" style="color:black; " v-on:click = "searchName">Search</button>

            
                </span>
             
            
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

    <table id="table1" class="table">
                    <thead  class="thead-dark">
                        <tr style="font-weight: bold; ">
                            <th style="width: 200px; height: 80px;" scope="col">Name of pharmacy</th>
                            <th style="width: 200px; height: 80px;" scope="col">Location</th>

                            <th style="width: 200px; height: 80px;" scope="col">Pharmacy Mark</th>
                            <th style="width: 200px; height: 80px;" scope="col">Consultation mark</th>
                       

                        </tr>
                    </thead>
                    <tbody>
                      
                        <tr  v-for="pharmacy in pharmacies" :key="pharmacy.id" v-on:click="goToBlabla(pharmacy)">
                            <td>{{pharmacy.pharmacyName}}</td>
                            <td>{{pharmacy.city}}</td>
                            <td>{{pharmacy.mark}}</td>
                            <td></td>
                        </tr>
                      
                    </tbody>
                </table>


        </div>



         <div id="customers" v-if="showSecondTable" style="background: whitesmoke; border: 3px solid #0D184F; height: 350px; width:1000px; margin-left:300px; margin-top: 20px">

    <table id="table2"  class="table">
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
       date: null,
       startTime: null,
        showTable: false,
                showTableAvailability : false,
      showSecondTable : false

    }
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
            makeAReservation : function(){

      window.location.href = "/showReservationPharmacies/";
 
        },
       showPharmacies : function(){

         
            window.location.href = "/showPharmaciesPatient";

      },

      showAvailability: function (date, startTime) {
          this.date = date;
          this.startTime = startTime;

            const datum = {
                date : this.date,
                time : this.startTime

           }
            console.log(datum)
            this.axios.post('/consulting/getPharmacies',datum
                ).then(response => {
                    alert("Complaint is successfully sent!.");
                    this.pharmacies= response.data;
                    console.log(response);                
                }).catch(res => {
                       alert("Please try later.");
                        console.log(res);
                });

        this.showTable = true;
          
              
            }, 

    goToBlabla: function (pharmacy) {
         this.pharmacy = pharmacy;
         const datum = {
                date : this.date,
                time : this.startTime,
                pharmacyId: pharmacy.id

           }
   this.axios.post('/consulting/getPharmacists',datum
                ).then(response => {
                    alert("Complaint is successfully sent!.");
                    this.pharmacists= response.data;
                  
                    console.log(response);                
                }).catch(res => {
                       alert("Please try later.");
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
   this.axios.post('/consulting/reserveConsultation',datum
                ).then(response => {
                    alert("Complaint is successfully sent!.");
                   
                    console.log(response);                
                }).catch(res => {
                       alert("Please try later.");
                        console.log(res);
                });


                 this.showTable = false;
           this.showSecondTable = true;
              
            }, 
}
}
</script>

<style>

</style>


  