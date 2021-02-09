<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                    <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Home</button>
                   <strong class="tab"></strong>  
                    <strong class="tab"></strong>

                    <button class = "btn btn-link btn-lg" v-on:click = "showMyProfile">My profile</button>

                    <strong class="tab"></strong>  
                    <strong class="tab"></strong>

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "showClients">My clients</button>
                    <strong class="tab"></strong>  
                    <strong class="tab"></strong>    

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "workCalendar">Work calendar</button>
                    <strong class="tab"></strong>  
                    <strong class="tab"></strong>

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "vacation">Create a vacation</button>
                    <strong class="tab"></strong>  
                    <strong class="tab"></strong>
                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "showMedications">Medications</button>
                    <strong class="tab"></strong>  
                    <strong class="tab"></strong>
                     <button class = "btn btn-info btn-lg" v-on:click = "writeConsulting">Add consultings</button>
             <b class="tab"></b>   

            <button class = "btn btn-info btn-lg" v-on:click = "addMedicine">Sell reserved medicines</button>
             <b class="tab"></b>   

            <button class = "btn btn-info btn-lg" v-on:click = "scheduleConsulting">Schedule new consulting</button>
                
             
            </span>
              <span  style="float:right;margin:15px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

             </span>

        </div>

        <div class = "container  md-4 p-2">

        <br><br>

        <div class= "row justify-content-md-center md-4 p-2">
            <label style = "font-size: 25px"> Choose a patient who is now in counseling:</label>
             <b class="tab"></b>  
                    <b class="tab"></b>  
                   <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose patient" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                    <b-dropdown-item v-for="patient in this.patients"  v-on:click ="patientIsSelected($event, patient)" v-bind:key="patient.id"> {{patient.name}} {{patient.surname}} </b-dropdown-item>
                    </b-dropdown> 
                
        </div>  

                <br><br>
                <div class="row justify-content-md-center md-2 p-2">

                                          <div class="col-25">
                                          <label style="font-size: 20px;">Start time:</label>
                                          </div>
                                          <b class="tab"></b> 
                                          <div class="col-75">
                                          <input type="time" style="font-size: 20px;" size="80" placeholder="Enter time.." v-model="startTime">
                                          </div>

                                          

             </div>
                                        
                                <br><br>
                                        <div class="row justify-content-md-center md-2 p-2">

                                          <div class="col-25">
                                          <label style="font-size: 20px;">Date of consulting:</label>
                                          </div>
                                          <b class="tab"></b> 
                                          <div class="col-75">
                                          <input type="date" style="font-size: 20px;" size="80" placeholder="Enter date of consulting.." v-model="date">
                                          </div>

                                         </div>
                                         <br><br><br>
                    <div class = "row justify-content-md-center">
                        
                         <button class = "btn btn-danger md-2 p-2" v-on:click="send">Scheduling consulting</button>
                    </div>
    </div>
</div>
</template>

<script>
export default {

  data() {
    return {
      
       patients : [],
       patient: {},
       pharmacist : {},
    
        date: null,
        startTime : null,
        duration : 15,
        price: 0,
        canceled : false,
        showedUp : false,
        information : "",
    }
  },
   mounted(){
     let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/pharmacist/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.pharmacist=response.data;
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });

    this.axios.get('/patient')
      .then(response => {
        console.log(response.data)
          this.patients = response.data;
          console.log(response);
      })
    
   },


  methods:{
        showclients : function(){
         window.location.href = "/pharmacistClients";
      },
      showMyProfile: function(){
         window.location.href = "/pharmacistProfile";
      },
      showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      workCalendar : function(){
       window.location.href = "/workingHoursPharmacist";


      },
      vacation: function(){
           window.location.href = "/createRequestVacation";
      },
     
      logOut : function(){
        localStorage.removeItem('token');
          window.location.href = "/login";

      },
      writeConsulting() {
        window.location.href = "/noteConsultig";
      },
        scheduleConsulting : function(){
             window.location.href = "/schedulingConsulting";
      },
     
      send : function() {

          const consulting = {
            pharmacistId : this.pharmacist.id,
            patientId : this.patient.id,
            date : this.date,
            startTime : this.startTime,
            duration : 15,
            price : 0,
             cancelled : false,
             showedUp : false,
             information : ""
          }
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
          console.log(token)
           this.axios.post('/consulting/add',consulting,{ 
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
      },
      sendComplaint : function(){

      },
       
      addMedicine : function(){
          this.$refs['my-modal1'].show()

      },
      patientIsSelected : function(event, patient) {
        this.patient = patient;
      },
      showMedications : function() {
          window.location.href = "/pharmacistMedicationSearch";
      },
  }
}

</script>

<style>

</style>