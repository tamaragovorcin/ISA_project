<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" href = "/isaHomePage">Home page</button>
            </span>
              <span  style="float:right;margin:15px">
                    <button class = "btn btn-warning btn-lg" href = "/login">&nbsp;&nbsp;Login&nbsp;&nbsp;</button>
                    <b class="tab"></b>    
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" href = "/registration">Register</button>
                </span>

        </div>
  <div class = "col md-8">
        <h1 style="color:#0D184F;font-suze:55px;font-weignt:bold;">{{pharmacy.pharmacyName}}</h1>
      
      
        <div v-if="dermatologistsShow" class="container" id ="table">

                          <h3 style="color:white;">Dermatologists of our pharmacy</h3>

                      <table class="table table-striped table-light">
                      <thead class="thead-light">
                        <tr>
                          <th scope="col"></th>
                          <th scope="col">Name</th>
                          <th scope="col">Surname</th>
                          <th scope="col">Phone number</th>
                          <th scope="col">Email</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="dermatologist in ourDermatologists" :key="dermatologist.id">
                                                                        <td></td>
                                                                        <td>{{dermatologist.name}}</td>
                                                                        <td>{{dermatologist.surname}}</td>
                                                                        <td>{{dermatologist.phoneNumber}}</td>
                                                                        <td>{{dermatologist.email}}</td>
                                                                    </tr>
                      
                      </tbody>
                    </table>

      </div>
       <div v-if="pharmacistsShow" class="container" id ="table">

                          <h3 style="color:white;">Pharmacists of our pharmacy</h3>

                      <table class="table table-striped table-light">
                      <thead class="thead-light">
                        <tr>
                          <th scope="col"></th>
                          <th scope="col">Name</th>
                          <th scope="col">Surname</th>
                          <th scope="col">Phone number</th>
                          <th scope="col">Email</th>
                          <th scope="col">Mark</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="pharmacist in ourPharmacists" :key="pharmacist.id">
                                                                        <td></td>
                                                                        <td>{{pharmacist.name}}</td>
                                                                        <td>{{pharmacist.surname}}</td>
                                                                        <td>{{pharmacist.phoneNumber}}</td>
                                                                        <td>{{pharmacist.email}}</td>
                                                                        <td>{{pharmacist.markPharmacist}}</td>
                                                                    </tr>
                      
                      </tbody>
                    </table>

      </div>


</div>
<div class="col md-2"></div>


    <div class = "col md-4">

                <div class="sidenav" style="margin-right:25px;">
                <hr/>
                <a  v-on:click = "showDermatologists"> Our dermatologists</a>
                <hr/>
                <a @click = "showPharmacists">Our pharmacists</a>
                <hr/>
                <a href = "/pharmacyMedications">Medications</a>
                <hr/>
                <a href = "/pharmacyMedications">Appointments with a dermatologists </a>
                <hr/>
                <a href = "/actionsAndBenefits">Pricelist</a>
                <hr/>
                <a href="/order"></a>
                </div>
                </div>
    </div>
</template>

<script>
export default {
  data() {
    return {
       id : this.$route.params.id,
       pharmacy : {},
       dermatologistsShow : false,
       pharmacistsShow : false,
       ourDermatologists : [],

    }
  },
  mounted() {
       let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/pharmacy/'+3,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
                this.pharmacy = response.data;
                 this.axios.get('pharmacy/dermatologists/'+3,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.ourDermatologists=response.data;
         }).catch(res => {
                       alert("Please try again later.");
                        console.log(res);
                 });
                  this.axios.get('pharmacy/pharmacists/'+3,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.ourPharmacists=response.data;
         }).catch(res => {
                       alert("Please try again later.");
                        console.log(res);
                 });
        
               
         }).catch(res => {
                alert("NOT OK");
                console.log(res);
        });
       

        
    }
     ,
  methods:{
      showModal() {
        this.$refs['my-modal'].show()
      },
      hideModal() {
        this.$refs['my-modal'].hide()
      },
      showDermatologists : function(){
            this.dermatologistsShow = true;
            this.pharmacistsShow = false;
      },
      showPharmacists : function(){
          this.dermatologistsShow = false;
          this.pharmacistsShow = true;
      }
   
}
}
</script>

<style>
@media (min-width: 992px){}
 .modal-lg {
    max-width: auto !important;
   }
@media (min-width: 992px){}
 .modal-dialog {
    margin: 100px !important;
   }
@media (min-width: 800px){}
  .modal-dialog {
    max-width: auto !important;
  }
#table {
    margin: 50px;
    float : right;
    background : #0D184F;
}
</style>
