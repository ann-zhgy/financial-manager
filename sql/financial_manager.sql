/*
 Navicat Premium Data Transfer

 Source Server         : aliyun mysql
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 39.97.251.105:33060
 Source Schema         : financial_manager

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 13/05/2020 12:19:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_deal
-- ----------------------------
DROP TABLE IF EXISTS `tb_deal`;
CREATE TABLE `tb_deal`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简介',
  `money` double(10, 2) NOT NULL COMMENT '金额',
  `type` int(0) NOT NULL COMMENT '类型：收入 | 支出',
  `created` datetime(0) NOT NULL COMMENT '创建时间',
  `updated` datetime(0) NOT NULL COMMENT '最后一次更新时间',
  `deal_time` date NULL DEFAULT NULL COMMENT '交易时间',
  `remark` varchar(220) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `open` int(0) NOT NULL COMMENT '是否公开信息',
  `user_id` bigint(0) UNSIGNED NOT NULL COMMENT '创建者ID',
  `deal_group` bigint(0) NULL DEFAULT NULL COMMENT '收支组',
  `plan_id` bigint(0) UNSIGNED NULL DEFAULT NULL COMMENT '所属计划',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `plan_id`(`plan_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_deal
-- ----------------------------
INSERT INTO `tb_deal` VALUES (40, '关联计划：测试1', 200.00, 1, '2020-05-12 02:17:49', '2020-05-12 21:50:02', '2020-05-12', NULL, 0, 56, NULL, 9);

-- ----------------------------
-- Table structure for tb_deal_group
-- ----------------------------
DROP TABLE IF EXISTS `tb_deal_group`;
CREATE TABLE `tb_deal_group`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名字',
  `created` datetime(0) NOT NULL COMMENT '创建时间',
  `updated` datetime(0) NOT NULL COMMENT '最后一次更新时间',
  `intro` varchar(220) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简介',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `open` int(0) NOT NULL DEFAULT 0 COMMENT '是否公开',
  `user_id` bigint(0) NOT NULL COMMENT '创建者ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_deal_group
-- ----------------------------

-- ----------------------------
-- Table structure for tb_family
-- ----------------------------
DROP TABLE IF EXISTS `tb_family`;
CREATE TABLE `tb_family`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名字',
  `created` datetime(0) NOT NULL COMMENT '创建时间',
  `updated` datetime(0) NOT NULL COMMENT '最后一次更新时间',
  `user_count` int(0) NOT NULL COMMENT '用户数量',
  `intro` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '介绍',
  `open` int(0) NOT NULL COMMENT '是否公开',
  `user_id` bigint(0) UNSIGNED NOT NULL COMMENT '创建者ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_family
-- ----------------------------
INSERT INTO `tb_family` VALUES (5, 'test01', '2020-05-13 01:32:46', '2020-05-13 01:32:46', 1, '0001', 0, 56);

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父权限',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `en_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限英文名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '授权路径',
  `weight` int(0) NOT NULL COMMENT '权重，用于排序',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `display` int(0) NOT NULL COMMENT '是否展示在管理列表中',
  `created` datetime(0) NOT NULL,
  `updated` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES (1, NULL, '用户管理', 'profile', 'parent-user', 1, '用户管理父级目录', 1, '2020-05-08 20:57:53', '2020-05-08 20:57:55');
INSERT INTO `tb_permission` VALUES (2, NULL, '计划管理', 'plan', 'parent-plan', 2, '计划管理父级目录', 1, '2020-05-08 20:58:52', '2020-05-08 20:58:54');
INSERT INTO `tb_permission` VALUES (3, NULL, '交易管理', 'deal', 'parent-deal', 3, '交易管理父级目录', 1, '2020-05-08 21:00:01', '2020-05-08 21:00:06');
INSERT INTO `tb_permission` VALUES (4, NULL, '交易组管理', 'deal group', 'parent-deal-group', 4, '交易组管理父级目录', 1, '2020-05-08 21:01:28', '2020-05-08 21:01:31');
INSERT INTO `tb_permission` VALUES (5, NULL, '家庭管理', 'family', 'parent-family', 5, '家庭管理父级目录', 1, '2020-05-08 21:02:28', '2020-05-08 21:02:31');
INSERT INTO `tb_permission` VALUES (6, 1, '查看个人信息', 'my info', '/profile', 101, NULL, 1, '2020-05-08 21:04:13', '2020-05-08 21:04:16');
INSERT INTO `tb_permission` VALUES (7, 1, '修改个人信息', 'update info', '/profile/modify', 102, NULL, 1, '2020-05-08 21:05:41', '2020-05-08 21:05:45');
INSERT INTO `tb_permission` VALUES (8, 1, '修改密码', 'update password', '/profile/password', 103, NULL, 0, '2020-05-08 21:07:01', '2020-05-08 21:06:59');
INSERT INTO `tb_permission` VALUES (9, 1, '修改头像', 'update icon', '/profile/icon', 104, NULL, 0, '2020-05-08 21:07:53', '2020-05-08 21:07:56');
INSERT INTO `tb_permission` VALUES (10, 1, '获取菜单', 'menu', '/profile/menu', 105, NULL, 0, '2020-05-08 21:40:27', '2020-05-08 21:40:30');
INSERT INTO `tb_permission` VALUES (11, 1, '删除账号', 'delete profile', '/profile/delete', 106, NULL, 0, '2020-05-08 21:42:33', '2020-05-08 21:42:36');
INSERT INTO `tb_permission` VALUES (12, 2, '创建计划', 'create plan', '/plan/save', 201, NULL, 1, '2020-05-08 21:13:52', '2020-05-08 21:13:55');
INSERT INTO `tb_permission` VALUES (13, 2, '计划列表', 'plan list', '/plan/list', 202, NULL, 1, '2020-05-08 21:09:02', '2020-05-08 21:09:04');
INSERT INTO `tb_permission` VALUES (14, 2, '计划列表 | 分页', 'plan list page', '/plan/list/page', 208, NULL, 0, '2020-05-09 20:00:22', '2020-05-09 20:00:25');
INSERT INTO `tb_permission` VALUES (15, 2, '计划详细信息', 'plan info', '/plan/*', 204, NULL, 0, '2020-05-08 21:09:57', '2020-05-08 21:10:00');
INSERT INTO `tb_permission` VALUES (16, 2, '更新计划信息', 'update plan info', '/plan/modify', 205, NULL, 1, '2020-05-08 21:11:25', '2020-05-08 21:11:28');
INSERT INTO `tb_permission` VALUES (17, 2, '修改计划状态', 'update plan status', '/plan/status', 206, NULL, 0, '2020-05-08 21:12:35', '2020-05-08 21:12:37');
INSERT INTO `tb_permission` VALUES (18, 2, '设置是否公开', 'set open', '/plan/open', 207, NULL, 0, '2020-05-08 21:46:50', '2020-05-08 21:46:53');
INSERT INTO `tb_permission` VALUES (19, 2, '获取未完成计划列表', 'available plans', '/plan/available', 203, NULL, 0, '2020-05-08 21:48:19', '2020-05-08 21:48:21');
INSERT INTO `tb_permission` VALUES (20, 3, '保存交易信息', 'save deal', '/deal/save', 301, NULL, 1, '2020-05-09 08:19:03', '2020-05-09 08:19:06');
INSERT INTO `tb_permission` VALUES (21, 3, '交易列表', 'deal list', '/deal/list', 302, NULL, 1, '2020-05-09 08:20:04', '2020-05-09 08:20:06');
INSERT INTO `tb_permission` VALUES (22, 3, '交易记录详情', 'deal info', '/deal/*', 304, NULL, 1, '2020-05-09 08:21:00', '2020-05-09 08:21:03');
INSERT INTO `tb_permission` VALUES (23, 3, '修改交易信息', 'deal modify', '/deal/modify', 305, NULL, 1, '2020-05-09 10:23:11', '2020-05-09 10:23:13');
INSERT INTO `tb_permission` VALUES (24, 3, '交易列表分页', 'deal list page', '/deal/list/page', 303, NULL, 0, '2020-05-09 10:18:37', '2020-05-09 10:18:39');
INSERT INTO `tb_permission` VALUES (25, 3, '设置是否公开', 'set open', '/deal/open', 306, NULL, 0, '2020-05-09 10:23:50', '2020-05-09 10:23:53');
INSERT INTO `tb_permission` VALUES (26, 3, '删除交易信息', 'delete deal', '/deal/delete', 307, NULL, 0, '2020-05-09 10:24:35', '2020-05-09 10:24:37');
INSERT INTO `tb_permission` VALUES (27, 5, '数据统计', 'member deals', '/deal/family/data', 504, NULL, 1, '2020-05-09 10:30:56', '2020-05-09 10:30:58');
INSERT INTO `tb_permission` VALUES (28, 4, '创建交易组', 'create deal group', '/deal/group/save', 401, NULL, 1, '2020-05-09 20:53:40', '2020-05-09 20:53:43');
INSERT INTO `tb_permission` VALUES (29, 4, '交易组列表', 'deal group list', '/deal/group/list', 402, NULL, 1, '2020-05-09 20:55:15', '2020-05-09 20:55:18');
INSERT INTO `tb_permission` VALUES (30, 4, '交易组列表  分页', 'deal group list page', '/deal/group/list/page', 403, NULL, 0, '2020-05-09 21:59:32', '2020-05-09 21:59:35');
INSERT INTO `tb_permission` VALUES (31, 4, '交易组详情', 'deal group info', '/deal/group/*', 404, NULL, 1, '2020-05-09 22:00:41', '2020-05-09 22:00:43');
INSERT INTO `tb_permission` VALUES (32, 4, '修改交易组信息', 'deal group modify', '/deal/group/modify', 405, NULL, 1, '2020-05-09 22:01:42', '2020-05-09 22:01:44');
INSERT INTO `tb_permission` VALUES (33, 4, '公开交易组信息', 'deal group open', '/deal/group/open', 406, NULL, 0, '2020-05-09 22:04:57', '2020-05-09 22:05:00');
INSERT INTO `tb_permission` VALUES (34, 4, '删除交易组', 'delete deal group', '/deal/group/delete', 407, NULL, 0, '2020-05-09 22:05:52', '2020-05-09 22:05:55');
INSERT INTO `tb_permission` VALUES (35, 4, '交易信息', 'group deal info ', '/deal/list/grop/*', 403, NULL, 1, '2020-05-09 08:24:57', '2020-05-09 08:24:59');
INSERT INTO `tb_permission` VALUES (36, 5, '创建家庭', 'create family', '/family/save', 501, NULL, 1, '2020-05-09 22:08:13', '2020-05-09 22:08:16');
INSERT INTO `tb_permission` VALUES (37, 5, '家庭详情', 'family info', '/family', 502, NULL, 1, '2020-05-09 22:09:10', '2020-05-09 22:09:12');
INSERT INTO `tb_permission` VALUES (38, 5, '获取家庭成员公开的计划', 'members plans', '/plan/family', 503, NULL, 1, '2020-05-08 22:18:41', '2020-05-08 22:18:43');
INSERT INTO `tb_permission` VALUES (39, 5, '获取家庭成员公开的交易记录', 'member deals', '/deal/family', 504, NULL, 0, '2020-05-09 10:28:49', '2020-05-09 10:28:52');
INSERT INTO `tb_permission` VALUES (40, 5, '通过用户名获取家庭信息', 'family info by username', '/family/*', 505, NULL, 0, '2020-05-09 22:10:47', '2020-05-09 22:10:50');
INSERT INTO `tb_permission` VALUES (41, 5, '获取家庭成员信息', 'family members', '/family/members', 506, NULL, 0, '2020-05-09 22:11:50', '2020-05-09 22:11:53');
INSERT INTO `tb_permission` VALUES (42, 5, '更新家庭信息', 'family modify', '/family/modify', 507, NULL, 1, '2020-05-09 22:12:42', '2020-05-09 22:12:44');
INSERT INTO `tb_permission` VALUES (43, 5, '退出家庭', 'exit family', '/family/exit', 508, NULL, 0, '2020-05-09 22:12:52', '2020-05-09 22:12:49');
INSERT INTO `tb_permission` VALUES (44, 5, '踢出家庭', 'kick out family', '/family/exit/*', 509, NULL, 0, '2020-05-09 22:16:25', '2020-05-09 22:16:28');
INSERT INTO `tb_permission` VALUES (45, 5, '删除家庭', 'delete family', '/family/delete', 510, NULL, 1, '2020-05-09 22:17:18', '2020-05-09 22:17:21');
INSERT INTO `tb_permission` VALUES (46, 5, '设置管理员', 'set admin', '/family/admin/*', 511, NULL, 0, '2020-05-09 22:18:21', '2020-05-09 22:18:23');
INSERT INTO `tb_permission` VALUES (47, 6, '加入家庭', 'join family', '/family/join', 512, NULL, 0, '2020-05-11 20:35:20', '2020-05-11 20:35:23');
INSERT INTO `tb_permission` VALUES (48, 3, '获取图表数据', 'data', '/deal/data', 308, NULL, 0, '2020-05-12 03:22:25', '2020-05-12 03:22:28');
INSERT INTO `tb_permission` VALUES (49, 5, '获取家庭图表数据', 'family data', '/deal/family/data', 513, NULL, 0, '2020-05-12 03:22:39', '2020-05-12 03:22:37');

-- ----------------------------
-- Table structure for tb_plan
-- ----------------------------
DROP TABLE IF EXISTS `tb_plan`;
CREATE TABLE `tb_plan`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(220) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名字',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简介',
  `money` double(10, 2) NOT NULL COMMENT '金额',
  `type` int(0) NOT NULL COMMENT '交易类型：收入 | 支出',
  `begin_time` date NOT NULL COMMENT '开始时间',
  `end_time` date NOT NULL COMMENT '结束时间',
  `finish_time` datetime(0) NULL DEFAULT NULL COMMENT '完成时间',
  `created` datetime(0) NOT NULL COMMENT '创建时间',
  `updated` datetime(0) NOT NULL COMMENT '最后一次更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` int(0) NOT NULL COMMENT '状态：1. 已完成 | 2. 未完成（默认） | 3. 已取消',
  `open` int(0) NOT NULL COMMENT '是否公开',
  `user_id` bigint(0) UNSIGNED NOT NULL COMMENT '创建者ID',
  `deal_id` bigint(0) UNSIGNED NULL DEFAULT NULL COMMENT '关联 交易记录',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `deal_id`(`deal_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_plan
-- ----------------------------
INSERT INTO `tb_plan` VALUES (9, 'test01', '测试1', 200.00, 1, '2020-04-08', '2020-04-24', '2020-05-12 02:17:49', '2020-05-09 23:01:52', '2020-05-12 02:13:04', '', 1, 1, 56, 40);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父角色',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `en_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色英文名称',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1, 6, '创建者', 'CREATOR', '特有权限 + 家庭公有权限 + 公有权限');
INSERT INTO `tb_role` VALUES (2, 6, '管理员', 'ADMIN', '特有权限 + 家庭公有权限 + 公有权限');
INSERT INTO `tb_role` VALUES (3, 6, '普通成员', 'MEMBER', '特有权限 + 家庭公有权限 + 公有权限');
INSERT INTO `tb_role` VALUES (4, 5, '普通用户', 'USER', '特有权限 + 公有权限');
INSERT INTO `tb_role` VALUES (5, NULL, 'public', 'PUBLIC', '公有权限');
INSERT INTO `tb_role` VALUES (6, NULL, 'family public', 'FAMILY_PUBLIC', '家庭公有权限');

-- ----------------------------
-- Table structure for tb_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(0) NOT NULL COMMENT '角色 ID',
  `permission_id` bigint(0) NOT NULL COMMENT '权限 ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_role_permission
-- ----------------------------
INSERT INTO `tb_role_permission` VALUES (1, 5, 1);
INSERT INTO `tb_role_permission` VALUES (2, 5, 2);
INSERT INTO `tb_role_permission` VALUES (3, 5, 3);
INSERT INTO `tb_role_permission` VALUES (4, 5, 4);
INSERT INTO `tb_role_permission` VALUES (5, 5, 6);
INSERT INTO `tb_role_permission` VALUES (6, 5, 7);
INSERT INTO `tb_role_permission` VALUES (7, 5, 8);
INSERT INTO `tb_role_permission` VALUES (8, 5, 9);
INSERT INTO `tb_role_permission` VALUES (9, 5, 10);
INSERT INTO `tb_role_permission` VALUES (10, 5, 12);
INSERT INTO `tb_role_permission` VALUES (11, 5, 13);
INSERT INTO `tb_role_permission` VALUES (12, 5, 14);
INSERT INTO `tb_role_permission` VALUES (13, 5, 15);
INSERT INTO `tb_role_permission` VALUES (14, 5, 16);
INSERT INTO `tb_role_permission` VALUES (15, 5, 17);
INSERT INTO `tb_role_permission` VALUES (16, 5, 18);
INSERT INTO `tb_role_permission` VALUES (17, 5, 19);
INSERT INTO `tb_role_permission` VALUES (18, 5, 20);
INSERT INTO `tb_role_permission` VALUES (19, 5, 21);
INSERT INTO `tb_role_permission` VALUES (20, 5, 22);
INSERT INTO `tb_role_permission` VALUES (21, 5, 23);
INSERT INTO `tb_role_permission` VALUES (22, 5, 24);
INSERT INTO `tb_role_permission` VALUES (23, 5, 25);
INSERT INTO `tb_role_permission` VALUES (24, 5, 26);
INSERT INTO `tb_role_permission` VALUES (25, 5, 28);
INSERT INTO `tb_role_permission` VALUES (26, 5, 29);
INSERT INTO `tb_role_permission` VALUES (27, 5, 30);
INSERT INTO `tb_role_permission` VALUES (28, 5, 31);
INSERT INTO `tb_role_permission` VALUES (29, 5, 32);
INSERT INTO `tb_role_permission` VALUES (30, 5, 33);
INSERT INTO `tb_role_permission` VALUES (31, 5, 34);
INSERT INTO `tb_role_permission` VALUES (32, 5, 35);
INSERT INTO `tb_role_permission` VALUES (33, 6, 37);
INSERT INTO `tb_role_permission` VALUES (34, 4, 36);
INSERT INTO `tb_role_permission` VALUES (35, 4, 40);
INSERT INTO `tb_role_permission` VALUES (36, 6, 5);
INSERT INTO `tb_role_permission` VALUES (37, 6, 11);
INSERT INTO `tb_role_permission` VALUES (38, 6, 38);
INSERT INTO `tb_role_permission` VALUES (39, 6, 39);
INSERT INTO `tb_role_permission` VALUES (40, 6, 41);
INSERT INTO `tb_role_permission` VALUES (41, 3, 43);
INSERT INTO `tb_role_permission` VALUES (42, 2, 42);
INSERT INTO `tb_role_permission` VALUES (43, 2, 43);
INSERT INTO `tb_role_permission` VALUES (44, 2, 44);
INSERT INTO `tb_role_permission` VALUES (45, 1, 42);
INSERT INTO `tb_role_permission` VALUES (46, 1, 43);
INSERT INTO `tb_role_permission` VALUES (47, 1, 44);
INSERT INTO `tb_role_permission` VALUES (48, 1, 45);
INSERT INTO `tb_role_permission` VALUES (49, 1, 46);
INSERT INTO `tb_role_permission` VALUES (50, 4, 47);
INSERT INTO `tb_role_permission` VALUES (51, 6, 48);
INSERT INTO `tb_role_permission` VALUES (52, 5, 49);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码，加密存储',
  `nick_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `gender` int(0) NULL DEFAULT NULL COMMENT '性别：male | female',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `birth` date NULL DEFAULT NULL COMMENT '生日',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '注册邮箱',
  `enable` int(0) NOT NULL COMMENT '是否激活',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '激活码',
  `created` datetime(0) NOT NULL,
  `updated` datetime(0) NOT NULL,
  `open` int(0) NOT NULL DEFAULT 0 COMMENT '是否公开',
  `family_id` bigint(0) UNSIGNED NULL DEFAULT NULL COMMENT '所属家庭id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 130 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (56, 'admin', '$2a$10$Q63uLT3Z1BGBJPWWRNHlzubCpCfBSMX.CsfEmcUSDfYjLuAMXXRo6', '管理员', 1, 'http://39.97.251.105:8040/group1/M00/00/00/rBFXSF664paABUjJAAB9qgpKBiM16.jpeg', '2020-03-17', NULL, 'ann_zhgy@163.com', 1, '413432', '2020-03-22 00:18:48', '2020-05-13 01:53:38', 0, 5);
INSERT INTO `tb_user` VALUES (129, 'ann_zhgy', '$2a$10$nuh8oA95hGLF7/hwAysU8.8L9Vzdr.v39uWDkNOFZB5EVFk2Jq3Wm', 'ann_zhgy', NULL, 'http://vgms.cqvip.com/lunwen2019/assets/images/head.gif', '2020-05-27', NULL, '1308767161@qq.com', 0, '882806', '2020-05-11 22:14:34', '2020-05-12 18:36:47', 0, NULL);
INSERT INTO `tb_user` VALUES (130, 'test0001', '$2a$10$oDZW5LNddp3zUp/Xdw0qiO8cNkIutma07JsyvG/gmaqXizAM92zFK', 'test0001', NULL, 'http://39.97.251.105:8040/group1/M00/00/00/rBFXSF66Ps2AZEnGAANCneVm0Rw404.jpg', NULL, NULL, 'test@qq.com', 0, '329513', '2020-05-12 21:13:42', '2020-05-12 21:13:42', 0, NULL);

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NOT NULL COMMENT '用户 ID',
  `role_id` bigint(0) NOT NULL COMMENT '角色 ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES (1, 56, 1);
INSERT INTO `tb_user_role` VALUES (2, 56, 5);
INSERT INTO `tb_user_role` VALUES (24, 129, 5);
INSERT INTO `tb_user_role` VALUES (25, 129, 4);
INSERT INTO `tb_user_role` VALUES (30, 130, 5);
INSERT INTO `tb_user_role` VALUES (31, 130, 4);
INSERT INTO `tb_user_role` VALUES (32, 56, 6);

SET FOREIGN_KEY_CHECKS = 1;
