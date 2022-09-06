-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: renfeid
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.6-MariaDB-1:10.4.6+maria~bionic

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `QRTZ_BLOB_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `BLOB_DATA` blob DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_BLOB_TRIGGERS`
--

LOCK TABLES `QRTZ_BLOB_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_BLOB_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_BLOB_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_CALENDARS`
--

DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(190) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_CALENDARS`
--

LOCK TABLES `QRTZ_CALENDARS` WRITE;
/*!40000 ALTER TABLE `QRTZ_CALENDARS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_CALENDARS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_CRON_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_CRON_TRIGGERS`
--

LOCK TABLES `QRTZ_CRON_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_CRON_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_CRON_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_JOB_DETAILS`
--

DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(190) NOT NULL,
  `JOB_GROUP` varchar(190) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_JOB_DETAILS`
--

LOCK TABLES `QRTZ_JOB_DETAILS` WRITE;
/*!40000 ALTER TABLE `QRTZ_JOB_DETAILS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_JOB_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_LOCKS`
--

DROP TABLE IF EXISTS `QRTZ_LOCKS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_LOCKS`
--

LOCK TABLES `QRTZ_LOCKS` WRITE;
/*!40000 ALTER TABLE `QRTZ_LOCKS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_LOCKS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_PAUSED_TRIGGER_GRPS`
--

DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_PAUSED_TRIGGER_GRPS`
--

LOCK TABLES `QRTZ_PAUSED_TRIGGER_GRPS` WRITE;
/*!40000 ALTER TABLE `QRTZ_PAUSED_TRIGGER_GRPS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_PAUSED_TRIGGER_GRPS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SCHEDULER_STATE`
--

DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(190) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_SCHEDULER_STATE`
--

LOCK TABLES `QRTZ_SCHEDULER_STATE` WRITE;
/*!40000 ALTER TABLE `QRTZ_SCHEDULER_STATE` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SCHEDULER_STATE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SIMPLE_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_SIMPLE_TRIGGERS`
--

LOCK TABLES `QRTZ_SIMPLE_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_SIMPLE_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SIMPLE_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SIMPROP_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_SIMPROP_TRIGGERS`
--

LOCK TABLES `QRTZ_SIMPROP_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_SIMPROP_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SIMPROP_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_category`
--

DROP TABLE IF EXISTS `cms_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_category` (
  `id` bigint(20) unsigned NOT NULL,
  `en_name` varchar(255) NOT NULL COMMENT '英文名',
  `zh_name` varchar(255) NOT NULL COMMENT '中文名',
  `secret_level` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '保密等级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='内容分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_category`
--

LOCK TABLES `cms_category` WRITE;
/*!40000 ALTER TABLE `cms_category` DISABLE KEYS */;
INSERT INTO `cms_category` VALUES (1,'default','默认分类',0),(1551850495436390445,'test','测试分类',0);
/*!40000 ALTER TABLE `cms_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_posts`
--

DROP TABLE IF EXISTS `cms_posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_posts` (
  `id` bigint(20) unsigned NOT NULL,
  `category_id` bigint(20) unsigned NOT NULL COMMENT '分类ID',
  `post_author_username` varchar(255) NOT NULL COMMENT '作者用户名',
  `post_author` bigint(20) unsigned NOT NULL COMMENT '作者ID',
  `post_date` datetime NOT NULL COMMENT '发布时间',
  `post_status` varchar(255) NOT NULL DEFAULT 'PUBLISH' COMMENT '状态：PUBLISH/REVISION/DELETED/OFFLINE/REVIEW',
  `post_views` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '浏览量',
  `comment_status` varchar(255) NOT NULL DEFAULT 'OPENED' COMMENT '评论状态：OPENED/CLOSED',
  `post_password` varchar(255) DEFAULT NULL COMMENT '密码保护',
  `post_modified` datetime NOT NULL COMMENT '修改时间',
  `post_modified_username` varchar(255) NOT NULL COMMENT '修改人用户名',
  `post_modified_user` bigint(20) unsigned NOT NULL COMMENT '修改人',
  `post_parent` bigint(20) unsigned DEFAULT NULL COMMENT '父级ID（归档使用）',
  `version_number` int(10) unsigned NOT NULL DEFAULT 1 COMMENT '版本',
  `thumbs_up` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点赞量',
  `thumbs_down` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点踩量',
  `avg_views` double DEFAULT NULL COMMENT '平均浏览量',
  `avg_comment` double DEFAULT NULL COMMENT '平均评论量',
  `page_rank` double DEFAULT NULL COMMENT '排序权重',
  `secret_level` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '保密等级',
  `is_original` tinyint(1) unsigned NOT NULL DEFAULT 1 COMMENT '是否原创',
  `featured_image` text NOT NULL COMMENT '特色图像',
  `post_title` varchar(255) NOT NULL COMMENT '标题',
  `post_keyword` varchar(100) DEFAULT NULL COMMENT '关键词',
  `post_excerpt` varchar(512) DEFAULT NULL COMMENT '摘要',
  `post_content` longtext NOT NULL COMMENT '内容',
  `source_name` varchar(255) DEFAULT NULL COMMENT '原作者',
  `source_url` varchar(255) DEFAULT NULL COMMENT '原链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='内容表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_posts`
--

LOCK TABLES `cms_posts` WRITE;
/*!40000 ALTER TABLE `cms_posts` DISABLE KEYS */;
INSERT INTO `cms_posts` VALUES (1550378678502817852,1,'renfei',1,'2022-07-22 15:10:30','PUBLISH',1,'OPENED',NULL,'2022-07-22 15:10:30','renfei',1,0,1,0,0,0,0,0,0,1,'https://cdn.renfei.net/upload/2022/6367480712d74b1a9cd6275335061761.png','测试','','测试','<p>测试测试测试测试</p>',NULL,NULL),(1550392946501615687,1,'renfei',1,'2022-07-22 16:08:27','PUBLISH',18,'OPENED',NULL,'2022-07-25 17:05:09','renfei',1,0,6,0,0,0,0,0,0,1,'https://cdn.renfei.net/upload/2022/0956a76c2a484c6daa9cd4b805fe123c.png','测试2','','测试2','<p>测试2测试2测试2测试2测试2测试2</p>','测试2','测试2'),(1550397187458859030,1551850495436390445,'renfei',1,'2022-07-22 16:08:27','PUBLISH',42,'OPENED',NULL,'2022-07-26 16:57:42','renfei',1,0,8,0,0,0,0,0,0,1,'https://cdn.renfei.net/upload/2022/8d7269255fc444d5a95736ca7a378cad.png','测试3','','测试2','<p>测试2测试2测试2测试2测试2测试2</p>','测试2','测试2'),(1554363560300118086,1,'renfei',1,'2022-08-02 15:07:06','PUBLISH',6,'OPENED',NULL,'2022-08-02 15:20:48','renfei',1,0,2,0,0,0,0,0,0,1,'https://cdn.renfei.net/upload/2022/858ba8877463409688b00a114d304d21.png','测试','','测试','<p>测试</p>','测试','测试');
/*!40000 ALTER TABLE `cms_posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_posts_archival`
--

DROP TABLE IF EXISTS `cms_posts_archival`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_posts_archival` (
  `id` bigint(20) unsigned NOT NULL,
  `category_id` bigint(20) unsigned NOT NULL COMMENT '分类ID',
  `post_author_username` varchar(255) NOT NULL COMMENT '作者用户名',
  `post_author` bigint(20) unsigned NOT NULL COMMENT '作者ID',
  `post_date` datetime NOT NULL COMMENT '发布时间',
  `post_status` varchar(255) NOT NULL DEFAULT 'PUBLISH' COMMENT '状态：PUBLISH/REVISION/DELETED/OFFLINE/REVIEW',
  `post_views` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '浏览量',
  `comment_status` varchar(255) NOT NULL DEFAULT 'OPENED' COMMENT '评论状态：OPENED/CLOSED',
  `post_password` varchar(255) DEFAULT NULL COMMENT '密码保护',
  `post_modified` datetime NOT NULL COMMENT '修改时间',
  `post_modified_username` varchar(255) NOT NULL COMMENT '修改人用户名',
  `post_modified_user` bigint(20) unsigned NOT NULL COMMENT '修改人',
  `post_parent` bigint(20) unsigned DEFAULT NULL COMMENT '父级ID（归档使用）',
  `version_number` int(10) unsigned NOT NULL DEFAULT 1 COMMENT '版本',
  `thumbs_up` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点赞量',
  `thumbs_down` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点踩量',
  `avg_views` double DEFAULT NULL COMMENT '平均浏览量',
  `avg_comment` double DEFAULT NULL COMMENT '平均评论量',
  `page_rank` double DEFAULT NULL COMMENT '排序权重',
  `secret_level` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '保密等级',
  `is_original` tinyint(1) unsigned NOT NULL DEFAULT 1 COMMENT '是否原创',
  `featured_image` text NOT NULL COMMENT '特色图像',
  `post_title` varchar(80) NOT NULL COMMENT '标题',
  `post_keyword` varchar(100) DEFAULT NULL COMMENT '关键词',
  `post_excerpt` varchar(200) DEFAULT NULL COMMENT '摘要',
  `post_content` longtext NOT NULL COMMENT '内容',
  `source_name` varchar(255) DEFAULT NULL COMMENT '原作者',
  `source_url` varchar(255) DEFAULT NULL COMMENT '原链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='内容表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_posts_archival`
--

LOCK TABLES `cms_posts_archival` WRITE;
/*!40000 ALTER TABLE `cms_posts_archival` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_posts_archival` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_posts_tag`
--

DROP TABLE IF EXISTS `cms_posts_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_posts_tag` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `post_id` bigint(20) unsigned NOT NULL COMMENT '内容ID',
  `tag_id` bigint(20) unsigned NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='内容与标签关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_posts_tag`
--

LOCK TABLES `cms_posts_tag` WRITE;
/*!40000 ALTER TABLE `cms_posts_tag` DISABLE KEYS */;
INSERT INTO `cms_posts_tag` VALUES (3,1554363560300118086,1);
/*!40000 ALTER TABLE `cms_posts_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_tag`
--

DROP TABLE IF EXISTS `cms_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_tag` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID，不采用分布式发号',
  `en_name` varchar(255) NOT NULL COMMENT '用于访问的英文名',
  `zh_name` varchar(255) NOT NULL COMMENT '用于显示的中文名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='标签';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_tag`
--

LOCK TABLES `cms_tag` WRITE;
/*!40000 ALTER TABLE `cms_tag` DISABLE KEYS */;
INSERT INTO `cms_tag` VALUES (1,'domainname','域名'),(2,'发发发','发发发');
/*!40000 ALTER TABLE `cms_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core_comments`
--

DROP TABLE IF EXISTS `core_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_comments` (
  `id` bigint(20) unsigned NOT NULL,
  `parent_id` bigint(20) unsigned DEFAULT NULL COMMENT '父级ID',
  `sys_type` varchar(255) NOT NULL COMMENT '系统模块',
  `object_id` bigint(20) unsigned NOT NULL COMMENT '评论对象ID',
  `author_id` bigint(20) unsigned DEFAULT NULL COMMENT '作者ID',
  `addtime` datetime NOT NULL COMMENT '评论时间',
  `is_delete` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否删除',
  `is_owner` tinyint(1) unsigned DEFAULT 0 COMMENT '是否是官方',
  `author` text DEFAULT NULL COMMENT '作者名称',
  `author_email` text DEFAULT NULL COMMENT '作者email',
  `author_url` text DEFAULT NULL COMMENT '作者链接',
  `author_ip` text NOT NULL COMMENT '作者IP',
  `author_address` text DEFAULT NULL COMMENT '作者地址',
  `content` text NOT NULL COMMENT '评论内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_comments`
--

LOCK TABLES `core_comments` WRITE;
/*!40000 ALTER TABLE `core_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `core_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core_logs`
--

DROP TABLE IF EXISTS `core_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_logs` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `log_time` datetime DEFAULT NULL COMMENT '日志时间',
  `log_level` varchar(255) DEFAULT NULL COMMENT '日志等级',
  `log_module` varchar(255) DEFAULT NULL COMMENT '日志模块',
  `log_type` varchar(255) DEFAULT NULL COMMENT '操作类型',
  `requ_method` varchar(255) DEFAULT NULL COMMENT '请求方法',
  `requ_uri` varchar(255) DEFAULT NULL COMMENT '请求地址',
  `requ_referrer` varchar(255) DEFAULT NULL COMMENT '请求来路',
  `user_uuid` varbinary(255) DEFAULT NULL COMMENT '用户UUID',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `requ_ip` varchar(255) DEFAULT NULL COMMENT '请求IP地址',
  `log_desc` text DEFAULT NULL COMMENT '操作描述',
  `requ_param` text DEFAULT NULL COMMENT '请求参数',
  `resp_param` text DEFAULT NULL COMMENT '响应内容',
  `requ_agent` text DEFAULT NULL COMMENT '请求UA',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='日志审计表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_logs`
--

LOCK TABLES `core_logs` WRITE;
/*!40000 ALTER TABLE `core_logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `core_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core_site_friendly_link`
--

DROP TABLE IF EXISTS `core_site_friendly_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_site_friendly_link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sitename` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '站点名称',
  `sitelink` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '站点链接',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `addtime` datetime NOT NULL,
  `audit_pass` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否审核通过',
  `link_type` int(11) NOT NULL DEFAULT 1 COMMENT '交换类型：1对等交换，2交叉交换',
  `in_site_link` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '在对方的链接位置',
  `contact_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系人姓名',
  `contact_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系邮箱',
  `contact_qq` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系QQ',
  `remarks` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `order_id` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='站点友情链接';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_site_friendly_link`
--

LOCK TABLES `core_site_friendly_link` WRITE;
/*!40000 ALTER TABLE `core_site_friendly_link` DISABLE KEYS */;
INSERT INTO `core_site_friendly_link` VALUES (1,'任霏博客','https://www.renfei.net/',0,'2019-09-25 09:40:36',1,1,NULL,'任霏','i@renfei.net','16076276',NULL,0);
/*!40000 ALTER TABLE `core_site_friendly_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core_system_setting`
--

DROP TABLE IF EXISTS `core_system_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_system_setting` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `setting_key` varchar(255) NOT NULL COMMENT '设置Key',
  `setting_value` varchar(1000) NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系统设置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_system_setting`
--

LOCK TABLES `core_system_setting` WRITE;
/*!40000 ALTER TABLE `core_system_setting` DISABLE KEYS */;
INSERT INTO `core_system_setting` VALUES (1,'GLOBAL_COMMENT_STATUS','OPENED');
/*!40000 ALTER TABLE `core_system_setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_fired_triggers`
--

DROP TABLE IF EXISTS `qrtz_fired_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `INSTANCE_NAME` varchar(190) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(190) DEFAULT NULL,
  `JOB_GROUP` varchar(190) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_fired_triggers`
--

LOCK TABLES `qrtz_fired_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_fired_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_fired_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_triggers`
--

DROP TABLE IF EXISTS `qrtz_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `JOB_NAME` varchar(190) NOT NULL,
  `JOB_GROUP` varchar(190) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(190) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_triggers`
--

LOCK TABLES `qrtz_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uaa_authority`
--

DROP TABLE IF EXISTS `uaa_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uaa_authority` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `authority_type` varchar(255) DEFAULT NULL COMMENT '授权类型（菜单、接口）',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色ID',
  `target_id` bigint(20) unsigned NOT NULL COMMENT '目标ID',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uaa_authority`
--

LOCK TABLES `uaa_authority` WRITE;
/*!40000 ALTER TABLE `uaa_authority` DISABLE KEYS */;
INSERT INTO `uaa_authority` VALUES (31,'API',1552836803759710216,1,'2022-07-29 12:00:16',NULL),(32,'MENU',1552836803759710216,3,'2022-07-29 12:00:16',NULL),(33,'MENU',1552836803759710216,2,'2022-07-29 12:00:16',NULL),(34,'API',1552219847201390592,1,'2022-07-29 12:01:55',NULL),(35,'MENU',1552219847201390592,1,'2022-07-29 12:01:55',NULL),(36,'MENU',1552219847201390592,2,'2022-07-29 12:01:55',NULL),(37,'MENU',1552219847201390592,3,'2022-07-29 12:01:55',NULL),(38,'MENU',1552219847201390592,4,'2022-07-29 12:01:55',NULL);
/*!40000 ALTER TABLE `uaa_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uaa_menu`
--

DROP TABLE IF EXISTS `uaa_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uaa_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) unsigned DEFAULT NULL COMMENT '父级ID',
  `menu_name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `menu_icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `menu_target` varchar(255) DEFAULT NULL COMMENT '菜单打开方式',
  `menu_class` varchar(255) DEFAULT NULL COMMENT '菜单样式类',
  `menu_title` varchar(255) DEFAULT NULL COMMENT '菜单标题',
  `menu_onclick` varchar(255) DEFAULT NULL COMMENT '菜单点击事件',
  `menu_order` varchar(255) DEFAULT NULL COMMENT '菜单排序',
  `enable` tinyint(1) DEFAULT NULL COMMENT '是否启用',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL ON UPDATE current_timestamp() COMMENT '更新时间',
  `menu_href` text DEFAULT NULL COMMENT '菜单链接',
  `menu_css` text DEFAULT NULL COMMENT '菜单CSS样式',
  `extend_json` text DEFAULT NULL COMMENT '扩展JSON',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uaa_menu`
--

LOCK TABLES `uaa_menu` WRITE;
/*!40000 ALTER TABLE `uaa_menu` DISABLE KEYS */;
INSERT INTO `uaa_menu` VALUES (1,NULL,'控制面板',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2022-07-28 16:21:33',NULL,'/dashboard',NULL,NULL),(2,NULL,'内容管理系统',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2022-07-28 17:01:05',NULL,'#',NULL,NULL),(3,2,'内容列表',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2022-07-28 17:01:26',NULL,'/dashboard/cms/posts',NULL,NULL),(4,2,'内容分类',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2022-07-28 17:07:31',NULL,'/dashboard/cms/category',NULL,NULL),(5,NULL,'用户账户与认证',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2022-07-28 17:09:22',NULL,'#',NULL,NULL),(6,5,'用户账号管理',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2022-07-28 17:09:47',NULL,'/dashboard/uaa/user',NULL,NULL),(7,5,'用户权限管理',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2022-07-28 17:10:07',NULL,'/dashboard/uaa/perm',NULL,NULL),(8,5,'角色管理',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2022-07-28 17:10:24',NULL,'/dashboard/uaa/role',NULL,NULL),(9,5,'菜单管理',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2022-07-28 17:10:43',NULL,'/dashboard/uaa/menu',NULL,NULL),(10,NULL,'系统设置',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2022-07-28 17:10:52',NULL,'#',NULL,NULL);
/*!40000 ALTER TABLE `uaa_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uaa_role`
--

DROP TABLE IF EXISTS `uaa_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uaa_role` (
  `id` bigint(20) unsigned NOT NULL,
  `role_name` varchar(255) NOT NULL COMMENT '角色名称',
  `role_describe` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL ON UPDATE current_timestamp() COMMENT '更新时间',
  `built_in_role` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否是内置角色',
  `extend_json` text DEFAULT NULL COMMENT '扩展JSON',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uaa_role`
--

LOCK TABLES `uaa_role` WRITE;
/*!40000 ALTER TABLE `uaa_role` DISABLE KEYS */;
INSERT INTO `uaa_role` VALUES (1552219847201390592,'测试角色','测试角色的描述','2022-07-27 17:10:25','2022-07-29 12:01:55',0,''),(1552836803759710216,'你你你','','2022-07-29 10:01:58','2022-07-29 12:00:16',0,'');
/*!40000 ALTER TABLE `uaa_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uaa_secret_key`
--

DROP TABLE IF EXISTS `uaa_secret_key`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uaa_secret_key` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) NOT NULL COMMENT 'UUID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE current_timestamp() COMMENT '更新时间',
  `public_key` text DEFAULT NULL COMMENT '公钥',
  `private_key` text DEFAULT NULL COMMENT '私钥',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秘钥表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uaa_secret_key`
--

LOCK TABLES `uaa_secret_key` WRITE;
/*!40000 ALTER TABLE `uaa_secret_key` DISABLE KEYS */;
/*!40000 ALTER TABLE `uaa_secret_key` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uaa_system_api`
--

DROP TABLE IF EXISTS `uaa_system_api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uaa_system_api` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `url_path` varchar(255) NOT NULL COMMENT 'API路径',
  `method_name` varchar(255) NOT NULL COMMENT '请求方法',
  `summary` varchar(255) NOT NULL COMMENT '概述',
  `description` varchar(255) DEFAULT NULL COMMENT '详细描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='系统接口列表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uaa_system_api`
--

LOCK TABLES `uaa_system_api` WRITE;
/*!40000 ALTER TABLE `uaa_system_api` DISABLE KEYS */;
INSERT INTO `uaa_system_api` VALUES (1,'/_/api/uaa/user/*','PUT','修改用户资料','此接口只能更新基础资料，修改密码、更改密级等有专门的接口'),(2,'/_/api/uaa/user/*/secret-level/*','PUT','给用户定密','给用户定密'),(3,'/_/api/uaa/user/*/role','PUT','编辑用户角色','编辑用户角色'),(4,'/_/api/uaa/user/*/reset-password','PUT','重置用户密码','重置用户密码');
/*!40000 ALTER TABLE `uaa_system_api` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uaa_user`
--

DROP TABLE IF EXISTS `uaa_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uaa_user` (
  `id` bigint(20) unsigned NOT NULL,
  `uuid` varchar(36) NOT NULL COMMENT 'UUID',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `email_verified` tinyint(1) unsigned DEFAULT 0 COMMENT '邮箱是否验证',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `phone_verified` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '手机号是否验证',
  `registration_date` datetime NOT NULL COMMENT '注册时间',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `totp` varchar(255) DEFAULT NULL COMMENT 'TOTP',
  `registration_ip` varchar(255) DEFAULT NULL COMMENT '注册IP地址',
  `trial_error_times` int(11) DEFAULT 0 COMMENT '密码错误累计次数',
  `lock_time` datetime DEFAULT NULL COMMENT '锁定时间',
  `secret_level` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '保密等级',
  `built_in_user` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否是内置用户',
  `password_expiration_time` datetime DEFAULT NULL COMMENT '密码过期时间',
  `locked` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否锁定',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否启用',
  `last_name` varchar(255) DEFAULT NULL COMMENT '姓',
  `first_name` varchar(255) DEFAULT NULL COMMENT '名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uaa_user`
--

LOCK TABLES `uaa_user` WRITE;
/*!40000 ALTER TABLE `uaa_user` DISABLE KEYS */;
INSERT INTO `uaa_user` VALUES (1,'7A159BF2BCB94B28BD185AC868169197','renfei','i@renfei.net',1,'',0,'2020-12-27 20:50:36','sha1:64000:18:0Htb/FaZQsur0KNR9iLq7oB5CHX4K8pF:/hF5/wG1yerCpJ/BuQAlUgIo',NULL,'2408:8207:c04:f908:2c8f:c185:cae0:924b',0,NULL,0,0,NULL,0,1,NULL,NULL),(1552175129360859183,'7BDBD4243C204755B5BD7C678E2E560F','zhangsan','zss@renfei.net',1,'13001000001',1,'2022-07-27 14:12:43','sha256:18:18:FZcaeKg73GExlCW9e2duARjig6S/JG9c:e/XAzqY4kFldnnKa/Ouw+VAn',NULL,'127.0.0.1',0,NULL,0,0,NULL,0,0,'张s','三三');
/*!40000 ALTER TABLE `uaa_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uaa_user_keep_name`
--

DROP TABLE IF EXISTS `uaa_user_keep_name`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uaa_user_keep_name` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='保留用户名';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uaa_user_keep_name`
--

LOCK TABLES `uaa_user_keep_name` WRITE;
/*!40000 ALTER TABLE `uaa_user_keep_name` DISABLE KEYS */;
/*!40000 ALTER TABLE `uaa_user_keep_name` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uaa_user_role`
--

DROP TABLE IF EXISTS `uaa_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uaa_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `account_id` bigint(20) unsigned NOT NULL COMMENT '账号ID',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色ID',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uaa_user_role`
--

LOCK TABLES `uaa_user_role` WRITE;
/*!40000 ALTER TABLE `uaa_user_role` DISABLE KEYS */;
INSERT INTO `uaa_user_role` VALUES (14,1552175129360859183,1552219847201390592,'2022-07-29 10:01:14',NULL),(16,1,1552836803759710216,'2022-07-29 10:01:58',NULL);
/*!40000 ALTER TABLE `uaa_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-03 18:23:18
