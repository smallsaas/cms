DROP TABLE IF EXISTS `meta_entity_patch_machine`;

CREATE TABLE `meta_entity_patch_machine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `entity` varchar(64) NOT NULL COMMENT '实体',
  `entity_table_name` varchar(64) NOT NULL COMMENT '实体对应表名',
  `entity_field_name` varchar(64) NOT NULL COMMENT '实体字段名',
  `entity_field_type` varchar(16) NOT NULL DEFAULT 'STRING' COMMENT '实体字段类型',
  `number_range_min` bigint(20) DEFAULT NULL COMMENT '数字类型字段的范围下限',
  `number_range_max` bigint(20) DEFAULT NULL COMMENT '数字类型字段的范围上限',
  `permission` varchar(64) DEFAULT NULL COMMENT '操作权限控制',
  PRIMARY KEY (`id`),
  UNIQUE KEY `entity` (`entity`,`entity_field_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

