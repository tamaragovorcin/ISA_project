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
                        <p>patient@gmail.com</p>
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
                        <p>Choose medication:</p>
                        <div class="col">
                                                                              
                        </div>
                        </div>
                       
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
        country : ""
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
      previousUpdateProfile : function(){
      },
    logOut : function(){
          window.location.href = "/login";
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
              
                this.axios.post('/update',p)
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
