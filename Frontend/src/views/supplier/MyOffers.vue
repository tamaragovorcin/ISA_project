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

       <div style = "background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
          <div class="row">
            <div class="form-group col">
                <h5 style="color: #0D184F">Filter offers by status</h5>
            </div >
            <template>
              <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample1" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'CREATED')">
                   <label class="custom-control-label" for="defaultGroupExample1">Created</label>
                
              </div> 
              <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample2" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'ACCEPTED')">
                   <label class="custom-control-label" for="defaultGroupExample2">Accepted</label>
                
              </div> 
              <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample3" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'REFUSED')">
                 <label class="custom-control-label" for="defaultGroupExample3">Refused</label>
                
              </div> 
              <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample4" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'ALL')" checked>
                 <label class="custom-control-label" for="defaultGroupExample4" >All</label>
                
              </div> 
             </template>

          </div> 
       </div>
      <div style = "background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
        <div v-for="offer in myOffers"   v-bind:key="offer.offerId">
            <div>
                  <div class="row">
                      <label >Pharmacy name:   {{offer.pharmacyName}}</label>
                  </div>
                 <div class="row" v-for="medication in offer.medications"   v-bind:key="medication.code">
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
                  <div class=" form-group col">
                        <button class = "btn btn-primary" style="margin-right:20px;" v-on:click = "showMyOffer($event,offer)">My offer</button>
                  </div>
                  <hr/>
                  <hr/>

              </div>                    

        </div>
      </div>
      <b-modal ref="my-modal" hide-footer scrollable title="Please change password to continue" size="lg" modal-class="b-modal">
            <div modal-class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content" style="background-color:whitesmoke" >
                    <div class="modal-body" v-if="choosenOfferChangable">
                        <div class="form-group">
                            <label>Offered price:</label>
                            <input type="number" class="form-control" v-model="choosenOfferPrice" placeholder="Price..">
                        </div>
                        <div class="form-group">
                          <div>
                            <label>Delievery date:  {{choosenOfferDate}}</label>
                          </div>
                          <div class="row">
                              <div class=" form-group col">
                                  <label>If you want to change date of delievery, choose the date     </label>
                              </div>
                              
                              <div class=" form-group col">
                                  <input type="date" v-model="newDelieveryDate" />
                              </div>
                          </div>
                        </div>
                        
                        <button v-on:click = "confirmChange" class="btn btn-primary">Confirm</button>        
                    </div>               
                    <div class="modal-body" v-if="!choosenOfferChangable">
                        <div class="form-group">
                            <label>Offered price:</label>
                            <input type="number" class="form-control" v-model="choosenOfferPrice" placeholder="Price..">
                        </div>
                        <div class="form-group">
                          <div>
                            <label>Delievery date:  {{choosenOfferDate}}</label>
                          </div>
                        </div>
                    </div>                 
                </div>
            </div>
        </b-modal>
    </div>
</template>

<script>
export default {

  data() {
    return {
      myOffers :[],
      choosenOffer : {},
      choosenOfferDate : null,
      choosenOfferPrice : 0,
      newDelieveryDate : null,
      choosenOfferId : 0,
      choosenOfferChangable : false
    }
  },

  methods:{
      logOut : function(){
           localStorage.removeItem('token');
           window.location.href = "/login";
      },
      showMyOffer : function(event,offer) {
        this.choosenOffer = offer;
        this.choosenOfferDate = offer.dateOfDelivery;
        this.choosenOfferPrice = offer.summaryPrice;
        this.choosenOfferId = offer.offerId;
        this.choosenOfferChangable = offer.changable;
        this.$refs['my-modal'].show();
      },
      updateFiler : function(event, filter) {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        if(filter==="ALL") {
            this.axios.get('/offer/myActiveOffers',{ 
                                          headers: {
                                                  'Authorization': 'Bearer ' + token,
                                  }}).then(response => {
                                      this.myOffers=response.data;
                                  }).catch(res => {
                                        alert("Please try later.");
                                          console.log(res);
                                  }); 
        }
        
        else {
            this.axios.get('/offer/myActiveOffers/'+filter,{ 
                                    headers: {
                                            'Authorization': 'Bearer ' + token,
                            }}).then(response => {
                                this.myOffers=response.data;
                            }).catch(res => {
                                  alert("Please try later.");
                                    console.log(res);
                            }); 
        }
      },
      confirmChange : function() {
        let newDate = null;
        if(this.newDelieveryDate!=null) {
          newDate = this.newDelieveryDate;
        }
        else {
          newDate = this.choosenOfferDate;
        }
         const offerUpdateInfo = {
            offerId : this.choosenOfferId,
            summaryPrice : this.choosenOfferPrice,
            dateOfDelivery : newDate,
         }
         let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
         this.axios.post('/offer/update',offerUpdateInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                   console.log(response);
                   alert("Offer is successfully updated!");
                }).catch(res => {
                       alert("Please try later.");
                        console.log(res);
                });
      }
      
      
},
 mounted() {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/offer/myActiveOffers',{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                    this.myOffers=response.data;
                }).catch(res => {
                       alert("Please try later.");
                        console.log(res);
                }); 
    }
}
</script>

<style>

</style>


  