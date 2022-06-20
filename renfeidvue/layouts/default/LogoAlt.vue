<template>
  <nuxt-link
    to="/"
  >
    <v-img
      :src="`https://cdn.renfei.net/Logo/RF_white.svg`"
      alt="任霏博客"
      class="shrink"
      max-width="30"
      min-width="30"
      transition="scale-transition"
    />
  </nuxt-link>
</template>

<script>
export default {
  name: "DefaultLogoAlt",
}
</script>
