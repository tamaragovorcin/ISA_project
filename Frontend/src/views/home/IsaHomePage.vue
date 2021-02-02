<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Pharmacies</button>
                   <b class="tab"></b>                
                   
                <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showMedications">Medications</button>
                  
            </span>
              <span  style="float:right;margin:15px">
                    <button class = "btn btn-warning btn-lg" v-on:click = "showLoginForm">&nbsp;&nbsp;Login&nbsp;&nbsp;</button>

                    <b class="tab"></b>                
                   

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "showRegistrationForm">Register</button>
                </span>

        </div>

            <div style="background: white; height: 60px; margin-top: 20px">
            
           <span  style="float:right;margin:15px">
                    
                   <div class="input-group mb-3">
  <input type="text" v-model="pharmacyName" class="form-control" placeholder="Search by pharmacy name" aria-label="Search by pharmacy name" aria-describedby="basic-addon2">
  <div class="input-group-append">
    <button class="btn btn-outline-secondary" type="button"  v-on:click = "searchName(pharmacyName)" >Search</button>
  </div>
</div>

            
                </span>

                  <span  style="float:right;margin:15px">
                    
                   <div class="input-group mb-3">
  <input type="text" v-model="pharmacyName" class="form-control" placeholder="Search pharmacy by city" aria-label="Search pharmacy by city" aria-describedby="basic-addon2">
  <div class="input-group-append">
    <button class="btn btn-outline-secondary" type="button"  v-on:click = "searchCity(pharmacyName)" >Search</button>
  </div>
</div>

            
                </span>
             
            
        </div>


               <h4 style="color: #0D184F;margin:20px">Pharmacies:</h4>

            <div class="row" style = "background-color:whitesmoke; margin: auto; width: 100%;height:5%;border: 3px solid #0D184F;padding: 10px;margin-top:15px;">
         <div class=" form-group col"  v-for="pharmacy in pharmacies" :key="pharmacy.id">

                      <ul class="nav navbar-nav">
                        <li>  <router-link :to="{ path: '/pharmacyProfile/'+pharmacy.id}" v-slot="{href, navigate}">
                                    <button class = "btn btn-primary" :href="href" @click="navigate"  elevation="1">{{pharmacy.pharmacyName}}</button>
                              </router-link>
                        </li>
                        <li class="divider-vertical"></li>



                      </ul>
                                          </div>
                        </div>
    </div>
</template>

<script>
export default {

  data() {
    return {
       pharmacy : {},
       pharmacies : [],
       showComplaintForm : false,
       pharmacists : [],
       pharmacist : null,
       dermatologists : [],
       dermatologist : null,
       showPharmacyComplaint : false,
       showPharmacistComplaint : false,
       showDermatologistComplaint : false,
       dermatologistAppointments: [],
       dermatologistAppointment : null,
       pharmacyName: null,
       dermatologistAppointmentsSearch: [],
       showTable: true,
       showSecondTable : false,
       filter: null
       
    }
  },

  methods:{
     searchName: function(pharmacyName){
           
             this.pharmacyName = pharmacyName
               alert(this.pharmacyName)
      this.axios.get('/pharmacy/searchName/'+ this.pharmacyName)
          .then(response => {
              this.showTable = false;
              this.showSecondTable = true;
                this.dermatologistAppointmentsSearch= response.data;
               console.log(this.dermatologistAppointmentsSearch);

                if(this.dermatologistAppointmentsSearch.length == null){
                     this.showSecondTable = false;
                }
              
          })
      },

      searchCity: function(pharmacyName){
           
             this.pharmacyName = pharmacyName
               alert(this.pharmacyName)
      this.axios.get('/pharmacy/searchCity/'+ this.pharmacyName)
          .then(response => {
              this.showTable = false;
              this.showSecondTable = true;
                this.dermatologistAppointmentsSearch= response.data;
               console.log(this.dermatologistAppointmentsSearch);

                if(this.dermatologistAppointmentsSearch.length == null){
                     this.showSecondTable = false;
                }
              
          })
      },
    showMedications: function(){
        window.location.href = "/showMedicationsHome";

      },
      showRegistrationForm : function(){
        window.location.href = "/registration";

      },
      showLoginForm: function(){
        window.location.href = "/login";

      },
      showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
        showPharmacy :function (event, pharmacy) {
            this.choosenPharmacy = pharmacy;
           
      },
},
mounted() {
        this.axios.get('/pharmacy/all')
        .then(response => {
                this.pharmacies = response.data;
                console.log(this.admin);
         }).catch(res => {
                alert("NOT OK");
                console.log(res);
        });
        
    }
     
}
</script>

<style>

</style>


  