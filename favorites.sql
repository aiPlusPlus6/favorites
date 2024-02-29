/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50716 (5.7.16)
 Source Host           : localhost:3306
 Source Schema         : favorites

 Target Server Type    : MySQL
 Target Server Version : 50716 (5.7.16)
 File Encoding         : 65001

 Date: 29/02/2024 09:36:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `menu_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NOT NULL DEFAULT 0 COMMENT '显示顺序',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '#' COMMENT '请求地址',
  `target` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `visible` tinyint(2) NOT NULL DEFAULT 0 COMMENT '菜单状态（0显示 1隐藏）',
  `is_refresh` tinyint(2) NOT NULL DEFAULT 1 COMMENT '是否刷新（0刷新 1不刷新）',
  `perms` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '权限标识',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '#' COMMENT '菜单图标',
  `menu_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '菜单类型（1目录 2菜单 3按钮）',
  `create_date` datetime(3) NOT NULL COMMENT '创建时间',
  `update_date` datetime(3) NOT NULL COMMENT '修改时间',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建者id',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '修改者id',
  `version` int(11) NOT NULL COMMENT '版本号',
  `deleted` bit(1) NOT NULL COMMENT '删除标识 0-未删除 1-删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` tinyint(2) NOT NULL DEFAULT 1 COMMENT '数据范围（1：全部数据权限 2：自定数据权限 ',
  `status` tinyint(2) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `create_date` datetime(3) NOT NULL COMMENT '创建时间',
  `update_date` datetime(3) NOT NULL COMMENT '修改时间',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建者id',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '修改者id',
  `version` int(11) NOT NULL COMMENT '版本号',
  `deleted` bit(1) NOT NULL COMMENT '删除标识 0-未删除 1-删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 0, 1, 0, '2024-02-19 16:18:43.000', '2024-02-19 16:18:47.000', '1', '1', 1, b'0');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单id',
  `create_date` datetime(3) NOT NULL COMMENT '创建时间',
  `update_date` datetime(3) NOT NULL COMMENT '修改时间',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建者id',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '修改者id',
  `version` int(11) NOT NULL COMMENT '版本号',
  `deleted` bit(1) NOT NULL COMMENT '删除标识 0-未删除 1-删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '手机号码',
  `gender` tinyint(2) NOT NULL COMMENT '0女 1男',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '头像存储地址',
  `signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '个性签名',
  `status` tinyint(2) NOT NULL COMMENT '0启用 1禁用',
  `create_date` datetime(3) NOT NULL COMMENT '创建时间',
  `update_date` datetime(3) NOT NULL COMMENT '修改时间',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建者id',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '修改者id',
  `version` int(11) NOT NULL COMMENT '版本号',
  `deleted` bit(1) NOT NULL COMMENT '删除标识 0-未删除 1-删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$c1eZ1jAmfXB5aOAhHltPAOUbV7UTQjV/lSrpUDiEI9pvHCtSlFqIK', '110', 0, 'sdadsd', 'dsdasd', 0, '2024-02-19 16:16:07.000', '2024-02-19 16:17:23.000', '1', '1', 1, b'0');
INSERT INTO `sys_user` VALUES (1762725366572830721, 'aiplusplus', '$2a$10$c1eZ1jAmfXB5aOAhHltPAOUbV7UTQjV/lSrpUDiEI9pvHCtSlFqIK', '18327072541', 0, 'avatar_r9tic', '永远爱宝宝', 0, '2024-02-28 14:24:11.903', '2024-02-28 14:24:12.746', '1', '1', 1, b'0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `create_date` datetime(3) NOT NULL COMMENT '创建时间',
  `update_date` datetime(3) NOT NULL COMMENT '修改时间',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建者id',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '修改者id',
  `version` int(11) NOT NULL COMMENT '版本号',
  `deleted` bit(1) NOT NULL COMMENT '删除标识 0-未删除 1-删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1, '2024-02-19 16:22:40.000', '2024-02-19 16:22:45.000', '1', '1', 1, b'0');
INSERT INTO `sys_user_role` VALUES (2, 1762725366572830721, 1, '2024-02-28 14:36:19.000', '2024-02-28 14:36:26.000', '1', '1', 1, b'0');

SET FOREIGN_KEY_CHECKS = 1;
