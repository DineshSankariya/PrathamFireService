
Script for client table in invoice project.

CREATE TABLE `invoice`.`client` (
  `cid` INT NOT NULL AUTO_INCREMENT,
  `cname` TEXT(1000) NOT NULL,
  `c_al_name` TEXT(1000) NOT NULL,
  `p_name` VARCHAR(255) NULL,
  `p_designation` VARCHAR(255) NULL,
  `b_title` VARCHAR(255) NULL,
  `c_email` VARCHAR(255) NOT NULL,
  `c_contact` VARCHAR(255) NOT NULL,
  `GST Number` VARCHAR(255) NULL,
  `c_address` TEXT(2000) NULL,
  `postal_code` VARCHAR(255) NULL,
  `city` VARCHAR(255) NOT NULL,
  `state` VARCHAR(255) NOT NULL,
  `isactive` TINYINT(1) NULL,
  PRIMARY KEY (`cid`))
ENGINE = InnoDB
COMMENT = 'Client Table for Invoice.';