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
            
           <span  style="float:right;margin:15px">
                    
                   <div class="input-group mb-3">
  <input type="text" v-model="pharmacyName" class="form-control" placeholder="Search by pharmacy name" aria-label="Search by pharmacy name" aria-describedby="basic-addon2">
  <div class="input-group-append">
    <button class="btn btn-outline-secondary" type="button"  v-on:click = "searchName(pharmacyName)" >Search</button>
  </div>
</div>

            
                </span>

                  <span  style="float:right;margin:15px">
                    
                   <div class="input-group mb-3">
  <input type="text" v-model="pharmacyCity" class="form-control" placeholder="Search pharmacy by city" aria-label="Search pharmacy by city" aria-describedby="basic-addon2">
  <div class="input-group-append">
    <button class="btn btn-outline-secondary" type="button"  v-on:click = "searchCity(pharmacyCity)" >Search</button>
  </div>
</div>

            
                </span>
             
            
        </div>


<template>
              <div class="row">
                 <div> <label> Sort by </label></div>
              <div class="custom-control custom-radio form-group col ">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample1" name="groupOfDefaultRadios" v-on:click="from1to5">
                   <label class="custom-control-label" for="defaultGroupExample1">Mark from 1 to 5</label>
              </div>

                    <div class="custom-control custom-radio form-group col ">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample2" name="groupOfDefaultRadios" v-on:click="from5to1">
                   <label class="custom-control-label" for="defaultGroupExample2">Mark from 5 to 1</label>

                  </div>
                
                   <div class="custom-control custom-radio form-group col ">  
                  <input type="radio" class="custom-control-input" id="defaultGroupExample3" name="groupOfDefaultRadios" v-on:click="cityfromatoz">
                 <label class="custom-control-label" for="defaultGroupExample3">City from A to Z</label>
                   </div>
        
                 
                   <div class="custom-control custom-radio form-group col ">  
                  <input type="radio" class="custom-control-input" id="defaultGroupExample4" name="groupOfDefaultRadios" v-on:click="cityfromztoa" >
                 <label class="custom-control-label" for="defaultGroupExample4" >City from Z to A</label>
                   </div>
                 
                   
                   <div class="custom-control custom-radio form-group col ">  
                  <input type="radio" class="custom-control-input" id="defaultGroupExample5" name="groupOfDefaultRadios" v-on:click="namefromatoz" >
                 <label class="custom-control-label" for="defaultGroupExample5" >Name from A to Z</label>
                   </div>

                    <div class="custom-control custom-radio form-group col ">  
                  <input type="radio" class="custom-control-input" id="defaultGroupExample6" name="groupOfDefaultRadios" v-on:click="namefromztoa" >
                 <label class="custom-control-label" for="defaultGroupExample6" >Name from Z to A</label>
                   </div>

              </div>
             </template>


<template>
              <div class="row">
                 <div> <label> Filter by mark from: </label></div>
              <div class="custom-control custom-radio form-group col ">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample7" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'1')">
                   <label class="custom-control-label" for="defaultGroupExample7">1-2</label>
              </div>

                    <div class="custom-control custom-radio form-group col ">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample8" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'2')">
                   <label class="custom-control-label" for="defaultGroupExample8">2-3</label>

                  </div>
                
                   <div class="custom-control custom-radio form-group col ">  
                  <input type="radio" class="custom-control-input" id="defaultGroupExample9" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'3')">
                 <label class="custom-control-label" for="defaultGroupExample9">3-4</label>
                   </div>
        
                 
                   <div class="custom-control custom-radio form-group col ">  
                  <input type="radio" class="custom-control-input" id="defaultGroupExample10" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'4')" >
                 <label class="custom-control-label" for="defaultGroupExample10" >4-5</label>
                   </div>
                 

              </div>
             </template>


 <div  v-for="dermatologistAppointment in this.dermatologistAppointments"  v-bind:key="dermatologistAppointment.id">
       <div id="customers" v-if="showTable"  style="background: whitesmoke; border: 3px solid #0D184F; height: 200px; width:1000px; margin-left:300px; margin-top: 20px">
          
        
 <router-link :to="{ path: '/pharmacyProfilePatient/'+dermatologistAppointment.id}" v-slot="{href, navigate}">
<table style = "" id="table1" class="table" :href="href" @click="navigate"  elevation="1">
 
 
    <tbody>
    <tr>
      <th scope="row"></th>
      <td>Pharmacy name</td>
      <td>{{dermatologistAppointment.pharmacyName}} </td>
    
    </tr>
    <tr>
      <th scope="row"></th>
      <td>Address</td>
      <td>{{dermatologistAppointment.postalCode}} {{dermatologistAppointment.city}} {{dermatologistAppointment.country}}, {{dermatologistAppointment.street}} {{dermatologistAppointment.number}}</td>

    </tr>
    <tr>
      <th scope="row"></th>
      <td>Mark</td>
      <td>{{dermatologistAppointment.mark}} </td>
     
    </tr>
     
    
  </tbody>
</table>
             </router-link>
     


           </div>

      </div>     

 <div  v-for="dermatologistAppointment in this.dermatologistAppointmentsSearch"  v-bind:key="dermatologistAppointment.id">
           <div id="customers" v-if="showSecondTable"  style="background: whitesmoke; border: 3px solid #0D184F; height: 200px; width:1000px; margin-left:300px; margin-top: 20px">
          
        
 <router-link :to="{ path: '/pharmacyProfilePatient/'+dermatologistAppointment.id}" v-slot="{href, navigate}">
