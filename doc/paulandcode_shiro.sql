/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50162
Source Host           : localhost:3306
Source Database       : paulandcode_shiro

Target Server Type    : MYSQL
Target Server Version : 50162
File Encoding         : 65001

Date: 2017-11-17 11:28:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `name` varchar(100) DEFAULT NULL COMMENT '机构名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父编号',
  `parent_ids` varchar(100) DEFAULT NULL COMMENT '父编号列表，如1/2/',
  `available` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可用 0:不可用 1:可用 默认1',
  PRIMARY KEY (`id`),
  KEY `idx_sys_organization_parent_id` (`parent_id`),
  KEY `idx_sys_organization_parent_ids` (`parent_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='机构';

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
INSERT INTO `sys_organization` VALUES ('1', '总公司', '0', '0/', '1');
INSERT INTO `sys_organization` VALUES ('2', '分公司1', '1', '0/1/', '1');
INSERT INTO `sys_organization` VALUES ('3', '分公司2', '1', '0/1/', '1');
INSERT INTO `sys_organization` VALUES ('4', '分公司11', '2', '0/1/2/', '1');
INSERT INTO `sys_organization` VALUES ('6', '分公司12', '2', '0/1/2/', '1');
INSERT INTO `sys_organization` VALUES ('7', '分公司13', '2', '0/1/2/', '1');
INSERT INTO `sys_organization` VALUES ('8', '分公司131', '7', '0/1/2/7/', '1');
INSERT INTO `sys_organization` VALUES ('9', '分公司132', '7', '0/1/2/7/', '1');
INSERT INTO `sys_organization` VALUES ('10', '分公司3', '1', '0/1/', '1');
INSERT INTO `sys_organization` VALUES ('11', '分公司21', '3', '0/1/3/', '1');
INSERT INTO `sys_organization` VALUES ('12', '分公司4', '1', '0/1/', '1');
INSERT INTO `sys_organization` VALUES ('13', '分公司5', '1', '0/1/', '1');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `name` varchar(100) DEFAULT NULL COMMENT '资源名称',
  `type` varchar(50) DEFAULT NULL COMMENT '资源类型',
  `url` varchar(200) DEFAULT NULL COMMENT '资源路径',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父编号',
  `parent_ids` varchar(100) DEFAULT NULL COMMENT '父编号列表',
  `permission` varchar(100) DEFAULT NULL COMMENT '权限字符串',
  `available` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可用 0:不可用 1:可用 默认1',
  PRIMARY KEY (`id`),
  KEY `idx_sys_resource_parent_id` (`parent_id`),
  KEY `idx_sys_resource_parent_ids` (`parent_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='资源';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '资源', 'menu', '', '0', '0/', '', '1');
