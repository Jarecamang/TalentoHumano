-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema humanresources
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `humanresources` ;

-- -----------------------------------------------------
-- Schema humanresources
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `humanresources` DEFAULT CHARACTER SET utf8 ;
USE `humanresources` ;

-- -----------------------------------------------------
-- Table `humanresources`.`areaofinterest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `humanresources`.`areaofinterest` ;

CREATE TABLE IF NOT EXISTS `humanresources`.`areaofinterest` (
  `pkID` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`pkID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `humanresources`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `humanresources`.`role` ;

CREATE TABLE IF NOT EXISTS `humanresources`.`role` (
  `pkID` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`pkID`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `humanresources`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `humanresources`.`user` ;

CREATE TABLE IF NOT EXISTS `humanresources`.`user` (
  `pkID` INT(11) NOT NULL AUTO_INCREMENT,
  `identifyCard` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `lastname` VARCHAR(255) NOT NULL,
  `age` INT(11) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `phone` MEDIUMTEXT NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `level_training` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(16) NOT NULL,
  `fkroleID` INT(11) NOT NULL,
  PRIMARY KEY (`pkID`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `identifyCard_UNIQUE` (`identifyCard` ASC),
  INDEX `fk_user_role1_idx` (`fkroleID` ASC),
  CONSTRAINT `fk_user_role1`
    FOREIGN KEY (`fkroleID`)
    REFERENCES `humanresources`.`role` (`pkID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 29
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `humanresources`.`certificate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `humanresources`.`certificate` ;

CREATE TABLE IF NOT EXISTS `humanresources`.`certificate` (
  `pkID` INT(11) NOT NULL AUTO_INCREMENT,
  `type` INT(11) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `fkuserID` INT(11) NOT NULL,
  PRIMARY KEY (`pkID`),
  UNIQUE INDEX `fkuserID_UNIQUE` (`fkuserID` ASC),
  INDEX `fk_certificate_user1_idx` (`fkuserID` ASC),
  CONSTRAINT `fk_certificate_user1`
    FOREIGN KEY (`fkuserID`)
    REFERENCES `humanresources`.`user` (`pkID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `humanresources`.`certifications`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `humanresources`.`certifications` ;

CREATE TABLE IF NOT EXISTS `humanresources`.`certifications` (
  `pkID` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `date` DATE NOT NULL,
  `place` VARCHAR(100) NOT NULL,
  `fkuserID` INT(11) NOT NULL,
  PRIMARY KEY (`pkID`),
  INDEX `fk_certifications_user1_idx` (`fkuserID` ASC),
  CONSTRAINT `fk_certifications_user1`
    FOREIGN KEY (`fkuserID`)
    REFERENCES `humanresources`.`user` (`pkID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `humanresources`.`certificationsareaofinterest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `humanresources`.`certificationsareaofinterest` ;

CREATE TABLE IF NOT EXISTS `humanresources`.`certificationsareaofinterest` (
  `fkcertificationsID` INT(11) NOT NULL,
  `fkareaofinterestID` INT(11) NOT NULL,
  INDEX `fk_certifications_has_areaofinterest_areaofinterest1_idx` (`fkareaofinterestID` ASC),
  INDEX `fk_certifications_has_areaofinterest_certifications1_idx` (`fkcertificationsID` ASC),
  CONSTRAINT `fk_certifications_has_areaofinterest_areaofinterest1`
    FOREIGN KEY (`fkareaofinterestID`)
    REFERENCES `humanresources`.`areaofinterest` (`pkID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_certifications_has_areaofinterest_certifications1`
    FOREIGN KEY (`fkcertificationsID`)
    REFERENCES `humanresources`.`certifications` (`pkID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `humanresources`.`contract`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `humanresources`.`contract` ;

CREATE TABLE IF NOT EXISTS `humanresources`.`contract` (
  `pkID` INT(11) NOT NULL AUTO_INCREMENT,
  `salary` DOUBLE NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `enddate` DATE NULL DEFAULT NULL,
  `fkuserID` INT(11) NOT NULL,
  PRIMARY KEY (`pkID`),
  UNIQUE INDEX `fkuserID_UNIQUE` (`fkuserID` ASC),
  INDEX `fk_contract_user1_idx` (`fkuserID` ASC),
  CONSTRAINT `fk_contract_user1`
    FOREIGN KEY (`fkuserID`)
    REFERENCES `humanresources`.`user` (`pkID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `humanresources`.`position`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `humanresources`.`position` ;

CREATE TABLE IF NOT EXISTS `humanresources`.`position` (
  `pkID` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`pkID`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `humanresources`.`contractposition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `humanresources`.`contractposition` ;

CREATE TABLE IF NOT EXISTS `humanresources`.`contractposition` (
  `fkcontractID` INT(11) NOT NULL,
  `fkpositionID` INT(11) NOT NULL,
  INDEX `fk_contract_has_position_position1_idx` (`fkpositionID` ASC),
  INDEX `fk_contract_has_position_contract_idx` (`fkcontractID` ASC),
  CONSTRAINT `fk_contract_has_position_contract`
    FOREIGN KEY (`fkcontractID`)
    REFERENCES `humanresources`.`contract` (`pkID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contract_has_position_position1`
    FOREIGN KEY (`fkpositionID`)
    REFERENCES `humanresources`.`position` (`pkID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `humanresources`.`notifications`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `humanresources`.`notifications` ;

CREATE TABLE IF NOT EXISTS `humanresources`.`notifications` (
  `pkID` INT(11) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NOT NULL,
  `date` DATE NOT NULL,
  `fkcertificationID` INT(11) NULL DEFAULT NULL,
  `fkuserID` INT(11) NOT NULL,
  `fkcertificateID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`pkID`),
  INDEX `fk_notifications_certifications1_idx` (`fkcertificationID` ASC),
  INDEX `fk_notifications_user1_idx` (`fkuserID` ASC),
  INDEX `fk_notifications_certificate1_idx` (`fkcertificateID` ASC),
  CONSTRAINT `fk_notifications_certificate1`
    FOREIGN KEY (`fkcertificateID`)
    REFERENCES `humanresources`.`certificate` (`pkID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notifications_certifications1`
    FOREIGN KEY (`fkcertificationID`)
    REFERENCES `humanresources`.`certifications` (`pkID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notifications_user1`
    FOREIGN KEY (`fkuserID`)
    REFERENCES `humanresources`.`user` (`pkID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `humanresources`.`userareaofinterest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `humanresources`.`userareaofinterest` ;

CREATE TABLE IF NOT EXISTS `humanresources`.`userareaofinterest` (
  `fkuserID` INT(11) NOT NULL,
  `fkareaofinterestID` INT(11) NOT NULL,
  INDEX `fk_user_has_areaofinterest_areaofinterest1_idx` (`fkareaofinterestID` ASC),
  INDEX `fk_user_has_areaofinterest_user1_idx` (`fkuserID` ASC),
  CONSTRAINT `fk_user_has_areaofinterest_areaofinterest1`
    FOREIGN KEY (`fkareaofinterestID`)
    REFERENCES `humanresources`.`areaofinterest` (`pkID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_areaofinterest_user1`
    FOREIGN KEY (`fkuserID`)
    REFERENCES `humanresources`.`user` (`pkID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

insert into `position` values(1,"Analista");
insert into `position` values(2,"Ingeniero");
insert into `position` values(3,"Abogado");
insert into `position` values(4,"medico");
insert into `position` values(5,"secretario");

INSERT INTO `role` VALUES(1,'User'),(2,'Administrator');

INSERT INTO `user` VALUES (1,'1012345456','Admin','Tester',99,'Juaz123',3212345656,'admin@admin.admin','PhD','admin','Admin2016',2);
INSERT INTO `user` VALUES 
(2,'2','Carlos','Restrepo',99,'Calle123',3212345656,'carlos@llll','Especialista','carlos','car',1);
INSERT INTO `user` VALUES 
(3,'3','pedro','Tester',99,'Juaz123',3212345656,'admin@admin.admin','Especialista','pedro','ped',2);
INSERT INTO `user` VALUES 
(4,'4','andrea','Tester',99,'Juaz123',3212345656,'admin@admin.admin','Especialista','andrea','and',2);
INSERT INTO `user` VALUES 
(5,'5','camila','Tester',99,'Juaz123',3212345656,'admin@admin.admin','Phd','camila','cami',2);
INSERT INTO `user` VALUES 
(6,'6','lola','Tester',99,'Juaz123',3212345656,'admin@admin.admin','Phd','lola','lol',2);
INSERT INTO `user` VALUES 
(7,'7','cata','Tester',99,'Juaz123',3212345656,'admin@admin.admin','Doctor','cata','cat',2);
INSERT INTO `user` VALUES 
(8,'8','alonso','Tester',99,'Juaz123',3212345656,'admin@admin.admin','Tecnico','alonso','alon',2);
INSERT INTO `user` VALUES 
(9,'9','pipe','Tester',99,'Juaz123',3212345656,'admin@admin.admin','Especialista','pipe','pip',2);
INSERT INTO `user` VALUES 
(10,'10','carolina','Tester',99,'Juaz123',3212345656,'admin@admin.admin','Tecnologo','carolina','caro',2);
INSERT INTO `user` VALUES 
(11,'11','rosa','Tester',99,'Juaz123',3212345656,'admin@admin.admin','Magister','rosa','ros',2);

insert into contract
values(1,1800000,"Definido",'2016-11-20',1);
insert into contract
values(2,2800000,"Indefinido",null,2);
insert into contract
values(3,3800000,"Definido",'2016-11-20',3);
insert into contract
values(4,4800000,"Definido",'2016-11-20',4);
insert into contract
values(5,1800000,"Indefinido",null,5);
insert into contract
values(6,1200000,"Definido",'2016-11-20',6);

insert into contractPosition values(1,1);
insert into contractPosition values(2,2);
insert into contractPosition values(3,3);
insert into contractPosition values(4,4);
insert into contractPosition values(5,5);
insert into contractPosition values(6,1);