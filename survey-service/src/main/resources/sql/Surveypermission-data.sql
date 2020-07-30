INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1051749539414994947', 'Survey模块', 'Survey.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1051749539377246209', '1051749539414994947', '查看Survey', 'Survey.view'),
('1051749539414994945', '1051749539414994947', '编辑Survey', 'Survey.edit');
