<template>
  <div id="app" class="area">
    <sidebar></sidebar>
    <div class="main">
      <main-header></main-header>
        <div class="content">
          <router-view></router-view>
        </div>
      <main-footer></main-footer>
    </div>
  </div>
</template>

<script>

import sidebar from "../views/Sidebar";
import mainHeader from "../views/Header";
import mainFooter from "../views/Footer";


export default {
  components: {
    sidebar,
    mainHeader,
    mainFooter,
  }
}
</script>

<style scoped>

.area{
  width: 100%;
  height: auto;
  display: flex;
}

.main{
  width: 100%;
  display: flex;
  flex-direction: column;
  background-color: #f8f9fe;
}

.content{
  width: 100%;
  max-width: 1300px;
  background-color: skyblue;
  margin-left: 50px;
  font-size: 28px;
  padding: 5px 10px;
  border-radius: 20px;
}

</style>