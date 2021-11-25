SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键，非自增，使用统一发号器',
  `en_name` varchar(255) NOT NULL COMMENT '英文分类，用于url',
  `zh_name` varchar(255) NOT NULL COMMENT '中文分类，用于显示',
  `secret_level` int(10) unsigned NOT NULL DEFAULT 1 COMMENT '保密等级（1:非密/2:秘密/3:机密）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `en_name` (`en_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='博客分类栏目';

-- ----------------------------
-- Records of blog_category
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for blog_postmeta
-- ----------------------------
DROP TABLE IF EXISTS `blog_postmeta`;
CREATE TABLE `blog_postmeta` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键，非自增，使用统一发号器',
  `post_id` bigint(20) NOT NULL COMMENT '文章ID',
  `meta_key` varchar(255) DEFAULT NULL COMMENT '信息的键',
  `meta_value` longtext DEFAULT NULL COMMENT '信息的值',
  PRIMARY KEY (`id`),
  KEY `post_id` (`post_id`) USING BTREE,
  KEY `meta_key` (`meta_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='博客文章扩展信息表';

-- ----------------------------
-- Records of blog_postmeta
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for blog_posts
-- ----------------------------
DROP TABLE IF EXISTS `blog_posts`;
CREATE TABLE `blog_posts` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键，非自增，使用统一发号器',
  `category_id` bigint(20) unsigned NOT NULL COMMENT '分类栏目ID',
  `featured_image` text NOT NULL COMMENT '特色图像',
  `post_title` text NOT NULL COMMENT '标题',
  `post_author` bigint(20) unsigned NOT NULL COMMENT '作者ID，来自用户表',
  `post_keyword` text DEFAULT NULL COMMENT '关键字，SEO',
  `post_excerpt` text DEFAULT NULL COMMENT '摘录，SEO',
  `post_content` longtext NOT NULL COMMENT '正文，此处存储html，注意xss攻击',
  `post_date` datetime NOT NULL COMMENT '发布时间',
  `post_status` varchar(20) NOT NULL DEFAULT 'publish' COMMENT '文章状态（publish:发布/revision:修订版/deleted:删除/offline:下线/review:审核流程中）',
  `post_views` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '文章浏览量',
  `comment_status` varchar(20) NOT NULL DEFAULT 'open' COMMENT '评论状态（open:任意评论/closed:禁止评论/signed:登录后可评论）',
  `post_password` varchar(20) DEFAULT NULL COMMENT '文章密码，输入密码可查看',
  `post_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `post_parent` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '父级id，默认为0，编辑修改后为历史版本',
  `source_name` text DEFAULT NULL COMMENT '来源名称',
  `source_url` text DEFAULT NULL COMMENT '原文链接',
  `thumbs_up` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点赞量',
  `thumbs_down` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点踩量',
  `avg_views` double NOT NULL DEFAULT 0 COMMENT '日均浏览量',
  `avg_comment` double NOT NULL DEFAULT 0 COMMENT '日均评论量',
  `page_rank` double NOT NULL DEFAULT 10000 COMMENT '权重排序',
  `secret_level` int(255) unsigned NOT NULL DEFAULT 1 COMMENT '保密等级（1:非密/2:秘密/3:机密）',
  PRIMARY KEY (`id`),
  KEY `secret_level` (`secret_level`) USING BTREE,
  KEY `post_date` (`post_date`) USING BTREE,
  KEY `post_author` (`post_author`) USING BTREE,
  KEY `post_status` (`post_status`) USING BTREE,
  KEY `page_rank` (`page_rank`) USING BTREE,
  KEY `category_id` (`category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='博客文章';

-- ----------------------------
-- Records of blog_posts
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for leaf_alloc
-- ----------------------------
DROP TABLE IF EXISTS `leaf_alloc`;
CREATE TABLE `leaf_alloc` (
  `biz_tag` varchar(128) NOT NULL DEFAULT '',
  `max_id` bigint(20) NOT NULL DEFAULT 1,
  `step` int(11) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`biz_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='全局发号号段表';

-- ----------------------------
-- Records of leaf_alloc
-- ----------------------------
BEGIN;
INSERT INTO `leaf_alloc` VALUES ('leaf-segment-global', 1, 2000, 'Segment Mode Get Id', '2021-11-12 05:36:43');
COMMIT;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `BLOB_DATA` blob DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_IBFK_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(190) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_CRON_TRIGGERS_IBFK_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
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
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
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

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(190) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_IBFK_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
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
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_IBFK_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS` (
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
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `QRTZ_TRIGGERS_IBFK_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_account_keep_name
-- ----------------------------
DROP TABLE IF EXISTS `sys_account_keep_name`;
CREATE TABLE `sys_account_keep_name` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `user_name` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='保留用户名';

-- ----------------------------
-- Records of sys_account_keep_name
-- ----------------------------
BEGIN;
INSERT INTO `sys_account_keep_name` VALUES (3, 'admin');
INSERT INTO `sys_account_keep_name` VALUES (5, 'administrator');
INSERT INTO `sys_account_keep_name` VALUES (30, 'alibaba');
INSERT INTO `sys_account_keep_name` VALUES (31, 'aliyun');
INSERT INTO `sys_account_keep_name` VALUES (33, 'baidu');
INSERT INTO `sys_account_keep_name` VALUES (9, 'bbs');
INSERT INTO `sys_account_keep_name` VALUES (11, 'blog');
INSERT INTO `sys_account_keep_name` VALUES (35, 'error');
INSERT INTO `sys_account_keep_name` VALUES (34, 'exit');
INSERT INTO `sys_account_keep_name` VALUES (21, 'master');
INSERT INTO `sys_account_keep_name` VALUES (1, 'renfei');
INSERT INTO `sys_account_keep_name` VALUES (4, 'root');
INSERT INTO `sys_account_keep_name` VALUES (6, 'sys');
INSERT INTO `sys_account_keep_name` VALUES (7, 'system');
INSERT INTO `sys_account_keep_name` VALUES (23, 'test');
INSERT INTO `sys_account_keep_name` VALUES (24, 'tester');
INSERT INTO `sys_account_keep_name` VALUES (22, 'webmaster');
INSERT INTO `sys_account_keep_name` VALUES (10, 'weibo');
INSERT INTO `sys_account_keep_name` VALUES (12, 'wiki');
INSERT INTO `sys_account_keep_name` VALUES (8, 'www');
INSERT INTO `sys_account_keep_name` VALUES (2, '任霏');
INSERT INTO `sys_account_keep_name` VALUES (25, '任飞');
INSERT INTO `sys_account_keep_name` VALUES (13, '官方');
INSERT INTO `sys_account_keep_name` VALUES (27, '百度');
INSERT INTO `sys_account_keep_name` VALUES (15, '管理');
INSERT INTO `sys_account_keep_name` VALUES (14, '管理员');
INSERT INTO `sys_account_keep_name` VALUES (19, '网站管理');
INSERT INTO `sys_account_keep_name` VALUES (17, '网站管理员');
INSERT INTO `sys_account_keep_name` VALUES (26, '腾讯');
INSERT INTO `sys_account_keep_name` VALUES (16, '认证');
INSERT INTO `sys_account_keep_name` VALUES (20, '认证管理');
INSERT INTO `sys_account_keep_name` VALUES (18, '认证管理员');
INSERT INTO `sys_account_keep_name` VALUES (28, '阿里');
INSERT INTO `sys_account_keep_name` VALUES (32, '阿里云');
INSERT INTO `sys_account_keep_name` VALUES (29, '阿里巴巴');
COMMIT;

-- ----------------------------
-- Table structure for sys_comments
-- ----------------------------
DROP TABLE IF EXISTS `sys_comments`;
CREATE TABLE `sys_comments` (
  `id` bigint(20) unsigned NOT NULL,
  `sys_type` varchar(255) NOT NULL COMMENT '给哪个子系统评论的',
  `object_id` bigint(20) unsigned NOT NULL COMMENT '评论哪个对象',
  `author_id` bigint(20) unsigned DEFAULT NULL COMMENT '如果是登录用户的话作者ID',
  `author` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者名称',
  `author_email` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者邮箱',
  `author_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者链接',
  `author_IP` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者IP',
  `author_address` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者物理地址',
  `addtime` datetime NOT NULL COMMENT '评论时间',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `parent_id` bigint(20) unsigned DEFAULT NULL COMMENT '父级评论ID',
  `is_owner` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否是官方回复',
  PRIMARY KEY (`id`),
  KEY `type_object_id` (`sys_type`,`object_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='全局评论表';

-- ----------------------------
-- Records of sys_comments
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_keyword_object
-- ----------------------------
DROP TABLE IF EXISTS `sys_keyword_object`;
CREATE TABLE `sys_keyword_object` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键，非自增，使用统一发号器',
  `tag_id` bigint(20) unsigned NOT NULL COMMENT '关键字外键',
  `object_type` varchar(255) NOT NULL COMMENT '属于哪个子系统的关系类型',
  `object_id` bigint(20) unsigned NOT NULL COMMENT '关联对象',
  PRIMARY KEY (`id`),
  KEY `tag_id` (`tag_id`) USING BTREE,
  KEY `object_type` (`object_type`) USING BTREE,
  KEY `object_id` (`object_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='关键字关系表';

-- ----------------------------
-- Records of sys_keyword_object
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_keyword_tag
-- ----------------------------
DROP TABLE IF EXISTS `sys_keyword_tag`;
CREATE TABLE `sys_keyword_tag` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键，非自增，使用统一发号器',
  `en_name` varchar(255) NOT NULL COMMENT '英文，用于url',
  `zh_name` varchar(255) NOT NULL COMMENT '中文，用于显示',
  PRIMARY KEY (`id`),
  UNIQUE KEY `en_name` (`en_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='全局关键字';

-- ----------------------------
-- Records of sys_keyword_tag
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_pages
-- ----------------------------
DROP TABLE IF EXISTS `sys_pages`;
CREATE TABLE `sys_pages` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键，非自增，使用统一发号器',
  `featured_image` text DEFAULT NULL COMMENT '特色图像',
  `page_title` text NOT NULL COMMENT '标题',
  `page_author` bigint(20) unsigned NOT NULL COMMENT '作者ID，来自用户表',
  `page_keyword` text DEFAULT NULL COMMENT '关键字，SEO',
  `page_excerpt` text DEFAULT NULL COMMENT '摘录，SEO',
  `page_content` longtext NOT NULL COMMENT '正文，此处存储html，注意xss攻击',
  `page_date` datetime NOT NULL COMMENT '发布时间',
  `page_status` varchar(20) NOT NULL DEFAULT 'publish' COMMENT '页面状态（publish:发布/revision:修订版/deleted:删除/offline:下线）',
  `page_views` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '页面浏览量',
  `page_password` varchar(20) DEFAULT NULL COMMENT '页面密码，输入密码可查看',
  `page_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `page_parent` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '父级id，默认为0，编辑修改后为历史版本',
  `thumbs_up` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点赞量',
  `thumbs_down` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点踩量',
  `secret_level` int(10) unsigned NOT NULL DEFAULT 1 COMMENT '保密等级（1:非密/2:秘密/3:机密）',
  PRIMARY KEY (`id`),
  KEY `secret_level` (`secret_level`) USING BTREE,
  KEY `page_author` (`page_author`) USING BTREE,
  KEY `page_date` (`page_date`) USING BTREE,
  KEY `page_status` (`page_status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站点页面';

-- ----------------------------
-- Records of sys_pages
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_region
-- ----------------------------
DROP TABLE IF EXISTS `sys_region`;
CREATE TABLE `sys_region` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` varchar(36) NOT NULL,
  `region_code` char(6) NOT NULL COMMENT '行政编码',
  `region_name` varchar(255) NOT NULL COMMENT '区划名称',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `region_code` (`region_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3439 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='行政区划，使用《中华人民共和国行政区划代码》国家标准(GB/T2260)';

-- ----------------------------
-- Records of sys_region
-- ----------------------------
BEGIN;
INSERT INTO `sys_region` VALUES (1, '9DECA852-0BBD-4DC6-959B-1A116784F87C', '110000', '北京市');
INSERT INTO `sys_region` VALUES (2, '7279C4F2-6E46-47E3-B103-57CC1913B300', '110101', '东城区');
INSERT INTO `sys_region` VALUES (3, '642817C3-7E23-47E2-9D6F-81CC4CA7611A', '110102', '西城区');
INSERT INTO `sys_region` VALUES (4, 'BD64EECF-BAD8-47A8-905B-6C647D30444B', '110105', '朝阳区');
INSERT INTO `sys_region` VALUES (5, 'D21D669F-0F24-42FE-B4E7-EAA6F8F0C478', '110106', '丰台区');
INSERT INTO `sys_region` VALUES (6, 'ECBBC616-BEF7-4040-B703-CBCFA3127989', '110107', '石景山区');
INSERT INTO `sys_region` VALUES (7, '590621D0-0DE5-41CD-BBA7-FCE00C07D8C6', '110108', '海淀区');
INSERT INTO `sys_region` VALUES (8, '95559E17-62C4-44C1-8199-2844126604C2', '110109', '门头沟区');
INSERT INTO `sys_region` VALUES (9, 'E64817BF-4C15-4F44-BE8C-2C7DC1D849CE', '110111', '房山区');
INSERT INTO `sys_region` VALUES (10, '1E2BE9FD-A294-4BA6-B2F3-19B0468349FC', '110112', '通州区');
INSERT INTO `sys_region` VALUES (11, '57734060-7B17-4E4A-8A82-560D929ECDA9', '110113', '顺义区');
INSERT INTO `sys_region` VALUES (12, '2EF717F2-5457-4F70-B837-45C96BF99A1A', '110114', '昌平区');
INSERT INTO `sys_region` VALUES (13, 'A897C6BF-D886-41DB-B4CE-F5FCDF210B43', '110115', '大兴区');
INSERT INTO `sys_region` VALUES (14, '17288D19-BFA3-44E1-937F-BD6BB61492C3', '110116', '怀柔区');
INSERT INTO `sys_region` VALUES (15, '439A72D8-826F-445F-8B21-A35786881EBD', '110117', '平谷区');
INSERT INTO `sys_region` VALUES (16, '780380AD-299C-4B9E-8915-CD50E3A4DF78', '110118', '密云区');
INSERT INTO `sys_region` VALUES (17, 'EB0A8FA2-E806-4057-B622-E7A4EFE8EA65', '110119', '延庆区');
INSERT INTO `sys_region` VALUES (18, '8D2244FB-7246-477D-A1F2-75865372E932', '120000', '天津市');
INSERT INTO `sys_region` VALUES (19, 'EC7201FF-E6FF-489F-8FCA-A1B7F3769D0F', '120101', '和平区');
INSERT INTO `sys_region` VALUES (20, 'D0B457AC-E2C5-4A12-87D9-1DB3087FD74F', '120102', '河东区');
INSERT INTO `sys_region` VALUES (21, '8838CFA4-5E1B-432C-BF0B-F3288B4A1A2C', '120103', '河西区');
INSERT INTO `sys_region` VALUES (22, 'D5A68EAF-4386-4DD7-A1DF-82EDF8D6F6D6', '120104', '南开区');
INSERT INTO `sys_region` VALUES (23, 'BE580CC8-A5BC-43F7-9278-82F801A258C3', '120105', '河北区');
INSERT INTO `sys_region` VALUES (24, '401EDD26-D118-4D03-BB2E-2675C4DE952A', '120106', '红桥区');
INSERT INTO `sys_region` VALUES (25, '527AE0AE-4D08-4AEB-84A4-4218E109E74E', '120110', '东丽区');
INSERT INTO `sys_region` VALUES (26, '16F4D03F-F046-4B3E-9042-83A2CEF3722A', '120111', '西青区');
INSERT INTO `sys_region` VALUES (27, '8D23EC9A-D733-4620-88A3-3C05D80FA3CD', '120112', '津南区');
INSERT INTO `sys_region` VALUES (28, 'DB4AD447-EA59-433C-811D-1541F36B4E09', '120113', '北辰区');
INSERT INTO `sys_region` VALUES (29, '4712DEC8-F41B-411B-B691-25181350D4A3', '120114', '武清区');
INSERT INTO `sys_region` VALUES (30, '1893DE2E-51AD-41E8-BADC-91D09CE8347F', '120115', '宝坻区');
INSERT INTO `sys_region` VALUES (31, 'B4C14112-7C37-4F97-A12E-B749B837BE7D', '120116', '滨海新区');
INSERT INTO `sys_region` VALUES (32, 'BBB85BE8-C65E-4AAC-AFF8-6C90EADB050F', '120117', '宁河区');
INSERT INTO `sys_region` VALUES (33, '880F15A7-D1E9-42B3-8337-43757FC3C310', '120118', '静海区');
INSERT INTO `sys_region` VALUES (34, '1B266DAC-C884-4FB6-8D82-5A62D600ABFB', '120119', '蓟州区');
INSERT INTO `sys_region` VALUES (35, 'A7D37528-441B-4A7C-A219-C9CCD0014522', '130000', '河北省');
INSERT INTO `sys_region` VALUES (36, '646E6F20-985F-4486-B809-76CE1A1C53C6', '130100', '石家庄市');
INSERT INTO `sys_region` VALUES (37, '3922681A-9B42-43C8-986F-4CE66BFCEDF2', '130102', '长安区');
INSERT INTO `sys_region` VALUES (38, '330A59A9-07C9-446A-87F2-61742B0ECD6C', '130104', '桥西区');
INSERT INTO `sys_region` VALUES (39, '33015B64-E12B-4417-AADF-A9D878E3059F', '130105', '新华区');
INSERT INTO `sys_region` VALUES (40, 'D05C1309-7715-4150-BE77-403587EF35CC', '130107', '井陉矿区');
INSERT INTO `sys_region` VALUES (41, '1187D5CC-AF96-46E4-A18B-94158DFF7321', '130108', '裕华区');
INSERT INTO `sys_region` VALUES (42, 'DFDEE3DF-170F-418F-8D2D-D2841A02668A', '130109', '藁城区');
INSERT INTO `sys_region` VALUES (43, 'FEA2346B-D7D8-41BB-9515-94A710C7E4CF', '130110', '鹿泉区');
INSERT INTO `sys_region` VALUES (44, '3927A054-7803-4DA5-8E3B-D5A69345C0BF', '130111', '栾城区');
INSERT INTO `sys_region` VALUES (45, '4D709459-DB8D-4B25-BA15-383065B56E24', '130121', '井陉县');
INSERT INTO `sys_region` VALUES (46, '2C03A233-25C4-463E-B4F7-C5D1C70F50C8', '130123', '正定县');
INSERT INTO `sys_region` VALUES (47, '05761964-1CB9-45E9-AC3E-1C0323BA32C3', '130125', '行唐县');
INSERT INTO `sys_region` VALUES (48, '3C9F7074-0EEC-48DF-8014-F50F727373D8', '130126', '灵寿县');
INSERT INTO `sys_region` VALUES (49, '36E2B8FE-33A4-41C3-84D4-165C0A2F60F1', '130127', '高邑县');
INSERT INTO `sys_region` VALUES (50, '06E589A7-23DA-4A40-8380-9A28FEC688C4', '130128', '深泽县');
INSERT INTO `sys_region` VALUES (51, '937F8005-9904-4B28-B817-D9ECB46346F1', '130129', '赞皇县');
INSERT INTO `sys_region` VALUES (52, 'D73D0E94-4BBD-4EAB-8DA5-1A22B005FD08', '130130', '无极县');
INSERT INTO `sys_region` VALUES (53, 'C157E033-442B-4C97-AE3E-F42AAB05C4C6', '130131', '平山县');
INSERT INTO `sys_region` VALUES (54, '6969C94D-139B-4AAA-966A-60C84B9B3385', '130132', '元氏县');
INSERT INTO `sys_region` VALUES (55, 'F06F32E4-6B21-4498-87F3-07CDB9CD877A', '130133', '赵县');
INSERT INTO `sys_region` VALUES (56, '86E3DDA2-402A-4D93-B4C0-B04CB5229527', '130181', '辛集市');
INSERT INTO `sys_region` VALUES (57, '6660D0B1-6D4A-4F07-B4C3-D77050E41FD4', '130183', '晋州市');
INSERT INTO `sys_region` VALUES (58, 'CADDB1AC-34BF-4233-B307-09FD5AC76390', '130184', '新乐市');
INSERT INTO `sys_region` VALUES (59, 'AB156110-9CD9-4BDC-A72C-C02F709A289D', '130200', '唐山市');
INSERT INTO `sys_region` VALUES (60, 'C78D0301-37E4-4524-A0F9-F4CFEC11FE32', '130202', '路南区');
INSERT INTO `sys_region` VALUES (61, '85A72100-67DF-4692-9B0A-92B357AC4070', '130203', '路北区');
INSERT INTO `sys_region` VALUES (62, '013006FC-99AD-4F51-B9C0-E6F343F90897', '130204', '古冶区');
INSERT INTO `sys_region` VALUES (63, 'EBFDC951-F5DC-41A6-9349-9750C1A0CABD', '130205', '开平区');
INSERT INTO `sys_region` VALUES (64, 'ADC5888A-1200-48F3-8630-BD0B8A2A8A23', '130207', '丰南区');
INSERT INTO `sys_region` VALUES (65, '72D534D3-BE7C-46D6-B7EE-3A333AC3D6FF', '130208', '丰润区');
INSERT INTO `sys_region` VALUES (66, '018C830C-67B5-4950-932C-1B089D84BF79', '130209', '曹妃甸区');
INSERT INTO `sys_region` VALUES (67, 'E9D84455-EBF6-4217-A829-4B06876FD3A2', '130224', '滦南县');
INSERT INTO `sys_region` VALUES (68, 'BE6AED0F-787C-4FF5-9DB3-2CFA88B2629F', '130225', '乐亭县');
INSERT INTO `sys_region` VALUES (69, 'CB4912A2-2EA5-41F1-B0D8-2C392D35F06B', '130227', '迁西县');
INSERT INTO `sys_region` VALUES (70, '9C7CB52B-5573-4CC6-AFD9-9F3A2B35CB70', '130229', '玉田县');
INSERT INTO `sys_region` VALUES (71, '6E0B1218-F5A0-422A-9023-DDCE355C7DB5', '130281', '遵化市');
INSERT INTO `sys_region` VALUES (72, '71656372-297F-4A1F-9DC6-EC13F73AA876', '130283', '迁安市');
INSERT INTO `sys_region` VALUES (73, '55624088-C223-4F46-9310-F9FA32480C67', '130284', '滦州市');
INSERT INTO `sys_region` VALUES (74, 'B50910AA-B1EC-47F9-B7E1-B018FD55C2ED', '130300', '秦皇岛市');
INSERT INTO `sys_region` VALUES (75, '1657EBAF-B161-44D4-A5D0-CB1AA138EDEA', '130302', '海港区');
INSERT INTO `sys_region` VALUES (76, '9661F9B1-F90E-4562-B12D-FCAFC43F6B54', '130303', '山海关区');
INSERT INTO `sys_region` VALUES (77, 'CD167234-5725-4CB2-B1D7-8407F7603177', '130304', '北戴河区');
INSERT INTO `sys_region` VALUES (78, '70855BEA-F438-46B4-AC05-E828EA876FC5', '130306', '抚宁区');
INSERT INTO `sys_region` VALUES (79, '9AB5AFAD-A426-49FE-B57F-71F09E2F7C32', '130321', '青龙满族自治县');
INSERT INTO `sys_region` VALUES (80, '36008EF6-BE27-4338-9E84-DE671974FC0D', '130322', '昌黎县');
INSERT INTO `sys_region` VALUES (81, '958ED533-3C99-4176-B5AF-C6EB2C320D00', '130324', '卢龙县');
INSERT INTO `sys_region` VALUES (82, '5D0FCEBA-0652-45DD-97DB-EA17038C4EF3', '130400', '邯郸市');
INSERT INTO `sys_region` VALUES (83, '60D98F58-1405-49E1-9524-BF6C6180A0E2', '130402', '邯山区');
INSERT INTO `sys_region` VALUES (84, '836F63EE-0FE0-4431-8BF0-A5A5CD7B212B', '130403', '丛台区');
INSERT INTO `sys_region` VALUES (85, '202737AA-9014-4622-951A-13E72F735195', '130404', '复兴区');
INSERT INTO `sys_region` VALUES (86, '71DA58BE-AA5D-4627-80AF-4A1D020D078D', '130406', '峰峰矿区');
INSERT INTO `sys_region` VALUES (87, '9D81803B-F65F-4589-B77F-B9CB36672554', '130407', '肥乡区');
INSERT INTO `sys_region` VALUES (88, '57F4719E-69EB-408B-8F78-6A3EA73E75C3', '130408', '永年区');
INSERT INTO `sys_region` VALUES (89, '5AEF956F-EDEB-4B29-B6EE-A243F5DBE037', '130423', '临漳县');
INSERT INTO `sys_region` VALUES (90, '689306F6-93B1-4E67-8BE6-B7B21EAE9CD9', '130424', '成安县');
INSERT INTO `sys_region` VALUES (91, 'A11B65A0-4459-4411-8D24-B348543BA779', '130425', '大名县');
INSERT INTO `sys_region` VALUES (92, '2E098B75-D8FF-40E4-AF52-182B456457B2', '130426', '涉县');
INSERT INTO `sys_region` VALUES (93, 'C492B9F2-4DAA-4883-9A71-C0FC00A31D8C', '130427', '磁县');
INSERT INTO `sys_region` VALUES (94, 'D39E6254-F394-4977-A485-F1E99704C8C5', '130430', '邱县');
INSERT INTO `sys_region` VALUES (95, '4650DC7E-554C-4496-84CD-2C8DE9D0001B', '130431', '鸡泽县');
INSERT INTO `sys_region` VALUES (96, '2139DBB2-B699-4FD5-A208-91E05878D4F0', '130432', '广平县');
INSERT INTO `sys_region` VALUES (97, 'EA787998-760A-4DC0-979F-8FB2BACBE893', '130433', '馆陶县');
INSERT INTO `sys_region` VALUES (98, 'F2C2E772-8677-4DC0-AA80-BE8A3B2D8B36', '130434', '魏县');
INSERT INTO `sys_region` VALUES (99, '8E577D9D-291E-4FF2-B2D8-9544F85E8F65', '130435', '曲周县');
INSERT INTO `sys_region` VALUES (100, '324398C9-10D4-4D8E-8357-5711D7E0A64A', '130481', '武安市');
INSERT INTO `sys_region` VALUES (101, '5127D869-DD6E-4B92-896C-1DD0FE854DBD', '130500', '邢台市');
INSERT INTO `sys_region` VALUES (102, '62C4F209-9DC5-45A8-B688-66A72F205744', '130502', '桥东区');
INSERT INTO `sys_region` VALUES (103, 'C0E7F4AA-8C54-49D1-850D-6EF37A1DB6A4', '130503', '桥西区');
INSERT INTO `sys_region` VALUES (104, '5B882C75-6C45-4D04-900A-CFB0F7DF5C9C', '130521', '邢台县');
INSERT INTO `sys_region` VALUES (105, 'C8C25261-1819-405C-A5BE-0F31F6608407', '130522', '临城县');
INSERT INTO `sys_region` VALUES (106, '5766E141-7597-4606-86E3-60F887912F34', '130523', '内丘县');
INSERT INTO `sys_region` VALUES (107, '36A7D289-80A1-4AF0-B95B-D508CBD7F967', '130524', '柏乡县');
INSERT INTO `sys_region` VALUES (108, 'FF3A710C-E23E-4F39-BA17-6DBECCE928AE', '130525', '隆尧县');
INSERT INTO `sys_region` VALUES (109, 'F28073A7-2308-4F01-A31F-886BA0DB4158', '130526', '任县');
INSERT INTO `sys_region` VALUES (110, '77469D8D-351E-4C84-B4E1-637D5537DFEC', '130527', '南和县');
INSERT INTO `sys_region` VALUES (111, '18B3ECBB-B549-43E2-B853-E93ADE177A5E', '130528', '宁晋县');
INSERT INTO `sys_region` VALUES (112, '5DD8C25C-1C19-45CD-ABC4-CF3FFFC2F09B', '130529', '巨鹿县');
INSERT INTO `sys_region` VALUES (113, '185D8F5D-27BF-45B5-B56A-0BC1256793D6', '130530', '新河县');
INSERT INTO `sys_region` VALUES (114, 'EA062929-2ADD-41D5-A113-32732FFF9989', '130531', '广宗县');
INSERT INTO `sys_region` VALUES (115, 'E999C431-8EA5-4683-8AF1-600BC3CF48C5', '130532', '平乡县');
INSERT INTO `sys_region` VALUES (116, 'FEA7C33E-4E1B-42C1-BCCF-FF2DE44AF438', '130533', '威县');
INSERT INTO `sys_region` VALUES (117, '5339C6E0-3FBE-4735-8C11-580D70E485C7', '130534', '清河县');
INSERT INTO `sys_region` VALUES (118, 'DA36D789-F619-4C44-BCEB-77CF6397D43F', '130535', '临西县');
INSERT INTO `sys_region` VALUES (119, 'F42FCC92-BD1C-4529-B87D-BACB393156F2', '130581', '南宫市');
INSERT INTO `sys_region` VALUES (120, '52B1279B-6361-4B63-8016-62684021EA8D', '130582', '沙河市');
INSERT INTO `sys_region` VALUES (121, '7A5E683C-4D26-46C1-A16A-5C0A93AF6094', '130600', '保定市');
INSERT INTO `sys_region` VALUES (122, '6AE6D21A-23B3-4629-ACD7-9C21996721C5', '130602', '竞秀区');
INSERT INTO `sys_region` VALUES (123, '39290020-90A9-4818-A9EE-43C4E1C7C1F0', '130606', '莲池区');
INSERT INTO `sys_region` VALUES (124, 'A53D5ABB-079F-4CAD-A0B5-5DD49D24D6EE', '130607', '满城区');
INSERT INTO `sys_region` VALUES (125, '0771D268-2795-49B2-8EE2-8EC5803115A4', '130608', '清苑区');
INSERT INTO `sys_region` VALUES (126, '1519DE73-00BA-4F47-B33E-C17041ACDFEB', '130609', '徐水区');
INSERT INTO `sys_region` VALUES (127, '71FF14AB-4DF9-4AEE-BE38-4695E08CDC64', '130623', '涞水县');
INSERT INTO `sys_region` VALUES (128, '8161769D-93C0-4C12-9E73-79A23886BA07', '130624', '阜平县');
INSERT INTO `sys_region` VALUES (129, '2657EDA6-BBA5-4F23-9509-E93189A4899A', '130626', '定兴县');
INSERT INTO `sys_region` VALUES (130, 'C758AB43-DD51-48E2-9727-0D9188094342', '130627', '唐县');
INSERT INTO `sys_region` VALUES (131, 'D78B32C8-86BA-470A-8ECF-10AD11C62BF2', '130628', '高阳县');
INSERT INTO `sys_region` VALUES (132, '9BEC3045-2826-4658-9464-9A82B4FE71E1', '130629', '容城县');
INSERT INTO `sys_region` VALUES (133, '1A93259B-5262-44D0-AF37-1BF00AF6B27F', '130630', '涞源县');
INSERT INTO `sys_region` VALUES (134, '8E411E40-065A-415D-B328-74F090AF6248', '130631', '望都县');
INSERT INTO `sys_region` VALUES (135, 'C1C68A4A-AB0A-4BAF-B455-124AFAD52B87', '130632', '安新县');
INSERT INTO `sys_region` VALUES (136, 'EA15ABB0-2096-4103-ABCB-1C30F9D1088E', '130633', '易县');
INSERT INTO `sys_region` VALUES (137, 'B8CB9D6C-3710-4826-9D66-FFC8793914F2', '130634', '曲阳县');
INSERT INTO `sys_region` VALUES (138, 'AD0DE226-57E9-4428-A219-F6DE0228AFA7', '130635', '蠡县');
INSERT INTO `sys_region` VALUES (139, '129D444F-8A30-43DD-B4AE-0936ECFB6F9E', '130636', '顺平县');
INSERT INTO `sys_region` VALUES (140, 'EFF9B1E5-CBFB-4A4A-ADE9-0E56F5671763', '130637', '博野县');
INSERT INTO `sys_region` VALUES (141, 'DCF00DFD-0EBD-4713-8BCF-618414747768', '130638', '雄县');
INSERT INTO `sys_region` VALUES (142, 'D7DB2EF2-4CB4-4C98-BBA4-7F0E4826CD70', '130681', '涿州市');
INSERT INTO `sys_region` VALUES (143, '9E37126E-1B90-4A09-8441-9C11311C05F4', '130682', '定州市');
INSERT INTO `sys_region` VALUES (144, 'B8AD07B8-23EC-49FF-8A2E-1F6A0FFF1758', '130683', '安国市');
INSERT INTO `sys_region` VALUES (145, '7FD6C0DA-E150-4984-B6DA-7B1ACC81D889', '130684', '高碑店市');
INSERT INTO `sys_region` VALUES (146, 'D3946300-DE4B-40D7-B7E8-C981EE9F2941', '130700', '张家口市');
INSERT INTO `sys_region` VALUES (147, '27280DFE-6392-4D9A-9323-CFC35F909938', '130702', '桥东区');
INSERT INTO `sys_region` VALUES (148, '9D1FE9F5-2212-4078-8700-61EEAB0000D5', '130703', '桥西区');
INSERT INTO `sys_region` VALUES (149, 'AB09CE50-A061-439C-9AB2-22BE2197FB1D', '130705', '宣化区');
INSERT INTO `sys_region` VALUES (150, 'FA4BC52D-16EA-42DB-AEC2-048B07B729D7', '130706', '下花园区');
INSERT INTO `sys_region` VALUES (151, 'A456671F-F27E-4413-9E89-398FC6B19168', '130708', '万全区');
INSERT INTO `sys_region` VALUES (152, '972B7017-6F5F-477A-9BE9-BE767C002B08', '130709', '崇礼区');
INSERT INTO `sys_region` VALUES (153, 'D51B695E-2A0C-497A-A74B-041A016C7F1D', '130722', '张北县');
INSERT INTO `sys_region` VALUES (154, 'DDC42705-E08F-4CEA-B8F5-62B5E422763C', '130723', '康保县');
INSERT INTO `sys_region` VALUES (155, '60712E34-52C5-49A3-9449-F3B62049023F', '130724', '沽源县');
INSERT INTO `sys_region` VALUES (156, '4249C1E2-2BD2-4B57-ACC3-8DA7661E0DD3', '130725', '尚义县');
INSERT INTO `sys_region` VALUES (157, 'A62B3C03-D1FC-4A07-ADD0-591324E53BAB', '130726', '蔚县');
INSERT INTO `sys_region` VALUES (158, 'B9322E3B-7029-4FFC-87A9-AE0072632201', '130727', '阳原县');
INSERT INTO `sys_region` VALUES (159, 'AE1DAE13-5FE6-4CB3-A4B8-DD7D8C5D3E3A', '130728', '怀安县');
INSERT INTO `sys_region` VALUES (160, '65352D7C-69E4-493F-A525-8237B907CF4F', '130730', '怀来县');
INSERT INTO `sys_region` VALUES (161, '7668213C-140C-484C-8B7D-527658A9F021', '130731', '涿鹿县');
INSERT INTO `sys_region` VALUES (162, '07AA46A5-8A7F-49D6-A3CE-9A61BC7255DF', '130732', '赤城县');
INSERT INTO `sys_region` VALUES (163, '56482F4D-D081-4A56-B452-C2C9F7606F6B', '130800', '承德市');
INSERT INTO `sys_region` VALUES (164, '456CE825-DE49-44B7-8C25-7AF5C3E55CBD', '130802', '双桥区');
INSERT INTO `sys_region` VALUES (165, '15B427D3-1A78-47FB-B172-4F706251A80B', '130803', '双滦区');
INSERT INTO `sys_region` VALUES (166, '8A930DA4-F885-4CE9-9847-687CD9FAB428', '130804', '鹰手营子矿区');
INSERT INTO `sys_region` VALUES (167, '25E2C13C-9B4B-42E3-BB3E-D44EC9505595', '130821', '承德县');
INSERT INTO `sys_region` VALUES (168, 'CE000FA2-FD8C-4817-B5D5-2546263A5130', '130822', '兴隆县');
INSERT INTO `sys_region` VALUES (169, '9E1F2638-5E5D-416D-924E-5E74D583FA1C', '130824', '滦平县');
INSERT INTO `sys_region` VALUES (170, '2373031F-35CF-4340-906C-7C7F06410AEF', '130825', '隆化县');
INSERT INTO `sys_region` VALUES (171, '71A24ADC-E2A0-4B1E-8BF5-CD29D7550C7F', '130826', '丰宁满族自治县');
INSERT INTO `sys_region` VALUES (172, '7E154F69-0CAC-44F0-8CE0-024BDF134C34', '130827', '宽城满族自治县');
INSERT INTO `sys_region` VALUES (173, 'E517C552-E095-4A73-B645-45F250414C14', '130828', '围场满族蒙古族自治县');
INSERT INTO `sys_region` VALUES (174, '98D5ED64-5353-482C-A17F-701392A34FB6', '130881', '平泉市');
INSERT INTO `sys_region` VALUES (175, '38A3B3C6-9FFE-49F9-910F-E82F16EED8DE', '130900', '沧州市');
INSERT INTO `sys_region` VALUES (176, '5072102A-A4A1-419B-99F4-495F56961C3F', '130902', '新华区');
INSERT INTO `sys_region` VALUES (177, '7C6448C2-1C45-44CF-8992-B9CDDA057E78', '130903', '运河区');
INSERT INTO `sys_region` VALUES (178, 'DD519C35-2FAF-42BD-98B6-27CC34F32D0B', '130921', '沧县');
INSERT INTO `sys_region` VALUES (179, '4AD35928-5CF0-4B61-AF20-DB65651E0D3E', '130922', '青县');
INSERT INTO `sys_region` VALUES (180, 'B6CC9E7C-5A77-4AF5-AFE5-B953346BD3FE', '130923', '东光县');
INSERT INTO `sys_region` VALUES (181, '4BDDD4E3-AAB7-4CB6-AA6D-45ED0E9403DE', '130924', '海兴县');
INSERT INTO `sys_region` VALUES (182, '0EA2C3A2-E3DD-4CDD-B3AD-CC13F02F4D39', '130925', '盐山县');
INSERT INTO `sys_region` VALUES (183, '4D16AF1E-7649-4B89-8CB6-D63502C36446', '130926', '肃宁县');
INSERT INTO `sys_region` VALUES (184, '993778CC-8C1C-49DA-A5CC-F441C9D508A9', '130927', '南皮县');
INSERT INTO `sys_region` VALUES (185, '973B6ACE-C4AE-4669-9820-39F15FDDC3FA', '130928', '吴桥县');
INSERT INTO `sys_region` VALUES (186, '101C1C1F-8CA5-4E1B-87A8-C24CDA670A84', '130929', '献县');
INSERT INTO `sys_region` VALUES (187, '8766FB09-C500-434F-8664-101913692108', '130930', '孟村回族自治县');
INSERT INTO `sys_region` VALUES (188, 'AE0B13A2-C808-4E49-9B2C-E8095FA4A614', '130981', '泊头市');
INSERT INTO `sys_region` VALUES (189, '43A70F42-47B2-4D27-96CF-30C6840AD042', '130982', '任丘市');
INSERT INTO `sys_region` VALUES (190, '0A33AC73-1EF9-44BC-A7C6-3AF5E8D3C896', '130983', '黄骅市');
INSERT INTO `sys_region` VALUES (191, '4696E782-49B1-4BBD-8D61-7521D7D31612', '130984', '河间市');
INSERT INTO `sys_region` VALUES (192, 'E2A8D0F7-179A-44BE-B6C8-63314E279A42', '131000', '廊坊市');
INSERT INTO `sys_region` VALUES (193, '13D16BFD-0A83-49BF-AF1C-CFDEA693D711', '131002', '安次区');
INSERT INTO `sys_region` VALUES (194, '37F2A674-23B0-4626-BFEE-1E193592A33B', '131003', '广阳区');
INSERT INTO `sys_region` VALUES (195, '39B6DF70-C40E-491A-974A-C226E59776A8', '131022', '固安县');
INSERT INTO `sys_region` VALUES (196, '9F1FFE83-201F-4D2B-B5D3-805A43B045A7', '131023', '永清县');
INSERT INTO `sys_region` VALUES (197, 'D925534A-5073-432C-9CA8-A9967189B88B', '131024', '香河县');
INSERT INTO `sys_region` VALUES (198, 'A66BC268-5DEF-43A5-B3BB-1B39EA762479', '131025', '大城县');
INSERT INTO `sys_region` VALUES (199, '3EEBC418-352E-451F-AF80-D759488750DF', '131026', '文安县');
INSERT INTO `sys_region` VALUES (200, '6D40773E-10CD-4C13-BE14-1860ED3F61CB', '131028', '大厂回族自治县');
INSERT INTO `sys_region` VALUES (201, 'E26B8F70-6655-4432-9CD6-F58D5F8B5E12', '131081', '霸州市');
INSERT INTO `sys_region` VALUES (202, 'A20FA07E-E28E-458B-B8D7-0EE38AE217F5', '131082', '三河市');
INSERT INTO `sys_region` VALUES (203, 'B18BE545-D795-4E35-BE31-B926A69C0AC8', '131100', '衡水市');
INSERT INTO `sys_region` VALUES (204, '25BD5B25-717D-4E3B-B9A0-9FA9FBA9F04F', '131102', '桃城区');
INSERT INTO `sys_region` VALUES (205, 'DB53087F-D12B-4411-870C-6AC3D079405B', '131103', '冀州区');
INSERT INTO `sys_region` VALUES (206, 'F6D55AAD-6D58-46C3-9160-F15468818AFB', '131121', '枣强县');
INSERT INTO `sys_region` VALUES (207, 'AF496545-E726-4AF3-9506-818BE6E67631', '131122', '武邑县');
INSERT INTO `sys_region` VALUES (208, '47366D5A-439A-4680-8931-50334FCD5532', '131123', '武强县');
INSERT INTO `sys_region` VALUES (209, '70C44176-3841-480B-B1ED-565A51D492BC', '131124', '饶阳县');
INSERT INTO `sys_region` VALUES (210, '4256AB89-E961-4CAD-BAD9-505E9103C248', '131125', '安平县');
INSERT INTO `sys_region` VALUES (211, '6FB62B79-1F85-4538-A489-14FEBA503117', '131126', '故城县');
INSERT INTO `sys_region` VALUES (212, '61848EB0-FCB1-4431-BB64-B4C25A404ADB', '131127', '景县');
INSERT INTO `sys_region` VALUES (213, '065EF493-284A-447C-86A4-82180ED2017A', '131128', '阜城县');
INSERT INTO `sys_region` VALUES (214, 'A88F38B3-2C70-41E9-867C-3F9B5DC5D3BA', '131182', '深州市');
INSERT INTO `sys_region` VALUES (215, '9EA77FE4-CFBB-4C76-880A-9ECCEFC735D4', '140000', '山西省');
INSERT INTO `sys_region` VALUES (216, 'F646D389-17AA-426A-ABCF-49D563BB0A54', '140100', '太原市');
INSERT INTO `sys_region` VALUES (217, 'C3513BB9-1B71-4C52-A742-D8561634239F', '140105', '小店区');
INSERT INTO `sys_region` VALUES (218, 'A6F567DC-BC52-4E47-9805-F5B25DF43F76', '140106', '迎泽区');
INSERT INTO `sys_region` VALUES (219, '5345F5E8-FD8E-4A49-8622-F64AE53D30F2', '140107', '杏花岭区');
INSERT INTO `sys_region` VALUES (220, '58552343-245B-4C09-B508-C7B66EFED792', '140108', '尖草坪区');
INSERT INTO `sys_region` VALUES (221, '1A85B596-4CE5-4271-9110-E6514EED0F8A', '140109', '万柏林区');
INSERT INTO `sys_region` VALUES (222, '8A839E57-3826-4DFA-A2CA-149F86C4B979', '140110', '晋源区');
INSERT INTO `sys_region` VALUES (223, 'B101FBCF-6D0E-4947-BA5F-AAA7BFE0D4E7', '140121', '清徐县');
INSERT INTO `sys_region` VALUES (224, '689C9655-7FA7-4475-B273-C10A1B6091E9', '140122', '阳曲县');
INSERT INTO `sys_region` VALUES (225, '5B2E00A9-D991-43D7-A4B1-BCA8AB793111', '140123', '娄烦县');
INSERT INTO `sys_region` VALUES (226, 'EAF39D6C-BE25-420F-8EE5-2B372053B254', '140181', '古交市');
INSERT INTO `sys_region` VALUES (227, '745082FF-8085-498F-B45A-2C3808383523', '140200', '大同市');
INSERT INTO `sys_region` VALUES (228, 'DDAF4E0E-C088-471F-8883-46E72899F0BF', '140212', '新荣区');
INSERT INTO `sys_region` VALUES (229, 'D339C605-B7FC-4DE5-AD08-4BB8EC07462B', '140213', '平城区');
INSERT INTO `sys_region` VALUES (230, '1D18BCED-C581-46B2-9593-7D9C9DA5E7D2', '140214', '云冈区');
INSERT INTO `sys_region` VALUES (231, '0C2087A5-6224-4B90-B8D4-E5F936263CEF', '140215', '云州区');
INSERT INTO `sys_region` VALUES (232, 'AE7E7932-8ADA-4EA7-9AE0-0CC741203E32', '140221', '阳高县');
INSERT INTO `sys_region` VALUES (233, 'C14DD080-E082-443F-BBAE-13ADE2DFE2CA', '140222', '天镇县');
INSERT INTO `sys_region` VALUES (234, 'A92D1498-0733-4586-976B-9028BE4DF30B', '140223', '广灵县');
INSERT INTO `sys_region` VALUES (235, '0AA4A9FD-669A-4821-91C7-0C84EC9A77C9', '140224', '灵丘县');
INSERT INTO `sys_region` VALUES (236, 'F07789FD-5C7E-43CC-8ED3-2CB7F53058FC', '140225', '浑源县');
INSERT INTO `sys_region` VALUES (237, 'A286F8C9-C30D-48DF-B89C-9D94A5A30BA9', '140226', '左云县');
INSERT INTO `sys_region` VALUES (238, 'E38A913E-FBEB-44DB-8CA3-F4E4AED53444', '140300', '阳泉市');
INSERT INTO `sys_region` VALUES (239, '4BCC027D-C58C-4E3D-B792-1112C387B54D', '140302', '城区');
INSERT INTO `sys_region` VALUES (240, 'D50C01CA-3780-4FB6-8425-25AF32F0EC4F', '140303', '矿区');
INSERT INTO `sys_region` VALUES (241, 'E181259C-4570-4AC8-8AE0-5E0771F8375A', '140311', '郊区');
INSERT INTO `sys_region` VALUES (242, 'A55FEDEF-228B-41BA-A816-9E9D010E7080', '140321', '平定县');
INSERT INTO `sys_region` VALUES (243, '2EA28113-5DDF-4F84-8D4A-A0CACDE2059D', '140322', '盂县');
INSERT INTO `sys_region` VALUES (244, 'E9B901A6-F420-4375-9C8A-C556FBA121A1', '140400', '长治市');
INSERT INTO `sys_region` VALUES (245, '59314A7F-72E9-4F6C-804C-03DD2C4D67AA', '140403', '潞州区');
INSERT INTO `sys_region` VALUES (246, '6CBA27EF-66D4-460A-93D8-DAE22F961D54', '140404', '上党区');
INSERT INTO `sys_region` VALUES (247, '9BC588BA-6ABD-4C23-A364-506F6695BB26', '140405', '屯留区');
INSERT INTO `sys_region` VALUES (248, '5C02E9C1-6288-4DE1-A45C-A0CC77F5E7DF', '140406', '潞城区');
INSERT INTO `sys_region` VALUES (249, '69F03CC6-2FA2-4BDD-8FEB-1B35D913EAA1', '140423', '襄垣县');
INSERT INTO `sys_region` VALUES (250, '73916DD1-BC07-4259-8C4D-D1EF9DC89DE3', '140425', '平顺县');
INSERT INTO `sys_region` VALUES (251, '2BD11707-FBFE-4A52-88BD-164CC83490BE', '140426', '黎城县');
INSERT INTO `sys_region` VALUES (252, '92CA142E-D3FE-4FC7-B49F-C8D1A0E3579D', '140427', '壶关县');
INSERT INTO `sys_region` VALUES (253, '318FDC1C-4990-4843-984A-5F7DF04DD9DD', '140428', '长子县');
INSERT INTO `sys_region` VALUES (254, 'EBB09BE0-EE4D-48CA-BF08-B6B392E4F897', '140429', '武乡县');
INSERT INTO `sys_region` VALUES (255, 'F99015F0-AE04-4856-A710-FB0FAAE9BF67', '140430', '沁县');
INSERT INTO `sys_region` VALUES (256, '0F63E171-99B1-4171-9EDF-50DCE6CDB7F1', '140431', '沁源县');
INSERT INTO `sys_region` VALUES (257, '19DEEB61-E1E2-4D8B-AF5F-67FA2B551313', '140500', '晋城市');
INSERT INTO `sys_region` VALUES (258, 'AD018885-B990-4DD5-834E-EF0AA4970245', '140502', '城区');
INSERT INTO `sys_region` VALUES (259, '6EDF3F2E-72EF-4A36-B1AF-146001E8148A', '140521', '沁水县');
INSERT INTO `sys_region` VALUES (260, '95A9A352-8394-4924-BA8B-144E16A99EC6', '140522', '阳城县');
INSERT INTO `sys_region` VALUES (261, '2B7FD0C2-6647-4843-B6FA-ED4A81E79306', '140524', '陵川县');
INSERT INTO `sys_region` VALUES (262, '6A899810-84A5-4D62-80E5-503195BAAFF9', '140525', '泽州县');
INSERT INTO `sys_region` VALUES (263, 'CD009512-9744-4C06-87C0-F9C290402F5E', '140581', '高平市');
INSERT INTO `sys_region` VALUES (264, 'D41C85C4-C763-4F60-9ED8-C5059C4DEAAC', '140600', '朔州市');
INSERT INTO `sys_region` VALUES (265, 'ABA04267-A344-4ACC-AFE8-1C28E386BD9D', '140602', '朔城区');
INSERT INTO `sys_region` VALUES (266, '6C644DF7-B98E-4EF4-A8CD-0E8FF1B1B4DD', '140603', '平鲁区');
INSERT INTO `sys_region` VALUES (267, '2498CB65-F3B5-42FC-8ADF-493434B130BA', '140621', '山阴县');
INSERT INTO `sys_region` VALUES (268, '1DA1B5BE-B94D-42AC-A995-CE4990C3CB9A', '140622', '应县');
INSERT INTO `sys_region` VALUES (269, '1B893AAC-8331-4919-A3E3-C39B128D7945', '140623', '右玉县');
INSERT INTO `sys_region` VALUES (270, 'F277B3FF-B069-4704-9727-6FA7594264B5', '140681', '怀仁市');
INSERT INTO `sys_region` VALUES (271, '8FE08011-01CB-41E1-A01E-6C944FE184E9', '140700', '晋中市');
INSERT INTO `sys_region` VALUES (272, '9E3B53C0-C979-404A-BD17-C2305FA9E7A8', '140702', '榆次区');
INSERT INTO `sys_region` VALUES (273, '6D92F74B-1F6F-4B24-8205-39CFC732040E', '140703', '太谷区');
INSERT INTO `sys_region` VALUES (274, 'E8B6F4BC-12E3-4AC1-B094-2BD69F4C8FC8', '140721', '榆社县');
INSERT INTO `sys_region` VALUES (275, '1CDC47A2-3768-4548-AC8B-4359A7E5C018', '140722', '左权县');
INSERT INTO `sys_region` VALUES (276, '4A3555B1-A498-499A-B41B-D4B81CC0A5A4', '140723', '和顺县');
INSERT INTO `sys_region` VALUES (277, 'EF8DB2FF-E3C2-4529-BB44-D7C4F9D42C45', '140724', '昔阳县');
INSERT INTO `sys_region` VALUES (278, '4928F02F-FC07-4E8F-B016-4AEBC1FF3CA1', '140725', '寿阳县');
INSERT INTO `sys_region` VALUES (279, '18EBA1ED-36F9-4960-AB8C-9E15DDD60ECE', '140727', '祁县');
INSERT INTO `sys_region` VALUES (280, '9A3F4A88-9775-4562-A1C1-BA45B3C73146', '140728', '平遥县');
INSERT INTO `sys_region` VALUES (281, 'F11C75B1-A079-4ECD-B878-59B4C04510FB', '140729', '灵石县');
INSERT INTO `sys_region` VALUES (282, 'E5F4F4B7-5C87-4D9D-BF01-D0A444A2E14F', '140781', '介休市');
INSERT INTO `sys_region` VALUES (283, '4D7E6D6A-9959-4791-AE60-3A8D56CD1968', '140800', '运城市');
INSERT INTO `sys_region` VALUES (284, 'BF5AA6AB-A340-478E-9335-419DEDD29251', '140802', '盐湖区');
INSERT INTO `sys_region` VALUES (285, 'AF6817DF-2615-46B9-A03B-6F19656325A5', '140821', '临猗县');
INSERT INTO `sys_region` VALUES (286, 'CC143859-561A-4209-BAE3-39C65F80627C', '140822', '万荣县');
INSERT INTO `sys_region` VALUES (287, 'ACE64281-3AC9-4E50-8F40-0AD91B7C0ECE', '140823', '闻喜县');
INSERT INTO `sys_region` VALUES (288, 'EBDBEAA5-8CFE-4F9C-8BCC-07BD8C43DF5C', '140824', '稷山县');
INSERT INTO `sys_region` VALUES (289, 'B1964255-1FDB-4726-85F9-1389C41635E9', '140825', '新绛县');
INSERT INTO `sys_region` VALUES (290, 'D57004D6-881F-4942-B473-3EEBF13A8F2D', '140826', '绛县');
INSERT INTO `sys_region` VALUES (291, '022540D8-8C59-4DCF-A1C6-38C44362E82C', '140827', '垣曲县');
INSERT INTO `sys_region` VALUES (292, '3287904D-F6C9-4005-8F0E-F518B8CF1DB1', '140828', '夏县');
INSERT INTO `sys_region` VALUES (293, '6352D230-6C94-4894-9000-EC8FBF15CAF9', '140829', '平陆县');
INSERT INTO `sys_region` VALUES (294, '860E21A3-AC3D-405A-B097-7E8798E26410', '140830', '芮城县');
INSERT INTO `sys_region` VALUES (295, '5640F2AA-3671-4F98-8A02-FF51707F42D8', '140881', '永济市');
INSERT INTO `sys_region` VALUES (296, '676BCA54-BD81-4BD4-BE1D-A416B44FDA2A', '140882', '河津市');
INSERT INTO `sys_region` VALUES (297, '2A9E7E9D-1F66-4BA6-B739-E9D8C3278CBB', '140900', '忻州市');
INSERT INTO `sys_region` VALUES (298, '54A1A13D-3A61-4FA5-9D3F-DBFB7816DC5B', '140902', '忻府区');
INSERT INTO `sys_region` VALUES (299, '430DEF28-9FC5-449B-9A6F-BEB38A8C7C82', '140921', '定襄县');
INSERT INTO `sys_region` VALUES (300, 'BB21909D-6787-4008-AADB-C7C673A491AF', '140922', '五台县');
INSERT INTO `sys_region` VALUES (301, 'B6ADF22A-B9CB-4516-BA43-D1F5034BBF56', '140923', '代县');
INSERT INTO `sys_region` VALUES (302, '93B2B50E-DFC7-4616-A86D-EE19E4CF4FDC', '140924', '繁峙县');
INSERT INTO `sys_region` VALUES (303, 'D5BCB5FC-4ECA-4412-96FF-CCF97159B17D', '140925', '宁武县');
INSERT INTO `sys_region` VALUES (304, '6D25D99B-E384-4FBA-AAF7-C5546F2D8F6C', '140926', '静乐县');
INSERT INTO `sys_region` VALUES (305, 'BC8BCC21-0C60-4375-BD54-B2F05A868573', '140927', '神池县');
INSERT INTO `sys_region` VALUES (306, '47214F52-32E3-4C4B-8413-FBF49A587733', '140928', '五寨县');
INSERT INTO `sys_region` VALUES (307, 'C5B2DE91-3080-4CD6-93CF-0119ED043B2E', '140929', '岢岚县');
INSERT INTO `sys_region` VALUES (308, '54DF5E45-1BB9-40F1-8351-FA92F8CC5962', '140930', '河曲县');
INSERT INTO `sys_region` VALUES (309, '0E5CFBFC-C971-4E9D-8E9D-4577E753C30B', '140931', '保德县');
INSERT INTO `sys_region` VALUES (310, 'FFEA2B81-45DD-4C22-A7CE-8BA4B9204090', '140932', '偏关县');
INSERT INTO `sys_region` VALUES (311, '99B48572-373B-4178-8CE1-2C64DCD9908C', '140981', '原平市');
INSERT INTO `sys_region` VALUES (312, '5FA97160-8E21-4035-BDB0-23841DC52647', '141000', '临汾市');
INSERT INTO `sys_region` VALUES (313, '5E22E517-F9F8-410F-B036-DFD7E4F93F8C', '141002', '尧都区');
INSERT INTO `sys_region` VALUES (314, '88318950-AB3E-437E-AE2F-6BC01C28E89E', '141021', '曲沃县');
INSERT INTO `sys_region` VALUES (315, '550038B7-AB57-48A5-9AEF-1B93E8A7EC89', '141022', '翼城县');
INSERT INTO `sys_region` VALUES (316, 'DDBC123F-8B1F-4D8D-A95D-12C99B130AFF', '141023', '襄汾县');
INSERT INTO `sys_region` VALUES (317, 'A7A124B0-8A53-48C9-A75F-15822C7D425F', '141024', '洪洞县');
INSERT INTO `sys_region` VALUES (318, '25197ADF-CFE1-45DE-AD28-41DC21C97A2E', '141025', '古县');
INSERT INTO `sys_region` VALUES (319, 'A105D891-5EE1-4147-9583-B696D07EB750', '141026', '安泽县');
INSERT INTO `sys_region` VALUES (320, 'AC1E782B-B16D-445B-B924-B1B59E2109FF', '141027', '浮山县');
INSERT INTO `sys_region` VALUES (321, '4A007533-C096-4CEE-9F30-4C6991D8B2EE', '141028', '吉县');
INSERT INTO `sys_region` VALUES (322, 'C088D670-5A66-48D1-823A-041B3B18049B', '141029', '乡宁县');
INSERT INTO `sys_region` VALUES (323, 'C52EDF1B-FAB7-4458-8F17-7AA0D7CC44A6', '141030', '大宁县');
INSERT INTO `sys_region` VALUES (324, 'D446F4CC-0B9B-4A1A-B620-ACCC7C1BF907', '141031', '隰县');
INSERT INTO `sys_region` VALUES (325, '3BA52A8D-F0A4-4DDE-8C8B-9B97672880B7', '141032', '永和县');
INSERT INTO `sys_region` VALUES (326, 'FE09928F-635C-4728-A99E-25586EB222EC', '141033', '蒲县');
INSERT INTO `sys_region` VALUES (327, '7C471E8C-21D0-4564-900D-69EA28E32403', '141034', '汾西县');
INSERT INTO `sys_region` VALUES (328, 'BE6AEC85-57BD-4FA8-B0E8-068C637ADBE2', '141081', '侯马市');
INSERT INTO `sys_region` VALUES (329, '240C209E-ADAB-494D-902B-4F1463A54712', '141082', '霍州市');
INSERT INTO `sys_region` VALUES (330, '88B6316D-96C4-4B40-8FAB-98E69D33F7B3', '141100', '吕梁市');
INSERT INTO `sys_region` VALUES (331, '62A60F35-180D-4D64-A365-6B7149D256CC', '141102', '离石区');
INSERT INTO `sys_region` VALUES (332, '79161BF9-ACA5-4012-BF39-050813FC88F5', '141121', '文水县');
INSERT INTO `sys_region` VALUES (333, 'D5B1694F-8E72-41E9-AD30-60010344D7B7', '141122', '交城县');
INSERT INTO `sys_region` VALUES (334, '65974BCB-6561-4C7B-92AA-04A7A07565B5', '141123', '兴县');
INSERT INTO `sys_region` VALUES (335, '04F5CA0C-A8AB-4C39-8EFF-3159554A583E', '141124', '临县');
INSERT INTO `sys_region` VALUES (336, 'D81C73AF-F9E4-4202-9928-A04DDDCD1923', '141125', '柳林县');
INSERT INTO `sys_region` VALUES (337, '0A7A47CC-197A-4A7F-96EC-27F6DE87B99C', '141126', '石楼县');
INSERT INTO `sys_region` VALUES (338, '8F633680-BF68-40FC-8BDA-596A64318E64', '141127', '岚县');
INSERT INTO `sys_region` VALUES (339, 'DAD9E3A9-6A71-434B-BC49-5EBEE32A8EE1', '141128', '方山县');
INSERT INTO `sys_region` VALUES (340, 'BCE3354E-4568-40E8-98C9-512E7411134C', '141129', '中阳县');
INSERT INTO `sys_region` VALUES (341, 'D6AA38EC-DD30-4D63-ADC9-1A88B310587E', '141130', '交口县');
INSERT INTO `sys_region` VALUES (342, 'CA5EE4C6-364C-4528-9682-D9A4C0E0B0C3', '141181', '孝义市');
INSERT INTO `sys_region` VALUES (343, '8E6A0084-E4E8-4E17-AABD-1BECF54C282B', '141182', '汾阳市');
INSERT INTO `sys_region` VALUES (344, '98EE5287-F66B-48F2-AC52-8B2279564490', '150000', '内蒙古自治区');
INSERT INTO `sys_region` VALUES (345, '7F1CADA6-DEE4-4298-B20B-F7891750AEFA', '150100', '呼和浩特市');
INSERT INTO `sys_region` VALUES (346, 'BF2BA910-9B07-4E5E-B34F-811B2D775EB6', '150102', '新城区');
INSERT INTO `sys_region` VALUES (347, '6EB19479-0BC0-4A62-A51F-E79441E7D4EE', '150103', '回民区');
INSERT INTO `sys_region` VALUES (348, '31040101-A44F-46AE-99B1-761A352A2F72', '150104', '玉泉区');
INSERT INTO `sys_region` VALUES (349, '1822BEE4-5FD3-4015-876E-DF416C6E39D1', '150105', '赛罕区');
INSERT INTO `sys_region` VALUES (350, 'B7FD5DAD-B006-4C5F-969B-04101C1F51BA', '150121', '土默特左旗');
INSERT INTO `sys_region` VALUES (351, '13032C1E-919B-4546-BF5C-7733288E3EFA', '150122', '托克托县');
INSERT INTO `sys_region` VALUES (352, '9C3190D9-7D5D-4396-B736-86ABCBD736D0', '150123', '和林格尔县');
INSERT INTO `sys_region` VALUES (353, '5ABA85D6-A221-4A8D-9C44-CED6509E00FB', '150124', '清水河县');
INSERT INTO `sys_region` VALUES (354, 'FCFAC02F-315D-4C87-874F-346988BC56B4', '150125', '武川县');
INSERT INTO `sys_region` VALUES (355, '0974760D-37B3-42E7-A4FB-29F74016037E', '150200', '包头市');
INSERT INTO `sys_region` VALUES (356, 'C1D81EBA-BBFA-43F1-86C8-03683E9BDCCF', '150202', '东河区');
INSERT INTO `sys_region` VALUES (357, 'C14573C3-185C-44FC-9443-B6E12EC3A306', '150203', '昆都仑区');
INSERT INTO `sys_region` VALUES (358, '533F2FF6-6600-41D6-B188-70FB7023748B', '150204', '青山区');
INSERT INTO `sys_region` VALUES (359, '54EC03D1-800A-4890-9968-65E9FF0FFDCA', '150205', '石拐区');
INSERT INTO `sys_region` VALUES (360, '13B92C5F-384C-4A88-BB97-7B1A6BEA7CFB', '150206', '白云鄂博矿区');
INSERT INTO `sys_region` VALUES (361, '82166289-BA0C-488F-90AF-F70CFBC7DB1B', '150207', '九原区');
INSERT INTO `sys_region` VALUES (362, '4C83A878-C539-4085-BFA6-99834C0966F2', '150221', '土默特右旗');
INSERT INTO `sys_region` VALUES (363, '6105725A-1564-4AF7-89C4-D78631244C48', '150222', '固阳县');
INSERT INTO `sys_region` VALUES (364, '76FEFF0F-AC50-477D-90CE-8CFCF06F29F7', '150223', '达尔罕茂明安联合旗');
INSERT INTO `sys_region` VALUES (365, 'A2BC0D3F-072A-400A-BE32-E213EB1F0C5C', '150300', '乌海市');
INSERT INTO `sys_region` VALUES (366, 'E9C30D0F-4F6E-492A-92C1-EFDCEAC4442B', '150302', '海勃湾区');
INSERT INTO `sys_region` VALUES (367, 'EDB35045-865C-4A32-8512-E54253254F7A', '150303', '海南区');
INSERT INTO `sys_region` VALUES (368, 'EA5A2037-D8D8-4168-9BCD-794AD1DCB23F', '150304', '乌达区');
INSERT INTO `sys_region` VALUES (369, 'CF878823-2A4A-48AF-BC36-6B69ED24B678', '150400', '赤峰市');
INSERT INTO `sys_region` VALUES (370, 'C26E9C67-2E65-4A80-95F0-4CCC4CF3F36D', '150402', '红山区');
INSERT INTO `sys_region` VALUES (371, '2761688E-7E84-4083-BB42-216C3208370C', '150403', '元宝山区');
INSERT INTO `sys_region` VALUES (372, 'D743FB1F-59D4-4B18-B139-DAEB932716A9', '150404', '松山区');
INSERT INTO `sys_region` VALUES (373, '7D1EE1AA-BD50-4202-B78E-FB5654BC2B95', '150421', '阿鲁科尔沁旗');
INSERT INTO `sys_region` VALUES (374, '7E24D21B-C2D2-42CE-8875-2D12A4511026', '150422', '巴林左旗');
INSERT INTO `sys_region` VALUES (375, '9D989E06-93AA-4269-B61D-80EA217CBD6D', '150423', '巴林右旗');
INSERT INTO `sys_region` VALUES (376, 'F20CBA62-B77E-4CEC-9BFD-2317469C9FD6', '150424', '林西县');
INSERT INTO `sys_region` VALUES (377, '6CD917D2-CC2D-4B70-9541-018327AA6E4C', '150425', '克什克腾旗');
INSERT INTO `sys_region` VALUES (378, 'E8662BC7-D19B-43FA-9908-3B890A2E250B', '150426', '翁牛特旗');
INSERT INTO `sys_region` VALUES (379, '9D5F7203-E77D-4D9A-A6F6-CBFC516B62A5', '150428', '喀喇沁旗');
INSERT INTO `sys_region` VALUES (380, 'E4F111D1-D08C-43D2-9C48-DB3543363665', '150429', '宁城县');
INSERT INTO `sys_region` VALUES (381, 'F7D8C37C-5DB7-46CD-A4AC-7CED66D49109', '150430', '敖汉旗');
INSERT INTO `sys_region` VALUES (382, '53128D88-0963-451F-BF20-57F69C128909', '150500', '通辽市');
INSERT INTO `sys_region` VALUES (383, '90DC90E3-674E-45BD-B4AE-B45ABFFF705C', '150502', '科尔沁区');
INSERT INTO `sys_region` VALUES (384, '400F4752-E24A-4ACC-88BF-F59BB7BFE8DB', '150521', '科尔沁左翼中旗');
INSERT INTO `sys_region` VALUES (385, 'C1778753-6B62-4403-9622-E763ACC20E2E', '150522', '科尔沁左翼后旗');
INSERT INTO `sys_region` VALUES (386, '382B54E3-2077-4AA8-A7BD-8D98832D9C94', '150523', '开鲁县');
INSERT INTO `sys_region` VALUES (387, '7E6F1A50-74CC-4551-988C-D0D7172EE1DE', '150524', '库伦旗');
INSERT INTO `sys_region` VALUES (388, 'F13F9372-F296-40C1-9D5D-B7CA05BB350B', '150525', '奈曼旗');
INSERT INTO `sys_region` VALUES (389, '52CBD930-8E3C-4A7C-9596-520BED3C24EE', '150526', '扎鲁特旗');
INSERT INTO `sys_region` VALUES (390, '58B5B1A4-28B0-464C-839C-6A8850C42317', '150581', '霍林郭勒市');
INSERT INTO `sys_region` VALUES (391, '9FB11748-619B-4770-AB6C-D609B73672C9', '150600', '鄂尔多斯市');
INSERT INTO `sys_region` VALUES (392, '0BEEF591-B3C7-4B4E-BA85-A3BEE64E4B1A', '150602', '东胜区');
INSERT INTO `sys_region` VALUES (393, 'E03AB8D7-365D-47E6-AADC-97E06CD21108', '150603', '康巴什区');
INSERT INTO `sys_region` VALUES (394, '19232607-C2B2-4838-A3E1-376A3749058E', '150621', '达拉特旗');
INSERT INTO `sys_region` VALUES (395, '0E4C0C02-8C58-4A19-AB53-7AFAB4D1E710', '150622', '准格尔旗');
INSERT INTO `sys_region` VALUES (396, '329F9358-CDE3-48BA-9E8C-8C7BBE3447E0', '150623', '鄂托克前旗');
INSERT INTO `sys_region` VALUES (397, 'CE82FC4C-CA32-46F0-BB5F-EB4F2F5EEB01', '150624', '鄂托克旗');
INSERT INTO `sys_region` VALUES (398, 'C168C455-9486-46E9-B58B-54B1FB491D99', '150625', '杭锦旗');
INSERT INTO `sys_region` VALUES (399, '97F4BB0B-51D2-4BF7-AF05-909C6AA503F0', '150626', '乌审旗');
INSERT INTO `sys_region` VALUES (400, '45B0829A-05A7-4689-8351-ED5C41218B9E', '150627', '伊金霍洛旗');
INSERT INTO `sys_region` VALUES (401, '6815285F-30D2-4DF3-BA20-944063A18496', '150700', '呼伦贝尔市');
INSERT INTO `sys_region` VALUES (402, 'FF8FB970-434D-4CBB-983E-A8D0C4DA446E', '150702', '海拉尔区');
INSERT INTO `sys_region` VALUES (403, '1FF2BE29-2747-4C65-8282-3163BA0B70B2', '150703', '扎赉诺尔区');
INSERT INTO `sys_region` VALUES (404, '00182417-4870-475D-A8DD-01E4A0F93D75', '150721', '阿荣旗');
INSERT INTO `sys_region` VALUES (405, 'CE36D211-6633-46D2-9D69-342D295D0E31', '150722', '莫力达瓦达斡尔族自治旗');
INSERT INTO `sys_region` VALUES (406, 'FAAFFA16-D09C-480C-A859-7D0A6BA35A69', '150723', '鄂伦春自治旗');
INSERT INTO `sys_region` VALUES (407, 'CD12AF5A-E70B-4A87-A674-3E8E92BF63C2', '150724', '鄂温克族自治旗');
INSERT INTO `sys_region` VALUES (408, '5CCDB521-9EFE-4DF8-96DE-6B2FF7F0B1D4', '150725', '陈巴尔虎旗');
INSERT INTO `sys_region` VALUES (409, '41021AE1-DEE1-40BD-B57D-B7202F0FCB31', '150726', '新巴尔虎左旗');
INSERT INTO `sys_region` VALUES (410, '379A5C94-BE3A-4ECE-B438-6246563FFA4C', '150727', '新巴尔虎右旗');
INSERT INTO `sys_region` VALUES (411, '99F6C43B-16C3-4A08-B9EC-ED27CA1B1329', '150781', '满洲里市');
INSERT INTO `sys_region` VALUES (412, '98918261-4E4C-4B87-8D9E-79309D86FD86', '150782', '牙克石市');
INSERT INTO `sys_region` VALUES (413, 'A2DBA111-3579-4F8C-AFB4-98BBBC518BD1', '150783', '扎兰屯市');
INSERT INTO `sys_region` VALUES (414, '601D33FD-28FA-45F1-A0C0-8AE29F276DF1', '150784', '额尔古纳市');
INSERT INTO `sys_region` VALUES (415, 'FAD57AEB-D21D-4609-A3F0-247819D67A8E', '150785', '根河市');
INSERT INTO `sys_region` VALUES (416, '7566715E-03DB-4B73-902D-AD8ACB22AC19', '150800', '巴彦淖尔市');
INSERT INTO `sys_region` VALUES (417, '7B333946-8858-4387-A210-2A1D06940535', '150802', '临河区');
INSERT INTO `sys_region` VALUES (418, '6781750E-97D4-42DB-8573-2D75F7948F95', '150821', '五原县');
INSERT INTO `sys_region` VALUES (419, '918548E0-F441-43B4-A6BD-8CE5925199C3', '150822', '磴口县');
INSERT INTO `sys_region` VALUES (420, '2B0B9E0B-7B1B-4C1C-97CC-715400F9E61A', '150823', '乌拉特前旗');
INSERT INTO `sys_region` VALUES (421, 'A29C6DD2-B368-412F-8010-6E50AB159B09', '150824', '乌拉特中旗');
INSERT INTO `sys_region` VALUES (422, 'D9222D41-2373-4E3A-B79B-A9D95C993739', '150825', '乌拉特后旗');
INSERT INTO `sys_region` VALUES (423, '3C825A15-D300-4FAF-AD33-1FE56DFA491E', '150826', '杭锦后旗');
INSERT INTO `sys_region` VALUES (424, '8DB5D2D1-412F-4B2D-9CFA-4B927DB5E4BB', '150900', '乌兰察布市');
INSERT INTO `sys_region` VALUES (425, '9F2B0FCC-7A60-455B-B1D6-F6B62EB620B0', '150902', '集宁区');
INSERT INTO `sys_region` VALUES (426, 'F212770A-9AFE-4050-83A2-FB8447E0FD44', '150921', '卓资县');
INSERT INTO `sys_region` VALUES (427, 'B1F8113A-E0DD-4A8B-A02D-8A015A025C26', '150922', '化德县');
INSERT INTO `sys_region` VALUES (428, '2916BAC3-3B1C-4746-B9B5-27676E091F6A', '150923', '商都县');
INSERT INTO `sys_region` VALUES (429, '5FD2B10B-B3AE-439A-9AE9-70DEA8280494', '150924', '兴和县');
INSERT INTO `sys_region` VALUES (430, 'CD1E6BBA-70ED-4624-982B-7C4EDDB2208B', '150925', '凉城县');
INSERT INTO `sys_region` VALUES (431, '572FEE76-4150-45CC-A104-6641AFAA0347', '150926', '察哈尔右翼前旗');
INSERT INTO `sys_region` VALUES (432, '398ADAF7-33FE-46ED-8A4A-40C62C580BB0', '150927', '察哈尔右翼中旗');
INSERT INTO `sys_region` VALUES (433, '33681573-7F02-4A4C-96E7-85D36EE8D1AE', '150928', '察哈尔右翼后旗');
INSERT INTO `sys_region` VALUES (434, 'EAEF67D0-29DF-4C4C-A0BD-FD135C4E09CC', '150929', '四子王旗');
INSERT INTO `sys_region` VALUES (435, '6A6C2833-E2B3-4C79-9C55-C8DB31CA1627', '150981', '丰镇市');
INSERT INTO `sys_region` VALUES (436, '3937FC61-5309-49BD-8CA4-30A9C62ECBCC', '152200', '兴安盟');
INSERT INTO `sys_region` VALUES (437, 'BC0BF925-B06C-4880-A4D5-FFB69F8E0753', '152201', '乌兰浩特市');
INSERT INTO `sys_region` VALUES (438, 'F136DC74-3830-4DE6-A48E-CA03889A649D', '152202', '阿尔山市');
INSERT INTO `sys_region` VALUES (439, 'C9161759-6B71-4BE9-A66C-80C371C47511', '152221', '科尔沁右翼前旗');
INSERT INTO `sys_region` VALUES (440, '9BEF3B5C-DFA0-40CC-A52E-C4648FC963FE', '152222', '科尔沁右翼中旗');
INSERT INTO `sys_region` VALUES (441, '4C08ACE3-CEA9-4497-8CFC-0A32F69EE26C', '152223', '扎赉特旗');
INSERT INTO `sys_region` VALUES (442, '0121E502-2C43-48B3-A7BC-F9B9DD692AD2', '152224', '突泉县');
INSERT INTO `sys_region` VALUES (443, '0D2B9AF1-1B16-4B72-8BD5-9BACE735D080', '152500', '锡林郭勒盟');
INSERT INTO `sys_region` VALUES (444, 'FAF0C21F-2D0F-4B15-81B8-487C95AB2C21', '152501', '二连浩特市');
INSERT INTO `sys_region` VALUES (445, 'D3DFDEA4-C8D6-4496-8DAD-DBD9E1E56161', '152502', '锡林浩特市');
INSERT INTO `sys_region` VALUES (446, '541110D3-920B-4D70-B519-4D88A3D8B90E', '152522', '阿巴嘎旗');
INSERT INTO `sys_region` VALUES (447, 'CE1C3900-8A87-46BA-9012-C2552CEBA66E', '152523', '苏尼特左旗');
INSERT INTO `sys_region` VALUES (448, '97939D8E-7F85-4FF7-9B4A-B7D416BD3C20', '152524', '苏尼特右旗');
INSERT INTO `sys_region` VALUES (449, '6B3A89D1-AE14-48E6-B087-9885B41EF23D', '152525', '东乌珠穆沁旗');
INSERT INTO `sys_region` VALUES (450, '572731BC-E488-4364-B76A-4F929D1624E8', '152526', '西乌珠穆沁旗');
INSERT INTO `sys_region` VALUES (451, '6D86D7E9-BAFA-4543-947E-724DCD8A1DA3', '152527', '太仆寺旗');
INSERT INTO `sys_region` VALUES (452, '3229050F-889D-40AC-B0AE-52B814B48FC2', '152528', '镶黄旗');
INSERT INTO `sys_region` VALUES (453, 'BD46A818-7BBB-4546-9EE5-F4F32189997C', '152529', '正镶白旗');
INSERT INTO `sys_region` VALUES (454, 'B675B3E7-0D64-4DD5-9A9E-66A611560585', '152530', '正蓝旗');
INSERT INTO `sys_region` VALUES (455, 'E048F163-25E0-43D4-B17E-7BAE22FDF827', '152531', '多伦县');
INSERT INTO `sys_region` VALUES (456, '79EC545F-CAE9-4F3B-A1EA-5CB26CD62D19', '152900', '阿拉善盟');
INSERT INTO `sys_region` VALUES (457, '06F5FBB7-3964-4377-B0E7-2AB35EDBD843', '152921', '阿拉善左旗');
INSERT INTO `sys_region` VALUES (458, '2BC1A072-820D-400E-A149-96BFB1A09C01', '152922', '阿拉善右旗');
INSERT INTO `sys_region` VALUES (459, '6C4DAC2C-3AD9-4885-AEF7-76813CBF68AF', '152923', '额济纳旗');
INSERT INTO `sys_region` VALUES (460, 'A6C0E882-911C-4E11-A55F-07B44FADA193', '210000', '辽宁省');
INSERT INTO `sys_region` VALUES (461, '15B55D55-B009-4ACF-B2AC-755585FE85E1', '210100', '沈阳市');
INSERT INTO `sys_region` VALUES (462, 'A6C970CF-1B7C-4401-8014-63A91DE2ED22', '210102', '和平区');
INSERT INTO `sys_region` VALUES (463, '424FBB57-7E9A-4EC1-838C-8B182E342D4A', '210103', '沈河区');
INSERT INTO `sys_region` VALUES (464, '0F8F94DC-7F1B-4C95-AB11-5297F01E0D04', '210104', '大东区');
INSERT INTO `sys_region` VALUES (465, 'DBCCA857-9F7A-4AD5-BA8B-64851BBE6D57', '210105', '皇姑区');
INSERT INTO `sys_region` VALUES (466, '914E9A61-DC2E-4317-8758-CD787E82410C', '210106', '铁西区');
INSERT INTO `sys_region` VALUES (467, 'C4214B1D-1D72-47EE-8A5F-C4CAEB16B46B', '210111', '苏家屯区');
INSERT INTO `sys_region` VALUES (468, '869C3DC1-FE4E-4FA3-B662-87FC747404CE', '210112', '浑南区');
INSERT INTO `sys_region` VALUES (469, '479956DB-F06B-4200-91D6-28E57A661EFF', '210113', '沈北新区');
INSERT INTO `sys_region` VALUES (470, '7971EC2D-0963-4F24-B33E-7F52E8EAA28A', '210114', '于洪区');
INSERT INTO `sys_region` VALUES (471, 'D099AAD7-780E-4BF1-AD90-1FDD698A4832', '210115', '辽中区');
INSERT INTO `sys_region` VALUES (472, '30B5F737-BC03-4C54-BEEC-8980ABCA7E8D', '210123', '康平县');
INSERT INTO `sys_region` VALUES (473, 'E26849A2-72D1-4D8C-94AC-FB21C6267B27', '210124', '法库县');
INSERT INTO `sys_region` VALUES (474, '4ECDF1A3-C683-47B5-B654-DCC370EA59DA', '210181', '新民市');
INSERT INTO `sys_region` VALUES (475, 'E8B602A8-BB3C-40A1-A354-518D16E7A491', '210200', '大连市');
INSERT INTO `sys_region` VALUES (476, '32A4F866-5BD1-44D6-9A66-7D2A94FCA37F', '210202', '中山区');
INSERT INTO `sys_region` VALUES (477, 'F03B88BC-3158-4455-B2D7-3EC2C980493E', '210203', '西岗区');
INSERT INTO `sys_region` VALUES (478, '32045442-0BA6-42D8-A94C-5B5A850FD6FF', '210204', '沙河口区');
INSERT INTO `sys_region` VALUES (479, 'C07A7FC3-A25B-4DC2-87D4-A6603D20223C', '210211', '甘井子区');
INSERT INTO `sys_region` VALUES (480, '88D60651-AE64-4724-9EDD-190D946B1C03', '210212', '旅顺口区');
INSERT INTO `sys_region` VALUES (481, '6971855F-C1C9-4D4F-B4C3-12CF198C2EBF', '210213', '金州区');
INSERT INTO `sys_region` VALUES (482, '0C5F74C1-8860-4DBC-935E-EDFE5D2FB5D6', '210214', '普兰店区');
INSERT INTO `sys_region` VALUES (483, 'DD318E97-7D2B-441D-83F2-8BFBEBF95F94', '210224', '长海县');
INSERT INTO `sys_region` VALUES (484, '8D60CD67-AFA7-49DD-9D74-FB5855455B7E', '210281', '瓦房店市');
INSERT INTO `sys_region` VALUES (485, '6864E0A9-D337-47DC-BE49-7FA4C425CD53', '210283', '庄河市');
INSERT INTO `sys_region` VALUES (486, '44EFDC62-EC42-447E-A158-791A04F74A7D', '210300', '鞍山市');
INSERT INTO `sys_region` VALUES (487, '8881C0D8-D020-471D-BBD4-62B62A9F3816', '210302', '铁东区');
INSERT INTO `sys_region` VALUES (488, '85E57CC2-0528-431A-A894-EAB39EC73C3C', '210303', '铁西区');
INSERT INTO `sys_region` VALUES (489, '5C4E3D53-8ADE-4E77-B111-7C0F635EB6BF', '210304', '立山区');
INSERT INTO `sys_region` VALUES (490, '5A50EF8E-0AA6-4054-91C9-4CAC6975F7C2', '210311', '千山区');
INSERT INTO `sys_region` VALUES (491, 'FD188D9E-DF78-48FE-B976-36E297AEEBAE', '210321', '台安县');
INSERT INTO `sys_region` VALUES (492, '86027E71-A69E-470E-A4CC-29706EB5D154', '210323', '岫岩满族自治县');
INSERT INTO `sys_region` VALUES (493, 'C5231D27-C582-491A-A4C2-1B45A44E15AF', '210381', '海城市');
INSERT INTO `sys_region` VALUES (494, '1CAD08E6-61A6-44E7-87BD-D6570F86F68C', '210400', '抚顺市');
INSERT INTO `sys_region` VALUES (495, 'DAA9DB8B-5412-4D93-A089-5D7C97E451C1', '210402', '新抚区');
INSERT INTO `sys_region` VALUES (496, '39241508-4CDC-4858-A263-BBB2823BCC19', '210403', '东洲区');
INSERT INTO `sys_region` VALUES (497, '917BA765-EFB1-4716-9CED-4885B9AF17AE', '210404', '望花区');
INSERT INTO `sys_region` VALUES (498, '56BAF8D2-1AF5-4872-9AA2-749A53C9ABF5', '210411', '顺城区');
INSERT INTO `sys_region` VALUES (499, 'DD8A5B62-AFE6-4FC1-A60F-59ADC64E4158', '210421', '抚顺县');
INSERT INTO `sys_region` VALUES (500, '20A892A2-0073-445C-800A-882437E994F9', '210422', '新宾满族自治县');
INSERT INTO `sys_region` VALUES (501, '46C59340-4858-4241-A0B3-32AFC1D4DDB6', '210423', '清原满族自治县');
INSERT INTO `sys_region` VALUES (502, '6D85B027-A1CC-43FF-BCEC-16D3058A0BE1', '210500', '本溪市');
INSERT INTO `sys_region` VALUES (503, '41E0841C-772F-436B-855F-1AB6090E6276', '210502', '平山区');
INSERT INTO `sys_region` VALUES (504, '7465EB50-243C-479A-B5B4-241ADA4E5DC4', '210503', '溪湖区');
INSERT INTO `sys_region` VALUES (505, '528232EC-B446-45BC-812E-4CA7DBF3C967', '210504', '明山区');
INSERT INTO `sys_region` VALUES (506, '75003DEA-378A-4E7B-9962-F031A7ADFEA4', '210505', '南芬区');
INSERT INTO `sys_region` VALUES (507, 'DF43A4A8-1FFF-4A32-AC97-B11FCB17162E', '210521', '本溪满族自治县');
INSERT INTO `sys_region` VALUES (508, 'B8DCACD1-452D-4B92-9EC0-098A879852F6', '210522', '桓仁满族自治县');
INSERT INTO `sys_region` VALUES (509, '640443BB-289D-46FB-850F-F222BF69B580', '210600', '丹东市');
INSERT INTO `sys_region` VALUES (510, '11B08B4B-28F8-4C82-B273-782D87CEDF28', '210602', '元宝区');
INSERT INTO `sys_region` VALUES (511, '7799DC76-45DA-47D5-AF63-A058ACFECA6E', '210603', '振兴区');
INSERT INTO `sys_region` VALUES (512, '304DC43B-81C9-4AC9-9CFF-8DD770390390', '210604', '振安区');
INSERT INTO `sys_region` VALUES (513, '6D27B6B1-0C5C-463C-981D-5AEDB5548371', '210624', '宽甸满族自治县');
INSERT INTO `sys_region` VALUES (514, '8BCA9BBD-95A8-4907-B771-49287915D1B3', '210681', '东港市');
INSERT INTO `sys_region` VALUES (515, '910D9D04-20CE-4B59-B579-9EF63B6AEA33', '210682', '凤城市');
INSERT INTO `sys_region` VALUES (516, 'F18675F6-EE26-4BA8-88E5-EC491F2060B3', '210700', '锦州市');
INSERT INTO `sys_region` VALUES (517, '80D54D1E-076F-49F8-8766-11732602C4D5', '210702', '古塔区');
INSERT INTO `sys_region` VALUES (518, 'FB962AB3-BC5B-4AF4-B51F-BA3CEFD570C9', '210703', '凌河区');
INSERT INTO `sys_region` VALUES (519, '08BACA76-6255-4560-9CB3-04539B9F0B9E', '210711', '太和区');
INSERT INTO `sys_region` VALUES (520, '9DEBA1E9-0355-4610-A77A-8BA49E189095', '210726', '黑山县');
INSERT INTO `sys_region` VALUES (521, 'F1B72128-5293-4C74-86D8-8D76B6F0C08F', '210727', '义县');
INSERT INTO `sys_region` VALUES (522, '8C4F0C38-0EA5-4C87-A4C2-3181AAFE2633', '210781', '凌海市');
INSERT INTO `sys_region` VALUES (523, 'AF3BCB25-354E-4420-8717-67F1FE7FE673', '210782', '北镇市');
INSERT INTO `sys_region` VALUES (524, '628DE964-D04F-41FA-B30F-8F95401881D1', '210800', '营口市');
INSERT INTO `sys_region` VALUES (525, 'CFE599B4-8EBE-41A6-8FC4-0712C43C9E26', '210802', '站前区');
INSERT INTO `sys_region` VALUES (526, '6C3EE885-941A-480B-8016-25BE7C7A8B63', '210803', '西市区');
INSERT INTO `sys_region` VALUES (527, 'EF055844-692B-4B4A-8E26-8D50DEF6D459', '210804', '鲅鱼圈区');
INSERT INTO `sys_region` VALUES (528, '7418D04E-199B-4445-BF1C-321F450A8063', '210811', '老边区');
INSERT INTO `sys_region` VALUES (529, '2970D7CF-F870-4FB6-BFC3-1DE9387AB283', '210881', '盖州市');
INSERT INTO `sys_region` VALUES (530, '3EE622F9-E299-426C-8FDB-39FBA1BCBD0D', '210882', '大石桥市');
INSERT INTO `sys_region` VALUES (531, 'BAB9C86D-191A-4D7E-8275-CFE9F63BEB8D', '210900', '阜新市');
INSERT INTO `sys_region` VALUES (532, '8BF227D2-31D6-403F-A0E2-F465AAAFD292', '210902', '海州区');
INSERT INTO `sys_region` VALUES (533, 'EC99F750-EDAE-4145-BFBF-BEB1320E58E6', '210903', '新邱区');
INSERT INTO `sys_region` VALUES (534, '5C2E6E36-7C54-4CA4-94EB-4F7B856BBAA1', '210904', '太平区');
INSERT INTO `sys_region` VALUES (535, '63EFD5CF-E570-4DE3-8EB2-6CDCFA9DAFCC', '210905', '清河门区');
INSERT INTO `sys_region` VALUES (536, '73454F44-5E00-413D-8188-17BB8DFD7FDF', '210911', '细河区');
INSERT INTO `sys_region` VALUES (537, '66CEA5D7-4773-4E86-9D47-FEB0E54DC595', '210921', '阜新蒙古族自治县');
INSERT INTO `sys_region` VALUES (538, '729B700A-5816-41B3-BD77-3F395B0AD5DF', '210922', '彰武县');
INSERT INTO `sys_region` VALUES (539, '777FA86D-656C-40E1-AA79-F944BACA948C', '211000', '辽阳市');
INSERT INTO `sys_region` VALUES (540, '7C4FBACD-410C-4C67-9A99-B74300465788', '211002', '白塔区');
INSERT INTO `sys_region` VALUES (541, 'F829459C-5B82-45A3-BD95-00CEE31C9CEF', '211003', '文圣区');
INSERT INTO `sys_region` VALUES (542, 'CD876EB2-CC89-4407-A494-DF259A26BC34', '211004', '宏伟区');
INSERT INTO `sys_region` VALUES (543, '9E4D662C-D9C6-462A-8B77-344F154F2122', '211005', '弓长岭区');
INSERT INTO `sys_region` VALUES (544, '9732DD2D-CF5E-4237-AA21-25F5C92FD2BB', '211011', '太子河区');
INSERT INTO `sys_region` VALUES (545, 'B688EEC7-9FF0-46FF-9A54-10241A8007D4', '211021', '辽阳县');
INSERT INTO `sys_region` VALUES (546, 'EA961540-7BE7-4899-B67D-2176218EFCDB', '211081', '灯塔市');
INSERT INTO `sys_region` VALUES (547, '987FA434-6E51-4175-B8D1-89FFB94CB5F8', '211100', '盘锦市');
INSERT INTO `sys_region` VALUES (548, '80BB815C-719E-4188-B9B1-54D3BD289C66', '211102', '双台子区');
INSERT INTO `sys_region` VALUES (549, 'B9D2CB20-C7DA-477A-89DC-DC7696B9E764', '211103', '兴隆台区');
INSERT INTO `sys_region` VALUES (550, '884122AF-65A5-43FD-A839-C126509ABF8C', '211104', '大洼区');
INSERT INTO `sys_region` VALUES (551, 'D732A9B1-CB38-4D2C-9677-B881D3433924', '211122', '盘山县');
INSERT INTO `sys_region` VALUES (552, 'BE0E6684-6CE9-445B-AC99-F78B12A7CEC0', '211200', '铁岭市');
INSERT INTO `sys_region` VALUES (553, 'C741DE46-B227-4B6E-95DB-BB68C97DB8C0', '211202', '银州区');
INSERT INTO `sys_region` VALUES (554, 'FC140E36-752A-4F93-805F-FFDA13120796', '211204', '清河区');
INSERT INTO `sys_region` VALUES (555, 'DA7CC0E5-FF13-483A-82C2-900558D4686C', '211221', '铁岭县');
INSERT INTO `sys_region` VALUES (556, 'AB8631DE-B622-4174-AD2A-D8EA77F762B6', '211223', '西丰县');
INSERT INTO `sys_region` VALUES (557, '0190FB63-315D-4111-88A5-5CECD4A2A970', '211224', '昌图县');
INSERT INTO `sys_region` VALUES (558, '2E7F5B2E-AC2E-4CAA-B0C8-DAC2D86809C8', '211281', '调兵山市');
INSERT INTO `sys_region` VALUES (559, 'A4FC0E4E-7948-46B6-B40F-E8FB5723ABD7', '211282', '开原市');
INSERT INTO `sys_region` VALUES (560, '8490C010-D2DD-4A41-B41C-653D5D47D010', '211300', '朝阳市');
INSERT INTO `sys_region` VALUES (561, '0238DE3A-72F7-4F8A-A5BC-C54398AA812B', '211302', '双塔区');
INSERT INTO `sys_region` VALUES (562, '5B2174F8-CC9B-48A1-8318-9C167157F05F', '211303', '龙城区');
INSERT INTO `sys_region` VALUES (563, '133FB3C3-9AD2-4DD4-B6AD-56068E3C5011', '211321', '朝阳县');
INSERT INTO `sys_region` VALUES (564, 'FB4CC2DE-EBB4-4E25-8ADA-3A834A122A1D', '211322', '建平县');
INSERT INTO `sys_region` VALUES (565, '18ED4C1E-A087-476B-B926-DA7D2BD14B23', '211324', '喀喇沁左翼蒙古族自治县');
INSERT INTO `sys_region` VALUES (566, '35DB4DC0-304D-487E-9E53-9AFA3784CCAC', '211381', '北票市');
INSERT INTO `sys_region` VALUES (567, 'E8EA95A6-2F55-449C-98C9-A35E45AF154F', '211382', '凌源市');
INSERT INTO `sys_region` VALUES (568, 'A4DAECE3-1AF7-49DB-B859-F8D8FDD4FC49', '211400', '葫芦岛市');
INSERT INTO `sys_region` VALUES (569, '90B0EAD6-47BF-42DC-8D18-0F191185560E', '211402', '连山区');
INSERT INTO `sys_region` VALUES (570, '1C3C774F-9217-40B3-8495-6CDF5D148667', '211403', '龙港区');
INSERT INTO `sys_region` VALUES (571, 'FC6FE8E5-9A29-4B26-98C8-146CBB855298', '211404', '南票区');
INSERT INTO `sys_region` VALUES (572, '3509A604-3FDF-40BB-9550-71CB747DF12A', '211421', '绥中县');
INSERT INTO `sys_region` VALUES (573, 'ED4910AB-A124-44E1-ABB9-B5858D960021', '211422', '建昌县');
INSERT INTO `sys_region` VALUES (574, 'F2AA3EBA-6F1E-4914-A923-52FA0B7C2F83', '211481', '兴城市');
INSERT INTO `sys_region` VALUES (575, '5B572ADA-A8F4-4A7E-AD1A-29358DEC5FDF', '220000', '吉林省');
INSERT INTO `sys_region` VALUES (576, '00342D49-C125-4D51-9D68-4D6B748F7E88', '220100', '长春市');
INSERT INTO `sys_region` VALUES (577, '3EFCF2E1-733F-49A9-8189-232D05EFC2A1', '220102', '南关区');
INSERT INTO `sys_region` VALUES (578, 'F56AAA66-6FBD-4B79-B5B6-D901761EDAF0', '220103', '宽城区');
INSERT INTO `sys_region` VALUES (579, '2F5B32AA-804D-44AE-B21E-91664AE7581A', '220104', '朝阳区');
INSERT INTO `sys_region` VALUES (580, '865EBC5C-E0D0-4F95-B485-9E7A9F0EE71C', '220105', '二道区');
INSERT INTO `sys_region` VALUES (581, '5204C42C-4215-4E7F-A507-CA371E04851F', '220106', '绿园区');
INSERT INTO `sys_region` VALUES (582, '6B5A11BC-170E-4699-9515-77FE03443A30', '220112', '双阳区');
INSERT INTO `sys_region` VALUES (583, '3293ABDA-3D32-4DCE-95F0-AB5C73DCF105', '220113', '九台区');
INSERT INTO `sys_region` VALUES (584, '66F8F755-F8CB-4947-819D-5E99629B9D89', '220122', '农安县');
INSERT INTO `sys_region` VALUES (585, 'A503379E-9A4A-474B-BE8D-1CFFA8F02278', '220182', '榆树市');
INSERT INTO `sys_region` VALUES (586, 'C245E9B2-86DB-4698-95D5-8B78B6A09DF0', '220183', '德惠市');
INSERT INTO `sys_region` VALUES (587, 'D065B2ED-C389-4C3F-9E7D-251813A88D2D', '220200', '吉林市');
INSERT INTO `sys_region` VALUES (588, '3C6C6FAC-B11E-4A5A-91F2-46B87333D437', '220202', '昌邑区');
INSERT INTO `sys_region` VALUES (589, '7EC27876-A25F-4D65-BB33-609EA56E01FC', '220203', '龙潭区');
INSERT INTO `sys_region` VALUES (590, '85180EAA-6595-40A2-8BB0-268C200CD138', '220204', '船营区');
INSERT INTO `sys_region` VALUES (591, '66A0E8E6-C726-410F-80C4-DF5A00EC6B22', '220211', '丰满区');
INSERT INTO `sys_region` VALUES (592, '07D6034D-4911-4192-ABCC-EDE4D8E3C448', '220221', '永吉县');
INSERT INTO `sys_region` VALUES (593, 'A4988F15-53DC-4231-B412-274321CAC349', '220281', '蛟河市');
INSERT INTO `sys_region` VALUES (594, '7FBE8333-BB85-484E-9AF5-25A8DD82D9F7', '220282', '桦甸市');
INSERT INTO `sys_region` VALUES (595, 'CA662BA6-5865-4801-9522-93F435B5D152', '220283', '舒兰市');
INSERT INTO `sys_region` VALUES (596, 'C3E7270F-0F41-474E-9430-FA8B4EDD665E', '220284', '磐石市');
INSERT INTO `sys_region` VALUES (597, '55B0981D-A0F7-4987-95E9-7BDC4CE1A3A7', '220300', '四平市');
INSERT INTO `sys_region` VALUES (598, '30A6E381-C229-49AD-AB1B-C56E9FC64A52', '220302', '铁西区');
INSERT INTO `sys_region` VALUES (599, '996F160E-121C-4A6C-A2C6-A83A9266685B', '220303', '铁东区');
INSERT INTO `sys_region` VALUES (600, '354B3E61-3032-4F0F-9980-DBECE096B5D3', '220322', '梨树县');
INSERT INTO `sys_region` VALUES (601, '015C2A73-956E-4BB6-B3FE-D73C151142F8', '220323', '伊通满族自治县');
INSERT INTO `sys_region` VALUES (602, 'FFE763BD-C4C9-4C32-9057-DABF54B63237', '220381', '公主岭市');
INSERT INTO `sys_region` VALUES (603, 'B39C8B1E-96E1-4504-B4AC-F92EF37EC605', '220382', '双辽市');
INSERT INTO `sys_region` VALUES (604, '657850E4-D01D-43B8-8E6D-B2D955CBB689', '220400', '辽源市');
INSERT INTO `sys_region` VALUES (605, '260B510F-CAC4-41B4-94E1-DC297ED4ADAF', '220402', '龙山区');
INSERT INTO `sys_region` VALUES (606, '7D8059D5-91FC-435A-B13D-1C6F4C847CD3', '220403', '西安区');
INSERT INTO `sys_region` VALUES (607, '5B77CA24-67CD-42B1-A5C3-0315387C6029', '220421', '东丰县');
INSERT INTO `sys_region` VALUES (608, 'AE9A0700-9152-436F-86EA-EDF269EDF454', '220422', '东辽县');
INSERT INTO `sys_region` VALUES (609, '73CC5E10-A72E-4080-9EAD-CA872C4C5D26', '220500', '通化市');
INSERT INTO `sys_region` VALUES (610, '19E4B6C4-1DD9-4855-AD0E-2C8AA7884C72', '220502', '东昌区');
INSERT INTO `sys_region` VALUES (611, '69F6E891-FA10-4B62-A71F-76B2AA3AD5AF', '220503', '二道江区');
INSERT INTO `sys_region` VALUES (612, 'D0B2E2BD-9B28-4502-B5F1-9D9AAF3614D2', '220521', '通化县');
INSERT INTO `sys_region` VALUES (613, 'C8BDA088-E0BB-4A32-A6D3-4278F54C8D99', '220523', '辉南县');
INSERT INTO `sys_region` VALUES (614, 'E90C04FA-A4D5-4448-B2C6-4887B442D0F7', '220524', '柳河县');
INSERT INTO `sys_region` VALUES (615, 'C6A51166-52B5-4E9D-9C59-5B0D3B8384AD', '220581', '梅河口市');
INSERT INTO `sys_region` VALUES (616, 'DA78E080-C527-494F-AB64-8CEC7D43DD92', '220582', '集安市');
INSERT INTO `sys_region` VALUES (617, 'FD2B820F-6792-40F7-A2C3-1D2AE8D0495D', '220600', '白山市');
INSERT INTO `sys_region` VALUES (618, '35855601-8A6D-4230-9120-72149EFBE209', '220602', '浑江区');
INSERT INTO `sys_region` VALUES (619, 'D1353AB2-6FE5-4D77-8558-EEEC1308E4C4', '220605', '江源区');
INSERT INTO `sys_region` VALUES (620, 'F8FA1D6A-3560-49FC-B4E4-735152F48082', '220621', '抚松县');
INSERT INTO `sys_region` VALUES (621, '8C5A9B7D-48C2-4BA5-9F74-FA3CE122FF41', '220622', '靖宇县');
INSERT INTO `sys_region` VALUES (622, '485E2D70-6F17-4208-A676-5B2CB65ADCF6', '220623', '长白朝鲜族自治县');
INSERT INTO `sys_region` VALUES (623, '2C91F83C-1225-48E1-8E75-7812ADAE93A7', '220681', '临江市');
INSERT INTO `sys_region` VALUES (624, '39ED42FC-10BB-464E-B898-F9216B4E0D0F', '220700', '松原市');
INSERT INTO `sys_region` VALUES (625, '79B6BB6B-E7A5-473B-871D-655900411321', '220702', '宁江区');
INSERT INTO `sys_region` VALUES (626, '0D25B9FE-B591-4EDB-A857-300ABE2C7599', '220721', '前郭尔罗斯蒙古族自治县');
INSERT INTO `sys_region` VALUES (627, '5F5DCDA4-AA20-4E9D-9826-AC96062005A7', '220722', '长岭县');
INSERT INTO `sys_region` VALUES (628, 'D1E07939-2149-4399-96B2-755D9D68EEA8', '220723', '乾安县');
INSERT INTO `sys_region` VALUES (629, 'F4980C6C-88D9-4F1D-B524-793D28A8CF39', '220781', '扶余市');
INSERT INTO `sys_region` VALUES (630, 'AEBE713A-8BAE-4806-B818-5C3FC496C368', '220800', '白城市');
INSERT INTO `sys_region` VALUES (631, '888237DF-0244-42C4-9D9F-A3954AEB97AF', '220802', '洮北区');
INSERT INTO `sys_region` VALUES (632, '8C7C0217-6AA2-420E-BA38-34A911C9846D', '220821', '镇赉县');
INSERT INTO `sys_region` VALUES (633, 'B2FEDC56-4A3F-4009-A1C1-4EE962357DDE', '220822', '通榆县');
INSERT INTO `sys_region` VALUES (634, '76BEA4C4-A1D9-414E-A794-F6BB66EE534F', '220881', '洮南市');
INSERT INTO `sys_region` VALUES (635, '4E001EE8-DD05-49CB-8C2E-6ACFC1589B4A', '220882', '大安市');
INSERT INTO `sys_region` VALUES (636, '2950B253-6964-4A8A-924F-979347C65297', '222400', '延边朝鲜族自治州');
INSERT INTO `sys_region` VALUES (637, 'BFC2E052-FF6F-471A-A243-E4CE997E3C30', '222401', '延吉市');
INSERT INTO `sys_region` VALUES (638, 'AD0D5F03-BD32-4D61-93CC-82578D4EA92D', '222402', '图们市');
INSERT INTO `sys_region` VALUES (639, 'B5AF03E7-22BE-4AB2-91C8-939C464C9335', '222403', '敦化市');
INSERT INTO `sys_region` VALUES (640, 'D35B669E-5699-42CA-836F-965CC0D68088', '222404', '珲春市');
INSERT INTO `sys_region` VALUES (641, 'A2D345CC-44B4-4A0C-AAC7-D697FB2B33B1', '222405', '龙井市');
INSERT INTO `sys_region` VALUES (642, 'A04E9A1D-2F13-4169-8CEE-8D8A2D8D84B2', '222406', '和龙市');
INSERT INTO `sys_region` VALUES (643, '2270B9F8-4197-41EA-96C7-CCCB6282EB40', '222424', '汪清县');
INSERT INTO `sys_region` VALUES (644, 'D6907537-14B6-4289-A9C6-4871D0245F6B', '222426', '安图县');
INSERT INTO `sys_region` VALUES (645, '2F7F596D-2DF4-4FC1-A7A0-8B174976C599', '230000', '黑龙江省');
INSERT INTO `sys_region` VALUES (646, '34226BE5-9329-46E7-8F1B-4C63BA25ACEA', '230100', '哈尔滨市');
INSERT INTO `sys_region` VALUES (647, '43E5EBCA-416B-4386-928D-6B3EE780036D', '230102', '道里区');
INSERT INTO `sys_region` VALUES (648, '179A2F17-1D4F-417F-A662-1749628B0B42', '230103', '南岗区');
INSERT INTO `sys_region` VALUES (649, '66849B56-EA5F-49CF-902B-9982D354719A', '230104', '道外区');
INSERT INTO `sys_region` VALUES (650, '2691A894-9AC8-4414-ABEA-911D6A6CAAE0', '230108', '平房区');
INSERT INTO `sys_region` VALUES (651, '7E02B8F0-4E11-463B-A2D9-D5837E4241CB', '230109', '松北区');
INSERT INTO `sys_region` VALUES (652, '96631E53-41AF-41D2-854C-45A1D3494AF0', '230110', '香坊区');
INSERT INTO `sys_region` VALUES (653, 'D31BC761-79AB-438A-B4C1-94949FE4095C', '230111', '呼兰区');
INSERT INTO `sys_region` VALUES (654, 'ED0417B1-F5F7-4F24-BF7F-B5F00374E70D', '230112', '阿城区');
INSERT INTO `sys_region` VALUES (655, '8726B2CC-1E08-46AD-B6F2-ACA33025CE33', '230113', '双城区');
INSERT INTO `sys_region` VALUES (656, '42524A88-782C-48EF-9E7C-3E7D89C9FBDB', '230123', '依兰县');
INSERT INTO `sys_region` VALUES (657, 'D96F42B3-C27E-48BB-87ED-967B57C99012', '230124', '方正县');
INSERT INTO `sys_region` VALUES (658, '05FEF9B3-45EC-4FD8-985B-1822DAE359CA', '230125', '宾县');
INSERT INTO `sys_region` VALUES (659, '60ABA5DB-7B73-4CB1-8DAE-7AB0F80856CD', '230126', '巴彦县');
INSERT INTO `sys_region` VALUES (660, '53D57A86-8853-4F74-8CB4-019EC1B106FC', '230127', '木兰县');
INSERT INTO `sys_region` VALUES (661, '9E0D0FB1-2E24-4A15-AEB1-060574B5997D', '230128', '通河县');
INSERT INTO `sys_region` VALUES (662, 'FFF73CF8-ACDF-46DE-B505-8E42C28F4CB9', '230129', '延寿县');
INSERT INTO `sys_region` VALUES (663, 'B8D31AE4-F117-46E4-97C6-025646B0485E', '230183', '尚志市');
INSERT INTO `sys_region` VALUES (664, '75E971C4-0B54-4D4F-86C7-E35029A6C9BB', '230184', '五常市');
INSERT INTO `sys_region` VALUES (665, '8244C14B-CD20-475E-BA0B-DC7C29CEA1EC', '230200', '齐齐哈尔市');
INSERT INTO `sys_region` VALUES (666, '624F6359-4069-423E-97AD-880D5AE75E07', '230202', '龙沙区');
INSERT INTO `sys_region` VALUES (667, '91FB9B45-F633-44F5-A2FF-9718B5CD420F', '230203', '建华区');
INSERT INTO `sys_region` VALUES (668, '2F0B4D1B-68E0-4C0C-8143-CFFE9D9BA066', '230204', '铁锋区');
INSERT INTO `sys_region` VALUES (669, '04765AD5-DF83-4308-A690-0EE8C8D357A0', '230205', '昂昂溪区');
INSERT INTO `sys_region` VALUES (670, '1C14B838-16D5-496C-AF9B-665B10CB0514', '230206', '富拉尔基区');
INSERT INTO `sys_region` VALUES (671, '0C715B8C-C04D-4AAF-B579-7512B1C5E12F', '230207', '碾子山区');
INSERT INTO `sys_region` VALUES (672, '84EB800E-A136-465B-8FEC-A123178A2C98', '230208', '梅里斯达斡尔族区');
INSERT INTO `sys_region` VALUES (673, '316DD056-CE48-4DB8-9E42-F305BA55CDB2', '230221', '龙江县');
INSERT INTO `sys_region` VALUES (674, 'E98CDD35-2AAF-4FB8-A1F5-0A39ACBE9697', '230223', '依安县');
INSERT INTO `sys_region` VALUES (675, '089F9331-87AD-4D3B-9A00-80902EC1ACD9', '230224', '泰来县');
INSERT INTO `sys_region` VALUES (676, 'C57BB00E-6CB8-46AF-B733-949ADA93F50D', '230225', '甘南县');
INSERT INTO `sys_region` VALUES (677, '68A88F24-3CB8-4814-8599-33A59A7BDCC2', '230227', '富裕县');
INSERT INTO `sys_region` VALUES (678, 'CE41CF3C-8C0D-48E7-9388-D2182557867E', '230229', '克山县');
INSERT INTO `sys_region` VALUES (679, '2E80F3C0-AC31-4BEC-ACC8-8519E51B8626', '230230', '克东县');
INSERT INTO `sys_region` VALUES (680, 'C1F0928D-AEC6-4715-A725-8C1A78CCC030', '230231', '拜泉县');
INSERT INTO `sys_region` VALUES (681, 'DB730786-9C0E-4CBB-AA15-26BF24515470', '230281', '讷河市');
INSERT INTO `sys_region` VALUES (682, '43CCFDC8-739D-4AD4-AAE0-3754DF66A4A6', '230300', '鸡西市');
INSERT INTO `sys_region` VALUES (683, '9563D0E6-582A-41F0-BA2E-77CFEEC58D28', '230302', '鸡冠区');
INSERT INTO `sys_region` VALUES (684, 'DB22F4D2-E384-4772-BE9A-44B2777119E0', '230303', '恒山区');
INSERT INTO `sys_region` VALUES (685, '42ED8417-6CFE-4EF6-85B6-DFCDAD86A03E', '230304', '滴道区');
INSERT INTO `sys_region` VALUES (686, '29DD7FF8-9FB1-4F83-AE99-3F5047111036', '230305', '梨树区');
INSERT INTO `sys_region` VALUES (687, '638B3C52-8867-4D1F-B4CA-8068EEC2CEF7', '230306', '城子河区');
INSERT INTO `sys_region` VALUES (688, 'FB899A5B-B698-470E-9519-BB38EB3B1E53', '230307', '麻山区');
INSERT INTO `sys_region` VALUES (689, '6D494365-9CEC-4C57-A7A4-40B93627BB6F', '230321', '鸡东县');
INSERT INTO `sys_region` VALUES (690, 'DDF2CC33-AB7E-4268-90C4-56E6D0C72A97', '230381', '虎林市');
INSERT INTO `sys_region` VALUES (691, 'CB4D4B14-FCD1-477C-939D-225B5F4A4D91', '230382', '密山市');
INSERT INTO `sys_region` VALUES (692, '9BED498C-007A-41F6-ABC3-6E678F3BB0D9', '230400', '鹤岗市');
INSERT INTO `sys_region` VALUES (693, '003FE8AF-EFE1-412B-A5DC-043908E36608', '230402', '向阳区');
INSERT INTO `sys_region` VALUES (694, '6E76B8AB-7A69-4F77-9F8B-84E327176850', '230403', '工农区');
INSERT INTO `sys_region` VALUES (695, 'B374FBA5-4497-41C0-BAA8-7AE8E4A8CD4F', '230404', '南山区');
INSERT INTO `sys_region` VALUES (696, '934B87EA-0748-4C40-B245-992F8B94685B', '230405', '兴安区');
INSERT INTO `sys_region` VALUES (697, '6E5ED2B4-0804-4D0E-B2AA-D18A9CC7D3D3', '230406', '东山区');
INSERT INTO `sys_region` VALUES (698, '7E5D6CD4-A73A-42A4-A651-568A0534074E', '230407', '兴山区');
INSERT INTO `sys_region` VALUES (699, 'E05E07B5-756E-4A7D-9459-243A5CC2BC67', '230421', '萝北县');
INSERT INTO `sys_region` VALUES (700, 'FC46A7EB-EF9E-44DA-BA0A-E2432B30CD6E', '230422', '绥滨县');
INSERT INTO `sys_region` VALUES (701, '1F584D92-C798-4F0D-AD7B-6C1E86B12436', '230500', '双鸭山市');
INSERT INTO `sys_region` VALUES (702, '40D53863-136D-41C8-994D-687C9EC52F92', '230502', '尖山区');
INSERT INTO `sys_region` VALUES (703, '9C1B5344-FF33-4CED-BF7B-3C876B92BE42', '230503', '岭东区');
INSERT INTO `sys_region` VALUES (704, 'AB0EE336-5797-4A02-B8AA-BBD12EEC14CC', '230505', '四方台区');
INSERT INTO `sys_region` VALUES (705, 'C84FCECC-7565-4570-84B2-4E81AF660624', '230506', '宝山区');
INSERT INTO `sys_region` VALUES (706, '2EEC15ED-B50B-4F77-B636-A8C51D41EC32', '230521', '集贤县');
INSERT INTO `sys_region` VALUES (707, 'C4CB3849-C284-4D1A-B4FA-C3CC3824D460', '230522', '友谊县');
INSERT INTO `sys_region` VALUES (708, '0C00397A-0E69-43F1-AB1E-72817DCAC5DA', '230523', '宝清县');
INSERT INTO `sys_region` VALUES (709, '58A91517-5464-440F-9C5F-43FAAE9083C0', '230524', '饶河县');
INSERT INTO `sys_region` VALUES (710, '8682ED64-346E-44DB-AD99-1461E947D7D3', '230600', '大庆市');
INSERT INTO `sys_region` VALUES (711, 'F5C6BE0E-4D8E-4978-92AE-EEC0FF5583B2', '230602', '萨尔图区');
INSERT INTO `sys_region` VALUES (712, 'E9EF3EDD-6653-49D8-8D34-C75A87919EC6', '230603', '龙凤区');
INSERT INTO `sys_region` VALUES (713, '67102807-3CCE-4C9A-84E7-66E55798DC5D', '230604', '让胡路区');
INSERT INTO `sys_region` VALUES (714, '747C282D-5AEE-4F22-9782-EFF1AA34BEC3', '230605', '红岗区');
INSERT INTO `sys_region` VALUES (715, '26B03510-0EE7-403F-B7C3-C46A4A8552B6', '230606', '大同区');
INSERT INTO `sys_region` VALUES (716, 'CD76B5A5-8B50-40A1-A143-6C04A45A8565', '230621', '肇州县');
INSERT INTO `sys_region` VALUES (717, '9904E592-9ABB-4718-BBFC-A9E70F928221', '230622', '肇源县');
INSERT INTO `sys_region` VALUES (718, '3DEF75CD-8E81-46CE-BF22-8DEAA32FBB7E', '230623', '林甸县');
INSERT INTO `sys_region` VALUES (719, 'D24CBED8-6CED-4F64-901A-E97AAA4300B2', '230624', '杜尔伯特蒙古族自治县');
INSERT INTO `sys_region` VALUES (720, '067008A4-E511-44BC-AAF7-0F6A20901819', '230700', '伊春市');
INSERT INTO `sys_region` VALUES (721, 'C54901FF-E5C0-46C8-AC8D-0137D7E98DD6', '230717', '伊美区');
INSERT INTO `sys_region` VALUES (722, '5B410DF5-2153-409D-B132-2779CED5DABC', '230718', '乌翠区');
INSERT INTO `sys_region` VALUES (723, '6298AF06-8CBA-4827-B265-0C77F61DA74F', '230719', '友好区');
INSERT INTO `sys_region` VALUES (724, '81113BD3-BC66-4F0D-BFA2-65210F1AA83C', '230722', '嘉荫县');
INSERT INTO `sys_region` VALUES (725, '4B60F925-F0E7-4E9D-AC88-4C1D90A8A946', '230723', '汤旺县');
INSERT INTO `sys_region` VALUES (726, '533ACCA2-F4C5-49EA-BA4F-A0A528C16993', '230724', '丰林县');
INSERT INTO `sys_region` VALUES (727, 'D7B75D94-A865-4F75-8A63-64A361B07330', '230725', '大箐山县');
INSERT INTO `sys_region` VALUES (728, 'CC6F47A3-DCAA-4221-AD39-9ADA9227C7A1', '230726', '南岔县');
INSERT INTO `sys_region` VALUES (729, '6640E2F1-8217-4D06-9E35-32896146DC64', '230751', '金林区');
INSERT INTO `sys_region` VALUES (730, '184EDD42-E606-42E4-8370-0D99739F79A0', '230781', '铁力市');
INSERT INTO `sys_region` VALUES (731, 'A01590D7-9358-4FF6-BF69-A3FF816A084A', '230800', '佳木斯市');
INSERT INTO `sys_region` VALUES (732, 'E6299690-8A60-433B-B617-10ADFA3375A8', '230803', '向阳区');
INSERT INTO `sys_region` VALUES (733, '32EEF9D2-E1B5-4365-8A94-3DBA19B25C98', '230804', '前进区');
INSERT INTO `sys_region` VALUES (734, '951F61C6-023C-405A-93A4-5AEEB3AABBE5', '230805', '东风区');
INSERT INTO `sys_region` VALUES (735, '5163EF94-8B58-404C-A27B-EBB6BE3F0645', '230811', '郊区');
INSERT INTO `sys_region` VALUES (736, '9A6E84B3-C26F-41E1-95DA-A5EB1C4CB49A', '230822', '桦南县');
INSERT INTO `sys_region` VALUES (737, '92C9DCA9-B1A6-41ED-8169-1BC35F42AD7E', '230826', '桦川县');
INSERT INTO `sys_region` VALUES (738, 'F32FD6E4-20C1-4D17-A3F4-FE6483CAA07A', '230828', '汤原县');
INSERT INTO `sys_region` VALUES (739, 'DE3B7D89-2B31-45E4-BB09-123C1ED1AF6E', '230881', '同江市');
INSERT INTO `sys_region` VALUES (740, '823B66D4-A075-4CC0-8F62-2DC9924777D9', '230882', '富锦市');
INSERT INTO `sys_region` VALUES (741, '1D0ECAC9-2D85-4C78-8DFB-D865EE046BF6', '230883', '抚远市');
INSERT INTO `sys_region` VALUES (742, '017198EA-AF65-4268-98C9-C822A768AA5D', '230900', '七台河市');
INSERT INTO `sys_region` VALUES (743, '0A3B9CF7-ABAB-4C73-9A66-B68C50C997A7', '230902', '新兴区');
INSERT INTO `sys_region` VALUES (744, 'E683671E-DBE8-463E-83E0-F7FF1A21A13D', '230903', '桃山区');
INSERT INTO `sys_region` VALUES (745, 'D30D4F08-02DA-4BB9-AAA0-287D4DFD3F7E', '230904', '茄子河区');
INSERT INTO `sys_region` VALUES (746, '0D49CDD0-488F-4306-A698-1B6C9E32EA96', '230921', '勃利县');
INSERT INTO `sys_region` VALUES (747, '545F9EF6-47AE-4EA9-9606-E9BDB8554225', '231000', '牡丹江市');
INSERT INTO `sys_region` VALUES (748, '8C8FE0C7-85EF-4C37-A224-E0ABC433D95F', '231002', '东安区');
INSERT INTO `sys_region` VALUES (749, '6F832286-B332-4BE3-8D78-3DB2833F1E3E', '231003', '阳明区');
INSERT INTO `sys_region` VALUES (750, 'D3A1305F-90FA-44F0-BA21-47B9FA61102E', '231004', '爱民区');
INSERT INTO `sys_region` VALUES (751, '34C75746-B58D-407C-BFEB-67693055583B', '231005', '西安区');
INSERT INTO `sys_region` VALUES (752, '9A864DFA-86B2-4731-9D0D-D0B379D24688', '231025', '林口县');
INSERT INTO `sys_region` VALUES (753, 'DCDE2C55-FD79-413D-80D7-3E201428AF25', '231081', '绥芬河市');
INSERT INTO `sys_region` VALUES (754, '5937C354-91A3-4229-8EF6-6C38E19C6DEA', '231083', '海林市');
INSERT INTO `sys_region` VALUES (755, 'EF8F97A0-BC63-40AB-AF45-F0EBB3054FAE', '231084', '宁安市');
INSERT INTO `sys_region` VALUES (756, '3BC60AAE-7DEE-4117-801E-0DEA12B9EBD4', '231085', '穆棱市');
INSERT INTO `sys_region` VALUES (757, 'A473B89D-B0CE-41A7-AC85-F61C9D555CCF', '231086', '东宁市');
INSERT INTO `sys_region` VALUES (758, '8104E4DA-7552-49A1-B9D1-E0FA6A8392D7', '231100', '黑河市');
INSERT INTO `sys_region` VALUES (759, 'DB7274B6-380B-436B-846C-2CBA32324690', '231102', '爱辉区');
INSERT INTO `sys_region` VALUES (760, '017FCB33-4EFA-4580-93A3-C548C7FBFAB9', '231121', '嫩江市');
INSERT INTO `sys_region` VALUES (761, 'FF2EEA7D-D88F-4702-ACCD-69AB626EF28D', '231123', '逊克县');
INSERT INTO `sys_region` VALUES (762, 'A6C93F9B-69F9-4A6D-9684-ADC837BD8377', '231124', '孙吴县');
INSERT INTO `sys_region` VALUES (763, 'C8925BC0-DEDB-4D4D-B5BF-6B337C807D45', '231181', '北安市');
INSERT INTO `sys_region` VALUES (764, 'EF93299D-7EB0-482D-AAEF-6ED889C0D369', '231182', '五大连池市');
INSERT INTO `sys_region` VALUES (765, 'CE2F23BC-0161-4960-B2F2-C9264D8D9396', '231183', '嫩江市');
INSERT INTO `sys_region` VALUES (766, 'C98CAB7A-EE1A-4DFB-A6BA-1A4C637784AC', '231200', '绥化市');
INSERT INTO `sys_region` VALUES (767, '6D87CE09-2DFA-4D50-8A65-C82D2A48F84E', '231202', '北林区');
INSERT INTO `sys_region` VALUES (768, '0D8EDCF8-3535-4491-8393-D4AEF230CB02', '231221', '望奎县');
INSERT INTO `sys_region` VALUES (769, '7567BAB1-9FDE-4808-8D1A-643200174147', '231222', '兰西县');
INSERT INTO `sys_region` VALUES (770, '3E7ADE7E-E5DE-4AA4-A0F3-23FAD1B14B0B', '231223', '青冈县');
INSERT INTO `sys_region` VALUES (771, 'B1B40A49-B8EB-4B23-9154-356DCF352E1C', '231224', '庆安县');
INSERT INTO `sys_region` VALUES (772, '7ACCD4A7-AF1C-455D-9689-7958DFF99D89', '231225', '明水县');
INSERT INTO `sys_region` VALUES (773, 'FC9C413F-C8C4-462F-84E0-E35553ECA958', '231226', '绥棱县');
INSERT INTO `sys_region` VALUES (774, 'E110A097-54D2-4603-8CE7-4D128642205B', '231281', '安达市');
INSERT INTO `sys_region` VALUES (775, '3F4A5DC2-F137-4303-9C47-851DC8C2AE91', '231282', '肇东市');
INSERT INTO `sys_region` VALUES (776, '4F689BF0-C630-4680-A1B3-007385ED6D91', '231283', '海伦市');
INSERT INTO `sys_region` VALUES (777, '124B126A-2B9E-40C7-83E0-1D693395B0C9', '232700', '大兴安岭地区');
INSERT INTO `sys_region` VALUES (778, 'C48F94A3-AA32-4AEE-A92B-F82A83009080', '232701', '漠河市');
INSERT INTO `sys_region` VALUES (779, '73093716-19F3-4072-87A5-B9C5688599A2', '232721', '呼玛县');
INSERT INTO `sys_region` VALUES (780, '534697B0-AC0D-495E-B932-A80E903C136F', '232722', '塔河县');
INSERT INTO `sys_region` VALUES (781, 'E2296FAF-6BD0-4932-AE33-F6359B6BFD43', '310000', '上海市');
INSERT INTO `sys_region` VALUES (782, '2B7006CD-3300-4395-BAD4-931D443AAD31', '310101', '黄浦区');
INSERT INTO `sys_region` VALUES (783, '35F84EF0-730E-4E18-909A-D629CEA8F184', '310104', '徐汇区');
INSERT INTO `sys_region` VALUES (784, '7901C043-651F-45F1-ABF5-1CC5889E283A', '310105', '长宁区');
INSERT INTO `sys_region` VALUES (785, 'AB35B173-7776-42E3-ADA6-62148A4DA645', '310106', '静安区');
INSERT INTO `sys_region` VALUES (786, '913BC73B-9993-4CC8-A8C6-A2BE97366BC9', '310107', '普陀区');
INSERT INTO `sys_region` VALUES (787, 'EA7E2C3A-ED65-4E0B-BFA8-AECF18C44AF4', '310109', '虹口区');
INSERT INTO `sys_region` VALUES (788, '0DFE6DE0-7D6C-4BF1-B6BB-FD9442D2AD10', '310110', '杨浦区');
INSERT INTO `sys_region` VALUES (789, 'EB808A3D-5CEA-4739-9D2F-F18CE65C1141', '310112', '闵行区');
INSERT INTO `sys_region` VALUES (790, '61FA773F-4ABF-4548-A169-0651D41A5C0C', '310113', '宝山区');
INSERT INTO `sys_region` VALUES (791, '13BDD68D-ADC3-4A15-8DB8-813F7FF9A636', '310114', '嘉定区');
INSERT INTO `sys_region` VALUES (792, '9D8B5A30-F551-48BE-B1DF-05EE492CEA26', '310115', '浦东新区');
INSERT INTO `sys_region` VALUES (793, 'E70CED81-E295-46AE-95A7-B84406B46A66', '310116', '金山区');
INSERT INTO `sys_region` VALUES (794, '1774CB8D-EE7B-4271-B5CA-FDA615EB1BCA', '310117', '松江区');
INSERT INTO `sys_region` VALUES (795, 'BC49DE8A-A070-40D0-B658-B8656AD26B17', '310118', '青浦区');
INSERT INTO `sys_region` VALUES (796, 'DC1650FE-861D-48FC-9151-173B328A3E62', '310120', '奉贤区');
INSERT INTO `sys_region` VALUES (797, 'E20290B3-C4C9-44A5-8855-85670747862C', '310151', '崇明区');
INSERT INTO `sys_region` VALUES (798, 'EBEA967B-BBAA-46C4-A3E1-2A289746D7CA', '320000', '江苏省');
INSERT INTO `sys_region` VALUES (799, '422BE89B-E634-47B3-8B63-F8D9A35F0BF8', '320100', '南京市');
INSERT INTO `sys_region` VALUES (800, '5ED054A0-C3CA-4A39-B47C-C2B09B3ACDBA', '320102', '玄武区');
INSERT INTO `sys_region` VALUES (801, '8EDCBF48-BE6D-4CF3-9DAC-1E44A90E1F97', '320104', '秦淮区');
INSERT INTO `sys_region` VALUES (802, '8F6D29A2-F938-4545-B17C-7BED26C328D3', '320105', '建邺区');
INSERT INTO `sys_region` VALUES (803, '821C6615-AA4C-4768-B788-564C3B9970D1', '320106', '鼓楼区');
INSERT INTO `sys_region` VALUES (804, '241DCBC9-CBBB-4904-B409-D0ACC316727C', '320111', '浦口区');
INSERT INTO `sys_region` VALUES (805, 'AD99CD61-6F2B-4E88-A976-369BD993147C', '320113', '栖霞区');
INSERT INTO `sys_region` VALUES (806, 'EB1ED84B-B99A-4825-822A-A8ED8B2B6F82', '320114', '雨花台区');
INSERT INTO `sys_region` VALUES (807, '5496C403-D12E-4D82-9E8A-31CD484C5CC0', '320115', '江宁区');
INSERT INTO `sys_region` VALUES (808, '465D48F5-57C2-4FF0-82FA-7144A0A66479', '320116', '六合区');
INSERT INTO `sys_region` VALUES (809, 'B7B05F7D-2291-413F-8307-B5070628E9D3', '320117', '溧水区');
INSERT INTO `sys_region` VALUES (810, '1E3C8905-33E1-4ABB-BB54-DC09DB7ABA94', '320118', '高淳区');
INSERT INTO `sys_region` VALUES (811, '85DA377D-D496-49F1-B551-5FDCD7A96708', '320200', '无锡市');
INSERT INTO `sys_region` VALUES (812, '4C88DE6A-EF41-451B-B785-D7BCF4B9CDFB', '320205', '锡山区');
INSERT INTO `sys_region` VALUES (813, '6C29EFD4-6F4E-4B72-ADA4-ACF469C3CD46', '320206', '惠山区');
INSERT INTO `sys_region` VALUES (814, 'AB852175-2A6C-4F39-8958-2617653A8D8C', '320211', '滨湖区');
INSERT INTO `sys_region` VALUES (815, 'FE2D7D62-0173-461C-9ED4-1815215CF344', '320213', '梁溪区');
INSERT INTO `sys_region` VALUES (816, '341F85AE-CD7A-464D-B9A2-506B9DC6CA7C', '320214', '新吴区');
INSERT INTO `sys_region` VALUES (817, 'CB59E1F1-C85B-4DE7-A03D-1849406314F4', '320281', '江阴市');
INSERT INTO `sys_region` VALUES (818, '057BE106-5149-4073-87B3-120A1C8DF691', '320282', '宜兴市');
INSERT INTO `sys_region` VALUES (819, 'DDC6E476-7068-4EB0-8002-0C927DE723C8', '320300', '徐州市');
INSERT INTO `sys_region` VALUES (820, '6B745D75-1A5F-4A04-8687-FCCCD0CA8598', '320302', '鼓楼区');
INSERT INTO `sys_region` VALUES (821, '4D1BEA04-671F-4DA9-A88E-6FCECE389292', '320303', '云龙区');
INSERT INTO `sys_region` VALUES (822, '31909EDC-80C9-4C35-B9D5-1E97BAB84B70', '320305', '贾汪区');
INSERT INTO `sys_region` VALUES (823, 'C5A2ABBD-D2B7-4AE8-B747-5E86C6FE5FDF', '320311', '泉山区');
INSERT INTO `sys_region` VALUES (824, '947EF21B-AE7B-41D7-B9D5-4EC39DA53D08', '320312', '铜山区');
INSERT INTO `sys_region` VALUES (825, 'A927FAF3-14D7-4F61-B648-D298C1B90A04', '320321', '丰县');
INSERT INTO `sys_region` VALUES (826, '8473360B-B0E8-4617-AC6B-0F741CDB63FA', '320322', '沛县');
INSERT INTO `sys_region` VALUES (827, '95DA6690-B4A2-4646-8D9C-0E073D3E0114', '320324', '睢宁县');
INSERT INTO `sys_region` VALUES (828, 'BE393DD8-F611-46F1-B37C-1D8C7CEC14D8', '320381', '新沂市');
INSERT INTO `sys_region` VALUES (829, 'A2555740-D8BB-4878-9F21-E376788C8211', '320382', '邳州市');
INSERT INTO `sys_region` VALUES (830, '9F7FD225-3532-47F3-A006-105151849EC3', '320400', '常州市');
INSERT INTO `sys_region` VALUES (831, '2714AC20-C1EA-44FE-B1B1-B9DD8B330918', '320402', '天宁区');
INSERT INTO `sys_region` VALUES (832, '61DED993-C64A-4ECF-A972-2CBA0FAF0433', '320404', '钟楼区');
INSERT INTO `sys_region` VALUES (833, 'C4E75E93-10B6-4E28-ADCB-758FBF782DCB', '320411', '新北区');
INSERT INTO `sys_region` VALUES (834, '957897E9-C39C-45B2-BC05-F9543D6C8A18', '320412', '武进区');
INSERT INTO `sys_region` VALUES (835, 'DFA7C97D-87B7-42B0-BB06-159533845AA0', '320413', '金坛区');
INSERT INTO `sys_region` VALUES (836, '76CEB4B0-15B0-462E-8BFA-9B8F240CC0F3', '320481', '溧阳市');
INSERT INTO `sys_region` VALUES (837, '729D04B0-5FF1-4829-B9E3-92E136D09D9D', '320500', '苏州市');
INSERT INTO `sys_region` VALUES (838, 'BAC9B7D5-4E88-478F-983A-C895D3E18C55', '320505', '虎丘区');
INSERT INTO `sys_region` VALUES (839, '7440A3C9-B74F-4EE9-9495-A41E8C13E73C', '320506', '吴中区');
INSERT INTO `sys_region` VALUES (840, '5DA00624-FA0A-4FA3-9258-611C13F271C6', '320507', '相城区');
INSERT INTO `sys_region` VALUES (841, 'D55FBEFE-52B9-4530-8D70-CC3BA1752F6D', '320508', '姑苏区');
INSERT INTO `sys_region` VALUES (842, 'BC05D3E2-6AD1-4F66-8092-4E6908A60C26', '320509', '吴江区');
INSERT INTO `sys_region` VALUES (843, 'E32FD1B6-A617-4FB9-B87A-58C837EDFEB6', '320581', '常熟市');
INSERT INTO `sys_region` VALUES (844, '2E07AC8E-CA57-415B-AC94-DC7AC1507B7F', '320582', '张家港市');
INSERT INTO `sys_region` VALUES (845, '07A99CE0-BF44-49D6-A840-A371A20B5C41', '320583', '昆山市');
INSERT INTO `sys_region` VALUES (846, '87C55E1C-8986-41AA-9011-C88509167354', '320585', '太仓市');
INSERT INTO `sys_region` VALUES (847, 'D30E7F58-9079-4627-AB67-28E9C300F50B', '320600', '南通市');
INSERT INTO `sys_region` VALUES (848, '92DBC7AA-2C2F-452D-9EC8-ADB62CCEEE73', '320602', '崇川区');
INSERT INTO `sys_region` VALUES (849, '74CC0ED1-E6A1-4553-AB2C-697762DDC35F', '320611', '港闸区');
INSERT INTO `sys_region` VALUES (850, 'F2E118EE-B2C8-41C5-8D67-C4139B0B60EC', '320612', '通州区');
INSERT INTO `sys_region` VALUES (851, 'DC2D5E00-2CA7-48FF-B285-1754B8E73FEF', '320623', '如东县');
INSERT INTO `sys_region` VALUES (852, '385BEC90-6ADC-4308-9F55-EACABEEE4436', '320681', '启东市');
INSERT INTO `sys_region` VALUES (853, '205C8FBF-B07A-4830-A964-B1D16B911D43', '320682', '如皋市');
INSERT INTO `sys_region` VALUES (854, 'CD344719-B289-4B39-AC69-2CF35534B79D', '320684', '海门市');
INSERT INTO `sys_region` VALUES (855, 'C175AC08-23BE-486C-8D70-7D5422B4B393', '320685', '海安市');
INSERT INTO `sys_region` VALUES (856, '6686771D-E234-40CE-ACA4-0E050219F119', '320700', '连云港市');
INSERT INTO `sys_region` VALUES (857, 'DFB5B023-993E-47CE-A8F1-4907E0A084EF', '320703', '连云区');
INSERT INTO `sys_region` VALUES (858, '2DDAC447-C3E0-4FBD-9C9E-BD09BFEAA881', '320706', '海州区');
INSERT INTO `sys_region` VALUES (859, '8E8CA71C-20D2-4BAA-9AA5-32716D08ECC5', '320707', '赣榆区');
INSERT INTO `sys_region` VALUES (860, '12C5001B-645C-4E18-BB79-FB12A6143098', '320722', '东海县');
INSERT INTO `sys_region` VALUES (861, '76FD8B41-BE66-4F64-BAB2-0AC6F8BA437F', '320723', '灌云县');
INSERT INTO `sys_region` VALUES (862, '9AB6B8E8-3470-4101-BF37-B69E01A78B72', '320724', '灌南县');
INSERT INTO `sys_region` VALUES (863, '771FE6FE-D8DA-4FBA-8D6C-517D1935A2B9', '320800', '淮安市');
INSERT INTO `sys_region` VALUES (864, 'F58B3F7E-DA33-4213-A1B5-E5FFB37604C3', '320803', '淮安区');
INSERT INTO `sys_region` VALUES (865, 'BF87FD98-4883-4779-A9FE-01D39745623F', '320804', '淮阴区');
INSERT INTO `sys_region` VALUES (866, 'C3C942C4-5A06-47AB-98DE-707743B56CA8', '320812', '清江浦区');
INSERT INTO `sys_region` VALUES (867, '60D66CD6-54A0-423D-82E6-C1CC1A12B4FF', '320813', '洪泽区');
INSERT INTO `sys_region` VALUES (868, '29FFF7EB-DDAC-4527-A6D9-BFF18F04E6FE', '320826', '涟水县');
INSERT INTO `sys_region` VALUES (869, '11BE5E91-E2B4-4E3F-8B6E-0A1A00281B97', '320830', '盱眙县');
INSERT INTO `sys_region` VALUES (870, '0834CF70-D8B1-49FE-9683-FB97F342E1F3', '320831', '金湖县');
INSERT INTO `sys_region` VALUES (871, 'C173D36C-B3F4-43A8-81EF-697F8C5FDA2A', '320900', '盐城市');
INSERT INTO `sys_region` VALUES (872, 'E4DE7085-DA2D-44F2-BA14-08BB29F8DDDD', '320902', '亭湖区');
INSERT INTO `sys_region` VALUES (873, 'DC7B76EB-3A3D-4A3B-BD66-96011FE82892', '320903', '盐都区');
INSERT INTO `sys_region` VALUES (874, '3FD8FD44-614C-4325-AF2A-5C8E3D15B91C', '320904', '大丰区');
INSERT INTO `sys_region` VALUES (875, '87008F6D-E39E-4BF9-8EFA-02F7C067B87E', '320921', '响水县');
INSERT INTO `sys_region` VALUES (876, '63E67BAE-4570-454A-A646-CCB057F1E68A', '320922', '滨海县');
INSERT INTO `sys_region` VALUES (877, 'F40D28CE-8147-4911-AAED-5FB69875D96C', '320923', '阜宁县');
INSERT INTO `sys_region` VALUES (878, '10507965-D93A-4755-AC46-33B0F3FB96AD', '320924', '射阳县');
INSERT INTO `sys_region` VALUES (879, '17BA4E20-8153-48C9-BFF9-03AF61CCB7FE', '320925', '建湖县');
INSERT INTO `sys_region` VALUES (880, 'C7EF202F-11BF-4176-AFA4-DB078C298151', '320981', '东台市');
INSERT INTO `sys_region` VALUES (881, 'DF469C91-4742-4F33-A6BA-6F3954FB8D1E', '321000', '扬州市');
INSERT INTO `sys_region` VALUES (882, '4C011772-DD77-4CC3-8029-8E1CF8A24E37', '321002', '广陵区');
INSERT INTO `sys_region` VALUES (883, '33AC69F3-DCA6-47F6-ADF8-293C0F0066FC', '321003', '邗江区');
INSERT INTO `sys_region` VALUES (884, '17F36173-2F2B-4FBC-9BF3-A00429F48B90', '321012', '江都区');
INSERT INTO `sys_region` VALUES (885, '029235CA-7D2F-4B66-8B45-0B5CEAE0A49C', '321023', '宝应县');
INSERT INTO `sys_region` VALUES (886, '8A0788F0-FB69-4F55-9FD5-F7A4711E92C3', '321081', '仪征市');
INSERT INTO `sys_region` VALUES (887, '2C4125E0-CBDE-4FBD-B421-291FBE7173C0', '321084', '高邮市');
INSERT INTO `sys_region` VALUES (888, '043C4B52-0CF3-4ED8-9E2A-82931C548BC4', '321100', '镇江市');
INSERT INTO `sys_region` VALUES (889, 'C373E5E3-6743-4FF2-B460-7138942E267D', '321102', '京口区');
INSERT INTO `sys_region` VALUES (890, 'EABB3D24-4C47-4020-B706-FA5B4642AFFA', '321111', '润州区');
INSERT INTO `sys_region` VALUES (891, '9C607A6D-DEE8-4A74-ADC5-603B45806FFB', '321112', '丹徒区');
INSERT INTO `sys_region` VALUES (892, 'AE0AD5E2-5044-4F0C-B6D5-46C9CE3C73E3', '321181', '丹阳市');
INSERT INTO `sys_region` VALUES (893, 'F489EF37-BDDF-4454-B7C3-D3D308624150', '321182', '扬中市');
INSERT INTO `sys_region` VALUES (894, '72A8055F-00C0-4E94-96A6-F59DB8CFFB5A', '321183', '句容市');
INSERT INTO `sys_region` VALUES (895, 'EB736B8B-4959-4612-A6FC-8BE859CE38D2', '321200', '泰州市');
INSERT INTO `sys_region` VALUES (896, 'FDA279D2-74EE-4983-AD65-97F707BCD764', '321202', '海陵区');
INSERT INTO `sys_region` VALUES (897, '3E048DA2-B78D-4987-AB68-0F7A902A8BF1', '321203', '高港区');
INSERT INTO `sys_region` VALUES (898, '225F67BC-8F30-4EEF-B098-A738B78E0BCE', '321204', '姜堰区');
INSERT INTO `sys_region` VALUES (899, '41AB455F-B98C-4C19-9B5D-F5CF0EC11DC0', '321281', '兴化市');
INSERT INTO `sys_region` VALUES (900, 'CC2FEA60-6BE2-48ED-9AFE-A2AC0CC146BA', '321282', '靖江市');
INSERT INTO `sys_region` VALUES (901, 'C4C12C16-A310-4F17-A805-040DB5E05DB7', '321283', '泰兴市');
INSERT INTO `sys_region` VALUES (902, 'A26032CB-6C6F-4E31-A70C-07D2E75A5F4B', '321300', '宿迁市');
INSERT INTO `sys_region` VALUES (903, 'A634F03D-687C-4344-B189-8C186EC15EB3', '321302', '宿城区');
INSERT INTO `sys_region` VALUES (904, '89F87F52-8215-4225-A457-8702DA990D15', '321311', '宿豫区');
INSERT INTO `sys_region` VALUES (905, 'DB2525FC-F85D-4633-95C5-E43A25C961C9', '321322', '沭阳县');
INSERT INTO `sys_region` VALUES (906, '31933293-5FB3-4BBA-AACE-99798AFD1F46', '321323', '泗阳县');
INSERT INTO `sys_region` VALUES (907, '647D930F-6085-4976-A316-AE75EF42BAD4', '321324', '泗洪县');
INSERT INTO `sys_region` VALUES (908, 'C40246E4-AE0D-4C61-8D9D-5126CF785E02', '330000', '浙江省');
INSERT INTO `sys_region` VALUES (909, 'B6F2D9FC-CD11-4779-B6A4-222112441366', '330100', '杭州市');
INSERT INTO `sys_region` VALUES (910, 'AB4F618F-C887-4565-8E1C-BF7610540A62', '330102', '上城区');
INSERT INTO `sys_region` VALUES (911, '597D9663-0A30-496F-9F60-4653F99D2F77', '330103', '下城区');
INSERT INTO `sys_region` VALUES (912, 'E6C84E17-2E2F-41D7-868A-D1E5BA1FC6A4', '330104', '江干区');
INSERT INTO `sys_region` VALUES (913, '0759EE7B-61EB-45A1-96C6-3806519678F3', '330105', '拱墅区');
INSERT INTO `sys_region` VALUES (914, 'FCBE69E2-41FB-4ACC-9224-F3F4FB3A2AE8', '330106', '西湖区');
INSERT INTO `sys_region` VALUES (915, '7DEF887C-01FD-450B-B8E4-58F56905205F', '330108', '滨江区');
INSERT INTO `sys_region` VALUES (916, '247E77C2-DC57-4094-849A-3D8D0F9D1440', '330109', '萧山区');
INSERT INTO `sys_region` VALUES (917, '25EF30EA-56D5-418A-9AA6-5A73A1397EBD', '330110', '余杭区');
INSERT INTO `sys_region` VALUES (918, 'C896D547-ADC1-484F-BFAF-3955C25094C8', '330111', '富阳区');
INSERT INTO `sys_region` VALUES (919, '4FBED81D-59FC-490A-AD00-28BB89FE102D', '330112', '临安区');
INSERT INTO `sys_region` VALUES (920, '85BDB5C4-C24E-48BC-A94D-AEA25165EBD4', '330122', '桐庐县');
INSERT INTO `sys_region` VALUES (921, '201EF6CB-3AA6-4127-B02A-EA84CAAA124D', '330127', '淳安县');
INSERT INTO `sys_region` VALUES (922, 'A80C11D1-8060-4230-BE07-1DDE7BC72CF9', '330182', '建德市');
INSERT INTO `sys_region` VALUES (923, 'FFCEB9BD-EC0E-4590-A76B-81286AFCB3E5', '330200', '宁波市');
INSERT INTO `sys_region` VALUES (924, '5A0BCA67-B490-4B2D-AFB4-CC7C5253E3C0', '330203', '海曙区');
INSERT INTO `sys_region` VALUES (925, '8D53A5AC-208E-4A8B-A356-045BCC38F311', '330205', '江北区');
INSERT INTO `sys_region` VALUES (926, '4F9131C5-5C6D-46A4-9907-771C6B190D9D', '330206', '北仑区');
INSERT INTO `sys_region` VALUES (927, '385DCDA0-3E2E-4928-9485-6D8C8FA674A9', '330211', '镇海区');
INSERT INTO `sys_region` VALUES (928, 'B9115872-BAEC-4EB4-B364-31E67BDA6A48', '330212', '鄞州区');
INSERT INTO `sys_region` VALUES (929, '697949B5-5976-4098-AEA3-F21CB40043E9', '330213', '奉化区');
INSERT INTO `sys_region` VALUES (930, '1734E9CF-2527-462C-B322-8057FE731264', '330225', '象山县');
INSERT INTO `sys_region` VALUES (931, '2C9B8F30-8786-4B48-B797-C528179DFBCA', '330226', '宁海县');
INSERT INTO `sys_region` VALUES (932, '28AF67CF-53D2-482D-A260-3C5C8D00B228', '330281', '余姚市');
INSERT INTO `sys_region` VALUES (933, '56B309D7-5D5C-4A7B-A438-FEE6B29CFE37', '330282', '慈溪市');
INSERT INTO `sys_region` VALUES (934, '073DE96E-4B24-41DC-BD71-28DCB457AE07', '330300', '温州市');
INSERT INTO `sys_region` VALUES (935, 'E582C165-AD3C-4298-BD16-4AC961D7F94B', '330302', '鹿城区');
INSERT INTO `sys_region` VALUES (936, '1FED343E-B0D9-49E8-ABFD-51B08374EC85', '330303', '龙湾区');
INSERT INTO `sys_region` VALUES (937, 'A47A1F55-7FD8-4B79-B8E7-63AB50E9C7F6', '330304', '瓯海区');
INSERT INTO `sys_region` VALUES (938, '9B394842-889C-4265-8381-74671BE9C3C9', '330305', '洞头区');
INSERT INTO `sys_region` VALUES (939, '3B941D28-7934-42B3-AA81-4DD63A15FF45', '330324', '永嘉县');
INSERT INTO `sys_region` VALUES (940, '26BC6A43-F02E-4CA7-B008-7EDCC5E6CA14', '330326', '平阳县');
INSERT INTO `sys_region` VALUES (941, 'CCFD296A-E5E6-4264-9C3F-6B3E3F776CAD', '330327', '苍南县');
INSERT INTO `sys_region` VALUES (942, '8119CD84-B2AD-4C79-8983-14712834D101', '330328', '文成县');
INSERT INTO `sys_region` VALUES (943, '2C54101E-31BA-4CCB-A6FC-B7BA837FEE78', '330329', '泰顺县');
INSERT INTO `sys_region` VALUES (944, 'EE6C0759-F706-4CCC-B92D-565EB528B1EF', '330381', '瑞安市');
INSERT INTO `sys_region` VALUES (945, 'FD142A85-943B-4F0F-8C2C-E32DA341CCE5', '330382', '乐清市');
INSERT INTO `sys_region` VALUES (946, '30FFFE86-9A5A-4A8E-A9A6-049077FF7D9E', '330383', '龙港市');
INSERT INTO `sys_region` VALUES (947, '8C53CF55-268B-49EB-A1FF-F4C0ADF466CC', '330400', '嘉兴市');
INSERT INTO `sys_region` VALUES (948, 'EFBAEA28-DFD7-4CB9-A5A6-44DD4349A5B4', '330402', '南湖区');
INSERT INTO `sys_region` VALUES (949, 'F02680AC-9C03-4099-8339-83F2C51C1C9B', '330411', '秀洲区');
INSERT INTO `sys_region` VALUES (950, '13E8D00D-4982-49F4-B7B5-B314FC65AC56', '330421', '嘉善县');
INSERT INTO `sys_region` VALUES (951, '80409F3E-2FDA-4E18-8C62-5CD4CBCFC3C7', '330424', '海盐县');
INSERT INTO `sys_region` VALUES (952, '04D9ADDC-5155-41BA-8EC0-C95C1BF76CB2', '330481', '海宁市');
INSERT INTO `sys_region` VALUES (953, 'BC2454C4-5145-447A-9E46-4DF6C37DFE97', '330482', '平湖市');
INSERT INTO `sys_region` VALUES (954, '42585194-1C48-4519-A1CC-EE98E1A588B0', '330483', '桐乡市');
INSERT INTO `sys_region` VALUES (955, 'FA637AA5-F364-4833-ABD7-90FD76E07DD9', '330500', '湖州市');
INSERT INTO `sys_region` VALUES (956, 'A530A058-F667-43E5-9BE9-ADBB6D143100', '330502', '吴兴区');
INSERT INTO `sys_region` VALUES (957, '94BCEA44-8D42-4C0F-877A-5FB419D65136', '330503', '南浔区');
INSERT INTO `sys_region` VALUES (958, 'BA44891C-29C3-42D1-98EA-0CA7B111915C', '330521', '德清县');
INSERT INTO `sys_region` VALUES (959, '52D8D6C8-5307-43A9-897D-1693A07F5EDE', '330522', '长兴县');
INSERT INTO `sys_region` VALUES (960, '02EB245D-2C40-4882-BE1E-AA6D3DE46C4C', '330523', '安吉县');
INSERT INTO `sys_region` VALUES (961, 'C7793FBF-177A-40E8-93CA-D8343D57B9E8', '330600', '绍兴市');
INSERT INTO `sys_region` VALUES (962, '8D5A615B-BA31-47A8-941F-07B2FE9FAF79', '330602', '越城区');
INSERT INTO `sys_region` VALUES (963, 'E29F056C-8B60-45B3-872A-F82A13B9D1B4', '330603', '柯桥区');
INSERT INTO `sys_region` VALUES (964, 'C72C4F3B-18DF-41DA-9252-65742F43D3EF', '330604', '上虞区');
INSERT INTO `sys_region` VALUES (965, '84C5D7AE-9581-447F-B02D-003E4B3E0E01', '330624', '新昌县');
INSERT INTO `sys_region` VALUES (966, '5B71A7DB-0E9E-4CCB-AC1A-FD185E2A3C01', '330681', '诸暨市');
INSERT INTO `sys_region` VALUES (967, 'B340C955-316C-4252-8C71-746EB2AD2C1B', '330683', '嵊州市');
INSERT INTO `sys_region` VALUES (968, 'A45F9F7A-B2E8-470A-A502-963B06739F83', '330700', '金华市');
INSERT INTO `sys_region` VALUES (969, '2B16BDC0-D448-41A3-AE03-70A04E2A880E', '330702', '婺城区');
INSERT INTO `sys_region` VALUES (970, '7946DE05-5389-404E-ADFC-278169E3A6D5', '330703', '金东区');
INSERT INTO `sys_region` VALUES (971, '693089DE-32CB-4EEA-AFB4-4BE9FABB9F6A', '330723', '武义县');
INSERT INTO `sys_region` VALUES (972, '2EBB4868-22CF-469B-BC57-E8138499C88D', '330726', '浦江县');
INSERT INTO `sys_region` VALUES (973, '7E54B8EC-536F-4AA2-838D-24D6B2101790', '330727', '磐安县');
INSERT INTO `sys_region` VALUES (974, 'CECC2A74-59D8-4BC8-8ECF-7196DAE2A33B', '330781', '兰溪市');
INSERT INTO `sys_region` VALUES (975, '6C75DE10-6E3C-4C7A-9F99-15AD0D645833', '330782', '义乌市');
INSERT INTO `sys_region` VALUES (976, 'C116F549-D8B8-44D6-8253-F3B298158937', '330783', '东阳市');
INSERT INTO `sys_region` VALUES (977, '8FD51937-8C77-48C6-B916-13845D125E22', '330784', '永康市');
INSERT INTO `sys_region` VALUES (978, 'ADB2C610-A9D1-4F34-AC13-8D9B63206AED', '330800', '衢州市');
INSERT INTO `sys_region` VALUES (979, 'AE9F9DB8-92A0-4E31-86A0-D059A37DCADB', '330802', '柯城区');
INSERT INTO `sys_region` VALUES (980, '553EA7E6-0D34-46BC-BEB6-15A7178CF017', '330803', '衢江区');
INSERT INTO `sys_region` VALUES (981, '634C8FEC-3D82-4ADF-9C4C-C0814B10830D', '330822', '常山县');
INSERT INTO `sys_region` VALUES (982, '0A4EDC27-B27A-4423-9C8A-73D62EB7E599', '330824', '开化县');
INSERT INTO `sys_region` VALUES (983, '1F1FC997-261F-4AA6-9305-1D490B6FD6AF', '330825', '龙游县');
INSERT INTO `sys_region` VALUES (984, 'DBB330AB-485B-48B1-AD5D-0E274F0BC4A3', '330881', '江山市');
INSERT INTO `sys_region` VALUES (985, 'DBC6A91A-918B-48A2-AA5B-B5062E50374D', '330900', '舟山市');
INSERT INTO `sys_region` VALUES (986, '1FB81E43-5094-4972-AC77-755840BC4986', '330902', '定海区');
INSERT INTO `sys_region` VALUES (987, '37BF66B0-1B2B-4659-A5C1-A66AEF428394', '330903', '普陀区');
INSERT INTO `sys_region` VALUES (988, '8FD70C7D-FBFC-48E3-92FD-74E1A1B2854F', '330921', '岱山县');
INSERT INTO `sys_region` VALUES (989, '5883308E-3A71-408D-A770-38A3618E0914', '330922', '嵊泗县');
INSERT INTO `sys_region` VALUES (990, 'F2A5DAFF-28E0-4B4B-88ED-F6F9619B04E2', '331000', '台州市');
INSERT INTO `sys_region` VALUES (991, '4ACFAB8D-FD23-4A65-A97C-3A87B145D7FF', '331002', '椒江区');
INSERT INTO `sys_region` VALUES (992, '8B4DC15C-36C6-4974-9949-828B0E07D1F3', '331003', '黄岩区');
INSERT INTO `sys_region` VALUES (993, '304AFB3D-E81C-40E9-A8A9-3BE23718D540', '331004', '路桥区');
INSERT INTO `sys_region` VALUES (994, 'D4F4572C-0665-44C4-B651-596A551E0E78', '331022', '三门县');
INSERT INTO `sys_region` VALUES (995, '15C859F2-C325-4061-A6E8-0D7E3BC86852', '331023', '天台县');
INSERT INTO `sys_region` VALUES (996, '791A02DA-702D-4A2B-9999-D744FB0BDAE4', '331024', '仙居县');
INSERT INTO `sys_region` VALUES (997, '1FC96F17-A45E-4C11-9E1D-865A093437E4', '331081', '温岭市');
INSERT INTO `sys_region` VALUES (998, '547708A8-D8A0-4624-833C-319B4EEC249E', '331082', '临海市');
INSERT INTO `sys_region` VALUES (999, '23E42B1A-9A6E-4DC4-A0EB-98F7933663CF', '331083', '玉环市');
INSERT INTO `sys_region` VALUES (1000, 'A75633F1-3D64-46CB-A4DF-88298669609E', '331100', '丽水市');
INSERT INTO `sys_region` VALUES (1001, '3629DF58-1E9D-48BF-B768-354F6214A1DB', '331102', '莲都区');
INSERT INTO `sys_region` VALUES (1002, '6BC3780C-60D8-4F79-A23D-A77BB498C43D', '331121', '青田县');
INSERT INTO `sys_region` VALUES (1003, '1C07243D-0AE9-4403-BC34-EE2C3A099883', '331122', '缙云县');
INSERT INTO `sys_region` VALUES (1004, 'DC14C501-B5DF-4D64-AF3B-DB3D1E4DC1B1', '331123', '遂昌县');
INSERT INTO `sys_region` VALUES (1005, 'A8AD415D-5EE9-4456-953F-AC6E94F94B6E', '331124', '松阳县');
INSERT INTO `sys_region` VALUES (1006, '1105EEED-566A-4DE0-A100-6DC9651B8AB1', '331125', '云和县');
INSERT INTO `sys_region` VALUES (1007, 'DDE104EF-FF9E-4230-B625-5BBB94BFADD7', '331126', '庆元县');
INSERT INTO `sys_region` VALUES (1008, '6987061C-1535-4DAE-B0F1-F7F2A5A612DD', '331127', '景宁畲族自治县');
INSERT INTO `sys_region` VALUES (1009, 'B1B1CCDA-A766-4D2E-9F8C-C8210ED11F25', '331181', '龙泉市');
INSERT INTO `sys_region` VALUES (1010, 'F9EAA3D1-AF77-49C1-9B7F-30BAE133D50F', '340000', '安徽省');
INSERT INTO `sys_region` VALUES (1011, '7E7C1C60-5E0F-474D-B7D6-C68D115E6766', '340100', '合肥市');
INSERT INTO `sys_region` VALUES (1012, 'CB963F58-5BEE-4943-976C-E1CD911461C1', '340102', '瑶海区');
INSERT INTO `sys_region` VALUES (1013, 'CECACC56-0547-470F-8ACB-996FC24DCF70', '340103', '庐阳区');
INSERT INTO `sys_region` VALUES (1014, '6F6B6E78-8DCC-459A-A5BE-B9AC130FC910', '340104', '蜀山区');
INSERT INTO `sys_region` VALUES (1015, 'BE6C7B81-8C6C-4B94-AC9B-ACA369CCD68E', '340111', '包河区');
INSERT INTO `sys_region` VALUES (1016, '0CF3ADFC-578D-4519-B008-ADF1F2578595', '340121', '长丰县');
INSERT INTO `sys_region` VALUES (1017, 'F7834668-47E2-4F31-B4A0-F21297B27AF2', '340122', '肥东县');
INSERT INTO `sys_region` VALUES (1018, '14631724-D14F-45AC-9C8C-04C4FAD3412B', '340123', '肥西县');
INSERT INTO `sys_region` VALUES (1019, 'FB4F46C7-826C-4731-8C17-1F8BCE799878', '340124', '庐江县');
INSERT INTO `sys_region` VALUES (1020, 'A9D4FCCE-AF29-4326-91A8-C975EDDD6057', '340181', '巢湖市');
INSERT INTO `sys_region` VALUES (1021, 'BD9C0A35-DEB4-4A72-846C-A22CF7A3A7FD', '340200', '芜湖市');
INSERT INTO `sys_region` VALUES (1022, '88D74EA4-9982-4A3B-8023-7A6072367BE5', '340202', '镜湖区');
INSERT INTO `sys_region` VALUES (1023, 'B4636D09-3B8C-4581-8B7D-E9D4EE462F51', '340203', '弋江区');
INSERT INTO `sys_region` VALUES (1024, 'E14F99F8-2298-4ECC-8CB3-E76EEE5DDCDF', '340207', '鸠江区');
INSERT INTO `sys_region` VALUES (1025, '02972F42-274B-4CF0-8854-70AEE9E5FB7A', '340208', '三山区');
INSERT INTO `sys_region` VALUES (1026, 'AB5A8C37-F988-43D1-9F1C-348E51F7F734', '340221', '芜湖县');
INSERT INTO `sys_region` VALUES (1027, 'A02B570B-1C47-43BB-975C-A2A5025BBEE1', '340222', '繁昌县');
INSERT INTO `sys_region` VALUES (1028, 'F80B2D6D-850A-44FF-9E1A-9FCF49072EA7', '340223', '南陵县');
INSERT INTO `sys_region` VALUES (1029, '25B8B6A6-B0BF-401F-8D51-FFE0BE253B08', '340281', '无为市');
INSERT INTO `sys_region` VALUES (1030, '1F14A4FC-1ACA-4114-8BFA-58A37FFA495C', '340300', '蚌埠市');
INSERT INTO `sys_region` VALUES (1031, '570697C9-2ED8-4AD1-8296-6AB04F7D8165', '340302', '龙子湖区');
INSERT INTO `sys_region` VALUES (1032, '02EAE153-BF6A-49D4-99F0-894D305A6555', '340303', '蚌山区');
INSERT INTO `sys_region` VALUES (1033, '1F1AA7BE-103F-421C-9DAA-916840914B7F', '340304', '禹会区');
INSERT INTO `sys_region` VALUES (1034, 'B4341404-8C00-4075-A112-AE36DAC036EB', '340311', '淮上区');
INSERT INTO `sys_region` VALUES (1035, '6FBB4DFA-2C85-49CC-A291-3BA33A3A3CBC', '340321', '怀远县');
INSERT INTO `sys_region` VALUES (1036, 'C89348F2-46F7-4DB1-AC64-E7C82B7BD1A4', '340322', '五河县');
INSERT INTO `sys_region` VALUES (1037, '76FB5264-3334-4055-B44E-DCBB78D6A604', '340323', '固镇县');
INSERT INTO `sys_region` VALUES (1038, '00F4C229-7D75-47B2-8132-D257C2BB4721', '340400', '淮南市');
INSERT INTO `sys_region` VALUES (1039, '5AEB96B4-594C-40EB-B14A-B315D1BDD7D5', '340402', '大通区');
INSERT INTO `sys_region` VALUES (1040, '91EC531E-04C0-463C-9D96-B9257D377C98', '340403', '田家庵区');
INSERT INTO `sys_region` VALUES (1041, '11441E2C-2A78-40AC-8B85-E77E34D02812', '340404', '谢家集区');
INSERT INTO `sys_region` VALUES (1042, '1A28F1D0-50A2-4B7D-A76F-E8F162411840', '340405', '八公山区');
INSERT INTO `sys_region` VALUES (1043, '2813FE1F-3E48-45DE-AEDC-EC6CC8CA5DEB', '340406', '潘集区');
INSERT INTO `sys_region` VALUES (1044, '8510283A-1F07-4427-86B5-F36FED6F996F', '340421', '凤台县');
INSERT INTO `sys_region` VALUES (1045, 'AF289FE9-D7A7-404C-9FA2-C72D951DED94', '340422', '寿县');
INSERT INTO `sys_region` VALUES (1046, 'B201006B-1A4A-4A30-94DC-4CE1EEDFD2F8', '340500', '马鞍山市');
INSERT INTO `sys_region` VALUES (1047, '09F3204D-346D-4D6D-9EEF-6280DDBF54C5', '340503', '花山区');
INSERT INTO `sys_region` VALUES (1048, '01E2278E-3730-4A50-B2E6-01F624B109F1', '340504', '雨山区');
INSERT INTO `sys_region` VALUES (1049, '7B3FA190-DA22-4829-B462-32313DEBA37B', '340506', '博望区');
INSERT INTO `sys_region` VALUES (1050, '42ED5D9F-E7D6-4B7F-8631-FF0B593A626E', '340521', '当涂县');
INSERT INTO `sys_region` VALUES (1051, '7E51D36E-BAFB-441C-B1EB-B96C70AD9ABC', '340522', '含山县');
INSERT INTO `sys_region` VALUES (1052, 'E9464E03-B4F9-4B4E-91F4-E99F75E1C7C0', '340523', '和县');
INSERT INTO `sys_region` VALUES (1053, 'F3242BE9-36D3-4643-A110-AB303DF7A790', '340600', '淮北市');
INSERT INTO `sys_region` VALUES (1054, '62AB9828-864D-463D-BDC5-155CAA16C786', '340602', '杜集区');
INSERT INTO `sys_region` VALUES (1055, '259B7D31-EB7B-4B98-B65B-AAAF27B32DA6', '340603', '相山区');
INSERT INTO `sys_region` VALUES (1056, '200CB023-6D27-4499-9684-DE04CD3BA4B9', '340604', '烈山区');
INSERT INTO `sys_region` VALUES (1057, 'A3F15C81-B963-461E-B5AF-E2E769C97B12', '340621', '濉溪县');
INSERT INTO `sys_region` VALUES (1058, 'F72B9EEE-ABAF-42C4-91B1-626335BA8FCA', '340700', '铜陵市');
INSERT INTO `sys_region` VALUES (1059, '468D4A04-78AF-4511-8CBB-77EC11C1F5C2', '340705', '铜官区');
INSERT INTO `sys_region` VALUES (1060, '5E59C7EE-1C77-42C4-AD11-E4CE934884F8', '340706', '义安区');
INSERT INTO `sys_region` VALUES (1061, '1A67B78D-73B4-456D-90C5-363A85DBB937', '340711', '郊区');
INSERT INTO `sys_region` VALUES (1062, '3E30D073-EAC2-4E5C-B354-04DCE95B010D', '340722', '枞阳县');
INSERT INTO `sys_region` VALUES (1063, '988CAFB4-131E-4C79-94FA-DA8455801EF9', '340800', '安庆市');
INSERT INTO `sys_region` VALUES (1064, '26BEFCDE-32B9-4356-9FD8-314B4EE87960', '340802', '迎江区');
INSERT INTO `sys_region` VALUES (1065, 'CEE69926-7DAC-4FFB-A99B-71E4AE42EC7A', '340803', '大观区');
INSERT INTO `sys_region` VALUES (1066, 'E8DE7E2F-C75A-42B1-A3FD-B156F6B7ACE4', '340811', '宜秀区');
INSERT INTO `sys_region` VALUES (1067, 'BDF6F3EC-E610-4198-B618-CC1D0CDC3792', '340822', '怀宁县');
INSERT INTO `sys_region` VALUES (1068, '6469A70D-624F-4625-853C-D9F00703899B', '340825', '太湖县');
INSERT INTO `sys_region` VALUES (1069, '71E36797-F2B9-4349-94B8-D6E7ABCA10E1', '340826', '宿松县');
INSERT INTO `sys_region` VALUES (1070, '2413CA7D-9DBC-4435-8004-74D838720989', '340827', '望江县');
INSERT INTO `sys_region` VALUES (1071, '8C586AD9-DB3B-4B1C-92A3-65532FC45852', '340828', '岳西县');
INSERT INTO `sys_region` VALUES (1072, '15A83811-315A-4F53-81D3-469C6373ABC7', '340881', '桐城市');
INSERT INTO `sys_region` VALUES (1073, '7460DFFB-3BEF-4BCF-BC65-D3FE23E19A54', '340882', '潜山市');
INSERT INTO `sys_region` VALUES (1074, '87B96DDA-114B-4552-8C53-64FD44EF852B', '341000', '黄山市');
INSERT INTO `sys_region` VALUES (1075, '75F47911-97A9-46F8-B24D-9759A016B84D', '341002', '屯溪区');
INSERT INTO `sys_region` VALUES (1076, '7EB307FF-B7AE-49DC-BAAA-C8BC540C32B1', '341003', '黄山区');
INSERT INTO `sys_region` VALUES (1077, 'E7944839-15E9-41FA-9590-D4DB24F51150', '341004', '徽州区');
INSERT INTO `sys_region` VALUES (1078, '4693BF86-1064-46D7-8131-CC33B9E1CCC2', '341021', '歙县');
INSERT INTO `sys_region` VALUES (1079, 'E2F3C0CB-D496-4785-AB77-2E0E337A2A13', '341022', '休宁县');
INSERT INTO `sys_region` VALUES (1080, '9055FBEB-C487-4C5F-9E36-8F21CE6EB7D9', '341023', '黟县');
INSERT INTO `sys_region` VALUES (1081, '0E5A74D8-FD58-4FAC-96ED-E256A3A8B018', '341024', '祁门县');
INSERT INTO `sys_region` VALUES (1082, '3A944FFE-C533-4109-B23F-26D0478852CA', '341100', '滁州市');
INSERT INTO `sys_region` VALUES (1083, '066F8237-21AE-4379-B459-49F4D633467A', '341102', '琅琊区');
INSERT INTO `sys_region` VALUES (1084, 'AB02E161-B823-4C04-8249-FCC9AD15140B', '341103', '南谯区');
INSERT INTO `sys_region` VALUES (1085, '01871021-3E1C-4E1A-B521-6ADE2E1EE4DE', '341122', '来安县');
INSERT INTO `sys_region` VALUES (1086, 'A8C7A62A-492A-4B7D-8EC3-637CC06BF3FE', '341124', '全椒县');
INSERT INTO `sys_region` VALUES (1087, '11B57577-D5CB-42E7-A607-40BFB0444E9A', '341125', '定远县');
INSERT INTO `sys_region` VALUES (1088, 'B3165407-4B5E-4AFD-9FEC-DE508C612BF1', '341126', '凤阳县');
INSERT INTO `sys_region` VALUES (1089, '38E3C5BB-925E-4587-8B9D-43ED44DB53FA', '341181', '天长市');
INSERT INTO `sys_region` VALUES (1090, '577E8D0C-0CBA-4A3A-8E91-CE167513CE43', '341182', '明光市');
INSERT INTO `sys_region` VALUES (1091, 'D387CF95-0D90-4822-B74A-2942DBE6E53F', '341200', '阜阳市');
INSERT INTO `sys_region` VALUES (1092, 'F85F2358-662B-4F1A-9D7E-B8ACA56862D7', '341202', '颍州区');
INSERT INTO `sys_region` VALUES (1093, 'A4FAB6FC-B1D3-4214-A2C4-C5DF6F800764', '341203', '颍东区');
INSERT INTO `sys_region` VALUES (1094, '6AF9D433-5802-4B3D-8291-019127496A7A', '341204', '颍泉区');
INSERT INTO `sys_region` VALUES (1095, '76431CD3-538D-43D6-818E-53C08F95C365', '341221', '临泉县');
INSERT INTO `sys_region` VALUES (1096, 'FA9E9D15-1EDE-4C65-9EF9-570875A7AFA4', '341222', '太和县');
INSERT INTO `sys_region` VALUES (1097, '41BE0431-4532-4DCE-8CCD-D59371B5EA0F', '341225', '阜南县');
INSERT INTO `sys_region` VALUES (1098, 'DFD853D9-4300-4A92-A029-9DF537627084', '341226', '颍上县');
INSERT INTO `sys_region` VALUES (1099, '9604F0A9-52C2-4311-82AF-A05F350EFD99', '341282', '界首市');
INSERT INTO `sys_region` VALUES (1100, '21AFC643-6B23-4A41-A146-7B16A0C56DF4', '341300', '宿州市');
INSERT INTO `sys_region` VALUES (1101, '086947A9-A8FD-49DB-9ADF-AC99F778F6D5', '341302', '埇桥区');
INSERT INTO `sys_region` VALUES (1102, '3C9663AB-93E7-469C-8F2B-00D760307033', '341321', '砀山县');
INSERT INTO `sys_region` VALUES (1103, '30E95A5D-D4C6-4A98-B05D-8885FF4B2B7B', '341322', '萧县');
INSERT INTO `sys_region` VALUES (1104, '06530B9D-65FC-4CD5-86A9-95296D6EC1CA', '341323', '灵璧县');
INSERT INTO `sys_region` VALUES (1105, '15C1D65B-D7F7-44E5-BD45-BF45E7B7B8A1', '341324', '泗县');
INSERT INTO `sys_region` VALUES (1106, 'D74BE7FE-9A85-4E7A-992A-2CBC61456D45', '341500', '六安市');
INSERT INTO `sys_region` VALUES (1107, '3B7D17D5-26FF-4913-A2CF-12396C6383D5', '341502', '金安区');
INSERT INTO `sys_region` VALUES (1108, 'FEC1CC57-6E72-439E-B62A-54E630B71749', '341503', '裕安区');
INSERT INTO `sys_region` VALUES (1109, '12911187-BD0C-4C51-ADA0-3E2672F23214', '341504', '叶集区');
INSERT INTO `sys_region` VALUES (1110, '909C34ED-40B6-47AE-98D1-43CBF142FCEF', '341522', '霍邱县');
INSERT INTO `sys_region` VALUES (1111, '8C885764-8DD2-4672-9CD0-9196E91365D8', '341523', '舒城县');
INSERT INTO `sys_region` VALUES (1112, 'B4CF6EEA-6B37-4AE3-B3E1-0E07EA9637C1', '341524', '金寨县');
INSERT INTO `sys_region` VALUES (1113, '74386D06-192D-4167-938D-0BC4A162F91F', '341525', '霍山县');
INSERT INTO `sys_region` VALUES (1114, '994D8485-CC21-41E3-9E8C-A564CCEC981B', '341600', '亳州市');
INSERT INTO `sys_region` VALUES (1115, 'D7B16B13-0442-404B-9837-B8F89FAE6E72', '341602', '谯城区');
INSERT INTO `sys_region` VALUES (1116, '2C996F51-DA7F-4305-B1F5-3BE5B321B4BB', '341621', '涡阳县');
INSERT INTO `sys_region` VALUES (1117, 'AD6D7713-E4B1-4005-A7CD-D06F04CAB846', '341622', '蒙城县');
INSERT INTO `sys_region` VALUES (1118, 'C3BF5B98-DBF3-4681-A815-0425D34D929C', '341623', '利辛县');
INSERT INTO `sys_region` VALUES (1119, 'C3DCC244-C2AE-4A04-9038-CB638B694125', '341700', '池州市');
INSERT INTO `sys_region` VALUES (1120, 'EDF8B288-DF16-4AAD-9767-62F703BA3F02', '341702', '贵池区');
INSERT INTO `sys_region` VALUES (1121, 'DDB2A4BF-82E9-41C2-8799-A11B03A29E7D', '341721', '东至县');
INSERT INTO `sys_region` VALUES (1122, '7467F6E9-51AA-4168-B6C0-DC12C0A38DA0', '341722', '石台县');
INSERT INTO `sys_region` VALUES (1123, 'B1F1F4D9-8965-4806-B59F-6E81BE95BD2D', '341723', '青阳县');
INSERT INTO `sys_region` VALUES (1124, 'DA95CD0A-928A-4BE2-988F-BD7F407BCCAF', '341800', '宣城市');
INSERT INTO `sys_region` VALUES (1125, '7D0E1453-9D96-4B20-9AA4-F749DC0B201C', '341802', '宣州区');
INSERT INTO `sys_region` VALUES (1126, 'F024F6EF-F602-4588-8C4E-CF0EF9485C72', '341821', '郎溪县');
INSERT INTO `sys_region` VALUES (1127, 'B1933667-322A-4DC7-910C-E4204AACDFD0', '341823', '泾县');
INSERT INTO `sys_region` VALUES (1128, '1871F4A0-BBE8-4070-8570-4159A9AA3B6B', '341824', '绩溪县');
INSERT INTO `sys_region` VALUES (1129, '4845DB9A-BA2A-4E64-BF41-7AA9103009BD', '341825', '旌德县');
INSERT INTO `sys_region` VALUES (1130, 'F925F49C-E53D-42C2-800F-78960D1729CE', '341881', '宁国市');
INSERT INTO `sys_region` VALUES (1131, '310C3C65-4360-4980-89B2-04081D2E6474', '341882', '广德市');
INSERT INTO `sys_region` VALUES (1132, 'B59519C2-7582-4248-A7EB-B37BDAA9C7D0', '350000', '福建省');
INSERT INTO `sys_region` VALUES (1133, '88114D05-1B3E-447C-AA44-A70892970C67', '350100', '福州市');
INSERT INTO `sys_region` VALUES (1134, 'A7F465B5-709C-41D3-9EC7-BD7223F36E90', '350102', '鼓楼区');
INSERT INTO `sys_region` VALUES (1135, '8D2EE3FC-C737-41BB-BE5E-FB4326710692', '350103', '台江区');
INSERT INTO `sys_region` VALUES (1136, 'DCA11D6D-E996-4A8F-8908-ED2753A4024B', '350104', '仓山区');
INSERT INTO `sys_region` VALUES (1137, '585C5872-E9DB-4438-8243-F1F98C0A4B36', '350105', '马尾区');
INSERT INTO `sys_region` VALUES (1138, 'EAF24F32-2487-44F8-99C2-D754A1181D0F', '350111', '晋安区');
INSERT INTO `sys_region` VALUES (1139, 'DC8CB999-274A-4638-AA44-A4FCF98DCA25', '350112', '长乐区');
INSERT INTO `sys_region` VALUES (1140, '9B83A560-1B5B-4F3A-B8CF-6AAA314C032A', '350121', '闽侯县');
INSERT INTO `sys_region` VALUES (1141, '90032964-5261-4A9A-BA63-B6BD1051191D', '350122', '连江县');
INSERT INTO `sys_region` VALUES (1142, '450A8472-D7A2-4BFD-A12A-7BE17D6CEABA', '350123', '罗源县');
INSERT INTO `sys_region` VALUES (1143, '73B3812B-98FA-44A0-9ABE-0826919C40F0', '350124', '闽清县');
INSERT INTO `sys_region` VALUES (1144, '3527C2CF-B01C-435F-8E5A-8C608B356D80', '350125', '永泰县');
INSERT INTO `sys_region` VALUES (1145, 'DA205DA8-3724-4D05-88D4-33249AD934EA', '350128', '平潭县');
INSERT INTO `sys_region` VALUES (1146, 'D000CB92-6EE0-4276-B35C-A37A3AA99EE8', '350181', '福清市');
INSERT INTO `sys_region` VALUES (1147, '8DBAB0BB-1CA1-4199-892A-1DE485D18D1E', '350200', '厦门市');
INSERT INTO `sys_region` VALUES (1148, '6D897E2A-3247-417B-8730-D15C7BE64493', '350203', '思明区');
INSERT INTO `sys_region` VALUES (1149, 'EDF09CC9-C6C7-427B-9B4E-14DCFB3C26B8', '350205', '海沧区');
INSERT INTO `sys_region` VALUES (1150, '86E9FCBC-2CC2-440B-8A0F-1B86206D64EC', '350206', '湖里区');
INSERT INTO `sys_region` VALUES (1151, '08A0B422-9CBE-464B-BC01-C855D6C7C181', '350211', '集美区');
INSERT INTO `sys_region` VALUES (1152, '8BD5A3D5-33B0-4F36-8F55-4CFE28825588', '350212', '同安区');
INSERT INTO `sys_region` VALUES (1153, '19C7B1FA-5B75-4C49-A62C-69ACEC2ECDFF', '350213', '翔安区');
INSERT INTO `sys_region` VALUES (1154, '397ABB30-924F-4BE8-91D0-D4E318F47423', '350300', '莆田市');
INSERT INTO `sys_region` VALUES (1155, '02C91066-2CBE-4276-85E8-4C734E0D87F5', '350302', '城厢区');
INSERT INTO `sys_region` VALUES (1156, 'F7697FAF-7B16-404F-9921-4B4C1BAAED69', '350303', '涵江区');
INSERT INTO `sys_region` VALUES (1157, '6A10B5B7-23BD-4CBA-A35C-04AB401656F7', '350304', '荔城区');
INSERT INTO `sys_region` VALUES (1158, '9570E56C-A68A-4C11-889E-02BF5ABDCD0F', '350305', '秀屿区');
INSERT INTO `sys_region` VALUES (1159, 'F5EE9E7E-E2AB-49C0-BF66-69764A10FC8C', '350322', '仙游县');
INSERT INTO `sys_region` VALUES (1160, 'C81F1AC5-DB4F-496B-8E4B-54C957B9E522', '350400', '三明市');
INSERT INTO `sys_region` VALUES (1161, '2723CE90-2493-4BA8-AB6A-D9A7DF1D221C', '350402', '梅列区');
INSERT INTO `sys_region` VALUES (1162, '7A4287C9-225D-4AAB-8342-32214AC6D3F9', '350403', '三元区');
INSERT INTO `sys_region` VALUES (1163, '1D7D0B9F-5AF2-4CAC-BC40-216920036601', '350421', '明溪县');
INSERT INTO `sys_region` VALUES (1164, '3D858FFF-9223-4053-BFEA-B7FF4CDF22B6', '350423', '清流县');
INSERT INTO `sys_region` VALUES (1165, '2D780B72-7A96-4FBB-8A6C-A5B6354F8EBE', '350424', '宁化县');
INSERT INTO `sys_region` VALUES (1166, '009E876D-8E5C-49F3-9E18-84664594E7F2', '350425', '大田县');
INSERT INTO `sys_region` VALUES (1167, '6B2E930D-0728-471A-B4C4-63C478888B6C', '350426', '尤溪县');
INSERT INTO `sys_region` VALUES (1168, 'C0E6D918-9F44-4C99-9D58-EB624F9AA238', '350427', '沙县');
INSERT INTO `sys_region` VALUES (1169, 'C113C049-1104-461D-A784-ABCB526C9D4F', '350428', '将乐县');
INSERT INTO `sys_region` VALUES (1170, '5F378D11-376B-4DB7-B91D-EEA698F821CF', '350429', '泰宁县');
INSERT INTO `sys_region` VALUES (1171, 'B2AF6BBB-88CF-4AA8-8649-168BC9C98338', '350430', '建宁县');
INSERT INTO `sys_region` VALUES (1172, '3B52EDFF-91DA-42C6-9913-A5258458A506', '350481', '永安市');
INSERT INTO `sys_region` VALUES (1173, '7F3A9BFF-D899-4720-B1C8-93DE8E094CEF', '350500', '泉州市');
INSERT INTO `sys_region` VALUES (1174, '324FFE85-8458-4493-8CBA-DA4457765D5C', '350502', '鲤城区');
INSERT INTO `sys_region` VALUES (1175, '597DFC78-F52C-47BD-988F-25D4E7A4354A', '350503', '丰泽区');
INSERT INTO `sys_region` VALUES (1176, '685DC277-46CC-4189-B8BC-B33AFA273FE9', '350504', '洛江区');
INSERT INTO `sys_region` VALUES (1177, '546034A1-18C5-4998-89F1-2ED527EF6BA1', '350505', '泉港区');
INSERT INTO `sys_region` VALUES (1178, '574F1AA4-84ED-4426-A222-2EDFF607F247', '350521', '惠安县');
INSERT INTO `sys_region` VALUES (1179, '22431800-2F0E-4906-824D-AEB3F0F7851F', '350524', '安溪县');
INSERT INTO `sys_region` VALUES (1180, 'BC95D0C2-DBD6-4962-8C64-88C38D8CFC21', '350525', '永春县');
INSERT INTO `sys_region` VALUES (1181, 'FAABAEF8-E652-476A-96B2-244212FDB5A8', '350526', '德化县');
INSERT INTO `sys_region` VALUES (1182, '06AEA1D7-2F95-4069-8B11-44EAFEC9689D', '350527', '金门县');
INSERT INTO `sys_region` VALUES (1183, '4FD14982-213F-482C-ACBC-6BAA6E09464B', '350581', '石狮市');
INSERT INTO `sys_region` VALUES (1184, 'A33E90BA-40AE-4BCE-A952-381FEB34E599', '350582', '晋江市');
INSERT INTO `sys_region` VALUES (1185, 'ED49A20E-8CBE-4641-8B42-6E82261BE125', '350583', '南安市');
INSERT INTO `sys_region` VALUES (1186, '53CA1776-6482-47C6-83C2-14452E06DBA8', '350600', '漳州市');
INSERT INTO `sys_region` VALUES (1187, '7036C2D6-EB7B-4D91-A36E-46685DB36486', '350602', '芗城区');
INSERT INTO `sys_region` VALUES (1188, '722BF3BC-B215-45DE-B325-87DAC8F4DD23', '350603', '龙文区');
INSERT INTO `sys_region` VALUES (1189, '26E5C9F3-5B9E-443C-9B9F-DBD5FA99B2E9', '350622', '云霄县');
INSERT INTO `sys_region` VALUES (1190, '94E9A2D8-E705-4124-8031-79D2FD5DB33F', '350623', '漳浦县');
INSERT INTO `sys_region` VALUES (1191, 'F8A59E30-2B27-4521-B655-3388F8ABCA6C', '350624', '诏安县');
INSERT INTO `sys_region` VALUES (1192, '78E0B131-953B-4B47-972D-EBD781F4AC7A', '350625', '长泰县');
INSERT INTO `sys_region` VALUES (1193, 'F4B14358-6FA6-45AD-BB38-FA737CCF8366', '350626', '东山县');
INSERT INTO `sys_region` VALUES (1194, 'E6C095FC-7898-4AC5-A7ED-E78D88C3F82E', '350627', '南靖县');
INSERT INTO `sys_region` VALUES (1195, 'B1CFF5A7-D147-4B37-B6F3-A43D70E32270', '350628', '平和县');
INSERT INTO `sys_region` VALUES (1196, '58A435F1-3902-4C7C-8437-D12DAC9B7E24', '350629', '华安县');
INSERT INTO `sys_region` VALUES (1197, '3E59FB01-29B9-46EC-A5DC-FBE82165183F', '350681', '龙海市');
INSERT INTO `sys_region` VALUES (1198, 'C8B12BAD-8CA4-4953-978E-59DD2591821B', '350700', '南平市');
INSERT INTO `sys_region` VALUES (1199, 'F57675B5-15C4-4D8F-BF71-0FFE3923B0F1', '350702', '延平区');
INSERT INTO `sys_region` VALUES (1200, '2B23E859-19E6-4FA5-8085-F9F02D1F1CE9', '350703', '建阳区');
INSERT INTO `sys_region` VALUES (1201, 'DE0A26BF-F20C-49E1-9229-DA4E49671E4B', '350721', '顺昌县');
INSERT INTO `sys_region` VALUES (1202, 'E16C6F05-D1BC-4AEE-9555-FCE95EB05045', '350722', '浦城县');
INSERT INTO `sys_region` VALUES (1203, '4DE7120D-54F2-4057-8589-C81A34269869', '350723', '光泽县');
INSERT INTO `sys_region` VALUES (1204, '21664C40-8005-4341-894E-49043FC32712', '350724', '松溪县');
INSERT INTO `sys_region` VALUES (1205, 'F080C38F-28DA-416D-B245-45A5DE218C8A', '350725', '政和县');
INSERT INTO `sys_region` VALUES (1206, 'D8D3A511-020F-449B-8203-361C46F29F6D', '350781', '邵武市');
INSERT INTO `sys_region` VALUES (1207, '32BCFD9F-8B73-4E22-BD2E-A081536426C1', '350782', '武夷山市');
INSERT INTO `sys_region` VALUES (1208, 'A1A0FB40-C574-46A6-8771-7E49117F6B3F', '350783', '建瓯市');
INSERT INTO `sys_region` VALUES (1209, '8BCA3C18-5744-4C2F-89C1-7AD8C949E66F', '350800', '龙岩市');
INSERT INTO `sys_region` VALUES (1210, 'F6073F46-ADE0-4009-B79D-EBED2FA5581D', '350802', '新罗区');
INSERT INTO `sys_region` VALUES (1211, '9C108F4E-BBDD-4D13-B56D-E2B3A00C0DB1', '350803', '永定区');
INSERT INTO `sys_region` VALUES (1212, '61A7553D-0960-4509-887A-CBCE9B4B2B26', '350821', '长汀县');
INSERT INTO `sys_region` VALUES (1213, 'F576C273-5A9D-4BAF-94BE-B8D1DFA159EC', '350823', '上杭县');
INSERT INTO `sys_region` VALUES (1214, 'B0FFA037-BDB3-4CCD-AC20-C7B75727436A', '350824', '武平县');
INSERT INTO `sys_region` VALUES (1215, '04304158-C887-4288-8BB4-C1F145AEAB1C', '350825', '连城县');
INSERT INTO `sys_region` VALUES (1216, '4EA366F0-7F1C-4CFD-AAA6-017E822CFFBE', '350881', '漳平市');
INSERT INTO `sys_region` VALUES (1217, '9F2CCC82-4D74-4D37-A0B6-AC69DC81F73F', '350900', '宁德市');
INSERT INTO `sys_region` VALUES (1218, '12591AFA-37A7-4A06-BAC7-09D4C23144E0', '350902', '蕉城区');
INSERT INTO `sys_region` VALUES (1219, '20E01F95-DC6B-4927-A80E-239AB4762651', '350921', '霞浦县');
INSERT INTO `sys_region` VALUES (1220, '2312982F-2A3E-48FF-8707-18828D599C15', '350922', '古田县');
INSERT INTO `sys_region` VALUES (1221, 'CD00C10B-7E28-4638-AD7F-7CE63ABF1462', '350923', '屏南县');
INSERT INTO `sys_region` VALUES (1222, 'CFF40ED5-4F10-4DF8-9D5E-79B17B975CB0', '350924', '寿宁县');
INSERT INTO `sys_region` VALUES (1223, '79115219-3FEC-4265-B65C-AD3FE309F404', '350925', '周宁县');
INSERT INTO `sys_region` VALUES (1224, 'B95795E1-71E7-46B9-B5D2-54570978393A', '350926', '柘荣县');
INSERT INTO `sys_region` VALUES (1225, '3C3C2D22-1675-49A8-8329-98B4A6CF77F1', '350981', '福安市');
INSERT INTO `sys_region` VALUES (1226, 'A81296BA-AFE0-4127-8279-1791DE6D8B34', '350982', '福鼎市');
INSERT INTO `sys_region` VALUES (1227, '957861A9-9602-4A45-ADB7-606CD72A7075', '360000', '江西省');
INSERT INTO `sys_region` VALUES (1228, '9522DA62-91E1-4DC4-9923-B910C45D1DA7', '360100', '南昌市');
INSERT INTO `sys_region` VALUES (1229, 'D4EC1BF3-67D5-48F3-8E74-29015EF523B7', '360102', '东湖区');
INSERT INTO `sys_region` VALUES (1230, 'CEAD7611-9A64-4C7D-882F-F59EBEBA2B6B', '360103', '西湖区');
INSERT INTO `sys_region` VALUES (1231, '7638A73B-7C9D-4742-9DE6-8EF06B242D2E', '360104', '青云谱区');
INSERT INTO `sys_region` VALUES (1232, '29824839-0811-4477-91C1-CC2B70F87CF1', '360111', '青山湖区');
INSERT INTO `sys_region` VALUES (1233, '1582667D-42FC-4572-A89E-549142E594A8', '360112', '新建区');
INSERT INTO `sys_region` VALUES (1234, 'E678A22B-E6B4-452F-B34D-CBC438C79B6B', '360113', '红谷滩区');
INSERT INTO `sys_region` VALUES (1235, 'A2500103-E4AC-494F-9CAC-9CB3BE17B4F1', '360121', '南昌县');
INSERT INTO `sys_region` VALUES (1236, 'B565F0DB-DCA6-44D6-9A0A-52FE037E4849', '360123', '安义县');
INSERT INTO `sys_region` VALUES (1237, '2D19D04F-8D90-442A-AF6A-872AE6B3216C', '360124', '进贤县');
INSERT INTO `sys_region` VALUES (1238, '8E67A992-DB21-49F2-8452-50DA35483855', '360200', '景德镇市');
INSERT INTO `sys_region` VALUES (1239, '3998BC4E-166D-44E8-BE80-16174BE7D4E2', '360202', '昌江区');
INSERT INTO `sys_region` VALUES (1240, 'D230C650-4347-4558-AAEF-34EE27C7539E', '360203', '珠山区');
INSERT INTO `sys_region` VALUES (1241, 'B4FFAF51-74A2-4EA3-BA86-7E84E31858E8', '360222', '浮梁县');
INSERT INTO `sys_region` VALUES (1242, 'F0113CE6-0BA5-4FC1-B103-87F851CA732C', '360281', '乐平市');
INSERT INTO `sys_region` VALUES (1243, '0ACC9F95-F82E-4DFE-83C1-ED79DF61BCDD', '360300', '萍乡市');
INSERT INTO `sys_region` VALUES (1244, 'FD7DE54C-6016-4EA2-9626-CD9B89706CDF', '360302', '安源区');
INSERT INTO `sys_region` VALUES (1245, 'D3EEFA57-143F-48EC-A5CD-D69C9CAF9315', '360313', '湘东区');
INSERT INTO `sys_region` VALUES (1246, 'BC951F17-860E-4DAE-B4CB-A35F705AD7B8', '360321', '莲花县');
INSERT INTO `sys_region` VALUES (1247, '9991FF00-6E20-4C77-906B-DCD4203D516F', '360322', '上栗县');
INSERT INTO `sys_region` VALUES (1248, '12712D3D-54AB-44B4-A53A-06121736A3C2', '360323', '芦溪县');
INSERT INTO `sys_region` VALUES (1249, '72FD4921-78EB-4EE4-B33A-1F7C95F940F6', '360400', '九江市');
INSERT INTO `sys_region` VALUES (1250, 'E2562565-8E13-4B90-8045-7655ED3B610B', '360402', '濂溪区');
INSERT INTO `sys_region` VALUES (1251, '849CE209-651D-4C00-84CA-E7224AE0757E', '360403', '浔阳区');
INSERT INTO `sys_region` VALUES (1252, 'F1CE6E4E-C3E3-4B09-B4BF-9BEB8F6D26E6', '360404', '柴桑区');
INSERT INTO `sys_region` VALUES (1253, '93F406ED-4015-4E12-9135-E68A5568C282', '360423', '武宁县');
INSERT INTO `sys_region` VALUES (1254, 'DEFE4A1D-98D2-4134-AB9A-5D32D02B7D0A', '360424', '修水县');
INSERT INTO `sys_region` VALUES (1255, '1F9A0551-8337-4B59-A04E-0C9BF0BCAE06', '360425', '永修县');
INSERT INTO `sys_region` VALUES (1256, 'EC4EDDA1-1DA8-41DE-933B-70CE5A02BCC2', '360426', '德安县');
INSERT INTO `sys_region` VALUES (1257, 'B7211B9D-BD95-4525-9291-82F8C8D468F9', '360428', '都昌县');
INSERT INTO `sys_region` VALUES (1258, '3E2D3DE1-3C28-4C34-BB5F-214FEBFA033D', '360429', '湖口县');
INSERT INTO `sys_region` VALUES (1259, '4EC1789B-026D-4703-9A7D-CFB647CF2E3C', '360430', '彭泽县');
INSERT INTO `sys_region` VALUES (1260, 'E4996F72-3846-48F6-928E-5FE50007824C', '360481', '瑞昌市');
INSERT INTO `sys_region` VALUES (1261, '81B127A5-187D-4F59-B744-CE43AA0F5A86', '360482', '共青城市');
INSERT INTO `sys_region` VALUES (1262, '77EE4232-E8E6-4BD4-B24C-B63A2DC40826', '360483', '庐山市');
INSERT INTO `sys_region` VALUES (1263, 'DBB6B1FE-9C6A-46F4-8CBB-42D2AAA3E592', '360500', '新余市');
INSERT INTO `sys_region` VALUES (1264, 'DDCA9EBD-2891-4F83-A924-CF8D589B5F71', '360502', '渝水区');
INSERT INTO `sys_region` VALUES (1265, '74B0C3FE-7908-4BA9-A2AC-347EAA61F6CF', '360521', '分宜县');
INSERT INTO `sys_region` VALUES (1266, 'F2EA2BE1-6157-4C53-A6ED-4D064A5BB991', '360600', '鹰潭市');
INSERT INTO `sys_region` VALUES (1267, 'E39835B3-EEAF-4F0F-AC6E-4045C8E137FB', '360602', '月湖区');
INSERT INTO `sys_region` VALUES (1268, '852B7524-4836-4FD7-A5BF-67D99AFE2730', '360603', '余江区');
INSERT INTO `sys_region` VALUES (1269, 'DC00E134-C17D-486D-9871-9246E9896B30', '360681', '贵溪市');
INSERT INTO `sys_region` VALUES (1270, '094E0B6F-6ABE-42A5-8B0E-BA9A403D62C2', '360700', '赣州市');
INSERT INTO `sys_region` VALUES (1271, '4499B79C-3DA9-4D89-9763-5D8F61783D75', '360702', '章贡区');
INSERT INTO `sys_region` VALUES (1272, '349E6409-8533-4C39-8131-8FD759DBDB1C', '360703', '南康区');
INSERT INTO `sys_region` VALUES (1273, '1CB5FD47-DED9-4C30-8690-9B234D46B9CB', '360704', '赣县区');
INSERT INTO `sys_region` VALUES (1274, '0FF5B807-0133-41A4-A58B-84623F1A8B80', '360722', '信丰县');
INSERT INTO `sys_region` VALUES (1275, 'F05CC54A-488A-4166-9F9D-8A22C163EA3F', '360723', '大余县');
INSERT INTO `sys_region` VALUES (1276, 'CA09BDAD-0609-41A3-8B01-8B6AA02A9D5B', '360724', '上犹县');
INSERT INTO `sys_region` VALUES (1277, 'B16D064C-E52E-4DB2-9B7D-C3C0D98AADCB', '360725', '崇义县');
INSERT INTO `sys_region` VALUES (1278, '10525FFA-7071-4187-9073-D3837F2C4B98', '360726', '安远县');
INSERT INTO `sys_region` VALUES (1279, '5B398F7D-6942-43FC-8B7E-740EAD24B539', '360727', '龙南县');
INSERT INTO `sys_region` VALUES (1280, '7E670351-4D08-4BB4-AF47-273C06469EE9', '360728', '定南县');
INSERT INTO `sys_region` VALUES (1281, '38267706-E397-4B70-8224-B853B7B03E55', '360729', '全南县');
INSERT INTO `sys_region` VALUES (1282, 'B2E47669-A494-42B3-9027-1DF6A33E190C', '360730', '宁都县');
INSERT INTO `sys_region` VALUES (1283, '74F7D5FB-FAD4-4108-A347-7055CB0B257E', '360731', '于都县');
INSERT INTO `sys_region` VALUES (1284, '9F8F00E3-9A71-443E-B364-7B52B66400E0', '360732', '兴国县');
INSERT INTO `sys_region` VALUES (1285, 'E18D1C57-73AB-4803-9543-C752B8F1F430', '360733', '会昌县');
INSERT INTO `sys_region` VALUES (1286, 'BD03B8B2-ABA8-4A17-A478-5F7110452905', '360734', '寻乌县');
INSERT INTO `sys_region` VALUES (1287, '61C31AFD-0B5D-4755-8ADD-E5DE4C442C3C', '360735', '石城县');
INSERT INTO `sys_region` VALUES (1288, 'E13B699F-7D64-40F8-9CC2-7D8848E915FC', '360781', '瑞金市');
INSERT INTO `sys_region` VALUES (1289, '5D99C014-6321-4888-9C49-996D50C45024', '360800', '吉安市');
INSERT INTO `sys_region` VALUES (1290, 'C683C68D-7A9D-478D-A4D0-5D6347DD4609', '360802', '吉州区');
INSERT INTO `sys_region` VALUES (1291, '34AB767A-20C7-41DD-881D-9BC87A674B9A', '360803', '青原区');
INSERT INTO `sys_region` VALUES (1292, 'EBC44F1D-3222-48E5-9A0D-564595A80EBE', '360821', '吉安县');
INSERT INTO `sys_region` VALUES (1293, '206D9867-EA4E-4A7E-B774-E0137FB84009', '360822', '吉水县');
INSERT INTO `sys_region` VALUES (1294, '8F4DFA8E-C72F-4CDF-8AE8-97DB92D6018F', '360823', '峡江县');
INSERT INTO `sys_region` VALUES (1295, 'C95A5B59-498C-4035-9E46-DB30AB5343FF', '360824', '新干县');
INSERT INTO `sys_region` VALUES (1296, '8F02DAE8-1FF1-47D1-BAE8-5D1F88BEFF4B', '360825', '永丰县');
INSERT INTO `sys_region` VALUES (1297, 'FAF7E064-3AC6-4D13-BA50-B41FC96EE281', '360826', '泰和县');
INSERT INTO `sys_region` VALUES (1298, 'D34A8F61-CC46-4C12-BB77-9F337C5DC70C', '360827', '遂川县');
INSERT INTO `sys_region` VALUES (1299, 'F2022A58-0933-431C-9E63-051629416484', '360828', '万安县');
INSERT INTO `sys_region` VALUES (1300, '27B7699E-C55C-45C8-935B-B021BB131EFC', '360829', '安福县');
INSERT INTO `sys_region` VALUES (1301, '1CA3942B-9536-423D-AD03-BCD06FCB5B81', '360830', '永新县');
INSERT INTO `sys_region` VALUES (1302, '6028EFB0-B592-4515-8C4A-522EB657232B', '360881', '井冈山市');
INSERT INTO `sys_region` VALUES (1303, 'C3AFBD58-CA89-499F-856B-AAF5206649FE', '360900', '宜春市');
INSERT INTO `sys_region` VALUES (1304, '5CCCBA78-16DE-430C-BFAF-F9E54D52E586', '360902', '袁州区');
INSERT INTO `sys_region` VALUES (1305, '5E1DCD55-6126-4E72-9F7F-D63F65DF1B37', '360921', '奉新县');
INSERT INTO `sys_region` VALUES (1306, 'D0BB7E4B-3251-4890-8882-515A1FE216C7', '360922', '万载县');
INSERT INTO `sys_region` VALUES (1307, 'F7061359-2562-402B-B435-C358502F98BE', '360923', '上高县');
INSERT INTO `sys_region` VALUES (1308, 'F37D068D-F8DC-4C59-8F22-047D0BB36C08', '360924', '宜丰县');
INSERT INTO `sys_region` VALUES (1309, 'E81FAEF3-C3D8-400A-A44A-FBE8603E83C0', '360925', '靖安县');
INSERT INTO `sys_region` VALUES (1310, '1E3BE064-2ECC-4421-9F96-4AFC7856D691', '360926', '铜鼓县');
INSERT INTO `sys_region` VALUES (1311, '0036A9B3-01F4-4AC6-B466-530C71B344BC', '360981', '丰城市');
INSERT INTO `sys_region` VALUES (1312, '7BF5AB87-33F9-42C8-BC09-B527B283B296', '360982', '樟树市');
INSERT INTO `sys_region` VALUES (1313, '3102C3DC-7852-49B8-A8DA-6CFB2BCF0570', '360983', '高安市');
INSERT INTO `sys_region` VALUES (1314, 'ECD2C53B-827E-4262-BCB9-D1D953260B1A', '361000', '抚州市');
INSERT INTO `sys_region` VALUES (1315, '855713B6-AD05-4CC2-97D4-21F020743A32', '361002', '临川区');
INSERT INTO `sys_region` VALUES (1316, '78C65B7C-BB5D-434B-9EBE-FCD357589562', '361003', '东乡区');
INSERT INTO `sys_region` VALUES (1317, '4868B4ED-04E9-46E8-A636-FFDAB110BCDA', '361021', '南城县');
INSERT INTO `sys_region` VALUES (1318, 'B950A04F-FF59-40EC-AB66-B921237480DE', '361022', '黎川县');
INSERT INTO `sys_region` VALUES (1319, 'A256423A-C3A0-4CFB-AE45-DF0E676978C1', '361023', '南丰县');
INSERT INTO `sys_region` VALUES (1320, 'A966B64F-CAD4-4823-9321-89D55B39D63B', '361024', '崇仁县');
INSERT INTO `sys_region` VALUES (1321, 'D79C14DE-90A4-46C2-86E6-08D0B6A419C9', '361025', '乐安县');
INSERT INTO `sys_region` VALUES (1322, 'E5928E80-D5E8-42AB-9B70-21834A2F0E8A', '361026', '宜黄县');
INSERT INTO `sys_region` VALUES (1323, '01DD43FB-D1CA-4CBF-8810-0045BE532E26', '361027', '金溪县');
INSERT INTO `sys_region` VALUES (1324, '04AA1EF7-2822-404F-A4B0-BF7B413BE898', '361028', '资溪县');
INSERT INTO `sys_region` VALUES (1325, '145ACDDC-E626-4F4D-A25F-12D894BD4F9D', '361030', '广昌县');
INSERT INTO `sys_region` VALUES (1326, 'E973BDC4-0C05-4150-B90B-67324B322409', '361100', '上饶市');
INSERT INTO `sys_region` VALUES (1327, '77C7BCA3-F558-440C-82E6-C9984382E28B', '361102', '信州区');
INSERT INTO `sys_region` VALUES (1328, 'CE313D8C-7D05-42BE-8D94-7C5133645DD3', '361103', '广丰区');
INSERT INTO `sys_region` VALUES (1329, 'EDE8CAFF-E5D9-4EDA-ABE5-C3D87C7B2581', '361104', '广信区');
INSERT INTO `sys_region` VALUES (1330, '2849498C-4BFB-4A51-815D-5F1F6A4ED0F3', '361123', '玉山县');
INSERT INTO `sys_region` VALUES (1331, '32BD8AA4-9DA8-44A4-93ED-5C3155FFDDBA', '361124', '铅山县');
INSERT INTO `sys_region` VALUES (1332, '245D95CC-CACC-4D32-A445-8E8F328939C0', '361125', '横峰县');
INSERT INTO `sys_region` VALUES (1333, '738A8A08-FE96-49F1-AC83-1666068DDF93', '361126', '弋阳县');
INSERT INTO `sys_region` VALUES (1334, '1ED67C8D-0FD7-45F1-A505-D82459F53837', '361127', '余干县');
INSERT INTO `sys_region` VALUES (1335, 'E695C3C8-F484-467C-92C3-093CE69DB1C3', '361128', '鄱阳县');
INSERT INTO `sys_region` VALUES (1336, '4002E5A3-109C-4055-B1FE-7DBDB8AE4D1C', '361129', '万年县');
INSERT INTO `sys_region` VALUES (1337, '277D77D4-2CE5-468A-BD15-F5D7B17D4C77', '361130', '婺源县');
INSERT INTO `sys_region` VALUES (1338, '4CB0B445-9F15-4CEA-B155-C6E488AF7075', '361181', '德兴市');
INSERT INTO `sys_region` VALUES (1339, '99D0109A-66AD-4A39-B97B-803DFCFD7FE1', '370000', '山东省');
INSERT INTO `sys_region` VALUES (1340, 'B81E498F-892E-4898-BA28-C2C3CD59C876', '370100', '济南市');
INSERT INTO `sys_region` VALUES (1341, '110C0B22-B388-40AB-9F23-37EAB13B5976', '370102', '历下区');
INSERT INTO `sys_region` VALUES (1342, '02722ED5-4FB8-4AF4-8907-E60CDC0FB61E', '370103', '市中区');
INSERT INTO `sys_region` VALUES (1343, '393927F1-D28E-46DE-B27B-106A7EAE2B16', '370104', '槐荫区');
INSERT INTO `sys_region` VALUES (1344, '87AE9EA5-3D63-44DF-AA44-14D03F6CAD2D', '370105', '天桥区');
INSERT INTO `sys_region` VALUES (1345, '29FE90BE-F0F4-4E54-9363-3684506474E2', '370112', '历城区');
INSERT INTO `sys_region` VALUES (1346, '5038F6BD-264B-43E4-80D0-BEDBD1C23BB3', '370113', '长清区');
INSERT INTO `sys_region` VALUES (1347, '4CA37EED-70CC-4118-B1B1-BD3B4E93EB9A', '370114', '章丘区');
INSERT INTO `sys_region` VALUES (1348, '2DCEF119-2ADD-4AD2-9F6A-8CD7E9BE59ED', '370115', '济阳区');
INSERT INTO `sys_region` VALUES (1349, '5E79ED3E-D1E7-4580-9C9D-FB19D24A7183', '370116', '莱芜区');
INSERT INTO `sys_region` VALUES (1350, 'DAB4F976-DE60-4FA6-9503-6B1273DCABA9', '370117', '钢城区');
INSERT INTO `sys_region` VALUES (1351, 'D3A9B4E3-823B-45AD-BFD9-0F9D758D1E81', '370124', '平阴县');
INSERT INTO `sys_region` VALUES (1352, '857DA4B9-1A07-43D0-A6E6-D50D7364EE2F', '370126', '商河县');
INSERT INTO `sys_region` VALUES (1353, '7C8B62BE-8264-45B1-ABE5-4A434E7B22B5', '370200', '青岛市');
INSERT INTO `sys_region` VALUES (1354, 'F4B2130D-13F5-43AA-BBF5-08EC4B08BC63', '370202', '市南区');
INSERT INTO `sys_region` VALUES (1355, '3ABC4E45-2A84-4EB2-A814-7DB9A6BD3D76', '370203', '市北区');
INSERT INTO `sys_region` VALUES (1356, '2D969D91-37CA-4786-BDBE-644A5139AFE1', '370211', '黄岛区');
INSERT INTO `sys_region` VALUES (1357, '02AFCC58-9BE5-42CE-A0E6-C75963BB8066', '370212', '崂山区');
INSERT INTO `sys_region` VALUES (1358, '95BD3557-4F34-473E-9990-3E1BC61920FF', '370213', '李沧区');
INSERT INTO `sys_region` VALUES (1359, '51F2DF23-5101-4D99-ABF6-E6F96921D05D', '370214', '城阳区');
INSERT INTO `sys_region` VALUES (1360, 'D637957D-CD7E-4961-BDE7-71806EA3AEDD', '370215', '即墨区');
INSERT INTO `sys_region` VALUES (1361, 'BEFE542A-6474-46AB-8088-A304BDF1885F', '370281', '胶州市');
INSERT INTO `sys_region` VALUES (1362, '97A90AA0-3773-4106-93CE-D3B4ACD1878F', '370283', '平度市');
INSERT INTO `sys_region` VALUES (1363, 'E144BAA5-D971-4F10-85F2-741BAF2FAEA6', '370285', '莱西市');
INSERT INTO `sys_region` VALUES (1364, '7748CB2E-9179-48FB-96AF-E98CE45046C5', '370300', '淄博市');
INSERT INTO `sys_region` VALUES (1365, '8DC3EDAC-97B5-4253-8988-AF625B674D2D', '370302', '淄川区');
INSERT INTO `sys_region` VALUES (1366, '9037EE20-EA2E-4A9B-B35A-BE0ED82D80CC', '370303', '张店区');
INSERT INTO `sys_region` VALUES (1367, '05ECF4E2-F5DE-4874-BBD5-399EEB59A7FC', '370304', '博山区');
INSERT INTO `sys_region` VALUES (1368, '5F85D1EF-E6DF-455D-BE5E-99035A9CE2E0', '370305', '临淄区');
INSERT INTO `sys_region` VALUES (1369, '61CBE2B5-9B8B-43A3-824A-39431D9CF616', '370306', '周村区');
INSERT INTO `sys_region` VALUES (1370, '78D19AC8-6CEA-4272-AD98-2ECF55B944AB', '370321', '桓台县');
INSERT INTO `sys_region` VALUES (1371, '734CCE0C-0693-470B-ACEF-3E15E69B512D', '370322', '高青县');
INSERT INTO `sys_region` VALUES (1372, 'C70368F9-4793-457A-9AAE-F5CF40148FBC', '370323', '沂源县');
INSERT INTO `sys_region` VALUES (1373, '37F81FA1-9C27-48E3-B6B7-770EBFF69143', '370400', '枣庄市');
INSERT INTO `sys_region` VALUES (1374, 'C76128F4-5C80-4326-B83F-F07980FB18E0', '370402', '市中区');
INSERT INTO `sys_region` VALUES (1375, '8B1056B0-7591-43B6-BA59-E99F6C222403', '370403', '薛城区');
INSERT INTO `sys_region` VALUES (1376, '659C7512-A0F0-4E53-9D97-FC9614E0F7E2', '370404', '峄城区');
INSERT INTO `sys_region` VALUES (1377, '7B8BFC04-19DA-4615-980C-A15B0B17F743', '370405', '台儿庄区');
INSERT INTO `sys_region` VALUES (1378, '6CDB52CA-26A9-4C48-A0D4-79ADF15A07AB', '370406', '山亭区');
INSERT INTO `sys_region` VALUES (1379, '88DBF0F9-6CA2-462F-A4F4-E5F6AAC3AE12', '370481', '滕州市');
INSERT INTO `sys_region` VALUES (1380, '291842B1-0185-4F53-8BB9-D579E607614F', '370500', '东营市');
INSERT INTO `sys_region` VALUES (1381, '8EEA80CD-5B0F-4761-8CE8-C76887F6092F', '370502', '东营区');
INSERT INTO `sys_region` VALUES (1382, '66C230FF-DBE0-4C5A-893F-D34B1B7F64F9', '370503', '河口区');
INSERT INTO `sys_region` VALUES (1383, 'FE7E177B-E171-4B03-81D3-AC1AB244450A', '370505', '垦利区');
INSERT INTO `sys_region` VALUES (1384, 'EF41B16B-BA4B-4305-8F9A-B43EEB4F181C', '370522', '利津县');
INSERT INTO `sys_region` VALUES (1385, '96007C26-44E3-4119-AD32-895CBEB3E3D9', '370523', '广饶县');
INSERT INTO `sys_region` VALUES (1386, '013BC9CF-A192-4B3D-B367-38ABABEF97D4', '370600', '烟台市');
INSERT INTO `sys_region` VALUES (1387, 'FD81444A-D5DC-4D52-8075-F8AA5AB9BADD', '370602', '芝罘区');
INSERT INTO `sys_region` VALUES (1388, '7F8A14D0-1275-4E99-AAA2-92A65C08EF8A', '370611', '福山区');
INSERT INTO `sys_region` VALUES (1389, '1C5355B3-A961-437C-BA95-0E5F098658D4', '370612', '牟平区');
INSERT INTO `sys_region` VALUES (1390, 'AA1BBD7D-EEDF-41BF-9561-9B2DB1F5F35E', '370613', '莱山区');
INSERT INTO `sys_region` VALUES (1391, '87D65C8E-61C6-4D8F-995F-31E19B86574A', '370614', '蓬莱区');
INSERT INTO `sys_region` VALUES (1392, 'B3F7C1B2-31E7-4356-8754-47B7E9A4849B', '370681', '龙口市');
INSERT INTO `sys_region` VALUES (1393, 'E313C859-06A7-4301-84F2-7CE08ECF9786', '370682', '莱阳市');
INSERT INTO `sys_region` VALUES (1394, '9F3755AF-993C-40EF-B145-4BFD7366B4A3', '370683', '莱州市');
INSERT INTO `sys_region` VALUES (1395, '8CCC4C79-20F0-41ED-BB51-CE07F0661D21', '370684', '蓬莱市');
INSERT INTO `sys_region` VALUES (1396, '4E4D49EF-723D-4A9F-B506-4404C380A3CE', '370685', '招远市');
INSERT INTO `sys_region` VALUES (1397, '418D81EA-1DFF-4BE8-9EA6-0B82AB806DBC', '370686', '栖霞市');
INSERT INTO `sys_region` VALUES (1398, '4F2AFA89-1239-40F1-A65C-6049DA366BEE', '370687', '海阳市');
INSERT INTO `sys_region` VALUES (1399, 'C50AB060-6168-444E-ABA3-75C0465CADD7', '370700', '潍坊市');
INSERT INTO `sys_region` VALUES (1400, '8FFE2569-ED2B-44DB-8D91-5AEC0045AEC3', '370702', '潍城区');
INSERT INTO `sys_region` VALUES (1401, 'A59181DC-A067-4FC5-935E-6AF5A820305B', '370703', '寒亭区');
INSERT INTO `sys_region` VALUES (1402, '0D5A03FB-C2BE-4143-87F0-61B1357E9B44', '370704', '坊子区');
INSERT INTO `sys_region` VALUES (1403, '577F265D-CC2A-4CCD-B99A-0B9403274EA1', '370705', '奎文区');
INSERT INTO `sys_region` VALUES (1404, '855B1A1A-56D2-42AB-959B-4F759D56AF85', '370724', '临朐县');
INSERT INTO `sys_region` VALUES (1405, '704C032E-FBB2-4BFF-8C33-B202BE6E5616', '370725', '昌乐县');
INSERT INTO `sys_region` VALUES (1406, '47D0CA68-696C-4AAD-8914-5FA3D9C6B73B', '370781', '青州市');
INSERT INTO `sys_region` VALUES (1407, 'A276FA95-5AD0-4D5D-A2ED-3317797046D6', '370782', '诸城市');
INSERT INTO `sys_region` VALUES (1408, 'A97C57BB-1090-40C7-925D-A30EC06A20C4', '370783', '寿光市');
INSERT INTO `sys_region` VALUES (1409, 'E444750E-336E-4FA8-ABCD-6ABB34AD7BC5', '370784', '安丘市');
INSERT INTO `sys_region` VALUES (1410, '619A7DC8-24D9-4746-84B8-75C3278CB675', '370785', '高密市');
INSERT INTO `sys_region` VALUES (1411, '696A4D25-7CF9-40DD-BF08-2A74D0FCFE17', '370786', '昌邑市');
INSERT INTO `sys_region` VALUES (1412, 'F6634DAC-94C7-4E39-B776-A9673079979C', '370800', '济宁市');
INSERT INTO `sys_region` VALUES (1413, '221AB24E-431F-4AB9-9181-81A95D00B0EF', '370811', '任城区');
INSERT INTO `sys_region` VALUES (1414, '3F30EDDB-49DB-4403-B026-D453F528E8E8', '370812', '兖州区');
INSERT INTO `sys_region` VALUES (1415, 'F3655A62-426F-472C-8FDB-07D6EFF1CACA', '370826', '微山县');
INSERT INTO `sys_region` VALUES (1416, 'BE0D9FB0-8E74-4B24-8C42-663C28EFB3B2', '370827', '鱼台县');
INSERT INTO `sys_region` VALUES (1417, '8B5B41F6-C69D-49AC-AF0B-9D1CFF895914', '370828', '金乡县');
INSERT INTO `sys_region` VALUES (1418, '0153C7E3-4027-4640-B885-C47499E8E534', '370829', '嘉祥县');
INSERT INTO `sys_region` VALUES (1419, '41D87D5D-5010-439E-8937-069FEF460891', '370830', '汶上县');
INSERT INTO `sys_region` VALUES (1420, 'E255D928-3768-4044-A336-1AC2EBA39962', '370831', '泗水县');
INSERT INTO `sys_region` VALUES (1421, '1EB91CFC-6B15-4AEB-BA59-A31BF58FF74B', '370832', '梁山县');
INSERT INTO `sys_region` VALUES (1422, '2E714A67-0164-469D-A052-C5BEB1708315', '370881', '曲阜市');
INSERT INTO `sys_region` VALUES (1423, '5C995ABE-0680-4957-AA59-CA8793E7B26B', '370883', '邹城市');
INSERT INTO `sys_region` VALUES (1424, '8F3605DF-E5FA-498F-B52F-57E9CE3BFB83', '370900', '泰安市');
INSERT INTO `sys_region` VALUES (1425, 'FDE0EF6D-C77F-4E58-AD03-9C08A40D0E72', '370902', '泰山区');
INSERT INTO `sys_region` VALUES (1426, '6E03DFD6-5A5C-4FA4-B46F-5A4169FF6D16', '370911', '岱岳区');
INSERT INTO `sys_region` VALUES (1427, 'FB1685C4-06D1-4A69-9C68-70B0961320FC', '370921', '宁阳县');
INSERT INTO `sys_region` VALUES (1428, 'A02C5F5D-2FC8-4DB7-A9AD-BE87C2774B36', '370923', '东平县');
INSERT INTO `sys_region` VALUES (1429, '2972F1A1-9090-4691-B595-3A6775C78472', '370982', '新泰市');
INSERT INTO `sys_region` VALUES (1430, '58A9411D-AF42-408C-B04E-58642FE7A661', '370983', '肥城市');
INSERT INTO `sys_region` VALUES (1431, 'C8643177-6528-4983-A30D-C0CA60A582DF', '371000', '威海市');
INSERT INTO `sys_region` VALUES (1432, '8A973DDF-FDAD-4F88-B421-299523FB12A8', '371002', '环翠区');
INSERT INTO `sys_region` VALUES (1433, '1B3AA759-06F1-4D0B-B34F-761CB72E46A9', '371003', '文登区');
INSERT INTO `sys_region` VALUES (1434, '34389E83-BAF1-4EEE-9F00-6467F337218C', '371082', '荣成市');
INSERT INTO `sys_region` VALUES (1435, 'D9440F2B-C88F-4800-8313-3211AC1E2821', '371083', '乳山市');
INSERT INTO `sys_region` VALUES (1436, '20B58631-32E1-4651-B897-34FA99225987', '371100', '日照市');
INSERT INTO `sys_region` VALUES (1437, 'AA6A9767-2C20-47B3-9064-04887FF6E999', '371102', '东港区');
INSERT INTO `sys_region` VALUES (1438, '41562271-D766-464E-9387-AD6CEC578F80', '371103', '岚山区');
INSERT INTO `sys_region` VALUES (1439, '4DEB30D1-C3C1-4CDA-8E8C-2145C164BB38', '371121', '五莲县');
INSERT INTO `sys_region` VALUES (1440, 'B705B170-2AFB-4BE8-B151-4D4DC8ECB23F', '371122', '莒县');
INSERT INTO `sys_region` VALUES (1441, '45F8BE19-D658-4233-AFE0-D9555CBF8143', '371300', '临沂市');
INSERT INTO `sys_region` VALUES (1442, 'E4180F6F-C74E-4C1F-9925-128B407D2977', '371302', '兰山区');
INSERT INTO `sys_region` VALUES (1443, '1E92BC92-CF51-43C2-80FE-4303C57945BF', '371311', '罗庄区');
INSERT INTO `sys_region` VALUES (1444, '6B7048AE-095A-4BF1-BF3D-EBD598314CE0', '371312', '河东区');
INSERT INTO `sys_region` VALUES (1445, '96624769-3F79-48BC-BF0E-3FA64FBC41E9', '371321', '沂南县');
INSERT INTO `sys_region` VALUES (1446, 'FD2ECD08-F47D-4362-ABA6-CF7831F7D2CF', '371322', '郯城县');
INSERT INTO `sys_region` VALUES (1447, '602BE34A-A716-41AB-B88C-BCA44B3CD7E1', '371323', '沂水县');
INSERT INTO `sys_region` VALUES (1448, '6A61826A-E848-432B-93BC-B2DE564000BD', '371324', '兰陵县');
INSERT INTO `sys_region` VALUES (1449, 'D8098110-4566-433B-AB96-C91FF123D731', '371325', '费县');
INSERT INTO `sys_region` VALUES (1450, 'FE9A8F64-EBAE-4793-B294-C3FE187A36C9', '371326', '平邑县');
INSERT INTO `sys_region` VALUES (1451, 'A58ECDC5-800F-43AA-AA9C-4BB62EF15C60', '371327', '莒南县');
INSERT INTO `sys_region` VALUES (1452, '371E9D20-9317-43E1-B497-BD6A28392246', '371328', '蒙阴县');
INSERT INTO `sys_region` VALUES (1453, '9EFF3E70-C878-4AA4-AB32-8B6477806DAB', '371329', '临沭县');
INSERT INTO `sys_region` VALUES (1454, '951F4F90-A3E3-4FC4-AF0E-1DEA53C274E3', '371400', '德州市');
INSERT INTO `sys_region` VALUES (1455, '7B8CFBA1-A989-49A9-BA93-E3C7809AEFAD', '371402', '德城区');
INSERT INTO `sys_region` VALUES (1456, 'A5E3C3C7-EA51-4726-B51C-78C86160A3FA', '371403', '陵城区');
INSERT INTO `sys_region` VALUES (1457, '91AC55B0-B62A-4FC8-9CA2-5BA57301F4FB', '371422', '宁津县');
INSERT INTO `sys_region` VALUES (1458, '6BB22D9C-8F6D-459E-91EE-13E4A118922C', '371423', '庆云县');
INSERT INTO `sys_region` VALUES (1459, 'CB39B21D-759F-47E7-AC2C-3DDB61083685', '371424', '临邑县');
INSERT INTO `sys_region` VALUES (1460, '43CCAD99-88A9-438D-A3FC-0315F8C6921B', '371425', '齐河县');
INSERT INTO `sys_region` VALUES (1461, '6A06E052-E830-4FD9-8138-BCD8C597BB36', '371426', '平原县');
INSERT INTO `sys_region` VALUES (1462, '575DD568-CC09-498C-948E-63DCE2CB244A', '371427', '夏津县');
INSERT INTO `sys_region` VALUES (1463, '6DC909D3-8CDE-49CF-B504-24DF7F6F2193', '371428', '武城县');
INSERT INTO `sys_region` VALUES (1464, 'FF776DDE-04E1-43BA-8F63-207C687ADAAA', '371481', '乐陵市');
INSERT INTO `sys_region` VALUES (1465, 'A3130820-62B1-47CB-8C81-77C29418F582', '371482', '禹城市');
INSERT INTO `sys_region` VALUES (1466, '70C07FAD-C82D-474E-BA61-3EC40622C917', '371500', '聊城市');
INSERT INTO `sys_region` VALUES (1467, '4EC40A24-021C-43F2-8E2D-B4D5E78575D9', '371502', '东昌府区');
INSERT INTO `sys_region` VALUES (1468, 'CC5AF6E2-8665-40BB-9FCB-5D4CAD13DDFF', '371503', '茌平区');
INSERT INTO `sys_region` VALUES (1469, '49EB5C18-CEE8-4877-89A9-873D0EAAB69D', '371521', '阳谷县');
INSERT INTO `sys_region` VALUES (1470, '7360AFFA-9831-41C4-AEAA-ABD21F653A94', '371522', '莘县');
INSERT INTO `sys_region` VALUES (1471, '148BD25B-59C2-4C48-A222-B8F56579660B', '371524', '东阿县');
INSERT INTO `sys_region` VALUES (1472, 'E4749D8D-4ACE-4B1A-8AE4-B3ACFBBA33E7', '371525', '冠县');
INSERT INTO `sys_region` VALUES (1473, 'C4237833-7CB7-46FC-AE45-E269591BE604', '371526', '高唐县');
INSERT INTO `sys_region` VALUES (1474, 'C5B80F6B-7052-451F-9527-9DFD3D1B6A28', '371581', '临清市');
INSERT INTO `sys_region` VALUES (1475, '6FD50524-8A1C-4346-B853-A78B5446E309', '371600', '滨州市');
INSERT INTO `sys_region` VALUES (1476, 'BD7027CA-605F-4BCD-BF04-D160FA1232D4', '371602', '滨城区');
INSERT INTO `sys_region` VALUES (1477, '767E6990-6F65-429D-92B9-0479555A80A9', '371603', '沾化区');
INSERT INTO `sys_region` VALUES (1478, 'DF8C86AC-BB7D-440A-BF62-613D6A3210D3', '371621', '惠民县');
INSERT INTO `sys_region` VALUES (1479, 'C4476285-B5AB-480C-89CF-5C8CFCADACB7', '371622', '阳信县');
INSERT INTO `sys_region` VALUES (1480, 'C35BC025-1BAB-4AD4-B097-16DE0520DD3B', '371623', '无棣县');
INSERT INTO `sys_region` VALUES (1481, 'B50267FB-6124-400F-9118-D4C3953160B9', '371625', '博兴县');
INSERT INTO `sys_region` VALUES (1482, '67045245-C6E4-4FE4-B70D-4E01AA453C59', '371681', '邹平市');
INSERT INTO `sys_region` VALUES (1483, 'EFF33DE2-E7DF-4A68-87F1-B066CB4814F4', '371700', '菏泽市');
INSERT INTO `sys_region` VALUES (1484, 'BE985296-CC6A-4C94-ACAE-11C2C8114D40', '371702', '牡丹区');
INSERT INTO `sys_region` VALUES (1485, 'C88E38E1-88B0-4E53-9F9F-AA6B85FF5BE7', '371703', '定陶区');
INSERT INTO `sys_region` VALUES (1486, '613CA001-D8FC-44AA-843E-3009CC1F9B0D', '371721', '曹县');
INSERT INTO `sys_region` VALUES (1487, '45F32BD7-D699-4110-BFD4-3A9C59B210C0', '371722', '单县');
INSERT INTO `sys_region` VALUES (1488, '6E2B458D-FEBC-4558-9CEE-CA3D9989188A', '371723', '成武县');
INSERT INTO `sys_region` VALUES (1489, '652AB473-AE8D-4949-8100-20E0385BD65B', '371724', '巨野县');
INSERT INTO `sys_region` VALUES (1490, '81DC44EF-6931-4B53-BAB8-063DBD12CA3F', '371725', '郓城县');
INSERT INTO `sys_region` VALUES (1491, 'F2F16C80-9EFF-4364-A7BB-2A75760C82D4', '371726', '鄄城县');
INSERT INTO `sys_region` VALUES (1492, 'C7D74882-163D-4F88-9BCE-38FA9EBAD02A', '371728', '东明县');
INSERT INTO `sys_region` VALUES (1493, 'D2D327A4-51F4-41CD-A8BC-54C8499FBBFA', '410000', '河南省');
INSERT INTO `sys_region` VALUES (1494, '06A7E182-7834-49D3-97C4-F5A6B14CCD9D', '410100', '郑州市');
INSERT INTO `sys_region` VALUES (1495, 'BBA842FA-6298-48B4-A761-12BA014BA742', '410102', '中原区');
INSERT INTO `sys_region` VALUES (1496, 'EC48F22C-62FD-4054-8CF4-B3BF58B594B2', '410103', '二七区');
INSERT INTO `sys_region` VALUES (1497, '1901B437-815E-4612-A89D-6318D6B2AB4B', '410104', '管城回族区');
INSERT INTO `sys_region` VALUES (1498, '180C2F80-5899-4052-A7C7-0A0214B6FA75', '410105', '金水区');
INSERT INTO `sys_region` VALUES (1499, '2101A3B4-812F-4615-AAD8-6C13C7160285', '410106', '上街区');
INSERT INTO `sys_region` VALUES (1500, '32612314-CE7F-4D7D-97E5-1CDF4A78623C', '410108', '惠济区');
INSERT INTO `sys_region` VALUES (1501, 'D03B1EB3-DF64-437C-B585-96CF608D3BDC', '410122', '中牟县');
INSERT INTO `sys_region` VALUES (1502, '6DBD79CF-C894-44E5-A4A2-12C7F191015D', '410181', '巩义市');
INSERT INTO `sys_region` VALUES (1503, 'A38FDE26-DCFB-4394-AFD5-57FC89378A4F', '410182', '荥阳市');
INSERT INTO `sys_region` VALUES (1504, 'F2C924AA-B242-4CED-AC33-00D9157D6E97', '410183', '新密市');
INSERT INTO `sys_region` VALUES (1505, '360D9698-1D7D-4535-B00B-593F7043406C', '410184', '新郑市');
INSERT INTO `sys_region` VALUES (1506, '49E7EA39-8087-44DF-BE17-6BBDD13AAC6D', '410185', '登封市');
INSERT INTO `sys_region` VALUES (1507, 'F993A0D5-9AF1-4704-B0E0-68C35F2FE43E', '410200', '开封市');
INSERT INTO `sys_region` VALUES (1508, '92C96419-C587-4D21-A1D6-6C26067B6A48', '410202', '龙亭区');
INSERT INTO `sys_region` VALUES (1509, '6CC06E6D-558F-48E7-8802-BA7430B3AD63', '410203', '顺河回族区');
INSERT INTO `sys_region` VALUES (1510, 'DFBCCB51-D9FB-4214-B094-E22B97099E75', '410204', '鼓楼区');
INSERT INTO `sys_region` VALUES (1511, 'C31AE73F-5150-4C77-95F5-FE412433E094', '410205', '禹王台区');
INSERT INTO `sys_region` VALUES (1512, 'E3054E10-3AF3-4D0A-96D0-CB16487D288C', '410212', '祥符区');
INSERT INTO `sys_region` VALUES (1513, '154BFC96-D2A5-4A09-8DE8-CE8775727705', '410221', '杞县');
INSERT INTO `sys_region` VALUES (1514, '29ED5E02-0241-414C-A57C-848EE5B5042F', '410222', '通许县');
INSERT INTO `sys_region` VALUES (1515, '95897739-DD7F-49B0-AB47-FAB0AF601660', '410223', '尉氏县');
INSERT INTO `sys_region` VALUES (1516, '9F98C98D-1545-4883-9F7A-4FB8BBE9230C', '410225', '兰考县');
INSERT INTO `sys_region` VALUES (1517, '03414233-0902-445E-A065-03C130DB8544', '410300', '洛阳市');
INSERT INTO `sys_region` VALUES (1518, '5F0422AC-2E9E-4BA7-9C8D-B4430F74A948', '410302', '老城区');
INSERT INTO `sys_region` VALUES (1519, '36A50290-119B-463D-ADA6-06A551D76B41', '410303', '西工区');
INSERT INTO `sys_region` VALUES (1520, '21A480A6-2A1E-41C6-A3ED-8039ADBE48EA', '410304', '瀍河回族区');
INSERT INTO `sys_region` VALUES (1521, '3B3229B2-7DAE-4310-B5BF-8BA3F26D3992', '410305', '涧西区');
INSERT INTO `sys_region` VALUES (1522, '1E74B596-2A0A-4226-812B-2D212894A410', '410306', '吉利区');
INSERT INTO `sys_region` VALUES (1523, '7883A0AA-CD49-4E4F-9F55-9AC4705FFF67', '410311', '洛龙区');
INSERT INTO `sys_region` VALUES (1524, 'F4CEAFEC-7F6F-4A25-ADC8-D59F06BD3C42', '410322', '孟津县');
INSERT INTO `sys_region` VALUES (1525, '94BD18B8-E0D8-4B84-BB3B-8ABB3229E02F', '410323', '新安县');
INSERT INTO `sys_region` VALUES (1526, '950F582A-92B3-4936-9954-A3E70967DC0B', '410324', '栾川县');
INSERT INTO `sys_region` VALUES (1527, '73013400-6F24-4CFB-885A-E62F73DC88FE', '410325', '嵩县');
INSERT INTO `sys_region` VALUES (1528, 'DC18B00D-3407-4D9C-8688-E0E6678F90F6', '410326', '汝阳县');
INSERT INTO `sys_region` VALUES (1529, 'C156ACAF-9934-468C-AD44-9D209DC08420', '410327', '宜阳县');
INSERT INTO `sys_region` VALUES (1530, 'AE947615-45B4-4573-B6C1-F656FA1DFADC', '410328', '洛宁县');
INSERT INTO `sys_region` VALUES (1531, 'EDFB7CD0-3E28-4B1F-9962-9BCD8C928254', '410329', '伊川县');
INSERT INTO `sys_region` VALUES (1532, '28C57668-6308-4257-93FD-649F63577E14', '410381', '偃师市');
INSERT INTO `sys_region` VALUES (1533, '4902C3B6-16D2-4D5D-910B-610C654453D3', '410400', '平顶山市');
INSERT INTO `sys_region` VALUES (1534, 'F5A75A86-BEEE-4F1F-8D0F-2DB2B1E76F46', '410402', '新华区');
INSERT INTO `sys_region` VALUES (1535, 'D40C4E26-8EF5-4F61-841F-F4740EBFA50D', '410403', '卫东区');
INSERT INTO `sys_region` VALUES (1536, 'C7385CA2-8BD5-4E3E-8350-1ACC21460BFD', '410404', '石龙区');
INSERT INTO `sys_region` VALUES (1537, '7A2DE5B4-6C7A-4A3E-A664-005600F4B06D', '410411', '湛河区');
INSERT INTO `sys_region` VALUES (1538, 'E3793F5A-2195-4BDE-BA3B-DA93B9708EDF', '410421', '宝丰县');
INSERT INTO `sys_region` VALUES (1539, '62DAB22D-D6A6-4CA7-8523-371678365916', '410422', '叶县');
INSERT INTO `sys_region` VALUES (1540, 'FBAA5837-0C79-4D8E-B73F-3AB096C5DC47', '410423', '鲁山县');
INSERT INTO `sys_region` VALUES (1541, '13AD146E-A47F-4054-B633-8C01E314B47A', '410425', '郏县');
INSERT INTO `sys_region` VALUES (1542, '5497106F-BF84-4270-8A64-359BC0E4832D', '410481', '舞钢市');
INSERT INTO `sys_region` VALUES (1543, '4786F15D-BD11-4CA6-87B9-4E382FA3CC75', '410482', '汝州市');
INSERT INTO `sys_region` VALUES (1544, '214ACCA0-7E07-4460-9EAC-5064869AA7E1', '410500', '安阳市');
INSERT INTO `sys_region` VALUES (1545, '8977BD2F-6AA1-41D5-9CEE-47B2D683A58D', '410502', '文峰区');
INSERT INTO `sys_region` VALUES (1546, '0CE694F0-2E3A-454B-9C44-357669CADD2C', '410503', '北关区');
INSERT INTO `sys_region` VALUES (1547, 'C58A1BF1-C2B3-4E04-ABB0-79C7E7A1DDF8', '410505', '殷都区');
INSERT INTO `sys_region` VALUES (1548, '67531081-011C-46ED-A333-D94A3C87D78C', '410506', '龙安区');
INSERT INTO `sys_region` VALUES (1549, 'CC209BE8-83DF-40A9-8429-319D95D24861', '410522', '安阳县');
INSERT INTO `sys_region` VALUES (1550, '3E8F7C27-0997-4ADA-9E4E-5958BD0FF284', '410523', '汤阴县');
INSERT INTO `sys_region` VALUES (1551, 'A456614F-1A07-4177-B276-8D7A9F716A0D', '410526', '滑县');
INSERT INTO `sys_region` VALUES (1552, '60E67ED4-C632-458E-AE6B-D9ABA0DF4016', '410527', '内黄县');
INSERT INTO `sys_region` VALUES (1553, '86755898-7BF0-489D-AC64-4906E7AECA22', '410581', '林州市');
INSERT INTO `sys_region` VALUES (1554, 'DC822C38-B0ED-413E-BC2A-FE8A897FC01B', '410600', '鹤壁市');
INSERT INTO `sys_region` VALUES (1555, '5FBF5CAB-55EE-4C51-BE84-AC7641ED37B3', '410602', '鹤山区');
INSERT INTO `sys_region` VALUES (1556, 'CF256B9B-DC04-461E-AF19-8330F46AA16D', '410603', '山城区');
INSERT INTO `sys_region` VALUES (1557, '3149A601-D967-4A54-9FC5-ECB1E579C53B', '410611', '淇滨区');
INSERT INTO `sys_region` VALUES (1558, 'A05016B6-0BAD-4BD9-9D41-9DDCEE866260', '410621', '浚县');
INSERT INTO `sys_region` VALUES (1559, '21603B0F-BA87-4AD7-8104-52FF4E80DE0B', '410622', '淇县');
INSERT INTO `sys_region` VALUES (1560, 'A27E177A-E90A-4FD7-8E64-335F5D0BE433', '410700', '新乡市');
INSERT INTO `sys_region` VALUES (1561, '5A4F6E02-385E-4520-BE02-623F9DE1B37F', '410702', '红旗区');
INSERT INTO `sys_region` VALUES (1562, 'C50544B3-0C4F-41FB-8741-FF8E1CD625C2', '410703', '卫滨区');
INSERT INTO `sys_region` VALUES (1563, '98AE4B4F-D546-426F-A437-F091B9C6D69F', '410704', '凤泉区');
INSERT INTO `sys_region` VALUES (1564, '812F3746-64AC-4973-BEC4-9E40619A2B74', '410711', '牧野区');
INSERT INTO `sys_region` VALUES (1565, '26BC19AA-A938-43BA-B8A1-438EE09F1FD6', '410721', '新乡县');
INSERT INTO `sys_region` VALUES (1566, 'B807365A-F88E-4AD2-8C90-57EF3A91B3F1', '410724', '获嘉县');
INSERT INTO `sys_region` VALUES (1567, 'C1F31812-2FE0-4EC9-BE58-AA834F9C3505', '410725', '原阳县');
INSERT INTO `sys_region` VALUES (1568, 'CC6A8590-4BEC-4BB0-AD5F-BCBDABA9FCAE', '410726', '延津县');
INSERT INTO `sys_region` VALUES (1569, '44769B56-80FD-43AB-8094-3628DB80AF95', '410727', '封丘县');
INSERT INTO `sys_region` VALUES (1570, '2855CF78-C5D3-460D-B42A-FD42937BFE69', '410781', '卫辉市');
INSERT INTO `sys_region` VALUES (1571, '76572D79-AF49-4458-96AA-C0E9DDAFB02F', '410782', '辉县市');
INSERT INTO `sys_region` VALUES (1572, '0D3380F6-8FD0-47A4-8DD4-B0BFFF5ABE91', '410783', '长垣市');
INSERT INTO `sys_region` VALUES (1573, '494556DE-A849-4361-BE11-774566BFDA9A', '410800', '焦作市');
INSERT INTO `sys_region` VALUES (1574, '59072237-D7FE-428A-94A8-6DB7FB32314B', '410802', '解放区');
INSERT INTO `sys_region` VALUES (1575, '6E2D674D-3CDA-4C6C-9A7F-DCFA6BD5CCE6', '410803', '中站区');
INSERT INTO `sys_region` VALUES (1576, '99F877A5-14E1-48FB-9C0B-F0501ADAAF16', '410804', '马村区');
INSERT INTO `sys_region` VALUES (1577, '14DFA263-D16D-40D3-90F4-ADBCD86DBC12', '410811', '山阳区');
INSERT INTO `sys_region` VALUES (1578, '82DAE48A-E446-4903-AA4C-C37325AA8C81', '410821', '修武县');
INSERT INTO `sys_region` VALUES (1579, '3C800351-DE65-4691-B5E8-24375990E5FB', '410822', '博爱县');
INSERT INTO `sys_region` VALUES (1580, '256F88A2-108A-4480-BEEC-31C631A784D9', '410823', '武陟县');
INSERT INTO `sys_region` VALUES (1581, '46622461-F93B-4225-AC08-84E7C4151054', '410825', '温县');
INSERT INTO `sys_region` VALUES (1582, '99DA6CBF-C33B-49AC-8D2D-AAAA1B9BD374', '410882', '沁阳市');
INSERT INTO `sys_region` VALUES (1583, '8F604883-14E9-43D4-946F-AB61156AE223', '410883', '孟州市');
INSERT INTO `sys_region` VALUES (1584, '9B05574C-EF84-4384-8084-07EEC334CC72', '410900', '濮阳市');
INSERT INTO `sys_region` VALUES (1585, 'D4006C2B-A857-487C-BE73-12961D6EDAF0', '410902', '华龙区');
INSERT INTO `sys_region` VALUES (1586, '884B03FD-6C7E-4E8D-BDD8-F9D6D8793E1F', '410922', '清丰县');
INSERT INTO `sys_region` VALUES (1587, '61B27CCD-0748-4EA2-B7C7-98BB837E7285', '410923', '南乐县');
INSERT INTO `sys_region` VALUES (1588, 'E3DB8C09-F0BB-452B-8AA9-19C8CFF40D5A', '410926', '范县');
INSERT INTO `sys_region` VALUES (1589, '3229BA97-BEB8-4696-A719-8C50FE3C87DF', '410927', '台前县');
INSERT INTO `sys_region` VALUES (1590, '4E76CF07-0B14-4FB4-A8D4-DA9C724F7790', '410928', '濮阳县');
INSERT INTO `sys_region` VALUES (1591, '96999017-A073-4B40-856D-A0520C069329', '411000', '许昌市');
INSERT INTO `sys_region` VALUES (1592, 'D2F2586B-A00E-41A0-BF0E-1237364F42C7', '411002', '魏都区');
INSERT INTO `sys_region` VALUES (1593, '774CB0FD-D63E-403E-BF62-416F3D0920CB', '411003', '建安区');
INSERT INTO `sys_region` VALUES (1594, '6F43DCAA-25E4-4C8B-B66B-C31642F8FB39', '411024', '鄢陵县');
INSERT INTO `sys_region` VALUES (1595, 'E237702E-A075-40C1-9845-397C2465BBDD', '411025', '襄城县');
INSERT INTO `sys_region` VALUES (1596, 'BB4FF853-90BD-476B-BCA7-81C18DF89402', '411081', '禹州市');
INSERT INTO `sys_region` VALUES (1597, '76846179-D450-496D-8892-0D2E4FCEEEFD', '411082', '长葛市');
INSERT INTO `sys_region` VALUES (1598, 'A4B338A1-AF63-4D18-85A6-6749E3DCD295', '411100', '漯河市');
INSERT INTO `sys_region` VALUES (1599, '2240E497-A1C2-4DE2-B3D4-216917F08970', '411102', '源汇区');
INSERT INTO `sys_region` VALUES (1600, 'F5BE9260-1C7F-4546-9DB5-2C582A81A5DF', '411103', '郾城区');
INSERT INTO `sys_region` VALUES (1601, 'D3DA3C45-908E-4544-AFCB-17179ED544E6', '411104', '召陵区');
INSERT INTO `sys_region` VALUES (1602, '1175E563-CFEB-480D-94CB-6D6E4F13BCF7', '411121', '舞阳县');
INSERT INTO `sys_region` VALUES (1603, '365FAE9D-12D2-4988-A034-AD2CC7E58A8F', '411122', '临颍县');
INSERT INTO `sys_region` VALUES (1604, '67934BC1-4918-4762-8393-C8B4E365E867', '411200', '三门峡市');
INSERT INTO `sys_region` VALUES (1605, 'CF730EF3-C292-438C-8673-AB21CED1F018', '411202', '湖滨区');
INSERT INTO `sys_region` VALUES (1606, 'A532A7B3-45EB-46BE-BB94-497B8B4F9E4B', '411203', '陕州区');
INSERT INTO `sys_region` VALUES (1607, '66B8047F-31F3-4B73-83AB-34A5DF455C5C', '411221', '渑池县');
INSERT INTO `sys_region` VALUES (1608, '67058F85-C063-42E9-A21E-C92F83D1DCBD', '411224', '卢氏县');
INSERT INTO `sys_region` VALUES (1609, '61B8B6C3-C15F-4BD2-99D9-B0E7A53F9067', '411281', '义马市');
INSERT INTO `sys_region` VALUES (1610, '473483D1-A643-44F8-B3FD-EF419CA8264C', '411282', '灵宝市');
INSERT INTO `sys_region` VALUES (1611, 'A1B3C6F7-62ED-48A7-A041-7D00862B44AD', '411300', '南阳市');
INSERT INTO `sys_region` VALUES (1612, 'CE0E6E2E-9019-4003-95B5-3F0730BEA085', '411302', '宛城区');
INSERT INTO `sys_region` VALUES (1613, '9E917AC6-B038-4D74-BECD-E5BE0651161C', '411303', '卧龙区');
INSERT INTO `sys_region` VALUES (1614, '4A09E1AC-B45D-42D6-AF97-32DBE99044FF', '411321', '南召县');
INSERT INTO `sys_region` VALUES (1615, '91069F57-0F25-4C05-93F0-4F9B6052D1EB', '411322', '方城县');
INSERT INTO `sys_region` VALUES (1616, 'DC38B3DA-326D-4C2B-9F52-D30F6CE669CA', '411323', '西峡县');
INSERT INTO `sys_region` VALUES (1617, '26089BFB-92D5-4E79-A689-72D528268F5B', '411324', '镇平县');
INSERT INTO `sys_region` VALUES (1618, 'BCD485DE-7007-44B7-B496-8081C5C579E1', '411325', '内乡县');
INSERT INTO `sys_region` VALUES (1619, '3B4B5106-6835-4231-8D72-F36E282FF99D', '411326', '淅川县');
INSERT INTO `sys_region` VALUES (1620, '94B9595B-5C66-4C69-A56B-4ED4050383FE', '411327', '社旗县');
INSERT INTO `sys_region` VALUES (1621, 'D8CC6345-47CD-43B1-94BF-C65B5DFBA3D4', '411328', '唐河县');
INSERT INTO `sys_region` VALUES (1622, '078650F8-EC5D-40E4-9D25-6CBE2B862BE3', '411329', '新野县');
INSERT INTO `sys_region` VALUES (1623, '3861153E-5B24-409B-9378-9E147A64E82F', '411330', '桐柏县');
INSERT INTO `sys_region` VALUES (1624, 'B5AC4F24-6A59-42F6-8642-83BAD58AE94D', '411381', '邓州市');
INSERT INTO `sys_region` VALUES (1625, '1F74D493-DF57-4512-85DE-19CB62F12DD7', '411400', '商丘市');
INSERT INTO `sys_region` VALUES (1626, '89FDA341-5500-4164-938D-E82385232882', '411402', '梁园区');
INSERT INTO `sys_region` VALUES (1627, '71A7B160-1CEF-481A-87CF-8F2B167FAA73', '411403', '睢阳区');
INSERT INTO `sys_region` VALUES (1628, 'E99017AC-8FC7-4356-8D18-86843A986E08', '411421', '民权县');
INSERT INTO `sys_region` VALUES (1629, '6CD7D6B1-CD3E-489C-B8FE-C129B337FE8B', '411422', '睢县');
INSERT INTO `sys_region` VALUES (1630, '29274190-AD67-4AEA-81F5-C13032F1A50A', '411423', '宁陵县');
INSERT INTO `sys_region` VALUES (1631, '3FEEB046-CE24-4028-B5E1-A8351B101636', '411424', '柘城县');
INSERT INTO `sys_region` VALUES (1632, '3FED7C40-DE42-4D06-B165-4EF137C97932', '411425', '虞城县');
INSERT INTO `sys_region` VALUES (1633, 'A920CAD3-889E-472D-BD9B-639E14940796', '411426', '夏邑县');
INSERT INTO `sys_region` VALUES (1634, '2B29D137-F6E1-4524-B673-B29426C42620', '411481', '永城市');
INSERT INTO `sys_region` VALUES (1635, '09025EDE-D977-4B78-AE9E-650F6AD75BE5', '411500', '信阳市');
INSERT INTO `sys_region` VALUES (1636, 'CD370CD7-A5EE-4DBD-BD07-AA142D2EC76A', '411502', '浉河区');
INSERT INTO `sys_region` VALUES (1637, 'FBA0781C-8660-46EE-A951-EE35925EC496', '411503', '平桥区');
INSERT INTO `sys_region` VALUES (1638, '558555BD-3E28-49A2-AF65-7FE3C4FEBD85', '411521', '罗山县');
INSERT INTO `sys_region` VALUES (1639, 'D803C34A-1A94-486E-BD7B-084D045E666F', '411522', '光山县');
INSERT INTO `sys_region` VALUES (1640, '37E914F6-7222-418B-BA38-DAE0D78AE296', '411523', '新县');
INSERT INTO `sys_region` VALUES (1641, '21EE3B22-0B98-4F90-A640-7EA9075EA201', '411524', '商城县');
INSERT INTO `sys_region` VALUES (1642, '08BB4E2F-4426-4563-BD1E-CBA178B868C8', '411525', '固始县');
INSERT INTO `sys_region` VALUES (1643, '9E1BA515-FAC9-45A0-B409-DE3C788522DB', '411526', '潢川县');
INSERT INTO `sys_region` VALUES (1644, 'B16E26DF-665D-4312-B580-83376A238CB9', '411527', '淮滨县');
INSERT INTO `sys_region` VALUES (1645, 'B392E5E2-C8AE-4972-835A-DAA29D38D4AD', '411528', '息县');
INSERT INTO `sys_region` VALUES (1646, 'BDCB7DFD-BD7B-4529-93C1-655ED905AD23', '411600', '周口市');
INSERT INTO `sys_region` VALUES (1647, 'ED65C3D3-4156-41C1-8D81-7EB55C6DC926', '411602', '川汇区');
INSERT INTO `sys_region` VALUES (1648, '169059EF-4947-4430-B0DE-FC96AD69F2BD', '411603', '淮阳区');
INSERT INTO `sys_region` VALUES (1649, 'A8575C84-DC54-4BD8-8462-0CD5A6171AE4', '411621', '扶沟县');
INSERT INTO `sys_region` VALUES (1650, 'CD6B8E06-69F5-451F-84D3-AFB4E759469A', '411622', '西华县');
INSERT INTO `sys_region` VALUES (1651, 'C9688340-9F1D-4F59-B61D-D0C33AF4E588', '411623', '商水县');
INSERT INTO `sys_region` VALUES (1652, 'A9308C68-424B-44F2-8DA8-195C974DC796', '411624', '沈丘县');
INSERT INTO `sys_region` VALUES (1653, 'EF313A81-4C4B-4646-AFB6-4D9285FCC976', '411625', '郸城县');
INSERT INTO `sys_region` VALUES (1654, '7EABB763-F655-40CB-A576-53F86FAC74EF', '411627', '太康县');
INSERT INTO `sys_region` VALUES (1655, 'BE41ABA2-C045-434D-AD68-962904EBCCE9', '411628', '鹿邑县');
INSERT INTO `sys_region` VALUES (1656, '1905BB27-47A9-478B-98B1-F097027DA17C', '411681', '项城市');
INSERT INTO `sys_region` VALUES (1657, '32C34318-1C61-4DCD-A253-389085147B67', '411700', '驻马店市');
INSERT INTO `sys_region` VALUES (1658, '99584DED-BAD2-467A-B05C-D01B57699E91', '411702', '驿城区');
INSERT INTO `sys_region` VALUES (1659, 'DF159F64-972E-4E3D-A2D4-D543E2B3EA8E', '411721', '西平县');
INSERT INTO `sys_region` VALUES (1660, 'EAC93049-92B7-4CD9-B6F7-186DEC9B1820', '411722', '上蔡县');
INSERT INTO `sys_region` VALUES (1661, '1017EB74-082E-4952-A319-53309BB17E5B', '411723', '平舆县');
INSERT INTO `sys_region` VALUES (1662, '6A265132-23EB-4B52-8940-98C9A21DAA28', '411724', '正阳县');
INSERT INTO `sys_region` VALUES (1663, '9202F7FC-CD25-4254-8BD8-2023A3596348', '411725', '确山县');
INSERT INTO `sys_region` VALUES (1664, '7C9B7F9D-21F9-4B9E-8F1C-0D7314405A2E', '411726', '泌阳县');
INSERT INTO `sys_region` VALUES (1665, '3C06AAE8-9962-43FA-B188-D2517C2952F9', '411727', '汝南县');
INSERT INTO `sys_region` VALUES (1666, '7DF3BC24-DA74-42CA-8C3C-7FB02C17E746', '411728', '遂平县');
INSERT INTO `sys_region` VALUES (1667, 'F88B9C2E-37F3-40A8-90E0-C5B359441A20', '411729', '新蔡县');
INSERT INTO `sys_region` VALUES (1668, 'D8F4C09C-9714-4211-B661-F9EA0D17BD4C', '419001', '济源市');
INSERT INTO `sys_region` VALUES (1669, 'E75A322B-693E-4437-9F7C-A1100898E508', '420000', '湖北省');
INSERT INTO `sys_region` VALUES (1670, 'AF4ACC40-303F-40FC-907F-D85030B7E9C6', '420100', '武汉市');
INSERT INTO `sys_region` VALUES (1671, 'BA725C7A-AC77-47FF-A47F-F69BEB2385D2', '420102', '江岸区');
INSERT INTO `sys_region` VALUES (1672, '7F6A014D-77EB-48B2-B11F-3DC5F75292B0', '420103', '江汉区');
INSERT INTO `sys_region` VALUES (1673, '27AD148A-8744-4AD1-8A99-75C984B451B7', '420104', '硚口区');
INSERT INTO `sys_region` VALUES (1674, 'A6FFE799-06CC-4EDB-9CA6-A80F1425C7B3', '420105', '汉阳区');
INSERT INTO `sys_region` VALUES (1675, '5EA63F77-5F78-4D08-B9B2-615F923E7B9F', '420106', '武昌区');
INSERT INTO `sys_region` VALUES (1676, '9C786FF9-C64B-4D69-969B-557D66C63BD2', '420107', '青山区');
INSERT INTO `sys_region` VALUES (1677, 'BBE420DD-680E-4579-8717-4C1A5D22FF32', '420111', '洪山区');
INSERT INTO `sys_region` VALUES (1678, 'C8DEBDA7-19F5-49B3-96C9-45EF5F321FA0', '420112', '东西湖区');
INSERT INTO `sys_region` VALUES (1679, '09C7E785-411E-4CCE-B856-FCAB4132C218', '420113', '汉南区');
INSERT INTO `sys_region` VALUES (1680, 'CDEA9298-7071-4495-B82D-7F84EB60A146', '420114', '蔡甸区');
INSERT INTO `sys_region` VALUES (1681, '85773DEE-A4F5-4D90-AAF1-F3A891718DA9', '420115', '江夏区');
INSERT INTO `sys_region` VALUES (1682, 'E0DD119F-1C94-4F6C-8AF3-8122337FB7E0', '420116', '黄陂区');
INSERT INTO `sys_region` VALUES (1683, '20EABC2B-C892-4E3E-9923-C87D3CA5EA4F', '420117', '新洲区');
INSERT INTO `sys_region` VALUES (1684, '38238259-76A9-455F-A94A-F6A2F6298792', '420200', '黄石市');
INSERT INTO `sys_region` VALUES (1685, '8E463CB9-9385-4A6C-82C6-F5B06CE43495', '420202', '黄石港区');
INSERT INTO `sys_region` VALUES (1686, 'E23EA4A6-4741-4003-B6F4-3A7C20287769', '420203', '西塞山区');
INSERT INTO `sys_region` VALUES (1687, '458F99E2-C8B1-4DF5-AE88-192E604265A4', '420204', '下陆区');
INSERT INTO `sys_region` VALUES (1688, '6AA1FA26-D6B2-47CF-B34C-DD70AE592663', '420205', '铁山区');
INSERT INTO `sys_region` VALUES (1689, '388AF130-872F-4B73-91CC-923CD5629371', '420222', '阳新县');
INSERT INTO `sys_region` VALUES (1690, 'CB600FDA-2437-433E-8CD4-844CB46B0A38', '420281', '大冶市');
INSERT INTO `sys_region` VALUES (1691, '1763D70B-62E9-42B2-9FBD-711A7F3CB857', '420300', '十堰市');
INSERT INTO `sys_region` VALUES (1692, 'E4F4C51C-3CE1-4AC0-8EB9-B304967D70AB', '420302', '茅箭区');
INSERT INTO `sys_region` VALUES (1693, 'B5535E3E-EF7A-4935-89BA-B5686D64DC89', '420303', '张湾区');
INSERT INTO `sys_region` VALUES (1694, 'C8900F56-2ACF-41B3-908F-E5218EFEDCD1', '420304', '郧阳区');
INSERT INTO `sys_region` VALUES (1695, 'A2A59B47-EEB6-4A09-A662-693D10B8724B', '420322', '郧西县');
INSERT INTO `sys_region` VALUES (1696, 'A8A11DDC-851E-48CA-9076-1E0D11039631', '420323', '竹山县');
INSERT INTO `sys_region` VALUES (1697, '46233AB6-00D7-43C4-867C-FB07C15C9946', '420324', '竹溪县');
INSERT INTO `sys_region` VALUES (1698, 'E424DFF6-FF55-4689-B124-56C60E279E7C', '420325', '房县');
INSERT INTO `sys_region` VALUES (1699, 'F4F5F5B4-1C7D-408F-8318-20FCD439D201', '420381', '丹江口市');
INSERT INTO `sys_region` VALUES (1700, '117BA4DD-CC18-4216-A03C-E182642490BE', '420500', '宜昌市');
INSERT INTO `sys_region` VALUES (1701, '668A9995-1E22-4FBE-AC2F-085BD27D48CB', '420502', '西陵区');
INSERT INTO `sys_region` VALUES (1702, 'BC5B859B-B133-4E6A-A827-9A5B5F52168B', '420503', '伍家岗区');
INSERT INTO `sys_region` VALUES (1703, '62332AB4-79FF-4E30-9B36-5A498C1BD596', '420504', '点军区');
INSERT INTO `sys_region` VALUES (1704, '004F29CB-9B69-47FA-8774-5A7299460B39', '420505', '猇亭区');
INSERT INTO `sys_region` VALUES (1705, '4092A16C-D721-4AC8-A43A-0469C8EBC786', '420506', '夷陵区');
INSERT INTO `sys_region` VALUES (1706, 'A24B298C-87B2-43EE-970D-7A85D7056032', '420525', '远安县');
INSERT INTO `sys_region` VALUES (1707, '6E2A7F55-0CF6-40AF-8CEA-68CB7F870DE8', '420526', '兴山县');
INSERT INTO `sys_region` VALUES (1708, '1A3C77E5-4B98-41A6-BE71-2702A6E7B02F', '420527', '秭归县');
INSERT INTO `sys_region` VALUES (1709, '4537488B-EC0A-40FA-9F43-9508E71B34CB', '420528', '长阳土家族自治县');
INSERT INTO `sys_region` VALUES (1710, 'D7AADCAE-A1CD-41AB-A8DA-2315C35A5C42', '420529', '五峰土家族自治县');
INSERT INTO `sys_region` VALUES (1711, '1D2B549F-8A27-46D4-A25C-4C1F715ED221', '420581', '宜都市');
INSERT INTO `sys_region` VALUES (1712, 'B9EAC184-DD34-4FD0-B17F-70A1C548CCF0', '420582', '当阳市');
INSERT INTO `sys_region` VALUES (1713, 'C8112477-510D-474C-BDF7-54BB570C9A7C', '420583', '枝江市');
INSERT INTO `sys_region` VALUES (1714, '5824E5E0-21DC-4596-BCE1-66D3EF7F34EA', '420600', '襄阳市');
INSERT INTO `sys_region` VALUES (1715, '47A32D90-1077-4158-B297-FBDA025E1AC5', '420602', '襄城区');
INSERT INTO `sys_region` VALUES (1716, 'D1B57727-F640-42DB-96D9-07F75DC4FD7E', '420606', '樊城区');
INSERT INTO `sys_region` VALUES (1717, '82DEA421-7353-4268-8939-4C8EC69AE20B', '420607', '襄州区');
INSERT INTO `sys_region` VALUES (1718, '82665D8E-1482-4152-8600-01EF8520B244', '420624', '南漳县');
INSERT INTO `sys_region` VALUES (1719, 'E5F78C99-4537-44DA-AE54-A07AE1EC0ED1', '420625', '谷城县');
INSERT INTO `sys_region` VALUES (1720, '3F1D7F21-D934-45FF-B53B-E1644C02036A', '420626', '保康县');
INSERT INTO `sys_region` VALUES (1721, 'C5C46990-9E1E-4492-AA1C-2291753FE713', '420682', '老河口市');
INSERT INTO `sys_region` VALUES (1722, 'FD5E2545-F3BA-4BDF-B5A3-E1ACF63D6995', '420683', '枣阳市');
INSERT INTO `sys_region` VALUES (1723, '1A0974DF-CA82-4B62-B764-7FFB68007827', '420684', '宜城市');
INSERT INTO `sys_region` VALUES (1724, '740AAA38-86F4-468C-AE56-44F4517EE996', '420700', '鄂州市');
INSERT INTO `sys_region` VALUES (1725, '1D9A9EAE-B5C7-4529-8898-2CE74302D66E', '420702', '梁子湖区');
INSERT INTO `sys_region` VALUES (1726, '17C5541A-6440-4167-91F5-4C130225FEB4', '420703', '华容区');
INSERT INTO `sys_region` VALUES (1727, '5C264E21-2769-4F5B-9E36-7F6A5DC22D37', '420704', '鄂城区');
INSERT INTO `sys_region` VALUES (1728, '847ACADA-8CAE-42E1-AD12-B63D29DBD7DC', '420800', '荆门市');
INSERT INTO `sys_region` VALUES (1729, '196F492A-C037-4523-8242-D3FCFD3D0F9C', '420802', '东宝区');
INSERT INTO `sys_region` VALUES (1730, '919FB87C-0C0D-417A-ACBC-CEA19F4BAC04', '420804', '掇刀区');
INSERT INTO `sys_region` VALUES (1731, 'F47C05F5-8AC4-4E1D-8BF0-8EB5D225EDC3', '420822', '沙洋县');
INSERT INTO `sys_region` VALUES (1732, '41B26078-791E-4E51-8F63-E5ED7664B913', '420881', '钟祥市');
INSERT INTO `sys_region` VALUES (1733, 'FAABF351-79F6-4C4B-9C5A-BC258D2245D2', '420882', '京山市');
INSERT INTO `sys_region` VALUES (1734, '0A4F271A-4214-4EEC-A48C-2F4DF3B6CC61', '420900', '孝感市');
INSERT INTO `sys_region` VALUES (1735, '026A7C42-A430-4C4B-B0F5-91C27464925F', '420902', '孝南区');
INSERT INTO `sys_region` VALUES (1736, 'AD507ECA-B390-462A-A4E6-3851DC44AD40', '420921', '孝昌县');
INSERT INTO `sys_region` VALUES (1737, '074156E7-07B7-4420-BB9E-95255F9098A4', '420922', '大悟县');
INSERT INTO `sys_region` VALUES (1738, 'CED44576-F729-4B41-B48D-1FBD6C9584D9', '420923', '云梦县');
INSERT INTO `sys_region` VALUES (1739, '8F5B0DCA-F6FB-4765-A826-561DFC6A4550', '420981', '应城市');
INSERT INTO `sys_region` VALUES (1740, '23F9D344-E3CF-440F-8416-3B7A25429087', '420982', '安陆市');
INSERT INTO `sys_region` VALUES (1741, 'CF59D831-A37A-4FF5-82C7-384DCF79B033', '420984', '汉川市');
INSERT INTO `sys_region` VALUES (1742, '97BD0169-3CEA-4B77-BFA4-31DE85F55B07', '421000', '荆州市');
INSERT INTO `sys_region` VALUES (1743, '396E7157-E739-48A6-B808-82A163B286AF', '421002', '沙市区');
INSERT INTO `sys_region` VALUES (1744, '7AC5BD13-AE9F-4A64-A84F-0579510EE623', '421003', '荆州区');
INSERT INTO `sys_region` VALUES (1745, '13CBE023-FD98-45EE-9058-A337C0AAF7DD', '421022', '公安县');
INSERT INTO `sys_region` VALUES (1746, '0B382CC7-A097-493E-9498-F4B665B69297', '421023', '监利县');
INSERT INTO `sys_region` VALUES (1747, '8C72BABC-3C83-4B70-9FDD-BDE667196DCF', '421024', '江陵县');
INSERT INTO `sys_region` VALUES (1748, 'A3EF75C8-AFBC-43C0-BDA4-47AB35895F98', '421081', '石首市');
INSERT INTO `sys_region` VALUES (1749, '9DFA274D-C1A2-4932-B236-51ED94AFF2E3', '421083', '洪湖市');
INSERT INTO `sys_region` VALUES (1750, '8AF10001-7C9A-4EBA-8005-E2DE79547423', '421087', '松滋市');
INSERT INTO `sys_region` VALUES (1751, '53608A09-DB1F-4DBA-AEA4-FD9264289620', '421100', '黄冈市');
INSERT INTO `sys_region` VALUES (1752, 'A3236257-6DFF-4088-A891-881EEAB44107', '421102', '黄州区');
INSERT INTO `sys_region` VALUES (1753, 'E63C7563-F20D-4F43-980A-6C3C82EAD37B', '421121', '团风县');
INSERT INTO `sys_region` VALUES (1754, '1EEF9F89-3D63-45BE-AF0E-04E0320283E2', '421122', '红安县');
INSERT INTO `sys_region` VALUES (1755, 'CEA3F7F8-6B07-4DCE-B67F-D8DE545453C7', '421123', '罗田县');
INSERT INTO `sys_region` VALUES (1756, '6647685C-5572-4E83-A42F-D2D3A1DEBD3A', '421124', '英山县');
INSERT INTO `sys_region` VALUES (1757, '5B656E37-31CA-4495-B28E-1E968F13A6F8', '421125', '浠水县');
INSERT INTO `sys_region` VALUES (1758, 'A99D028D-1321-4BB3-857F-7F408FE5AAFD', '421126', '蕲春县');
INSERT INTO `sys_region` VALUES (1759, '6525FD0A-E60B-4E73-A197-0DDF37324C90', '421127', '黄梅县');
INSERT INTO `sys_region` VALUES (1760, '90EC8F81-0D94-4DEB-ABAF-4616D97B7DE3', '421181', '麻城市');
INSERT INTO `sys_region` VALUES (1761, '13FAAD9B-7174-4A47-A5CE-9786013F3CA6', '421182', '武穴市');
INSERT INTO `sys_region` VALUES (1762, 'BFDB4415-9023-44C3-B3C1-CBBD0C2FB66B', '421200', '咸宁市');
INSERT INTO `sys_region` VALUES (1763, 'D74D8F38-BBCF-4AC5-A3E6-EA42B2040CA8', '421202', '咸安区');
INSERT INTO `sys_region` VALUES (1764, 'D7159D17-3722-4F54-9357-96FF686D237E', '421221', '嘉鱼县');
INSERT INTO `sys_region` VALUES (1765, '5A0612E6-33E7-41E6-9C38-D70059DBCCCC', '421222', '通城县');
INSERT INTO `sys_region` VALUES (1766, 'DB8BB0AE-19D6-4BC8-9ACD-8AEAD39FDD54', '421223', '崇阳县');
INSERT INTO `sys_region` VALUES (1767, 'C417044E-BAC7-4AD1-96AF-5B652016D415', '421224', '通山县');
INSERT INTO `sys_region` VALUES (1768, '764F8E5E-C340-4348-A779-60BDB0149AA5', '421281', '赤壁市');
INSERT INTO `sys_region` VALUES (1769, '3D64B793-43A8-465E-9AAC-A7351C22929B', '421300', '随州市');
INSERT INTO `sys_region` VALUES (1770, '58575AFD-241B-4065-B9F1-FD2DB639B152', '421303', '曾都区');
INSERT INTO `sys_region` VALUES (1771, '9FB3C986-0546-47CD-A083-4E291C1B716B', '421321', '随县');
INSERT INTO `sys_region` VALUES (1772, '01024EC9-DD66-4E65-B4F2-5D6D9BBE09C0', '421381', '广水市');
INSERT INTO `sys_region` VALUES (1773, 'F23AAAA8-9F3D-4561-BFF4-3738C23BAFAC', '422800', '恩施土家族苗族自治州');
INSERT INTO `sys_region` VALUES (1774, 'F3DDF13C-BD1C-40E1-8588-06A3A517C9B7', '422801', '恩施市');
INSERT INTO `sys_region` VALUES (1775, '1B376AB4-B4A6-4397-AA3A-7F6522E85D0F', '422802', '利川市');
INSERT INTO `sys_region` VALUES (1776, 'BD0ABE5F-C394-4C6F-BBFD-86187F3093C7', '422822', '建始县');
INSERT INTO `sys_region` VALUES (1777, 'D03D55FE-473B-4270-A6B8-8B1C140DF16B', '422823', '巴东县');
INSERT INTO `sys_region` VALUES (1778, 'ECB666B9-E6E2-45A1-B4AB-E3EA45A4810E', '422825', '宣恩县');
INSERT INTO `sys_region` VALUES (1779, '53B3D6E3-4838-4C01-99E0-3E11BA4EDD3A', '422826', '咸丰县');
INSERT INTO `sys_region` VALUES (1780, '491EFBEA-4761-49F2-81A5-659AD91589C4', '422827', '来凤县');
INSERT INTO `sys_region` VALUES (1781, '8A53F18B-C3F2-49DE-A738-8EA499411561', '422828', '鹤峰县');
INSERT INTO `sys_region` VALUES (1782, '79D2F26A-3073-47B6-A4CB-2BC195A36C4E', '429004', '仙桃市');
INSERT INTO `sys_region` VALUES (1783, '92E93D7D-F6F2-4F76-A737-C97437E93687', '429005', '潜江市');
INSERT INTO `sys_region` VALUES (1784, '4732144B-D3FE-4586-B147-521914A937C8', '429006', '天门市');
INSERT INTO `sys_region` VALUES (1785, '73974FB9-F736-4E2F-9089-BDD597D3A7E5', '429021', '神农架林区');
INSERT INTO `sys_region` VALUES (1786, '28F393C9-9741-4133-BA13-40D7BCA6EDCB', '430000', '湖南省');
INSERT INTO `sys_region` VALUES (1787, '1F849F40-5F52-49CD-869D-C62E70D4AEBE', '430100', '长沙市');
INSERT INTO `sys_region` VALUES (1788, '61727C22-82D1-4B57-8280-5AA914AAB5A3', '430102', '芙蓉区');
INSERT INTO `sys_region` VALUES (1789, '328F1368-12C3-4778-B53E-A7D6BD0A735C', '430103', '天心区');
INSERT INTO `sys_region` VALUES (1790, '72886D64-2C7D-4D4F-86E1-8AD9D7C284B0', '430104', '岳麓区');
INSERT INTO `sys_region` VALUES (1791, 'E3EEA438-7573-4023-B53C-3C3B7367DC87', '430105', '开福区');
INSERT INTO `sys_region` VALUES (1792, '167F4465-E941-4FC1-809A-EE07B8D01A63', '430111', '雨花区');
INSERT INTO `sys_region` VALUES (1793, '20CF9407-A9E8-4E74-A79C-0ECF50A31C86', '430112', '望城区');
INSERT INTO `sys_region` VALUES (1794, '3EC92A32-932D-4EF0-B2A8-027319DFF56E', '430121', '长沙县');
INSERT INTO `sys_region` VALUES (1795, '6F38DABC-F712-4E72-B159-7853BB8900B7', '430181', '浏阳市');
INSERT INTO `sys_region` VALUES (1796, 'A88D11C4-0118-4C24-88B0-00148CE286D3', '430182', '宁乡市');
INSERT INTO `sys_region` VALUES (1797, 'E6FD83DB-8CA9-42AD-B3A2-15263ACE7C96', '430200', '株洲市');
INSERT INTO `sys_region` VALUES (1798, '06C2C554-59DB-435D-A6A4-6CBD69234AEB', '430202', '荷塘区');
INSERT INTO `sys_region` VALUES (1799, 'B9EAD737-0AE7-4A17-B304-E9054C68BBE4', '430203', '芦淞区');
INSERT INTO `sys_region` VALUES (1800, 'D26D7AB8-4C93-42B2-8812-CF96623398BB', '430204', '石峰区');
INSERT INTO `sys_region` VALUES (1801, '80375D66-CCA0-4E61-824B-E3A181D4498C', '430211', '天元区');
INSERT INTO `sys_region` VALUES (1802, '5C52F784-DA49-40A0-8C12-138CE670EB56', '430212', '渌口区');
INSERT INTO `sys_region` VALUES (1803, 'B445E6F3-3799-4059-8D50-5AF431BEDDE9', '430223', '攸县');
INSERT INTO `sys_region` VALUES (1804, 'B847A7C1-FA2F-4DC5-A6D3-2B2EA97F75F1', '430224', '茶陵县');
INSERT INTO `sys_region` VALUES (1805, 'D90D039E-658B-4649-A965-203BCE24AC6E', '430225', '炎陵县');
INSERT INTO `sys_region` VALUES (1806, '6057D482-1222-4A5B-8E83-9DDD18C5A3B5', '430281', '醴陵市');
INSERT INTO `sys_region` VALUES (1807, 'FA8C5047-6A4E-4782-80FE-D6FC3EFA0D4F', '430300', '湘潭市');
INSERT INTO `sys_region` VALUES (1808, 'DDABF022-1896-4B55-8615-2E15EA49E346', '430302', '雨湖区');
INSERT INTO `sys_region` VALUES (1809, '60DC09E2-D431-4FDA-8DAE-C5B77338E076', '430304', '岳塘区');
INSERT INTO `sys_region` VALUES (1810, '3560F595-CED5-4705-98F7-DA9C30C9816A', '430321', '湘潭县');
INSERT INTO `sys_region` VALUES (1811, '6AEEAB30-CAA7-44A5-8396-BCC2C3D31FF8', '430381', '湘乡市');
INSERT INTO `sys_region` VALUES (1812, '32E4CC69-92EF-465A-A879-A15CC9181F93', '430382', '韶山市');
INSERT INTO `sys_region` VALUES (1813, 'CDCBD46E-E229-42DA-AF1C-5EE4E5649798', '430400', '衡阳市');
INSERT INTO `sys_region` VALUES (1814, 'E2CF0C7F-D4E4-4664-9300-15CAC2C614F4', '430405', '珠晖区');
INSERT INTO `sys_region` VALUES (1815, '57268462-1B93-408E-B66A-2E9F42E91FAF', '430406', '雁峰区');
INSERT INTO `sys_region` VALUES (1816, '8A327D56-EF4E-43E4-9EDE-562052F3A5CB', '430407', '石鼓区');
INSERT INTO `sys_region` VALUES (1817, '9CED3CA5-1DC2-4378-81FA-03EF032B5F2D', '430408', '蒸湘区');
INSERT INTO `sys_region` VALUES (1818, 'F320C176-BF4C-4E45-8663-C9442075B2C8', '430412', '南岳区');
INSERT INTO `sys_region` VALUES (1819, 'C432F089-14C3-4E6E-9EAA-D0B6B16815BF', '430421', '衡阳县');
INSERT INTO `sys_region` VALUES (1820, '8258607E-EE46-4C69-8037-5A4863CF881B', '430422', '衡南县');
INSERT INTO `sys_region` VALUES (1821, 'E634A819-F5A8-47F1-A549-0677B6DDDF65', '430423', '衡山县');
INSERT INTO `sys_region` VALUES (1822, 'B2E4C822-AD8E-4E92-8FDD-FF88261078D2', '430424', '衡东县');
INSERT INTO `sys_region` VALUES (1823, '462B06EC-CF85-4295-BAAD-A27B2E07BC61', '430426', '祁东县');
INSERT INTO `sys_region` VALUES (1824, '3D956BBC-8879-4952-85BA-1A541438CF47', '430481', '耒阳市');
INSERT INTO `sys_region` VALUES (1825, 'D0349321-E19B-4692-BA56-B338C6E829CD', '430482', '常宁市');
INSERT INTO `sys_region` VALUES (1826, 'CE603443-42DE-4384-861C-4AC8E663BCAB', '430500', '邵阳市');
INSERT INTO `sys_region` VALUES (1827, '8C54D01B-D339-463E-BCD3-49DE5F3FBDC2', '430502', '双清区');
INSERT INTO `sys_region` VALUES (1828, '59FF38EE-8B0B-4A47-877A-D3D4C5F6A23F', '430503', '大祥区');
INSERT INTO `sys_region` VALUES (1829, '6E7DA139-5ED9-4769-92EB-D9F06CAC7DA9', '430511', '北塔区');
INSERT INTO `sys_region` VALUES (1830, '76FFBDDA-AA97-4D20-81FF-9C10C4989656', '430522', '新邵县');
INSERT INTO `sys_region` VALUES (1831, 'F070DCF3-8744-478F-A076-13721683D7CA', '430523', '邵阳县');
INSERT INTO `sys_region` VALUES (1832, '9F154298-5C28-428B-8989-44F602694C0E', '430524', '隆回县');
INSERT INTO `sys_region` VALUES (1833, '422819BC-B2AE-44CA-ABBC-DE2131E84699', '430525', '洞口县');
INSERT INTO `sys_region` VALUES (1834, '6499BD98-74FE-4883-9C51-5CC7F8A7C836', '430527', '绥宁县');
INSERT INTO `sys_region` VALUES (1835, 'E7B1C28D-4974-4B5C-8A5E-CC8A9B68FF0D', '430528', '新宁县');
INSERT INTO `sys_region` VALUES (1836, 'EEB18A5D-44DB-498A-9425-0F150FADE7F4', '430529', '城步苗族自治县');
INSERT INTO `sys_region` VALUES (1837, '3572F8E3-92A4-48AA-8EE2-93BF25B9E442', '430581', '武冈市');
INSERT INTO `sys_region` VALUES (1838, '51F865B5-A5B1-4EDA-97E7-70D2BAF7D1CB', '430582', '邵东市');
INSERT INTO `sys_region` VALUES (1839, '210FB6BE-08E5-4A37-8EA0-D8F494FA1804', '430600', '岳阳市');
INSERT INTO `sys_region` VALUES (1840, 'E913D445-9C65-4DB9-B534-D02BE03BF542', '430602', '岳阳楼区');
INSERT INTO `sys_region` VALUES (1841, 'DAA24BD6-ABEE-4C59-8F4F-C9968AD2A5E2', '430603', '云溪区');
INSERT INTO `sys_region` VALUES (1842, 'A0B33099-92A0-4D9F-A4D8-594E28EB81B0', '430611', '君山区');
INSERT INTO `sys_region` VALUES (1843, '9B9AF053-E81C-4981-A0B6-09F3021EBDFF', '430621', '岳阳县');
INSERT INTO `sys_region` VALUES (1844, '013855EB-7AF5-42B9-95F5-8AE98073D864', '430623', '华容县');
INSERT INTO `sys_region` VALUES (1845, '54564396-71F9-446D-B4FA-F41B0F8B0E31', '430624', '湘阴县');
INSERT INTO `sys_region` VALUES (1846, '4F9BDBF0-3AE5-4D1E-BF21-E9504A2A3493', '430626', '平江县');
INSERT INTO `sys_region` VALUES (1847, 'BCD4EA2D-9D12-4A21-AB84-E262BF6B4A2E', '430681', '汨罗市');
INSERT INTO `sys_region` VALUES (1848, 'A9FBBAAD-09DF-4865-8FCF-5CCD780F9651', '430682', '临湘市');
INSERT INTO `sys_region` VALUES (1849, '3882AC5A-500B-49B0-AE25-55D86909B306', '430700', '常德市');
INSERT INTO `sys_region` VALUES (1850, '348D2235-0019-4139-ADCC-A9ADF3362311', '430702', '武陵区');
INSERT INTO `sys_region` VALUES (1851, 'B0D5DE4E-DE2C-4E1B-B8B2-6B85AA9D58F8', '430703', '鼎城区');
INSERT INTO `sys_region` VALUES (1852, '9001AFC8-0E74-416E-BAC2-D654B6B0F162', '430721', '安乡县');
INSERT INTO `sys_region` VALUES (1853, '412006C8-888A-482D-BCE6-FCA56B946E30', '430722', '汉寿县');
INSERT INTO `sys_region` VALUES (1854, '8D68E6D7-85BE-4ECD-8465-C0AAD6E21275', '430723', '澧县');
INSERT INTO `sys_region` VALUES (1855, 'AA2F7E64-8010-4FF2-B5A4-8E67B485AAA0', '430724', '临澧县');
INSERT INTO `sys_region` VALUES (1856, 'CE5FCE04-989D-48FC-A3F7-539A267DDDE3', '430725', '桃源县');
INSERT INTO `sys_region` VALUES (1857, 'E645C24F-7BFC-4FA2-806B-1DF0B58F619C', '430726', '石门县');
INSERT INTO `sys_region` VALUES (1858, '0A6D69E9-B393-4870-A2FE-8EB752E78822', '430781', '津市市');
INSERT INTO `sys_region` VALUES (1859, '800794BD-3909-421A-9C67-0FA9C0570A93', '430800', '张家界市');
INSERT INTO `sys_region` VALUES (1860, '520BB018-36F8-4B06-87A9-448B7BE3192E', '430802', '永定区');
INSERT INTO `sys_region` VALUES (1861, 'B2DC8D51-5568-4741-AC24-CFA6C5CF9157', '430811', '武陵源区');
INSERT INTO `sys_region` VALUES (1862, '66611D41-BE4B-47B8-BA5A-232F559DC0BE', '430821', '慈利县');
INSERT INTO `sys_region` VALUES (1863, 'A696FC99-288D-4D10-8156-59C102801C2B', '430822', '桑植县');
INSERT INTO `sys_region` VALUES (1864, 'EFCA0DDF-7470-46B1-9EE8-5C2A5B0BD5FC', '430900', '益阳市');
INSERT INTO `sys_region` VALUES (1865, 'BF0D446E-E116-49CE-9C4A-1B5F01541F59', '430902', '资阳区');
INSERT INTO `sys_region` VALUES (1866, '8B3118A6-D7CA-4E53-AF16-4ED0F130F4F3', '430903', '赫山区');
INSERT INTO `sys_region` VALUES (1867, 'EC168D1E-258D-4AA1-A863-7AF3FF0F447F', '430921', '南县');
INSERT INTO `sys_region` VALUES (1868, 'C153694C-3183-4E8C-86C4-C7644D977715', '430922', '桃江县');
INSERT INTO `sys_region` VALUES (1869, 'AD755A39-57ED-44CE-B828-B6460F0189EF', '430923', '安化县');
INSERT INTO `sys_region` VALUES (1870, '1EBFF127-FFB8-47A4-96B8-5B4AB65276EA', '430981', '沅江市');
INSERT INTO `sys_region` VALUES (1871, '296751E0-375F-4766-856F-7358260F182F', '431000', '郴州市');
INSERT INTO `sys_region` VALUES (1872, 'A015BBBA-6BEC-4964-9623-0260E0BAA8A6', '431002', '北湖区');
INSERT INTO `sys_region` VALUES (1873, '0D808C62-9896-4F64-8BA7-9BE8EC46CE7C', '431003', '苏仙区');
INSERT INTO `sys_region` VALUES (1874, '71691F7F-AB5D-4EB7-B604-47ACFFAE6F92', '431021', '桂阳县');
INSERT INTO `sys_region` VALUES (1875, '85310200-3C69-4047-968B-D808957101D8', '431022', '宜章县');
INSERT INTO `sys_region` VALUES (1876, '829E6CFF-3567-4F46-9AD6-066DDCD3A5FC', '431023', '永兴县');
INSERT INTO `sys_region` VALUES (1877, '8CA36C56-4AA0-4A46-9124-325C086D9B80', '431024', '嘉禾县');
INSERT INTO `sys_region` VALUES (1878, '97768FBA-E7C5-47C4-9982-A94A98D64C7D', '431025', '临武县');
INSERT INTO `sys_region` VALUES (1879, '1F9B3C93-BE47-492B-938A-A1C00AEE2706', '431026', '汝城县');
INSERT INTO `sys_region` VALUES (1880, '4DAA79BB-12CB-4F93-AB0C-8CB6AEF22FFB', '431027', '桂东县');
INSERT INTO `sys_region` VALUES (1881, '6C809148-3D52-46C0-87FA-534B85931DDA', '431028', '安仁县');
INSERT INTO `sys_region` VALUES (1882, '10F6B4CB-3083-41D5-B5FE-F4DD88E93290', '431081', '资兴市');
INSERT INTO `sys_region` VALUES (1883, '8A22BA8D-6BA8-4C26-A0A4-E579F0F3A7B4', '431100', '永州市');
INSERT INTO `sys_region` VALUES (1884, '3156B8C6-4CEA-4E88-935E-BA95FAB6405A', '431102', '零陵区');
INSERT INTO `sys_region` VALUES (1885, '1344F1EF-4AFE-4578-B4B7-91E17EA38A80', '431103', '冷水滩区');
INSERT INTO `sys_region` VALUES (1886, '8BED8F17-150F-471A-AB7D-E02676AAF409', '431121', '祁阳县');
INSERT INTO `sys_region` VALUES (1887, '56D2669E-6692-4533-BC92-BDCC4473F241', '431122', '东安县');
INSERT INTO `sys_region` VALUES (1888, 'F8A808EB-3E1F-4185-A2A5-A407E18EC418', '431123', '双牌县');
INSERT INTO `sys_region` VALUES (1889, '03DD52B8-67C0-41D6-A172-75DDE42CD50C', '431124', '道县');
INSERT INTO `sys_region` VALUES (1890, '1C50E6A6-4017-4610-8F7B-FD54A39FE6FB', '431125', '江永县');
INSERT INTO `sys_region` VALUES (1891, '2AC20FD6-168B-4C96-9CDD-D4AABF444046', '431126', '宁远县');
INSERT INTO `sys_region` VALUES (1892, '62B373DB-28B2-442A-89F7-4541478D6BED', '431127', '蓝山县');
INSERT INTO `sys_region` VALUES (1893, '88AB109F-DA89-4754-B3CF-B8D6E948D52E', '431128', '新田县');
INSERT INTO `sys_region` VALUES (1894, 'C2FEEE1F-38E3-4B6D-8486-818E7D1F3EB1', '431129', '江华瑶族自治县');
INSERT INTO `sys_region` VALUES (1895, '011B0993-126F-4D36-9DDB-A04E2D1707A4', '431200', '怀化市');
INSERT INTO `sys_region` VALUES (1896, '8022F6AF-423A-4252-A52F-743B1399CDB1', '431202', '鹤城区');
INSERT INTO `sys_region` VALUES (1897, '7BE1728A-47DF-4454-BF6E-CCF96317B260', '431221', '中方县');
INSERT INTO `sys_region` VALUES (1898, '3E3FE025-9F06-4945-AE1C-D11F9E1EF6E2', '431222', '沅陵县');
INSERT INTO `sys_region` VALUES (1899, '27BA1687-10C6-4B4C-BED7-2889071EC7B0', '431223', '辰溪县');
INSERT INTO `sys_region` VALUES (1900, '9F7360D7-EE6D-48FD-B4BB-80F69321A26E', '431224', '溆浦县');
INSERT INTO `sys_region` VALUES (1901, '897003EB-6675-4E60-8D20-3B7A6A9FEF88', '431225', '会同县');
INSERT INTO `sys_region` VALUES (1902, 'BDEC01B5-3F3B-4604-9F9A-EB72AEAE5D26', '431226', '麻阳苗族自治县');
INSERT INTO `sys_region` VALUES (1903, 'FCF357FB-1E29-4CA2-BF71-BCF8B8D9AEBF', '431227', '新晃侗族自治县');
INSERT INTO `sys_region` VALUES (1904, 'A4D6E4EA-8795-4053-BB9F-D7A4F363F043', '431228', '芷江侗族自治县');
INSERT INTO `sys_region` VALUES (1905, '6DB5BFEA-FB58-4FAC-8F52-DF2AA385C87D', '431229', '靖州苗族侗族自治县');
INSERT INTO `sys_region` VALUES (1906, 'C92D10A9-E64A-4954-9824-CA7A39C0426A', '431230', '通道侗族自治县');
INSERT INTO `sys_region` VALUES (1907, '56E1DF98-BA81-4483-B73A-E6DF53E15494', '431281', '洪江市');
INSERT INTO `sys_region` VALUES (1908, '57AE9090-DA99-4CE2-8CA4-F05A838F1813', '431300', '娄底市');
INSERT INTO `sys_region` VALUES (1909, '60377E41-BEFF-459E-BF76-2A82F52427F0', '431302', '娄星区');
INSERT INTO `sys_region` VALUES (1910, 'B5A8AFEB-EE46-47F9-BAC8-9D886576900D', '431321', '双峰县');
INSERT INTO `sys_region` VALUES (1911, 'EFF9632D-8393-4715-85B8-8C2A7D42381A', '431322', '新化县');
INSERT INTO `sys_region` VALUES (1912, '6FDAE4DA-795B-4D8C-8795-35FFBCF341D8', '431381', '冷水江市');
INSERT INTO `sys_region` VALUES (1913, 'D67C3225-411A-41A7-BD6C-C6E19356F389', '431382', '涟源市');
INSERT INTO `sys_region` VALUES (1914, '6161B599-4E15-4CA4-B3D9-2D036B45EBE1', '433100', '湘西土家族苗族自治州');
INSERT INTO `sys_region` VALUES (1915, '9093D25E-1B73-4CD8-B197-3CA1CD58D964', '433101', '吉首市');
INSERT INTO `sys_region` VALUES (1916, 'E7E5091B-E053-46D1-B6C0-D45568BCAB04', '433122', '泸溪县');
INSERT INTO `sys_region` VALUES (1917, '15261891-DA5A-4E89-8730-8CD5BAAA2F84', '433123', '凤凰县');
INSERT INTO `sys_region` VALUES (1918, '39D230D9-D123-46D3-A7C5-B1817FD46932', '433124', '花垣县');
INSERT INTO `sys_region` VALUES (1919, '34A13D00-0EA5-40A2-A090-F238280ABEDD', '433125', '保靖县');
INSERT INTO `sys_region` VALUES (1920, 'AB625D3D-720B-4CE9-BC07-770DBCBD75B4', '433126', '古丈县');
INSERT INTO `sys_region` VALUES (1921, 'DE70EDA8-F8A9-4E11-9CAA-D0607A4B2D79', '433127', '永顺县');
INSERT INTO `sys_region` VALUES (1922, '07F5D054-6372-4911-838F-A9FBABE1AE90', '433130', '龙山县');
INSERT INTO `sys_region` VALUES (1923, '9EFC4295-FA68-4CE4-BC29-CDD682F37243', '440000', '广东省');
INSERT INTO `sys_region` VALUES (1924, 'E1F56284-D3FB-47EC-B137-003F3CC4B53C', '440100', '广州市');
INSERT INTO `sys_region` VALUES (1925, 'F99B5FAD-3F0B-4CD8-B45B-F6AA23284B09', '440103', '荔湾区');
INSERT INTO `sys_region` VALUES (1926, '29E2D388-865A-454C-A8AA-D1C0D10DBB9B', '440104', '越秀区');
INSERT INTO `sys_region` VALUES (1927, '43FCEF8F-3810-4045-A9A4-96DAD030FF93', '440105', '海珠区');
INSERT INTO `sys_region` VALUES (1928, '2342614F-8580-4082-AEC7-59839DC47C94', '440106', '天河区');
INSERT INTO `sys_region` VALUES (1929, '24AAAA15-E17E-4950-A662-CC786803A92A', '440111', '白云区');
INSERT INTO `sys_region` VALUES (1930, 'F54CBECF-7213-4EFD-9FDC-050A452EA1AC', '440112', '黄埔区');
INSERT INTO `sys_region` VALUES (1931, '9AC75237-5DC0-495C-91C0-606DE9297E75', '440113', '番禺区');
INSERT INTO `sys_region` VALUES (1932, '36DE7302-1D0C-4B4B-BE94-7C987CB7C8C1', '440114', '花都区');
INSERT INTO `sys_region` VALUES (1933, '41EF4521-84CD-4653-83D5-8C3992F663EF', '440115', '南沙区');
INSERT INTO `sys_region` VALUES (1934, 'AB7078FB-79F8-43BA-8BA1-F32E7430CB78', '440117', '从化区');
INSERT INTO `sys_region` VALUES (1935, 'AD5ABA88-19A3-4D16-94FF-A0A6288DEC72', '440118', '增城区');
INSERT INTO `sys_region` VALUES (1936, '53B3D29F-8F00-4950-9B75-A7C05191902C', '440200', '韶关市');
INSERT INTO `sys_region` VALUES (1937, '0F7883D0-9C0C-4E0F-B3DD-32994E256305', '440203', '武江区');
INSERT INTO `sys_region` VALUES (1938, '53E05318-6372-4531-964D-EB4F9FB6F5C0', '440204', '浈江区');
INSERT INTO `sys_region` VALUES (1939, 'B3976C75-E971-4E3D-81DF-92F8032CF1CD', '440205', '曲江区');
INSERT INTO `sys_region` VALUES (1940, 'A8993E27-BC17-472A-84DE-9B16FB739780', '440222', '始兴县');
INSERT INTO `sys_region` VALUES (1941, '3DF4ADD1-81D1-493D-9B8E-8AE6B7E96423', '440224', '仁化县');
INSERT INTO `sys_region` VALUES (1942, 'F7C3DC83-A516-4045-A897-40E7DACA65E8', '440229', '翁源县');
INSERT INTO `sys_region` VALUES (1943, '6494E389-F44C-49DC-9435-9D936250DBF6', '440232', '乳源瑶族自治县');
INSERT INTO `sys_region` VALUES (1944, '05C85E42-9200-4981-9458-2F78CDD4639F', '440233', '新丰县');
INSERT INTO `sys_region` VALUES (1945, 'CF78B715-42F2-48E1-AE74-87FA0BC80EEF', '440281', '乐昌市');
INSERT INTO `sys_region` VALUES (1946, '49059A20-6BD7-40AE-A6CB-8E27897A037A', '440282', '南雄市');
INSERT INTO `sys_region` VALUES (1947, 'A43ED66B-F61D-46FC-8735-27B08CEC7BEF', '440300', '深圳市');
INSERT INTO `sys_region` VALUES (1948, 'A4A1A682-36C2-43A7-80BF-227FC4F031D4', '440303', '罗湖区');
INSERT INTO `sys_region` VALUES (1949, 'EEC627FE-5ACD-4160-9716-61EF17B6FB09', '440304', '福田区');
INSERT INTO `sys_region` VALUES (1950, 'E597DD93-FA7E-481C-8B43-F52D6239CD78', '440305', '南山区');
INSERT INTO `sys_region` VALUES (1951, '279E6CFF-A31D-4C72-8970-95EE4AA89E02', '440306', '宝安区');
INSERT INTO `sys_region` VALUES (1952, 'EDEC7A28-3FA7-4262-8BDE-2EFFD6EB0CE3', '440307', '龙岗区');
INSERT INTO `sys_region` VALUES (1953, 'F93713E3-4020-4A53-863F-4FD01D1C0E45', '440308', '盐田区');
INSERT INTO `sys_region` VALUES (1954, '0A2F7848-4049-4749-862D-AF68584F911D', '440309', '龙华区');
INSERT INTO `sys_region` VALUES (1955, '9E6568CC-962F-4003-A8CF-1DCE3A32C771', '440310', '坪山区');
INSERT INTO `sys_region` VALUES (1956, '8C5A4708-E36E-481E-BD0B-764C7416D122', '440311', '光明区');
INSERT INTO `sys_region` VALUES (1957, '9C9E3A20-A299-42BA-9203-41A953696AA1', '440400', '珠海市');
INSERT INTO `sys_region` VALUES (1958, '6DA8DA29-94A9-43DD-83C9-777A1AABD87E', '440402', '香洲区');
INSERT INTO `sys_region` VALUES (1959, '1EAA9BB7-41FD-4EBA-92E1-28EC94EAC5E2', '440403', '斗门区');
INSERT INTO `sys_region` VALUES (1960, 'A0D6BDEF-C5B3-4DA7-B056-CD8CD2F8C9D8', '440404', '金湾区');
INSERT INTO `sys_region` VALUES (1961, '6984792E-A6A3-4C09-AE75-6622E12569D2', '440500', '汕头市');
INSERT INTO `sys_region` VALUES (1962, 'E3ADD8C5-54F2-40C9-9B4F-6C06AE95E564', '440507', '龙湖区');
INSERT INTO `sys_region` VALUES (1963, 'E9DB64CB-E5AB-4A63-BD33-7E5005305DCE', '440511', '金平区');
INSERT INTO `sys_region` VALUES (1964, '183CD8B0-B8CC-49DE-92F6-2A26AFCE0DBC', '440512', '濠江区');
INSERT INTO `sys_region` VALUES (1965, '56074A21-F88E-4FB9-A2D0-1117FEC351B0', '440513', '潮阳区');
INSERT INTO `sys_region` VALUES (1966, 'F91B72FF-E6E5-43EE-A311-52CF287294DB', '440514', '潮南区');
INSERT INTO `sys_region` VALUES (1967, 'FB00F799-326A-4BF7-99B0-A38C43908FA5', '440515', '澄海区');
INSERT INTO `sys_region` VALUES (1968, 'EBAD4530-A929-4FD3-AA2E-314B25CECA43', '440523', '南澳县');
INSERT INTO `sys_region` VALUES (1969, 'A2D9BD61-0E5A-456E-AC75-497CD5AD7A2B', '440600', '佛山市');
INSERT INTO `sys_region` VALUES (1970, '626C98F0-188B-40C7-BC40-86CEAC937221', '440604', '禅城区');
INSERT INTO `sys_region` VALUES (1971, '0AE7274F-8399-4740-8A65-BFAFE7D5FC95', '440605', '南海区');
INSERT INTO `sys_region` VALUES (1972, '7894B7FF-75CB-4B66-B9BA-AE6C246AE525', '440606', '顺德区');
INSERT INTO `sys_region` VALUES (1973, '47C26E5F-3391-4D07-8035-319ABE3959F2', '440607', '三水区');
INSERT INTO `sys_region` VALUES (1974, '0599871A-7123-4D50-B3DD-840DD9D1A4C9', '440608', '高明区');
INSERT INTO `sys_region` VALUES (1975, '5787480A-AC59-4123-8E9F-C58144F3CD4F', '440700', '江门市');
INSERT INTO `sys_region` VALUES (1976, 'CE665135-A4E9-4C0E-922D-CD3514744E1B', '440703', '蓬江区');
INSERT INTO `sys_region` VALUES (1977, 'C61356AD-B96C-4F00-B15C-E196DFF9B762', '440704', '江海区');
INSERT INTO `sys_region` VALUES (1978, '6B327F20-ED04-4EEE-B919-00C5D9FBD075', '440705', '新会区');
INSERT INTO `sys_region` VALUES (1979, '75BE9369-3F6A-4083-BACA-1BBE87D04AE0', '440781', '台山市');
INSERT INTO `sys_region` VALUES (1980, 'E787EDB9-A6A5-489F-A3F4-EAE3C44E9532', '440783', '开平市');
INSERT INTO `sys_region` VALUES (1981, 'F4366741-4F08-4E89-98E8-2BF327EAAF55', '440784', '鹤山市');
INSERT INTO `sys_region` VALUES (1982, '893C595B-99FB-408E-B996-E0B5396A26E4', '440785', '恩平市');
INSERT INTO `sys_region` VALUES (1983, 'EB7C99DC-3137-4C44-9CB6-93FD8448035A', '440800', '湛江市');
INSERT INTO `sys_region` VALUES (1984, '3A5F5E17-1FB5-46DC-AF4B-027ECDDC8FDF', '440802', '赤坎区');
INSERT INTO `sys_region` VALUES (1985, '247D0A68-CFBF-4F1D-AA22-4F35E4624F6A', '440803', '霞山区');
INSERT INTO `sys_region` VALUES (1986, 'BF73AF67-F6BE-44EE-BD24-5EBF8C5AB522', '440804', '坡头区');
INSERT INTO `sys_region` VALUES (1987, 'D1870CC6-4610-4671-950E-A93A495D6626', '440811', '麻章区');
INSERT INTO `sys_region` VALUES (1988, '30EBD6F2-DBAD-4DB5-B73F-F07F73A60F2B', '440823', '遂溪县');
INSERT INTO `sys_region` VALUES (1989, 'B3692C85-29DD-42F0-B13B-23B4499E9884', '440825', '徐闻县');
INSERT INTO `sys_region` VALUES (1990, '4C2F31DE-0811-4721-95A3-AB30CD5C7E48', '440881', '廉江市');
INSERT INTO `sys_region` VALUES (1991, 'FD669738-2961-41F7-A8D0-790965C4E4E2', '440882', '雷州市');
INSERT INTO `sys_region` VALUES (1992, 'EAD4FD25-FD88-45B8-B3ED-5493F42DDCB8', '440883', '吴川市');
INSERT INTO `sys_region` VALUES (1993, '0D952802-9814-4A16-A5E3-E23736A8432A', '440900', '茂名市');
INSERT INTO `sys_region` VALUES (1994, '9C9E3C53-C1DB-49D4-8FA0-8B58688C1BC3', '440902', '茂南区');
INSERT INTO `sys_region` VALUES (1995, 'E77CAACC-5A96-4661-8307-CCFC5432DC52', '440904', '电白区');
INSERT INTO `sys_region` VALUES (1996, '16D4F37B-BE85-4413-9E1D-0E4EC8052B5D', '440981', '高州市');
INSERT INTO `sys_region` VALUES (1997, '95EF96AD-35FE-4227-97AF-7D42E248AD20', '440982', '化州市');
INSERT INTO `sys_region` VALUES (1998, 'B1D11997-2A9C-40B3-9C95-1E4B44E5FF74', '440983', '信宜市');
INSERT INTO `sys_region` VALUES (1999, '49F73A64-0F94-4B51-8031-484D69EC0B32', '441200', '肇庆市');
INSERT INTO `sys_region` VALUES (2000, 'C1F17F13-0D0F-4459-9C4B-DDF813E27B6B', '441202', '端州区');
INSERT INTO `sys_region` VALUES (2001, '99D3CAF5-D282-4633-9371-22B2340A485B', '441203', '鼎湖区');
INSERT INTO `sys_region` VALUES (2002, 'CA8B5B40-2FAD-40EF-B900-DE345C8E33D8', '441204', '高要区');
INSERT INTO `sys_region` VALUES (2003, 'A0998D03-3C6D-48F7-BCE0-AEF1D050B4F6', '441223', '广宁县');
INSERT INTO `sys_region` VALUES (2004, '4F2E6C82-91CC-4D81-8E44-42750D663C6D', '441224', '怀集县');
INSERT INTO `sys_region` VALUES (2005, '47F36E0C-AF2A-4621-8988-818E8C409573', '441225', '封开县');
INSERT INTO `sys_region` VALUES (2006, '3A57DCE6-09E8-4708-851C-EA183F4E724E', '441226', '德庆县');
INSERT INTO `sys_region` VALUES (2007, '503AB288-370F-4549-939E-E4E53B181108', '441284', '四会市');
INSERT INTO `sys_region` VALUES (2008, 'FE8DFFFF-27DE-4085-8F42-AD25C21DBE80', '441300', '惠州市');
INSERT INTO `sys_region` VALUES (2009, '873C3156-586A-439A-A3B0-985AF9566236', '441302', '惠城区');
INSERT INTO `sys_region` VALUES (2010, '350DE8E9-347B-402A-8F12-58BBA039DB18', '441303', '惠阳区');
INSERT INTO `sys_region` VALUES (2011, '2110358C-E109-400A-9D09-C9686332F229', '441322', '博罗县');
INSERT INTO `sys_region` VALUES (2012, 'E0907F80-529A-44DB-8FCE-C788A9FEEDFA', '441323', '惠东县');
INSERT INTO `sys_region` VALUES (2013, '0169D116-FA3D-41A7-98F0-2C3519C297B5', '441324', '龙门县');
INSERT INTO `sys_region` VALUES (2014, 'C93ADB62-711C-42B8-AD14-2E84D0E957C8', '441400', '梅州市');
INSERT INTO `sys_region` VALUES (2015, 'A3B9D1D5-2220-4BC2-AE08-9BB339E89912', '441402', '梅江区');
INSERT INTO `sys_region` VALUES (2016, 'FC97EE7F-DF53-4EFA-9C2D-25F09C6D6AB9', '441403', '梅县区');
INSERT INTO `sys_region` VALUES (2017, '90A0FBDA-4073-4475-972D-36F3B7B72854', '441422', '大埔县');
INSERT INTO `sys_region` VALUES (2018, 'EFD31C69-F74C-42C6-B865-1DFF57FFA157', '441423', '丰顺县');
INSERT INTO `sys_region` VALUES (2019, '31E8CE85-8B5A-48E3-BA45-B211D57CD660', '441424', '五华县');
INSERT INTO `sys_region` VALUES (2020, '220B9156-220A-450B-91AB-4AB2DF15686D', '441426', '平远县');
INSERT INTO `sys_region` VALUES (2021, 'CF81A5D3-6F60-46DD-991A-2E0B0FCBF135', '441427', '蕉岭县');
INSERT INTO `sys_region` VALUES (2022, '0E8D0743-25A8-44FF-99F5-70402E76A300', '441481', '兴宁市');
INSERT INTO `sys_region` VALUES (2023, 'FE1820F6-6455-408B-8937-4A385369F1D5', '441500', '汕尾市');
INSERT INTO `sys_region` VALUES (2024, '8E283229-F739-4ACC-B2F9-F100046B94FB', '441502', '城区');
INSERT INTO `sys_region` VALUES (2025, '0719EC77-3E08-4809-BA16-5A118FA1D8F8', '441521', '海丰县');
INSERT INTO `sys_region` VALUES (2026, '25F9B807-E52E-4D1A-87D6-1193C3F47514', '441523', '陆河县');
INSERT INTO `sys_region` VALUES (2027, '12678A27-17D7-45C2-A91C-31C6E456DC3A', '441581', '陆丰市');
INSERT INTO `sys_region` VALUES (2028, 'AFFC9912-BFAD-4747-A498-B17FEC15108B', '441600', '河源市');
INSERT INTO `sys_region` VALUES (2029, '574DF3D9-1C72-4F5B-AC0B-341358334E07', '441602', '源城区');
INSERT INTO `sys_region` VALUES (2030, '7B7A18CF-3C98-49D6-BEA3-CB607A7E2169', '441621', '紫金县');
INSERT INTO `sys_region` VALUES (2031, '8C4F17B9-A055-4B1A-9561-16BE3DA81387', '441622', '龙川县');
INSERT INTO `sys_region` VALUES (2032, 'D643DFF6-8C77-4637-92B8-EA612969CEAD', '441623', '连平县');
INSERT INTO `sys_region` VALUES (2033, 'CEA6CED5-D18E-4444-B2B5-B7BA284FEE6F', '441624', '和平县');
INSERT INTO `sys_region` VALUES (2034, 'C4AB2424-70CC-49DD-B2C4-9E7516011557', '441625', '东源县');
INSERT INTO `sys_region` VALUES (2035, '46D07D83-49F8-4777-BCD3-C34450AAC2B7', '441700', '阳江市');
INSERT INTO `sys_region` VALUES (2036, 'E9C775F6-291C-4431-B903-59BB47E162F5', '441702', '江城区');
INSERT INTO `sys_region` VALUES (2037, '29AED487-0072-4C94-B9C4-68A631C5C0DC', '441704', '阳东区');
INSERT INTO `sys_region` VALUES (2038, '8344E192-27AC-4111-BAD6-D7F024B5633A', '441721', '阳西县');
INSERT INTO `sys_region` VALUES (2039, '75437111-1EC3-45DE-8398-860063DE02DC', '441781', '阳春市');
INSERT INTO `sys_region` VALUES (2040, 'E33023FD-9E14-46CD-A3D5-45175B1193CC', '441800', '清远市');
INSERT INTO `sys_region` VALUES (2041, '7DEDD35B-8687-452C-A5CB-BD1DE034E234', '441802', '清城区');
INSERT INTO `sys_region` VALUES (2042, '38BEEA36-11A2-4A35-B59C-078404FCEF61', '441803', '清新区');
INSERT INTO `sys_region` VALUES (2043, '053FF210-82ED-4D6F-BBF5-41B1E159CDF9', '441821', '佛冈县');
INSERT INTO `sys_region` VALUES (2044, 'EA1085D2-F6E5-48AE-93E6-9A46E30E8AC0', '441823', '阳山县');
INSERT INTO `sys_region` VALUES (2045, 'E3AEAD62-A8B7-4F9C-994E-F30611590704', '441825', '连山壮族瑶族自治县');
INSERT INTO `sys_region` VALUES (2046, 'FB8C2F41-91D4-487A-AFEF-366014ED986E', '441826', '连南瑶族自治县');
INSERT INTO `sys_region` VALUES (2047, 'FB03950F-F47C-4949-90ED-9F30D983B8EE', '441881', '英德市');
INSERT INTO `sys_region` VALUES (2048, 'C9435CFD-B4E3-403C-B97D-8C144F7501A6', '441882', '连州市');
INSERT INTO `sys_region` VALUES (2049, 'C3A36A7D-E250-45FB-9E6B-33967BDD6291', '441900', '东莞市');
INSERT INTO `sys_region` VALUES (2050, '8302E716-854B-4B27-ABFC-75539DBD15DD', '442000', '中山市');
INSERT INTO `sys_region` VALUES (2051, 'C476DB0A-760E-4CB8-B6A1-FBCC10F7CB19', '445100', '潮州市');
INSERT INTO `sys_region` VALUES (2052, 'DFE327B2-7068-48C1-9751-FE207121B6C1', '445102', '湘桥区');
INSERT INTO `sys_region` VALUES (2053, 'BEF05490-85CE-45CE-AF91-848D0B50A995', '445103', '潮安区');
INSERT INTO `sys_region` VALUES (2054, '75AFB18B-53A4-4721-82BA-7A4318EEFC6A', '445122', '饶平县');
INSERT INTO `sys_region` VALUES (2055, '1CBBFE1C-C471-4305-8CD9-D1FC95354823', '445200', '揭阳市');
INSERT INTO `sys_region` VALUES (2056, '8CE38691-DFCD-4A1D-86EE-CDDB32FDA998', '445202', '榕城区');
INSERT INTO `sys_region` VALUES (2057, 'C0D8C082-AE7A-4DB8-8E4C-3BD98BB46C11', '445203', '揭东区');
INSERT INTO `sys_region` VALUES (2058, 'C1709280-B867-49E2-A295-77486104B34A', '445222', '揭西县');
INSERT INTO `sys_region` VALUES (2059, '58005B76-41A3-412F-8769-30CE41078879', '445224', '惠来县');
INSERT INTO `sys_region` VALUES (2060, '4C443FB9-C5A6-4FE0-985D-51B75625FD0D', '445281', '普宁市');
INSERT INTO `sys_region` VALUES (2061, '9B922DB3-EC81-4C0C-A3B1-C2DCF47765D7', '445300', '云浮市');
INSERT INTO `sys_region` VALUES (2062, 'E7BF5DA9-F55C-411C-8193-718FF94BD973', '445302', '云城区');
INSERT INTO `sys_region` VALUES (2063, 'E25629A0-1B93-4BAE-9A55-8622684406FA', '445303', '云安区');
INSERT INTO `sys_region` VALUES (2064, '4B0A0097-9099-4990-8BFE-980FEA0CF2B4', '445321', '新兴县');
INSERT INTO `sys_region` VALUES (2065, '95B611DD-C503-4CF1-8530-09C56DAC4C49', '445322', '郁南县');
INSERT INTO `sys_region` VALUES (2066, '501AC7E9-DF99-4796-AF88-1D6891DE1449', '445381', '罗定市');
INSERT INTO `sys_region` VALUES (2067, '387B451E-1B55-4784-95BD-6C20E087F7A5', '450000', '广西壮族自治区');
INSERT INTO `sys_region` VALUES (2068, 'FE3BEF77-1947-4677-803D-FCEE62F06F33', '450100', '南宁市');
INSERT INTO `sys_region` VALUES (2069, '6F062E39-B09C-46E6-A70F-3A1CE67388F1', '450102', '兴宁区');
INSERT INTO `sys_region` VALUES (2070, 'D4A76812-7CAA-47AB-8201-2EAD146337A2', '450103', '青秀区');
INSERT INTO `sys_region` VALUES (2071, 'E3AB8BF1-9B3D-4883-B264-01358FC4074B', '450105', '江南区');
INSERT INTO `sys_region` VALUES (2072, '62FC138D-1FDA-4C2A-9763-3E7BBEB4AE2A', '450107', '西乡塘区');
INSERT INTO `sys_region` VALUES (2073, '101A7A6D-7C4B-49B9-9D11-97BA13D0BF79', '450108', '良庆区');
INSERT INTO `sys_region` VALUES (2074, 'F515197A-6F7F-49AB-B22F-4A4FA9639080', '450109', '邕宁区');
INSERT INTO `sys_region` VALUES (2075, '591C0999-9016-4FE1-8BAF-CC82182D2D7A', '450110', '武鸣区');
INSERT INTO `sys_region` VALUES (2076, '40D84F87-9CFB-44CE-A2CA-448D79EE0DE1', '450123', '隆安县');
INSERT INTO `sys_region` VALUES (2077, '2C38AAED-FA34-4171-BE60-C9CB70E428B3', '450124', '马山县');
INSERT INTO `sys_region` VALUES (2078, 'DF4562F8-9B1A-4FC3-9CCF-5375D457DCCA', '450125', '上林县');
INSERT INTO `sys_region` VALUES (2079, 'D40BCCFE-4654-432A-9546-B692E2E96105', '450126', '宾阳县');
INSERT INTO `sys_region` VALUES (2080, '89B55A0C-29CF-4F36-8ABB-C26D85ADD137', '450127', '横县');
INSERT INTO `sys_region` VALUES (2081, 'B3B58303-29F6-40F7-A1EE-4B1E701E1834', '450200', '柳州市');
INSERT INTO `sys_region` VALUES (2082, 'FD218A2A-EC90-456A-B67C-8F25583BFED4', '450202', '城中区');
INSERT INTO `sys_region` VALUES (2083, 'BE047C95-B425-40A8-B064-A0E6032A8B1F', '450203', '鱼峰区');
INSERT INTO `sys_region` VALUES (2084, '0D076BB3-B61C-4E19-9308-2B62FF291987', '450204', '柳南区');
INSERT INTO `sys_region` VALUES (2085, '15BEEB89-A4DF-4C05-9451-5E604DDD1EA3', '450205', '柳北区');
INSERT INTO `sys_region` VALUES (2086, '872129B1-A208-415A-A2DF-1E9C5959AF2A', '450206', '柳江区');
INSERT INTO `sys_region` VALUES (2087, '5BA33871-D612-4E8D-A57C-064136BD0060', '450222', '柳城县');
INSERT INTO `sys_region` VALUES (2088, '164F11A1-C386-4342-9955-9B6FB1EC8F54', '450223', '鹿寨县');
INSERT INTO `sys_region` VALUES (2089, 'BF36A53F-9AF5-4291-B64B-D88E87DD677F', '450224', '融安县');
INSERT INTO `sys_region` VALUES (2090, '6E8236BF-1A94-4D10-BCA9-4BC03BA584D8', '450225', '融水苗族自治县');
INSERT INTO `sys_region` VALUES (2091, '2758DCF3-0963-4B99-8FDA-B4111E026A70', '450226', '三江侗族自治县');
INSERT INTO `sys_region` VALUES (2092, 'BD350B4F-4A3F-4280-9B29-5F1E4B1C6991', '450300', '桂林市');
INSERT INTO `sys_region` VALUES (2093, '0955A906-50C3-4EF5-8A19-CD3CA374E0B2', '450302', '秀峰区');
INSERT INTO `sys_region` VALUES (2094, '19FA7AFB-8DF7-42D2-B552-723BAC2126E1', '450303', '叠彩区');
INSERT INTO `sys_region` VALUES (2095, '987F397F-C519-4BD8-85CD-48B88B4B09FC', '450304', '象山区');
INSERT INTO `sys_region` VALUES (2096, 'A8BFC9AD-E2EC-4661-948B-BD818B137FBE', '450305', '七星区');
INSERT INTO `sys_region` VALUES (2097, '766A0A8A-83EA-4381-BEBE-03E3B5F7834B', '450311', '雁山区');
INSERT INTO `sys_region` VALUES (2098, 'F368E903-AC85-45BB-85CC-28F4A49D8CFE', '450312', '临桂区');
INSERT INTO `sys_region` VALUES (2099, 'FE3586C4-556B-4C1E-9A94-27D765761CCB', '450321', '阳朔县');
INSERT INTO `sys_region` VALUES (2100, '46F7D8BE-E89A-4E25-B4B5-F2B2CA5929FA', '450323', '灵川县');
INSERT INTO `sys_region` VALUES (2101, '01E2364C-6080-48CD-8E3A-DA588C03396A', '450324', '全州县');
INSERT INTO `sys_region` VALUES (2102, '0C02A001-D7DE-45A6-A9E2-D6A0D8D0E4E4', '450325', '兴安县');
INSERT INTO `sys_region` VALUES (2103, 'F5BD2D67-8FA8-4D44-A8EA-6EADFCE475B7', '450326', '永福县');
INSERT INTO `sys_region` VALUES (2104, 'D2C300D2-7C23-4889-AD07-38986BFF336F', '450327', '灌阳县');
INSERT INTO `sys_region` VALUES (2105, '7BDF32D1-B90E-409F-89A2-DF55D9FEEC9B', '450328', '龙胜各族自治县');
INSERT INTO `sys_region` VALUES (2106, 'C80FB3E1-64ED-463D-89BC-02879AC4FE50', '450329', '资源县');
INSERT INTO `sys_region` VALUES (2107, 'E318675A-DB1D-4001-AE72-1F63B58AF215', '450330', '平乐县');
INSERT INTO `sys_region` VALUES (2108, '15325080-02CA-423C-999E-78227435EEC5', '450332', '恭城瑶族自治县');
INSERT INTO `sys_region` VALUES (2109, '85AFF507-8535-498A-9AD6-2ADC54A09F7E', '450381', '荔浦市');
INSERT INTO `sys_region` VALUES (2110, '42BF217C-0E0F-4DA5-86BF-879F114A7A41', '450400', '梧州市');
INSERT INTO `sys_region` VALUES (2111, '17A3C81E-6077-48F4-9457-083EE3283E02', '450403', '万秀区');
INSERT INTO `sys_region` VALUES (2112, 'D285D3B7-F459-4C27-8AE2-DE0546E3E4A0', '450405', '长洲区');
INSERT INTO `sys_region` VALUES (2113, '5D53932B-DF66-4404-8706-8BDB3E7A4F09', '450406', '龙圩区');
INSERT INTO `sys_region` VALUES (2114, '9611698E-A3FC-45F4-8322-ADDAC5265428', '450421', '苍梧县');
INSERT INTO `sys_region` VALUES (2115, 'D24CF42B-F191-4DFF-9318-3F722295A8E9', '450422', '藤县');
INSERT INTO `sys_region` VALUES (2116, '7F6DDC77-5D7A-42E1-9B40-F5D7076452F6', '450423', '蒙山县');
INSERT INTO `sys_region` VALUES (2117, '70069C66-BD5C-4274-95DB-F294CEFA0AC8', '450481', '岑溪市');
INSERT INTO `sys_region` VALUES (2118, 'E833CB21-0D6F-47B8-9854-EC3A271FD433', '450500', '北海市');
INSERT INTO `sys_region` VALUES (2119, 'D05DBCC7-A2F0-468A-A910-4C21006C26AE', '450502', '海城区');
INSERT INTO `sys_region` VALUES (2120, 'E7293111-FB30-475B-BD37-8587265E8828', '450503', '银海区');
INSERT INTO `sys_region` VALUES (2121, '5B895AF4-8BDE-42EF-9C23-9D0E627F7A08', '450512', '铁山港区');
INSERT INTO `sys_region` VALUES (2122, '2ACFD2CF-974A-48E8-84AB-CE63212FD7AF', '450521', '合浦县');
INSERT INTO `sys_region` VALUES (2123, '572D32CD-8BA5-4731-B34D-AF67E45F27CC', '450600', '防城港市');
INSERT INTO `sys_region` VALUES (2124, '4A5073D6-EFBF-46AD-8BB4-E2C6AC3171B4', '450602', '港口区');
INSERT INTO `sys_region` VALUES (2125, '7B5EC878-2F8F-4E62-86FA-DAA6BEAE31E9', '450603', '防城区');
INSERT INTO `sys_region` VALUES (2126, '9CC33FFA-2631-4E95-A28B-D58B9C2144A6', '450621', '上思县');
INSERT INTO `sys_region` VALUES (2127, 'B7ECD324-FB0C-451A-ACC8-89A90D3EE603', '450681', '东兴市');
INSERT INTO `sys_region` VALUES (2128, 'DE49C4AA-3907-4A4A-B02E-C64E19A2A894', '450700', '钦州市');
INSERT INTO `sys_region` VALUES (2129, 'C3DF636B-354F-4764-82E3-301177CA1A31', '450702', '钦南区');
INSERT INTO `sys_region` VALUES (2130, '7585584C-C0DA-4172-85EE-F83CDC1B81ED', '450703', '钦北区');
INSERT INTO `sys_region` VALUES (2131, 'EFE72F50-0D65-4FF9-96D6-4826B425097A', '450721', '灵山县');
INSERT INTO `sys_region` VALUES (2132, '5C98429B-350E-4057-B3DF-34C3DBDBB5A7', '450722', '浦北县');
INSERT INTO `sys_region` VALUES (2133, 'D71E8D3A-D4D5-47C1-B9F8-13659611A50D', '450800', '贵港市');
INSERT INTO `sys_region` VALUES (2134, 'A18A726E-8D09-4931-9384-378A1A7EE62F', '450802', '港北区');
INSERT INTO `sys_region` VALUES (2135, 'A9B8D3B9-375C-43F7-BF27-04A8B61E5717', '450803', '港南区');
INSERT INTO `sys_region` VALUES (2136, '015709E6-8A13-4862-AEE4-85407D2ACF62', '450804', '覃塘区');
INSERT INTO `sys_region` VALUES (2137, '53392B12-BF95-47A1-9859-217D5C7CE244', '450821', '平南县');
INSERT INTO `sys_region` VALUES (2138, 'B06B6E70-3E4F-4750-AD1A-38B5984FEE82', '450881', '桂平市');
INSERT INTO `sys_region` VALUES (2139, '26808E54-CBDA-4976-A003-101A0683D5B9', '450900', '玉林市');
INSERT INTO `sys_region` VALUES (2140, '26AAB094-6670-4B77-9027-60FB82D4577C', '450902', '玉州区');
INSERT INTO `sys_region` VALUES (2141, 'ED396795-E634-4B69-94A3-3A5CCD6AE1BC', '450903', '福绵区');
INSERT INTO `sys_region` VALUES (2142, '48C5A045-5C06-4431-AD50-CF9C78A09081', '450921', '容县');
INSERT INTO `sys_region` VALUES (2143, 'DEADB73C-874B-4A1E-9FD4-3D56E8A767B5', '450922', '陆川县');
INSERT INTO `sys_region` VALUES (2144, '5091B4C9-1D9D-42DD-BA63-50091E961528', '450923', '博白县');
INSERT INTO `sys_region` VALUES (2145, '24CF6BA9-4C33-4AB5-A9A2-C656C58B3ED3', '450924', '兴业县');
INSERT INTO `sys_region` VALUES (2146, 'B880FAC0-8624-4440-B024-80300CB8BAC5', '450981', '北流市');
INSERT INTO `sys_region` VALUES (2147, '9C585C14-F087-4191-AD6B-3B2FB052BE49', '451000', '百色市');
INSERT INTO `sys_region` VALUES (2148, 'DBFEBB18-21AB-4D70-A9C4-5A2F41E66CA5', '451002', '右江区');
INSERT INTO `sys_region` VALUES (2149, '0F28D4D2-ADA7-4649-9B07-C086B464051B', '451003', '田阳区');
INSERT INTO `sys_region` VALUES (2150, '90862331-BD93-4E3A-A755-DD1F102DAE7F', '451022', '田东县');
INSERT INTO `sys_region` VALUES (2151, 'A28E8C3E-F658-439A-A9CD-C8BD5605D716', '451024', '德保县');
INSERT INTO `sys_region` VALUES (2152, 'BEE5F5B4-F22A-4710-B63E-A6291858D535', '451026', '那坡县');
INSERT INTO `sys_region` VALUES (2153, 'AF2B2531-1BC7-428B-BB64-35BCDAAE6C6B', '451027', '凌云县');
INSERT INTO `sys_region` VALUES (2154, '5A898CB1-4648-4811-ABBC-902FC4A60D9E', '451028', '乐业县');
INSERT INTO `sys_region` VALUES (2155, 'ECD6DEFD-72FA-4D58-AEDB-03A4D6C03FB4', '451029', '田林县');
INSERT INTO `sys_region` VALUES (2156, '71AA8981-63CE-45A5-894E-5D6399626D2A', '451030', '西林县');
INSERT INTO `sys_region` VALUES (2157, '414946CA-7430-486C-BB06-788CBFD27500', '451031', '隆林各族自治县');
INSERT INTO `sys_region` VALUES (2158, '4624AB8A-4E6C-46F0-A1A6-B154BD3C7BBE', '451081', '靖西市');
INSERT INTO `sys_region` VALUES (2159, '870364C5-502F-447A-A8F9-D6298ED72284', '451082', '平果市');
INSERT INTO `sys_region` VALUES (2160, '8D88CBCF-48C6-41FD-80E7-BBFA4FC27CFF', '451100', '贺州市');
INSERT INTO `sys_region` VALUES (2161, 'CEE7B3A1-46DE-483A-ABB4-448F0DEB3DFF', '451102', '八步区');
INSERT INTO `sys_region` VALUES (2162, 'C2F55B76-719D-4567-83C0-4FF8036E8023', '451103', '平桂区');
INSERT INTO `sys_region` VALUES (2163, 'DF3B92BD-2334-4A26-A742-61C83B91D76E', '451121', '昭平县');
INSERT INTO `sys_region` VALUES (2164, '01B955D3-7FFE-4F4C-9447-B34E15332AB7', '451122', '钟山县');
INSERT INTO `sys_region` VALUES (2165, '889B3CE5-D32A-4347-B279-883692278AD2', '451123', '富川瑶族自治县');
INSERT INTO `sys_region` VALUES (2166, '5B13A788-CB9D-47B6-83FA-5593AA9E1B6E', '451200', '河池市');
INSERT INTO `sys_region` VALUES (2167, 'F3B940DA-0496-4038-8D01-001D6199A9F2', '451202', '金城江区');
INSERT INTO `sys_region` VALUES (2168, 'D71F7EB0-608A-4BE7-BF6F-54E412D9729A', '451203', '宜州区');
INSERT INTO `sys_region` VALUES (2169, '7E1221A0-A432-46E1-A0A1-0A3CA49C46A0', '451221', '南丹县');
INSERT INTO `sys_region` VALUES (2170, '9F079B9B-006C-42CB-ADEC-38B5D542DAD9', '451222', '天峨县');
INSERT INTO `sys_region` VALUES (2171, 'D03D93FD-7497-4FA1-9ECC-5FC7586FD415', '451223', '凤山县');
INSERT INTO `sys_region` VALUES (2172, '82A0FFAE-ACBB-464C-80DD-B03A0B2E60F4', '451224', '东兰县');
INSERT INTO `sys_region` VALUES (2173, '5CB4FFF9-0027-48C0-A68B-78251B560E9B', '451225', '罗城仫佬族自治县');
INSERT INTO `sys_region` VALUES (2174, '9D8912F0-B515-4849-B7E4-BA1CA57B0D3C', '451226', '环江毛南族自治县');
INSERT INTO `sys_region` VALUES (2175, '3D656F70-FD16-41A8-B040-E6F2824026B6', '451227', '巴马瑶族自治县');
INSERT INTO `sys_region` VALUES (2176, '2CF45029-E6BF-4BCB-AC2B-8EB86FC9C0F5', '451228', '都安瑶族自治县');
INSERT INTO `sys_region` VALUES (2177, 'B0DEFD6B-470F-43D6-A01D-156ECB97DF12', '451229', '大化瑶族自治县');
INSERT INTO `sys_region` VALUES (2178, '2BFE32D9-61CA-4209-B283-477E9E310239', '451300', '来宾市');
INSERT INTO `sys_region` VALUES (2179, '02CBEECE-43A3-4065-BCB8-E27FF15BEE7A', '451302', '兴宾区');
INSERT INTO `sys_region` VALUES (2180, '1685783A-F24C-47B8-BB0C-B511C7323105', '451321', '忻城县');
INSERT INTO `sys_region` VALUES (2181, '6791F75F-EB7C-4428-B7BD-F0AE3A4393A1', '451322', '象州县');
INSERT INTO `sys_region` VALUES (2182, '41E8320E-2DED-4293-9AE5-D670A9CBF3A7', '451323', '武宣县');
INSERT INTO `sys_region` VALUES (2183, 'CF86185D-CD9C-4DE7-B444-7F52820195DE', '451324', '金秀瑶族自治县');
INSERT INTO `sys_region` VALUES (2184, '91C2CDC7-5DD7-4ABD-AF97-122D8C21BAF1', '451381', '合山市');
INSERT INTO `sys_region` VALUES (2185, '23B140C3-444E-4644-8496-3F7CB3B86403', '451400', '崇左市');
INSERT INTO `sys_region` VALUES (2186, '6E7320E0-C9FC-4CF6-B786-4861CC2526C8', '451402', '江州区');
INSERT INTO `sys_region` VALUES (2187, '0CE48030-A4DE-43F7-BD2D-44E242DA1B1A', '451421', '扶绥县');
INSERT INTO `sys_region` VALUES (2188, '112CDF14-0800-41EE-BF28-B7F7673368ED', '451422', '宁明县');
INSERT INTO `sys_region` VALUES (2189, 'DB42DD4F-35E5-4F10-9334-F2AED183E375', '451423', '龙州县');
INSERT INTO `sys_region` VALUES (2190, '1B4448FD-A0D0-44AB-AE5E-201FB75DEF07', '451424', '大新县');
INSERT INTO `sys_region` VALUES (2191, 'EF412820-6592-493C-A87A-A2CA7D42C856', '451425', '天等县');
INSERT INTO `sys_region` VALUES (2192, '9881C94C-F6BA-419A-923F-933E25B00B98', '451481', '凭祥市');
INSERT INTO `sys_region` VALUES (2193, '57788E33-E772-497D-9280-8952EC618AB2', '460000', '海南省');
INSERT INTO `sys_region` VALUES (2194, 'B07927F1-5328-4400-A3D1-2603EB31ED11', '460100', '海口市');
INSERT INTO `sys_region` VALUES (2195, '686A8D9C-8CF0-463D-85E2-50E02F25C077', '460105', '秀英区');
INSERT INTO `sys_region` VALUES (2196, '23766915-61BA-49F1-843A-90C6ADBAED9F', '460106', '龙华区');
INSERT INTO `sys_region` VALUES (2197, '4409D5BA-F61F-44EF-B185-342243FA5CAD', '460107', '琼山区');
INSERT INTO `sys_region` VALUES (2198, '7AFA2D09-A25F-4472-8DDB-1A7FE1ABFE11', '460108', '美兰区');
INSERT INTO `sys_region` VALUES (2199, '9266240A-871F-4801-9BE0-3945FE8C5ECC', '460200', '三亚市');
INSERT INTO `sys_region` VALUES (2200, '10E809EE-AB41-411E-9143-3CC43E097655', '460202', '海棠区');
INSERT INTO `sys_region` VALUES (2201, '6473F4A8-402B-4041-8823-201E63061228', '460203', '吉阳区');
INSERT INTO `sys_region` VALUES (2202, '1A476455-BDBB-4382-BEF5-6DD71D053079', '460204', '天涯区');
INSERT INTO `sys_region` VALUES (2203, '60D146DA-FCAE-4B63-972F-EF4CA6B263C9', '460205', '崖州区');
INSERT INTO `sys_region` VALUES (2204, '96826650-C4D5-42E4-BCB2-BA684780C7C7', '460300', '三沙市');
INSERT INTO `sys_region` VALUES (2205, 'CBEB053B-DE90-4A4C-AC99-9775003E9722', '460321', '西沙群岛');
INSERT INTO `sys_region` VALUES (2206, '2E331C61-1C6B-498A-AC37-4552584EA4FE', '460322', '南沙群岛');
INSERT INTO `sys_region` VALUES (2207, '56B1D08C-BE71-4FEE-B5B7-6595B21E40F9', '460323', '中沙群岛的岛礁及其海域');
INSERT INTO `sys_region` VALUES (2208, 'A5FA00B8-DC58-4233-8F08-1751FBCEC285', '460324', '永乐群岛');
INSERT INTO `sys_region` VALUES (2209, '3767E064-FA5A-4E2E-866C-AE5EAE150518', '460400', '儋州市');
INSERT INTO `sys_region` VALUES (2210, 'D9D2E80F-2989-428C-849B-2575D772BE77', '469001', '五指山市');
INSERT INTO `sys_region` VALUES (2211, '875FEAC8-4C33-42B1-9E37-AD780AB2F876', '469002', '琼海市');
INSERT INTO `sys_region` VALUES (2212, '340EED3E-1A19-4768-A5C7-D421096AB21E', '469005', '文昌市');
INSERT INTO `sys_region` VALUES (2213, 'E89062CD-81B9-416E-A1CA-3A280A04072A', '469006', '万宁市');
INSERT INTO `sys_region` VALUES (2214, 'BC65821C-AD5B-497A-AD1C-F94312910E72', '469007', '东方市');
INSERT INTO `sys_region` VALUES (2215, '164AE73E-41C2-4D80-BD79-970A0A331987', '469021', '定安县');
INSERT INTO `sys_region` VALUES (2216, 'E6E78847-2A45-418A-8CCF-B62ED85A37F5', '469022', '屯昌县');
INSERT INTO `sys_region` VALUES (2217, '5E5BF91C-0CE6-4640-A282-4EC6648A255F', '469023', '澄迈县');
INSERT INTO `sys_region` VALUES (2218, '23947FBB-32C8-4FE7-AE98-39F5318AD8AA', '469024', '临高县');
INSERT INTO `sys_region` VALUES (2219, '06225CD9-EDB4-41FF-9277-06C5EC786130', '469025', '白沙黎族自治县');
INSERT INTO `sys_region` VALUES (2220, '495CCAED-8703-4A59-8D93-160CC0B941F5', '469026', '昌江黎族自治县');
INSERT INTO `sys_region` VALUES (2221, '5FCC4459-0478-4432-8390-7CB82A90D5CF', '469027', '乐东黎族自治县');
INSERT INTO `sys_region` VALUES (2222, '369A3239-FAD8-42B4-BA1C-AC5975BCC06F', '469028', '陵水黎族自治县');
INSERT INTO `sys_region` VALUES (2223, 'AA73F5DF-A495-45BF-814F-3016A8CFD28B', '469029', '保亭黎族苗族自治县');
INSERT INTO `sys_region` VALUES (2224, '685D20C2-4FC4-4E72-AD2D-879041A1EADA', '469030', '琼中黎族苗族自治县');
INSERT INTO `sys_region` VALUES (2225, 'E259D4B7-7DC2-4F24-BFC6-08F86342B067', '500000', '重庆市');
INSERT INTO `sys_region` VALUES (2226, '96F0439E-5454-4405-9866-26626F3BDAD6', '500101', '万州区');
INSERT INTO `sys_region` VALUES (2227, '6C0DF730-7F41-4450-AB24-F99E7F405497', '500102', '涪陵区');
INSERT INTO `sys_region` VALUES (2228, 'B393F791-1CE6-4D04-97D9-60558B1FAF3E', '500103', '渝中区');
INSERT INTO `sys_region` VALUES (2229, 'B090D09B-0C4C-4790-B893-2F3BB61DC042', '500104', '大渡口区');
INSERT INTO `sys_region` VALUES (2230, 'D90178D6-669F-4122-8051-F3268A2274DC', '500105', '江北区');
INSERT INTO `sys_region` VALUES (2231, '3CE949B2-3F41-436B-B789-ECE5809943E2', '500106', '沙坪坝区');
INSERT INTO `sys_region` VALUES (2232, 'C8C0763E-6C5D-4CE7-BC88-471A35DE7B6A', '500107', '九龙坡区');
INSERT INTO `sys_region` VALUES (2233, '38B04499-D449-4710-89FE-76B6E21E7852', '500108', '南岸区');
INSERT INTO `sys_region` VALUES (2234, '303012F2-6494-4B26-BEF6-98799332F213', '500109', '北碚区');
INSERT INTO `sys_region` VALUES (2235, 'B76C13A1-FE20-435F-BAD1-4FDB36D56519', '500110', '綦江区');
INSERT INTO `sys_region` VALUES (2236, '7AEEC6F1-1153-4B6E-B363-A4BDFF657995', '500111', '大足区');
INSERT INTO `sys_region` VALUES (2237, 'CC63AFD1-683E-49BB-8DAC-BCF5A35A90C3', '500112', '渝北区');
INSERT INTO `sys_region` VALUES (2238, 'C03789ED-9130-4901-BE3D-65C0A10B17F8', '500113', '巴南区');
INSERT INTO `sys_region` VALUES (2239, '8B2E6BC1-AD63-4719-BCD4-7A5D3B1608B5', '500114', '黔江区');
INSERT INTO `sys_region` VALUES (2240, '95FDC65B-F997-4EDA-988A-1778080E1332', '500115', '长寿区');
INSERT INTO `sys_region` VALUES (2241, 'BAFF4EFC-D54A-477E-86FF-FE811073280A', '500116', '江津区');
INSERT INTO `sys_region` VALUES (2242, '7F08ED15-0221-4621-BB83-BE03056F4BC5', '500117', '合川区');
INSERT INTO `sys_region` VALUES (2243, '99828DEB-3A90-4B86-803B-C475521D7C54', '500118', '永川区');
INSERT INTO `sys_region` VALUES (2244, 'E0D07B70-2DA0-431B-867B-4960932657BC', '500119', '南川区');
INSERT INTO `sys_region` VALUES (2245, '66254040-D974-4B2A-8091-4C37F2BDAF94', '500120', '璧山区');
INSERT INTO `sys_region` VALUES (2246, '0979EF12-13FB-405C-BC02-E9FC7DC75CC0', '500151', '铜梁区');
INSERT INTO `sys_region` VALUES (2247, '690DF3F0-66CA-47A4-B1B8-B9FC77D92AFF', '500152', '潼南区');
INSERT INTO `sys_region` VALUES (2248, '6CEF131B-54EA-409E-A57B-E9532C81BBD8', '500153', '荣昌区');
INSERT INTO `sys_region` VALUES (2249, '943F4E94-CA68-4FA4-A867-1A52D962226E', '500154', '开州区');
INSERT INTO `sys_region` VALUES (2250, '511B6DE0-B139-45AC-A753-2FEF6D9C0AD0', '500155', '梁平区');
INSERT INTO `sys_region` VALUES (2251, '8BBE093F-46C6-41B1-B231-B3F673905D20', '500156', '武隆区');
INSERT INTO `sys_region` VALUES (2252, '04187D4B-4AFE-43F6-AB80-0BCFF6ADA1B7', '500229', '城口县');
INSERT INTO `sys_region` VALUES (2253, 'CE1D3FD3-AC54-4252-84E0-734DCFF7C40C', '500230', '丰都县');
INSERT INTO `sys_region` VALUES (2254, '43201151-045A-418D-877B-9799A4914E0C', '500231', '垫江县');
INSERT INTO `sys_region` VALUES (2255, 'E706661C-ACE9-45D7-853F-B5D28F1CE44D', '500233', '忠县');
INSERT INTO `sys_region` VALUES (2256, '68A0D5FA-E522-4325-A5AD-0B6011DF261B', '500235', '云阳县');
INSERT INTO `sys_region` VALUES (2257, 'D90341BA-6C55-484D-80F0-6CFA9AF48A14', '500236', '奉节县');
INSERT INTO `sys_region` VALUES (2258, '4055E2AD-ED60-42C5-82DE-1F15BBB71535', '500237', '巫山县');
INSERT INTO `sys_region` VALUES (2259, '0DB53EF9-D7B0-449F-9D73-3CAABE2D8E5E', '500238', '巫溪县');
INSERT INTO `sys_region` VALUES (2260, '11F9523C-90C4-4540-953A-2C12B5FB3BA3', '500240', '石柱土家族自治县');
INSERT INTO `sys_region` VALUES (2261, '13D6595B-32A6-461E-B6A3-264D38D77049', '500241', '秀山土家族苗族自治县');
INSERT INTO `sys_region` VALUES (2262, 'EB07E4BF-CB55-4EE8-88A0-E1F67CCDB714', '500242', '酉阳土家族苗族自治县');
INSERT INTO `sys_region` VALUES (2263, '1A7B61EB-84FD-4B6C-BD55-094FA467C2EC', '500243', '彭水苗族土家族自治县');
INSERT INTO `sys_region` VALUES (2264, '5E71B3D3-57C9-41DB-B4CE-0BC20BCB6A3D', '510000', '四川省');
INSERT INTO `sys_region` VALUES (2265, '505F36C1-071D-441C-BDD9-4269625E20EA', '510100', '成都市');
INSERT INTO `sys_region` VALUES (2266, '1077A864-3A55-4D59-B7FE-6039CCF924E1', '510104', '锦江区');
INSERT INTO `sys_region` VALUES (2267, '1E0B763D-79B3-4036-A9B9-C7F3F77A23AB', '510105', '青羊区');
INSERT INTO `sys_region` VALUES (2268, '4D319497-95C2-40D7-BC0D-D68E408FD9B6', '510106', '金牛区');
INSERT INTO `sys_region` VALUES (2269, '53F777AF-053D-4A74-AD51-0190F865BF11', '510107', '武侯区');
INSERT INTO `sys_region` VALUES (2270, '923B24B7-4A8F-429C-8702-891EE2800998', '510108', '成华区');
INSERT INTO `sys_region` VALUES (2271, '4991360E-6DB1-4403-913E-D17A582DBBAC', '510112', '龙泉驿区');
INSERT INTO `sys_region` VALUES (2272, '9E30A12A-FDFB-4444-A8AA-61E3379BE280', '510113', '青白江区');
INSERT INTO `sys_region` VALUES (2273, '44FE70B0-F0D4-4A22-8799-600F900BD589', '510114', '新都区');
INSERT INTO `sys_region` VALUES (2274, 'D01DF206-E60A-4D5B-897F-D2EF428772D2', '510115', '温江区');
INSERT INTO `sys_region` VALUES (2275, '69E1554F-065F-4644-86BA-AD71FF711DDB', '510116', '双流区');
INSERT INTO `sys_region` VALUES (2276, '90EE283C-790D-4EBA-A463-56B76C19BBAE', '510117', '郫都区');
INSERT INTO `sys_region` VALUES (2277, '317B145F-A3FC-4685-9670-DA60CBE8D506', '510121', '金堂县');
INSERT INTO `sys_region` VALUES (2278, '4F1B9766-120D-4FD5-A300-2FBD1094054A', '510129', '大邑县');
INSERT INTO `sys_region` VALUES (2279, 'C7967896-2AA4-44CB-A0D1-E71704C32FA6', '510131', '蒲江县');
INSERT INTO `sys_region` VALUES (2280, '7370EB7D-3E3D-49CC-B89B-6344958D0E35', '510132', '新津县');
INSERT INTO `sys_region` VALUES (2281, '13E1857D-B473-4E74-957D-D96A15D3AAD6', '510181', '都江堰市');
INSERT INTO `sys_region` VALUES (2282, '3DCED84F-2021-4426-B084-01C167567AD4', '510182', '彭州市');
INSERT INTO `sys_region` VALUES (2283, 'E8F4F5EE-307C-4C71-AC2A-5BEBF9CBFF2A', '510183', '邛崃市');
INSERT INTO `sys_region` VALUES (2284, '02449C0F-4275-498A-94E6-D83B295DB61C', '510184', '崇州市');
INSERT INTO `sys_region` VALUES (2285, '5CDEF723-C0A5-44CC-866B-3DAB59C9D62B', '510185', '简阳市');
INSERT INTO `sys_region` VALUES (2286, '76856025-65AA-43F2-BD0A-AA94B6DF13EB', '510300', '自贡市');
INSERT INTO `sys_region` VALUES (2287, 'FC2D206D-A3B6-4EE9-94DD-2DB9C98B32DC', '510302', '自流井区');
INSERT INTO `sys_region` VALUES (2288, 'E7B6F5A0-39B8-45D1-ABA9-F339EC134F8E', '510303', '贡井区');
INSERT INTO `sys_region` VALUES (2289, 'A8FEF4D2-B85E-4419-8E19-3495BCAE6B2A', '510304', '大安区');
INSERT INTO `sys_region` VALUES (2290, '189314D0-85B4-4111-94E9-C77935FFFD44', '510311', '沿滩区');
INSERT INTO `sys_region` VALUES (2291, '9CC26E05-E65D-4FD5-AB22-A7BEA3265197', '510321', '荣县');
INSERT INTO `sys_region` VALUES (2292, 'E0F801DA-99A8-47E6-A40E-2ED27599B169', '510322', '富顺县');
INSERT INTO `sys_region` VALUES (2293, '35C19F9B-8867-49F9-875F-B4EA55E4A05B', '510400', '攀枝花市');
INSERT INTO `sys_region` VALUES (2294, 'E3FE42F3-EFB5-4BE2-87D2-F349B32541D2', '510402', '东区');
INSERT INTO `sys_region` VALUES (2295, 'D49AEF77-0546-49F9-A9AB-C07690210F45', '510403', '西区');
INSERT INTO `sys_region` VALUES (2296, 'A8F2A4BC-1449-40F9-86EA-C4CB6BC035B3', '510411', '仁和区');
INSERT INTO `sys_region` VALUES (2297, 'EBBE78FC-FB24-4001-BA5C-AA20DF611C2E', '510421', '米易县');
INSERT INTO `sys_region` VALUES (2298, 'DC508A9C-9075-45E8-9620-1DB0F980B9B6', '510422', '盐边县');
INSERT INTO `sys_region` VALUES (2299, '0C0AE5E7-3484-4B1D-B0F9-C48E1BD1DA85', '510500', '泸州市');
INSERT INTO `sys_region` VALUES (2300, 'AC98E8A9-CF0B-46F4-B4F3-8058BD3BAF73', '510502', '江阳区');
INSERT INTO `sys_region` VALUES (2301, 'FACB71DE-1757-4284-958E-EA2550CEF6C6', '510503', '纳溪区');
INSERT INTO `sys_region` VALUES (2302, 'C3053877-B133-4942-8614-EB53D9C0406B', '510504', '龙马潭区');
INSERT INTO `sys_region` VALUES (2303, '31D9A78E-FABD-42D9-8847-0470CEBA455F', '510521', '泸县');
INSERT INTO `sys_region` VALUES (2304, '83D71BFC-F478-474C-8C65-01AAA20F3008', '510522', '合江县');
INSERT INTO `sys_region` VALUES (2305, '07BAE596-9336-4272-A46B-F1E181CBC55E', '510524', '叙永县');
INSERT INTO `sys_region` VALUES (2306, 'E82B0E05-B473-40C0-8932-615217C1FA2D', '510525', '古蔺县');
INSERT INTO `sys_region` VALUES (2307, 'FC724671-5227-4009-9487-C54A270D36EC', '510600', '德阳市');
INSERT INTO `sys_region` VALUES (2308, '9350E8BD-8902-4B48-8F6F-564519188A2D', '510603', '旌阳区');
INSERT INTO `sys_region` VALUES (2309, '44059603-A97F-4671-9352-1E313135F4CB', '510604', '罗江区');
INSERT INTO `sys_region` VALUES (2310, '8423396C-F865-4410-A121-7367B2C9354E', '510623', '中江县');
INSERT INTO `sys_region` VALUES (2311, 'BDDD1F00-EFD0-4727-AAD5-84D77C08437A', '510681', '广汉市');
INSERT INTO `sys_region` VALUES (2312, '6ECE0584-7D83-4344-9B46-259B77020F8E', '510682', '什邡市');
INSERT INTO `sys_region` VALUES (2313, 'C26E1444-6028-4CB4-B349-BE8B7D6FF666', '510683', '绵竹市');
INSERT INTO `sys_region` VALUES (2314, '0C201A28-2803-458A-A68B-3A1901E4AF8D', '510700', '绵阳市');
INSERT INTO `sys_region` VALUES (2315, '7592FBF0-3EC0-447F-82AD-60CC09B5AD24', '510703', '涪城区');
INSERT INTO `sys_region` VALUES (2316, '3E369927-EFCD-4C6B-B8A8-F350E0E93347', '510704', '游仙区');
INSERT INTO `sys_region` VALUES (2317, 'BABC45BB-09F4-4C2F-9219-84B8D130BBC4', '510705', '安州区');
INSERT INTO `sys_region` VALUES (2318, '0DB50797-5D7C-4290-9967-A30E7D1ED26B', '510722', '三台县');
INSERT INTO `sys_region` VALUES (2319, '23B5ACF6-DF04-4D4D-87E1-A3C6EF56243B', '510723', '盐亭县');
INSERT INTO `sys_region` VALUES (2320, '02FE5137-324D-4DED-A52A-A42EB30BA422', '510725', '梓潼县');
INSERT INTO `sys_region` VALUES (2321, 'E21A6785-0C77-4FC2-B961-57998F90E3B5', '510726', '北川羌族自治县');
INSERT INTO `sys_region` VALUES (2322, '28005C14-F49F-404F-B8A9-3905590CCFA4', '510727', '平武县');
INSERT INTO `sys_region` VALUES (2323, 'C3CC03D0-81D5-4EBE-A0DD-3ED332985E01', '510781', '江油市');
INSERT INTO `sys_region` VALUES (2324, '2E4F0394-40E7-420D-BE9C-09834586D2AC', '510800', '广元市');
INSERT INTO `sys_region` VALUES (2325, 'DC7C9932-B4E3-43C5-B02E-F37C11655421', '510802', '利州区');
INSERT INTO `sys_region` VALUES (2326, '55C64852-B715-4A9C-A066-7048B71782D0', '510811', '昭化区');
INSERT INTO `sys_region` VALUES (2327, '40FAC668-A352-4A2D-A692-F2DEF50D9C64', '510812', '朝天区');
INSERT INTO `sys_region` VALUES (2328, '3ACAD27D-1294-45EB-9E97-A67DED033B5D', '510821', '旺苍县');
INSERT INTO `sys_region` VALUES (2329, '77C6828E-A362-4994-89A9-DCA0BB0EC891', '510822', '青川县');
INSERT INTO `sys_region` VALUES (2330, '8FFCB009-5531-4FCF-AAA1-C2981FAC745B', '510823', '剑阁县');
INSERT INTO `sys_region` VALUES (2331, '3A169191-5E10-47DF-8090-4AD5B3D0FA67', '510824', '苍溪县');
INSERT INTO `sys_region` VALUES (2332, 'A4886A6D-A27B-46A9-8E99-C891CA819415', '510900', '遂宁市');
INSERT INTO `sys_region` VALUES (2333, 'FE8680B5-DCE3-4A48-86C8-C8807FC9749F', '510903', '船山区');
INSERT INTO `sys_region` VALUES (2334, '36A18FA0-4E3F-447D-999C-128149257404', '510904', '安居区');
INSERT INTO `sys_region` VALUES (2335, '636485AC-9849-48FB-85C2-3BF0B0BED2E0', '510921', '蓬溪县');
INSERT INTO `sys_region` VALUES (2336, '58106963-65F4-4773-AD83-D78FAAA81F82', '510923', '大英县');
INSERT INTO `sys_region` VALUES (2337, '3D8F9010-CCFF-42BD-95AC-E6DE56FBC540', '510981', '射洪市');
INSERT INTO `sys_region` VALUES (2338, '641A9E4D-2C97-41BD-B074-AA170774D0D0', '511000', '内江市');
INSERT INTO `sys_region` VALUES (2339, '8946E9F1-888F-4EC8-A1CB-0237B7B576BF', '511002', '市中区');
INSERT INTO `sys_region` VALUES (2340, '4C5CB5EB-F7E0-460A-916D-1085FE22B4C3', '511011', '东兴区');
INSERT INTO `sys_region` VALUES (2341, 'E0C57373-E374-4446-BF3A-246173966168', '511024', '威远县');
INSERT INTO `sys_region` VALUES (2342, '0D5490F0-F843-47D3-B941-81754F937ECE', '511025', '资中县');
INSERT INTO `sys_region` VALUES (2343, 'F328CA2C-F673-4CF4-A136-5FD78EFC12FF', '511083', '隆昌市');
INSERT INTO `sys_region` VALUES (2344, 'C73F5E3F-231A-433B-8574-9A6654977A1F', '511100', '乐山市');
INSERT INTO `sys_region` VALUES (2345, '5687C6DF-C089-4791-AF23-066D7034775D', '511102', '市中区');
INSERT INTO `sys_region` VALUES (2346, 'F520CC95-3117-4DBE-98D5-D4228123C8C4', '511111', '沙湾区');
INSERT INTO `sys_region` VALUES (2347, '95B30B32-F64F-46D4-A46D-A76EDE0C6189', '511112', '五通桥区');
INSERT INTO `sys_region` VALUES (2348, 'E6976351-EF9A-4FB2-8EBC-78765410E345', '511113', '金口河区');
INSERT INTO `sys_region` VALUES (2349, 'F26EBDB6-4339-42EC-9FB9-C86A27D983E8', '511123', '犍为县');
INSERT INTO `sys_region` VALUES (2350, '19EA2796-0DDB-466B-87FD-8C768DB10EEA', '511124', '井研县');
INSERT INTO `sys_region` VALUES (2351, '513CD3E9-A3E6-4FA9-B905-41621FE7E7C9', '511126', '夹江县');
INSERT INTO `sys_region` VALUES (2352, 'DF229F41-13ED-435A-BEFD-9779B65F160D', '511129', '沐川县');
INSERT INTO `sys_region` VALUES (2353, 'D68AA0D0-4BDB-4752-AE28-8AFAE700F8ED', '511132', '峨边彝族自治县');
INSERT INTO `sys_region` VALUES (2354, '00BC90B1-E51D-4743-9F45-94C64A35943F', '511133', '马边彝族自治县');
INSERT INTO `sys_region` VALUES (2355, 'CF3AF980-419F-4ED8-98F6-A604FFE0BE2A', '511181', '峨眉山市');
INSERT INTO `sys_region` VALUES (2356, '9A90E477-AEEE-48BE-A140-FA7B8B524E1A', '511300', '南充市');
INSERT INTO `sys_region` VALUES (2357, 'E2AD7A8E-E4ED-434B-8CC8-50EB1EDF583C', '511302', '顺庆区');
INSERT INTO `sys_region` VALUES (2358, '1A2B9225-1C77-4C44-8A7A-1C0B78E45BC6', '511303', '高坪区');
INSERT INTO `sys_region` VALUES (2359, '357071DC-1C9C-49C1-BA60-78F92A26FC8C', '511304', '嘉陵区');
INSERT INTO `sys_region` VALUES (2360, '267C7628-5943-47D4-91A8-D2E909E78B35', '511321', '南部县');
INSERT INTO `sys_region` VALUES (2361, '8287A081-4093-42A3-BA46-6D004EE40663', '511322', '营山县');
INSERT INTO `sys_region` VALUES (2362, '91141C97-0D74-4558-A287-81B79102BDC7', '511323', '蓬安县');
INSERT INTO `sys_region` VALUES (2363, '0E411F09-24E7-4240-98FA-9C2A9E596FB6', '511324', '仪陇县');
INSERT INTO `sys_region` VALUES (2364, '091869E7-1B8A-487C-9CEA-1632D89A89A6', '511325', '西充县');
INSERT INTO `sys_region` VALUES (2365, 'FF3FD33F-0A08-418B-8278-D64D07AB061D', '511381', '阆中市');
INSERT INTO `sys_region` VALUES (2366, 'F951EE61-C5F2-4B59-9985-3DA64B7EC0FF', '511400', '眉山市');
INSERT INTO `sys_region` VALUES (2367, '9B3DA315-1BEF-41E5-BAA2-AB563C3265C0', '511402', '东坡区');
INSERT INTO `sys_region` VALUES (2368, '22F14E03-9890-4EAF-AC90-F3BB5000FBD9', '511403', '彭山区');
INSERT INTO `sys_region` VALUES (2369, 'E509FBDF-8615-48D4-A36E-8D362F133B01', '511421', '仁寿县');
INSERT INTO `sys_region` VALUES (2370, '304246CA-290C-4402-8AB4-09CD2B8B018C', '511423', '洪雅县');
INSERT INTO `sys_region` VALUES (2371, '3D54717D-56C8-4364-BE3D-74D5AAFB990B', '511424', '丹棱县');
INSERT INTO `sys_region` VALUES (2372, '9C31C09C-E4B5-40E3-A09B-DE98195D312D', '511425', '青神县');
INSERT INTO `sys_region` VALUES (2373, '12D7E22E-9021-4045-8C38-826BCF733AD1', '511500', '宜宾市');
INSERT INTO `sys_region` VALUES (2374, 'A4606856-82A0-4BE5-B40E-398578D2C91A', '511502', '翠屏区');
INSERT INTO `sys_region` VALUES (2375, '37E722A7-1669-464A-B745-CF9D05F45663', '511503', '南溪区');
INSERT INTO `sys_region` VALUES (2376, 'E7752A8A-18D6-4CB7-8D8F-61BF7238EAC8', '511504', '叙州区');
INSERT INTO `sys_region` VALUES (2377, 'A11101C8-CFB2-43A1-919D-9F0806099316', '511523', '江安县');
INSERT INTO `sys_region` VALUES (2378, '39B3DC41-2243-45AD-8864-A12A1BEE8F78', '511524', '长宁县');
INSERT INTO `sys_region` VALUES (2379, '1CE29BFC-154E-4FDA-B05B-95561425D4B1', '511525', '高县');
INSERT INTO `sys_region` VALUES (2380, 'A17FB577-BCC8-44B5-9B9C-A2E93524458E', '511526', '珙县');
INSERT INTO `sys_region` VALUES (2381, 'D5FCD922-138E-4031-95C7-41C798FDAAA1', '511527', '筠连县');
INSERT INTO `sys_region` VALUES (2382, 'CB3B3796-FDD3-474A-B21F-C00EB4E6C631', '511528', '兴文县');
INSERT INTO `sys_region` VALUES (2383, 'EC5A3598-7253-4C51-8A81-DAAFCFB76DB5', '511529', '屏山县');
INSERT INTO `sys_region` VALUES (2384, '2FA519CC-03E8-4786-A445-14A782BF4F99', '511600', '广安市');
INSERT INTO `sys_region` VALUES (2385, 'A3612AB1-C9FF-456C-B669-C3EF8BD6860E', '511602', '广安区');
INSERT INTO `sys_region` VALUES (2386, 'C27753BA-00AD-4B4E-864C-D23376F7CAAC', '511603', '前锋区');
INSERT INTO `sys_region` VALUES (2387, '0069406B-D71B-45F2-8D3F-4D2468309D94', '511621', '岳池县');
INSERT INTO `sys_region` VALUES (2388, '3CA972D7-E489-4DF9-BB44-38A5BBAD83F5', '511622', '武胜县');
INSERT INTO `sys_region` VALUES (2389, 'EE4400F7-2D57-43E0-A2B1-009DEF953F51', '511623', '邻水县');
INSERT INTO `sys_region` VALUES (2390, '4A389B8B-723F-443F-9281-9F020115AD15', '511681', '华蓥市');
INSERT INTO `sys_region` VALUES (2391, 'FD2BDC0E-1769-4A9A-A677-64B33BC3ABC4', '511700', '达州市');
INSERT INTO `sys_region` VALUES (2392, '82BCD74F-089B-41AA-A6E8-FE0A9F5F45EE', '511702', '通川区');
INSERT INTO `sys_region` VALUES (2393, '07A6B568-0F68-4025-8D60-7802567E0C1A', '511703', '达川区');
INSERT INTO `sys_region` VALUES (2394, '6FFE7F32-AA2E-4779-8C31-CB5F5B4C15CB', '511722', '宣汉县');
INSERT INTO `sys_region` VALUES (2395, '98495518-4031-40EC-BE8B-83EEA19CCDF4', '511723', '开江县');
INSERT INTO `sys_region` VALUES (2396, '8774E6B9-79A6-4B0D-8FD7-FCCBCB0F8D96', '511724', '大竹县');
INSERT INTO `sys_region` VALUES (2397, '7449E937-CCDD-45E7-8A95-DE66738422A3', '511725', '渠县');
INSERT INTO `sys_region` VALUES (2398, 'AC3B4306-D2F3-455A-AD2E-FBCE532FCCD6', '511781', '万源市');
INSERT INTO `sys_region` VALUES (2399, '6774541E-5507-4244-BC53-67F139D83FA5', '511800', '雅安市');
INSERT INTO `sys_region` VALUES (2400, 'A6D46AF4-72EF-4F9D-8D55-4B54DD43E192', '511802', '雨城区');
INSERT INTO `sys_region` VALUES (2401, 'CF2B1769-965F-479A-BF22-96F0DAC88CC8', '511803', '名山区');
INSERT INTO `sys_region` VALUES (2402, '1594F293-BC82-48CE-86FB-8B970676DF12', '511822', '荥经县');
INSERT INTO `sys_region` VALUES (2403, '69CE7C66-770F-401A-95D0-8CB7F4CB2E56', '511823', '汉源县');
INSERT INTO `sys_region` VALUES (2404, '92C4EAFD-F50E-46B6-B67B-C53D27FD2DC7', '511824', '石棉县');
INSERT INTO `sys_region` VALUES (2405, 'CACE2E1C-B3D7-4CFE-8889-DF30C4A5F0A9', '511825', '天全县');
INSERT INTO `sys_region` VALUES (2406, '0AD778BD-E473-4BD1-8D78-F2C2A86D1CD7', '511826', '芦山县');
INSERT INTO `sys_region` VALUES (2407, '9B8331EE-FD7F-482B-9731-78831D00C424', '511827', '宝兴县');
INSERT INTO `sys_region` VALUES (2408, '72028026-259E-4C6C-8E75-13D15D1B92F6', '511900', '巴中市');
INSERT INTO `sys_region` VALUES (2409, 'A11BA73D-945F-4DCB-92CE-50DBC0CBA4EE', '511902', '巴州区');
INSERT INTO `sys_region` VALUES (2410, '1DE406C6-D79C-46E8-B94C-107A1AEE4212', '511903', '恩阳区');
INSERT INTO `sys_region` VALUES (2411, 'BF76AAA2-D470-487B-8F98-52E7838A99F9', '511921', '通江县');
INSERT INTO `sys_region` VALUES (2412, 'C3B1B11C-3BF2-4413-BA04-E06F3D856C56', '511922', '南江县');
INSERT INTO `sys_region` VALUES (2413, 'FBE8B125-BC6A-4193-B4F6-514A30E251BD', '511923', '平昌县');
INSERT INTO `sys_region` VALUES (2414, 'D9F30980-395F-4E36-BC32-D434AB60D052', '512000', '资阳市');
INSERT INTO `sys_region` VALUES (2415, '7AE290C8-E16C-4B3D-B232-3F2CA94709D0', '512002', '雁江区');
INSERT INTO `sys_region` VALUES (2416, 'D448E5C0-724B-433F-9724-0D1382F07E1C', '512021', '安岳县');
INSERT INTO `sys_region` VALUES (2417, 'B757B9CC-25A2-459A-AC28-28B959E4F582', '512022', '乐至县');
INSERT INTO `sys_region` VALUES (2418, 'F78624E5-D47B-459E-8339-3DF18CBE79F0', '513200', '阿坝藏族羌族自治州');
INSERT INTO `sys_region` VALUES (2419, '88EBA691-E909-4A77-9392-660DAD665A4A', '513201', '马尔康市');
INSERT INTO `sys_region` VALUES (2420, '0ABE11DA-1225-4EB6-B882-FBFA682F1686', '513221', '汶川县');
INSERT INTO `sys_region` VALUES (2421, 'E7D4E056-4094-4C1C-AF24-830953163E9E', '513222', '理县');
INSERT INTO `sys_region` VALUES (2422, '8C5B97D7-0918-4369-B0DD-CE0196A529FB', '513223', '茂县');
INSERT INTO `sys_region` VALUES (2423, '2A2F08AC-869D-414D-AA45-8358B41D5737', '513224', '松潘县');
INSERT INTO `sys_region` VALUES (2424, 'A69307AD-F495-4590-A280-74266C7B5276', '513225', '九寨沟县');
INSERT INTO `sys_region` VALUES (2425, 'AA5BEC94-350A-4070-80D3-56A087C1E008', '513226', '金川县');
INSERT INTO `sys_region` VALUES (2426, 'C86FD2FA-9B18-49D0-AA86-4148659A4967', '513227', '小金县');
INSERT INTO `sys_region` VALUES (2427, 'B8D881A7-218D-4270-9776-5B11EAF31A00', '513228', '黑水县');
INSERT INTO `sys_region` VALUES (2428, '8386842A-047A-4126-82AF-830C6D4784A6', '513230', '壤塘县');
INSERT INTO `sys_region` VALUES (2429, 'DA6F7D6E-2A9E-413D-94BF-88D0CE752DF1', '513231', '阿坝县');
INSERT INTO `sys_region` VALUES (2430, '84CA90BB-FA7F-4E92-9FD8-13BABA3A7C12', '513232', '若尔盖县');
INSERT INTO `sys_region` VALUES (2431, '47040D8D-8F36-4903-BB1F-E39980CD6BBC', '513233', '红原县');
INSERT INTO `sys_region` VALUES (2432, '95F59AA4-C8CE-41DA-B4F3-77FA07C60BD1', '513300', '甘孜藏族自治州');
INSERT INTO `sys_region` VALUES (2433, '4B30E7A5-4D5B-4494-BCA9-4C687003EF33', '513301', '康定市');
INSERT INTO `sys_region` VALUES (2434, '90C468A5-B56E-47CB-AB53-B8E03CA395C1', '513322', '泸定县');
INSERT INTO `sys_region` VALUES (2435, '53C10499-1E6F-4437-B760-B974EF6FF7C1', '513323', '丹巴县');
INSERT INTO `sys_region` VALUES (2436, 'C02BF027-8652-4E6E-8154-C0A30DE4430A', '513324', '九龙县');
INSERT INTO `sys_region` VALUES (2437, '0193507C-6538-4E1E-933C-7273501043B7', '513325', '雅江县');
INSERT INTO `sys_region` VALUES (2438, '4408681E-7404-4724-87BC-F0A8D5E49583', '513326', '道孚县');
INSERT INTO `sys_region` VALUES (2439, '2459C142-10CE-4433-B83C-DE92CCC5E9FA', '513327', '炉霍县');
INSERT INTO `sys_region` VALUES (2440, '83B11384-5136-4DA4-8AB3-E1611ED49C68', '513328', '甘孜县');
INSERT INTO `sys_region` VALUES (2441, '14552F9B-3734-4045-8546-A5469818525B', '513329', '新龙县');
INSERT INTO `sys_region` VALUES (2442, '80F058B4-7CD3-42CB-A6AB-D2FF209D6B32', '513330', '德格县');
INSERT INTO `sys_region` VALUES (2443, 'BEA3529C-9786-4521-B437-136C4D6CDAF5', '513331', '白玉县');
INSERT INTO `sys_region` VALUES (2444, '8555581F-9FAF-41FA-960A-B64F407DDF11', '513332', '石渠县');
INSERT INTO `sys_region` VALUES (2445, '27A6BA4A-08FF-427D-848C-20B41FD244D3', '513333', '色达县');
INSERT INTO `sys_region` VALUES (2446, '27ACE98E-CC7E-4E72-9844-A307EB1F5742', '513334', '理塘县');
INSERT INTO `sys_region` VALUES (2447, '3021EACD-D78C-49A8-B76C-CD6BB4BD0701', '513335', '巴塘县');
INSERT INTO `sys_region` VALUES (2448, '5FD636C8-00EB-45F6-8541-0390D8609685', '513336', '乡城县');
INSERT INTO `sys_region` VALUES (2449, '28FD8458-E201-45A8-9117-A584458CE4A3', '513337', '稻城县');
INSERT INTO `sys_region` VALUES (2450, 'BBF4DCDC-551E-4BB6-8E47-2CD6719BF8C4', '513338', '得荣县');
INSERT INTO `sys_region` VALUES (2451, '6BED6E1F-2BC3-41B1-B106-7F972EF4E230', '513400', '凉山彝族自治州');
INSERT INTO `sys_region` VALUES (2452, '289DD471-FFBC-4E2B-B607-6894112BF4DF', '513401', '西昌市');
INSERT INTO `sys_region` VALUES (2453, '29A183BE-F46B-4ABB-A47B-9485939DEAA9', '513422', '木里藏族自治县');
INSERT INTO `sys_region` VALUES (2454, '37C70388-7C8D-430F-8F2B-F84C105D7BD6', '513423', '盐源县');
INSERT INTO `sys_region` VALUES (2455, '128D39DD-23FC-4009-8FD3-397740A54410', '513424', '德昌县');
INSERT INTO `sys_region` VALUES (2456, '507DE895-AB23-4580-92F5-3F05732A5D02', '513425', '会理县');
INSERT INTO `sys_region` VALUES (2457, '98143BF7-FAEC-4541-B1EF-5D7A7B6D6E94', '513426', '会东县');
INSERT INTO `sys_region` VALUES (2458, 'A694740C-47A1-4397-B619-94A9B5FD08C2', '513427', '宁南县');
INSERT INTO `sys_region` VALUES (2459, '11B4C953-4C2C-406E-9C70-8F244415F5A0', '513428', '普格县');
INSERT INTO `sys_region` VALUES (2460, '553586C5-B8D3-4C6A-A64A-87F79D3AEE16', '513429', '布拖县');
INSERT INTO `sys_region` VALUES (2461, '2705308B-2B76-476D-B952-6070C8459940', '513430', '金阳县');
INSERT INTO `sys_region` VALUES (2462, '0D67527D-B599-4930-B61A-ED0469224E73', '513431', '昭觉县');
INSERT INTO `sys_region` VALUES (2463, 'B399A08B-2E0B-4306-8F37-EBC6C7F466D2', '513432', '喜德县');
INSERT INTO `sys_region` VALUES (2464, '0B998E86-901E-4AF2-9374-37B430BBAAA7', '513433', '冕宁县');
INSERT INTO `sys_region` VALUES (2465, '8571B6E1-2D44-4F05-8D89-F308F0EBA695', '513434', '越西县');
INSERT INTO `sys_region` VALUES (2466, '12C82FE4-661B-4665-BF52-36FE2FACE989', '513435', '甘洛县');
INSERT INTO `sys_region` VALUES (2467, '711F62EA-03C2-45F9-AEFB-39BFDAE50674', '513436', '美姑县');
INSERT INTO `sys_region` VALUES (2468, 'B5719ED5-3AA4-464C-8C7B-133EA3F51E40', '513437', '雷波县');
INSERT INTO `sys_region` VALUES (2469, '28DB34E9-ACBD-4666-A3C3-4EDBA039FA33', '520000', '贵州省');
INSERT INTO `sys_region` VALUES (2470, '2749FBC1-425E-40EA-AD3E-C5B2D473EEFF', '520100', '贵阳市');
INSERT INTO `sys_region` VALUES (2471, '852409AA-86D6-4918-AEAA-8F7DE396BF7C', '520102', '南明区');
INSERT INTO `sys_region` VALUES (2472, '4F60AA37-7FB0-4762-BF6C-E18976011AF7', '520103', '云岩区');
INSERT INTO `sys_region` VALUES (2473, '490FD033-B5FF-43F1-A8C4-175B33C76054', '520111', '花溪区');
INSERT INTO `sys_region` VALUES (2474, 'FBAD97F2-9855-426F-B613-C86764DA87F2', '520112', '乌当区');
INSERT INTO `sys_region` VALUES (2475, '1AF2CCBD-E379-401B-980E-155467F15906', '520113', '白云区');
INSERT INTO `sys_region` VALUES (2476, '0CE91EF1-2686-445C-AE24-2F250FBC5FBB', '520115', '观山湖区');
INSERT INTO `sys_region` VALUES (2477, '3A1F02B6-2B42-47CA-848C-D5F3AB01018B', '520121', '开阳县');
INSERT INTO `sys_region` VALUES (2478, '138DBF9E-4242-482A-B030-A48089A25345', '520122', '息烽县');
INSERT INTO `sys_region` VALUES (2479, 'BEA9CC6B-EA32-417C-BAF0-BCBF8EA2E594', '520123', '修文县');
INSERT INTO `sys_region` VALUES (2480, '732460AF-7C20-487A-A8B8-B4DCCA01BF23', '520181', '清镇市');
INSERT INTO `sys_region` VALUES (2481, '457E4787-E308-4A80-A140-061AC91085DA', '520200', '六盘水市');
INSERT INTO `sys_region` VALUES (2482, '19489230-4487-4026-A73A-04E702AA967D', '520201', '钟山区');
INSERT INTO `sys_region` VALUES (2483, '22C7C3E7-6861-4673-A630-3B89C0E42BDC', '520203', '六枝特区');
INSERT INTO `sys_region` VALUES (2484, '7F44D959-6320-4C2D-A29B-103DFCE4F4AA', '520221', '水城县');
INSERT INTO `sys_region` VALUES (2485, '8139D71F-97BF-4D61-BB2D-65809637DB16', '520281', '盘州市');
INSERT INTO `sys_region` VALUES (2486, '01038E9E-4318-4944-9AD3-EC843AF53E8F', '520300', '遵义市');
INSERT INTO `sys_region` VALUES (2487, '6F2515CA-2746-4C05-8AE8-9A4EED0886E8', '520302', '红花岗区');
INSERT INTO `sys_region` VALUES (2488, '188DC0E0-F5AD-47DA-8B18-4F18BA4E7B49', '520303', '汇川区');
INSERT INTO `sys_region` VALUES (2489, '0E3004DC-3698-4B48-92BA-AAB387C7009E', '520304', '播州区');
INSERT INTO `sys_region` VALUES (2490, 'CFD2176A-33E8-4527-BEFE-66DE6748E79D', '520322', '桐梓县');
INSERT INTO `sys_region` VALUES (2491, '0301F8A6-DB87-47BF-B952-B4225A3D3775', '520323', '绥阳县');
INSERT INTO `sys_region` VALUES (2492, '688D105F-3848-418E-B4B3-1121483A480D', '520324', '正安县');
INSERT INTO `sys_region` VALUES (2493, 'DD631B26-D231-403C-BFF4-EC6117329C3E', '520325', '道真仡佬族苗族自治县');
INSERT INTO `sys_region` VALUES (2494, 'B0A09A74-7A32-476B-9385-2F9CD9408204', '520326', '务川仡佬族苗族自治县');
INSERT INTO `sys_region` VALUES (2495, 'CF7DB20F-0285-4999-9054-6A027BB07FD3', '520327', '凤冈县');
INSERT INTO `sys_region` VALUES (2496, '60D508D3-504F-4A0D-910E-78DC11932248', '520328', '湄潭县');
INSERT INTO `sys_region` VALUES (2497, '4BE60C8F-3282-44F3-9560-8AD1FF17D63C', '520329', '余庆县');
INSERT INTO `sys_region` VALUES (2498, '6B15CD84-6ECC-430D-ADBA-0D00BEC5F169', '520330', '习水县');
INSERT INTO `sys_region` VALUES (2499, '3A3B56DF-1FC0-4C99-8B40-DC9FEE415CE6', '520381', '赤水市');
INSERT INTO `sys_region` VALUES (2500, '67049466-0F92-43CC-8D8A-9B5B730B6AAA', '520382', '仁怀市');
INSERT INTO `sys_region` VALUES (2501, '2D3F0773-1F24-4415-A7CB-4E89A93B434C', '520400', '安顺市');
INSERT INTO `sys_region` VALUES (2502, '677DEC44-CED4-4252-ABD6-D0EFEB11E45A', '520402', '西秀区');
INSERT INTO `sys_region` VALUES (2503, '2A9E9A35-4C6F-401E-B60F-202CB3A4EAD0', '520403', '平坝区');
INSERT INTO `sys_region` VALUES (2504, '0429565B-30BC-4A0A-9A51-C6448270E556', '520422', '普定县');
INSERT INTO `sys_region` VALUES (2505, '64E3B258-62E8-426A-88AE-FCDF1C0C5172', '520423', '镇宁布依族苗族自治县');
INSERT INTO `sys_region` VALUES (2506, '077A9DA4-D00F-409D-B227-9B42F9428CE2', '520424', '关岭布依族苗族自治县');
INSERT INTO `sys_region` VALUES (2507, '026A5141-6B55-4DAD-9DC6-3FD73CB595C6', '520425', '紫云苗族布依族自治县');
INSERT INTO `sys_region` VALUES (2508, 'CCED3F77-E933-4B27-999E-F1B03CA055EF', '520500', '毕节市');
INSERT INTO `sys_region` VALUES (2509, '0A08EB0E-AEA6-44C5-B934-31A2EA8BCB7B', '520502', '七星关区');
INSERT INTO `sys_region` VALUES (2510, '3BB12356-3431-4D71-9336-0FA5322742F1', '520521', '大方县');
INSERT INTO `sys_region` VALUES (2511, '35882B67-D142-4425-BDB8-B58256E8A2BE', '520522', '黔西县');
INSERT INTO `sys_region` VALUES (2512, '274A5659-C5A4-4401-B7DC-4FEE4EF83714', '520523', '金沙县');
INSERT INTO `sys_region` VALUES (2513, 'CB36D943-F123-4D53-A040-C63D33BBE537', '520524', '织金县');
INSERT INTO `sys_region` VALUES (2514, 'ED5E4247-F7F4-41BF-8EEE-F56F2D6A7E70', '520525', '纳雍县');
INSERT INTO `sys_region` VALUES (2515, 'ADB0023C-7418-4165-A031-769B96EF6C48', '520526', '威宁彝族回族苗族自治县');
INSERT INTO `sys_region` VALUES (2516, 'D7ED57F3-C2C7-4B12-8A67-C1F117BEE5F1', '520527', '赫章县');
INSERT INTO `sys_region` VALUES (2517, 'C2878487-0402-4D2E-AD23-7520CAB11DCC', '520600', '铜仁市');
INSERT INTO `sys_region` VALUES (2518, 'CF4D8FAF-C12A-46E7-B9DF-F4AC6B795977', '520602', '碧江区');
INSERT INTO `sys_region` VALUES (2519, 'A4D420E8-537C-4595-B7AA-CE45CB10F9C6', '520603', '万山区');
INSERT INTO `sys_region` VALUES (2520, '7EF20034-B73D-4BE8-A081-EB47C626A4FC', '520621', '江口县');
INSERT INTO `sys_region` VALUES (2521, 'A13CAE8C-66EE-4B23-9412-BFBF68A0ED4E', '520622', '玉屏侗族自治县');
INSERT INTO `sys_region` VALUES (2522, 'FFEA1BCC-C762-4337-939F-2E35B03DA421', '520623', '石阡县');
INSERT INTO `sys_region` VALUES (2523, '8FB3EC2C-5689-4BA0-B79A-F1D60929260A', '520624', '思南县');
INSERT INTO `sys_region` VALUES (2524, 'D461D227-52E4-4528-B57D-1308B0D8B65C', '520625', '印江土家族苗族自治县');
INSERT INTO `sys_region` VALUES (2525, '4FCBA766-71CD-49CA-BBE8-652D1C998F97', '520626', '德江县');
INSERT INTO `sys_region` VALUES (2526, 'E663958B-4A2F-4B5E-A3E7-FAD21610D5A3', '520627', '沿河土家族自治县');
INSERT INTO `sys_region` VALUES (2527, '21463057-9C3B-42CB-88DB-5D42526ED106', '520628', '松桃苗族自治县');
INSERT INTO `sys_region` VALUES (2528, '00267100-FE44-467F-8FE1-CE1D04629226', '522300', '黔西南布依族苗族自治州');
INSERT INTO `sys_region` VALUES (2529, 'EC0E2BC3-CEC4-4E2B-AB1D-56FF66EC1463', '522301', '兴义市');
INSERT INTO `sys_region` VALUES (2530, '64841D9E-6BEA-4769-AF1D-903B5E17061D', '522302', '兴仁市');
INSERT INTO `sys_region` VALUES (2531, '4612832A-71C7-4673-8ED0-F6AC59E1C033', '522323', '普安县');
INSERT INTO `sys_region` VALUES (2532, '21352CBF-F388-448F-A286-107D7E105A4E', '522324', '晴隆县');
INSERT INTO `sys_region` VALUES (2533, 'AFA27D37-93FA-4986-93A0-67B7C33676A3', '522325', '贞丰县');
INSERT INTO `sys_region` VALUES (2534, '1F138626-3A1D-479D-8E40-015614343ADD', '522326', '望谟县');
INSERT INTO `sys_region` VALUES (2535, 'B136E373-51BF-4D0A-8017-C6E101EAB2B3', '522327', '册亨县');
INSERT INTO `sys_region` VALUES (2536, '736C2D9B-31F1-48AD-ACD5-93003E189DA8', '522328', '安龙县');
INSERT INTO `sys_region` VALUES (2537, '818A01F4-1F70-4068-9EB5-029A1CBF5251', '522600', '黔东南苗族侗族自治州');
INSERT INTO `sys_region` VALUES (2538, '64892066-16CA-447F-B4DA-05DBF1000E94', '522601', '凯里市');
INSERT INTO `sys_region` VALUES (2539, 'ABE6594C-2B37-4AF8-BFB9-5B3A2897DEAA', '522622', '黄平县');
INSERT INTO `sys_region` VALUES (2540, '38C8D43D-3638-4744-8EB5-2FBB787C0DA9', '522623', '施秉县');
INSERT INTO `sys_region` VALUES (2541, '39BC54E4-5BFB-4CA5-9876-8D4520EBA70F', '522624', '三穗县');
INSERT INTO `sys_region` VALUES (2542, 'CE458131-B10D-4D43-B4CE-738240683112', '522625', '镇远县');
INSERT INTO `sys_region` VALUES (2543, '8D4CDA10-B932-45CF-929D-4C70B18BD3B9', '522626', '岑巩县');
INSERT INTO `sys_region` VALUES (2544, 'B0BFE949-B2CA-4F56-86D2-43B75368C3F6', '522627', '天柱县');
INSERT INTO `sys_region` VALUES (2545, 'DAF162A5-EB0A-4B5A-9BA9-DAC38DA9C6E2', '522628', '锦屏县');
INSERT INTO `sys_region` VALUES (2546, 'E19C0D7D-EC8F-4525-BC02-47FB64FD359C', '522629', '剑河县');
INSERT INTO `sys_region` VALUES (2547, 'DE41349E-B10B-4E57-A9A1-0E4B8F81B496', '522630', '台江县');
INSERT INTO `sys_region` VALUES (2548, 'F9E32521-AF7B-465A-878A-229700B4A814', '522631', '黎平县');
INSERT INTO `sys_region` VALUES (2549, '39004EEC-2A1E-4393-90F8-3DA49679BB4F', '522632', '榕江县');
INSERT INTO `sys_region` VALUES (2550, '10F4C039-D0A6-47EE-9364-B0ECDAF78D6F', '522633', '从江县');
INSERT INTO `sys_region` VALUES (2551, 'C0C794BD-F78A-40C4-83B4-2290B13579A2', '522634', '雷山县');
INSERT INTO `sys_region` VALUES (2552, '389A681A-9337-4E1D-B13D-E9A639C318CB', '522635', '麻江县');
INSERT INTO `sys_region` VALUES (2553, '42EF6845-3568-4426-8897-1BEF35E6CA69', '522636', '丹寨县');
INSERT INTO `sys_region` VALUES (2554, '8B7C7053-B272-44A1-8F75-73DD7C998026', '522700', '黔南布依族苗族自治州');
INSERT INTO `sys_region` VALUES (2555, '6C220106-CF4D-4F6C-80F6-5A340B2BE80F', '522701', '都匀市');
INSERT INTO `sys_region` VALUES (2556, '3BD84076-5441-41B4-8C33-A82B7B1428C4', '522702', '福泉市');
INSERT INTO `sys_region` VALUES (2557, '1FC452A1-CD35-4B32-8E19-54EEAB4818A6', '522722', '荔波县');
INSERT INTO `sys_region` VALUES (2558, '05E99C4A-1DD1-4010-B0E2-64455DFB14A2', '522723', '贵定县');
INSERT INTO `sys_region` VALUES (2559, 'EB944CE6-5732-465F-BC5D-64BE2EBE1E27', '522725', '瓮安县');
INSERT INTO `sys_region` VALUES (2560, '74C9CB06-9A19-4BF9-B3F5-69FEC89B1A34', '522726', '独山县');
INSERT INTO `sys_region` VALUES (2561, '04EE99AE-CB2B-41A5-A5B1-CFDE5EB24102', '522727', '平塘县');
INSERT INTO `sys_region` VALUES (2562, '3C8E878A-9688-44DB-B05B-42813863B53A', '522728', '罗甸县');
INSERT INTO `sys_region` VALUES (2563, '94B57266-C5EE-40C6-AB1B-54FB9A86BB93', '522729', '长顺县');
INSERT INTO `sys_region` VALUES (2564, 'A6595F9B-0E01-479C-B240-17600ADBFCF7', '522730', '龙里县');
INSERT INTO `sys_region` VALUES (2565, 'F4635ABD-4EEF-4F80-8B07-3CAF86022472', '522731', '惠水县');
INSERT INTO `sys_region` VALUES (2566, 'FDE08151-A000-4610-8F0E-8B6BDF4561F9', '522732', '三都水族自治县');
INSERT INTO `sys_region` VALUES (2567, '7963E1EF-4DEE-4907-843E-FB47A614FFA0', '530000', '云南省');
INSERT INTO `sys_region` VALUES (2568, 'D5782BB8-810D-4C4A-9A2C-2133145CCA1B', '530100', '昆明市');
INSERT INTO `sys_region` VALUES (2569, '05BD7506-3BF2-4668-B5AF-387F473B5794', '530102', '五华区');
INSERT INTO `sys_region` VALUES (2570, '8B597EEE-C68C-40BE-A3C0-A94C4FE40B7A', '530103', '盘龙区');
INSERT INTO `sys_region` VALUES (2571, 'A6E866F1-A86D-4B3F-9F10-4C2F4DF0F38A', '530111', '官渡区');
INSERT INTO `sys_region` VALUES (2572, 'FF8CDC53-79C6-4F7C-998A-25921092651C', '530112', '西山区');
INSERT INTO `sys_region` VALUES (2573, '5F8A629E-76C9-4915-A81A-BE2C222DE273', '530113', '东川区');
INSERT INTO `sys_region` VALUES (2574, 'F5534F3A-C3B4-48A6-8070-0442DE624752', '530114', '呈贡区');
INSERT INTO `sys_region` VALUES (2575, '8A081527-E351-48F9-9B0C-6BA5EC616C5A', '530115', '晋宁区');
INSERT INTO `sys_region` VALUES (2576, 'C557EE94-E9B0-4812-9A76-8B0A6BFABDF4', '530124', '富民县');
INSERT INTO `sys_region` VALUES (2577, '2CAFB7FA-B58E-45BF-B43B-F049E8BFA3FD', '530125', '宜良县');
INSERT INTO `sys_region` VALUES (2578, 'E5A05BFF-79A1-48C6-8A54-7F6040922B07', '530126', '石林彝族自治县');
INSERT INTO `sys_region` VALUES (2579, '89C4E5A0-E83A-4928-B0E5-8E9167D427A7', '530127', '嵩明县');
INSERT INTO `sys_region` VALUES (2580, '88AA3B04-B4B0-4B1E-9219-3E4BEC4972F3', '530128', '禄劝彝族苗族自治县');
INSERT INTO `sys_region` VALUES (2581, 'A76BE82F-B0F0-4742-890E-A89A00125878', '530129', '寻甸回族彝族自治县');
INSERT INTO `sys_region` VALUES (2582, '21E075D6-D82A-4A36-9E53-D79275905766', '530181', '安宁市');
INSERT INTO `sys_region` VALUES (2583, 'AE036196-C655-4C28-9F86-160FE9DEBB58', '530300', '曲靖市');
INSERT INTO `sys_region` VALUES (2584, 'E03B18F7-D05B-412D-A92C-281067EEA90B', '530302', '麒麟区');
INSERT INTO `sys_region` VALUES (2585, 'BB607926-C1E3-43B0-8EF6-162C1070E235', '530303', '沾益区');
INSERT INTO `sys_region` VALUES (2586, 'C01D7795-0001-4ED7-8629-A92AB002E262', '530304', '马龙区');
INSERT INTO `sys_region` VALUES (2587, 'E8D59421-D45B-408F-A8C8-64971C9FF673', '530322', '陆良县');
INSERT INTO `sys_region` VALUES (2588, '1052582E-D53C-4BFB-A9EC-90C07EBE5C36', '530323', '师宗县');
INSERT INTO `sys_region` VALUES (2589, 'FA2CB385-22C8-4553-805F-AA30E9E8FBE2', '530324', '罗平县');
INSERT INTO `sys_region` VALUES (2590, '3B2115C2-5346-4BA5-8500-0932B1E50D85', '530325', '富源县');
INSERT INTO `sys_region` VALUES (2591, '776BF13D-D864-41B4-ACE9-2F5D567D8212', '530326', '会泽县');
INSERT INTO `sys_region` VALUES (2592, 'E9624DDB-DC72-436A-91CC-3F530A9F6329', '530381', '宣威市');
INSERT INTO `sys_region` VALUES (2593, 'D1E26565-8997-46F5-BD5B-E214011E4628', '530400', '玉溪市');
INSERT INTO `sys_region` VALUES (2594, 'EAAEFA56-F159-42A6-A46A-E7DB07B13E9B', '530402', '红塔区');
INSERT INTO `sys_region` VALUES (2595, '13CBABC6-54F9-41EA-8EE6-3799EA5686BA', '530403', '江川区');
INSERT INTO `sys_region` VALUES (2596, '9C79DAF7-8578-4CB2-8820-9FF1EA0086D5', '530423', '通海县');
INSERT INTO `sys_region` VALUES (2597, '10B71D0A-4E5B-4705-88D4-F2C8FA02DB81', '530424', '华宁县');
INSERT INTO `sys_region` VALUES (2598, '88765471-10D9-4622-A240-B00F4E5CD316', '530425', '易门县');
INSERT INTO `sys_region` VALUES (2599, '50C450FE-6E12-496B-AFFC-49B880E9B031', '530426', '峨山彝族自治县');
INSERT INTO `sys_region` VALUES (2600, '41E6EA87-19EA-4915-91FB-C6C776499C21', '530427', '新平彝族傣族自治县');
INSERT INTO `sys_region` VALUES (2601, 'C3413294-1F70-4187-AC2B-3965B46228C4', '530428', '元江哈尼族彝族傣族自治县');
INSERT INTO `sys_region` VALUES (2602, '05F27B09-B73B-4209-91D8-E6F77BE4AFDB', '530481', '澄江市');
INSERT INTO `sys_region` VALUES (2603, '031B05D4-B6EC-4D53-AFB9-601F8EC62754', '530500', '保山市');
INSERT INTO `sys_region` VALUES (2604, '57CB1CD7-9B92-4E21-9BA1-F472C0D9D06C', '530502', '隆阳区');
INSERT INTO `sys_region` VALUES (2605, 'CEE0792A-5955-4262-A31D-90D0FCF88E4B', '530521', '施甸县');
INSERT INTO `sys_region` VALUES (2606, 'BA0E431D-8311-406C-9C57-8D8C4F5B1B1A', '530523', '龙陵县');
INSERT INTO `sys_region` VALUES (2607, '8C3B31B5-EF4D-4DF2-8965-390F426FD0A3', '530524', '昌宁县');
INSERT INTO `sys_region` VALUES (2608, 'A5BD2809-7BCC-4015-9A9D-30E77A3C53CA', '530581', '腾冲市');
INSERT INTO `sys_region` VALUES (2609, 'EB5C60BC-82C2-495E-AD08-26A3E6598370', '530600', '昭通市');
INSERT INTO `sys_region` VALUES (2610, '7BFF434A-4901-4DC5-B85E-B184F7B7591F', '530602', '昭阳区');
INSERT INTO `sys_region` VALUES (2611, '4002C818-7936-4A2C-ADD8-738D4A2FE296', '530621', '鲁甸县');
INSERT INTO `sys_region` VALUES (2612, 'B32D9DDA-E71A-4D69-AA68-8945502BB491', '530622', '巧家县');
INSERT INTO `sys_region` VALUES (2613, '94199A06-63BF-4BAC-B89E-B785FF921CEF', '530623', '盐津县');
INSERT INTO `sys_region` VALUES (2614, '3F92B9DD-4DC7-4D8E-B03B-25E15A113E0B', '530624', '大关县');
INSERT INTO `sys_region` VALUES (2615, '01E7165E-AAD6-447C-A15C-958291763FF0', '530625', '永善县');
INSERT INTO `sys_region` VALUES (2616, 'E1D41FA0-69BD-4A68-8B3F-661D1586A382', '530626', '绥江县');
INSERT INTO `sys_region` VALUES (2617, 'D943988E-DA0C-4882-AA2B-0CB8D1FE44D7', '530627', '镇雄县');
INSERT INTO `sys_region` VALUES (2618, 'A89FABCC-BEB2-4767-B1D6-4D5A7614B34F', '530628', '彝良县');
INSERT INTO `sys_region` VALUES (2619, 'FB7CF546-4F6D-4AE1-9278-56096D0305C1', '530629', '威信县');
INSERT INTO `sys_region` VALUES (2620, 'B9336F0E-758E-48EE-ADF7-78FEC2C2C6B5', '530681', '水富市');
INSERT INTO `sys_region` VALUES (2621, '481349C7-3027-4EF8-B95D-9BAE293793CD', '530700', '丽江市');
INSERT INTO `sys_region` VALUES (2622, '8665AEA4-FB90-4FD8-88CE-E477091C854C', '530702', '古城区');
INSERT INTO `sys_region` VALUES (2623, 'A269404E-C605-4179-B12F-239C0A49304A', '530721', '玉龙纳西族自治县');
INSERT INTO `sys_region` VALUES (2624, 'F1F50FC7-4328-4C2E-9A3C-0A85F91EB918', '530722', '永胜县');
INSERT INTO `sys_region` VALUES (2625, 'AC68444D-D8B2-40D9-856C-35A97A4E873F', '530723', '华坪县');
INSERT INTO `sys_region` VALUES (2626, '0BF29A97-BA0C-4F36-95E0-E32CD7552996', '530724', '宁蒗彝族自治县');
INSERT INTO `sys_region` VALUES (2627, '960243A4-F7EB-47BA-9A3D-BF63BF8EA95F', '530800', '普洱市');
INSERT INTO `sys_region` VALUES (2628, 'C11F1885-3CBD-4FC2-884D-FB4FA1E79BF2', '530802', '思茅区');
INSERT INTO `sys_region` VALUES (2629, 'CAAC073E-2DCD-4E18-94F9-1B737F72BCBF', '530821', '宁洱哈尼族彝族自治县');
INSERT INTO `sys_region` VALUES (2630, 'F9D99285-3F9E-4AD6-BC95-20129FB083EC', '530822', '墨江哈尼族自治县');
INSERT INTO `sys_region` VALUES (2631, 'CF2D1DB1-180A-41FF-92CB-F9C62DCBCCA1', '530823', '景东彝族自治县');
INSERT INTO `sys_region` VALUES (2632, '4C28A370-0CEE-43A9-90EF-04956EFF2706', '530824', '景谷傣族彝族自治县');
INSERT INTO `sys_region` VALUES (2633, '895FFA0E-6277-434E-98C0-4A092C1F9416', '530825', '镇沅彝族哈尼族拉祜族自治县');
INSERT INTO `sys_region` VALUES (2634, 'E7DD506B-5B19-456E-8768-FC2458557775', '530826', '江城哈尼族彝族自治县');
INSERT INTO `sys_region` VALUES (2635, 'FC03C227-99E5-429C-AF70-69CABC2B0552', '530827', '孟连傣族拉祜族佤族自治县');
INSERT INTO `sys_region` VALUES (2636, '7A95B992-886A-4990-B6D3-EAA60FB31212', '530828', '澜沧拉祜族自治县');
INSERT INTO `sys_region` VALUES (2637, '79E0C8F4-6CF1-4D3B-8492-F27D3E581596', '530829', '西盟佤族自治县');
INSERT INTO `sys_region` VALUES (2638, '68E581FB-3894-4F44-BD51-BE13CE721F5D', '530900', '临沧市');
INSERT INTO `sys_region` VALUES (2639, 'FB4C0859-A99F-40A9-BD39-70FF765C99A3', '530902', '临翔区');
INSERT INTO `sys_region` VALUES (2640, '243EB707-83B2-41BC-8FC8-59921D115280', '530921', '凤庆县');
INSERT INTO `sys_region` VALUES (2641, '9439305C-2927-4E0D-8BE8-C70C2F3E66BD', '530922', '云县');
INSERT INTO `sys_region` VALUES (2642, '4FF1D9C0-DEBE-4287-AD40-C5354EB8180D', '530923', '永德县');
INSERT INTO `sys_region` VALUES (2643, 'BE389EE0-A080-4A25-A52D-4AC3A54F8CCA', '530924', '镇康县');
INSERT INTO `sys_region` VALUES (2644, '02ED9B9B-F1B3-4543-B3B0-886D3B4928F4', '530925', '双江拉祜族佤族布朗族傣族自治县');
INSERT INTO `sys_region` VALUES (2645, '10112481-E15F-4EF2-82ED-506DB1F91182', '530926', '耿马傣族佤族自治县');
INSERT INTO `sys_region` VALUES (2646, '25C0E1AF-BBA9-4678-8EBF-50FE89B6E73F', '530927', '沧源佤族自治县');
INSERT INTO `sys_region` VALUES (2647, 'BFFDA2F2-3308-4ADD-A92B-38E1FF054FB0', '532300', '楚雄彝族自治州');
INSERT INTO `sys_region` VALUES (2648, 'DB48A30B-BD48-4FAE-9292-B6810CB87381', '532301', '楚雄市');
INSERT INTO `sys_region` VALUES (2649, '266484A1-440E-445F-8A36-E54332D6F51C', '532322', '双柏县');
INSERT INTO `sys_region` VALUES (2650, '4E751DF7-2F05-4C43-8479-0C6F9BE56F09', '532323', '牟定县');
INSERT INTO `sys_region` VALUES (2651, '9B0D342B-FCB6-4B32-BE68-908FC0541F54', '532324', '南华县');
INSERT INTO `sys_region` VALUES (2652, '7B04E31C-761A-4216-BD16-674F4A5039A1', '532325', '姚安县');
INSERT INTO `sys_region` VALUES (2653, 'DAFFB9B2-AAD4-4356-ADB0-6640A9A3B269', '532326', '大姚县');
INSERT INTO `sys_region` VALUES (2654, '725F9263-083F-4457-AF08-DC4C9813E5AB', '532327', '永仁县');
INSERT INTO `sys_region` VALUES (2655, '6299CDE9-C35E-4A55-A7DB-4BC4CBBDB7E4', '532328', '元谋县');
INSERT INTO `sys_region` VALUES (2656, 'F7240C5E-C282-4166-A568-406EC6CB1467', '532329', '武定县');
INSERT INTO `sys_region` VALUES (2657, '8DA597B6-5B3C-4679-915F-6B9FD9914908', '532331', '禄丰县');
INSERT INTO `sys_region` VALUES (2658, '833420BE-7A12-4E2D-911A-5BC6242FE499', '532500', '红河哈尼族彝族自治州');
INSERT INTO `sys_region` VALUES (2659, '2B27B1A0-922C-4BE0-848B-CC1AABA41DC3', '532501', '个旧市');
INSERT INTO `sys_region` VALUES (2660, 'CD9C2B2A-201E-4C3C-A103-4E03D515323D', '532502', '开远市');
INSERT INTO `sys_region` VALUES (2661, 'E328E495-D313-439A-85E8-186742B6C3B9', '532503', '蒙自市');
INSERT INTO `sys_region` VALUES (2662, '7407F855-B41D-4187-A390-8B9DFF839F4C', '532504', '弥勒市');
INSERT INTO `sys_region` VALUES (2663, 'B9CADE25-3B94-4020-9F69-E1CA7E7C06C4', '532523', '屏边苗族自治县');
INSERT INTO `sys_region` VALUES (2664, '664B0AD1-8E2A-42FB-AA4A-FD98D4D288F5', '532524', '建水县');
INSERT INTO `sys_region` VALUES (2665, 'DB3C06C3-CC52-4D35-9F57-6316AB7026BC', '532525', '石屏县');
INSERT INTO `sys_region` VALUES (2666, '0B17CA69-4C73-4BB3-BEEF-F7BED543ABD9', '532527', '泸西县');
INSERT INTO `sys_region` VALUES (2667, '8E582C3D-A5F9-4E35-9107-EC197522FE33', '532528', '元阳县');
INSERT INTO `sys_region` VALUES (2668, '5948A125-5BB0-41A8-8E2E-2EE14D2B5F9F', '532529', '红河县');
INSERT INTO `sys_region` VALUES (2669, 'E723B37F-BD66-4581-BDB8-F8EAFAE66CB7', '532530', '金平苗族瑶族傣族自治县');
INSERT INTO `sys_region` VALUES (2670, '87645B4C-7862-4D9D-90A4-956BBFB07CAE', '532531', '绿春县');
INSERT INTO `sys_region` VALUES (2671, '41682AB9-09F5-4C16-84A5-8BFE83DC5B7F', '532532', '河口瑶族自治县');
INSERT INTO `sys_region` VALUES (2672, '2519CDD2-81F1-475E-9803-F961A4116277', '532600', '文山壮族苗族自治州');
INSERT INTO `sys_region` VALUES (2673, 'C1AC2C09-380F-4572-A438-255544B98FC2', '532601', '文山市');
INSERT INTO `sys_region` VALUES (2674, '8392AA35-81CF-4366-BBE0-E0CBAC5136E4', '532622', '砚山县');
INSERT INTO `sys_region` VALUES (2675, '3794E335-DAFC-4BE2-B6C0-5C0B72B0B5C0', '532623', '西畴县');
INSERT INTO `sys_region` VALUES (2676, '931C338B-03D3-42BA-BAAE-8B008B9D429F', '532624', '麻栗坡县');
INSERT INTO `sys_region` VALUES (2677, '40A54A3D-B61C-40ED-9CB4-BAEC0AADEF96', '532625', '马关县');
INSERT INTO `sys_region` VALUES (2678, 'E15B01A5-E1DC-405A-A6A5-1F44108670E5', '532626', '丘北县');
INSERT INTO `sys_region` VALUES (2679, 'B901E7E6-6CAA-4314-9557-4A12DBE9C87E', '532627', '广南县');
INSERT INTO `sys_region` VALUES (2680, '0B5A9841-0678-456D-BF46-9A487D67D84B', '532628', '富宁县');
INSERT INTO `sys_region` VALUES (2681, 'B693AF43-D23C-45FE-A53A-5B88A7DD4EA1', '532800', '西双版纳傣族自治州');
INSERT INTO `sys_region` VALUES (2682, 'F9BFA0B6-7005-4E59-AEB9-3B36448FB472', '532801', '景洪市');
INSERT INTO `sys_region` VALUES (2683, 'D2549399-0E90-44A9-A2E0-9850C99AEAF8', '532822', '勐海县');
INSERT INTO `sys_region` VALUES (2684, '5F3F7BB9-700D-4764-ADAB-D13E28E7AC81', '532823', '勐腊县');
INSERT INTO `sys_region` VALUES (2685, 'D7B55372-1ECE-415A-B579-47E9AD776DEC', '532900', '大理白族自治州');
INSERT INTO `sys_region` VALUES (2686, 'CBA763F6-1AE8-4A34-BCD4-5F439D02903F', '532901', '大理市');
INSERT INTO `sys_region` VALUES (2687, '01B23829-409C-40ED-9E4A-72641C49AB6A', '532922', '漾濞彝族自治县');
INSERT INTO `sys_region` VALUES (2688, '62131E23-6864-4F23-97A5-D693D79FB606', '532923', '祥云县');
INSERT INTO `sys_region` VALUES (2689, '0053CB36-6C51-4EF9-84FC-2F1B4FF61A32', '532924', '宾川县');
INSERT INTO `sys_region` VALUES (2690, '995DD2FB-E96C-46B5-A9BF-A89A6C6F05BC', '532925', '弥渡县');
INSERT INTO `sys_region` VALUES (2691, 'F3DDDC3B-2C05-44A6-B005-F04A06DF3A91', '532926', '南涧彝族自治县');
INSERT INTO `sys_region` VALUES (2692, '48D28389-DB42-4E94-8C86-5179ADB90CAB', '532927', '巍山彝族回族自治县');
INSERT INTO `sys_region` VALUES (2693, '7DC94280-7039-4C8B-A60A-CC3E6F71F0BA', '532928', '永平县');
INSERT INTO `sys_region` VALUES (2694, 'E3BDE16C-AF03-42C8-B88D-FCFC6CB02895', '532929', '云龙县');
INSERT INTO `sys_region` VALUES (2695, 'B5F78D21-383E-4C7C-8299-B6CDD7CC083B', '532930', '洱源县');
INSERT INTO `sys_region` VALUES (2696, '5A73E1BE-B20C-4E3E-B183-22F4040182F4', '532931', '剑川县');
INSERT INTO `sys_region` VALUES (2697, '29AE81C2-9953-4181-9998-AA24EB163489', '532932', '鹤庆县');
INSERT INTO `sys_region` VALUES (2698, '6FE58469-71BA-4E1B-A5B1-775B62CAABA5', '533100', '德宏傣族景颇族自治州');
INSERT INTO `sys_region` VALUES (2699, '5FDC85A4-9DC5-47DC-B220-A8B3813EDCCC', '533102', '瑞丽市');
INSERT INTO `sys_region` VALUES (2700, '11F57CE4-83F2-4065-82FA-EB3F3394A20F', '533103', '芒市');
INSERT INTO `sys_region` VALUES (2701, '599BC675-3A7C-454C-A913-7EEBA186D24A', '533122', '梁河县');
INSERT INTO `sys_region` VALUES (2702, '80673F9B-98D0-49DD-93D1-AEE344724FC3', '533123', '盈江县');
INSERT INTO `sys_region` VALUES (2703, '2A13BD6D-EFA7-4E85-8258-5D5518563697', '533124', '陇川县');
INSERT INTO `sys_region` VALUES (2704, '210FFA1C-6DDC-44D2-925F-BADCFDC81BB4', '533300', '怒江傈僳族自治州');
INSERT INTO `sys_region` VALUES (2705, 'BC096DED-DAC1-4DCF-8C0B-74BBFDDA2B59', '533301', '泸水市');
INSERT INTO `sys_region` VALUES (2706, '0EE0D28E-1520-4C83-AEFE-DDAE10D5A6C8', '533323', '福贡县');
INSERT INTO `sys_region` VALUES (2707, '7FEEBBF2-4E27-4D2C-8810-5BF526AECFBB', '533324', '贡山独龙族怒族自治县');
INSERT INTO `sys_region` VALUES (2708, 'AE7205ED-7A2C-49EE-BE73-9F68179B9FFB', '533325', '兰坪白族普米族自治县');
INSERT INTO `sys_region` VALUES (2709, '05501E34-8A5D-4BCF-AD75-647A4D1919B1', '533400', '迪庆藏族自治州');
INSERT INTO `sys_region` VALUES (2710, '3C22627F-E84C-4A38-B2ED-578BCA735649', '533401', '香格里拉市');
INSERT INTO `sys_region` VALUES (2711, 'B9C4DE26-731C-4552-9C85-BFBD0F31C717', '533422', '德钦县');
INSERT INTO `sys_region` VALUES (2712, '0AD7657F-FC1E-4E9D-88EB-4E81927BB861', '533423', '维西傈僳族自治县');
INSERT INTO `sys_region` VALUES (2713, '9DB9F688-762F-4831-86FA-2F894ACBE4C1', '540000', '西藏自治区');
INSERT INTO `sys_region` VALUES (2714, '3F042D8D-6A4E-49F4-8C1B-572BABC14B41', '540100', '拉萨市');
INSERT INTO `sys_region` VALUES (2715, '7F36B92A-5E43-440B-8D89-876BE4EEC1D6', '540102', '城关区');
INSERT INTO `sys_region` VALUES (2716, '1123099F-02A4-4FEE-8CF5-15367F57F778', '540103', '堆龙德庆区');
INSERT INTO `sys_region` VALUES (2717, '2817E1F0-D393-4C40-BDC8-3CB59049AB5B', '540104', '达孜区');
INSERT INTO `sys_region` VALUES (2718, '35F7E430-BF75-4B9B-8C93-0703B399CBAC', '540121', '林周县');
INSERT INTO `sys_region` VALUES (2719, 'EC1F561A-7BD0-44BC-B590-A8CB441369C4', '540122', '当雄县');
INSERT INTO `sys_region` VALUES (2720, '6E4AE08E-A082-417F-AC65-F7F9482DB296', '540123', '尼木县');
INSERT INTO `sys_region` VALUES (2721, '306E792D-4805-43DC-9766-AF27D81ECCFC', '540124', '曲水县');
INSERT INTO `sys_region` VALUES (2722, 'C7A65749-EAEB-495C-81CD-BF107D0A4ED4', '540127', '墨竹工卡县');
INSERT INTO `sys_region` VALUES (2723, 'CCD40CCB-FA51-47A1-B8C4-CB27735E1B33', '540200', '日喀则市');
INSERT INTO `sys_region` VALUES (2724, 'CD1D2D94-E8E5-42F7-A0D2-B1A46DA1B96C', '540202', '桑珠孜区');
INSERT INTO `sys_region` VALUES (2725, '3BDF34D5-1D4B-416B-9D31-02BE11014F68', '540221', '南木林县');
INSERT INTO `sys_region` VALUES (2726, '7909D72C-5ED3-411E-8D21-35341F2FD10F', '540222', '江孜县');
INSERT INTO `sys_region` VALUES (2727, '725A9B0B-2F2E-4553-839C-30E971130B76', '540223', '定日县');
INSERT INTO `sys_region` VALUES (2728, 'E1508BC8-DD13-4D4A-9E71-D13CBC50DFBE', '540224', '萨迦县');
INSERT INTO `sys_region` VALUES (2729, 'BA034F89-0229-4F71-A4B2-9BD9C3064C56', '540225', '拉孜县');
INSERT INTO `sys_region` VALUES (2730, '3D6430C0-BBC9-4C21-BC41-A96FEE29AC79', '540226', '昂仁县');
INSERT INTO `sys_region` VALUES (2731, '58DBE3E1-8B2B-4D08-B254-5C0991E6EE19', '540227', '谢通门县');
INSERT INTO `sys_region` VALUES (2732, 'CB7AEFD4-0543-49BF-8A1B-64BB0C8D3F2A', '540228', '白朗县');
INSERT INTO `sys_region` VALUES (2733, '67EDCFAC-E3EF-44CB-9D86-1B256C08EFD0', '540229', '仁布县');
INSERT INTO `sys_region` VALUES (2734, '0A4524B5-8F26-4492-B0E5-3BB55AE17A5A', '540230', '康马县');
INSERT INTO `sys_region` VALUES (2735, 'A7170DF3-3B33-4824-B272-C91819E34C30', '540231', '定结县');
INSERT INTO `sys_region` VALUES (2736, '7E104DCB-06DD-48C9-92B2-5910A8ECEFA4', '540232', '仲巴县');
INSERT INTO `sys_region` VALUES (2737, '50216D47-7270-4D20-9848-1A3AE2F46E34', '540233', '亚东县');
INSERT INTO `sys_region` VALUES (2738, 'E6F72DEA-3FBC-424D-9C43-7B34EC9A19C7', '540234', '吉隆县');
INSERT INTO `sys_region` VALUES (2739, '822AB020-B330-4096-A703-9B1FB44858CF', '540235', '聂拉木县');
INSERT INTO `sys_region` VALUES (2740, 'C3F390AF-186D-4FEB-AFBE-5F4444073D81', '540236', '萨嘎县');
INSERT INTO `sys_region` VALUES (2741, '414942AD-7130-4FAD-9030-4D34C2B1D464', '540237', '岗巴县');
INSERT INTO `sys_region` VALUES (2742, '3238FCE0-8B64-4DB6-BF46-CB2456E16A5E', '540300', '昌都市');
INSERT INTO `sys_region` VALUES (2743, '72566118-BBF2-41C7-811B-79237B54F1C0', '540302', '卡若区');
INSERT INTO `sys_region` VALUES (2744, '69DED4F0-8C60-4771-8EB6-90750C185039', '540321', '江达县');
INSERT INTO `sys_region` VALUES (2745, '639EA236-07C2-4B62-BC28-9482A1C777A2', '540322', '贡觉县');
INSERT INTO `sys_region` VALUES (2746, '1BFA4756-48D9-41BE-830A-46E2DD0DCAD1', '540323', '类乌齐县');
INSERT INTO `sys_region` VALUES (2747, 'B6D67E6B-F4AB-4ECD-BF0C-7726E187D156', '540324', '丁青县');
INSERT INTO `sys_region` VALUES (2748, '07AC2168-71E9-47BE-90AA-FC6C0D751368', '540325', '察雅县');
INSERT INTO `sys_region` VALUES (2749, '1AF4A4C5-929E-45D8-BA71-35AD14614AC6', '540326', '八宿县');
INSERT INTO `sys_region` VALUES (2750, '033D4CBA-7527-41AF-9422-99AC5F87A795', '540327', '左贡县');
INSERT INTO `sys_region` VALUES (2751, '644C7BB6-0977-4706-954B-800E20EB4F4E', '540328', '芒康县');
INSERT INTO `sys_region` VALUES (2752, '05267F97-0A6D-45C5-B8D5-42B5B09CE123', '540329', '洛隆县');
INSERT INTO `sys_region` VALUES (2753, '887E2B49-2EDB-40B0-835C-76F5E7BCA599', '540330', '边坝县');
INSERT INTO `sys_region` VALUES (2754, '1613BB37-802C-4E63-AF59-4F695B619879', '540400', '林芝市');
INSERT INTO `sys_region` VALUES (2755, 'DB72A799-6BE2-4FB0-B65F-B51F0CE8447A', '540402', '巴宜区');
INSERT INTO `sys_region` VALUES (2756, '04189573-DA3B-4401-8302-077DEC8FCCB8', '540421', '工布江达县');
INSERT INTO `sys_region` VALUES (2757, 'C5B88C47-1346-4FDA-8202-68CFEE5A421C', '540422', '米林县');
INSERT INTO `sys_region` VALUES (2758, '2182B846-E7E1-4834-85C9-127D31E57B59', '540423', '墨脱县');
INSERT INTO `sys_region` VALUES (2759, '4669D16B-B532-4D62-87F2-597D24156BDC', '540424', '波密县');
INSERT INTO `sys_region` VALUES (2760, '3E59030E-9AF9-48E7-90DA-79550A37453B', '540425', '察隅县');
INSERT INTO `sys_region` VALUES (2761, 'A9F38A18-6810-41E6-BCF4-6E9F4ECB213F', '540426', '朗县');
INSERT INTO `sys_region` VALUES (2762, '8950E4D3-D136-4BB9-922B-75C4DEE6DE34', '540500', '山南市');
INSERT INTO `sys_region` VALUES (2763, 'BDCB58ED-B711-456E-A7A0-BE019657434E', '540502', '乃东区');
INSERT INTO `sys_region` VALUES (2764, 'F5EAC570-6267-460C-9068-70C6DE9BDE56', '540521', '扎囊县');
INSERT INTO `sys_region` VALUES (2765, 'D6010607-B548-446F-B1DB-B5D0C2139095', '540522', '贡嘎县');
INSERT INTO `sys_region` VALUES (2766, 'F561FDC8-14E7-4227-90BB-867E1A716874', '540523', '桑日县');
INSERT INTO `sys_region` VALUES (2767, '794B1B24-B0E8-47E9-BC85-AE5F16E32A20', '540524', '琼结县');
INSERT INTO `sys_region` VALUES (2768, '34BF3B04-E72C-42B9-8AAF-646EB1942590', '540525', '曲松县');
INSERT INTO `sys_region` VALUES (2769, '30F6F45F-42AC-47AA-939D-9872069C24B5', '540526', '措美县');
INSERT INTO `sys_region` VALUES (2770, '6BB67FC4-F4E2-47FC-8A1B-02E3BF2B0C70', '540527', '洛扎县');
INSERT INTO `sys_region` VALUES (2771, '07AA4BF3-025E-449B-840B-737293E3300E', '540528', '加查县');
INSERT INTO `sys_region` VALUES (2772, '3BBF58FD-F15A-4990-BEE0-0DC4D7580E7E', '540529', '隆子县');
INSERT INTO `sys_region` VALUES (2773, 'AA1A51F0-CC94-42EF-AF11-4AE2394DF457', '540530', '错那县');
INSERT INTO `sys_region` VALUES (2774, 'D89B05D4-9CE4-453D-AE57-366F60A18788', '540531', '浪卡子县');
INSERT INTO `sys_region` VALUES (2775, 'B5C96A1A-8143-4557-93F0-24A7EA8860D9', '540600', '那曲市');
INSERT INTO `sys_region` VALUES (2776, '373F1E8E-5570-40A0-899A-964C60010CE1', '540602', '色尼区');
INSERT INTO `sys_region` VALUES (2777, '983C0B3D-9342-40BF-97E4-C190D58C8288', '540621', '嘉黎县');
INSERT INTO `sys_region` VALUES (2778, '68A62274-5417-46AA-A511-72BEA118007F', '540622', '比如县');
INSERT INTO `sys_region` VALUES (2779, 'AB371227-7ED2-4592-853B-4D837EF5B96C', '540623', '聂荣县');
INSERT INTO `sys_region` VALUES (2780, '8F9C9FBD-33D6-4C07-9D9C-6B4443E6B716', '540624', '安多县');
INSERT INTO `sys_region` VALUES (2781, '799D2276-49D2-4E1E-B75D-CF8F218F1AD6', '540625', '申扎县');
INSERT INTO `sys_region` VALUES (2782, '42D7D14B-ABFF-41E9-A69D-601C90C4F8B4', '540626', '索县');
INSERT INTO `sys_region` VALUES (2783, '420E0F40-C987-4452-9021-ABC3C2AA9F02', '540627', '班戈县');
INSERT INTO `sys_region` VALUES (2784, 'ACD4B038-AB0F-4E44-BF64-EBD8B4B22265', '540628', '巴青县');
INSERT INTO `sys_region` VALUES (2785, '3128CB75-01AA-49B1-9970-5431F8A59050', '540629', '尼玛县');
INSERT INTO `sys_region` VALUES (2786, '6CC32FCF-1DC8-43AF-A738-DA98B5B8EBF2', '540630', '双湖县');
INSERT INTO `sys_region` VALUES (2787, 'D891914B-259E-4D47-86C5-73CDB4565CDE', '542500', '阿里地区');
INSERT INTO `sys_region` VALUES (2788, '560776DB-6AF5-4ACF-8587-AD6AD7647F17', '542521', '普兰县');
INSERT INTO `sys_region` VALUES (2789, '1064F464-F6C2-44FD-B930-96930FE82EC5', '542522', '札达县');
INSERT INTO `sys_region` VALUES (2790, '175761A2-5FC7-4214-9706-6305B63400CF', '542523', '噶尔县');
INSERT INTO `sys_region` VALUES (2791, '3ED75CFC-4138-47E9-9BA3-D897C5D90E1B', '542524', '日土县');
INSERT INTO `sys_region` VALUES (2792, '4C069C34-DA85-45C5-984E-55CD3F50B141', '542525', '革吉县');
INSERT INTO `sys_region` VALUES (2793, 'D6CB6C00-938C-4944-BD1B-F80FC39697A2', '542526', '改则县');
INSERT INTO `sys_region` VALUES (2794, '3249B8E5-0F20-49D2-841B-41F4BD8D48FC', '542527', '措勤县');
INSERT INTO `sys_region` VALUES (2795, '3D54A5F9-CC4C-4AFC-9957-362AD4933AA4', '610000', '陕西省');
INSERT INTO `sys_region` VALUES (2796, '52B1AC9E-6B7F-4BED-85B3-2E86E6D53FBC', '610100', '西安市');
INSERT INTO `sys_region` VALUES (2797, 'B3C6AA12-AE9B-49EB-89CE-FE6C9F601D84', '610102', '新城区');
INSERT INTO `sys_region` VALUES (2798, 'F99600EA-D9B3-4038-916F-DB342B6D8591', '610103', '碑林区');
INSERT INTO `sys_region` VALUES (2799, '2C60C166-A923-4423-A07E-9D40BA3F4604', '610104', '莲湖区');
INSERT INTO `sys_region` VALUES (2800, 'CFBF00A1-755A-4EB8-9C21-EEEE0DC70411', '610111', '灞桥区');
INSERT INTO `sys_region` VALUES (2801, 'F8280DD9-143B-4109-BF8C-0CFD4A1BD026', '610112', '未央区');
INSERT INTO `sys_region` VALUES (2802, '4C9BFB6B-4CDF-4E7A-AFE7-4DA7FDD22F7B', '610113', '雁塔区');
INSERT INTO `sys_region` VALUES (2803, 'E5B272AA-3373-461E-AED0-196599815DDD', '610114', '阎良区');
INSERT INTO `sys_region` VALUES (2804, '06B75841-1CB8-4399-B536-C1606BDE9698', '610115', '临潼区');
INSERT INTO `sys_region` VALUES (2805, '5D75D956-F96F-440A-9BB8-430E045906D9', '610116', '长安区');
INSERT INTO `sys_region` VALUES (2806, '1F8E7C81-2EAA-4B0A-8C69-1034BE6A3276', '610117', '高陵区');
INSERT INTO `sys_region` VALUES (2807, 'F8D3169E-E984-4166-B126-E8F6149F2FA6', '610118', '鄠邑区');
INSERT INTO `sys_region` VALUES (2808, 'F524CD4E-3A1F-46BD-986B-6FEF9E38924E', '610122', '蓝田县');
INSERT INTO `sys_region` VALUES (2809, '8116E624-D010-48CD-8ABD-B35CAEBAC019', '610124', '周至县');
INSERT INTO `sys_region` VALUES (2810, 'FEBD78B9-FB78-4353-861A-04833434DFA6', '610200', '铜川市');
INSERT INTO `sys_region` VALUES (2811, 'D8D78480-6397-456C-AD0C-748566C9069F', '610202', '王益区');
INSERT INTO `sys_region` VALUES (2812, '711C4B2C-3FDE-47F9-8E35-EF6F192134B0', '610203', '印台区');
INSERT INTO `sys_region` VALUES (2813, '17741EFC-3EB2-4324-9372-BD8A1F9E5CD9', '610204', '耀州区');
INSERT INTO `sys_region` VALUES (2814, '7653E5AB-92A9-41DB-B002-0CDAD7BB1F99', '610222', '宜君县');
INSERT INTO `sys_region` VALUES (2815, 'E7008726-F7F9-4816-8324-DBFBEB1496F3', '610300', '宝鸡市');
INSERT INTO `sys_region` VALUES (2816, '0D6E116D-6976-4323-A920-DF2CEA38EEEE', '610302', '渭滨区');
INSERT INTO `sys_region` VALUES (2817, '562475AD-764B-4546-A79D-79487D6A1D5A', '610303', '金台区');
INSERT INTO `sys_region` VALUES (2818, '6E0EFC9D-4327-481C-8CB3-74273339F44A', '610304', '陈仓区');
INSERT INTO `sys_region` VALUES (2819, '42F92C44-AD4B-4F2F-A764-08FF2E2D659C', '610322', '凤翔县');
INSERT INTO `sys_region` VALUES (2820, '17EFE6C9-2042-48B3-AE62-C7A794C1C006', '610323', '岐山县');
INSERT INTO `sys_region` VALUES (2821, 'C67B9134-9C4B-4AA7-8ED1-C334CEC6C6DE', '610324', '扶风县');
INSERT INTO `sys_region` VALUES (2822, '231EC798-05D8-4EED-9198-31EBE082C746', '610326', '眉县');
INSERT INTO `sys_region` VALUES (2823, '09A833D0-6BE1-47EF-BB10-566F7DC257CA', '610327', '陇县');
INSERT INTO `sys_region` VALUES (2824, 'AA2D989A-0CBB-4C80-91B7-898B9B16AE83', '610328', '千阳县');
INSERT INTO `sys_region` VALUES (2825, '6BAB44BA-DB04-40BF-B693-CD64B86E6A94', '610329', '麟游县');
INSERT INTO `sys_region` VALUES (2826, 'D566F54D-10EC-4C05-ACAA-5A0C416B5434', '610330', '凤县');
INSERT INTO `sys_region` VALUES (2827, 'E379BDBC-898C-4695-8E24-2AE7282AF6EB', '610331', '太白县');
INSERT INTO `sys_region` VALUES (2828, '19927EE0-AF7B-40BF-BC82-F3E84D963004', '610400', '咸阳市');
INSERT INTO `sys_region` VALUES (2829, 'B9E80F1C-17A5-47D8-B1FE-C2C69E91D718', '610402', '秦都区');
INSERT INTO `sys_region` VALUES (2830, '64CD55AB-B344-4A10-B637-74B323F6B861', '610403', '杨陵区');
INSERT INTO `sys_region` VALUES (2831, '655565E3-EE44-45E5-8E11-83936D2D4A0C', '610404', '渭城区');
INSERT INTO `sys_region` VALUES (2832, '143D3379-547A-4DC9-9B0B-EB5945952514', '610422', '三原县');
INSERT INTO `sys_region` VALUES (2833, '42358589-DB77-4367-A9AD-CA45869C546E', '610423', '泾阳县');
INSERT INTO `sys_region` VALUES (2834, '62493444-B929-4CDD-B8F3-A43EB7777D32', '610424', '乾县');
INSERT INTO `sys_region` VALUES (2835, '64D0A8A5-17CE-4164-946B-BEC7C8AF6090', '610425', '礼泉县');
INSERT INTO `sys_region` VALUES (2836, '1CD035B2-6E66-462E-AD1E-238493F1C3B9', '610426', '永寿县');
INSERT INTO `sys_region` VALUES (2837, '42717DE4-AB43-49A1-A582-D14E50435544', '610428', '长武县');
INSERT INTO `sys_region` VALUES (2838, '53C5B58E-2F2E-45E6-8A62-057A22BF0D7A', '610429', '旬邑县');
INSERT INTO `sys_region` VALUES (2839, '26604F4E-3088-4BAB-9736-03C5A856D5D2', '610430', '淳化县');
INSERT INTO `sys_region` VALUES (2840, '38AEED5C-210A-4FD5-9263-3BEF1E32801B', '610431', '武功县');
INSERT INTO `sys_region` VALUES (2841, 'F6E4D469-BA68-477E-B104-494E7F1379F1', '610481', '兴平市');
INSERT INTO `sys_region` VALUES (2842, 'FACF2810-B63B-45D9-BE2E-70C05BA563CB', '610482', '彬州市');
INSERT INTO `sys_region` VALUES (2843, '2043B39F-0AA5-41B5-8B51-2D581FBDEA13', '610500', '渭南市');
INSERT INTO `sys_region` VALUES (2844, '5E7A4376-BFE6-4AC7-A2B2-D0ABF34FFAB7', '610502', '临渭区');
INSERT INTO `sys_region` VALUES (2845, '8D3D2BE3-C03F-4132-8D01-9C8BA93FB27C', '610503', '华州区');
INSERT INTO `sys_region` VALUES (2846, '31726C1E-EF55-403D-A9F4-EBB1E42148D1', '610522', '潼关县');
INSERT INTO `sys_region` VALUES (2847, 'D6F4D1D5-A88F-4BFD-A3BE-7047F95A3EBA', '610523', '大荔县');
INSERT INTO `sys_region` VALUES (2848, '8CC05141-0318-4327-BB10-E25BD7F4CF88', '610524', '合阳县');
INSERT INTO `sys_region` VALUES (2849, 'C837DB42-6D96-42FF-8668-0E59D359297E', '610525', '澄城县');
INSERT INTO `sys_region` VALUES (2850, 'BC1F84FD-4F35-422D-AB66-5CCA3D102F7F', '610526', '蒲城县');
INSERT INTO `sys_region` VALUES (2851, 'C1B5D6F4-DFF7-4B9D-A040-3ECCE4A42872', '610527', '白水县');
INSERT INTO `sys_region` VALUES (2852, '6474CC63-7801-4D08-B1EB-418AF6C0852E', '610528', '富平县');
INSERT INTO `sys_region` VALUES (2853, '511B2696-674B-417A-9764-0F6447834C2A', '610581', '韩城市');
INSERT INTO `sys_region` VALUES (2854, '2F3C95C6-6B2B-46E2-8671-8A44456A8074', '610582', '华阴市');
INSERT INTO `sys_region` VALUES (2855, '87D24CF2-7CCA-4C12-BDE7-558BDD7C262E', '610600', '延安市');
INSERT INTO `sys_region` VALUES (2856, 'C2695FD0-EDA6-4D61-A5D4-D9AAAB674EFA', '610602', '宝塔区');
INSERT INTO `sys_region` VALUES (2857, '743F41E7-C0AA-4D7B-9E43-F94E4975BC3A', '610603', '安塞区');
INSERT INTO `sys_region` VALUES (2858, '0CE4BF01-DACB-43A3-B92C-2F169DA79045', '610621', '延长县');
INSERT INTO `sys_region` VALUES (2859, 'DFED5B3C-12B3-42B3-B054-6B5C5CA34210', '610622', '延川县');
INSERT INTO `sys_region` VALUES (2860, '206B5CBC-7801-419E-AA1D-55176EE39A22', '610625', '志丹县');
INSERT INTO `sys_region` VALUES (2861, '4A9CDB1F-468A-4DCA-AD37-AB460C22A770', '610626', '吴起县');
INSERT INTO `sys_region` VALUES (2862, '544FD93C-AF54-4FF7-93AE-D625B438EBBD', '610627', '甘泉县');
INSERT INTO `sys_region` VALUES (2863, '5B49B7C4-93A2-43AA-AD63-2AA8FF4CC8A5', '610628', '富县');
INSERT INTO `sys_region` VALUES (2864, 'BEDC88A8-E232-4FFF-8852-9BE6C9200451', '610629', '洛川县');
INSERT INTO `sys_region` VALUES (2865, '4055F2D7-C66C-4C25-B261-DAD958599FC0', '610630', '宜川县');
INSERT INTO `sys_region` VALUES (2866, 'D3826294-9A27-41C2-951F-F23BF0D62E46', '610631', '黄龙县');
INSERT INTO `sys_region` VALUES (2867, '91514FFE-E87C-44F4-AF5F-B9435FD7ACD1', '610632', '黄陵县');
INSERT INTO `sys_region` VALUES (2868, 'F1AF6D7E-880C-4ECC-BD1C-4EEDECCA6104', '610681', '子长市');
INSERT INTO `sys_region` VALUES (2869, '9204C984-DD0B-44FE-8C98-DC706E715732', '610700', '汉中市');
INSERT INTO `sys_region` VALUES (2870, 'A996DCC5-7399-4390-ACE9-1A194EF81AF8', '610702', '汉台区');
INSERT INTO `sys_region` VALUES (2871, '16D1D2C8-BA9F-4F94-B8BA-7938CA011D26', '610703', '南郑区');
INSERT INTO `sys_region` VALUES (2872, '8B282362-10AC-4C1E-AB47-01C590DFD01A', '610722', '城固县');
INSERT INTO `sys_region` VALUES (2873, '75899999-80A5-47FD-A12F-05B34201DAD1', '610723', '洋县');
INSERT INTO `sys_region` VALUES (2874, 'DE682A4A-0F09-4F13-86F5-FFEA28ADE597', '610724', '西乡县');
INSERT INTO `sys_region` VALUES (2875, '14E81192-1C02-40CD-A73A-E187A55AA16D', '610725', '勉县');
INSERT INTO `sys_region` VALUES (2876, '1936B93A-34B5-4CE7-AC36-7FB54C44F09D', '610726', '宁强县');
INSERT INTO `sys_region` VALUES (2877, 'AE90BCDC-379A-415D-80E5-B8AD3213F409', '610727', '略阳县');
INSERT INTO `sys_region` VALUES (2878, 'F52CB1EC-A6E6-4750-AAE3-63B289732144', '610728', '镇巴县');
INSERT INTO `sys_region` VALUES (2879, '0EF4552D-F3CC-4B36-BAED-23DCC4AAE686', '610729', '留坝县');
INSERT INTO `sys_region` VALUES (2880, '9F197251-E3EB-4FE3-BDF4-C17EA31B5F94', '610730', '佛坪县');
INSERT INTO `sys_region` VALUES (2881, 'AD0538CD-D4EF-4216-89A7-45C59CE166DE', '610800', '榆林市');
INSERT INTO `sys_region` VALUES (2882, '278EBD78-B60C-4BB4-A167-CB724C2C91CF', '610802', '榆阳区');
INSERT INTO `sys_region` VALUES (2883, 'C09CCD00-83BA-458A-ABC4-BFFCB5827EB2', '610803', '横山区');
INSERT INTO `sys_region` VALUES (2884, 'A98684A2-880E-49C9-85C2-EA8C831DD525', '610822', '府谷县');
INSERT INTO `sys_region` VALUES (2885, '39EFD14B-874C-4E15-8F56-9D497CF0F73E', '610824', '靖边县');
INSERT INTO `sys_region` VALUES (2886, '8BDEA9E0-FBA1-4C09-9475-04584F209F42', '610825', '定边县');
INSERT INTO `sys_region` VALUES (2887, 'EB2C0BE1-9704-4965-8DBD-4E1BB7D25073', '610826', '绥德县');
INSERT INTO `sys_region` VALUES (2888, '84673356-BA4C-4008-A384-804025D99A7E', '610827', '米脂县');
INSERT INTO `sys_region` VALUES (2889, '3DDD2C4B-0853-4E10-A677-66F27A5FECF0', '610828', '佳县');
INSERT INTO `sys_region` VALUES (2890, '00275A25-254D-440B-8DFF-6DDFB00DF8FC', '610829', '吴堡县');
INSERT INTO `sys_region` VALUES (2891, '37556011-83BF-49CA-B3B2-83D6583D4149', '610830', '清涧县');
INSERT INTO `sys_region` VALUES (2892, '9453F5BB-2F55-4645-9028-E7B082BFBBA0', '610831', '子洲县');
INSERT INTO `sys_region` VALUES (2893, '29C8FB5C-FCD1-4725-9634-1D7C24C1DFF9', '610881', '神木市');
INSERT INTO `sys_region` VALUES (2894, '8E37D8A0-9278-4DDA-B048-8170597F5CEE', '610900', '安康市');
INSERT INTO `sys_region` VALUES (2895, '8C3F1073-F8C2-4EDA-AF6B-2E58955AC675', '610902', '汉滨区');
INSERT INTO `sys_region` VALUES (2896, '7AD348C6-32AF-4322-91C8-8A2465750701', '610921', '汉阴县');
INSERT INTO `sys_region` VALUES (2897, 'D747CE5D-D6BB-4DA0-96B8-028FCCE2B7DF', '610922', '石泉县');
INSERT INTO `sys_region` VALUES (2898, 'DCED8FCA-9145-4DCB-90EF-BFDDAFA0252E', '610923', '宁陕县');
INSERT INTO `sys_region` VALUES (2899, 'C3EE23DF-17E8-4807-A79B-73ABC0EA7819', '610924', '紫阳县');
INSERT INTO `sys_region` VALUES (2900, 'E9222284-52F7-492B-8CCF-4CF60BFC8B8A', '610925', '岚皋县');
INSERT INTO `sys_region` VALUES (2901, '235E4633-2BB4-49EE-8C61-AC5495A50263', '610926', '平利县');
INSERT INTO `sys_region` VALUES (2902, '49B964A2-00EB-4B75-88D4-D88D3E8B4495', '610927', '镇坪县');
INSERT INTO `sys_region` VALUES (2903, '9B4093DE-CE67-4145-B241-88C704FB354F', '610928', '旬阳县');
INSERT INTO `sys_region` VALUES (2904, '816E297A-CD2F-4DB6-88D4-75C92EBE4AFF', '610929', '白河县');
INSERT INTO `sys_region` VALUES (2905, '8BAB5339-A814-4FE9-A153-3C6755008013', '611000', '商洛市');
INSERT INTO `sys_region` VALUES (2906, 'A5ACAC52-ED9A-4B04-A610-BA2CFF0F39B2', '611002', '商州区');
INSERT INTO `sys_region` VALUES (2907, 'C897880C-E8F6-4844-8675-81504E6117F1', '611021', '洛南县');
INSERT INTO `sys_region` VALUES (2908, '085E7F3B-CB30-4479-AF9E-072F8FF558A3', '611022', '丹凤县');
INSERT INTO `sys_region` VALUES (2909, 'A06BD961-AB6A-4E67-AB0B-10FC5D04DB88', '611023', '商南县');
INSERT INTO `sys_region` VALUES (2910, 'B08A289B-0D3E-4FE3-825C-0648B195FA1C', '611024', '山阳县');
INSERT INTO `sys_region` VALUES (2911, 'FC164AA6-42FF-430F-BB20-756615A806B3', '611025', '镇安县');
INSERT INTO `sys_region` VALUES (2912, '68DA4C9E-1979-4CC4-A851-AE554480AD1E', '611026', '柞水县');
INSERT INTO `sys_region` VALUES (2913, 'B155C32D-69EF-477D-BF92-F383244717AE', '620000', '甘肃省');
INSERT INTO `sys_region` VALUES (2914, '03C24CCD-450C-4A77-9F24-5A3B55F86A0A', '620100', '兰州市');
INSERT INTO `sys_region` VALUES (2915, '81B7DA7C-95C2-4240-A85F-0C4E4EDA3DE3', '620102', '城关区');
INSERT INTO `sys_region` VALUES (2916, '5C60BDFB-84AF-4F0E-8722-3F0265F19D65', '620103', '七里河区');
INSERT INTO `sys_region` VALUES (2917, 'B5722BBE-2BA3-4ED3-8F45-05B00E8B7E5C', '620104', '西固区');
INSERT INTO `sys_region` VALUES (2918, 'A98CC734-034A-48C5-99D8-9797F571C6F7', '620105', '安宁区');
INSERT INTO `sys_region` VALUES (2919, '061580FA-0032-4183-ADB7-03845CBA1379', '620111', '红古区');
INSERT INTO `sys_region` VALUES (2920, '7A08B021-B383-451B-83EA-E5704A82833E', '620121', '永登县');
INSERT INTO `sys_region` VALUES (2921, 'A3253CD0-445E-4B31-894E-90ABE3332FA5', '620122', '皋兰县');
INSERT INTO `sys_region` VALUES (2922, 'A3684D32-57D8-4A7C-A63E-6A50C81CDE19', '620123', '榆中县');
INSERT INTO `sys_region` VALUES (2923, '66101ADE-5604-4E56-9F82-0B37BB4143C6', '620200', '嘉峪关市');
INSERT INTO `sys_region` VALUES (2924, '04A67354-0E2A-4F10-BC5C-224070F75A49', '620201', '市辖区');
INSERT INTO `sys_region` VALUES (2925, 'C7B82D6C-E5A7-479F-990C-E66E0EB36510', '620300', '金昌市');
INSERT INTO `sys_region` VALUES (2926, '0C8CC1EC-1B33-411F-BF01-3E68C831B5B9', '620302', '金川区');
INSERT INTO `sys_region` VALUES (2927, '037813FB-F296-4F6C-93C6-3247B3D94F44', '620321', '永昌县');
INSERT INTO `sys_region` VALUES (2928, 'C8AD7300-4FED-4B4F-A304-3E172BFC4C76', '620400', '白银市');
INSERT INTO `sys_region` VALUES (2929, '17FA06B5-B56B-4C6E-9F3F-FBAD1BFAA1DB', '620402', '白银区');
INSERT INTO `sys_region` VALUES (2930, 'C50E6F16-AF40-4CEF-8681-38D5885243C2', '620403', '平川区');
INSERT INTO `sys_region` VALUES (2931, '5157CD84-67F3-4500-AEF5-31DA2DC99806', '620421', '靖远县');
INSERT INTO `sys_region` VALUES (2932, 'EF46DF2C-E922-4CF6-BCAC-642960752410', '620422', '会宁县');
INSERT INTO `sys_region` VALUES (2933, 'FC775D35-1D49-4EE1-AB0E-F0EBBBB3ADA0', '620423', '景泰县');
INSERT INTO `sys_region` VALUES (2934, '48C02F99-CE20-4262-9BE2-A71FFD5369AE', '620500', '天水市');
INSERT INTO `sys_region` VALUES (2935, 'F0C9FB76-3787-4189-96A5-F7D17B49F106', '620502', '秦州区');
INSERT INTO `sys_region` VALUES (2936, '9BE68B2C-33BE-484D-9198-3CB307FC2A28', '620503', '麦积区');
INSERT INTO `sys_region` VALUES (2937, 'E2E99E69-AB3E-440D-9076-432883154633', '620521', '清水县');
INSERT INTO `sys_region` VALUES (2938, 'BA963DD9-32C8-4950-9539-EC5445F7279E', '620522', '秦安县');
INSERT INTO `sys_region` VALUES (2939, '8766575C-AB69-4AE3-91A3-B5E79A10D990', '620523', '甘谷县');
INSERT INTO `sys_region` VALUES (2940, '0FF0BE82-36D7-4D65-ADCC-43953F2EDD0E', '620524', '武山县');
INSERT INTO `sys_region` VALUES (2941, '7E94B05D-F447-4BF4-8EB8-592F3412F974', '620525', '张家川回族自治县');
INSERT INTO `sys_region` VALUES (2942, '7AF7111F-7F09-4C24-A303-EEE6B56C1862', '620600', '武威市');
INSERT INTO `sys_region` VALUES (2943, 'B53DE3A5-966A-47C2-B706-239F0420C88E', '620602', '凉州区');
INSERT INTO `sys_region` VALUES (2944, '03828152-208E-4911-91AA-6B5C8CB32774', '620621', '民勤县');
INSERT INTO `sys_region` VALUES (2945, 'D10C4DCF-0F47-49C1-A8B3-CAC0A5823C89', '620622', '古浪县');
INSERT INTO `sys_region` VALUES (2946, 'CE37E9D8-AB5D-46EC-8F14-33E9F2841053', '620623', '天祝藏族自治县');
INSERT INTO `sys_region` VALUES (2947, '8B738A65-509A-4346-9BC1-C5922DBC77CE', '620700', '张掖市');
INSERT INTO `sys_region` VALUES (2948, 'F601F019-1AA7-4F66-8F3F-A11117709128', '620702', '甘州区');
INSERT INTO `sys_region` VALUES (2949, 'C38C9AE8-470D-41FF-84AE-77AE5FC74E73', '620721', '肃南裕固族自治县');
INSERT INTO `sys_region` VALUES (2950, '4E75A4C7-E683-4DE0-B482-273295112220', '620722', '民乐县');
INSERT INTO `sys_region` VALUES (2951, 'A987C131-669B-4351-B9B2-369200C20BF2', '620723', '临泽县');
INSERT INTO `sys_region` VALUES (2952, 'A007B62F-A41A-4959-8CA4-051B1E145D51', '620724', '高台县');
INSERT INTO `sys_region` VALUES (2953, '2FD1321D-2202-436D-85F7-FDF9C7C9711B', '620725', '山丹县');
INSERT INTO `sys_region` VALUES (2954, 'EF5D64FD-E41D-4B5C-88DB-030A2D9D8E3F', '620800', '平凉市');
INSERT INTO `sys_region` VALUES (2955, '5EE75795-C35A-4654-8E6F-961F94B92E29', '620802', '崆峒区');
INSERT INTO `sys_region` VALUES (2956, '5A7D66CB-C8A5-4D33-A244-9730D59F7598', '620821', '泾川县');
INSERT INTO `sys_region` VALUES (2957, 'C9F3708B-F84E-4910-A492-D24C763F7336', '620822', '灵台县');
INSERT INTO `sys_region` VALUES (2958, 'A285967E-96D5-4F8E-AB86-FF753BBF2F81', '620823', '崇信县');
INSERT INTO `sys_region` VALUES (2959, 'C9E68B0B-8BDF-42E0-98EE-F9631AB4B953', '620825', '庄浪县');
INSERT INTO `sys_region` VALUES (2960, '05739C03-A95B-4110-99A6-37E513E347D1', '620826', '静宁县');
INSERT INTO `sys_region` VALUES (2961, '3DD1F68A-AD7A-4B65-8738-BBA95F85BC25', '620881', '华亭市');
INSERT INTO `sys_region` VALUES (2962, 'FF529428-D63A-42DD-A927-3E47E2110133', '620900', '酒泉市');
INSERT INTO `sys_region` VALUES (2963, 'D3628C6D-2B38-423F-B9FD-A8433522CBA1', '620902', '肃州区');
INSERT INTO `sys_region` VALUES (2964, '6A3C9942-938F-46E8-847D-5C09DD74C280', '620921', '金塔县');
INSERT INTO `sys_region` VALUES (2965, 'B910D3F2-C63E-456B-B8D7-E5C3A8FD66F4', '620922', '瓜州县');
INSERT INTO `sys_region` VALUES (2966, 'CA0CB796-33C3-4926-95BF-3A53B316447F', '620923', '肃北蒙古族自治县');
INSERT INTO `sys_region` VALUES (2967, '9E88F798-208D-421C-8477-88E3B33D7960', '620924', '阿克塞哈萨克族自治县');
INSERT INTO `sys_region` VALUES (2968, '59D59709-BDB2-4115-A783-60BDE3A5FFF0', '620981', '玉门市');
INSERT INTO `sys_region` VALUES (2969, 'D3EA528B-DB34-447A-B199-023C83389E0B', '620982', '敦煌市');
INSERT INTO `sys_region` VALUES (2970, '8DD588A6-B434-461F-8501-8B02F6ECB8BA', '621000', '庆阳市');
INSERT INTO `sys_region` VALUES (2971, '9E60CC73-2012-434E-9736-5E1CA0E42C4E', '621002', '西峰区');
INSERT INTO `sys_region` VALUES (2972, '17AEE961-2646-47CF-A9BD-3000018BEB4B', '621021', '庆城县');
INSERT INTO `sys_region` VALUES (2973, '485454CE-2F75-4B1B-A402-9711ABECD961', '621022', '环县');
INSERT INTO `sys_region` VALUES (2974, '4E6BF9F5-9E4A-4BDD-892A-846AB908402D', '621023', '华池县');
INSERT INTO `sys_region` VALUES (2975, '3558C119-204C-4F55-BF67-96E4927DCD54', '621024', '合水县');
INSERT INTO `sys_region` VALUES (2976, '3BC3D2FB-052A-491C-B88B-E47B150F415B', '621025', '正宁县');
INSERT INTO `sys_region` VALUES (2977, '4AD519DA-AD04-4573-97A7-D380828FA57E', '621026', '宁县');
INSERT INTO `sys_region` VALUES (2978, 'B2371D5A-0095-4487-98E1-3A7E672C280E', '621027', '镇原县');
INSERT INTO `sys_region` VALUES (2979, '67367A9D-5EB5-4C99-9BED-E8ADACF6C073', '621100', '定西市');
INSERT INTO `sys_region` VALUES (2980, '5B0D8D8B-4F1C-4918-A195-263357B529BE', '621102', '安定区');
INSERT INTO `sys_region` VALUES (2981, '39F4B83F-00BE-42A9-80B9-E067B4ED9E1B', '621121', '通渭县');
INSERT INTO `sys_region` VALUES (2982, '5FE8F453-DF48-4DA1-9BA4-3FC2D6324E8A', '621122', '陇西县');
INSERT INTO `sys_region` VALUES (2983, '7CAC657D-599A-43D7-881F-1AACB8200A80', '621123', '渭源县');
INSERT INTO `sys_region` VALUES (2984, '239D7D5A-D0C7-48D4-B2BF-F3528786AFB5', '621124', '临洮县');
INSERT INTO `sys_region` VALUES (2985, '79078491-DF32-434E-B090-52061FB5A664', '621125', '漳县');
INSERT INTO `sys_region` VALUES (2986, 'EF4646E3-5D59-4D1C-BC0E-A6011DD296C7', '621126', '岷县');
INSERT INTO `sys_region` VALUES (2987, '1241EB5B-6436-4547-8D5D-AEEAD3F00E20', '621200', '陇南市');
INSERT INTO `sys_region` VALUES (2988, '299CFC48-A1E6-435E-B16C-D903718603E2', '621202', '武都区');
INSERT INTO `sys_region` VALUES (2989, 'C1C281D8-CCF0-4643-999F-14C1B86844DF', '621221', '成县');
INSERT INTO `sys_region` VALUES (2990, 'FBAFC415-C85A-4A5E-9BAE-1448F66E62F4', '621222', '文县');
INSERT INTO `sys_region` VALUES (2991, 'D79FFA64-8396-45D3-9203-DA93F97A7B8C', '621223', '宕昌县');
INSERT INTO `sys_region` VALUES (2992, 'FEF96937-0251-4374-82F7-701BB777132C', '621224', '康县');
INSERT INTO `sys_region` VALUES (2993, 'B8620DC7-F633-4A3A-93D4-D9FBBB4BFC52', '621225', '西和县');
INSERT INTO `sys_region` VALUES (2994, 'D1A155EA-F78B-4D12-A3DB-332D252F6410', '621226', '礼县');
INSERT INTO `sys_region` VALUES (2995, '1411CD89-837D-4CC7-87EA-0D379AC92F8E', '621227', '徽县');
INSERT INTO `sys_region` VALUES (2996, 'CC7AB72A-0688-4A58-974D-504BF0FCE21C', '621228', '两当县');
INSERT INTO `sys_region` VALUES (2997, '48F3271E-A0FB-4324-8426-F2610725A6F8', '622900', '临夏回族自治州');
INSERT INTO `sys_region` VALUES (2998, 'A8117962-19E9-4DE9-A4F3-3AD11E72529F', '622901', '临夏市');
INSERT INTO `sys_region` VALUES (2999, '3F51AB5B-79CA-4493-89FD-D5A926B499F7', '622921', '临夏县');
INSERT INTO `sys_region` VALUES (3000, 'CF6755C2-A32B-4535-8ECB-FEC8211C0626', '622922', '康乐县');
INSERT INTO `sys_region` VALUES (3001, 'A104CC01-376A-447F-B813-E55129862D80', '622923', '永靖县');
INSERT INTO `sys_region` VALUES (3002, 'F3447D0B-7C5B-4340-80A0-E94BE808EEB1', '622924', '广河县');
INSERT INTO `sys_region` VALUES (3003, '57C25E59-307D-465D-AFE8-B043B6593DCD', '622925', '和政县');
INSERT INTO `sys_region` VALUES (3004, '134DF13F-A122-44D0-8990-4DE58C0D2A2D', '622926', '东乡族自治县');
INSERT INTO `sys_region` VALUES (3005, 'B3A41F37-256B-4211-B0D0-36AB29C28FB9', '622927', '积石山保安族东乡族撒拉族自治县');
INSERT INTO `sys_region` VALUES (3006, '0E70A003-F54C-4B81-BBB3-5DBAE34A7033', '623000', '甘南藏族自治州');
INSERT INTO `sys_region` VALUES (3007, '929654B2-A065-4AE6-AA6D-75A90ABEADF4', '623001', '合作市');
INSERT INTO `sys_region` VALUES (3008, 'C95FE054-4867-41ED-B868-6A92F4EF4CD6', '623021', '临潭县');
INSERT INTO `sys_region` VALUES (3009, '02EFB824-2FFB-4203-8F75-B459983D8150', '623022', '卓尼县');
INSERT INTO `sys_region` VALUES (3010, 'DB5DF8B7-CD95-4DC8-B119-0AEEE3182D4D', '623023', '舟曲县');
INSERT INTO `sys_region` VALUES (3011, '2BC01BB8-4575-4C8B-9FC8-F93DF8C67C9D', '623024', '迭部县');
INSERT INTO `sys_region` VALUES (3012, '1B6C6A94-4D82-4C9F-97C3-1044FC670F24', '623025', '玛曲县');
INSERT INTO `sys_region` VALUES (3013, '784A41B1-E575-4AEB-B2E8-DF598141FE61', '623026', '碌曲县');
INSERT INTO `sys_region` VALUES (3014, 'FC3D5EE7-C4B6-4754-B47C-7270251FDBBD', '623027', '夏河县');
INSERT INTO `sys_region` VALUES (3015, '49D071B4-427E-4F77-B426-E7962736C555', '630000', '青海省');
INSERT INTO `sys_region` VALUES (3016, '7E80C1F0-310C-4BA7-B07A-D6D9AD76050F', '630100', '西宁市');
INSERT INTO `sys_region` VALUES (3017, '31E8F594-2D96-4702-86A3-6EA9FE58331F', '630102', '城东区');
INSERT INTO `sys_region` VALUES (3018, '6041EAB7-3842-4007-A521-EE3AB6A87B8D', '630103', '城中区');
INSERT INTO `sys_region` VALUES (3019, '0AE44718-D9DB-4E54-9959-61112D59EEE2', '630104', '城西区');
INSERT INTO `sys_region` VALUES (3020, '023F79BE-83E5-4D58-85D7-8DEF14EB2041', '630105', '城北区');
INSERT INTO `sys_region` VALUES (3021, 'D06D75B2-7237-42F2-B64E-2EEDCDCE4C47', '630106', '湟中区');
INSERT INTO `sys_region` VALUES (3022, 'A1F42143-1662-40C4-A806-8B0DEEE10C9B', '630121', '大通回族土族自治县');
INSERT INTO `sys_region` VALUES (3023, '92669EF7-F75E-4F13-801F-7DCE902C200A', '630123', '湟源县');
INSERT INTO `sys_region` VALUES (3024, '7EB48BF2-8997-4EB2-8641-93A7E3417524', '630200', '海东市');
INSERT INTO `sys_region` VALUES (3025, '49ED420A-20DC-4AA6-8C3B-1070805D1320', '630202', '乐都区');
INSERT INTO `sys_region` VALUES (3026, 'E9B739D6-CC1A-4775-ABA6-B11E9A412926', '630203', '平安区');
INSERT INTO `sys_region` VALUES (3027, 'F6720DDC-CE21-426B-A006-1358E2557705', '630222', '民和回族土族自治县');
INSERT INTO `sys_region` VALUES (3028, '493E05D3-9055-458D-9E5F-EE02B65809CA', '630223', '互助土族自治县');
INSERT INTO `sys_region` VALUES (3029, 'CF01F5EC-E135-4816-ABAC-B73683CE08C5', '630224', '化隆回族自治县');
INSERT INTO `sys_region` VALUES (3030, 'DCA38A7D-B23D-48C3-AB85-D6FD5E86EC0D', '630225', '循化撒拉族自治县');
INSERT INTO `sys_region` VALUES (3031, 'AB1073FA-D5EC-4CB4-9A22-DBC3A4CB0A32', '632200', '海北藏族自治州');
INSERT INTO `sys_region` VALUES (3032, '4359D349-E20E-488A-8D98-2018DFB13122', '632221', '门源回族自治县');
INSERT INTO `sys_region` VALUES (3033, 'F88F31D0-9C0B-4AE5-BF50-C18875166DEF', '632222', '祁连县');
INSERT INTO `sys_region` VALUES (3034, '1CACD8AD-AA39-46E8-B0DD-F202AAE88DB4', '632223', '海晏县');
INSERT INTO `sys_region` VALUES (3035, 'AC873513-47DB-4AA6-9E20-4286B8792916', '632224', '刚察县');
INSERT INTO `sys_region` VALUES (3036, 'F16B3354-71E9-41EC-82F2-FB3527464F1E', '632300', '黄南藏族自治州');
INSERT INTO `sys_region` VALUES (3037, 'AD7CADA5-34E2-4912-B88E-BDF0F3A3E4DE', '632321', '同仁县');
INSERT INTO `sys_region` VALUES (3038, '4F0572E5-BB23-4480-8C07-C5F7EEEB6E12', '632322', '尖扎县');
INSERT INTO `sys_region` VALUES (3039, 'B9CA1373-D6E0-42E6-9A1A-81C9581E95FD', '632323', '泽库县');
INSERT INTO `sys_region` VALUES (3040, '4DD7EA2C-E54B-4B46-B575-9F5A69851DC3', '632324', '河南蒙古族自治县');
INSERT INTO `sys_region` VALUES (3041, '83653B0F-FCE8-4F4A-A2BB-27039F139199', '632500', '海南藏族自治州');
INSERT INTO `sys_region` VALUES (3042, 'F670E91B-91A5-41D8-B30C-94815163665E', '632521', '共和县');
INSERT INTO `sys_region` VALUES (3043, '02EB32DA-B872-4797-9A82-3DDB93EB1581', '632522', '同德县');
INSERT INTO `sys_region` VALUES (3044, '0E34C43B-A475-49A1-99DD-1D03A6317FD4', '632523', '贵德县');
INSERT INTO `sys_region` VALUES (3045, '7E024F02-53E0-481E-973A-B14846FCF4A9', '632524', '兴海县');
INSERT INTO `sys_region` VALUES (3046, '5B18D7EC-5D03-4E71-BAB2-A3A52268BC9B', '632525', '贵南县');
INSERT INTO `sys_region` VALUES (3047, '07E8F469-804C-4812-8752-D69A5A7007A6', '632600', '果洛藏族自治州');
INSERT INTO `sys_region` VALUES (3048, '1C8C5DE9-27A5-4D7F-9BDA-A129DEC75C20', '632621', '玛沁县');
INSERT INTO `sys_region` VALUES (3049, '04BB9C87-9191-414C-BA9A-261471358BEE', '632622', '班玛县');
INSERT INTO `sys_region` VALUES (3050, 'F604CA4E-BFBE-4549-AF39-90BADABBE40E', '632623', '甘德县');
INSERT INTO `sys_region` VALUES (3051, '3044075A-2E18-4743-8F3D-5DF1A411DE9D', '632624', '达日县');
INSERT INTO `sys_region` VALUES (3052, '6AB5768E-1710-4F51-B06B-CF41A09A09D6', '632625', '久治县');
INSERT INTO `sys_region` VALUES (3053, '3518022B-49C6-4227-99AE-C6F5D1D6338E', '632626', '玛多县');
INSERT INTO `sys_region` VALUES (3054, '9CE60AEC-CF98-46DA-BD40-47C66D65997A', '632700', '玉树藏族自治州');
INSERT INTO `sys_region` VALUES (3055, 'F46D50B1-EF68-4298-81DA-5E0BAB346D4C', '632701', '玉树市');
INSERT INTO `sys_region` VALUES (3056, 'EE25CEE9-F976-4B19-A389-A986A30D0545', '632722', '杂多县');
INSERT INTO `sys_region` VALUES (3057, 'D3513C7E-BFBB-4039-8D7E-6EAB75906727', '632723', '称多县');
INSERT INTO `sys_region` VALUES (3058, 'AA77E942-D5EF-47E9-9438-90A49CB7DDA0', '632724', '治多县');
INSERT INTO `sys_region` VALUES (3059, '3744395E-368E-41ED-B93F-B0F77E7D42BE', '632725', '囊谦县');
INSERT INTO `sys_region` VALUES (3060, 'A6AC1CFA-E725-44C5-BFB8-D55041BB94B3', '632726', '曲麻莱县');
INSERT INTO `sys_region` VALUES (3061, 'F6184F2A-A2E3-4380-BED3-CA7B269F1170', '632800', '海西蒙古族藏族自治州');
INSERT INTO `sys_region` VALUES (3062, '0491933C-1232-4C04-B658-CE3072710341', '632801', '格尔木市');
INSERT INTO `sys_region` VALUES (3063, 'E2B85ABA-F8DE-46E6-9457-BBD57FE26702', '632802', '德令哈市');
INSERT INTO `sys_region` VALUES (3064, 'C98B45C6-560A-4432-81A5-53FCF90A0008', '632803', '茫崖市');
INSERT INTO `sys_region` VALUES (3065, '6221ACA9-04DB-4CA2-A3EF-20321E21C22A', '632821', '乌兰县');
INSERT INTO `sys_region` VALUES (3066, '625DB0C0-E00D-41C5-8F4A-C27B7DD77005', '632822', '都兰县');
INSERT INTO `sys_region` VALUES (3067, 'BCAE6106-AA59-4C9E-9BD5-93EC21E1AF38', '632823', '天峻县');
INSERT INTO `sys_region` VALUES (3068, 'F26392D3-257F-4D56-81FB-B19FC271768A', '640000', '宁夏回族自治区');
INSERT INTO `sys_region` VALUES (3069, '69C35511-7708-422A-A35F-76EEE5FD526C', '640100', '银川市');
INSERT INTO `sys_region` VALUES (3070, '215BB491-009D-491B-B8E7-AAB99F9CE8F1', '640104', '兴庆区');
INSERT INTO `sys_region` VALUES (3071, 'AF5204C2-6AFF-4A7F-AA78-5180695B1523', '640105', '西夏区');
INSERT INTO `sys_region` VALUES (3072, '9405EA51-DEB9-4FB8-BB47-3A9197CF9776', '640106', '金凤区');
INSERT INTO `sys_region` VALUES (3073, 'FD4EC384-A879-4102-AFE9-705A56B7D0BA', '640121', '永宁县');
INSERT INTO `sys_region` VALUES (3074, 'F56580C7-CD8C-43FF-9317-47134A103A43', '640122', '贺兰县');
INSERT INTO `sys_region` VALUES (3075, 'C7D39E26-5185-4856-94E1-BFB5B02F50A1', '640181', '灵武市');
INSERT INTO `sys_region` VALUES (3076, 'E9AFD75A-EC66-4C81-A837-0AE5D21A7F18', '640200', '石嘴山市');
INSERT INTO `sys_region` VALUES (3077, '796A09F2-8118-4BA8-B9D7-4B9D276FBBBE', '640202', '大武口区');
INSERT INTO `sys_region` VALUES (3078, '8C0F4B90-5572-44E4-BE62-98EA3385B0BD', '640205', '惠农区');
INSERT INTO `sys_region` VALUES (3079, '1356D6ED-FA8F-4E8A-A496-34CC6B171C14', '640221', '平罗县');
INSERT INTO `sys_region` VALUES (3080, '50567B13-4FF6-4615-8108-DDC6A13AD939', '640300', '吴忠市');
INSERT INTO `sys_region` VALUES (3081, '4334C4B1-27FA-4091-864F-AEE801787DB7', '640302', '利通区');
INSERT INTO `sys_region` VALUES (3082, '0AF0356F-AF42-4068-913D-57C0BB7ACC07', '640303', '红寺堡区');
INSERT INTO `sys_region` VALUES (3083, '6215C91C-5EE9-4B71-AB42-1730606DB2B3', '640323', '盐池县');
INSERT INTO `sys_region` VALUES (3084, 'A74E0F7A-3C9C-4BF5-98E7-8610AEEC727D', '640324', '同心县');
INSERT INTO `sys_region` VALUES (3085, '46F466E2-06E9-42FB-B3C6-6FC6C1085AE3', '640381', '青铜峡市');
INSERT INTO `sys_region` VALUES (3086, '3B9A3E67-7803-467D-AEE8-69FD97C43A72', '640400', '固原市');
INSERT INTO `sys_region` VALUES (3087, 'D0022030-C931-4B21-BA65-8C02AF024253', '640402', '原州区');
INSERT INTO `sys_region` VALUES (3088, 'AB02E27D-A591-47C8-9DA4-435DCB898C65', '640422', '西吉县');
INSERT INTO `sys_region` VALUES (3089, 'BF6CA96B-EB8B-4EA2-8A96-E97F42D21AC9', '640423', '隆德县');
INSERT INTO `sys_region` VALUES (3090, '0B0AF565-56AA-40A1-8043-7B668655F68F', '640424', '泾源县');
INSERT INTO `sys_region` VALUES (3091, 'CD2A583A-F929-4662-BAA8-5B1F11CB3E22', '640425', '彭阳县');
INSERT INTO `sys_region` VALUES (3092, '9DE9BD3D-C74B-4A0E-84EB-8892386B669A', '640500', '中卫市');
INSERT INTO `sys_region` VALUES (3093, '2988F206-444A-47FD-966B-BAD5097B8CF3', '640502', '沙坡头区');
INSERT INTO `sys_region` VALUES (3094, '0134E8BA-37FD-4BAD-9C0E-2E18A04A56D2', '640521', '中宁县');
INSERT INTO `sys_region` VALUES (3095, '234FDA6B-2D80-4501-945D-500D45448B28', '640522', '海原县');
INSERT INTO `sys_region` VALUES (3096, 'ADFF21CE-6CF8-4C29-82B1-679EC898A1FD', '650000', '新疆维吾尔自治区');
INSERT INTO `sys_region` VALUES (3097, 'FEFFD403-A2AA-4834-99DD-D33C7A608EDD', '650100', '乌鲁木齐市');
INSERT INTO `sys_region` VALUES (3098, '2D9B9814-0321-4248-B5CE-870C4AF33D3F', '650102', '天山区');
INSERT INTO `sys_region` VALUES (3099, '370E10B6-24D6-4D38-89CB-260805E0AB55', '650103', '沙依巴克区');
INSERT INTO `sys_region` VALUES (3100, '14FE8FFF-A3A0-43B7-9685-C6C996C9A1AE', '650104', '新市区');
INSERT INTO `sys_region` VALUES (3101, '61389D6E-94B4-4ED1-B24C-E414516127B9', '650105', '水磨沟区');
INSERT INTO `sys_region` VALUES (3102, '5D4D797A-B882-496B-AD6C-BA5D1E0F0917', '650106', '头屯河区');
INSERT INTO `sys_region` VALUES (3103, 'DD3FF617-A43F-4142-BC48-8A0D3C452822', '650107', '达坂城区');
INSERT INTO `sys_region` VALUES (3104, '4E2568D2-F3C2-4A6F-A6AE-7C2F53F82B5E', '650109', '米东区');
INSERT INTO `sys_region` VALUES (3105, '89AEC08F-0AB0-4B5F-B25A-C3A7380935B9', '650121', '乌鲁木齐县');
INSERT INTO `sys_region` VALUES (3106, '0D23E964-FB0B-449F-8127-B78DB76FC335', '650200', '克拉玛依市');
INSERT INTO `sys_region` VALUES (3107, '90FFE66A-589E-4555-B1B5-868E394B6334', '650202', '独山子区');
INSERT INTO `sys_region` VALUES (3108, '57C42D4F-72CD-4458-8597-2044D3EF31A7', '650203', '克拉玛依区');
INSERT INTO `sys_region` VALUES (3109, '0ADF8F57-1EDD-4D64-9091-5B1A4BCCF834', '650204', '白碱滩区');
INSERT INTO `sys_region` VALUES (3110, 'CBE2FDCF-846B-49CA-B372-51E5E2B867A5', '650205', '乌尔禾区');
INSERT INTO `sys_region` VALUES (3111, '2ACCF60B-61F5-41A5-B9A4-732760BB77C2', '650400', '吐鲁番市');
INSERT INTO `sys_region` VALUES (3112, 'F9E3CBC7-01B4-44A4-9A26-0A9B909E54E4', '650402', '高昌区');
INSERT INTO `sys_region` VALUES (3113, 'DDE5C71C-9023-43EB-BD42-FF9B225BEFD6', '650421', '鄯善县');
INSERT INTO `sys_region` VALUES (3114, '656F6F9E-9C75-4C08-8941-1D231577E274', '650422', '托克逊县');
INSERT INTO `sys_region` VALUES (3115, 'ABD28D01-8340-457E-B08E-B22574625628', '650500', '哈密市');
INSERT INTO `sys_region` VALUES (3116, '8D5D7E79-19B0-424A-A32D-01885A2755FA', '650502', '伊州区');
INSERT INTO `sys_region` VALUES (3117, '475C9C11-BF1E-4EE9-9072-A309F8990610', '650521', '巴里坤哈萨克自治县');
INSERT INTO `sys_region` VALUES (3118, 'FF7C5DE4-2595-4BA8-BD1D-0B94235DD84C', '650522', '伊吾县');
INSERT INTO `sys_region` VALUES (3119, '7F0012BE-EA95-4374-9FD7-AFDC6B64051A', '652300', '昌吉回族自治州');
INSERT INTO `sys_region` VALUES (3120, 'C843845B-AC84-4214-9081-C207993E1664', '652301', '昌吉市');
INSERT INTO `sys_region` VALUES (3121, 'A5C93FE4-2202-46F3-996F-F7293C99D208', '652302', '阜康市');
INSERT INTO `sys_region` VALUES (3122, '8ACB641A-E987-4420-A3A5-BBF75F6269B4', '652323', '呼图壁县');
INSERT INTO `sys_region` VALUES (3123, '8C4DBE25-E167-4C8F-8A83-B8ACDDD6B8C7', '652324', '玛纳斯县');
INSERT INTO `sys_region` VALUES (3124, 'B7C4853F-E985-4EC2-86A1-A91FB82C7816', '652325', '奇台县');
INSERT INTO `sys_region` VALUES (3125, '674440BC-BB9C-460A-BA66-992356F9AC06', '652327', '吉木萨尔县');
INSERT INTO `sys_region` VALUES (3126, '4B71B3E0-F95F-4890-98D1-33E6B95A206A', '652328', '木垒哈萨克自治县');
INSERT INTO `sys_region` VALUES (3127, '9852E13A-2F52-41B4-927E-CAF44084EF86', '652700', '博尔塔拉蒙古自治州');
INSERT INTO `sys_region` VALUES (3128, 'D5045599-4820-4370-A213-33F05849A0D5', '652701', '博乐市');
INSERT INTO `sys_region` VALUES (3129, '90CAA8E2-96BC-416F-BC7B-2B08C25CF938', '652702', '阿拉山口市');
INSERT INTO `sys_region` VALUES (3130, '6A717A12-FC8E-4DB0-B035-74E8780F2EF9', '652722', '精河县');
INSERT INTO `sys_region` VALUES (3131, '74D3BAA4-6E25-4DB6-B547-DAEB6CE08B06', '652723', '温泉县');
INSERT INTO `sys_region` VALUES (3132, '1826803E-A174-479A-AA1F-38A8323D2AFF', '652800', '巴音郭楞蒙古自治州');
INSERT INTO `sys_region` VALUES (3133, 'CA60EDB0-BA44-4944-AC22-C900C6AA9E02', '652801', '库尔勒市');
INSERT INTO `sys_region` VALUES (3134, '5BBB7D96-8C93-4515-8C51-003AEF4BEC1B', '652822', '轮台县');
INSERT INTO `sys_region` VALUES (3135, 'EE94BBD3-108F-472A-ABB7-E827DE3318A3', '652823', '尉犁县');
INSERT INTO `sys_region` VALUES (3136, 'BED99B7D-E6D3-41DE-BD2F-7F9033EB30C5', '652824', '若羌县');
INSERT INTO `sys_region` VALUES (3137, '19AAAC66-67D9-4B22-AEA8-FC0C17D0CB69', '652825', '且末县');
INSERT INTO `sys_region` VALUES (3138, 'F5B1815C-F179-415B-BAEF-85088A3FCC34', '652826', '焉耆回族自治县');
INSERT INTO `sys_region` VALUES (3139, '62586DE3-5EC5-4F56-937E-7E8FCE60003E', '652827', '和静县');
INSERT INTO `sys_region` VALUES (3140, '73C0025C-611D-41EE-BD5D-0B04554D729B', '652828', '和硕县');
INSERT INTO `sys_region` VALUES (3141, '0980E5E2-DC62-4BFA-8DC9-2BC7E6A0CAAC', '652829', '博湖县');
INSERT INTO `sys_region` VALUES (3142, '773EB468-6B5B-4601-9D89-6CC6DC526B1C', '652900', '阿克苏地区');
INSERT INTO `sys_region` VALUES (3143, '4D437238-56E3-4BD1-970F-56D89D53C4CF', '652901', '阿克苏市');
INSERT INTO `sys_region` VALUES (3144, '1DA0F50C-2B06-4861-B877-036E184CDEFE', '652902', '库车市');
INSERT INTO `sys_region` VALUES (3145, '13B1BEFE-13AB-464D-9232-F002D1DB8DBC', '652922', '温宿县');
INSERT INTO `sys_region` VALUES (3146, '4200AD41-4AC7-4454-8FB4-5CCBCCC9FCF7', '652924', '沙雅县');
INSERT INTO `sys_region` VALUES (3147, 'B3C71368-D9E3-444B-8FAA-581122EA946E', '652925', '新和县');
INSERT INTO `sys_region` VALUES (3148, 'D38CB0F2-5DD5-4354-BA3A-B4D0AF0305B6', '652926', '拜城县');
INSERT INTO `sys_region` VALUES (3149, '39D87AFE-C26F-486E-8399-57A79B68F8EE', '652927', '乌什县');
INSERT INTO `sys_region` VALUES (3150, 'BEAC9001-799C-49E3-8E45-FBD07E8764C4', '652928', '阿瓦提县');
INSERT INTO `sys_region` VALUES (3151, '7C8B6BCF-A12E-4F71-9C57-42BF8B1A7F9D', '652929', '柯坪县');
INSERT INTO `sys_region` VALUES (3152, '2DAE2F08-CBD7-453C-AEE9-DF0D8805F4D2', '653000', '克孜勒苏柯尔克孜自治州');
INSERT INTO `sys_region` VALUES (3153, '5BF659D3-97A1-463B-93C3-0651AA0738DF', '653001', '阿图什市');
INSERT INTO `sys_region` VALUES (3154, '5DDB8DE1-1B19-49D1-A9F8-8C36C1782A3A', '653022', '阿克陶县');
INSERT INTO `sys_region` VALUES (3155, 'B8FAC3F7-C48C-4A2A-BFC1-4AA8421E53C1', '653023', '阿合奇县');
INSERT INTO `sys_region` VALUES (3156, '22262E7B-D18A-433A-8340-B78E262D79D1', '653024', '乌恰县');
INSERT INTO `sys_region` VALUES (3157, '27367F5F-0CF4-4906-A713-54F57AF4E368', '653100', '喀什地区');
INSERT INTO `sys_region` VALUES (3158, '61A0ECAA-C7C9-4273-B42D-7F38A8F779CE', '653101', '喀什市');
INSERT INTO `sys_region` VALUES (3159, 'BFE6BA65-38AE-4AE7-836F-BDB0B356D5E6', '653121', '疏附县');
INSERT INTO `sys_region` VALUES (3160, '6FB8AA4F-3959-4832-81FB-AF0D00BFF8B7', '653122', '疏勒县');
INSERT INTO `sys_region` VALUES (3161, 'B9AB5F5E-85D7-4737-9CB9-684865A1875C', '653123', '英吉沙县');
INSERT INTO `sys_region` VALUES (3162, 'A61CA0AA-B075-4980-BA4F-A52E9833057C', '653124', '泽普县');
INSERT INTO `sys_region` VALUES (3163, '407470CE-F758-4E1D-8CA3-F33A9E1FE3E7', '653125', '莎车县');
INSERT INTO `sys_region` VALUES (3164, '27063EBD-7AC0-47F0-B156-03A2C5F20288', '653126', '叶城县');
INSERT INTO `sys_region` VALUES (3165, 'B0122146-EFAD-4BA1-A0EC-9AFCF597ED6E', '653127', '麦盖提县');
INSERT INTO `sys_region` VALUES (3166, 'AD2D8F4A-C87F-4A7F-8A59-A5DFB8DC6684', '653128', '岳普湖县');
INSERT INTO `sys_region` VALUES (3167, 'FB170FFF-9154-435E-A634-0D22BBFB4F14', '653129', '伽师县');
INSERT INTO `sys_region` VALUES (3168, '9DC58F78-2788-4699-978C-EDFCE8685D5E', '653130', '巴楚县');
INSERT INTO `sys_region` VALUES (3169, '28F48ECD-DF7C-4B63-BB23-69F533CD2F89', '653131', '塔什库尔干塔吉克自治县');
INSERT INTO `sys_region` VALUES (3170, '30431C06-0A23-4A59-809C-17FE9BF6F341', '653200', '和田地区');
INSERT INTO `sys_region` VALUES (3171, 'FF793277-5BA5-4023-8C96-55DC80F0577D', '653201', '和田市');
INSERT INTO `sys_region` VALUES (3172, '890A4AA7-0B7F-462F-9620-DBD393D96109', '653221', '和田县');
INSERT INTO `sys_region` VALUES (3173, 'E180A85E-0E12-43F5-AFF9-EDCE5C820BF4', '653222', '墨玉县');
INSERT INTO `sys_region` VALUES (3174, '24E48C7F-6559-47C2-B44F-3E00D54BE6C6', '653223', '皮山县');
INSERT INTO `sys_region` VALUES (3175, '08F6AD06-939B-40BB-904B-00E327B74E69', '653224', '洛浦县');
INSERT INTO `sys_region` VALUES (3176, 'D7B812BC-1B4F-41D0-8AD8-4A47DA37481B', '653225', '策勒县');
INSERT INTO `sys_region` VALUES (3177, '8B80821C-CA89-42F3-9A2C-5F4F6D405A7C', '653226', '于田县');
INSERT INTO `sys_region` VALUES (3178, '6DA60DF9-55A8-409E-AE19-2A82BCD1675D', '653227', '民丰县');
INSERT INTO `sys_region` VALUES (3179, 'D3BE65FC-1240-44F9-9527-A8339F1AEBC4', '654000', '伊犁哈萨克自治州');
INSERT INTO `sys_region` VALUES (3180, '962729CC-735C-4241-9207-9169BF8AEA20', '654002', '伊宁市');
INSERT INTO `sys_region` VALUES (3181, '7052BA35-4E3D-4233-91CB-EAB134497085', '654003', '奎屯市');
INSERT INTO `sys_region` VALUES (3182, '9AC70FD8-19A4-4469-AA9B-EA2D7F8FDEBD', '654004', '霍尔果斯市');
INSERT INTO `sys_region` VALUES (3183, '95E5DDF9-5170-4081-89A1-7F3BFA0ECA87', '654021', '伊宁县');
INSERT INTO `sys_region` VALUES (3184, '4480188F-5D70-40CD-B888-D08CC92078E9', '654022', '察布查尔锡伯自治县');
INSERT INTO `sys_region` VALUES (3185, '3B0DF085-5944-4E7E-A525-DF5495C832FC', '654023', '霍城县');
INSERT INTO `sys_region` VALUES (3186, '3C97B38B-0759-4AA3-965C-88A58272E272', '654024', '巩留县');
INSERT INTO `sys_region` VALUES (3187, 'C2628FB3-2E98-4AC6-939C-5C62D276DDA3', '654025', '新源县');
INSERT INTO `sys_region` VALUES (3188, 'B57BD7B3-E037-43DD-AD51-ED4F1B5DE731', '654026', '昭苏县');
INSERT INTO `sys_region` VALUES (3189, 'B5AE4DB5-9C61-428D-92AF-FCE38A84AFDD', '654027', '特克斯县');
INSERT INTO `sys_region` VALUES (3190, '504BCCFF-1EA7-426A-BDFA-568C7CA67E0C', '654028', '尼勒克县');
INSERT INTO `sys_region` VALUES (3191, '8200375E-8713-4411-91EB-A84D150B0C5A', '654200', '塔城地区');
INSERT INTO `sys_region` VALUES (3192, 'F2EB03C4-5B4E-4ED0-8BC7-078E35CD651D', '654201', '塔城市');
INSERT INTO `sys_region` VALUES (3193, '86864C91-2802-48B4-AAE8-4D200BFF8D06', '654202', '乌苏市');
INSERT INTO `sys_region` VALUES (3194, '45402701-3520-4F19-9EB8-9B09338A09BB', '654221', '额敏县');
INSERT INTO `sys_region` VALUES (3195, '4C819AB1-A68A-4BE0-BDEC-5AA862A0F14B', '654223', '沙湾县');
INSERT INTO `sys_region` VALUES (3196, '7EF20947-3AD5-4F3D-9988-1BC4DF2A3F4F', '654224', '托里县');
INSERT INTO `sys_region` VALUES (3197, 'EDFC96F8-882F-4A46-932B-E68D6BB78032', '654225', '裕民县');
INSERT INTO `sys_region` VALUES (3198, 'D12C5051-9418-4794-A0A2-148BE108C654', '654226', '和布克赛尔蒙古自治县');
INSERT INTO `sys_region` VALUES (3199, '443D2D76-9677-4A55-9111-E2DA8A2B7895', '654300', '阿勒泰地区');
INSERT INTO `sys_region` VALUES (3200, 'CC1CE671-CB03-4E27-AEE1-4CFCC6C14EC3', '654301', '阿勒泰市');
INSERT INTO `sys_region` VALUES (3201, 'D07BA644-EB44-48AC-8AD4-B6C22537E0BF', '654321', '布尔津县');
INSERT INTO `sys_region` VALUES (3202, '3D6C7E52-A947-4DC1-B9AC-E04C39537A33', '654322', '富蕴县');
INSERT INTO `sys_region` VALUES (3203, '3DA9646B-74D1-4B3B-9A75-9C4045FB1AD3', '654323', '福海县');
INSERT INTO `sys_region` VALUES (3204, '8006D182-08E8-42CB-9859-0A11F146198A', '654324', '哈巴河县');
INSERT INTO `sys_region` VALUES (3205, '04D44511-B7EC-4686-85D2-488714C2A664', '654325', '青河县');
INSERT INTO `sys_region` VALUES (3206, '5CA80A60-3363-40EF-BBD0-B72D083BEC5B', '654326', '吉木乃县');
INSERT INTO `sys_region` VALUES (3207, '86415A64-6AF0-4327-A4EE-8375BF98FB57', '659001', '石河子市');
INSERT INTO `sys_region` VALUES (3208, '3CEA1B0D-07E1-4675-BDB0-95350BFA0754', '659002', '阿拉尔市');
INSERT INTO `sys_region` VALUES (3209, '804FCEA9-5152-4D2D-BB28-2D7FFAA7A62A', '659003', '图木舒克市');
INSERT INTO `sys_region` VALUES (3210, 'F19AF4D6-D4A2-4042-84F2-BB53A7CBEED4', '659004', '五家渠市');
INSERT INTO `sys_region` VALUES (3211, '1C653E4A-DFBB-498B-B636-0410E21EF2DE', '659005', '北屯市');
INSERT INTO `sys_region` VALUES (3212, '9045BFDE-2394-4649-908E-A61BDF5B37C3', '659006', '铁门关市');
INSERT INTO `sys_region` VALUES (3213, 'C2D7A9F1-0055-4168-9EB0-2972D814CC45', '659007', '双河市');
INSERT INTO `sys_region` VALUES (3214, '9749FECA-9148-403C-A74F-36C74D3CB9DD', '659008', '可克达拉市');
INSERT INTO `sys_region` VALUES (3215, 'B84E1321-4733-4C82-AB72-BA5D4D37A759', '659009', '昆玉市');
INSERT INTO `sys_region` VALUES (3216, '04FC7C74-55F8-4084-A5BC-39337E21D024', '659010', '胡杨河市');
INSERT INTO `sys_region` VALUES (3217, '446D86F1-6379-4A31-862D-998272A3882D', '810000', '香港特别行政区');
INSERT INTO `sys_region` VALUES (3218, '26BC0618-B702-47CF-B4C2-454BEB042382', '810101', '中西区');
INSERT INTO `sys_region` VALUES (3219, '34F0C4F0-46C3-4CA2-A1FC-D803F6169D11', '810102', '湾仔区');
INSERT INTO `sys_region` VALUES (3220, '58AC3669-158A-475C-8939-725087F4481D', '810103', '东区');
INSERT INTO `sys_region` VALUES (3221, '1FD255B8-77DA-4FC2-81F5-AB57F946296D', '810104', '南区');
INSERT INTO `sys_region` VALUES (3222, 'CF69D1C1-4ACE-4BD5-B2BA-286B05E67FEB', '810105', '油尖旺区');
INSERT INTO `sys_region` VALUES (3223, 'C8A0D4BE-1DA9-40D2-B89E-084FF48121E9', '810106', '深水埗区');
INSERT INTO `sys_region` VALUES (3224, '9FB913C8-F1D9-4B73-A71B-E7E17465E4D0', '810107', '九龙城区');
INSERT INTO `sys_region` VALUES (3225, '83D1A259-5128-49F7-957F-960AF647B0F4', '810108', '黄大仙区');
INSERT INTO `sys_region` VALUES (3226, '86577E4D-B8CC-45C6-AB7C-5121356DE697', '810109', '观塘区');
INSERT INTO `sys_region` VALUES (3227, 'FADDEE5B-110E-4CF3-8CF3-B965893C9E8F', '810110', '北区');
INSERT INTO `sys_region` VALUES (3228, '8F09371E-8415-439B-98A0-C421201F156F', '810111', '大埔区');
INSERT INTO `sys_region` VALUES (3229, '7C8DA5E3-3833-4612-AEF2-00B83D6E5C8C', '810112', '沙田区');
INSERT INTO `sys_region` VALUES (3230, 'AC32EA52-1E88-49C7-96E0-581B48B52712', '810113', '西贡区');
INSERT INTO `sys_region` VALUES (3231, '82EAE67A-7F3C-4A91-8F42-9F4299F77DCA', '810114', '荃湾区');
INSERT INTO `sys_region` VALUES (3232, '4D89A564-EF54-4843-B8B5-F214D44A9D44', '810115', '屯门区');
INSERT INTO `sys_region` VALUES (3233, '13F7BB6C-A743-467E-9FA2-9F06D50A0613', '810116', '元朗区');
INSERT INTO `sys_region` VALUES (3234, 'FBAF7DBE-3ED2-4480-9535-2C076845A414', '810117', '葵青区');
INSERT INTO `sys_region` VALUES (3235, 'D2B383B0-9D83-4301-BB24-0D89E38D7C65', '810118', '离岛区');
INSERT INTO `sys_region` VALUES (3236, '8D1E663F-9F9E-47D7-B3C3-7419A3BB68FF', '820000', '澳门特别行政区');
INSERT INTO `sys_region` VALUES (3237, 'D3D79816-088A-4B94-9E3A-399BB4A57FE2', '820101', '花地玛堂区');
INSERT INTO `sys_region` VALUES (3238, 'A1442F9B-CBC6-46BE-BA10-D217379BA5D7', '820102', '圣安多尼堂区');
INSERT INTO `sys_region` VALUES (3239, '520A376B-4AE3-48ED-BB4C-D3ED3510E751', '820103', '大堂区');
INSERT INTO `sys_region` VALUES (3240, '7DFC49A0-70FA-4489-AD8E-2A5AEF78A967', '820104', '望德堂区');
INSERT INTO `sys_region` VALUES (3241, '211FF670-E049-4FA9-8C77-1B15C089A9F6', '820105', '风顺堂区');
INSERT INTO `sys_region` VALUES (3242, '99C09EFC-DE14-40D5-B158-5BE06701B838', '820106', '嘉模堂区');
INSERT INTO `sys_region` VALUES (3243, 'F3191C76-C710-4716-85A4-D1ED7082DF66', '820107', '圣方济各堂区');
INSERT INTO `sys_region` VALUES (3244, '539F2472-4D95-423F-A5C7-68A979FF9E32', '820108', '路氹城');
INSERT INTO `sys_region` VALUES (3245, 'BDEE5192-A2B8-4A84-87B3-780985A34CDB', '820109', '澳门新城');
INSERT INTO `sys_region` VALUES (3246, 'D2EA25AF-3EB4-48DD-874F-407AD5C963A7', '830000', '台湾省');
INSERT INTO `sys_region` VALUES (3247, '1D0AE3C9-5C6E-41EB-96EA-008C3216A4FA', '830100', '台北市');
INSERT INTO `sys_region` VALUES (3248, '459EACE9-9B69-4876-BC36-6B2FB4DA4B39', '830101', '中正区');
INSERT INTO `sys_region` VALUES (3249, '7476776C-3781-4ACE-B76D-152010D4F743', '830102', '大同区');
INSERT INTO `sys_region` VALUES (3250, '4A403BBE-05FB-4D25-B337-E17B82BC5199', '830103', '中山区');
INSERT INTO `sys_region` VALUES (3251, 'AC1040E3-6CCB-45C9-9D8D-D8B6B98D67D3', '830104', '万华区');
INSERT INTO `sys_region` VALUES (3252, '61ADEFE4-CEC3-43F4-8391-FBCB05E831DB', '830105', '信义区');
INSERT INTO `sys_region` VALUES (3253, 'D1A265DE-2DD0-4B39-90F7-3765A5B73D45', '830106', '松山区');
INSERT INTO `sys_region` VALUES (3254, 'DE59021A-FBD2-45A2-B9A3-77E6151B0DCA', '830107', '大安区');
INSERT INTO `sys_region` VALUES (3255, '176D8A8C-5EF8-4E97-BCF5-447B43814A78', '830108', '南港区');
INSERT INTO `sys_region` VALUES (3256, '498CEE05-FA40-44C0-ABDA-39E497204978', '830109', '北投区');
INSERT INTO `sys_region` VALUES (3257, 'B4FF05A1-DB81-48AE-A485-E8C9BDCAC654', '830110', '内湖区');
INSERT INTO `sys_region` VALUES (3258, 'B09BCE29-5E54-460F-8228-986C1C298691', '830111', '士林区');
INSERT INTO `sys_region` VALUES (3259, '7B387FA9-E593-4024-8A5E-558B111060F9', '830112', '文山区');
INSERT INTO `sys_region` VALUES (3260, '9CF9E557-B958-400B-B350-6B1453E90443', '830200', '新北市');
INSERT INTO `sys_region` VALUES (3261, '6B73AC0A-241B-4C7D-8074-3EC2D5B4B2DE', '830201', '板桥区');
INSERT INTO `sys_region` VALUES (3262, '3AE67868-F636-401A-8AA5-810D800602B0', '830202', '土城区');
INSERT INTO `sys_region` VALUES (3263, '8022CEB0-5F9D-4DA0-ABCC-E9B2D2C036C2', '830203', '新庄区');
INSERT INTO `sys_region` VALUES (3264, 'B0CAE925-EF70-431F-9CDC-E49BFBD4F3A7', '830204', '新店区');
INSERT INTO `sys_region` VALUES (3265, '257D9F8F-DEBB-43C1-8341-641E337EEBFF', '830205', '深坑区');
INSERT INTO `sys_region` VALUES (3266, '7DB8A7E4-FB64-46BB-8DB5-4EE6D6CF7D37', '830206', '石碇区');
INSERT INTO `sys_region` VALUES (3267, 'C9546B7B-6C88-4E76-B2FD-52B8EF6A9C64', '830207', '坪林区');
INSERT INTO `sys_region` VALUES (3268, '5384577D-49D1-4AC8-A6C5-904FF3546D24', '830208', '乌来区');
INSERT INTO `sys_region` VALUES (3269, '79EE88C2-1B1F-459C-85E0-4D267BFC6AEA', '830209', '五股区');
INSERT INTO `sys_region` VALUES (3270, '005A1796-4A89-4C98-844D-F072E1B393BB', '830210', '八里区');
INSERT INTO `sys_region` VALUES (3271, '2AE14981-B991-427E-A718-C407AB3CF363', '830211', '林口区');
INSERT INTO `sys_region` VALUES (3272, '920B1E23-1119-452F-B435-AD082232F347', '830212', '淡水区');
INSERT INTO `sys_region` VALUES (3273, 'ECD27929-1C80-47D2-96D4-368780EC1543', '830213', '中和区');
INSERT INTO `sys_region` VALUES (3274, '95CABE85-5D7F-48A7-858D-8B1D6E988BA6', '830214', '永和区');
INSERT INTO `sys_region` VALUES (3275, '105506BD-1150-483B-AF30-EEA31298D77C', '830215', '三重区');
INSERT INTO `sys_region` VALUES (3276, 'F6BB44B7-1F55-4067-96F2-A5408998CD15', '830216', '芦洲区');
INSERT INTO `sys_region` VALUES (3277, '027E0B37-E3EC-4E74-9360-B7E3C2F113E8', '830217', '泰山区');
INSERT INTO `sys_region` VALUES (3278, '00859465-EE70-4E67-88A1-9E2493FD1CA1', '830218', '树林区');
INSERT INTO `sys_region` VALUES (3279, 'E3285215-58BF-4502-B33E-EA0AA21363C1', '830219', '莺歌区');
INSERT INTO `sys_region` VALUES (3280, '858DCBAD-EC24-4696-A45A-880581F95075', '830220', '三峡区');
INSERT INTO `sys_region` VALUES (3281, '7FDA14F0-AB33-4E64-B82E-3621D79B4A1F', '830221', '汐止区');
INSERT INTO `sys_region` VALUES (3282, '054C7F67-94C4-4DAE-A901-E5BC4371FAFA', '830222', '金山区');
INSERT INTO `sys_region` VALUES (3283, 'E92A56BB-9079-462E-BE21-E6C5DE9CD018', '830223', '万里区');
INSERT INTO `sys_region` VALUES (3284, 'BB716274-6C95-4EFC-B701-552AD7222B2C', '830224', '三芝区');
INSERT INTO `sys_region` VALUES (3285, 'FE35D5E1-3765-460E-99A6-90D5C93BE77A', '830225', '石门区');
INSERT INTO `sys_region` VALUES (3286, '0724F99A-397C-4DDA-BCC4-19E60A624CCD', '830226', '瑞芳区');
INSERT INTO `sys_region` VALUES (3287, '74D30CB8-FD99-4C09-B7D1-1E6C704F68DC', '830227', '贡寮区');
INSERT INTO `sys_region` VALUES (3288, 'C1AACB2D-2307-48A7-AD95-DAF36AB202F4', '830228', '双溪区');
INSERT INTO `sys_region` VALUES (3289, '10BCA81B-AA8C-409B-B12B-79B03BC7C0F9', '830229', '平溪区');
INSERT INTO `sys_region` VALUES (3290, 'AB604EC5-FFF9-4369-A1E2-C7D395A2DCD8', '830300', '桃园市');
INSERT INTO `sys_region` VALUES (3291, 'FF7F6A87-DACC-43B0-84EE-05635DD54487', '830301', '桃园区');
INSERT INTO `sys_region` VALUES (3292, '16898416-6B5E-4E12-BC30-37E1CF613205', '830302', '中坜区');
INSERT INTO `sys_region` VALUES (3293, '4C19D098-1D77-44A3-B9D2-15153C430E1A', '830303', '平镇区');
INSERT INTO `sys_region` VALUES (3294, '57462206-BDCB-4C44-B9A0-46C92F1DE3C4', '830304', '八德区');
INSERT INTO `sys_region` VALUES (3295, '7106103D-A9CC-4AB2-ABA2-1C2E9AE74AAC', '830305', '杨梅区');
INSERT INTO `sys_region` VALUES (3296, '5930ABA7-20E4-4889-8E32-3585A8B363CE', '830306', '芦竹区');
INSERT INTO `sys_region` VALUES (3297, 'B5056AFD-4817-47D0-BA68-B2217927E03F', '830307', '大溪区');
INSERT INTO `sys_region` VALUES (3298, '85CE92D0-3717-4210-8915-146866100AEF', '830308', '龙潭区');
INSERT INTO `sys_region` VALUES (3299, '70750D56-CF2A-4F9C-B1BD-9F7347A017EF', '830309', '龟山区');
INSERT INTO `sys_region` VALUES (3300, '7912CBC3-F1D6-46A4-9530-C751090EB1FE', '830310', '大园区');
INSERT INTO `sys_region` VALUES (3301, '9C531A4D-667E-491D-BAC6-2984373B0953', '830311', '观音区');
INSERT INTO `sys_region` VALUES (3302, '7552B475-A249-466B-96DE-8E9CD1963A78', '830312', '新屋区');
INSERT INTO `sys_region` VALUES (3303, 'EEDA4930-19B2-4F3D-B76F-1328E36BDA70', '830313', '复兴区');
INSERT INTO `sys_region` VALUES (3304, 'F797B092-2C4A-45D5-8A35-C74195E51660', '830400', '台中市');
INSERT INTO `sys_region` VALUES (3305, '3FD40B5F-79AF-4B85-ADDB-B258A73AC0D7', '830401', '中区');
INSERT INTO `sys_region` VALUES (3306, '53608E7F-4AB3-4FA5-A083-E798ACF90A4F', '830402', '东区');
INSERT INTO `sys_region` VALUES (3307, 'AE62E57F-8C34-46E5-988D-F5D9A6577B06', '830403', '西区');
INSERT INTO `sys_region` VALUES (3308, 'F611949B-7038-415F-9D56-F12A5DF602C4', '830404', '南区');
INSERT INTO `sys_region` VALUES (3309, '89480991-597A-4A37-B596-368FEBD8EBCA', '830405', '北区');
INSERT INTO `sys_region` VALUES (3310, 'C2776130-2D0C-4974-9C1E-E4A6384EF3E7', '830406', '西屯区');
INSERT INTO `sys_region` VALUES (3311, 'EAACAC50-B8E0-4429-912C-093495B84872', '830407', '南屯区');
INSERT INTO `sys_region` VALUES (3312, '80DD5547-7CF6-4091-9172-F20FEE14DB3B', '830408', '北屯区');
INSERT INTO `sys_region` VALUES (3313, 'E9991D82-35C2-434E-B315-80C7041ADC9A', '830409', '丰原区');
INSERT INTO `sys_region` VALUES (3314, 'C1E46948-A925-46A2-AF14-FB77E7AC503A', '830410', '大里区');
INSERT INTO `sys_region` VALUES (3315, '551F1855-4E83-489A-85EE-B4DF7BD89150', '830411', '太平区');
INSERT INTO `sys_region` VALUES (3316, '59086AB0-C636-4054-879E-1FD49C06BF9F', '830412', '东势区');
INSERT INTO `sys_region` VALUES (3317, 'AFD8D504-3CDD-4C6D-940D-D318BBDEF272', '830413', '大甲区');
INSERT INTO `sys_region` VALUES (3318, '48FD25B5-D555-404E-8287-1ACCDCF880BD', '830414', '清水区');
INSERT INTO `sys_region` VALUES (3319, '03AAFE80-4416-4ACE-A205-B81D8DD8ED5B', '830415', '沙鹿区');
INSERT INTO `sys_region` VALUES (3320, '79316AD0-DAF5-4FF5-900E-497F27308A16', '830416', '梧栖区');
INSERT INTO `sys_region` VALUES (3321, '1C2DEFE5-5EED-4C6D-BD05-C596035E04D0', '830417', '后里区');
INSERT INTO `sys_region` VALUES (3322, '283FC49C-4E25-484A-8589-38E821CFCD0A', '830418', '神冈区');
INSERT INTO `sys_region` VALUES (3323, '91079D81-C353-4130-A9F3-48CE08E046E2', '830419', '潭子区');
INSERT INTO `sys_region` VALUES (3324, 'F9E54981-6205-43FE-93AF-4DCE39567C3E', '830420', '大雅区');
INSERT INTO `sys_region` VALUES (3325, 'DDD213F6-B947-4726-8E37-58230F742F46', '830421', '新小区');
INSERT INTO `sys_region` VALUES (3326, 'EA1BDD65-1B50-428C-A91B-072982ABAB10', '830422', '石冈区');
INSERT INTO `sys_region` VALUES (3327, '2F6FF48D-EA37-451A-AC53-9579E17D6E09', '830423', '外埔区');
INSERT INTO `sys_region` VALUES (3328, '227BBFDB-283D-4DDB-847D-C2D82DFF086E', '830424', '大安区');
INSERT INTO `sys_region` VALUES (3329, '702A2CBF-F16B-41F4-99CD-1129AEA51737', '830425', '乌日区');
INSERT INTO `sys_region` VALUES (3330, '2322914D-152C-4476-BBFD-23517FCF958E', '830426', '大肚区');
INSERT INTO `sys_region` VALUES (3331, '85F40B7F-B88D-4DCB-93B5-15A04C0B18DB', '830427', '龙井区');
INSERT INTO `sys_region` VALUES (3332, 'D0DEDAFC-456D-4AB6-8EBA-954035AF6776', '830428', '雾峰区');
INSERT INTO `sys_region` VALUES (3333, '6D084A86-66BC-40C4-8C6A-4C14F451219A', '830429', '和平区');
INSERT INTO `sys_region` VALUES (3334, 'C1C10402-6AE2-42FF-B36F-5323DAD7F93F', '830500', '台南市');
INSERT INTO `sys_region` VALUES (3335, '24B59B5D-896D-4660-9AF3-28A117925BB9', '830501', '中西区');
INSERT INTO `sys_region` VALUES (3336, '9D8E9109-06EE-42B7-9FD7-BA3C90AC7D1E', '830502', '东区');
INSERT INTO `sys_region` VALUES (3337, 'D8C5FAAE-189A-4EE5-AD74-0A676D935474', '830503', '南区');
INSERT INTO `sys_region` VALUES (3338, '98319463-A30C-488D-93E7-B35639C78270', '830504', '北区');
INSERT INTO `sys_region` VALUES (3339, '6E70587F-02DC-489A-9DC1-ABD2586CF3C5', '830505', '安平区');
INSERT INTO `sys_region` VALUES (3340, 'D634A8C4-29EE-46E3-9FD6-AEC77A6EB015', '830506', '安南区');
INSERT INTO `sys_region` VALUES (3341, 'D1577DBD-72F9-4F25-8269-88F7C0940DA7', '830507', '永康区');
INSERT INTO `sys_region` VALUES (3342, 'DFDE06A3-F73C-48A4-8CEB-5C32A5E575A1', '830508', '归仁区');
INSERT INTO `sys_region` VALUES (3343, 'DF658C1C-7A10-4B56-9F31-B277FC13E8C1', '830509', '新化区');
INSERT INTO `sys_region` VALUES (3344, 'E78A1889-9DCB-49EF-B804-AB786369ECE7', '830510', '左镇区');
INSERT INTO `sys_region` VALUES (3345, '8C4E1CBB-C253-42DC-BC47-9F75E5CCB2FF', '830511', '玉井区');
INSERT INTO `sys_region` VALUES (3346, 'D3AF1F01-9758-4CC0-879D-29B5A98661CF', '830512', '楠西区');
INSERT INTO `sys_region` VALUES (3347, 'D879EC08-6132-4D67-9BCF-F769E09F8BE5', '830513', '南化区');
INSERT INTO `sys_region` VALUES (3348, '3E48B740-54B0-4C42-B802-0D3C71A24EC1', '830514', '仁德区');
INSERT INTO `sys_region` VALUES (3349, '3AE66AEA-EB24-4687-BC17-E7070990D64D', '830515', '关庙区');
INSERT INTO `sys_region` VALUES (3350, '8C0A67E4-A3E2-4A81-98B8-89D77063EE1B', '830516', '龙崎区');
INSERT INTO `sys_region` VALUES (3351, 'A53273A4-EEF9-4D3F-AF00-FDA45E3ADF58', '830517', '官田区');
INSERT INTO `sys_region` VALUES (3352, 'AA7143D8-0948-4BD8-92A9-2937CA058D73', '830518', '麻豆区');
INSERT INTO `sys_region` VALUES (3353, '193068AD-6D31-4F33-9388-5E83814C71FB', '830519', '佳里区');
INSERT INTO `sys_region` VALUES (3354, '19F93E78-FA9F-47EA-ADE2-3C3249925B1B', '830520', '西港区');
INSERT INTO `sys_region` VALUES (3355, '06282108-061A-4645-B133-EBE728C45F41', '830521', '七股区');
INSERT INTO `sys_region` VALUES (3356, 'D3C337C9-08E9-46B0-9EA4-B725411F7DAD', '830522', '将军区');
INSERT INTO `sys_region` VALUES (3357, '4F617DD0-6338-43B3-9BE3-F4A1AD42E0B5', '830523', '学甲区');
INSERT INTO `sys_region` VALUES (3358, '0FAF7DD5-4875-4464-BA0B-F3A9582F7C9E', '830524', '北门区');
INSERT INTO `sys_region` VALUES (3359, 'A1F53428-59B8-4302-BFDF-50B9F698A969', '830525', '新营区');
INSERT INTO `sys_region` VALUES (3360, '76A33A79-D6AC-4836-ABA7-E1FCED8CA981', '830526', '后壁区');
INSERT INTO `sys_region` VALUES (3361, '40296FD1-A94A-4F7B-9C1C-78C8A8BC25AD', '830527', '白河区');
INSERT INTO `sys_region` VALUES (3362, '132572FD-28E9-4C15-BD90-F7836C14EC90', '830528', '东山区');
INSERT INTO `sys_region` VALUES (3363, '2FBF904B-65CB-43E7-9FCF-7A3AFF40757A', '830529', '六甲区');
INSERT INTO `sys_region` VALUES (3364, 'D6C5CE3D-2AFC-42E5-AA2E-542400129ABC', '830530', '下营区');
INSERT INTO `sys_region` VALUES (3365, '0756435E-E3C4-448A-A98E-D459052169A7', '830531', '柳营区');
INSERT INTO `sys_region` VALUES (3366, 'F0F2337E-C986-481D-A97D-9E19A21F7852', '830532', '盐水区');
INSERT INTO `sys_region` VALUES (3367, 'CE154FB2-952F-4C01-A1D7-D5E32F015086', '830533', '善化区');
INSERT INTO `sys_region` VALUES (3368, '8B139063-B4E6-4000-94D2-EDEE02C65E3E', '830534', '大内区');
INSERT INTO `sys_region` VALUES (3369, '983D710F-8536-46FE-BCC8-44A30A50740C', '830535', '山上区');
INSERT INTO `sys_region` VALUES (3370, '5B313F56-3D71-4FA3-8F54-AF617B1AC135', '830536', '新市区');
INSERT INTO `sys_region` VALUES (3371, 'A59DEDB0-6C04-4BC9-A070-D740D2B7525F', '830537', '安定区');
INSERT INTO `sys_region` VALUES (3372, 'B46C3036-6823-4053-97C0-F5AFE5C54916', '830600', '高雄市');
INSERT INTO `sys_region` VALUES (3373, '0393B05D-AE8C-435E-A299-BE010013D4BC', '830601', '楠梓区');
INSERT INTO `sys_region` VALUES (3374, '137D75CB-5072-4AC9-9C19-08E9D687E23F', '830602', '左营区');
INSERT INTO `sys_region` VALUES (3375, '0576599C-EE4B-453E-ADE0-350386893F3B', '830603', '鼓山区');
INSERT INTO `sys_region` VALUES (3376, 'D9E8E36B-B525-4365-97EA-8A3AC8943414', '830604', '三民区');
INSERT INTO `sys_region` VALUES (3377, 'FF6DAFBB-5626-4E9B-ADE2-9E0BA95FDBA0', '830605', '盐埕区');
INSERT INTO `sys_region` VALUES (3378, 'A4E129FA-2C22-46D3-8D4D-ED82DC2037F0', '830606', '前金区');
INSERT INTO `sys_region` VALUES (3379, '1A4B6086-81AE-4DDB-92B9-5FF1E40EF7EC', '830607', '新兴区');
INSERT INTO `sys_region` VALUES (3380, '31B5D6C4-0661-48AE-B03D-2BE2EDB4E1A7', '830608', '苓雅区');
INSERT INTO `sys_region` VALUES (3381, '34E906C7-2FAA-4E8A-AA90-85923C462803', '830609', '前镇区');
INSERT INTO `sys_region` VALUES (3382, '4C170A5C-5829-43D2-B88C-C0F49FAD3D16', '830610', '旗津区');
INSERT INTO `sys_region` VALUES (3383, 'C5E7870B-B5FD-4481-80A7-3C45F52C3427', '830611', '小港区');
INSERT INTO `sys_region` VALUES (3384, '6939AAC3-27D7-4295-B6F6-0EB8BCE00909', '830612', '凤山区');
INSERT INTO `sys_region` VALUES (3385, '2D809689-E0A0-4F27-8C1C-C71295F4AE69', '830613', '大寮区');
INSERT INTO `sys_region` VALUES (3386, '44CE4494-22A6-4438-BB7D-6401036F5413', '830614', '鸟松区');
INSERT INTO `sys_region` VALUES (3387, '77552BDE-CECA-498E-96D5-2D1582520476', '830615', '林园区');
INSERT INTO `sys_region` VALUES (3388, '5DDF8E7F-6C4B-4276-A264-D994F505B3C9', '830616', '仁武区');
INSERT INTO `sys_region` VALUES (3389, 'E361436A-A4AB-4BFD-BCA1-107CA2494BB2', '830617', '大树区');
INSERT INTO `sys_region` VALUES (3390, 'FBAA3538-7AAF-4F92-AF6A-2E369C580C30', '830618', '大社区');
INSERT INTO `sys_region` VALUES (3391, '4143B273-E9C3-4531-A6AC-AFC8C549B3A1', '830619', '冈山区');
INSERT INTO `sys_region` VALUES (3392, '0CA144DB-7BA1-45F5-9210-10EE1CF63DF0', '830620', '路竹区');
INSERT INTO `sys_region` VALUES (3393, '113E8BD5-C0E5-4FB7-BD1E-4C059F7F46E4', '830621', '桥头区');
INSERT INTO `sys_region` VALUES (3394, '6227BB61-C181-4344-93CD-6AC892234BA1', '830622', '梓官区');
INSERT INTO `sys_region` VALUES (3395, 'C0A4EB6B-4B65-4848-9312-079E2E460FF3', '830623', '弥陀区');
INSERT INTO `sys_region` VALUES (3396, 'BE2BA193-9F79-4242-9DD1-F52807084197', '830624', '永安区');
INSERT INTO `sys_region` VALUES (3397, '503AC5BE-526A-4A92-B7CE-0F8FA1182415', '830625', '燕巢区');
INSERT INTO `sys_region` VALUES (3398, '7D8AA548-DA3D-49B6-B872-DC3E4E8F246B', '830626', '阿莲区');
INSERT INTO `sys_region` VALUES (3399, 'ED1F7179-87C1-41A8-B540-0E1EFF7535F5', '830627', '茄萣区');
INSERT INTO `sys_region` VALUES (3400, 'E1EEB468-955C-4834-8ED2-E8CC3C418B1D', '830628', '湖内区');
INSERT INTO `sys_region` VALUES (3401, '69ECF230-405D-4A92-B0DB-454F031318D4', '830629', '旗山区');
INSERT INTO `sys_region` VALUES (3402, 'C5C52E80-C95E-4B98-AED4-CBCA9C669B49', '830630', '美浓区');
INSERT INTO `sys_region` VALUES (3403, 'BE24623F-D7EF-48E9-A329-A34B419DD221', '830631', '内门区');
INSERT INTO `sys_region` VALUES (3404, '947C5CB6-176E-4471-B138-7590C9F77AAB', '830632', '杉林区');
INSERT INTO `sys_region` VALUES (3405, 'EBA20237-F856-4E86-ADFE-A7A53C95FDDC', '830633', '甲仙区');
INSERT INTO `sys_region` VALUES (3406, 'F190DA72-E49A-43E8-AE23-8E311F9ACEAF', '830634', '六龟区');
INSERT INTO `sys_region` VALUES (3407, 'B252F301-2D9A-42E2-BDE0-20DDD928CC76', '830635', '茂林区');
INSERT INTO `sys_region` VALUES (3408, 'C7F9DB47-9EEB-46E4-908A-EDA8E49A4738', '830636', '桃源区');
INSERT INTO `sys_region` VALUES (3409, '15F8C306-C9D7-4F5D-9B7F-489665932A25', '830637', '那玛夏区');
INSERT INTO `sys_region` VALUES (3410, '76156D54-40E9-46F6-8678-712EEE8211DF', '830700', '基隆市');
INSERT INTO `sys_region` VALUES (3411, '0BB8F32C-91DA-4741-99A2-E1EB9304A1DC', '830701', '中正区');
INSERT INTO `sys_region` VALUES (3412, 'DD3A840A-8926-431F-9E69-8F490D8C9E72', '830702', '七堵区');
INSERT INTO `sys_region` VALUES (3413, '78EAA614-0275-49E6-AAE3-824911AC11B5', '830703', '暖暖区');
INSERT INTO `sys_region` VALUES (3414, '590C6980-308A-43EE-A95E-B8A434E3E5DC', '830704', '仁爱区');
INSERT INTO `sys_region` VALUES (3415, '51C8587F-AC4E-412E-ADE7-044D238D65F4', '830705', '中山区');
INSERT INTO `sys_region` VALUES (3416, 'E29227F6-2615-420B-A931-3AF725BAA28B', '830706', '安乐区');
INSERT INTO `sys_region` VALUES (3417, 'F0EF5CED-142A-40F1-A4E0-0B2537C5FD02', '830707', '信义区');
INSERT INTO `sys_region` VALUES (3418, '682F9ADC-D20B-4D1B-9C06-F11802074FF9', '830800', '新竹市');
INSERT INTO `sys_region` VALUES (3419, 'A301480D-0A14-4AB8-AB15-CF96D54A4D22', '830801', '东区');
INSERT INTO `sys_region` VALUES (3420, '1F215A5D-3CFF-4603-AA9E-2FAA093D6AB5', '830802', '北区');
INSERT INTO `sys_region` VALUES (3421, 'AC006619-7E5B-4971-93B9-CA12B7D3A217', '830803', '香山区');
INSERT INTO `sys_region` VALUES (3422, 'A118A7A8-1AED-4AB3-88D1-BB4763D1CF82', '830900', '嘉义市');
INSERT INTO `sys_region` VALUES (3423, 'DB2868E6-0DF3-40E7-BEAE-2A3CFEFB2B5D', '830901', '东区');
INSERT INTO `sys_region` VALUES (3424, 'CB4CEE72-40E5-41FD-8026-3B0047497EDA', '830902', '西区');
INSERT INTO `sys_region` VALUES (3425, '0BB00D3A-3A43-40E4-A2D1-8E1014176313', '839001', '宜兰县');
INSERT INTO `sys_region` VALUES (3426, '4F683E0F-6E9C-435D-9DA6-293833AD0140', '839002', '新竹县');
INSERT INTO `sys_region` VALUES (3427, 'E254646F-E232-4D50-B2D6-37DF36ED5A43', '839003', '苗栗县');
INSERT INTO `sys_region` VALUES (3428, '153F2395-8149-431F-B71A-46C09C7E0B56', '839004', '彰化县');
INSERT INTO `sys_region` VALUES (3429, '3F13D4FA-7D6C-4641-8DD6-239CE47EA5F8', '839005', '南投县');
INSERT INTO `sys_region` VALUES (3430, 'FDB5F0CB-2CFC-4F87-B089-48D50389A4F3', '839006', '嘉义县');
INSERT INTO `sys_region` VALUES (3431, '1A3FB12E-A2E6-4AE8-A657-90533E28C985', '839007', '云林县');
INSERT INTO `sys_region` VALUES (3432, '535CFF9F-C5CB-44F3-AC3E-E6434C8FD401', '839008', '屏东县');
INSERT INTO `sys_region` VALUES (3433, 'E8DD075B-3E34-4856-91EB-D398FB29E072', '839009', '台东县');
INSERT INTO `sys_region` VALUES (3434, 'BC10DB57-E4E0-4EC1-AF82-7B772A37E7C0', '839010', '花莲县');
INSERT INTO `sys_region` VALUES (3435, '082A7D27-D9CE-4C4B-923B-64644AC7579B', '839011', '澎湖县');
INSERT INTO `sys_region` VALUES (3436, 'E5974B5F-62E5-4999-9446-728A7C949981', '839012', '金门县');
INSERT INTO `sys_region` VALUES (3437, '6ED8EA95-BF61-429F-A8C7-DCEC2EFDDC23', '839013', '连江县');
COMMIT;

-- ----------------------------
-- Table structure for sys_secret_key
-- ----------------------------
DROP TABLE IF EXISTS `sys_secret_key`;
CREATE TABLE `sys_secret_key` (
  `id` bigint(20) unsigned NOT NULL,
  `uuid` varchar(36) NOT NULL,
  `public_key` text DEFAULT NULL COMMENT '公钥',
  `private_key` text DEFAULT NULL COMMENT '私钥',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid` (`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统密钥交换表';

-- ----------------------------
-- Records of sys_secret_key
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for weibo_postmeta
-- ----------------------------
DROP TABLE IF EXISTS `weibo_postmeta`;
CREATE TABLE `weibo_postmeta` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键，非自增，使用统一发号器',
  `post_id` bigint(20) NOT NULL COMMENT '微博ID',
  `meta_key` varchar(255) DEFAULT NULL COMMENT '信息的键',
  `meta_value` longtext DEFAULT NULL COMMENT '信息的值',
  PRIMARY KEY (`id`),
  KEY `post_id` (`post_id`) USING BTREE,
  KEY `meta_key` (`meta_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微博扩展信息表';

-- ----------------------------
-- Records of weibo_postmeta
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for weibo_posts
-- ----------------------------
DROP TABLE IF EXISTS `weibo_posts`;
CREATE TABLE `weibo_posts` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键，非自增，使用统一发号器',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容',
  `release_time` datetime NOT NULL COMMENT '发布时间',
  `views` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '浏览量',
  `thumbs_up` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点赞量',
  `thumbs_down` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点踩量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微博内容';

-- ----------------------------
-- Records of weibo_posts
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
