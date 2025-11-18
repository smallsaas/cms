SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS t_ad_group;
CREATE TABLE `t_ad_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '轮播图组名称',
  `identifier` varchar(50) DEFAULT NULL COMMENT '轮播图组标识',
  PRIMARY KEY (`id`),
  UNIQUE KEY `identifier` (`identifier`)
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS t_ad;
CREATE TABLE `t_ad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL COMMENT '轮播图组ID',
  `name` varchar(100) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL COMMENT '类型',
  `enabled` int(11) NOT NULL DEFAULT '1',
  `target_url` varchar(200) DEFAULT NULL COMMENT '目标链接',
  `strategy` varchar(200) DEFAULT NULL COMMENT '策略',
  `seq` smallint(6) DEFAULT 0 COMMENT '排序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS t_ad_link_definition;
CREATE TABLE IF NOT EXISTS t_ad_link_definition (
    id bigint(20) not null primary key auto_increment,
    `type` integer default 0 COMMENT '链接类型 0 功能链接, 1 产品链接'
    `name` varchar(100) COMMENT '链接名称',
    `url` varchar(255) COMMENT '链接URL', 
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS t_ad_library;
CREATE TABLE IF NOT EXISTS t_ad_library (
    id bigint(20) not null primary key auto_increment,
    `url` varchar(255) COMMENT '图片URL', 
    UNIQUE(url)
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8;