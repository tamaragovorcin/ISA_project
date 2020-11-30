<template>
  <div id="home">
    <div id="header">
      <div id="header-text">
        <div id="header-text2">
            <p id="header-desc">Pharmaceutical information system</p>
        </div>
        <div id="header-text2">

            <router-link to="/pswregistration" v-slot="{href, navigate}">
              
              <v-btn  class="btn btn-success" :href="href" @click="navigate"  elevation="1">Get your API key</v-btn>
            </router-link>
            <router-link to="/pharmacyVue" v-slot="{href, navigate}">
              <v-btn :href="href" @click="navigate"  class="btn btn-success" elevation="1">Pharmacy</v-btn>
            </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {

  data() {
    return {
    }
  }

}
</script>

<style scoped>

  
</style>