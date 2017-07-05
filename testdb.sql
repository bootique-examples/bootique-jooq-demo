 -----------------------------------------------------
-- Schema testdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `testdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

-- -----------------------------------------------------
-- Table `testdb`.`domain`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testdb`.`domain` (
  `id` BIGINT(19) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `host` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`));

-- Insert data
INSERT INTO testdb.domain (name,host) VALUES('Bootique', 'http://bootique.io/docs/0/getting-started ');
INSERT INTO testdb.domain (name,host) VALUES('LinkMove', 'https://github.com/nhl/link-move ');
INSERT INTO testdb.domain (name,host) VALUES('ObjectStyle LLC', 'https://www.objectstyle.com/about ');

