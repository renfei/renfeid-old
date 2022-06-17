<template>
  <v-row class="py-5">
    <v-col xs="12">
      <v-row>
        <v-col class="mx-auto" cols="12" xs="12" sm="12" md="10" lg="10" xl="9">
          <div v-if="error.statusCode === 404">
            <h1 >页面不存在</h1>
            <p v-text="error.message"></p>
          </div>
          <div v-else-if="error.statusCode === 500">
            <h1>服务器错误</h1>
            <p v-text="error.message"></p>
          </div>
          <div v-else>
            <h1>未知错误</h1>
            <p v-text="error.message"></p>
          </div>
          <div>
            <NuxtLink to="/">Home page</NuxtLink>
          </div>
        </v-col>
      </v-row>
    </v-col>
  </v-row>
</template>

<script>
export default {
  props: ['error'],
  layout: 'default/index'
}
</script>