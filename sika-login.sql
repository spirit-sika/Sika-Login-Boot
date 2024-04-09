/*
 Navicat Premium Data Transfer

 Source Server         : MySQL8
 Source Server Type    : MySQL
 Source Server Version : 80034 (8.0.34)
 Source Host           : localhost:3306
 Source Schema         : sika-login

 Target Server Type    : MySQL
 Target Server Version : 80034 (8.0.34)
 File Encoding         : 65001

 Date: 09/04/2024 11:14:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sika_permission
-- ----------------------------
DROP TABLE IF EXISTS `sika_permission`;
CREATE TABLE `sika_permission`  (
  `permission_id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限唯一标识符',
  `permission_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限名称',
  `permission_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限描述',
  `permission_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限类型(菜单权限, 操作权限)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`permission_id`) USING BTREE,
  UNIQUE INDEX `permission_name`(`permission_content` ASC) USING BTREE COMMENT '权限字符串唯一性索引'
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限字符串表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sika_permission
-- ----------------------------
INSERT INTO `sika_permission` VALUES (1, '*:*:*', '通用权限', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sika_role
-- ----------------------------
DROP TABLE IF EXISTS `sika_role`;
CREATE TABLE `sika_role`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色唯一标识符(主键)',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `role_name`(`role_name` ASC) USING BTREE COMMENT '角色名称唯一性索引'
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sika_role
-- ----------------------------
INSERT INTO `sika_role` VALUES (1, 'admin', '管理员', '2024-04-03 21:42:48', '2024-04-03 21:42:48');

-- ----------------------------
-- Table structure for sika_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sika_role_permission`;
CREATE TABLE `sika_role_permission`  (
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint NULL DEFAULT NULL COMMENT '权限字符串id',
  INDEX `idx_role_id`(`role_id` ASC) USING BTREE,
  INDEX `idx_role_permission`(`role_id` ASC, `permission_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sika_role_permission
-- ----------------------------
INSERT INTO `sika_role_permission` VALUES (1, 1);

-- ----------------------------
-- Table structure for sika_user
-- ----------------------------
DROP TABLE IF EXISTS `sika_user`;
CREATE TABLE `sika_user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户唯一标识',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `status` int NULL DEFAULT 1 COMMENT '状态 0:禁用 1:正常',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像地址',
  `sex` tinyint NULL DEFAULT NULL COMMENT '性别 0:未知 1:男 2:女',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE COMMENT '用户名唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sika_user
-- ----------------------------
INSERT INTO `sika_user` VALUES (23, 'spirit', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '2024-04-03 21:38:42', '2024-04-03 21:38:46', 1, '2235569493@qq.com', '13553727721', NULL, 1);

-- ----------------------------
-- Table structure for sika_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sika_user_role`;
CREATE TABLE `sika_user_role`  (
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户标识符',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色标识符',
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE COMMENT '用户id索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sika_user_role
-- ----------------------------
INSERT INTO `sika_user_role` VALUES (23, 1);

SET FOREIGN_KEY_CHECKS = 1;
