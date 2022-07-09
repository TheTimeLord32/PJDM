insert into ordine values
(0, ?, ?, ?, ?, ?);

insert into ordine1 values
(1, 3, "Margherita", "4 Formaggi", "Boscaiola", null, null, 2, "Crocchette", "Suppli cassico", null, null, null, 1, "Acqua grande naturale", null, null, null, null, "Prova", 0);

SELECT x, y, etc, CASE WHEN field IS NOT NULL THEN field ELSE NULL END AS hehe FROM table;

select COALESCE (id_ordine, NULL), COALESCE (num_pizze, NULL), COALESCE (pizza1, NULL), COALESCE (pizza2, NULL), COALESCE (pizza3, NULL), COALESCE (pizza4, NULL), COALESCE (pizza5, NULL), COALESCE (pizza6, NULL)

delete from ordine where id_ordine = "id_ordine";

select ordine.orario, count(orario) from ordine group by orario;

select ordine1.pizza1, ordine1.pizza2, ordine1.pizza3, ordine1.pizza4, ordine1.pizza5, count(pizza1), count(pizza2), count(pizza3), count(pizza4), count(pizza5) from ordine1;

select * from ordine, ordine1 where ordine.id_ordine = ordine1.id_ordine;

DECLARE @counter int;
SET @counter = 1;
UPDATE prova set quantita=@counter+1 where nome = 'Margherita';
SET @counter = counter = @counter + 1;


CREATE USER 'utente'@'localhost' IDENTIFIED BY 'password';
GRANT PRIVILEGE ON pizza.* TO 'utente'@'localhost';

alter table pizza
add column quantita int;
add column ingredienti1 varchar(50), 
add column ingredienti2 varchar(50),
add column ingredienti3 varchar(50),
add column ingredienti4 varchar(50),
add column ingredienti5 varchar(50);

create table ingredienti(
	nome varchar(50) primary key,
	quantita int
);

insert into ingredienti values
('Alici', 10),
('Carciofi', 10),
('Funghi', 10),
('Melanzane', 10),
('Mozzarella', 10),
('Olio', 10),
('Olive', 10),
('Origano', 10),
('Patate', 10),
('Pomodoro', 10),
('Prosciutto', 10),
('Salame', 10),
('Salsiccia', 10),
('Uovo', 10),
('Zucchine', 10);

CREATE TABLE pizza(
	nome varchar(50) primary key,
	prezzo float,
	ingredienti1 varchar(50),
	ingredienti2 varchar(50),
	ingredienti3 varchar(50),
	ingredienti4 varchar(50),
	ingredienti5 varchar(50),
	quantita int
);


INSERT INTO pizza VALUES 
('4 Stagioni', 6.5, 'Mozzarella', 'Pomodoro', 'Olio', 'Olive', 'Prosciutto', 0),
('Bismarck', 6, 'Mozzarella', 'Pomodoro', 'Olio', 'Prosciutto', 'Uovo', 0),
('Boscaiola', 6.5, 'Mozzarella', 'Olio', 'Salsiccia', 'Funghi', NULL, 0),
('Bufalona', 7, 'Pomodoro', 'Olive', 'Alici', NULL, NULL, 0),
('Capricciosa', 7.5, 'Mozzarella', 'Pomodoro', 'Olio', 'Funghi', 'Carciofi', 0),
('Carbonara', 7, 'Mozzarella', 'Pomodoro', 'Olio', 'Pancetta', 'Uovo', 0),
('Crostino', 5, 'Mozzarella', 'Olio', 'Patate', 'Prosciutto', NULL, 0),
('Diavola', 6, 'Mozzarella', 'Pomodoro', 'Olio', 'Salame', 'Origano', 0),
('Margherita', 4.5, 'Mozzarella', 'Pomodoro', 'Olio', NULL, NULL, 0),
('Napoli', 4.5, 'Mozzarella', 'Pomodoro', 'Olio', 'Alici', NULL, 0),
('Ortolana', 5.5, 'Mozzarella', 'Olio', 'Pomodoro', 'Melanzane', 'Zucchine', 0),
('Parmigiana', 6, 'Mozzarella', 'Pomodoro', 'Olio', 'Melanzane', NULL, 0),
('Patate', 4.5, 'Mozzarella', 'Olio', 'Patate', NULL, NULL, 0),
('Porcini', 6.5, 'Mozzarella', 'Olio', 'Funghi', NULL, NULL, 0),
('Rossa', 3.5, 'Pomodoro', 'Olio', NULL, NULL, NULL, 0);

