/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.44-log : Database - aop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `t_opt_log_detail` */

DROP TABLE IF EXISTS `t_opt_log_detail`;

CREATE TABLE `t_opt_log_detail` (
  `opt_log_detail_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `opt_log_id` bigint(20) DEFAULT NULL,
  `diff_field_value` text COLLATE utf8mb4_bin COMMENT '操作前后字段值差异比对结果',
  PRIMARY KEY (`opt_log_detail_id`),
  KEY `index_opt_id` (`opt_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_opt_log_original` */

DROP TABLE IF EXISTS `t_opt_log_original`;

CREATE TABLE `t_opt_log_original` (
  `opt_log_original_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `opt_log_id` bigint(20) NOT NULL,
  `method` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求方法',
  `column_name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '查询列名',
  `table_name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '查询表名',
  `column_value` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '查询列值',
  `column_paramter_name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '查询列对应参数名',
  `request_params_and_value` text COLLATE utf8mb4_bin COMMENT '请求参数和值',
  `before_operator_data` text COLLATE utf8mb4_bin COMMENT '操作前数据库值',
  `after_operator_data` text COLLATE utf8mb4_bin COMMENT '操作后数据库值',
  `return_value` text COLLATE utf8mb4_bin COMMENT '返回结果',
  `summary_column_name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '需要展示的列名',
  `summary_column_value` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '需要展示列名对应的值',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`opt_log_original_id`),
  KEY `index_opt_id` (`opt_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1231497452472324098 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_opt_log_summary` */

DROP TABLE IF EXISTS `t_opt_log_summary`;

CREATE TABLE `t_opt_log_summary` (
  `opt_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增长',
  `user_id` int(11) DEFAULT NULL COMMENT '操作人id',
  `user_name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人用户名',
  `ip` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户ip',
  `run_time` int(11) DEFAULT NULL COMMENT '运行时间',
  `operator_type` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作类型',
  `opt_summary` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作概述',
  `trace_id` bigint(20) DEFAULT NULL COMMENT '唯一追踪id',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`opt_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1231497452413603843 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_person` */

DROP TABLE IF EXISTS `t_person`;

CREATE TABLE `t_person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `email` varchar(64) DEFAULT '',
  `address` varchar(256) DEFAULT NULL,
  `state` int(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
