-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.28 - MySQL Community Server - GPL
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 sbm 的数据库结构
CREATE DATABASE IF NOT EXISTS `sbm` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sbm`;

-- 导出  表 sbm.sys_log 结构
CREATE TABLE IF NOT EXISTS `sys_log` (
                                         `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                         `operation` varchar(255) DEFAULT NULL COMMENT '操作',
                                         `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作用户名',
                                         `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作方法',
                                         `params` varchar(10240) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作方法参数',
                                         `cost` bigint DEFAULT NULL COMMENT '耗时',
                                         `ip` varchar(50) DEFAULT NULL COMMENT '操作人ip',
                                         `create_user` bigint DEFAULT NULL COMMENT '创建人',
                                         `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                         `last_edit_user` bigint DEFAULT NULL COMMENT '修改人',
                                         `last_edit_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统日志表';

-- 数据导出被取消选择。

-- 导出  表 sbm.sys_menu 结构
CREATE TABLE IF NOT EXISTS `sys_menu` (
                                          `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                          `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
                                          `menu_url` varchar(50) DEFAULT NULL COMMENT '菜单路径',
                                          `menu_icon` varchar(10240) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单图标',
                                          `parent_id` bigint DEFAULT NULL COMMENT '父级菜单ID',
                                          `sort` int DEFAULT NULL COMMENT '排序',
                                          `description` varchar(255) DEFAULT NULL COMMENT '备注',
                                          `create_user` bigint DEFAULT NULL COMMENT '创建人',
                                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                          `last_edit_user` bigint DEFAULT NULL COMMENT '修改人',
                                          `last_edit_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统菜单表';

-- 数据导出被取消选择。

-- 导出  表 sbm.sys_org 结构
CREATE TABLE IF NOT EXISTS `sys_org` (
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统组织表';

-- 数据导出被取消选择。

-- 导出  表 sbm.sys_role 结构
CREATE TABLE IF NOT EXISTS `sys_role` (
                                          `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                          `role_code` varchar(50) DEFAULT NULL COMMENT '角色编码',
                                          `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
                                          `description` varchar(255) DEFAULT NULL COMMENT '备注',
                                          `create_user` bigint DEFAULT NULL COMMENT '创建人',
                                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                          `last_edit_user` bigint DEFAULT NULL COMMENT '修改人',
                                          `last_edit_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统角色表';

-- 数据导出被取消选择。

-- 导出  表 sbm.sys_role_menu 结构
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
                                               `id` bigint NOT NULL AUTO_INCREMENT,
                                               `menu_id` bigint DEFAULT NULL COMMENT '菜单ID',
                                               `role_id` bigint DEFAULT NULL COMMENT '角色ID',
                                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统菜单和角色关联表';

-- 数据导出被取消选择。

-- 导出  表 sbm.sys_user 结构
CREATE TABLE IF NOT EXISTS `sys_user` (
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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户表';

-- 数据导出被取消选择。

-- 导出  表 sbm.sys_user_role 结构
CREATE TABLE IF NOT EXISTS `sys_user_role` (
                                               `id` bigint NOT NULL AUTO_INCREMENT,
                                               `user_id` bigint DEFAULT NULL COMMENT '用户ID',
                                               `role_id` bigint DEFAULT NULL COMMENT '角色ID',
                                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户和角色关联表';

-- 数据导出被取消选择。
-- 正在导出表  sbm.sys_menu 的数据：~6 rows (大约)
DELETE FROM `sys_menu`;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_url`, `menu_icon`, `parent_id`, `sort`, `description`, `create_user`, `create_time`, `last_edit_user`, `last_edit_time`) VALUES
                                                                                                                                                                              (1, '系统管理', '/setting', 'setting', NULL, 0, NULL, 2, '2022-04-08 14:49:56', 2, '2022-04-12 13:18:21'),
                                                                                                                                                                              (6, '用户管理', '/setting/user', ' ', 1, 2, NULL, 2, '2022-04-11 09:57:43', 2, '2022-04-12 13:20:19'),
                                                                                                                                                                              (7, '菜单管理', '/setting/menu', NULL, 1, 3, NULL, 2, '2022-04-12 13:23:46', NULL, NULL),
                                                                                                                                                                              (8, '角色管理', '/setting/role', NULL, 1, 4, NULL, 2, '2022-04-12 13:24:18', NULL, NULL),
                                                                                                                                                                              (9, '组织管理', '/setting/org', NULL, 1, 5, NULL, 2, '2022-04-12 13:24:47', NULL, NULL),
                                                                                                                                                                              (11, '日志管理', '/setting/log', NULL, 1, 6, NULL, 2, '2022-04-12 13:25:43', NULL, NULL);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;

-- 正在导出表  sbm.sys_org 的数据：~5 rows (大约)
DELETE FROM `sys_org`;
/*!40000 ALTER TABLE `sys_org` DISABLE KEYS */;
INSERT INTO `sys_org` (`id`, `org_name`, `org_code`, `parent_id`, `description`, `create_user`, `create_time`, `last_edit_user`, `last_edit_time`) VALUES
                                                                                                                                                       (1, 'IT技术部门', 'it_001', NULL, '测试', NULL, '2022-04-07 13:48:10', NULL, '2022-04-07 13:52:57'),
                                                                                                                                                       (2, 'NOC', '002', 1, NULL, NULL, '2022-04-07 14:18:40', NULL, NULL),
                                                                                                                                                       (3, 'HR', 'hr_001', NULL, NULL, NULL, '2022-04-07 14:19:36', NULL, NULL),
                                                                                                                                                       (4, 'noc1', 'noc1', 1, 'noc1', NULL, '2022-04-07 14:34:11', NULL, '2022-04-07 15:05:53'),
                                                                                                                                                       (6, 'HR001', 'HR001', 3, 'HR001', NULL, '2022-04-07 14:35:54', NULL, NULL);
/*!40000 ALTER TABLE `sys_org` ENABLE KEYS */;

-- 正在导出表  sbm.sys_role 的数据：~2 rows (大约)
DELETE FROM `sys_role`;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`id`, `role_code`, `role_name`, `description`, `create_user`, `create_time`, `last_edit_user`, `last_edit_time`) VALUES
                                                                                                                                             (1, 'admin', 'admin', '管理员', 2, '2022-04-10 04:29:26', 2, '2022-04-11 07:53:48'),
                                                                                                                                             (2, 'member', 'member', 'member', 2, '2022-04-10 02:12:12', NULL, NULL);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

-- 正在导出表  sbm.sys_role_menu 的数据：~9 rows (大约)
DELETE FROM `sys_role_menu`;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` (`id`, `menu_id`, `role_id`) VALUES
                                                             (11, 5, 3),
                                                             (12, 2, 3),
                                                             (31, 1, 1),
                                                             (32, 6, 1),
                                                             (33, 7, 1),
                                                             (34, 8, 1),
                                                             (35, 11, 1),
                                                             (36, 9, 1),
                                                             (37, 12, 1);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;

-- 正在导出表  sbm.sys_user 的数据：~29 rows (大约)
DELETE FROM `sys_user`;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id`, `username`, `password`, `name`, `email`, `description`, `del_flag`, `org_id`, `create_user`, `create_time`, `last_edit_user`, `last_edit_time`) VALUES
                                                                                                                                                                                  (2, 'admin', 'cb876eb3c8baaabc5d0c86786fb11b0b', 'Admin', 'admin@163.com', 'default admin user', 0, NULL, NULL, '2022-03-31 13:57:56', 2, '2022-04-10 05:01:06'),
                                                                                                                                                                                  (35, 'member', '1f8b0399cae63338a11b2c270190bef6', 'Member', NULL, NULL, 0, NULL, 2, '2022-04-17 10:57:51', NULL, NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

-- 正在导出表  sbm.sys_user_role 的数据：~5 rows (大约)
DELETE FROM `sys_user_role`;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES
                                                             (3, 32, 2),
                                                             (5, 2, 1),
                                                             (8, 24, 1),
                                                             (9, 3, 1),
                                                             (14, 35, 2);
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
