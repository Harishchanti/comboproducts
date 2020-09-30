-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: catalog_management
-- ------------------------------------------------------
-- Server version	5.7.31-0ubuntu0.18.04.1

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
-- Table structure for table `catagory`
--

DROP TABLE IF EXISTS `catagory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catagory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `parent_Id` varchar(45) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `imgs_link` longtext,
  `returnable` bit(1) DEFAULT NULL,
  `created_at` datetime(3) DEFAULT NULL,
  `update_at` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1006 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catagory`
--

LOCK TABLES `catagory` WRITE;
/*!40000 ALTER TABLE `catagory` DISABLE KEYS */;
INSERT INTO `catagory` VALUES (1000,'Beverages',_binary '','0',1,'',_binary '\0','2020-09-29 13:49:42.000','2020-09-29 13:49:42.000'),(1001,'Milk',_binary '','1000',2,'',_binary '\0','2020-09-29 13:51:06.000','2020-09-29 13:51:06.000'),(1002,'Cookies & Rusk',_binary '','1000',2,NULL,_binary '\0','2020-09-29 13:54:56.000','2020-09-29 13:54:56.000'),(1003,'Meat',_binary '','0',1,NULL,_binary '','2020-09-29 14:06:04.000','2020-09-29 14:06:04.000'),(1004,'Chiken Meat',_binary '','1003',2,NULL,_binary '','2020-09-29 14:09:47.000','2020-09-29 14:09:47.000'),(1005,'Mutton Meat',_binary '','1003',2,NULL,_binary '','2020-09-29 14:12:14.000','2020-09-29 14:12:14.000');
/*!40000 ALTER TABLE `catagory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `combo_product_list`
--

DROP TABLE IF EXISTS `combo_product_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `combo_product_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `combo_id` bigint(20) DEFAULT NULL,
  `product_sku` varchar(45) DEFAULT NULL,
  `mrp` decimal(10,2) DEFAULT NULL,
  `created_at` datetime(3) DEFAULT NULL,
  `update_at` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_table1_1_idx` (`combo_id`),
  KEY `fk_combo_product_list_1_idx` (`product_sku`),
  CONSTRAINT `fk_combo_product_list_1` FOREIGN KEY (`product_sku`) REFERENCES `products` (`sku`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_1` FOREIGN KEY (`combo_id`) REFERENCES `combo_products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combo_product_list`
--

LOCK TABLES `combo_product_list` WRITE;
/*!40000 ALTER TABLE `combo_product_list` DISABLE KEYS */;
INSERT INTO `combo_product_list` VALUES (1,3,'LICIOUSPROD002',NULL,'2020-09-30 13:07:02.401','2020-09-30 13:07:02.401'),(2,3,'LICIOUSPROD003',NULL,'2020-09-30 13:07:02.417','2020-09-30 13:07:02.417'),(3,4,'LICIOUSPROD005',NULL,'2020-09-30 14:40:23.869','2020-09-30 14:40:23.869'),(4,4,'LICIOUSPROD001',NULL,'2020-09-30 14:40:23.872','2020-09-30 14:40:23.872');
/*!40000 ALTER TABLE `combo_product_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `combo_products`
--

DROP TABLE IF EXISTS `combo_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `combo_products` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `discription` longtext,
  `selling_price` decimal(10,2) DEFAULT NULL,
  `created_at` datetime(3) DEFAULT NULL,
  `update_at` datetime(3) DEFAULT NULL,
  `enabled` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combo_products`
--

LOCK TABLES `combo_products` WRITE;
/*!40000 ALTER TABLE `combo_products` DISABLE KEYS */;
INSERT INTO `combo_products` VALUES (3,'Chicken Frankfurters and Nestle Milkmaid Sweetened Condensed Milk',700.00,'2020-09-30 13:06:52.987','2020-09-30 13:06:52.987','1'),(4,'Lean goat curry and Milk Powder',500.00,'2020-09-30 14:40:23.838','2020-09-30 14:40:23.838','1');
/*!40000 ALTER TABLE `combo_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `line_items`
--

DROP TABLE IF EXISTS `line_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `line_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL,
  `product_sku` varchar(45) DEFAULT NULL,
  `mrp` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_line_items_1_idx` (`order_id`),
  CONSTRAINT `fk_line_items_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `line_items`
--

LOCK TABLES `line_items` WRITE;
/*!40000 ALTER TABLE `line_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `line_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_number` varchar(45) DEFAULT NULL,
  `buyer_name` varchar(45) DEFAULT NULL,
  `buyer_ph_number` varchar(45) DEFAULT NULL,
  `combo_id` bigint(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `total_amount` decimal(10,2) DEFAULT NULL,
  `final_amount` decimal(10,2) DEFAULT NULL,
  `discount_amount` decimal(10,2) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `created_at` datetime(3) DEFAULT NULL,
  `update_at` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'245769','Harish V','7411489557',3,1,700.00,700.00,NULL,'ORDER_PLACED','2020-09-30 14:35:09.369','2020-09-30 14:35:09.369'),(2,'139422','Harish V','7411489557',3,2,1400.00,1400.00,NULL,'ORDER_PLACED','2020-09-30 14:37:34.411','2020-09-30 14:37:34.411');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(512) DEFAULT NULL,
  `description` varchar(2048) DEFAULT NULL,
  `catagory_id` bigint(20) DEFAULT NULL,
  `sku` varchar(45) DEFAULT NULL,
  `imgs_link` longtext,
  `enabled` bit(1) DEFAULT NULL,
  `mrp` decimal(10,2) DEFAULT NULL,
  `selling _price` decimal(10,2) DEFAULT NULL,
  `created_at` datetime(3) NOT NULL,
  `update_at` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sku_UNIQUE` (`sku`),
  KEY `fk_products_1_idx` (`catagory_id`),
  CONSTRAINT `fk_products_catagory` FOREIGN KEY (`catagory_id`) REFERENCES `catagory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1000,'estle Everyday Milk Powder For Tea, 200 G Pouch','estle Everyday Milk Powder For Tea, 200 G Pouch',1002,'LICIOUSPROD001',NULL,_binary '',120.00,100.00,'2020-09-29 14:01:41.000','2020-09-29 14:01:41.000'),(1001,'Nestle Milkmaid Sweetened Condensed Milk, 400 G Tin','Nestle Milkmaid Sweetened Condensed Milk, 400 G Tin',1002,'LICIOUSPROD002',NULL,_binary '',130.00,110.00,'2020-09-29 14:04:03.000','2020-09-29 14:04:03.000'),(1002,'Chicken Frankfurters','Chicken Frankfurters',1004,'LICIOUSPROD003',NULL,_binary '',600.00,550.00,'2020-09-29 14:16:14.000','2020-09-29 14:16:14.000'),(1003,'Chicken - Curry','Chicken - Curry',1004,'LICIOUSPROD004',NULL,_binary '',500.00,450.00,'2020-09-29 14:18:46.000','2020-09-29 14:18:46.000'),(1004,'Lean goat curry cut','Lean goat curry cut',1005,'LICIOUSPROD005',NULL,_binary '',500.00,450.00,'2020-09-29 14:21:16.000','2020-09-29 14:21:16.000');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-30 14:41:07
