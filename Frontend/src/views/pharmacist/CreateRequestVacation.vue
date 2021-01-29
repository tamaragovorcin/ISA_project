<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            <span style="float: left; margin: 15px;">
                  <button class = "btn btn-link btn-lg" v-on:click = "showMyProfile">My profile</button>
                  <b class="tab"></b>                
            </span>
            <span  style="float:right;margin:15px">
                     <b class="tab"></b>    
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
            </span>
        </div>
        <div style="background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
            <h3 style="color: #0D184F">Create a new vacation request.</h3>
            
        </div>
        <div class="d-flex justify-content-center md-2 p-40" style="padding: 20px;margin-top:45px">
            
            <label style="font-size: 25px">Chose start day of vacation</label>
            <b class="tab"></b>    
            <input type="date" name="dateS">

            <label style="font-size: 25px">Chose end day of vacation</label>
            <b class="tab"></b>    
            <input type="date" name="dateE">
        </div>

        <div>
            <button class="btn btn-primary">Send request</button>
        </div>
    </div>
</template>

<script>
export default {
  data() {
    return {
      supplierInfo: {
                email : "",
                password : "",
                firstname : "",
                surname : "",
                phonenumber : "",
                address : {
                    town : "",
                    street : "",
                    number : "",
                    postalCode : "",
                    country : ""
                }
                },
    }
  },
  methods:{
      logOut : function(){
           localStorage.removeItem('token');
           window.location.href = "/login";
      },
      showMyProfile : function() {
         
      },
      changeInformation : function() {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/pharmacist/update',this.supplierInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Profile is successfully changed!!");
                        console.log(response.data);
                })
                .catch(response => {
                       alert("Please try later.");
                        console.log(response);
                 });    
      }
      
},
mounted() {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/pharmacist/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
                this.supplierInfo = response.data;
                console.log(response.data);
         }).catch(res => {
                alert("NOT OK");
                console.log(res);
        });
    }
}
</script>

<style>
</style>
