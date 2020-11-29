<template>
  <div  id="home">
    <main-navigation />
    <div id="header">
      <div id="header-text">
        <div id="header-title">
          <p id="header-desc">Register in pharmacy</p>
          <select v-model="selected">
            <option disabled value="">Please select one</option>
            <option>apila1234</option>
            <option>apisa1234</option>
            </select>
            <span>Selected: {{ selected }}</span>
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

 
  #title {
    font-weight: 500;
    font-size: 3.5rem;
    color: #fff;
  }
 
  #header {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    height: 100vh;
    background: none;
    background-color: #87CEEB;
    
  }  
  #header-desc {
    font-size: 1.5rem;
    color: #fff;
  }
  
</style>