<template>
  <div  v-if="loggedIn"  id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
         <span style="float: left; margin-top: 15px;">
                              <b-dropdown id="ddCommodity" name="ddCommodity" text="My profile" 
                                              class = "btn btn-link btn-lg">
                                    <b-dropdown-item href = "/pharmacyAdminProfile">Profile</b-dropdown-item>
                                    <b-dropdown-item href = "/phAdminProfileUpdate">Update profile</b-dropdown-item>      
                                </b-dropdown>        
<a   class = "btn btn-link" href="/updatePharmacyProfile">Update pharmacy</a>
                                    <router-link :to="{ path: '/pharmacyProfile/'+pharmacy.id}" v-slot="{href, navigate}">
                                                <button class = "btn btn-link" :href="href" @click="navigate"  elevation="1">My pharmacy</button>
                                    </router-link>
                   

                                  <b-dropdown id="ddCommodity" name="ddCommodity" text="Pharmacists" 
                                                class = "btn btn-link btn-lg">
                                      <b-dropdown-item href = "/pharmacyPharmacists">Our pharmacists</b-dropdown-item>
                                      <b-dropdown-item href = "/addPharmacist">Add new pharmacist</b-dropdown-item>      
                                  </b-dropdown> 
                                  <b-dropdown id="ddCommodity" name="ddCommodity" text="Dermatologists" 
                                                  class = "btn btn-link btn-lg">
                                        <b-dropdown-item href = "/pharmacyDermatologists">Our dermatologists(Add new)</b-dropdown-item>
                                        <b-dropdown-item href = "/examinationTerms">Examination terms</b-dropdown-item>      
                                    </b-dropdown>                 
                            <a   class = "btn btn-link" href = "/pharmacyMedications">Medications</a>
                            <a   class = "btn btn-link" href = "/pharmacyAdminMedicationSearch">Medications in system</a>
                            <a  class = "btn btn-link" href = "/actionsAndBenefits">Actions and benefits</a>
                            <b-dropdown id="ddCommodity" name="ddCommodity" text="Orders" 
                                                  class = "btn btn-link btn-lg">
                                        <b-dropdown-item href = "/order">Preview orders and offers(Add new)</b-dropdown-item>
                                        <b-dropdown-item href = "/editOrder">Edit/remove order</b-dropdown-item>      
                                    </b-dropdown>                             
                            <a   class = "btn btn-link" href="/holidayRequests">Holiday/absence requests</a>
                            <b-dropdown id="ddCommodity" name="ddCommodity" text="Graphical reviews" 
                                                  class = "btn btn-link btn-lg">
                                        <b-dropdown-item href = "/examinationGraphics">Examinations</b-dropdown-item>
                                        <b-dropdown-item href = "/medicationGraphics">Medication consumption</b-dropdown-item> 
                                        <b-dropdown-item href = "/incomeGraphics">Income</b-dropdown-item>      
                                    </b-dropdown>     
                             <a   class = "btn btn-link" href="/medicationInquires">Inquires for medication</a>

            </span>
              <span  style="float:right;margin:15px">
                   
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
                </span>
        </div>
        
       
                  <div style="height:30px"></div>

        <div class="container-fluid">

         <b-button class = "btn btn-warning" @click="showModal">+ Add examination terms</b-button>
        
        <b-modal ref="my-modal" hide-footer scrollable title="Add examination to schedule" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                    
                                    <div class="modal-body">
                                    
                                            <label>Choose dermatologist:</label>
                            <div class="form-row">
                                 <div class="form-group col">
                                    <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose dermatologist"
                                        class = "btn btn-link btn-lg" style="float:left; width=200px;">
                                            <b-dropdown-item v-for="dermatologist in this.ourDermatologists"  v-on:click = "dermatologistIsSelected($event, dermatologist)" v-bind:key="dermatologist.id"> 
                                            {{ dermatologist.firstname }}&nbsp; {{dermatologist.surname }}
                                            </b-dropdown-item>
                                    </b-dropdown> 
                                 </div>
                                <div class="form-group col-md-6 ">
                                    <label style="font-size:25px;font-weight:bold;">{{this.selectedDermatologist.firstname}}&nbsp; {{this.selectedDermatologist.surname}} </label>
                                </div>
                                    
                            </div>
                                             <div class="row">
                                           <div class="col">
                                                 <label for="name">Date:</label>
                                            </div> 
                                         </div>
                                           <div class="row">
                                              <input type="date" v-model = "date" class="form-control" placeholder = "01/01/2021">
                                            </div>
                                             <div class="row">
                                           <div class="col">
                                                 <label for="name">Start time:</label>
                                            </div> 
                                         </div>
                                           <div class="row">
                                              <input type="time" v-model = "startTime" class="form-control" placeholder = "01/01/2021">
                                            </div>
                                        <div class="row">
                                           <div class="col">
                                                 <label for="name">Duration:</label>
                                            </div> 
                                         </div>
                                           <div class="row">
                                              <input type="number" v-model = "duration" class="form-control">
                                            </div>

                                         <div class="row">
                                           <div class="col">
                                                 <label for="name">Price:</label>
                                            </div> 
                                         </div>
                                           <div class="row">
                                              <input type="number" v-model = "price" class="form-control">
                                            </div>

                                     
                                     
                                    </div>
                    <div class="modal-footer">
                                        <button class="btn btn-secondary" block @click="hideModal">Close</button>
                                        <button class="btn btn-primary" v-on:click="addTerm">Add</button>
                   </div>

                            
                                   
                            </div>
                    </div>
    
    </b-modal>






    



    
    <div style="height:45px"></div>
      <h3>Examination terms</h3>

  <table class="table table-striped table-dark">
  <thead class="thead-dark">
    <tr>
      <th scope="col"></th>
      <th scope="col">Dermatologist</th>
      <th scope="col">Date</th>
      <th scope="col">Time</th>
      <th scope="col">Duration</th>
      <th scope="col">Price</th>
  </tr>
  </thead>
  <tbody>
    <tr v-for="term in terms" :key="term.id">
                                                    <td></td>
                                                    <td>{{term.dermatologistName}} &nbsp; {{term.dermatologistSurname}}</td>
                                                    <td>{{term.date}}</td>
                                                    <td>{{term.time}}</td>
                                                    <td>{{term.duration}}</td>
                                                    <td>{{term.price}}</td>
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
       showMed : false,
       pharmacyId : 1,
       actions : [],
       ourDermatologists :[],
       allDermatologists : [],
       selectedDermatologist : {},
       dermatologistName : "",
       dermatologistSurName : "",
       date : "",
       startTime : "",
       endTime : "",
       pharmacy : {},
       admin : {},
       duration : 0,
       price : 0,
       selectedDermatologist1 : {},
        selectedDermatologist2 : {},
        terms: {},
        loggedIn : false
      
    }
  },
  mounted() {
       let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/pharmacyAdmin/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
                this.admin = response.data;
                this.loggedIn = true;
                console.log(this.admin);
                this.axios.get('/pharmacyAdmin/myPharmacy',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.pharmacy = response.data;
                            console.log(this.pharmacy);
                             
                    }).catch(res => {
                            alert("NOT OK");
                            console.log(res);
                    });
                    
                
         
         }).catch(res => {
                alert("Plesase, log in.");
                window.location.href = "/login";
                console.log(res);
        });
        this.axios.get('/pharmacyAdmin/dermatologists',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.ourDermatologists = response.data;
                    }).catch(res => {
                            alert("NOT OK");
                            console.log(res);
                    });
        this.axios.get('/pharmacyAdmin/terms',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.terms = response.data;
                    }).catch(res => {
                            alert("NOT OK");
                            console.log(res);
                    });
        
        
        
    }
     ,
  methods:{
       showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      showMyProfile: function(){
      },
       showActionsAndBenefitsForm : function(){
              this.$refs['my-modal'].show()
      },
       showOrderForm : function(){
          window.location.href = "/order";
      },
       logOut : function(){
         localStorage.removeItem('token');
          window.location.href = "/login";

      },
      showMyPharmacy : function (){
          window.location.href = "/myPharmacy"
      },
      addNewPharmacist : function(){
        window.location.href = "/addNewPharmacist";
      },
      showModal() {
        this.$refs['my-modal'].show()
      },
      hideModal() {
        this.$refs['my-modal'].hide()
      },
      showModal1() {
        this.$refs['my-modal1'].show()
      },
      hideModal1() {
        this.$refs['my-modal1'].hide()
      },
       hideModalSchedule() {
        this.$refs['my-modalSchedule'].hide()
      },
       addTerm : function(){
            const data ={
              pharmacy : this.pharmacy.id,
              dermatologist : this.selectedDermatologist.id,
              date : this.date,
              startTime : this.startTime,
              duration : this.duration,
              price : this.price
          }
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/pharmacy/addExaminationSchedule',data,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                        alert(response.data)
                        console.log(response.data);
                })
                .catch(response => {
                        alert(response.response.data.message);
                        console.log(response);
                 });    
      },
        
      dermatologistIsSelected : function(event, dermatologist) {
            this.selectedDermatologist = dermatologist;
            this.dermatologistName = this.selectedDermatologist.name;
            this.dermatologistSurName = this.selectedDermatologist.surname;
            console.log(event);
      },
        dermatologistIsSelected1 : function(event, dermatologist) {
            this.selectedDermatologist1 = dermatologist;
            this.dermatologistName1 = this.selectedDermatologist1.name;
            this.dermatologistSurName1 = this.selectedDermatologist1.surname;
            console.log(event);
      }
}
}
</script>

<style>
body {
  font-family: "Lato", sans-serif;
}
.sidenav {
  height: 100%;
  width: 270px;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color:whitesmoke ;
  overflow-x: hidden;
  padding-top: 20px;
  margin-top : 90px;
}
.sidenav a {
  padding: 6px 6px 6px 2px;
  text-decoration: none;
  font-size: 25px;
  color: #292b2c;
  text-align : left;
  font-color : white;
}
.sidenav a:hover {
  color: darkgray;
}
@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
</style>