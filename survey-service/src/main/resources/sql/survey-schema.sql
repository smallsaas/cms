SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `t_survey`;
CREATE TABLE `t_survey` (
	/*问卷调查表*/
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	`author` VARCHAR (50) DEFAULT NULL COMMENT '作者',
	`title` VARCHAR (200) NOT NULL COMMENT '标题',
	`type` VARCHAR (50) DEFAULT NULL COMMENT '类型',
	`status` VARCHAR (26) DEFAULT NULL COMMENT '状态',
	`enabled` SMALLINT (5) NOT NULL default 0 COMMENT '是否启用 0-否 1-是',
	`sort_num` SMALLINT (5) NOT NULL default 0 COMMENT '排序号',
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` datetime DEFAULT NULL COMMENT '更新时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `t_survey_item`;
CREATE TABLE `t_survey_item` (
	/*问卷调查问题表*/
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT  COMMENT '主键ID',
	`survey_id` BIGINT (20) NOT NULL COMMENT '问卷调查ID',
	`question_num` SMALLINT(5) DEFAULT NULL COMMENT '题号',
	`content` text NOT NULL COMMENT '问题',
	`type` VARCHAR (50) NOT NULL DEFAULT 'RADIO' COMMENT '类型：RADIO-单选，MULTI-多选，FILL-填空',
	`is_required` smallint NOT NULL DEFAULT 1 COMMENT '是否必填(选)',
	`enabled` SMALLINT NOT NULL DEFAULT 1 COMMENT '是否启用该题目',
	`sort_num` SMALLINT (5) NOT NULL default 0 COMMENT '排序号',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `t_survey_item_option`;
CREATE TABLE `t_survey_item_option` (
	/*问卷调查选择项表*/
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	`item_id` BIGINT (20) NOT NULL COMMENT '问题ID',
	`option_num` SMALLINT(5) DEFAULT NULL COMMENT '选择项',
	`content` text DEFAULT NULL COMMENT '选择项内容',
	`image_url` VARCHAR(255) DEFAULT NULL COMMENT '图片地址',
	`enabled` SMALLINT NOT NULL DEFAULT 1 COMMENT '是否启用该选项',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `t_survey_answer`;
CREATE TABLE `t_survey_answer` (
	/*问卷调查答案表*/
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	`survey_id` BIGINT (20) NOT NULL COMMENT '问卷调查ID',
	`author_id` BIGINT (20) NOT NULL COMMENT '作者ID',
	`author_message` VARCHAR (50) DEFAULT NULL COMMENT '作者信息',
	`content` text NOT NULL COMMENT '内容：题号+答案(json保存问题及答案)',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE=utf8mb4_unicode_ci;