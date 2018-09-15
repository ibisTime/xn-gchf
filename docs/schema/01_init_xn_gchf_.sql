/*
-- Query: SELECT * FROM dev_xn_gchf.thf_channel_bank
LIMIT 0, 1000
-- Date: 2018-06-29 13:58
*/
INSERT INTO `thf_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (1,'ICBC','中国工商银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `thf_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (2,'CCB','中国建设银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `thf_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (3,'BOC','中国银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `thf_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (4,'BCM','中国交通银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `thf_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (5,'CIB','兴业银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `thf_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (6,'CITIC','中信银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `thf_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (7,'CEB','中国光大银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `thf_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (8,'PAB','平安银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `thf_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (9,'PSBC','中国邮政储蓄银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);

/*
-- Query: SELECT * FROM dev_xn_gchf.tsys_role
LIMIT 0, 1000
-- Date: 2018-06-29 13:59
*/
INSERT INTO `tsys_role` (`code`,`type`,`name`,`updater`,`update_datetime`,`remark`) VALUES ('RO201800000000000001','P','平台管理员','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_role` (`code`,`type`,`name`,`updater`,`update_datetime`,`remark`) VALUES ('RO201800000000000002','B','银行管理员','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_role` (`code`,`type`,`name`,`updater`,`update_datetime`,`remark`) VALUES ('RO201800000000000003','O','业主管理员','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_role` (`code`,`type`,`name`,`updater`,`update_datetime`,`remark`) VALUES ('RO201800000000000004','S','监管管理员','USYS201800000000001',now(),NULL);

/*
-- Query: SELECT * FROM dev_xn_gchf.thf_user
LIMIT 0, 1000
-- Date: 2018-06-29 14:00
*/
INSERT INTO `thf_user` (`user_id`,`real_name`,`type`,`login_name`,`mobile`,`login_pwd`,`login_pwd_strength`,`user_refree`,`create_datetime`,`role_code`,`updater`,`update_datetime`,`status`,`remark`)  VALUES ('USYS201800000000001','平台端','P','admin',NULL,'21218cca77804d2ba1922c33e0151105','1',NULL,now(),'RO201800000000000001',NULL,NULL,'0',NULL);

/*
-- Query: SELECT * FROM dev_xn_gchf.tsys_dict
LIMIT 0, 1000
-- Date: 2018-09-14 01:21
*/
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (1,'0',NULL,'user_kind','用户类型','admin','2018-07-27 03:07:37',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (2,'1','user_kind','P','平台用户','admin','2018-07-27 03:07:37',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (3,'1','user_kind','B','银行用户','admin','2018-07-27 03:07:37',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (4,'1','user_kind','O','业主单位','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (5,'1','user_kind','S','监管单位','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (6,'0',NULL,'user_status','用户状态','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (7,'1','user_status','0','正常','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (8,'1','user_status','1','人工锁定','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (9,'1','user_status','2','程序锁定','admin','2018-07-27 03:07:38','');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (10,'0',NULL,'project_status','项目状态','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (11,'1','project_status','0','待编辑','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (12,'1','project_status','1','待开工','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (13,'1','project_status','2','在建','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (15,'0',NULL,'account_status','账户状态','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (16,'1','account_status','0','正常','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (17,'1','account_status','1','人工锁定','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (18,'1','account_status','2','程序锁定','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (19,'0',NULL,'staff_status','员工状态','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (20,'1','staff_status','0','在职','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (21,'1','staff_status','1','请假','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (22,'1','staff_status','2','离职','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (23,'0',NULL,'message_status','代发消息状态','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (24,'1','message_status','0','待发送','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (25,'1','message_status','1','待处理','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (26,'1','message_status','2','待反馈','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (27,'0',NULL,'salary_status','工资条状态','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (28,'1','salary_status','0','待人工复核','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (29,'1','salary_status','1','已审核待提交','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (30,'1','salary_status','2','已提交待发放','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (31,'1','salary_status','3','正常发放','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (32,'1','salary_status','4','部分发放','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (33,'1','salary_status','5','已转正常','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (34,'0',NULL,'staff_type','务工人员类型','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (35,'1','staff_type','0','直招工人','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (36,'1','staff_type','1','劳务工人','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (37,'1','staff_type','2','包工工人','admin','2018-07-27 03:07:39',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (38,'1','staff_type','3','内勤人员','admin','2018-07-27 03:07:39',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (39,'0',NULL,'id_type','证件类型','admin','2018-07-27 03:07:39',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (40,'1','id_type','1','身份证','admin','2018-07-27 03:07:39',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (41,'0',NULL,'attendance_status','考勤状态','admin','2018-07-27 03:07:39',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (42,'1','attendance_status','0','待上班打卡','admin','2018-07-27 03:07:39',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (43,'1','attendance_status','1','待下班打卡','admin','2018-07-27 03:07:39',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (44,'1','attendance_status','2','已打卡待结算','admin','2018-07-27 03:07:39',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (45,'1','attendance_status','3','已结算','admin','2018-07-27 03:07:39',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (46,'0',NULL,'salary_log_type','日志类型','admin','2018-07-27 03:07:39',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (47,'1','salary_log_type','0','正常','admin','2018-07-27 03:07:39',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (48,'1','salary_log_type','1','异常','admin','2018-07-27 03:07:39',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (49,'1','project_status','3','停工','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (50,'1','project_status','4','结束','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (51,'1','message_status','3','已处理','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (52,'0',NULL,'abnormal_type','事件类型','admin','2018-07-27 03:07:39',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (53,'1','abnormal_type','0','工资条异常','admin','2018-07-27 03:07:39',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (54,'1','salary_status','6','延迟发放','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (55,'1','salary_status','7','部分且延迟发放','admin','2018-07-27 03:07:38',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (56,'0',NULL,'position_type','职位类型','admin','2018-07-27 03:07:39',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (57,'1','position_type','0','普工','admin','2018-07-27 03:07:39',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (58,'1','position_type','1','主管','admin','2018-07-27 03:07:39',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (59,'1','attendance_status','4','旷工','admin','2018-07-27 03:07:39',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (60,'0','NULL','bankcard_number_status','银行卡号状态','admin','2018-07-27 03:07:39',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (61,'1','bankcard_number_status','0','未录入','admin','2018-07-27 03:07:39',NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (62,'1','bankcard_number_status','1','已录入','admin','2018-07-27 03:07:39',NULL);

/*
-- Query: SELECT * FROM dev_xn_gchf.tsys_config
LIMIT 0, 1000
-- Date: 2018-08-06 14:39
*/
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (1,'sys_txt','telephone','0571-88888888','USYS201800000000001','2018-07-27 03:07:39','联系电话');
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (2,'sys_txt','about_us','关于我们112','USYS201800000000001','2018-07-27 03:07:39','关于我们');
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (3,'sys_txt','service_time','9:00-17:40','USYS201800000000001','2018-07-27 03:07:39','服务时间');
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (4,'qiniu','qiniu_access_key','07KR5rNezHcXebD-GalrPw0npsAODOMVxygvdFFt','USYS201800000000001','2018-07-27 03:07:39','七牛云key1');
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (5,'qiniu','qiniu_secret_key','nsMbXOfEtk3SvQ3GFHbKMozJua3jbTiGPIIwu4tq','USYS201800000000001','2018-07-27 03:07:39','qiniu_secret_key');
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (6,'qiniu','qiniu_bucket','zwzj','USYS201800000000001','2018-07-27 03:07:39','qiniu_bucket');
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (7,'qiniu','qiniu_domain','otoieuivb.bkt.clouddn.com','USYS201800000000001','2018-07-27 03:07:39','访问域名');

insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('GCHFBSM201800000000000000','根目录','1','B','#','1','admin','2018-08-20 09:09:17','','');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('GCHFOSM201800000000000000','根目录','1','O','#','1','admin','2018-08-20 09:09:17','','');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('GCHFPSM201800000000000000','根目录','1','P','#','1','admin','2018-08-20 09:09:17','','');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('GCHFSSM201800000000000000','根目录','1','S','#','1','admin','2018-08-20 09:09:17','','');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201030483125593','系统管理','1','P','#','1','USYS201800000000001','2018-07-20 10:30:48','','GCHFPSM201800000000000000');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201031386412313','运维管理','1','P','#','1','USYS201800000000001','2018-07-20 10:31:38','','SM201807201030483125593');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201031554085068','运营管理','1','P','#','2','USYS201800000000001','2018-07-20 10:31:55','','SM201807201030483125593');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201035525556549','菜单管理','1','P','/system/menu.htm','1','USYS201800000000001','2018-07-20 10:35:52','','SM201807201031386412313');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201036520116270','系统参数管理','1','P','/system/sysPara.htm','2','USYS201800000000001','2018-07-20 10:36:52','','SM201807201031386412313');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201037279421821','数据字典管理','1','P','/system/dataDict.htm','3','USYS201800000000001','2018-07-20 10:37:27','','SM201807201031386412313');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201038045508404','角色管理','1','P','/system/role.htm','1','USYS201800000000001','2018-07-20 10:38:04','','SM201807201031554085068');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201038358168249','用户管理','1','P','/system/user.htm','2','USYS201800000000001','2018-07-20 10:38:35','','SM201807201031554085068');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201124298734547','修改','2','P','/edit','2','USYS201800000000001','2018-07-20 11:24:29','','SM201807201035525556549');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201124447788270','删除','2','P','/delete','3','USYS201800000000001','2018-07-20 11:24:44','','SM201807201035525556549');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201125161861248','修改','2','P','/edit','1','USYS201800000000001','2018-07-20 11:25:16','','SM201807201036520116270');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201125421699687','修改','2','P','/edit','1','USYS201800000000001','2018-07-20 11:25:42','','SM201807201037279421821');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201126211661803','新增','2','P','/add','1','USYS201800000000001','2018-07-20 11:26:21','','SM201807201038045508404');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201126371934570','修改','2','P','/edit','2','USYS201800000000001','2018-07-20 11:26:37','','SM201807201038045508404');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201127025377983','分配菜单','2','P','/change','3','USYS201800000000001','2018-07-20 11:27:02','','SM201807201038045508404');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201127186657558','删除','2','P','/delete','4','USYS201800000000001','2018-07-20 11:27:18','','SM201807201038045508404');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201129177262546','新增','2','P','/add','1','USYS201800000000001','2018-07-20 11:29:17','','SM201807201038358168249');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201129177262547','新增','2','P','/add','1','USYS201800000000001','2018-07-20 11:29:17','','SM201807201035525556549');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201129373238575','重置密码','2','P','/reset','2','USYS201800000000001','2018-07-20 11:29:37','','SM201807201038358168249');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201130034336159','注销/激活','2','P','/rock','3','USYS201800000000001','2018-07-20 11:35:06','','SM201807201038358168249');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201130316042069','设置角色','2','P','/assign','4','USYS201800000000001','2018-07-20 11:35:15','','SM201807201038358168249');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201131011696446','修改手机号','2','P','/changeMobile','5','USYS201800000000001','2018-07-20 11:34:55','','SM201807201038358168249');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201131571203094','员工管理','1','P','#','3','USYS201800000000001','2018-07-25 11:24:12','','GCHFPSM201800000000000000');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201240189699293','人员建档','1','P','/staff/jiandang.htm','1','USYS201800000000001','2018-07-20 12:40:18','','SM201807201131571203094');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201240487754033','员工查看','1','P','/staff/allStaff.htm','2','USYS201800000000001','2018-07-20 12:40:48','','SM201807201131571203094');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201345378904554','重新建档','2','P','/rejiandang','1','USYS201800000000001','2018-07-20 13:45:37','','SM201807201240487754033');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201346222811520','补录技能','2','P','/skill','2','USYS201800000000001','2018-07-20 13:46:22','','SM201807201240487754033');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201346521318233','工作履历','2','P','/history','3','USYS201800000000001','2018-07-20 13:46:52','','SM201807201240487754033');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807201347158228349','详情','2','P','/detail','4','USYS201800000000001','2018-07-20 13:47:15','','SM201807201240487754033');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807231030483125593','系统管理','1','B','#','1','USYS201800000000001','2018-07-20 10:30:48','','GCHFBSM201800000000000000');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807231031386412313','运维管理','1','B','#','1','USYS201800000000001','2018-07-20 10:31:38','','SM201807231030483125593');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807231035525556549','菜单管理','1','B','/system/menu.htm','1','USYS201800000000001','2018-07-20 10:35:52','','SM201807231031386412313');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807231129177262547','新增','2','B','/add','1','USYS201800000000001','2018-07-20 11:29:17','','SM201807231035525556549');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241030483125593','系统管理','1','O','#','1','USYS201800000000001','2018-07-20 10:30:48','','GCHFOSM201800000000000000');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241031386412313','运维管理','1','O','#','1','USYS201800000000001','2018-07-20 10:31:38','','SM201807241030483125593');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241035525556549','菜单管理','1','O','/system/menu.htm','1','USYS201800000000001','2018-07-20 10:35:52','','SM201807241031386412313');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241129177262547','新增','2','O','/add','1','USYS201800000000001','2018-07-20 11:29:17','','SM201807241035525556549');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241335405222279','运营管理','1','B','#','2','U201807201448369903584','2018-07-24 13:35:40','','SM201807231030483125593');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241336205099958','角色管理','1','B','/system/role.htm','1','U201807201448369903584','2018-07-24 13:36:20','','SM201807241335405222279');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241338036803470','银行单位','1','B','#','1','U201807201448369903584','2018-07-24 13:38:03','','SM201807241337175287731');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241339528185483','修改','2','B','/edit','2','U201807201448369903584','2018-07-24 13:39:52','','SM201807231035525556549');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241340146487913','删除','2','B','/delete','3','U201807201448369903584','2018-07-24 13:40:14','','SM201807231035525556549');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241342024542245','分配菜单','2','B','/change','3','U201807201448369903584','2018-07-24 13:50:06','','SM201807241336205099958');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241342422409334','新增','2','B','/add','1','U201807201448369903584','2018-07-24 13:42:42','','SM201807241336205099958');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241342596513263','修改','2','B','/edit','2','U201807201448369903584','2018-07-24 13:42:59','','SM201807241336205099958');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241343221479328','删除','2','B','/delete','4','U201807201448369903584','2018-07-24 13:43:22','','SM201807241336205099958');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241354444837809','工资核发','1','B','#','3','U201807201448369903584','2018-07-24 13:54:44','','GCHFBSM201800000000000000');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241355087008815','工资待发','1','B','/waitList/postRequest.htm','1','U201807201448369903584','2018-07-24 13:55:08','','SM201807241354444837809');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241355483004750','待反馈','1','B','/waitList/feedback.htm','2','U201807201448369903584','2018-07-24 13:58:27','','SM201807241354444837809');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241356215805380','历史请求','1','B','/waitList/alreadyQuest.htm','3','U201807201448369903584','2018-07-24 13:58:21','','SM201807241354444837809');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241356471961442','短信通知人','1','B','/waitList/textMessage.htm','4','U201807201448369903584','2018-07-24 13:58:15','','SM201807241354444837809');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241359246395059','详情','2','B','/detail','1','U201807201448369903584','2018-07-24 13:59:24','','SM201807241356215805380');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241359560335048','新增','2','B','/add','1','U201807201448369903584','2018-07-24 13:59:56','','SM201807241356471961442');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241400081795457','修改','2','B','/edit','2','U201807201448369903584','2018-07-24 14:00:08','','SM201807241356471961442');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241400236309411','删除','2','B','/delete','3','U201807201448369903584','2018-07-24 14:00:23','','SM201807241356471961442');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241400373916195','详情','2','B','/detail','4','U201807201448369903584','2018-07-24 14:00:37','','SM201807241356471961442');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241716127538688','修改','2','O','/edit','2','U201807201449261577720','2018-07-24 17:16:12','','SM201807241035525556549');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241716256391719','删除','2','O','/delete','3','U201807201449261577720','2018-07-24 17:16:25','','SM201807241035525556549');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241716521466051','运营管理','1','O','#','2','U201807201449261577720','2018-07-24 17:16:52','','SM201807241030483125593');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241717261696782','角色管理','1','O','/system/role.htm','1','U201807201449261577720','2018-07-24 17:17:26','','SM201807241716521466051');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241723457719659','新增','2','O','/add','1','U201807201449261577720','2018-07-24 17:23:45','','SM201807241717261696782');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241723573361745','修改','2','O','/edit','2','U201807201449261577720','2018-07-24 17:23:57','','SM201807241717261696782');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241724163987002','删除','2','O','/delete','4','U201807201449261577720','2018-07-24 17:24:16','','SM201807241717261696782');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241724339186026','分配菜单','2','O','/change','3','U201807201449261577720','2018-07-24 17:24:33','','SM201807241717261696782');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241750117357259','项目管理','1','O','#','3','U201807201449261577720','2018-07-24 17:50:11','','GCHFOSM201800000000000000');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241751002521252','员工管理','1','O','#','4','U201808101551265155214','2018-09-11 10:32:33','','GCHFOSM201800000000000000');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241751172466144','工资核发','1','O','#','5','U201807201449261577720','2018-07-24 17:51:17','','GCHFOSM201800000000000000');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241751344565023','事件处理','1','O','#','6','U201807201449261577720','2018-07-24 17:51:34','','GCHFOSM201800000000000000');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241752310501351','项目管理','1','O','#','1','U201808101551265155214','2018-09-10 23:02:07','','SM201807241750117357259');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241753209506985','项目管理','1','O','/projectManage/project.htm','1','U201807201449261577720','2018-07-24 17:53:20','','SM201807241752310501351');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241753481912350','专户信息','1','O','/yewuManage/account.htm','2','U201808131623216146056','2018-08-15 16:06:40','','SM201807241752310501351');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241757482373477','办理入职','1','O','/staff/jiandang1.htm','1','U201807201449261577720','2018-07-24 17:57:48','','SM201807241751002521252');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241758132452206','工程人员','1','O','/projectStaff/projectStaff.htm','2','U201807201449261577720','2018-07-24 17:58:13','','SM201807241751002521252');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241759034094106','查看考勤','1','O','/newProj/kaoqin.htm','4','U201807241650032989800','2018-07-25 14:16:43','','SM201807241751002521252');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241759353788991','请假明细','1','O','/staff/allStaff/leaveRecords.htm','5','U201807201449261577720','2018-07-24 17:59:35','','SM201807241751002521252');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241759554185762','工资卡管理','1','O','/staff/bankCard.htm','6','U201807201449261577720','2018-07-24 17:59:55','','SM201807241751002521252');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241821586779099','编辑项目','2','O','/editPro','1','U201807201449261577720','2018-07-24 18:23:45','','SM201807241753209506985');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241824314342023','施工单位','2','O','/addBumen','2','U201807201449261577720','2018-07-24 18:24:31','','SM201807241753209506985');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241825425045330','项目开工','2','O','/checkPro','3','U201807201449261577720','2018-07-24 18:25:42','','SM201807241753209506985');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241826245194724','项目停工','2','O','/overPro','4','U201807201449261577720','2018-07-24 18:26:24','','SM201807241753209506985');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241827016053393','重新开工','2','O','/aWork','5','U201807201449261577720','2018-07-24 18:27:01','','SM201807241753209506985');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241827308831941','项目结束','2','O','/overTime','6','U201807201449261577720','2018-07-24 18:27:30','','SM201807241753209506985');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241857310508573','工资明细','1','O','/salary.htm','1','U201808101551265155214','2018-09-10 10:45:39','','SM201807241751172466144');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241858080526660','待发请求','1','O','/daifa/daifa.htm','2','U201807201449261577720','2018-07-24 18:58:08','','SM201807241751172466144');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241858292354817','历史请求','1','O','/waitList/alreadyQuest.htm','3','U201807201449261577720','2018-07-24 18:58:29','','SM201807241751172466144');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241859489641457','事件查看','1','O','/staff/allStafferror.htm','1','U201808101551265155214','2018-09-11 10:34:14','','SM201807241751344565023');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807241900154863609','历史事件','1','O','/staff/allStafferrHistory.htm','2','U201807201449261577720','2018-07-24 19:00:15','','SM201807241751344565023');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251021011637513','修改','2','O','/edit','1','U201807201449261577720','2018-07-25 10:21:01','','SM201807241753481912350');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251021266445121','详情','2','O','/detail','2','U201807201449261577720','2018-07-25 10:21:26','','SM201807241753481912350');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251030483125593','系统管理','1','S','#','1','USYS201800000000001','2018-07-20 10:30:48','','GCHFSSM201800000000000000');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251031386412313','运维管理','1','S','#','1','USYS201800000000001','2018-07-20 10:31:38','','SM201807251030483125593');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251035525556549','菜单管理','1','S','/system/menu.htm','1','USYS201800000000001','2018-07-20 10:35:52','','SM201807251031386412313');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251040061583593','重新入职','2','O','/reruzhi','1','U201807201449261577720','2018-07-25 10:40:06','','SM201807241758132452206');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251040378537609','请假','2','O','/weekday','2','U201807201449261577720','2018-07-25 10:40:37','','SM201807241758132452206');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251041091321387','离职','2','O','/quit','3','U201807201449261577720','2018-07-25 10:41:09','','SM201807241758132452206');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251045289737863','详情','2','O','/detail','9','U201808171655276276678','2018-09-15 22:34:43','','SM201807241758132452206');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251100586462320','工资卡录入/修改','2','O','/edit','1','U201808101551265155214','2018-09-12 11:38:33','','SM201807241759554185762');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251101159737671','导出','2','O','/export','2','U201808101551265155214','2018-09-12 11:38:52','','SM201807241759554185762');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251102287288581','发送','2','O','/send','1','U201807201449261577720','2018-07-25 11:02:28','','SM201807241858080526660');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251102441529195','详情','2','O','/detail','2','U201807201449261577720','2018-07-25 11:02:44','','SM201807241858080526660');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251103314158958','详情','2','O','/detail','1','U201807201449261577720','2018-07-25 11:03:31','','SM201807241858292354817');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251122527841467','开设账号','1','P','#','2','USYS201800000000001','2018-07-25 11:22:52','','GCHFPSM201800000000000000');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251123462276274','银行单位','1','P','www.baidu.com','1','USYS201800000000001','2018-07-25 11:23:46','','SM201807251122527841467');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251124338772219','业主单位','1','P','www.baidu.com','2','USYS201800000000001','2018-07-25 11:24:33','','SM201807251122527841467');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251124551918527','监管单位','1','P','www.baidu.com','3','USYS201800000000001','2018-07-25 11:24:55','','SM201807251122527841467');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251126134348811','银行管理','1','P','/newId/operation.htm','1','USYS201800000000001','2018-07-25 11:26:13','','SM201807251123462276274');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251126285602356','银行用户','1','P','/newId/bank.htm','2','USYS201800000000001','2018-07-25 11:26:28','','SM201807251123462276274');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251127455775704','新增银行','2','P','/add','1','USYS201800000000001','2018-07-25 11:27:45','','SM201807251126134348811');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251128071608961','修改银行','2','P','/edit','2','USYS201800000000001','2018-07-25 11:28:07','','SM201807251126134348811');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251129177262547','新增','2','S','/add','1','USYS201800000000001','2018-07-20 11:29:17','','SM201807251035525556549');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251129247701972','新增','2','P','/add','1','USYS201800000000001','2018-07-25 11:29:24','','SM201807251126285602356');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251129425669001','详情','2','P','/detail','2','USYS201800000000001','2018-07-25 11:29:42','','SM201807251126285602356');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251131493932434','新增项目','1','P','/newId/newProject.htm','1','USYS201800000000001','2018-07-25 14:32:12','','SM201807251124338772219');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251132224253480','业主用户','1','P','/newId/yezhu.htm','2','USYS201800000000001','2018-07-25 11:32:22','','SM201807251124338772219');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251133480854384','详情','2','P','/detail','1','USYS201800000000001','2018-07-25 15:15:11','','SM201807251132224253480');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251134435204415','监管用户','1','P','/newId/supervise.htm','1','USYS201800000000001','2018-07-25 11:34:43','','SM201807251124551918527');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251135184292102','新增','2','P','/add','1','USYS201800000000001','2018-07-25 11:35:18','','SM201807251134435204415');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251135329712143','详情','2','P','/detail','2','USYS201800000000001','2018-07-25 11:35:32','','SM201807251134435204415');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251406462215177','运营管理','1','S','#','2','U201807201449530475052','2018-07-25 14:14:04','','SM201807251030483125593');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251407213512067','角色管理','1','S','/system/role.htm','1','U201807201449530475052','2018-07-25 14:14:25','','SM201807251406462215177');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251407426617014','用户管理','1','S','/system/user.htm','2','U201807201449530475052','2018-07-25 14:07:42','','SM201807251406462215177');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251409118616376','分配菜单','2','S','/change','4','U201807201449530475052','2018-07-25 14:09:11','','SM201807251407213512067');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251409276064184','新增','2','S','/add','1','U201807201449530475052','2018-07-25 14:09:27','','SM201807251407213512067');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251409439392574','修改','2','S','/edit','2','U201807201449530475052','2018-07-25 14:09:43','','SM201807251407213512067');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251409597614501','删除','2','S','/delete','3','U201807201449530475052','2018-07-25 14:09:59','','SM201807251407213512067');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251413269639712','修改','2','S','/edit','2','U201807201449530475052','2018-07-25 14:13:26','','SM201807251035525556549');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251413436536990','删除','2','S','/delete','3','U201807201449530475052','2018-07-25 14:13:43','','SM201807251035525556549');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251431097809013','项目管理','1','S','#','2','U201807201449530475052','2018-07-25 14:31:09','','GCHFSSM201800000000000000');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251432097337089','工程管理','1','S','#','1','U201807201449530475052','2018-07-25 14:32:09','','SM201807251431097809013');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251433595347588','项目管理','1','S','/projectManage/project.htm','1','U201807201449530475052','2018-07-25 14:33:59','','SM201807251432097337089');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251434344865351','账户信息','1','S','/yewuManage/account.htm','2','U201807201449530475052','2018-07-25 14:34:34','','SM201807251432097337089');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251435063692212','承包商合同','1','S','/hetong/chengbaoshang.htm','3','U201807201449530475052','2018-07-25 14:35:06','','SM201807251432097337089');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251435274394669','工程进度','1','S','/hetong/jindu.htm','4','U201807201449530475052','2018-07-25 14:35:27','','SM201807251432097337089');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251436050028604','工程人员','1','S','#','3','U201807201449530475052','2018-07-25 14:36:05','','GCHFSSM201800000000000000');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251436269647664','人员工资','1','S','#','4','U201808161619064475257','2018-09-15 21:52:24','','GCHFSSM201800000000000000');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251436517125577','工资核发','1','S','#','5','U201807201449530475052','2018-07-25 14:36:51','','GCHFSSM201800000000000000');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251437067992626','事件处理','1','S','#','6','U201807201449530475052','2018-07-25 14:37:06','','GCHFSSM201800000000000000');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251438359547331','查看考勤','2','S','/attendance','1','U201807201449530475052','2018-07-25 14:38:35','','SM201807251433595347588');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251438586332022','发薪可延迟天数','2','S','/salaryDelayDays','2','U201807201449530475052','2018-07-25 14:38:58','','SM201807251433595347588');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251439204274248','项目详情','2','S','/proDetail','3','U201807201449530475052','2018-07-25 14:39:20','','SM201807251433595347588');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251440190864422','详情','2','S','/detail','1','U201807201449530475052','2018-07-25 14:40:19','','SM201807251434344865351');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251440537853856','详情','2','S','/detail','1','U201807201449530475052','2018-07-25 14:40:53','','SM201807251435063692212');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251443214656044','工程人员','1','S','/projectStaff/projectStaff.htm','1','U201807201449530475052','2018-07-25 14:43:21','','SM201807251436050028604');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251443587321830','查看考勤','1','S','/newProj/kaoqin.htm','3','U201807201449530475052','2018-07-25 14:43:58','','SM201807251436050028604');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251444216463006','请假明细','1','S','/staff/allStaff/leaveRecords.htm','4','U201807201449530475052','2018-07-25 14:44:21','','SM201807251436050028604');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251452120368644','详情','2','S','/detail','1','U201807201449530475052','2018-07-25 14:52:12','','SM201807251443214656044');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251453553311578','人员工资','1','S','/staff/idCardQuery.htm','2','U201808161619064475257','2018-09-15 21:55:10','','SM201807251436269647664');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251457111208136','待发请求','1','S','/daifa/daifa.htm','1','U201807201449530475052','2018-07-25 14:57:11','','SM201807251436517125577');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251457293645507','工资待发','1','S','/waitList/postRequest.htm','2','U201807201449530475052','2018-07-25 14:57:29','','SM201807251436517125577');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251457500979955','历史请求','1','S','/waitList/alreadyQuest.htm','3','U201807201449530475052','2018-07-25 14:57:50','','SM201807251436517125577');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251458072736824','短信通知人','1','S','/waitList/textMessage.htm','4','U201807201449530475052','2018-07-25 14:58:07','','SM201807251436517125577');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251458556236189','详情','2','S','/detail','1','U201807201449530475052','2018-07-25 14:58:55','','SM201807251457111208136');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251459445122186','详情','2','S','/detail','1','U201807201449530475052','2018-07-25 14:59:44','','SM201807251457500979955');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251500512573483','新增','2','S','/add','1','U201807201449530475052','2018-07-25 15:00:51','','SM201807251458072736824');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251501058167751','修改','2','S','/edit','2','U201807201449530475052','2018-07-25 15:01:05','','SM201807251458072736824');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251501213029662','删除','2','S','/delete','3','U201807201449530475052','2018-07-25 15:01:21','','SM201807251458072736824');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251501372661300','详情','2','S','/detail','4','U201807201449530475052','2018-07-25 15:01:37','','SM201807251458072736824');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251502444911000','事件查看','1','S','/handleError.htm','1','U201808101552418309784','2018-09-12 15:50:04','','SM201807251437067992626');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251503000203563','历史事件','1','S','/staff/allStafferrHistory.htm','2','U201807201449530475052','2018-07-25 15:03:00','','SM201807251437067992626');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251518294819305','用户管理','1','O','/system/user.htm','2','U201807241650032989800','2018-07-25 15:18:29','','SM201807241716521466051');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251519476155139','新增','2','O','/add','1','U201807241650032989800','2018-07-25 15:19:47','','SM201807251518294819305');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251520047941473','重置密码','2','O','/reset','2','U201807241650032989800','2018-07-25 15:20:04','','SM201807251518294819305');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251520288012418','注销/激活','2','O','/rock','3','U201807241650032989800','2018-07-25 15:20:28','','SM201807251518294819305');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251520484576549','设置角色','2','O','/assign','4','U201807241650032989800','2018-07-25 15:20:48','','SM201807251518294819305');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251521045971222','修改手机号','2','O','/changeMobile','5','U201807241650032989800','2018-07-25 15:21:04','','SM201807251518294819305');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251542246937138','用户管理','1','B','/system/user.htm','2','U201807201448369903584','2018-07-25 15:42:24','','SM201807241335405222279');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251544214857469','新增','2','B','/add','1','U201807201448369903584','2018-07-25 15:44:21','','SM201807251542246937138');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251544488732528','重置密码','2','B','/reset','2','U201807201448369903584','2018-07-25 15:44:48','','SM201807251542246937138');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251545152369492','注销/激活','2','B','/reset','3','U201807201448369903584','2018-07-25 15:45:15','','SM201807251542246937138');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251545383158357','设置角色','2','B','/assign','4','U201807201448369903584','2018-07-25 15:45:38','','SM201807251542246937138');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251546038878459','修改手机号','2','B','/changeMobile','5','U201807201448369903584','2018-07-25 15:46:03','','SM201807251542246937138');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251702481921490','新增','2','S','/add','1','U201807201449530475052','2018-07-25 17:02:48','','SM201807251407426617014');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251703058949902','重置密码','2','S','/reset','2','U201807201449530475052','2018-07-25 17:03:05','','SM201807251407426617014');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251703259708849','注销/激活','2','S','/rock','3','U201807201449530475052','2018-07-25 17:03:25','','SM201807251407426617014');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251703450141178','设置角色','2','S','/assign','4','U201807201449530475052','2018-07-25 17:03:45','','SM201807251407426617014');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201807251704075757333','修改手机号','2','S','/changeMobile','5','U201807201449530475052','2018-07-25 17:04:07','','SM201807251407426617014');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201808081621410917107','操作指南管理','1','P','/system/help.htm','4','USYS201800000000001','2018-08-08 16:21:41','','SM201807201031386412313');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201808081621558511446','新增','2','P','/add','1','USYS201800000000001','2018-08-08 16:21:55','','SM201808081621410917107');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201808081622094266869','修改','2','P','/edit','2','USYS201800000000001','2018-08-08 16:22:09','','SM201808081621410917107');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201808081622234698565','删除','2','P','/delete','3','USYS201800000000001','2018-08-08 16:22:23','','SM201808081621410917107');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201808081703299382872','补录合同','2','O','/addContract','4','U201808081619291286710','2018-08-08 17:03:29','','SM201807241758132452206');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201808131423408687001','详情','2','P','/detail','4','USYS201800000000001','2018-08-13 14:23:40','','SM201808081621410917107');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201809121032370178022','处理','2','O','/detail','1','U201808101551265155214','2018-09-12 10:32:37','','SM201807241859489641457');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201809131107320047229','拍摄免冠照','2','O','/takePhoto','5','U201808101551265155214','2018-09-13 11:07:32','','SM201807241758132452206');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201809131108052361321','提取特征值','2','O','/getFeat','7','U201808171655276276678','2018-09-15 22:34:25','','SM201807241758132452206');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201809131108382303089','验证特征值','2','O','/checkFeat','8','U201808171655276276678','2018-09-15 22:34:37','','SM201807241758132452206');
insert into `tsys_menu`(`code`,`name`,`type`,`system_code`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`)
values('SM201809152233072843174','拍摄身份证','2','O','/takeIdPic','6','U201808171655276276678','2018-09-15 22:33:07','','SM201807241758132452206');

insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3567','RO201800000000000002','GCHFBSM201800000000000000','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3568','RO201800000000000002','SM201807231030483125593','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3569','RO201800000000000002','SM201807231031386412313','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3570','RO201800000000000002','SM201807231035525556549','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3571','RO201800000000000002','SM201807231129177262547','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3572','RO201800000000000002','SM201807241336205099958','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3573','RO201800000000000002','SM201807241342422409334','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3574','RO201800000000000002','SM201807241355087008815','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3575','RO201800000000000002','SM201807241359246395059','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3576','RO201800000000000002','SM201807241359560335048','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3577','RO201800000000000002','SM201807241335405222279','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3578','RO201800000000000002','SM201807241339528185483','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3579','RO201800000000000002','SM201807241342596513263','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3580','RO201800000000000002','SM201807241355483004750','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3581','RO201800000000000002','SM201807241400081795457','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3582','RO201800000000000002','SM201807241340146487913','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3583','RO201800000000000002','SM201807241342024542245','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3584','RO201800000000000002','SM201807241354444837809','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3585','RO201800000000000002','SM201807241356215805380','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3586','RO201800000000000002','SM201807241400236309411','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3587','RO201800000000000002','SM201807241343221479328','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3588','RO201800000000000002','SM201807241356471961442','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3589','RO201800000000000002','SM201807241400373916195','U201807201448369903584','2018-07-25 15:46:19',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3590','RO201800000000000002','SM201807251542246937138','U201807201448369903584','2018-07-25 15:46:20',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3591','RO201800000000000002','SM201807251544214857469','U201807201448369903584','2018-07-25 15:46:20',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3592','RO201800000000000002','SM201807251544488732528','U201807201448369903584','2018-07-25 15:46:20',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3593','RO201800000000000002','SM201807251545152369492','U201807201448369903584','2018-07-25 15:46:20',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3594','RO201800000000000002','SM201807251545383158357','U201807201448369903584','2018-07-25 15:46:20',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('3595','RO201800000000000002','SM201807251546038878459','U201807201448369903584','2018-07-25 15:46:20',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4412','SR201808101401167395662','SM201807241751172466144','U201808101400195147701','2018-08-10 14:03:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4413','SR201808101401167395662','SM201807241857310508573','U201808101400195147701','2018-08-10 14:03:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4414','SR201808101401167395662','SM201807241858080526660','U201808101400195147701','2018-08-10 14:03:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4415','SR201808101401167395662','SM201807251102287288581','U201808101400195147701','2018-08-10 14:03:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4416','SR201808101401167395662','SM201807251102441529195','U201808101400195147701','2018-08-10 14:03:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4417','SR201808101401167395662','SM201807241858292354817','U201808101400195147701','2018-08-10 14:03:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4418','SR201808101401167395662','SM201807251103314158958','U201808101400195147701','2018-08-10 14:03:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4419','SR201808101401167395662','SM201807241759554185762','U201808101400195147701','2018-08-10 14:03:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4420','SR201808101401167395662','SM201807251100586462320','U201808101400195147701','2018-08-10 14:03:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4421','SR201808101401167395662','SM201807251101159737671','U201808101400195147701','2018-08-10 14:03:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4422','SR201808101401167395662','SM201807241751344565023','U201808101400195147701','2018-08-10 14:03:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4423','SR201808101401167395662','SM201807241859489641457','U201808101400195147701','2018-08-10 14:03:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4424','SR201808101401167395662','SM201807241900154863609','U201808101400195147701','2018-08-10 14:03:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4425','SR201808101401298754550','SM201807241750117357259','U201808101400195147701','2018-08-10 14:03:58',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4426','SR201808101401298754550','SM201807241752310501351','U201808101400195147701','2018-08-10 14:03:58',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4427','SR201808101401298754550','SM201807241753209506985','U201808101400195147701','2018-08-10 14:03:58',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4428','SR201808101401298754550','SM201807241821586779099','U201808101400195147701','2018-08-10 14:03:58',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4429','SR201808101401298754550','SM201807241824314342023','U201808101400195147701','2018-08-10 14:03:58',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4430','SR201808101401298754550','SM201807241825425045330','U201808101400195147701','2018-08-10 14:03:58',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4431','SR201808101401298754550','SM201807241826245194724','U201808101400195147701','2018-08-10 14:03:58',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4432','SR201808101401298754550','SM201807241827016053393','U201808101400195147701','2018-08-10 14:03:58',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4433','SR201808101401298754550','SM201807241827308831941','U201808101400195147701','2018-08-10 14:03:58',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4434','SR201808101401298754550','SM201807241753481912350','U201808101400195147701','2018-08-10 14:03:58',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4435','SR201808101401298754550','SM201807251021011637513','U201808101400195147701','2018-08-10 14:03:58',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4436','SR201808101401298754550','SM201807251021266445121','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4437','SR201808101401298754550','SM201807241754128281700','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4438','SR201808101401298754550','SM201807251024028172458','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4439','SR201808101401298754550','SM201807251024316852729','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4440','SR201808101401298754550','SM201807251024572542774','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4441','SR201808101401298754550','SM201807241754430292519','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4442','SR201808101401298754550','SM201807241751002521252','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4443','SR201808101401298754550','SM201807241757482373477','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4444','SR201808101401298754550','SM201807241758132452206','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4445','SR201808101401298754550','SM201807251040061583593','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4446','SR201808101401298754550','SM201807251040378537609','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4447','SR201808101401298754550','SM201807251041091321387','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4448','SR201808101401298754550','SM201807251045289737863','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4449','SR201808101401298754550','SM201808081703299382872','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4450','SR201808101401298754550','SM201807241759034094106','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4451','SR201808101401298754550','SM201807241759353788991','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4452','SR201808101401298754550','SM201807241759554185762','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4453','SR201808101401298754550','SM201807251100586462320','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4454','SR201808101401298754550','SM201807251101159737671','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4455','SR201808101401298754550','SM201807241751172466144','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4456','SR201808101401298754550','SM201807241857310508573','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4457','SR201808101401298754550','SM201807241858080526660','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4458','SR201808101401298754550','SM201807251102441529195','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4459','SR201808101401298754550','SM201807241858292354817','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4460','SR201808101401298754550','SM201807251103314158958','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4461','SR201808101401298754550','SM201807241751344565023','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4462','SR201808101401298754550','SM201807241859489641457','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4463','SR201808101401298754550','SM201807241900154863609','U201808101400195147701','2018-08-10 14:03:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4483','SR201808101609457393532','GCHFOSM201800000000000000','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4484','SR201808101609457393532','SM201807241751172466144','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4485','SR201808101609457393532','SM201807241857310508573','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4486','SR201808101609457393532','SM201807241858080526660','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4487','SR201808101609457393532','SM201807251102287288581','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4488','SR201808101609457393532','SM201807251102441529195','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4489','SR201808101609457393532','SM201807241858292354817','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4490','SR201808101609457393532','SM201807251103314158958','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4491','SR201808101609457393532','SM201807241751344565023','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4492','SR201808101609457393532','SM201807241859489641457','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4493','SR201808101609457393532','SM201807241900154863609','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4494','SR201808101609457393532','SM201807241750117357259','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4495','SR201808101609457393532','SM201807241752310501351','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4496','SR201808101609457393532','SM201807241753209506985','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4497','SR201808101609457393532','SM201807241753481912350','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4498','SR201808101609457393532','SM201807251021266445121','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4499','SR201808101609457393532','SM201807241751002521252','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4500','SR201808101609457393532','SM201807241758132452206','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4501','SR201808101609457393532','SM201807241759034094106','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4502','SR201808101609457393532','SM201807241759353788991','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4503','SR201808101609457393532','SM201807241759554185762','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4504','SR201808101609457393532','SM201807251101159737671','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4505','SR201808101609457393532','SM201807251045289737863','U201808101551265155214','2018-08-10 16:10:17',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4506','SR201808101608233267844','GCHFOSM201800000000000000','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4507','SR201808101608233267844','SM201807241752310501351','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4508','SR201808101608233267844','SM201807241753209506985','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4509','SR201808101608233267844','SM201807241757482373477','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4510','SR201808101608233267844','SM201807241753481912350','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4511','SR201808101608233267844','SM201807241758132452206','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4512','SR201808101608233267844','SM201807251021266445121','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4513','SR201808101608233267844','SM201807251040378537609','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4514','SR201808101608233267844','SM201807251101159737671','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4515','SR201808101608233267844','SM201807241750117357259','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4516','SR201808101608233267844','SM201807241754128281700','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4517','SR201808101608233267844','SM201807251024572542774','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4518','SR201808101608233267844','SM201807251041091321387','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4519','SR201808101608233267844','SM201807241751002521252','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4520','SR201808101608233267844','SM201807241754430292519','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4521','SR201808101608233267844','SM201807241759034094106','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4522','SR201808101608233267844','SM201807241759353788991','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4523','SR201808101608233267844','SM201807241759554185762','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4524','SR201808101608233267844','SM201807251045289737863','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4525','SR201808101608233267844','SM201807251040061583593','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4526','SR201808101608233267844','SM201808081703299382872','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4527','SR201808101608233267844','SM201807251100586462320','U201808101551265155214','2018-08-10 16:27:31',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4528','RO201800000000000001','GCHFPSM201800000000000000','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4529','RO201800000000000001','SM201807201030483125593','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4530','RO201800000000000001','SM201807201031386412313','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4531','RO201800000000000001','SM201807201035525556549','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4532','RO201800000000000001','SM201807201038045508404','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4533','RO201800000000000001','SM201807201125161861248','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4534','RO201800000000000001','SM201807201125421699687','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4535','RO201800000000000001','SM201807201126211661803','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4536','RO201800000000000001','SM201807201129177262546','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4537','RO201800000000000001','SM201807201129177262547','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4538','RO201800000000000001','SM201807201240189699293','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4539','RO201800000000000001','SM201807201345378904554','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4540','RO201800000000000001','SM201807251123462276274','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4541','RO201800000000000001','SM201807251126134348811','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4542','RO201800000000000001','SM201807251127455775704','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4543','RO201800000000000001','SM201807251129247701972','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4544','RO201800000000000001','SM201807251131493932434','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4545','RO201800000000000001','SM201807251133480854384','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4546','RO201800000000000001','SM201807251134435204415','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4547','RO201800000000000001','SM201807251135184292102','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4548','RO201800000000000001','SM201808081621558511446','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4549','RO201800000000000001','SM201807201031554085068','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4550','RO201800000000000001','SM201807201036520116270','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4551','RO201800000000000001','SM201807201038358168249','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4552','RO201800000000000001','SM201807201124298734547','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4553','RO201800000000000001','SM201807201126371934570','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4554','RO201800000000000001','SM201807201129373238575','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4555','RO201800000000000001','SM201807201240487754033','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4556','RO201800000000000001','SM201807201346222811520','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4557','RO201800000000000001','SM201807251122527841467','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4558','RO201800000000000001','SM201807251124338772219','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4559','RO201800000000000001','SM201807251126285602356','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4560','RO201800000000000001','SM201807251128071608961','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4561','RO201800000000000001','SM201807251129425669001','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4562','RO201800000000000001','SM201807251132224253480','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4563','RO201800000000000001','SM201807251135329712143','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4564','RO201800000000000001','SM201808081622094266869','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4565','RO201800000000000001','SM201807201037279421821','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4566','RO201800000000000001','SM201807201124447788270','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4567','RO201800000000000001','SM201807201127025377983','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4568','RO201800000000000001','SM201807201130034336159','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4569','RO201800000000000001','SM201807201131571203094','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4570','RO201800000000000001','SM201807201346521318233','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4571','RO201800000000000001','SM201807251124551918527','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4572','RO201800000000000001','SM201808081622234698565','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4573','RO201800000000000001','SM201807201127186657558','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4574','RO201800000000000001','SM201807201130316042069','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4575','RO201800000000000001','SM201807201347158228349','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4576','RO201800000000000001','SM201808081621410917107','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4577','RO201800000000000001','SM201807201131011696446','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4578','RO201800000000000001','SM201808131423408687001','USYS201800000000001','2018-08-13 14:23:51',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4833','RO201800000000000004','GCHFSSM201800000000000000','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4834','RO201800000000000004','SM201807251432097337089','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4835','RO201800000000000004','SM201807251433595347588','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4836','RO201800000000000004','SM201807251438359547331','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4837','RO201800000000000004','SM201807251440190864422','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4838','RO201800000000000004','SM201807251443214656044','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4839','RO201800000000000004','SM201807251452120368644','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4840','RO201800000000000004','SM201807251457111208136','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4841','RO201800000000000004','SM201807251458556236189','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4842','RO201800000000000004','SM201807251459445122186','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4843','RO201800000000000004','SM201807251500512573483','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4844','RO201800000000000004','SM201807251502444911000','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4845','RO201800000000000004','SM201807251431097809013','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4846','RO201800000000000004','SM201807251434344865351','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4847','RO201800000000000004','SM201807251438586332022','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4848','RO201800000000000004','SM201807251453553311578','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4849','RO201800000000000004','SM201807251457293645507','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4850','RO201800000000000004','SM201807251501058167751','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4851','RO201800000000000004','SM201807251503000203563','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4852','RO201800000000000004','SM201807251436050028604','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4853','RO201800000000000004','SM201807251439204274248','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4854','RO201800000000000004','SM201807251443587321830','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4855','RO201800000000000004','SM201807251457500979955','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4856','RO201800000000000004','SM201807251501213029662','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4857','RO201800000000000004','SM201807251436269647664','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4858','RO201800000000000004','SM201807251444216463006','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4859','RO201800000000000004','SM201807251458072736824','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4860','RO201800000000000004','SM201807251501372661300','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4861','RO201800000000000004','SM201807251436517125577','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('4862','RO201800000000000004','SM201807251437067992626','USYS201800000000001','2018-09-15 21:58:59',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5033','RO201800000000000003','GCHFOSM201800000000000000','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5034','RO201800000000000003','SM201807241030483125593','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5035','RO201800000000000003','SM201807241717261696782','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5036','RO201800000000000003','SM201807241723457719659','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5037','RO201800000000000003','SM201807241752310501351','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5038','RO201800000000000003','SM201807241753209506985','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5039','RO201800000000000003','SM201807241757482373477','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5040','RO201800000000000003','SM201807241821586779099','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5041','RO201800000000000003','SM201807241857310508573','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5042','RO201800000000000003','SM201807241859489641457','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5043','RO201800000000000003','SM201807251021011637513','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5044','RO201800000000000003','SM201807251040061583593','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5045','RO201800000000000003','SM201807251100586462320','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5046','RO201800000000000003','SM201807251102287288581','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5047','RO201800000000000003','SM201807251103314158958','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5048','RO201800000000000003','SM201807251519476155139','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5049','RO201800000000000003','SM201809121032370178022','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5050','RO201800000000000003','SM201807241716521466051','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5051','RO201800000000000003','SM201807241723573361745','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5052','RO201800000000000003','SM201807241753481912350','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5053','RO201800000000000003','SM201807241758132452206','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5054','RO201800000000000003','SM201807241824314342023','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5055','RO201800000000000003','SM201807241858080526660','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5056','RO201800000000000003','SM201807241900154863609','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5057','RO201800000000000003','SM201807251021266445121','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5058','RO201800000000000003','SM201807251040378537609','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5059','RO201800000000000003','SM201807251101159737671','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5060','RO201800000000000003','SM201807251102441529195','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5061','RO201800000000000003','SM201807251518294819305','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5062','RO201800000000000003','SM201807251520047941473','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5063','RO201800000000000003','SM201807241724339186026','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5064','RO201800000000000003','SM201807241750117357259','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5065','RO201800000000000003','SM201807241825425045330','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5066','RO201800000000000003','SM201807241858292354817','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5067','RO201800000000000003','SM201807251041091321387','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5068','RO201800000000000003','SM201807251520288012418','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5069','RO201800000000000003','SM201807241724163987002','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5070','RO201800000000000003','SM201807241751002521252','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5071','RO201800000000000003','SM201807241759034094106','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5072','RO201800000000000003','SM201807241826245194724','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5073','RO201800000000000003','SM201807251520484576549','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5074','RO201800000000000003','SM201808081703299382872','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5075','RO201800000000000003','SM201807241751172466144','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5076','RO201800000000000003','SM201807241759353788991','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5077','RO201800000000000003','SM201807241827016053393','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5078','RO201800000000000003','SM201807251521045971222','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5079','RO201800000000000003','SM201809131107320047229','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5080','RO201800000000000003','SM201807241751344565023','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5081','RO201800000000000003','SM201807241759554185762','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5082','RO201800000000000003','SM201807241827308831941','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5083','RO201800000000000003','SM201809152233072843174','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5084','RO201800000000000003','SM201809131108052361321','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5085','RO201800000000000003','SM201809131108382303089','USYS201800000000001','2018-09-15 22:38:11',null);
insert into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`)
values('5086','RO201800000000000003','SM201807251045289737863','USYS201800000000001','2018-09-15 22:38:11',null);
