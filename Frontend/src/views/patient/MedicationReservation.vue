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

                 <select v-model="medication" >
                              
                            <option disabled value="">Please select one</option>
                            <option>BB</option>
                            <option v-for="medication in this.medications" v-bind:key="medication.id">
                                   {{ medication.code }}
                            </option>


                            
                            </select>
          
        </div>
       
 
         <div style="background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
            <h3 style="color: #0D184F">Reserve new medicine</h3>
           
                
                    <div class="form-row">
                          <div class="form-group col-md-6">
                                    <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose medication"
                                        class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                                            <b-dropdown-item v-for="medicine in this.medications"  v-on:click = "medicineIsSelected($event, medicine)" v-bind:key="medicine.id"> {{ medicine.name }}</b-dropdown-item>
                                    </b-dropdown> 
                          </div>
                      
                
                    </div>
                      <div class="form-row">
                       
                        <label>Choose pharmacy:</label>
                        <input type="text" class="form-control" v-model="pharmacy" placeholder="Choose pharmacy">
                       
                        
                    </div>
                     <div class="form-row">
         
                        <label>Enter a pick-up day:</label>
                        <input type="text" class="form-control" v-model="pickUpDay" placeholder="Enter a pick-up date">
                 
                      
                    </div>
                  
                    <button class="btn btn-primary btn-lg" v-on:click = "reserve">Reserve a medication</button>
                    <div style="height:30px;"></div>
                


        </div>

        <div style="background-color:whitesmoke; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
            <h3 style="color: #0D184F">Reserved medication</h3>
               


        </div>


       
     
  

</div>
</template>
<script>
export default {

  data() {
    return {
      medications: [],
      medicationName: "",
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

    }
  },
mounted() {
      this.axios.get('/medication')
          .then(response => {
                this.medications= response.data;
       
          })
          
},
  methods:{
      medicineIsSelected : function(event, medicine) {
          
            this.medication = medicine;
            this.medicationName = medicine.name;
            console.log(this.medication)


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
      reserve : function(){
        console.log("Reserved!");

      },
      previousPageReservations : function() {
        console.log("Prvious!");
      }
     
}
}
</script>

<style>

</style>


  