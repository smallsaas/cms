-- INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
-- ('935348998329204741', '反馈模块', 'feedback.management');
--
-- INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
-- ('935348998329204738', '935348998329204741', '查看反馈', 'Feedback.view'),
-- ('935348998329204739', '935348998329204741', '更新反馈', 'Feedback.update'),
-- ('935348998329204740', '935348998329204741', '删除反馈', 'Feedback.delete');

DELETE
FROM `t_feedback`;
INSERT INTO `t_feedback` (id, user_id, user_name, contact, feedback, feedback_image, status, solved_by, solved_note,
                          solved_user_id, create_time, update_time)
VALUES ('935348998329204568', '935348998329204741', '小明', '13677777777', '这个任务急，要搞定', null, 'UNREAD', null, null, null,
        '2021-01-01', null),
       ('935348998329204567', '935348998329204741', '小陈', '185555555555', '今天天气不错', null, 'UNSOLVED', null, null, null,
        '2021-06-01', null),
       ('935348998329204560', '935348998329343434', '老王', '88888888', '我的号码不错吧', null, 'CLOSE', null, null, null,
        '2021-06-01', null),
       ('935348998329204569', '935348998329292345', '张三', '22222222', '下雨要带伞', null, 'UNREAD', null, null, null,
        '2021-03-08', null),
       ('935348998329204996', '935348998329343434', '老王', '88888888', '打不通电话', null, 'UNREAD', null, null, null,
        '2021-06-01', null),
       ('935348998329204563', '935348998329204741', '老李', '0101010101', '不说人话', null, 'SOLVED', null, null, null,
        '2022-01-01', null);