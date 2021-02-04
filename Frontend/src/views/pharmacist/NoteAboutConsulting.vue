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
                    <button class = "btn btn-link btn-lg" style="margin-right:10px;" v-on:click = "showMedications">Medications</button>
                    <strong class="tab"></strong>  
                    <strong class="tab"></strong>
                    

                
            </span>
              <span  style="float:right;margin:15px">
                    

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>

             </span>
    </div>
    

    <div class="container-fluid">
                    <b class="tab"></b>     
                    <b class="tab"></b>  
                     <div style="height:45px"></div>
                         <h3>Consulting of pharmacist</h3>

                                        <table class="table table-striped table-dark">
                                        <thead class="thead-dark">
                                          <tr>
                                            <th scope="col"></th>
                                            <th scope="col">Name patient</th>
                                            <th scope="col">Surname patient</th>
                                            <th scope="col">Date of consulting</th>
                                            <th scope="col">Time:</th>
                                            <th scope="col">Note:</th>
                                            <th scope="col">Prescription:</th>
                                            <th scope="col">Ignored:</th>
                                          </tr>
                                        </thead>
                                        <tbody>
                                          <tr v-for="consulting in ourConsultings" :key="consulting.id">
                                                    <td></td>
                                                    <td>{{consulting.namePatient}}</td>
                                                    <td>{{consulting.surnamePatient}}</td>
                                                    <td>{{consulting.date}}</td>
                                                    <td>{{consulting.startTime}}</td>
                                                    <td><button class="btn btn-primary btn-lg" v-on:click="Belezi ($event, consulting)">Note Consulting</button></td>
                                                   <td><button class="btn btn-success btn-lg" v-on:click="Recept ($event, consulting)">Add prescription</button></td>
                                                    <td> <button class="btn btn-danger  btn-lg" v-on:click="ignored($event, consulting.patientId)">Ignored, add penal</button></td>

                                                </tr>
   
  </tbody>
</table>



                                                   
                                        
                    
    </div>
<div>
     
    <b-modal ref="my-modal" hide-footer scrollable title="Write complaint" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                      <div class="modal-header">
                                      
                                      <h3>Note consulting</h3>
                                      </div>
                                      <div class="modal-body">
                                      
                                      
                                        <div class="row justify-content-md-center md-4 p-2">

                                          <div class="col-25">
                                          <label style="font-size: 20px;">Your comment about consulting:</label>
                                          </div>
                                          <b class="tab"></b> 
                                          <div class="col-75">
                                          <input type="text" style="font-size: 20px; background:white; height:80px" size="80" placeholder="Enter consulting.." v-model="consulting.information">
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
                                              <label style="font-size: 20px;">Duration of consulting:</label>
                                          </div>
                                            <b class="tab"></b> 
                                          <div class="col-75">
                                            <input type="number" style="font-size: 20px;" size="80" placeholder="Enter duration of consulting.." v-model="consulting.duration">
                                          </div>

                                         </div>
                                         
                                      </div>
                                      <div class="modal-footer">
                                         <button class="btn btn-secondary" v:on:click="hideModal">Close</button>
                                        <button class="btn btn-primary" v-on:click="salji">Confirm consulting</button>
                                      </div>
                              </div>
                      </div>
    </b-modal>
