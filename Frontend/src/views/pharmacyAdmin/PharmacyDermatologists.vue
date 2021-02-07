
  
<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
                <span style="float: left; margin: 15px;">
                <a  class = "btn btn-secondary" href= "/isaHomePage">Home</a>
                <strong class="tab"></strong>    
                <a  class = "btn btn-secondary" href = "/pharmacyAdminProfile">My profile</a>
                 <strong class="tab"></strong>      
                <a  class = "btn btn-secondary" href = "/myPharmacy">My Pharmacy</a>
                    <strong class="tab"></strong>      
                 <a  class = "btn btn-secondary" href = "/phAdminProfileUpdate">Update profile</a>
                 
                   <strong class="tab"></strong>    
                <a  class = "btn btn-secondary" href = "/addPharmacist">Add pharmacist</a>    
                  <strong class="tab"></strong>     
                 <a  class = "btn btn-secondary" href = "/pharmacyPharmacists">Our pharmacists</a> 
                    <strong class="tab"></strong>     
                <a  class = "btn btn-secondary" href = "/pharmacyDermatologists">Our dermatologists</a>      
                   <strong class="tab"></strong>    
                <a   class = "btn btn-secondary" href = "/pharmacyMedications">Medications</a>
                          <strong class="tab"></strong>      
                <a  class = "btn btn-secondary" href = "/actionsAndBenefits">Actions and benefits</a>
                           <strong class="tab"></strong>       
                <a   class = "btn btn-secondary" href="/order">Orders</a>
            </span>
              <span  style="float:right;margin:15px">
                   
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
                </span>

        </div>
        
       
                  <div style="height:30px"></div>

        <div class="container-fluid">

         <b-button class = "btn btn-warning" @click="showModal">+ Add examination terms</b-button>
        
        <b-modal ref="my-modal" hide-footer scrollable title="Add examination to schedule" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                    
                                    <div class="modal-body">
                                    
                                            <label>Choose dermatologist:</label>
                            <div class="form-row">
                                 <div class="form-group col">
                                    <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose dermatologist"
                                        class = "btn btn-link btn-lg" style="float:left; width=200px;">
                                            <b-dropdown-item v-for="dermatologist in this.allDermatologists"  v-on:click = "dermatologistIsSelected($event, dermatologist)" v-bind:key="dermatologist.id"> 
                                            {{ dermatologist.firstname }}<div style="width:20px"></div>{{dermatologist.surname }}
                                            </b-dropdown-item>
                                    </b-dropdown> 
                                 </div>
                                <div class="form-group col-md-6 ">
                                    <label style="font-size:25px;font-weight:bold;">{{this.dermatologistName}}</label>
                                    <label style="font-size:25px;font-weight:bold;">{{this.dermatologistSurName}}</label>
                                </div>
                                    
                            </div>
                                             <div class="row">
                                           <div class="col">
                                                 <label for="name">Date:</label>
                                            </div> 
                                         </div>
                                           <div class="row">
                                              <input type="date" v-model = "date" class="form-control" placeholder = "01/01/2021">
                                            </div>
                                             <div class="row">
                                           <div class="col">
                                                 <label for="name">Start time:</label>
                                            </div> 
                                         </div>
                                           <div class="row">
                                              <input type="time" v-model = "startTime" class="form-control" placeholder = "01/01/2021">
                                            </div>
                                        <div class="row">
                                           <div class="col">
                                                 <label for="name">Duration:</label>
                                            </div> 
                                         </div>
                                           <div class="row">
                                              <input type="number" v-model = "duration" class="form-control">
                                            </div>

                                         <div class="row">
                                           <div class="col">
                                                 <label for="name">Price:</label>
                                            </div> 
                                         </div>
                                           <div class="row">
                                              <input type="number" v-model = "price" class="form-control">
                                            </div>

                                     
                                     
                                    </div>
                    <div class="modal-footer">
                                        <button class="btn btn-secondary" block @click="hideModal">Close</button>
                                        <button class="btn btn-primary" v-on:click="add">Add</button>
                   </div>

                            
                                   
                            </div>
                    </div>
    
    </b-modal>


