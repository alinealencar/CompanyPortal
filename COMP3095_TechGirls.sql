DROP DATABASE IF EXISTS COMP3095;
CREATE DATABASE IF NOT EXISTS COMP3095;
USE COMP3095;
grant all on COMP3095.* to 'admin'@'localhost' identified by 'admin'; 

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
    role varchar(20),
    username varchar(20),
    password varchar(20),
    token varchar(36),
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

CREATE TABLE REPORT_TEMPLATE
(
	template_id int(11) AUTO_INCREMENT PRIMARY KEY,
	template_name varchar(255) NOT NULL,
	template_date datetime NOT NULL,
	section1 varchar(255) NOT NULL,
	s1_criteria1 varchar(255) NOT NULL,
	s1_criteria2 varchar(255),
	s1_criteria3 varchar(255),
	s1_criteria4 varchar(255),
	s1_criteria5 varchar(255),
	s1_c1_maximum int(1) NOT NULL,
	s1_c2_maximum int(1),
	s1_c3_maximum int(1),
	s1_c4_maximum int(1),
	s1_c5_maximum int(1),
	section2 varchar(255) NOT NULL,
	s2_criteria1 varchar(255) NOT NULL,
	s2_criteria2 varchar(255),
	s2_criteria3 varchar(255),
	s2_c1_maximum int(1) NOT NULL,
	s2_c2_maximum int(1),
	s2_c3_maximum int(1),
	section3 varchar(255) NOT NULL,
	s3_criteria1 varchar(255) NOT NULL,
	s3_criteria2 varchar(255),
	s3_criteria3 varchar(255),
	s3_c1_maximum int(1) NOT NULL,
	s3_c2_maximum int(1),
	s3_c3_maximum int(1),
	dept_id_fk int(11) NOT NULL,
	FOREIGN KEY (dept_id_fk) REFERENCES DEPARTMENT(id)
);

CREATE TABLE REPORT 
(
	report_id int(11) AUTO_INCREMENT PRIMARY KEY,
	report_title varchar(255) NOT NULL,
	report_date datetime NOT NULL,
	report_type varchar(10) NOT NULL,
	s1_c1_evaluation int(1) NOT NULL,
	s1_c2_evaluation int(1),
	s1_c3_evaluation int(1),
	s1_c4_evaluation int(1),
	s1_c5_evaluation int(1),
	comment1 varchar(255) NOT NULL,
	s2_c1_evaluation int(1) NOT NULL,
	s2_c2_evaluation int(1),
	s2_c3_evaluation int(1),
	comment2 varchar(255) NOT NULL,
	s3_c1_evaluation int(1) NOT NULL,
	s3_c2_evaluation int(1),
	s3_c3_evaluation int(1),
	comment3 varchar(255) NOT NULL,
	template_id_fk int(11) NOT NULL,
	FOREIGN KEY (template_id_fk) REFERENCES REPORT_TEMPLATE(template_id)
--	groups_id_fk int(11) NOT NULL,
--	FOREIGN KEY (groups_id_fk) REFERENCES GROUPS(groups_id)
);

CREATE TABLE EMPLOYEE_REPORT
(
	employee_report_id int(11) AUTO_INCREMENT PRIMARY KEY,
	emp_id_fk int(11) NOT NULL,
    report_id_fk int(11) NOT NULL,
    FOREIGN KEY (emp_id_fk) REFERENCES EMPLOYEE(emp_id),
    FOREIGN KEY (report_id_fk) REFERENCES REPORT(report_id)
);

CREATE TABLE GROUP_REPORT
(
	group_report_id int(11) AUTO_INCREMENT PRIMARY KEY,
	group_id_fk int(11) NOT NULL,
	report_id_fk int(11) NOT NULL,
	FOREIGN KEY (group_id_fk) REFERENCES GROUPS(groups_id),
	FOREIGN KEY (report_id_fk) REFERENCES REPORT(report_id)
);

CREATE TABLE ATTENDANCE
(
	attendance_id int(11) AUTO_INCREMENT PRIMARY KEY,
	attendance_date date NOT NULL,
	dept_name VARCHAR(20) NOT NULL,
	dept_id_fk int(11) NOT NULL,
	FOREIGN KEY (dept_id_fk) REFERENCES DEPARTMENT(id)
);

CREATE TABLE EMPLOYEE_ATTENDANCE
(
	employee_attendance_id int(11) AUTO_INCREMENT PRIMARY KEY,
	emp_id_fk int(11) NOT NULL,
    attendance_id_fk int(11) NOT NULL,
    present boolean,
    FOREIGN KEY (emp_id_fk) REFERENCES EMPLOYEE(emp_id),
    FOREIGN KEY (attendance_id_fk) REFERENCES ATTENDANCE(attendance_id)
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

INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, role, username, password, token, dept_id_fk) VALUES
('Admin', 'Admin', 'admin@domain.ca', '1000000', '2015', 'Manager', 'admin', 'admin', 'admin', NULL, 1);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, role, username, password, token, dept_id_fk) VALUES
('Chace', 'Braeden', 'cbraeden@gmail.com', '1002345', '2015', 'Community Manager', 'admin', 'chaceb', 'password', NULL, 1);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, role, username, password, token, dept_id_fk) VALUES
('Chris', 'Martin', 'cmartin@gmail.com', '1002346', '2012', 'Analytics Manager', 'admin', 'chrism', 'password', NULL, 1);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, role, username, password, token, dept_id_fk) VALUES
('Beyonce', 'Knowles', 'bknowles@gmail.com', '1002347', '2015', 'Engineering Manager', 'admin', 'beyoncek', 'password', NULL, 2);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, role, username, password, token, dept_id_fk) VALUES
('Hayley', 'Williams', 'hwilliams@gmail.com', '1002348', '2013', 'Front End', NULL, NULL, NULL, NULL, 2);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, role, username, password, token, dept_id_fk) VALUES
('Arya', 'Stark', 'astark@gmail.com', '1002349', '2014', 'Back End', NULL, NULL, NULL, NULL, 2);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, role, username, password, token, dept_id_fk) VALUES
('Jaime', 'Lannister', 'jlannister@gmail.com', '1003344', '2014', 'Product Manager', 'admin', 'jaimel', 'password', NULL, 3);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, role, username, password, token, dept_id_fk) VALUES
('Jon', 'Snow', 'jsnow@gmail.com', '1003345', '2012', 'Feature Management', NULL, NULL, NULL, NULL, 3);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, role, username, password, token, dept_id_fk) VALUES
('Daenerys', 'Targaryen', 'dtargaryen@gmail.com', '1003346', '2013', 'Sales Manager', 'admin', 'daeneryst', 'password', NULL, 4);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, role, username, password, token, dept_id_fk) VALUES
('Sansa', 'Bolton', 'sbolton@gmail.com', '1003347', '2014', 'Pre-Sales', NULL, NULL, NULL, NULL, 4);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, role, username, password, token, dept_id_fk) VALUES
('Petyr', 'Baelish', 'pbaelish@gmail.com', '1003348', '2012', 'Account Management', NULL, NULL, NULL, NULL, 4);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, role, username, password, token, dept_id_fk) VALUES
('Sandor', 'Clegane', 'sclegane@gmail.com', '1003349', '2013', 'Finance Manager', 'admin', 'sandorc', 'password', NULL, 5);
INSERT INTO employee(firstname, lastname, email, emp_no, hire_year, job_position, role, username, password, token, dept_id_fk) VALUES
('Davos', 'Seaworth', 'dseaworth@gmail.com', '1003456', '2016', 'Admin', NULL, NULL, NULL, NULL, 5);

