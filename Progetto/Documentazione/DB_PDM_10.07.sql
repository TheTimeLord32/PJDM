CREATE DATABASE pdm;
USE pdm;

CREATE TABLE pizza (
  nome varchar(50) NOT NULL,
  prezzo float NULL,
  quantita int NULL,
  PRIMARY KEY (nome)
);

INSERT INTO pizza VALUES
('',0, 0),
('4 Stagioni',6.5, 0),
('Bismarck',6, 0),
('Boscaiola',6.5, 0),
('Bufalona',7, 0),
('Capricciosa',7.5, 0),
('Carbonara',7, 0),
('Crostino',5, 0),
('Diavola',6, 0),
('Margherita',4.5, 0),
('Napoli',4.5, 0),
('Ortolana',5.5, 0),
('Parmigiana',6, 0),
('Patate',4.5, 0),
('Porcini',6.5, 0),
('Rossa',3.5, 0);

CREATE TABLE fritti (
  nome varchar(50)  NOT NULL,
  prezzo float NOT NULL,
  PRIMARY KEY (nome)
);

INSERT INTO fritti VALUES 
('',0),
('Crocchette',1),
('Fiori di zucca (1 pz)',1.5),
('Gricette (3 pz)',2),
('Mozzarelline (5 pz)',3),
('Olive ascolane (5 pz)',3),
('Patatine fritte',3),
('Suppli classico',1);

CREATE TABLE bibite (
  nome varchar(50)  NOT NULL,
  prezzo float NOT NULL,
  PRIMARY KEY (nome)
);

INSERT INTO bibite VALUES 
('',0),
('Acqua grande frizzante',2),
('Acqua grande naturale' ,2),
('Acqua piccola frizzante',1),
('Acqua piccola naturale',1),
('Ceres 33cl',3),
('Coca-Cola grande',3),
('Coca-Cola piccola',2),
('Fanta bottiglietta',2),
('Franziskaner 50cl',3),
('Heineken 33cl',2.5),
('Kozel 50cl',2.5),
('Peroni 66cl',3);

CREATE TABLE ordine (
  id_ordine int NOT NULL AUTO_INCREMENT,
  nome_cliente varchar(50) NOT NULL,
  orario time NOT NULL,
  recapito varchar(10)  NOT NULL,
  indirizzo varchar(50)  NULL,
  confermato boolean NULL,
  PRIMARY KEY (id_ordine)
);

CREATE TABLE ordine2 (
  id_riga int NOT NULL AUTO_INCREMENT,
  id_ordine int NULL,
  pizza varchar(50) NULL,
  fritti varchar(50) NULL,
  bibite varchar(50) NULL,
  confermato boolean NULL,
  PRIMARY KEY (id_riga)
);

CREATE VIEW stats_pizza AS
SELECT pizza, count(pizza) AS cntPizza
FROM ordine2 
GROUP BY pizza 
ORDER BY pizza ASC;

CREATE TABLE ingredienti (
  nome varchar(50)  NOT NULL,
  quantita int NOT NULL,
  PRIMARY KEY (nome)
);

CREATE TABLE ricetta (
  nome varchar(50)  NOT NULL,
  prezzo float NOT NULL,
  PRIMARY KEY (nome)
);

delimiter //
create procedure reset()
begin

	truncate ordine;
	truncate ordine2;
	update pizza
	set quantita = 0;

end //
delimiter ;

/* user */
create user 'utente'@'localhost' identified by 'utente';
grant select on pdm.* to 'utente'@'localhost';
grant select on pdm.stats_pizza to 'utente'@'localhost';
grant update on pdm.pizza to 'utente'@'localhost';
grant insert, update on pdm.ordine to 'utente'@'localhost';
grant insert, update on pdm.ordine2 to 'utente'@'localhost';
