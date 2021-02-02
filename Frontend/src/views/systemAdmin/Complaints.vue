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

         <div style = "background-color:lightgray; margin: auto; width: 60%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
             <h3> Complaints on pharmacies</h3>
               <div class="row">
                    <div class=" form-group col">
                        <label >Patient</label>
                    </div>
                     
                    <div class=" form-group col">          
                        <label >Pharmacy name</label>
                    </div>
                     <div class=" form-group col">          
                        <label >Pharmacy info</label>
                    </div>
                    <div class=" form-group col">          
                        <label >See complaint</label>
                    </div>
                   
               </div>
               <div v-for="pharmacyComplaint in allComplaintsPharmacies"   v-bind:key="pharmacyComplaint.id">
                <div class="row">
                        <div class=" form-group col">
                            <label >{{pharmacyComplaint.patient.firstname + "  " +pharmacyComplaint.patient.surname}}</label>
                        </div>
                        <div class=" form-group col">
                            <label >{{pharmacyComplaint.pharmacyName.pharmacyName}}</label>
                        </div>
                        <div class=" form-group col">
                            <button class = "btn btn-link btn-lg" v-on:click = "seePharmacyInfo($event, pharmacyComplaint)">Pharmacy info</button>
                        </div>
                        <div class=" form-group col">          
                            <button class = "btn btn-link btn-lg" v-on:click = "seeComplaint($event, pharmacyComplaint)">Complaint</button>
                        </div>
                        
                </div>
               </div>
        </div>
        <div style = "background-color:lightgray; margin: auto; width: 60%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
             <h3> Complaints on dermatologists</h3>
               <div class="row">
                    <div class=" form-group col">
                        <label >Patient</label>
                    </div>
                     
                    <div class=" form-group col">          
                        <label >Dermatologist</label>
                    </div>
                     <div class=" form-group col">          
                        <label >Dermatologist info</label>
                    </div>
                    <div class=" form-group col">          
                        <label >See complaint</label>
                    </div>
                   
               </div>
               <div v-for="dermatologistComplaint in allComplaintsDermatologists"   v-bind:key="dermatologistComplaint.id">
                <div class="row">
                        <div class=" form-group col">
                            <label >{{dermatologistComplaint.patient.firstname + "  " +dermatologistComplaint.patient.surname}}</label>
                        </div>
                        <div class=" form-group col">
                            <label >{{dermatologistComplaint.dermatologist.firstname + "  " +dermatologistComplaint.dermatologist.surname}}</label>
                        </div>
                        <div class=" form-group col">
                            <button class = "btn btn-link btn-lg" v-on:click = "seeDermatologistInfo($event, dermatologistComplaint)">Dermatologist info</button>
                        </div>
                        <div class=" form-group col">          
                            <button class = "btn btn-link btn-lg" v-on:click = "seeComplaint($event, dermatologistComplaint)">Complaint</button>
                        </div>
                        
                </div>
               </div>
        </div>
         <div style = "background-color:lightgray; margin: auto; width: 60%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
             <h3> Complaints on pharmacists</h3>
               <div class="row">
                    <div class=" form-group col">
                        <label >Patient</label>
                    </div>
                     
                    <div class=" form-group col">          
                        <label >Pharmacist</label>
                    </div>
                     <div class=" form-group col">          
                        <label >Pharmacist info</label>
                    </div>
                    <div class=" form-group col">          
                        <label >See complaint</label>
                    </div>
                   
               </div>
               <div v-for="pharmacistComplaint in allComplaintsPharmacist"   v-bind:key="pharmacistComplaint.id">
                <div class="row">
                        <div class=" form-group col">
                            <label >{{pharmacistComplaint.patient.firstname + "  " +pharmacistComplaint.patient.surname}}</label>
                        </div>
                        <div class=" form-group col">
                            <label >{{pharmacistComplaint.pharmacist.firstname + "  " +pharmacistComplaint.pharmacist.surname}}</label>
                        </div>
                        <div class=" form-group col">
                            <button class = "btn btn-link btn-lg" v-on:click = "seePharmacistInfo($event, pharmacistComplaint)">Pharmacist info</button>
                        </div>
                        <div class=" form-group col">          
                            <button class = "btn btn-link btn-lg" v-on:click = "seeComplaint($event, pharmacistComplaint)">Complaint</button>
                        </div>
                        
                </div>
               </div>
        </div>

        <b-modal ref="pharmacyInfo" hide-footer scrollable title="" size="lg" modal-class="b-modal">
            <div modal-class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content" style="background-color:whitesmoke">
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Name: {{choosenComplaintPharmacy.pharmacyName.pharmacyName }}</label>
                        </div>
                        <div class="form-group">
                            <label>Consulting price: {{choosenComplaintPharmacy.pharmacyName.consultingPrice }}</label>
                        </div>
                        <div class="form-group">
                            <label>Address: {{choosenComplaintPharmacy.pharmacyName.address.country }},  {{choosenComplaintPharmacy.pharmacyName.address.town }}, 
                                {{choosenComplaintPharmacy.pharmacyName.address.street }}  {{choosenComplaintPharmacy.pharmacyName.address.number }}</label>
                        </div>
                    </div>                
                </div>
            </div>
        </b-modal>
        <b-modal ref="dermatologistInfo" hide-footer scrollable title="" size="lg" modal-class="b-modal">
            <div modal-class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content" style="background-color:whitesmoke">
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Name: {{choosenComplaintDermatologist.dermatologist.firstname + "  " +choosenComplaintDermatologist.dermatologist.surname}}</label>
                        </div>
                        <div class="form-group">
                            <label>Email: {{choosenComplaintDermatologist.dermatologist.email }}</label>
                        </div>
                        <div class="form-group">
                            <label>Address: {{choosenComplaintDermatologist.dermatologist.address.country }},  {{choosenComplaintDermatologist.dermatologist.address.town }}, 
                                {{choosenComplaintDermatologist.dermatologist.address.street }}  {{choosenComplaintDermatologist.dermatologist.address.number }}</label>
                        </div>
                    </div>                
                </div>
            </div>
        </b-modal>
        <b-modal ref="pharmacistInfo" hide-footer scrollable title="" size="lg" modal-class="b-modal">
            <div modal-class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content" style="background-color:whitesmoke">
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Name: {{choosenComplaintPharmacist.pharmacist.firstname + "  " +choosenComplaintPharmacist.pharmacist.surname}}</label>
                        </div>
                        <div class="form-group">
                            <label>Email: {{choosenComplaintPharmacist.pharmacist.email }}</label>
                        </div>
                        <div class="form-group">
                            <label>Address: {{choosenComplaintPharmacist.pharmacist.address.country }} , {{choosenComplaintPharmacist.pharmacist.address.town }}, 
                                {{choosenComplaintPharmacist.pharmacist.address.street }}  {{choosenComplaintPharmacist.pharmacist.address.number }}</label>
                        </div>
                    </div>                
                </div>
            </div>
        </b-modal>
         <b-modal ref="seeCompplaint" hide-footer scrollable title="Complaint" size="lg" modal-class="b-modal">
            <div modal-class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content" style="background-color:whitesmoke">
                    <div class="modal-body">
                        <div class="form-group">
                            <label>{{choosenComplaint.massage}}</label>
                        </div>
                        <hr />
                        <div class="row">
                               <div class="col">
                                    <label for="name">Enter your answer:</label>
                                </div> 
                        </div>
                        <div class="row">
                            <input type="textarea" style="height:300px;width:750px;background-color:white;" v-model="choosenComplaint.answer" class="form-control">
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" block @click="hideModalComplaint">Close</button>
                            <button class="btn btn-primary" @click="sendAnswer($event,choosenComplaint)">Send answer</button>
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
      accountInformation :null,
      allowSystemAdminRegistration : false,
      notallowSystemAdminRegistration : false,
      allComplaints : [],
      allComplaintsPharmacies : [],
      allComplaintsDermatologists : [],
      allComplaintsPharmacist : [],
      choosenComplaintDermatologist : {
          id : 0,
          patient : {
                email : "",
                password : "",
                firstname : "",
                surname : "",
                phonenumber : "",
                address : null
          },
          subject : "",
          dermatologist : {
                email : "",
                password : "",
                firstname : "",
                surname : "",
                phonenumber : "",
                address : {
                    town : "",
                    street :"",
                    number :0,
                    postalCode : 0,
                    country : ""
                }
            },
            pharmacist : null,
            answered : false,
            answer : "",
            message : ""
          },
          choosenComplaintPharmacist : {
                id : 0,
                patient : {
                        email : "",
                        password : "",
                        firstname : "",
                        surname : "",
                        phonenumber : "",
                        address : null
                },
                subject : "",
                pharmacist : {
                        email : "",
                        password : "",
                        firstname : "",
                        surname : "",
                        phonenumber : "",
                        address : {
                            town : "",
                            street :"",
                            number :0,
                            postalCode : 0,
                            country : ""
                        }
                },
                dermatologist : null,
                answered : false,
                answer : "",
                message : ""
          },
          choosenComplaintPharmacy : {
            id : 0,
            patient : {
                    email : "",
                    password : "",
                    firstname : "",
                    surname : "",
                    phonenumber : "",
                    address : null
            },
            subject : "",
            dermatologist : null,
                pharmacist : null,
                pharmacyName : {
                    pharmacyName : "",
                    address: {
                        town : "",
                        street :"",
                        number :0,
                        postalCode : 0,
                        country : ""
                        },
                    consultingPrice : 0
                },
                answered : false,
                answer : "",
                message : ""
          },
           choosenComplaint : {
          id : 0,
          patient : {
                email : "",
                password : "",
                firstname : "",
                surname : "",
                phonenumber : "",
                address : null
          },
          subject : "",
          dermatologist : {
                email : "",
                password : "",
                firstname : "",
                surname : "",
                phonenumber : "",
                address : {
                    town : "",
                    street :"",
                    number :0,
                    postalCode : 0,
                    country : ""
                }
            },
            pharmacist : {
                email : "",
                password : "",
                firstname : "",
                surname : "",
                phonenumber : "",
                address : {
                    town : "",
                    street :"",
                    number :0,
                    postalCode : 0,
                    country : ""
                    }
            },
            pharmacyName : {
                pharmacyName : "",
                address: {
                    town : "",
                    street :"",
                    number :0,
                    postalCode : 0,
                    country : ""
                    },
                consultingPrice : 0
            },
            answered : false,
            answer : "",
            message : ""
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
      showComplaints : function() {
            window.location.href = "/complaints";
      },
      divideIntoSeparateLists : function(){
          var i=0;
          for(i =0; i<this.allComplaints.length; i++) {
              if(this.allComplaints[i].dermatologist!=null) {
                  this.allComplaintsDermatologists.push(this.allComplaints[i]);
              }
              else if(this.allComplaints[i].pharmacist!=null) {
                this.allComplaintsPharmacist.push(this.allComplaints[i]);
              }
              else {
                    this.allComplaintsPharmacies.push( this.allComplaints[i]);
              }
          }
      },
      seePharmacyInfo : function(event, pharmacyComplaint) {
            this.choosenComplaintPharmacy = pharmacyComplaint;
            this.$refs['pharmacyInfo'].show();
      },
      seeDermatologistInfo : function(event, complaint) {
            this.choosenComplaintDermatologist = complaint;
            this.$refs['dermatologistInfo'].show();
      },
      seePharmacistInfo : function(event, complaint) {
            this.choosenComplaintPharmacist = complaint;
            this.$refs['pharmacistInfo'].show();
      },
      seeComplaint : function(event, complaint) {
            this.choosenComplaint = complaint;
            this.$refs['seeCompplaint'].show();
      },
      hideModalComplaint : function() {
            this.$refs['seeCompplaint'].hide();
      },
      sendAnswer : function(event,choosenComplaint) {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/complaint/answer',choosenComplaint,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                        console.log(response);
                        alert("Answer is sent!");
                }).catch(res => {
                       alert("Please try later.");
                        console.log(res);
                });
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


        this.axios.get('/complaint/all',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.allComplaints=response.data;
               this.divideIntoSeparateLists();
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });
    }
}
</script>

<style>

</style>


  