export function dark(vuetify, isDark) {
  if (isDark === undefined) {
    if (window.matchMedia) {
      // 支持，跟随系统设置
      vuetify.theme.dark = window.matchMedia('(prefers-color-scheme: dark)').matches
      window.matchMedia('(prefers-color-scheme: dark)')
        .addEventListener('change', event => {
          if (event.matches) {
            vuetify.theme.dark = window.matchMedia('(prefers-color-scheme: dark)').matches
          } else {
            vuetify.theme.dark = window.matchMedia('(prefers-color-scheme: dark)').matches
          }
        })
    } else {
      // 不支持，取用户自定义的设置
    }
  } else {
    vuetify.theme.dark = isDark;
  }
}
