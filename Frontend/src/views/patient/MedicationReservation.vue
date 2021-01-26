ines (39 sloc)  1.61 KB
  
<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "previousPageReservations">Previous page</button>
                     <b class="tab2"></b>                

                       
              
            </span>
              <span  style="float:right;margin:10px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

                </span>

                 
        </div>
       
 
         <div style="background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
            <h3 style="color: #0D184F">Reserve new medicine</h3>
           
                
                    <div class="form-row">
                          <div class="form-group col-md-6">
                                    <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose medication"
                                        class = "btn btn-secondary dropdown-toggle" style=" width: 680px; float:left;margin-left:20px;">
                                            <b-dropdown-item v-for="medicine in this.medications"  v-on:click = "medicineIsSelected($event, medicine)" v-bind:key="medicine.id"> {{ medicine.name }}</b-dropdown-item>
                                    </b-dropdown> 
                          </div>
                      
                
                    </div>
                      <div class="form-row">
                       
                        <label>Choose pharmacy:</label>
                        <div class="form-row">
                          <div class="form-group col-md-6">
                                    <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose pharmacy"
                                        class = "btn btn-secondary dropdown-toggle" style="width: 680px; float:left;margin-left:20px;">
                                            <b-dropdown-item v-for="pharmacy in this.pharmacies"  v-on:click = "pharmacyIsSelected($event, pharmacy)" v-bind:key="pharmacy.id"> {{ pharmacy.pharmacyName }}</b-dropdown-item>
                                    </b-dropdown> 
                          </div>
                        </div>
                       
                        
                    </div>
                     <div class="form-row">
         
                        <label>Enter a pick-up day:</label>
                        <input type="text" name = "pickUpDay" class="form-control" v-model="pickUpDay" placeholder="Enter a pick-up date">
                 
                      
                    </div>
                    <b class="tab2"></b>     
                    <p><button class="btn btn-primary btn-lg" v-on:click = "reserve">Reserve a medication</button></p>
                    <div style="height:30px;"></div>
                


        </div>

        <div style="background-color:whitesmoke; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
        
                <label>Already reseved medications</label>
                    <b class="tab"></b> 

                <table class="table table-striped table-dark">
                  <thead>
                    <tr>
                      
                      <th scope="col">Name</th>
                      <th scope="col">Code</th>
                      <th scope="col">Type</th>
                      <th scope="col">Date of takeover</th>
                       <th scope="col">Pharmacy</th>
                       <th></th>
                    </tr>
                  </thead>
                  <tbody v-for="reservedMedication in this.reservedMedications"  v-bind:key="reservedMedication.id">
                    <tr> <td>   {{reservedMedication.name }}</td> 
                    
                  <td>    {{reservedMedication.code }}</td> 

                    <td> {{reservedMedication.form }}</td>

                     
                 <td>  {{reservedMedication.dateOfTakeOver }}</td>

                 <td>  {{reservedMedication.pharmacyName }}</td>
                 
                 <td><button class="btn btn-primary btn" v-on:click = "cancelReservation($event, reservedMedication)">Cancel reservation</button></td>
                 
                 </tr>
                  </tbody>
                </table>



        </div>



</div>
</template>
<script>
export default {

  data() {
    return {

      medications: [],
      reservedMedications: [],
      reservedMedication: null,
      medicationName: "",
      pharmacyName: "",
      medication : null,
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
      pickUpDay : null,
      patient : null,
      helpMedication: [],
      help: null
      
    }
  },
mounted() {

    this.axios.get('/medication')
          .then(response => {
                this.medications= response.data;
               console.log(this.medications);
              
          })

     
          

      this.axios.get('/pharmacy/all')
          .then(response => {
                this.pharmacies= response.data;
        
          })

            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/patient/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,

             }
         }).then(response => {
                this.patient = response.data;
         
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });


                  this.axios.get('/medicationReservation/' + this.patient.id)
          .then(response => {
                this.reservedMedications= response.data;
               console.log(this.reservedMedications);
              
          })
          
},
  methods:{
      medicineIsSelected : function(event, medicine) {
          
            this.medication = medicine;
            this.medicationName = medicine.name;
            console.log(this.medication)


      },

      pharmacyIsSelected: function(event, pharmacy) {
          
            this.pharmacy = pharmacy;
            this.pharmacyName = pharmacy.name;
            console.log(this.pharmacy)


      },
      appointmentsAndConsultings: function(){
      },
      eRecipes : function(){
      },
      logOut : function(){
          window.location.href = "/login";

      },
      medicationReservation : function(){
             window.location.href = "/medicationReservation";
      },
       changeMyProfile : function(){
             window.location.href = "/updateProfilePatient";

      },
      proba: function(){
        console.log(this.medications);
      },

      cancelReservation: function(event, reservedMedication){
        alert(reservedMedication.id)
          this.axios.get('/medicationReservation/cancel/'+ reservedMedication.id)
        
                   
      },
      reserve : function(){
    
              const med = {
                  patient: this.patient,
                  pharmacy: this.pharmacy,
                  medication: this.medication,
                  dateOfTakeOver: this.pickUpDay 
                };

               

     
     

         this.axios.post('/medicationReservation/add',med)
        
                    .then(res => {
                       
                        console.log(res);
                    })
                    .catch(res => {
                     
                        console.log(res);
                    })

                 
      },
      previousPageReservations : function() {
         window.location.href = "/myProfilePatient";
      },
      showReserved() {
        this.$refs['my-modal'].show()
      },
     
}
}
</script>

<style>

</style>


  