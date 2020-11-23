<template>
  <div id="home-main">
    <h1>Register in our pharmacy</h1>
     
          <button  v-on:click = "getApiKey">Get api key</button>
          <label>{{apiKey}}</label>
      
  </div>
</template>

<script>

export default {
    data() {
        return {
            apiKey : ""
        }
    },
    methods: {
      getHospitals: function() {
        this.axios.get("pswapi/apila1234")
          .then(res => {
            this.testdata = res.data;
          })
          .catch(res => {
            console.log(res);
          })
      },
      getApiKey: function () {
        this.apiKey = "apila1234";
      }
    },
   //mounted(){
      //  this.getHospitals();
//}
}
</script>
