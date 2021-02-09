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

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "showclients">My clients</button>
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

                
            </span>
              <span  style="float:right;margin:15px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

             </span>
    </div>
     <div style="background: white; height: 60px; margin-top: 20px">
          <span  style="float:right;margin:15px">
            <div class="input-group mb-3">
              <input type="text" v-model="patientName" class="form-control" placeholder="Patient name..." aria-label="Enter name..." aria-describedby="basic-addon2">
              <input type="text" v-model="patientSurname" class="form-control" placeholder="Patient surname..." aria-label="Enter surname..." aria-describedby="basic-addon2">

                 <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button"  v-on:click = "showByPharmacistName" >Search</button>
                  </div>
            </div>
          </span>
     </div>

     <div style="height:25px"></div>
      <h3>Patients</h3>

          <table class="table table-striped table-dark">
            <thead class="thead-info">
              <tr>
                <th scope="col"></th>
                <th scope="col">Name</th>
                <th scope="col">Surname</th>
                <th scope="col">Email</th>
                 <th scope="col">Phone number</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="pat in ourPatients" :key="pat.idExamination">
                <td></td>
                <td>{{pat.firstname}}</td>
                <td>{{pat.surname}}</td>
                 <td>{{pat.email}}</td>
                <td>{{pat.phonenumber}}</td>
                <td><button  v-on:click ="lookHistory($event, pat.id)" class="btn btn-info">Look history</button></td>
              </tr>
            </tbody>
          </table>
     
      <div> 
          <b-modal ref="specification-modal" hide-footer scrollable title="Medication specification" size="lg" modal-class="b-modal">
               <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:whitesmoke">
                         <div class="modal-body">
                          
                          {{history.namePatient}}
                        
                                                        
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
      
        patientName : "",
        patientSurname : "",
       patients : [],
        name : "",
        surname : "",
        email : "",
        phoneNumber : "",
        town : "",
        street : "",
        number : "",
        postalCode : "",
        country : "",
        showMedicationListInfoDiv: false,
        medicationSeacrhList : [],
         ourPatients: [],
         consulting: {
       patient : {},
       dermatologist : "", 
       date: "",
       startTime: "",
       duration: "", 
       price: "",
       canceled: false,
       showedUp: false,
       information: ""
       },
       history : "",
       idExamination : ""
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
      
	

      this.axios.get('/dermatologist/myPatients',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.ourPatients = response.data;
                            
                    }).catch(res => {
                            alert("NOT OK");
                            console.log(res);
                    });


         
    },
 
     methods:{
        showClients : function(){
            window.location.href = "/dermatologistClients";
      },
      showMyProfile: function(){
           window.location.href = "/dermatologistProfile";
      },
      
    
       vacation: function(){
          window.location.href = "/dermatologistHoliday";

      },
       workCalendar : function(){
            window.location.href = "/workingHoursDermatologist";
      },
      remove : function(event,patient){
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.post('patient/delete', patient,{ 
                headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            console.log(response);
                          
                             
                    }).catch(response => {
                            alert("NOT OK");
                            console.log(response);
                    });
         
     },
         showByPharmacistName : function() {
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
          const data = {
            name : this.patientName,
            surname : this.patientSurname
          }
            this.axios.post('/patient/searchName',data,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
          .then(response => {
                    this.ourPatients= response.data;
            }).catch(res => {
                        alert("There is no patient with entered name.");
                        console.log(res);
                    });
      },
       cancel: function(){
         
      },
      lookHistory : function(event, id) {
         let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/patient/history', +id, { 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.history = response.data;
                          
                    }).catch(res => {
                            alert("NOT OK");
                            console.log(res);
                    });
        
        this.$refs['specificationList-modal'].show()
      },

      showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      logOut : function(){
        localStorage.removeItem('token');
          window.location.href = "/login";
      },
      showMedications : function() {
          window.location.href = "/pharmacistMedicationSearch";
      },
  }
}
</script>

<style>
</style>