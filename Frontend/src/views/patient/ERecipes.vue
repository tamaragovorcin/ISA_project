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

        <label>File
        <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
      </label>
      <button v-on:click="submitFile()">Submit</button>
       
     
  

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
       formData: null,
       urlImage: [],
       lista : [],
        file: ''

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
        
            let formData = new FormData();

            formData.append('file', this.file);

            this.axios.post( '/erecipes/file',
                formData,
                {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
              }
            ).then(function(){
          console.log('SUCCESS!!');
        })
        .catch(function(){
          console.log('FAILURE!!');
        });
      },

      
      handleFileUpload(){
        this.file = this.$refs.file.files[0];
      },
     
}
}
</script>

<style>

</style>


  