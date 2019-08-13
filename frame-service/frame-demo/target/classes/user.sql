CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL COMMENT '昵称',
  `reg_time` datetime DEFAULT NULL COMMENT '注册时间',
  `mobile` varchar(45) DEFAULT NULL COMMENT '手机号',
  `profile_photo` varchar(1000) DEFAULT NULL COMMENT '头像',
  `user_type` int(45) DEFAULT NULL COMMENT 'DIC。用户类型。1：房买家，2：二手房业主，3：租房房东，4：租房业主，5：随便看看',
  `id_card` char(18) DEFAULT NULL COMMENT '身份证号',
  `id_card_front` varchar(45) DEFAULT NULL COMMENT '身份证正面图片',
  `id_card_back` varchar(45) DEFAULT NULL COMMENT '身份证反面图片',
  `real_name` varchar(45) DEFAULT NULL COMMENT '真实姓名',
  `user_no` char(20) DEFAULT NULL COMMENT '用户编码。',
  `is_forbidden` tinyint(1) DEFAULT NULL COMMENT '用户是否被禁。0：正常；1：禁掉',
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8 COMMENT='用户表';