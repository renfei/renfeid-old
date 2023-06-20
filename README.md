# renfeid

[![License](https://img.shields.io/github/license/renfei/renfeid)](https://github.com/renfei/renfeid/blob/master/LICENSE)

这是 [任霏的个人博客与网站](https://www.renfei.net) 的代码库，能进到这个代码库说明你很有品位嘛。

预览版本见如下链接，代码仓库提交后将自动部署到预览版网站：

* 前台：[https://preview.renfei.net](https://preview.renfei.net)
* 后台：[https://preview.renfei.net/dashboard](https://preview.renfei.net/dashboard)

我的网站结构为前后端分离，所以会产生前后端两个子项目：

| 后端                                                                             | 前端                                                                                       |
|--------------------------------------------------------------------------------|------------------------------------------------------------------------------------------|
| 所在目录：[renfeid-service](./renfeid-service)                                      | 所在目录：[renfeid-ui](./renfeid-ui)                                                          |
| 技术栈：Java17、SpringBoot3.x、MyBatis、MariaDB、Druid、RestfulAPI、Maven                | 技术栈： Node.js、 React、 Next.js、AntD                                                        |
| 简要说明：尝试过SpringCloud微服务架构，但对于个人网站来说过于沉重和耗费资源，所以我用maven多模块的方式进行了拆分，将来可以改造为微服务架构。 | 简要说明：之前一直没有使用Vue/React是因为SPA对SEO不友好，但随着前端技术的完善，Next的SSR服务器端渲染已经很成熟了，本次是我第一次尝试前台也使用React。 |
| 自我点评：采用前后端分离架构，是目前行业比较流行的结构，SpringBoot也是最新版本的依赖。                               | 自我点评：本人非前端专业，只是业余学习，很可能使用姿势不符合行业规范，前端项目仅供参考。                                             |

## 注意事项

后端的主键ID一般为 Long 类型，在 Java 中是 64位整数型，而在 JavaScript 中数字是 64位浮点数，这就造成了精度不一致！
所以，在 JavaScript 中要使用字符串来处理后端的 Long 类型数字，否则会出现意想不到的情况。

## 目录结构

### 后端项目目录结构

为啥不选微服务？我尝试过微服务架构，并不适合我，微服务过于沉重，并且在低并发的场景中性能并不如单体应用好，并且造成运维代价大幅提升。

那为啥还参考微服务划分多个模块？虽然我的场景不适合微服务，但我的程序保留微服务化的能力，如果需要改造可以快速的微服务化、分布式部署。

<details>
<summary>点此展开查看详情</summary>

| 工程名                                  | 描述                      |
|--------------------------------------|-------------------------|
| + renfeid-bpm                        | 流程引擎服务（待开发）             |
| + renfeid-cms                        | 内容管理服务（CMS）             |
| &nbsp;&nbsp; - renfeid-cms-api       | 内容管理服务接口                |
| &nbsp;&nbsp; - renfeid-cms-service   | 内容管理服务实现                |
| + renfeid-common                     | 通用模块                    |
| &nbsp;&nbsp; - renfeid-common-api    | 全局通用的接口与对象              |
| &nbsp;&nbsp; - renfeid-common-bom    | 全局统一制品清单                |
| &nbsp;&nbsp; - renfeid-common-core   | 核心服务                    |
| &nbsp;&nbsp; - renfeid-common-leaf   | 分布式发号器雪花算法（美团实现）        |
| &nbsp;&nbsp; - renfeid-common-search | 搜索引擎服务（ElasticSearch实现） |
| + renfeid-pro                        | 私有功能仅供参考                |
| &nbsp;&nbsp; - renfeid-pro-discuz    | 与Discuz的集成              |
| &nbsp;&nbsp; - renfeid-pro-wechat    | 与微信公众号的集成               |
| &nbsp;&nbsp; - 其他（工具箱、微博、相册等）        | 闭源管理                    |
| + renfeid-server                     | 服务入口（类似微服务的网关）          |
| + renfeid-uaa                        | 用户认证与鉴权                 |
| &nbsp;&nbsp; - renfeid-uaa-api       | UAA暴露的接口                |
| &nbsp;&nbsp; - renfeid-uaa-service   | UAA服务实现                 |
| + mybatis-generator                  | mybatis dao层生成          |

</details>

关于 ```renfeid-pro``` 模块的说明，这个模块中是我自己网站特有的功能，你可以直接删除掉。 ```mybatis-generator```
模块只是为了方便自动生成 mybatis
dao层，没有实际的作用，可以删除。

### 前端项目目录结构

为啥选择了 React？ 我作为后端开发，尝试了 Vue 和 React，最后我觉得 React 更像后端开发，我更喜欢、看着舒服、用着顺手，所以就选择了
React。

<details>
<summary>点此展开查看详情</summary>

| 工程名                                  | 描述    |
|--------------------------------------|-------|
| + components                         | 组件目录  |
| &nbsp;&nbsp; + layout                | 前台布局  |
| &nbsp;&nbsp;&nbsp;&nbsp; - dashboard | 后台布局  |
| + pages                              | 页面    |
| &nbsp;&nbsp; - api                   | API接口 |
| &nbsp;&nbsp; - 其他                    | 页面实现  |
| + public                             | 静态文件  |
| + services                           | 服务    |
| + styles                             | 样式    |
| + utils                              | 工具    |

</details>

## 构建

### 后端项目构建

后端使用 ```Maven``` 来管理依赖，如果你已经安装了 ```Maven``` 可以直接使用 mvn 命令，如果你的机器上没有安装  ```Maven```
，那么你可以使用目录中自带的 ```mvnw```
来代替 ```mvn``` 命令。

<details>
<summary>点此展开查看构建命令</summary>

首先，进入后端端项目的 ```renfeidboot``` 目录里执行下面的命令：

```bash
# 清除缓存
mvn clean

# 编译项目
mvn compile

# 执行单元测试
mvn test

# 项目打包
mvn package
```

</details>

### 前端项目构建

前端我使用 ```npm``` 包管理器。

<details>
<summary>点此展开查看构建命令</summary>

首先，进入前端项目的 ```renfeid-ui``` 目录里执行下面的命令：

```bash
# install dependencies
$ npm install

# serve with hot reload at localhost:3000
$ npm run dev

# build for production and launch server
$ npm run build
$ npm run start
```

</details>

## 涉密应用系统"三员"

系统设计时采用了系统管理员、安全保密管理员、安全审计管理员三员分立，分别负责系统的运行、安全保密和安全审计工作。三员权限划分如下：

> 【注意】涉密系统需要重新审查前端项目！！
>
> 前端依赖第三方包过多，导致我无法逐一审查，目前已知的是项目中使用的富文本编辑器 TinyMCE 会要求联网
>
> 所以涉密系统需要更换富文本编辑器 TinyMCE，其他请自查

<details>
<summary>点此展开查看详情</summary>

### 系统管理员

* 负责系统的日常运行维护工作
* 负责系统用户创建、用户删除

### 安全保密管理员

* 负责系统的日常安全保密管理工作
* 负责系统用户修改、用户密码重置、用户停启
* 负责系统用户的角色分配、角色的功能资源分配
* 负责管理与审查系统用户及安全审计管理员的操作日志

### 安全审计管理员

* 负责对系统管理员和安全保密管理员的日常操作行为进行审计跟踪分析和监督检查
* 审计管理员禁止访问管理平台安装的系统文件和直接访问数据库
* 禁止执行其它项目管理平台管理工作

</details>