<table style = "" id="table2" class="table" :href="href" @click="navigate"  elevation="1">
 
 
    <tbody>
    <tr>
      <th scope="row"></th>
      <td>Pharmacy name</td>
      <td>{{dermatologistAppointment.pharmacyName}} </td>
    
    </tr>
    <tr>
      <th scope="row"></th>
      <td>Address</td>
      <td>{{dermatologistAppointment.postalCode}}  {{dermatologistAppointment.city}}  {{dermatologistAppointment.country}}, {{dermatologistAppointment.street}} {{dermatologistAppointment.number}}</td>

    </tr>
    <tr>
      <th scope="row"></th>
      <td>Mark</td>
      <td>{{dermatologistAppointment.mark}} </td>
     
    </tr>
     
    
  </tbody>
</table>
             </router-link>
     


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
       dermatologistAppointments: [],
       dermatologistAppointment : null,
       pharmacyName: null,
       dermatologistAppointmentsSearch: [],
       showTable: true,
       pharmacyCity: null,
       showSecondTable : false,
       filter: null

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
              
            
         }).catch(res => {
                       alert("Please, log in first!");
                          window.location.href = "/login";
                        console.log(res);
                 });
        

    this.axios.get('/pharmacy/all')
          .then(response => {
                this.dermatologistAppointments= response.data;
               console.log(this.dermatologistAppointments);
              
          })
          .catch(res => {
                     
                        alert("There are no pharmacies in the system yet.")
                         console.log(res);
                    })

},

  methods:{
medicationReservation : function(){
             window.location.href = "/medicationReservation";
      },
    updateFiler : function(event, filter) {
        this.axios.get('/pharmacy/filterMark/'+filter)
          .then(response => {
               console.log(response.data);
                this.dermatologistAppointments = response.data;
              this.showTable = false;
              this.showTable = true;
               })
                .catch(res => {
                     
                        alert("Unable to apply filter right now.");
                        console.log(res)
                    })

      },
     
         searchName: function(pharmacyName){
           
             this.pharmacyName = pharmacyName
      this.axios.get('/pharmacy/searchName/'+ this.pharmacyName)
          .then(response => {
              this.showTable = false;
              this.showSecondTable = true;
                this.dermatologistAppointmentsSearch= response.data;
               console.log(this.dermatologistAppointmentsSearch);

                if(this.dermatologistAppointmentsSearch.length == null){
                     this.showSecondTable = false;
                }
              
           })
                .catch(res => {
                     
                       alert(res.response.data.message);
                        console.log(res)
                    })
      },

      searchCity: function(pharmacyCity){
           
             this.pharmacyCity = pharmacyCity
      this.axios.get('/pharmacy/searchCity/'+ this.pharmacyCity)
          .then(response => {
              this.showTable = false;
              this.showSecondTable = true;
                this.dermatologistAppointmentsSearch= response.data;
               console.log(this.dermatologistAppointmentsSearch);

                if(this.dermatologistAppointmentsSearch.length == null){
                     this.showSecondTable = false;
                }
              
           })
                .catch(res => {
                     
                       alert(res.response.data.message);
                        console.log(res)
                    })
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
      
       hideModal() {
        this.$refs['my-modal'].hide()
      },
     
     
      from1to5: function(){
      this.axios.get('/pharmacy/from1to5')
          .then(response => {
               console.log(response.data);
              this.dermatologistAppointments = response.data;
              this.showTable = false;
              this.showTable = true;
                })
                .catch(res => {
                     
                        alert("Unable to apply filter right now.");
                        console.log(res)
                    })


      },

      
      from5to1: function(){
   
      this.axios.get('/pharmacy/from5to1')
          .then(response => {
               console.log(response.data);
                this.dermatologistAppointments = response.data;
              this.showTable = false;
              this.showTable = true;
               })
                .catch(res => {
                     
                        alert("Unable to apply filter right now.");
                        console.log(res)
                    })


      },

           cityfromatoz: function(){
   
      this.axios.get('/pharmacy/cityfromatoz')
          .then(response => {
               console.log(response.data);
                   this.dermatologistAppointments = response.data;
              this.showTable = false;
              this.showTable = true;
               })
                .catch(res => {
                     
                      alert("Unable to apply filter right now.");
                        console.log(res)
                    })


      },
  
        cityfromztoa: function(){
   
      this.axios.get('/pharmacy/cityfromztoa')
          .then(response => {
               console.log(response.data);
                   this.dermatologistAppointments = response.data;
              this.showTable = false;
              this.showTable = true;
                })
                .catch(res => {
                     
                       alert("Unable to apply filter right now.");
                        console.log(res)
                    })

      },
      namefromatoz: function(){
   
      this.axios.get('/pharmacy/namefromatoz')
          .then(response => {
               console.log(response.data);
                  this.dermatologistAppointments = response.data;
              this.showTable = false;
              this.showTable = true;
               })
                .catch(res => {
                     
                        alert("Unable to apply filter right now.");
                        console.log(res)
                    })


      },

            namefromztoa: function(){
   
      this.axios.get('/pharmacy/namefromztoa')
          .then(response => {
               console.log(response.data);
                  this.dermatologistAppointments = response.data;
              this.showTable = false;
              this.showTable = true;
                })
                .catch(res => {
                     
                        alert("Unable to apply filter right now.");
                        console.log(res)
                    })


      },
  }

}

</script>

<style>
#div1 {
    display: inline-block;
}

</style>