<template>
 <div style="background-image: url(https://previews.123rf.com/images/mongstock/mongstock1712/mongstock171200069/91420426-colorful-medicine-pharmacy-on-pink-background-.jpg);background-repeat: no-repeat;
  background-size: 125% 80%;  height: 800px">
        <div>
            <h3 style="font-size: 35px;font-weight:bold">Your pharmacy   {{p.name}} </h3>
        </div>
        <div class="container">

            <div class="row">
                <div class="col-50">
                   <button class = "btn btn-danger" v-on:click="getReport">Get file report</button>
                </div>
                <div class="col-50">
                     <label>{{report}}</label>
                </div>
            </div>
            <div class="row">
                <div class="col-30">
                    <p>Add new action and benefit</p>
                </div>
                <div class="col-30">
                     <input type="text" id="benefitText" name="benefitText" v-model="benefitText" placeholder="New action and benefit..">
                </div>
                <div class="col-30">
                     <input type="text" id="benefitDate" name="benefitDate" v-model="benefitDate" placeholder="day/month/year">
                </div>
            </div>
            <div class="row">
                <div class>
                   <button class = "btn btn-danger" v-on:click="send">Send</button>
                </div>

            </div>
            <div class="row">
               <label v-if="unique" style="color:lightgreen;font-size:25px;">Successfully added!</label>
                    <label v-if="notUnique" style="color:red;font-size:25px;">Problem with sending.!</label>
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