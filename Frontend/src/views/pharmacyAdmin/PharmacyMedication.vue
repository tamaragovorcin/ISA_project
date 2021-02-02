<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
              <span style="float: left; margin: 15px;">
                <a  class = "btn btn-secondary" href= "/isaHomePage">Home</a>
                <strong class="tab"></strong>  
                <a  class = "btn btn-secondary" href = "/pharmacyAdminProfile">My profile</a>
                <strong class="tab"></strong>  
                <a  class = "btn btn-secondary" href = "/myPharmacy">My Pharmacy</a>
                <strong class="tab"></strong>  
                 <a  class = "btn btn-secondary" href = "/phAdminProfileUpdate">Update profile</a>
                 
                <strong class="tab"></strong>  
                 <b-dropdown id="ddCommodity" name="ddCommodity" text="Pharmacists" 
                               class = "btn btn-link btn-lg">
                    <b-dropdown-item href = "/pharmacyPharmacists">Our pharmacists</b-dropdown-item>
                    <b-dropdown-item href = "/addPharmacist">Add new pharmacist</b-dropdown-item>      
                </b-dropdown> 
                <strong class="tab"></strong>  
                <a  class = "btn btn-secondary" href = "/pharmacyDermatologists">Our dermatologists</a>      
                <strong class="tab"></strong>  
                <a   class = "btn btn-secondary" href = "/pharmacyMedications">Medications</a>
                <strong class="tab"></strong>  
                 <a   class = "btn btn-secondary" href = "/pharmacyAdminMedicationSearch">Medications in system</a>
                <strong class="tab"></strong>  
                <a  class = "btn btn-secondary" href = "/actionsAndBenefits">Actions and benefits</a>
                <strong class="tab"></strong>  
                <a   class = "btn btn-secondary" href="/order">Orders</a>
            </span>
              <span  style="float:right;margin:15px">
                   
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
                </span>

        </div>
        
       
                  <div style="height:30px"></div>

        <div class="container-fluid">

         <b-button class = "btn btn-warning" @click="showModal">Define medication price</b-button>
        
        <b-modal ref="my-modal" hide-footer scrollable title="Add examination to schedule" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                    
                                    <div class="modal-body">
                                    
                                            <label>Choose medication:</label>
                            <div class="form-row">
                                 <div class="form-group col">
                                    <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose medication"
                                        class = "btn btn-link btn-lg" style="float:left; width=200px;">
                                            <b-dropdown-item v-for="medication in this.allMedications"  v-on:click = "medicationIsSelected($event, medication)" v-bind:key="medication.id"> 
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
                                        <button class="btn btn-primary" v-on:click="addMedication">Save</button>
                   </div>

                            
                                   
                            </div>
                    </div>
    
    </b-modal>




    
    <div style="height:45px"></div>
      <h3>Medications in our pharmacy</h3>

  <table class="table table-striped table-dark">
  <thead class="thead-dark">
    <tr>
      <th scope="col">Medication name</th>
      <th scope="col">Medication code</th>
      <th scope="col">Medication form</th>
      <th scope="col">Manufacturer</th>
      <th scope="col">Price</th>
      <th scope="col">Price expiry date</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="med in ourMedications" :key="med.id">
                                                    <td>{{med.name}}</td>
                                                    <td>{{med.code}}</td>
                                                    <td>{{med.form}}</td>
                                                    <td>{{med.manufacturer}}</td>
                                                    <td>{{med.price}}</td>
                                                    <td>{{med.date}}</td>
                                                    
                                                  
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
       admin : {}

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
                alert("NOT OK");
                console.log(res);
        });
       
     
        

        
    }
     ,

  methods:{
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
      logOut : function(){
          localStorage.removeItem('token');
          window.location.href = "/login";
      },
       addMedication : function(){
            const data ={
              pharmacy : this.pharmacy,
              medication : this.selectedMedication,
              date : this.date,
              price : this.price
          }
            
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

            this.axios.post('/medication/priceInPharmacy',data,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Successfully updated medication price.");
                        window.location.href= "/pharmacyMedications";
                        console.log(response.data);
                })
                .catch(response => {
                       alert("Please try later.");
                        console.log(response);
                 });    
      },
        
      medicationIsSelected : function(event, medication) {
            this.selectedMedication = medication;
           

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


  