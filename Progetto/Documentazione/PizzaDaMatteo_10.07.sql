CREATE DATABASE pdm;
USE pdm;

CREATE TABLE 'pizza' (
  'nome' varchar(50) NOT NULL,
  'prezzo' float NULL,
  'quantita' int NULL,
  PRIMARY KEY ('nome')
);


DROP TABLE 'bibite';
CREATE TABLE 'bibite' (
  'nome' varchar(50)  NOT NULL,
  'prezzo' float NOT NULL,
  PRIMARY KEY ('nome')
);


LOCK TABLES 'bibite' WRITE;
INSERT INTO 'bibite' VALUES ('',0),('Acqua grande naturale ',2),('Acqua grande frizzante',2),('Acqua piccola naturale',1),('Acqua piccola frizzante',1),('Fanta bottiglietta',2),('Coca-Cola grande',3),('Coca-Cola piccola',2),('Beck\'s 33cl',2.5),('Ceres 33cl',3),('Tennent\'s 33cl',3),('Peroni 66cl',3),('Heineken 33cl',2.5);
UNLOCK TABLES;


DROP TABLE 'fritti';
CREATE TABLE 'fritti' (
  'nome' varchar(50)  NOT NULL,
  'prezzo' float NOT NULL,
  PRIMARY KEY ('nome')
);


LOCK TABLES 'fritti' WRITE;
INSERT INTO 'fritti' VALUES ('',0),('Suppli classico',1),('Crocchette',1),('Fiori di zucca (1 pz)',1.5),('Olive ascolane (5 pz)',3),('Mozzarelline (5 pz)',3),('Gricette (3 pz)',2),('Patatine fritte',3),('Pizza donuts (2 pz)',3);
UNLOCK TABLES;


DROP TABLE 'ingredienti';
CREATE TABLE 'ingredienti' (
  'nome' varchar(50) NOT NULL,
  'quantita' int NULL,
  PRIMARY KEY ('nome')
);


LOCK TABLES 'ingredienti' WRITE;
INSERT INTO 'ingredienti' VALUES ('Alici',10),('Carciofi',10),('Funghi',10),('Melanzane',10),('Mozzarella',7),('Olio',10),('Olive',10),('Origano',10),('Patate',10),('Pomodoro',10),('Prosciutto',10),('Salame',10),('Salsiccia',10),('Uovo',10),('Zucchine',10);
UNLOCK TABLES;


DROP TABLE 'ingredientinuova';
CREATE TABLE 'ingredientinuova' (
  'id' int NOT NULL AUTO_INCREMENT,
  'nome' varchar(50) NULL,
  'quantita' int NULL,
  PRIMARY KEY ('id')
);


LOCK TABLES 'ingredientinuova' WRITE;
INSERT INTO 'ingredientinuova' VALUES (1,'Alici',10),(2,'Carciofi',10),(3,'Funghi',10),(4,'Melanzane',10),(5,'Mozzarella',10),(6,'Olio',10),(7,'Olive',10),(8,'Origano',10),(9,'Patate',10),(10,'Pomodoro',10),(11,'Prosciutto',10),(12,'Salame',10),(13,'Salsiccia',10),(14,'Uovo',10),(15,'Zucchine',10);
UNLOCK TABLES;


DROP TABLE 'numero';
SET @saved_cs_client     =character_set_client;
 1 AS 'pizza',
 1 AS 'cntPizza'*/;
SET  @saved_cs_client;


DROP TABLE 'ordine';
CREATE TABLE 'ordine' (
  'id_ordine' int NOT NULL AUTO_INCREMENT,
  'nome_cliente' varchar(50) NOT NULL,
  'orario' time NOT NULL,
  'recapito' varchar(10)  NOT NULL,
  'indirizzo' varchar(50)  NULL,
  'confermato' tinyint(1) NULL,
  PRIMARY KEY ('id_ordine'),
  KEY 'orario' ('orario')
);


LOCK TABLES 'ordine' WRITE;
INSERT INTO 'ordine' VALUES (1,'Thomas','22:00:00','3462472413','Viale',1),(2,'Test','22:00:00','3462472413','Viale',0),(3,'Thomas','22:00:00','3462472413','Viale',0),(4,'Ele','19:00:00','3462472413','Viale',0),(5,'Test','22:00:00','3462472413','viale',1),(6,'Amanda','22:00:00','3462472413','viale',1),(7,'Billy','22:00:00','3462472413','viale delle Ferriere',1);
UNLOCK TABLES;


DROP TABLE 'ordine1';
CREATE TABLE 'ordine1' (
  'id_ordine' int NOT NULL AUTO_INCREMENT,
  'pizza1' varchar(50)  NULL,
  'pizza2' varchar(50)  NULL,
  'pizza3' varchar(50)  NULL,
  'pizza4' varchar(50)  NULL,
  'pizza5' varchar(50)  NULL,
  'fritti1' varchar(50)  NULL,
  'fritti2' varchar(50)  NULL,
  'fritti3' varchar(50)  NULL,
  'fritti4' varchar(50)  NULL,
  'fritti5' varchar(50)  NULL,
  'bibite1' varchar(50)  NULL,
  'bibite2' varchar(50)  NULL,
  'bibite3' varchar(50)  NULL,
  'bibite4' varchar(50)  NULL,
  'bibite5' varchar(50)  NULL,
  'confermato' tinyint(1) NULL,
  PRIMARY KEY ('id_ordine')
);


LOCK TABLES 'ordine1' WRITE;
UNLOCK TABLES;


