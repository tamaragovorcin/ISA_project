


<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">

        <div style=" height: 6.5cm;"/>

        <div> <h3 align = "center" style="font-size: 35px;font-weight:bold; color: #0D184F" > Pharmaceutical information system</h3></div>

        <div style="background: #0D184F; height: 3cm;">
            
            <span style="float: right; margin-top: 0.5cm; margin-right: 14.5cm;">
                <router-link to="/pswregistration" v-slot="{href, navigate}">

                    <button class = "button1" :href="href" @click="navigate"  elevation="1">Get your API key</button>

                    </router-link>
                    <b class="tab"></b>                
                    <router-link to="/pharmacyVue" v-slot="{href, navigate}">

                    <button class = "button1" :href="href" @click="navigate"  elevation="1">Pharmacy</button>

                    </router-link>
                    <button style="margin-left:15px" class="button1" v-on:click="prikazi">Prikazi</button>
            </span>
                                    

        </div>


    </div>
</template>

<script>
export default {

  data() {
    return {
    }
  },

  methods:{
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

<style>

    .button1 {
        background: #939393;
        color: #ffffff;
        border: 8px;
        border-radius: 10%;
        font-size: 18px;
        height: 1.5cm;
        width: 200px;
        font: bold;}

    .tab {
         margin-left: 1cm
        }

</style>


  