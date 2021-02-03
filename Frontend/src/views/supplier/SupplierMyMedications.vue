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
       <div style = "background-color:lightgray; margin: auto; width: 60%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
               <div class="row">
                    <div class=" form-group col">
                        <label >Name</label>
                    </div>
                    <div class=" form-group col">
                        <label >Code</label>
                    </div>
                    <div class=" form-group col">          
                        <label >Type</label>
                    </div>
                    <div class=" form-group col">
                        <label >Form</label>
                    </div>
                    <div class=" form-group col">          
                        <label >Reserved quantity for offers</label>
                    </div>
                    <div class=" form-group col">          
                        <label >Available quantity</label>
                    </div>
                    <div class=" form-group col">
                        <label ></label>
                    </div>
                   
               </div>
               <div v-for="medicationInfo in myMedications"   v-bind:key="medicationInfo.code">
                    <div class="row">
                            <div class=" form-group col">
                                <label >{{medicationInfo.name}}</label>
                            </div>
                            <div class=" form-group col">          
                                <label >{{medicationInfo.code}}</label>
                            </div>
                            <div class=" form-group col">
                                <label >{{medicationInfo.type}}</label>
                            </div>
                            <div class=" form-group col">          
                                <label >{{medicationInfo.form}}</label>
                            </div>
                             <div class=" form-group col">          
                                <label >{{medicationInfo.reservedQuantity}}</label>
                            </div>
                             <div class=" form-group col">          
                                <label >{{medicationInfo.quantity}}</label>
                            </div>
                           
                            <div class=" form-group col">
                                <button v-on:click = "showMedicationQuantity($event,medicationInfo)" class="btn btn-primary">Add more quantity</button>
                            </div>
                    </div>
               </div>
        </div>
      
       <div> 
          <b-modal ref="quantity-modal" hide-footer scrollable title="Medication specification" size="lg" modal-class="b-modal">
               <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:whitesmoke">
                         <div class="modal-body">
                             <div class="row">
                                <div class=" form-group col">
                                     <label>Available quantity: </label> 
                                </div>
                                <div class=" form-group col">  
                                    <input type="text" class="form-control" v-model="choosenMedicationQuantity" placeholder="Quantity..">
                                </div>
                             </div>
                            <button v-on:click = "changeQuantity" class="btn btn-primary">Confirm</button> 
                                                       
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
        myMedications : [],
        choosenMedication : {},
        choosenMedicationQuantity : 0,
        choosenMedicationId : null
    
    }
  },

  methods:{
      logOut : function(){
           localStorage.removeItem('token');
           window.location.href = "/login";
      },
      changeQuantity : function() {
            const quantityMedicationInfo ={
                medicationId : this.choosenMedicationId,
                newQuantity : this.choosenMedicationQuantity,
            } 
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/supplierMedications/update',quantityMedicationInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                    console.log(response);
                    this.getMyMedications();

                   alert("Quantity is successfully updated!"); 
                }).catch(res => {
                       alert("Please try later.");
                        console.log(res);
                });
            this.$refs['quantity-modal'].hide();
            

      },
     
      showMedicationQuantity : function(event, medicationInfo) {
          this.choosenMedication = medicationInfo;
          this.choosenMedicationQuantity = medicationInfo.quantity;
          this.choosenMedicationId = medicationInfo.id;
           this.$refs['quantity-modal'].show();
      },
      getMyMedications : function() {
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
          this.myMedications = []
          this.axios.get('/supplierMedications',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
             this.myMedications=response.data;
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });
      }
      
      
},
 mounted() {
        this.getMyMedications();
    }
}
</script>

<style>

</style>


  