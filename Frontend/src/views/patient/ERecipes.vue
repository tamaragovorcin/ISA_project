 <template>
  <div v-if="isAuthorized" id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
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
                         <div class="row">
                            <div class="form-group col d-flex justify-content-start">
                                <label>Sort by mark:</label> 
                            </div>
                            <div class="custom-control custom-radio form-group col ">

                                <input type="radio" class="custom-control-input" id="defaultGroupExample3" name="groupOfDefaultRadios2" v-on:click="markInreasing">
                                <label class="custom-control-label" for="defaultGroupExample3">Increasing</label>
                                
                            </div> 
                            <div class="custom-control custom-radio form-group col ">

                                <input type="radio" class="custom-control-input" id="defaultGroupExample4" name="groupOfDefaultRadios2" v-on:click="markDecreasing">
                                <label class="custom-control-label" for="defaultGroupExample4">Decreasing</label>
                                
                            </div> 
                        </div>
                    </div>
                    <template>
                        <div class="row">
                            <div class="form-group col d-flex justify-content-start">
                                <label >Sort by price:</label> 
                            </div>
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
                      
                         <div class="form-group col d-flex justify-content-start">
                                <label>Sort by name:</label> 
                            </div>
                            <div class="custom-control custom-radio form-group col ">

                                <input type="radio" class="custom-control-input" id="defaultGroupExample5" name="groupOfDefaultRadios3" v-on:click="nameInreasing">
                                <label class="custom-control-label" for="defaultGroupExample5">Increasing</label>
                                
                            </div> 
                            <div class="custom-control custom-radio form-group col ">

                                <input type="radio" class="custom-control-input" id="defaultGroupExample6" name="groupOfDefaultRadios3" v-on:click="nameDecreasing">
                                <label class="custom-control-label" for="defaultGroupExample6">Decreasing</label>
                                
                            </div> 
                </div>
                <div class="form-row">
                         <div class="form-group col d-flex justify-content-start">
                                <label>Sort by country:</label> 
                            </div>
                            <div class="custom-control custom-radio form-group col ">

                                <input type="radio" class="custom-control-input" id="defaultGroupExample7" name="groupOfDefaultRadios4" v-on:click="countryInreasing">
                                <label class="custom-control-label" for="defaultGroupExample7">Increasing</label>
                                
                            </div> 
                            <div class="custom-control custom-radio form-group col ">

                                <input type="radio" class="custom-control-input" id="defaultGroupExample8" name="groupOfDefaultRadios4" v-on:click="countryDecreasing">
                                <label class="custom-control-label" for="defaultGroupExample8">Decreasing</label>
                                
                            </div> 
                </div>
                <div class="form-row">
                            <div class="form-group col d-flex justify-content-start">
                                <label>Sort by town:</label> 
                            </div>
                            <div class="custom-control custom-radio form-group col ">

                                <input type="radio" class="custom-control-input" id="defaultGroupExample9" name="groupOfDefaultRadios5" v-on:click="townInreasing">
                                <label class="custom-control-label" for="defaultGroupExample9">Increasing</label>
                                
                            </div> 
                            <div class="custom-control custom-radio form-group col ">

                                <input type="radio" class="custom-control-input" id="defaultGroupExample10" name="groupOfDefaultRadios5" v-on:click="townDecreasing">
                                <label class="custom-control-label" for="defaultGroupExample10">Decreasing</label>
                                
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
        pharmacyCountry : "",
        pharmacyTown : "",
        pharmacyListFilter : [],
        isAuthorized : false,
        eReceiptCode : ""
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
            this.pharmacyList = [];
             this.pharmacyListFilter=[];

            this.axios.post( '/erecipes/file', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                     'Authorization': 'Bearer ' + token
                }
              }). then(response => {
                    this.pharmacyList = response.data.pharmacies;
                    this.pharmacyListFilter=response.data.pharmacies;
                    this.medications = response.data.medicationsInQRcode;
                    this.eReceiptCode = response.data.code;
                    if(this.pharmacyList.length===0) {
                        alert("There is no pharmacy that has all mediciations.")
                    }    
                }).catch(res => {
                    alert(res.response.data.message);
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
              code : this.eReceiptCode
            }
          this.axios.post( '/erecipes/choosePharmacy', PharmacyRequest,{
                          headers: {
                              'Authorization': 'Bearer ' + token
                          }
                        }). then(function() {
                            alert("Medications are successfully taken from choosen pharmacy!");
                        }).catch(res => {
                            alert("Please try again later!");
                            console.log(res);
                        });     
      },
    markDecreasing: function() {
        return this.pharmacyListFilter.sort((p1,p2) => {
                    let modifier = -1;
                    if(p1.mark < p2.mark) return -1 * modifier; if(p1.mark > p2.mark) return 1 * modifier;
                    return 0;
                });
    },
     markInreasing : function( ) {
      return this.pharmacyListFilter.sort((p1,p2) => {
                    let modifier = 1;
                    if(p1.mark < p2.mark) return -1 * modifier; if(p1.mark > p2.mark) return 1 * modifier;
                    return 0;
                });
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
     nameInreasing : function() {
        this.pharmacyListFilter =  this.pharmacyListFilter.slice().sort(function(a, b){
                return (a.pharmacyName > b.pharmacyName) ? 1 : -1;
            });
     },
     nameDecreasing : function() {
         this.pharmacyListFilter =  this.pharmacyListFilter.slice().sort(function(a, b){
                return (a.pharmacyName < b.pharmacyName) ? 1 : -1;
            });
     },
     countryInreasing : function() {
        this.pharmacyListFilter =  this.pharmacyListFilter.slice().sort(function(a, b){
                return (a.address.country > b.address.country) ? 1 : -1;
            });
     },
     countryDecreasing : function() {
         this.pharmacyListFilter =  this.pharmacyListFilter.slice().sort(function(a, b){
                return (a.address.country < b.address.country) ? 1 : -1;
            });  
     },
     townInreasing : function() {
         this.pharmacyListFilter =  this.pharmacyListFilter.slice().sort(function(a, b){
                return (a.address.town > b.address.town) ? 1 : -1;
            });
     },
    townDecreasing : function() {
        this.pharmacyListFilter =  this.pharmacyListFilter.slice().sort(function(a, b){
                return (a.address.town < b.address.town) ? 1 : -1;
            });
     },
},
 mounted() {
      let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/patient/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
                this.isAuthorized = true;
                this.patient = response.data;

         }).catch(res => {
                this.isAuthorized=false;
                alert("Please log in first!");
                window.location.href = "/login";
                console.log(res);
        });
 }
}
</script>