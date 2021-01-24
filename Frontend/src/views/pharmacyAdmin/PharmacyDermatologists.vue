<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
              <span style="float: left; margin-left: 270px;!important">
                <a href= "/isaHomePage">Home</a>
                <b class="tab"></b>    
                <a href = "/pharmacyAdminProfile">My profile</a>
                <b class="tab"></b>    
                <a href = "/myPharmacy">My Pharmacy</a>
                       
            </span>
              <span  style="float:right;margin:15px">
                   
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
                </span>
        </div>
        
        <div class="sidenav">
        <hr/>
        <a href = "/pharmacyDermatologists">Dermatologists</a>
        <a href = "/pharmacyPharmacists">Pharmacists</a>
        <a href = "/pharmacyMedications">Medications</a>
        <a href="#">Contact</a>
        </div>

        <div class="container-fluid">

        <div v-if="showMed">
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
                                                <input type="text" class="form-control">

                                                </div>


                                            </div>
                                            <div class="row">

                                                <div class="col">
                                                    <label>Quantity:</label>
                                                </div>
                                                <div class="col">
                                                    <input type="number"  placeholder="Enter quantity..">
                                                </div>

                                            </div>
                                            <div class="row">
                                                <div class="col">
                                                </div>
                                                <div class="col">
                                                    <button class="btn btn-secondary" style="margin-left:3px">&nbsp;&nbsp;+&nbsp;&nbsp;</button>
                                                </div>
                                            </div>
                                           





                                        </div>
                                        <div class="row">
                                            <table class="table table-striped table-dark">
                                                <thead>
                                                <th>Medicine name</th>
                                                <th>Quantity</th>
                                                </thead>
                                                
                                            </table>
                                        </div>
                                        <hr />
                                        <div class="row">
                                            <div class="col">
                                                <label for="name">Closing date:</label>
                                            </div>
                                            <div class="col">
                                                <input type="text"  placeholder="01/01/2020" />
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
        

       

       

    </div>
</template>

<script>
export default {

  data() {
    return {
       showMed : false,
    }
  },

  methods:{
       showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      showMyProfile: function(){

      },
       showActionsAndBenefitsForm : function(){
              this.$refs['my-modal'].show()
      },
       showOrderForm : function(){
          window.location.href = "/order";

      },
       logOut : function(){
           window.location.href = "/login";
      },
      showMyPharmacy : function (){
          window.location.href = "/myPharmacy"
      },
      addNewPharmacist : function(){
        window.location.href = "/addNewPharmacist";
      },
      showDermatologists : function(){

      },
      showPharmacists : function(){

      },
      showMedications : function(){
        this.showMed = true;
      },
      showModal() {
        this.$refs['my-modal'].show()
      },
      hideModal() {
        this.$refs['my-modal'].hide()
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
  width: 200px;
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


  