/* resettare auto increment*/
truncate table ordine;
truncate table ordine1;
/* resettare auto increment */


/* conosco gli ingredienti delle pizze prese in ordine*/
CREATE VIEW ingredientiOrdine1 AS
SELECT ordine1.pizza1, ordine1.pizza2, ordine1.pizza3, ordine1.pizza4, ordine1.pizza5, pizza.ingredienti1, pizza.ingredienti2, pizza.ingredienti3, pizza.ingredienti4, pizza.ingredienti5 FROM ordine1, pizza WHERE ordine1.pizza1 = pizza.nome OR ordine1.pizza2 = pizza.nome OR ordine1.pizza3 = pizza.nome OR ordine1.pizza4 = pizza.nome OR ordine1.pizza5 = pizza.nome;

/* conto quanti ingredienti ho usato */
CREATE VIEW countIngredienti AS 
SELECT ingredienti1, count(ingredienti1) as quantIngr1, ingredienti2, count(ingredienti2) as quantIngr2, ingredienti3, count(ingredienti3) as quantIngr3, ingredienti4, count(ingredienti4) as quantIngr4, ingredienti5, count(ingredienti5) as quantIngr5 from ingredientiOrdine1;

/* associo gli ingredienti usati con l'inventario */
create view numIngredienti as
select * from ingredienti, countIngredienti where ingredienti.nome = countIngredienti.ingredienti1 or ingredienti.nome = countIngredienti.ingredienti2 or ingredienti.nome = countIngredienti.ingredienti3 or ingredienti.nome = countIngredienti.ingredienti4 or ingredienti.nome = countIngredienti.ingredienti5;

/* update ingredienti set ingredienti.quantita = ingredienti.quantita - countIngredienti.count from numIngredienti where numIngredienti.nome = numIngredienti.ingredienti1 or numIngredienti.nome = numIngredienti.ingredienti2 or numIngredienti.nome = numIngredienti.ingredienti3 or numIngredienti.nome = numIngredienti.ingredienti4 or numIngredienti.nome = numIngredienti.ingredienti5; */

select * from numIngredienti where nome=ingredienti1;
update numIngredienti set quantita=quantita-quantIngr1 where nome=ingredienti1;
/* aggiorno la quantita dell'inventario in base alla quantità usata*/

create table pizzaNuova(
	codice int primary key auto_increment,
	nome varchar(50),
	prezzo float,
	quantita int
);

INSERT INTO pizzanuova VALUES 
(0, '4 Stagioni', 6.5, 0),
(0, 'Bismarck', 6, 0),
(0, 'Boscaiola', 6.5, 0),
(0, 'Bufalona', 7, 0),
(0, 'Capricciosa', 7.5, 0),
(0, 'Carbonara', 7, 0),
(0, 'Crostino', 5, 0),
(0, 'Diavola', 6, 0),
(0, 'Margherita', 4.5, 0),
(0, 'Napoli', 4.5, 0),
(0, 'Ortolana', 5.5, 0),
(0, 'Parmigiana', 6, 0),
(0, 'Patate', 4.5, 0),
(0, 'Porcini', 6.5, 0),
(0, 'Rossa', 3.5, 0);

create table ingredientiNuova(
	id int primary key auto_increment,
	nome varchar(50),
	quantita int
);

insert into ingredientinuova values
(1, 'Alici', 10),
(2, 'Carciofi', 10),
(3, 'Funghi', 10),
(4, 'Melanzane', 10),
(5, 'Mozzarella', 10),
(6, 'Olio', 10),
(7, 'Olive', 10),
(8, 'Origano', 10),
(9, 'Patate', 10),
(10, 'Pomodoro', 10),
(11, 'Prosciutto', 10),
(12, 'Salame', 10),
(13, 'Salsiccia', 10),
(14, 'Uovo', 10),
(15, 'Zucchine', 10);

create table ricetta(
	codice int,
	id int,
	quantita int
);

insert into ricetta values
(1, 5, 1),
(1, 10, 1),
(1, 6, 1),
(1, 7, 1),
(1, 11, 1);

select * from ricetta, pizzanuova, ingredientinuova where ricetta.codice=pizzanuova.codice and ricetta.id=ingredientinuova.id; 
/* trovo la pizza con i suoi ingredienti */
