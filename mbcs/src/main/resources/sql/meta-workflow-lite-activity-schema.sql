DROP TABLE IF EXISTS `meta_workflow_lite_activity`;
CREATE TABLE `meta_workflow_lite_activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `entity` varchar(64) NOT NULL COMMENT '实体',
  `entity_id` bigint(20) NOT NULL COMMENT '实体id',
  `note` varchar(256) DEFAULT NULL COMMENT '意见',
  `from_status` varchar(64) NOT NULL COMMENT '原状态',
  `to_status` varchar(64) NOT NULL COMMENT '下一个状态',
  `created_by_id` bigint(20) NOT NULL COMMENT '创建者id',
  `created_by` varchar(64) NOT NULL COMMENT '创建者名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `field1` varchar(128) DEFAULT NULL COMMENT '保留字段1',
  `field2` varchar(128) DEFAULT NULL COMMENT '保留字段2',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作流记录表';

