-- MySQL dump 10.13  Distrib 8.0.28, for macos11 (x86_64)
--
-- Host: 127.0.0.1    Database: quizapp
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `administrators`
--

DROP TABLE IF EXISTS `administrators`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrators` (
  `id` bigint NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `user_role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrators`
--

LOCK TABLES `administrators` WRITE;
/*!40000 ALTER TABLE `administrators` DISABLE KEYS */;
INSERT INTO `administrators` VALUES (15,'admin','admin','admin','admin','administrator');
/*!40000 ALTER TABLE `administrators` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (302);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `options` (
  `id` bigint NOT NULL,
  `option_text` varchar(255) DEFAULT NULL,
  `question_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5bmv46so2y5igt9o9n9w4fh6y` (`question_id`),
  CONSTRAINT `FK5bmv46so2y5igt9o9n9w4fh6y` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES (1,'minerals',1),(2,'fun!',1),(3,'protection',1),(4,'food',1),(5,'DNA',2),(6,'RNA',2),(7,'BMA',2),(8,'Genes',2),(9,'Hibiscus',3),(10,'Coconut',3),(11,'Mango',3),(12,'Mint',3),(171,'111',NULL),(172,'2222',NULL),(173,'3333',NULL),(174,'4444',NULL),(220,'Eyes',219),(221,'Hands',219),(222,'Nose',219),(223,'Ear',219),(225,'30',224),(226,'89',224),(227,'79',224),(228,'54',224),(230,'51',229),(231,'151',229),(232,'15',229),(233,'115',229),(235,'60',234),(236,'50',234),(237,'70',234),(238,'80',234),(242,'10',241),(243,'20',241),(244,'33',241),(245,'40',241),(247,'2',246),(248,'1',246),(249,'1.2',246),(250,'2.1',246),(252,'1',251),(253,'2',251),(254,'3',251),(255,'4',251),(257,'ant',256),(258,'box',256),(259,'cup',256),(260,'bin',256),(262,'old-gold',261),(263,'ate-late',261),(264,' note-book',261),(265,'eat-beat',261),(267,'Cups',266),(268,'Cup',266),(269,'Cupk',266),(270,'Upck',266),(272,'an ant',271),(273,'an egg',271),(274,'an eagle',271),(275,'an ball',271),(277,'Hot',276),(278,'Magma',276),(279,'Lava',276),(280,'Fire',276),(282,'Celsius',281),(283,'Fahrenheit',281),(284,'Kelvin',281),(285,'Rankine',281),(287,'C multiplied by square of M',286),(288,'M multiplied by square of C',286),(289,'M multiplied by cube of C',286),(290,'C multiplied by cube of M',286),(292,'power',291),(293,'none',291),(294,'energy',291),(295,'force',291);
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `question_id` bigint NOT NULL,
  `question_title` varchar(255) NOT NULL,
  `quiz_id` bigint DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `option_correct` varchar(255) NOT NULL,
  `option_chosen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `FKhu7bacopenb4tq8fwpqq4mluy` (`quiz_id`),
  CONSTRAINT `FKhu7bacopenb4tq8fwpqq4mluy` FOREIGN KEY (`quiz_id`) REFERENCES `quizes` (`quiz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,'Plants catch insect to get ___________',1,'science','minerals',NULL),(2,'Which among the following is not present in pure sugar ?',1,'science','Genes',NULL),(3,'Which of the following plant is a herb, choose correct option?',1,'science','Mint',NULL),(219,'We can write with help of ?',NULL,'science','Hands',NULL),(224,'Which number is greater than 80?',NULL,'maths','89',NULL),(229,'Write ‘fifteen’ in numeral',NULL,'maths','151',NULL),(234,' Complete the sequence: 10 , 20 , 30 , 40, ___',NULL,'maths','50',NULL),(241,'Find the odd one:',NULL,'maths','33',NULL),(246,'What is 22/11 ?',NULL,'maths','2',NULL),(251,'How may ears do we have?',NULL,'science','2',NULL),(256,' Which word starts with a vowel?',NULL,'english','ant',NULL),(261,'Which set of words is not rhyming?',NULL,'english',' note-book',NULL),(266,' What is the singular of ‘CUPS’ ?',NULL,'english','Cup',NULL),(271,'Which of the following is incorrect ?',NULL,'english','an ball',NULL),(276,'What is the opposite of ‘cold’ ?',NULL,'english','Hot',NULL),(281,'Which among the following temperature scale is based upon absolute zero? [A] Celsius',NULL,'physics','Kelvin',NULL),(286,'E = ?',NULL,'physics','M multiplied by square of C',NULL),(291,'Gravity is a ____',NULL,'physics','force',NULL);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quizes`
--

DROP TABLE IF EXISTS `quizes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quizes` (
  `quiz_id` bigint NOT NULL,
  `subject` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`quiz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quizes`
--

LOCK TABLES `quizes` WRITE;
/*!40000 ALTER TABLE `quizes` DISABLE KEYS */;
INSERT INTO `quizes` VALUES (1,'science'),(2,'maths'),(3,'english'),(4,'physics'),(22,'science'),(46,'science'),(48,'science'),(59,'science'),(61,'science'),(63,'science'),(65,'science'),(67,'science'),(69,'science'),(71,'science'),(73,'science'),(75,'science'),(77,'science'),(79,'science'),(81,'science'),(83,'science'),(85,'science'),(87,'science'),(89,'science'),(91,'science'),(93,'science'),(95,'science'),(97,'science'),(99,'science'),(101,'science'),(103,'science'),(105,'science'),(127,'maths'),(129,'physics'),(131,'english'),(133,'science'),(135,'science'),(137,'science'),(139,'science'),(141,'science'),(143,'science'),(145,'science'),(147,'science'),(149,'maths'),(151,'maths'),(153,'english'),(155,'physics'),(157,'science'),(159,'science'),(181,'science'),(188,'maths'),(190,'maths'),(192,'maths'),(195,'science'),(202,'science'),(204,'science'),(206,'science'),(210,'science'),(212,'science'),(214,'science'),(216,'science'),(218,'science'),(240,'science'),(297,'maths'),(299,'maths'),(301,'english');
/*!40000 ALTER TABLE `quizes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessions`
--

DROP TABLE IF EXISTS `sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sessions` (
  `id` bigint NOT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `score` int DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `session_id` bigint DEFAULT NULL,
  `Learner_id` bigint DEFAULT NULL,
  `user_role` varchar(255) DEFAULT NULL,
  `current_Learner_id` varchar(255) DEFAULT NULL,
  `Learner_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhoi03gle6p9oioyfkjfcygecl` (`session_id`),
  KEY `FK5px2wfvd6mo5o51mlt6wt1m2k` (`Learner_id`),
  CONSTRAINT `FK5px2wfvd6mo5o51mlt6wt1m2k` FOREIGN KEY (`Learner_id`) REFERENCES `Learners` (`id`),
  CONSTRAINT `FKhoi03gle6p9oioyfkjfcygecl` FOREIGN KEY (`session_id`) REFERENCES `quizes` (`quiz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessions`
--

LOCK TABLES `sessions` WRITE;
/*!40000 ALTER TABLE `sessions` DISABLE KEYS */;
INSERT INTO `sessions` VALUES (23,NULL,0,NULL,22,18,NULL,NULL,NULL),(45,NULL,0,NULL,46,NULL,NULL,NULL,NULL),(47,NULL,0,NULL,48,NULL,NULL,NULL,NULL),(58,NULL,0,NULL,59,NULL,NULL,NULL,NULL),(60,NULL,0,NULL,61,NULL,NULL,'18',NULL),(62,'2022-02-13 19:42:16.828',3,'2022-02-13 19:42:10.792',63,NULL,NULL,'18',NULL),(64,'2022-02-13 19:54:09.344',1,'2022-02-13 19:54:03.191',65,NULL,NULL,'18',NULL),(66,'2022-02-13 20:42:08.422',2,'2022-02-13 20:41:54.809',67,NULL,NULL,'18',NULL),(68,'2022-02-13 20:44:53.837',0,'2022-02-13 20:44:46.602',69,NULL,NULL,'18',NULL),(70,'2022-02-13 20:45:00.906',1,'2022-02-13 20:44:56.766',71,NULL,NULL,'18',NULL),(72,NULL,0,'2022-02-13 20:45:09.27',73,NULL,NULL,'18',NULL),(74,NULL,0,'2022-02-13 20:45:19.032',75,NULL,NULL,'18',NULL),(76,NULL,0,'2022-02-13 20:45:22.705',77,NULL,NULL,'18',NULL),(78,NULL,0,'2022-02-13 20:48:23.933',79,NULL,NULL,'18',NULL),(80,NULL,0,'2022-02-13 20:48:26.615',81,NULL,NULL,'18',NULL),(82,NULL,0,'2022-02-13 20:48:53.791',83,NULL,NULL,'18',NULL),(84,NULL,0,'2022-02-13 20:49:43.349',85,NULL,NULL,'18',NULL),(86,NULL,0,'2022-02-13 20:52:01.317',87,NULL,NULL,'18',NULL),(88,'2022-02-13 20:54:05.322',0,'2022-02-13 20:54:02.511',89,NULL,NULL,'18',NULL),(90,'2022-02-13 20:54:22.09',1,'2022-02-13 20:54:12.932',91,NULL,NULL,'18',NULL),(92,'2022-02-13 20:54:28.15',2,'2022-02-13 20:54:24.466',93,NULL,NULL,'18',NULL),(94,'2022-02-13 20:54:32.364',1,'2022-02-13 20:54:29.582',95,NULL,NULL,'18',NULL),(96,NULL,0,'2022-02-13 20:54:36.065',97,NULL,NULL,'18',NULL),(98,'2022-02-13 20:54:41.108',1,'2022-02-13 20:54:39.369',99,NULL,NULL,'18',NULL),(100,NULL,0,'2022-02-13 20:54:58.401',101,NULL,NULL,'18',NULL),(102,'2022-02-13 20:55:15.99',0,'2022-02-13 20:55:13.252',103,NULL,NULL,'18',NULL),(104,'2022-02-13 21:17:34.929',0,'2022-02-13 21:17:27.187',105,NULL,NULL,'29','krishna'),(126,NULL,0,'2022-02-14 04:22:06.603',127,NULL,NULL,'18','Learner'),(128,NULL,0,'2022-02-14 04:22:12.929',129,NULL,NULL,'18','Learner'),(130,NULL,0,'2022-02-14 04:22:53.382',131,NULL,NULL,'18','Learner'),(132,'2022-02-14 04:35:16.983',2,'2022-02-14 04:34:57.336',133,NULL,NULL,'18','Learner'),(134,'2022-02-14 04:35:27.381',2,'2022-02-14 04:35:23.41',135,NULL,NULL,'18','Learner'),(136,'2022-02-14 06:56:34.2',0,'2022-02-14 06:56:26.058',137,NULL,NULL,'18','Learner'),(138,NULL,0,'2022-02-14 06:56:36.933',139,NULL,NULL,'18','Learner'),(140,NULL,0,'2022-02-14 07:02:05.238',141,NULL,NULL,'18','Learner'),(142,'2022-02-14 07:07:15.517',2,'2022-02-14 07:07:07.836',143,NULL,NULL,'18','Learner'),(144,NULL,0,'2022-02-14 07:26:24.937',145,NULL,NULL,'18','Learner'),(146,'2022-02-14 07:29:12.434',1,'2022-02-14 07:29:02.947',147,NULL,NULL,'18','Learner'),(148,'2022-02-14 07:29:20.585',0,'2022-02-14 07:29:19.23',149,NULL,NULL,'18','Learner'),(150,NULL,0,'2022-02-14 07:29:22.698',151,NULL,NULL,'18','Learner'),(152,NULL,0,'2022-02-14 07:29:25.247',153,NULL,NULL,'18','Learner'),(154,'2022-02-14 07:29:30.45',0,'2022-02-14 07:29:29.614',155,NULL,NULL,'18','Learner'),(156,'2022-02-14 15:20:58.974',3,'2022-02-14 15:20:51.608',157,NULL,NULL,'18','Learner'),(158,'2022-02-14 15:42:11.966',2,'2022-02-14 15:35:47.247',159,NULL,NULL,'18','Learner'),(180,NULL,0,'2022-02-15 03:12:26.713',181,NULL,NULL,'18','Learner'),(187,'2022-02-15 03:13:51.2',1,'2022-02-15 03:13:47.425',188,NULL,NULL,'18','Learner'),(189,NULL,0,'2022-02-15 03:13:54.769',190,NULL,NULL,'18','Learner'),(191,NULL,0,'2022-02-15 03:14:03.988',192,NULL,NULL,'18','Learner'),(194,'2022-02-15 13:04:48.203',2,'2022-02-15 13:04:13.693',195,NULL,NULL,'193','akshay123'),(201,'2022-02-15 13:07:51.379',4,'2022-02-15 13:07:35.508',202,NULL,NULL,'193','akshay123'),(203,NULL,0,'2022-02-15 13:09:56.25',204,NULL,NULL,'193','akshay123'),(205,NULL,0,'2022-02-15 13:10:20.614',206,NULL,NULL,'193','akshay123'),(209,'2022-02-15 13:22:27.674',0,'2022-02-15 13:22:19.258',210,NULL,NULL,'193','Subash123'),(211,'2022-02-15 13:29:49.798',0,'2022-02-15 13:26:39.153',212,NULL,NULL,'193','Subash123'),(213,NULL,0,'2022-02-16 08:13:32.576',214,NULL,NULL,'18','Learner'),(215,NULL,0,'2022-02-16 08:17:02.487',216,NULL,NULL,'18','Learner'),(217,NULL,0,'2022-02-16 08:19:42.552',218,NULL,NULL,'18','Learner'),(239,NULL,0,'2022-02-16 08:38:44.931',240,NULL,NULL,'18','Learner'),(296,'2022-02-16 08:57:38.438',4,'2022-02-16 08:57:22.02',297,NULL,NULL,'18','Learner'),(298,'2022-02-16 08:59:42.385',0,'2022-02-16 08:57:45.375',299,NULL,NULL,'18','Learner'),(300,'2022-02-16 09:00:13.921',5,'2022-02-16 08:59:53.59',301,NULL,NULL,'18','Learner');
/*!40000 ALTER TABLE `sessions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Learners`
--

DROP TABLE IF EXISTS `Learners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Learners` (
  `id` bigint NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `user_role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Learners`
--

LOCK TABLES `Learners` WRITE;
/*!40000 ALTER TABLE `Learners` DISABLE KEYS */;
INSERT INTO `Learners` VALUES (3,'Rajimko','Mano','rajimano','rajimano','Learner'),(4,'Sai Tej','Krishna','test','saikrishna','Learner'),(6,'Arun','Rathod','@burfi78','Arun_03','Learner'),(10,'anirudh','kaavi','#kjoiaye87','anukavi21','Learner'),(18,'Chaitanya','Vaddisriram','Learner','Learner','Learner'),(29,'Krishna','kannan','test','krishna','Learner'),(193,'Akshay','Subash','test','Subash123','Learner');
/*!40000 ALTER TABLE `Learners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Formers`
--

DROP TABLE IF EXISTS `Formers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Formers` (
  `id` bigint NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `user_role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Formers`
--

LOCK TABLES `Formers` WRITE;
/*!40000 ALTER TABLE `Formers` DISABLE KEYS */;
INSERT INTO `Formers` VALUES (16,'Former','Sanya','sanya','sanya','Former'),(30,'Former','B','Former','Former','Former');
/*!40000 ALTER TABLE `Formers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-16 14:32:24
