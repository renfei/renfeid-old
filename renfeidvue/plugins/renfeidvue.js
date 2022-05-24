import Vue from 'vue'

let renfeidvue = {
  install(Vue) {
    Vue.prototype.renfeidvue = {
      title: function () {
        return "任霏的个人博客与网站"
      },
      adsenseClientId: function () {
        return "xse"
      }
    };

  }
};
Vue.use(renfeidvue);
