/*
Navicat MySQL Data Transfer

Source Server         : Silent-Y
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : sbannouncement

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2017-11-02 11:20:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  `type` varchar(255) DEFAULT NULL COMMENT '类型[系统System,内部Internal,外部External]',
  `status` varchar(255) DEFAULT 'Draft' COMMENT '状态',
  `enabled` smallint(5) NOT NULL default 1 COMMENT '是否启用 0-否 1-是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `end_time` datetime DEFAULT NULL COMMENT '到期时间',
  `sort_num` int DEFAULT '1' COMMENT '排序号[Deprecated]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
