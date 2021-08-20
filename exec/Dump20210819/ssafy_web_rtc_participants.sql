-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 52.79.80.65    Database: ssafy_web_rtc
-- ------------------------------------------------------
-- Server version	8.0.26-0ubuntu0.20.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `participants`
--

DROP TABLE IF EXISTS `participants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participants` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code_id` varchar(255) DEFAULT NULL,
  `group_code` varchar(255) DEFAULT NULL,
  `room_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkh6lv68r8rtbkxb33384wu88y` (`code_id`),
  KEY `FKrj1mxeafdkldadjy6cxkjt380` (`group_code`),
  KEY `FK7tef5pn33nxufey94c0y7kayp` (`room_id`),
  KEY `FKghixrahoj1s8cloinfx8lyeqa` (`user_id`),
  CONSTRAINT `FK7tef5pn33nxufey94c0y7kayp` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`),
  CONSTRAINT `FKghixrahoj1s8cloinfx8lyeqa` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKkh6lv68r8rtbkxb33384wu88y` FOREIGN KEY (`code_id`) REFERENCES `code` (`code_id`),
  CONSTRAINT `FKrj1mxeafdkldadjy6cxkjt380` FOREIGN KEY (`group_code`) REFERENCES `group_code` (`group_code`)
) ENGINE=InnoDB AUTO_INCREMENT=242 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participants`
--

LOCK TABLES `participants` WRITE;
/*!40000 ALTER TABLE `participants` DISABLE KEYS */;
INSERT INTO `participants` VALUES (154,'001','00',37,16),(155,'100','00',37,14),(156,'000','00',37,15),(157,'000','00',37,17),(158,'000','00',37,18),(159,'001','00',38,16),(160,'100','00',38,14),(161,'000','00',38,15),(162,'000','00',38,17),(163,'000','00',38,18),(164,'001','00',39,16),(165,'100','00',39,14),(166,'100','00',39,15),(167,'100','00',39,17),(168,'100','00',39,18),(169,'001','00',40,16),(170,'100','00',40,14),(171,'100','00',40,15),(172,'100','00',40,17),(173,'100','00',40,18),(174,'001','00',41,16),(175,'100','00',41,14),(176,'100','00',41,15),(177,'100','00',41,17),(178,'100','00',41,18),(179,'001','00',42,16),(180,'100','00',42,14),(181,'100','00',42,15),(224,'001','00',43,17),(225,'100','00',43,14),(226,'100','00',43,15),(227,'100','00',43,16),(228,'100','00',43,18),(229,'100','00',43,24),(230,'100','00',43,23),(231,'001','00',56,15),(239,'001','00',59,17),(240,'001','00',60,17),(241,'100','00',60,25);
/*!40000 ALTER TABLE `participants` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-19 19:43:44
