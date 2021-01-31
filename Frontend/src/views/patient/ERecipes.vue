ines (39 sloc)  1.61 KB
  
<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "appointmentsAndConsultings">My appointments and consultings</button>
                     <b class="tab2"></b>                

                       
                   <button class = "btn btn-link btn-lg" v-on:click = "eRecipes">eRecipes</button>

                    <b class="tab2"></b>                
                   

                    <button class = "btn btn-link btn-lg" style="margin-right:20px;" v-on:click = "medicationReservation">Medication reservation</button>
                    <b class="tab2"></b>    


                     <button class = "btn btn-link btn-lg" style="margin-right:20px;" v-on:click = "penals">Penals</button>
                    <b class="tab2"></b>   

                        <button class = "btn btn-link btn-lg" style="margin-right:20px;" v-on:click = "changeMyProfile">Change my profile</button>
                    <b class="tab2"></b>   
            </span>
              <span  style="float:right;margin:10px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

                </span>

        </div>
        <div style = "background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
                       <h3 style="color: #0D184F;margin-bottom:20px">Upload QR code</h3>
                <div class="form-group">
                   <div class="row">
                       
                        <div class="col">
                             <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
                        </div> 
                          <div class="col">
                              <button  class="btn btn-primary" v-on:click="submitFile()">Check availability in pharmacies</button>
                        </div>
                   </div>
                </div>
        </div>

        <div v-for="pharmacy in pharmacyList"   v-bind:key="pharmacy.pharmacyId">

            <div style = "background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
                        
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
                      <div class="row d-flex align-items-end">
                          <div class="col">
                              <button  class="btn btn-primary" v-on:click="choosePharmacy($event,pharmacy.pharmacyId)">Choose this pharmacy</button>
                          </div>
                      </div>
                  </div>
            </div> 
        </div>



     
  

</div>
</template>
<script>
export default {

  data() {
    return {
       pharmacyList : [],
       formData: null,
       urlImage: [],
       lista : [],
       file: '',
       medications : []

    }
  },

  methods:{
     
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
      penals : function(){},
      submitFile(){
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

            let formData = new FormData();

            formData.append('file', this.file);

            this.axios.post( '/erecipes/file', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                     'Authorization': 'Bearer ' + token
                }
              }). then(response => {
                    this.medications = response.data;
                    this.axios.post( '/erecipes/availability', this.medications,{
                          headers: {
                              'Authorization': 'Bearer ' + token
                          }
                        }). then(response => {
                            this.pharmacyList = response.data;
                        }).catch(res => {
                            alert("Please try again later!");
                            console.log(res);
                        });     

                }).catch(res => {
                     alert("Please upload correct QR code!");
                    console.log(res);
                });     
      },

      
      handleFileUpload(){
        this.file = this.$refs.file.files[0];
      },
      choosePharmacy($event,pharmacyid) {
         const pharmacyMedications = {
                      medications : this.medications,
                      pharmacyId = pharmacyid
                    }

          console.log(pharmacyid);
      }
     
}
}
</script>

<style>

</style>


  