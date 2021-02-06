<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                    <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Home</button>
                   <strong class="tab"></strong>  
                    <strong class="tab"></strong>

                    <button class = "btn btn-link btn-lg" v-on:click = "showMyProfile">My profile</button>

                    <strong class="tab"></strong>  
                    <strong class="tab"></strong>

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "showclients">My clients</button>
                    <strong class="tab"></strong>  
                    <strong class="tab"></strong>   

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "workCalendar">Work calendar</button>
                    <strong class="tab"></strong>  
                    <strong class="tab"></strong>

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "vacation">Create a vacation</button>
                    <strong class="tab"></strong>  
                    <strong class="tab"></strong>
                    
                 
                
            </span>
              <span  style="float:right;margin:15px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

                </span>

        </div>


    <div>
        
         <table class="table table-striped table-dark">
            <thead class="thead-info">
              <tr>
                <th scope="col"></th>
                <th scope="col">Monday</th>
                <th scope="col">Tuesday</th>
                <th scope="col">Wednesday</th>
                <th scope="col">Thursday</th>
                <th scope="col">Friday</th>
                <th scope="col">Saturday</th>
                <th scope="col">Sunday</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Start time of your shift</td>
                <td>{{workingHours.startTimeMonday}}</td>
                <td>{{workingHours.startTimeTuesday}}</td>
                <td>{{workingHours.startTimeWednesday}}</td>
                <td>{{workingHours.startTimeThursday}}</td>
                <td>{{workingHours.startTimeFriday}}</td>
                <td>{{workingHours.startTimeSaturday}}</td>
                <td>{{workingHours.startTimeSunday}}</td>
                
              </tr>
              <tr>
                <td>End time of your shift</td>
                <td>{{workingHours.endTimeMonday}}</td>
                <td>{{workingHours.endTimeTuesday}}</td>
                <td>{{workingHours.endTimeWednesday}}</td>
                <td>{{workingHours.endTimeThursday}}</td>
                <td>{{workingHours.endTimeFriday}}</td>
                <td>{{workingHours.endTimeSaturday}}</td>
                <td>{{workingHours.endTimeSunday}}</td>
                
                
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
      
       pharmacist : [],
      
        workingHours : {},
       
      

    }
  },
  mounted(){
     let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/pharmacist/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.pharmacist=response.data;
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });


          this.axios.get('/workingHours/my', { 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.workingHours = response.data;
                             console.log(response.data);
                           
                    }).catch(res => {
                            alert("NOT OK");
                            console.log(res);
                    });

     
    
   },
  methods:{
      showclients : function(){
         window.location.href = "/pharmacistClients";
      },
      showMyProfile: function(){
         window.location.href = "/pharmacistProfile";
      },
      showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      logOut : function(){
          window.location.href = "/login";

      },
      prikaz : function(){
         
      },
      
      workCalendar : function(){

      },
      vacation: function(){
           window.location.href = "/createRequestVacation";
      },
     
      sendComplaint : function(){
           
      },
     
  }
}

</script>

<style>

</style>


  