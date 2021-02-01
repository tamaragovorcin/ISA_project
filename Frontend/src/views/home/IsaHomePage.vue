<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Home page</button>
                  
            </span>
              <span  style="float:right;margin:15px">
                    <button class = "btn btn-warning btn-lg" v-on:click = "showLoginForm">&nbsp;&nbsp;Login&nbsp;&nbsp;</button>

                    <b class="tab"></b>                
                   

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "showRegistrationForm">Register</button>
                </span>

        </div>

               <h4 style="color: #0D184F;margin:20px">Pharmacies:</h4>

            <div class="row" style = "background-color:whitesmoke; margin: auto; width: 100%;height:5%;border: 3px solid #0D184F;padding: 10px;margin-top:15px;">
         <div class=" form-group col"  v-for="pharmacy in pharmacies" :key="pharmacy.id">

                      <ul class="nav navbar-nav">
                        <li>  <router-link :to="{ path: '/pharmacyProfile/'+pharmacy.id}" v-slot="{href, navigate}">
                                    <button class = "btn btn-primary" :href="href" @click="navigate"  elevation="1">{{pharmacy.pharmacyName}}</button>
                              </router-link>
                        </li>
                        <li class="divider-vertical"></li>



                      </ul>
                                          </div>
                        </div>
    </div>
</template>

<script>
export default {

  data() {
    return {
       pharmacy : {},
       pharmacies : []
    }
  },

  methods:{
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
        this.axios.get('/pharmacy/all')
        .then(response => {
                this.pharmacies = response.data;
                console.log(this.admin);
         }).catch(res => {
                alert("NOT OK");
                console.log(res);
        });
        
    }
     
}
</script>

<style>

</style>


  