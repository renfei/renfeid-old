<template>
  <v-footer
      app
      absolute
      style="margin-top: 300px;padding-top: 30px;"
  >
    <div class="footer_image" style="background: url('/image/footer_image.webp') 50% center no-repeat;"></div>
    <v-container fluid>
      <v-row>
        <v-col class="mx-auto" cols="12" xs="12" sm="12" md="10" lg="10" xl="9">
          <v-row>
            <v-col cols="12" xs="12" sm="6" md="6" lg="3" xl="3">
              <h3 class="py-5">社会化互动</h3>
              asdgadsg
            </v-col>
            <v-col cols="12" xs="12" sm="6" md="6" lg="3" xl="3">
              <h3 class="py-5">公益与捐赠</h3>
              asdgadsg
            </v-col>
            <v-col cols="12" xs="12" sm="6" md="6" lg="3" xl="3">
              <h3 class="py-5">推荐厂商</h3>
              asdgadsg
            </v-col>
            <v-col cols="12" xs="12" sm="6" md="6" lg="3" xl="3">
              <h3 class="py-5">其他内容</h3>
              asdgadsg
            </v-col>
          </v-row>
          <v-row>
            <v-col class="text-right text-caption">
              <ul class="foot-list foot-list-right">
                <li>
                  <a href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=13070902000275" target="_blank"
                     rel="nofollow noopener">冀公网安备13070902000275号</a>
                </li>
                <li>
                  <a href="https://beian.miit.gov.cn" target="_blank" rel="nofollow noopener">冀ICP备12003293号-9</a>
                </li>
              </ul>
            </v-col>
          </v-row>
          <hr style="margin: 7px 0;"/>
          <v-row>
            <v-col class="text-caption">
              <ul class="foot-list">
                <li>
                  <span style="color: #6c757d;">Copyright &copy; 2012-{{ new Date().getFullYear() }} RENFEI.NET All rights reserved.</span>
                </li>
              </ul>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
  </v-footer>
</template>

<script>
export default {
  name: "DefaultFooter",
}
</script>
