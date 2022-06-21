<template>
  <div>
    <editor
        :height="600"
        :editor-content="initValue"
        :init="{
          height:400,
          branding:false,
          language:'zh_CN',
        }"
        v-model="value"
    />
    {{ this.value }}
  </div>
</template>

<script>
import Editor from '@/components/Editor'

export default {
  name: 'DashboardPostsPage',
  components: {
    Editor
  },
  layout: function (context) {
    return 'dashboard/default'
  },
  data: () => ({
    initValue: "",
    value: "",
  }),
  methods: {},
}
</script>
