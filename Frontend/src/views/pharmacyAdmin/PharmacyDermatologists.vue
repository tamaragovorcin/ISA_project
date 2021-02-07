<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
                <span style="float: left; margin: 15px;">
                              <b-dropdown id="ddCommodity" name="ddCommodity" text="My profile" 
                                              class = "btn btn-link btn-lg">
                                    <b-dropdown-item href = "/pharmacyAdminProfile">Our pharmacists</b-dropdown-item>
                                    <b-dropdown-item href = "/phAdminProfileUpdate">Update profile</b-dropdown-item>      
                                </b-dropdown>        

                        <strong class="tab"></strong>  

                                    <router-link :to="{ path: '/pharmacyProfile/'+pharmacy.id}" v-slot="{href, navigate}">
                                                <button class = "btn btn-secondary" :href="href" @click="navigate"  elevation="1">My pharmacy profile</button>
                                    </router-link>

                          <strong class="tab"></strong>  

                                  <b-dropdown id="ddCommodity" name="ddCommodity" text="Pharmacists" 
                                                class = "btn btn-link btn-lg">
                                      <b-dropdown-item href = "/pharmacyPharmacists">Our pharmacists</b-dropdown-item>
                                      <b-dropdown-item href = "/addPharmacist">Add new pharmacist</b-dropdown-item>      
                                  </b-dropdown> 
                          <strong class="tab"></strong>  
                                  <b-dropdown id="ddCommodity" name="ddCommodity" text="Dermatologists" 
                                                  class = "btn btn-link btn-lg">
                                        <b-dropdown-item href = "/pharmacyDermatologists">Our dermatologists(Add new)</b-dropdown-item>
                                        <b-dropdown-item href = "/examinationTerms">Examination terms</b-dropdown-item>      
                                    </b-dropdown>                 
                            <strong class="tab"></strong>  
                            <a   class = "btn btn-secondary" href = "/pharmacyMedications">Medications</a>
                            <strong class="tab"></strong>  
                            <a   class = "btn btn-secondary" href = "/pharmacyAdminMedicationSearch">Medications in system</a>
                            <strong class="tab"></strong>  
                            <a  class = "btn btn-secondary" href = "/actionsAndBenefits">Actions and benefits</a>
                            <strong class="tab"></strong>  
                            <a   class = "btn btn-secondary" href="/order">Orders</a>
                            <strong class="tab"></strong>  
                            <a   class = "btn btn-secondary" href="/holidayRequests">Holiday/absence requests</a>
            </span>
              <span  style="float:right;margin:15px">
                   
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
                </span>

        </div>
        
       
                  <div style="height:30px"></div>

        <div class="container-fluid">





