<template>
<div  v-if="loggedIn"  id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 175%;  height: 2500px">
        <div style="background: #0D184F; height: 90px;">
           <span style="float: left; margin-top: 15px;">
                              <b-dropdown id="ddCommodity" name="ddCommodity" text="My profile" 
                                              class = "btn btn-link btn-lg">
                                    <b-dropdown-item href = "/pharmacyAdminProfile">Profile</b-dropdown-item>
                                    <b-dropdown-item href = "/phAdminProfileUpdate">Update profile</b-dropdown-item>      
                                </b-dropdown>        

                                    <router-link :to="{ path: '/pharmacyProfile/'+pharmacy.id}" v-slot="{href, navigate}">
                                                <button class = "btn btn-link" :href="href" @click="navigate"  elevation="1">My pharmacy profile</button>
                                    </router-link>
                   

                                  <b-dropdown id="ddCommodity" name="ddCommodity" text="Pharmacists" 
                                                class = "btn btn-link btn-lg">
                                      <b-dropdown-item href = "/pharmacyPharmacists">Our pharmacists</b-dropdown-item>
                                      <b-dropdown-item href = "/addPharmacist">Add new pharmacist</b-dropdown-item>      
                                  </b-dropdown> 
                                  <b-dropdown id="ddCommodity" name="ddCommodity" text="Dermatologists" 
                                                  class = "btn btn-link btn-lg">
                                        <b-dropdown-item href = "/pharmacyDermatologists">Our dermatologists(Add new)</b-dropdown-item>
                                        <b-dropdown-item href = "/examinationTerms">Examination terms</b-dropdown-item>      
                                    </b-dropdown>                 
                            <a   class = "btn btn-link" href = "/pharmacyMedications">Medications</a>
                            <a   class = "btn btn-link" href = "/pharmacyAdminMedicationSearch">Medications in system</a>
                            <a  class = "btn btn-link" href = "/actionsAndBenefits">Actions and benefits</a>
                            <b-dropdown id="ddCommodity" name="ddCommodity" text="Orders" 
                                                  class = "btn btn-link btn-lg">
                                        <b-dropdown-item href = "/order">Preview orders and offers(Add new)</b-dropdown-item>
                                        <b-dropdown-item href = "/editOrder">Edit/remove order</b-dropdown-item>      
                                    </b-dropdown>                             
                            <a   class = "btn btn-link" href="/holidayRequests">Holiday/absence requests</a>
                            <b-dropdown id="ddCommodity" name="ddCommodity" text="Graphical reviews" 
                                                  class = "btn btn-link btn-lg">
                                        <b-dropdown-item href = "/examinationGraphics">Examinations</b-dropdown-item>
                                        <b-dropdown-item href = "/medicationGraphics">Medication consumption</b-dropdown-item> 
                                        <b-dropdown-item href = "/incomeGraphics">Income</b-dropdown-item>      
                                    </b-dropdown>     
                             <a   class = "btn btn-link" href="/medicationInquires">Inquires for medication</a>

            </span>
              <span  style="float:right;margin:15px">
                   
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
                </span>
        </div>
  <section class="container">
    <div class="columns">
     
      <div class="column">
    <h3 style="color: #0D184F">Yearly review of examinations</h3>
   <chartjs-line :labels="labels" :data="dataset" :bind="true"></chartjs-line>
       <h3 style="color: #0D184F">Monthly review of examinations</h3>
   <chartjs-line :labels="labels1" :data="dataset1" :bind="true"></chartjs-line>
       <h3 style="color: #0D184F">Qurterly review of examinations</h3>

  <chartjs-line :labels="labels2" :data="dataset2" :bind="true"></chartjs-line>

      </div>
    </div>
  </section>
  </div>
</template>

