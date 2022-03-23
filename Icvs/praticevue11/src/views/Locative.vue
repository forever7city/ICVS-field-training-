<template>
  <div class="basic">
    <div class="select">
      <h4>Select a parameter : </h4>
      <select v-model="firstOption" @change="paramCall" class="chose">
        <option v-for="option in firstList" v-bind:key="option.id" :value="option.name" >
          {{ option.text }}
        </option>
      </select>
    </div>
    <div class="select">
      <h4>Select a Area : </h4>
      <select v-model="AreaOption" @change="areaCall" class="chose">
        <option v-for="option in AreaList" v-bind:key="option.id" :value="option.value" >
          {{ option.text }}
        </option>
      </select>
    </div>
    <div class="time">
        <div class="start">
          <h4>Select start data & time : </h4>
          <button @click="startDateAdd(-1)" class="Button"></button>
          <VueCtkDateTimePicker v-model="startTime"
                                @validate="requestAxios"></VueCtkDateTimePicker>
          <button @click="startDateAdd(1 )" class="Button"></button>
        </div>
        <div class="start">
          <h4>Select end data & time : </h4>
          <button @click="endDateAdd(-1)" class="Button"></button>
          <VueCtkDateTimePicker v-model="endTime"
                                @validate="requestAxios"></VueCtkDateTimePicker>
          <button @click="endDateAdd(1 )" class="Button"></button>
        </div>
    </div>
    <highcharts :options="chartOptions" class="Chart"></highcharts>
  </div>
</template>

<script>
import Vue from "vue"
import VueCtkDateTimePicker from 'vue-ctk-date-time-picker'
import 'vue-ctk-date-time-picker/dist/vue-ctk-date-time-picker.css';

import VueMoment from "vue-moment"
import axios from 'axios'

import Highcharts from "highcharts"
import HighchartsVue from "highcharts-vue"

Vue.use(VueMoment)
Vue.use(HighchartsVue, {tagName : "highcharts"})

import exporting from "highcharts/modules/exporting"
import exportData from "highcharts/modules/export-data"
exporting(Highcharts)
exportData(Highcharts)



export default {
  components : {
    VueCtkDateTimePicker,
  },
  data () {
    return {
      firstOption : "NAV_PERFORM_EW",
      firstList: [ { text : "Nav Performance EW", id: "1", name: "NAV_PERFORM_EW" },
        { text : "Nav Performance NS", id: "2", name: "NAV_PERFORM_NS"},
      ],
      chartOptions : {
        chart: {
          height: 500,
          zoomType: "xy",
          borderColor: '#cccccc',
          borderWidth: 2,
          type: 'line'
        },
        plotOptions: {
          series: {
            // lineWidth: 1,
            marker: {
              // enabled: true,
              symbol: 'circle',
              radius: 5
            }
          }
        },
        legend: {
          align: 'right',  //  or 'left', 'bottom', 'right'(default)
          verticalAlign: 'top',
          layout: 'vertical',
          y: 50,
        },
        title: {
          text: 'HighCharts Example'
        },
        xAxis: {
          type: 'datetime',
          startOnTick: false,
          endOnTick: false,
          showLastLabel: false,
          labels: {
            format: '{value:%Y-%m-%d %H:%M}',
            // rotation: -45
          },
        },
        yAxis: {
          title: {
            text: "Values"
          }
        },
        series: [],
        exporting: {
          csv: {
            itemDelimiter: ','
          },
          buttons: {
            contextButton: {
              enabled: false
            },
            exportButton: {
              text: 'Download',
              menuItems: [
                'downloadPNG',
                'downloadJPEG',
                'downloadPDF',
                'downloadCSV'
              ]
            },
            printButton: {
              text: 'Print',
              onclick: function () {
                this.print();
              }
            }
          }
        },
        credits: {
          enabled: false
        },
      },
      axiosRequestOption : {
        tableName : "VW_L1B_MBC",
        value : this.firstOption,
        area : this.AreaOption,
        startTime : this.$moment().subtract(14, 'days'),
        endTime : this.$moment(),
      },
      startTime : this.$moment(new Date(2021, 5, 1, 0,0,0)),
      endTime : this.$moment(new Date(2021, 5, 8, 0,0,0)),
      AreaOption : "FD",
      AreaList : [
        {text : "FD", value: "FD", id: "1"},
        {text : "LA", value: "LA", id: "2"},
      ],
    }
  },
  name: "Locate",
  mounted() {
    this.requestAxios()
  },
  methods: {
    requestAxios() {
      const vm = this;
      this.axiosRequestOption.startTime=new Date(this.startTime)
      this.axiosRequestOption.endTime=new Date(this.endTime)
      this.axiosRequestOption.area= this.AreaOption
      this.axiosRequestOption.parameter = this.firstOption

      vm.chartOptions.title.text       = vm.firstOption;
      vm.chartOptions.yAxis.title.text = vm.firstOption;
      vm.chartOptions.series = [];

      let axiosInput= {
        url : "/locate.do",
        method : "POST",
        data : this.axiosRequestOption,
      }
      axios(axiosInput)
          .then((response) => {
            console.log(response);
            vm.chartOptions.series = response.data
          }).catch(err => {``
        console.log("Network response was not ok");
        console.log(err);
      })
    },
    startDateAdd(Day) {
      this.startTime=this.$moment(new Date(this.startTime)).add(Day,"days").format()
      this.requestAxios()
    },
    endDateAdd(Day) {
      this.endTime=this.$moment(new Date(this.endTime)).add(Day,"days").format()
      this.requestAxios()
    },
    areaCall() {
      this.requestAxios()
    },
    paramCall() {
      this.requestAxios()
    },
  }
}
</script>

<style scoped>

.basic{
  padding: 1.5rem;
}

.select{
  display: flex;
  margin-bottom: 15px;
}

.chose{
  margin-left: 10px;
  font-size: 20px;
}

.time{
  display: flex;
  flex-wrap: wrap;
}

.start{
  margin-right: 20px;
  display: flex;
}

.time{
  font-size: 12px;
  height: 36px;
  margin-left: 10px;
}

.Chart{
  margin-top: 50px;
}

.Button{
  height: 42px;
}

</style>