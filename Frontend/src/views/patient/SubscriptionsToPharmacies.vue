ines (39 sloc)  1.61 KB
  
<template>
  <div v-if="isAuthorized" id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;

     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
             <span style="float: left; margin: 15px;">
                               
                    <a  class = "btn btn-link btn-lg" href= "/patientProfile">Home page</a>
                    <a  class = "btn btn-link btn-lg" href= "/showPharmaciesPatient">Pharmacies</a>
                    <a  class = "btn btn-link btn-lg" href= "/eRecipes">ERecipes</a>
                    <a  class = "btn btn-link btn-lg" href= "/subscriptionsToPharmacies">My subscriptions</a>
                    <a  class = "btn btn-link btn-lg" href= "/patientComplaint">Write complaint</a>
                     <a  class = "btn btn-link btn-lg" href= "/updateProfilePatient">Change my profile</a>
                         <a  class = "btn btn-link btn-lg" href= "/medicationReservation">Reserve a medication</a>
                   

             
                  

            </span>
            <span  style="float:right;margin:15px">
                <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
           </span>
        </div>

    <div v-for="item in this.completeDictionary" v-bind:key="item.pharmacy.id">
                        <div style = "background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">

                            <table id="table2" class="table">
                            
                            
                                <tbody>
                                    <tr>
                                    <th scope="row"></th>
                                    <td>Pharmacy name</td>
                                    <td>{{item.pharmacy.pharmacyName}}</td>
                                    
                                    </tr>
                                    <tr>
                                    <th scope="row"></th>
                                    <td>Address</td>
                                    <td>{{item.pharmacy.postalCode}} {{item.pharmacy.country}}, {{item.pharmacy.street}} {{item.pharmacy.number}}</td>

                                    </tr>
                                    <tr>
                                    <th scope="row"></th>
                                    <td>Mark</td>
                                    <td>{{item.pharmacy.mark}} </td>
                                    
                                    </tr>
                                    <tr>
                                    <th scope="row"></th>
                                    <td v-if = "item.subscribed==='NO'" colspan="2">
                                        <button class="btn btn-primary" v-on:click = "subrsribe($event,item.pharmacy.id)">Subscribe</button>
                                    </td>
                                    <td v-if = "item.subscribed==='YES'" colspan="2">
                                        <button class="btn btn-primary" v-on:click = "unsubrsribe($event,item.pharmacy.id)">Unsubscribe</button>
                                    </td>  
                                    </tr>
                                    
                                
                                </tbody>
                            </table>
                         
                         </div>
                  
    </div>

</div>
</template>

<script>
export default {

  data() {
    return {
       pharmaciesSubscriptions : null,
       patientsSubscriptions : null,
       completeDictionary : [],
       p: null,
       isAuthorized : false
    }
  },

mounted(){
 let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/patient/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,

             }
         }).then(response => {
                this.patient = response.data;
                this.isAuthorized = true;
         
         }).catch(res => {
           this.isAuthorized = false;
                       alert("Please log in first!");
                                 window.location.href = "/login";
                                 console.log(res);
                
                 });

},
  methods:{
     
     
      logOut : function(){
          localStorage.removeItem('token');
          window.location.href = "/login";

      },
      
      unsubrsribe : function(event, pharmacy) {
         let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
           const pharmacyInfo = {
                pharmacyId : pharmacy
            }
            this.axios.post('/patient/unsubscribeToPharmacy',pharmacyInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                    this.refreshInformation();
                    console.log(response)
                }).catch(res => {
                      alert(res.response.data.message);
             });
      },
      subrsribe : function(event, pharmacy){
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            const pharmacyInfo = {
                pharmacyId : pharmacy
            }
            this.axios.post('/patient/subscribeToPharmacy',pharmacyInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                    this.refreshInformation();
                    console.log(response)
                }).catch(res => {
                    alert(res.response.data.message);

                });
      },
      refreshInformation : function() {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.completeDictionary = [];
        this.patientsSubscriptions = [];
        this.axios.get('/patient/mySubscriptions',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                        this.patientsSubscriptions=response.data;
                        var i = 0;
                        for (i = 0; i < this.pharmaciesSubscriptions.length; i++) {
                            if(this.patientsSubscriptions.includes(this.pharmaciesSubscriptions[i].id)) {
                                const dict = {
                                        pharmacy :  this.pharmaciesSubscriptions[i] ,
                                        subscribed : "YES"
                                    }
                                    this.completeDictionary.push(dict)
                            }
                            else {
                                const dict = {
                                        pharmacy : this.pharmaciesSubscriptions[i],
                                        subscribed : "NO"
                                    }
                                this.completeDictionary.push(dict)
                                
                            }   
                        }
                        console.log(this.completeDictionary)
                        
                    }).catch(res => {
                                alert("Please try again later.");
                                console.log(res);});
      }
},
    beforeMount() {
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/patient/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,

             }
         }).then(response => {
             this.isAuthorized = true;
            this.patient = response.data;
         
         }).catch(res => {
                       this.isAuthorized = false;
                       alert("Please log in first!");
                        window.location.href = "/login";
                        console.log(res);
                
                 });
       
        this.axios.get('/pharmacy/allNames',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
            }).then(response => { 
                this.pharmaciesSubscriptions=[];
                this.pharmaciesSubscriptions=response.data;
                console.log(this.pharmaciesSubscriptions)
            }).catch(res => {
                        alert("Please try again later.");
                        console.log(res);});

        this.refreshInformation()
        
    }
}
</script>