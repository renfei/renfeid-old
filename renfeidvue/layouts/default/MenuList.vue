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
          :href="item.link"
          :target="item.target"
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
        {icon: 'home', title: '首页', target:"_self", link: "/"},
        {icon: 'info', title: '文章', target:"_self", link: "/posts"},
        {icon: 'warning', title: '微博', target:"_self", link: "/weibo"},
        {icon: 'warning', title: '相册', target:"_self", link: "/photo"},
        {icon: 'warning', title: '论坛', target:"_blank", link: "https://bbs.renfei.net"},
        {icon: 'warning', title: '工具箱', target:"_self", link: "/kitbox"},
        {icon: 'warning', title: '更多', target:"_self", link: "javascript:void(0)"},
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
