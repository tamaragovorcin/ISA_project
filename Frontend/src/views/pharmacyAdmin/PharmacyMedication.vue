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

         <b-button class = "btn btn-warning btn-lg" @click="showModal">+ Add medication</b-button>
        
        <b-modal ref="my-modal" hide-footer scrollable title="Add medication in pharmacy" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                    
                                    <div class="modal-body">
                                    
                                            <label>Choose medication:</label>
                            <div class="form-row">
                                 <div class="form-group col">
                                    <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose medication"
                                        class = "btn btn-link btn-lg" style="float:left; width=200px;">
                                            <b-dropdown-item v-for="medication in this.notInPharmacy"  v-on:click = "medicationIsSelected($event, medication)" v-bind:key="medication.id"> 
                                            {{ medication.name }}
                                            </b-dropdown-item>
                                    </b-dropdown> 
                                 </div>
                               
                                    
                            </div>


                            <div class="row">
                                           <div class="col">
                                                 <label for="name">Price:</label>
                                            </div> 
                                         </div>
                                           <div class="row">
                                              <input type="number" v-model = "price" class="form-control">
                                            </div>
                                             <div class="row">
                                           <div class="col">
                                                 <label for="name">Price expiration date :</label>
                                            </div> 
                                         </div>
                                           <div class="row">
                                              <input type="date" v-model = "date" class="form-control" placeholder = "01/01/2021">
                                            </div>
                                          
                                          
                                        
                                        
                                     
                                     
                                    </div>
                    <div class="modal-footer">
                                        <button class="btn btn-secondary" block @click="hideModal">Close</button>
                                        <button class="btn btn-primary" v-on:click="addMedication">Add</button>
                   </div>

                            
                                   
                            </div>
                    </div>
    
    </b-modal>


    <b-modal ref="my-modal1" hide-footer scrollable title="Add examination to schedule" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                    
                                    <div class="modal-body">
                                    
                                            <label>Selected medication: &nbsp; <strong> {{selectedMed.name}}</strong></label>
                                 

                            <div class="row">
                                           <div class="col">
                                                 <label for="name">Price:</label>
                                            </div> 
                                         </div>
                                           <div class="row">
                                              <input type="number" v-model = "price1" class="form-control">
                                            </div>
                                             <div class="row">
                                           <div class="col">
                                                 <label for="name">Price expiration date :</label>
                                            </div> 
                                         </div>
                                           <div class="row">
                                              <input type="date" v-model = "date1" class="form-control" placeholder = "01/01/2021">
                                            </div>
                                          
                                          
                                        
                                        
                                     
                                     
                                    </div>
                    <div class="modal-footer">
                                        <button class="btn btn-secondary" block @click="hideModal">Close</button>
                                        <button class="btn btn-primary" v-on:click="definePrice">Save</button>
                   </div>

                            
                                   
                            </div>
                    </div>
    
    </b-modal>


 <div style="background: white; height: 60px; margin-top: 20px">
          <span  style="float:right;margin:15px">
            <div class="input-group mb-3">
            <label style="font-weight:bold; margin-top:10px;margin-right:10px;">Search by medication name or code:</label>
              <input type="text" v-model="searchField" class="form-control" aria-describedby="basic-addon2">

                 <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button"  v-on:click = "search" >Search</button>
                  </div>
            </div>
          </span>
          
        </div>

    
    <div style="height:45px"></div>
      <h3>Medications in our pharmacy</h3>

  <table class="table table-striped table-dark">
  <thead class="thead-dark">
    <tr>
      <th scope="col">Medication name</th>
      <th scope="col">Medication code</th>
      <th scope="col">Medication form</th>
      <th scope="col">Manufacturer</th>
      <th scope="col">Quantity</th>
      <th scope="col">Price</th>
      <th scope="col">Price expiry date</th>
      <th scope="col">Define price</th>
      <th scope="col">Remove from pharmacy</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="med in ourMedications" :key="med.id">
                                                    <td>{{med.name}}</td>
                                                    <td>{{med.code}}</td>
                                                    <td>{{med.form}}</td>
                                                    <td>{{med.manufacturer}}</td>
                                                    <td>{{med.quantity}}</td>
                                                    <td>{{med.price}}</td>
                                                    <td>{{med.date}}</td>
                                                    <td><button  v-on:click ="defineFromRow($event, med)" class="btn btn-info">Define price</button></td>
                                                    <td><button  v-on:click ="remove($event, med)" class="btn btn-info">Remove</button></td>

                                                    
                                                  
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
       ourMedications :[],
       allMedications : [],
       selectedMedication : {},
       date : "",
       price : 0,
       pharmacy : {},
       admin : {},
       price1 : 0,
       date1 : 0,
       selectedMed : {},
       notInPharmacy : {},
       searchField : "",
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
                console.log(this.admin);
                this.loggedIn = true;
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


                     this.axios.get('/pharmacyAdmin/medication',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.allMedications = response.data;
                    }).catch(response => {
                            alert("Please try again later.");
                            console.log(response);
                    });
                    
                     this.axios.get('/pharmacyAdmin/medicationFront',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.ourMedications = response.data;
                    }).catch(response => {
                            alert("Please try again later.");
                            console.log(response);
                    });

                    
                     

         

         }).catch(res => {
                alert("Please, log in.");
                window.location.href ="/login";
                console.log(res);
        });
       
                
     
        

        
    }
     ,

  methods:{
      showModal() {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

        this.axios.get('/medication/notInPharmacy/'+ this.pharmacy.id,{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.notInPharmacy = response.data;
                    }).catch(response => {
                            alert("Please try again later.");
                            console.log(response);
                    });
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
      logOut : function(){
          localStorage.removeItem('token');
          window.location.href = "/login";
      },
       addMedication : function(){
        
            const data ={
              pharmacy : this.pharmacy.id,
              medication : this.selectedMedication,
              date : this.date,
              price : this.price
          }
            
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

            this.axios.post('/medication/addToPharmacy',data,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert(response.data);
                       window.location.href= "/pharmacyMedications";
                       console.log(response.data);
                })
                .catch(response => {
                       alert(response.response.data.message);

                        console.log(response);
                 });    
      },
      defineFromRow(event,med){
        this.$refs['my-modal1'].show();
        this.selectedMed = med;
      },
      definePrice() {
        const data ={
              pharmacy : this.pharmacy.id,
              medication : this.selectedMed,
              date : this.date1,
              price : this.price1
          }
            
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

            this.axios.post('/medication/priceInPharmacy',data,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert(response.data);
                       window.location.href="/pharmacyMedications";
                })
                .catch(response => {
                       alert(response.response.data.message);
                        console.log(response);
                 });    
      },
        
      medicationIsSelected : function(event, medication) {
            this.selectedMedication = medication;
            console.log(event);


      },
      remove(event,med){
         let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            const data = {
              medication : med.id,
              pharmacy : this.pharmacy.id
            }

            this.axios.post('/medication/remove',data,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert(response.data);
                       window.location.href= "/pharmacyMedications";
                      console.log(response.data);
                })
                .catch(response => {
                       alert("Medication can't be removed.");
                        console.log(response);
                 });   
      },
      search : function(){
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

            this.axios.get('/pharmacyAdmin/medicationFront/'+this.searchField,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
          .then(response => {
                    this.ourMedications= response.data;
            }).catch(res => {
                        alert("Try again later.");
                        console.log(res);
                    });
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


  