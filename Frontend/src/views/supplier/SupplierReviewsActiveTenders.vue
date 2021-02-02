<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
             <span style="float: left; margin: 15px;">
                  <a  class = "btn btn-secondary" href = "/supplierProfileInfo">My profile</a>
                  <strong class="tab"></strong>  
                  <a  class = "btn btn-secondary" href = "/supplierReviewsActiveTenders">Active tenders</a>
                  <strong class="tab"></strong>  
                  <a  class = "btn btn-secondary" href = "/myOffers">My offers</a>   
                  <strong class="tab"></strong>  
                  <a  class = "btn btn-secondary" href = "/supplierAddMedication">Add medication</a>   
                  <strong class="tab"></strong>  
                  <a  class = "btn btn-secondary" href = "/supplierMyMedications">My medications</a>   
            </span>
            <span  style="float:right;margin:15px">
                    <strong class="tab"></strong>  
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
            </span>
        </div>
        <div> 

            <div style = "background-color:lightgray; margin: auto; width: 70%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
                        <h3 style="color: #0D184F;margin-bottom:20px">Choose tender</h3>
                    <div class="row">
                        <div class=" form-group col"  v-for="order in orders" :key="order.id">
                            <button class="btn btn-secondary" v-on:click="showTender($event, order)">Tender {{order.pharmacyName}}</button>
                        </div>
                    </div>
                    <hr/>
                    <hr/>
                    <div v-if="showConcreteTender">
                        <div class="row"></div>
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
                        <hr/>
                        <div class="row justify-content-center">
                            <h4 style="color: #0D184F;margin-bottom:20px">Make offer</h4>

                        </div>
                        <div class="row">
                            <div class=" form-group col">
                                <label >Price</label>
                            </div>
                            
                            <div class=" form-group col">          
                                <input type="number" class="form-control" v-model="priceOffered"  placeholder="Enter price..">
                            </div>
                            
                        </div>
                        <div class="row">
                            <div class="col">
                                   <label for="name">Can be delievered until:</label>
                            </div>
                             <div class="col">
                                  <input type="date" v-model="delieveryDate" />
                             </div>
                            
                        </div>
                        <div class="row justify-content-end">
                           <div class="modal-footer">
                            <button class="btn btn-primary" @click="sendOffer">Send offer</button>
                        </div>
                            
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
        orders : [],
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
        supplierAccount : {}
    }
  },

  methods:{
      logOut : function(){
           localStorage.removeItem('token');
           window.location.href = "/login";
      },
      showTender :function (event, order) {
           this.delieveryDate = null
           this.priceOffered = 0
            this.medicationsOrder =[]
            let i =0;
            for(i =0; i<order.medications.length;i++) {
                this.medicine = {}
                this.medicine = order.medications[i];
                this.medicationsOrder.push(this.medicine);
            }
            this.showConcreteTender = true;
            this.choosenTender = order;
            this.choosenTender.medications = this.medicationsOrder;
      },
      sendOffer : function(){
        const offer = {
            supplier : this.supplierAccount,
            orderId : this.choosenTender.id,
            dateOfDelivery : this.delieveryDate,
            summaryPrice : this.priceOffered,
        }
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

        this.axios.get('/offer/checksQuantity/'+this.choosenTender.id,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }}).then(response => {
                        if(response.data==true) {

                                this.axios.post('/offer/add',offer,{ 
                                                headers: {
                                                        'Authorization': 'Bearer ' + token,
                                        }}).then(response => {
                                            console.log(response)
                                            alert("Successfully sent offer!")
                                        }).catch(res => {
                                            alert("Please try later.");
                                                console.log(res);
                                        });
                        }
                      else {
                            alert("Sorry. You cant make offer for this tender. You dont have enough medications.");
                      }

                })
                .catch(response => {
                       alert("Sorry. You cant make offer for this tender. You dont have enough medications.");
                        console.log(response);
                 }); 
         
      }
      
},
 mounted() {
       let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/order/active',{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                    this.orders=response.data;
                    console.log(this.orders)
                }).catch(res => {
                       alert("Please try later.");
                        console.log(res);
                }); 
        this.axios.get('/supplier/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
                this.supplierAccount = response.data;
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });

    }
}
</script>

<style>

</style>


  