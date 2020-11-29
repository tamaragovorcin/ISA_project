<template>
  <div id="home">
    
    <main-navigation />
    <div id="header">
      <div id="header-text">
        <div id="header-title">
          <p id="header-desc">Pharmaceutical information system</p>
          <router-link to="/pswregistration" v-slot="{href, navigate}">
            <v-btn :href="href" @click="navigate" id="mainbtn" elevation="1">Get your API key</v-btn>
          </router-link>
           <router-link to="/pharmacy" v-slot="{href, navigate}">
            <v-btn :href="href" @click="navigate" id="mainbtn" elevation="1">Pharmacy</v-btn>
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
  #mainbtn {
    color: #fff;
    background-color: #0000CD
  }

  #header-text {
    display: flex;
    flex-direction: column;
    justify-content: center;
  }
  #title {
    font-weight: 500;
    font-size: 3.5rem;
    color: #fff;
  }
  #header-desc {
    font-size: 1.5rem;
    color: #fff;
  }
 #header-img {
    height: 100%;
    width: 100%;
  }
  #header {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    height: 100vh;
    background: none;
    background-color: #87CEEB;
    
  }
  
</style>