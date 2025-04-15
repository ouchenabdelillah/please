-- MySQL dump 10.16  Distrib 10.1.26-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: db
-- ------------------------------------------------------
-- Server version	10.1.26-MariaDB-0+deb9u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking` (
  `booking_id` tinyint(4) DEFAULT NULL,
  `customer_id` tinyint(4) DEFAULT NULL,
  `booking_room` varchar(4) DEFAULT NULL,
  `guests` tinyint(4) DEFAULT NULL,
  `check_in` bigint(20) DEFAULT NULL,
  `check_out` bigint(20) DEFAULT NULL,
  `booking_type` varchar(16) DEFAULT NULL,
  `has_checked_out` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (24,4,'A101',2,1439537365,1440173817,'On Spot',0),(25,26,'A201',2,1439796741,1440174705,'On Spot',1),(26,39,'A301',2,1439796768,1440174486,'On Spot',1),(27,35,'A401',2,1440315213,1440174554,'On Spot',1),(28,34,'A101',2,1440180748,1440174176,'On Spot',1),(29,40,'A201',2,1440400451,1440173969,'On Spot',1),(30,26,'A101',3,1469332731,1469505609,'On Spot',0),(31,4,'A501',2,1469419721,1469160671,'On Spot',1),(32,42,'A101',3,1548423207,1548682588,'Paid reservation',0),(33,42,'A201',3,1548423207,1548682588,'Paid reservation',0),(34,42,'A301',3,1548423207,1548682588,'Paid reservation',0);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food` (
  `food_id` tinyint(4) DEFAULT NULL,
  `name` varchar(6) DEFAULT NULL,
  `price` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'burger',100);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `item_id` tinyint(4) DEFAULT NULL,
  `name` varchar(7) DEFAULT NULL,
  `description` varchar(33) DEFAULT NULL,
  `price` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (2,'Swiming','Get access to Swimming for 1 hour',200);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderItem`
--

DROP TABLE IF EXISTS `orderItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderItem` (
  `order_id` tinyint(4) DEFAULT NULL,
  `booking_id` tinyint(4) DEFAULT NULL,
  `item_food` varchar(6) DEFAULT NULL,
  `price` smallint(6) DEFAULT NULL,
  `quantity` tinyint(4) DEFAULT NULL,
  `total` mediumint(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderItem`
--

LOCK TABLES `orderItem` WRITE;
/*!40000 ALTER TABLE `orderItem` DISABLE KEYS */;
INSERT INTO `orderItem` VALUES (1,25,'burger',100,2,200),(3,27,'burger',100,2,200),(5,28,'A101',1500,0,0),(6,27,'A401',1500,2,3000),(7,26,'A301',1500,4,6000),(8,25,'A201',1500,4,6000),(9,24,'A101',1500,7,10500),(10,29,'A201',1500,2,3000),(11,28,'A101',1500,0,0),(12,26,'A301',1500,4,6000),(13,27,'A401',1500,1,1500),(14,25,'A201',1500,4,6000),(15,31,'burger',100,3,300),(16,31,'A501',1500,2,3000);
/*!40000 ALTER TABLE `orderItem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `room_id` tinyint(4) DEFAULT NULL,
  `room_no` varchar(4) DEFAULT NULL,
  `bed_number` tinyint(4) DEFAULT NULL,
  `tv` varchar(4) DEFAULT NULL,
  `wifi` varchar(4) DEFAULT NULL,
  `gizer` varchar(5) DEFAULT NULL,
  `phone` varchar(5) DEFAULT NULL,
  `room_class` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (13,'A101',2,'true','true','false','false','Standard'),(14,'A201',2,'true','true','false','false','Standard'),(15,'A301',2,'true','true','false','false','Standard'),(16,'A401',2,'true','true','false','false','Standard'),(17,'A501',2,'true','true','false','false','Standard');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomType`
--

DROP TABLE IF EXISTS `roomType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roomType` (
  `type` varchar(8) DEFAULT NULL,
  `price` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomType`
--

LOCK TABLES `roomType` WRITE;
/*!40000 ALTER TABLE `roomType` DISABLE KEYS */;
INSERT INTO `roomType` VALUES ('deluxe',3000),('Standard',1500),('Suite',5000);
/*!40000 ALTER TABLE `roomType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sqlite_sequence`
--

DROP TABLE IF EXISTS `sqlite_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sqlite_sequence` (
  `name` varchar(9) DEFAULT NULL,
  `seq` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sqlite_sequence`
--

LOCK TABLES `sqlite_sequence` WRITE;
/*!40000 ALTER TABLE `sqlite_sequence` DISABLE KEYS */;
INSERT INTO `sqlite_sequence` VALUES ('food',1),('item',2),('userInfo',42),('orderItem',16),('booking',34),('room',17);
/*!40000 ALTER TABLE `sqlite_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userInfo`
--

DROP TABLE IF EXISTS `userInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userInfo` (
  `user_id` tinyint(4) DEFAULT NULL,
  `name` varchar(13) DEFAULT NULL,
  `address` varchar(11) DEFAULT NULL,
  `phone` bigint(20) DEFAULT NULL,
  `type` varchar(7) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userInfo`
--

LOCK TABLES `userInfo` WRITE;
/*!40000 ALTER TABLE `userInfo` DISABLE KEYS */;
INSERT INTO `userInfo` VALUES (4,'Faysal','Wari',1913,'regular'),(26,'Akib Anwar','Rajshahi',120540342,'premium'),(27,'Rakibul Islam','Banani',123214325,'regular'),(28,'Nahian','Farmgate',112312,'null'),(29,'Samiul Islam','Motijheel',123456,'null'),(30,'sid','boshundhora',12321321,'null'),(31,'rizwan','mogbazar',34523,'null'),(33,'sunnat','donia',5465,'null'),(34,'tahmid','mmmm',12321,'null'),(35,'murad','nooooosfda',3242341,'null'),(36,'Tomal','dsf',321,'null'),(37,'sazzu','fsda',32121,'null'),(39,'Md Babul','nobabStreet',123456,'null'),(40,'parshi','narinda',12211,'null'),(42,'Tony','California',9998876665,'null');
/*!40000 ALTER TABLE `userInfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-22 15:20:26
