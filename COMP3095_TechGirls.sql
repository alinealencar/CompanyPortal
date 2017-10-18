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
	password varchar(20)	NOT NULL
);

INSERT INTO USERS (firstname, lastname, email, role, username, password) VALUES
(NULL, NULL, 'admin@domain.ca', NULL, 'admin', 'admin');

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
    emp_no varchar(255),
    hire_year varchar(255) NOT NULL,
    job_position varchar(255) NOT NULL,
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

CREATE TABLE GROUPS
( 
	groups_id int(11) AUTO_INCREMENT PRIMARY KEY, 
	dept_name varchar(255)	NOT NULL,
	member1 varchar(255)	NOT NULL,
    member2 varchar(255),
    member3 varchar(255),
    member4 varchar(255),
    member5 varchar(255),
    member6 varchar(255),
    dept_id_fk int(11) NOT NULL,
    FOREIGN KEY (dept_id_fk) REFERENCES DEPARTMENT(id)
);


