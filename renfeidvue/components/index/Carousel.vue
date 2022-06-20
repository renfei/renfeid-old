<template>
  <v-carousel
    cycle
    hide-delimiters
    v-model="carousel_model"
    height="400"
    class="rounded-xl"
    delimiter-icon="mdi-minus"
  >
    <v-carousel-item>
      <v-row>
        <v-col cols="12" xs="12" sm="12" md="12" lg="6" xl="6">
          <v-card
            flat
            class="px-10 py-15"
            width="100%"
            height="400"
          >
            <v-card-text>
              <v-row align="center">
                <v-col
                  class="text-h2"
                >
                  啦啦啦啦啦啦
                </v-col>
              </v-row>
            </v-card-text>
            <v-list-item>
              <v-list-item-subtitle class="px-5">zfgadghadhdfhdf</v-list-item-subtitle>
            </v-list-item>

            <v-list-item>
              <div class="text-center">
                <v-btn
                  rounded
                  outlined
                  color="grey"
                  dark
                >
                  Rounded Button
                </v-btn>
              </div>
            </v-list-item>
          </v-card>
        </v-col>
        <v-col cols="12" xs="0" sm="0" md="0" lg="6" xl="6" class="hidden-md-and-down">
          <kinesis-container class="kinesis-container">
            <kinesis-element :strength="20" type="depth">
              <v-img
                class="mx-auto"
                width="20vw"
                style="margin-top: 10vh;"
                src="/image/undraw_design_components_9vy6.svg"/>
            </kinesis-element>
            <!-- background-circles-props -->
            <kinesis-element :strength="150">
              <div class="circle circle-purple"></div>
            </kinesis-element>
            <kinesis-element :strength="180">
              <div class="circle circle-purple1"></div>
            </kinesis-element>
            <kinesis-element :strength="240">
              <div class="circle circle-purple2"></div>
            </kinesis-element>
            <kinesis-element :strength="260">
              <div class="circle circle-purple3"></div>
            </kinesis-element>
          </kinesis-container>
        </v-col>
      </v-row>
    </v-carousel-item>
    <v-carousel-item>
      <v-row>
        <v-col cols="12" xs="12" sm="12" md="12" lg="6" xl="6">
          <v-card
            flat
            class="px-10 py-15"
            width="100%"
            height="400"
          >
            <v-card-text>
              <v-row align="center">
                <v-col
                  class="text-h2"
                >
                  开发者工具箱
                </v-col>
              </v-row>
            </v-card-text>
            <v-list-item>
              <v-list-item-subtitle class="px-5">
                子曰："工欲善其事，必先利其器。"<br/>
                利用工具优化自己的学习工作效率，将时间留给更美好的事物。
              </v-list-item-subtitle>
            </v-list-item>

            <v-list-item>
              <div class="text-center">
                <v-btn
                  rounded
                  outlined
                  color="grey"
                  dark
                  href="/kitbox"
                >
                  开发者工具箱
                </v-btn>
              </div>
            </v-list-item>
          </v-card>
        </v-col>
        <v-col cols="12" xs="0" sm="0" md="0" lg="6" xl="6" class="hidden-md-and-down">
          <kinesis-container class="kinesis-container">
            <kinesis-element :strength="20" type="depth">
              <v-img
                class="mx-auto"
                width="20vw"
                style="margin-top: 10vh;"
                src="/image/undraw_code_inspection_bdl7.svg"/>
            </kinesis-element>
            <!-- background-circles-props -->
            <kinesis-element :strength="150">
              <div class="circle circle-purple"></div>
            </kinesis-element>
            <kinesis-element :strength="180">
              <div class="circle circle-purple1"></div>
            </kinesis-element>
            <kinesis-element :strength="240">
              <div class="circle circle-purple2"></div>
            </kinesis-element>
            <kinesis-element :strength="260">
              <div class="circle circle-purple3"></div>
            </kinesis-element>
          </kinesis-container>
        </v-col>
      </v-row>
    </v-carousel-item>
  </v-carousel>
</template>
<script>
export default {
  name: "carousel",
  data: () => ({
    carousel_model: 0,
  }),
}
</script>

<style>
.computer {
  width: 5rem;
}

.kinesis-container {
  text-align: center;
}

.circle {
  position: absolute;
  border-radius: 100%;
}

.circle.circle-purple {
  border: 10px solid #444444;
  opacity: 0.1;
  width: 4vw;
  height: 4vw;
  left: 10%;
  top: 35%;
}

.circle.circle-purple1 {
  border: 15px solid #444444;
  opacity: 0.1;
  width: 8vw;
  height: 8vw;
  right: -2%;
  bottom: 10%;
}

.circle.circle-purple2 {
  background-color: #444444;
  opacity: 0.1;
  width: 5vw;
  height: 5vw;
  left: 20%;
  bottom: 7%;
}

.circle.circle-purple3 {
  border: 15px solid #444444;
  opacity: 0.3;
  width: 3vw;
  height: 3vw;
  top: 80%;
  left: 60%;
}
</style>
