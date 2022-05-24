<template>
  <v-menu
    v-model="menuModel"
    max-height="75vh"
    dark
    offset-y
    readonly
    @input="resetSearch"
  >
    <template #activator="{ attrs }">
      <v-text-field
        dark
        ref="searchInput"
        v-model="searchString"
        v-bind="attrs"
        :class="isSearching ? 'rounded-b-0' : ' rounded-lg'"
        flat
        placeholder="搜索一下，发现更多"
        autocomplete="off"
        class="mx-2 mx-md-4"
        dense
        hide-details
        solo
        style="max-width: 450px;"
        @focus="onFocus"
      >
        <template #prepend-inner>
          <v-icon
            :color="!isFocused ? 'grey' : undefined"
            class="ml-1 mr-2"
          >
            mdi-magnify
          </v-icon>
        </template>
      </v-text-field>
    </template>
  </v-menu>
</template>

<script>
export default {
  name: "DefaultSearch",
  data: () => ({
    isFocused: false,
    menuModel: false,
    searchString: '',
  }),
  computed: {
    isSearching() {
      return this.searchString && this.searchString.length > 0
    },
  },
  methods: {
    async onFocus() {
      clearTimeout(this.timeout)

      this.isFocused = true
    },
    resetSearch() {
      clearTimeout(this.timeout)

      this.$nextTick(() => {
        this.searchString = ''
        this.timeout = setTimeout(() => this.isFocused = false)
        this.menuModel = false
      })
    },
  },
}
</script>
