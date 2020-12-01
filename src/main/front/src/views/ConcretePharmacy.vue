<template>
  <div  id="home">
    <div id="header">
      <div id="header-text">
        <div id="header-title">
          <p>Your pharmacy </p>
           
          <p>{{p.name}} </p>

          <button  v-on:click = "getReport">Get file report</button>
          <label>{{report}}</label>
          <p> </p>
          <p>Add new action and benefit</p>
          <div>
              <label for="benefitText">Text:</label>
          </div>
          <div>
            <input type="text" id="benefitText" name="benefitText" v-model="benefitText" placeholder="New action and benefit..">
          </div>
          <div>
            <label for="benefitText">Text:</label>
          </div>
          <div>
            <input type="text" id="benefitDate" name="benefitDate" v-model="benefitDate" placeholder="day/month/year">
          </div>
          <div>
                <button class="button" v-on:click="send">Send</button>
          </div>
          <div class="row">
                    <label v-if="unique" style="color:lightgreen;font-size:25px;">Successfully added!</label>
                    <label v-if="notUnique" style="color:red;font-size:25px;">Problem with sending.!</label>
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
        id : this.$route.params.id,
        p: {
         name : "",
         town : "",
         apiKey: ""

       },
        report : "",
        benefitText: "",
        benefitDate : "",
        unique: false,
        notUnique: false,
    }
  },

beforeMount() {
      this.axios.get('/pharmacy/'+this.id)
      .then(response => {
            this.p= response.data;

      })

},
     
methods:{
  showPharmacy : function(event, p){
    this.pharmacy = p;
  },
  getReport: function () {
    this.axios.get('/download/file/report/'+this.id)
          .then(res => {
            this.report = res.data;
          })
          .catch(res => {
            console.log(res);
          })
  },
  send: function () {
                const message = {
                    text: this.p.name + ":   " + this.benefitText,
                    dateAction : this.benefitDate,
                };
                this.axios.post('/messageRabbit/', message)
                    .then(res => {
                        this.unique = true;
                        this.notUnique = false;
                        console.log(res);
                    })
                    .catch(res => {
                       this.notUnique = true;
                        this.unique = false;
                        console.log(res);
                    })
            }

}




}
</script>
<style scoped>


</style>