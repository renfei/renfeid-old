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
  `post_author` bigint(20) unsigned NOT NULL COMMENT '作者ID',
  `post_date` datetime NOT NULL COMMENT '发布时间',
  `post_status` varchar(255) NOT NULL DEFAULT 'PUBLISH' COMMENT '状态：PUBLISH/REVISION/DELETED/OFFLINE/REVIEW',
  `post_views` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '浏览量',
  `comment_status` varchar(255) NOT NULL DEFAULT 'OPENED' COMMENT '评论状态：OPENED/CLOSED',
  `post_password` varchar(255) DEFAULT NULL COMMENT '密码保护',
  `post_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `post_modified_user` bigint(20) unsigned DEFAULT NULL COMMENT '修改人',
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
-- Dumping data for table `cms_posts`
--

LOCK TABLES `cms_posts` WRITE;
/*!40000 ALTER TABLE `cms_posts` DISABLE KEYS */;
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
  `post_author` bigint(20) unsigned NOT NULL COMMENT '作者ID',
  `post_date` datetime NOT NULL COMMENT '发布时间',
  `post_status` varchar(255) NOT NULL DEFAULT 'PUBLISH' COMMENT '状态：PUBLISH/REVISION/DELETED/OFFLINE/REVIEW',
  `post_views` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '浏览量',
  `comment_status` varchar(255) NOT NULL DEFAULT 'OPENED' COMMENT '评论状态：OPENED/CLOSED',
  `post_password` varchar(255) DEFAULT NULL COMMENT '密码保护',
  `post_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `post_modified_user` bigint(20) unsigned DEFAULT NULL COMMENT '修改人',
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='日志审计表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_logs`
--

LOCK TABLES `core_logs` WRITE;
/*!40000 ALTER TABLE `core_logs` DISABLE KEYS */;
INSERT INTO `core_logs` VALUES (1,'2022-07-04 11:46:06','FATAL','SYSTEM','DELETE',NULL,NULL,NULL,NULL,NULL,NULL,'系统停车！！！ System shutdown!!!',NULL,NULL,NULL),(2,'2022-07-04 14:08:02','FATAL','SYSTEM','DELETE',NULL,NULL,NULL,NULL,NULL,NULL,'系统停车！！！ System shutdown!!!',NULL,NULL,NULL),(3,'2022-07-04 14:11:18','FATAL','SYSTEM','DELETE',NULL,NULL,NULL,NULL,NULL,NULL,'系统停车！！！ System shutdown!!!',NULL,NULL,NULL),(4,'2022-07-04 14:11:54','FATAL','SYSTEM','DELETE',NULL,NULL,NULL,NULL,NULL,NULL,'系统停车！！！ System shutdown!!!',NULL,NULL,NULL),(5,'2022-07-04 14:19:13','FATAL','SYSTEM','DELETE',NULL,NULL,NULL,NULL,NULL,NULL,'系统停车！！！ System shutdown!!!',NULL,NULL,NULL),(6,'2022-07-04 14:49:02','FATAL','SYSTEM','DELETE',NULL,NULL,NULL,NULL,NULL,NULL,'系统停车！！！ System shutdown!!!',NULL,NULL,NULL),(7,'2022-07-04 15:08:47','FATAL','SYSTEM','DELETE',NULL,NULL,NULL,NULL,NULL,NULL,'系统停车！！！ System shutdown!!!',NULL,NULL,NULL);
/*!40000 ALTER TABLE `core_logs` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统设置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_system_setting`
--

LOCK TABLES `core_system_setting` WRITE;
/*!40000 ALTER TABLE `core_system_setting` DISABLE KEYS */;
/*!40000 ALTER TABLE `core_system_setting` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uaa_authority`
--

LOCK TABLES `uaa_authority` WRITE;
/*!40000 ALTER TABLE `uaa_authority` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uaa_menu`
--

LOCK TABLES `uaa_menu` WRITE;
/*!40000 ALTER TABLE `uaa_menu` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统接口列表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uaa_system_api`
--

LOCK TABLES `uaa_system_api` WRITE;
/*!40000 ALTER TABLE `uaa_system_api` DISABLE KEYS */;
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
  `uuid` int(11) NOT NULL COMMENT 'UUID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uaa_user_role`
--

LOCK TABLES `uaa_user_role` WRITE;
/*!40000 ALTER TABLE `uaa_user_role` DISABLE KEYS */;
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

-- Dump completed on 2022-07-04 15:28:14
