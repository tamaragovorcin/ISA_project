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
             
                     <h3 style="margin:25px;color:#0D184F;font-weight:bold;">Active tenders:</h3>
                 

 <b-modal ref="edit-modal" hide-footer scrollable title="Edit order" size="lg" modal-class="b-modal">
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
                                                                         <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose medication"
                                        class = "btn btn-link btn-lg" style="float:left; width=200px;">
                                            <b-dropdown-item v-for="medicine in this.medications"  v-on:click = "medicationIsSelected($event, medicine)" v-bind:key="medicine.id"> 
                                            {{ medicine.name }}<div style="width:20px"></div>{{medicine.type }}
                                            </b-dropdown-item>
                                    </b-dropdown> 

                                                </div>


                                            </div>
                                            <div class="row">

                                                <div class="col">
                                                    <label>Quantity:</label>
                                                </div>
                                                <div class="col">
                                                    <input type="number" v-model="quantityEdit" placeholder="Enter quantity..">
                                                </div>

                                            </div>
                                            <div class="row">
                                                <div class="col">
                                                </div>
                                                <div class="col">
                                                    <button class="btn btn-secondary" style="margin-left:3px" v-on:click="addNewMedicineEdit">&nbsp;&nbsp;+&nbsp;&nbsp;</button>
                                                </div>
                                            </div>





                                        </div>
                                        <div class="row">
                                            <table class="table table-striped table-dark">
                                                <thead>
                                                <th>Medicine name</th>
                                                <th>Quantity</th>
                                                </thead>
                                                <tr v-for="med in medicationQuantityListEdit" :key="med.id">
                                                    <td>{{med.medicine.name}}</td>
                                                    <td>{{med.quantity}}</td>
                                                    <td><button class = "btn btn-primary" >Remove medication</button></td>
                                                </tr>
                                            </table>
                                        </div>
                                        <hr />
                                        <div class="row">
                                            <div class="col">
                                                <label for="name">Closing date:</label>
                                            </div>
                                            <div class="col">
                                                <input type="date" v-model="endDateEdit" />
                                            </div>
                                        </div>
                    

                                </div>


                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" block @click="hideModalEdit">Close</button>
                        <button class="btn btn-primary" @click="editOrder">Share order</button>
                    </div>
                    </div>
                </div>
    
    </b-modal>




       <div>
       <h3 style="margin:25px;color:#0D184F;font-weight:bold;" v-if="active">Active tenders(waiting for offers):</h3>

        <div class="row" style = "background-color:whitesmoke; margin: auto; width: 100%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
                        <div class=" form-group col"  v-for="order in orders" :key="order.id">
                                   <h4 style="color: #0D184F;margin:20px">Choose tender:</h4>

                            <button class="btn btn-secondary" v-on:click="showTender($event, order)">Tender {{order.id}}</button>
                        </div>
                    </div>
 <div v-if="showConcreteTender" style = "background-color:white; margin: auto; width: 100%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
                   
                    <hr/>
                                                       <h4 style="color: #0D184F;margin:20px">Tender {{choosenTender.id}}</h4>

                    <hr/>
                    <div >
                        <div class="row">
                            <div class=" form-group col">
                                <label >Medication</label>
                            </div>
                            
                            <div class=" form-group col">          
                                <label >Code</label>
                            </div>
                            <div class=" form-group col">          
                                <label >Required quantity</label>
                            </div>
                        </div>
                        <div v-for="medication in choosenTender.medications"   v-bind:key="medication.id">
                            <div class="row">
                                    <div class=" form-group col">
                                        <label >{{medication.name}}</label>
                                    </div>
                                    <div class=" form-group col">
                                        <label >{{medication.code}}</label>
                                    </div>
                                     <div class=" form-group col">
                                        <label >{{medication.quantity}}</label>
                                    </div>
                             </div>
                         </div>
                         <div class="row"></div>
                         <div class="row"></div>
                         <div class="row">
                            <div class=" form-group col">
                                <label >Active until     {{choosenTender.date}}</label>
                            </div>
                           
                        </div>
                        <hr/>
                         <div class="row justify-content-center">
                           <div class="modal-footer">
                            <button class="btn btn-primary" @click="showEditForm">Edit</button>
                           <button class="btn btn-primary" @click="removeOrder">Remove</button>

                        </div>
                            
                        </div>
                        <hr/>
                       

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
       medications : null,
       quantity : 0,
       showTable : false,
       medicationQuantityList : [],
       endDate : "",
       admin : {},
       pharmacy : {},
       selectedMedication : {},
       selectedMedicationEdit : {},
       orders : [],
       medicationInOrder : [],
       choosenOffer :{
         id : 0,
         supplier: {},
         dateOfDelivery : "",
         summaryPrice : ""
       },
       choosenTender : {
            id : 0,
            date : null,
            status : "",
            medications : [],
            pharmacyName : "" 
        },
         medicine :{
              id : 0,
              name : "",
              code : 0,
              type : "",
              form : "",
              quantity : 0
          },
        medicationsOrder : [],
        showConcreteTender : false,
        delieveryDate : null,
        priceOffered :0,
        supplierAccount : {},
        offers : [],
        showOffers : false,
        showConcreteOffer : false,
        supplierName : "",
        supplierSurName : "",
        offerData : "",
        medicationQuantityListEdit : "",
        endDateEdit : "",
        quantityEdit : "",
        active : false,
        finished :false
        

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
                    this.axios.get('/pharmacyAdmin/orders',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.orders = response.data;
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
      showEditForm(){
                  this.$refs['edit-modal'].show()

      },
      showModal() {
        this.$refs['my-modal'].show()
      },
      hideModal() {
        this.$refs['my-modal'].hide()
      },
       hideModalEdit() {
        this.$refs['edit-modal'].hide()
      },
     dermatologistIsSelected : function(event, medicine) {
            this.selectedMedication = medicine;
            console.log(event);
      },
        medicationIsSelected : function(event, medicine) {
            this.selectedMedicationEdit = medicine;
            console.log(event);
      },
       showTender :function (event, order) {
            this.showConcreteTender = true;
            this.choosenTender = order;
           
      },
       addNewMedicineEdit : function(){
                const medicineWithQuantityEdit = {
                    medicine: this.selectedMedicationEdit,
                    quantity: this.quantityEdit,
                };
                this.medicationQuantityListEdit.push(medicineWithQuantityEdit)
      },
       editOrder : function(){
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
         const order = {
                    orderId : this.choosenTender.id,
                    medicationsInOrderDTO: this.medicationQuantityListEdit,
                    date: this.endDateEdit
                };
            this.axios.post('/order/update',order,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert(response.data);
                       window.location.href = "/order";
                })
                .catch(response => {
                       alert("Please try later.");
                        console.log(response);
                 });    
      },
    removeOrder : function(){
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        
            this.axios.get('/order/remove/'+this.choosenTender.id,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert(response.data);
                       window.location.href = "/editOrder";
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