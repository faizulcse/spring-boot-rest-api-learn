DROP DATABASE IF EXISTS restapi;
CREATE DATABASE IF NOT EXISTS restapi;
USE restapi;

DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   age INT NOT NULL,
   name VARCHAR(255) NOT NULL,
   location VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   department VARCHAR(255) NOT NULL
);
INSERT INTO employee (name, age, location, email, department) VALUES ('faizul', 26, 'Dhaka', 'faizulcse@gmail.com', 'it');
INSERT INTO employee (name, age, location, email, department) VALUES ('appon', 27, 'Khulna', 'aaponcse@gmail.com', 'math');
INSERT INTO employee (name, age, location, email, department) VALUES ('islam', 28, 'Barisal', 'islam@gmail.com', 'english');