<template>
  <div v-if="isAuthorized" id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
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
                  <strong class="tab"></strong>  
                 <button class = "btn btn-link btn-lg" v-on:click = "addMedicine">Add medication</button>
                  <strong class="tab"></strong>  
                 <button class = "btn btn-link btn-lg" v-on:click = "medicationSearch">Medications</button>
                  <strong class="tab"></strong>  
                 <button class = "btn btn-link btn-lg" v-on:click = "defineLoyaltyProgram">Loyalty program</button>
                  <strong class="tab"></strong>  
                 <button class = "btn btn-link btn-lg" v-on:click = "showComplaints">Complaints</button>
            </span>
            <span  style="float:right;margin:15px">
                  <strong class="tab"></strong>  
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
             </span>
        </div>
       <div style="background: white; height: 60px; margin-top: 20px">
          <span  style="float:right;margin:15px">
            <div class="input-group mb-3">
              <input type="text" v-model="medicationName" class="form-control" placeholder="Enter name..." aria-label="Enter name..." aria-describedby="basic-addon2">
                 <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button"  v-on:click = "showMedicationInfo" >Search</button>
                  </div>
            </div>
          </span>
          <span  style="float:right;margin:15px">
              <div class="input-group mb-3">
                  <label>Form:</label>
                   <div class="input-group-append  align-self-center">
                      <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose medication form" class = "btn btn-link btn-lg " style="float:left;margin-left:20px;">
                              <b-dropdown-item v-for="item in this.forms"  v-on:click ="formIsSelected($event, item.form)" v-bind:key="item.form"> {{item.form }}</b-dropdown-item>
                      </b-dropdown>
                  </div>
              </div>
          </span>  
           <span  style="float:right;margin:15px">
              <div class="input-group mb-3">
                  <label>Type:</label>
                   <div class="input-group-append  align-self-center">
                       <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose medication type" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                              <b-dropdown-item v-for="item in this.types"  v-on:click ="typeIsSelected($event, item.type)" v-bind:key="item.type"> {{item.type }}</b-dropdown-item>
                        </b-dropdown> 
                  </div>
              </div>
          </span>  
           <span  style="float:right;margin:15px">
              <div class="input-group mb-3">
                  <label>Average mark around:</label>
                   <div class="input-group-append  align-self-center">
                       <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose medication mark" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                              <b-dropdown-item v-for="item in this.marks"  v-on:click ="markIsSelected($event, item.mark)" v-bind:key="item.mark"> {{item.mark }}</b-dropdown-item>
                        </b-dropdown> 
                  </div>
              </div>
          </span>  
        </div>
    <h4 style="color: #0D184F;margin:auto; width: 50%;margin-top:45px;">Medications:</h4>

      <div style = "background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">

          <div class="row">
                        <div class=" form-group col">
                            <label >Medication name</label>
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
                            <label >Specification</label>
                        </div>
                          <div class=" form-group col">
                            <label >Availability in pharmacies</label>
                        </div>

           </div>
      </div>
      <div v-if = "showMedicationInfoDiv" style = "background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">

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
                        <button v-on:click = "showMedicationSpecification" class="btn btn-primary">Show</button>
                    </div>
                    <div class=" form-group col">
                        <button v-on:click =  "showMedicationPharmacyAvailability($event, medicationInfo.code)"  class="btn btn-primary">Show</button>
                    </div>
            </div>
      </div>
            <div v-if="choosenMedicationForAvailability==medicationInfo.code">
                          <div v-if="showMedicationPharmacyAvailabilityDiv" style = "background-color:lightgray; margin: auto; width: 40%;border: 3px solid #0D184F;padding: 0px;margin-top:0px;">

                                <div v-for="pharmacy in pharmaciesAvailability"   v-bind:key="pharmacy.id">

                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col">
                                                <label>Pharmacy {{pharmacy.pharmacyName}}</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col">
                                                <label>Pharmacy average mark  {{pharmacy.mark}}</label>
                                            </div>
                                        </div>   
                                        <div class="row">
                                            <div class="col">
                                                <label>Price {{pharmacy.sumPrice}}</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col">
                                                <label>Address  {{pharmacy.address.country}}, {{pharmacy.address.town}}, {{pharmacy.address.street}} {{pharmacy.address.number}}</label>
                                            </div>
                                        </div>
                                    </div>
                                    <hr/>
                                    <hr/>
                              </div>
                      </div>
            
      </div>
       <div v-if = "showMedicationListInfoDiv"> 
              <div v-for="medicine in medicationSeacrhList"   v-bind:key="medicine.medicationId">
                  <div style = "background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">

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
                            <button v-on:click = "showMedicationSpecificationList($event, medicine.name)" class="btn btn-primary">Show</button>
                        </div>
                        <div class=" form-group col">
                            <button v-on:click = "showMedicationPharmacyAvailabilityList($event, medicine.code)" class="btn btn-primary">Show</button>
                        </div>
                      </div>
                  </div>
                      <div v-if="choosenMedicationForAvailability==medicine.code">
                          <div v-if="showMedicationPharmacyAvailabilityListDiv" style = "background-color:lightgray; margin: auto; width: 40%;border: 3px solid #0D184F;padding: 0px;margin-top:0px;">

                                <div v-for="pharmacy in pharmaciesAvailability"   v-bind:key="pharmacy.id">

                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col">
                                                <label>Pharmacy {{pharmacy.pharmacyName}}</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col">
                                                <label>Pharmacy average mark  {{pharmacy.mark}}</label>
                                            </div>
                                        </div>   
                                        <div class="row">
                                            <div class="col">
                                                <label>Price {{pharmacy.sumPrice}}</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col">
                                                <label>Address  {{pharmacy.address.country}}, {{pharmacy.address.town}}, {{pharmacy.address.street}} {{pharmacy.address.number}}</label>
                                            </div>
                                        </div>
                                    </div>
                                    <hr/>
                                    <hr/>
                                </div>
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
            medicationId : 0,
            code : 0
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
      showMedicationListInfoDiv: true,
      concreteSpecification :{
                    contraIndications :"",
                    structure : "",
                    recommendedConsumption : "",
                    manufacturer : ""
            },
        pharmaciesAvailability : [],
        showMedicationPharmacyAvailabilityDiv : false,
        showMedicationPharmacyAvailabilityListDiv : false,
        choosenMedicationForAvailability : 0,
        isAuthorized : false
       
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
      
      showComplaints : function() {
            window.location.href = "/complaints";
      },
       formIsSelected : function(event, form) { 
           this.axios.get('/medication/searchForm/'+form).
            then(response => {
                    this.medicationSeacrhList= response.data;
                    this.showMedicationInfoDiv=false;
                    this.showMedicationListInfoDiv = true;
                    this.showMedicationPharmacyAvailabilityListDiv = false;
                    this.showMedicationPharmacyAvailabilityDiv = false;

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
                    this.showMedicationPharmacyAvailabilityListDiv = false;
                    this.showMedicationPharmacyAvailabilityDiv = false;
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
                     this.showMedicationPharmacyAvailabilityListDiv = false;
                    this.showMedicationPharmacyAvailabilityDiv = false;
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
                        alert("There is no medication with entered name.");
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
      showMedicationPharmacyAvailability : function(event, code) {
            this.pharmaciesAvailability = []
            this.axios.get('/pharmacy/medicationAvailability/'+code,).then(response => {
                          this.pharmaciesAvailability= response.data;
                          this.choosenMedicationForAvailability = code;
                          this.showMedicationPharmacyAvailabilityListDiv = false;
                          this.showMedicationPharmacyAvailabilityDiv = true;
                    }).catch(res => {
                                  alert("NOT OK");
                                    console.log(res);
                            });
      },
      showMedicationPharmacyAvailabilityList : function(event, code) {
            this.pharmaciesAvailability = []
            this.axios.get('/pharmacy/medicationAvailability/'+code,).then(response => {
                          this.pharmaciesAvailability= response.data;
                          this.choosenMedicationForAvailability = code;
                          this.showMedicationPharmacyAvailabilityDiv = false;
                          this.showMedicationPharmacyAvailabilityListDiv = true;
                    }).catch(res => {
                                  alert("NOT OK");
                                    console.log(res);
                            });
      },
      
},
 mounted() {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/systemAdmin/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
             this.isAuthorized = true;
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
             this.isAuthorized = false;
                alert("Please, log in first!");
                window.location.href = "/login";
                console.log(res);
        });

         this.axios.get('/medication/getAll',).then(response => {
               this.medicationSeacrhList= response.data;
         }).catch(res => {
                       alert("Please log in again or try later.");
                        console.log(res);
                 });
    }
}
</script>