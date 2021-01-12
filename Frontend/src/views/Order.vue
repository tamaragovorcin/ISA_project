<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                <button class = "btn btn-secondary btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Home</button>
                  
            </span>
              <span  style="float:right;margin:15px">
                    <button class = "btn btn-secondary btn-lg" v-on:click = "showMyProfile">My profile</button>

                    <b class="tab"></b>    
                    <button class = "btn btn-secondary btn-lg" style="margin-right:20px;" v-on:click = "showActionsAndBenefitsForm">Actions/benefits</button>
                    
                     <b class="tab"></b>    
                    <button class = "btn btn-secondary btn-lg" style="margin-right:20px;" v-on:click = "showOrderForm">Orders</button>
                     <b class="tab"></b>    
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
                </span>

        </div>
        <div>
    <b-button class = "btn btn-warning btn-lg" @click="showModal">+ Make new order</b-button>
    <b-modal ref="my-modal" hide-footer scrollable title="Fill order form" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:whitesmoke">
                            <div class="modal-header">
                                <h3 class="modal-title" id="exampleModalLabel">Medicing ordering</h3>
                
                            </div>
                            <div class="modal-body">
                                <div  class="container5">

                                        <div  style="margin-top:30px">
                                            <div class="row">
                                                <div class="col">
                                                    <label >Medication:</label>
                                                </div>
                                                <div class="col">
                                                <input type="text" class="form-control" v-model="medicine">

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
                                                    <td>{{med.medicineName}}</td>
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
                                                <input type="text" v-model="endDate" placeholder="01/01/2020" />
                                            </div>
                                        </div>
                    

                                </div>


                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" block @click="hideModal">Close</button>
                        <button class="btn btn-primary" @click="hideModal">Share order</button>
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
       show : false,
       medicine : "",
       quantity : 0,
       showTable : false,
       medicationQuantityList : [],
       endDate : "",



    }
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
      addNewMedicine : function(){
               this.showTable = true;
                const medicineWithQuantity = {
                    medicineName: this.medicine,
                    quantity: this.quantity,
                };
                this.medicationQuantityList.push(medicineWithQuantity)
      },
      sendOrder : function(){
           const order = {
                    medicinesWithQuantity: this.medicationQuantityList,
                    date: this.endDate,
                };
            console.log(order);
      }
   
}
}
</script>

<style>
@media (min-width: 992px)
 .modal-lg {
    max-width: auto !important;

   }
@media (min-width: 992px)
 .modal-dialog {
    margin: 100px !important;

   }


@media (min-width: 800px)
  .modal-dialog {
    max-width: auto !important;

  }
</style>


  