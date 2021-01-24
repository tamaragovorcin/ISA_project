<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                    <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Home</button>
                    <b class="tab"></b>  
                    <b class="tab"></b>  
                    <b class="tab"></b>  

                    <button class = "btn btn-link btn-lg" v-on:click = "showMyProfile">My profile</button>

                    <b class="tab"></b>     
                    <b class="tab"></b>             
                   

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "showclients">My clients</button>
                    <b class="tab"></b>  
                    <b class="tab"></b>      

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "workCalendar">Work calendar</button>
                    <b class="tab"></b>
                    <b class="tab"></b>  

                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "vacation">Create a vacation</button>
                    <b class="tab"></b>
                    

                
            </span>
              <span  style="float:right;margin:15px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

             </span>
    </div>
    <div>
            <h2> Consulgting </h2>

            <div style="font-size:30px" class="text-dark d-flex justify-content-start p-2 md-2">
                <b class="tab"></b>  
                    <b class="tab"></b>  
                    <b class="tab"></b>  

                <label>Choose your patient:</label>
                 <select v-model="patient">
                    <option v-for="patient in patients" :key="patient.id">
                        {{patient.name}}+{{patient.surname}}
                    </option>
                </select>
                <b class="tab"></b>  
                <b class="tab"></b> 
                 <b class="tab"></b>  
                <b class="tab"></b>  

            </div>
            <div class="d-flex align-content-center flex-wrap md-2 p-5">
                <b class="tab"></b>  
                <b class="tab"></b>  

                <button class="btn btn-success">Saved consulting</button>
                <b class="tab"></b>  
                
                <button class="btn btn-danger">The patient did not show up for counseling.</button>
            </div>

    </div>
 <div>
     <b-modal ref="my-modal" hide-footer scrollable title="Write complaint" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                    <div class="modal-header">
                        
                                    </div>
                                    <div class="modal-body">
                                        <label>Input comment about this consulting</label>
                                    </div>
                                     <div class="modal-footer">
                                        <button class="btn btn-secondary">Close</button>
                                        <button class="btn btn-primary">Send complaint</button>
                                    </div>
                            </div>
                    </div>
    </b-modal>
</div>

</div>
</template>

<script>
export default {

  data() {
    return {
      
       patients : [],
       patient: ""
      

    }
  },
   mounted(){
		this.axios.get('/patient')
        .then(response => {
          this.patients = response.data;
          console.log(response);

      })
    },
 
     methods:{
      showMyProfile: function(){
         
      },
        showclients : function(){

      },
        workCalendar: function(){
         
      },
        vacation: function(){
         
      },
       search: function(){
         
      },
       cancel: function(){
         
      },
      showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      logOut : function(){
          window.location.href = "/login";

      },
      sendComplaint : function(){

      }
  }
}

</script>

<style>

</style>