<b-button class = "btn btn-warning" @click="showModal1">+ Add dermatologist</b-button>
        
        <b-modal ref="my-modal1" hide-footer scrollable title="Add dermatologist as employee" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                    
                                    <div class="modal-body">
                                    
                                    <label>Choose dermatologist:</label>
                            <div class="form-row">
                                 <div class="form-group col">
                                        <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose dermatologist"
                                        class = "btn btn-link btn-lg" style="float:left; width=200px;">
                                            <b-dropdown-item v-for="dermatologist in this.allDermatologists"  v-on:click = "dermatologistIsSelected1($event, dermatologist)" v-bind:key="dermatologist.id"> 
                                            {{ dermatologist.firstname }}&nbsp; {{dermatologist.surname }}
                                            </b-dropdown-item>
                                    </b-dropdown> 
                                 </div>
                                <div class="form-group col-md-6 ">
                                    <label style="font-size:25px;font-weight:bold;">{{this.selectedDermatologist1.firstname}}&nbsp; {{this.selectedDermatologist1.surname}}</label>
                                </div>
                                    
                            </div>
                                          
                                             
                            

                                     
                                     
                                    </div>
                    <div class="modal-footer">
                                        <button class="btn btn-secondary" block @click="hideModal1">Close</button>
                                        <button class="btn btn-primary" v-on:click="addDermatologist">Add</button>
                   </div>

                            
                                   
                            </div>
                    </div>
    
    </b-modal>


     <b-modal ref="my-modalSchedule" hide-footer scrollable title="Define" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                    <div class="modal-header">
                                                                                 
                        
                                    </div>
                                    <div class="modal-body">


                                        <div class="form-row">
                                                              <label align="center">Monday:</label>
                                        </div>
                                        <div class = "form-row">
                                              <div class="form-group col-md-4">
                                                  <label>Start time:</label>
                                                  <input type="time" class="form-control" v-model="startTimeMonday">
                                              </div>
                                              <div class="form-group col-md-4">
                                                  <label>End time:</label>
                                                  <input type="time" class="form-control" v-model = "endTimeMonday">
                                              </div>
                                        </div>

                                         <div class="form-row">
                                                              <label align="center">Tuesday:</label>
                                        </div>
                                        <div class = "form-row">
                                              <div class="form-group col-md-4">
                                                  <label>Start time:</label>
                                                  <input type="time" class="form-control" v-model="startTimeTuesday">
                                              </div>
                                              <div class="form-group col-md-4">
                                                  <label>End time:</label>
                                                  <input type="time" class="form-control" v-model = "endTimeTuesday">
                                              </div>
                                        </div>

                                         <div class="form-row">
                                                              <label align="center">Wednesday:</label>
                                        </div>
                                        <div class = "form-row">
                                              <div class="form-group col-md-4">
                                                  <label>Start time:</label>
                                                  <input type="time" class="form-control" v-model="startTimeWednesday">
                                              </div>
                                              <div class="form-group col-md-4">
                                                  <label>End time:</label>
                                                  <input type="time" class="form-control" v-model = "endTimeWednesday">
                                              </div>
                                        </div>

                                         <div class="form-row">
                                                              <label align="center">Thursday:</label>
                                        </div>
                                        <div class = "form-row">
                                              <div class="form-group col-md-4">
                                                  <label>Start time:</label>
                                                  <input type="time" class="form-control" v-model="startTimeThursday">
                                              </div>
                                              <div class="form-group col-md-4">
                                                  <label>End time:</label>
                                                  <input type="time" class="form-control" v-model = "endTimeThursday">
                                              </div>
                                        </div>


                                        <div class="form-row">
                                                              <label align="center">Friday:</label>
                                        </div>
                                        <div class = "form-row">
                                              <div class="form-group col-md-4">
                                                  <label>Start time:</label>
                                                  <input type="time" class="form-control" v-model="startTimeFriday">
                                              </div>
                                              <div class="form-group col-md-4">
                                                  <label>End time:</label>
                                                  <input type="time" class="form-control" v-model = "endTimeFriday">
                                              </div>
                                        </div>


                                        <div class="form-row">
                                                              <label align="center">Saturday:</label>
                                        </div>
                                        <div class = "form-row">
                                              <div class="form-group col-md-4">
                                                  <label>Start time:</label>
                                                  <input type="time" class="form-control" v-model="startTimeSaturday">
                                              </div>
                                              <div class="form-group col-md-4">
                                                  <label>End time:</label>
                                                  <input type="time" class="form-control" v-model = "endTimeSaturday">
                                              </div>
                                        </div>

                                        <div class="form-row">
                                                              <label align="center">Sunday:</label>
                                        </div>
                                        <div class = "form-row">
                                              <div class="form-group col-md-4">
                                                  <label>Start time:</label>
                                                  <input type="time" class="form-control" v-model="startTimeSunday">
                                              </div>
                                              <div class="form-group col-md-4">
                                                  <label>End time:</label>
                                                  <input type="time" class="form-control" v-model = "endTimeSunday">
                                              </div>
                                        </div>
                                          
                                          

                                          
                                     
                                    </div>
                    <div class="modal-footer">
                                       <button class="btn btn-secondary" block @click="addSchedule">Add</button>

                                        <button class="btn btn-secondary" block @click="hideModalSchedule">Close</button>
                   </div>

                            
                                   
                            </div>
                    </div>
    
    </b-modal>



    
    <div style="height:45px"></div>
      <h3>Dermatologists of our pharmacy</h3>
       <div style="background: white; height: 60px; margin-top: 20px">
          <span  style="float:right;margin:15px">
            <div class="input-group mb-3">
              <input type="text" v-model="dermatologistFirstName" class="form-control" placeholder="Dermatologist name..." aria-label="Enter name..." aria-describedby="basic-addon2">
              <input type="text" v-model="dermatologistLastName" class="form-control" placeholder="Dermatologist surname..." aria-label="Enter surname..." aria-describedby="basic-addon2">

                 <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button"  v-on:click = "showByDermatologistsName" >Search</button>
                  </div>
            </div>
          </span>

        
         
           <span  style="float:right;margin:15px">
              <div class="input-group mb-3">
                  <label>Average mark around:</label>
                   <div class="input-group-append  align-self-center">
                       <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose pharmacists mark" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                              <b-dropdown-item v-for="item in this.marks"  v-on:click ="markIsSelected($event, item.mark)" v-bind:key="item.mark"> {{item.mark }}</b-dropdown-item>
                        </b-dropdown> 
                  </div>
              </div>
          </span>  
        </div>

  <table class="table table-striped table-dark">
  <thead class="thead-dark">
    <tr>
      <th scope="col"></th>
      <th scope="col">Name</th>
      <th scope="col">Surname</th>
      <th scope="col">Phone number</th>
      <th scope="col">Email</th>
      <th scope="col">Mark</th>
      <th scope="col">Remove from pharmacy</th>
      <th scope="col">Define schedule</th>
  </tr>
  </thead>
  <tbody>
    <tr v-for="dermatologist in ourDermatologists" :key="dermatologist.id">
                                                    <td></td>
                                                    <td>{{dermatologist.firstname}}</td>
                                                    <td>{{dermatologist.surname}}</td>
                                                    <td>{{dermatologist.phonenumber}}</td>
                                                    <td>{{dermatologist.email}}</td>
                                                    <td>{{dermatologist.markDermatologist}}</td>
                                                    <td><button  v-on:click ="remove($event, dermatologist)" class="btn btn-info">Remove</button></td>
      <td><button  v-on:click ="defineSchedule($event, dermatologist)" class="btn btn-info">Define schedule</button></td>
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
        startTimeMonday : "",
        startTimeTuesday : "",
        startTimeWednesday : "",
        startTimeThursday : "",
        startTimeFriday : "",
        startTimeSaturday : "",
        startTimeSunday : "",
        endTimeMonday : "",
        endTimeTuesday : "",
        endTimeWednesday : "",
        endTimeThursday : "",
        endTimeFriday : "",
        endTimeSaturday : "",
        endTimeSunday : "",
        marks: [
          { mark: "0-1" },
          { mark: "1-2" },
          { mark: "2-3" },
          { mark: "3-5" },
          { mark: "4-5" },
      ],
      choosenMark :0,
      dermatologistFirstName : "",
      dermatologistLastName : "",
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
                alert("NOT OK");
                console.log(res);
        });
        this.axios.get('/pharmacyAdmin/dermatologists',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.ourDermatologists = response.data;
                            console.log(this.ourDermatologists);
                    }).catch(res => {
                            alert("NOT OK");
                            console.log(res);
                    });
        this.axios.get('/dermatologist/notInPharmacy',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.allDermatologists=response.data;
         }).catch(res => {
                       alert("Please try again later.");
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
      },
      addDermatologist: function(){
         const data ={
              pharmacyId : this.pharmacy.id,
              dermatologistId : this.selectedDermatologist1.id,
              
          }
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/dermatologist/addPharmacy',data,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                        alert("Dermatologist successfully added as employee in your pharmacy");
                        window.location.href= "pharmacyDermatologists";
                        console.log(response.data);
                })
                .catch(response => {
                       alert("Please try later.");
                        console.log(response);
                 });  
      },
      remove : function(event,dermatologist){
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/pharmacyAdmin/dermatologist/remove/'+ dermatologist.id,{ 
                headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            alert(response.data);
                            window.location.href="pharmacyDermatologists";
                            console.log(response);
                          
                             
                    }).catch(response => {
                            alert("NOT OK");
                            console.log(response);
                    });

		},
    defineSchedule : function(event, dermatologist){
            this.selectedDermatologist2 = dermatologist;  
            this.$refs['my-modalSchedule'].show()
  
    },
    addSchedule : function(){
      const data ={
                    pharmacyId : this.pharmacy.id,
                    dermatologistId : this.selectedDermatologist2.id,
                    startTimeMonday : this.startTimeMonday,
                    startTimeTuesday : this.startTimeTuesday,
                    startTimeWednesday : this.startTimeWednesday,
                    startTimeThursday : this.startTimeWednesday,
                    startTimeFriday : this.startTimeThursday,
                    startTimeSaturday : this.startTimeFriday,
                    startTimeSunday : this.startTimeSunday,
                    endTimeMonday : this.endTimeMonday,
                    endTimeTuesday : this.endTimeTuesday,
                    endTimeWednesday : this.endTimeWednesday,
                    endTimeThursday : this.endTimeThursday,
                    endTimeFriday : this.endTimeFriday,
                    endTimeSaturday : this.endTimeSunday,
                    endTimeSunday : this.endTimeSaturday
          }
           let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

            this.axios.post('/workingHours/dermatologist',data,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Successfully added schedule.");
                        console.log(response.data);
                })
                .catch(response => {
                       alert("Please try later.");
                        console.log(response);
                 });  
    },
      markIsSelected : function(event, mark) { 
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            var marks = mark.split('-')
            var MarkMin = parseInt(marks[0])
            var MarkMax = parseInt(marks[1])
            this.axios.get('pharmacyAdmin/dermatologist/searchMark/'+MarkMin+"/"+MarkMax,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                     this.ourDermatologists= response.data;
                }).catch(res => {
                     alert("NOT OK");
                    console.log(res);
                });     
     },
      showByDermatologistsName : function() {
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

          const data = {
            firstName : this.dermatologistFirstName,
            surName : this.dermatologistLastName
          }
            this.axios.post('pharmacyAdmin/dermatologist/searchName',data,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
          .then(response => {
                    this.ourDermatologists= response.data;
            }).catch(res => {
                        alert("No dermatologists with entered name.");
                        console.log(res);
                    });
      },
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
}
.sidenav a:hover {
  color: darkgray;
}
@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}

</style>
