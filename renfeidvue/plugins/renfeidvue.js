import Vue from 'vue'

const renfeid = {
    install(Vue) {
        Vue.prototype.$renfeid = {
            logo: 'https://cdn.renfei.net/Logo/RF.svg',
            // 上传文件最大尺寸 10MB
            uploadMaxSize: 1024 * 1024 * 10,
            uploadMaxSizeText: "10MB",
            ok: function (content, msg) {
                content.$store.dispatch('snackbar/openSnackbar', {
                    msg: msg,
                    color: 'success'
                })
            },
            info: function (content, msg) {
                content.$store.dispatch('snackbar/openSnackbar', {
                    msg: msg,
                    color: 'primary'
                })
            },
            error: function (content, msg) {
                content.$store.dispatch('snackbar/openSnackbar', {
                    msg: msg,
                    color: 'red accent-2'
                })
            },
        }
    }
}

Vue.use(renfeid)
