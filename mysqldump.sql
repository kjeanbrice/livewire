-- MySQL dump 10.13  Distrib 5.7.15, for Win64 (x86_64)
--
-- Host: localhost    Database: project
-- ------------------------------------------------------
-- Server version	5.7.15-log

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
-- Table structure for table `advertisement_data`
--

DROP TABLE IF EXISTS `advertisement_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisement_data` (
  `advertisement_id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL,
  `date_of_ad` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `type_of_ad` varchar(50) NOT NULL,
  `company` varchar(50) NOT NULL,
  `item_name` varchar(50) NOT NULL,
  `content` longtext NOT NULL,
  `unit_price` decimal(4,2) NOT NULL,
  `num_available` int(11) NOT NULL,
  PRIMARY KEY (`advertisement_id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `advertisement_data_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee_data` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisement_data`
--

LOCK TABLES `advertisement_data` WRITE;
/*!40000 ALTER TABLE `advertisement_data` DISABLE KEYS */;
INSERT INTO `advertisement_data` VALUES (1,5,'2016-11-20 01:20:45','Quality Assurance','Microsoft','Seroquel','tempor augue ac ipsum.',34.88,420),(2,3,'2017-02-10 05:42:29','Legal Department','Yahoo','Alprazolam','neque tellus,',61.53,46),(3,2,'2016-11-22 04:47:56','Customer Service','Google','Lisinopril','ut, sem. Nulla interdum. Curabitur dictum. Phasellus in',47.83,622),(4,4,'2015-12-19 08:08:03','Public Relations','Chami','Lisinopril','lacinia at, iaculis quis, pede. Praesent eu',62.07,263),(5,9,'2016-03-18 14:55:44','Finances','Finale','Alprazolam','laoreet posuere, enim nisl elementum purus,',85.81,787),(6,3,'2017-02-27 18:52:06','Sales and Marketing','Google','Warfarin Sodium','dictum eu, placerat eget, venenatis a, magna. Lorem ipsum',22.56,470),(7,1,'2016-11-24 00:12:06','Quality Assurance','Apple Systems','Naproxen','elit. Etiam',21.10,649),(8,5,'2017-10-04 00:17:58','Public Relations','Sibelius','Lyrica','nec orci. Donec nibh.',72.40,573),(9,4,'2017-04-25 16:41:42','Asset Management','Apple Systems','Simvastatin','dictum ultricies',75.28,139),(10,10,'2017-06-17 07:40:35','Finances','Microsoft','Tramadol HCl','arcu.',7.66,2);
/*!40000 ALTER TABLE `advertisement_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_data`
--

DROP TABLE IF EXISTS `comment_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment_data` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `comment_content` varchar(250) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`),
  KEY `user_id` (`user_id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `comment_data_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_data_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `post_data` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_data`
--

LOCK TABLES `comment_data` WRITE;
/*!40000 ALTER TABLE `comment_data` DISABLE KEYS */;
INSERT INTO `comment_data` VALUES (1,3,6,'at pede. Cras vulputate velit eu','2017-10-10 12:33:15'),(2,5,3,'mi enim, condimentum','2016-01-14 22:37:49'),(3,6,9,'non, egestas a, dui. Cras pellentesque. Sed dictum. Proin eget','2015-12-12 04:36:36'),(4,4,1,'mollis dui, in sodales','2016-06-22 03:43:02'),(5,4,4,'accumsan laoreet ipsum. Curabitur consequat, lectus sit amet luctus vulputate,','2017-03-06 14:33:09'),(6,10,6,'mattis semper,','2016-04-18 08:37:41'),(7,8,2,'sed dolor. Fusce mi lorem, vehicula et, rutrum eu, ultrices','2017-04-26 12:22:57'),(8,9,4,'diam. Proin dolor.','2016-02-28 07:25:29'),(9,9,1,'non arcu. Vivamus sit','2017-11-03 12:51:31'),(10,3,5,'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices','2016-10-12 17:18:36');
/*!40000 ALTER TABLE `comment_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_data`
--

DROP TABLE IF EXISTS `employee_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_data` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `social` bigint(20) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(2) NOT NULL,
  `zipcode` int(11) NOT NULL,
  `telephone` bigint(20) NOT NULL,
  `startDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `hourly` decimal(4,2) NOT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `social` (`social`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_data`
--

LOCK TABLES `employee_data` WRITE;
/*!40000 ALTER TABLE `employee_data` DISABLE KEYS */;
INSERT INTO `employee_data` VALUES (1,1648080447699,'Salinas','Jameson','P.O. Box 660, 6034 Diam. Rd.','Montignies-sur-Sambre','DL',52621,8666219373,'2016-09-13 07:57:18',15.77),(2,1660071493699,'Higgins','Basil','740-7570 Malesuada Rd.','Lakewood','GR',77156,9915025975,'2017-07-04 22:10:25',35.83),(3,1610011740399,'Padilla','Mikayla','P.O. Box 258, 2949 Dui, Road','Bridgnorth','NU',6983,2857832620,'2016-10-17 00:05:01',35.09),(4,1676111774799,'Carlson','Dawn','322-3021 Non, Av.','Sagrada Familia','FZ',15364,9895765627,'2016-03-28 00:51:52',29.72),(5,1633051535399,'Hull','Robert','P.O. Box 940, 192 Non Street','Hamilton','EZ',81539,3619343738,'2017-09-29 02:16:57',88.54),(6,1622031330699,'Mosley','Teagan','Ap #909-585 Vitae Av.','Melton Mowbray','OZ',40406,5126848284,'2015-12-17 14:09:20',91.16),(7,1652062041899,'Mejia','Giacomo','1930 Lectus St.','Mandela','GQ',8035,8326712605,'2017-06-26 12:11:41',71.25),(8,1629040670599,'Cameron','Courtney','996-8363 Et, Road','Colico','MZ',36726,2278457852,'2017-02-02 11:30:35',9.51),(9,1691090430399,'Watson','Fay','2249 Metus. Rd.','Carluke','KN',4053,1842894446,'2016-10-12 23:41:09',8.38),(10,1681091438799,'Hood','Oliver','P.O. Box 262, 2055 Vestibulum Rd.','St. Veit an der Glan','WP',42780,7017659414,'2017-02-05 19:28:53',1.99);
/*!40000 ALTER TABLE `employee_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_data`
--

DROP TABLE IF EXISTS `group_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_data` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `group_name` varchar(50) NOT NULL,
  `group_type` varchar(50) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`group_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `group_data_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_data`
--

LOCK TABLES `group_data` WRITE;
/*!40000 ALTER TABLE `group_data` DISABLE KEYS */;
INSERT INTO `group_data` VALUES (1,10,'dolor egestas rhoncus. Proin','organization','2017-01-25 05:00:00'),(2,8,'lorem, luctus ut, pellentesque','club','2017-10-23 04:00:00'),(3,7,'Donec egestas. Aliquam nec','club','2017-08-14 04:00:00'),(4,10,'Phasellus dapibus','organization','2017-05-24 04:00:00'),(5,10,'ultrices. Vivamus rhoncus. Donec','club','2017-09-04 04:00:00'),(6,7,'a nunc. In','business','2016-03-10 05:00:00'),(7,1,'Cras sed leo.','business','2016-06-11 04:00:00'),(8,6,'sit amet, consectetuer adipiscing','club','2017-10-21 04:00:00'),(9,2,'faucibus lectus, a','organization','2016-04-13 04:00:00'),(10,7,'nisl sem, consequat nec,','business','2016-05-28 04:00:00');
/*!40000 ALTER TABLE `group_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_members`
--

DROP TABLE IF EXISTS `group_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_members` (
  `group_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `join_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`group_id`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `group_members_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `group_members_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `group_data` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_members`
--

LOCK TABLES `group_members` WRITE;
/*!40000 ALTER TABLE `group_members` DISABLE KEYS */;
INSERT INTO `group_members` VALUES (1,6,'2016-06-02 05:31:08'),(2,8,'2016-11-16 23:59:46'),(3,4,'2016-11-08 16:29:26'),(4,4,'2017-05-29 02:14:42'),(5,3,'2015-11-24 19:47:40'),(6,9,'2016-10-20 18:17:50'),(7,3,'2016-03-02 17:25:40'),(8,5,'2016-01-24 22:53:13'),(9,3,'2017-04-29 13:26:59'),(10,4,'2016-11-29 06:12:32');
/*!40000 ALTER TABLE `group_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `liked_comments`
--

DROP TABLE IF EXISTS `liked_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `liked_comments` (
  `comment_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  KEY `comment_id` (`comment_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `liked_comments_ibfk_1` FOREIGN KEY (`comment_id`) REFERENCES `comment_data` (`comment_id`),
  CONSTRAINT `liked_comments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liked_comments`
--

LOCK TABLES `liked_comments` WRITE;
/*!40000 ALTER TABLE `liked_comments` DISABLE KEYS */;
INSERT INTO `liked_comments` VALUES (10,9),(6,2),(9,7),(7,7),(5,3),(7,2),(8,4),(4,2),(8,5),(9,3);
/*!40000 ALTER TABLE `liked_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `liked_posts`
--

DROP TABLE IF EXISTS `liked_posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `liked_posts` (
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  KEY `post_id` (`post_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `liked_posts_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post_data` (`post_id`),
  CONSTRAINT `liked_posts_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liked_posts`
--

LOCK TABLES `liked_posts` WRITE;
/*!40000 ALTER TABLE `liked_posts` DISABLE KEYS */;
INSERT INTO `liked_posts` VALUES (3,6),(5,2),(9,1),(7,4),(4,4),(9,4),(9,8),(3,10),(7,3),(9,5);
/*!40000 ALTER TABLE `liked_posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages_data`
--

DROP TABLE IF EXISTS `messages_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages_data` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `message_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `message_subject` varchar(249) DEFAULT NULL,
  `message_content` varchar(511) DEFAULT NULL,
  `message_sender` int(11) NOT NULL,
  `message_receiver` int(11) NOT NULL,
  PRIMARY KEY (`message_id`),
  KEY `message_sender` (`message_sender`),
  KEY `message_receiver` (`message_receiver`),
  CONSTRAINT `messages_data_ibfk_1` FOREIGN KEY (`message_sender`) REFERENCES `user_data` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `messages_data_ibfk_2` FOREIGN KEY (`message_receiver`) REFERENCES `user_data` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages_data`
--

LOCK TABLES `messages_data` WRITE;
/*!40000 ALTER TABLE `messages_data` DISABLE KEYS */;
INSERT INTO `messages_data` VALUES (1,'2017-05-12 11:42:59','montes, nascetur ridiculus mus. Proin vel','lacinia. Sed',10,8),(2,'2017-08-01 23:01:30','tellus. Aenean egestas hendrerit neque. In ornare','eleifend non, dapibus rutrum, justo. Praesent luctus. Curabitur',9,10),(3,'2017-11-05 08:09:39','nunc sed libero. Proin sed turpis nec mauris','nec orci. Donec nibh. Quisque nonummy ipsum non arcu.',10,2),(4,'2016-06-21 17:50:06','Quisque libero lacus,','posuere at, velit. Cras',4,2),(5,'2016-10-31 19:00:35','convallis, ante lectus','Mauris vestibulum, neque sed dictum eleifend, nunc risus varius',7,7),(6,'2016-03-19 08:58:21','posuere vulputate, lacus. Cras interdum. Nunc sollicitudin commodo ipsum.','Pellentesque ut ipsum ac mi eleifend egestas.',5,10),(7,'2015-11-18 03:19:20','ac libero nec','dolor, nonummy ac, feugiat non,',3,2),(8,'2016-01-05 07:53:16','Phasellus dolor elit, pellentesque','sed',1,4),(9,'2016-05-25 22:27:38','enim. Etiam','sem mollis dui,',3,2),(10,'2016-07-20 08:03:23','montes, nascetur ridiculus','fringilla, porttitor vulputate, posuere vulputate,',2,9);
/*!40000 ALTER TABLE `messages_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `page_data`
--

DROP TABLE IF EXISTS `page_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `page_data` (
  `page_id` int(11) NOT NULL AUTO_INCREMENT,
  `page_owner` int(11) DEFAULT NULL,
  `associated_group` int(11) DEFAULT NULL,
  `post_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`page_id`),
  KEY `page_owner` (`page_owner`),
  KEY `associated_group` (`associated_group`),
  CONSTRAINT `page_data_ibfk_1` FOREIGN KEY (`page_owner`) REFERENCES `user_data` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `page_data_ibfk_2` FOREIGN KEY (`associated_group`) REFERENCES `group_data` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `page_data`
--

LOCK TABLES `page_data` WRITE;
/*!40000 ALTER TABLE `page_data` DISABLE KEYS */;
INSERT INTO `page_data` VALUES (1,4,1,11),(2,6,4,48),(3,4,2,73),(4,3,4,51),(5,8,1,89),(6,5,7,12),(7,5,4,4),(8,2,7,78),(9,8,5,9),(10,6,6,63);
/*!40000 ALTER TABLE `page_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_data`
--

DROP TABLE IF EXISTS `post_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_data` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `dateOfPost` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `content` longtext,
  `comment_count` int(11) DEFAULT NULL,
  `page_id` int(11) NOT NULL,
  PRIMARY KEY (`post_id`),
  KEY `user_id` (`user_id`),
  KEY `page_id` (`page_id`),
  CONSTRAINT `post_data_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `post_data_ibfk_2` FOREIGN KEY (`page_id`) REFERENCES `page_data` (`page_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_data`
--

LOCK TABLES `post_data` WRITE;
/*!40000 ALTER TABLE `post_data` DISABLE KEYS */;
INSERT INTO `post_data` VALUES (1,4,'2016-11-19 02:45:43','urna. Vivamus molestie dapibus ligula.',25,8),(2,1,'2016-11-19 02:45:43','tincidunt dui augue eu tellus. Phasellus elit pede, malesuada vel,',8,7),(3,3,'2016-11-19 02:45:43','lobortis ultrices. Vivamus rhoncus. Donec est. Nunc ullamcorper,',91,10),(4,7,'2016-11-19 02:45:43','consequat dolor vitae dolor. Donec',0,2),(5,2,'2016-11-19 02:45:43','et magnis dis parturient montes, nascetur',31,9),(6,6,'2016-11-19 02:45:43','nec, eleifend non, dapibus rutrum, justo. Praesent luctus. Curabitur',72,9),(7,7,'2016-11-19 02:45:43','elit, a feugiat',87,9),(8,4,'2016-11-19 02:45:43','commodo tincidunt nibh. Phasellus nulla. Integer vulputate, risus a',15,5),(9,4,'2016-11-19 02:45:43','egestas',52,6),(10,9,'2016-11-19 02:45:43','ultricies dignissim',3,1);
/*!40000 ALTER TABLE `post_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale_data`
--

DROP TABLE IF EXISTS `sale_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sale_data` (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `ad_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `consumer_id` int(11) NOT NULL,
  `transaction_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `number_of_units` int(11) NOT NULL,
  `account_number` bigint(20) NOT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `seller_id` (`seller_id`),
  KEY `consumer_id` (`consumer_id`),
  KEY `ad_id` (`ad_id`),
  KEY `account_number` (`account_number`),
  CONSTRAINT `sale_data_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `user_data` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sale_data_ibfk_2` FOREIGN KEY (`consumer_id`) REFERENCES `user_data` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sale_data_ibfk_3` FOREIGN KEY (`ad_id`) REFERENCES `advertisement_data` (`advertisement_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sale_data_ibfk_4` FOREIGN KEY (`account_number`) REFERENCES `user_data` (`account_number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_data`
--

LOCK TABLES `sale_data` WRITE;
/*!40000 ALTER TABLE `sale_data` DISABLE KEYS */;
INSERT INTO `sale_data` VALUES (1,9,10,2,'2017-10-13 20:45:35',810,1682041888499),(2,5,5,7,'2017-10-07 22:11:57',984,1629072077699),(3,6,6,10,'2016-10-04 23:14:45',482,1638092104499),(4,9,9,7,'2017-05-10 07:39:21',605,1638092104499),(5,4,4,8,'2016-01-23 20:12:53',596,1682041888499),(6,7,8,6,'2016-06-20 13:12:08',393,1665102589699),(7,7,8,1,'2017-08-11 16:59:44',780,1638092104499),(8,9,10,10,'2016-11-26 21:39:21',201,1673022297399),(9,5,4,8,'2017-07-29 01:35:24',679,1613081568599),(10,1,5,2,'2017-09-02 01:38:45',244,1603010561899);
/*!40000 ALTER TABLE `sale_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_data`
--

DROP TABLE IF EXISTS `user_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_data` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(30) DEFAULT NULL,
  `first_name` varchar(30) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` char(2) DEFAULT NULL,
  `zip_code` int(11) DEFAULT NULL,
  `telephone` bigint(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `account_number` bigint(20) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `credit_card` bigint(20) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `account_number` (`account_number`),
  UNIQUE KEY `telephone` (`telephone`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_data`
--

LOCK TABLES `user_data` WRITE;
/*!40000 ALTER TABLE `user_data` DISABLE KEYS */;
INSERT INTO `user_data` VALUES (1,'Mcdonald','Walker','Ap #754-7702 Consequat Road','Marcq-en-Baroeul','TM',9850,15952573248,'vitae@quis.org',1684060889999,'2016-09-21 04:00:00',4615246025226042,1),(2,'Hull','Alexa','8735 Sed Street','Kemzeke','VZ',59524,18647952786,'mattis.Cras.eget@euerat.net',1613081568599,'2017-03-25 04:00:00',4024007158120583,6),(3,'Campos','Brandon','751-9568 Nunc Rd.','Mataró','SQ',14047,12576755014,'et.netus.et@libero.ca',1673022297399,'2017-04-15 04:00:00',4532163910514,9),(4,'Barnett','Olivia','734-4507 Metus. St.','Gonnosnò','LO',692,15332334787,'neque.et@cubilia.org',1638092104499,'2016-03-07 05:00:00',4556624469661,2),(5,'Hurst','Baker','Ap #730-1110 Nulla Ave','Vergemoli','XW',43659,18363321045,'eros.Proin.ultrices@risusDonec.edu',1672082752599,'2016-07-16 04:00:00',4929512094216,2),(6,'Sullivan','Mufutau','Ap #719-2196 Fermentum Av.','Ancud','VC',50653,12066358623,'Proin.non.massa@egestasSedpharetra.net',1665102589699,'2017-01-22 05:00:00',4929594918688,5),(7,'Nielsen','Zephania','3606 Enim Street','Nemi','CI',6616,12747719202,'lorem@euismodet.edu',1629072077699,'2016-12-10 05:00:00',4974482571895832,6),(8,'Fischer','Nero','3368 Diam St.','Montpelier','QY',2556,11651335108,'placerat@est.com',1603010561899,'2016-11-03 04:00:00',4485136405888148,10),(9,'Mack','Castor','4806 In St.','Merchtem','AM',11802,16274104750,'luctus.et.ultrices@acnulla.net',1666011115399,'2017-03-08 05:00:00',4316900960603209,7),(10,'Oliver','Roary','3620 Sed Av.','Khanpur','CY',49887,18265068335,'condimentum.Donec.at@sed.co.uk',1682041888499,'2017-08-23 04:00:00',4716502176672436,3);
/*!40000 ALTER TABLE `user_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_preferences`
--

DROP TABLE IF EXISTS `user_preferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_preferences` (
  `user_id` int(11) NOT NULL,
  `preference` varchar(511) DEFAULT NULL,
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_preferences_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_preferences`
--

LOCK TABLES `user_preferences` WRITE;
/*!40000 ALTER TABLE `user_preferences` DISABLE KEYS */;
INSERT INTO `user_preferences` VALUES (9,'sandwiches, noodles, pies, desserts, seafood, cereals, salads, pasta, stews, soups'),(8,'sandwiches, cereals, noodles, pasta, salads, pies, stews'),(5,'salads, seafood, cereals, soups, sandwiches, pies'),(5,'soups, noodles, pasta, desserts, salads, cereals, stews, seafood, sandwiches, pies'),(3,'cereals, pasta, pies, sandwiches'),(9,'noodles, desserts, seafood'),(1,'sandwiches, desserts, cereals, soups, stews, pies, salads, seafood'),(1,'cereals, noodles, sandwiches, pasta, pies'),(6,'sandwiches, salads, seafood, cereals, stews, pies, pasta'),(8,'cereals, sandwiches, pies, soups, noodles, stews, salads, seafood');
/*!40000 ALTER TABLE `user_preferences` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-18 22:08:15
