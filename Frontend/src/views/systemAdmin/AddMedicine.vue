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
                 <b class="tab"></b>   
                 <button class = "btn btn-link btn-lg" v-on:click = "addMedicine">Add medication</button>
                 <b class="tab"></b>   
                 <button class = "btn btn-link btn-lg" v-on:click = "medicationSearch">Medications</button>
                 <b class="tab"></b>  
                 <button class = "btn btn-link btn-lg" v-on:click = "defineLoyaltyProgram">Loyalty program</button>
            </span>
            <span  style="float:right;margin:15px">
                     <b class="tab"></b>    
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
             </span>
        </div>
        <div style="background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
            <h3 style="color: #0D184F">Add new medicine</h3>
                
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Name:</label>
                        <input type="text" class="form-control" v-model="name" placeholder="Enter name">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Code:</label>
                        <input type="number" class="form-control" v-model = "code" placeholder="Enter code">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Form:</label>
                        <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose medication form" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                              <b-dropdown-item v-for="item in this.forms"  v-on:click ="formIsSelected($event, item.form)" v-bind:key="item.form"> {{item.form }}</b-dropdown-item>
                        </b-dropdown> 
                        </div>
                        <div class="form-group col-md-6">
                        <label>Type:</label>
                         <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose medication type" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                              <b-dropdown-item v-for="item in this.types"  v-on:click ="typeIsSelected($event, item.type)" v-bind:key="item.type"> {{item.type }}</b-dropdown-item>
                        </b-dropdown> 
                        </div>
                    </div>
                      <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Issuance Regime:</label>
                        <input type="text" class="form-control" v-model="issuanceRegime" placeholder="Enter issuance Regime">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Way of selling:</label>
                       <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose way of selling" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                              <b-dropdown-item v-for="item in this.waysSelling"  v-on:click ="wayOfSellingIsSelected($event, item.way)" v-bind:key="item.way"> {{item.way }}</b-dropdown-item>
                        </b-dropdown> 
                        </div>
                    </div>
                     <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Loyalty Points:</label>
                        <input type="number" class="form-control" v-model="loyaltyPoints" placeholder="Enter loyalty points">
                        </div>
                    </div>
                     <div class="form-row">
                        <div class="form-group col-md-6">
                            <button class="btn btn-primary btn-lg" v-on:click = "showSpecificationModal">Click to add specification</button>
                        </div>
                        <div class="form-group col-md-6">
                            <button class="btn btn-primary btn-lg" v-on:click = "showAlternativesModal">Click to add alternatives</button>
                        </div>
                    </div>
                   
                    <button class="btn btn-primary btn-lg" v-on:click = "add">Add medicine</button>
                    <div style="height:30px;"></div>
        </div>
       <div> 
          <b-modal ref="specification-modal" hide-footer scrollable title="Fill medicine specification" size="lg" modal-class="b-modal">
               <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:whitesmoke">
                         <div class="modal-body">
                         <div class="form-group">
                              <label>Define contra indications:</label>
                              <input type="text" class="form-control" v-model="specification.contraIndications" placeholder="Enter contra indications">
                         </div>
                         <div class="form-group">
                              <label>Define structure:</label>
                              <input type="text" class="form-control" v-model="specification.structure" placeholder="Enter structure">
                         </div>
                         <div class="form-group">
                              <label>Define recommended consumption:</label>
                              <input type="text" class="form-control" v-model="specification.recommendedConsumption" placeholder="Enter recommended consumption">
                         </div>
                              <div class="form-group">
                              <label>Manufacturer:</label>
                              <input type="text" class="form-control" v-model="specification.manufacturer" placeholder="Enter manufacturer">
                         </div>
                         <button v-on:click = "confirmSpecification" class="btn btn-primary">Confirm</button>        
                         </div>                
                    </div>
               </div>
          </b-modal>
       </div>
        <b-modal ref="alternatives-modal" hide-footer scrollable title="Fill medicine specification" size="lg" modal-class="b-modal">
            <div modal-class="modal-dialog" role="document">
                <div class="modal-content" style="background-color:whitesmoke">
                    <div class="modal-body">
                        <div class="form-group col-md-6">
                              <label>Form:</label>
                              <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose alternatives" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                                        <b-dropdown-item v-for="item in this.allMedications"  v-on:click ="addAlternativeTolist($event, item)" v-bind:key="item.id"> {{item.name }}</b-dropdown-item>
                              </b-dropdown> 
                        </div>
                        <div class="form-group">
                            <hr/>
                        </div>
                        
                        <button v-on:click = "confirmAltenratives" class="btn btn-primary">Confirm</button>        
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
      accountInformation :null,
      allowSystemAdminRegistration : false,
      notallowSystemAdminRegistration : false,
      forms: [
          { form: 'Capsule' },
          { form: 'Powder' },
          { form: 'Gel' },
          { form: 'Tablet' },
          { form: 'Pill' },
          { form: 'Syrup' },
      ],
      types: [
          { type: 'Antibiotik' },
          { type: 'Anesthetic' },
          { type: 'Antihistamine' },
          { type: 'TaAntidepressantblet' },
          { type: 'For dementia' },
          { type: 'For addiction' },
      ],
      name : "",
      code : 0,
      issuanceRegime : "",
      waysSelling: [
          { way: 'With receipt' },
          { way: 'Without receipt' },
      ],
      loyaltyPoints :0,
      specification : {
           contraIndications : "",
           structure : "",
           recommendedConsumption : "", 
           manufacturer : ""
      },
      allMedications : null,
      alternativeMedications : [],
      choosenType : "",
      choosenForm : "",
      choosenWyOfSelling : ""
    }
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
      logOut : function(){
          localStorage.removeItem('token');
          window.location.href = "/login";
      },
      addMedicine : function() {
           window.location.href = "/addMedicine";
      },
      add : function() {
          const medicationInfo = {
               name : this.name,
               code : this.code,
               form : this.choosenForm,
               type : this.choosenType,
               wayOfSelling : this.wayOfSelling,
               issuanceRegime : this.issuanceRegime,
               mark : 0,
               loyaltyPoints : this.loyaltyPoints,
               specification : this.specification,
               medicationsAlternatives : this.alternativeMedications
          }
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

          this.axios.post('/medication/add',medicationInfo,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }}).then(response => {
                       alert("Medicine is added!");
                        console.log(response.data);
                })
                .catch(response => {
                       alert("Please try later");
                        console.log(response);
                 });    

      },
      typeIsSelected : function(event, type) { 
           this.choosenType = type;
           console.log(this.choosenType);
      },
      formIsSelected : function(event, form) { 
           this.choosenForm = form;
           console.log(this.choosenForm);
      },
       wayOfSellingIsSelected : function(event, way) { 
          this.wayOfSellingIsSelected = way;
          console.log(this.wayOfSellingIsSelected);

      },
      showSpecificationModal : function() {
          this.$refs['specification-modal'].show();
      },
      showAlternativesModal : function() {
          this.$refs['alternatives-modal'].show();
      },
      confirmSpecification : function() {
        this.$refs['specification-modal'].hide()
      },
      addAlternativeTolist : function(event, item) {
           this.alternativeMedications.push(item);
      },
      confirmAltenratives : function() {
          this.$refs['alternatives-modal'].hide()
      },
      defineLoyaltyProgram : function(){
          window.location.href = "/loyaltyProgram";
      },
      medicationSearch : function() {
          window.location.href = "/systemAdminMedicationSearch";
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
     this.axios.get('/medication',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
              this.allMedications = response.data;
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });
    }
}
</script>

<style>

</style>


  