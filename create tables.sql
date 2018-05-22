create Table EMPLOYEE
(
Fname Varchar2(25),
Minit Varchar2(20),
Lname Varchar2(25),
SSN INT PRIMARY KEY,
Bdate varchar2(25),Address Varchar2(50),
Sex varchar2(5),
Salary Int,
Super_ssn Int,Dno Int);



create Table DEPARTMENT
(
Dname Varchar2(25),
Dnumber Int,
Mgr_ssn Int,Mgr_start_date Varchar2(25),primary Key (Dnumber),
Foreign Key (Mgr_ssn) references EMPLOYEE(SSN) ON DELETE CASCADE
); 



create Table DEPT_LOCATIONS
(
Dnumber Int,
Dlocation Varchar2(20),
Primary key (Dnumber,Dlocation),
Foreign Key (Dnumber) references DEPARTMENT(Dnumber) ON DELETE CASCADE
);

create TABLE PROJECT(Pname varchar2(20) , Pnumber INT  PRIMARY KEY, Plocation varchar2(20), Dnum INT, Constraint fk_dnumber FOREIGN KEY(Dnum) REFERENCES DEPARTMENT(Dnumber) );

create TABLE WORKS_ON(Essn Int, Pno INT, PRIMARY  KEY(Essn,Pno),Hours DOUBLE PRECISION, constraint fk1_wkson FOREIGN KEY(Essn) REFERENCES EMPLOYEE(Ssn) ON DELETE CASCADE,constraint fk2_wkson FOREIGN KEY(Pno) REFERENCES Project(Pnumber) ON DELETE CASCADE );

create Table DEPENDENT(Essn INT, Dependent_name varchar2(20), sex  varchar2(5), Bdate Date, Relationship varchar2(20), PRIMARY KEY(Essn,Dependent_name), constraint fk_dep FOREIGN KEY(Essn) REFERENCES EMPLOYEE(Ssn) ON DELETE CASCADE);

alter table employee add constraint fk_dno Foreign Key(Dno) references department(Dnumber);