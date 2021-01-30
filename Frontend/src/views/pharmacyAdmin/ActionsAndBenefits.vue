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
                 <a  class = "btn btn-secondary" href = "/phAdminProfileUpdate">Update profile</a>
                 
                <b class="tab"></b> 
                 <b-dropdown id="ddCommodity" name="ddCommodity" text="Pharmacists" 
                               class = "btn btn-link btn-lg">
                    <b-dropdown-item href = "/pharmacyPharmacists">Our pharmacists</b-dropdown-item>
                    <b-dropdown-item href = "/addPharmacist">Add new pharmacist</b-dropdown-item>      
                </b-dropdown> 
                  <b class="tab"></b>  
                <a  class = "btn btn-secondary" href = "/pharmacyDermatologists">Our dermatologists</a>      
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

         <b-button class = "btn btn-warning" @click="showModal">+ New action or benefit</b-button>
        
        <b-modal ref="my-modal" hide-footer scrollable title="Write action or benefit" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                    <div class="modal-header">
                                        
                                  <h3>Share actions and benefits</h3>
                                         
                        
                                    </div>
                                    <div class="modal-body">
                                         <div class="row">
                                           <div class="col">
                                                 <label for="name">Describe action or benefit:</label>
                                            </div> 
                                         </div>
                                           <div class="row">
                                              <input type="textarea" style="height:300px;width:750px;background-color:white;" v-model = "description" class="form-control">
                                            </div>
                                             <div class="row">
                                           <div class="col">
                                                 <label for="name">Expiry date:</label>
                                            </div> 
                                         </div>
                                           <div class="row">
                                              <input type="date" v-model = "expiryDate" class="form-control" placeholder = "01/01/2021">
                                            </div>
                                     
                                     
                                    </div>
                    <div class="modal-footer">
                                        <button class="btn btn-secondary" block @click="hideModal">Close</button>
                                        <button class="btn btn-primary" v-on:click="share">Share</button>
                   </div>

                            
                                   
                            </div>
                    </div>
    
    </b-modal>
    <div style="height:25px"></div>
      <h3>Active actions and benefits</h3>

  <table class="table table-striped table-dark">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Description</th>
      <th scope="col">Expiry date</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="action in actions" :key="action.id">
                                                    <td></td>
                                                    <td>{{action.description}}</td>
                                                    <td>{{action.expiryDate}}</td>
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
       showMed : false,
       description : "",
       expiryDate : "",
       actions : [],
       pharmacy : {}
    }
  },
    mounted() {
       let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/pharmacyAdmin/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
                this.admin = response.data;
                console.log(this.admin);
                this.axios.get('/pharmacyAdmin/myPharmacy',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.pharmacy = response.data;
                            console.log(this.pharmacy);
                             
                    }).catch(res => {
                            alert("NOT OK");
                            console.log(res);
                    });
                     this.axios.get('/pharmacyAdmin/actions',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.actions = response.data;
                            console.log(this.pharmacy);
                             
                    }).catch(res => {
                            alert("NOT OK");
                            console.log(res);
                    });
                    
                
         

         }).catch(res => {
                alert("NOT OK");
                console.log(res);
        });
          
     
},

  methods:{
       showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      showMyProfile: function(){

      },
       showActionsAndBenefitsForm : function(){
              this.$refs['my-modal'].show()
      },
       showOrderForm : function(){
          window.location.href = "/order";

      },
       logOut : function(){
           window.location.href = "/login";
      },
      showMyPharmacy : function (){
          window.location.href = "/myPharmacy"
      },
      addNewPharmacist : function(){
        window.location.href = "/addNewPharmacist";
      },
      showModal() {
        this.$refs['my-modal'].show()
      },
      hideModal() {
        this.$refs['my-modal'].hide()
      },
      share : function(){
             const action = {
                    pharmacy : this.pharmacy,
                    description: this.description,
                    expiryDate : this.expiryDate,
                  
                };
                this.axios.post('pharmacy/addActions', action)
                    .then(res => {
                        console.log(res);
                    })
                    .catch(res => {
                        console.log(res);
                    })
                    window.location.href = "/actionsAndBenefits";
        }
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


  