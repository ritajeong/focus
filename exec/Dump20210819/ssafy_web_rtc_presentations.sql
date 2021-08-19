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
-- Table structure for table `presentations`
--

DROP TABLE IF EXISTS `presentations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `presentations` (
  `file_id` int NOT NULL AUTO_INCREMENT,
  `directory` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `original` varchar(255) DEFAULT NULL,
  `group_id` int DEFAULT NULL,
  PRIMARY KEY (`file_id`),
  KEY `FKrf32p4nht7t42wtwlxujbs1n0` (`group_id`),
  CONSTRAINT `FKrf32p4nht7t42wtwlxujbs1n0` FOREIGN KEY (`group_id`) REFERENCES `rooms_presentations` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=230 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presentations`
--

LOCK TABLES `presentations` WRITE;
/*!40000 ALTER TABLE `presentations` DISABLE KEYS */;
INSERT INTO `presentations` VALUES (175,'/home/ubuntu/presentations/43/18/1','1','1.jpg',83),(176,'/home/ubuntu/presentations/43/18/2','2','2.jpg',83),(177,'/home/ubuntu/presentations/43/18/3','3','3.jpg',83),(178,'/home/ubuntu/presentations/43/18/4','4','4.jpg',83),(179,'/home/ubuntu/presentations/43/18/5','5','5.jpg',83),(180,'/home/ubuntu/presentations/43/18/6','6','6.jpg',83),(181,'/home/ubuntu/presentations/43/18/7','7','7.jpg',83),(184,'/home/ubuntu/presentations/56/15/1','1','그림1.png',85),(185,'/home/ubuntu/presentations/43/15/1','1','그림1.png',86),(199,'/home/ubuntu/presentations/43/16/1','1','1.jpg',89),(200,'/home/ubuntu/presentations/43/16/2','2','2.jpg',89),(201,'/home/ubuntu/presentations/43/16/3','3','3.jpg',89),(202,'/home/ubuntu/presentations/43/16/4','4','4.jpg',89),(203,'/home/ubuntu/presentations/43/16/5','5','5.jpg',89),(204,'/home/ubuntu/presentations/43/16/6','6','6.jpg',89),(205,'/home/ubuntu/presentations/43/16/7','7','7.jpg',89),(206,'/home/ubuntu/presentations/43/16/8','8','8.png',89),(223,'/home/ubuntu/presentations/59/17/1','1','Vuejs.png',94),(224,'/home/ubuntu/presentations/59/17/2','2','focus.png',94),(225,'/home/ubuntu/presentations/59/17/3','3','대체불가.png',94),(226,'/home/ubuntu/presentations/60/17/1','1','로고 (4).png',95),(227,'/home/ubuntu/presentations/60/17/2','2','로고 (5).png',95),(228,'/home/ubuntu/presentations/60/17/3','3','로고 (6).png',95),(229,'/home/ubuntu/presentations/60/17/4','4','로고 (7).png',95);
/*!40000 ALTER TABLE `presentations` ENABLE KEYS */;
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
