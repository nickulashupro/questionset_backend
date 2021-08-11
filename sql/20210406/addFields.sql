ALTER TABLE `ums_user`
    ADD `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
    ADD `sex` varchar(20) DEFAULT NULL COMMENT '性别',
    ADD `open_id` varchar(100) DEFAULT NULL COMMENT 'openId';

Alter table `i_question_questionset` ADD `orderNum` Integer DEFAULT NULL COMMENT '序号';

Alter table `i_question` ADD `creat_user_id` varchar(50) DEFAULT NULL COMMENT '创建人ID',
                         ADD `creat_time` datetime DEFAULT NULL COMMENT '创建人时间';