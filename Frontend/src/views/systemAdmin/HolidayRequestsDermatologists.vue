<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            <span style="float: left; margin: 15px;">

                <b-dropdown id="ddCommodity" name="ddCommodity" text="New registration" 
                               class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                    <b-dropdown-item v-on:click = "registerPharmacy">Pharmacy</b-dropdown-item>
                    <b-dropdown-item v-on:click = "registerPharmacyAdmin">Pharmacy admin</b-dropdown-item>
                    <b-dropdown-item v-on:click = "registerDermatologist">Dermatologist</b-dropdown-item> 
                    <b-dropdown-item v-on:click = "registerSupplier">Supplier</b-dropdown-item>
                    <div v-if = "allowSystemAdminRegistration">
                         <b-dropdown-item v-on:click = "registerSystemAdmin">System admin</b-dropdown-item>
                    </div>
                     <div v-if = "notallowSystemAdminRegistration">
                         <b-dropdown-item v-on:click = "registerSystemAdmin" disabled>System admin</b-dropdown-item>
                    </div>                    
                 </b-dropdown> 
                  <strong class="tab"></strong>  
                 <button class = "btn btn-link btn-lg" v-on:click = "addMedicine">Add medication</button>
                  <strong class="tab"></strong>  
                 <button class = "btn btn-link btn-lg" v-on:click = "medicationSearch">Medications</button>
                  <strong class="tab"></strong>  
                 <button class = "btn btn-link btn-lg" v-on:click = "defineLoyaltyProgram">Loyalty program</button>
                  <strong class="tab"></strong>  
                 <button class = "btn btn-link btn-lg" v-on:click = "showComplaints">Complaints</button>
                 <strong class="tab"></strong>  
                 <a class = "btn btn-link btn-lg" href = "/holidayRequestsDermatologists">Holiday requests</a>
            </span>
            <span  style="float:right;margin:15px">
                  <strong class="tab"></strong>  
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
             </span>    
        </div>
        



        <div style="height:30px"></div>

        <div class="container-fluid">

    
    <div style="height:45px"></div>
      <h3>Holiday/absence requests from dermatoligists</h3>

  <table class="table table-striped table-dark">
  <thead class="thead-dark">
    <tr>
      <th scope="col">Dermatologist</th>
      <th scope="col">Dermatologist email</th>
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
       accountInformation :null,
       allowSystemAdminRegistration : false,
       notallowSystemAdminRegistration : false

    }
  },
  mounted() {
     let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/systemAdmin/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.accountInformation=response.data;
               if(this.accountInformation.mainAdmin) {
                      this.allowSystemAdminRegistration = true;
                      this.notallowSystemAdminRegistration = false;
               }
               else{
                    this.allowSystemAdminRegistration = false;
                    this.notallowSystemAdminRegistration = true;
               }
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });

        this.axios.get('/holidayDermatologist/waiting',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.holidayRequests=response.data;
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });
    
  },
  methods:{
     registerPharmacy: function() {
            window.location.href = "/registerPharmacy";
      },
      registerPharmacyAdmin: function() {
            window.location.href = "/registerPharmacyAdmin";
      },
      registerDermatologist: function() {
           window.location.href = "/registerDermatologist";
      },
      registerSupplier: function() {
           window.location.href = "/registerSupplier";
      },
      registerSystemAdmin: function() {
           window.location.href = "/registerSystemAdmin";
      },
      addMedicine : function() {
           window.location.href = "/addMedicine";
      },
      defineLoyaltyProgram : function(){
          window.location.href = "/loyaltyProgram";
      },
      medicationSearch : function() {
          window.location.href = "/systemAdminMedicationSearch";
      },
      showComplaints : function() {
            window.location.href = "/complaints";
      },
      hideModal() {
        this.$refs['my-modal'].hide()
      },
      logOut : function(){
          localStorage.removeItem('token');
          window.location.href = "/login";
      },
      approve :  function(event, request){
           let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.get('/holidayDermatologist/approve/'+request,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Successfully approved.");
                        console.log(response.data);
                        window.location.href ="/holidayRequestsDermatologists";
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
            this.axios.post('/holidayDermatologist/refuse', data,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Successfully refused.");
                       this.reason = "";
                        window.location.href ="/holidayRequestsDermatologists";
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


  