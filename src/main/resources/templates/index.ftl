<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/@mdi/font@6.x/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
</head>
<body>
<div id="renfei">
    <v-app>
        <v-app-bar app flat>
            <v-app-bar-nav-icon>xx</v-app-bar-nav-icon>
            <v-spacer></v-spacer>
            <v-menu open-on-hover offset-y close-on-content-click>
                <template v-slot:activator="{ on, attrs }">
                    <v-btn
                            v-bind="attrs"
                            v-on="on"
                            icon
                    >
                        <v-icon>mdi-translate</v-icon>
                    </v-btn>
                </template>
                <v-list
                        nav
                        dense
                >
                    <v-list-item-group>
                        <v-list-item>
                            <v-list-item-content>
                                <v-list-item-title>xxxx</v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>
                        <v-list-item>
                            <v-list-item-content>
                                <v-list-item-title>xxxx</v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>
                        <v-list-item>
                            <v-list-item-content>
                                <v-list-item-title>xxxx</v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>
                    </v-list-item-group>
                </v-list>
            </v-menu>
            <v-btn icon @click="setTheme">
                <v-icon>mdi-theme-light-dark</v-icon>
            </v-btn>
        </v-app-bar>
        <v-main>
            <v-container>Hello world</v-container>
        </v-main>
    </v-app>
</div>

<script src="https://cdn.jsdelivr.net/npm/vue@2.x/dist/vue.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.js"></script>
<script src="/js/util/Storage.js"></script>
<script src="/js/main.js"></script>
<script>
</script>
</body>
</html>