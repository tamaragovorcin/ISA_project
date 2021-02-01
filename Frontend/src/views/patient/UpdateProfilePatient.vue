<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                <button class = "btn btn-secondary btn-lg" style="float:left;margin-left:20px;" v-on:click = "previousUpdateProfile">Previous page</button>
                  
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
                        <input type="text" id = "name" name = "name" class="form-control" v-model = "patient.name"  placeholder="Enter name">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Surname:</label>
                        <input type="text" id = "surname" name = "surname" class="form-control" v-model = "patient.surname" placeholder="Enter surname">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Email:</label>
                        
                        <p><input v-model = "patient.email" readonly></p>
                        </div>
                        <div class="form-group col-md-6">
                        <label>Phone number:</label>
                        <input type="text" id = "phone"  name = "phone" class="form-control" v-model="patient.phoneNumber" placeholder="Enter phone number">
                        </div>
                    </div>
                      <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Country:</label>
                        <input type="text" id = "country" name = "country" class="form-control" v-model="patient.address.country" placeholder="Enter country">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Town:</label>
                        <input type="text" id = "town" name = "town" class="form-control" v-model="patient.address.town" placeholder="Enter town">
                        </div>
                    </div>
                     <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Street:</label>
                        <input type="text" id = "street" name = "street" class="form-control" v-model="patient.address.street" placeholder="Enter street">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Number:</label>
                        <input type="number" id = "number" name = "number" class="form-control" v-model="patient.address.number" placeholder="Enter number">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Postal code:</label>
                        <input type="text" id = "postalCode" name = "postalCode" class="form-control" v-model="patient.address.postalCode" placeholder="Enter postal code">
                        </div>

                        
                       
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Password:</label>
                        <input type="password" id = "password" name = "password" class="form-control" v-model="password" placeholder="Enter new password">
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
                                        class = "btn btn-secondary dropdown-toggle" style=" width: 680px; float:left;margin-left:20px;">
                                            <b-dropdown-item v-for="medicine in this.medications"  v-on:click = "addNewAlergie(medicine)" v-bind:key="medicine.id"> {{ medicine.name }}</b-dropdown-item>
                                    </b-dropdown> 

                            
                          </div>

                        
                    </div>
                         
                        </div>

                        
                       
                    </div>

                     <div>
                <table id="tenderMedicine" class="table table-striped" v-if="showTable">
                    <thead>
                    <th>Alergy list</th>
                  
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
        alergies: []
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
            alert(med)

          this.axios.get('/patient/deleteAlergies/'+med.id)
         

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
         
           const ad = {
                   
                    country: this.patient.country,
                    town: this.patient.town,
                    street: this.patient.street,
                    number:this.patient.number,
                    postalCode: this.patient.postalCode,
                };
            const p = {
                    id: this.patient.id,
                    email:this.patient.email,
                    name: this.patient.name,
                    surname : this.patient.surname,
                    phone: this.patient.phone,
                    address: ad,
                    password: this.patient.password
                };

        for(const a in this.medicationQuantityList){
            
            const alergies = {

                patient: this.patient,
                medication: this.medicationQuantityList[a]
            }


                this.axios.post('/patient/addAlergies',alergies)
                    .then(res => {
                       
                        console.log(res);
                    })
                    .catch(res => {
                     
                        console.log(res);
                    })




            }
              
                this.axios.post('/patient/update',p)
                    .then(res => {
                       
                        console.log(res);
                    })
                    .catch(res => {
                     
                        console.log(res);
                    })

            
      }
}
}
</script>

<style>
</style>
