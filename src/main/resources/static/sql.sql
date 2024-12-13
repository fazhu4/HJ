/*
SQLyog Community v13.2.1 (64 bit)
MySQL - 8.0.36 : Database - hj
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hj` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `hj`;

/*Table structure for table `user` */

CREATE TABLE `user` (
  `uid` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `name` varchar(255) DEFAULT NULL COMMENT '用户姓名',
  `account` varchar(255) DEFAULT NULL COMMENT '用户账号',
  `password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `status` int NOT NULL DEFAULT '1' COMMENT '用户状态(1：正常，0：封号)',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`uid`,`avatar`,`name`,`account`,`password`,`status`) values 
(1,NULL,'fz','123','123',1),
(2,NULL,'fazhu','23','23',1),
(3,NULL,'fazhu4','423','234',1),
(4,NULL,'法助','531','213',1),
(5,NULL,'fz4','556','213',1),
(6,'/FYBS/img/avatar/0.jpg','用户453827','123','123',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
