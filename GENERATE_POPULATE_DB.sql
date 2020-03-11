-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema wannatryschema
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema wannatryschema
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS wannatryschema;
CREATE SCHEMA IF NOT EXISTS `wannatryschema` DEFAULT CHARACTER SET utf8 ;
USE `wannatryschema` ;

-- -----------------------------------------------------
-- Table `wannatryschema`.`staff`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS `wannatryschema`.`staff` (
  `password` VARCHAR(45) NOT NULL,
  `staff_id` VARCHAR(255) NOT NULL,
  `staff_firstname` VARCHAR(255) NOT NULL,
  `staff_surname` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`staff_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wannatryschema`.`modules`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wannatryschema`.`modules` ;

CREATE TABLE IF NOT EXISTS `wannatryschema`.`modules` (
  `module_id` VARCHAR(8) NOT NULL,
  `module_name` VARCHAR(255) NOT NULL,
  `lecturer_id` VARCHAR(8) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `end_date` DATE NOT NULL,
  `price` DOUBLE NOT NULL,
  `start_date` DATE NOT NULL,
  PRIMARY KEY (`module_id`),
  UNIQUE INDEX `module_name_UNIQUE` (`module_name` ASC) VISIBLE,
  UNIQUE INDEX `module_id_UNIQUE` (`module_id` ASC) VISIBLE,
  INDEX `staff_id_idx` (`lecturer_id` ASC) VISIBLE,
  CONSTRAINT `staff_id`
    FOREIGN KEY (`lecturer_id`)
    REFERENCES `wannatryschema`.`staff` (`staff_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wannatryschema`.`students`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wannatryschema`.`students` ;

CREATE TABLE IF NOT EXISTS `wannatryschema`.`students` (
  `student_id` VARCHAR(8) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `student_firstname` VARCHAR(45) NOT NULL,
  `student_surname` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `amount_paid` DOUBLE NOT NULL,
  `dob` DATE NOT NULL,
  `gender` VARCHAR(255) NOT NULL,
  `stage` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wannatryschema`.`module_enrolments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wannatryschema`.`module_enrolments` ;

CREATE TABLE IF NOT EXISTS `wannatryschema`.`module_enrolments` (
  `module_id` VARCHAR(8) NOT NULL,
  `student_id` VARCHAR(8) NOT NULL,
  `relation_id` VARCHAR(17) NOT NULL,
  PRIMARY KEY (`relation_id`),
  INDEX `module_id_idx` (`module_id` ASC) VISIBLE,
  INDEX `student_id_idx` (`student_id` ASC) VISIBLE,
  CONSTRAINT `module_id`
    FOREIGN KEY (`module_id`)
    REFERENCES `wannatryschema`.`modules` (`module_id`),
  CONSTRAINT `student_id`
    FOREIGN KEY (`student_id`)
    REFERENCES `wannatryschema`.`students` (`student_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 94663705
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wannatryschema`.`student_module_grades`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wannatryschema`.`student_module_grades` ;

CREATE TABLE IF NOT EXISTS `wannatryschema`.`student_module_grades` (
  `module_id` VARCHAR(8) NOT NULL,
  `student_id` VARCHAR(8) NOT NULL,
  `percentage` DOUBLE NOT NULL,
  `letter_grade` VARCHAR(2) NOT NULL,
  `relation_id` VARCHAR(17) NOT NULL,
  PRIMARY KEY (`relation_id`),
  INDEX `module_idx` (`module_id` ASC) VISIBLE,
  INDEX `student_idx` (`student_id` ASC) VISIBLE,
  CONSTRAINT `module`
    FOREIGN KEY (`module_id`)
    REFERENCES `wannatryschema`.`modules` (`module_id`),
  CONSTRAINT `student`
    FOREIGN KEY (`student_id`)
    REFERENCES `wannatryschema`.`students` (`student_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `wannatryschema` ;

-- -----------------------------------------------------
-- generate staff data
-- -----------------------------------------------------
DROP function if exists generate_fname;
DELIMITER $$
CREATE FUNCTION generate_fname () RETURNS varchar(255)
BEGIN
	RETURN ELT(FLOOR(1 + (RAND() * (100-1))), "James","Mary","John","Patricia","Robert","Linda","Michael","Barbara","William","Elizabeth","David","Jennifer","Richard","Maria","Charles","Susan","Joseph","Margaret","Thomas","Dorothy","Christopher","Lisa","Daniel","Nancy","Paul","Karen","Mark","Betty","Donald","Helen","George","Sandra","Kenneth","Donna","Steven","Carol","Edward","Ruth","Brian","Sharon","Ronald","Michelle","Anthony","Laura","Kevin","Sarah","Jason","Kimberly","Matthew","Deborah","Gary","Jessica","Timothy","Shirley","Jose","Cynthia","Larry","Angela","Jeffrey","Melissa","Frank","Brenda","Scott","Amy","Eric","Anna","Stephen","Rebecca","Andrew","Virginia","Raymond","Kathleen","Gregory","Pamela","Joshua","Martha","Jerry","Debra","Dennis","Amanda","Walter","Stephanie","Patrick","Carolyn","Peter","Christine","Harold","Marie","Douglas","Janet","Henry","Catherine","Carl","Frances","Arthur","Ann","Ryan","Joyce","Roger","Diane");
END$$

DELIMITER ;

DROP function if exists generate_lname;
DELIMITER $$
CREATE FUNCTION generate_lname () RETURNS varchar(255)
BEGIN
	RETURN ELT(FLOOR(1 + (RAND() * (100-1))), "Smith","Johnson","Williams","Jones","Brown","Davis","Miller","Wilson","Moore","Taylor","Anderson","Thomas","Jackson","White","Harris","Martin","Thompson","Garcia","Martinez","Robinson","Clark","Rodriguez","Lewis","Lee","Walker","Hall","Allen","Young","Hernandez","King","Wright","Lopez","Hill","Scott","Green","Adams","Baker","Gonzalez","Nelson","Carter","Mitchell","Perez","Roberts","Turner","Phillips","Campbell","Parker","Evans","Edwards","Collins","Stewart","Sanchez","Morris","Rogers","Reed","Cook","Morgan","Bell","Murphy","Bailey","Rivera","Cooper","Richardson","Cox","Howard","Ward","Torres","Peterson","Gray","Ramirez","James","Watson","Brooks","Kelly","Sanders","Price","Bennett","Wood","Barnes","Ross","Henderson","Coleman","Jenkins","Perry","Powell","Long","Patterson","Hughes","Flores","Washington","Butler","Simmons","Foster","Gonzales","Bryant","Alexander","Russell","Griffin","Diaz","Hayes");
END$$
DELIMITER ;





DROP procedure if exists WhileLoop;
DELIMITER $$
CREATE PROCEDURE WhileLoop()
	BEGIN
		DECLARE		X	INT;
        SET X = 2;
	REPEAT
		INSERT INTO staff 
        VALUES ("test1234",LPAD(X,8,0),generate_fname(),generate_lname());
        SET X = X + 1;
        
	until X>15	
    END REPEAT;
    END$$
DELIMITER ;
CALL WhileLoop();
REPLACE INTO staff
Values("test1234",LPAD(99,8,0),"Samantha","Carter");

-- GENERATE STUDENTS
USE wannatryschema;



DROP function if exists generate_fname;
DELIMITER $$
CREATE FUNCTION generate_fname () RETURNS varchar(255)
BEGIN
	RETURN ELT(FLOOR(1 + (RAND() * (100-1))), "James","Mary","John","Patricia","Robert","Linda","Michael","Barbara","William","Elizabeth","David","Jennifer","Richard","Maria","Charles","Susan","Joseph","Margaret","Thomas","Dorothy","Christopher","Lisa","Daniel","Nancy","Paul","Karen","Mark","Betty","Donald","Helen","George","Sandra","Kenneth","Donna","Steven","Carol","Edward","Ruth","Brian","Sharon","Ronald","Michelle","Anthony","Laura","Kevin","Sarah","Jason","Kimberly","Matthew","Deborah","Gary","Jessica","Timothy","Shirley","Jose","Cynthia","Larry","Angela","Jeffrey","Melissa","Frank","Brenda","Scott","Amy","Eric","Anna","Stephen","Rebecca","Andrew","Virginia","Raymond","Kathleen","Gregory","Pamela","Joshua","Martha","Jerry","Debra","Dennis","Amanda","Walter","Stephanie","Patrick","Carolyn","Peter","Christine","Harold","Marie","Douglas","Janet","Henry","Catherine","Carl","Frances","Arthur","Ann","Ryan","Joyce","Roger","Diane");
END$$

DELIMITER ;

DROP function if exists generate_lname;
DELIMITER $$
CREATE FUNCTION generate_lname () RETURNS varchar(255)
BEGIN
	RETURN ELT(FLOOR(1 + (RAND() * (100-1))), "Smith","Johnson","Williams","Jones","Brown","Davis","Miller","Wilson","Moore","Taylor","Anderson","Thomas","Jackson","White","Harris","Martin","Thompson","Garcia","Martinez","Robinson","Clark","Rodriguez","Lewis","Lee","Walker","Hall","Allen","Young","Hernandez","King","Wright","Lopez","Hill","Scott","Green","Adams","Baker","Gonzalez","Nelson","Carter","Mitchell","Perez","Roberts","Turner","Phillips","Campbell","Parker","Evans","Edwards","Collins","Stewart","Sanchez","Morris","Rogers","Reed","Cook","Morgan","Bell","Murphy","Bailey","Rivera","Cooper","Richardson","Cox","Howard","Ward","Torres","Peterson","Gray","Ramirez","James","Watson","Brooks","Kelly","Sanders","Price","Bennett","Wood","Barnes","Ross","Henderson","Coleman","Jenkins","Perry","Powell","Long","Patterson","Hughes","Flores","Washington","Butler","Simmons","Foster","Gonzales","Bryant","Alexander","Russell","Griffin","Diaz","Hayes");
END$$
DELIMITER ;

DROP function if exists generate_address;
DELIMITER $$
CREATE FUNCTION generate_address () RETURNS varchar(255)
BEGIN
	RETURN ELT(FLOOR(1 + (RAND() * (100-1))),"36 Newgate Street, Cottingham, HU16 4DT",
"10 Ballards Close, Mickleton, GL55 6RL",
"3 Rothesay Crescent, Heysham, LA3 2XR",
"17 Glen Close, Little Bytham, NG33 4PS",
"Bryn Hyfryd, Mount Pleasant, Denbigh, LL16 3LS",
"41 Bramble Hill, Beverley, HU17 8UZ",
"1 Churchill Road, Cirencester, GL7 1AH",
"18 Summerleaze Avenue, Bude, EX23 8NP",
"Thorpe Fen House, Thorpe Fen, Thorpe St Peter, PE24 4LD",
"4 The Graylands, Cardiff, CF14 6AS",
"Old Vicarage, Uppington, TF6 5HN",
"Blubeckers, Bath Road, Hare Hatch, RG10 9SB",
"5 Bryn Celyn, Pontardawe, SA8 4LG",
"27 Welshpool Close, Bransholme, HU7 5DN",
"13 Glenside Court, Breckenbeds Road, Gateshead, NE9 6HW",
"23 Gwynfryn Road, Pontarddulais, SA4 8LG",
"7 Green Crescent, Bucklesham, IP10 0EA",
"24 Portland Grange, Hucknall, NG15 6RS",
"21 Devonshire Street, Huddersfield, HD1 3TR",
"16 South Road, Ash Vale, GU12 5AJ",
"5C School Street, Brierley Hill, DY5 4HQ",
"Bridge House, Trumpsgreen Road, Virginia Water, GU25 4JA",
"3 Roundwood Close, Welwyn, AL6 0XB",
"Lamorna, Hall Road, Bedingfield, IP23 7QE",
"4 Midfield Way, Keelby, DN41 8SL",
"23A Swains Lane, London, N6 6QX",
"9 Barcelona Crescent, Wroughton, SN4 9EE",
"9 Glendale Terrace, Whitland, SA34 0QP",
"Eton House, Little Tew, OX7 4JJ",
"84 Broadmead, Bristol, BS1 3DW",
"6 Cambrian Terrace, Porthmadog, LL49 9EH",
"5 Arle Gardens, Cheltenham, GL51 8HP",
"50 Marlescroft Way, Loughton, IG10 3NA",
"6 Botany Cottages, London Road, Purfleet, RM19 1PS",
"Apartment 62, 68 Falkner Street, Liverpool, L8 7AE",
"2 Duxford, Duxford, SN7 8SQ",
"14 Sandpiper Drive, Houndstone, BA22 8FN",
"2 Williams Close, Dawlish, EX7 9SP",
"3 Rye Gardens, Little Downham, CB6 2RY",
"20 Durham Road, Wolviston, TS22 5LP",
"20 Abbey Street, Accrington, BB5 1EB",
"10 Altwood, Harpenden, AL5 5RU",
"238 Whitley Road, Whitley Bay, NE26 2TE",
"11 Batstone Way, Ferndown, BH22 9TJ",
"Millbrook House, Murcot, WR12 7HS",
"3 Hillside, Sedgeford, PE36 5NF",
"10 Reade Court, Victoria Road, Farnham Common, SL2 3NW",
"3 Ashdale Close, Staines, TW19 7BA",
"14 Whitecroft Crescent, Brinsworth, S60 5HW",
"1 Hillview Drive, Hanley Swan, WR8 0EL",
"37 Draycott, Telford, TF3 2DN",
"Chevin, Rye Park, Beaford, EX19 8LY",
"16 Hill Brow, Bearsted, ME14 4AW",
"1 Planton Way, Brightlingsea, CO7 0LB",
"162 Westbury Avenue, London, N22 6RU",
"2 Blackthorn Lane, Cherry Willingham, LN3 4FD",
"Broom Lodge, Pitt Road, Hinton St George, TA17 8SR",
"Ael Y Bryn, Beulah, LD5 4YE",
"Ground Floor Premises, 2 - 4 North Parade, Chessington, KT9 1QF",
"35 Upper Belgrave Road, Bristol, BS8 2XN",
"Percy Thrower's Gardening Centre, Thrower Road, Shrewsbury, SY2 6QW",
"Bramble Rough, Upper Hartfield, TN7 4DL",
"44 The Layne, Bognor Regis, PO22 6JL",
"Bourbon Court, Nightingales Corner, Amersham, HP7 9QS",
"Briar Cottage, High Street, Marden, TN12 9DR",
"13 High Street, Yatton, BS49 4JD",
"Lavender Cottage, Reynalton, SA68 0PG",
"3 Aycliffe Gardens, Plymouth, PL7 1YN",
"2 Bellfield Avenue, Harrow, HA3 6SX",
"200 Town Street, Middleton, LS10 3TJ",
"9 Roffey Close, Purley, CR8 4BH",
"Warehouse 3, Balch House, New Spitalfields Market, 25 Sherrin Road, London, E10 5SQ",
"1 Wonersh Court, The Street, Wonersh, GU5 0PG",
"1 Manor Villas, Lechlade, GL7 3AY",
"Flat 7, Kensington Apartments, 11 Commercial Street, London, E1 6LW",
"23 Christow Street, Leicester, LE1 2GJ",
"3 Hockney Gardens, Ipswich, IP3 0RY",
"Hillside House, Argoed, NP12 0HR",
"2 Highways Cottages, Farnham Road, Liss, GU33 6JD",
"27 Brook Road, Bomere Heath, SY4 3PY",
"The Fron, Kinnersley, HR3 6QB",
"Perranporth, Weydon Farm Lane, Farnham, GU9 8QJ",
"Vallarta House, 150A Clevedon Road, Tickenham, BS21 6RG",
"8 Clarendon Path, Orpington, BR5 2PD",
"127 Langsett Crescent, Sheffield, S6 2TW",
"82 Church Street, Crowthorne, RG45 7AN",
"39 Valley Drive, Wilnecote, B77 5FL",
"6 Nobes Close, Gosport, PO13 0JL",
"56 Tandridge Court, 189 - 197 Croydon Road, Caterham, CR3 6PT",
"5 Bark Knotts Terrace, Norton, YO17 9DX",
"Ward Green Post Office, 97 Vernon Road, Worsbrough, S70 5HJ",
"7 Jubilee Close, Spennymoor, DL16 6GA",
"Cherry Trees, Pit Road, Hemsby, NR29 4LG",
"7 Woodstock Rise, Sutton, SM3 9JE",
"56 Crossways, York, YO10 5JQ",
"10 Baltic Street, Salford, M5 5JT",
"3 Bullen Court, New North Road, Ilford, IG6 2UY",
"24 Ashton Road, Darwen, BB3 2DX",
"10 Heron Road, Twickenham, TW1 1PG",
"Brecon House, Castle Dene, DH3 4HE" );
END$$
DELIMITER ;


drop function if exists date_rand;
DELIMITER $$
CREATE function date_rand() returns DATE

	BEGIN
    DECLARE days_between int;
    DECLARE startdate DATE;
	DECLARE enddate DATE;
    DECLARE days_rand int;
    SET startdate = '1996-01-01';
    SET enddate = '2001-12-31';
    SET days_between = datediff(startdate,enddate);
    SET days_rand = RAND()*10000 % days_between;
	return date_add(startdate,INTERVAL days_rand DAY);
	end$$
DELIMITER ;

DROP procedure if exists WhileLoop;
DELIMITER $$
CREATE PROCEDURE WhileLoop()
	BEGIN
		DECLARE		X	INT;
        DECLARE id varchar(8);
		DECLARE fname varchar(45);
        DECLARE sname varchar(45);
        DECLARE address varchar(255);
        DECLARE pnumber varchar(45);
        DECLARE email varchar(255);
        DECLARE dob DATE;
        DECLARE gender varchar(255);
        DECLARE stage varchar(255);
	
        
        
        SET X = 1;
	REPEAT
		SET id = LPAD(FLOOR(RAND() * 10000000000), 8, 0);
		SET fname = generate_fname();
        SET sname = generate_lname();
        SET address = generate_address();
        SET email = concat(fname,concat("_",concat(id,"@ucdconnect.ie")));
        SET pnumber = LPAD(0, 0, FLOOR(RAND() * 10000000000));
        SET dob = date_rand();
        SET gender = ELT(FLOOR(1+(RAND() * (3))),"MALE","FEMALE","OTHER");
        SET stage = ELT(FLOOR(1+(RAND() * (4))),"STAGE 1","STAGE 2","STAGE 3","STAGE 4");
		INSERT INTO students
        VALUES (id,email,address,pnumber,fname,sname,"test123",0.0,dob,gender,stage );
        SET X = X + 1;
        
	until X>50	
    END REPEAT;
    END$$
DELIMITER ;
CALL WhileLoop();
-- create modules




DROP function if exists find_staff;
DELIMITER $$
CREATE function find_staff() RETURNS varchar(8)
BEGIN 
	RETURN (SELECT staff_id FROM staff ORDER BY RAND() LIMIT 1);
END$$
DELIMITER ;

DROP function if exists get_start_date;
DELIMITER $$
CREATE function get_start_date() RETURNS date
BEGIN 
	DECLARE sem1 DATE;
    DECLARE sem2 DATE;
    SET sem1 = '2019-09-14';
    SET sem2 = '2020-01-22';
    
	RETURN ELT(FLOOR(1 + (RAND() * (3-1))), sem1,sem2);
END$$
DELIMITER ;

DROP function if exists get_end_date;
DELIMITER $$
CREATE function get_end_date() RETURNS date
BEGIN 
	DECLARE sem1 DATE;
    DECLARE sem2 DATE;
    SET sem1 = '2019-11-26';
    SET sem2 = '2020-04-24';
    
	RETURN ELT(FLOOR(1 + (RAND() * (3-1))), sem1,sem2);
END$$
DELIMITER ;

DROP function if exists generate_name_prefix;
DELIMITER $$
CREATE function generate_name_prefix() RETURNS varchar(255)
BEGIN 
	RETURN ELT(FLOOR(1 + (RAND() * (3))), "Introduction to ","Advanced ","Secure ","Modern ","Theoretical ");
END$$
DELIMITER ;

DROP function if exists generate_name_suffix;
DELIMITER $$
CREATE function generate_name_suffix() RETURNS varchar(255)
BEGIN 
	RETURN ELT(FLOOR(1 + (RAND() * (7))), "Software Engineering","Networking","Android Development","Ethical Hacking","iOS Development","Data Structures and Algorithms","Artificial Intelligence","Functional Programming","Linear Algebra","Game Development");
END$$
DELIMITER ;








DROP procedure if exists WhileLoop;
DELIMITER $$
CREATE PROCEDURE WhileLoop()
	BEGIN
		DECLARE		X	INT;
        DECLARE s_date DATE;
        DECLARE e_date DATE;
        SET X = 1;
	REPEAT
		SET s_date = get_start_date();
		SET e_date = IF(s_date='2019-09-14','2019-11-26','2020-04-24');
  
		REPLACE INTO modules
        VALUES (concat("COMP",LPAD(X,4,0)) ,concat(generate_name_prefix(),generate_name_suffix()),find_staff(),"description",e_date,360.00,s_date);
        SET X = X + 1;
        
	until X>100
    END REPEAT;
    END$$
DELIMITER ;
CALL WhileLoop();
-- --- constant staff 

REPLACE INTO modules 
values("COMP0099","Introduction to Web QA Engineering","00000099","This module revolves around testing web products and generating bug reports",'2019-11-19',360.0,'2019-09-14');

drop function if exists randomStudent;
DELIMITER $$
CREATE FUNCTION randomStudent() returns varchar(8)
	BEGIN
		RETURN (SELECT student_id FROM students ORDER BY RAND() LIMIT 1);
	END$$
DELIMITER ;

drop function if exists randomModule;
DELIMITER $$
CREATE FUNCTION randomModule() returns varchar(8)
	BEGIN
		RETURN (SELECT module_id FROM modules ORDER BY RAND() LIMIT 1);
	END$$
DELIMITER ;

DROP procedure if exists WhileLoop;
DELIMITER $$
CREATE PROCEDURE WhileLoop()
	BEGIN
		DECLARE		X	INT;
        DECLARE 	M Varchar(8);
        DECLARE		S VARCHAR(8);
        DECLARE 	R VARCHAR(40);
        SET X = 1;
	REPEAT
		SET M = randomModule();
        SET S = randomStudent();
        SET R = concat(M,concat("/",S));
        
        
		REPLACE INTO module_enrolments VALUES (M,S,R);
        SET X = X + 1;
	until X>100	
    END REPEAT;
    END$$
DELIMITER ;
CALL WhileLoop();
DROP procedure if exists WhileLoop;
DELIMITER $$
CREATE Procedure WhileLoop()
	BEGIN
		declare x INT;
        DECLARE S  varchar(8);
        DECLARE R varchar(17);
		SET X = 1;
	REPEAT
		SET S = randomStudent();
		SET R = concat('COMP0099',concat("/",S));
		REPLACE INTO module_enrolments VALUES ('COMP0099',S,R);
		SET X = X + 1;
    UNTIL X > 6
    END REPEAT;
    END$$
DELIMITER ;
CALL WhileLoop();

