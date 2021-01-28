 <div class="form-group col-md-6">
                        <label>Country:</label>
                        <input type="text" class="form-control" v-model="supplierInfo.address.country" placeholder="Enter country">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Town:</label>
                        <input type="text" class="form-control" v-model="supplierInfo.address.town" placeholder="Enter town">
                        </div>
                    </div>
                     <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Street:</label>
                        <input type="text" class="form-control" v-model="supplierInfo.address.street" placeholder="Enter street">
                        </div>
                        <div class="form-group col-md-6">
                        <label>Number:</label>
                        <input type="number" class="form-control" v-model="supplierInfo.address.number" placeholder="Enter number">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Postal code:</label>
                        <input type="text" class="form-control" v-model="supplierInfo.address.postalCode" placeholder="Enter postal code">
                        </div>
                       
                    </div>
                    
                   
                    <button class="btn btn-primary btn-lg" v-on:click = "changeInformation">Confirm</button>
                    <div style="height:30px;"></div>
        </div>
    </div>
</template>

<script>
export default {
  data() {
    return {
      supplierInfo: {
                email : "",
                password : "",
                firstname : "",
                surname : "",
                phonenumber : "",
                address : {
                    town : "",
                    street : "",
                    number : "",
                    postalCode : "",
                    country : ""
                }
                },
    }
  },
  methods:{
      logOut : function(){
           localStorage.removeItem('token');
           window.location.href = "/login";
      },
      showMyProfile : function() {
          window.location.href = "/dermatologistProfile";
      },
      changeInformation : function() {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/dermatologist/update',this.supplierInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Profile is successfully changed!!");
                        console.log(response.data);
                })
                .catch(response => {
                       alert("Please try later.");
                        console.log(response);
                 });    
      }
      
},
mounted() {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/dermatologist/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
                this.supplierInfo = response.data;
                console.log(response.data);
         }).catch(res => {
                alert("NOT OK");
                console.log(res);
        });
    }
}
</script>

<style>
</style>
