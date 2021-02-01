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
            <h3 style="color: #0D184F">Create a new vacation or absence request.</h3>
            
        </div>
        <div class="d-flex justify-content-center md-2 p-40" style="padding: 20px;margin-top:45px">
            
            <label style="font-size: 25px">Chose start day:</label>
            <b class="tab"></b>    
            <input type="date" name="dateS" v-model = "startDate">

            <label style="font-size: 25px">Chose end day:</label>
            <b class="tab"></b>    
            <input type="date" name="dateE" v-model = "endDate">
        </div>
        <div class="d-flex justify-content-center md-2 p-40" style="padding: 20px;margin-top:45px">
               <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose break type" class = "btn btn-info btn-lg" style="float:left;margin-left:50px;">
                <b-dropdown-item v-for="item in this.types"  v-on:click ="typeIsSelected($event, item.type)" v-bind:key="item.type"> {{item.type }}</b-dropdown-item>
                </b-dropdown> 
        </div>
        <div class="d-flex justify-content-center md-2 p-40" style="padding: 20px;margin-top:45px">
            <button class="btn btn-primary btn-lg" v-on:click = "sendRequest">Send request</button>
        </div>
    </div>
</template>

<script>
export default {
  data() {
    return {
        pharmacist : {},
    
        startDate: null,
        endDate : null,
        types: [
          { type: 'VACATION' },
          { type: 'ABSENCE' },
      
      ],
         type : ""
     
    }
  },
  methods:{
      logOut : function(){
           localStorage.removeItem('token');
           window.location.href = "/login";
      },
      showMyProfile : function() {
         
      },
        sendRequest : function() {
            const holiday = {
            pharmacist : this.pharmacist,
            startDate : this.startDate,
            endDate  :this.endDate,
            type : this.type
          }
          console.log(holiday)
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
          console.log(token)
           this.axios.post('/holidayPharmacist/add',holiday,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Request is successfully created!");
                        console.log(response.data);
                })
                .catch(response => {
                       alert("Please try later.");
                        console.log(response);
                 }); 
         
      },
      typeIsSelected : function(event, type) {
        this.type = type;
      }
     
      
},
mounted() {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/pharmacist/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
                this.pharmacist = response.data;
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
