<template>
    <div class = "row h-100">
    <div class="col-sm-1.5 vh-100" style="background-color:blue !important">  
           
    </div>
    <div class="col-sm" style="!important">  
           
    </div>
    <div class="col-sm" style=" !important">  
           
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
