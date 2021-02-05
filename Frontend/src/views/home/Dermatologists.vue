<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Pharmacies</button>
                   <strong class="tab"></strong>                
                   
                <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showMedications">Medications</button>
                   <strong class="tab"></strong>                
                <a class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" href="/pharmacists">Pharmacists</a>
                   <strong class="tab"></strong>                
                <a class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" href="/pharmacists">Dermatologists</a>
       

            </span>
              <span  style="float:right;margin:15px">
                    <button class = "btn btn-warning btn-lg" v-on:click = "showLoginForm">&nbsp;&nbsp;Login&nbsp;&nbsp;</button>

                   <strong class="tab"></strong>                
                   

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "showRegistrationForm">Register</button>
                </span>
        </div>
        <div class="container-fluid">

       
    <div style="height:25px"></div>

    
        <div style="background: white; height: 60px; margin-top: 20px">
          <span  style="float:right;margin:15px">
            <div class="input-group mb-3">
              <input type="text" v-model="dermatologistName" class="form-control" placeholder="Dermatologist name..." aria-label="Enter name..." aria-describedby="basic-addon2">
              <input type="text" v-model="dermatologistSurname" class="form-control" placeholder="Dermatologist surname..." aria-label="Enter surname..." aria-describedby="basic-addon2">

                 <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button"  v-on:click = "showByDermatologisttName" >Search</button>
                  </div>
            </div>
          </span>
           <span  style="float:right;margin:15px">
              <div class="input-group mb-3">
                  <label>Pharmacy:</label>
                   <div class="input-group-append  align-self-center">
                       <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose pharmacy" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                              <b-dropdown-item v-for="item in this.pharmacies"  v-on:click ="pharmacyIsSelected($event, item.pharmacyName)" v-bind:key="item.id"> {{item.pharmacyName}}</b-dropdown-item>
                        </b-dropdown> 
                  </div>
              </div>
          </span>  

        
         
           <span  style="float:right;margin:15px">
              <div class="input-group mb-3">
                  <label>Average mark around:</label>
                   <div class="input-group-append  align-self-center">
                       <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose pharmacists mark" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                              <b-dropdown-item v-for="item in this.marks"  v-on:click ="markIsSelected($event, item.mark)" v-bind:key="item.mark"> {{item.mark }}</b-dropdown-item>
                        </b-dropdown> 
                  </div>
              </div>
          </span>  
        </div>

      <h3>Pharmacists</h3>

  <table class="table table-striped table-dark">
  <thead class="thead-dark">
    <tr>
      <th scope="col"></th>
      <th scope="col">Name</th>
      <th scope="col">Surname</th>
      <th scope="col">Mark</th>
      <th scope="col">Pharmacies</th>

    </tr>
  </thead>
  <tbody>
    <tr v-for="derm in dermatologists" :key="derm.id">
      <td></td>
      <td>{{derm.firstname}}</td>
      <td>{{derm.surname}}</td>
      <td>{{derm.markDermatologist}}</td>
      <td>
      <tr v-for="pharmacy  in derm.pharmacies" :key="pharmacy.id">
      <td>{{pharmacy}}</td>
      </tr>
      </td>
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
        firstname : "",
        surname : "",
        email : "",
        phoneNumber : "",
        town : "",
        street : "",
        number : "",
        postalCode : "",
        country : "",
        pharmacy : {},
        pharmacists : [],
        dermatologists : [],
        user : {},
        pharmacyName : "",
        marks: [
          { mark: "0-1" },
          { mark: "1-2" },
          { mark: "2-3" },
          { mark: "3-5" },
          { mark: "4-5" },
      ],
      choosenMark :0,
      choosenPharmacy : {},
      dermatologistName : "",
      dermatologistSurname : "",
      pharmacies : [],
    



        
    }
  },

  methods:{     
       logOut : function(){
            
      },
      showByPharmacyName : function(){

      },
      markIsSelected : function(event, mark) { 
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            var marks = mark.split('-')
            var MarkMin = parseInt(marks[0])
            var MarkMax = parseInt(marks[1])
            this.axios.get('/dermatologist/searchMark/'+MarkMin+"/"+MarkMax,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                     this.dermatologists= response.data;
                }).catch(res => {
                     alert("NOT OK");
                    console.log(res);
                });     
     },
      pharmacyIsSelected : function(event, item) { 
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.get('/dermatologist/searchPharmacy/'+item,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                     this.dermatologists= response.data;
                }).catch(res => {
                     alert("Try again later.");
                    console.log(res);
                });     
     },
      showByDermatologisttName : function() {
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
          const data = {
            firstName : this.dermatologistName,
            surName : this.dermatologistSurname
          }
            this.axios.post('/dermatologist/searchName',data,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
          .then(response => {
                    this.dermatologists= response.data;
            }).catch(res => {
                        alert("There is no phatmacists with entered name.");
                        console.log(res);
                    });
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
       let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/dermatologist/front',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
                this.dermatologists = response.data;

         }).catch(res => {
                alert("Please log in.");
                console.log(res);
        });
        this.axios.get('/pharmacy/all',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
                this.pharmacies = response.data;

         }).catch(res => {
                alert("Please log in.");
                console.log(res);
        });
        
    }
}
</script>

<style>

</style>


