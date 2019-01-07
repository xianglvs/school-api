-- MySQL dump 10.16  Distrib 10.3.9-MariaDB, for Win64 (AMD64)
--
-- Host: 192.168.99.100    Database: school
-- ------------------------------------------------------
-- Server version	5.7.24

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
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '唯一编码',
  `title` varchar(255) NOT NULL COMMENT '文章标题',
  `content` text NOT NULL COMMENT '文章内容',
  `description` varchar(512) DEFAULT NULL COMMENT '文章概述',
  `sort` int(11) DEFAULT NULL COMMENT '排序号',
  `create_by` varchar(63) NOT NULL COMMENT '创建用户',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(63) NOT NULL COMMENT '最后更新用户',
  `update_date` datetime NOT NULL COMMENT '最后更新时间',
  `disable_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '禁用标志 0.否 1.是',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志 0.正常 1.删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `article_UN` (`title`,`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES ('1',' sit amet, consectetur adipiscing elit, sed do ','rum.Lorem ipsum dolor sit amet, con',' esse cillum dolore eu fugiat nulla pariatur. Exc',22731,'met, consectetur adipiscing elit, sed d','1990-12-29 12:41:00','ui officia deserunt m','1996-12-01 12:20:08',-125,-8),('2','uis aute irure dolor in reprehenderit i','t. Duis aute irure dolor in reprehenderit in voluptat','dolor in reprehenderi',24469,'nisi ut aliquip ex ea commodo consequat. Duis aute irure','1989-03-30 10:05:21',' in reprehenderit in voluptate velit esse cillum dol','2007-06-23 18:55:44',50,-38);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_category`
--

DROP TABLE IF EXISTS `article_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_category` (
  `article_id` varchar(64) NOT NULL COMMENT '文章唯一编码',
  `category_id` varchar(64) NOT NULL COMMENT '分类唯一编码',
  PRIMARY KEY (`article_id`,`category_id`),
  KEY `article_category_category_fk` (`category_id`),
  CONSTRAINT `article_category_article_fk` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
  CONSTRAINT `article_category_category_fk` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章分类关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_category`
--

LOCK TABLES `article_category` WRITE;
/*!40000 ALTER TABLE `article_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '唯一编码',
  `title` varchar(255) NOT NULL COMMENT '名称',
  `parent_id` varchar(63) NOT NULL DEFAULT '0' COMMENT '上级分类id',
  `parent_ids` varchar(511) DEFAULT NULL COMMENT '上级分类索引用,号分割',
  `create_by` varchar(63) NOT NULL COMMENT '创建用户',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(63) NOT NULL COMMENT '最后更新用户',
  `update_date` datetime NOT NULL COMMENT '最后更新时间',
  `disable_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '禁用标志 0.否 1.是',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志 0.正常 1.删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_UN` (`title`,`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `description_element`
--

DROP TABLE IF EXISTS `description_element`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `description_element` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '唯一编码',
  `description` varchar(511) NOT NULL COMMENT '信息描述',
  PRIMARY KEY (`id`),
  CONSTRAINT `description_element_general_element_fk` FOREIGN KEY (`id`) REFERENCES `general_element` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='带描述模块详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `description_element`
--

LOCK TABLES `description_element` WRITE;
/*!40000 ALTER TABLE `description_element` DISABLE KEYS */;
/*!40000 ALTER TABLE `description_element` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `general_element`
--

DROP TABLE IF EXISTS `general_element`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `general_element` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '唯一编码',
  `index_element_id` varchar(64) NOT NULL COMMENT '所属首页模块',
  `title` varchar(255) NOT NULL COMMENT '名称',
  `url` varchar(511) NOT NULL COMMENT '访问地址',
  `sort` int(11) DEFAULT NULL COMMENT '排序号',
  `create_by` varchar(63) NOT NULL COMMENT '创建用户',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(63) NOT NULL COMMENT '最后更新用户',
  `update_date` datetime NOT NULL COMMENT '最后更新时间',
  `disable_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '禁用状态 0.否 1.是',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志 0.正常 1.删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `general_element_UN` (`index_element_id`,`title`,`del_flag`),
  KEY `general_element_index_element_fk` (`index_element_id`),
  CONSTRAINT `general_element_index_element_fk` FOREIGN KEY (`index_element_id`) REFERENCES `index_element` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公共模块详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `general_element`
--

LOCK TABLES `general_element` WRITE;
/*!40000 ALTER TABLE `general_element` DISABLE KEYS */;
/*!40000 ALTER TABLE `general_element` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image_element`
--

DROP TABLE IF EXISTS `image_element`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image_element` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '唯一编码',
  `image_url` varchar(511) NOT NULL COMMENT '图片地址',
  PRIMARY KEY (`id`),
  CONSTRAINT `image_element_general_element_fk` FOREIGN KEY (`id`) REFERENCES `general_element` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='带图片模块详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image_element`
--

LOCK TABLES `image_element` WRITE;
/*!40000 ALTER TABLE `image_element` DISABLE KEYS */;
/*!40000 ALTER TABLE `image_element` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `index_element`
--

DROP TABLE IF EXISTS `index_element`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `index_element` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '唯一标识',
  `type` tinyint(4) NOT NULL COMMENT '模块类型\r\n100.置顶多列文字\r\n101.多列文字\r\n200.多列图片文字\r\n202.双列图片文字\r\n300.多列图片\r\n301.单列图片\r\n400.文字描述\r\n500.单列图片文字描述',
  `title` varchar(255) NOT NULL COMMENT '模块名称',
  `url` varchar(511) DEFAULT NULL COMMENT '访问地址',
  `sort` varchar(100) DEFAULT NULL COMMENT '排序号',
  `create_by` varchar(63) NOT NULL COMMENT '创建用户',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(63) NOT NULL COMMENT '最后更新用户',
  `update_date` datetime NOT NULL COMMENT '最后更新时间',
  `disable_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '禁用标志 0.否 1是',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志 0.正常 1.删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_element_UN` (`type`,`title`,`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='首页模块';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `index_element`
--

LOCK TABLES `index_element` WRITE;
/*!40000 ALTER TABLE `index_element` DISABLE KEYS */;
/*!40000 ALTER TABLE `index_element` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'school'
--
