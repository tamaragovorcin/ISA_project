  
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
                        <td align="left" style="font-size:25px">Patient's name:</td>
                        <td>&nbsp;</td>
                            <input style="width: 200px;" class="form-control" placeholder="Enter patient's name" name="name" type="text" id="name" v-model="name" >              
                        <td>&nbsp;</td>  
                         
                         
                        <b class="tab"></b>     
                        <b class="tab"></b>  

                        <td align="left" style="font-size:25px">Patient's secondname:</td>
                        <td>&nbsp;</td>
                          <input style="width: 200px;" class="form-control" placeholder="Enter patient's secondname" name="secondname" type="text" v-model="surname" id="secondname">              
                        <td>&nbsp;</td>  
                        <b class="tab"></b>     
                        <b class="tab"></b>  


                        <td align="right" ><button  class = "btn btn-success btn-lg" v-on:click="search">Search</button></td>
                        <b class="tab"></b>  
                       
                    </tr>

                    
                </table>   
		</form>
    </section>    
    </div>

     <div style="height:25px"></div>
      <h3>Patients</h3>

          <table class="table table-striped table-dark">
            <thead class="thead-info">
              <tr>
                <th scope="col"></th>
                <th scope="col">Name</th>
                <th scope="col">Surname</th>
                
              </tr>
            </thead>
            <tbody>
              <tr v-for="consulting in ourConsultings" :key="consulting.id">
                <td></td>
                <td>{{consulting.namePatient}}</td>
                <td>{{consulting.surnamePatient}}</td>
               
                <td><button  v-on:click ="remove($event, patient)" class="btn btn-info">Remove</button></td>
              </tr>
            </tbody>
          </table>
      <div v-if = "showMedicationListInfoDiv" style = "background-color:lightgray; margin: auto; width: 60%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
               <div class="row">
                    <div class=" form-group col">
                        <label >Name</label>
                    </div>
                    <div class=" form-group col">          
                        <label >Surname</label>
                    </div>
                    <div class=" form-group col">
                        <label ></label>
                    </div>
               </div>
               <div v-for="patient in medicationSeacrhList"   v-bind:key="patient.name">
                <div class="row">
                        <div class=" form-group col">
                            <label >{{patient.name}}</label>
                        </div>
                        <div class=" form-group col">          
                            <label >{{patient.surname}}</label>
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
        name : "",
        surname : "",
        email : "",
        phoneNumber : "",
        town : "",
        street : "",
        number : "",
        postalCode : "",
        country : "",
        showMedicationListInfoDiv: false,
        medicationSeacrhList : [],
         ourConsultings: "",
         consulting: {
       patient : {
         name : "",
         surname : ""
       },
       pharmacist : "", 
       date: "",
       startTime: "",
       duration: "", 
       price: "",
       canceled: false,
       showedUp: false,
       information: ""
       },
    }  
   },

   mounted(){
    let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
      this.axios.get('/pharmacist/consultings',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.ourConsultings = response.data;
                            alert(this.ourConsultings);
                    }).catch(res => {
                            alert("NOT OK");
                            console.log(res);
                    });
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
      remove : function(event,patient){
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.post('patient/delete', patient,{ 
                headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            console.log(response);
                          
                             
                    }).catch(response => {
                            alert("NOT OK");
                            console.log(response);
                    });
         
     },
       search: function(){
           this.axios.get('/patient/searchForm/'+this.name).
            then(response => {
                    this.medicationSeacrhList= response.data;
                    this.showMedicationListInfoDiv = true;

            }).catch(res => {
                        alert("NOT OK");
                        console.log(res);
                    });
         
      },
       cancel: function(){
         
      },
      showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      logOut : function(){
          window.location.href = "/login";
      }
  }
}
</script>

<style>
</style>