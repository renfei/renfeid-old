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
-- Table structure for table `ACT_APP_APPDEF`
--

DROP TABLE IF EXISTS `ACT_APP_APPDEF`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_APP_APPDEF` (
  `ID_` varchar(255) NOT NULL,
  `REV_` int(11) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `KEY_` varchar(255) NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `CATEGORY_` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) DEFAULT NULL,
  `TENANT_ID_` varchar(255) DEFAULT '',
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_IDX_APP_DEF_UNIQ` (`KEY_`,`VERSION_`,`TENANT_ID_`),
  KEY `ACT_IDX_APP_DEF_DPLY` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_APP_DEF_DPLY` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_APP_DEPLOYMENT` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_APP_APPDEF`
--

LOCK TABLES `ACT_APP_APPDEF` WRITE;
/*!40000 ALTER TABLE `ACT_APP_APPDEF` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_APP_APPDEF` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_APP_DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `ACT_APP_DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_APP_DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_APP_DATABASECHANGELOG`
--

LOCK TABLES `ACT_APP_DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `ACT_APP_DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `ACT_APP_DATABASECHANGELOG` VALUES ('1','flowable','org/flowable/app/db/liquibase/flowable-app-db-changelog.xml','2022-09-08 02:36:22',1,'EXECUTED','8:496fc778bdf2ab13f2e1926d0e63e0a2','createTable tableName=ACT_APP_DEPLOYMENT; createTable tableName=ACT_APP_DEPLOYMENT_RESOURCE; addForeignKeyConstraint baseTableName=ACT_APP_DEPLOYMENT_RESOURCE, constraintName=ACT_FK_APP_RSRC_DPL, referencedTableName=ACT_APP_DEPLOYMENT; createIndex...','',NULL,'4.3.5',NULL,NULL,'0683828159'),('2','flowable','org/flowable/app/db/liquibase/flowable-app-db-changelog.xml','2022-09-08 02:36:22',2,'EXECUTED','8:ccea9ebfb6c1f8367ca4dd473fcbb7db','modifyDataType columnName=DEPLOY_TIME_, tableName=ACT_APP_DEPLOYMENT','',NULL,'4.3.5',NULL,NULL,'0683828159'),('3','flowable','org/flowable/app/db/liquibase/flowable-app-db-changelog.xml','2022-09-08 02:36:22',3,'EXECUTED','8:f1f8aff320aade831944ebad24355f3d','createIndex indexName=ACT_IDX_APP_DEF_UNIQ, tableName=ACT_APP_APPDEF','',NULL,'4.3.5',NULL,NULL,'0683828159');
/*!40000 ALTER TABLE `ACT_APP_DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_APP_DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `ACT_APP_DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_APP_DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_APP_DATABASECHANGELOGLOCK`
--

LOCK TABLES `ACT_APP_DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `ACT_APP_DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `ACT_APP_DATABASECHANGELOGLOCK` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `ACT_APP_DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_APP_DEPLOYMENT`
--

DROP TABLE IF EXISTS `ACT_APP_DEPLOYMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_APP_DEPLOYMENT` (
  `ID_` varchar(255) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `CATEGORY_` varchar(255) DEFAULT NULL,
  `KEY_` varchar(255) DEFAULT NULL,
  `DEPLOY_TIME_` datetime(3) DEFAULT NULL,
  `TENANT_ID_` varchar(255) DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_APP_DEPLOYMENT`
--

LOCK TABLES `ACT_APP_DEPLOYMENT` WRITE;
/*!40000 ALTER TABLE `ACT_APP_DEPLOYMENT` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_APP_DEPLOYMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_APP_DEPLOYMENT_RESOURCE`
--

DROP TABLE IF EXISTS `ACT_APP_DEPLOYMENT_RESOURCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_APP_DEPLOYMENT_RESOURCE` (
  `ID_` varchar(255) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) DEFAULT NULL,
  `RESOURCE_BYTES_` longblob DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_APP_RSRC_DPL` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_APP_RSRC_DPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_APP_DEPLOYMENT` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_APP_DEPLOYMENT_RESOURCE`
--

LOCK TABLES `ACT_APP_DEPLOYMENT_RESOURCE` WRITE;
/*!40000 ALTER TABLE `ACT_APP_DEPLOYMENT_RESOURCE` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_APP_DEPLOYMENT_RESOURCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_CMMN_CASEDEF`
--

DROP TABLE IF EXISTS `ACT_CMMN_CASEDEF`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_CMMN_CASEDEF` (
  `ID_` varchar(255) NOT NULL,
  `REV_` int(11) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `KEY_` varchar(255) NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `CATEGORY_` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` bit(1) DEFAULT NULL,
  `TENANT_ID_` varchar(255) DEFAULT '',
  `DGRM_RESOURCE_NAME_` varchar(4000) DEFAULT NULL,
  `HAS_START_FORM_KEY_` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_IDX_CASE_DEF_UNIQ` (`KEY_`,`VERSION_`,`TENANT_ID_`),
  KEY `ACT_IDX_CASE_DEF_DPLY` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_CASE_DEF_DPLY` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_CMMN_DEPLOYMENT` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_CMMN_CASEDEF`
--

LOCK TABLES `ACT_CMMN_CASEDEF` WRITE;
/*!40000 ALTER TABLE `ACT_CMMN_CASEDEF` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_CMMN_CASEDEF` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_CMMN_DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `ACT_CMMN_DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_CMMN_DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_CMMN_DATABASECHANGELOG`
--

LOCK TABLES `ACT_CMMN_DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `ACT_CMMN_DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `ACT_CMMN_DATABASECHANGELOG` VALUES ('1','flowable','org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml','2022-09-08 02:36:22',1,'EXECUTED','8:8b4b922d90b05ff27483abefc9597aa6','createTable tableName=ACT_CMMN_DEPLOYMENT; createTable tableName=ACT_CMMN_DEPLOYMENT_RESOURCE; addForeignKeyConstraint baseTableName=ACT_CMMN_DEPLOYMENT_RESOURCE, constraintName=ACT_FK_CMMN_RSRC_DPL, referencedTableName=ACT_CMMN_DEPLOYMENT; create...','',NULL,'4.3.5',NULL,NULL,'0683828602'),('2','flowable','org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml','2022-09-08 02:36:22',2,'EXECUTED','8:65e39b3d385706bb261cbeffe7533cbe','addColumn tableName=ACT_CMMN_CASEDEF; addColumn tableName=ACT_CMMN_DEPLOYMENT_RESOURCE; addColumn tableName=ACT_CMMN_RU_CASE_INST; addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST','',NULL,'4.3.5',NULL,NULL,'0683828602'),('3','flowable','org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml','2022-09-08 02:36:22',3,'EXECUTED','8:c01f6e802b49436b4489040da3012359','addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_CASE_INST; createIndex indexName=ACT_IDX_PLAN_ITEM_STAGE_INST, tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableNam...','',NULL,'4.3.5',NULL,NULL,'0683828602'),('4','flowable','org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml','2022-09-08 02:36:22',4,'EXECUTED','8:e40d29cb79345b7fb5afd38a7f0ba8fc','createTable tableName=ACT_CMMN_HI_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_MIL_INST; addColumn tableName=ACT_CMMN_HI_MIL_INST','',NULL,'4.3.5',NULL,NULL,'0683828602'),('5','flowable','org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml','2022-09-08 02:36:23',5,'EXECUTED','8:70349de472f87368dcdec971a10311a0','modifyDataType columnName=DEPLOY_TIME_, tableName=ACT_CMMN_DEPLOYMENT; modifyDataType columnName=START_TIME_, tableName=ACT_CMMN_RU_CASE_INST; modifyDataType columnName=START_TIME_, tableName=ACT_CMMN_RU_PLAN_ITEM_INST; modifyDataType columnName=T...','',NULL,'4.3.5',NULL,NULL,'0683828602'),('6','flowable','org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml','2022-09-08 02:36:23',6,'EXECUTED','8:10e82e26a7fee94c32a92099c059c18c','createIndex indexName=ACT_IDX_CASE_DEF_UNIQ, tableName=ACT_CMMN_CASEDEF','',NULL,'4.3.5',NULL,NULL,'0683828602'),('7','flowable','org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml','2022-09-08 02:36:23',7,'EXECUTED','8:530bc81a1e30618ccf4a2da1f7c6c043','renameColumn newColumnName=CREATE_TIME_, oldColumnName=START_TIME_, tableName=ACT_CMMN_RU_PLAN_ITEM_INST; renameColumn newColumnName=CREATE_TIME_, oldColumnName=CREATED_TIME_, tableName=ACT_CMMN_HI_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_P...','',NULL,'4.3.5',NULL,NULL,'0683828602'),('8','flowable','org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml','2022-09-08 02:36:23',8,'EXECUTED','8:e8c2eb1ce28bc301efe07e0e29757781','addColumn tableName=ACT_CMMN_HI_PLAN_ITEM_INST','',NULL,'4.3.5',NULL,NULL,'0683828602'),('9','flowable','org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml','2022-09-08 02:36:23',9,'EXECUTED','8:4cb4782b9bdec5ced2a64c525aa7b3a0','addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_HI_PLAN_ITEM_INST','',NULL,'4.3.5',NULL,NULL,'0683828602'),('10','flowable','org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml','2022-09-08 02:36:23',10,'EXECUTED','8:341c16be247f5d17badc9809da8691f9','addColumn tableName=ACT_CMMN_RU_CASE_INST; addColumn tableName=ACT_CMMN_RU_CASE_INST; createIndex indexName=ACT_IDX_CASE_INST_REF_ID_, tableName=ACT_CMMN_RU_CASE_INST; addColumn tableName=ACT_CMMN_HI_CASE_INST; addColumn tableName=ACT_CMMN_HI_CASE...','',NULL,'4.3.5',NULL,NULL,'0683828602'),('11','flowable','org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml','2022-09-08 02:36:23',11,'EXECUTED','8:d7c4da9276bcfffbfb0ebfb25e3f7b05','addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_HI_PLAN_ITEM_INST','',NULL,'4.3.5',NULL,NULL,'0683828602'),('12','flowable','org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml','2022-09-08 02:36:23',12,'EXECUTED','8:adf4ecc45f2aa9a44a5626b02e1d6f98','addColumn tableName=ACT_CMMN_RU_CASE_INST','',NULL,'4.3.5',NULL,NULL,'0683828602'),('13','flowable','org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml','2022-09-08 02:36:23',13,'EXECUTED','8:7550626f964ab5518464709408333ec1','addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_HI_PLAN_ITEM_INST','',NULL,'4.3.5',NULL,NULL,'0683828602'),('14','flowable','org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml','2022-09-08 02:36:23',14,'EXECUTED','8:086b40b3a05596dcc8a8d7479922d494','addColumn tableName=ACT_CMMN_RU_CASE_INST; addColumn tableName=ACT_CMMN_HI_CASE_INST','',NULL,'4.3.5',NULL,NULL,'0683828602'),('16','flowable','org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml','2022-09-08 02:36:23',15,'EXECUTED','8:a697a222ddd99dd15b36516a252f1c63','addColumn tableName=ACT_CMMN_RU_CASE_INST; addColumn tableName=ACT_CMMN_HI_CASE_INST','',NULL,'4.3.5',NULL,NULL,'0683828602');
/*!40000 ALTER TABLE `ACT_CMMN_DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_CMMN_DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `ACT_CMMN_DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_CMMN_DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_CMMN_DATABASECHANGELOGLOCK`
--

LOCK TABLES `ACT_CMMN_DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `ACT_CMMN_DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `ACT_CMMN_DATABASECHANGELOGLOCK` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `ACT_CMMN_DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_CMMN_DEPLOYMENT`
--

DROP TABLE IF EXISTS `ACT_CMMN_DEPLOYMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_CMMN_DEPLOYMENT` (
  `ID_` varchar(255) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `CATEGORY_` varchar(255) DEFAULT NULL,
  `KEY_` varchar(255) DEFAULT NULL,
  `DEPLOY_TIME_` datetime(3) DEFAULT NULL,
  `PARENT_DEPLOYMENT_ID_` varchar(255) DEFAULT NULL,
  `TENANT_ID_` varchar(255) DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_CMMN_DEPLOYMENT`
--

LOCK TABLES `ACT_CMMN_DEPLOYMENT` WRITE;
/*!40000 ALTER TABLE `ACT_CMMN_DEPLOYMENT` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_CMMN_DEPLOYMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_CMMN_DEPLOYMENT_RESOURCE`
--

DROP TABLE IF EXISTS `ACT_CMMN_DEPLOYMENT_RESOURCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_CMMN_DEPLOYMENT_RESOURCE` (
  `ID_` varchar(255) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) DEFAULT NULL,
  `RESOURCE_BYTES_` longblob DEFAULT NULL,
  `GENERATED_` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_CMMN_RSRC_DPL` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_CMMN_RSRC_DPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_CMMN_DEPLOYMENT` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_CMMN_DEPLOYMENT_RESOURCE`
--

LOCK TABLES `ACT_CMMN_DEPLOYMENT_RESOURCE` WRITE;
/*!40000 ALTER TABLE `ACT_CMMN_DEPLOYMENT_RESOURCE` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_CMMN_DEPLOYMENT_RESOURCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_CMMN_HI_CASE_INST`
--

DROP TABLE IF EXISTS `ACT_CMMN_HI_CASE_INST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_CMMN_HI_CASE_INST` (
  `ID_` varchar(255) NOT NULL,
  `REV_` int(11) NOT NULL,
  `BUSINESS_KEY_` varchar(255) DEFAULT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `PARENT_ID_` varchar(255) DEFAULT NULL,
  `CASE_DEF_ID_` varchar(255) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `START_TIME_` datetime(3) DEFAULT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `START_USER_ID_` varchar(255) DEFAULT NULL,
  `CALLBACK_ID_` varchar(255) DEFAULT NULL,
  `CALLBACK_TYPE_` varchar(255) DEFAULT NULL,
  `TENANT_ID_` varchar(255) DEFAULT '',
  `REFERENCE_ID_` varchar(255) DEFAULT NULL,
  `REFERENCE_TYPE_` varchar(255) DEFAULT NULL,
  `LAST_REACTIVATION_TIME_` datetime(3) DEFAULT NULL,
  `LAST_REACTIVATION_USER_ID_` varchar(255) DEFAULT NULL,
  `BUSINESS_STATUS_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_CMMN_HI_CASE_INST`
--

LOCK TABLES `ACT_CMMN_HI_CASE_INST` WRITE;
/*!40000 ALTER TABLE `ACT_CMMN_HI_CASE_INST` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_CMMN_HI_CASE_INST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_CMMN_HI_MIL_INST`
--

DROP TABLE IF EXISTS `ACT_CMMN_HI_MIL_INST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_CMMN_HI_MIL_INST` (
  `ID_` varchar(255) NOT NULL,
  `REV_` int(11) NOT NULL,
  `NAME_` varchar(255) NOT NULL,
  `TIME_STAMP_` datetime(3) DEFAULT NULL,
  `CASE_INST_ID_` varchar(255) NOT NULL,
  `CASE_DEF_ID_` varchar(255) NOT NULL,
  `ELEMENT_ID_` varchar(255) NOT NULL,
  `TENANT_ID_` varchar(255) DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_CMMN_HI_MIL_INST`
--

LOCK TABLES `ACT_CMMN_HI_MIL_INST` WRITE;
/*!40000 ALTER TABLE `ACT_CMMN_HI_MIL_INST` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_CMMN_HI_MIL_INST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_CMMN_HI_PLAN_ITEM_INST`
--

DROP TABLE IF EXISTS `ACT_CMMN_HI_PLAN_ITEM_INST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_CMMN_HI_PLAN_ITEM_INST` (
  `ID_` varchar(255) NOT NULL,
  `REV_` int(11) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `CASE_DEF_ID_` varchar(255) DEFAULT NULL,
  `CASE_INST_ID_` varchar(255) DEFAULT NULL,
  `STAGE_INST_ID_` varchar(255) DEFAULT NULL,
  `IS_STAGE_` bit(1) DEFAULT NULL,
  `ELEMENT_ID_` varchar(255) DEFAULT NULL,
  `ITEM_DEFINITION_ID_` varchar(255) DEFAULT NULL,
  `ITEM_DEFINITION_TYPE_` varchar(255) DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_AVAILABLE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_ENABLED_TIME_` datetime(3) DEFAULT NULL,
  `LAST_DISABLED_TIME_` datetime(3) DEFAULT NULL,
  `LAST_STARTED_TIME_` datetime(3) DEFAULT NULL,
  `LAST_SUSPENDED_TIME_` datetime(3) DEFAULT NULL,
  `COMPLETED_TIME_` datetime(3) DEFAULT NULL,
  `OCCURRED_TIME_` datetime(3) DEFAULT NULL,
  `TERMINATED_TIME_` datetime(3) DEFAULT NULL,
  `EXIT_TIME_` datetime(3) DEFAULT NULL,
  `ENDED_TIME_` datetime(3) DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  `START_USER_ID_` varchar(255) DEFAULT NULL,
  `REFERENCE_ID_` varchar(255) DEFAULT NULL,
  `REFERENCE_TYPE_` varchar(255) DEFAULT NULL,
  `TENANT_ID_` varchar(255) DEFAULT '',
  `ENTRY_CRITERION_ID_` varchar(255) DEFAULT NULL,
  `EXIT_CRITERION_ID_` varchar(255) DEFAULT NULL,
  `SHOW_IN_OVERVIEW_` bit(1) DEFAULT NULL,
  `EXTRA_VALUE_` varchar(255) DEFAULT NULL,
  `DERIVED_CASE_DEF_ID_` varchar(255) DEFAULT NULL,
  `LAST_UNAVAILABLE_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_CMMN_HI_PLAN_ITEM_INST`
--

LOCK TABLES `ACT_CMMN_HI_PLAN_ITEM_INST` WRITE;
/*!40000 ALTER TABLE `ACT_CMMN_HI_PLAN_ITEM_INST` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_CMMN_HI_PLAN_ITEM_INST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_CMMN_RU_CASE_INST`
--

DROP TABLE IF EXISTS `ACT_CMMN_RU_CASE_INST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_CMMN_RU_CASE_INST` (
  `ID_` varchar(255) NOT NULL,
  `REV_` int(11) NOT NULL,
  `BUSINESS_KEY_` varchar(255) DEFAULT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `PARENT_ID_` varchar(255) DEFAULT NULL,
  `CASE_DEF_ID_` varchar(255) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `START_TIME_` datetime(3) DEFAULT NULL,
  `START_USER_ID_` varchar(255) DEFAULT NULL,
  `CALLBACK_ID_` varchar(255) DEFAULT NULL,
  `CALLBACK_TYPE_` varchar(255) DEFAULT NULL,
  `TENANT_ID_` varchar(255) DEFAULT '',
  `LOCK_TIME_` datetime(3) DEFAULT NULL,
  `IS_COMPLETEABLE_` bit(1) DEFAULT NULL,
  `REFERENCE_ID_` varchar(255) DEFAULT NULL,
  `REFERENCE_TYPE_` varchar(255) DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) DEFAULT NULL,
  `LAST_REACTIVATION_TIME_` datetime(3) DEFAULT NULL,
  `LAST_REACTIVATION_USER_ID_` varchar(255) DEFAULT NULL,
  `BUSINESS_STATUS_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_CASE_INST_CASE_DEF` (`CASE_DEF_ID_`),
  KEY `ACT_IDX_CASE_INST_PARENT` (`PARENT_ID_`),
  KEY `ACT_IDX_CASE_INST_REF_ID_` (`REFERENCE_ID_`),
  CONSTRAINT `ACT_FK_CASE_INST_CASE_DEF` FOREIGN KEY (`CASE_DEF_ID_`) REFERENCES `ACT_CMMN_CASEDEF` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_CMMN_RU_CASE_INST`
--

LOCK TABLES `ACT_CMMN_RU_CASE_INST` WRITE;
/*!40000 ALTER TABLE `ACT_CMMN_RU_CASE_INST` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_CMMN_RU_CASE_INST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_CMMN_RU_MIL_INST`
--

DROP TABLE IF EXISTS `ACT_CMMN_RU_MIL_INST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_CMMN_RU_MIL_INST` (
  `ID_` varchar(255) NOT NULL,
  `NAME_` varchar(255) NOT NULL,
  `TIME_STAMP_` datetime(3) DEFAULT NULL,
  `CASE_INST_ID_` varchar(255) NOT NULL,
  `CASE_DEF_ID_` varchar(255) NOT NULL,
  `ELEMENT_ID_` varchar(255) NOT NULL,
  `TENANT_ID_` varchar(255) DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_MIL_CASE_DEF` (`CASE_DEF_ID_`),
  KEY `ACT_IDX_MIL_CASE_INST` (`CASE_INST_ID_`),
  CONSTRAINT `ACT_FK_MIL_CASE_DEF` FOREIGN KEY (`CASE_DEF_ID_`) REFERENCES `ACT_CMMN_CASEDEF` (`ID_`),
  CONSTRAINT `ACT_FK_MIL_CASE_INST` FOREIGN KEY (`CASE_INST_ID_`) REFERENCES `ACT_CMMN_RU_CASE_INST` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_CMMN_RU_MIL_INST`
--

LOCK TABLES `ACT_CMMN_RU_MIL_INST` WRITE;
/*!40000 ALTER TABLE `ACT_CMMN_RU_MIL_INST` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_CMMN_RU_MIL_INST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_CMMN_RU_PLAN_ITEM_INST`
--

DROP TABLE IF EXISTS `ACT_CMMN_RU_PLAN_ITEM_INST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_CMMN_RU_PLAN_ITEM_INST` (
  `ID_` varchar(255) NOT NULL,
  `REV_` int(11) NOT NULL,
  `CASE_DEF_ID_` varchar(255) DEFAULT NULL,
  `CASE_INST_ID_` varchar(255) DEFAULT NULL,
  `STAGE_INST_ID_` varchar(255) DEFAULT NULL,
  `IS_STAGE_` bit(1) DEFAULT NULL,
  `ELEMENT_ID_` varchar(255) DEFAULT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `START_USER_ID_` varchar(255) DEFAULT NULL,
  `REFERENCE_ID_` varchar(255) DEFAULT NULL,
  `REFERENCE_TYPE_` varchar(255) DEFAULT NULL,
  `TENANT_ID_` varchar(255) DEFAULT '',
  `ITEM_DEFINITION_ID_` varchar(255) DEFAULT NULL,
  `ITEM_DEFINITION_TYPE_` varchar(255) DEFAULT NULL,
  `IS_COMPLETEABLE_` bit(1) DEFAULT NULL,
  `IS_COUNT_ENABLED_` bit(1) DEFAULT NULL,
  `VAR_COUNT_` int(11) DEFAULT NULL,
  `SENTRY_PART_INST_COUNT_` int(11) DEFAULT NULL,
  `LAST_AVAILABLE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_ENABLED_TIME_` datetime(3) DEFAULT NULL,
  `LAST_DISABLED_TIME_` datetime(3) DEFAULT NULL,
  `LAST_STARTED_TIME_` datetime(3) DEFAULT NULL,
  `LAST_SUSPENDED_TIME_` datetime(3) DEFAULT NULL,
  `COMPLETED_TIME_` datetime(3) DEFAULT NULL,
  `OCCURRED_TIME_` datetime(3) DEFAULT NULL,
  `TERMINATED_TIME_` datetime(3) DEFAULT NULL,
  `EXIT_TIME_` datetime(3) DEFAULT NULL,
  `ENDED_TIME_` datetime(3) DEFAULT NULL,
  `ENTRY_CRITERION_ID_` varchar(255) DEFAULT NULL,
  `EXIT_CRITERION_ID_` varchar(255) DEFAULT NULL,
  `EXTRA_VALUE_` varchar(255) DEFAULT NULL,
  `DERIVED_CASE_DEF_ID_` varchar(255) DEFAULT NULL,
  `LAST_UNAVAILABLE_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_PLAN_ITEM_CASE_DEF` (`CASE_DEF_ID_`),
  KEY `ACT_IDX_PLAN_ITEM_CASE_INST` (`CASE_INST_ID_`),
  KEY `ACT_IDX_PLAN_ITEM_STAGE_INST` (`STAGE_INST_ID_`),
  CONSTRAINT `ACT_FK_PLAN_ITEM_CASE_DEF` FOREIGN KEY (`CASE_DEF_ID_`) REFERENCES `ACT_CMMN_CASEDEF` (`ID_`),
  CONSTRAINT `ACT_FK_PLAN_ITEM_CASE_INST` FOREIGN KEY (`CASE_INST_ID_`) REFERENCES `ACT_CMMN_RU_CASE_INST` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_CMMN_RU_PLAN_ITEM_INST`
--

LOCK TABLES `ACT_CMMN_RU_PLAN_ITEM_INST` WRITE;
/*!40000 ALTER TABLE `ACT_CMMN_RU_PLAN_ITEM_INST` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_CMMN_RU_PLAN_ITEM_INST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_CMMN_RU_SENTRY_PART_INST`
--

DROP TABLE IF EXISTS `ACT_CMMN_RU_SENTRY_PART_INST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_CMMN_RU_SENTRY_PART_INST` (
  `ID_` varchar(255) NOT NULL,
  `REV_` int(11) NOT NULL,
  `CASE_DEF_ID_` varchar(255) DEFAULT NULL,
  `CASE_INST_ID_` varchar(255) DEFAULT NULL,
  `PLAN_ITEM_INST_ID_` varchar(255) DEFAULT NULL,
  `ON_PART_ID_` varchar(255) DEFAULT NULL,
  `IF_PART_ID_` varchar(255) DEFAULT NULL,
  `TIME_STAMP_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_SENTRY_CASE_DEF` (`CASE_DEF_ID_`),
  KEY `ACT_IDX_SENTRY_CASE_INST` (`CASE_INST_ID_`),
  KEY `ACT_IDX_SENTRY_PLAN_ITEM` (`PLAN_ITEM_INST_ID_`),
  CONSTRAINT `ACT_FK_SENTRY_CASE_DEF` FOREIGN KEY (`CASE_DEF_ID_`) REFERENCES `ACT_CMMN_CASEDEF` (`ID_`),
  CONSTRAINT `ACT_FK_SENTRY_CASE_INST` FOREIGN KEY (`CASE_INST_ID_`) REFERENCES `ACT_CMMN_RU_CASE_INST` (`ID_`),
  CONSTRAINT `ACT_FK_SENTRY_PLAN_ITEM` FOREIGN KEY (`PLAN_ITEM_INST_ID_`) REFERENCES `ACT_CMMN_RU_PLAN_ITEM_INST` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_CMMN_RU_SENTRY_PART_INST`
--

LOCK TABLES `ACT_CMMN_RU_SENTRY_PART_INST` WRITE;
/*!40000 ALTER TABLE `ACT_CMMN_RU_SENTRY_PART_INST` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_CMMN_RU_SENTRY_PART_INST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_CO_CONTENT_ITEM`
--

DROP TABLE IF EXISTS `ACT_CO_CONTENT_ITEM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_CO_CONTENT_ITEM` (
  `ID_` varchar(255) NOT NULL,
  `NAME_` varchar(255) NOT NULL,
  `MIME_TYPE_` varchar(255) DEFAULT NULL,
  `TASK_ID_` varchar(255) DEFAULT NULL,
  `PROC_INST_ID_` varchar(255) DEFAULT NULL,
  `CONTENT_STORE_ID_` varchar(255) DEFAULT NULL,
  `CONTENT_STORE_NAME_` varchar(255) DEFAULT NULL,
  `FIELD_` varchar(400) DEFAULT NULL,
  `CONTENT_AVAILABLE_` bit(1) DEFAULT b'0',
  `CREATED_` timestamp(6) NULL DEFAULT NULL,
  `CREATED_BY_` varchar(255) DEFAULT NULL,
  `LAST_MODIFIED_` timestamp(6) NULL DEFAULT NULL,
  `LAST_MODIFIED_BY_` varchar(255) DEFAULT NULL,
  `CONTENT_SIZE_` bigint(20) DEFAULT 0,
  `TENANT_ID_` varchar(255) DEFAULT NULL,
  `SCOPE_ID_` varchar(255) DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `idx_contitem_taskid` (`TASK_ID_`),
  KEY `idx_contitem_procid` (`PROC_INST_ID_`),
  KEY `idx_contitem_scope` (`SCOPE_ID_`,`SCOPE_TYPE_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_CO_CONTENT_ITEM`
--

LOCK TABLES `ACT_CO_CONTENT_ITEM` WRITE;
/*!40000 ALTER TABLE `ACT_CO_CONTENT_ITEM` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_CO_CONTENT_ITEM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_CO_DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `ACT_CO_DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_CO_DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_CO_DATABASECHANGELOG`
--

LOCK TABLES `ACT_CO_DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `ACT_CO_DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `ACT_CO_DATABASECHANGELOG` VALUES ('1','activiti','org/flowable/content/db/liquibase/flowable-content-db-changelog.xml','2022-09-08 02:36:23',1,'EXECUTED','8:7644d7165cfe799200a2abdd3419e8b6','createTable tableName=ACT_CO_CONTENT_ITEM; createIndex indexName=idx_contitem_taskid, tableName=ACT_CO_CONTENT_ITEM; createIndex indexName=idx_contitem_procid, tableName=ACT_CO_CONTENT_ITEM','',NULL,'4.3.5',NULL,NULL,'0683830961'),('2','flowable','org/flowable/content/db/liquibase/flowable-content-db-changelog.xml','2022-09-08 02:36:23',2,'EXECUTED','8:fe7b11ac7dbbf9c43006b23bbab60bab','addColumn tableName=ACT_CO_CONTENT_ITEM; createIndex indexName=idx_contitem_scope, tableName=ACT_CO_CONTENT_ITEM','',NULL,'4.3.5',NULL,NULL,'0683830961');
/*!40000 ALTER TABLE `ACT_CO_DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_CO_DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `ACT_CO_DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_CO_DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_CO_DATABASECHANGELOGLOCK`
--

LOCK TABLES `ACT_CO_DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `ACT_CO_DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `ACT_CO_DATABASECHANGELOGLOCK` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `ACT_CO_DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_DMN_DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `ACT_DMN_DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_DMN_DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_DMN_DATABASECHANGELOG`
--

LOCK TABLES `ACT_DMN_DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `ACT_DMN_DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `ACT_DMN_DATABASECHANGELOG` VALUES ('1','activiti','org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml','2022-09-08 02:36:23',1,'EXECUTED','8:c8701f1c71018b55029f450b2e9a10a1','createTable tableName=ACT_DMN_DEPLOYMENT; createTable tableName=ACT_DMN_DEPLOYMENT_RESOURCE; createTable tableName=ACT_DMN_DECISION_TABLE','',NULL,'4.3.5',NULL,NULL,'0683830359'),('2','flowable','org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml','2022-09-08 02:36:23',2,'EXECUTED','8:47f94b27feb7df8a30d4e338c7bd5fb8','createTable tableName=ACT_DMN_HI_DECISION_EXECUTION','',NULL,'4.3.5',NULL,NULL,'0683830359'),('3','flowable','org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml','2022-09-08 02:36:23',3,'EXECUTED','8:ac17eae89fbdccb6e08daf3c7797b579','addColumn tableName=ACT_DMN_HI_DECISION_EXECUTION','',NULL,'4.3.5',NULL,NULL,'0683830359'),('4','flowable','org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml','2022-09-08 02:36:23',4,'EXECUTED','8:f73aabc4529e7292c2942073d1cff6f9','dropColumn columnName=PARENT_DEPLOYMENT_ID_, tableName=ACT_DMN_DECISION_TABLE','',NULL,'4.3.5',NULL,NULL,'0683830359'),('5','flowable','org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml','2022-09-08 02:36:23',5,'EXECUTED','8:3e03528582dd4eeb4eb41f9b9539140d','modifyDataType columnName=DEPLOY_TIME_, tableName=ACT_DMN_DEPLOYMENT; modifyDataType columnName=START_TIME_, tableName=ACT_DMN_HI_DECISION_EXECUTION; modifyDataType columnName=END_TIME_, tableName=ACT_DMN_HI_DECISION_EXECUTION','',NULL,'4.3.5',NULL,NULL,'0683830359'),('6','flowable','org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml','2022-09-08 02:36:23',6,'EXECUTED','8:646c6a061e0b6e8a62e69844ff96abb0','createIndex indexName=ACT_IDX_DEC_TBL_UNIQ, tableName=ACT_DMN_DECISION_TABLE','',NULL,'4.3.5',NULL,NULL,'0683830359'),('7','flowable','org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml','2022-09-08 02:36:23',7,'EXECUTED','8:215a499ff7ae77685b55355245b8b708','dropIndex indexName=ACT_IDX_DEC_TBL_UNIQ, tableName=ACT_DMN_DECISION_TABLE; renameTable newTableName=ACT_DMN_DECISION, oldTableName=ACT_DMN_DECISION_TABLE; createIndex indexName=ACT_IDX_DMN_DEC_UNIQ, tableName=ACT_DMN_DECISION','',NULL,'4.3.5',NULL,NULL,'0683830359'),('8','flowable','org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml','2022-09-08 02:36:23',8,'EXECUTED','8:5355bee389318afed91a11702f2df032','addColumn tableName=ACT_DMN_DECISION','',NULL,'4.3.5',NULL,NULL,'0683830359');
/*!40000 ALTER TABLE `ACT_DMN_DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_DMN_DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `ACT_DMN_DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_DMN_DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_DMN_DATABASECHANGELOGLOCK`
--

LOCK TABLES `ACT_DMN_DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `ACT_DMN_DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `ACT_DMN_DATABASECHANGELOGLOCK` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `ACT_DMN_DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_DMN_DECISION`
--

DROP TABLE IF EXISTS `ACT_DMN_DECISION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_DMN_DECISION` (
  `ID_` varchar(255) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `KEY_` varchar(255) DEFAULT NULL,
  `CATEGORY_` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) DEFAULT NULL,
  `TENANT_ID_` varchar(255) DEFAULT NULL,
  `RESOURCE_NAME_` varchar(255) DEFAULT NULL,
  `DESCRIPTION_` varchar(255) DEFAULT NULL,
  `DECISION_TYPE_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_IDX_DMN_DEC_UNIQ` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_DMN_DECISION`
--

LOCK TABLES `ACT_DMN_DECISION` WRITE;
/*!40000 ALTER TABLE `ACT_DMN_DECISION` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_DMN_DECISION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_DMN_DEPLOYMENT`
--

DROP TABLE IF EXISTS `ACT_DMN_DEPLOYMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_DMN_DEPLOYMENT` (
  `ID_` varchar(255) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `CATEGORY_` varchar(255) DEFAULT NULL,
  `DEPLOY_TIME_` datetime(3) DEFAULT NULL,
  `TENANT_ID_` varchar(255) DEFAULT NULL,
  `PARENT_DEPLOYMENT_ID_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_DMN_DEPLOYMENT`
--

LOCK TABLES `ACT_DMN_DEPLOYMENT` WRITE;
/*!40000 ALTER TABLE `ACT_DMN_DEPLOYMENT` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_DMN_DEPLOYMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_DMN_DEPLOYMENT_RESOURCE`
--

DROP TABLE IF EXISTS `ACT_DMN_DEPLOYMENT_RESOURCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_DMN_DEPLOYMENT_RESOURCE` (
  `ID_` varchar(255) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) DEFAULT NULL,
  `RESOURCE_BYTES_` longblob DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_DMN_DEPLOYMENT_RESOURCE`
--

LOCK TABLES `ACT_DMN_DEPLOYMENT_RESOURCE` WRITE;
/*!40000 ALTER TABLE `ACT_DMN_DEPLOYMENT_RESOURCE` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_DMN_DEPLOYMENT_RESOURCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_DMN_HI_DECISION_EXECUTION`
--

DROP TABLE IF EXISTS `ACT_DMN_HI_DECISION_EXECUTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_DMN_HI_DECISION_EXECUTION` (
  `ID_` varchar(255) NOT NULL,
  `DECISION_DEFINITION_ID_` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) DEFAULT NULL,
  `START_TIME_` datetime(3) DEFAULT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `INSTANCE_ID_` varchar(255) DEFAULT NULL,
  `EXECUTION_ID_` varchar(255) DEFAULT NULL,
  `ACTIVITY_ID_` varchar(255) DEFAULT NULL,
  `FAILED_` bit(1) DEFAULT b'0',
  `TENANT_ID_` varchar(255) DEFAULT NULL,
  `EXECUTION_JSON_` longtext DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_DMN_HI_DECISION_EXECUTION`
--

LOCK TABLES `ACT_DMN_HI_DECISION_EXECUTION` WRITE;
/*!40000 ALTER TABLE `ACT_DMN_HI_DECISION_EXECUTION` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_DMN_HI_DECISION_EXECUTION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_EVT_LOG`
--

DROP TABLE IF EXISTS `ACT_EVT_LOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_EVT_LOG` (
  `LOG_NR_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_STAMP_` timestamp(3) NOT NULL DEFAULT current_timestamp(3),
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_` longblob DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_PROCESSED_` tinyint(4) DEFAULT 0,
  PRIMARY KEY (`LOG_NR_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_EVT_LOG`
--

LOCK TABLES `ACT_EVT_LOG` WRITE;
/*!40000 ALTER TABLE `ACT_EVT_LOG` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_EVT_LOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_FO_DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `ACT_FO_DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_FO_DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_FO_DATABASECHANGELOG`
--

LOCK TABLES `ACT_FO_DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `ACT_FO_DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `ACT_FO_DATABASECHANGELOG` VALUES ('1','activiti','org/flowable/form/db/liquibase/flowable-form-db-changelog.xml','2022-09-08 02:36:23',1,'EXECUTED','8:033ebf9380889aed7c453927ecc3250d','createTable tableName=ACT_FO_FORM_DEPLOYMENT; createTable tableName=ACT_FO_FORM_RESOURCE; createTable tableName=ACT_FO_FORM_DEFINITION; createTable tableName=ACT_FO_FORM_INSTANCE','',NULL,'4.3.5',NULL,NULL,'0683830710'),('2','flowable','org/flowable/form/db/liquibase/flowable-form-db-changelog.xml','2022-09-08 02:36:23',2,'EXECUTED','8:986365ceb40445ce3b27a8e6b40f159b','addColumn tableName=ACT_FO_FORM_INSTANCE','',NULL,'4.3.5',NULL,NULL,'0683830710'),('3','flowable','org/flowable/form/db/liquibase/flowable-form-db-changelog.xml','2022-09-08 02:36:23',3,'EXECUTED','8:abf482518ceb09830ef674e52c06bf15','dropColumn columnName=PARENT_DEPLOYMENT_ID_, tableName=ACT_FO_FORM_DEFINITION','',NULL,'4.3.5',NULL,NULL,'0683830710'),('4','flowable','org/flowable/form/db/liquibase/flowable-form-db-changelog.xml','2022-09-08 02:36:23',4,'EXECUTED','8:2087829f22a4b2298dbf530681c74854','modifyDataType columnName=DEPLOY_TIME_, tableName=ACT_FO_FORM_DEPLOYMENT; modifyDataType columnName=SUBMITTED_DATE_, tableName=ACT_FO_FORM_INSTANCE','',NULL,'4.3.5',NULL,NULL,'0683830710'),('5','flowable','org/flowable/form/db/liquibase/flowable-form-db-changelog.xml','2022-09-08 02:36:23',5,'EXECUTED','8:b4be732b89e5ca028bdd520c6ad4d446','createIndex indexName=ACT_IDX_FORM_DEF_UNIQ, tableName=ACT_FO_FORM_DEFINITION','',NULL,'4.3.5',NULL,NULL,'0683830710'),('6','flowable','org/flowable/form/db/liquibase/flowable-form-db-changelog.xml','2022-09-08 02:36:23',6,'EXECUTED','8:384bbd364a649b67c3ca1bcb72fe537f','createIndex indexName=ACT_IDX_FORM_TASK, tableName=ACT_FO_FORM_INSTANCE; createIndex indexName=ACT_IDX_FORM_PROC, tableName=ACT_FO_FORM_INSTANCE; createIndex indexName=ACT_IDX_FORM_SCOPE, tableName=ACT_FO_FORM_INSTANCE','',NULL,'4.3.5',NULL,NULL,'0683830710');
/*!40000 ALTER TABLE `ACT_FO_DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_FO_DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `ACT_FO_DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_FO_DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_FO_DATABASECHANGELOGLOCK`
--

LOCK TABLES `ACT_FO_DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `ACT_FO_DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `ACT_FO_DATABASECHANGELOGLOCK` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `ACT_FO_DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_FO_FORM_DEFINITION`
--

DROP TABLE IF EXISTS `ACT_FO_FORM_DEFINITION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_FO_FORM_DEFINITION` (
  `ID_` varchar(255) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `KEY_` varchar(255) DEFAULT NULL,
  `CATEGORY_` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) DEFAULT NULL,
  `TENANT_ID_` varchar(255) DEFAULT NULL,
  `RESOURCE_NAME_` varchar(255) DEFAULT NULL,
  `DESCRIPTION_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_IDX_FORM_DEF_UNIQ` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_FO_FORM_DEFINITION`
--

LOCK TABLES `ACT_FO_FORM_DEFINITION` WRITE;
/*!40000 ALTER TABLE `ACT_FO_FORM_DEFINITION` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_FO_FORM_DEFINITION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_FO_FORM_DEPLOYMENT`
--

DROP TABLE IF EXISTS `ACT_FO_FORM_DEPLOYMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_FO_FORM_DEPLOYMENT` (
  `ID_` varchar(255) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `CATEGORY_` varchar(255) DEFAULT NULL,
  `DEPLOY_TIME_` datetime(3) DEFAULT NULL,
  `TENANT_ID_` varchar(255) DEFAULT NULL,
  `PARENT_DEPLOYMENT_ID_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_FO_FORM_DEPLOYMENT`
--

LOCK TABLES `ACT_FO_FORM_DEPLOYMENT` WRITE;
/*!40000 ALTER TABLE `ACT_FO_FORM_DEPLOYMENT` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_FO_FORM_DEPLOYMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_FO_FORM_INSTANCE`
--

DROP TABLE IF EXISTS `ACT_FO_FORM_INSTANCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_FO_FORM_INSTANCE` (
  `ID_` varchar(255) NOT NULL,
  `FORM_DEFINITION_ID_` varchar(255) NOT NULL,
  `TASK_ID_` varchar(255) DEFAULT NULL,
  `PROC_INST_ID_` varchar(255) DEFAULT NULL,
  `PROC_DEF_ID_` varchar(255) DEFAULT NULL,
  `SUBMITTED_DATE_` datetime(3) DEFAULT NULL,
  `SUBMITTED_BY_` varchar(255) DEFAULT NULL,
  `FORM_VALUES_ID_` varchar(255) DEFAULT NULL,
  `TENANT_ID_` varchar(255) DEFAULT NULL,
  `SCOPE_ID_` varchar(255) DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_FORM_TASK` (`TASK_ID_`),
  KEY `ACT_IDX_FORM_PROC` (`PROC_INST_ID_`),
  KEY `ACT_IDX_FORM_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_FO_FORM_INSTANCE`
--

LOCK TABLES `ACT_FO_FORM_INSTANCE` WRITE;
/*!40000 ALTER TABLE `ACT_FO_FORM_INSTANCE` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_FO_FORM_INSTANCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_FO_FORM_RESOURCE`
--

DROP TABLE IF EXISTS `ACT_FO_FORM_RESOURCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_FO_FORM_RESOURCE` (
  `ID_` varchar(255) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) DEFAULT NULL,
  `RESOURCE_BYTES_` longblob DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_FO_FORM_RESOURCE`
--

LOCK TABLES `ACT_FO_FORM_RESOURCE` WRITE;
/*!40000 ALTER TABLE `ACT_FO_FORM_RESOURCE` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_FO_FORM_RESOURCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_GE_BYTEARRAY`
--

DROP TABLE IF EXISTS `ACT_GE_BYTEARRAY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_GE_BYTEARRAY` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob DEFAULT NULL,
  `GENERATED_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_BYTEARR_DEPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_RE_DEPLOYMENT` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_GE_BYTEARRAY`
--

LOCK TABLES `ACT_GE_BYTEARRAY` WRITE;
/*!40000 ALTER TABLE `ACT_GE_BYTEARRAY` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_GE_BYTEARRAY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_GE_PROPERTY`
--

DROP TABLE IF EXISTS `ACT_GE_PROPERTY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_GE_PROPERTY` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL,
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_GE_PROPERTY`
--

LOCK TABLES `ACT_GE_PROPERTY` WRITE;
/*!40000 ALTER TABLE `ACT_GE_PROPERTY` DISABLE KEYS */;
INSERT INTO `ACT_GE_PROPERTY` VALUES ('batch.schema.version','6.7.2.0',1),('common.schema.version','6.7.2.0',1),('entitylink.schema.version','6.7.2.0',1),('eventsubscription.schema.version','6.7.2.0',1),('identitylink.schema.version','6.7.2.0',1),('job.schema.version','6.7.2.0',1),('next.dbid','1',1),('schema.history','create(6.7.2.0)',1),('schema.version','6.7.2.0',1),('task.schema.version','6.7.2.0',1),('variable.schema.version','6.7.2.0',1);
/*!40000 ALTER TABLE `ACT_GE_PROPERTY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_HI_ACTINST`
--

DROP TABLE IF EXISTS `ACT_HI_ACTINST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_HI_ACTINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT 1,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `TRANSACTION_ORDER_` int(11) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_HI_ACTINST`
--

LOCK TABLES `ACT_HI_ACTINST` WRITE;
/*!40000 ALTER TABLE `ACT_HI_ACTINST` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_HI_ACTINST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_HI_ATTACHMENT`
--

DROP TABLE IF EXISTS `ACT_HI_ATTACHMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_HI_ATTACHMENT` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `URL_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CONTENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_HI_ATTACHMENT`
--

LOCK TABLES `ACT_HI_ATTACHMENT` WRITE;
/*!40000 ALTER TABLE `ACT_HI_ATTACHMENT` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_HI_ATTACHMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_HI_COMMENT`
--

DROP TABLE IF EXISTS `ACT_HI_COMMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_HI_COMMENT` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `FULL_MSG_` longblob DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_HI_COMMENT`
--

LOCK TABLES `ACT_HI_COMMENT` WRITE;
/*!40000 ALTER TABLE `ACT_HI_COMMENT` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_HI_COMMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_HI_DETAIL`
--

DROP TABLE IF EXISTS `ACT_HI_DETAIL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_HI_DETAIL` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`),
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`),
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_HI_DETAIL`
--

LOCK TABLES `ACT_HI_DETAIL` WRITE;
/*!40000 ALTER TABLE `ACT_HI_DETAIL` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_HI_DETAIL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_HI_ENTITYLINK`
--

DROP TABLE IF EXISTS `ACT_HI_ENTITYLINK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_HI_ENTITYLINK` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `LINK_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ELEMENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REF_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REF_SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REF_SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ROOT_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ROOT_SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HIERARCHY_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ENT_LNK_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`,`LINK_TYPE_`),
  KEY `ACT_IDX_HI_ENT_LNK_ROOT_SCOPE` (`ROOT_SCOPE_ID_`,`ROOT_SCOPE_TYPE_`,`LINK_TYPE_`),
  KEY `ACT_IDX_HI_ENT_LNK_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`,`LINK_TYPE_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_HI_ENTITYLINK`
--

LOCK TABLES `ACT_HI_ENTITYLINK` WRITE;
/*!40000 ALTER TABLE `ACT_HI_ENTITYLINK` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_HI_ENTITYLINK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_HI_IDENTITYLINK`
--

DROP TABLE IF EXISTS `ACT_HI_IDENTITYLINK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_HI_IDENTITYLINK` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_IDENT_LNK_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_IDENT_LNK_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_HI_IDENTITYLINK`
--

LOCK TABLES `ACT_HI_IDENTITYLINK` WRITE;
/*!40000 ALTER TABLE `ACT_HI_IDENTITYLINK` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_HI_IDENTITYLINK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_HI_PROCINST`
--

DROP TABLE IF EXISTS `ACT_HI_PROCINST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_HI_PROCINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT 1,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `END_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CALLBACK_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CALLBACK_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REFERENCE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REFERENCE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROPAGATED_STAGE_INST_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_STATUS_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_HI_PROCINST`
--

LOCK TABLES `ACT_HI_PROCINST` WRITE;
/*!40000 ALTER TABLE `ACT_HI_PROCINST` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_HI_PROCINST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_HI_TASKINST`
--

DROP TABLE IF EXISTS `ACT_HI_TASKINST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_HI_TASKINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT 1,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROPAGATED_STAGE_INST_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_TASK_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_TASK_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_TASK_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_TASK_INST_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_HI_TASKINST`
--

LOCK TABLES `ACT_HI_TASKINST` WRITE;
/*!40000 ALTER TABLE `ACT_HI_TASKINST` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_HI_TASKINST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_HI_TSK_LOG`
--

DROP TABLE IF EXISTS `ACT_HI_TSK_LOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_HI_TSK_LOG` (
  `ID_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TIME_STAMP_` timestamp(3) NOT NULL DEFAULT current_timestamp(3) ON UPDATE current_timestamp(3),
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_HI_TSK_LOG`
--

LOCK TABLES `ACT_HI_TSK_LOG` WRITE;
/*!40000 ALTER TABLE `ACT_HI_TSK_LOG` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_HI_TSK_LOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_HI_VARINST`
--

DROP TABLE IF EXISTS `ACT_HI_VARINST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_HI_VARINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT 1,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`,`VAR_TYPE_`),
  KEY `ACT_IDX_HI_VAR_SCOPE_ID_TYPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_VAR_SUB_ID_TYPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_EXE` (`EXECUTION_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_HI_VARINST`
--

LOCK TABLES `ACT_HI_VARINST` WRITE;
/*!40000 ALTER TABLE `ACT_HI_VARINST` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_HI_VARINST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_ID_BYTEARRAY`
--

DROP TABLE IF EXISTS `ACT_ID_BYTEARRAY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_ID_BYTEARRAY` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_ID_BYTEARRAY`
--

LOCK TABLES `ACT_ID_BYTEARRAY` WRITE;
/*!40000 ALTER TABLE `ACT_ID_BYTEARRAY` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_ID_BYTEARRAY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_ID_GROUP`
--

DROP TABLE IF EXISTS `ACT_ID_GROUP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_ID_GROUP` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_ID_GROUP`
--

LOCK TABLES `ACT_ID_GROUP` WRITE;
/*!40000 ALTER TABLE `ACT_ID_GROUP` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_ID_GROUP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_ID_INFO`
--

DROP TABLE IF EXISTS `ACT_ID_INFO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_ID_INFO` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD_` longblob DEFAULT NULL,
  `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_ID_INFO`
--

LOCK TABLES `ACT_ID_INFO` WRITE;
/*!40000 ALTER TABLE `ACT_ID_INFO` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_ID_INFO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_ID_MEMBERSHIP`
--

DROP TABLE IF EXISTS `ACT_ID_MEMBERSHIP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_ID_MEMBERSHIP` (
  `USER_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`USER_ID_`,`GROUP_ID_`),
  KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_MEMB_GROUP` FOREIGN KEY (`GROUP_ID_`) REFERENCES `ACT_ID_GROUP` (`ID_`),
  CONSTRAINT `ACT_FK_MEMB_USER` FOREIGN KEY (`USER_ID_`) REFERENCES `ACT_ID_USER` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_ID_MEMBERSHIP`
--

LOCK TABLES `ACT_ID_MEMBERSHIP` WRITE;
/*!40000 ALTER TABLE `ACT_ID_MEMBERSHIP` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_ID_MEMBERSHIP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_ID_PRIV`
--

DROP TABLE IF EXISTS `ACT_ID_PRIV`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_ID_PRIV` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_PRIV_NAME` (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_ID_PRIV`
--

LOCK TABLES `ACT_ID_PRIV` WRITE;
/*!40000 ALTER TABLE `ACT_ID_PRIV` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_ID_PRIV` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_ID_PRIV_MAPPING`
--

DROP TABLE IF EXISTS `ACT_ID_PRIV_MAPPING`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_ID_PRIV_MAPPING` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PRIV_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_PRIV_MAPPING` (`PRIV_ID_`),
  KEY `ACT_IDX_PRIV_USER` (`USER_ID_`),
  KEY `ACT_IDX_PRIV_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_PRIV_MAPPING` FOREIGN KEY (`PRIV_ID_`) REFERENCES `ACT_ID_PRIV` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_ID_PRIV_MAPPING`
--

LOCK TABLES `ACT_ID_PRIV_MAPPING` WRITE;
/*!40000 ALTER TABLE `ACT_ID_PRIV_MAPPING` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_ID_PRIV_MAPPING` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_ID_PROPERTY`
--

DROP TABLE IF EXISTS `ACT_ID_PROPERTY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_ID_PROPERTY` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL,
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_ID_PROPERTY`
--

LOCK TABLES `ACT_ID_PROPERTY` WRITE;
/*!40000 ALTER TABLE `ACT_ID_PROPERTY` DISABLE KEYS */;
INSERT INTO `ACT_ID_PROPERTY` VALUES ('schema.version','6.7.2.0',1);
/*!40000 ALTER TABLE `ACT_ID_PROPERTY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_ID_TOKEN`
--

DROP TABLE IF EXISTS `ACT_ID_TOKEN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_ID_TOKEN` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TOKEN_VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TOKEN_DATE_` timestamp(3) NOT NULL DEFAULT current_timestamp(3) ON UPDATE current_timestamp(3),
  `IP_ADDRESS_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_AGENT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TOKEN_DATA_` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_ID_TOKEN`
--

LOCK TABLES `ACT_ID_TOKEN` WRITE;
/*!40000 ALTER TABLE `ACT_ID_TOKEN` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_ID_TOKEN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_ID_USER`
--

DROP TABLE IF EXISTS `ACT_ID_USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_ID_USER` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `FIRST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DISPLAY_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PWD_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PICTURE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_ID_USER`
--

LOCK TABLES `ACT_ID_USER` WRITE;
/*!40000 ALTER TABLE `ACT_ID_USER` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_ID_USER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_PROCDEF_INFO`
--

DROP TABLE IF EXISTS `ACT_PROCDEF_INFO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_PROCDEF_INFO` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `INFO_JSON_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_IDX_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_INFO_JSON_BA` (`INFO_JSON_ID_`),
  CONSTRAINT `ACT_FK_INFO_JSON_BA` FOREIGN KEY (`INFO_JSON_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_INFO_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_PROCDEF_INFO`
--

LOCK TABLES `ACT_PROCDEF_INFO` WRITE;
/*!40000 ALTER TABLE `ACT_PROCDEF_INFO` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_PROCDEF_INFO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RE_DEPLOYMENT`
--

DROP TABLE IF EXISTS `ACT_RE_DEPLOYMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RE_DEPLOYMENT` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `DEPLOY_TIME_` timestamp(3) NULL DEFAULT NULL,
  `DERIVED_FROM_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DERIVED_FROM_ROOT_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_DEPLOYMENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ENGINE_VERSION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RE_DEPLOYMENT`
--

LOCK TABLES `ACT_RE_DEPLOYMENT` WRITE;
/*!40000 ALTER TABLE `ACT_RE_DEPLOYMENT` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RE_DEPLOYMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RE_MODEL`
--

DROP TABLE IF EXISTS `ACT_RE_MODEL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RE_MODEL` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`),
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`),
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_RE_DEPLOYMENT` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RE_MODEL`
--

LOCK TABLES `ACT_RE_MODEL` WRITE;
/*!40000 ALTER TABLE `ACT_RE_MODEL` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RE_MODEL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RE_PROCDEF`
--

DROP TABLE IF EXISTS `ACT_RE_PROCDEF`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RE_PROCDEF` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint(4) DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `ENGINE_VERSION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DERIVED_FROM_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DERIVED_FROM_ROOT_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DERIVED_VERSION_` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY_`,`VERSION_`,`DERIVED_VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RE_PROCDEF`
--

LOCK TABLES `ACT_RE_PROCDEF` WRITE;
/*!40000 ALTER TABLE `ACT_RE_PROCDEF` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RE_PROCDEF` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_ACTINST`
--

DROP TABLE IF EXISTS `ACT_RU_ACTINST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_ACTINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT 1,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `TRANSACTION_ORDER_` int(11) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_RU_ACTI_START` (`START_TIME_`),
  KEY `ACT_IDX_RU_ACTI_END` (`END_TIME_`),
  KEY `ACT_IDX_RU_ACTI_PROC` (`PROC_INST_ID_`),
  KEY `ACT_IDX_RU_ACTI_PROC_ACT` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_RU_ACTI_EXEC` (`EXECUTION_ID_`),
  KEY `ACT_IDX_RU_ACTI_EXEC_ACT` (`EXECUTION_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_RU_ACTI_TASK` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_ACTINST`
--

LOCK TABLES `ACT_RU_ACTINST` WRITE;
/*!40000 ALTER TABLE `ACT_RU_ACTINST` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_ACTINST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_DEADLETTER_JOB`
--

DROP TABLE IF EXISTS `ACT_RU_DEADLETTER_JOB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_DEADLETTER_JOB` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ELEMENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ELEMENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CORRELATION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_DEADLETTER_JOB_EXCEPTION_STACK_ID` (`EXCEPTION_STACK_ID_`),
  KEY `ACT_IDX_DEADLETTER_JOB_CUSTOM_VALUES_ID` (`CUSTOM_VALUES_ID_`),
  KEY `ACT_IDX_DEADLETTER_JOB_CORRELATION_ID` (`CORRELATION_ID_`),
  KEY `ACT_IDX_DJOB_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_DJOB_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_DJOB_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_DEADLETTER_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_DEADLETTER_JOB`
--

LOCK TABLES `ACT_RU_DEADLETTER_JOB` WRITE;
/*!40000 ALTER TABLE `ACT_RU_DEADLETTER_JOB` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_DEADLETTER_JOB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_ENTITYLINK`
--

DROP TABLE IF EXISTS `ACT_RU_ENTITYLINK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_ENTITYLINK` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LINK_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ELEMENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REF_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REF_SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REF_SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ROOT_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ROOT_SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HIERARCHY_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_ENT_LNK_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`,`LINK_TYPE_`),
  KEY `ACT_IDX_ENT_LNK_ROOT_SCOPE` (`ROOT_SCOPE_ID_`,`ROOT_SCOPE_TYPE_`,`LINK_TYPE_`),
  KEY `ACT_IDX_ENT_LNK_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`,`LINK_TYPE_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_ENTITYLINK`
--

LOCK TABLES `ACT_RU_ENTITYLINK` WRITE;
/*!40000 ALTER TABLE `ACT_RU_ENTITYLINK` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_ENTITYLINK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_EVENT_SUBSCR`
--

DROP TABLE IF EXISTS `ACT_RU_EVENT_SUBSCR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_EVENT_SUBSCR` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_` timestamp(3) NOT NULL DEFAULT current_timestamp(3),
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`),
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_EVENT_SUBSCR`
--

LOCK TABLES `ACT_RU_EVENT_SUBSCR` WRITE;
/*!40000 ALTER TABLE `ACT_RU_EVENT_SUBSCR` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_EVENT_SUBSCR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_EXECUTION`
--

DROP TABLE IF EXISTS `ACT_RU_EXECUTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_EXECUTION` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ROOT_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IS_ACTIVE_` tinyint(4) DEFAULT NULL,
  `IS_CONCURRENT_` tinyint(4) DEFAULT NULL,
  `IS_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_MI_ROOT_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `CACHED_ENT_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IS_COUNT_ENABLED_` tinyint(4) DEFAULT NULL,
  `EVT_SUBSCR_COUNT_` int(11) DEFAULT NULL,
  `TASK_COUNT_` int(11) DEFAULT NULL,
  `JOB_COUNT_` int(11) DEFAULT NULL,
  `TIMER_JOB_COUNT_` int(11) DEFAULT NULL,
  `SUSP_JOB_COUNT_` int(11) DEFAULT NULL,
  `DEADLETTER_JOB_COUNT_` int(11) DEFAULT NULL,
  `EXTERNAL_WORKER_JOB_COUNT_` int(11) DEFAULT NULL,
  `VAR_COUNT_` int(11) DEFAULT NULL,
  `ID_LINK_COUNT_` int(11) DEFAULT NULL,
  `CALLBACK_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CALLBACK_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REFERENCE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REFERENCE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROPAGATED_STAGE_INST_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_STATUS_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_IDC_EXEC_ROOT` (`ROOT_PROC_INST_ID_`),
  KEY `ACT_IDX_EXEC_REF_ID_` (`REFERENCE_ID_`),
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
  KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE CASCADE,
  CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_EXECUTION`
--

LOCK TABLES `ACT_RU_EXECUTION` WRITE;
/*!40000 ALTER TABLE `ACT_RU_EXECUTION` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_EXECUTION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_EXTERNAL_JOB`
--

DROP TABLE IF EXISTS `ACT_RU_EXTERNAL_JOB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_EXTERNAL_JOB` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ELEMENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ELEMENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CORRELATION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXTERNAL_JOB_EXCEPTION_STACK_ID` (`EXCEPTION_STACK_ID_`),
  KEY `ACT_IDX_EXTERNAL_JOB_CUSTOM_VALUES_ID` (`CUSTOM_VALUES_ID_`),
  KEY `ACT_IDX_EXTERNAL_JOB_CORRELATION_ID` (`CORRELATION_ID_`),
  KEY `ACT_IDX_EJOB_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_EJOB_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_EJOB_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  CONSTRAINT `ACT_FK_EXTERNAL_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_EXTERNAL_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_EXTERNAL_JOB`
--

LOCK TABLES `ACT_RU_EXTERNAL_JOB` WRITE;
/*!40000 ALTER TABLE `ACT_RU_EXTERNAL_JOB` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_EXTERNAL_JOB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_HISTORY_JOB`
--

DROP TABLE IF EXISTS `ACT_RU_HISTORY_JOB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_HISTORY_JOB` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ADV_HANDLER_CFG_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_HISTORY_JOB`
--

LOCK TABLES `ACT_RU_HISTORY_JOB` WRITE;
/*!40000 ALTER TABLE `ACT_RU_HISTORY_JOB` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_HISTORY_JOB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_IDENTITYLINK`
--

DROP TABLE IF EXISTS `ACT_RU_IDENTITYLINK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_IDENTITYLINK` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
  KEY `ACT_IDX_IDENT_LNK_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_IDENT_LNK_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_IDENT_LNK_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`),
  CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `ACT_RU_TASK` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_IDENTITYLINK`
--

LOCK TABLES `ACT_RU_IDENTITYLINK` WRITE;
/*!40000 ALTER TABLE `ACT_RU_IDENTITYLINK` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_IDENTITYLINK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_JOB`
--

DROP TABLE IF EXISTS `ACT_RU_JOB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_JOB` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ELEMENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ELEMENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CORRELATION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_JOB_EXCEPTION_STACK_ID` (`EXCEPTION_STACK_ID_`),
  KEY `ACT_IDX_JOB_CUSTOM_VALUES_ID` (`CUSTOM_VALUES_ID_`),
  KEY `ACT_IDX_JOB_CORRELATION_ID` (`CORRELATION_ID_`),
  KEY `ACT_IDX_JOB_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_JOB_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_JOB_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_JOB`
--

LOCK TABLES `ACT_RU_JOB` WRITE;
/*!40000 ALTER TABLE `ACT_RU_JOB` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_JOB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_SUSPENDED_JOB`
--

DROP TABLE IF EXISTS `ACT_RU_SUSPENDED_JOB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_SUSPENDED_JOB` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ELEMENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ELEMENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CORRELATION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_SUSPENDED_JOB_EXCEPTION_STACK_ID` (`EXCEPTION_STACK_ID_`),
  KEY `ACT_IDX_SUSPENDED_JOB_CUSTOM_VALUES_ID` (`CUSTOM_VALUES_ID_`),
  KEY `ACT_IDX_SUSPENDED_JOB_CORRELATION_ID` (`CORRELATION_ID_`),
  KEY `ACT_IDX_SJOB_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_SJOB_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_SJOB_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_SUSPENDED_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_SUSPENDED_JOB`
--

LOCK TABLES `ACT_RU_SUSPENDED_JOB` WRITE;
/*!40000 ALTER TABLE `ACT_RU_SUSPENDED_JOB` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_SUSPENDED_JOB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_TASK`
--

DROP TABLE IF EXISTS `ACT_RU_TASK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_TASK` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROPAGATED_STAGE_INST_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DELEGATION_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  `IS_COUNT_ENABLED_` tinyint(4) DEFAULT NULL,
  `VAR_COUNT_` int(11) DEFAULT NULL,
  `ID_LINK_COUNT_` int(11) DEFAULT NULL,
  `SUB_TASK_COUNT_` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
  KEY `ACT_IDX_TASK_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_TASK_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_TASK_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_TASK`
--

LOCK TABLES `ACT_RU_TASK` WRITE;
/*!40000 ALTER TABLE `ACT_RU_TASK` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_TASK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_TIMER_JOB`
--

DROP TABLE IF EXISTS `ACT_RU_TIMER_JOB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_TIMER_JOB` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ELEMENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ELEMENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CORRELATION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TIMER_JOB_EXCEPTION_STACK_ID` (`EXCEPTION_STACK_ID_`),
  KEY `ACT_IDX_TIMER_JOB_CUSTOM_VALUES_ID` (`CUSTOM_VALUES_ID_`),
  KEY `ACT_IDX_TIMER_JOB_CORRELATION_ID` (`CORRELATION_ID_`),
  KEY `ACT_IDX_TIMER_JOB_DUEDATE` (`DUEDATE_`),
  KEY `ACT_IDX_TJOB_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_TJOB_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_TJOB_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_TIMER_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_TIMER_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_TIMER_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_TIMER_JOB`
--

LOCK TABLES `ACT_RU_TIMER_JOB` WRITE;
/*!40000 ALTER TABLE `ACT_RU_TIMER_JOB` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_TIMER_JOB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_VARIABLE`
--

DROP TABLE IF EXISTS `ACT_RU_VARIABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_VARIABLE` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_RU_VAR_SCOPE_ID_TYPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_RU_VAR_SUB_ID_TYPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_VARIABLE`
--

LOCK TABLES `ACT_RU_VARIABLE` WRITE;
/*!40000 ALTER TABLE `ACT_RU_VARIABLE` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_VARIABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FLW_CHANNEL_DEFINITION`
--

DROP TABLE IF EXISTS `FLW_CHANNEL_DEFINITION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FLW_CHANNEL_DEFINITION` (
  `ID_` varchar(255) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `KEY_` varchar(255) DEFAULT NULL,
  `CATEGORY_` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `TENANT_ID_` varchar(255) DEFAULT NULL,
  `RESOURCE_NAME_` varchar(255) DEFAULT NULL,
  `DESCRIPTION_` varchar(255) DEFAULT NULL,
  `TYPE_` varchar(255) DEFAULT NULL,
  `IMPLEMENTATION_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_IDX_CHANNEL_DEF_UNIQ` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FLW_CHANNEL_DEFINITION`
--

LOCK TABLES `FLW_CHANNEL_DEFINITION` WRITE;
/*!40000 ALTER TABLE `FLW_CHANNEL_DEFINITION` DISABLE KEYS */;
/*!40000 ALTER TABLE `FLW_CHANNEL_DEFINITION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FLW_EVENT_DEFINITION`
--

DROP TABLE IF EXISTS `FLW_EVENT_DEFINITION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FLW_EVENT_DEFINITION` (
  `ID_` varchar(255) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `KEY_` varchar(255) DEFAULT NULL,
  `CATEGORY_` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) DEFAULT NULL,
  `TENANT_ID_` varchar(255) DEFAULT NULL,
  `RESOURCE_NAME_` varchar(255) DEFAULT NULL,
  `DESCRIPTION_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_IDX_EVENT_DEF_UNIQ` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FLW_EVENT_DEFINITION`
--

LOCK TABLES `FLW_EVENT_DEFINITION` WRITE;
/*!40000 ALTER TABLE `FLW_EVENT_DEFINITION` DISABLE KEYS */;
/*!40000 ALTER TABLE `FLW_EVENT_DEFINITION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FLW_EVENT_DEPLOYMENT`
--

DROP TABLE IF EXISTS `FLW_EVENT_DEPLOYMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FLW_EVENT_DEPLOYMENT` (
  `ID_` varchar(255) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `CATEGORY_` varchar(255) DEFAULT NULL,
  `DEPLOY_TIME_` datetime(3) DEFAULT NULL,
  `TENANT_ID_` varchar(255) DEFAULT NULL,
  `PARENT_DEPLOYMENT_ID_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FLW_EVENT_DEPLOYMENT`
--

LOCK TABLES `FLW_EVENT_DEPLOYMENT` WRITE;
/*!40000 ALTER TABLE `FLW_EVENT_DEPLOYMENT` DISABLE KEYS */;
/*!40000 ALTER TABLE `FLW_EVENT_DEPLOYMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FLW_EVENT_RESOURCE`
--

DROP TABLE IF EXISTS `FLW_EVENT_RESOURCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FLW_EVENT_RESOURCE` (
  `ID_` varchar(255) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) DEFAULT NULL,
  `RESOURCE_BYTES_` longblob DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FLW_EVENT_RESOURCE`
--

LOCK TABLES `FLW_EVENT_RESOURCE` WRITE;
/*!40000 ALTER TABLE `FLW_EVENT_RESOURCE` DISABLE KEYS */;
/*!40000 ALTER TABLE `FLW_EVENT_RESOURCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FLW_EV_DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `FLW_EV_DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FLW_EV_DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FLW_EV_DATABASECHANGELOG`
--

LOCK TABLES `FLW_EV_DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `FLW_EV_DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `FLW_EV_DATABASECHANGELOG` VALUES ('1','flowable','org/flowable/eventregistry/db/liquibase/flowable-eventregistry-db-changelog.xml','2022-09-08 02:36:23',1,'EXECUTED','8:1b0c48c9cf7945be799d868a2626d687','createTable tableName=FLW_EVENT_DEPLOYMENT; createTable tableName=FLW_EVENT_RESOURCE; createTable tableName=FLW_EVENT_DEFINITION; createIndex indexName=ACT_IDX_EVENT_DEF_UNIQ, tableName=FLW_EVENT_DEFINITION; createTable tableName=FLW_CHANNEL_DEFIN...','',NULL,'4.3.5',NULL,NULL,'0683830120'),('2','flowable','org/flowable/eventregistry/db/liquibase/flowable-eventregistry-db-changelog.xml','2022-09-08 02:36:23',2,'EXECUTED','8:0ea825feb8e470558f0b5754352b9cda','addColumn tableName=FLW_CHANNEL_DEFINITION; addColumn tableName=FLW_CHANNEL_DEFINITION','',NULL,'4.3.5',NULL,NULL,'0683830120'),('3','flowable','org/flowable/eventregistry/db/liquibase/flowable-eventregistry-db-changelog.xml','2022-09-08 02:36:23',3,'EXECUTED','8:3c2bb293350b5cbe6504331980c9dcee','customChange','',NULL,'4.3.5',NULL,NULL,'0683830120');
/*!40000 ALTER TABLE `FLW_EV_DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FLW_EV_DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `FLW_EV_DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FLW_EV_DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FLW_EV_DATABASECHANGELOGLOCK`
--

LOCK TABLES `FLW_EV_DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `FLW_EV_DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `FLW_EV_DATABASECHANGELOGLOCK` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `FLW_EV_DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FLW_RU_BATCH`
--

DROP TABLE IF EXISTS `FLW_RU_BATCH`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FLW_RU_BATCH` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin NOT NULL,
  `SEARCH_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SEARCH_KEY2_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) NOT NULL,
  `COMPLETE_TIME_` datetime(3) DEFAULT NULL,
  `STATUS_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `BATCH_DOC_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FLW_RU_BATCH`
--

LOCK TABLES `FLW_RU_BATCH` WRITE;
/*!40000 ALTER TABLE `FLW_RU_BATCH` DISABLE KEYS */;
/*!40000 ALTER TABLE `FLW_RU_BATCH` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FLW_RU_BATCH_PART`
--

DROP TABLE IF EXISTS `FLW_RU_BATCH_PART`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FLW_RU_BATCH_PART` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `BATCH_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin NOT NULL,
  `SCOPE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SEARCH_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SEARCH_KEY2_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) NOT NULL,
  `COMPLETE_TIME_` datetime(3) DEFAULT NULL,
  `STATUS_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `RESULT_DOC_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `FLW_IDX_BATCH_PART` (`BATCH_ID_`),
  CONSTRAINT `FLW_FK_BATCH_PART_PARENT` FOREIGN KEY (`BATCH_ID_`) REFERENCES `FLW_RU_BATCH` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FLW_RU_BATCH_PART`
--

LOCK TABLES `FLW_RU_BATCH_PART` WRITE;
/*!40000 ALTER TABLE `FLW_RU_BATCH_PART` DISABLE KEYS */;
/*!40000 ALTER TABLE `FLW_RU_BATCH_PART` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Table structure for table `core_logs_archive`
--

DROP TABLE IF EXISTS `core_logs_archive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_logs_archive` (
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
) ENGINE=InnoDB AUTO_INCREMENT=383 DEFAULT CHARSET=utf8mb4 COMMENT='日志审计表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_logs_archive`
--

LOCK TABLES `core_logs_archive` WRITE;
/*!40000 ALTER TABLE `core_logs_archive` DISABLE KEYS */;
/*!40000 ALTER TABLE `core_logs_archive` ENABLE KEYS */;
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
INSERT INTO `uaa_authority` VALUES (31,1552836803759710216,1,'2022-07-29 12:00:16',NULL),(32,1552836803759710216,3,'2022-07-29 12:00:16',NULL),(33,1552836803759710216,2,'2022-07-29 12:00:16',NULL),(34,1552219847201390592,1,'2022-07-29 12:01:55',NULL),(35,1552219847201390592,1,'2022-07-29 12:01:55',NULL),(36,1552219847201390592,2,'2022-07-29 12:01:55',NULL),(37,1552219847201390592,3,'2022-07-29 12:01:55',NULL),(38,1552219847201390592,4,'2022-07-29 12:01:55',NULL);
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
  `menu_name` varchar(255) NOT NULL COMMENT '菜单名称',
  `menu_type` varchar(255) NOT NULL DEFAULT 'MENU' COMMENT '菜单、按钮、接口',
  `permission_expr` varchar(255) DEFAULT NULL COMMENT '权限表达式',
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uaa_menu`
--

LOCK TABLES `uaa_menu` WRITE;
/*!40000 ALTER TABLE `uaa_menu` DISABLE KEYS */;
INSERT INTO `uaa_menu` VALUES (1,NULL,'控制面板','MENU',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2022-07-28 16:21:33','2022-09-15 15:30:29','/dashboard',NULL,NULL),(2,NULL,'内容管理系统','MENU',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2022-07-28 17:01:05','2022-09-15 14:35:48','#',NULL,NULL),(3,2,'内容列表','MENU','cms:post:query','',NULL,NULL,NULL,NULL,NULL,1,'2022-07-28 17:01:26','2022-09-15 14:35:50','/dashboard/cms/posts',NULL,NULL),(4,2,'内容分类','MENU','cms:category:query',NULL,NULL,NULL,NULL,NULL,NULL,1,'2022-07-28 17:07:31','2022-09-15 14:35:51','/dashboard/cms/category',NULL,NULL),(5,NULL,'用户账户与认证','MENU',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2022-07-28 17:09:22','2022-09-15 14:35:52','#',NULL,NULL),(6,5,'用户账号管理','MENU',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2022-07-28 17:09:47','2022-09-15 14:35:54','/dashboard/uaa/user',NULL,NULL),(7,5,'用户权限管理','MENU',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2022-07-28 17:10:07','2022-09-15 14:35:55','/dashboard/uaa/perm',NULL,NULL),(8,5,'角色管理','MENU',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2022-07-28 17:10:24','2022-09-15 14:35:56','/dashboard/uaa/role',NULL,NULL),(9,5,'菜单管理','MENU',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2022-07-28 17:10:43','2022-09-15 14:35:58','/dashboard/uaa/menu',NULL,NULL),(10,NULL,'系统设置','MENU',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2022-07-28 17:10:52','2022-09-15 14:35:59','#',NULL,NULL),(11,3,'新建内容','BUTTON','cms:post:create',NULL,NULL,NULL,NULL,NULL,NULL,1,'2022-09-15 11:45:59','2022-09-15 14:36:01','#',NULL,NULL),(12,3,'修改内容','BUTTON','cms:post:update',NULL,NULL,NULL,NULL,NULL,NULL,1,'2022-09-15 15:11:34',NULL,'#',NULL,NULL),(13,3,'删除内容','BUTTON','cms:post:delete',NULL,NULL,NULL,NULL,NULL,NULL,1,'2022-09-15 15:11:58',NULL,'#',NULL,NULL),(14,3,'下线内容','BUTTON','cms:post:offline',NULL,NULL,NULL,NULL,NULL,NULL,1,'2022-09-15 15:12:25',NULL,'#',NULL,NULL),(15,4,'创建分类','BUTTON','cms:category:create',NULL,NULL,NULL,NULL,NULL,NULL,1,'2022-09-15 15:13:14',NULL,'#',NULL,NULL),(16,4,'修改分类','BUTTON','cms:category:update',NULL,NULL,NULL,NULL,NULL,NULL,1,'2022-09-15 15:13:39',NULL,'#',NULL,NULL),(17,4,'删除分类','BUTTON','cms:category:delete',NULL,NULL,NULL,NULL,NULL,NULL,1,'2022-09-15 15:33:10',NULL,'#',NULL,NULL);
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

-- Dump completed on 2022-09-08 10:41:14
