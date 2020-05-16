SET GLOBAL log_bin_trust_function_creators = 1;
drop schema if exists wannatryschema;
create schema wannatryschema;

DROP TABLE if exists  wannatryschema.`grades`, `wannatryschema`.`module_enrolement`, `wannatryschema`.`modules`, `wannatryschema`.`new_table`, `wannatryschema`.`priveleges`, `wannatryschema`.`roles`, `wannatryschema`.`roles_priveleges`, `wannatryschema`.`user`, `wannatryschema`.`user_fee`, `wannatryschema`.`user_roles`;

-- ROLE TABLE --
CREATE TABLE if not exists `wannatryschema`.`roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- login attempt table --
CREATE TABLE `wannatryschema`.`failed_attempts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `IP_ADDRESS` VARCHAR(15) NOT NULL,
  `ATTEMPTS` INT NOT NULL,
  `LAST_ATTEMPT` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `IP_ADDRESS_UNIQUE` (`IP_ADDRESS` ASC));

  -- Action log table --
CREATE TABLE `wannatryschema`.`logged_actions` (
  `id` INT NOT NULL AUTO_INCREMENT UNIQUE,
  `USER_ID` INT NOT NULL,
  `ACTION_TYPE` VARCHAR(30) NOT NULL,
  `ACTION_DESC` VARCHAR(100) NOT NULL,
  `IP_ADDRESS` VARCHAR(15) NOT NULL,
  `DATE_OF_ACTION` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;
  
-- PRIVILEGE TABLE --
CREATE TABLE if not exists `wannatryschema`.`priveleges` (
  `id` int NOT NULL UNIQUE,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

-- ROLES_PRIVILIGE --
CREATE TABLE if not exists `wannatryschema`.`roles_priveleges` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `privelege_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfrby94iar8c4nowkfbu033b3u` (`privelege_id`),
  KEY `FKejk7tly0tvig2pn74fxpm2ucs` (`role_id`),
  CONSTRAINT `FKejk7tly0tvig2pn74fxpm2ucs` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKfrby94iar8c4nowkfbu033b3u` FOREIGN KEY (`privelege_id`) REFERENCES `priveleges` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- USER TABLE --
CREATE TABLE if not exists `wannatryschema`.`user` (
  `id` int NOT NULL,
  `address` varchar(255) NOT NULL,
  `dob` date NOT NULL,
  `ethnicity` varchar(255) NOT NULL,
  `fname` varchar(255) NOT NULL,
  `gender` char(1) NOT NULL,
  `lname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- USER ROLES --
CREATE TABLE if not exists `wannatryschema`.`user_roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  KEY `FK55itppkw3i07do3h7qoclqd4k` (`user_id`),
  CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3256 DEFAULT CHARSET=utf8;

-- MODULE --
CREATE TABLE if not exists `wannatryschema`.`modules` (
  `module_id` varchar(8) NOT NULL,
  `module_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` date NOT NULL,
  `price` double NOT NULL,
  `start_date` date NOT NULL,
  `lecturer_id` INT NOT NULL,
  PRIMARY KEY (`module_id`),
  UNIQUE KEY `module_name_UNIQUE` (`module_name`),
  UNIQUE KEY `module_id_UNIQUE` (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- module enrolement --
CREATE TABLE `wannatryschema`.`module_enrolement` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `module_id` varchar(8) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmnvefqj644xdkvel0iptlpeyw` (`student_id`),
  KEY `FKhxgsf7oi56y6257tfwb43ruib` (`module_id`),
  CONSTRAINT `FKhxgsf7oi56y6257tfwb43ruib` FOREIGN KEY (`module_id`) REFERENCES `modules` (`module_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKmnvefqj644xdkvel0iptlpeyw` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;


CREATE TABLE if not exists `wannatryschema`.`grades` (
  `id` int NOT NULL AUTO_INCREMENT,
  `module_id` varchar(8) NOT NULL,
  `student_id` int NOT NULL,
  `percentage` double NOT NULL,
  `Letter` varchar(1) NOT NULL,
  `feedback` varchar(4255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE if not existS `wannatryschema`.`new_table` (
  `id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE if not exists `wannatryschema`.`user_fee` (
  `id` int NOT NULL,
  `fees` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




-- HELPER FUNCTIONS
-- ----------------------
-- CREATE ROLES

drop procedure if exists `wannatryschema`.`load_role_data`;
delimiter $$
CREATE procedure wannatryschema.load_role_data()
	BEGIN
		REPLACE INTO roles VALUE (50,"ROLE_ADMIN");
		REPLACE INTO roles VALUE (60,"ROLE_STAFF");
		REPLACE INTO roles VALUE (70,"ROLE_STUDENT");
	END $$
DELIMITER ;
CALL wannatryschema.load_role_data();

-- CREATE RANDOM ADDRESS
DROP function if exists wannatryschema.generateAddress;
DELIMITER $$
CREATE FUNCTION  wannatryschema.generateAddress() RETURNS varchar(255)
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
END $$
DELIMITER ;

-- RANDOM DATE --
drop function if exists wannatryschema.dateRand;
DELIMITER $$
CREATE function wannatryschema.dateRand() returns DATE
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
	end $$
DELIMITER ;

-- RANDOM ETHNICITY --
drop function if exists wannatryschema.random_ethnicity;
DELIMITER $$
CREATE FUNCTION wannatryschema.random_ethnicity() RETURNS varchar(255)
BEGIN
	return ELT(FLOOR(1+(RAND() * (4))),"White","Black or African American","Ameican Indian or Alaska Native","Asian","African");
END $$
DELIMITER ;

-- RANDOM FNAME
DROP function if exists wannatryschema.generate_fname;
DELIMITER $$
CREATE FUNCTION wannatryschema.generate_fname () RETURNS varchar(255)
BEGIN
	RETURN ELT(FLOOR(1 + (RAND() * (100-1))), "James","Mary","John","Patricia","Robert","Linda","Michael","Barbara","William","Elizabeth","David","Jennifer","Richard","Maria","Charles","Susan","Joseph","Margaret","Thomas","Dorothy","Christopher","Lisa","Daniel","Nancy","Paul","Karen","Mark","Betty","Donald","Helen","George","Sandra","Kenneth","Donna","Steven","Carol","Edward","Ruth","Brian","Sharon","Ronald","Michelle","Anthony","Laura","Kevin","Sarah","Jason","Kimberly","Matthew","Deborah","Gary","Jessica","Timothy","Shirley","Jose","Cynthia","Larry","Angela","Jeffrey","Melissa","Frank","Brenda","Scott","Amy","Eric","Anna","Stephen","Rebecca","Andrew","Virginia","Raymond","Kathleen","Gregory","Pamela","Joshua","Martha","Jerry","Debra","Dennis","Amanda","Walter","Stephanie","Patrick","Carolyn","Peter","Christine","Harold","Marie","Douglas","Janet","Henry","Catherine","Carl","Frances","Arthur","Ann","Ryan","Joyce","Roger","Diane");
END $$
DELIMITER ;
-- RANDOM LNAME --
DROP function if exists wannatryschema.generate_lname;
DELIMITER $$
CREATE FUNCTION wannatryschema.generate_lname () RETURNS varchar(255)
BEGIN
	RETURN ELT(FLOOR(1 + (RAND() * (100-1))), "Smith","Johnson","Williams","Jones","Brown","Davis","Miller","Wilson","Moore","Taylor","Anderson","Thomas","Jackson","White","Harris","Martin","Thompson","Garcia","Martinez","Robinson","Clark","Rodriguez","Lewis","Lee","Walker","Hall","Allen","Young","Hernandez","King","Wright","Lopez","Hill","Scott","Green","Adams","Baker","Gonzalez","Nelson","Carter","Mitchell","Perez","Roberts","Turner","Phillips","Campbell","Parker","Evans","Edwards","Collins","Stewart","Sanchez","Morris","Rogers","Reed","Cook","Morgan","Bell","Murphy","Bailey","Rivera","Cooper","Richardson","Cox","Howard","Ward","Torres","Peterson","Gray","Ramirez","James","Watson","Brooks","Kelly","Sanders","Price","Bennett","Wood","Barnes","Ross","Henderson","Coleman","Jenkins","Perry","Powell","Long","Patterson","Hughes","Flores","Washington","Butler","Simmons","Foster","Gonzales","Bryant","Alexander","Russell","Griffin","Diaz","Hayes");
END $$
DELIMITER ;

-- RANDOM GENDER
drop function if exists wannatryschema.random_gender;
DELIMITER $$
CREATE FUNCTION wannatryschema.random_gender() RETURNS varchar(1)
BEGIN
	return ELT(FLOOR(1+RAND()*2),"M","F","O");
END $$
DELIMITER ;

-- RANDOM STAFF --
DROP function if exists wannatryschema.findStaff;
DELIMITER $$
CREATE function wannatryschema.findStaff() RETURNS int
BEGIN 
	RETURN ((SELECT u.id FROM user as u join user_roles as ur where u.id = ur.user_id and ur.role_id = 60)  ORDER BY RAND() LIMIT 1);
END $$
DELIMITER ;

-- RANDOM START DATE --
DROP function if exists wannatryschema.get_start_date;
DELIMITER $$
CREATE function wannatryschema.get_start_date() RETURNS date
BEGIN 
	DECLARE sem1 DATE;
    DECLARE sem1ALT DATE;
    DECLARE sem2 DATE;
    DECLARE sem2ALT DATE;
    SET sem1 = '2020-09-14';
    SET sem2 = '2021-01-22';
    SET sem1ALT = '2019-09-14';
    SET sem2ALT = '2020-01-22';
    
	RETURN ELT(FLOOR(1 + (RAND() * (5-1))), sem1,sem2,sem1ALT,sem2ALT);
END $$
DELIMITER ;
-- RANDOM END DATE --
DROP function if exists wannatryschema.get_end_date;
DELIMITER $$
CREATE function wannatryschema.get_end_date() RETURNS date
	BEGIN 
		DECLARE sem1 DATE;
		DECLARE sem2 DATE;
		SET sem1 = '2020-11-26';
		SET sem2 = '2021-04-24';
		RETURN ELT(FLOOR(1 + (RAND() * (3-1))), sem1,sem2);
	END $$
DELIMITER ;

DROP function if exists wannatryschema.generate_name_prefix;
DELIMITER $$
CREATE function wannatryschema.generate_name_prefix() RETURNS varchar(255)
BEGIN 
	RETURN ELT(FLOOR(1 + (RAND() * (3))), "Introduction to ","Advanced ","Secure ","Modern ","Theoretical ");
END$$
DELIMITER ;

DROP function if exists wannatryschema.generate_name_suffix;
DELIMITER $$
CREATE function wannatryschema.generate_name_suffix() RETURNS varchar(255)
BEGIN 
	RETURN ELT(FLOOR(1 + (RAND() * (7))), "Software Engineering","Networking","Android Development","Ethical Hacking","iOS Development","Data Structures and Algorithms","Artificial Intelligence","Functional Programming","Linear Algebra","Game Development");
END$$
DELIMITER ;
Drop function if exists wannatryschema.random_id;
Delimiter $$
Create function wannatryschema.random_id() returns int
BEGIN
	declare ID int;
    set ID = (select * from new_table order by Rand() limit 1);
    DELETE FROM new_table where new_table.id = ID;
	RETURN ID;
END$$
DELIMITER ;


-- CREATES 200 RANDOM IDS
drop procedure if exists wannatryschema.load_foo_test_data;

delimiter $$
create procedure wannatryschema.load_foo_test_data()
begin

declare v_max int unsigned default 3000;
declare v_counter int unsigned default 0;

  truncate table wannatryschema.new_table;
  start transaction;
  while v_counter < v_max do
    replace into wannatryschema.new_table values( LPAD(FLOOR(RAND() * 9999999), 8,1));
    set v_counter=v_counter+1;
  end while;
  commit;
end $$

delimiter ;

call wannatryschema.load_foo_test_data();

-- RANDOM ID PROCEDURE --
drop procedure if exists wannatryschema.RANDOM_ID;
delimiter $$
create procedure wannatryschema.RANDOM_ID()
BEGIN
    declare ID int;
    set ID = (select * from wannatryschema.new_table order by Rand() limit 1);
    DELETE FROM wannatryschema.new_table where wannatryschema.new_table.id = ID;
	SELECT ID;
END $$
delimiter ;


-- CREATES STUDENTS AND ASSIGNS STUDENT ROLE
drop procedure if exists wannatryschema.load_user_data;
delimiter $$
create procedure wannatryschema.load_user_data()
begin
declare v_max int unsigned default 100;
declare v_counter int unsigned default 0;
declare mid int;
 start transaction;
  while v_counter < v_max do
  set mid = random_id();
    replace INTO wannatryschema.user (id,address,dob,ethnicity,fname,gender,lname,password,phone_number,email)
VALUES (mid,generateAddress(),dateRand(),random_ethnicity(),generate_fname(),random_gender(),generate_lname(),'$2a$10$fUwYy1YDiQNWg345oRVer.Aqvy02pnTfahNwhtXrnyx1bGY4JcDwW',123456789,'testemail@gmail.com');
    REPLACE Into wannatryschema.user_roles (user_id,role_id)
    VALUES (mid,70);
    set v_counter=v_counter+1;
  end while;
  commit;
end $$
delimiter ;

call wannatryschema.load_user_data();

-- CREATES STAFF AND ASSIGNS STAFF ROLE
drop procedure if exists wannatryschema.load_staff_data;
delimiter #
create procedure wannatryschema.load_staff_data()
begin
declare v_max int unsigned default 15;
declare v_counter int unsigned default 0;
declare mid int;

 start transaction;
  while v_counter < v_max do
  set mid = random_id();
    replace INTO wannatryschema.user (id,address,dob,ethnicity,fname,gender,lname,password,phone_number,email)
VALUES (mid,generateAddress(),dateRand(),random_ethnicity(),generate_fname(),random_gender(),generate_lname(),'$2a$10$fUwYy1YDiQNWg345oRVer.Aqvy02pnTfahNwhtXrnyx1bGY4JcDwW',123456789,'staffemail@gmail.com');
    replace Into wannatryschema.user_roles (user_id,role_id)
    VALUES (mid,60);
    set v_counter=v_counter+1;
  end while;
  commit;
end #
delimiter ;

call wannatryschema.load_staff_data();

DROP procedure if exists wannatryschema.load_module_data;
DELIMITER $$
CREATE PROCEDURE wannatryschema.load_module_data()
	BEGIN
		DECLARE		X	INT;
        DECLARE s_date DATE;
        DECLARE e_date DATE;
        SET X = 1;
	REPEAT
		SET s_date = get_start_date();
		IF s_date = '2019-09-14' then set e_date = '2019-11-26';
		ELSEIF s_date = '2020-01-22' then set e_date = '2020-04-24';
		ELSEIF s_date = '2020-09-13' then set e_date = '2020-11-26';
		ELSEIF s_date = '2021-01-22' then set e_date = '2021-04-24';
        END IF;
		REPLACE INTO modules
        VALUES (concat("COMP",LPAD(X,4,0)) ,concat(generate_name_prefix(),generate_name_suffix()),"description",e_date,360.00,s_date,findStaff());
        SET X = X + 1;
        
	until X>100
    END REPEAT;
    END$$
DELIMITER ;

call wannatryschema.load_module_data();

-- CREATES STAFF AND ASSIGNS STAFF ROLE
-- drop procedure if exists load_staff_module_data;
-- delimiter #
-- create procedure load_staff_module_data()
-- begin
-- declare v_max int unsigned default 15;
-- declare v_counter int unsigned default 0;
-- declare sid int;
-- declare m_id varchar(8);

--  start transaction;
--   while v_counter < v_max do
--   set sid = getRandomStaff();
--   set m_id = randomModule();
-- 		INSERT INTO staff_module (id,staff_id,module_id) VALUES (concat(sid,concat('/',m_id)),sid,m_id);
--     set v_counter=v_counter+1;
--   end while;
--   commit;
-- end #
-- delimiter ;


-- random enrolment --
drop procedure if exists wannatryschema.random_enrol;
DELIMITER $$
CREATE procedure wannatryschema.random_enrol()
BEGIN
	DECLARE COUNTER INT ;
	DECLARE s_id INT;
    DECLARE m_id VARCHAR(8);
    SET COUNTER = 1;
    repeat
		SET s_id = ((SELECT u.id from user as u JOIN user_roles as ur where u.id = ur.user_id and ur.role_id= 70) order by RAND() LIMIT 1);
		SET m_id = (SELECT module_id from modules order by RAND() LIMIT 1);
		REPLACE INTO module_enrolement (student_id,module_id)
		VALUE(s_id,m_id);
        SET COUNTER = COUNTER +1;
	until COUNTER > 40
    END REPEAT;
	END $$
DELIMITER ;
call wannatryschema.random_enrol();

drop procedure if exists wannatryschema.populate_roles_privi;
DELIMITER $$
CREATE procedure wannatryschema.populate_roles_privi()
BEGIN
	INSERT INTO priveleges(id,name) VALUE(20,"READ");
    INSERT INTO priveleges(id,name) VALUE(30,"WRITE");
    INSERT INTO priveleges(id,name) VALUE(40,"DELETE");
    
    -- ADMIN --
    INSERT INTO roles_priveleges(role_id,privelege_id) VALUE(50,20);
    INSERT INTO roles_priveleges(role_id,privelege_id) VALUE(50,30);
    INSERT INTO roles_priveleges(role_id,privelege_id) VALUE(50,40);
    -- STAFF --
    INSERT INTO roles_priveleges(role_id,privelege_id) VALUE(60,20);
    INSERT INTO roles_priveleges(role_id,privelege_id) VALUE(60,30);
    -- STUDENT --
    INSERT INTO roles_priveleges(role_id,privelege_id) VALUE(70,20);
    
END $$
DELIMITER ;
call wannatryschema.populate_roles_privi();

drop procedure if exists wannatryschema.delete_user;
DELIMITER $$
create procedure wannatryschema.delete_user(s_id INT)
BEGIN
	DELETE FROM user_roles where user_id = s_id;
    DELETE FROM user_fee where id = s_id;
    DELETE FROM module_enrolement where student_id = s_id;
    delete from user where id = s_id;
END $$
DELIMITER ;
