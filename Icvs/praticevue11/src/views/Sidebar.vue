<template>
  <div class="Side">
    <router-link to="/main">
      <Sidebar><img src="../assets/spacelogo.png"></Sidebar>
    </router-link>
    <router-link to="/login" class="Select">NMSC ICVS home</router-link>
    <b-button v-b-toggle.collapse-1 class="m-1" id="Select">GK-2A Status</b-button>
    <b-collapse id="collapse-1">
      <router-link to="/main" class="option"><b-card>Radiative Correction</b-card></router-link>
      <router-link to="/locate" class="option"><b-card>Locative Correction</b-card></router-link>
      <b-card>Environmental Information</b-card>
    </b-collapse>
    <b-button v-b-toggle.collapse-2 class="m-1" id="Select">GSICS(Inter-sensor Comparisons)</b-button>
    <b-collapse id="collapse-2">
      <router-link to="/menu" class="option"><b-card>Methodology</b-card></router-link>
      <router-link to="/gsicsAmiIr" class="option"><b-card>IR(infrared)</b-card></router-link>
      <b-card>VIS(Visible)</b-card>
    </b-collapse>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.Side{
  width: 100%;
  max-width: 500px;
  height: 960px;
  font-size: 30px;
  color: black;
  text-align: center;
  display: flex;
  flex-direction: column;
  cursor: pointer;
  border: 1px solid black;
}

.Select{
  color: black;
  text-decoration-line: none;
  margin: 20px 0px;
  display: flex;
  justify-content: space-evenly;
}

#Select{
  color: black;
  margin-top: 20px;
  display: flex;
  justify-content: space-evenly;
  background-color: white;
  border: none;
  font-size: 30px;
}

.option{
  color: black;
  text-decoration-line: none;
}
</style>