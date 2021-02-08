<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                    <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Home</button>
                    <b class="tab"></b>  
                    <b class="tab"></b>  
                    <b class="tab"></b>  

                    <button class = "btn btn-link btn-lg" v-on:click = "showMyProfile">My profile</button>

                    <b class="tab"></b>     
                    <b class="tab"></b>             
                   

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "showclients">My clients</button>
                    <b class="tab"></b>  
                    <b class="tab"></b>      

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "workCalendar">Work calendar</button>
                    <b class="tab"></b>
                    <b class="tab"></b>  

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "vacation">Create a vacation</button>
                    <b class="tab"></b>
                      <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "showMedications">Medications</button>
                    <strong class="tab"></strong>
                    
  <button class = "btn btn-info btn-lg" v-on:click = "writeExamination">My examination</button>
             <b class="tab"></b>   
 
            <button class = "btn btn-info btn-lg" v-on:click = "schedule">Schedule new examination</button>
                
            </span>
              <span  style="float:right;margin:15px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

             </span>
    </div>
   <div class="container-fluid">
                    <b class="tab"></b>     
                    <b class="tab"></b>  
                     <div style="height:45px"></div>
                         <h3>Examination schedule of dermatologist</h3>

                                        <table class="table table-striped table-dark">
                                        <thead class="thead-dark">
                                          <tr>
                                            <th scope="col"></th>
                                            <th scope="col">Pharmacy </th>
                                            <th scope="col">Date </th>
                                            <th scope="col">Start time</th>
                                            <th scope="col">Duration</th>
                                            <th scope="col">Price:</th>
                                            <th scope="col">Note:</th>
                                            
                                          </tr>
                                        </thead>
                                        <tbody>
                                          <tr v-for="examinationSchedule in myExaminationSchedule" :key="examinationSchedule.id">
                                                    <td></td>
                                                    <td>{{examinationSchedule.pharmacy}}</td>
                                                    <td>{{examinationSchedule.date}}</td>
                                                    <td>{{examinationSchedule.startTime}}</td>
                                                    <td>{{examinationSchedule.duration}}</td>
                                                    <td>{{examinationSchedule.price}}</td>
                                                    <td><button class="btn btn-primary btn-lg" v-on:click="Add ($event, examinationSchedule)">Reserve examination</button></td>
                                                  
                                                </tr>
   
  </tbody>
</table>
</div>
<div>
    <b-modal ref="my-modal1" hide-footer scrollable title="Write complaint" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                      <div class="modal-header">
                                      
                                      <h3>Reserve examination </h3>
                                      </div>
                                      <div class="modal-body">
                                      
                                        <div class="container">
                                          <div class="col">
                                            <label style="font-size:20px">Choose patient</label>
                                          </div>
                                          <div class="col">
                                             <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose patient" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                                            <b-dropdown-item v-for="patient in this.patients"  v-on:click ="patientIsSelected($event, patient)" v-bind:key="patient.id"> {{patient.name}} {{patient.surname}} </b-dropdown-item>
                                            </b-dropdown> 
                                          </div>
                                           <b class="tab"></b> 
                                           
                                          
                                        </div>
                                      </div>
                                      <div class="modal-footer">
                                         <button class="btn btn-secondary">Close</button>
                                        <button class="btn btn-primary" v-on:click="Reserve">Reserve</button>
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
      dermatologist : "",
      myExaminationSchedule : "",
      examinationSchedule : {
            dermatologistFirst : this.dermatologistFirst,
            DermatologistLast : this.DermatologistLast,
              date : this.date,
              startTime : this.startTime,
              duration : this.duration,
              price : this.price
      },
      patient : {},
      patients : [],
      selectedTerm: {}
      
      

    }
  },
   mounted(){
      let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/dermatologist/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.dermatologist=response.data;
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });
  
             this.axios.get('/dermatologist/examinationSchedule',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.myExaminationSchedule = response.data;
                            alert(response.data);
                           
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
      showMyProfile: function(){
         
      },
        showclients : function(){

      },
        workCalendar: function(){
         
      },
        vacation: function(){
         
      },
       search: function(){
         
      },
      writeExamination() {
       window.location.href = "/noteExamination";
      },
     schedule : function(){
              window.location.href = "/schedulingExamination";
      },

       Add: function(event, examinationSchedule){
         this.selectedTerm = examinationSchedule;
         this.$refs['my-modal1'].show()

      },
       cancel: function(){
         
      },
      Reserve: function(){
        const data = {
          patientId : this.patient.id,
          examinationTermId : this.selectedTerm.id
        }
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
          console.log(token)
           this.axios.post('/examination/add', data,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Examination is successfully created!");
                        console.log(response.data);
                })
                .catch(response => {
                       alert("Please try later.");
                        console.log(response);
                 });   
         
      },
      showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      logOut : function(){
          window.location.href = "/login";

      },
      sendComplaint : function(){

      },
       patientIsSelected : function(event, patient) {
        this.patient = patient;
      },
       showMedications : function() {
          window.location.href = "/dermatologistMedicationSearch";
      },
  }
}

</script>

<style>

</style>