# renfeid

[![License](https://img.shields.io/github/license/renfei/renfeid)](https://github.com/renfei/renfeid/blob/master/LICENSE)
[![Actions Status](https://github.com/renfei/renfeid/workflows/CI/badge.svg)](https://github.com/renfei/renfeid/actions)
[![codecov](https://codecov.io/gh/renfei/renfeid/branch/master/graph/badge.svg?token=2Hd5NL3fnV)](https://codecov.io/gh/renfei/renfeid)
[![Coverage Status](https://coveralls.io/repos/github/renfei/renfeid/badge.svg?branch=master)](https://coveralls.io/github/renfei/renfeid?branch=master)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/945285e334094d2f93643778bb4c8dd7)](https://www.codacy.com/gh/renfei/renfeid/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=renfei/renfeid&amp;utm_campaign=Badge_Grade)

## 简介

这是任霏的个人网站（[www.renfei.net](https://www.renfei.net)），一直保持一年一次大更新，所以这个版本是为2022年准备的。

代号 renfeid 是因为参考了一部分 DDD 领域设计，从之前的贫血模型到现在的充血模型的应用；并且 Linux 中的服务都已 d 结尾来表示 daemon，伴随着系统的启动而启动，系统的关闭而结束，在后台等待执行的进程。

### 历史版本

随着知识不断的累积，每年都看不上去年自己的设计和代码，总想推翻重来，慢慢形成了一年一次大更新的习惯。同时这也是我的试验场，新技术和理念在这里尝试和学习，掌握后用于公司内的商用项目中。

- 2021年：[https://github.com/renfei/renfeid](https://github.com/renfei/renfeid) (当前版本)
- 2020年：[https://github.com/renfei/www.renfei.net](https://github.com/renfei/www.renfei.net) (Java)
- 2019年：[https://github.com/renfei/RenFei.Net](https://github.com/renfei/RenFei.Net) (Java)
- 2017年：[https://github.com/renfei/NEILREN4J](https://github.com/renfei/NEILREN4J) (Java)
- 2015年：[https://github.com/renfei/NeilNT](https://github.com/renfei/NeilNT) (ASP.NET)
- 2012~2014年：WordPress （PHP）

## 技术栈

| 依赖 | 类型 | 版本 | 说明 |
| ----: | :----: | :----: | :---- |
| Java | 基础设施 | JDK1.8 | 任他版本怎么发，我用Java8（考虑升到JDK11） |
| SpringBoot | Framework | 2.6.2 | 我会保持最新版本 |
| Druid | 数据库连接池 | 1.2.8 | 来自 Alibaba 的开源数据库连接池 |
| MyBatis | ORM | 2.2.1 | 选用 MyBatis 写 SQL 更自由 |
| Feign | HttpClient | 3.1.0 | 虽然它来自SpringCloud，但我很喜欢用它 |
| MariaDB | DataBase | 10.6.5 | MySQL 的最佳替代者，我使用最新版本 |
| JWT | Token | - | 我使用JWT支持无状态接口，支持分布式部署 |
| Redis | NoSQL | 6.2.6 | 用于缓存加速，数据变化不频繁的扔到内存里 |
| ElasticSearch | 搜索引擎 | 7.16.2 | 站内搜索就是依赖 ES 搜索引擎实现的 |
| Freemarker | 视图引擎 | 2.3.31 | 我是放弃 Thymeleaf 又换回来的，不解释 |
| Quartz | 定时任务 | 2.3.2 | 为了支持分布式部署，本地定时任务不能用 |
| SpringSecurity | 安全框架 | 5.6.1 | Spring全家桶，各种权限判断和拦截就是基于它的 |
| Leaf | 全局发号器 | - | 美团技术团队的开源分布式ID生成，参考雪花算法 |

### 一些计划和主张

- [x] 已移除 Lombok，一旦使用整个团队其他人也必须安装插件，强X队友；
同时在慢慢使用中发现除非真的非常了解 Lombok 的注解会生成什么，否则会出现奇怪的各种问题，当然只使用简单的```@Data```没什么问题。
- [ ] 计划升级到 Java11，虽然 Java8 完全满足需求，但毕竟新的东西会优化很多旧的问题。

### DevOps 和 CI/CD 的探索

之前使用 GitHub 的 Actions Workflow，但因为是境外所以和境内的相关服务器网络延迟高失败率高，一直没什么太大用处，
直到 2021年底 GitLab 在国内成立了分公司并上线了 [极狐(GitLab)](https://gitlab.cn) SaaS 平台，一群开源大佬布道中国的 DevOps，
我正好参与了内测，开始接触并尝试探索大佬们的理念。

项目中的 [.gitlab-ci.yml](./.gitlab-ci.yml) 就是 GitLab 的 Pipeline 文件，主要由这几个阶段 (stage) 组成：

- initialize：初始化准备环境，包括下载测试环境配置文件、[IP2Location](https://github.com/renfei/ip2location) 数据 BIN 文件，将 artifact 向下面的阶段传递。
- compile：编译，先执行编译确保没有基础的语法错误，如果编译都过不了下面的就不用执行了。
- test：测试，执行单元测试，验证各个接口和服务可以正常运行。
- package：打包，将 SpringBoot 应用打包成 Jar 包。
- release：发布，构建 Docker 镜像，发布到制品仓库中

自动部署方面，我还没探索，后面等着尝试探索一下。

#### 安全风险告知

如果您也使用 GitLab Pipeline，注意项目可访问性，或者设置 CI/CD 的可访问性，如果都是公开的状态，
那么每个阶段 (stage) 的制品 (artifact) 将会公开下载！其中一旦包含配置文件或相关敏感文件将被公开。

## License

[Apache-2.0 License](https://www.apache.org/licenses/LICENSE-2.0)

虽然我这点代码在开源生态里就是芝麻渣渣，但是出于对各路大佬的尊重和敬仰，我同样遵守开源圈的 License 协议，我选用 Apache-2.0 License。

由于 MySQL 是 GPL License，所以已经移除所有 MySQL 的依赖，全部使用 MariaDB 来兼容 MySQL。

[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Frenfei%2Frenfeid.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Frenfei%2Frenfeid?ref=badge_large)