<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                    <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Home</button>
                    <strong class="tab"></strong>  
                    <strong class="tab"></strong>

                    <button class = "btn btn-link btn-lg" v-on:click = "showMyProfile">My profile</button>

                    <strong class="tab"></strong>  
                    <strong class="tab"></strong>            
                   

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "showClients">My clients</button>
                    <strong class="tab"></strong>  
                    <strong class="tab"></strong>    

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "workCalendar">Work calendar</button>
                    <strong class="tab"></strong>  
                    <strong class="tab"></strong>

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "vacation">Create a vacation</button>
                    <strong class="tab"></strong>  
                    <strong class="tab"></strong>
                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "showMedications">Medications</button>
                    <strong class="tab"></strong>  
                    <strong class="tab"></strong>

                
            </span>
              <span  style="float:right;margin:15px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

             </span>

        </div>
            <div class="d-flex justify-content-center md-2 p-2">
                <h2 class="text-dark" style="font-weight: bold">Your profile</h2>
                <b class="tab"></b>
                <b class="tab"></b>
                <b class="tab"></b>
                <b class="tab"></b>
                <button class="btn btn-success btn-lg" style="margin-right:20px;" v-on:click = "changePersonal">Change your personal data</button>
            </div>

            <div  class="blog-box d-flex justify-content-start md-4 p-5">
                <div class="blog-desc">

                    <b class="tab"></b>

                    <h4>Name: {{pharmacist.name}}  </h4>
                    <b class="tab"></b>

                    <h4>Surname: {{pharmacist.surname}} </h4>
                    <b class="tab"></b>

                    <h4>Email: {{pharmacist.email}} </h4>
                    <b class="tab"></b>

                    <h4>Phone number: {{pharmacist.phoneNumber}} </h4>   
                    <b class="tab"></b>

                    <h4>Country: {{pharmacist.address.country}} </h4>
                    <b class="tab"></b>

                    <h4>Town: {{pharmacist.address.town}} </h4>
                    <b class="tab"></b>

                    <h4>Street: {{pharmacist.address.street}} </h4>
                    <b class="tab"></b>

                    <h4>Number: {{pharmacist.address.number}} </h4>
                    <b class="tab"></b>

                    <h4>Postal code: {{pharmacist.address.postalCode}} </h4>
                    <b class="tab"></b>

                    <h4>Password: {{pharmacist.password}}</h4>
                    <b class="tab"></b>





                </div>
            </div>
        
</div>
</template>

<script>
export default {

  data() {
    return {
      
       pharmacies : [],
       pharmacist : "",
       name: "",
       surname: "",
       email: "",
       phoneNumber: "",
       address: {
          country: "",
           town: "", 
            street: "",
            postalCode: "",
           number: ""
           }
     
       

      

    }
  },
   mounted(){
     let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/pharmacist/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.pharmacist=response.data;
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });
    
   },


  methods:{
      showClients : function(){
        

      },
      showMyProfile: function(){
         
      },
      changePersonal: function(){
          window.location.href = "/pharmacistUpdateProfile";
      },
      showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      logOut : function(){
        localStorage.removeItem('token');
          window.location.href = "/login";
      },
      vacation : function(){

      },
      workCalendar : function(){

      },
     
      sendComplaint : function(){

      },
      showMedications : function() {
          window.location.href = "/pharmacistMedicationSearch";
      },
  }
}

</script>

<style>

</style>