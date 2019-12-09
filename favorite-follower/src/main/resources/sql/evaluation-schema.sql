DROP TABLE IF EXISTS `t_stock_favorite`;
CREATE TABLE `t_stock_favorite` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT(20) NOT NULL COMMENT '用户ID',
  `member_name` VARCHAR(100) NOT NULL COMMENT '用户ID',
  `stock_id` BIGINT(20) NOT NULL COMMENT '收藏对象ID',
  `stock_name` varchar(255) NOT NULL COMMENT '收藏对象',
  `stock_type` VARCHAR(20) NOT NULL COMMENT '收藏类型类型',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
  UNIQUE(`member_id`,`stock_id`,`stock_type`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_stock_flower`;
CREATE TABLE `t_stock_flower` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT(20) NOT NULL COMMENT '用户ID',
  `member_name` VARCHAR(100) NOT NULL COMMENT '点赞用户',
  `stock_id` BIGINT(20) NOT NULL COMMENT '点赞对象ID',
  `stock_name` varchar(255) NOT NULL COMMENT '点赞对象对象',
  `stock_type` VARCHAR(20) NOT NULL COMMENT '点赞对象类型',
  `flower_count` INT DEFAULT '0' COMMENT '点赞次数',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '点赞时间',
  UNIQUE(`member_id`,`stock_id`,`stock_type`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
