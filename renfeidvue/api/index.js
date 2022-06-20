// api模块化自动加载
/**
 *** 这里要注意 下面代码匹配的文件,是名称内包含 Api.js 的文件
 *** 意味着要想自动加载 api 其他文件的名称 必须以Api.js 结尾
 ***	例如 loginApi.js  或者 userApi.js
 ***	如果不想以Api.js 结尾来命名
 *** 需要修改 正则表达式为
 *** const files = require.context('./', true, /\.js$/)
 *** 并且在循环里面判断如果是index.js 就return
 *** 也就是剔除index.js
 *** 博主这里用的是以Api.js 结尾来命名文件的方式
 **/
const files = require.context('./', true, /\Api.js$/)
// eslint-disable-next-line import/no-mutable-exports
let apiEntire = {}
files.keys().forEach((key) => {
    const tmp = files(key).default
    apiEntire = { ...apiEntire, ...tmp }
})

export default apiEntire
