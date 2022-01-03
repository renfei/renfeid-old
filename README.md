# renfeid

[![License](https://img.shields.io/github/license/renfei/renfeid)](https://github.com/renfei/renfeid/blob/master/LICENSE)
[![Actions Status](https://github.com/renfei/renfeid/workflows/CI/badge.svg)](https://github.com/renfei/renfeid/actions)
[![pipeline status](https://gitlab.com/renfei/renfeid/badges/master/pipeline.svg)](https://gitlab.com/renfei/renfeid/-/pipelines)
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
| Java | 基础设施 | JDK1.8 | 本程序基于JVM运行 |
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

## License

[Apache-2.0 License](https://www.apache.org/licenses/LICENSE-2.0)

由于 MySQL 是 GPL License，所以已经移除所有 MySQL 的依赖，全部使用 MariaDB 来兼容 MySQL。

[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Frenfei%2Frenfeid.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Frenfei%2Frenfeid?ref=badge_large)