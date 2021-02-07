<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
           <span style="float: left; margin: 15px;">
                               
                    <a  class = "btn btn-link btn-lg" href= "/patientProfile">Home page</a>
                    <a  class = "btn btn-link btn-lg" href= "/showPharmaciesPatient">Pharmacies</a>
                    <a  class = "btn btn-link btn-lg" href= "/eRecipes">ERecipes</a>
                    <a  class = "btn btn-link btn-lg" href= "/subscriptionsToPharmacies">My subscriptions</a>
                    <a  class = "btn btn-link btn-lg" href= "/patientComplaint">Write complaint</a>
               
                     <a  class = "btn btn-link btn-lg" href= "/updateProfilePatient">Change my profile</a>
                     <a  class = "btn btn-link btn-lg" href= "/logOut">Collect a medication</a>
            
                   

             
                  

            </span>
              <span  style="float:right;margin:10px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

                </span>
        </div>

      

        <div style="background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
            <h3 style="color: #0D184F">Change My Profile</h3>
                <form>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Name:</label>
                        <input type="text" class="form-control" v-model = "patient.name"  placeholder="Enter name">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Surname:</label>
                        <input type="text"  class="form-control" v-model = "patient.surname" placeholder="Enter surname">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Email:</label>
                        
                        <p><input v-model = "patient.email" readonly></p>
                        </div>
                        <div class="form-group col-md-6">
                        <label>Phone number:</label>
                        <input type="text"  class="form-control" v-model="patient.phoneNumber" placeholder="Enter phone number">
                        </div>
                    </div>
                      <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Country:</label>
                        <input type="text"  class="form-control" v-model="patient.address.country" placeholder="Enter country">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Town:</label>
                        <input type="text" class="form-control" v-model="patient.address.town" placeholder="Enter town">
                        </div>
                    </div>
                     <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Street:</label>
                        <input type="text"  class="form-control" v-model="patient.address.street" placeholder="Enter street">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Number:</label>
                        <input type="number" class="form-control" v-model="patient.address.number" placeholder="Enter number">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Postal code:</label>
                        <input type="text"  class="form-control" v-model="patient.address.postalCode" placeholder="Enter postal code">
                        </div>

                        
                       
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Password:</label>
                        <input type="password"  class="form-control" v-model="password" placeholder="Enter new password">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Repeat password:</label>
                        <input type="password" class="form-control" v-model="repeatPassword" placeholder="Repeat new password">
                        </div>
                    </div>
                     <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Alergies:</label>
                          
                    <div class="form-row">
                          <div class="form-group col-md-6">
                                    <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose medication"
                                        class = "btn btn-secondary dropdown-toggle" style=" width: 750px; float:left;margin-left:20px;">
                                            <b-dropdown-item v-for="medicine in this.medications"  v-on:click = "addNewAlergie(medicine)" v-bind:key="medicine.id"> {{ medicine.name }}</b-dropdown-item>
                                    </b-dropdown> 

                            
                          </div>

                       
                    </div>
                         
                        </div>

                        
                       
                    </div>

                     <div>
                <table style="" id="tenderMedicine" class="table table-striped" v-if="showTable">
                    <thead>
                    <th id="alergyList">Alergy list</th>
                  
                    </thead>
                    <tr v-for="med in medicationQuantityList" :key="med.id">
                        <td>{{med.name}}</td><td><button class="btn btn-outline-secondary" v-on:click = "remove(med)">Remove</button></td>
                     
                    </tr>
                </table>
            </div>

              <div>
                <table style="" id="tenderMedicine" class="table table-striped" v-if="showSecondTable">
                    <thead>
                    <th id="alergyList">Alergy list</th>
                  
                    </thead>
                    <tr v-for="med in medicationQuantityList" :key="med.id">
                        <td>{{med.name}}</td><td><button class="btn btn-outline-secondary" v-on:click = "remove(med)">Remove</button></td>
                     
                    </tr>
                </table>
            </div>
             
                    <button class="btn btn-primary btn-lg" v-on:click = "update">Update</button>
                    <div style="height:30px;"></div>
                </form>



     


        </div>


    </div>
</template>

<script>
export default {
  data() {
    return {
        id : this.$route.params.id,
        patient: null,
        name : "",
        surname : "",
        email : "",
        password : "",
        repeatPassword : "",
        phoneNumber : "",
        town : "",
        street : "",
        number : "",
        postalCode : "",
        country : "",
        medications: [],
        medication: null,
        alergy: null,
        medicationQuantityList: [],
        showTable: true,
        medicine: null,
        a: null,
        alergies: [],
        helparray : [],
        showSecondTable: false,
        arrayy: []
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
                  this.axios.get('/patient/getAlergies/' + this.patient.id)
                        .then(response => {
                           
                                this.medicationQuantityList= response.data;
                            console.log(this.medicationQuantityList);
                            
              
          })

            
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });


  this.axios.get('/medication')
          .then(response => {
                this.medications= response.data;
               console.log(this.medications);
              
          })


   
  
     
},
  methods:{
    
      previousUpdateProfile : function(){
      },
    logOut : function(){
          window.location.href = "/login";
      },
        remove : function(med){
             
                this.medicine = med;
                this.helparray = []
                
                for(const a in this.medicationQuantityList){
                  
                     if(this.medicationQuantityList[a] == (this.medicine)){
                         
                         console.log(this.medicationQuantityList[a].name)
                           

                     }
                     else{
            
                       this.helparray.push(this.medicationQuantityList[a])
                     }
                 }
                    
                    this.medicationQuantityList = this.helparray
                    this.showTable = false;
             this.showSecondTable = true;
           
            
         

      },

      medicineIsSelected : function(){
          window.location.href = "/login";

      },

       addNewAlergie: function (medicine) {
                this.showTable = true;
                this.medicine = medicine;
            
                 for(const a in this.medicationQuantityList){
                    
                     if(this.medicationQuantityList[a] == (this.medicine)){
                            return;
                           

                     }
                 }

                   this.medicationQuantityList.push(this.medicine)

              
            },

       update : function(){
         let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
           const ad = {
                   
                    country: this.patient.address.country,
                    town: this.patient.address.town,
                    street: this.patient.address.street,
                    number:this.patient.address.number,
                    postalCode: this.patient.address.postalCode,
                };
            const p = {
                    id: this.patient.id,
                    email:this.patient.email,
                    name: this.patient.name,
                    surname : this.patient.surname,
                    phoneNumber: this.patient.phoneNumber,
                    address: ad,
                    password: this.patient.password
                };

        for(const a in this.medicationQuantityList){
            
            const alergies = {
                id: this.patient.id,
                medication: this.medicationQuantityList[a]
            }
            alert(this.patient.id)
             this.arrayy.push(alergies)

        }
         if(this.medicationQuantityList.length == 0){
                const alergies = {

                id: this.patient.id,
                medication: null
            }
             this.arrayy.push(alergies)
         }
           alert("sjergfsh")
                this.axios.post('/patient/addAlergies',this.arrayy, { 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                    .then(res => {
                       
                        console.log(res);
                    })
                    .catch(res => {
                     
                        console.log(res);
                    })




        
                
            
                this.axios.post('/patient/update',p, { 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                    .then(res => {
                       
                        console.log(res);
                    })
                    .catch(res => {
                     
                        console.log(res);
                    })

       
      
}}
}
</script>

<style>
</style>
