<template>
  <v-toolbar
    height="44"
    flat
    class="px-0"
    style="background: rgba(0,0,0,0)!important;"
  >
    <v-toolbar-items
      flat
      class="px-0 hidden-sm-and-down"
      style="background: rgba(0,0,0,0)!important;"
    >
      <v-btn
        dark
        plain
        class="px-6"
        style="background: rgba(0,0,0,0)!important;"
        v-for="item in menu"
        :key="item.icon"
        :to="item.link"
        text
      >{{ item.title }}
      </v-btn>
    </v-toolbar-items>
    <v-menu class="hidden-md-and-up">
      <v-list>
        <v-list-tile v-for="item in menu" :key="item.icon">
          <v-list-tile-content>
            <v-list-tile-title>{{ item.title }}</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-menu>
  </v-toolbar>
</template>

<script>
export default {
  name: "DefaultMenuList",
  data() {
    return {
      menu: [
        {icon: 'home', title: 'Link A'},
        {icon: 'info', title: 'Link B'},
        {icon: 'warning', title: 'Link C'}
      ]
    }
  },
  methods: {
    menuItems() {
      return this.menu
    }
  }
}
</script>
