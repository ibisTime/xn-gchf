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
INSERT INTO `thf_user` (`user_id`,`real_name`,`type`,`photo`,`login_name`,`mobile`,`login_pwd`,`login_pwd_strength`,`user_refree`,`create_datetime`,`role_code`,`company_code`,`company_name`,`province`,`city`,`area`,`bank_name`,`subbranch`,`department_code`,`updater`,`update_datetime`,`status`,`remark`) VALUES ('USYS201800000000001','平台端','P',NULL,'admin',NULL,'21218cca77804d2ba1922c33e0151105','1',NULL,now(),'RO201800000000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,'0',NULL);

/*
-- Query: SELECT * FROM dev_xn_gchf.tsys_dict
LIMIT 0, 1000
-- Date: 2018-06-29 14:01
*/
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (1,'0',NULL,'user_kind','用户类型','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (2,'1','user_kind','P','平台用户','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (3,'1','user_kind','B','银行用户','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (4,'1','user_kind','O','业主单位','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (5,'1','user_kind','S','监管单位','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (6,'0',NULL,'user_status','用户状态','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (7,'1','user_status','0','正常','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (8,'1','user_status','1','人工锁定','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (9,'1','user_status','2','程序锁定','admin',now(),'');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (10,'0',NULL,'project_status','项目状态','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (11,'1','project_status','0','待编辑','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (12,'1','project_status','1','待开工','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (13,'1','project_status','2','在建','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (49,'1','project_status','3','停工','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (50,'1','project_status','4','结束','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (15,'0',NULL,'account_status','账户状态','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (16,'1','account_status','0','正常','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (17,'1','account_status','1','人工锁定','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (18,'1','account_status','2','程序锁定','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (19,'0',NULL,'staff_status','员工状态','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (20,'1','staff_status','0','在职','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (21,'1','staff_status','1','请假','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (22,'1','staff_status','2','离职','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (23,'0',NULL,'message_status','代发消息状态','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (24,'1','message_status','0','待发送','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (25,'1','message_status','1','待处理','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (26,'1','message_status','2','待反馈','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (51,'1','message_status','3','已处理','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (27,'0',NULL,'salary_status','工资条状态','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (28,'1','salary_status','0','待人工复核','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (29,'1','salary_status','1','已审核待提交','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (30,'1','salary_status','2','已提交待发放','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (31,'1','salary_status','3','正常发放','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (32,'1','salary_status','4','部分发放','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (33,'1','salary_status','5','已转正常','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (54,'1','salary_status','6','延迟发放','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (55,'1','salary_status','7','部分且延迟发放','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (34,'0',NULL,'staff_type','务工人员类型','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (35,'1','staff_type','0','直招工人','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (36,'1','staff_type','1','劳务工人','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (37,'1','staff_type','2','包工工人','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (38,'1','staff_type','3','内勤人员','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (39,'0',NULL,'id_type','证件类型','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (40,'1','id_type','1','身份证','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (41,'0',NULL,'attendance_status','考勤状态','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (42,'1','attendance_status','0','待上班打卡','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (43,'1','attendance_status','1','待下班打卡','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (44,'1','attendance_status','2','已打卡待结算','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (45,'1','attendance_status','3','已结算','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (46,'0',NULL,'salary_log_type','日志类型','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (47,'1','salary_log_type','0','正常','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (48,'1','salary_log_type','1','异常','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (52,'0',NULL,'abnormal_type','事件类型','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (53,'1','abnormal_type','0','工资条异常','admin',now(),NULL);

/*
-- Query: SELECT * FROM dev_xn_gchf.tsys_config
LIMIT 0, 1000
-- Date: 2018-06-29 14:01
*/
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (1,'sys_txt','telephone','0571-88888888','USYS201800000000001',now(),'联系电话');
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (2,'sys_txt','about_us','关于我们112','USYS201800000000001',now(),'关于我们');
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (3,'sys_txt','service_time','9:00-17:40','USYS201800000000001',now(),'服务时间');
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (4,'qiniu','qiniu_access_key','07KR5rNezHcXebD-GalrPw0npsAODOMVxygvdFFt','USYS201800000000001',now(),'七牛云key1');
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (5,'qiniu','qiniu_secret_key','nsMbXOfEtk3SvQ3GFHbKMozJua3jbTiGPIIwu4tq','USYS201800000000001',now(),'qiniu_secret_key');
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (6,'qiniu','qiniu_bucket','zwzj','USYS201800000000001',now(),'qiniu_bucket');
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (7,'qiniu','qiniu_domain','otoieuivb.bkt.clouddn.com','USYS201800000000001',now(),'访问域名');
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (8,'sys_txt','telephone','0571-88888888','USYS201800000000001',now(),'联系电话');
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (9,'sys_txt','about_us','关于我们112','USYS201800000000001',now(),'关于我们');
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (10,'sys_txt','service_time','9:00-17:40','USYS201800000000001',now(),'服务时间');
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (11,'qiniu','qiniu_access_key','07KR5rNezHcXebD-GalrPw0npsAODOMVxygvdFFt','USYS201800000000001',now(),'七牛云key1');
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (12,'qiniu','qiniu_secret_key','nsMbXOfEtk3SvQ3GFHbKMozJua3jbTiGPIIwu4tq','USYS201800000000001',now(),'qiniu_secret_key');
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (13,'qiniu','qiniu_bucket','zwzj','USYS201800000000001',now(),'qiniu_bucket');
INSERT INTO `tsys_config` (`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES (14,'qiniu','qiniu_domain','otoieuivb.bkt.clouddn.com','USYS201800000000001',now(),'访问域名');

/*
-- Query: SELECT * FROM dev_xn_gchf.tsys_menu
LIMIT 0, 1000
-- Date: 2018-07-19 13:12
*/
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('GCHFSM201800000000000000','根目录','1','#','1','admin',now(),'','');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('GCHFSM201800001000000001','系统管理','1','#','1','admin',now(),'','GCHFSM201800000000000000');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('GCHFSM201800001000000002','运维管理','1','#','2','admin',now(),NULL,'GCHFSM201800001000000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('GCHFSM201800001000000003','菜单管理','1','/system/menu.htm','1','admin',now(),NULL,'GCHFSM201800001000000002');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('GCHFSM201800001000000004','新增','2','/add','1','admin',now(),'','GCHFSM201800001000000003');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('GCHFSM201800001000000006','运营管理','1','#','2','admin',now(),'','GCHFSM201800001000000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('GCHFSM201800001000000007','角色管理','1','/system/role.htm','1','admin',now(),'','GCHFSM201800001000000006');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('GCHFSM201800001000000008','用户管理','1','/system/user.htm','1','admin',now(),'','GCHFSM201800001000000006');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('GCHFSM201800001000000009','系统参数管理','1','/system/sysPara.htm','1','USYS201800000000001',now(),'','GCHFSM201800001000000002');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('GCHFSM201800001000000010','数据字典管理','1','/system/dataDict.htm','1','USYS201800000000001',now(),'','GCHFSM201800001000000002');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('GCHFSM201800001000000011','分配菜单','2','/change','4','admin',now(),'','GCHFSM201800001000000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804261613078269182','修改','2','/edit','2','admin',now(),'','GCHFSM201800001000000003');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804261614002237155','删除','2','/delete','3','admin',now(),'','GCHFSM201800001000000003');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804261623266254030','修改','2','/edit','1','admin',now(),'','GCHFSM201800001000000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804261705017439319','修改','2','/edit','1','admin',now(),'','GCHFSM201800001000000010');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804261722191103766','新增','2','/add','1','admin',now(),'','GCHFSM201800001000000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804261722366268383','修改','2','/edit','2','admin',now(),'','GCHFSM201800001000000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804261722548756668','删除','2','/delete','4','admin',now(),'','GCHFSM201800001000000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804261758457556570','新增','2','/add','1','admin',now(),'','GCHFSM201800001000000008');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804261759056455609','重置密码','2','/reset','2','admin',now(),'','GCHFSM201800001000000008');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804261800054386747','注销/激活','2','/rock','3','admin',now(),'','GCHFSM201800001000000008');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804261800542746171','设置角色','2','/assign','4','admin',now(),'','GCHFSM201800001000000008');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804261921017207214','修改手机号','2','/changeMobile','5','admin',now(),'','GCHFSM201800001000000008');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804262009375685547','工资核发','1','#','5','admin',now(),'','GCHFSM201800000000000000');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804262037078836469','工资待发','1','/waitList/postRequest.htm','3','USYS201800000000001',now(),'','SM201804262009375685547');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804262037546867086','历史请求','1','/waitList/alreadyQuest.htm','5','USYS201800000000001',now(),'','SM201804262009375685547');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804262110196148832','公司管理','1','#','3','admin','2018-07-06 12:03:24','','GCHFSM201800001000000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804262111286723837','公司结构','1','/newProj/companyConstruct.htm','1','admin','2018-07-06 12:03:24','','SM201804262110196148832');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804262112397419688','员工管理','1','#','4','admin','2018-07-06 12:03:24','','GCHFSM201800000000000000');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804262118246417123','承包商合同','1','/hetong/chengbaoshang.htm','3','USYS201800000000001','2018-07-12 20:55:17','','SM201807122039242882417');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804262121064388530','待发请求','1','/daifa/daifa.htm','2','USYS201800000000001','2018-07-16 14:22:52','','SM201804262009375685547');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804271918126145662','项目管理','1','#','2','USYS201800000000001','2018-07-14 16:02:29','','GCHFSM201800000000000000');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804271923251312840','项目管理','1','/projectManage/project.htm','1','USYS201800000000001','2018-07-13 10:29:10','','SM201807122039242882417');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804291350415659355','账户信息','1','/yewuManage/account.htm','2','USYS201800000000001','2018-07-13 10:31:12','','SM201807122039242882417');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804291425230875702','修改','2','/edit','1','admin','2018-07-06 12:03:24','','SM201804291350415659355');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804291425476906723','详情','2','/detail','2','admin','2018-07-06 12:03:24','','SM201804291350415659355');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804291522572535111','设置部门','2','/setBumen','7','admin','2018-07-06 12:03:24','','GCHFSM201800001000000008');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805021513358511912','合同录入','2','/add','1','admin','2018-07-06 12:03:24','','SM201804262118246417123');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805021608214954446','录入进度','2','/add','1','18870421644','2018-07-06 12:03:24','','SM201804262119227928535');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805021608430535380','修改进度','2','/edit','2','18870421644','2018-07-06 12:03:24','','SM201804262119227928535');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805021609039267284','详情','2','/detail','3','18870421644','2018-07-06 12:03:24','','SM201804262119227928535');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805021637202209454','修改','2','/edit','2','18870421644','2018-07-06 12:03:24','','SM201804262118246417123');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805021637409537163','详情','2','/detail','3','18870421644','2018-07-06 12:03:24','','SM201804262118246417123');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805021701134437182','详情','2','/detail','1','admin','2018-07-06 12:03:24','','SM201804262037078836469');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805022048193922921','修改','2','/edit','2','18870421644','2018-07-06 12:03:24','','SM201804262117511207724');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805022048376225056','详情','2','/detail','3','18870421644','2018-07-06 12:03:24','','SM201804262117511207724');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805031143039272323','员工查看','1','/staff/allStaff.htm','2','USYS201800000000001','2018-07-11 16:42:51','','SM201804262112397419688');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805031146406461279','下载','2','/onDownLoad','2','admin','2018-07-06 12:03:24','','SM201804262037078836469');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805031341536277279','修改','2','/edit','6','USYS201800000000001','2018-07-06 12:03:24','','SM201805031143039272323');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805031342142928555','详情','2','/detail','7','USYS201800000000001','2018-07-06 12:03:24','','SM201805031143039272323');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805031622490571847','发送','2','/send','1','USYS201800000000003','2018-07-06 12:03:24','','SM201804262121064388530');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805031623038864903','详情','2','/detail','2','USYS201800000000003','2018-07-06 12:03:24','','SM201804262121064388530');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805032049250384279','工资卡管理','1','/staff/bankCard.htm','6','USYS201800000000001','2018-07-14 15:33:38','','SM201807122042550547654');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805041050394705600','详情','2','/detail','1','admin','2018-07-06 12:03:24','','SM201804262037546867086');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805041411074644391','工作履历','2','/history','5','USYS201800000000001','2018-05-26 22:17:31','','SM201805031143039272323');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805061642131878722','开设账号','1','#','2','admin','2018-07-06 12:03:24','','GCHFSM201800000000000000');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805061646092985904','银行单位','1','www.baidu.com','1','USYS201800000000001','2018-07-06 12:03:24','','SM201805061642131878722');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805061647022105306','业主单位','1','www.baidu.com','2','USYS201800000000001','2018-07-06 12:03:24','','SM201805061642131878722');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805061647325709749','监管单位','1','www.baidu.com','3','admin','2018-07-06 12:03:24','','SM201805061642131878722');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805061648484984373','银行用户','1','/newId/bank.htm','2','USYS201800000000001','2018-07-06 12:03:24','','SM201805061646092985904');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805061649465011644','业主用户','1','/newId/yezhu.htm','2','USYS201800000000001','2018-07-06 12:03:24','','SM201805061647022105306');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805061653506988861','监管用户','1','/newId/supervise.htm','1','admin','2018-07-06 12:03:24','','SM201805061647325709749');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805061657291081116','新增','2','/add','1','admin','2018-07-06 12:03:24','','SM201805061648484984373');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805061714532327913','事件处理','1','#','6','admin','2018-07-06 12:03:24','','GCHFSM201800000000000000');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805061739537493514','用工合同','1','/hetong/wugong.htm','3','USYS201800000000001','2018-07-12 20:51:24','','SM201807122042550547654');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805071356535937524','新增','2','/add','1','admin','2018-07-06 12:03:24','','SM201805061649465011644');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805071357335216787','新增','2','/add','1','admin','2018-07-06 12:03:24','','SM201805061653506988861');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805071518444813149','异常查看','1','/staff/allStafferror.htm','1','USYS201800000000001','2018-05-18 11:13:24','','SM201805061714532327913');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805101121136563046','公司结构','1','/newId/companyConstruct.htm','1','admin','2018-07-06 12:03:24','','SM201805061647022105306');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805101326343014288','修改','2','/edit','1','admin','2018-07-06 12:03:24','','SM201805032049250384279');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805101326483303672','详情','2','/detail','2','admin','2018-07-06 12:03:24','','SM201805032049250384279');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805101339482085520','设置部门','2','/setBumen1','2','admin','2018-07-06 12:03:24','','SM201805061649465011644');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805122203323176704','新增项目','2','/addProject','1','admin','2018-07-06 12:03:24','','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805122244282147848','查看考勤','2','/attendance','9','USYS201800000000001','2018-07-14 18:40:58','','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805122246258158393','项目详情','2','/proDetail','10','USYS201800000000001','2018-07-16 14:00:58','','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805122247268142994','修改项目','2','/editPro','3','USYS201800000000001','2018-07-14 18:40:06','','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805122247567061152','审核项目','2','/checkPro','5','USYS201800000000001','2018-07-14 18:40:27','','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805122248245127470','项目停工','2','/overPro','6','USYS201800000000001','2018-07-06 12:03:24','','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805122249222019735','重新开工','2','/aWork','7','USYS201800000000001','2018-07-06 12:03:24','','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805142040150952935','项目结束','2','/overTime','8','USYS201800000000001','2018-07-06 12:03:25','','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805142231375155440','提请审核','2','/tiqingshenhe','4','USYS201800000000001','2018-07-14 18:40:19','','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805151541147523361','补录技能','2','/skill','2','USYS201800000000001','2018-07-12 16:31:20','','SM201805031143039272323');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805152234494002315','详情','2','/detail','2','USYS201800000000001','2018-07-06 12:03:25','','SM201805061648484984373');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805160550482243371','详情','2','/detail','2','USYS201800000000001','2018-07-06 12:03:25','','SM201805061649465011644');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805160557297112397','详情','2','/detail','2','USYS201800000000001','2018-07-06 12:03:25','','SM201805061653506988861');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805171903390774769','银行管理','1','/newId/operation.htm','1','USYS201800000000001','2018-07-06 12:03:25','','SM201805061646092985904');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805181426110139658','新增银行','2','/add','1','USYS201800000000001','2018-07-06 12:03:25','','SM201805171903390774769');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805181426547627373','修改银行','2','/edit','2','USYS201800000000001','2018-07-06 12:03:25','','SM201805171903390774769');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805231252504327898','身份证号查询','1','/staff/idCardQuery.htm','4','USYS201800000000001','2018-07-11 16:43:05','','SM201804262112397419688');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805251354520521925','查看考勤','1','/newProj/kaoqin.htm','4','USYS201800000000001','2018-07-14 15:33:55','','SM201807122042550547654');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805251516327463058','历史事件','1','/staff/allStafferrHistory.htm','2','USYS201800000000001','2018-07-06 12:03:25','','SM201805061714532327913');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805251544130622832','转换为正常','2','/Transformation','2','USYS201800000000001','2018-07-06 12:03:25','','SM201805071518444813149');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805261001490495192','申请请假','2','/weekday','3','USYS201800000000001','2018-07-08 14:47:48','','SM201805031143039272323');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805261021084049823','离职申请','2','/quit','17','USYS201800000000001','2018-06-29 16:12:33','','SM201805031143039272323');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805261621476424511','工程进度','1','/hetong/jindu.htm','4','USYS201800000000001','2018-07-12 20:57:04','','SM201807122039242882417');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805261758591375743','办理入职','2','/addWorkers','1','USYS201800000000001','2018-07-06 12:03:25','','SM201805031143039272323');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201806252141430605486','部门管理','2','/addBumen','2','USYS201800000000001','2018-07-14 18:39:59','','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201806252143270574752','人员建档','1','/staff/jiandang.htm','1','USYS201800000000001','2018-07-06 12:03:25','','SM201804262112397419688');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201806270003243064218','待反馈','1','/waitList/feedback.htm','4','USYS201800000000001','2018-07-16 14:23:21','','SM201804262009375685547');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807081447207731033','请假明细','2','/leaveRecords','4','USYS201800000000001','2018-07-08 14:47:20','','SM201805031143039272323');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807091728303553393','发薪可延迟天数','2','/salaryDelayDays','11','USYS201800000000001','2018-07-16 14:01:05','','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807121137146109835','补录银行卡','2','/addBankCard','8','USYS201800000000001','2018-07-12 14:44:53','','SM201805031143039272323');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807122039242882417','工程管理','1','#','1','USYS201800000000001','2018-07-12 20:39:24','','SM201804271918126145662');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807122042550547654','工程人员','1','#','3','USYS201800000000001','2018-07-14 16:02:39','','GCHFSM201800000000000000');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807122044534007113','办理入职','1','/staff/jiandang1.htm','1','USYS201800000000001','2018-07-13 15:10:05','','SM201807122042550547654');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807131050291613067','工程人员','1','/projectStaff/projectStaff.htm','2','USYS201800000000001','2018-07-13 10:50:29','','SM201807122042550547654');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807131055567816441','重新入职','2','/reruzhi','1','USYS201800000000001','2018-07-13 10:55:56','','SM201807131050291613067');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807131056355705106','请假','2','/weekday','2','USYS201800000000001','2018-07-13 11:00:35','','SM201807131050291613067');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807131057091569975','离职','2','/quit','3','USYS201800000000001','2018-07-14 00:59:16','','SM201807131050291613067');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807131057359281356','详情','2','/detail','6','USYS201800000000001','2018-07-14 00:59:38','','SM201807131050291613067');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807131135534057732','补录银行卡','2','/addBankCard','5','USYS201800000000001','2018-07-13 11:35:53','','SM201807131050291613067');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807131136349734604','补录技能','2','/skill','4','USYS201800000000001','2018-07-14 00:59:29','','SM201807131050291613067');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807132139452983411','重新建档','2','/rejiandang','1','USYS201800000000001','2018-07-13 21:39:45','','SM201805031143039272323');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807132349023619781','请假明细','1','/staff/allStaff/leaveRecords.htm','5','USYS201800000000001','2018-07-14 00:05:07','','SM201807122042550547654');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807141823096063448','工资明细','1','/projectManage/project/salary.htm','1','USYS201800000000001','2018-07-16 14:22:30','','SM201804262009375685547');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807181056231472667','短信通知人','1','/waitList/textMessage.htm','6','USYS201800000000001','2018-07-18 10:56:23','','SM201804262009375685547');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807181117390757399','新增','2','/add','1','USYS201800000000001','2018-07-18 11:17:39','','SM201807181056231472667');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807181118341654660','修改','2','/edit','2','USYS201800000000001','2018-07-18 11:18:34','','SM201807181056231472667');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807181118519808526','删除','2','/delete','3','USYS201800000000001','2018-07-18 11:18:51','','SM201807181056231472667');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807181119116692561','详情','2','/detail','4','USYS201800000000001','2018-07-18 11:19:11','','SM201807181056231472667');

/*
-- Query: SELECT * FROM dev_xn_gchf.tsys_menu_role
LIMIT 0, 1000
-- Date: 2018-07-19 13:14
*/
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1,'SR201804261443398913168','GCHFSM201800001000000008','admin','2018-07-06 12:03:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (8,'SR201805251644020984824','SM201805061714532327913','U201805142013427676188','2018-07-06 12:03:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (9,'SR201805251644020984824','SM201805071518444813149','U201805142013427676188','2018-07-06 12:03:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1737,'RO201800000000000001','GCHFSM201800000000000000','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1738,'RO201800000000000001','GCHFSM201800001000000001','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1739,'RO201800000000000001','GCHFSM201800001000000003','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1740,'RO201800000000000001','GCHFSM201800001000000004','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1741,'RO201800000000000001','GCHFSM201800001000000007','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1742,'RO201800000000000001','GCHFSM201800001000000008','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1743,'RO201800000000000001','GCHFSM201800001000000009','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1744,'RO201800000000000001','GCHFSM201800001000000010','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1745,'RO201800000000000001','SM201804261623266254030','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1746,'RO201800000000000001','SM201804261705017439319','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1747,'RO201800000000000001','SM201804261722191103766','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1748,'RO201800000000000001','SM201804261758457556570','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1749,'RO201800000000000001','SM201805061646092985904','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1750,'RO201800000000000001','SM201805061653506988861','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1751,'RO201800000000000001','SM201805061657291081116','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1752,'RO201800000000000001','SM201805071356535937524','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1753,'RO201800000000000001','SM201805071357335216787','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1754,'RO201800000000000001','SM201805101121136563046','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1755,'RO201800000000000001','SM201805101326343014288','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1756,'RO201800000000000001','SM201805171903390774769','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1757,'RO201800000000000001','SM201805181426110139658','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1758,'RO201800000000000001','SM201806252143270574752','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1759,'RO201800000000000001','SM201807132139452983411','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1760,'RO201800000000000001','GCHFSM201800001000000002','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1761,'RO201800000000000001','GCHFSM201800001000000006','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1762,'RO201800000000000001','SM201804261613078269182','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1763,'RO201800000000000001','SM201804261722366268383','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1764,'RO201800000000000001','SM201804261759056455609','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1765,'RO201800000000000001','SM201805021608430535380','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1766,'RO201800000000000001','SM201805031143039272323','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1767,'RO201800000000000001','SM201805061642131878722','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1768,'RO201800000000000001','SM201805061647022105306','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1769,'RO201800000000000001','SM201805061648484984373','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1770,'RO201800000000000001','SM201805061649465011644','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1771,'RO201800000000000001','SM201805101326483303672','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1772,'RO201800000000000001','SM201805151541147523361','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1773,'RO201800000000000001','SM201805152234494002315','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1774,'RO201800000000000001','SM201805160550482243371','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1775,'RO201800000000000001','SM201805160557297112397','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1776,'RO201800000000000001','SM201805181426547627373','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1777,'RO201800000000000001','SM201804261614002237155','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1778,'RO201800000000000001','SM201804261800054386747','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1779,'RO201800000000000001','SM201805021609039267284','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1780,'RO201800000000000001','SM201805061647325709749','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1781,'RO201800000000000001','GCHFSM201800001000000011','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1782,'RO201800000000000001','SM201804261722548756668','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1783,'RO201800000000000001','SM201804261800542746171','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1784,'RO201800000000000001','SM201804262112397419688','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1785,'RO201800000000000001','SM201804261921017207214','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1786,'RO201800000000000001','SM201805041411074644391','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1787,'RO201800000000000001','SM201805031342142928555','USYS201800000000001','2018-07-14 20:09:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1788,'RO201800000000000003','GCHFSM201800000000000000','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1789,'RO201800000000000003','SM201804262121064388530','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1790,'RO201800000000000003','SM201804271923251312840','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1791,'RO201800000000000003','SM201804291425230875702','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1792,'RO201800000000000003','SM201805021513358511912','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1793,'RO201800000000000003','SM201805021608214954446','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1794,'RO201800000000000003','SM201805031622490571847','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1795,'RO201800000000000003','SM201805041050394705600','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1796,'RO201800000000000003','SM201805071518444813149','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1797,'RO201800000000000003','SM201805101326343014288','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1798,'RO201800000000000003','SM201805122203323176704','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1799,'RO201800000000000003','SM201807122039242882417','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1800,'RO201800000000000003','SM201807122044534007113','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1801,'RO201800000000000003','SM201807131055567816441','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1802,'RO201800000000000003','SM201807141831032767681','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1803,'RO201800000000000003','SM201805122246258158393','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1804,'RO201800000000000003','SM201804271918126145662','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1805,'RO201800000000000003','SM201804291350415659355','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1806,'RO201800000000000003','SM201804291425476906723','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1807,'RO201800000000000003','SM201805021608430535380','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1808,'RO201800000000000003','SM201805021637202209454','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1809,'RO201800000000000003','SM201805022048193922921','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1810,'RO201800000000000003','SM201805031623038864903','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1811,'RO201800000000000003','SM201805101326483303672','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1812,'RO201800000000000003','SM201805251516327463058','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1813,'RO201800000000000003','SM201806252141430605486','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1814,'RO201800000000000003','SM201807131050291613067','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1815,'RO201800000000000003','SM201807131056355705106','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1816,'RO201800000000000003','SM201804262037546867086','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1817,'RO201800000000000003','SM201804262118246417123','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1818,'RO201800000000000003','SM201805021609039267284','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1819,'RO201800000000000003','SM201805021637409537163','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1820,'RO201800000000000003','SM201805022048376225056','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1821,'RO201800000000000003','SM201805061739537493514','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1822,'RO201800000000000003','SM201805122247268142994','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1823,'RO201800000000000003','SM201807122042550547654','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1824,'RO201800000000000003','SM201807131057091569975','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1825,'RO201800000000000003','SM201805142231375155440','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1826,'RO201800000000000003','SM201805251354520521925','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1827,'RO201800000000000003','SM201805261621476424511','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1828,'RO201800000000000003','SM201807131136349734604','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1829,'RO201800000000000003','SM201804262009375685547','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1830,'RO201800000000000003','SM201805122247567061152','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1831,'RO201800000000000003','SM201807131135534057732','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1832,'RO201800000000000003','SM201807132349023619781','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1833,'RO201800000000000003','SM201805032049250384279','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1834,'RO201800000000000003','SM201805061714532327913','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1835,'RO201800000000000003','SM201805122248245127470','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1836,'RO201800000000000003','SM201807131057359281356','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1837,'RO201800000000000003','SM201805122249222019735','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1838,'RO201800000000000003','SM201805142040150952935','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1839,'RO201800000000000003','SM201805122244282147848','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1840,'RO201800000000000003','SM201807141823096063448','USYS201800000000001','2018-07-16 13:49:42',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1849,'RO201800000000000002','GCHFSM201800000000000000','USYS201800000000001','2018-07-18 11:19:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1850,'RO201800000000000002','SM201805021701134437182','USYS201800000000001','2018-07-18 11:19:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1851,'RO201800000000000002','SM201805041050394705600','USYS201800000000001','2018-07-18 11:19:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1852,'RO201800000000000002','SM201804262037078836469','USYS201800000000001','2018-07-18 11:19:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1853,'RO201800000000000002','SM201806270003243064218','USYS201800000000001','2018-07-18 11:19:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1854,'RO201800000000000002','SM201804262009375685547','USYS201800000000001','2018-07-18 11:19:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1855,'RO201800000000000002','SM201804262037546867086','USYS201800000000001','2018-07-18 11:19:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1856,'RO201800000000000002','SM201807181056231472667','USYS201800000000001','2018-07-18 11:19:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1857,'RO201800000000000002','SM201807181117390757399','USYS201800000000001','2018-07-18 11:19:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1858,'RO201800000000000002','SM201807181118341654660','USYS201800000000001','2018-07-18 11:19:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1859,'RO201800000000000002','SM201807181118519808526','USYS201800000000001','2018-07-18 11:19:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1860,'RO201800000000000002','SM201807181119116692561','USYS201800000000001','2018-07-18 11:19:29',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1861,'RO201800000000000004','GCHFSM201800000000000000','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1862,'RO201800000000000004','SM201804271923251312840','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1863,'RO201800000000000004','SM201805021701134437182','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1864,'RO201800000000000004','SM201805041050394705600','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1865,'RO201800000000000004','SM201805071518444813149','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1866,'RO201800000000000004','SM201807122039242882417','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1867,'RO201800000000000004','SM201805122246258158393','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1868,'RO201800000000000004','SM201807091728303553393','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1869,'RO201800000000000004','SM201804262121064388530','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1870,'RO201800000000000004','SM201804271918126145662','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1871,'RO201800000000000004','SM201804291350415659355','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1872,'RO201800000000000004','SM201804291425476906723','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1873,'RO201800000000000004','SM201805031143039272323','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1874,'RO201800000000000004','SM201805031146406461279','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1875,'RO201800000000000004','SM201805031623038864903','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1876,'RO201800000000000004','SM201805101326483303672','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1877,'RO201800000000000004','SM201805251516327463058','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1878,'RO201800000000000004','SM201807131050291613067','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1879,'RO201800000000000004','SM201804262037078836469','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1880,'RO201800000000000004','SM201804262118246417123','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1881,'RO201800000000000004','SM201805021609039267284','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1882,'RO201800000000000004','SM201805021637409537163','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1883,'RO201800000000000004','SM201805061739537493514','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1884,'RO201800000000000004','SM201807122042550547654','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1885,'RO201800000000000004','SM201804262112397419688','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1886,'RO201800000000000004','SM201805231252504327898','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1887,'RO201800000000000004','SM201805251354520521925','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1888,'RO201800000000000004','SM201805261621476424511','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1889,'RO201800000000000004','SM201807081447207731033','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1890,'RO201800000000000004','SM201804262009375685547','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1891,'RO201800000000000004','SM201804262037546867086','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1892,'RO201800000000000004','SM201805041411074644391','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1893,'RO201800000000000004','SM201807132349023619781','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1894,'RO201800000000000004','SM201805061714532327913','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1895,'RO201800000000000004','SM201807131057359281356','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1896,'RO201800000000000004','SM201805031342142928555','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1897,'RO201800000000000004','SM201805122244282147848','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1898,'RO201800000000000004','SM201807181056231472667','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1899,'RO201800000000000004','SM201807181117390757399','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1900,'RO201800000000000004','SM201807181118341654660','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1901,'RO201800000000000004','SM201807181118519808526','USYS201800000000001','2018-07-18 16:55:50',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1902,'RO201800000000000004','SM201807181119116692561','USYS201800000000001','2018-07-18 16:55:50',NULL);

###V1.4.0
/*
-- Query: SELECT * FROM dev_xn_gchf.tsys_menu where role_type = 'P'
LIMIT 0, 1000
-- Date: 2018-07-20 13:48
-- P
*/
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('GCHFPSM201800000000000000','根目录','1','P','#','1','admin','2018-08-20 09:09:17','','');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201030483125593','系统管理','1','P','#','1','USYS201800000000001','2018-07-20 10:30:48','','GCHFPSM201800000000000000');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201031386412313','运维管理','1','P','#','1','USYS201800000000001','2018-07-20 10:31:38','','SM201807201030483125593');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201031554085068','运营管理','1','P','#','2','USYS201800000000001','2018-07-20 10:31:55','','SM201807201030483125593');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201035525556549','菜单管理','1','P','/system/menu.htm','1','USYS201800000000001','2018-07-20 10:35:52','','SM201807201031386412313');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201036520116270','系统参数管理','1','P','/system/sysPara.htm','2','USYS201800000000001','2018-07-20 10:36:52','','SM201807201031386412313');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201037279421821','数据字典管理','1','P','/system/dataDict.htm','3','USYS201800000000001','2018-07-20 10:37:27','','SM201807201031386412313');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201038045508404','角色管理','1','P','/system/role.htm','1','USYS201800000000001','2018-07-20 10:38:04','','SM201807201031554085068');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201038358168249','用户管理','1','P','/system/user.htm','2','USYS201800000000001','2018-07-20 10:38:35','','SM201807201031554085068');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201124298734547','修改','2','P','/edit','2','USYS201800000000001','2018-07-20 11:24:29','','SM201807201035525556549');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201124447788270','删除','2','P','/delete','3','USYS201800000000001','2018-07-20 11:24:44','','SM201807201035525556549');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201125161861248','修改','2','P','/edit','1','USYS201800000000001','2018-07-20 11:25:16','','SM201807201036520116270');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201125421699687','修改','2','P','/edit','1','USYS201800000000001','2018-07-20 11:25:42','','SM201807201037279421821');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201126211661803','新增','2','P','/add','1','USYS201800000000001','2018-07-20 11:26:21','','SM201807201038045508404');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201126371934570','修改','2','P','/edit','2','USYS201800000000001','2018-07-20 11:26:37','','SM201807201038045508404');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201127025377983','分配菜单','2','P','/change','3','USYS201800000000001','2018-07-20 11:27:02','','SM201807201038045508404');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201127186657558','删除','2','P','/delete','4','USYS201800000000001','2018-07-20 11:27:18','','SM201807201038045508404');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201129177262546','新增','2','P','/add','1','USYS201800000000001','2018-07-20 11:29:17','','SM201807201038358168249');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201129177262547','新增','2','P','/add','1','USYS201800000000001','2018-07-20 11:29:17','','SM201807201035525556549');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201129373238575','重置密码','2','P','/reset','2','USYS201800000000001','2018-07-20 11:29:37','','SM201807201038358168249');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201130034336159','注销/激活','2','P','/rock','3','USYS201800000000001','2018-07-20 11:35:06','','SM201807201038358168249');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201130316042069','设置角色','2','P','/assign','4','USYS201800000000001','2018-07-20 11:35:15','','SM201807201038358168249');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201131011696446','修改手机号','2','P','/changeMobile','5','USYS201800000000001','2018-07-20 11:34:55','','SM201807201038358168249');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201131571203094','员工管理','1','P','#','2','USYS201800000000001','2018-07-20 11:31:57','','GCHFPSM201800000000000000');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201240189699293','人员建档','1','P','/staff/jiandang.htm','1','USYS201800000000001','2018-07-20 12:40:18','','SM201807201131571203094');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201240487754033','员工查看','1','P','/staff/allStaff.htm','2','USYS201800000000001','2018-07-20 12:40:48','','SM201807201131571203094');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201345378904554','重新建档','2','P','/rejiandang','1','USYS201800000000001','2018-07-20 13:45:37','','SM201807201240487754033');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201346222811520','补录技能','2','P','/skill','2','USYS201800000000001','2018-07-20 13:46:22','','SM201807201240487754033');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201346521318233','工作履历','2','P','/history','3','USYS201800000000001','2018-07-20 13:46:52','','SM201807201240487754033');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807201347158228349','详情','2','P','/detail','4','USYS201800000000001','2018-07-20 13:47:15','','SM201807201240487754033');

INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2012,'RO201800000000000001','GCHFPSM201800000000000000','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2013,'RO201800000000000001','SM201807201030483125593','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2014,'RO201800000000000001','SM201807201031386412313','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2015,'RO201800000000000001','SM201807201035525556549','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2016,'RO201800000000000001','SM201807201038045508404','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2017,'RO201800000000000001','SM201807201125161861248','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2018,'RO201800000000000001','SM201807201125421699687','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2019,'RO201800000000000001','SM201807201126211661803','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2020,'RO201800000000000001','SM201807201129177262546','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2021,'RO201800000000000001','SM201807201129177262547','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2022,'RO201800000000000001','SM201807201240189699293','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2023,'RO201800000000000001','SM201807201031554085068','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2024,'RO201800000000000001','SM201807201036520116270','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2025,'RO201800000000000001','SM201807201038358168249','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2026,'RO201800000000000001','SM201807201124298734547','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2027,'RO201800000000000001','SM201807201126371934570','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2028,'RO201800000000000001','SM201807201129373238575','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2029,'RO201800000000000001','SM201807201131571203094','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2030,'RO201800000000000001','SM201807201037279421821','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2031,'RO201800000000000001','SM201807201124447788270','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2032,'RO201800000000000001','SM201807201127025377983','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2033,'RO201800000000000001','SM201807201130034336159','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2034,'RO201800000000000001','SM201807201127186657558','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2035,'RO201800000000000001','SM201807201130316042069','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2036,'RO201800000000000001','SM201807201131011696446','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2037,'RO201800000000000001','SM201807201240487754033','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2038,'RO201800000000000001','SM201807201345378904554','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2039,'RO201800000000000001','SM201807201346222811520','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2040,'RO201800000000000001','SM201807201346521318233','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2041,'RO201800000000000001','SM201807201347158228349','USYS201800000000001','2018-07-20 13:47:25',NULL);


/*
-- Query: SELECT * FROM dev_xn_gchf.tsys_menu where role_type = 'B'
LIMIT 0, 1000
-- Date: 2018-07-24 15:41
-- B
*/
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('GCHFBSM201800000000000000','根目录','1','B','#','1','admin','2018-08-20 09:09:17','','');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807231030483125593','系统管理','1','B','#','1','USYS201800000000001','2018-07-20 10:30:48','','GCHFBSM201800000000000000');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807231031386412313','运维管理','1','B','#','1','USYS201800000000001','2018-07-20 10:31:38','','SM201807231030483125593');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807231035525556549','菜单管理','1','B','/system/menu.htm','1','USYS201800000000001','2018-07-20 10:35:52','','SM201807231031386412313');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807231129177262547','新增','2','B','/add','1','USYS201800000000001','2018-07-20 11:29:17','','SM201807231035525556549');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241335405222279','运营管理','1','B','#','2','U201807201448369903584','2018-07-24 13:35:40','','SM201807231030483125593');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241336205099958','角色管理','1','B','/system/role.htm','1','U201807201448369903584','2018-07-24 13:36:20','','SM201807241335405222279');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241337175287731','开设账号','1','B','#','2','U201807201448369903584','2018-07-24 13:37:17','','GCHFBSM201800000000000000');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241338036803470','银行单位','1','B','#','1','U201807201448369903584','2018-07-24 13:38:03','','SM201807241337175287731');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241338459594318','银行管理','1','B','/newId/operation.htm','1','U201807201448369903584','2018-07-24 13:38:45','','SM201807241338036803470');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241339204132943','银行用户','1','B','/newId/bank.htm','2','U201807201448369903584','2018-07-24 13:39:20','','SM201807241338036803470');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241339528185483','修改','2','B','/edit','2','U201807201448369903584','2018-07-24 13:39:52','','SM201807231035525556549');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241340146487913','删除','2','B','/delete','3','U201807201448369903584','2018-07-24 13:40:14','','SM201807231035525556549');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241342024542245','分配菜单','2','B','/change','3','U201807201448369903584','2018-07-24 13:50:06','','SM201807241336205099958');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241342422409334','新增','2','B','/add','1','U201807201448369903584','2018-07-24 13:42:42','','SM201807241336205099958');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241342596513263','修改','2','B','/edit','2','U201807201448369903584','2018-07-24 13:42:59','','SM201807241336205099958');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241343221479328','删除','2','B','/delete','4','U201807201448369903584','2018-07-24 13:43:22','','SM201807241336205099958');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241350541879018','新增银行','2','B','/add','1','U201807201448369903584','2018-07-24 13:50:54','','SM201807241338459594318');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241351142238690','修改银行','2','B','/edit','2','U201807201448369903584','2018-07-24 13:51:14','','SM201807241338459594318');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241351477248259','新增','2','B','/add','1','U201807201448369903584','2018-07-24 13:51:47','','SM201807241339204132943');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241352061201385','详情','2','B','/detail','2','U201807201448369903584','2018-07-24 13:52:06','','SM201807241339204132943');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241354444837809','工资核发','1','B','#','3','U201807201448369903584','2018-07-24 13:54:44','','GCHFBSM201800000000000000');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241355087008815','工资待发','1','B','/waitList/postRequest.htm','1','U201807201448369903584','2018-07-24 13:55:08','','SM201807241354444837809');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241355483004750','待反馈','1','B','/waitList/feedback.htm','2','U201807201448369903584','2018-07-24 13:58:27','','SM201807241354444837809');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241356215805380','历史请求','1','B','/waitList/alreadyQuest.htm','3','U201807201448369903584','2018-07-24 13:58:21','','SM201807241354444837809');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241356471961442','短信通知人','1','B','/waitList/textMessage.htm','4','U201807201448369903584','2018-07-24 13:58:15','','SM201807241354444837809');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241359246395059','详情','2','B','/detail','1','U201807201448369903584','2018-07-24 13:59:24','','SM201807241356215805380');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241359560335048','新增','2','B','/add','1','U201807201448369903584','2018-07-24 13:59:56','','SM201807241356471961442');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241400081795457','修改','2','B','/edit','2','U201807201448369903584','2018-07-24 14:00:08','','SM201807241356471961442');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241400236309411','删除','2','B','/delete','3','U201807201448369903584','2018-07-24 14:00:23','','SM201807241356471961442');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241400373916195','详情','2','B','/detail','4','U201807201448369903584','2018-07-24 14:00:37','','SM201807241356471961442');

INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2093,'RO201800000000000002','GCHFBSM201800000000000000','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2094,'RO201800000000000002','SM201807231030483125593','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2095,'RO201800000000000002','SM201807231031386412313','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2096,'RO201800000000000002','SM201807231035525556549','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2097,'RO201800000000000002','SM201807231129177262547','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2098,'RO201800000000000002','SM201807241339528185483','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2099,'RO201800000000000002','SM201807241340146487913','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2100,'RO201800000000000002','SM201807241335405222279','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2101,'RO201800000000000002','SM201807241336205099958','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2102,'RO201800000000000002','SM201807241342024542245','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2103,'RO201800000000000002','SM201807241342422409334','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2104,'RO201800000000000002','SM201807241342596513263','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2105,'RO201800000000000002','SM201807241343221479328','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2106,'RO201800000000000002','SM201807241337175287731','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2107,'RO201800000000000002','SM201807241338036803470','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2108,'RO201800000000000002','SM201807241338459594318','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2109,'RO201800000000000002','SM201807241350541879018','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2110,'RO201800000000000002','SM201807241351142238690','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2111,'RO201800000000000002','SM201807241339204132943','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2112,'RO201800000000000002','SM201807241351477248259','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2113,'RO201800000000000002','SM201807241352061201385','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2114,'RO201800000000000002','SM201807241354444837809','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2115,'RO201800000000000002','SM201807241355087008815','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2116,'RO201800000000000002','SM201807241355483004750','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2117,'RO201800000000000002','SM201807241356215805380','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2118,'RO201800000000000002','SM201807241359246395059','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2119,'RO201800000000000002','SM201807241356471961442','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2120,'RO201800000000000002','SM201807241359560335048','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2121,'RO201800000000000002','SM201807241400081795457','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2122,'RO201800000000000002','SM201807241400236309411','U201807201448369903584','2018-07-24 14:00:48',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2123,'RO201800000000000002','SM201807241400373916195','U201807201448369903584','2018-07-24 14:00:48',NULL);


/*
-- Query: SELECT * FROM dev_xn_gchf.tsys_menu where role_type = 'B'
LIMIT 0, 1000
-- Date: 2018-07-20 13:48
-- O
*/
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('GCHFOSM201800000000000000','根目录','1','O','#','1','admin','2018-08-20 09:09:17','','');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241030483125593','系统管理','1','O','#','1','USYS201800000000001','2018-07-20 10:30:48','','GCHFOSM201800000000000000');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241031386412313','运维管理','1','O','#','1','USYS201800000000001','2018-07-20 10:31:38','','SM201807241030483125593');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241035525556549','菜单管理','1','O','/system/menu.htm','1','USYS201800000000001','2018-07-20 10:35:52','','SM201807241031386412313');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807241129177262547','新增','2','O','/add','1','USYS201800000000001','2018-07-20 11:29:17','','SM201807241035525556549');

INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2124,'RO201800000000000003','GCHFOSM201800000000000000','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2125,'RO201800000000000003','SM201807241030483125593','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2126,'RO201800000000000003','SM201807241031386412313','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2127,'RO201800000000000003','SM201807241035525556549','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2128,'RO201800000000000003','SM201807241129177262547','USYS201800000000001','2018-07-20 13:47:25',NULL);

/*
-- Query: SELECT * FROM dev_xn_gchf.tsys_menu where role_type = 'B'
LIMIT 0, 1000
-- Date: 2018-07-20 13:48
-- S
*/
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('GCHFSSM201800000000000000','根目录','1','S','#','1','admin','2018-08-20 09:09:17','','');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807251030483125593','系统管理','1','S','#','1','USYS201800000000001','2018-07-20 10:30:48','','GCHFSSM201800000000000000');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807251031386412313','运维管理','1','S','#','1','USYS201800000000001','2018-07-20 10:31:38','','SM201807251030483125593');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807251035525556549','菜单管理','1','S','/system/menu.htm','1','USYS201800000000001','2018-07-20 10:35:52','','SM201807251031386412313');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`role_type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201807251129177262547','新增','2','S','/add','1','USYS201800000000001','2018-07-20 11:29:17','','SM201807251035525556549');

INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2129,'RO201800000000000004','GCHFSSM201800000000000000','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2130,'RO201800000000000004','SM201807251030483125593','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2131,'RO201800000000000004','SM201807251031386412313','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2132,'RO201800000000000004','SM201807251035525556549','USYS201800000000001','2018-07-20 13:47:25',NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (2133,'RO201800000000000004','SM201807251129177262547','USYS201800000000001','2018-07-20 13:47:25',NULL);
