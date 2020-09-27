CREATE DATABASE atm;
USE atm;

CREATE TABLE customer (
   id INT NOT NULL,
   customerid INT NOT NULL,
   type VARCHAR(45) NOT NULL,
   balance float NOT NULL,
   PRIMARY KEY (id)
   

);