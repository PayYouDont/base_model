/*
 Navicat Premium Data Transfer

 Source Server         : baseMysql
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : base-mysql:3306
 Source Schema         : base_model

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 20/04/2020 13:53:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app_user
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `device_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `online` bit(1) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `checked` bit(1) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` int(11) NULL DEFAULT NULL,
  `permissions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pid` int(11) NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKilsmbm8k8qqnrc9wombfa73po`(`pid`) USING BTREE,
  CONSTRAINT `FKilsmbm8k8qqnrc9wombfa73po` FOREIGN KEY (`pid`) REFERENCES `sys_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (0, '超级管理员', '2020-04-15 16:00:13', 1, '超级管理员', '2020-04-15 16:00:13', b'0', '根菜单', '', 0, 'root', NULL, 'open', '作为一级菜单', '');
INSERT INTO `sys_menu` VALUES (2, '超级管理员', '2020-04-15 16:05:23', 0, '超级管理员', '2020-04-15 16:36:42', b'0', '控制台', 'layui-icon-home', 1, 'menu:home', 0, 'open', '控制台', '');
INSERT INTO `sys_menu` VALUES (3, '超级管理员', '2020-04-15 16:07:32', 0, '超级管理员', '2020-04-15 16:07:32', b'0', '主页', '', 11, 'menu:index', 2, 'open', '主页', '/home/console');
INSERT INTO `sys_menu` VALUES (4, '超级管理员', '2020-04-15 16:09:53', 0, '超级管理员', '2020-04-15 16:37:16', b'0', '系统设置', 'layui-icon-set', 2, 'menu:sys', 0, 'open', '系统设置', '');
INSERT INTO `sys_menu` VALUES (5, '超级管理员', '2020-04-15 16:11:56', 0, '超级管理员', '2020-04-15 16:11:56', b'0', '菜单管理', '', 21, 'menu:menu', 4, 'open', '菜单管理', '/menu/toView');
INSERT INTO `sys_menu` VALUES (6, '超级管理员', '2020-04-15 16:13:29', 0, '超级管理员', '2020-04-15 16:17:35', b'0', '角色管理', '', 22, 'menu:role', 4, 'open', '角色管理', '/role/toView');
INSERT INTO `sys_menu` VALUES (7, '超级管理员', '2020-04-15 16:18:40', 0, '超级管理员', '2020-04-15 16:18:40', b'0', '系统用户管理', '', 23, 'menu:user', 4, 'open', '系统用户', '/user/toView');
INSERT INTO `sys_menu` VALUES (8, '超级管理员', '2020-04-15 16:43:03', 0, '超级管理员', '2020-04-15 16:43:03', b'0', '权限管理', '', 24, 'menu:permission', 4, 'open', '权限管理', '/permission/toView');
INSERT INTO `sys_menu` VALUES (9, '超级管理员', '2020-04-16 15:16:25', 0, '超级管理员', '2020-04-16 15:16:25', b'0', '文档接口管理', 'layui-icon-read', 3, 'menu:api', 0, 'open', '开发文档', '');
INSERT INTO `sys_menu` VALUES (10, '超级管理员', '2020-04-16 15:27:40', 0, '超级管理员', '2020-04-16 15:27:40', b'0', '后台swagger文档接口', '', 31, 'menu:api:back', 9, 'open', '后台接口', '/doc.html');

-- ----------------------------
-- Table structure for sys_menu_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role_relation`;
CREATE TABLE `sys_menu_role_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `is_add` bit(1) NULL DEFAULT NULL,
  `is_delete` bit(1) NULL DEFAULT NULL,
  `is_modify` bit(1) NULL DEFAULT NULL,
  `is_view` bit(1) NULL DEFAULT NULL,
  `menu_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKi406fen1n5vgkkj3w61xbskge`(`menu_id`) USING BTREE,
  INDEX `FKapjt9h5sc6noyk7j55wdqax32`(`role_id`) USING BTREE,
  CONSTRAINT `FKapjt9h5sc6noyk7j55wdqax32` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKi406fen1n5vgkkj3w61xbskge` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', '2020-04-15 16:16:21', NULL, '超级管理员', '2020-04-15 16:16:21', '权力很大！责任很重！', '1', '超级管理员', 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  `sex` int(11) NULL DEFAULT NULL,
  `user_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (2, NULL, NULL, NULL, NULL, NULL, 'admin', NULL, '超级管理员', 'd0af8fa1272ef5a152d9e27763eea293', NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
