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
                 <b class="tab"></b>  
                 <button class = "btn btn-link btn-lg" v-on:click = "showComplaints">Complaints</button>
                 

            </span>
              <span  style="float:right;margin:15px">
                     <b class="tab"></b>    
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
                </span>
        </div>
       <div style = "background-color:lightgray; margin: auto; width: 30%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
                       <h3 style="color: #0D184F;margin-bottom:20px">Filters</h3>
                <div class="form-row">
                    <div class="form-group col-md-10">
                        <input type="text" class="form-control" v-model="medicationName" placeholder="Enter name...">
                    </div>
                    <div class="form-group col-md-2">
                        <button v-on:click = "showMedicationInfo" class="btn btn-primary">Confirm</button>
                    </div>
               </div>
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <label>Form:</label>
                       
                    </div>
                    <div class="form-group col-md-2">
                         <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose medication form" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                              <b-dropdown-item v-for="item in this.forms"  v-on:click ="formIsSelected($event, item.form)" v-bind:key="item.form"> {{item.form }}</b-dropdown-item>
                        </b-dropdown>
                    </div>
               </div>
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <label>Type:</label> 
                    </div>
                    <div class="form-group col-md-2">
                         <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose medication type" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                              <b-dropdown-item v-for="item in this.types"  v-on:click ="typeIsSelected($event, item.type)" v-bind:key="item.type"> {{item.type }}</b-dropdown-item>
                        </b-dropdown> 
                    </div>
               </div>
               <div class="form-row">
                    <div class="form-group col-md-2">
                        <label>Average mark around:</label> 
                    </div>
                    <div class="form-group col-md-2">
                         <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose medication mark" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                              <b-dropdown-item v-for="item in this.marks"  v-on:click ="markIsSelected($event, item.mark)" v-bind:key="item.mark"> {{item.mark }}</b-dropdown-item>
                        </b-dropdown> 
                    </div>
               </div>
        </div>
        <div v-if = "showMedicationInfoDiv" style = "background-color:lightgray; margin: auto; width: 60%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
               <div class="row">
                    <div class=" form-group col">
                        <label >Name</label>
                    </div>
                    <div class=" form-group col">          
                        <label >Type</label>
                    </div>
                    <div class=" form-group col">
                        <label >Form</label>
                    </div>
                    <div class=" form-group col">          
                        <label >Mark</label>
                    </div>
                    <div class=" form-group col">
                        <label ></label>
                    </div>
               </div>
               <div class="row">
                    <div class=" form-group col">
                        <label >{{medicationInfo.name}}</label>
                    </div>
                    <div class=" form-group col">          
                        <label >{{medicationInfo.type}}</label>
                    </div>
                    <div class=" form-group col">
                        <label >{{medicationInfo.form}}</label>
                    </div>
                    <div class=" form-group col">          
                        <label >{{medicationInfo.mark}}</label>
                    </div>
                    <div class=" form-group col">
                        <button v-on:click = "showMedicationSpecification" class="btn btn-primary">Specification</button>
                    </div>
               </div>
        </div>
        <div v-if = "showMedicationListInfoDiv" style = "background-color:lightgray; margin: auto; width: 60%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
               <div class="row">
                    <div class=" form-group col">
                        <label >Name</label>
                    </div>
                    <div class=" form-group col">          
                        <label >Type</label>
                    </div>
                    <div class=" form-group col">
                        <label >Form</label>
                    </div>
                    <div class=" form-group col">          
                        <label >Mark</label>
                    </div>
                    <div class=" form-group col">
                        <label ></label>
                    </div>
               </div>
               <div v-for="medicine in medicationSeacrhList"   v-bind:key="medicine.name">
                <div class="row">
                        <div class=" form-group col">
                            <label >{{medicine.name}}</label>
                        </div>
                        <div class=" form-group col">          
                            <label >{{medicine.type}}</label>
                        </div>
                        <div class=" form-group col">
                            <label >{{medicine.form}}</label>
                        </div>
                        <div class=" form-group col">          
                            <label >{{medicine.mark}}</label>
                        </div>
                        <div class=" form-group col">
                            <button v-on:click = "showMedicationSpecificationList($event, medicine.name)" class="btn btn-primary">Specification</button>
                        </div>
                </div>
               </div>
        </div>
        <div> 
          <b-modal ref="specification-modal" hide-footer scrollable title="Medication specification" size="lg" modal-class="b-modal">
               <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:whitesmoke">
                         <div class="modal-body">
                             <div class="row">
                                <div class=" form-group col">
                                     <label>Contra indications</label> 
                                </div>
                                <div class=" form-group col">  
                                      <label>{{medicationInfo.specification.contraIndications}}</label> 
                                </div>
                             </div>
                            <div class="row">
                                <div class=" form-group col">
                                     <label>Structure</label> 
                                </div>
                                <div class=" form-group col">  
                                      <label>{{medicationInfo.specification.structure}}</label> 
                                </div>
                             </div>
                             <div class="row">
                                <div class=" form-group col">
                                     <label>Recommended consumption</label>
                                </div>
                                <div class=" form-group col">  
                                      <label>{{medicationInfo.specification.recommendedConsumption}}</label> 
                                </div>
                             </div>
                            
                            <div class="row">
                                <div class=" form-group col">
                                    <label>Manufacturer</label>
                                </div>
                                <div class=" form-group col">  
                                      <label>{{medicationInfo.specification.manufacturer}}</label> 
                                </div>
                             </div>                               
                         </div>                
                    </div>
               </div>
          </b-modal>
       </div>
       <div> 
          <b-modal ref="specificationList-modal" hide-footer scrollable title="Medication specification" size="lg" modal-class="b-modal">
               <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:whitesmoke">
                         <div class="modal-body">
                             <div class="row">
                                <div class=" form-group col">
                                     <label>Contra indications</label> 
                                </div>
                                <div class=" form-group col">  
                                      <label>{{concreteSpecification.contraIndications}}</label> 
                                </div>
                             </div>
                            <div class="row">
                                <div class=" form-group col">
                                     <label>Structure</label> 
                                </div>
                                <div class=" form-group col">  
                                      <label>{{concreteSpecification.structure}}</label> 
                                </div>
                             </div>
                             <div class="row">
                                <div class=" form-group col">
                                     <label>Recommended consumption</label>
                                </div>
                                <div class=" form-group col">  
                                      <label>{{concreteSpecification.recommendedConsumption}}</label> 
                                </div>
                             </div>
                            <div class="row">
                                <div class=" form-group col">
                                    <label>Manufacturer</label>
                                </div>
                                <div class=" form-group col">  
                                      <label>{{concreteSpecification.manufacturer}}</label> 
                                </div>
                             </div>                               
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
      accountInformation :null,
      allowSystemAdminRegistration : false,
      notallowSystemAdminRegistration : false,
      medicationName : "",
      specificationInfo : {
            contraIndications :"",
            structure : "",
            recommendedConsumption : "",
            manufacturer : ""
      },
      medicationInfo : {
            name : "",
            form : "",
            type :"",
            issuanceRegime :"",
            mark : 0,
            specification: {
                    contraIndications :"",
                    structure : "",
                    recommendedConsumption : "",
                    manufacturer : ""
            },
      },
      showMedicationInfoDiv : false, 
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
      marks: [
          { mark: "0-1" },
          { mark: "1-2" },
          { mark: "2-3" },
          { mark: "3-4" },
          { mark: "4-5" },
      ],
      choosenForm : "",
      choosenType : "",
      choosenMark :0,
      medicationSeacrhList : [],
      showMedicationListInfoDiv: false,
      concreteSpecification :{
                    contraIndications :"",
                    structure : "",
                    recommendedConsumption : "",
                    manufacturer : ""
            },
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
      defineLoyaltyProgram : function(){
          window.location.href = "/loyaltyProgram";
      },
      medicationSearch : function() {
          window.location.href = "/systemAdminMedicationSearch";
      }, 
      formIsSelected : function(event, form) { 
           this.axios.get('/medication/searchForm/'+form).
            then(response => {
                    this.medicationSeacrhList= response.data;
                    this.showMedicationInfoDiv=false;
                    this.showMedicationListInfoDiv = true;
            }).catch(res => {
                        alert("NOT OK");
                        console.log(res);
                    });
      },
      typeIsSelected : function(event, type) { 
           this.axios.get('/medication/searchType/'+type).
            then(response => {
                    this.medicationSeacrhList= response.data;
                    this.showMedicationInfoDiv=false;
                    this.showMedicationListInfoDiv = true;
            }).catch(res => {
                        alert("NOT OK");
                        console.log(res);
                    });
      },
      markIsSelected : function(event, mark) { 
            var marks = mark.split('-')
            var MarkMin = parseInt(marks[0])
            var MarkMax = parseInt(marks[1])
            this.axios.get('/medication/searchMark/'+MarkMin+"/"+MarkMax).
                then(response => {
                     this.medicationSeacrhList= response.data;
                     this.showMedicationInfoDiv=false;
                     this.showMedicationListInfoDiv = true;
                }).catch(res => {
                     alert("NOT OK");
                    console.log(res);
                });     
     },
      showMedicationInfo : function() {
            this.axios.get('/medication/searchName/'+this.medicationName).
            then(response => {
                    this.medicationInfo= response.data;
                    this.showMedicationListInfoDiv = false;
                    this.showMedicationInfoDiv = true;
            }).catch(res => {
                        alert("NOT OK");
                        console.log(res);
                    });
      },
      showMedicationSpecification : function() {
         this.$refs['specification-modal'].show();
      },
      closeSpecification : function() {
        this.$refs['specification-modal'].hide()
      },
      showMedicationSpecificationList : function($event, name) {
          let i =0;
          for(i=0; i< this.medicationSeacrhList.length;i++) {
              if(this.medicationSeacrhList[i].name===name) {
                  this.concreteSpecification = this.medicationSeacrhList[i].specification;
              }
          }
          this.$refs['specificationList-modal'].show();
      },
       closeSpecificationList : function() {
        this.$refs['specificationList-modal'].hide()
      }, 
      showComplaints : function() {
            window.location.href = "/complaints";
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
    }
}
</script>

<style>

</style>


  