<b-button class = "btn btn-warning" @click="showModal1">+ Add dermatologist</b-button>
        
        <b-modal ref="my-modal1" hide-footer scrollable title="Add dermatologist as employee" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                    
                                    <div class="modal-body">
                                    
                                    <label>Choose dermatologist:</label>
                            <div class="form-row">
                                 <div class="form-group col">
                                        <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose dermatologist"
                                        class = "btn btn-link btn-lg" style="float:left; width=200px;">
                                            <b-dropdown-item v-for="dermatologist in this.allDermatologists"  v-on:click = "dermatologistIsSelected1($event, dermatologist)" v-bind:key="dermatologist.id"> 
                                            {{ dermatologist.firstname }}<div style="width:20px"></div>{{dermatologist.surname }}
                                            </b-dropdown-item>
                                    </b-dropdown> 
                                 </div>
                                <div class="form-group col-md-6 ">
                                    <label style="font-size:25px;font-weight:bold;">{{this.selectedDermatologist1.firstname}}</label>
                                    <label style="font-size:25px;font-weight:bold;">{{this.selectedDermatologist1.surname}}</label>
                                </div>
                                    
                            </div>
                                          
                                             
                            

                                     
                                     
                                    </div>
                    <div class="modal-footer">
                                        <button class="btn btn-secondary" block @click="hideModal1">Close</button>
                                        <button class="btn btn-primary" v-on:click="addDermatologist">Add</button>
                   </div>

                            
                                   
                            </div>
                    </div>
    
    </b-modal>



    
    <div style="height:45px"></div>
      <h3>Dermatologists of our pharmacy</h3>

  <table class="table table-striped table-dark">
  <thead class="thead-dark">
    <tr>
      <th scope="col"></th>
      <th scope="col">Name</th>
      <th scope="col">Surname</th>
      <th scope="col">Phone number</th>
      <th scope="col">Email</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="dermatologist in ourDermatologists" :key="dermatologist.id">
                                                    <td></td>
                                                    <td>{{dermatologist.name}}</td>
                                                    <td>{{dermatologist.surname}}</td>
                                                    <td>{{dermatologist.phoneNumber}}</td>
                                                    <td>{{dermatologist.email}}</td>
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
       pharmacyId : 1,
       actions : [],
       ourDermatologists :[],
       allDermatologists : [],
       selectedDermatologist : {},
       dermatologistName : "",
       dermatologistSurName : "",
       date : "",
       startTime : "",
       endTime : "",
       pharmacy : {},
       admin : {},
       duration : 0,
       price : 0,
       selectedDermatologist1 : {}
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
                    
                
         
         }).catch(res => {
                alert("NOT OK");
                console.log(res);
        });
        this.axios.get('/pharmacyAdmin/dermatologists',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.ourDermatologists = response.data;
                            alert(this.ourDermatologists);
                    }).catch(res => {
                            alert("NOT OK");
                            console.log(res);
                    });
        this.axios.get('/dermatologist',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.allDermatologists=response.data;
         }).catch(res => {
                       alert("Please try again later.");
                        console.log(res);
                 });
        
        
    }
     ,
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
      showModal1() {
        this.$refs['my-modal1'].show()
      },
      hideModal1() {
        this.$refs['my-modal1'].hide()
      },
       add : function(){
            const data ={
              pharmacy : this.pharmacy,
              dermatologist : this.selectedDermatologist,
              date : this.date,
              startTime : this.startTime,
              duration : this.duration,
              price : this.price
          }
            
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/pharmacy/addExaminationSchedule',data,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                        console.log(response.data);
                })
                .catch(response => {
                       alert("Please try later.");
                        console.log(response);
                 });    
      },
        
      dermatologistIsSelected : function(event, dermatologist) {
            this.selectedDermatologist = dermatologist;
            this.dermatologistName = this.selectedDermatologist.name;
            this.dermatologistSurName = this.selectedDermatologist.surname;
            console.log(event);
      },
        dermatologistIsSelected1 : function(event, dermatologist) {
            this.selectedDermatologist1 = dermatologist;
            this.dermatologistName1 = this.selectedDermatologist1.name;
            this.dermatologistSurName1 = this.selectedDermatologist1.surname;
            console.log(event);
      },
      addDermatologist: function(){
         const data ={
              pharmacy : this.pharmacy,
              dermatologist : this.selectedDermatologist1,
              
          }
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/dermatologist/addPharmacy',data,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                        alert("Dermatologist successfully added as employee in your pharmacy");
                        console.log(response.data);
                })
                .catch(response => {
                       alert("Please try later.");
                        console.log(response);
                 });  
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
}
.sidenav a:hover {
  color: darkgray;
}
@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
</style>