</div>
<div>
    <b-modal ref="my-modal1" hide-footer scrollable title="Write complaint" size="lg" modal-class="b-modal">
                    <div modal-class="modal-dialog" role="document">
                            <div class="modal-content" style="background-color:whitesmoke">
                                      <div class="modal-header">
                                      
                                      <h3>Add prescription</h3>
                                      </div>
                                      <div class="modal-body">
                                      
                                        <div class="container">
                                          <div class="col">
                                            <label style="font-size:20px">Choose medication for patient</label>
                                          </div>
                                          <div class="col">
                                            <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose medication" class = "btn btn-danger btn-lg" style="float:left;margin-left:20px;">
                                            <b-dropdown-item v-for="medication in this.medications"  v-on:click ="patientIsSelected($event, medication)" v-bind:key="medication.id"> {{medication.name}} </b-dropdown-item>
                                            </b-dropdown> 
                                          </div>
                                           <b class="tab"></b> 
                                           <div class=" col">
                                              <button v-on:click = "showMedicationSpecification" class="btn btn-danger btn-lg">Specification of {{medicationName}}</button>
                                           </div>
                                          <div class="col">
                                            <label> </label>
                                          
                                          </div>
                                           <div class="col">
                                            <input type="text" style= "background-color: lightgrey; height:50px; width:250px" v-model="durationOfTherapy" placeholder="Duration of therapy in days">
                                          
                                          </div>
                                          
                                        </div>
                                        
                                      </div>
                                      <div class="modal-footer">
                                         <button class="btn btn-secondary">Close</button>
                                        <button class="btn btn-primary" v-on:click="praviRecept">Create prescription</button>
                                      </div>
                              </div>
                      </div>
    </b-modal>
  </div>
   <div> 
        <b-modal ref="specification-modal" hide-footer scrollable title="Medication specification" size="lg" modal-class="b-modal">
               <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:whitesmoke">
                         <div class="modal-body">
                             <div class="row">
                                <div class=" form-group col">
                                     <label>Contra indications</label> 
                                </div>
                                <div class=" form-group col">  
                                      <label>{{medicationInfo.specification.contraIndications}}</label> 
                                </div>
                             </div>
                            <div class="row">
                                <div class=" form-group col">
                                     <label>Structure</label> 
                                </div>
                                <div class=" form-group col">  
                                      <label>{{medicationInfo.specification.structure}}</label> 
                                </div>
                             </div>
                             <div class="row">
                                <div class=" form-group col">
                                     <label>Recommended consumption</label>
                                </div>
                                <div class=" form-group col">  
                                      <label>{{medicationInfo.specification.recommendedConsumption}}</label> 
                                </div>
                             </div>
                            
                            <div class="row">
                                <div class=" form-group col">
                                    <label>Manufacturer</label>
                                </div>
                                <div class=" form-group col">  
                                      <label>{{medicationInfo.specification.manufacturer}}</label> 
                                </div>
                             </div>                               
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
      medicationName : "",
      consultingId : "",
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
      ourConsultings: "",
      selectedConsulting : {},
      medications : [],
      durationOfTherapy : "",
      medicationList : [],
     
      medication : "",
        medicationInfo : {
            name : "",
            form : "",
            type :"",
            issuanceRegime :"",
            mark : 0,
            specification: {
                    contraIndications :"",
                    structure : "",
                    recommendedConsumption : "",
                    manufacturer : ""
            },
            medicationId : 0,
            code : 0
      },
      

      

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
    this.axios.get('/medication')
      .then(response => {
          this.medications = response.data;
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
      praviRecept: function(){
         this.medicationList.push(this.medication)
         const pres ={
              
               consultingId : this.selectedConsulting.consultingId,
               medicationList : this.medicationList,
               durationOfTherapy : this.durationOfTherapy,
              
            }
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/prescription/add',pres,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Your prescription are successfully updated!");
                        console.log(response.data);
                })
                .catch(response => {
                       alert("Please try later.");
                        console.log(response);
                 });    
      },
      Recept: function(event, consulting) {
            this.selectedConsulting = consulting;  
            this.$refs['my-modal1'].show()

      },
     
      showMedicationSpecification : function() {
          this.axios.get('/medication/searchName/'+this.medicationName).
            then(response => {
                    this.medicationInfo= response.data;
                  
            }).catch(res => {
                        alert("There is no medication with entered name.");
                        console.log(res);
                    });
         this.$refs['specification-modal'].show();
      },
       hideModal() {
        this.$refs['my-modal'].hide()
      },
      Belezi: function(event, consulting) {
            this.selectedConsulting = consulting;  
            this.$refs['my-modal'].show()
          
      },
     ignored: function(event, id) {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        
        
       this.axios.get('/patient/penals/'+ id,{ 

                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then((response) => {
                          console.log(response)
                    alert("Success added penal");
                    }).catch(res => {
                            alert("NOT OK");
                            console.log(res);
                    });

      },
      showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
      logOut : function(){
          window.location.href = "/login";

      },
      salji : function(){
       
        const consult ={
              
               consultingId : this.selectedConsulting.consultingId,
              information : this.consulting.information,
               duration : this.consulting.duration,
              
            }
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/consulting/update',consult,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Your changes are successfully updated!");
                        console.log(response.data);
                })
                .catch(response => {
                       alert("Please try later.");
                        console.log(response);
                 });    

      },
      showMedications : function() {
          window.location.href = "/pharmacistMedicationSearch";
      },
       patientIsSelected : function(event, medication) {
        this.medication = medication;
        this.medicationName = medication.name;
      }

      },
      

  
}

</script>

<style>

</style>