INSERT INTO `sys_resource` VALUES ('11', '机构管理', 'menu', '/organization', '1', '0/1/', 'organization:*', '1');
INSERT INTO `sys_resource` VALUES ('12', '机构新增', 'button', '', '11', '0/1/11/', 'organization:create', '1');
INSERT INTO `sys_resource` VALUES ('13', '机构修改', 'button', '', '11', '0/1/11/', 'organization:update', '1');
INSERT INTO `sys_resource` VALUES ('14', '机构删除', 'button', '', '11', '0/1/11/', 'organization:delete', '1');
INSERT INTO `sys_resource` VALUES ('15', '机构查看', 'button', '', '11', '0/1/11/', 'organization:view', '1');
INSERT INTO `sys_resource` VALUES ('21', '用户管理', 'menu', '/user', '1', '0/1/', 'user:*', '1');
INSERT INTO `sys_resource` VALUES ('22', '用户新增', 'button', '', '21', '0/1/21/', 'user:create', '1');
INSERT INTO `sys_resource` VALUES ('23', '用户修改', 'button', '', '21', '0/1/21/', 'user:update', '1');
INSERT INTO `sys_resource` VALUES ('24', '用户删除', 'button', '', '21', '0/1/21/', 'user:delete', '1');
INSERT INTO `sys_resource` VALUES ('25', '用户查看', 'button', '', '21', '0/1/21/', 'user:view', '1');
INSERT INTO `sys_resource` VALUES ('31', '资源管理', 'menu', '/resource', '1', '0/1/', 'resource:*', '1');
INSERT INTO `sys_resource` VALUES ('32', '资源新增', 'button', '', '31', '0/1/31/', 'resource:create', '1');
INSERT INTO `sys_resource` VALUES ('33', '资源修改', 'button', '', '31', '0/1/31/', 'resource:update', '1');
INSERT INTO `sys_resource` VALUES ('34', '资源删除', 'button', '', '31', '0/1/31/', 'resource:delete', '1');
INSERT INTO `sys_resource` VALUES ('35', '资源查看', 'button', '', '31', '0/1/31/', 'resource:view', '1');
INSERT INTO `sys_resource` VALUES ('41', '角色管理', 'menu', '/role', '1', '0/1/', 'role:*', '1');
INSERT INTO `sys_resource` VALUES ('42', '角色新增', 'button', '', '41', '0/1/41/', 'role:create', '1');
INSERT INTO `sys_resource` VALUES ('43', '角色修改', 'button', '', '41', '0/1/41/', 'role:update', '1');
INSERT INTO `sys_resource` VALUES ('44', '角色删除', 'button', '', '41', '0/1/41/', 'role:delete', '1');
INSERT INTO `sys_resource` VALUES ('45', '角色查看', 'button', '', '41', '0/1/41/', 'role:view', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `role` varchar(100) DEFAULT NULL COMMENT '角色标识 程序中判断使用,如"admin"',
  `description` varchar(100) DEFAULT NULL COMMENT '角色描述,UI界面显示使用',
  `resource_ids` varchar(100) DEFAULT NULL COMMENT '拥有的资源',
  `available` tinyint(1) DEFAULT '1' COMMENT '是否可用 0:不可用 1:可用 默认1',
  PRIMARY KEY (`id`),
  KEY `idx_sys_role_resource_ids` (`resource_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '管理一切', '11,21,31,41', '1');
INSERT INTO `sys_role` VALUES ('3', '机构管理员', '管理机构', '11', '1');
INSERT INTO `sys_role` VALUES ('4', '用户管理员', '管理用户', '21', '1');
INSERT INTO `sys_role` VALUES ('5', '资源管理员', '管理资源', '31', '1');
INSERT INTO `sys_role` VALUES ('6', '角色管理员', '管理角色', '41', '1');
INSERT INTO `sys_role` VALUES ('7', '普通用户', '查看一切', '15,25,35,45', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `organization_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(100) DEFAULT NULL COMMENT '加密密码的盐',
  `role_ids` varchar(100) DEFAULT NULL COMMENT '拥有的角色列表',
  `locked` tinyint(1) DEFAULT '0' COMMENT '账号是否锁定 0:未锁定 1:已锁定 默认0',
  `available` tinyint(1) DEFAULT '1' COMMENT '是否可用 0:不可用 1:可用 默认1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_user_username` (`username`),
  KEY `idx_sys_user_organization_id` (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', 'admin', '53f8acb221173f5c22e9591963b487fa', 'b47d57ae89441d6659805ce8973b87c1', '1', '0', '1');
INSERT INTO `sys_user` VALUES ('3', '2', 'a', '16b2c08d144549e927c626fea5423581', '42e9f9b53e3b69cb992d09c9fdf90475', '3,7', '0', '1');
INSERT INTO `sys_user` VALUES ('4', '3', 'b', '07f52069a1a033bab466691de471d7bb', '44e20d18fe2aed86e390e0e0e87d1309', '4,7', '0', '1');
INSERT INTO `sys_user` VALUES ('5', '10', 'c', 'ea7708907d32c60195bfdc35e76082ed', '3306b7ce1be93611a8f84fa9dacbdf58', '5,7', '0', '1');
INSERT INTO `sys_user` VALUES ('6', '12', 'd', 'f7db4bf30d4a7eabb1c2f8cc42729232', '9b3179183f11989f18250583b2c499b9', '6,7', '0', '1');
INSERT INTO `sys_user` VALUES ('7', '13', 'e', 'd08461def06f36b61f1cca30dc94941f', 'f6ef9c1ca6fed95f5694f88be2dd41c5', '7', '0', '1');
