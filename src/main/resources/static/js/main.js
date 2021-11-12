var app = new Vue({
    el: '#renfei',
    vuetify: new Vuetify(),
    data: function () {
        return {}
    },
    beforeMount() {
        let isDark = getLocalStore("isDark");
        if (isDark !== null && isDark !== undefined && isDark !== "" && isDark === "true") {
            this.$vuetify.theme.dark = true;
        }
    },
    methods: {
        setTheme() {
            app.$vuetify.theme.dark = !app.$vuetify.theme.isDark;
            setLocalStore("isDark", app.$vuetify.theme.isDark);
        }
    }
});