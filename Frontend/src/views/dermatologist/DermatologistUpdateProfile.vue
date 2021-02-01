<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            
             
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
                        <input type="text" id = "name" name = "name" class="form-control" v-model="dermatologist.name">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Surname:</label>
                        <input type="text" id = "surname" name = "surname" class="form-control" v-model = "dermatologist.surname" placeholder="Enter surname">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Email:</label>
                        <p>{{dermatologist.email}}</p>
                        </div>
                        <div class="form-group col-md-6">
                        <label>Phone number:</label>
                        <input type="text" id = "phone"  name = "phone" class="form-control" v-model="dermatologist.phoneNumber" placeholder="Enter phone number">
                        </div>
                    </div>
                      <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Country:</label>
                        <input type="text" id = "country" name = "country" class="form-control" v-model="dermatologist.address.country" placeholder="Enter country">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Town:</label>
                        <input type="text" id = "town" name = "town" class="form-control" v-model="dermatologist.address.town" placeholder="Enter town">
                        </div>
                    </div>
                     <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Street:</label>
                        <input type="text" id = "street" name = "street" class="form-control" v-model="dermatologist.address.street" placeholder="Enter street">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Number:</label>
                        <input type="number" id = "number" name = "number" class="form-control" v-model="dermatologist.address.number" placeholder="Enter number">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Postal code:</label>
                        <input type="text" id = "postalCode" name = "postalCode" class="form-control" v-model="dermatologist.address.postalCode" placeholder="Enter postal code">
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
        dermatologist: "",
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
        this.axios.get('/dermatologist/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.dermatologist=response.data;
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
          const addressInfo ={
              town : this.dermatologist.address.town,
              street : this.dermatologist.address.street,
              number : this.dermatologist.address.number,
              postalcode : this.dermatologist.address.postalCode,
              country : this.dermatologist.address.country
          }
            const userInfo ={
                email : this.dermatologist.email,
                password : this.dermatologist.password,
                firstname : this.dermatologist.name,
                surname : this.dermatologist.surname,
                phonenumber : this.dermatologist.phoneNumber,
                address : addressInfo,
            }
              let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/dermatologist/update',userInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Your changes are successfully updated!");
                        console.log(response.data);
                })
                .catch(response => {
                       alert("Please try later.");
                        console.log(response);
                 });    
               
           
            window.location.href = "/dermatologistProfile";  
               
      }
}
}
</script>

<style>
</style>
