<template>
  <v-row>
    <v-col cols="12" class="py-0">
      <v-breadcrumbs
          class="pb-0 pl-0"
          :items="breadcrumbItems"></v-breadcrumbs>
    </v-col>
    <v-col cols="12" xs="6" sm="7" md="8" lg="8" xl="9">
      <v-row>
        <v-col>
          <v-card
              flat
              outlined
          >
            <v-card-title>
              内容详情
            </v-card-title>
            <v-card-text>
              <v-row>
                <v-col
                    cols="12"
                    class="pb-0">
                  <v-text-field
                      label="内容标题"
                      v-model="post.postTitle"
                      outlined
                      dense
                      hide-details
                  >
                    <template v-slot:append-outer>
                      <v-file-input
                          label="特色图像"
                          outlined
                          dense
                          hide-details
                          prepend-icon="mdi-file-image"
                          accept="image/*"
                          style="margin-top: -8px;width: 135px;"
                      >
                      </v-file-input>
                    </template>
                  </v-text-field>
                </v-col>
                <v-col
                    cols="12"
                    class="pb-0">
                  <v-combobox
                      label="关键字"
                      v-model="keyWordModel"
                      :items="keyWordItems"
                      :search-input.sync="keyWordSearch"
                      hide-selected
                      persistent-hint
                      small-chips
                      multiple
                      outlined
                      dense
                      hide-details
                  >
                    <template v-slot:append-outer>
                      <v-btn
                          depressed
                          style="margin-top: -8px;width: 135px;"
                      >
                        自动正文提取
                      </v-btn>
                    </template>

                    <template v-slot:no-data>
                      <v-list-item>
                        <v-list-item-content>
                          <v-list-item-title>
                            No results matching "<strong>{{ keyWordSearch }}</strong>". Press <kbd>enter</kbd> to create
                            a new one
                          </v-list-item-title>
                        </v-list-item-content>
                      </v-list-item>
                    </template>
                  </v-combobox>
                </v-col>
                <v-col
                    cols="12"
                    class="pb-0">
                  <v-textarea
                      label="内容摘要"
                      v-model="post.postExcerpt"
                      outlined
                      dense
                      height="80"
                      hide-details
                  ></v-textarea>
                </v-col>
                <v-col
                    cols="12"
                    class="pb-0">
                  <editor
                      :height="700"
                      :editor-content="initValue"
                      v-model="post.postContent"
                  />
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
                    保存
                  </v-btn>
                </v-col>
              </v-row>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-col>
    <v-col cols="12" xs="6" sm="5" md="4" lg="4" xl="3">
      <v-row>
        <v-col cols="12">
          <v-card
              flat
              outlined
          >
            <v-card-title>
              元信息
            </v-card-title>
            <v-card-text>
              <v-row>
                <v-col cols="12" xs="6" sm="6" class="pb-0">
                  <v-select
                      label="栏目分类"
                      v-model="post.categoryId"
                      outlined
                      dense
                      hide-details
                  ></v-select>
                </v-col>
                <v-col cols="12" xs="6" sm="6" class="pb-0">
                  <v-select
                      label="保密等级"
                      v-model="post.secretLevel"
                      :items="secretLevelItems"
                      item-text="value"
                      item-value="key"
                      outlined
                      dense
                      hide-details
                  ></v-select>
                </v-col>
                <v-col cols="12" xs="6" sm="6" class="pb-0">
                  <v-text-field
                      label="密码保护"
                      v-model="post.postPassword"
                      outlined
                      dense
                      hide-details
                  ></v-text-field>
                </v-col>
                <v-col cols="12" xs="6" sm="6" class="pb-0">
                  <v-select
                      label="是否允许评论"
                      v-model="post.commentStatus"
                      :items="commentStatusItems"
                      item-text="value"
                      item-value="key"
                      outlined
                      dense
                      hide-details
                  ></v-select>
                </v-col>
                <v-col
                    cols="12"
                    xs="6" sm="6"
                    class="pb-0">
                  <v-text-field
                      label="原文链接"
                      v-model="post.sourceUrl"
                      outlined
                      dense
                      hide-details
                  ></v-text-field>
                </v-col>
                <v-col
                    cols="12"
                    xs="6" sm="6"
                    class="pb-0">
                  <v-text-field
                      label="原文作者"
                      v-model="post.sourceName"
                      outlined
                      dense
                      hide-details
                  ></v-text-field>
                </v-col>
                <v-col cols="12" class="pb-0">
                  <v-datetime-picker
                      label="发布时间"
                      v-model="post.postDate"
                      outlined
                      dense
                      hide-details>
                  </v-datetime-picker>
                </v-col>
                <v-col cols="12" xs="6" sm="6" class="pb-0">
                  <v-text-field
                      label="作者"
                      v-model="post.postAuthor"
                      outlined
                      dense
                      filled
                      readonly
                      hide-details
                  ></v-text-field>
                </v-col>
                <v-col cols="12" xs="6" sm="6" class="pb-0">
                  <v-select
                      label="状态"
                      v-model="post.postStatus"
                      :items="postStatusItems"
                      item-value="key"
                      item-text="value"
                      outlined
                      dense
                      filled
                      readonly
                      hide-details
                  ></v-select>
                </v-col>
                <v-col cols="12" xs="6" sm="6" class="pb-0">
                  <v-text-field
                      label="修改人"
                      v-model="post.postModifiedUser"
                      outlined
                      dense
                      filled
                      readonly
                      hide-details
                  ></v-text-field>
                </v-col>
                <v-col cols="12" xs="6" sm="6" class="pb-0">
                  <v-text-field
                      label="修改时间"
                      v-model="post.postModified"
                      outlined
                      dense
                      filled
                      readonly
                      hide-details
                  ></v-text-field>
                </v-col>
                <v-col cols="12" xs="6" sm="6" class="pb-0">
                  <v-text-field
                      label="版本号"
                      v-model="post.versionNumber"
                      outlined
                      dense
                      filled
                      readonly
                      hide-details
                  ></v-text-field>
                </v-col>
                <v-col cols="12" xs="6" sm="6" class="pb-0">
                  <v-text-field
                      label="浏览量"
                      v-model="post.postViews"
                      outlined
                      dense
                      filled
                      readonly
                      hide-details
                  ></v-text-field>
                </v-col>
                <v-col
                    cols="12">
                </v-col>
              </v-row>
            </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="12">
          <v-card
              flat
              outlined
          >
            <v-card-title>
              历史版本
            </v-card-title>
            <v-card-text>
              <v-list
              >
                <v-list-item three-line>
                  <v-list-item-content>
                    <v-list-item-title>标题</v-list-item-title>
                    <v-list-item-subtitle>
                      2022-06-12 12:23:34
                    </v-list-item-subtitle>
                    <v-list-item-subtitle>
                      版本号：2，修改人：任霏
                    </v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-col>
  </v-row>
