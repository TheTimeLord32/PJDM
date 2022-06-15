create database pizza;
use pizza;

CREATE TABLE IF NOT EXISTS bibite (
  nome varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  prezzo float NOT NULL,
  PRIMARY KEY (nome)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci AUTO_INCREMENT=1 ;

INSERT INTO bibite (nome, prezzo) VALUES
('Acqua grande naturale ', 2),
('Acqua grande frizzante', 2),
('Acqua piccola naturale', 1),
('Acqua piccola frizzante', 1),
('Fanta bottiglietta', 2),
('Coca-Cola grande', 3),
('Coca-Cola piccola', 2),
('Beck''s 33cl', 2.5),
('Ceres 33cl', 3),
('Tennent''s 33cl', 3),
('Peroni 66cl', 3),
('Heineken 33cl', 2.5),
('Scegli le bibite', 0);

CREATE TABLE IF NOT EXISTS fritti (
  nome varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  prezzo float NOT NULL,
  PRIMARY KEY (nome)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci AUTO_INCREMENT=1 ;

INSERT INTO fritti (nome, prezzo) VALUES
('Suppli classico', 1),
('Suppli/arancino della settimana', 2),
('Crocchette', 1),
('Fiori di zucca (1 pz)', 1.5),
('Olive ascolane (5 pz)', 3),
('Mozzarelline (5 pz)', 3),
('Gricette (3 pz)', 2),
('Patatine fritte', 3),
('Pizza donuts (2 pz)', 3),
('Scegli i fritti', 0);

CREATE TABLE IF NOT EXISTS ordine (
  id_ordine int NOT NULL AUTO_INCREMENT,
  modalita varchar(50) NOT NULL,
  nome_cliente varchar(50) NOT NULL,
  orario time NOT NULL,
  recapito varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  indirizzo varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (id_ordine),
  KEY orario (orario)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci AUTO_INCREMENT=10 ;

CREATE TABLE IF NOT EXISTS ordine1 (
  id_ordine int NOT NULL AUTO_INCREMENT,
  num_pizze int DEFAULT NULL,
  pizza1 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  pizza2 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  pizza3 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  pizza4 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  pizza5 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  pizza6 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  pizza7 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  pizza8 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  pizza9 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  pizza10 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  num_pale int DEFAULT NULL,
  pale1 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  pale2 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  pale3 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  pale4 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  num_fritti int DEFAULT NULL,
  fritti1 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  fritti2 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  fritti3 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  fritti4 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  fritti5 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  num_bibite int DEFAULT NULL,
  bibite1 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  bibite2 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  bibite3 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  bibite4 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  bibite5 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  note varchar(1000) DEFAULT NULL,
  conto float DEFAULT NULL,
  PRIMARY KEY (id_ordine)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci AUTO_INCREMENT=10 ;

CREATE TABLE IF NOT EXISTS pale (
  nome varchar(20) NOT NULL,
  prezzo float NOT NULL,
  PRIMARY KEY (nome)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci AUTO_INCREMENT=1 ;

INSERT INTO pale (nome, prezzo) VALUES
('Focaccia', 7),
('Rossa', 8),
('Margherita', 12),
('Altro', 15),
('Scegli le pale', 0);

CREATE TABLE IF NOT EXISTS pizza (
  nome varchar(20) NOT NULL,
  prezzo float NOT NULL,
  PRIMARY KEY (nome)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci AUTO_INCREMENT=1 ;

INSERT INTO pizza (nome, prezzo) VALUES
('Rossa', 3.5),
('Marinara', 4.5),
('Margherita', 4.5),
('Margherita bufala', 6.5),
('4 Stagioni', 6.5),
('Capricciosa', 7.5),
('Diavola', 6),
('Bismark', 6),
('Parmigiana', 6),
('Zingara', 5.5),
('Patate', 4.5),
('Crostino', 5),
('Ortolana', 5.5),
('Boscaiola', 6.5),
('Cicoria', 6.5),
('Broccoletti', 6.5),
('Porcini', 6.5),
('Speck', 6.5),
('Tirolese', 6.5),
('4 Formaggi', 6),
('Bufalona', 7),
('Bresaola', 7),
('Salmone', 7.5),
('Estivo', 8),
('Nutella', 5),
('Gamberetti', 7.5),
('Amatriciana', 6.5),
('Carbonara', 7),
('Radicchio', 7),
('Flavia', 7),
('Pesto', 7.5),
('Scegli la pizza', 0);

CREATE TABLE IF NOT EXISTS uscita (
  id_ordine int NOT NULL AUTO_INCREMENT,
  orario time NOT NULL,
  domicilio int NOT NULL,
  fattorino varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  asporto int NOT NULL,
  note varchar(200) DEFAULT NULL,
  tot_pizze int NOT NULL,
  tot_pale int NOT NULL,
  tot_fritti int NOT NULL,
  conto float NOT NULL,
  PRIMARY KEY (id_ordine)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci AUTO_INCREMENT=10 ;
