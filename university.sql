-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: university
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes` (
  `id` varchar(8) NOT NULL,
  `faculty_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `faculty1_id_idx` (`faculty_id`),
  CONSTRAINT `faculty1_id` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES ('DH20IT02','CNTT'),('SS','NN');
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes_subject`
--

DROP TABLE IF EXISTS `classes_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes_subject` (
  `id` int NOT NULL AUTO_INCREMENT,
  `subject_id` varchar(12) NOT NULL,
  `class_id` varchar(8) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `class_id_idx` (`class_id`),
  KEY `subject_id_idx` (`subject_id`),
  CONSTRAINT `class_id` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `subject_id` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes_subject`
--

LOCK TABLES `classes_subject` WRITE;
/*!40000 ALTER TABLE `classes_subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `classes_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_created` date NOT NULL,
  `user_id` int NOT NULL,
  `post_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `post_id_idx` (`post_id`),
  KEY `user6_id_idx` (`user_id`),
  CONSTRAINT `post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `user6_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (3,'Làm điều có ích','2023-08-08',83,6),(4,'Học ','2023-08-08',84,7),(5,'Làm gì đi','2023-08-08',84,6),(6,'Nội dung bình luận của bạn','2023-09-04',82,6),(7,'Phải học thôi','2023-09-04',82,7),(8,'sss','2023-09-06',81,12),(9,'alo','2023-09-06',81,12),(10,'Báo Hiếu','2023-09-06',81,12),(11,'test1','2023-09-06',81,12),(12,'a','2023-09-08',82,14),(13,'Hahaa','2023-09-14',81,6);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty` (
  `id` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES ('CNSH','Công nghệ sinh học '),('CNTT','Công nghệ thông tin'),('KINHTE','Kinh Tế'),('LUAT','Luật'),('NN','Ngoại ngữ');
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturer`
--

DROP TABLE IF EXISTS `lecturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecturer` (
  `id` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `birthday` date NOT NULL,
  `gender` tinyint NOT NULL,
  `phone` varchar(10) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `faculty_id` varchar(10) NOT NULL,
  `user_id` int NOT NULL,
  `classes_id` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `faculty_id_idx` (`faculty_id`),
  KEY `user_id_idx` (`user_id`),
  KEY `classes1_id_idx` (`classes_id`),
  CONSTRAINT `classes1_id` FOREIGN KEY (`classes_id`) REFERENCES `classes` (`id`),
  CONSTRAINT `faculty_id` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturer`
--

LOCK TABLES `lecturer` WRITE;
/*!40000 ALTER TABLE `lecturer` DISABLE KEYS */;
INSERT INTO `lecturer` VALUES ('GV2','Phương','2023-08-01',1,'0353894917','Nguyễn Kiệm','ngominhthanh8522@gmail.com','CNTT',23,NULL),('GV3','Dương Hữu Thành','2023-08-01',1,'0353894917','Nguyễn Kiệm','ngominhthanh8522@gmail.com','CNTT',82,'DH20IT02');
/*!40000 ALTER TABLE `lecturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturer_subject`
--

DROP TABLE IF EXISTS `lecturer_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecturer_subject` (
  `id` int NOT NULL AUTO_INCREMENT,
  `lecturer_id` varchar(10) NOT NULL,
  `subject_id` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `lecturer_id_idx` (`lecturer_id`),
  KEY `subject1_id_idx` (`subject_id`),
  CONSTRAINT `lecturer_id` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `subject1_id` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturer_subject`
--

LOCK TABLES `lecturer_subject` WRITE;
/*!40000 ALTER TABLE `lecturer_subject` DISABLE KEYS */;
INSERT INTO `lecturer_subject` VALUES (3,'GV3','CSLT'),(4,'GV3','KTLT'),(5,'GV3','OOP');
/*!40000 ALTER TABLE `lecturer_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `major` (
  `id` varchar(12) NOT NULL,
  `name` varchar(100) NOT NULL,
  `faculty_id` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `faculty2_id_idx` (`faculty_id`),
  CONSTRAINT `faculty2_id` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES ('MCNTT','Công nghệ thông tin','CNTT'),('MLUAT','Luật','LUAT'),('MNN','Ngôn ngữ Nhật','NN'),('NNT','Ngôn ngữ Trung','NN');
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `post_time` date NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user5_id_idx` (`user_id`),
  CONSTRAINT `user7_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (6,'Cách làm những điều có ích','Bước 1, bước 2','2022-12-01',81),(7,'Làm sao để được điểm cao','Học ','2022-12-01',82),(9,'Lỗi 400','Em bị lỗi 400, cho em hỏi bị lỗi gì ạ','2023-09-04',82),(10,'Lỗi 401','Em bị lỗi 401, cho em hỏi bị lỗi gì ạ','2023-09-04',82),(11,'sss','ssssssssssssss','2023-09-04',82),(12,'sss','ssssssssssssss','2023-09-04',82),(13,'Lỗi 401','Em bị lỗi 401, cho em hỏi bị lỗi gì ạ','2023-09-04',82),(14,'test1','test1','2023-09-04',82),(15,'test2','test thôi','2023-09-09',82),(16,'Lỗi 404','Em bị lỗi 404, cho em hỏi bị lỗi gì ạ','2023-09-14',81);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score` (
  `id` int NOT NULL AUTO_INCREMENT,
  `semester_id` varchar(5) NOT NULL,
  `student_subject_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `semester_id_idx` (`semester_id`),
  KEY `student_subject_id_idx` (`student_subject_id`),
  CONSTRAINT `semester_id` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `student_subject_id` FOREIGN KEY (`student_subject_id`) REFERENCES `student_subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (155,'12023',12),(156,'12023',14),(157,'12023',18),(158,'12023',19),(159,'32023',12),(161,'22023',13),(165,'32023',16),(166,'32023',17);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score_column`
--

DROP TABLE IF EXISTS `score_column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score_column` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score_column`
--

LOCK TABLES `score_column` WRITE;
/*!40000 ALTER TABLE `score_column` DISABLE KEYS */;
INSERT INTO `score_column` VALUES (1,'Quá trình'),(2,'Giữa kì'),(3,'Cuối kì'),(6,'Điểm cộng');
/*!40000 ALTER TABLE `score_column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score_value`
--

DROP TABLE IF EXISTS `score_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score_value` (
  `id` int NOT NULL AUTO_INCREMENT,
  `value` double NOT NULL,
  `score_id` int NOT NULL,
  `score_column_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `score_id_idx` (`score_id`),
  KEY `score_column_id_idx` (`score_column_id`),
  CONSTRAINT `score_column_id` FOREIGN KEY (`score_column_id`) REFERENCES `score_column` (`id`),
  CONSTRAINT `score_id` FOREIGN KEY (`score_id`) REFERENCES `score` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score_value`
--

LOCK TABLES `score_value` WRITE;
/*!40000 ALTER TABLE `score_value` DISABLE KEYS */;
INSERT INTO `score_value` VALUES (158,9,161,1),(159,8.5,165,1),(160,9,166,1);
/*!40000 ALTER TABLE `score_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semester` (
  `id` varchar(5) NOT NULL,
  `name` varchar(10) NOT NULL,
  `school_year` int NOT NULL,
  `from_date` date NOT NULL,
  `to_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES ('12023','Học kì 1',2023,'2023-01-12','2023-03-22'),('22023','Học kì 2',2023,'2023-03-28','2023-04-04'),('32023','Học kì 3',2023,'2023-06-06','2023-08-24');
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `birthday` date NOT NULL,
  `gender` tinyint NOT NULL,
  `phone` varchar(10) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `user_id` int NOT NULL,
  `classes_id` varchar(8) NOT NULL,
  `faculty_id` varchar(10) NOT NULL,
  `major_id` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user2_id_idx` (`user_id`),
  KEY `classes2_id_idx` (`classes_id`),
  KEY `faculty3_id_idx` (`faculty_id`),
  KEY `major_id_idx` (`major_id`),
  CONSTRAINT `classes2_id` FOREIGN KEY (`classes_id`) REFERENCES `classes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `faculty3_id` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `major_id` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user2_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('2051052000','Nguyễn Văn Minh','2020-12-30',1,'0353894917','Nguyễn Kiệm',83,'DH20IT02','CNTT','MCNTT'),('2051052001','Nguyễn Thị Bích Hằng','2020-12-30',1,'0353894917','Thủ Đức',84,'DH20IT02','CNTT','MCNTT'),('2051052054','Đoàn Gia Huy','2020-12-30',1,'0353894917','Nguyễn Kiệm',98,'DH20IT02','CNTT','MCNTT'),('2051052127','Ngô Minh Thành','2020-12-30',1,'0353894917','Nguyễn Kiệm',81,'DH20IT02','CNTT','MCNTT');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_subject`
--

DROP TABLE IF EXISTS `student_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_subject` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` varchar(10) NOT NULL,
  `subject_id` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `student1_id_idx` (`student_id`),
  KEY `subject3_id_idx` (`subject_id`),
  CONSTRAINT `student1_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `subject3_id` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_subject`
--

LOCK TABLES `student_subject` WRITE;
/*!40000 ALTER TABLE `student_subject` DISABLE KEYS */;
INSERT INTO `student_subject` VALUES (12,'2051052127','CSLT'),(13,'2051052127','KTLT'),(14,'2051052054','CSLT'),(15,'2051052054','KTLT'),(16,'2051052000','OOP'),(17,'2051052001','OOP'),(18,'2051052000','CSLT'),(19,'2051052001','CSLT');
/*!40000 ALTER TABLE `student_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `id` varchar(12) NOT NULL,
  `name` varchar(45) NOT NULL,
  `credit` int NOT NULL,
  `faculty_id` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `falculty4_id_idx` (`faculty_id`),
  CONSTRAINT `faculty4_id` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES ('CSDL','Cơ sở dữ liệu',4,'CNTT'),('CSLT','Cơ sở lập trình',4,'CNTT'),('KTLT','Kỹ thuật lập trình',4,'CNTT'),('LUATKT','Luật Kinh Tế',3,'KINHTE'),('OOP','Lập trình hướng đối tượng',4,'CNTT');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject_semester`
--

DROP TABLE IF EXISTS `subject_semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject_semester` (
  `id` int NOT NULL AUTO_INCREMENT,
  `subject_id` varchar(12) NOT NULL,
  `semester_id` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `subject4_id_idx` (`subject_id`),
  KEY `semester_id_idx` (`semester_id`),
  CONSTRAINT `semester1_id` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `subject4_id` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject_semester`
--

LOCK TABLES `subject_semester` WRITE;
/*!40000 ALTER TABLE `subject_semester` DISABLE KEYS */;
INSERT INTO `subject_semester` VALUES (5,'CSLT','12023'),(6,'KTLT','22023'),(7,'CSDL','32023'),(8,'OOP','32023');
/*!40000 ALTER TABLE `subject_semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `role` varchar(45) NOT NULL,
  `avatar` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (21,'giaovu','$2a$10$2y/TneiRaG5AsFMQ4hHhyeIrJ2TZ61ES3XQAFnY1JfVh8sX3flvj2','giaovu@gmail.com','ROLE_GIAOVU','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692353332/qtrxjqvonf8cdry6rias.png'),(22,'giangvien','$2a$10$JeqC18iX8DqIL.KnMj8/Teh4hFOYLF3QuRtsTWn//4y.fFgsr4muW','giangvien@gmail.com','ROLE_GIANGVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692353357/xtpgvpwtjzv5rrjptasy.webp'),(23,'sinhvien','$2a$10$pduSyoXrLVmJyw65FihmTOZsRutMwuXUOtLHj3OMUay/owJ/FNccq','sinhvien1@gmail.com','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692353374/xqbfwsr94fwwidoqj05u.webp'),(63,'ngominhthanh','$2a$10$kvh5HjPvCpy04YJESdYnZ.rSDM9F870VCHo20Zd0wSfgNM273mYK2','ngominhthanh@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692781983/iznsrggmbhiypitzbkca.webp'),(64,'tranphuong','$2a$10$kvh5HjPvCpy04YJESdYnZ.rSDM9F870VCHo20Zd0wSfgNM273mYK2','tranphuong@ou.edu.vn','ROLE_GIANGVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692781983/iznsrggmbhiypitzbkca.webp'),(65,'thanhdh','$2a$10$kvh5HjPvCpy04YJESdYnZ.rSDM9F870VCHo20Zd0wSfgNM273mYK2','dhthanh@ou.edu.vn','ROLE_GIANGVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692781983/iznsrggmbhiypitzbkca.webp'),(66,'nguyentuyet','$2a$10$kvh5HjPvCpy04YJESdYnZ.rSDM9F870VCHo20Zd0wSfgNM273mYK2','nguyentuyet@ou.edu.vn','ROLE_GIANGVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692781983/iznsrggmbhiypitzbkca.webp'),(67,'ngominhthanh1','$2a$10$GZ0wnZL6GpMHituEMI3jPenvUjijWdbOWe1AVNUkUkkF0iolFxqzq','ngominhthanh1@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692793584/gspcfzjvudoo6r2i4swv.webp'),(68,'ngominhthanh2','$2a$10$FyOy2JZOSNm/5h4DTIZI6.stDWmc5.uquh8jG6hNA9KAITscLseAe','ngominhthanh2@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692793620/wpc8arekbdzegqrjvtx8.webp'),(69,'doangiahuy','$2a$10$3bhoV6PiF8j7kschaeeGgu0BWg.wkNwas3cAgz/.5WnEe.m8bwYQq','doangiahuy@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692803480/i6nwzifgkwlm1usfll3j.png'),(70,'doangiahuy1','$2a$10$idS8yzD/o9I9cVjrMCYhNeHFyVlLHsF9OqZSvctYJ5paxZnTgEdLS','doangiahuy1@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692803575/osnaavywjzyyu9ibxwlh.webp'),(72,'thanh12345','$2a$10$ID6r0OYk9epfxwg105fKe.lYq8Tat2JOw6tx04W.seS0Qd.EDXnvi','thanh12345@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692803730/a880ececjvh5ealsxnhi.png'),(73,'thanh12346','$2a$10$oxoq8maoIzrSvAAPrdyWJOfAxkkGYVyc7hbkIt/d/THkJ/16OShuy','thanh12346@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692804290/cdk63jldrjmvobkxxvik.png'),(74,'thanhtest1','$2a$10$R1x9vM5pUwQXPwjZrwuhS.t3bwgwWf/j45MuNXbcjTjoLekWbNrbC','thanhtest1@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692805332/nxffaegjstnntuus9dqr.jpg'),(75,'thanhtest2','$2a$10$v8NgzixclCgjrlT2K8MoIu3aauUlBtFiVgcLZiQEJ9K1fAVFe7cbi','thanhtest2@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692805350/nom4manieznssshk9prx.webp'),(76,'thanhtest3','$2a$10$VUlBFnm6YDmdQChWEA/2hO2dmFR7AzVaYT73clWuj4.zpLSNRl2FS','thanhtest3@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692807671/ay987xt6ga0rgmylurdz.png'),(77,'thanhtest4','$2a$10$2aVhjmXu4Sk.UGb.BDQ1iOgoykIzYWzjY6l9Qg7kYnMyeReDoKrbC','thanhtest4@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692807691/jcrgf61wpaxl8xplbosm.png'),(79,'thanhtest7','$2a$10$QbNhMh8Ki1BOJxc9E8rYY.ff6.5mOSG78D7jt.xKI4wSGV8YMBBuK','thanhtest7@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692808023/zdaugg4yu2syya5x8car.png'),(80,'thanhtest8','$2a$10$jEByWuy6hNwYkaLvzJ30Yeki.PSCaUxyhTbqZWHfXPtdbsr7lyC.y','thanhtest8@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692808172/aunz22vpfrxailoroiel.webp'),(81,'thanhtest9','$2a$10$x3gebTmFeoN9yTTy/iB5euMOlCF.SHwWJQzaVCYa./TTswRWUOsgW','thanhtest9@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692808191/bopqxmoarbfsmtmzf5k7.webp'),(82,'thanhtest10','$2a$10$crB.459SYgZKnlNJnyQdi.WgoSHfWEUo1etXBK/OUShQ1B1ICsCs.','thanhtest10@ou.edu.vn','ROLE_GIANGVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692817532/pyz3yranhyvejeuvrb0c.webp'),(83,'tktest1','$2a$10$BXKOdSyDWtJjcNN3r2FOXekWmPXwFv2k1ARkLf.Fzk9YjYumFXBSy','tktest1@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692870679/jdlqaxapmjwbiamltni2.jpg'),(84,'tktest2','$2a$10$mMpS.lMVug3cLB0r3fiU7ORalVQxGQMgO4XAinG3.6z/BUTobc0NG','tktest2@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692870697/dxq9ra983a4sc0g5edf5.jpg'),(85,'tktest3','$2a$10$ZRp8SJ6gfrATRwxB.DajneMwHJwDLhjzyxONKmK7VeXADrwnkETvy','tktest3@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692871086/byjv1xb86bzcfprqjpm0.png'),(86,'tktest4','$2a$10$aU3PBb9PvhTIUc7jRRY.3.fiIh.H/w7DLRz1vtG1AjW.mTiQGyuXK','tktest4@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692873949/hczocdwhxajmugfwjtte.webp'),(87,'tktest5','$2a$10$6FXe0wtpKw20w/xNQodFo.j0ffT/rU1lmbBTBbjd7fOx4fHwgbe3a','tktest5@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1692873975/dsqhpwxnesn3eh2wbpbv.webp'),(98,'thanhtest12','$2a$10$l6w5UkvK4bExXJw.836Y7uiU9h0xniE6Ts5wqFmrDSpAKuOwyme.S','thanhtest12@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1693138745/jusbkv548v1gpr55fxpf.png'),(99,'thanhtest44','$2a$10$i9YxhwpNuUNegQ2kEULxMO1s5ToqQhViLSvudc/2jqmG2wCCCT6XS','thanhtest44@ou.edu.vn','ROLE_GIANGVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1693138887/sav7j4cf2twidhfpcckv.webp'),(103,'thanhthanhthanh','$2a$10$i2kbHah0qn5ndc4JjV4lweAI.7.CUS468jEYH0ChZnvc5wISvKUQu','ngominhthanh22@ou.edu.vn','ROLE_SINHVIEN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1694622302/vba8viz0qrq8inxpry8u.jpg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-14 17:30:54
