<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            <span style="float: left; margin: 15px;">
                  <a  class = "btn btn-secondary" href = "/supplierProfileInfo">My profile</a>
                  <b class="tab"></b>
                  <a  class = "btn btn-secondary" href = "/supplierReviewsActiveTenders">Active tenders</a>
                  <b class="tab"></b>
                  <a  class = "btn btn-secondary" href = "/myOffers">My offers</a> 
                  <b class="tab"></b>
                  <a  class = "btn btn-secondary" href = "/supplierAddMedication">Add medication</a>   
                  <b class="tab"></b>
                  <a  class = "btn btn-secondary" href = "/supplierMyMedications">My medications</a>   
            </span>
            <span  style="float:right;margin:15px">
                     <b class="tab"></b>    
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
            </span>
        </div>
       <div style="background-color:lightgray; margin: auto; width: 50%;border: 3px solid #0D184F;padding: 10px;margin-top:45px;">
            <h3 style="color: #0D184F">Add new medication</h3>
                
                    <div class="form-row">
                        <div class="form-group col">
                            <label>Name:</label>
                            <input type="text" class="form-control" v-model="name" placeholder="Enter name">
                        </div>
                        <div class="form-group col">
                            <label>Code:</label>
                            <input type="number" class="form-control" v-model = "code" placeholder="Enter code">
                        </div>
                        <div class="form-group col">
                            <label>Quantity:</label>
                            <input type="number" class="form-control" v-model = "quantity" placeholder="Enter quantity">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col d-flex justify-content-center">
                        <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose medication form" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                              <b-dropdown-item v-for="item in this.forms"  v-on:click ="formIsSelected($event, item.form)" v-bind:key="item.form"> {{item.form }}</b-dropdown-item>
                        </b-dropdown> 
                        </div>
                        <div class="form-group col d-flex justify-content-center">
                            <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose medication type" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;">
                                <b-dropdown-item v-for="item in this.types"  v-on:click ="typeIsSelected($event, item.type)" v-bind:key="item.type"> {{item.type }}</b-dropdown-item>
                            </b-dropdown> 
                        </div>
                        <div class="form-group col d-flex justify-content-center">
                            <button class="btn btn-primary" v-on:click = "showSpecificationModal">Click to add specification</button>
                        </div>
                    </div>
                    
                    <button class="btn btn-primary btn-lg" v-on:click = "add">Add medicine</button>
                    <div style="height:30px;"></div>
        </div>
        <div> 
          <b-modal ref="specification-modal" hide-footer scrollable title="Fill medicine specification" size="lg" modal-class="b-modal">
               <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:whitesmoke">
                         <div class="modal-body">
                         <div class="form-group">
                              <label>Define contra indications:</label>
                              <input type="text" class="form-control" v-model="specification.contraIndications" placeholder="Enter contra indications">
                         </div>
                         <div class="form-group">
                              <label>Define structure:</label>
                              <input type="text" class="form-control" v-model="specification.structure" placeholder="Enter structure">
                         </div>
                         <div class="form-group">
                              <label>Define recommended consumption:</label>
                              <input type="text" class="form-control" v-model="specification.recommendedConsumption" placeholder="Enter recommended consumption">
                         </div>
                              <div class="form-group">
                              <label>Manufacturer:</label>
                              <input type="text" class="form-control" v-model="specification.manufacturer" placeholder="Enter manufacturer">
                         </div>
                         <button v-on:click = "confirmSpecification" class="btn btn-primary">Confirm</button>        
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
       forms: [
          { form: 'Capsule' },
          { form: 'Powder' },
          { form: 'Gel' },
          { form: 'Tablet' },
          { form: 'Pill' },
          { form: 'Syrup' },
      ],
      types: [
          { type: 'Antibiotik' },
          { type: 'Anesthetic' },
          { type: 'Antihistamine' },
          { type: 'TaAntidepressantblet' },
          { type: 'For dementia' },
          { type: 'For addiction' },
      ],
      name : "",
      code : 0,
      specification : {
           contraIndications : "",
           structure : "",
           recommendedConsumption : "", 
           manufacturer : ""
      },
      choosenType : "",
      choosenForm : "",
      quantity : 0
    }
  },

  methods:{
      logOut : function(){
           localStorage.removeItem('token');
           window.location.href = "/login";
      },
      typeIsSelected : function(event, type) { 
           this.choosenType = type;
      },
      formIsSelected : function(event, form) { 
           this.choosenForm = form;
      },
      showSpecificationModal : function() {
          this.$refs['specification-modal'].show();
      },
      confirmSpecification : function() {
        this.$refs['specification-modal'].hide()
      },
       add : function() {
          const medicationInfo = {
               name : this.name,
               code : this.code,
               form : this.choosenForm,
               type : this.choosenType,
               specification : this.specification,
               quantity : this.quantity
          }
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

          this.axios.post('/supplierMedications/add',medicationInfo,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }}).then(response => {
                       alert("Medicine is added!");
                        console.log(response.data);
                })
                .catch(response => {
                       alert("Please try later");
                        console.log(response);
                 });    

      },
      
      
},
 mounted() {
        
    }
}
</script>

<style>

</style>


  