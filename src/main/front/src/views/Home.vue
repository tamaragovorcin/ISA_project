<template>
  <div id="home-main">
    <h1>{{test}}</h1>
    <div>{{testdata}}</div>
  </div>
</template>

<script>

export default {
    data() {
        return {
            test: "Hello",
            testdata: "",
        }
    },
    methods: {
      getHospitals: function() {
        this.axios.get("pswapi/apisa1234")
          .then(res => {
            this.testdata = res.data;
          })
          .catch(res => {
            console.log(res);
          })
      }
    },
    mounted(){
        this.getHospitals();
    }
}
</script>