<script>
  export default {
    name: 'VueCharts',
    data () {
      return {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July','August','September','October','November','December'],
        dataset: [],
        dataentry : "",
        datalabel : "",
        year : {},
        labels1 : [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31],
        dataset1 : [],
        labels2 : ["First quarter","Second quarter","Third quarter"],
        dataset2 : [],
        pharmacy : {},
        admin : {},
        loggedIn : false
      }
    },
    methods: {
  addData () {
    this.dataset.push(this.dataentry)
    this.labels.push(this.datalabel)
    this.datalabel = ''
    this.dataentry = ''
  },
     logOut : function(){
           window.location.href = "/login";
      },
    },
   mounted() {
     let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/pharmacyAdmin/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.admin=response.data;
               this.loggedIn = true;
        
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });
        this.axios.get('/pharmacyAdmin/myPharmacy',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.pharmacy = response.data;
                            console.log(this.pharmacyAdminPharmacy);
                    }).catch(res => {
                            alert("NOT OK");
                            console.log(res);
                    });
    
        this.axios.get('/graphical/examinationYearly',{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
          }}).then(response => {
           var january = response.data.january;
           this.dataset.push(january);

           var february = response.data.february;
           this.dataset.push(february);

           var march = response.data.march;
           this.dataset.push(march);

           var april = response.data.april;
           this.dataset.push(april);

           var may = response.data.may;
           this.dataset.push(may);

           var june = response.data.june;
           this.dataset.push(june);

           var july = response.data.july;
           this.dataset.push(july);

           var august = response.data.august;
           this.dataset.push(august);

           var september = response.data.september;
           this.dataset.push(september);

           var october = response.data.october;
           this.dataset.push(october);

           var november = response.data.november;
           this.dataset.push(november);

           var december = response.data.december;
           this.dataset.push(december);

           
          
         
         }).catch(res => {
                alert("You are not logged in.");
                console.log(res);
        });

        this.axios.get('/graphical/examinationMonthly',{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
           var one = response.data.one;
           this.dataset1.push(one);

           var two = response.data.two;
           this.dataset1.push(two);

           var three = response.data.three;
           this.dataset1.push(three);

           var four = response.data.four;
           this.dataset1.push(four);

           var five = response.data.five;
           this.dataset1.push(five);

           var six = response.data.six;
           this.dataset1.push(six);

           var seven = response.data.seven;
           this.dataset1.push(seven);

           var eight = response.data.eight;
           this.dataset1.push(eight);

           var nine = response.data.nine;
           this.dataset1.push(nine);

           var ten = response.data.ten;
           this.dataset1.push(ten);

           var eleven = response.data.eleven;
           this.dataset1.push(eleven);

           var twelve = response.data.twelve;
           this.dataset1.push(twelve);

           var thirteen = response.data.thirteen;
           this.dataset1.push(thirteen);

           var fourteen = response.data.fourteen;
           this.dataset1.push(fourteen);

           var fifteen = response.data.fifteen;
           this.dataset1.push(fifteen);

           var sixteen = response.data.sixteen;
           this.dataset1.push(sixteen);

           var seventeen = response.data.seventeen;
           this.dataset1.push(seventeen);

           var eighteen = response.data.eighteen;
           this.dataset1.push(eighteen);

           var nineteen = response.data.nineteen;
           this.dataset1.push(nineteen);

           var twenty = response.data.twenty;
           this.dataset1.push(twenty);

           var twentyOne = response.data.twentyOne;
           this.dataset1.push(twentyOne);

           var twentyTwo = response.data.twentyTwo;
           this.dataset1.push(twentyTwo);

           var twentyThree = response.data.twentyThree;
           this.dataset1.push(twentyThree);

           var twentyFour = response.data.twentyFour;
           this.dataset1.push(twentyFour);

           var twentyFive = response.data.twentyFive;
           this.dataset1.push(twentyFive);

           var twentySix = response.data.twentySix;
           this.dataset1.push(twentySix);

           var twentySeven = response.data.twentySeven;
           this.dataset1.push(twentySeven);

           var twentyEight = response.data.twentyEight;
           this.dataset1.push(twentyEight);

           var twentyNine = response.data.twentyNine;
           this.dataset1.push(twentyNine);

           var thirty = response.data.thirty;
           this.dataset1.push(thirty);

           var thirtyOne = response.data.thirtyOne;
           this.dataset1.push(thirtyOne);
         
         }).catch(res => {
                alert("You are not logged in.");
                console.log(res);
        });

         this.axios.get('/graphical/examinationQuarter',{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
           var first = response.data.first;
           this.dataset2.push(first);

           var second = response.data.second;
           this.dataset2.push(second);

           var third = response.data.third;
           this.dataset2.push(third);

           var fourth = response.data.fourth;
           this.dataset2.push(fourth);
       
         }).catch(res => {
                alert("You are not logged in.");
                console.log(res);
        });

        
    },

  }
</script>