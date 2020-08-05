SET FOREIGN_KEY_CHECKS=0;

/* INSERT INTO t_ad_group(id,`name`) VALUES ("1","首页轮播图");
 INSERT INTO t_ad_group(id,`name`) VALUES ("2","精品推荐");
 INSERT INTO `t_ad_group` (`id`, `name`) VALUES ("3", "热门商品");

INSERT INTO `t_ad` (`id`, `group_id`, `name`, `image`, `type`, `enabled`, `target_url`, `strategy`) VALUES ('1', '1', '谷歌广告', 'https://api.cloud.biliya.cn/images/alliance/p/3febd99b52151b0ac8df2ab10d1172f3.jpg', '产品', '1', 'www.google.com', 'EVERY_DAY');
INSERT INTO `t_ad` (`id`, `group_id`, `name`, `image`, `type`, `enabled`, `target_url`, `strategy`) VALUES ('2', '1', '洗发水广告', 'https://api.cloud.biliya.cn/images/alliance/p/8baacd61482b34e74f6b8c4aeaa130bf.jpg', '产品', '1', 'www.baidu.com', 'EVERY_DAY');
INSERT INTO `t_ad` (`id`, `group_id`, `name`, `image`, `type`, `enabled`, `target_url`, `strategy`) VALUES ('3', '2', '洗发水广告', 'https://api.cloud.biliya.cn/images/alliance/p/8baacd61482b34e74f6b8c4aeaa130bf.jpg', '产品', '1', 'www.baidu.com', 'EVERY_DAY&ODD_DAY&EVEN_DAY&ODD_HOUR&EVEN_HOUR');


INSERT INTO `t_ad_link_definition` (`id`, `type`, `name`, `url`) VALUES ('1', '0', '首页', '#/home/homePage');
INSERT INTO `t_ad_link_definition` (`id`, `type`, `name`, `url`) VALUES ('2', '0', '购物车', '#/home/cart');
INSERT INTO `t_ad_link_definition` (`id`, `type`, `name`, `url`) VALUES ('3', '0', '分类', '#/home/category');
INSERT INTO `t_ad_link_definition` (`id`, `type`, `name`, `url`) VALUES ('4', '0', '个人中心', '#/home/my');
INSERT INTO `t_ad_link_definition` (`id`, `type`, `name`, `url`) VALUES ('5', '0', '销售中心', '#/home/sellerPage');
INSERT INTO `t_ad_link_definition` (`id`, `type`, `name`, `url`) VALUES ('6', '0', '订单中心', '#/order/all');
INSERT INTO `t_ad_link_definition` (`id`, `type`, `name`, `url`) VALUES ('7', '0', '10元区', '#/goodsList/10');
INSERT INTO `t_ad_link_definition` (`id`, `type`, `name`, `url`) VALUES ('8', '1', '产品', '#/details/');
INSERT INTO `t_ad_link_definition` (`id`, `type`, `name`, `url`) VALUES ('9', '2', '类别', '#/home/category/');
INSERT INTO `t_ad_link_definition` (`id`, `type`, `name`, `url`) VALUES ('10', '0', '拼团活动', '#/home/category/-1/pieceGroup');
*/