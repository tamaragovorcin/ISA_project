<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
              <span style="float: left; margin: 15px;">
                <a  class = "btn btn-secondary" href = "/pharmacyAdminProfile">My profile</a>
                <b class="tab"></b>    
                <a  class = "btn btn-secondary" href = "/myPharmacy">My Pharmacy</a>
                 <b class="tab"></b>    
                 <a  class = "btn btn-secondary" href = "/phAdminProfileUpdate">Update profile</a>
                 
                <b class="tab"></b>    
                <a  class = "btn btn-secondary" href = "/addPharmacist">Add pharmacist</a>    
                <b class="tab"></b> 
                 <a  class = "btn btn-secondary" href = "/pharmacyPharmacists">Our pharmacists</a> 
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
        
        <b-modal ref="my-modal" hide-footer scrollable title="Define" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                    <div class="modal-header">
                                        
                                  <h3>Share actions and benefits</h3>
                                         
                        
                                    </div>
                                    <div class="modal-body">
                                        
                                          
                                     
                                    </div>
                    <div class="modal-footer">
                                        <button class="btn btn-secondary" block @click="hideModal">Close</button>
                   </div>

                            
                                   
                            </div>
                    </div>
    
    </b-modal>
    <div style="height:25px"></div>
      <h3>Pharmacists in {{pharmacy.pharmacyName}}</h3>

  <table class="table table-striped table-dark">
  <thead class="thead-dark">
    <tr>
      <th scope="col"></th>
      <th scope="col">Name</th>
      <th scope="col">Surname</th>
      <th scope="col">Email</th>
      <th scope="col">Phone Number</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="pharmacist in pharmacists" :key="pharmacist.id">
                                                    <td></td>
                                                    <td>{{pharmacist.name}}</td>
                                                    <td>{{pharmacist.surname}}</td>
                                                    <td>{{pharmacist.email}}</td>
                                                    <td>{{pharmacist.phoneNumber}}</td>
                                                  	<td><button  v-on:click ="remove($event, pharmacist)" class="btn btn-info">Remove</button></td>
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
        pharmacists : []
    }
  },

  methods:{
      showModal() {
        this.$refs['my-modal'].show()
      },
      hideModal() {
        this.$refs['my-modal'].hide()
      },
      add : function(){
          
      },
       logOut : function(){
            
      },
      remove : function(event,pharmacist){
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        	this.axios.post('pharmacist/delete', pharmacist,{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            console.log(response);
                          
                             
                    }).catch(response => {
                            alert("NOT OK");
                            console.log(response);
                    });
          window.location.href = "/pharmacyPharmacists";

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
                    this.axios.get('/pharmacyAdmin/pharmacists',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.pharmacists = response.data;
                            alert(this.pharmacists);
                    }).catch(res => {
                            alert("NOT OK");
                            console.log(res);
                    });
                    
                
         

         }).catch(res => {
                alert("NOT OK");
                console.log(res);
        });
        
    }
}
</script>

<style>

</style>


  