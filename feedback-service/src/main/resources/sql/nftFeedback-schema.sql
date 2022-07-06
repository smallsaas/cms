SET
FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for complain_record
-- ----------------------------
DROP TABLE IF EXISTS `nft_complain_record`;
CREATE TABLE `nft_complain_record`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT '申诉单ID',
    `complainant_id`    bigint(20) NOT NULL COMMENT '申诉者ID',
    `relation_order_id` bigint(20) DEFAULT NULL COMMENT '关联订单ID',
    `complainant_role`  varchar(64) DEFAULT NULL COMMENT '申诉者身份',
    `title`             varchar(64)  NOT NULL COMMENT '申诉标题',
    `content`           varchar(512) NOT NULL COMMENT '申诉内容',
    `credential_link`   varchar(512) NOT NULL COMMENT '凭证链接',
    `status`            varchar(16)  NOT NULL COMMENT '状态 待回复 - PENDING_REPLY 已回复 - REPLIED 已完成 - COMPLETED 创建时间 (create_time) 更新时间 (update_time)',
    `create_time`       datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    `update_time`       datetime    DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
    `request_type` varchar(32) default 'ORDER_DISPUTES' not null comment '申诉类型，用于记录是反馈还是订单纠纷，对应的类型枚举如下：FEEDBACK，ORDER_DISPUTES';
PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `nft_complain_type`;
CREATE TABLE `nft_complain_type`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT '申诉单ID',
    `title`             varchar(64)  NOT NULL COMMENT '申诉标题',
    `content`           varchar(512) NOT NULL COMMENT '申诉内容',
    `create_time`       datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    `update_time`       datetime    DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
    `request_type` varchar(32) default 'ORDER_DISPUTES' not null comment '申诉类型，用于记录是反馈还是订单纠纷，对应的类型枚举如下：FEEDBACK，ORDER_DISPUTES';
PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for complain_record
-- ----------------------------
DROP TABLE IF EXISTS `nft_complain_reply_record`;
CREATE TABLE `nft_complain_reply_record`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
    `complain_record_id` bigint(20) NOT NULL COMMENT '申诉单ID',
    `replier_id`         bigint(20) DEFAULT NULL COMMENT '回复人ID',
    `content`            varchar(256) NOT NULL COMMENT '回复内容',
    `is_manager_reply`   tinyint(255) NOT NULL DEFAULT '0' COMMENT '是否管理员回复',
    `reply_time`         datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '回复时间',
    `create_time`        datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    PRIMARY KEY (`id`)
);