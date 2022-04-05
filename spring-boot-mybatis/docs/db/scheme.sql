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


-- sbm.sys_log definition

CREATE TABLE `sys_log` (
                           `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                           `operation` varchar(255) DEFAULT NULL COMMENT '操作',
                           `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作用户名',
                           `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作方法',
                           `params` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作方法参数',
                           `cost` bigint DEFAULT NULL COMMENT '耗时',
                           `ip` varchar(50) DEFAULT NULL COMMENT '操作人ip',
                           `create_user` bigint DEFAULT NULL COMMENT '创建人',
                           `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `last_edit_user` bigint DEFAULT NULL COMMENT '修改人',
                           `last_edit_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统日志表';


-- sbm.sys_menu definition

CREATE TABLE `sys_menu` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                            `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
                            `menu_url` varchar(50) DEFAULT NULL COMMENT '菜单路径',
                            `menu_icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
                            `parent_id` bigint DEFAULT NULL COMMENT '父级菜单ID',
                            `sort` int DEFAULT NULL COMMENT '排序',
                            `description` varchar(255) DEFAULT NULL COMMENT '备注',
                            `create_user` bigint DEFAULT NULL COMMENT '创建人',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `last_edit_user` bigint DEFAULT NULL COMMENT '修改人',
                            `last_edit_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统菜单表';


-- sbm.sys_org definition

CREATE TABLE `sys_org` (
                           `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                           `org_name` varchar(50) DEFAULT NULL COMMENT '组织名称',
                           `org_code` varchar(50) DEFAULT NULL COMMENT '组织编码',
                           `parent_id` bigint DEFAULT NULL COMMENT '父级组织ID',
                           `description` varchar(255) DEFAULT NULL COMMENT '备注',
                           `create_user` bigint DEFAULT NULL COMMENT '创建人',
                           `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `last_edit_user` bigint DEFAULT NULL COMMENT '修改人',
                           `last_edit_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统组织表';


-- sbm.sys_role definition

CREATE TABLE `sys_role` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                            `role_code` varchar(50) DEFAULT NULL COMMENT '角色编码',
                            `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
                            `description` varchar(255) DEFAULT NULL COMMENT '备注',
                            `org_id` bigint DEFAULT NULL COMMENT '组织ID',
                            `create_user` bigint DEFAULT NULL COMMENT '创建人',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `last_edit_user` bigint DEFAULT NULL COMMENT '修改人',
                            `last_edit_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统角色表';


-- sbm.sys_role_menu definition

CREATE TABLE `sys_role_menu` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `menu_id` bigint DEFAULT NULL COMMENT '菜单ID',
                                 `role_id` bigint DEFAULT NULL COMMENT '角色ID',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统菜单和角色关联表';


-- sbm.sys_user definition

CREATE TABLE `sys_user` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                            `username` varchar(50) DEFAULT NULL COMMENT '用户名',
                            `password` varchar(50) DEFAULT NULL COMMENT '密码',
                            `name` varchar(50) DEFAULT NULL COMMENT '姓名',
                            `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
                            `description` varchar(255) DEFAULT NULL COMMENT '备注',
                            `del_flag` int NOT NULL DEFAULT '0' COMMENT '删除标识（1代表删除）',
                            `org_id` bigint DEFAULT NULL COMMENT '组织ID',
                            `create_user` bigint DEFAULT NULL COMMENT '创建人',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `last_edit_user` bigint DEFAULT NULL COMMENT '修改人',
                            `last_edit_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户表';


-- sbm.sys_user_role definition

CREATE TABLE `sys_user_role` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `user_id` bigint DEFAULT NULL COMMENT '用户ID',
                                 `role_id` bigint DEFAULT NULL COMMENT '角色ID',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户和角色关联表';
