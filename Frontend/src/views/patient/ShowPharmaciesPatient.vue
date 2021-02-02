<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Home</button>
                     <b class="tab"></b>                

                    <button class = "btn btn-link btn-lg" v-on:click = "showPharmacies">Pharmacies</button>

                    <b class="tab"></b>     
                   <button class = "btn btn-link btn-lg" v-on:click = "showMyProfile">My profile</button>

                    <b class="tab"></b>                
                   

   

                    <button class = "btn btn-link btn-lg" style="margin-right:20px;" v-on:click = "writeComplaint">Write complaint</button>
                    <b class="tab"></b>   
            </span>
              <span  style="float:right;margin:15px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

                </span>

        </div>

        
        <div style="background: lightgray; height: 60px;">
            
            <span style="float: left; margin: 15px;">
                <b><button class = "btn btn-link btn-lg" style=" color:black; float:left; margin-left:20px; margin-top:0px;" v-on:click = "allPharmacies">Show all pharmacies</button></b>
                     <b class="tab"></b>                

                    <button class = "btn btn-link btn-lg" style="color:black; " v-on:click = "allDermatologists">Dermatologist appointments</button>

                    <b class="tab"></b>     
                   <button class = "btn btn-link btn-lg" style="color:black; " v-on:click = "allPharmacists">Pharmacist consultation</button>

                    <b class="tab"></b>                
                   
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



<template>
              <div class="custom-control custom-radio form-group col ">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample1" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'1')">
                   <label class="custom-control-label" for="defaultGroupExample1">0-1</label>
                   <p class="tab"></p>     
        

                  <input type="radio" class="custom-control-input" id="defaultGroupExample2" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'2')">
                   <label class="custom-control-label" for="defaultGroupExample2">1-2</label>
                
                 <p class="tab"></p>     
                  <input type="radio" class="custom-control-input" id="defaultGroupExample3" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'3')">
                 <label class="custom-control-label" for="defaultGroupExample3">2-3</label>
                
        
             <p class="tab"></p>     
                  <input type="radio" class="custom-control-input" id="defaultGroupExample4" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'4')" >
                 <label class="custom-control-label" for="defaultGroupExample4" >3-4</label>
                
                  <p class="tab"></p>     
                  <input type="radio" class="custom-control-input" id="defaultGroupExample5" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'5')" >
                 <label class="custom-control-label" for="defaultGroupExample4" >4-5</label>
              </div> 
             </template>



 <div  v-for="dermatologistAppointment in this.dermatologistAppointments"  v-bind:key="dermatologistAppointment.id">
       <div id="customers" v-if="showTable"  style="background: whitesmoke; border: 3px solid #0D184F; height: 200px; width:1000px; margin-left:300px; margin-top: 20px">
          

<table id="table1" class="table">
 
 
    <tbody>
    <tr>
      <th scope="row"></th>
      <td>Pharmacy name</td>
      <td>{{dermatologistAppointment.pharmacyName}} </td>
    
    </tr>
    <tr>
      <th scope="row"></th>
      <td>Address</td>
      <td>{{dermatologistAppointment.postalCode}} {{dermatologistAppointment.country}}, {{dermatologistAppointment.street}} {{dermatologistAppointment.number}}</td>

    </tr>
    <tr>
      <th scope="row"></th>
      <td>Mark</td>
      <td>{{dermatologistAppointment.mark}} </td>
     
    </tr>
     
    
  </tbody>
</table>
       
     


           </div>

      </div>     

 <div  v-for="dermatologistAppointment in this.dermatologistAppointmentsSearch"  v-bind:key="dermatologistAppointment.id">
           <div id="customers" v-if="showSecondTable"  style="background: whitesmoke; border: 3px solid #0D184F; height: 200px; width:1000px; margin-left:300px; margin-top: 20px">
          

<table id="table2" class="table">
 
 
    <tbody>
    <tr>
      <th scope="row"></th>
      <td>Pharmacy name</td>
      <td>{{dermatologistAppointment.pharmacyName}} </td>
    
    </tr>
    <tr>
      <th scope="row"></th>
      <td>Address</td>
      <td>{{dermatologistAppointment.postalCode}} {{dermatologistAppointment.country}}, {{dermatologistAppointment.street}} {{dermatologistAppointment.number}}</td>

    </tr>
    <tr>
      <th scope="row"></th>
      <td>Mark</td>
      <td>{{dermatologistAppointment.mark}} </td>
     
    </tr>
     
    
  </tbody>
</table>
       
     


           </div>

      </div>           

</div>
</template>

<script>
export default {
  data() {
    return {
       showComplaintForm : false,
       pharmacies : [],
       pharmacy : null,
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
mounted() {

    this.axios.get('/pharmacy/allPharmacistPharmacies')
          .then(response => {
                this.dermatologistAppointments= response.data;
               console.log(this.dermatologistAppointments);
              
          })
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
       allPharmacies: function(){
        window.location.href = "/myProfilePatient";
      },
      allDermatologists : function(){
          window.location.href = "/dermatologistAppointments";
      },
      allPharmacists : function(){
          window.location.href = "/login";
      },
      showMyProfile: function(){
        window.location.href = "/myProfilePatient";
      },
      showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      logOut : function(){
          window.location.href = "/login";
      },
      writeComplaint() {
        this.$refs['my-modal'].show()
      },
      
       hideModal() {
        this.$refs['my-modal'].hide()
      },
      complainAboutPharmacy : function(){
          this.showPharmacyComplaint = true;
          this.showPharmacistComplaint = false;
          this.showDermatologistComplaint = false;
      },
      complainAboutPharmacist : function(){
          this.showPharmacyComplaint = false;
          this.showPharmacistComplaint = true;
          this.showDermatologistComplaint = false;
      },
       complainAboutDermatologist : function(){
          this.showPharmacyComplaint = false;
          this.showPharmacistComplaint = false;
          this.showDermatologistComplaint = true;
      },
      sendComplaint : function(){
 
        },
       showPharmacies : function(){
            window.location.href = "/showPharmaciesPatient";

      },
      

        updateFiler : function(event, filter) {
               this.filter = filter;
            },
}
}
</script>

<style>
</style>