<template>
  <v-app-bar
    flat
    app
    height="44"
    class="v-bar--underline renfeid-app-bar"
  >
    <v-app-bar-nav-icon
      class="hidden-lg-and-up"
      @click="drawer = !drawer"
    />

    <default-logo-alt
      to="/"
      class="ml-0 mr-2 ml-md-2"
    />
    <v-spacer />
    <default-app-bar-items/>
    <v-btn
      class="ma-2 hidden-sm-and-down"
      dark
      icon
      @click="btnClick"
    >
      <v-icon>mdi-theme-light-dark</v-icon>
    </v-btn>
  </v-app-bar>
</template>

<script>
import {dark} from "@/utils/dark";

import DefaultLogoAlt from './LogoAlt'
import DefaultAppBarItems from './AppBarItems'

export default {
  name: "DefaultBar",
  components: {
    DefaultAppBarItems,
    DefaultLogoAlt
  },
  computed: {},
  data() {
    return {}
  },
  methods: {
    btnClick() {
      dark(this.$vuetify, !this.$vuetify.theme.dark);
    },
  }
}
</script>
