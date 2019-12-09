INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1026783397089071107', 'ImagesService模块', 'ImagesService.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1026783397059710978', '1026783397089071107', '查看ImagesService', 'ImagesService.view'),
('1026783397089071105', '1026783397089071107', '编辑ImagesService', 'ImagesService.edit');
