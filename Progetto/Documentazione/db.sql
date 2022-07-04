-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: pizza
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bibite`
--

DROP TABLE IF EXISTS `bibite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bibite` (
  `nome` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `prezzo` float NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bibite`
--

LOCK TABLES `bibite` WRITE;
/*!40000 ALTER TABLE `bibite` DISABLE KEYS */;
INSERT INTO `bibite` VALUES ('Acqua grande naturale ',2),('Acqua grande frizzante',2),('Acqua piccola naturale',1),('Acqua piccola frizzante',1),('Fanta bottiglietta',2),('Coca-Cola grande',3),('Coca-Cola piccola',2),('Beck\'s 33cl',2.5),('Ceres 33cl',3),('Tennent\'s 33cl',3),('Peroni 66cl',3),('Heineken 33cl',2.5),('Scegli le bibite',0);
/*!40000 ALTER TABLE `bibite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `countingredienti`
--

DROP TABLE IF EXISTS `countingredienti`;
/*!50001 DROP VIEW IF EXISTS `countingredienti`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `countingredienti` AS SELECT 
 1 AS `ingredienti1`,
 1 AS `quantIngr1`,
 1 AS `ingredienti2`,
 1 AS `quantIngr2`,
 1 AS `ingredienti3`,
 1 AS `quantIngr3`,
 1 AS `ingredienti4`,
 1 AS `quantIngr4`,
 1 AS `ingredienti5`,
 1 AS `quantIngr5`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `fritti`
--

DROP TABLE IF EXISTS `fritti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fritti` (
  `nome` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `prezzo` float NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fritti`
--

LOCK TABLES `fritti` WRITE;
/*!40000 ALTER TABLE `fritti` DISABLE KEYS */;
INSERT INTO `fritti` VALUES ('Suppli classico',1),('Suppli/arancino della settimana',2),('Crocchette',1),('Fiori di zucca (1 pz)',1.5),('Olive ascolane (5 pz)',3),('Mozzarelline (5 pz)',3),('Gricette (3 pz)',2),('Patatine fritte',3),('Pizza donuts (2 pz)',3),('Scegli i fritti',0);
/*!40000 ALTER TABLE `fritti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredienti`
--

DROP TABLE IF EXISTS `ingredienti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredienti` (
  `nome` varchar(50) NOT NULL,
  `quantita` int DEFAULT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredienti`
--

LOCK TABLES `ingredienti` WRITE;
/*!40000 ALTER TABLE `ingredienti` DISABLE KEYS */;
INSERT INTO `ingredienti` VALUES ('Alici',10),('Carciofi',10),('Funghi',10),('Melanzane',10),('Mozzarella',7),('Olio',10),('Olive',10),('Origano',10),('Patate',10),('Pomodoro',10),('Prosciutto',10),('Salame',10),('Salsiccia',10),('Uovo',10),('Zucchine',10);
/*!40000 ALTER TABLE `ingredienti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `ingredientiordine1`
--

DROP TABLE IF EXISTS `ingredientiordine1`;
/*!50001 DROP VIEW IF EXISTS `ingredientiordine1`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `ingredientiordine1` AS SELECT 
 1 AS `pizza1`,
 1 AS `pizza2`,
 1 AS `pizza3`,
 1 AS `pizza4`,
 1 AS `pizza5`,
 1 AS `ingredienti1`,
 1 AS `ingredienti2`,
 1 AS `ingredienti3`,
 1 AS `ingredienti4`,
 1 AS `ingredienti5`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `numingredienti`
--

DROP TABLE IF EXISTS `numingredienti`;
/*!50001 DROP VIEW IF EXISTS `numingredienti`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `numingredienti` AS SELECT 
 1 AS `nome`,
 1 AS `quantita`,
 1 AS `ingredienti1`,
 1 AS `quantIngr1`,
 1 AS `ingredienti2`,
 1 AS `quantIngr2`,
 1 AS `ingredienti3`,
 1 AS `quantIngr3`,
 1 AS `ingredienti4`,
 1 AS `quantIngr4`,
 1 AS `ingredienti5`,
 1 AS `quantIngr5`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine` (
  `id_ordine` int NOT NULL AUTO_INCREMENT,
  `modalita` varchar(50) DEFAULT NULL,
  `nome_cliente` varchar(50) NOT NULL,
  `orario` time NOT NULL,
  `recapito` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `indirizzo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `confermato` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_ordine`),
  KEY `orario` (`orario`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (1,NULL,'Thomas','19:00:00','3462472413','Viale Alcide de Gasperi',0),(2,NULL,'Eleonora','21:00:00','3462472413','Via Roma',0),(3,NULL,'Dario','22:30:00','3462472413','Via Toscana',0),(4,NULL,'Federico','20:30:00','1234123','A',0),(5,NULL,'Thomas','23:00:00','3462472413','Viale Alcide de Gasperi',0);
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine1`
--

DROP TABLE IF EXISTS `ordine1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine1` (
  `id_ordine` int NOT NULL AUTO_INCREMENT,
  `num_pizze` int DEFAULT NULL,
  `pizza1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pizza2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pizza3` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pizza4` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pizza5` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `num_fritti` int DEFAULT NULL,
  `fritti1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `fritti2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `fritti3` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `fritti4` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `fritti5` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `num_bibite` int DEFAULT NULL,
  `bibite1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `bibite2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `bibite3` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `bibite4` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `bibite5` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `note` varchar(1000) DEFAULT NULL,
  `conto` float DEFAULT NULL,
  `confermato` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_ordine`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine1`
--

LOCK TABLES `ordine1` WRITE;
/*!40000 ALTER TABLE `ordine1` DISABLE KEYS */;
INSERT INTO `ordine1` VALUES (1,0,'Margherita',NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,0,0),(2,0,'Napoli',NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,0,0),(3,0,'Bufalona',NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,0,0),(4,0,'Napoli',NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,0,0),(5,0,'Rossa',NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,0,0);
/*!40000 ALTER TABLE `ordine1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizza`
--

DROP TABLE IF EXISTS `pizza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pizza` (
  `nome` varchar(50) NOT NULL,
  `prezzo` float DEFAULT NULL,
  `ingredienti1` varchar(50) DEFAULT NULL,
  `ingredienti2` varchar(50) DEFAULT NULL,
  `ingredienti3` varchar(50) DEFAULT NULL,
  `ingredienti4` varchar(50) DEFAULT NULL,
  `ingredienti5` varchar(50) DEFAULT NULL,
  `quantita` int DEFAULT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza`
--

LOCK TABLES `pizza` WRITE;
/*!40000 ALTER TABLE `pizza` DISABLE KEYS */;
INSERT INTO `pizza` VALUES ('4 Stagioni',6.5,'Mozzarella','Pomodoro','Olio','Olive','Prosciutto',0),('Bismarck',6,'Mozzarella','Pomodoro','Olio','Prosciutto','Uovo',0),('Boscaiola',6.5,'Mozzarella','Olio','Salsiccia','Funghi',NULL,0),('Bufalona',7,'Pomodoro','Olive','Alici',NULL,NULL,1),('Capricciosa',7.5,'Mozzarella','Pomodoro','Olio','Funghi','Carciofi',0),('Carbonara',7,'Mozzarella','Pomodoro','Olio','Pancetta','Uovo',0),('Crostino',5,'Mozzarella','Olio','Patate','Prosciutto',NULL,0),('Diavola',6,'Mozzarella','Pomodoro','Olio','Salame','Origano',0),('Margherita',4.5,'Mozzarella','Pomodoro','Olio',NULL,NULL,0),('Napoli',4.5,'Mozzarella','Pomodoro','Olio','Alici',NULL,2),('Ortolana',5.5,'Mozzarella','Olio','Pomodoro','Melanzane','Zucchine',0),('Parmigiana',6,'Mozzarella','Pomodoro','Olio','Melanzane',NULL,0),('Patate',4.5,'Mozzarella','Olio','Patate',NULL,NULL,0),('Porcini',6.5,'Mozzarella','Olio','Funghi',NULL,NULL,0),('Rossa',3.5,'Pomodoro','Olio',NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `pizza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `countingredienti`
--

/*!50001 DROP VIEW IF EXISTS `countingredienti`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `countingredienti` AS select `ingredientiordine1`.`ingredienti1` AS `ingredienti1`,count(`ingredientiordine1`.`ingredienti1`) AS `quantIngr1`,`ingredientiordine1`.`ingredienti2` AS `ingredienti2`,count(`ingredientiordine1`.`ingredienti2`) AS `quantIngr2`,`ingredientiordine1`.`ingredienti3` AS `ingredienti3`,count(`ingredientiordine1`.`ingredienti3`) AS `quantIngr3`,`ingredientiordine1`.`ingredienti4` AS `ingredienti4`,count(`ingredientiordine1`.`ingredienti4`) AS `quantIngr4`,`ingredientiordine1`.`ingredienti5` AS `ingredienti5`,count(`ingredientiordine1`.`ingredienti5`) AS `quantIngr5` from `ingredientiordine1` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `ingredientiordine1`
--

/*!50001 DROP VIEW IF EXISTS `ingredientiordine1`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `ingredientiordine1` AS select `ordine1`.`pizza1` AS `pizza1`,`ordine1`.`pizza2` AS `pizza2`,`ordine1`.`pizza3` AS `pizza3`,`ordine1`.`pizza4` AS `pizza4`,`ordine1`.`pizza5` AS `pizza5`,`pizza`.`ingredienti1` AS `ingredienti1`,`pizza`.`ingredienti2` AS `ingredienti2`,`pizza`.`ingredienti3` AS `ingredienti3`,`pizza`.`ingredienti4` AS `ingredienti4`,`pizza`.`ingredienti5` AS `ingredienti5` from (`ordine1` join `pizza`) where ((`ordine1`.`pizza1` = `pizza`.`nome`) or (`ordine1`.`pizza2` = `pizza`.`nome`) or (`ordine1`.`pizza3` = `pizza`.`nome`) or (`ordine1`.`pizza4` = `pizza`.`nome`) or (`ordine1`.`pizza5` = `pizza`.`nome`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `numingredienti`
--

/*!50001 DROP VIEW IF EXISTS `numingredienti`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `numingredienti` AS select `ingredienti`.`nome` AS `nome`,`ingredienti`.`quantita` AS `quantita`,`countingredienti`.`ingredienti1` AS `ingredienti1`,`countingredienti`.`quantIngr1` AS `quantIngr1`,`countingredienti`.`ingredienti2` AS `ingredienti2`,`countingredienti`.`quantIngr2` AS `quantIngr2`,`countingredienti`.`ingredienti3` AS `ingredienti3`,`countingredienti`.`quantIngr3` AS `quantIngr3`,`countingredienti`.`ingredienti4` AS `ingredienti4`,`countingredienti`.`quantIngr4` AS `quantIngr4`,`countingredienti`.`ingredienti5` AS `ingredienti5`,`countingredienti`.`quantIngr5` AS `quantIngr5` from (`ingredienti` join `countingredienti`) where ((`ingredienti`.`nome` = `countingredienti`.`ingredienti1`) or (`ingredienti`.`nome` = `countingredienti`.`ingredienti2`) or (`ingredienti`.`nome` = `countingredienti`.`ingredienti3`) or (`ingredienti`.`nome` = `countingredienti`.`ingredienti4`) or (`ingredienti`.`nome` = `countingredienti`.`ingredienti5`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-04 10:14:23
