<template>
  <v-row>
    <v-col>
      <v-card
          :width="width"
          :loading="loading"
          :outlined="outlined"
          flat
          class="signin-card mx-auto"
      >
        <v-card-title class="mt-6">
          <v-img :src="pageLogo"
                 height="45"
                 width="45"
                 max-height="45"
                 max-width="45"
                 alt="logo"/>
        </v-card-title>
        <v-card-subtitle>
          <v-row>
            <v-col
                cols="12"
                class="font-weight-black py-2 text-h6">
              创建您的账号
            </v-col>
          </v-row>
        </v-card-subtitle>
        <v-card-text class="py-3 px-0">
          <v-row>
            <v-col cols="12" xs="12" sm="12" md="7">
              <v-row>
                <v-col
                    cols="12"
                    class="pb-0 ml-4 pr-7">
                  <v-row>
                    <v-col class="py-0" cols="12" sm="6">
                      <v-text-field
                          label="姓氏"
                          outlined
                          dense
                          v-model="form.lastname"
                      ></v-text-field>
                    </v-col>
                    <v-col class="py-0" cols="12" sm="6">
                      <v-text-field
                          label="名字"
                          outlined
                          dense
                          v-model="form.firstname"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col class="py-0" cols="12">
                      <v-text-field
                          label="用户名"
                          hint="创建成功后用户名不可修改"
                          outlined
                          dense
                          v-model="form.username"
                          :error-messages="error.usernameErrorMessages"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col class="py-0" cols="12">
                      <v-text-field
                          label="电子邮箱"
                          hint="请填写真实的电子邮箱地址，您会收到一封激活邮件"
                          outlined
                          dense
                          v-model="form.email"
                          :error-messages="error.emailErrorMessages"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col class="py-0" cols="12" sm="6">
                      <v-text-field
                          label="密码"
                          outlined
                          dense
                          type="password"
                          v-model="form.password"
                          :error-messages="error.passwordErrorMessages"
                      ></v-text-field>
                    </v-col>
                    <v-col class="py-0" cols="12" sm="6">
                      <v-text-field
                          label="重复密码"
                          outlined
                          dense
                          type="password"
                          v-model="form.password2"
                          :error-messages="error.passwordErrorMessages"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
            </v-col>
            <v-col cols="12" xs="0" sm="0" md="5" class="text-center d-none d-md-block">
              <v-row>
                <v-col cols="12">
                  <v-icon
                      size="150"
                  >
                    mdi-shield-account
                  </v-icon>
                </v-col>
                <v-col cols="12">
                  仅需一个账号，您便可以使用全部服务。
                </v-col>
              </v-row>
            </v-col>
          </v-row>
        </v-card-text>
        <v-card-actions
            class="px-0">
          <v-row>
            <v-col cols="12" xs="12" sm="12" md="7">
              <v-row>
                <v-col
                    cols="12"
                    sm="6">
                  <v-btn
                      text
                      nuxt
                      to="/auth/signIn"
                      color="primary"
                  >
                    登录现有账号
                  </v-btn>
                </v-col>
                <v-col
                    cols="12"
                    sm="6"
                    class="text-right">
                  <v-btn
                      depressed
                      color="primary"
                      @click="next"
                  >
                    创建账号
                  </v-btn>
                </v-col>
              </v-row>
            </v-col>
            <v-col cols="12" xs="0" sm="0" md="5" class="d-none d-md-block"></v-col>
          </v-row>
        </v-card-actions>
      </v-card>
      <div style="margin-top: 15px;"></div>
      <v-card
          :width="width"
          flat
          class="mx-auto"
          style="margin-bottom: 300px;"
      >
        <v-row
            class="text-caption"
        >
          <v-col
              cols="12"
              sm="6"
          >
          </v-col>
          <v-col
              cols="12"
              sm="6"
              class="text-right font-weight-regular"
          >
            <ul class="foot-list foot-list-right">
              <li>
                <a>帮助</a>
                <a>隐私权</a>
                <a>条款</a>
              </li>
            </ul>
          </v-col>
        </v-row>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import snackbar from "~/store/snackbar";

export default {
  name: 'SigInPage',
  layout: function (context) {
    return 'default/index'
  },
  components: {},
  data: () => ({
    loading: false,
    pageLogo: "https://cdn.renfei.net/Logo/RF.svg",
    form: {
      firstname: "",
      lastname: "",
      username: "",
      email: "",
      password: "",
      password2: "",
    },
    error: {
      usernameErrorMessages: "",
      emailErrorMessages: "",
      passwordErrorMessages: "",
    },
  }),
  methods: {
    next() {
      this.$renfeid.info(this,"sss")
      this.loading = true
      this.clearError()
      if (this.form.username === "") {
        this.error.usernameErrorMessages = "用户名不能为空"
        this.loading = false
        return;
      }
      if (this.form.email === "") {
        this.error.emailErrorMessages = "电子邮箱不能为空"
        this.loading = false
        return;
      }
      if (this.form.password === "") {
        this.error.passwordErrorMessages = "密码不能为空"
        this.loading = false
        return;
      }
      if (this.form.password !== this.form.password2) {
        this.error.passwordErrorMessages = "密码不一致"
        this.loading = false
        return;
      }
    },
    clearError() {
      this.error.usernameErrorMessages = ""
      this.error.passwordErrorMessages = ""
      this.error.emailErrorMessages = ""
    },
  },
  head() {
    return {
      title: "创建您的账号",
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
  computed: {
    width() {
      switch (this.$vuetify.breakpoint.name) {
        case 'xs':
          return '100%'
        case 'sm':
          return 450
        case 'md':
        case 'lg':
        case 'xl':
          return 750
      }
    },
    outlined() {
      switch (this.$vuetify.breakpoint.name) {
        case 'xs':
          return false
        case 'sm':
        case 'md':
        case 'lg':
        case 'xl':
          return true
      }
    },
  },
}
</script>
