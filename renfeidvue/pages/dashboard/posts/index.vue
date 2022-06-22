<template>
  <v-row>
    <v-col cols="12" class="py-0">
      <v-breadcrumbs
          class="pb-0 pl-0"
          :items="breadcrumbItems"></v-breadcrumbs>
    </v-col>
    <v-col cols="12">
      <v-card
          flat
          outlined
      >
        <v-card-title>搜索内容列表</v-card-title>
        <v-card-text>
          <v-row>
            <v-col
                cols="12" sm="4" md="3" lg="3" xl="2">
              <v-select
                  label="栏目分类"
                  outlined
                  dense
                  hide-details
              ></v-select>
            </v-col>
            <v-col
                cols="12" sm="4" md="3" lg="3" xl="2">
              <v-select
                  label="状态"
                  :items="postStatusItems"
                  item-value="key"
                  item-text="value"
                  outlined
                  dense
                  hide-details
              ></v-select>
            </v-col>
          </v-row>
        </v-card-text>
        <v-card-actions>
          <v-row>
            <v-col cols="12" class="text-right">
              <v-btn
                  depressed
                  color="primary"
              >
                搜索
              </v-btn>
            </v-col>
          </v-row>
        </v-card-actions>
      </v-card>
    </v-col>
    <v-col cols="12">
      <v-data-table
          disable-sort
          disable-filtering
          no-data-text="暂无数据"
          :headers="headers"
          :items="desserts"
          :options.sync="options"
          :server-items-length="totalDesserts"
          :loading="loading"
          class="elevation-1"
      ></v-data-table>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: 'DashboardPostsPage',
  layout: function (context) {
    return 'dashboard/default'
  },
  data: () => ({
    breadcrumbItems: [
      {
        text: '控制台',
        disabled: false,
        href: '/dashboard',
      },
      {
        text: '内容管理列表',
        disabled: true,
        href: '/dashboard/posts',
      },
    ],
    totalDesserts: 0,
    desserts: [],
    loading: true,
    options: {},
    headers: [
      {text: '标题', value: 'name',},
      {text: 'Calories', value: 'calories'},
      {text: 'Fat (g)', value: 'fat'},
      {text: 'Carbs (g)', value: 'carbs'},
      {text: 'Protein (g)', value: 'protein'},
      {text: 'Iron (%)', value: 'iron'},
    ],
    postStatusItems: [
      {key: "PUBLISH", value: "发布"},
      {key: "REVISION", value: "修订版"},
      {key: "DELETED", value: "删除"},
      {key: "OFFLINE", value: "下线"},
      {key: "REVIEW", value: "审核流程中"},
    ],
  }),
  head: () => ({
    title: "内容管理列表",
    meta: [
      // hid is used as unique identifier. Do not use `vmid` for it as it will not work
      {
        hid: 'description',
        name: 'description',
        content: 'My custom description'
      },
      {
        hid: 'keyword',
        name: 'keyword',
        content: 'My custom keyword'
      }
    ]
  }),
  watch: {
    options: {
      handler() {
        this.getDataFromApi()
      },
      deep: true,
    },
  },
  methods: {
    getDataFromApi() {
      this.loading = true
      this.fakeApiCall().then(data => {
        this.desserts = data.items
        this.totalDesserts = data.total
        this.loading = false
      })
    },
    /**
     * In a real application this would be a call to fetch() or axios.get()
     */
    fakeApiCall() {
      return new Promise((resolve, reject) => {
        const {sortBy, sortDesc, page, itemsPerPage} = this.options

        let items = this.getDesserts()
        const total = items.length

        if (sortBy.length === 1 && sortDesc.length === 1) {
          items = items.sort((a, b) => {
            const sortA = a[sortBy[0]]
            const sortB = b[sortBy[0]]

            if (sortDesc[0]) {
              if (sortA < sortB) return 1
              if (sortA > sortB) return -1
              return 0
            } else {
              if (sortA < sortB) return -1
              if (sortA > sortB) return 1
              return 0
            }
          })
        }

        if (itemsPerPage > 0) {
          items = items.slice((page - 1) * itemsPerPage, page * itemsPerPage)
        }

        setTimeout(() => {
          resolve({
            items,
            total,
          })
        }, 1000)
      })
    },
    getDesserts() {
      return [
        {
          name: 'Frozen Yogurt',
          calories: 159,
          fat: 6.0,
          carbs: 24,
          protein: 4.0,
          iron: '1%',
        },
        {
          name: 'Ice cream sandwich',
          calories: 237,
          fat: 9.0,
          carbs: 37,
          protein: 4.3,
          iron: '1%',
        },
        {
          name: 'Eclair',
          calories: 262,
          fat: 16.0,
          carbs: 23,
          protein: 6.0,
          iron: '7%',
        },
        {
          name: 'Cupcake',
          calories: 305,
          fat: 3.7,
          carbs: 67,
          protein: 4.3,
          iron: '8%',
        },
        {
          name: 'Gingerbread',
          calories: 356,
          fat: 16.0,
          carbs: 49,
          protein: 3.9,
          iron: '16%',
        },
        {
          name: 'Jelly bean',
          calories: 375,
          fat: 0.0,
          carbs: 94,
          protein: 0.0,
          iron: '0%',
        },
        {
          name: 'Lollipop',
          calories: 392,
          fat: 0.2,
          carbs: 98,
          protein: 0,
          iron: '2%',
        },
        {
          name: 'Honeycomb',
          calories: 408,
          fat: 3.2,
          carbs: 87,
          protein: 6.5,
          iron: '45%',
        },
        {
          name: 'Donut',
          calories: 452,
          fat: 25.0,
          carbs: 51,
          protein: 4.9,
          iron: '22%',
        },
        {
          name: 'KitKat',
          calories: 518,
          fat: 26.0,
          carbs: 65,
          protein: 7,
          iron: '6%',
        },
      ]
    },
  },
}
</script>
