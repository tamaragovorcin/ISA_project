<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            <span style="float: left; margin: 15px;">
                  <a  class = "btn btn-secondary" href = "/supplierProfileInfo">My profile</a>
                  <strong class="tab"></strong>  
                  <a  class = "btn btn-secondary" href = "/supplierReviewsActiveTenders">Active tenders</a>
                  <strong class="tab"></strong>  
                  <a  class = "btn btn-secondary" href = "/myOffers">My offers</a>   
                  <strong class="tab"></strong>  
                  <a  class = "btn btn-secondary" href = "/supplierAddMedication">Add medication</a>   
                  <strong class="tab"></strong>  
                  <a  class = "btn btn-secondary" href = "/supplierMyMedications">My medications</a>   
            </span>
            <span  style="float:right;margin:15px">
                    <strong class="tab"></strong>  
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
                    </div>
                    <div class="form-row">

                        <div class="form-group col">
                            <label>Code:</label>
                            <input type="number" class="form-control" v-model = "code" placeholder="Enter code">
                        </div>
                    </div>
                    <div class="form-row">

                        <div class="form-group col">
                            <label>Quantity:</label>
                            <input type="number" class="form-control" v-model = "quantity" placeholder="Enter quantity">
                        </div>
                    </div>
                    <div class="form-row">
                         <button class="btn btn-primary btn-lg" v-on:click = "add">Add medicine</button>
                    </div>
                    
        </div>
        
    </div>
</template>

<script>
export default {

  data() {
    return {
      name : "",
      code : 0,
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


  