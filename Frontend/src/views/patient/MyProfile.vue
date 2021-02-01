  
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


                

                        <button class = "btn btn-link btn-lg" style="margin-right:20px;" v-on:click = "changeMyProfile">Change my profile</button>
                    <b class="tab2"></b>   
            </span>
              <span  style="float:right;margin:10px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

                </span>

        </div>

        <div style="background-color:lightgray;  width: 100%; margin-top:30px;">
        <div style="background-color:white;  margin: auto; width: 80%;padding: 30px;">
            <h4 style="color: #0D184F"><b>POINTS</b></h4>
             <h5 style="color: #0D184F">{{this.patient.points}}</h5>
        </div>
        <div style="background-color:white; margin: auto; width: 80%;padding: 30px;margin-top:30px;">
            <b><h4 style="color: #0D184F"><b> CATEGORY</b></h4></b>
             <h5 style="color: #0D184F">{{this.patient.loyaltyCategory}}</h5>
        </div>
        <div style="background-color:white; margin: auto; width: 80%; padding:30px;margin-top:30px;">
            <b><h4 style="color: #0D184F"><b>BENEFITS</b></h4></b>
             <h5 style="color: #0D184F">{{this.patient.discount}}</h5>
        </div>
         <div style="background-color:white; margin: auto; width: 80%; padding:30px;margin-top:30px;">
            <b><h4 style="color: #0D184F"><b>PENALTIES</b></h4></b>
             <h5 style="color: #0D184F">{{this.patient.penalties}}</h5>
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
       patient: null,

    }
  },
mounted() {

 let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/patient/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,

             }})
             .then(response => {
                this.patient = response.data;
                console.log(response.data);
                

            
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });
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
     
}
}
</script>

<style>
</style>

