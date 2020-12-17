create database clinicms;
use clinicms;
create table clinicms.appointment (tokenid int, mobile varchar(12), name varchar(40), gender enum('M','F') ,birthdate varchar(12), age int,bloodgroup ENUM('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-'));
ALTER TABLE `clinicms`.`appointment` 
CHANGE COLUMN `tokenid` `tokenid` INT NOT NULL AUTO_INCREMENT ,
ADD PRIMARY KEY (`tokenid`);
;
create table clinicms.credential (designation varchar(30), password varchar(30));
ALTER TABLE `clinicms`.`credential` 
ADD UNIQUE INDEX `designation_UNIQUE` (`designation` ASC);
;
insert into clinicms.credential (designation , password)
values
	('Receptionist','12345'),
	('Doctor','12345'),
	('Chemist','12345');
create table clinicms.medicineinfo (medicinename varchar(50), medicinetype enum('Tablet', 'Syrup', 'Ointment', 'Spray', 'Gargle', 'Capsule'), medicineprice double);
ALTER TABLE `clinicms`.`medicineinfo` 
CHANGE COLUMN `medicinename` `medicinename` VARCHAR(50) NOT NULL ,
CHANGE COLUMN `medicinetype` `medicinetype` ENUM('Tablet', 'Syrup', 'Ointment', 'Spray', 'Gargle', 'Capsule') NOT NULL ,
CHANGE COLUMN `medicineprice` `medicineprice` DOUBLE NOT NULL DEFAULT '0' ,
ADD PRIMARY KEY (`medicinename`),
ADD UNIQUE INDEX `medicinename_UNIQUE` (`medicinename` ASC) VISIBLE;
;
insert into clinicms.medicineinfo (medicinename,medicinetype,medicineprice)
values
('Benadryl 150ml', 'Syrup', '108'),
('Betadine Mint Gargle 100ml', 'Gargle', '214'),
('Betadine Ointment 15gm', 'Ointment', '85.5'),
('Cheston Cold 60ml', 'Syrup', '44'),
('Cipladine 10gm', 'Ointment', '24'),
('Cofsils Experdine 100ml', 'Gargle', '170'),
('Crocin 650mg', 'Tablet', '30.24'),
('Meimox 500mg', 'Capsule', '3.16'),
('Moov 15gm', 'Spray', '60'),
('Paracetamol 500mg', 'Tablet', '9.79'),
('Rantac 150mg', 'Tablet', '2.46'),
('Relispray 95gm', 'Spray', '237.6'),
('Volini 15gm', 'Ointment', '48'),
('Zecuf 100ml', 'Syrup', '95.34');

create table clinicms.patientdetails (mobile varchar(11), name varchar(40), gender enum('M','F'), age int, birthdate varchar(12), bloodgroup ENUM('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-'), address varchar(100));
ALTER TABLE `clinicms`.`patientdetails` 
CHANGE COLUMN `mobile` `mobile` VARCHAR(11) NOT NULL ,
ADD PRIMARY KEY (`mobile`),
ADD UNIQUE INDEX `mobile_UNIQUE` (`mobile` ASC);

create table clinicms.billinfo(
billno int NOT NULL AUTO_INCREMENT,
mobile varchar(12),
nettotal double,
PRIMARY KEY(billno)
);

alter table clinicms.billinfo auto_increment=100100;

ALTER TABLE `clinicms`.`billinfo` 
ADD COLUMN `date` VARCHAR(10) NOT NULL AFTER `nettotal`;
