DROP TABLE IF EXISTS `t_feedback`;
CREATE TABLE `t_feedback`
(
    id             BIGINT(20)  NOT NULL AUTO_INCREMENT,
    user_id        BIGINT(20)  not null default 0 comment '反馈用户ID',
    user_name      varchar(50) NOT null comment '反馈用户名',
    contact        varchar(50)          DEFAULT NULL comment '联系方式',
    feedback       text comment '反馈内容',
    feedback_image varchar(512)         DEFAULT NULL comment '返馈图片',
    status         varchar(26) NOT NULL default 'UNREAD' comment '状态[UNREAD,SOLVED,CLOSED]',
    solved_by      varchar(50)          DEFAULT NULL comment '处理人签名',
    solved_note    varchar(200)         DEFAULT NULL comment '处理人意见',
    solved_user_id BIGINT(20)           DEFAULT NULL comment '处理人用户ID',
    create_time    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP comment '反馈时间',
    update_time    datetime             DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP comment '最新反馈处理时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

--CREATE TABLE IF NOT EXISTS t_feedback_image (
--    id BIGINT(20) primary key,
--    feedback_id BIGINT(20) not null,
--    url varchar(200)
--) ENGINE=InnoDB  DEFAULT CHARSET=UTF8;