DROP TABLE 'ordine2';
CREATE TABLE 'ordine2' (
  'id_riga' int NOT NULL AUTO_INCREMENT,
  'id_ordine' int NULL,
  'pizza' varchar(50) NULL,
  'fritti' varchar(50) NULL,
  'bibite' varchar(50) NULL,
  'confermato' tinyint(1) NULL,
  PRIMARY KEY ('id_riga')
);


LOCK TABLES 'ordine2' WRITE;
INSERT INTO 'ordine2' VALUES (1,1,'Bufalona','Crocchette','Peroni 66cl',0),(2,1,'Crostino','','Acqua piccola naturale',0),(3,1,'Diavola','Patatine fritte','Beck\'s 33cl',0),(4,2,'Bismarck','Patatine fritte','Beck\'s 33cl',0),(5,2,'4 Stagioni','Suppli classico','Ceres 33cl',0),(6,3,'Bismarck','Patatine fritte','',0),(7,3,'Carbonara','','Ceres 33cl',0),(8,3,'Diavola','','',0),(9,4,'Bufalona','Gricette (3 pz)','Beck\'s 33cl',0),(10,4,'Bufalona','Gricette (3 pz)','Beck\'s 33cl',0),(11,4,'Bufalona','Gricette (3 pz)','Beck\'s 33cl',0),(12,4,'Bufalona','Gricette (3 pz)','Beck\'s 33cl',0),(13,4,'Bufalona','Gricette (3 pz)','Beck\'s 33cl',0),(14,4,'Bufalona','Gricette (3 pz)','Beck\'s 33cl',0),(15,4,'Bufalona','Gricette (3 pz)','Beck\'s 33cl',0),(16,4,'Bufalona','Gricette (3 pz)','Beck\'s 33cl',0),(17,4,'Bufalona','Gricette (3 pz)','Beck\'s 33cl',0),(18,4,'Bufalona','Gricette (3 pz)','Beck\'s 33cl',0),(19,5,'Napoli','','',1),(20,6,'Rossa','','',1),(21,7,'Bufalona','','',1);
UNLOCK TABLES;


DROP TABLE 'pizza';


LOCK TABLES 'pizza' WRITE;
INSERT INTO 'pizza' VALUES ('',0,'0','0','0','0','0',0),('4 Stagioni',6.5,'Mozzarella','Pomodoro','Olio','Olive','Prosciutto',4),('Bismarck',6,'Mozzarella','Pomodoro','Olio','Prosciutto','Uovo',0),('Boscaiola',6.5,'Mozzarella','Olio','Salsiccia','Funghi',NULL,1),('Bufalona',7,'Pomodoro','Olive','Alici',NULL,NULL,2),('Capricciosa',7.5,'Mozzarella','Pomodoro','Olio','Funghi','Carciofi',2),('Carbonara',7,'Mozzarella','Pomodoro','Olio','Pancetta','Uovo',0),('Crostino',5,'Mozzarella','Olio','Patate','Prosciutto',NULL,0),('Diavola',6,'Mozzarella','Pomodoro','Olio','Salame','Origano',2),('Margherita',4.5,'Mozzarella','Pomodoro','Olio',NULL,NULL,0),('Napoli',4.5,'Mozzarella','Pomodoro','Olio','Alici',NULL,0),('Ortolana',5.5,'Mozzarella','Olio','Pomodoro','Melanzane','Zucchine',0),('Parmigiana',6,'Mozzarella','Pomodoro','Olio','Melanzane',NULL,0),('Patate',4.5,'Mozzarella','Olio','Patate',NULL,NULL,0),('Porcini',6.5,'Mozzarella','Olio','Funghi',NULL,NULL,0),('Rossa',3.5,'Pomodoro','Olio',NULL,NULL,NULL,0);
UNLOCK TABLES;


DROP TABLE 'pizzanuova';
CREATE TABLE 'pizzanuova' (
  'codice' int NOT NULL AUTO_INCREMENT,
  'nome' varchar(50) NULL,
  'prezzo' float NULL,
  'quantita' int NULL,
  PRIMARY KEY ('codice')
);


LOCK TABLES 'pizzanuova' WRITE;
INSERT INTO 'pizzanuova' VALUES (1,'4 Stagioni',6.5,0),(2,'Bismarck',6,0),(3,'Boscaiola',6.5,0),(4,'Bufalona',7,0),(5,'Capricciosa',7.5,0),(6,'Carbonara',7,0),(7,'Crostino',5,0),(8,'Diavola',6,0),(9,'Margherita',4.5,0),(10,'Napoli',4.5,0),(11,'Ortolana',5.5,0),(12,'Parmigiana',6,0),(13,'Patate',4.5,0),(14,'Porcini',6.5,0),(15,'Rossa',3.5,0);
UNLOCK TABLES;


DROP TABLE 'ricetta';
CREATE TABLE 'ricetta' (
  'codice' int NULL,
  'id' int NULL,
  'quantita' int NULL,
  KEY 'codice' ('codice'),
  KEY 'id' ('id'),
  CONSTRAINT 'ricetta_ibfk_1' FOREIGN KEY ('codice') REFERENCES 'pizzanuova' ('codice'),
  CONSTRAINT 'ricetta_ibfk_2' FOREIGN KEY ('id') REFERENCES 'ingredientinuova' ('id')
);


LOCK TABLES 'ricetta' WRITE;
INSERT INTO 'ricetta' VALUES (1,5,1),(1,10,1),(1,6,1),(1,7,1),(1,11,1);
UNLOCK TABLES;
