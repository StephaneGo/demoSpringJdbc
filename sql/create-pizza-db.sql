
DROP TABLE IF EXISTS pizza;

 create table pizza(  
    id int NOT NULL PRIMARY KEY,  
    nom nvarchar(100),  
    prix decimal(5,2)  
    );  
	
	
insert into pizza (id, nom, prix) values (1, 'CALZONE', 13.60);	
insert into pizza (id, nom, prix) values (2, 'DELLA NONA', 14.90);
insert into pizza (id, nom, prix) values (3, 'VULCANO', 14.10);