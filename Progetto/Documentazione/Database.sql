DROP TABLE IF EXISTS `bibite`;
CREATE TABLE `bibite` (
  `nome` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `prezzo` float NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `bibite` WRITE;
INSERT INTO `bibite` VALUES ('', 0), ('Acqua grande naturale ',2),('Acqua grande frizzante',2),('Acqua piccola naturale',1),('Acqua piccola frizzante',1),('Fanta bottiglietta',2),('Coca-Cola grande',3),('Coca-Cola piccola',2),('Beck\'s 33cl',2.5),('Ceres 33cl',3),('Tennent\'s 33cl',3),('Peroni 66cl',3),('Heineken 33cl',2.5);
UNLOCK TABLES;

DROP TABLE IF EXISTS `countingredienti`;
SET @saved_cs_client     = @@character_set_client;
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

DROP TABLE IF EXISTS `fritti`;
CREATE TABLE `fritti` (
  `nome` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `prezzo` float NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `fritti` WRITE;
INSERT INTO `fritti` VALUES ('', 0), ('Suppli classico',1),('Suppli/arancino della settimana',2),('Crocchette',1),('Fiori di zucca (1 pz)',1.5),('Olive ascolane (5 pz)',3),('Mozzarelline (5 pz)',3),('Gricette (3 pz)',2),('Patatine fritte',3),('Pizza donuts (2 pz)',3);
UNLOCK TABLES;

DROP TABLE IF EXISTS `ingredienti`;
CREATE TABLE `ingredienti` (
  `nome` varchar(50) NOT NULL,
  `quantita` int DEFAULT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `ingredienti` WRITE;
INSERT INTO `ingredienti` VALUES ('Alici',10),('Carciofi',10),('Funghi',10),('Melanzane',10),('Mozzarella',7),('Olio',10),('Olive',10),('Origano',10),('Patate',10),('Pomodoro',10),('Prosciutto',10),('Salame',10),('Salsiccia',10),('Uovo',10),('Zucchine',10);
UNLOCK TABLES;

DROP TABLE IF EXISTS `ingredientiordine1`;
SET @saved_cs_client     = @@character_set_client;
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

DROP TABLE IF EXISTS `numingredienti`;
SET @saved_cs_client     = @@character_set_client;
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

DROP TABLE IF EXISTS `ordine`;
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
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `ordine1`;
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
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `pizza`;
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

LOCK TABLES `pizza` WRITE;
INSERT INTO `pizza` VALUES ('', 0, 0, 0, 0, 0, 0, 0), ('4 Stagioni',6.5,'Mozzarella','Pomodoro','Olio','Olive','Prosciutto',3),('Bismarck',6,'Mozzarella','Pomodoro','Olio','Prosciutto','Uovo',1),('Boscaiola',6.5,'Mozzarella','Olio','Salsiccia','Funghi',NULL,1),('Bufalona',7,'Pomodoro','Olive','Alici',NULL,NULL,2),('Capricciosa',7.5,'Mozzarella','Pomodoro','Olio','Funghi','Carciofi',1),('Carbonara',7,'Mozzarella','Pomodoro','Olio','Pancetta','Uovo',0),('Crostino',5,'Mozzarella','Olio','Patate','Prosciutto',NULL,0),('Diavola',6,'Mozzarella','Pomodoro','Olio','Salame','Origano',0),('Margherita',4.5,'Mozzarella','Pomodoro','Olio',NULL,NULL,0),('Napoli',4.5,'Mozzarella','Pomodoro','Olio','Alici',NULL,2),('Ortolana',5.5,'Mozzarella','Olio','Pomodoro','Melanzane','Zucchine',0),('Parmigiana',6,'Mozzarella','Pomodoro','Olio','Melanzane',NULL,0),('Patate',4.5,'Mozzarella','Olio','Patate',NULL,NULL,0),('Porcini',6.5,'Mozzarella','Olio','Funghi',NULL,NULL,0),('Rossa',3.5,'Pomodoro','Olio',NULL,NULL,NULL,1);
UNLOCK TABLES;


GRANT USAGE ON pizza TO 'utente'@'localhost';
GRANT SELECT ON pizza.bibite TO 'utente'@'localhost';
GRANT SELECT ON pizza.fritti TO 'utente'@'localhost';
GRANT SELECT, UPDATE ON pizza.pizza TO 'utente'@'localhost';
GRANT SELECT, INSERT, UPDATE ON pizza.ordine TO 'utente'@'localhost';
GRANT SELECT, INSERT, UPDATE ON pizza.ordine1 TO 'utente'@'localhost';
