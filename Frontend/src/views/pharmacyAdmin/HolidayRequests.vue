<template>
  <div  v-if="loggedIn"  id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
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

       


    
    <div style="height:45px"></div>
      <h3>Holiday/absence requests from pharmacists</h3>

  <table class="table table-striped table-dark">
  <thead class="thead-dark">
    <tr>
      <th scope="col">Pharmacist</th>
      <th scope="col">Pharmacist email</th>
      <th scope="col">Type</th>
      <th scope="col">Start date</th>
      <th scope="col">End date</th>
       <th scope="col">Approvement</th>
        <th scope="col" style="margin-left:55px;">Refusal</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="request in holidayRequests" :key="request.id">
                                                    <td>{{request.pharmacist}}</td>
                                                    <td>{{request.email}}</td>
                                                    <td>{{request.type}}</td>
                                                    <td>{{request.startDate}}</td>
                                                    <td>{{request.endDate}}</td>
                                                    <td> <button class= "btn btn-success" @click= "approve($event, request.scheduleId)">Approve</button></td>
                                                    <td>
                                                     <button class= "btn btn-danger"  @click= "refuseModal($event, request.scheduleId)">Refuse</button></td>

                                                </tr>
   
  </tbody>
</table>


                   <b-modal ref="my-modal" hide-footer scrollable title="Define reason for refusal:" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                    
                                    <div class="modal-body">
                                                                                        Reason for refusal:                                                  

                                                                                    <input type="textarea" style="background-color:white;" v-model = "reason" class="form-control">

                                    
                                    </div>


                          
                    <div class="modal-footer">
                                        <button class="btn btn-secondary" block @click="hideModal">Close</button>
                                        <button class="btn btn-primary" v-on:click="refuse">Refuse</button>
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
       pharmacy : {},
       admin : {},
       holidayRequests : {},
       reason : "",
       selectedRequest : 0,
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


                     this.axios.get('/pharmacyAdmin/holidayRequests',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.holidayRequests = response.data;
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
      hideModal() {
        this.$refs['my-modal'].hide()
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


      },
      approve :  function(event, request){
           let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.get('/holidayPharmacist/approve/'+request,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Successfully approved.");
                        console.log(response.data);
                })
                .catch(response => {
                       alert("Please try later.");
                        console.log(response);
                 });    
      },
      refuseModal : function(event,request){
        this.$refs['my-modal'].show()
        this.selectedRequest = request;

      },
      refuse : function(){
         let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
         const data = {
             requestId: this.selectedRequest,
             reason : this.reason
         }
            this.axios.post('/holidayPharmacist/refuse', data,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Successfully refused.");
                       this.reason = "";
                       window.location.href = "/holidayRequests";
                       console.log(response.data);
                })
                .catch(response => {
                       alert("Please try later.");
                        console.log(response);
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


  