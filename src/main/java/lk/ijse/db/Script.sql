

DROP DATABASE IF EXISTS ims;
CREATE DATABASE IF NOT EXISTS ims;
SHOW DATABASES;
USE ims;

CREATE TABLE IF NOT EXISTS Employee(
    emp_id VARCHAR(15)PRIMARY KEY,
    name VARCHAR(50),
    gmail TEXT,
    contactNo INT(15),
    nic VARCHAR (15),
    address TEXT,
    position VARCHAR(20),
    registrationDate DATE,
    bankAccountNum VARCHAR(30),
    bankBranchName VARCHAR(30),
    age INT(3),
    gendar VARCHAR(10),
    image longblob

    );
dr

CREATE TABLE IF NOT EXISTS Employee_Attendance(
                                                  date DATE,
                                                  time TIME,
                                                  emp_id VARCHAR(15),
    CONSTRAINT FOREIGN KEY (emp_id) REFERENCES Employee(emp_id) ON DELETE CASCADE ON UPDATE CASCADE
    );
drop table Employee_Attendance;
drop table Employee;

CREATE TABLE IF NOT EXISTS Subject(
    sub_id VARCHAR(15) PRIMARY KEY ,
    subjectName VARCHAR(20),
    tut_id VARCHAR(15)
    );

CREATE TABLE IF NOT EXISTS Tutor(
    tut_id VARCHAR(15) PRIMARY KEY ,
    tutorName VARCHAR(50),
    sub_id VARCHAR(15),
    CONSTRAINT FOREIGN KEY (sub_id) REFERENCES Subject(sub_id) ON DELETE CASCADE ON UPDATE CASCADE

    );

ALTER TABLE Subject
    ADD FOREIGN KEY (tut_id) REFERENCES Tutor(tut_id);

INSERT INTO tutor VALUES('T001','Nill Wauliyada','MM001'),('T002','Senaka Idangedara','EE001'),('T003','Mohmed','TT001'),('T004','Presana Herath','SS001'),('T005','Presath Kumara','BS001'),('T006','Tharind Wijerathna','AC001'),('T007','Nimal Presath','EM001');


CREATE TABLE IF NOT EXISTS Class(
    class_id VARCHAR(15) PRIMARY KEY ,
    tut_id VARCHAR(15),
    CONSTRAINT FOREIGN KEY (tut_id) REFERENCES Tutor(tut_id) ON DELETE CASCADE ON UPDATE CASCADE

    );

INSERT INTO class VALUES('G6/MM001','T001','Grade6-Maths'),('G6/SS001','T004','Grade6-Science'),('G6/EE001','T002','Grade6-English'),('G6/TT001','T003','Grade6-Tamil'),('G7/MM001','T001','Grade7-Maths'),('G7/SS001','T004','Grade7-Science'),('G7/EE001','T002','Grade7-English'),('G7/TT001','T003','Grade7-Tamil'),('G8/MM001','T001','Grade8-Maths'),('G8/SS001','T004','Grade8-Science'),('G8/EE001','T002','Grade8-English'),('G8/TT001','T003','Grade8-Tamil');

