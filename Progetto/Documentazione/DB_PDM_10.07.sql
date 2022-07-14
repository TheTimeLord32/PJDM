CREATE DATABASE pdm;
USE pdm;

CREATE TABLE pizza (
  nome varchar(50) NOT NULL,
  prezzo float NULL,
  PRIMARY KEY (nome)
);

INSERT INTO pizza VALUES
('',0),
('4 Stagioni',6.5),
('Bismarck',6),
('Boscaiola',6.5),
('Bufalona',7),
('Capricciosa',7.5),
('Carbonara',7),
('Crostino',5),
('Diavola',6),
('Margherita',4.5),
('Napoli',4.5),
('Ortolana',5.5),
('Parmigiana',6),
('Patate',4.5),
('Porcini',6.5),
('Rossa',3.5);

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
  conto float NULL,
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
WHERE pizza != ''
GROUP BY pizza 
ORDER BY pizza ASC;

CREATE TABLE ingrediente (
  nome varchar(50)  NOT NULL,
  quantita int NOT NULL,
  PRIMARY KEY (nome)
);

INSERT INTO ingrediente VALUES
('Alici', 200),
('Carciofi', 200),
('Funghi', 200),
('Melanzane', 200),
('Mozzarella', 200),
('Olio', 200),
('Olive', 200),
('Origano', 200),
('Patate', 200),
('Pomodoro', 200),
('Prosciutto', 200),
('Salame', 200),
('Salsiccia', 200),
('Uovo', 200),
('Zucchine', 200);

CREATE TABLE ricetta (
	codice_pizza varchar(50) references pizza(nome) on update cascade on delete no action,
	codice_ingrediente varchar(50) references ingrediente(nome) on update cascade on delete no action,
	quantita int
);

INSERT INTO ricetta VALUES
('4 Stagioni', 'Mozzarella', 2),
('4 Stagioni', 'Pomodoro', 3),
('4 Stagioni', 'Olio', 5),

('Bismarck', 'Prosciutto', 4),
('Bismarck', 'Uovo', 2),
('Bismarck', 'Olio', 3),

('Boscaiola', 'Mozzarella', 3),
('Boscaiola', 'Salsiccia', 3),
('Boscaiola', 'Funghi', 3),

('Bufalona', 'Pomodoro', 5),
('Bufalona', 'Olive', 4),
('Bufalona', 'Alici', 3),

('Capricciosa', 'Mozzarella', 3),
('Capricciosa', 'Funghi', 4),
('Capricciosa', 'Carciofi', 2),

('Carbonara', 'Pomodoro', 3),
('Carbonara', 'Pancetta', 6),
('Carbonara', 'Uovo', 7),

('Crostino', 'Mozzarella', 2),
('Crostino', 'Patate', 4),
('Crostino', 'Prosciutto', 6),

('Diavola', 'Pomodoro', 7),
('Diavola', 'Salame', 4),
('Diavola', 'Origano', 5),

('Margherita', 'Mozzarella', 8),
('Margherita', 'Pomodoro', 8),
('Margherita', 'Olio', 6),

('Napoli', 'Mozzarella', 5),
('Napoli', 'Pomodoro', 6),
('Napoli', 'Alici', 3),

('Ortolana', 'Pomodoro', 4),
('Ortolana', 'Melanzane', 6),
('Ortolana', 'Zucchine', 3),

('Parmigiana', 'Mozzarella', 2),
('Parmigiana', 'Pomodoro', 4),
('Parmigiana', 'Melanzane', 7),

('Patate', 'Mozzarella', 3),
('Patate', 'Olio', 4),
('Patate', 'Patate', 7),

('Porcini', 'Mozzarella', 5),
('Porcini', 'Olio', 6),
('Porcini', 'Funghi', 7),

('Rossa','Pomodoro', 7),
('Rossa','Olio', 4),
('Rossa','Origano', 3);

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
