ines (39 sloc)  1.61 KB
  
<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
             <span style="float: left; margin: 15px;">
                    <a  class = "btn btn-secondary" href= "/isaHomePage">Home</a>
                    <b class="tab"></b>                
                    <a  class = "btn btn-secondary" href= "/showPharmaciesPatient">Pharmacies</a>
                    <b class="tab"></b>     
                    <a  class = "btn btn-secondary" href= "/myProfilePatient">My profile</a>
                    <b class="tab"></b>                
                    <a  class = "btn btn-secondary" href= "/patientComplaint">Write complaint</a>
                    <b class="tab"></b>   
                    <a  class = "btn btn-secondary" href= "/subscriptionsToPharmacies">My subscriptions</a>
                    <b class="tab"></b>   
                    <a  class = "btn btn-secondary" href= "/eRecipes">ERecipes</a>
                    <b class="tab"></b> 
            </span>
              <span  style="float:right;margin:15px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

                </span>
        </div>

    <div style = "background-color:lightgray; margin: auto; width: 13%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">

                <table class="form-group">
                    <tr>
                        <th></th>
                        <th></th>
                    </tr>

                    <tr v-for="item in this.completeDictionary" v-bind:key="item.pharmacy.pharmacyName">
                        <td>{{item.pharmacy.pharmacyName}}</td>
                        <td v-if = "item.subscribed==='NO'">
                            <button class = "btn btn-link btn-lg" v-on:click = "subrsribe($event,item.pharmacy)">Subscribe</button>
                        </td>
                        <td v-if = "item.subscribed==='YES'">
                            <button class = "btn btn-link btn-lg" v-on:click = "unsubrsribe($event,item.pharmacy)">Unsubscribe</button>
                        </td>   
                    </tr>
                </table>     
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
    }
  },

  methods:{
     
     
      logOut : function(){
          localStorage.removeItem('token');
          window.location.href = "/login";

      },
      
      unsubrsribe : function(event, pharmacy) {
         let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/patient/unsubscribeToPharmacy',pharmacy,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                    this.refreshInformation();
                    console.log(response)
                }).catch(res => {
                       alert("Please try later.");
                        console.log(res);
                });
      },
      subrsribe : function(event, pharmacy){
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/patient/subscribeToPharmacy',pharmacy,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                    this.refreshInformation();
                    console.log(response)
                }).catch(res => {
                       alert("Please try later.");
                        console.log(res);
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
                            if(this.patientsSubscriptions.includes(this.pharmaciesSubscriptions[i])) {
                                const dict = {
                                        pharmacy : {pharmacyName : this.pharmaciesSubscriptions[i] },
                                        subscribed : "YES"
                                    }
                                    this.completeDictionary.push(dict)
                            }
                            else {
                                const dict = {
                                        pharmacy : { pharmacyName: this.pharmaciesSubscriptions[i] },
                                        subscribed : "NO"
                                    }
                                this.completeDictionary.push(dict)
                                
                            }   
                        }
                        
                    }).catch(res => {
                                alert("Please try again later.");
                                console.log(res);});
      }
},
    beforeMount() {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
       
        this.axios.get('/pharmacy/allNames',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
            }).then(response => { 
                this.pharmaciesSubscriptions=[];
                this.pharmaciesSubscriptions=response.data;
            }).catch(res => {
                        alert("Please try again later.");
                        console.log(res);});

        this.refreshInformation()
        
    }
}
</script>

<style>

</style>


  