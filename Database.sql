CREATE DATABASE  IF NOT EXISTS `humanresources` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `humanresources`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: humanresources
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `areaofinterest`
--

DROP TABLE IF EXISTS `areaofinterest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `areaofinterest` (
  `pkID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`pkID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areaofinterest`
--

LOCK TABLES `areaofinterest` WRITE;
/*!40000 ALTER TABLE `areaofinterest` DISABLE KEYS */;
/*!40000 ALTER TABLE `areaofinterest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `certificate`
--

DROP TABLE IF EXISTS `certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `certificate` (
  `pkID` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `descripción` varchar(255) DEFAULT NULL,
  `fkuserID` int(11) NOT NULL,
  PRIMARY KEY (`pkID`),
  UNIQUE KEY `fkuserID_UNIQUE` (`fkuserID`),
  KEY `fk_certificate_user1_idx` (`fkuserID`),
  CONSTRAINT `fk_certificate_user1` FOREIGN KEY (`fkuserID`) REFERENCES `user` (`pkID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificate`
--

LOCK TABLES `certificate` WRITE;
/*!40000 ALTER TABLE `certificate` DISABLE KEYS */;
/*!40000 ALTER TABLE `certificate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `certifications`
--

DROP TABLE IF EXISTS `certifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `certifications` (
  `pkID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `place` varchar(100) NOT NULL,
  `fkuserID` int(11) NOT NULL,
  PRIMARY KEY (`pkID`),
  KEY `fk_certifications_user1_idx` (`fkuserID`),
  CONSTRAINT `fk_certifications_user1` FOREIGN KEY (`fkuserID`) REFERENCES `user` (`pkID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certifications`
--

LOCK TABLES `certifications` WRITE;
/*!40000 ALTER TABLE `certifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `certifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `certificationsareaofinterest`
--

DROP TABLE IF EXISTS `certificationsareaofinterest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `certificationsareaofinterest` (
  `fkcertificationsID` int(11) NOT NULL,
  `fkareaofinterestID` int(11) NOT NULL,
  KEY `fk_certifications_has_areaofinterest_areaofinterest1_idx` (`fkareaofinterestID`),
  KEY `fk_certifications_has_areaofinterest_certifications1_idx` (`fkcertificationsID`),
  CONSTRAINT `fk_certifications_has_areaofinterest_areaofinterest1` FOREIGN KEY (`fkareaofinterestID`) REFERENCES `areaofinterest` (`pkID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_certifications_has_areaofinterest_certifications1` FOREIGN KEY (`fkcertificationsID`) REFERENCES `certifications` (`pkID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificationsareaofinterest`
--

LOCK TABLES `certificationsareaofinterest` WRITE;
/*!40000 ALTER TABLE `certificationsareaofinterest` DISABLE KEYS */;
/*!40000 ALTER TABLE `certificationsareaofinterest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contract` (
  `pkID` int(11) NOT NULL AUTO_INCREMENT,
  `salary` double NOT NULL,
  `type` varchar(45) NOT NULL,
  `enddate` date DEFAULT NULL,
  `fkuserID` int(11) NOT NULL,
  PRIMARY KEY (`pkID`),
  UNIQUE KEY `fkuserID_UNIQUE` (`fkuserID`),
  KEY `fk_contract_user1_idx` (`fkuserID`),
  CONSTRAINT `fk_contract_user1` FOREIGN KEY (`fkuserID`) REFERENCES `user` (`pkID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contractposition`
--

DROP TABLE IF EXISTS `contractposition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contractposition` (
  `fkcontractID` int(11) NOT NULL,
  `fkpositionID` int(11) NOT NULL,
  KEY `fk_contract_has_position_position1_idx` (`fkpositionID`),
  KEY `fk_contract_has_position_contract_idx` (`fkcontractID`),
  CONSTRAINT `fk_contract_has_position_contract` FOREIGN KEY (`fkcontractID`) REFERENCES `contract` (`pkID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_contract_has_position_position1` FOREIGN KEY (`fkpositionID`) REFERENCES `position` (`pkID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contractposition`
--

LOCK TABLES `contractposition` WRITE;
/*!40000 ALTER TABLE `contractposition` DISABLE KEYS */;
/*!40000 ALTER TABLE `contractposition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notifications` (
  `pkID` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `fkcertificationID` int(11) DEFAULT NULL,
  `fkuserID` int(11) NOT NULL,
  `fkcertificateID` int(11) DEFAULT NULL,
  PRIMARY KEY (`pkID`),
  KEY `fk_notifications_certifications1_idx` (`fkcertificationID`),
  KEY `fk_notifications_user1_idx` (`fkuserID`),
  KEY `fk_notifications_certificate1_idx` (`fkcertificateID`),
  CONSTRAINT `fk_notifications_certificate1` FOREIGN KEY (`fkcertificateID`) REFERENCES `certificate` (`pkID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_notifications_certifications1` FOREIGN KEY (`fkcertificationID`) REFERENCES `certifications` (`pkID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_notifications_user1` FOREIGN KEY (`fkuserID`) REFERENCES `user` (`pkID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `pkID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`pkID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `pkID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`pkID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'User'),(2,'Administrator');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `pkID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `age` int(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone` double NOT NULL,
  `email` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(16) NOT NULL,
  `fkroleID` int(11) NOT NULL,
  PRIMARY KEY (`pkID`),
  UNIQUE KEY `fkroleID_UNIQUE` (`fkroleID`),
  KEY `fk_user_role1_idx` (`fkroleID`),
  CONSTRAINT `fk_user_role1` FOREIGN KEY (`fkroleID`) REFERENCES `role` (`pkID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Admin','Tester',99,'Juaz123',3212345656,'admin@admin.admin','admin','Admin2016',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userareaofinterest`
--

DROP TABLE IF EXISTS `userareaofinterest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userareaofinterest` (
  `fkuserID` int(11) NOT NULL,
  `fkareaofinterestID` int(11) NOT NULL,
  KEY `fk_user_has_areaofinterest_areaofinterest1_idx` (`fkareaofinterestID`),
  KEY `fk_user_has_areaofinterest_user1_idx` (`fkuserID`),
  CONSTRAINT `fk_user_has_areaofinterest_areaofinterest1` FOREIGN KEY (`fkareaofinterestID`) REFERENCES `areaofinterest` (`pkID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_areaofinterest_user1` FOREIGN KEY (`fkuserID`) REFERENCES `user` (`pkID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userareaofinterest`
--

LOCK TABLES `userareaofinterest` WRITE;
/*!40000 ALTER TABLE `userareaofinterest` DISABLE KEYS */;
/*!40000 ALTER TABLE `userareaofinterest` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-22 16:37:15
