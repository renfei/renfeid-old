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
          <v-img class="mx-auto"
                 :src="pageLogo"
                 height="48"
                 width="48"
                 max-height="48"
                 max-width="48"
                 alt="logo"/>
        </v-card-title>
        <v-card-subtitle>
          <v-row
              v-if="showUsername"
          >
            <v-col
                cols="12"
                class="text-center font-weight-black py-1 text-h5">
              登录
            </v-col>
            <v-col
                cols="12"
                class="text-center font-weight-black py-1">
              使用您的 RENFEI.NET 帐号
            </v-col>
          </v-row>
          <v-row
              v-if="showPassword"
          >
            <v-col
                cols="12"
                class="text-center font-weight-black py-1 text-h5">
              欢迎
            </v-col>
            <v-col
                cols="12"
                class="text-center font-weight-black py-1">
              <v-btn
                  depressed
                  outlined
                  rounded
                  color="grey darken-1"
                  @click="backUsername"
              >
                <v-icon left>
                  mdi-account-circle
                </v-icon>
                {{ this.form.username }}
              </v-btn>
            </v-col>
          </v-row>
          <v-row
              v-if="show2FA"
          >
            <v-col
                cols="12"
                class="text-center font-weight-black py-1 text-h5">
              两步验证
            </v-col>
            <v-col
                cols="12"
                class="text-center font-weight-black py-1">
              为了保护您的帐号安全，我们需要确认是您本人在登录
            </v-col>
            <v-col
                cols="12"
                class="text-center font-weight-black py-1">
              <v-btn
                  depressed
                  outlined
                  rounded
                  color="grey darken-1"
                  @click="backUsername"
              >
                <v-icon left>
                  mdi-account-circle
                </v-icon>
                {{ this.form.username }}
              </v-btn>
            </v-col>
          </v-row>
        </v-card-subtitle>
        <v-card-text class="py-3 px-0">
          <v-row
              v-if="showUsername">
            <v-col
                cols="12"
                class="pb-0">
              <v-text-field
                  label="用户名"
                  outlined
                  dense
                  v-model="form.username"
                  :error-messages="error.usernameErrorMessages"
              ></v-text-field>
            </v-col>
            <v-col
                cols="12"
                class="pt-0 px-0">
              <v-btn
                  text
                  color="primary"
              >
                忘记了用户名？
              </v-btn>
            </v-col>
          </v-row>
          <v-row
              v-if="showPassword">
            <v-col
                cols="12"
                class="pb-0">
              <v-text-field
                  label="输入您的密码"
                  outlined
                  dense
                  :append-icon="form.showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                  :type="form.showPassword ? 'text' : 'password'"
                  @click:append="form.showPassword = !form.showPassword"
                  v-model="form.password"
                  :error-messages="error.passwordErrorMessages"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row
              v-if="show2FA">
            <v-col
                cols="12"
                class="font-weight-black py-1">
              从身份验证器APP中获取动态验证码
            </v-col>
            <v-col
                cols="12"
                class="pb-0">
              <v-otp-input
                  v-model="form.totp"
                  length="6"
              ></v-otp-input>
            </v-col>
          </v-row>
        </v-card-text>
        <v-card-actions
            class="px-0">
          <v-row
              v-if="showUsername">
            <v-col
                cols="12"
                sm="6"
                class="px-0">
              <v-btn
                  text
                  nuxt
                  to="/auth/signUp"
                  color="primary"
              >
                创建账号
              </v-btn>
            </v-col>
            <v-col
                cols="12"
                sm="6"
                class="text-right">
              <v-btn
                  depressed
                  color="primary"
                  @click="usernameNext"
              >
                下一步
              </v-btn>
            </v-col>
          </v-row>
          <v-row
              v-if="showPassword">
            <v-col
                cols="12"
                sm="6"
                class="px-0">
              <v-btn
                  text
                  color="primary"
              >
                忘记了密码？
              </v-btn>
            </v-col>
            <v-col
                cols="12"
                sm="6"
                class="text-right">
              <v-btn
                  depressed
                  color="primary"
                  @click="passwordNext"
              >
                下一步
              </v-btn>
            </v-col>
          </v-row>
          <v-row
              v-if="show2FA">
            <v-col
                cols="12"
                sm="6"
                class="px-0">
              <v-btn
                  text
                  color="primary"
              >
                验证器丢失？
              </v-btn>
            </v-col>
            <v-col
                cols="12"
                sm="6"
                class="text-right">
              <v-btn
                  depressed
                  color="primary"
                  @click="totpNext"
              >
                下一步
              </v-btn>
            </v-col>
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
export default {
  name: 'SigInPage',
  layout: function (context) {
    return 'default/index'
  },
  data: () => ({
    loading: false,
    pageLogo: "https://cdn.renfei.net/Logo/RF.svg",
    form: {
      username: "",
      password: "",
      totp: "",
      showPassword: false,
    },
    error: {
      usernameErrorMessages: "",
      passwordErrorMessages: "",
      totpErrorMessages: "",
    },
    showUsername: true,
    showPassword: false,
    show2FA: false,
  }),
  methods: {
    usernameNext() {
      this.clearError()
      if (this.form.username === "") {
        this.error.usernameErrorMessages = "用户名不能为空"
        return;
      }
      this.showUsername = false
      this.showPassword = true
    },
    passwordNext() {
      this.clearError()
      if (this.form.password === "") {
        this.error.passwordErrorMessages = "密码不能为空"
        return;
      }
      this.showUsername = false
      this.showPassword = false
      this.show2FA = true
    },
    totpNext() {
      this.clearError()
      if (this.form.totp === "") {
        this.error.totpErrorMessages = "验证码不能为空"
        return;
      }
      this.showUsername = false
      this.showPassword = false
      this.show2FA = true
    },
    backUsername() {
      this.showUsername = true
      this.showPassword = false
      this.show2FA = false
    },
    clearError() {
      this.error.usernameErrorMessages = ""
      this.error.passwordErrorMessages = ""
      this.error.totpErrorMessages = ""
    },
  },
  head: () => ({
    title: "登录",
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
  computed: {
    width() {
      switch (this.$vuetify.breakpoint.name) {
        case 'xs':
          return '100%'
        case 'sm':
        case 'md':
        case 'lg':
        case 'xl':
          return 450
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