CREATE TABLE IF NOT EXISTS class_Details(
    full_id VARCHAR(15) PRIMARY KEY ,
    stu_id VARCHAR(15),
    class_id VARCHAR(15),
    stu_name VARCHAR(50),
    CONSTRAINT FOREIGN KEY (stu_id) REFERENCES studentfull_details(stu_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (class_id) REFERENCES Class(class_id) ON DELETE CASCADE ON UPDATE CASCADE

    );

create table studentfull_details(
                                    stu_id VARCHAR(15)PRIMARY KEY,
                                    reg_id VARCHAR(15),
                                    name VARCHAR(50),
                                    regDate VARCHAR(15) ,
                                    Student_gmail VARCHAR(50),
                                    Student_contactNo VARCHAR(15),
                                    sub_id VARCHAR(10),
                                    address VARCHAR(50),
                                    age VARCHAR(3),
                                    grade VARCHAR(3),
                                    perant_id VARCHAR(15),
                                    Perant_Name VARCHAR(50),
                                    Perant_Gmail VARCHAR(50),
                                    Perant_contactNo VARCHAR(15)

);

INSERT INTO class_Details VALUES('S006/G6','S006','GS/MM001','Kavindu wijerathna');

INSERT INTO class_Details VALUES('S006/G6','S006','G6/MM001','Kavindu wijerathna');

select *from class_Details;




CREATE TABLE IF NOT EXISTS Stu_Attendance(
    name VARCHAR(100),
    date DATE,
    full_id VARCHAR(15),
    stu_id VARCHAR(15),
    class_id VARCHAR(15),
    time TIME,
    CONSTRAINT FOREIGN KEY (full_id) REFERENCES class_Details(full_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (stu_id) REFERENCES studentfull_details(stu_id) ON DELETE CASCADE ON UPDATE CASCADE

    );

CREATE TABLE IF NOT EXISTS class_Payment(
    payment_Id VARCHAR(10) PRIMARY KEY ,
    Class_Id VARCHAR(10),
    stu_Id VARCHAR(10),
    name VARCHAR(200),
    paymentMonth VARCHAR(20),
    date DATE,
    full_id VARCHAR(15),
    amount double,
    CONSTRAINT FOREIGN KEY (full_id) REFERENCES class_details(full_id) ON DELETE CASCADE ON UPDATE CASCADE

    );

Drop TABLE class_payment;
CREATE TABLE IF NOT EXISTS course(
    cus_id VARCHAR(15) PRIMARY KEY ,
    cus_name VARCHAR(15),
    cou_fee VARCHAR(20)

    );

CREATE TABLE IF NOT EXISTS course_details(
    cusDfull_id VARCHAR(15) PRIMARY KEY ,
    cus_id VARCHAR(15),
    stu_id VARCHAR(15),
    stu_name VARCHAR(50),
    cus_name VARCHAR(15),
    paidCou_fee DOUBLE,
    CONSTRAINT FOREIGN KEY (stu_id) REFERENCES studentfull_details(stu_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (cus_id) REFERENCES course(cus_id) ON DELETE CASCADE ON UPDATE CASCADE

    );

CREATE TABLE IF NOT EXISTS Course_payment(
    cusPay_id INT(15) PRIMARY KEY AUTO_INCREMENT ,
    payment DOUBLE,
    Date DATE,
    time TIME,
    cusDfull_id VARCHAR(15),
    CONSTRAINT FOREIGN KEY (cusDfull_id) REFERENCES course_details(cusDfull_id) ON DELETE CASCADE ON UPDATE CASCADE
    );

CREATE TABLE IF NOT EXISTS Employee_Attendance(
    attendnceID INT(15) PRIMARY KEY AUTO_INCREMENT ,
    attendanceMarkID VARCHAR(20),
    emp_id VARCHAR(15),
    Date DATE,
    time TIME,
    CONSTRAINT FOREIGN KEY (emp_id) REFERENCES employee(emp_id) ON DELETE CASCADE ON UPDATE CASCADE
    );

CREATE TABLE IF NOT EXISTS user(
    userID VARCHAR(10) primary key,
    password VARCHAR(20),
    userName VARCHAR(50),
    image longblob

    );

CREATE TABLE IF NOT EXISTS institute_details (
    gmail VARCHAR(100),
    contact VARCHAR(50),
    facebook VARCHAR(10),
    hall VARCHAR(5)

    );

CREATE TABLE IF NOT EXISTS DayShedule (
    ClassName VARCHAR(100),
    Date DATE,
    startTime VARCHAR(20),
    EndTime VARCHAR(20)

    );

CREATE TABLE IF NOT EXISTS institute_Details(
    contact VARCHAR(100),
    gmail VARCHAR(100),
    fb VARCHAR(100)
    );

CREATE TABLE IF NOT EXISTS course_Attendance(
    attendanceID VARCHAR(10) PRIMARY KEY ,
    cusfull_id VARCHAR(20),
    date DATE,
    time time,
    CONSTRAINT FOREIGN KEY (cusfull_id) REFERENCES course_details (cusDfull_id) ON DELETE CASCADE ON UPDATE CASCADE

    );

CREATE TABLE IF NOT EXISTS notice(
    notice VARCHAR(500),
    date DATE

    );
