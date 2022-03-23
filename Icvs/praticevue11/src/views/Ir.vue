<template>
  <div class="options">
    <div>
      <h2>Select Data Type to Display</h2>
      <div class="first">
        <div class="divide">
          <h4>Statistics for GSICS Correction</h4>
          <input type="radio" v-model='typeValue'
                 value="0" @change="clickFunc">Scatter plot
        </div>
        <div>
          <h4>Time Series</h4>
          <input type="radio" v-model='typeValue'
                 value="1" @change="clickFunc" >BT Difference
          <input type="radio" v-model='typeValue'
                 value="2" @change="clickFunc">Reflectance Diff
        </div>
      </div>
      <div class="calendar">
        <h4>Select a Date</h4>
        <datepicker :value="irDate" @input="clickFunc"></datepicker>
      </div>
      <div class="instrument">
        <h4>Select LEO Instrument</h4>
        <div class="option">
          <div v-for="(leoName, i) in leoNames" :key="leoName">
            <input type="radio" v-model='leoValue'
                   :value="i" @change="clickFunc">{{leoName}}
          </div>
        </div>
        <div class="instrument">
          <h4>Select AMI Channel/Band</h4>
          <div v-for="(bandName,i) in bandNames" :key="bandName">
            <input type="radio" v-model='chanValue'
                   :value=i @change="clickFunc">{{ bandName }}
          </div>
        </div>
        <div class="instrument">
          <h4>Select Node</h4>
          <div v-for="(selectName,i) in selectNames" :key="selectName">
            <input type="radio" v-model='nodeValue'
                   :value=i @change="clickFunc">{{ selectName }}
          </div>
        </div>
      </div>
    </div>
    <div class="box">
      <img :src="img" style="max-width: 100%; max-height: 100%">
    </div>
  </div>
</template>

<script>

import Datepicker from 'vuejs-datepicker';
import axios from "axios";

export default {
  name:  "GsicsAmiIr",
  props:['removeValue'],
  components: {
    Datepicker
  },
  data(){
    return{
      defaultImage : require("@/assets/Noimage.png"),
      img : "",
      bandNames : ["Band 07(3.83Um)", "Band 08(3.83Um)", "Band 09(3.83Um)", "Band 10(3.83Um)", "Band 11(3.83Um)",
                  "Band 12(3.83Um)", "Band 13(3.83Um)", "Band 14(3.83Um)", "Band 15(3.83Um)", "Band 16(3.83Um)"],
      leoNames : ["AQUA/AIRS", "Metop-A/IASI", "Metop-B/IASI", "Metop-C/IASI", "Suomi NPP/CrlS", "NOAA20/Crls"],
      selectNames : ["Ascending", "Descending", "Both"],
      typeValue:0,
      leoValue:0,
      chanValue:0,
      nodeValue:0,
      type : ["scat", "times_tbbias", "times_regcoeff"],
      Leo : ["AIRS", "IASI", "IASI_B", "IASI_C", "SNPP_CrISF", "NOAA20_CrISF"],
      Band : [7, 8, 9, 10, 11,
              12, 13, 14, 15, 16],
      node : ["asc", "dec", "all"],
      axiosOption : {
        type: "scat",
        date: new Date(),
        Leo: "AIRS",
        band: 7,
        node: "asc"
      },
      irDate : new Date()
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    init() {
      this.img=this.defaultImage
    },
    clickFunc() {
      this.makeJson()
      this.requestAxios()
    },
    makeJson() {
      this.axiosOption.type=this.type[this.typeValue];
      this.axiosOption.Leo=this.Leo[this.leoValue];
      this.axiosOption.band=this.Band[this.chanValue];
      this.axiosOption.node=this.node[this.nodeValue];

      if(this.axiosOption.type == 'scat'){
        console.log('scatter');
        this.axiosOption.date = new Date(2021, 4, 17, 9)
      } else{
        console.log('not scatter')
        this.axiosOption.date = new Date(2021, 4, 30, 9)
      }
    },
    requestAxios() {

      let axiosInput= {
        url : "/gsicsAmilr.do",
        method : "POST",
        data : this.axiosOption,
        responseType: 'arraybuffer'
      }

      console.log(axiosInput)
      axios(axiosInput).then((res) =>{
        console.log(res.data)
        let bytes = new Uint8Array(res.data);
        console.log("E",bytes)
        let binary = bytes.reduce((data, b) => data += String.fromCharCode(b), '');
        console.log(binary)
        this.img = "data:image/png;base64," + btoa(binary);
        console.log(this.img)
      }).catch((error) => {
        console.log(error)
        this.img = this.defaultImage
      })
    }
  },
}
</script>

<style scoped>
  .options{
    width: 100%;
    padding: 1.5rem;
    display: flex;
  }

  .first{
    width: 100%;
    margin-top: 30px;
    display: flex;
    flex-wrap: wrap;
  }

  .divide{
    margin-right: 50px;
  }

  .calendar{
    margin-top: 30px;
  }

  .instrument{
    margin-top: 30px;
  }

  .box{

  }
</style>