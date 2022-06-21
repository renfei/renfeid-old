<template>
  <v-app>
    <default-system-bar/>

    <default-app-bar/>

    <v-main>
      <v-container fluid style="padding: 0;">
        <Nuxt/>
      </v-container>
    </v-main>

    <div style="height: 300px;"></div>

    <default-footer />

    <Snackbar></Snackbar>
  </v-app>
</template>

<script>
import {dark} from '@/utils/dark'

import Snackbar from "@/components/Snackbar"
import DefaultSystemBar from "./SystemBar";
import DefaultAppBar from "./AppBar";
import DefaultFooter from "./Footer";

export default {
  name: 'DefaultLayout',
  components: {
    Snackbar,
    DefaultSystemBar,
    DefaultAppBar,
    DefaultFooter,
  },
  data() {
    return {}
  },
  beforeMount: function () {
    dark(this.$vuetify, undefined);
  },
  methods: {}
}
</script>
