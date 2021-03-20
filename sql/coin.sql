CREATE DATABASE /*!32312 IF NOT EXISTS*/`coin` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `coin`;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL ,
  `password` varchar(32) DEFAULT NULL ,
  `email` varchar(32) DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `user` (`id`,`username`,`password`,`email`) VALUES
(1,'Test','123123','123@qq.com'),
(2,'motherfucker2','123321','123321@qq.com')