/* SQL Manager Lite for MySQL                              5.8.0.53447 */
/* ------------------------------------------------------------------- */
/* Host     : localhost                                                */
/* Port     : 3306                                                     */
/* Database : bank                                                     */


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES 'utf8mb4' */;

SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS `bank`;

CREATE DATABASE `bank`
    CHARACTER SET 'utf8mb4'
    COLLATE 'utf8mb4_0900_ai_ci';

USE `bank`;

SET sql_mode = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';

/* Dropping database objects */

DROP TABLE IF EXISTS `deposit`;
DROP TABLE IF EXISTS `credit`;
DROP TABLE IF EXISTS `bill`;
DROP TABLE IF EXISTS `account`;

/* Structure for the `account` table : */

CREATE TABLE `account` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(30) COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` VARCHAR(30) COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` VARCHAR(40) COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` VARCHAR(30) COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `surname` VARCHAR(30) COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone_number` VARCHAR(30) COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY USING BTREE (`id`),
  UNIQUE KEY `id` USING BTREE (`id`),
  UNIQUE KEY `login` USING BTREE (`login`),
  UNIQUE KEY `email` USING BTREE (`email`),
  UNIQUE KEY `phone_number` USING BTREE (`phone_number`)
) ENGINE=InnoDB
AUTO_INCREMENT=12 ROW_FORMAT=DYNAMIC CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci'
;

/* Structure for the `bill` table : */

CREATE TABLE `bill` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `type` ENUM('DEPOSIT','CREDIT','UNSIGNED') COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `balance` FLOAT NOT NULL,
  `validity` DATE DEFAULT NULL,
  `account_id` INTEGER NOT NULL,
  PRIMARY KEY USING BTREE (`id`),
  UNIQUE KEY `id` USING BTREE (`id`),
  KEY `bill_fk1` USING BTREE (`account_id`),
  CONSTRAINT `bill_fk1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB
AUTO_INCREMENT=46 ROW_FORMAT=DYNAMIC CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci'
;

/* Structure for the `credit` table : */

CREATE TABLE `credit` (
  `id` INTEGER NOT NULL,
  `debt` FLOAT NOT NULL,
  `limit` FLOAT NOT NULL,
  `percentage` FLOAT NOT NULL,
  `start` DATE NOT NULL,
  `deadline` DATE NOT NULL,
  PRIMARY KEY USING BTREE (`id`),
  UNIQUE KEY `id` USING BTREE (`id`),
  CONSTRAINT `credit_fk1` FOREIGN KEY (`id`) REFERENCES `bill` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB
ROW_FORMAT=DYNAMIC CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci'
;

/* Structure for the `deposit` table : */

CREATE TABLE `deposit` (
  `id` INTEGER NOT NULL,
  `amount` FLOAT NOT NULL,
  `rate` FLOAT NOT NULL,
  `start` DATE NOT NULL,
  `finish` DATE NOT NULL,
  PRIMARY KEY USING BTREE (`id`),
  UNIQUE KEY `id` USING BTREE (`id`),
  CONSTRAINT `deposit_fk1` FOREIGN KEY (`id`) REFERENCES `bill` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB
ROW_FORMAT=DYNAMIC CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci'
;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;