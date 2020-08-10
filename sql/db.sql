drop table if exists `user_info`;
CREATE TABLE `user_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '姓名',
  `gender` tinyint NOT NULL DEFAULT 0 COMMENT '1代表男性，2代表女性',
  `age` int(11) NOT NULL DEFAULT 0 COMMENT '年龄',
  `telphone` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `register_mode` varchar(10) NOT NULL DEFAULT '' COMMENT '账号注册来源于 byphone,bywechat,byalipay',
  `third_party_id` varchar(64) NOT NULL DEFAULT '' COMMENT '支付渠道',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';

drop table if exists `user_password`;
CREATE TABLE `user_password`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `encrypt_password` varchar(128) NOT NULL DEFAULT '' COMMENT '加密的密码',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户密码表';

drop table if exists `item`;
CREATE TABLE `item`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `title` varchar(64) NOT NULL DEFAULT '' COMMENT '商品标题',
  `price` double NOT NULL DEFAULT 0 COMMENT '价格',
  `description` varchar(500) NOT NULL DEFAULT '' COMMENT '描述',
  `sales` int(11) NOT NULL DEFAULT 0 COMMENT '销量',
  `img_url` varchar(1024) NOT NULL DEFAULT '' COMMENT '图片URL',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

drop table if exists `item_stock`;
CREATE TABLE `item_stock`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `stock` int(11) NOT NULL DEFAULT 0 COMMENT '库存',
  `item_id` bigint(20) NOT NULL COMMENT '商品id',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品库存表';

drop table if exists `order_info`;
CREATE TABLE `order_info` (
  `id` varchar(32) NOT NULL COMMENT '订单号',
  `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '下单的用户id',
  `item_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '下单的商品id',
  `item_price` double NOT NULL DEFAULT 0 COMMENT '商品价格',
  `amount` int(11) NOT NULL DEFAULT 0 COMMENT '商品数量',
  `order_price` double NOT NULL DEFAULT 0 COMMENT '订单价格',
  `promo_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '秒杀活动id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

drop table if exists `sequence_info`;
CREATE TABLE `sequence_info`  (
  `name` varchar(20) NOT NULL COMMENT '名称',
  `current_value` int(11) NOT NULL DEFAULT 0 COMMENT '当前值',
  `step` int(11) NOT NULL DEFAULT 0 COMMENT '步长',
  PRIMARY KEY (`name`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='序列号';
INSERT INTO `sequence_info` (`name`, `current_value`, `step`) VALUES ('order_info', 0, 1);

drop table if exists `promo`;
CREATE TABLE `promo`  (
   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
   `promo_name` varchar(128) NOT NULL DEFAULT 0 COMMENT '秒杀活动名称',
   `start_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀活动开始时间',
   `end_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀活动结束时间',
   `item_id` bigint(20) NOT NULL COMMENT '商品id',
   `promo_item_price` double NOT NULL DEFAULT 0 COMMENT '秒杀商品价格',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀活动表';



INSERT INTO `item` (`id`, `title`, `price`, `description`, `sales`, `img_url`)
VALUES
(2, 'iphone8', 6888.00, '苹果第8代手机', 9, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1585674468639&di=e2e732ea6836b88dc3fa460e114e4572&imgtype=0&src=http%3A%2F%2Fzbimg.taopic.com%2F171220%2F819-1G22012415344.jpg'),
(3, 'iphone11', 5999.00, '苹果第11代手机', 20, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1585674579860&di=423f0f59906e4939f4cb91b5e4e90fa1&imgtype=0&src=http%3A%2F%2Fx0.ifengimg.com%2Fres%2F2019%2F4C4A8140B683618EAE44EF478D78391AF0D10C4F_size21_w587_h300.jpeg');

INSERT INTO `item_stock` (`id`, `stock`, `item_id`)
VALUES
(2, 594, 2),
(3, 990, 3);

INSERT INTO `promo` (`id`, `promo_name`, `start_date`, `end_date`, `item_id`, `promo_item_price`)
VALUES
(1, 'iphone11抢购', '2020-04-06 18:32:00', '2021-04-06 19:00:00', 3, 4999);

INSERT INTO `sequence_info` (`name`, `current_value`, `step`)
VALUES
('order_info', 16, 1);

INSERT INTO `user_info` (`id`, `name`, `gender`, `age`, `telphone`, `register_mode`, `third_party_id`)
VALUES
(2, '张三', 1, 34, '13914567811', 'byphone', '');

INSERT INTO `user_password` (`id`, `user_id`, `encrypt_password`)
VALUES
(1, 2, 'ICy5YqxZB1uWSwcVLSNLcA==');

INSERT INTO `order_info` (`id`, `user_id`, `item_id`, `item_price`, `amount`, `order_price`, `promo_id`)
VALUES
('2020040600000500', 2, 3, 4999, 1, 4999, 1),
('2020040600000600', 2, 3, 5999, 1, 5999, 0),
('2020040600000700', 2, 3, 5999, 1, 5999, 0),
('2020041800000800', 2, 3, 5999, 1, 5999, 0),
('2020050300000900', 2, 3, 5999, 1, 5999, 0),
('2020050300001000', 2, 3, 5999, 1, 5999, 0),
('2020050400001100', 2, 2, 6888, 1, 6888, 0),
('2020050800001200', 2, 2, 6888, 1, 6888, 0),
('2020072800001300', 2, 3, 5999, 1, 5999, 0),
('2020072800001400', 2, 3, 5999, 1, 5999, 0),
('2020072800001500', 2, 2, 6888, 1, 6888, 0);


alter table `item_stock` add unique index item_id_index(`item_id`);