</template>

<script>
import Editor from '@/components/Editor'

export default {
  name: "PostEditor",
  components: {
    Editor
  },
  layout: function (context) {
    return 'dashboard/default'
  },
  data: () => ({
    breadcrumbItems:[
      {
        text: '控制台',
        disabled: false,
        href: '/dashboard',
      },
      {
        text: '内容管理列表',
        disabled: false,
        href: '/dashboard/posts',
      },
      {
        text: '内容详情管理',
        disabled: true,
        href: 'breadcrumbs_link_2',
      },
    ],
    post: {
      id: -1,
      categoryId: '',
      postAuthor: '',
      postDate: new Date(),
      postStatus: 'PUBLISH',
      postViews: 0,
      commentStatus: 'OPEN',
      postPassword: '',
      postModified: '',
      postModifiedUser: '',
      postParent: '',
      versionNumber: 0,
      thumbsUp: 0,
      thumbsDown: 0,
      avgViews: 0,
      avgComment: 0,
      pageRank: 0,
      secretLevel: 0,
      isOriginal: '',
      featuredImage: '',
      postTitle: '',
      postKeyword: '',
      postExcerpt: '',
      postContent: '',
      sourceName: '',
      sourceUrl: '',
      keyWord: [],
    },
    datetime: new Date(),
    pathMatch: "create",
    initValue: "",
    keyWordSearch: null,
    keyWordModel: [],
    keyWordItems: [],
    secretLevelItems: [
      {key: 0, value: "非密"},
      {key: 1, value: "内部"},
      {key: 2, value: "秘密"},
      {key: 3, value: "机密"},
    ],
    postStatusItems: [
      {key: "PUBLISH", value: "发布"},
      {key: "REVISION", value: "修订版"},
      {key: "DELETED", value: "删除"},
      {key: "OFFLINE", value: "下线"},
      {key: "REVIEW", value: "审核流程中"},
    ],
    commentStatusItems: [
      {key: "OPEN", value: "允许评论"},
      {key: "SIGNED", value: "登录后评论"},
      {key: "CLOSED", value: "禁止评论"},
    ],
  }),
  head: () => ({
    title: "编辑或创建内容",
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
  mounted() {
    this.pathMatch = this.$route.params.pathMatch
  },
  watch: {
    keyWordModel(val) {
      if (val.length > 5) {
        this.$nextTick(() => this.keyWordModel.pop())
      }
    },
  },
}
</script>
