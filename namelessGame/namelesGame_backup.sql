CREATE DATABASE  IF NOT EXISTS `namelessgame` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `namelessgame`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: namelessgame
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dungeon`
--

DROP TABLE IF EXISTS `dungeon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dungeon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `descr` varchar(45) NOT NULL,
  `min_lv` int(11) DEFAULT '0',
  `background` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`player_id`),
  KEY `fk_inventory_player1_idx` (`player_id`),
  CONSTRAINT `fk_inventory_player1` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inventory_has_item`
--

DROP TABLE IF EXISTS `inventory_has_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `inventory_has_item` (
  `inventory_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `count` int(11) DEFAULT '1',
  KEY `fk_inventory_has_item_item1_idx` (`item_id`),
  KEY `fk_inventory_has_item_inventory1_idx` (`inventory_id`),
  CONSTRAINT `fk_inventory_has_item_inventory1` FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_inventory_has_item_item1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `str` int(11) DEFAULT '0',
  `agi` int(11) DEFAULT '0',
  `con` int(11) DEFAULT '0',
  `heal` int(11) DEFAULT '0',
  `slot` int(11) DEFAULT '0',
  `min_lv` int(11) DEFAULT '0',
  `name` varchar(45) NOT NULL,
  `icon` varchar(45) NOT NULL,
  `stackable` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `monster`
--

DROP TABLE IF EXISTS `monster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `monster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `str` int(11) DEFAULT '5',
  `agi` int(11) DEFAULT '5',
  `con` int(11) DEFAULT '5',
  `exp` int(11) DEFAULT '0',
  `gold_min` int(11) DEFAULT '0',
  `gold_max` int(11) DEFAULT '0',
  `dungeon_id` int(11) NOT NULL,
  `round` int(11) DEFAULT '1',
  `icon` varchar(45) NOT NULL,
  PRIMARY KEY (`id`,`dungeon_id`),
  KEY `fk_monster_dungeon1_idx` (`dungeon_id`),
  CONSTRAINT `fk_monster_dungeon1` FOREIGN KEY (`dungeon_id`) REFERENCES `dungeon` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `monster_has_item`
--

DROP TABLE IF EXISTS `monster_has_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `monster_has_item` (
  `monster_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `count_min` int(11) DEFAULT '1',
  `chance` int(11) DEFAULT '100',
  `count_max` int(11) DEFAULT NULL,
  KEY `fk_monster_has_item_item1_idx` (`item_id`),
  KEY `fk_monster_has_item_monster1_idx` (`monster_id`),
  CONSTRAINT `fk_monster_has_item_item1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `fk_monster_has_item_monster1` FOREIGN KEY (`monster_id`) REFERENCES `monster` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `sex` varchar(1) NOT NULL,
  `level` int(11) DEFAULT '1',
  `exp` int(11) DEFAULT '0',
  `gold` bigint(20) DEFAULT '0',
  `status_points` int(11) DEFAULT '0',
  `str` int(11) DEFAULT '5',
  `agi` int(11) DEFAULT '5',
  `con` int(11) DEFAULT '5',
  `head` int(11) DEFAULT NULL,
  `body` int(11) DEFAULT NULL,
  `weapon` int(11) DEFAULT NULL,
  `shield` int(11) DEFAULT NULL,
  `legs` int(11) DEFAULT NULL,
  `boots` int(11) DEFAULT NULL,
  `avatar` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stash`
--

DROP TABLE IF EXISTS `stash`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `stash` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`player_id`),
  KEY `fk_stash_player1_idx` (`player_id`),
  CONSTRAINT `fk_stash_player1` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stash_has_item`
--

DROP TABLE IF EXISTS `stash_has_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `stash_has_item` (
  `stash_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `count` int(11) DEFAULT '1',
  KEY `fk_stash_has_item_item1_idx` (`item_id`),
  KEY `fk_stash_has_item_stash1_idx` (`stash_id`),
  CONSTRAINT `fk_stash_has_item_item1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_stash_has_item_stash1` FOREIGN KEY (`stash_id`) REFERENCES `stash` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-18 18:03:43
