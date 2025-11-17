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
-- Table structure for t_cms_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_notice`;
CREATE TABLE `t_cms_notice` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '命名',
  `author` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '作者',
  `title` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 NOT NULL COMMENT '内容',
  `type` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '类型[系统System,内部Internal,外部External]',
  `status` varchar(255) CHARACTER SET utf8  DEFAULT 'Draft' COMMENT '状态',
  `enabled` smallint NOT NULL DEFAULT '1' COMMENT '是否启用 0-否 1-是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `start_time` datetime DEFAULT NULL COMMENT ' 开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '到期时间',
  `sort_num` int DEFAULT '1' COMMENT '排序号[Deprecated]',
  `org_id` bigint NOT NULL COMMENT 'orgId',
  `content_path` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '内容路径',
  `picture_url` text CHARACTER SET utf8 COMMENT '图片url',
  `period_type` tinyint DEFAULT '0' COMMENT '周期类型 0-没有周期 1-每天重复 2-永不过期',
  `view_number` int NOT NULL DEFAULT '0' COMMENT '浏览数',
  `tag` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `reference` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '文字链接',
  `content_type` tinyint NOT NULL DEFAULT '0' COMMENT ' 文章内容类型 0取当前的 1-取链接的内容',
  `notice_type` tinyint NOT NULL DEFAULT '0' COMMENT '文章类型 0-公告类型 1-产品详情文章 默认0',
  `stick` tinyint NOT NULL DEFAULT '0' COMMENT '是否置顶 0-不置顶 1置顶',
  `template` tinyint NOT NULL DEFAULT '0' COMMENT '是否为模板',
  `template_id` bigint DEFAULT NULL COMMENT '模板id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1628215074134978562 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC
