


DROP TABLE IF EXISTS `t_stock_images`;
CREATE TABLE `t_stock_images` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(255) NOT NULL COMMENT 'images address',
  `stock_id` BIGINT(20) NOT NULL COMMENT 'owner id',
  `stock_type` varchar(20) NOT NULL COMMENT 'owner type:like product/member and etc...',
  `name` varchar(100) DEFAULT NULL COMMENT 'owner type:like product/member and etc...',
  `image_note` varchar(255) DEFAULT NULL COMMENT 'owner type:like product/member and etc...',
  `is_primary` smallint(5) default 0 COMMENT 'is primary,0 means not ,1 means primary ',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'upload time',
  UNIQUE(`url`,`stock_id`,`stock_type`),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
