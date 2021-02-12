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
                             <a   class = "btn btn-link" href="/medicationInquires">Inquires for medication</a>

            </span>
              <span  style="float:right;margin:15px">
                   
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
                </span>

        </div>
             
                     <h3 style="margin:25px;color:#0D184F;font-weight:bold;">Active tenders:</h3>
                 

 


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
                                                       <h3 style="color: #0D184F;margin:20pxlfont-weight:bold">Tender {{choosenTender.id}}</h3>

                    <hr/>
                    <div >
                        <div class="row">
                            <div class=" form-group col">
                                <label style="color:#0D184F"><u>Medication</u></label>
                            </div>
                            
                            <div class=" form-group col">          
                                <label><u>Code</u></label>
                            </div>
                            <div class=" form-group col">          
                                <label><u>Required quantity</u></label>
                            </div>
                            <div class=" form-group col">          
                                <label><u>Change quantity</u></label>
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
                                   <label>{{medication.quantity}}</label>
                                    </div>
                                 <div class=" form-group col">

                                 <input type="number" v-model="quantity">
                                 <button style="background:lightgreen;width:15px;" @click="addNewMedicine($event,medication)">✓</button>
                                </div>
                               
                             </div>
                         </div>
                         <div class="row"></div>
                         <div class="row"></div>
                         <hr/>
                         <div class="row">
                            <div class=" form-group col">
                                <label >Active until:     {{choosenTender.date}}</label>

                            </div>
                      <div class=" form-group col">

                            <label>Change date:</label><input type="date"  v-model = "choosenTender.date">
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
       orders : [],
       medicationInOrder : [],
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
        endDateEdit : "",
        quantityEdit : "",
        active : false,
        finished :false,
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
                    this.axios.get('/pharmacyAdmin/activeOrders',{ 
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
      showModal() {
        this.$refs['my-modal'].show()
      },
      hideModal() {
        this.$refs['my-modal'].hide()
      },
       hideModalEdit() {
        this.$refs['edit-modal'].hide()
      },
       showTender :function (event, order) {
            this.showConcreteTender = true;
            this.choosenTender = order;
           
      },
       showEditForm : function(){
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
         const order = {
                    orderId : this.choosenTender.id,
                    medicationsInOrderDTO: this.choosenTender.medications,
                    date: this.choosenTender.date,
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
       addNewMedicine : function(event,selectedMedication){
                selectedMedication.quantity = this.quantity;

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