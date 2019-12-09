DROP TABLE IF EXISTS `meta_enable_machine`;

CREATE TABLE `meta_enable_machine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `entity` varchar(64) NOT NULL COMMENT '实体',
  `entity_table_name` varchar(64) NOT NULL COMMENT '实体对应的表名',
  `entity_field_name` varchar(32) NOT NULL COMMENT '实体字段名',
  `range_min` smallint(6) NOT NULL COMMENT '选取范围下限',
  `range_max` smallint(6) NOT NULL COMMENT '选取范围上限',
  `negative` smallint(6) DEFAULT 0 COMMENT '有效位是否需要取反',
  `permission` varchar(64) DEFAULT NULL COMMENT '操作权限控制',
  PRIMARY KEY (`id`),
  UNIQUE KEY `entity` (`entity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8