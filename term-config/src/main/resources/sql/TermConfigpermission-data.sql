SET FOREIGN_KEY_CHECKS = 0;
/*
INSERT INTO `t_term_config` (`id`, `type`, `title`, `content`, `created_time`, `previous_modified_time`, `last_modified_time`) VALUES ('1195215989266153474', 'ABOUT_US', '关于我们', '我们是一个富有活力软件开发团队', '2019-11-15 13:42:18', '2019-11-15 13:44:33', '2019-11-15 13:47:04');
INSERT INTO `t_term_config` (`id`, `type`, `title`, `content`, `created_time`, `previous_modified_time`, `last_modified_time`) VALUES ('1196642269345865729', 'NOTICE', '公告', '您好，欢迎来到这里。', '2019-11-19 12:12:04', '2019-11-19 12:12:04', '2019-11-19 12:12:04');
INSERT INTO `t_term_config` (`id`, `type`, `title`, `content`, `created_time`, `previous_modified_time`, `last_modified_time`) VALUES ('1196642322106015746', 'CONNECT_US', '联系我们', '欢迎联系我们', '2019-11-19 12:12:17', '2019-11-19 12:12:17', '2019-11-19 12:12:17');
*/


INSERT INTO `t_term_config` (`id`, `type`, `title`, `content`, `created_time`, `previous_modified_time`, `last_modified_time`) VALUES ('1195215989266153474', 'MEMBER_RULE', '会员规则', '<blockquote>会员规则---</blockquote>', '2019-11-15 13:42:18', '2019-11-19 16:17:00', '2019-11-19 16:29:06');
INSERT INTO `t_term_config` (`id`, `type`, `title`, `content`, `created_time`, `previous_modified_time`, `last_modified_time`) VALUES ('1196642269345865729', 'INTEGRAL_RULE', '积分规则', '积分规则---', '2019-11-19 12:12:04', '2019-11-19 12:12:04', '2019-11-19 12:12:04');
INSERT INTO `t_term_config` (`id`, `type`, `title`, `content`, `created_time`, `previous_modified_time`, `last_modified_time`) VALUES ('1196642322106015746', 'BRAND_STORY', '品牌故事', '<p><span style=\"color:#07a9fe\">欢迎联系我们</span></p>', '2019-11-19 12:12:17', '2019-11-19 12:12:17', '2019-11-19 16:32:53');
INSERT INTO `t_term_config` (`id`, `type`, `title`, `content`, `created_time`, `previous_modified_time`, `last_modified_time`) VALUES ('1196642322106015777', 'PRIVACY_POLICY', '隐私策略', '<p>隐私策略1</p><p>2</p><p>3</p>', '2019-11-19 12:12:17', '2019-11-19 16:20:21', '2019-11-19 16:22:26');


/*
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('939349783914717189', 'TermConfig模块', 'TermConfig.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('939349783914717186', '939349783914717189', '查看TermConfig', 'TermConfig.view'),
('939349783914717187', '939349783914717189', '更新TermConfig', 'TermConfig.update'),
('939349783914717188', '939349783914717189', '删除TermConfig', 'TermConfig.delete');*/