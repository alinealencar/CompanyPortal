DROP DATABASE IF EXISTS COMP3095;
CREATE DATABASE IF NOT EXISTS COMP3095;
USE COMP3095;
grant all on COMP3095.* to 'admin'@'localhost' identified by 'admin'; 

CREATE TABLE APPUSERS 
( 
	id int(11) AUTO_INCREMENT PRIMARY KEY, 
	firstname varchar(255),
	lastname varchar(255),
	email varchar(255), 
	role varchar(20),
	username varchar(20)	NOT NULL,
	password varchar(20)	NOT NULL,
	token varchar(36)
);

INSERT INTO APPUSERS (firstname, lastname, email, role, username, password, token) VALUES
('Jane', 'Smith', 'admin@domain.ca', NULL, 'admin', 'admin', NULL);

CREATE TABLE DEPARTMENT
( 
	id int(11) AUTO_INCREMENT PRIMARY KEY, 
	dept_name varchar(255)	NOT NULL,
	location varchar(255)	NOT NULL
);

CREATE TABLE EMPLOYEE
( 
	emp_id int(11) AUTO_INCREMENT PRIMARY KEY, 
	firstname varchar(255)	NOT NULL,
	lastname varchar(255)	NOT NULL,
	email varchar(255) NOT NULL,
    	emp_no varchar(255),
    	hire_year varchar(255) NOT NULL,
    	job_position varchar(255) NOT NULL,
    	dept_id_fk int(11) NOT NULL,
    	FOREIGN KEY (dept_id_fk) REFERENCES DEPARTMENT(id)
);

CREATE TABLE GROUPS
( 
	groups_id int(11) AUTO_INCREMENT PRIMARY KEY, 
	dept_name varchar(255)	NOT NULL,
	group_name varchar(255) NOT NULL,
	member1 varchar(255)	NOT NULL,
    	member2 varchar(255),
    	member3 varchar(255),
    	member4 varchar(255),
    	member5 varchar(255),
    	member6 varchar(255),
    	dept_id_fk int(11) NOT NULL,
    	FOREIGN KEY (dept_id_fk) REFERENCES DEPARTMENT(id)
);

CREATE TABLE EMPLOYEE_GROUPS
(
	employee_groups_id int(11) AUTO_INCREMENT PRIMARY KEY,
    	emp_id_fk int(11) NOT NULL,
    	groups_id_fk int(11) NOT NULL,
    	FOREIGN KEY (emp_id_fk) REFERENCES EMPLOYEE(emp_id),
    	FOREIGN KEY (groups_id_fk) REFERENCES GROUPS(groups_id)
);

INSERT INTO DEPARTMENT (dept_name, location) VALUES
('Marketing', 'C110');
INSERT INTO DEPARTMENT (dept_name, location) VALUES
('Engineering', 'C220');
INSERT INTO DEPARTMENT (dept_name, location) VALUES
('Product', 'C230');
INSERT INTO DEPARTMENT (dept_name, location) VALUES
('Sales', 'C310');
INSERT INTO DEPARTMENT (dept_name, location) VALUES
('Finance & Admin', 'C330');

INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, dept_id_fk) VALUES
('Chace', 'Braeden', 'cbraeden@gmail.com', '1002345', '2015', 'Community Manager', 1);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, dept_id_fk) VALUES
('Chris', 'Martin', 'cmartin@gmail.com', '1002346', '2012', 'Analytics Manager', 1);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, dept_id_fk) VALUES
('Beyonce', 'Knowles', 'bknowles@gmail.com', '1002347', '2015', 'CTO', 2);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, dept_id_fk) VALUES
('Hayley', 'Williams', 'hwilliams@gmail.com', '1002348', '2013', 'Front End', 2);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, dept_id_fk) VALUES
('Arya', 'Stark', 'astark@gmail.com', '1002349', '2014', 'Back End', 2);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, dept_id_fk) VALUES
('Jaime', 'Lannister', 'jlannister@gmail.com', '1003344', '2014', 'Designer', 3);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, dept_id_fk) VALUES
('Jon', 'Snow', 'jsnow@gmail.com', '1003345', '2012', 'Feature Management', 3);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, dept_id_fk) VALUES
('Daenerys', 'Targaryen', 'dtargaryen@gmail.com', '1003346', '2013', 'Introductory Agreement', 4);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, dept_id_fk) VALUES
('Sansa', 'Bolton', 'sbolton@gmail.com', '1003347', '2014', 'Pre-Sales', 4);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, dept_id_fk) VALUES
('Petyr', 'Baelish', 'pbaelish@gmail.com', '1003348', '2012', 'Account Management', 4);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, dept_id_fk) VALUES
('Sandor', 'Clegane', 'sclegane@gmail.com', '1003349', '2013', 'CFO', 5);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, dept_id_fk) VALUES
('Davos', 'Seaworth', 'dseaworth@gmail.com', '1003456', '2016', 'Admin', 5);
