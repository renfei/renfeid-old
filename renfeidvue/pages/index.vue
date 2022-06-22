<template>
  <v-row class="py-5">
    <v-col xs="12">
      <v-row>
        <v-col class="mx-auto" cols="12" xs="12" sm="12" md="10" lg="10" xl="9">
          <carousel/>

          <!--          <Adsense-->
          <!--              :data-ad-client="adsenseClientId"-->
          <!--              data-ad-slot="1847785593">-->
          <!--          </Adsense>-->

          <h1 class="py-5">最新博文</h1>
          <v-row>
            <v-col cols="12" xs="12" sm="12" md="6" lg="6" xl="6">
              <v-card
                  :href="'/posts/'+this.post_1.id"
                  flat
                  class="rounded-xl"
                  height="500"
              >
                <v-img
                    :src="this.post_1.featuredImage"
                    width="100%"
                    height="500"
                    class="white--text align-end"
                    gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                >
                  <v-card-title class="text-sm-h7 text-md-h6 text-lg-h5 text-xl-h4">{{ this.post_1.postTitle }}
                  </v-card-title>
                  <v-card-text>
                    <v-row>
                      <v-col cols="12" class="py-0">
                      </v-col>
                      <v-col cols="12">
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-img>
              </v-card>
            </v-col>
            <v-col cols="12" xs="12" sm="12" md="6" lg="6" xl="6">
              <v-card
                  :href="'/posts/'+post.id"
                  height="150"
                  flat
                  v-for="(post,i) in post_2_4"
                  :key="post.id"
                  :class="i === 2 ? 'rounded-xl' : 'rounded-xl mb-6'"
              >
                <v-parallax
                    :src="post.featuredImage"
                    width="100%"
                    height="150"
                    class="white--text align-end"
                >
                  <v-card-title
                      class="text-sm-h8 text-md-h7 text-lg-h6 text-xl-h5"
                      v-text="post.postTitle">
                  </v-card-title>
                  <v-card-text>
                    <v-row>
                      <v-col cols="12" class="py-0" v-text="post.postDate">
                      </v-col>
                      <v-col cols="12" v-text="post.postExcerpt">
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-parallax>
              </v-card>
            </v-col>
          </v-row>
          <v-row class="py-5">
            <v-col cols="12" xs="12" sm="12" md="8" lg="8" xl="8">
              <h2 class="py-5">全部博文</h2>
              <v-card
                  :href="'/posts/'+post.id"
                  flat
                  outlined
                  :class="i === 0 ? 'rounded-xl' : 'rounded-xl mt-6'"
                  v-for="(post,i) in post_5_10"
                  :key="post.id"
              >
                <div class="d-flex flex-no-wrap">
                  <v-avatar
                      class="ma-3 rounded-lg"
                      size="125"
                      tile
                  >
                    <v-img :src="post.featuredImage"></v-img>
                  </v-avatar>

                  <div>
                    <v-card-title
                        class="text-h5"
                        v-text="post.postTitle"
                    ></v-card-title>

                    <v-card-subtitle v-text="post.postDate">
                    </v-card-subtitle>

                    <v-card-text v-text="post.postExcerpt">
                    </v-card-text>
                  </div>
                </div>
              </v-card>
            </v-col>
            <v-col cols="12" xs="12" sm="0" md="4" lg="4" xl="4">
              <v-row>
                <v-col>
                  <v-card
                      flat
                      outlined
                  >
                    <v-card-title>
                      <v-icon>mdi-wechat</v-icon>
                      微信订阅
                    </v-card-title>
                    <v-card-text align="center">
                      <img src="https://cdn.renfei.net/images/WechatQR.png"
                           alt="任霏博客的微信订阅号"
                           width="500px"
                           height="500px"
                           style="max-width: 200px;height:auto;margin: auto;">
                    </v-card-text>
                  </v-card>
                </v-col>
              </v-row>
              <v-row>
                <v-col>
                  <v-card
                      flat
                      outlined
                  >
                    <v-card-title>
                      <v-icon>mdi-github</v-icon>
                      开源开放
                    </v-card-title>
                    <v-card-text>
                      <p style="text-indent: 2em;">
                        虽然我还是个菜鸟，但站在前辈们的肩膀上让我构建起了我的小站，作为对各位前辈的感谢和致敬，我也将我所做的开源献丑了。
                      </p>
                      <p>
                        后端技术栈：
                      </p>
                      <p>
                        前端技术栈：
                      </p>
                    </v-card-text>
                    <v-card-actions>
                      <v-btn
                          color="primary lighten-2"
                          text
                          href="https://github.com/renfei/renfeid"
                          target="_blank"
                          rel="nofollow noopener"
                      >
                        github.com/renfei/renfeid
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-col>
              </v-row>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-col>
  </v-row>
</template>

<script>
import Carousel from "@/components/index/Carousel";

export default {
  name: 'IndexPage',
  components: {Carousel},
  layout: function (context) {
    return 'default/index'
  },
  data: () => ({
    title: "任霏的主页",
    adsenseClientId: "test",
    post_1: {},
    post_2_4: [],
    post_5_10: [],
  }),
  head: () => ({
    title: this.title,
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
  async asyncData(context) {
    const headers = (context && context.headers) ? Object.assign({}, context.headers) : {}
    const [posts] = await Promise.all([
      context.$api.getPostsList(headers)
    ])
    let post_1 = {}
    let post_2_4 = []
    let post_5_10 = []
    if (posts.data?.data?.data?.length > 0) {
      post_1 = posts.data?.data?.data[0];
    }
    if (posts.data?.data?.data?.length > 1) {
      for (let i = 1; i < 4 && i < posts.data?.data?.data?.length; i++) {
        post_2_4.push(posts.data?.data?.data[i])
      }
    }
    if (posts.data?.data?.data?.length > 4) {
      for (let i = 4; i < 10 && i < posts.data?.data?.data?.length; i++) {
        post_5_10.push(posts.data?.data?.data[i])
      }
    }
    return {
      post_1: post_1,
      post_2_4: post_2_4,
      post_5_10: post_5_10,
    }
  },
}
</script>

<style>
.v-parallax__content {
  background: linear-gradient(rgba(0, 0, 0, 0.1), rgba(0, 0, 0, 0.5));
}
</style>
