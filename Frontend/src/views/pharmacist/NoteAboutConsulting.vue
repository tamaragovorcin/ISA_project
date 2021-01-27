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
    <section class = "border">
        <h3 align="center"><b>Search your patients</b></h3>
			<form accept-charset="UTF-8">
                <table align="center">
                    <tr>
                        <b class="tab"></b> 
                        <td align="left">Patient's name::</td>
                        <td>&nbsp;</td>
                            <input style="width: 200px;" class="form-control" placeholder="Enter patient's name" name="name" type="text" id="name" >              
                        <td>&nbsp;</td>  
                         
                         
                        <b class="tab"></b>     
                        <b class="tab"></b>  

                        <td align="left">Patient's secondname:</td>
                        <td>&nbsp;</td>
                            <input style="width: 200px;" class="form-control" placeholder="Enter patient's secondname" name="secondname" type="text" id="secondname">              
                        <td>&nbsp;</td>  
                        <b class="tab"></b>     
                        <b class="tab"></b>  


                        <td align="right" ><button  class = "btn btn-success" v-on:click="search">Search</button></td>
                        <b class="tab"></b>  
                       
                    </tr>

                    
                </table>   
		</form>
    </section>    
    </div>

    <div class="container-fluid">
                    <b class="tab"></b>     
                    <b class="tab"></b>  
                    <h3 align = "center"><b>Your patients</b></h3>
                    <div v-for="patient in patients" :key="patient.id" class="col-md-12 border ">
                        <div class="blog-box" >
                            <div class="blog-desc" style="font-size:25">
                                <p>Name: {{patient.name}} </p>
                                <p>Surname: {{patient.surname}} </p>
                                <button class ="btn btn-primary" v-on:click = "note($event, patient.id)">Note consulting</button>
                                <b class="tab"></b> 
                                <button class ="btn btn-danger" v-on:click = "ignored">Patient ignored consulting</button>
                            </div>
                        </div>
                    </div>
    </div>
<div>
     <b-modal ref="my-modal" hide-footer scrollable title="Write complaint" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                      <div class="modal-header">
                                      
                                      <h3>Note consulting</h3>
                                      </div>
                                      <div class="modal-body">
                                      
                                      <div class="row justify-content-md-end">
                                          <label style="font-size:17">Date:</label>
                                          <input type="date" name="date" v-model="date">
                                      </div>
                                        
                                        <div class="row justify-content-md-center">

                                          <div class="col-25">
                                          <label style="font-size: 20px;">Your comment about consulting:</label>
                                          </div>
                                          <b class="tab"></b> 
                                          <div class="col-75">
                                          <input type="text" style="font-size: 20px; background:white; height:80px" size="80" placeholder="Enter consulting.." v-model="information">
                                          </div>
                                           <b class="tab"></b> 
                                        <b class="tab"></b> 
                                        <b class="tab"></b> 
                                         </div>
                                         <b class="tab"></b> 
                                        <b class="tab"></b> 
                                        <b class="tab"></b> 
                                        
                                        <div class="row justify-content-md-center">

                                          <div class="col-25">
                                          <label style="font-size: 20px;">Start time:</label>
                                          </div>
                                          <b class="tab"></b> 
                                          <div class="col-75">
                                          <input type="time" style="font-size: 20px;" size="80" placeholder="Enter time.." v-model="startTime">
                                          </div>

                                         </div>
                                        

                                        <div class="row justify-content-md-center">

                                          <div class="col-25">
                                          <label style="font-size: 20px;">Duration of consulting:</label>
                                          </div>
                                          <b class="tab"></b> 
                                          <div class="col-75">
                                          <input type="number" style="font-size: 20px;" size="80" placeholder="Enter duration of consulting.." v-model="duration">
                                          </div>

                                         </div>
                                        
                                      </div>
                                      <div class="modal-footer">
                                         <button class="btn btn-secondary" click="hideModal">Close</button>
                                        <button class="btn btn-primary" click="sendConfirm">Confirm consulting</button>
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
       pharmacist: "", 
       patient: "",
       date: "",
       startTime: "",
       duration: "", 
       price: "",
       canceled: false,
       showedUp: false,
       information: ""

      

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
      
      note: function(){
         this.$refs['my-modal'].show()
      },
       hideModal() {
        this.$refs['my-modal'].hide()
      },
      sendConfirm: function() {
    

      },
      ignored: function(){
         
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