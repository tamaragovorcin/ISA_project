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
          <div  style = "background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
                       <h3 style="color: #0D184F;margin-bottom:20px">Define loyalty program</h3>
                <div class="form-group">
                    <label>Points for consulting</label>
                    <input type="number" class="form-control" v-model="loyaltyProgram.consultingPoints"  placeholder="Enter consulting points">
                </div>
                <div class="form-group">
                    <label>Points for examination:</label>
                    <input type="number" class="form-control" v-model="loyaltyProgram.examinationPoints" placeholder="Enter examination points">
                </div>
                <div class="form-group">
                    <label>Discount for regular users:</label>      
                    <input type="number" class="form-control" v-model="loyaltyProgram.regularDiscount" placeholder="Enter discount">
                </div>
               
                 <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Discount for silver users:</label>
                        <input type="number" class="form-control" v-model="loyaltyProgram.silverDiscount" placeholder="Enter discount">
                        </div>
                        <div class="form-group col-md-6">
                        <label>User is silver with minimum points:</label>
                        <input type="number" class="form-control" v-model="loyaltyProgram.silverLimit" placeholder="Enter limit">

                        </div>
                </div>
                 <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Discount for gold users:</label>
                        <input type="number" class="form-control" v-model="loyaltyProgram.goldenDiscount" placeholder="Enter discount">
                        </div>
                        <div class="form-group col-md-6">
                        <label>User is gold with minimum points:</label>
                        <input type="number" class="form-control" v-model="loyaltyProgram.goldLimit" placeholder="Enter limit">

                        </div>
                </div>
                <button v-on:click = "confirmUpdate" class="btn btn-primary">Confirm</button>
           
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
      loyaltyProgram : {
            consultingPoints: 0,
            examinationPoints: 0,
            regularLimit: 0,
            silverLimit: 0,
            goldLimit: 0,
            regularDiscount: 0,
            silverDiscount: 0,
            goldenDiscount: 0
      },
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
      confirmUpdate : function() {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
           this.axios.post('/loyaltyProgram/define',this.loyaltyProgram,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Loyalty program is successfully defined!");
                        console.log(response.data);
                })
                .catch(res => {
                    alert(res.response.data.message);
                 });    
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

        this.axios.get('/loyaltyProgram',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
            }).then(response => {
                this.loyaltyProgram = response.data;
            }).catch(res => {
                       alert("Please log in again or try later.");
                        console.log(res);
                    });

    }
}
</script>