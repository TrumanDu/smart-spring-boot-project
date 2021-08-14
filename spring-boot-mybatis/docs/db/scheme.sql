-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               8.0.26 - MySQL Community Server - GPL
-- Server OS:                    Linux
-- HeidiSQL Version:             8.3.0.4811
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for sbm
DROP DATABASE IF EXISTS `sbm`;
CREATE DATABASE IF NOT EXISTS `sbm` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sbm`;


-- Dumping structure for table sbm.sys_menu
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(50) DEFAULT NULL COMMENT '菜单路径',
  `menu_icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `parent_id` bigint DEFAULT NULL COMMENT '父级菜单ID',
  `sort` int DEFAULT NULL COMMENT '排序',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  `in_user` bigint DEFAULT NULL COMMENT '创建人',
  `in_date` datetime DEFAULT NULL COMMENT '创建时间',
  `last_edit_user` bigint DEFAULT NULL COMMENT '修改人',
  `last_edit_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统菜单表';

-- Data exporting was unselected.


-- Dumping structure for table sbm.sys_org
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE IF NOT EXISTS `sys_org` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `org_name` varchar(50) DEFAULT NULL COMMENT '组织名称',
  `org_code` varchar(50) DEFAULT NULL COMMENT '组织编码',
  `parent_id` bigint DEFAULT NULL COMMENT '父级组织ID',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  `in_user` bigint DEFAULT NULL COMMENT '创建人',
  `in_date` datetime DEFAULT NULL COMMENT '创建时间',
  `last_edit_user` bigint DEFAULT NULL COMMENT '修改人',
  `last_edit_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统组织表';

-- Data exporting was unselected.


-- Dumping structure for table sbm.sys_role
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_code` varchar(50) DEFAULT NULL COMMENT '角色编码',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  `org_id` bigint DEFAULT NULL COMMENT '组织ID',
  `in_user` bigint DEFAULT NULL COMMENT '创建人',
  `in_date` datetime DEFAULT NULL COMMENT '创建时间',
  `last_edit_user` bigint DEFAULT NULL COMMENT '修改人',
  `last_edit_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统角色表';

-- Data exporting was unselected.


-- Dumping structure for table sbm.sys_role_menu
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `menu_id` bigint DEFAULT NULL COMMENT '菜单ID',
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统菜单和角色关联表';

-- Data exporting was unselected.


-- Dumping structure for table sbm.sys_user
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `org_id` bigint DEFAULT NULL COMMENT '组织ID',
  `in_user` bigint DEFAULT NULL COMMENT '创建人',
  `in_date` datetime DEFAULT NULL COMMENT '创建时间',
  `last_edit_user` bigint DEFAULT NULL COMMENT '修改人',
  `last_edit_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户表';

-- Data exporting was unselected.


-- Dumping structure for table sbm.sys_user_role
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户和角色关联表';

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
