<template>
  <div style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
    background-size: 125% 80%;  height: 1500px">

      <ul>
         <li><a style="color:white; font-weight:bold;text-decoration: underline;font-size:22px" v-on:click="showReportForm">Get report about medicine consumtion</a></li>
         <li><a style="color:white; font-weight:bold;text-decoration: underline;font-size:22px" v-on:click="showFormForActionsAndBenefits">Share actions and benefits with hospital</a></li>
          <li><a style="color:white; font-weight:bold;text-decoration: underline;font-size:22px" v-on:click="showUrgentOrders">Urgent orders</a></li>
      </ul>

       <div>
          <h1 style="color: #0D184F; font-size: 35px;font-weight:bold;margin:auto;width:50%;padding:10px">{{p.name}}</h1>
      </div>

      <div v-if="showForm">
            <h3>Describe your offer and send it to hospital </h3>
            <label for="fname">Describe action or benefit for hospital:</label>
            <input type="text" id="fname"  name="benefitText" v-model="benefitText" placeholder="New action or benefit..">
            <label for="lname">Expiring date:</label>
            <input type="text" id="benefitDate" name="benefitDate" v-model="benefitDate" placeholder="day/month/year">
            <button class = "button" v-on:click="send">Send</button>
      </div>
 <div v-if="showOrders">


  <table style=" border-radius: 5%; border:1;  align: center; width: 25cm; background: #FFFFFF; margin: 0;position: absolute;top: 50%;left: 50%;transform: translate(-50%, -50%);">
          <th style="width: 40px">
                <div class="form-group"><label style="font-size: 30px; font-weight:bold"><span>Medicine name</span></label></div>
          </th>
          <th style="width: 40px">
                <div class="form-group"><label style="font-size: 30px; font-weight:bold"><span>Quantity</span></label></div>
          </th >
          <th style="width: 40px">
                <div class="form-group"><label style="font-size: 30px; font-weight:bold"><span>Date of order</span></label></div>
          </th>

             <tr v-for="order in orders" :key="order.id">
                    <td><div class="form-group w-75"> <label style="font-size: 20px"><span>{{order.name}}</span></label></div> </td>
                    <td><div class="form-group w-75"> <label style="font-size: 20px"><span>{{order.quantity}}</span></label></div> </td>
                    <td><div class="form-group w-75"> <label style="font-size: 20px"><span>{{order.dateOrder}}</span></label></div> </td>
             </tr>

 </table>
  </div>
  <div  v-if="showReport">
      <div>
                                  <button style="margin-left:15px" class="button1" v-on:click="prikazi">Prikazi</button>

           <h3>Report about medicine consumption in hospital </h3>
           <button class = "button2" v-on:click="getReport">Get report</button>
          <div style="background:white;display: flex;justify-content: center;align-items: center;height: 200px;color: #0D184F;border: 3px solid black;">
              {{report}}
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
        showForm : false,
        showReport : false,
        showOrders : false,
        orders: [],
    }
  },

mounted() {
      this.axios.get('/pharmacy/'+this.id)
          .then(response => {
                this.p= response.data;
          });
     
},
     
methods:{
  showPharmacy : function(event, p){
    this.pharmacy = p;
  },
  getReport: function () {
    this.axios.get('/download/file/report/'+this.id)
          .then(res => {
            alert("Moze");
            this.report = res.data;
          })
          .catch(res => {
            alert("Ne moze");
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
            },
  showFormForActionsAndBenefits: function(){
      this.showForm = true;
      this.showReport = false;
      this.showOrders = false;
  },
  showReportForm :function(){
        this.showReport = true;
        this.showForm = false;
        this.showOrders = false;
  },
  showUrgentOrders :  function(){
       this.axios.get('/order/'+this.id)
                .then(response => {
                    alert(response);
                    this.orders= response.data;
                    console.log(response.data)
                })
                .catch(response => {
                  alert(response);
                  console.log(response)
            })
      this.showOrders = true;
      this.showForm = false;
      this.showReport = false;

  },
   prikazi : function(){
     this.axios.get('/probni')
                .then(response => {
                   alert("PROBNI");
                   console.log(response);
                })
                .catch(response => {
                       alert("Nije prosao probni");
                        console.log(response);
                 });
  }

}




}
</script>
<style scoped>
  #mainbtn {
    color: #fff;
    background-color: #0000CD
  }

 
  #title {
    font-weight: 500;
    font-size: 3.5rem;
    color: #fff;
  }
 
 .klasa{
    position: absolute;
    top: 50%;
    left: 50%;
    -moz-transform: translateX(-50%) translateY(-50%);
    -webkit-transform: translateX(-50%) translateY(-50%);
    transform: translateX(-50%) translateY(-50%);
 }
  #header-desc {
    font-size: 1.5rem;
    color: #fff;
  }
  #paragraph{
    height: 10vh;
  }
  .button2 {
        background-color: #19b65f;
        border: double;
        color: white;
        padding: 15px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 25px;
        margin: 4px 2px;
        cursor: pointer;
        color : white;
        width: 300px;
        border: 8px;
         border: 8px;
        border-radius: 10%;
    }
  
  .button {
        background-color: #0D184F;
        border: double;
        color: white;
        padding: 15px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 25px;
        margin: 4px 2px;
        cursor: pointer;
        color : white;
        width: 250px;
        border: 8px;
        border-radius: 10%;
    }
  .button1 {
        color: #ffffff;
        border: 8px;
        border-radius: 10%;
        font-size: 22px;
        width: 250px;
        font: bold;
        text-decoration: underline;
        }
  ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #0D184F;
}

li {
  float: left;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover:not(.active) {
  background-color: rgb(132, 204, 223);
}

.active {
  background-color: #334de4;
}
input[type=text], select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=submit] {
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

div {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>

<style>
  .tab {
          margin-left: 2cm
          }
</style>