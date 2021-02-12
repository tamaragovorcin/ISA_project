<template>
  <div  v-if="loggedIn" id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            <span style="float: left; margin-top: 15px;">
                              <b-dropdown id="ddCommodity" name="ddCommodity" text="My profile" 
                                              class = "btn btn-link btn-lg">
                                    <b-dropdown-item href = "/pharmacyAdminProfile">Profile</b-dropdown-item>
                                    <b-dropdown-item href = "/phAdminProfileUpdate">Update profile</b-dropdown-item>      
                                </b-dropdown>        

                                    <router-link :to="{ path: '/pharmacyProfile/'+pharmacy.id}" v-slot="{href, navigate}">
                                                <button class = "btn btn-link" :href="href" @click="navigate"  elevation="1">My pharmacy profile</button>
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
            </span>
              <span  style="float:right;margin:15px">
                   
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
                </span>
        </div>
        <div class="container-fluid">

        
        <b-modal ref="my-modal" hide-footer scrollable title="Define" size="lg" modal-class="b-modal">
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
                                       <button class="btn btn-primary" block @click="addSchedule">Add</button>

                                        <button class="btn btn-secondary" block @click="hideModal">Close</button>
                   </div>

                            
                                   
                            </div>
                    </div>
    
    </b-modal>

        <div style="background: white; height: 60px; margin-top: 20px">
          <span  style="float:right;margin:15px">
            <div class="input-group mb-3">
              <input type="text" v-model="pharmacistName" class="form-control" placeholder="Pharmacist name..." aria-label="Enter name..." aria-describedby="basic-addon2">
              <input type="text" v-model="phrmacistSurname" class="form-control" placeholder="Pharmacist surname..." aria-label="Enter surname..." aria-describedby="basic-addon2">

                 <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button"  v-on:click = "showByPharmacistName" >Search</button>
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


    <div style="height:25px"></div>
      <h3>Pharmacists in {{pharmacy.pharmacyName}}</h3>

  <table class="table table-striped table-dark">
  <thead class="thead-dark">
    <tr>
      <th scope="col"></th>
      <th scope="col">Name</th>
      <th scope="col">Surname</th>
      <th scope="col">Email</th>
      <th scope="col">Phone Number</th>
      <th scope="col">Mark</th>
      <th scope="col">Define schedule</th>
       <th scope="col">Remove from pharmacy</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="pharmacist in pharmacists" :key="pharmacist.id">
      <td></td>
      <td>{{pharmacist.name}}</td>
      <td>{{pharmacist.surname}}</td>
      <td>{{pharmacist.email}}</td>
      <td>{{pharmacist.phoneNumber}}</td>
      <td>{{pharmacist.markPharmacist}}</td>
      <td><button  v-on:click ="defineSchedule($event, pharmacist)" class="btn btn-info">Define schedule</button></td>
      <td><button  v-on:click ="remove($event, pharmacist)" class="btn btn-info">Remove</button></td>
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
        firstname : "",
        surname : "",
        email : "",
        phoneNumber : "",
        town : "",
        street : "",
        number : "",
        postalCode : "",
        country : "",
        pharmacy : {},
        pharmacists : [],
        selectedPharmacist : {},
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
         pharmacyName : "",
        marks: [
          { mark: "0-1" },
          { mark: "1-2" },
          { mark: "2-3" },
          { mark: "3-5" },
          { mark: "4-5" },
      ],
      choosenMark :0,
      choosenPharmacy : {},
      pharmacistName : "",
      phrmacistSurname : "",
      pharmacies : [],
      loggedIn : false
    }
  },

  methods:{     
      hideModal() {
        this.$refs['my-modal'].hide()
      },
      add : function(){
          
      },
       logOut : function(){
            localStorage.removeItem('token');
            window.location.href ="/login";
      },
      remove : function(event,pharmacist){
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('pharmacist/delete/'+ pharmacist.id,{ 
                headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            console.log(response);
                            alert("Pharmacists successfully removed.")
                    }).catch(response => {
                            alert("NOT OK");
                            console.log(response);
                    });

		},
    defineSchedule : function(event, pharmacist){
            this.selectedPharmacist = pharmacist;  
            this.$refs['my-modal'].show()
  
    },
    addSchedule : function(){
      const data ={
                    pharmacistId : this.selectedPharmacist.id,
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

            this.axios.post('/workingHours/pharmacist',data,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Successfully added schedule to pharmacist.");
                       this.hideModal()
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
            this.axios.get('pharmacyAdmin/pharmacist/searchMark/'+MarkMin+"/"+MarkMax,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                     this.pharmacists= response.data;
                }).catch(res => {
                     alert("NOT OK");
                    console.log(res);
                });     
     },
      showByPharmacistName : function() {
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

          const data = {
            firstName : this.pharmacistName,
            surName : this.phrmacistSurname
          }
            this.axios.post('pharmacyAdmin/pharmacist/searchName',data,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
          .then(response => {
                    this.pharmacists= response.data;
            }).catch(res => {
                        alert("There is no phatmacists with entered name.");
                        console.log(res);
                    });
      },
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
                            
                            console.log(res);
                    });
                    this.axios.get('/pharmacyAdmin/pharmacists',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.pharmacists = response.data;
                    }).catch(res => {
                            alert("Please, try later.");
                            console.log(res);
                    });
                    
                
         

         }).catch(res => {
                alert("Please, log in.");
                window.location.href="/login";
                console.log(res);
        });
        
    }
}
</script>

<style>

</style>


