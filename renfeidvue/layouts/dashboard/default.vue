<template>
  <v-app id="inspire">
    <v-navigation-drawer
      v-model="drawer"
      flat
      app
    >
      <v-toolbar>
        <v-list-item class="pa-0 mx-n1">
          后台管理
        </v-list-item>
      </v-toolbar>
      <v-list
        dense
        expand
        nav
      >
        <template v-for="(item, i) in items">
          <v-subheader
            v-if="item.heading"
            :key="`heading-${i}`"
            class="text--primary font-weight-black text-uppercase"
            v-text="item.heading"
          />

          <v-divider
            v-else-if="item.divider"
            :key="`divider-${i}`"
            class="mt-3 mb-2 ml-2 mr-n2"
          />

          <default-list-group
            v-else-if="item.items"
            :key="`group-${i}`"
            :item="item"
          />

          <slot
            v-else-if="$scopedSlots.item"
            name="item"
            :index="i"
            :item="item"
          />

          <default-list-item
            v-else
            :key="`item-${i}`"
            :item="item"
          />
        </template>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar app>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>Application</v-toolbar-title>
    </v-app-bar>

    <v-main>
      <v-container>
        <v-row>
          <v-col>
            <Nuxt/>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import {dark} from '@/utils/dark'

export default {
  name: 'DefaultLayout',
  props: {
    items: {
      type: Array,
      default: () => ([]),
    },
  },
  data() {
    return {
      drawer: true,
      links: [
        'Dashboard',
        'Messages',
        'Profile',
        'Updates',
      ],
    }
  },
  beforeMount: function () {
    dark(this.$vuetify, undefined);
  },
  methods: {
    btnClick() {
      dark(this.$vuetify, !this.$vuetify.theme.dark);
    },
  }
}
</script>
