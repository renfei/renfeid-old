import Vue from 'vue'

const renfeid = {
    install(Vue) {
        Vue.prototype.renfeid = {
            logo: 'https://cdn.renfei.net/Logo/RF.svg'
        }
    }
}

Vue.use(renfeid)
