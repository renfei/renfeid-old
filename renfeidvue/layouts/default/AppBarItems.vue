<template>
  <div class="flex-1-1-auto d-flex align-center justify-end">
    <default-menu-list/>

    <v-spacer/>

    <default-search/>

    <v-spacer/>

    <v-menu offset-y style="float: right;">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="ma-2"
          dark
          icon
          v-bind="attrs"
          v-on="on"
        >
          <v-icon>mdi-account-circle</v-icon>
        </v-btn>
      </template>
      <v-list>
        <v-list-item
          v-for="(item, index) in items"
          :key="index"
        >
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
  </div>
</template>

<script>
import DefaultSearch from './Search'
import DefaultMenuList from './MenuList'

export default {
  name: 'DefaultAppBarItems',

  components: {
    DefaultSearch,
    DefaultMenuList,
  },

  data() {
    return {
      links: [
        'Dashboard',
        'Messages',
      ],
      items: [
        {title: 'Click Me'},
        {title: 'Click Me'},
        {title: 'Click Me'},
        {title: 'Click Me 2'},
      ],
    }
  },
}
</script>
