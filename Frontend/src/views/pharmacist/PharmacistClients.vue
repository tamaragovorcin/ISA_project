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
                            <input style="width: 200px;" class="form-control" placeholder="Enter patient's name" name="name" type="text" id="name" v-model="name" >              
                        <td>&nbsp;</td>  
                         
                         
                        <b class="tab"></b>     
                        <b class="tab"></b>  

                        <td align="left">Patient's secondname:</td>
                        <td>&nbsp;</td>
                          <input style="width: 200px;" class="form-control" placeholder="Enter patient's secondname" name="secondname" type="text" v-model="secondname" id="secondname">              
                        <td>&nbsp;</td>  
                        <b class="tab"></b>     
                        <b class="tab"></b>  


                        <td align="right" ><button  class = "btn btn-success" v-on:click="search">Search</button></td>
                        <b class="tab"></b>  
                        <td align="right" ><button  class = "btn btn-secondary" v-on:click="cancel">Cancel</button></td>
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
                            <div class="blog-desc">
                                <p>Name: {{patient.name}} </p>
                                <p>Surname: {{patient.surname}} </p>
                            </div>
                        </div>
                    </div>
    </div>

</div>
</template>

<script>
export default {

  data() {
    return {
      
       patients : [],
       name: "",
       secondname: ""
      

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