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
  `img_url` varchar(128) NOT NULL DEFAULT '' COMMENT '图片URL',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

drop table if exists `item_stock`;
CREATE TABLE `item_stock`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `stock` int(11) NOT NULL DEFAULT 0 COMMENT '库存',
  `item_id` bigint(20) NOT NULL COMMENT '商品id',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品库存表';