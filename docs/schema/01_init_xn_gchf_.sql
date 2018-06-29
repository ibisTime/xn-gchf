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
INSERT INTO `tsys_role` (`code`,`type`,`name`,`updater`,`update_datetime`,`remark`) VALUES ('RO201800000000000001','P','超级管理员','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_role` (`code`,`type`,`name`,`updater`,`update_datetime`,`remark`) VALUES ('RO201800000000000002','B','银行端','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_role` (`code`,`type`,`name`,`updater`,`update_datetime`,`remark`) VALUES ('RO201800000000000003','O','业主端','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_role` (`code`,`type`,`name`,`updater`,`update_datetime`,`remark`) VALUES ('RO201800000000000004','S','监管端','USYS201800000000001',now(),NULL);

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
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (11,'1','project_status','0','待提请审核','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (12,'1','project_status','1','待审核','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (13,'1','project_status','2','审核未通过','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (14,'1','project_status','5','项目结束','admin',now(),NULL);
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
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (27,'0',NULL,'salary_status','工资条状态','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (28,'1','salary_status','0','待人工复核','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (29,'1','salary_status','1','已审核待提交','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (30,'1','salary_status','2','已提交待发放','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (31,'1','salary_status','3','正常发放','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (32,'1','salary_status','4','部分发放','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (33,'1','salary_status','5','已补发','admin',now(),NULL);
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
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (49,'1','project_status','3','在建','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (50,'1','project_status','4','停工','admin',now(),NULL);
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES (51,'1','message_status','3','已处理','admin',now(),NULL);
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
-- Date: 2018-06-29 17:50
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
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804262037078836469','工资待发','1','/waitList/postRequest.htm','2','USYS201800000000001','2018-05-18 10:16:07','','SM201804262009375685547');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804262037546867086','历史请求','1','/waitList/alreadyQuest.htm','3','USYS201800000000001','2018-06-28 00:05:42','','SM201804262009375685547');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804262110196148832','公司管理','1','#','3','admin',now(),'','GCHFSM201800001000000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804262111286723837','公司结构','1','/newProj/companyConstruct.htm','1','admin',now(),'','SM201804262110196148832');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804262112397419688','员工管理','1','#','4','admin',now(),'','GCHFSM201800000000000000');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804262118246417123','承包商合同','1','/hetong/chengbaoshang.htm','4','USYS201800000000001',now(),'','SM201804271918126145662');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804262121064388530','待发请求','1','/daifa/daifa.htm','1','USYS201800000000001',now(),'','SM201804262009375685547');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804271918126145662','项目管理','1','#','3','USYS201800000000003',now(),'','GCHFSM201800000000000000');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804271923251312840','项目管理','1','/projectManage/project.htm','1','USYS201800000000001',now(),'','SM201804271918126145662');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804291350415659355','账户信息','1','/yewuManage/account.htm','2','USYS201800000000001',now(),'','SM201804271918126145662');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804291425230875702','修改','2','/edit','1','admin',now(),'','SM201804291350415659355');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804291425476906723','详情','2','/detail','2','admin',now(),'','SM201804291350415659355');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201804291522572535111','设置部门','2','/setBumen','7','admin',now(),'','GCHFSM201800001000000008');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805021513358511912','合同录入','2','/add','1','admin',now(),'','SM201804262118246417123');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805021608214954446','录入进度','2','/add','1','18870421644',now(),'','SM201804262119227928535');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805021608430535380','修改进度','2','/edit','2','18870421644',now(),'','SM201804262119227928535');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805021609039267284','详情','2','/detail','3','18870421644',now(),'','SM201804262119227928535');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805021637202209454','修改','2','/edit','2','18870421644',now(),'','SM201804262118246417123');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805021637409537163','详情','2','/detail','3','18870421644',now(),'','SM201804262118246417123');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805021701134437182','详情','2','/detail','1','admin',now(),'','SM201804262037078836469');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805022048193922921','修改','2','/edit','2','18870421644',now(),'','SM201804262117511207724');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805022048376225056','详情','2','/detail','3','18870421644',now(),'','SM201804262117511207724');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805031143039272323','员工查看','1','/staff/allStaff.htm','1','USYS201800000000003','2018-05-06 17:06:30','','SM201804262112397419688');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805031146406461279','下载','2','/onDownLoad','2','admin',now(),'','SM201804262037078836469');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805031341536277279','修改','2','/edit','6','USYS201800000000001',now(),'','SM201805031143039272323');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805031342142928555','详情','2','/detail','7','USYS201800000000001',now(),'','SM201805031143039272323');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805031622490571847','发送','2','/send','1','USYS201800000000003',now(),'','SM201804262121064388530');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805031623038864903','详情','2','/detail','2','USYS201800000000003',now(),'','SM201804262121064388530');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805032049250384279','工资卡管理','1','/staff/bankCard.htm','2','USYS201800000000001','2018-05-23 12:53:52','','SM201804262112397419688');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805041050394705600','详情','2','/detail','1','admin',now(),'','SM201804262037546867086');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805041411074644391','工作履历','2','/history','5','USYS201800000000001','2018-05-26 22:17:31','','SM201805031143039272323');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805061642131878722','开设账号','1','#','2','admin',now(),'','GCHFSM201800000000000000');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805061646092985904','银行单位','1','www.baidu.com','1','USYS201800000000001',now(),'','SM201805061642131878722');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805061647022105306','业主单位','1','www.baidu.com','2','USYS201800000000001',now(),'','SM201805061642131878722');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805061647325709749','监管单位','1','www.baidu.com','3','admin',now(),'','SM201805061642131878722');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805061648484984373','银行用户','1','/newId/bank.htm','2','USYS201800000000001',now(),'','SM201805061646092985904');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805061649465011644','业主用户','1','/newId/yezhu.htm','2','USYS201800000000001',now(),'','SM201805061647022105306');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805061653506988861','监管用户','1','/newId/supervise.htm','1','admin',now(),'','SM201805061647325709749');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805061657291081116','新增','2','/add','1','admin',now(),'','SM201805061648484984373');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805061714532327913','事件处理','1','#','6','admin',now(),'','GCHFSM201800000000000000');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805061739537493514','用工合同','1','/hetong/wugong.htm','3','admin',now(),'','SM201804271918126145662');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805071356535937524','新增','2','/add','1','admin',now(),'','SM201805061649465011644');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805071357335216787','新增','2','/add','1','admin',now(),'','SM201805061653506988861');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805071518444813149','异常查看','1','/staff/allStafferror.htm','1','USYS201800000000001','2018-05-18 11:13:24','','SM201805061714532327913');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805101121136563046','公司结构','1','/newId/companyConstruct.htm','1','admin',now(),'','SM201805061647022105306');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805101326343014288','修改','2','/edit','1','admin',now(),'','SM201805032049250384279');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805101326483303672','详情','2','/detail','2','admin',now(),'','SM201805032049250384279');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805101339482085520','设置部门','2','/setBumen1','2','admin',now(),'','SM201805061649465011644');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805122203323176704','新增项目','2','/addProject','1','admin',now(),'','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805122243231093581','统计信息','2','/Statistics','15','USYS201800000000001',now(),'','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805122243558813635','办理入职','2','/addWorkers','9','USYS201800000000001',now(),'','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805122244282147848','查看考勤','2','/attendance','10','USYS201800000000001',now(),'','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805122244587461594','工资明细','2','/wages','11','USYS201800000000001',now(),'','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805122246258158393','项目详情','2','/proDetail','14','USYS201800000000001',now(),'','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805122247268142994','修改项目','2','/editPro','2','USYS201800000000001',now(),'','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805122247567061152','审核项目','2','/checkPro','4','USYS201800000000001',now(),'','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805122248245127470','项目停工','2','/overPro','6','USYS201800000000001',now(),'','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805122249222019735','重新开工','2','/aWork','7','USYS201800000000001',now(),'','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805142040150952935','项目结束','2','/overTime','8','USYS201800000000001',now(),'','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805142231375155440','提请审核','2','/tiqingshenhe','3','USYS201800000000001',now(),'','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805151541147523361','补录员工信息','2','/detailAdd','2','USYS201800000000001',now(),'','SM201805031143039272323');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805152234494002315','详情','2','/detail','2','USYS201800000000001',now(),'','SM201805061648484984373');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805160550482243371','详情','2','/detail','2','USYS201800000000001',now(),'','SM201805061649465011644');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805160557297112397','详情','2','/detail','2','USYS201800000000001',now(),'','SM201805061653506988861');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805171454281342035','工程进度','2','/progress','5','USYS201800000000001',now(),'','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805171903390774769','银行管理','1','/newId/operation.htm','1','USYS201800000000001',now(),'','SM201805061646092985904');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805181426110139658','新增银行','2','/add','1','USYS201800000000001',now(),'','SM201805171903390774769');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805181426547627373','修改银行','2','/edit','2','USYS201800000000001',now(),'','SM201805171903390774769');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805211541378324831','新增进度','2','/addjindu','16','USYS201800000000001',now(),'','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805231252504327898','身份证号查询','1','/staff/idCardQuery.htm','3','USYS201800000000001',now(),'','SM201804262112397419688');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805231511426547519','查看考勤','1','/newProj/kaoqin.htm','5','USYS201800000000001',now(),'','SM201804262009375685547');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805251354520521925','查看考勤','1','/newProj/kaoqin.htm','5','USYS201800000000001',now(),'','SM201804271918126145662');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805251516327463058','历史事件','1','/staff/allStafferrHistory.htm','2','USYS201800000000001',now(),'','SM201805061714532327913');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805251544130622832','转换为正常','2','/Transformation','2','USYS201800000000001',now(),'','SM201805071518444813149');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805261001490495192','请假','2','/weekday','3','USYS201800000000001',now(),'','SM201805031143039272323');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805261021084049823','离职申请','2','/quit','17','USYS201800000000001','2018-06-29 16:12:33','','SM201805031143039272323');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805261621476424511','工程进度','1','/hetong/jindu.htm','6','USYS201800000000001',now(),'','SM201804271918126145662');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201805261758591375743','办理入职','2','/addWorkers','1','USYS201800000000001',now(),'','SM201805031143039272323');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201806252141430605486','部门管理','2','/addBumen','19','USYS201800000000001',now(),'','SM201804271923251312840');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201806252143270574752','人员建档','1','/staff/jiandang.htm','1','USYS201800000000001',now(),'','SM201804262112397419688');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) VALUES ('SM201806270003243064218','待反馈','1','/waitList/feedback.htm','2','USYS201800000000001',now(),'','SM201804262009375685547');

/*
-- Query: SELECT * FROM dev_xn_gchf.tsys_menu_role
LIMIT 0, 1000
-- Date: 2018-06-29 17:52
*/
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (1,'SR201804261443398913168','GCHFSM201800001000000008','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (8,'SR201805251644020984824','SM201805061714532327913','U201805142013427676188',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (9,'SR201805251644020984824','SM201805071518444813149','U201805142013427676188',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (145,'RO201800000000000004','GCHFSM201800000000000000','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (146,'RO201800000000000004','GCHFSM201800001000000001','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (147,'RO201800000000000004','GCHFSM201800001000000007','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (148,'RO201800000000000004','GCHFSM201800001000000008','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (149,'RO201800000000000004','SM201804261722191103766','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (150,'RO201800000000000004','SM201804261758457556570','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (151,'RO201800000000000004','SM201804262111286723837','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (152,'RO201800000000000004','SM201804262121064388530','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (153,'RO201800000000000004','SM201804271923251312840','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (154,'RO201800000000000004','SM201805021701134437182','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (155,'RO201800000000000004','SM201805031143039272323','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (156,'RO201800000000000004','SM201805041050394705600','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (157,'RO201800000000000004','SM201805071518444813149','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (158,'RO201800000000000004','SM201805122244282147848','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (159,'RO201800000000000004','SM201805122244587461594','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (160,'RO201800000000000004','SM201805122246258158393','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (161,'RO201800000000000004','GCHFSM201800001000000006','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (162,'RO201800000000000004','SM201804261722366268383','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (163,'RO201800000000000004','SM201804261759056455609','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (164,'RO201800000000000004','SM201804262037078836469','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (165,'RO201800000000000004','SM201804262037546867086','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (166,'RO201800000000000004','SM201804291350415659355','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (167,'RO201800000000000004','SM201804291425476906723','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (168,'RO201800000000000004','SM201805031146406461279','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (169,'RO201800000000000004','SM201805031623038864903','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (170,'RO201800000000000004','SM201805032049250384279','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (171,'RO201800000000000004','SM201805101326483303672','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (172,'RO201800000000000004','SM201805251516327463058','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (173,'RO201800000000000004','SM201804261800054386747','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (174,'RO201800000000000004','SM201804262110196148832','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (175,'RO201800000000000004','SM201804271918126145662','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (176,'RO201800000000000004','SM201805021609039267284','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (177,'RO201800000000000004','SM201805021637409537163','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (178,'RO201800000000000004','SM201805031342142928555','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (179,'RO201800000000000004','SM201805061739537493514','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (180,'RO201800000000000004','SM201805231252504327898','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (181,'RO201800000000000004','GCHFSM201800001000000011','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (182,'RO201800000000000004','SM201804261722548756668','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (183,'RO201800000000000004','SM201804261800542746171','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (184,'RO201800000000000004','SM201804262112397419688','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (185,'RO201800000000000004','SM201804262118246417123','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (186,'RO201800000000000004','SM201805041411074644391','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (187,'RO201800000000000004','SM201804261921017207214','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (188,'RO201800000000000004','SM201804262009375685547','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (189,'RO201800000000000004','SM201805251354520521925','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (190,'RO201800000000000004','SM201805061714532327913','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (191,'RO201800000000000004','SM201805261621476424511','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (256,'RO201800000000000002','GCHFSM201800000000000000','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (257,'RO201800000000000002','SM201805021701134437182','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (258,'RO201800000000000002','SM201805041050394705600','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (259,'RO201800000000000002','SM201804262037078836469','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (260,'RO201800000000000002','SM201804262037546867086','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (261,'RO201800000000000002','SM201804262009375685547','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (262,'RO201800000000000002','SM201806270003243064218','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (263,'RO201800000000000001','GCHFSM201800000000000000','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (264,'RO201800000000000001','GCHFSM201800001000000001','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (265,'RO201800000000000001','GCHFSM201800001000000003','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (266,'RO201800000000000001','GCHFSM201800001000000004','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (267,'RO201800000000000001','GCHFSM201800001000000007','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (268,'RO201800000000000001','GCHFSM201800001000000008','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (269,'RO201800000000000001','GCHFSM201800001000000009','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (270,'RO201800000000000001','GCHFSM201800001000000010','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (271,'RO201800000000000001','SM201804261623266254030','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (272,'RO201800000000000001','SM201804261705017439319','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (273,'RO201800000000000001','SM201804261722191103766','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (274,'RO201800000000000001','SM201804261758457556570','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (275,'RO201800000000000001','SM201805061646092985904','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (276,'RO201800000000000001','SM201805061653506988861','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (277,'RO201800000000000001','SM201805061657291081116','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (278,'RO201800000000000001','SM201805071356535937524','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (279,'RO201800000000000001','SM201805071357335216787','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (280,'RO201800000000000001','SM201805101121136563046','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (281,'RO201800000000000001','SM201805171903390774769','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (282,'RO201800000000000001','SM201805181426110139658','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (283,'RO201800000000000001','GCHFSM201800001000000002','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (284,'RO201800000000000001','GCHFSM201800001000000006','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (285,'RO201800000000000001','SM201804261613078269182','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (286,'RO201800000000000001','SM201804261722366268383','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (287,'RO201800000000000001','SM201804261759056455609','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (288,'RO201800000000000001','SM201805021608430535380','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (289,'RO201800000000000001','SM201805061642131878722','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (290,'RO201800000000000001','SM201805061647022105306','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (291,'RO201800000000000001','SM201805061648484984373','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (292,'RO201800000000000001','SM201805061649465011644','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (293,'RO201800000000000001','SM201805152234494002315','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (294,'RO201800000000000001','SM201805160550482243371','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (295,'RO201800000000000001','SM201805160557297112397','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (296,'RO201800000000000001','SM201805181426547627373','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (297,'RO201800000000000001','SM201804261614002237155','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (298,'RO201800000000000001','SM201804261800054386747','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (299,'RO201800000000000001','SM201805021609039267284','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (300,'RO201800000000000001','SM201805061647325709749','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (301,'RO201800000000000001','GCHFSM201800001000000011','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (302,'RO201800000000000001','SM201804261722548756668','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (303,'RO201800000000000001','SM201804261800542746171','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (304,'RO201800000000000001','SM201804261921017207214','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (417,'RO201800000000000003','GCHFSM201800000000000000','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (418,'RO201800000000000003','SM201804262121064388530','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (419,'RO201800000000000003','SM201804271923251312840','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (420,'RO201800000000000003','SM201804291425230875702','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (421,'RO201800000000000003','SM201805021513358511912','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (422,'RO201800000000000003','SM201805021608214954446','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (423,'RO201800000000000003','SM201805031143039272323','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (424,'RO201800000000000003','SM201805031622490571847','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (425,'RO201800000000000003','SM201805071518444813149','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (426,'RO201800000000000003','SM201805101326343014288','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (427,'RO201800000000000003','SM201805122203323176704','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (428,'RO201800000000000003','SM201806252143270574752','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (429,'RO201800000000000003','SM201805122244282147848','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (430,'RO201800000000000003','SM201805122244587461594','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (431,'RO201800000000000003','SM201805122246258158393','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (432,'RO201800000000000003','SM201805261021084049823','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (433,'RO201800000000000003','SM201806252141430605486','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (434,'RO201800000000000003','SM201804291350415659355','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (435,'RO201800000000000003','SM201804291425476906723','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (436,'RO201800000000000003','SM201805021608430535380','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (437,'RO201800000000000003','SM201805021637202209454','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (438,'RO201800000000000003','SM201805022048193922921','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (439,'RO201800000000000003','SM201805031623038864903','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (440,'RO201800000000000003','SM201805032049250384279','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (441,'RO201800000000000003','SM201805101326483303672','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (442,'RO201800000000000003','SM201805122247268142994','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (443,'RO201800000000000003','SM201805151541147523361','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (444,'RO201800000000000003','SM201805251516327463058','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (445,'RO201800000000000003','SM201804271918126145662','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (446,'RO201800000000000003','SM201805021609039267284','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (447,'RO201800000000000003','SM201805021637409537163','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (448,'RO201800000000000003','SM201805022048376225056','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (449,'RO201800000000000003','SM201805061739537493514','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (450,'RO201800000000000003','SM201805142231375155440','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (451,'RO201800000000000003','SM201805231252504327898','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (452,'RO201800000000000003','SM201805261001490495192','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (453,'RO201800000000000003','SM201804262112397419688','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (454,'RO201800000000000003','SM201804262118246417123','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (455,'RO201800000000000003','SM201805122247567061152','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (456,'RO201800000000000003','SM201804262009375685547','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (457,'RO201800000000000003','SM201805041411074644391','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (458,'RO201800000000000003','SM201805251354520521925','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (459,'RO201800000000000003','SM201805031341536277279','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (460,'RO201800000000000003','SM201805061714532327913','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (461,'RO201800000000000003','SM201805122248245127470','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (462,'RO201800000000000003','SM201805261621476424511','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (463,'RO201800000000000003','SM201805031342142928555','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (464,'RO201800000000000003','SM201805122249222019735','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (465,'RO201800000000000003','SM201805142040150952935','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (466,'RO201800000000000003','SM201804262037546867086','USYS201800000000001',now(),NULL);
INSERT INTO `tsys_menu_role` (`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES (467,'RO201800000000000003','SM201805041050394705600','USYS201800000000001',now(),NULL);
