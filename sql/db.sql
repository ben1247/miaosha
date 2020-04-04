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
  `price` decimal(8,2) NOT NULL DEFAULT 0 COMMENT '价格',
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