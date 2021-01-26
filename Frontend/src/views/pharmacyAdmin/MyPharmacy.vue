<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
              <span style="float: left; margin: 15px;">
                <a  class = "btn btn-secondary" href= "/isaHomePage">Home</a>
                <b class="tab"></b>    
                <a  class = "btn btn-secondary" href = "/pharmacyAdminProfile">My profile</a>
                <b class="tab"></b>    
                <a  class = "btn btn-secondary" href = "/myPharmacy">My Pharmacy</a>
                 <b class="tab"></b>    

        <a  class = "btn btn-secondary" href = "/pharmacyDermatologists">Dermatologists</a>
                <b class="tab"></b>    
        <a class = "btn btn-secondary" href = "/pharmacyPharmacists">Pharmacists</a>
                <b class="tab"></b>    
        <a   class = "btn btn-secondary" href = "/pharmacyMedications">Medications</a>
                <b class="tab"></b>    
        <a  class = "btn btn-secondary" href = "/actionsAndBenefits">Actions and benefits</a>
                <b class="tab"></b>    
        <a   class = "btn btn-secondary" href="/order">Orders</a>
            </span>
              <span  style="float:right;margin:15px">
                   
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
                </span>

        </div>
        
      
        <div class="container-fluid">
          
      <h3 style="color: #0D184F; font-size: 35px; font-weight:bold; margin-top:45px"></h3>

            

                
          

                <button class="btn btn-primary" v-on:click ="showMyProfile">Show</button>
        

          


        </div>
        

       

       

    </div>
</template>

<script>
export default {

  data() {
    return {
      pharmacy : null,
      admin : null,
    }
  },

 mounted() {
   let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/pharmacyAdmin/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
              this.admin=response.data;
              alert(this.admin.id);
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });

     


 },
  methods:{
    
      showMyProfile: function(){
           let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

           var id = this.admin.id;
      alert(id);
      this.axios.get('/pharmacy/admin/'+this.admin.id,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
          }).then(response => {
            this.pharmacy = response.data;
          });
      },
       
       logOut : function(){
           window.location.href = "/login";
      },
      showMyPharmacy : function (){
          window.location.href = "/myPharmacy"
      },
      showModal() {
        this.$refs['my-modal'].show()
      },
      hideModal() {
        this.$refs['my-modal'].hide()
      },
}
}
</script>

<style>
body {
  font-family: "Lato", sans-serif;
}

.sidenav {
  height: 100%;
  width: 270px;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color:whitesmoke ;
  overflow-x: hidden;
  padding-top: 20px;
  margin-top : 90px;
}

.sidenav a {
  padding: 6px 6px 6px 2px;
  text-decoration: none;
  font-size: 25px;
  color: #292b2c;
  text-align : left;
  font-color : white;
}

.sidenav a:hover {
  color: darkgray;
}



@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
</style>


  