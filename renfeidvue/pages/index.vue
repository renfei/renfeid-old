<template>
  <v-row class="py-5">
    <v-col xs="12">
      <v-row>
        <v-col class="mx-auto" cols="12" xs="12" sm="12" md="10" lg="10" xl="9">
          <Adsense
            :data-ad-client="adsenseClientId"
            data-ad-slot="1847785593">
          </Adsense>

          <carousel/>

          <h1 class="py-5">最新博文</h1>
          <v-row>
            <v-col cols="12" xs="12" sm="12" md="6" lg="6" xl="6">
              <v-card
                href="/"
                flat
                class="rounded-xl"
                height="500"
              >
                <v-img
                  src="https://cdn.renfei.net/upload/2022/45723ed1db3b4a7196db995340a1a9ec.jpg?x-oss-process=style/992.558"
                  width="100%"
                  height="500"
                  class="white--text align-end"
                  gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                >
                  <v-card-title class="text-sm-h7 text-md-h6 text-lg-h5 text-xl-h4">这是一个文章标题</v-card-title>
                  <v-card-text>
                    <v-row>
                      <v-col cols="12" class="py-0">
                        2022年5月20日
                      </v-col>
                      <v-col cols="12">
                        喜欢拆解的小伙伴，我的硬盘跟了我4年，抢救无效，今天就拆给小伙伴们看看吧，发挥它最后的余热，给爱好电子设备的小伙伴带来最后的科普知识。
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-img>
              </v-card>
            </v-col>
            <v-col cols="12" xs="12" sm="12" md="6" lg="6" xl="6">
              <v-card
                href="/"
                height="150"
                flat
                v-for="(post,i) in post_2_4"
                :class="i === 2 ? 'rounded-xl' : 'rounded-xl mb-6'"
              >
                <v-parallax
                  :src="post.image"
                  width="100%"
                  height="150"
                  class="white--text align-end"
                >
                  <v-card-title
                    class="text-sm-h8 text-md-h7 text-lg-h6 text-xl-h5"
                    v-text="post.title">
                  </v-card-title>
                  <v-card-text>
                    <v-row>
                      <v-col cols="12" class="py-0">
                        2022年5月20日
                      </v-col>
                      <v-col cols="12">
                        喜欢拆解的小伙伴，我的硬盘跟了我4年，抢救无效，今天就拆给小伙伴们看看吧，发挥它最后的余热，给爱好电子设备的小伙伴带来最后的科普知识。
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
                href="/"
                flat
                outlined
                :class="i === 0 ? 'rounded-xl' : 'rounded-xl mt-6'"
                v-for="(post,i) in post_5_10"
              >
                <div class="d-flex flex-no-wrap">
                  <v-avatar
                    class="ma-3 rounded-lg"
                    size="125"
                    tile
                  >
                    <v-img :src="post.image"></v-img>
                  </v-avatar>

                  <div>
                    <v-card-title
                      class="text-h5"
                      v-text="post.title"
                    ></v-card-title>

                    <v-card-subtitle>
                      dgadsgsadgas
                    </v-card-subtitle>

                    <v-card-text>
                      asdgasdgsadgasdgasdgsadgasdgasdgsadgasdgasdgsadgasdgasdgsadg
                    </v-card-text>
                  </div>
                </div>
              </v-card>
            </v-col>
            <v-col cols="12" xs="12" sm="0" md="4" lg="4" xl="4">
              <v-row>
                <v-col>
                  <h2 class="py-5">微信订阅</h2>
                  <v-card
                    flat
                    outlined
                  >
                    <v-card-text align="center">
                      lalal
                    </v-card-text>
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
    post_2_4: [
      {
        title: "啦啦啦啦啦",
        image: "https://cdn.renfei.net/upload/2022/45723ed1db3b4a7196db995340a1a9ec.jpg",
      },
      {
        title: "啦啦啦啦啦",
        image: "https://cdn.renfei.net/upload/2022/45723ed1db3b4a7196db995340a1a9ec.jpg",
      },
      {
        title: "啦啦啦啦啦",
        image: "https://cdn.renfei.net/upload/2022/45723ed1db3b4a7196db995340a1a9ec.jpg",
      },
    ],
    post_5_10: [
      {
        title: "啦啦啦啦啦",
        image: "https://cdn.renfei.net/upload/2022/45723ed1db3b4a7196db995340a1a9ec.jpg",
      },
      {
        title: "啦啦啦啦啦",
        image: "https://cdn.renfei.net/upload/2022/45723ed1db3b4a7196db995340a1a9ec.jpg",
      },
      {
        title: "啦啦啦啦啦",
        image: "https://cdn.renfei.net/upload/2022/45723ed1db3b4a7196db995340a1a9ec.jpg",
      },
    ],
  }),
  head() {
    return {
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
    }
  },
  // async asyncData(context) {
  //   let hitokoto = await context.$axios.get('https://v1.hitokoto.cn')
  //     .then((res) => {
  //       return res
  //     })
  //
  //   return {
  //     hitokoto: hitokoto.data.hitokoto,
  //   }
  // },
}
</script>

<style>
.v-parallax__content {
  background: linear-gradient(rgba(0, 0, 0, 0.1), rgba(0, 0, 0, 0.5));
}
</style>
