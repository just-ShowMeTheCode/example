CREATE DATABASE `transactiontest` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

CREATE TABLE `t_person` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;