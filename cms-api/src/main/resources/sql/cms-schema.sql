/*
Navicat MySQL Data Transfer

Source Server         : location
Source Server Version : 50718
Source Host           : 127.0.0.1:3306
Source Database       : softto

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-07-11 15:25:28
*/

SET FOREIGN_KEY_CHECKS=0;


-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(26) NOT NULL COMMENT '类型ID',
  `category_id` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '类别ID',
  `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
  `title` VARCHAR(255) NOT NULL COMMENT '标题',
  `sub_title` VARCHAR(255) default NULL COMMENT '章节数',
  `sub_head` VARCHAR(255) default NULL COMMENT '原文',
  `summary` VARCHAR(255) default NULL COMMENT '内容摘要',
  `created_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `sticky` INT(11) NOT NULL DEFAULT '0' COMMENT '1置顶,0不置顶',
  `display` INT(11) NOT NULL DEFAULT '1' COMMENT '是否显示，1显示',
  `recommended` INT(11) NOT NULL DEFAULT '0' COMMENT '1精华帖,0普通帖',
  `visit_count` INT(11) NOT NULL DEFAULT '0' COMMENT '阅读量',
  `like_count` INT(11) NOT NULL DEFAULT '0' COMMENT '点赞数量',
  `favorite_count` INT(11) NOT NULL DEFAULT '0' COMMENT '收藏数量',
  `status` VARCHAR(50) NOT NULL DEFAULT 'Draft' COMMENT '状态:Draft-草稿，Published-发布',
   `author` VARCHAR(50)  not null COMMENT '作者',
   `cover` VARCHAR(255)  default null COMMENT '封面',
   `update_time` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `article_product_relation`;
CREATE TABLE `article_product_relation` (
  `article_id` BIGINT(20) NOT NULL COMMENT '资讯ID',
  `target_id` BIGINT(20) NOT NULL COMMENT '产品ID',
  `target_type` varchar(26) NOT NULL COMMENT '类型',
  unique(`article_id`,`target_id`,`target_type`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
-- ----------------------------
-- Table structure for article_category
-- ----------------------------
DROP TABLE IF EXISTS `article_category`;
CREATE TABLE `article_category` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `type_id` BIGINT(20) NOT NULL COMMENT '类别ID',
  `name` VARCHAR(255) NOT NULL COMMENT '类别名称',
  `cover` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '图片',
  `allow_image` INT(11) NOT NULL DEFAULT '1' COMMENT '允许上传图片',
  `fast_entry` INT(11) NOT NULL DEFAULT '0' COMMENT '快速入口标记',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for article_content
-- ----------------------------
DROP TABLE IF EXISTS `article_content`;
CREATE TABLE `article_content` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `article_id` BIGINT(20) NOT NULL COMMENT '文章ID',
  `content` TEXT NOT NULL COMMENT '内容',
  unique(`article_id`),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for article_image
-- ----------------------------
DROP TABLE IF EXISTS `article_image`;
CREATE TABLE `article_image` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `article_id` BIGINT(20) NOT NULL COMMENT '文章ID',
  `url` VARCHAR(255) NOT NULL COMMENT '图片地址',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for article_type
-- ----------------------------
DROP TABLE IF EXISTS `article_type`;
CREATE TABLE `article_type` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `identifier` BIGINT(20) NOT NULL COMMENT '类别id',
  `name` VARCHAR(255) NOT NULL COMMENT '类别名称',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_stock_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `t_stock_evaluation`;
CREATE TABLE `t_stock_evaluation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stock_id` bigint(20) NOT NULL COMMENT '评价对象ID',
  `member_id` bigint(20) NOT NULL COMMENT '用户id',
  `member_name` varchar(50) default null COMMENT '用户昵称',
  `stock_type` varchar(26) NOT NULL COMMENT '评价对象类型 评价为 商品/订单/其他',
  `origin_id` bigint(20) NOT NULL COMMENT '评价最上层父节点id',
  `origin_type` varchar(26) NOT NULL COMMENT '评价最上层节点type',
  `content` text default NULL COMMENT '评价信息',
  `is_display` smallint default '1' COMMENT '默认显示',
  `is_stick` smallint default '0' COMMENT '是否置顶 默认不置顶',
  `status` varchar(26) default null COMMENT '状态',
  `stock_tag` varchar(50) default null COMMENT '选填多层嵌套索引',
  `trade_number`  varchar(26) DEFAULT NULL COMMENT '订单编号',
  `trade_time`  datetime default null COMMENT '下单时间',
  `create_time` timestamp not null default current_timestamp COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_stock_evaluation_addition
-- ----------------------------
DROP TABLE IF EXISTS `t_stock_evaluation_addition`;
CREATE TABLE `t_stock_evaluation_addition` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `evaluate_id` BIGINT(20) NOT NULL COMMENT '外键',
  `note` TEXT NOT NULL COMMENT '评价信息',
  `status` VARCHAR(26) DEFAULT NULL COMMENT '状态',
  `is_display` SMALLINT(6) DEFAULT '1' COMMENT '默认显示',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_stock_evaluation_image
-- ----------------------------
DROP TABLE IF EXISTS `t_stock_evaluation_image`;
CREATE TABLE `t_stock_evaluation_image` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `evaluate_id` BIGINT(20) DEFAULT NULL COMMENT '外键',
  `url` VARCHAR(255) DEFAULT NULL COMMENT '图片',
  `status` VARCHAR(26) DEFAULT NULL COMMENT '状态',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `evaluation_addition_id` BIGINT(20) DEFAULT NULL COMMENT '外键',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_stock_evaluation_star
-- ----------------------------
DROP TABLE IF EXISTS `t_stock_evaluation_star`;
CREATE TABLE `t_stock_evaluation_star` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `evaluate_id` BIGINT(20) NOT NULL COMMENT '外键',
  `star_name` VARCHAR(255) DEFAULT NULL COMMENT '星级名称',
  `star_value` SMALLINT(6) DEFAULT NULL COMMENT '星级值',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_stock_favorite
-- ----------------------------
DROP TABLE IF EXISTS `t_stock_favorite`;
CREATE TABLE `t_stock_favorite` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT(20) NOT NULL COMMENT '用户ID',
  `member_name` BIGINT(20) NOT NULL COMMENT '用户ID',
  `stock_id` BIGINT(20) NOT NULL COMMENT '收藏对象ID',
  `stock_name` BIGINT(20) NOT NULL COMMENT '收藏对象',
  `stock_type` VARCHAR(20) NOT NULL COMMENT '收藏类型类型',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY(`member_id`,`stock_id`,`stock_type`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_stock_flower
-- ----------------------------
DROP TABLE IF EXISTS `t_stock_flower`;
CREATE TABLE `t_stock_flower` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT(20) NOT NULL COMMENT '用户ID',
  `member_name` VARCHAR(20) NOT NULL COMMENT '点赞用户',
  `stock_id` BIGINT(20) NOT NULL COMMENT '点赞对象ID',
  `stock_name` BIGINT(20) NOT NULL COMMENT '点赞对象对象',
  `stock_type` VARCHAR(20) NOT NULL COMMENT '点赞对象类型',
  `flower_count` INT(11) DEFAULT '0' COMMENT '点赞次数',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '点赞时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`member_id`,`stock_id`,`stock_type`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;




