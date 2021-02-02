<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            <span style="float: left; margin: 15px;">
                <a  class = "btn btn-secondary" href= "/isaHomePage">Home</a>
                <b class="tab"></b>    
                <a  class = "btn btn-secondary" href = "/pharmacyAdminProfile">My profile</a>
                <b class="tab"></b>    
                <a  class = "btn btn-secondary" href = "/myPharmacy">My Pharmacy</a>
                 <b class="tab"></b>    
                 <a  class = "btn btn-secondary" href = "/phAdminProfileUpdate">Update profile</a>
                 
                <b class="tab"></b>    
                <a  class = "btn btn-secondary" href = "/addPharmacist">Add pharmacist</a>    
                <b class="tab"></b> 
                 <a  class = "btn btn-secondary" href = "/pharmacyPharmacists">Our pharmacists</a> 
                  <b class="tab"></b>  
                <a  class = "btn btn-secondary" href = "/pharmacyDermatologists">Our dermatologists</a>      
                <b class="tab"></b> 
                <a   class = "btn btn-secondary" href = "/pharmacyMedications">Medications</a>
                        <b class="tab"></b>    
                 <a   class = "btn btn-secondary" href = "/pharmacyAdminMedicationSearch">Medications in system</a>
                        <b class="tab"></b>  
                <a  class = "btn btn-secondary" href = "/actionsAndBenefits">Actions and benefits</a>
                        <b class="tab"></b>    
                <a   class = "btn btn-secondary" href="/order">Orders</a>
            </span>
              <span  style="float:right;margin:15px">
                   
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
                </span>

        </div>
        <div>
      
                    <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:whitesmoke">
                            <div class="modal-header">
                                <h3 class="modal-title" id="exampleModalLabel">Medicine ordering</h3>
                
                            </div>
                            <div class="modal-body">
                                <div  class="container5">

                                        <div  style="margin-top:30px">
                                            <div class="row">
                                                <div class="col">
                                                    <label >Medication:</label>
                                                </div>
                                                <div class="col">
                                                                         <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose dermatologist"
                                        class = "btn btn-link btn-lg" style="float:left; width=200px;">
                                            <b-dropdown-item v-for="medicine in this.medications"  v-on:click = "dermatologistIsSelected($event, medicine)" v-bind:key="medicine.id"> 
                                            {{ medicine.name }}<div style="width:20px"></div>
                                            </b-dropdown-item>
                                    </b-dropdown> 

                                                </div>


                                            </div>
                                            <div class="row">

                                                <div class="col">
                                                    <label>Quantity:</label>
                                                </div>
                                                <div class="col">
                                                    <input type="number" v-model="quantity" placeholder="Enter quantity..">
                                                </div>

                                            </div>
                                            <div class="row">
                                                <div class="col">
                                                </div>
                                                <div class="col">
                                                    <button class="btn btn-secondary" style="margin-left:3px" v-on:click="addNewMedicine">&nbsp;&nbsp;+&nbsp;&nbsp;</button>
                                                </div>
                                            </div>
                                            <hr v-if="showTable" />





                                        </div>
                                        <div class="row">
                                            <table v-if="showTable" class="table table-striped table-dark">
                                                <thead>
                                                <th>Medicine name</th>
                                                <th>Quantity</th>
                                                </thead>
                                                <tr v-for="med in medicationQuantityList" :key="med.id">
                                                    <td>{{med.medicine.name}}</td>
                                                    <td>{{med.quantity}}</td>
                                                </tr>
                                            </table>
                                        </div>
                                        <hr />
                                        <div class="row">
                                            <div class="col">
                                                <label for="name">Closing date:</label>
                                            </div>
                                            <div class="col">
                                                <input type="date" v-model="endDate" />
                                            </div>
                                        </div>
                    

                                </div>


                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" block @click="hideModal">Close</button>
                        <button class="btn btn-primary" @click="shareOrder">Share order</button>
                    </div>
                    </div>
                </div>
    
  </div>
       

       

    </div>
</template>

<script>
export default {
  data() {
    return {
       show : false,
       medications : [],
       quantity : 0,
       showTable : false,
       medicationQuantityList : [],
       endDate : "",
       admin : {},
       pharmacy : {},
       selectedMedication : {}
    }
  },
   mounted() {

        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

            this.axios.get('/medication',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
            }).then(response => {
                            this.medications = response.data;
                    }).catch(res => {
                            alert("NOT OK");
                            console.log(res);
                    });


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
                            
                            this.selectedMedication  = {};
                            this.medicationQuantityList = [];
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
     
        
    },
  methods:{
       showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      showMyProfile: function(){
      },
       showActionsAndBenefitsForm : function(){
      },
       showOrderForm : function(){
      },
       logOut : function(){
           window.location.href = "/login";
      },
      showModal() {
        this.$refs['my-modal'].show()
      },
      hideModal() {
        this.$refs['my-modal'].hide()
      },
     dermatologistIsSelected : function(event, medicine) {
            this.selectedMedication = medicine;
            console.log(event);
      },
      addNewMedicine : function(){
               this.showTable = true;
                const medicineWithQuantity = {
                    medicine: this.selectedMedication,
                    quantity: this.quantity,
                };
                this.medicationQuantityList.push(medicineWithQuantity)
      },
      shareOrder : function(){
          const order = {
                    medicationsInOrderDTO: this.medicationQuantityList,
                    date: this.endDate,
                    pharmacyAdmin : this.admin,
                    status : "KREIRANA"
                };
            console.log(order);
             let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/order/add',order,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Order is successfully sent!");
                       this.selectedMedication  = {};
                       this.medicationQuantityList = [];
                       console.log(response.data);
                       window.location.href = "/order";
                })
                .catch(response => {
                       alert("Please try later.");
                        console.log(response);
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
  background-color:#0D184F ;
  overflow-x: hidden;
  padding-top: 20px;
  margin-top : 90px;
}
.sidenav a {
  padding: 6px 6px 6px 0px;
  text-decoration: none;
  font-size: 25px;
  color: white;
  display: block;
}
.sidenav a:hover {
  color: #f1f1f1;
}
@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
</style>