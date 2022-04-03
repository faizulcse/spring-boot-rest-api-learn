DROP DATABASE IF EXISTS restapi;
CREATE DATABASE restapi;
USE restapi;

CREATE TABLE employee (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    age INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    department VARCHAR(255) NOT NULL
);
INSERT INTO employee (name, age, email, department) VALUES ("faizul", 26, "faizulcse@gmail.com", "it");
INSERT INTO employee (name, age, email, department) VALUES ("appon", 27, "aaponcse@gmail.com", "math");
INSERT INTO employee (name, age, email, department) VALUES ("islam", 28, "islam@gmail.com", "english");