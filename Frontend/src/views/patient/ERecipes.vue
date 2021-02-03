ines (39 sloc)  1.61 KB
  
<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "appointmentsAndConsultings">My appointments and consultings</button>
                    <strong class="tab"></strong>          
                   <button class = "btn btn-link btn-lg" v-on:click = "eRecipes">eRecipes</button>
                    <strong class="tab"></strong>          
                    <button class = "btn btn-link btn-lg" style="margin-right:20px;" v-on:click = "medicationReservation">Medication reservation</button>
                    <strong class="tab"></strong>          
                    <button class = "btn btn-link btn-lg" style="margin-right:20px;" v-on:click = "penals">Penals</button>
                    <strong class="tab"></strong>          

                    <button class = "btn btn-link btn-lg" style="margin-right:20px;" v-on:click = "changeMyProfile">Change my profile</button>
                    <strong class="tab"></strong>          
            </span>
            <span  style="float:right;margin:10px">
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
            </span>
        </div>
    <div class="container">
         <div class="row">
 
            <div class = "col-4 align-top container-fluid align-left">
                <h3 style="color: #0D184F;margin-bottom:20px">Filters</h3>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label>Average mark around:</label> 
                        </div>
                        <div class="form-group col-md-8">
                            <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose pharmacy mark" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                                <b-dropdown-item v-for="item in this.marks"  v-on:click ="markIsSelected($event, item.mark)" v-bind:key="item.mark"> {{item.mark }}</b-dropdown-item>
                            </b-dropdown> 
                        </div>
                    </div>
                    <template>
                        <div class="row">
                            <label>Sort price:</label> 
                    
                            <div class="custom-control custom-radio form-group col ">

                                <input type="radio" class="custom-control-input" id="defaultGroupExample1" name="groupOfDefaultRadios" v-on:click="priceInreasing">
                                <label class="custom-control-label" for="defaultGroupExample1">Increasing</label>
                                
                            </div> 
                            <div class="custom-control custom-radio form-group col ">

                                <input type="radio" class="custom-control-input" id="defaultGroupExample2" name="groupOfDefaultRadios" v-on:click="priceDecreasing">
                                <label class="custom-control-label" for="defaultGroupExample2">Decreasing</label>
                                
                            </div> 
                        </div>
                    
                     </template>
                <div class="form-row">
                        <div class="form-group col">
                                <label class="custom-control-label" for="defaultGroupExample2">Pharmacy name</label>
                        </div>
                        <div class="form-group col">
                            <input type="text" class="form-control" v-model="pharmacyName" placeholder="Enter name...">
                        </div>
                        <div class="form-group col">
                            <button v-on:click = "showPharmaciesByName" class="btn btn-primary">Confirm</button>
                        </div>
                </div>
                <div class="form-row">
                          <div class="form-group col">
                                <label class="custom-control-label" for="defaultGroupExample2">Pharmacy country</label>
                        </div>
                        <div class="form-group col">
                            <input type="text" class="form-control" v-model="pharmacyCountry" placeholder="Enter country...">
                        </div>
                        <div class="form-group col">
                            <button v-on:click = "showPharmaciesByCountry" class="btn btn-primary">Confirm</button>
                        </div>
                </div>
                <div class="form-row">
                          <div class="form-group col">
                                <label class="custom-control-label" for="defaultGroupExample2">Pharmacy town</label>
                        </div>
                        <div class="form-group col">
                            <input type="text" class="form-control" v-model="pharmacyTown" placeholder="Enter town...">
                        </div>
                        <div class="form-group col">
                            <button v-on:click = "showPharmaciesByCity" class="btn btn-primary">Confirm</button>
                        </div>
                </div>
            </div>
                    
            
       
            <div class = "col-8">
                <div style = "background-color:lightgray; margin: auto; border: 3px solid #0D184F;padding: 10px;">
                            <h3 style="color: #0D184F;margin-bottom:20px">Upload QR code</h3>
                        <div class="form-group">
                            <div class="row">
                                
                                    <div class="col">
                                        <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
                                    </div> 
                                    <div class="col">
                                        <button  class="btn btn-primary" v-on:click="submitFile()">Check availability in pharmacies</button>
                                    </div>
                            </div>
                        </div>
                </div>

                <div v-for="pharmacy in pharmacyListFilter"   v-bind:key="pharmacy.pharmacyId">

                    <div style = "background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
                                
                        <div class="form-group">
                            <div class="row">
                                <div class="col">
                                    <label>Pharmacy {{pharmacy.pharmacyName}}</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <label>Pharmacy average mark  {{pharmacy.mark}}</label>
                                </div>
                            </div>   
                            <div class="row">
                                <div class="col">
                                    <label>Price {{pharmacy.sumPrice}}</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <label>Address  {{pharmacy.address.country}}, {{pharmacy.address.town}}, {{pharmacy.address.street}} {{pharmacy.address.number}}</label>
                                </div>
                            </div>
                            <div class="row d-flex align-items-end">
                                <div class="col">
                                    <button  class="btn btn-primary" v-on:click="choosePharmacy($event,pharmacy.pharmacyId)">Choose this pharmacy</button>
                                </div>
                            </div>
                        </div>
                    </div>
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
       pharmacyList : [],
       formData: null,
       urlImage: [],
       lista : [],
       file: '',
       medications : [],
       pharmacyName : "",
        marks: [
          { mark: "0-1" },
          { mark: "1-2" },
          { mark: "2-3" },
          { mark: "3-4" },
          { mark: "4-5" },
      ],
        pharmacyCountry : "",
        pharmacyTown : "",
        pharmacyListFilter : []

    }
  },

  methods:{
     
      appointmentsAndConsultings: function(){
      },
      eRecipes : function(){
      },
      logOut : function(){
          window.location.href = "/login";

      },
      medicationReservation : function(){
             window.location.href = "/medicationReservation";
      },
       changeMyProfile : function(){
             window.location.href = "/updateProfilePatient";

      },
      penals : function(){},
      submitFile(){
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

            let formData = new FormData();

            formData.append('file', this.file);

            this.axios.post( '/erecipes/file', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                     'Authorization': 'Bearer ' + token
                }
              }). then(response => {
                    this.medications = response.data;
                    this.axios.post( '/erecipes/availability', this.medications,{
                          headers: {
                              'Authorization': 'Bearer ' + token
                          }
                        }). then(response => {
                            this.pharmacyList = response.data;
                            this.pharmacyListFilter=response.data;
                            if(this.pharmacyList.length===0) {
                                alert("There is no pharmacy that has all mediciations.")
                            }
                        }).catch(res => {
                            alert("Please try again later!");
                            console.log(res);
                        });     

                }).catch(res => {
                     alert("Please upload correct QR code!");
                    console.log(res);
                });     
      },

      
      handleFileUpload(){
        this.file = this.$refs.file.files[0];
      },
      choosePharmacy($event,pharmacy) { 
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

            const PharmacyRequest = {
              pharmacyId : pharmacy,
              medications : this.medications,
            }
          this.axios.post( '/erecipes/choosePharmacy', PharmacyRequest,{
                          headers: {
                              'Authorization': 'Bearer ' + token
                          }
                        }). then(function() {
                            alert("Medications are successfully reserved in choosen pharmacy!");
                        }).catch(res => {
                            alert("Please try again later!");
                            console.log(res);
                        });     
      },
       markIsSelected : function(event, mark) { 
            var marks = mark.split('-')
            var MarkMin = parseInt(marks[0])
            var MarkMax = parseInt(marks[1])
            let i =0;
            this.pharmacyListFilter=[]
            for(i=0; i< this.pharmacyList.length;i++) {
                if(this.pharmacyList[i].mark>=MarkMin && this.pharmacyList[i].mark<=MarkMax) {
                    this.pharmacyListFilter.push(this.pharmacyList[i])
                }
            }
     },
     priceDecreasing : function() {
             return this.pharmacyListFilter.sort((p1,p2) => {
                    let modifier = -1;
                    if(p1.sumPrice < p2.sumPrice) return -1 * modifier; if(p1.sumPrice > p2.sumPrice) return 1 * modifier;
                    return 0;
                });
     },
     priceInreasing : function() {
         return this.pharmacyListFilter.sort((p1,p2) => {
                    let modifier = 1;
                    if(p1.sumPrice < p2.sumPrice) return -1 * modifier; if(p1.sumPrice > p2.sumPrice) return 1 * modifier;
                    return 0;
                });
     },
     showPharmaciesByName : function() {
            this.pharmacyListFilter=[]
            var name = this.pharmacyName;
            var i =0;
            for(i=0; i< this.pharmacyList.length;i++) {
                if(this.pharmacyList[i].pharmacyName.startsWith(name)) {
                    this.pharmacyListFilter.push(this.pharmacyList[i])
                }
            }
     },
     showPharmaciesByCity : function() {
          this.pharmacyListFilter=[]
            var town = this.pharmacyTown;
            var i =0;
            for(i=0; i< this.pharmacyList.length;i++) {
                if(this.pharmacyList[i].address.town.startsWith(town)) {
                    this.pharmacyListFilter.push(this.pharmacyList[i])
                }
            }
     },
     showPharmaciesByCountry : function(){
          this.pharmacyListFilter=[]
            var country = this.pharmacyCountry;
            var i =0;
            for(i=0; i< this.pharmacyList.length;i++) {
                if(this.pharmacyList[i].address.country.startsWith(country)) {
                    this.pharmacyListFilter.push(this.pharmacyList[i])
                }
            }
     }
}
}
</script>