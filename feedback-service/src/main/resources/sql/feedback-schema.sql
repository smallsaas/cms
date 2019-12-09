/*
CREATE TABLE IF NOT EXISTS t_feedback (
    id BIGINT(20) primary key,
    user_id BIGINT(20) not null comment '反馈用户ID',
    deal_user_id BIGINT(20) DEFAULT null comment '处理用户ID',
    create_name VARCHAR(255) NOT null,
    deal_opinion text comment '处理意见',
    content text comment '反馈内容',
    created_date timestamp null comment '反馈时间' ,
    connect_way VARCHAR(50) not null comment '联系方式',
    unread integer not null default 1 comment '是否未读',
    solved integer default 0 comment '是否已处理'
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS t_feedback_image (
    id BIGINT(20) primary key,
    feedback_id BIGINT(20) not null,
    url varchar(200)
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8;
*/