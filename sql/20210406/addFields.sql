ALTER TABLE `ums_user`
    ADD `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
    ADD `sex` varchar(20) DEFAULT NULL COMMENT '性别',
    ADD `open_id` varchar(100) DEFAULT NULL COMMENT 'openId'