insert into `thf_channel_bank`(`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) values
('ICBC','中国工商银行','40','1',null,null,null,null,null,null),
('CCB','中国建设银行','40','1',null,null,null,null,null,null),
('BOC','中国银行','40','1',null,null,null,null,null,null),
('BCM','中国交通银行','40','1',null,null,null,null,null,null),
('CIB','兴业银行','40','1',null,null,null,null,null,null),
('CITIC','中信银行','40','1',null,null,null,null,null,null),
('CEB','中国光大银行','40','1',null,null,null,null,null,null),
('PAB','平安银行','40','1',null,null,null,null,null,null),
('PSBC','中国邮政储蓄银行','40','1',null,null,null,null,null,null);


insert into `tsys_role`(`code`,`type`,`name`,`updater`,`update_datetime`,`remark`) values
('RO201800000000000001','P','超级管理员','USYS201800000000001',now(),null),
('RO201800000000000002','B','银行端','USYS201800000000001',now(),null),
('RO201800000000000003','O','业主端','USYS201800000000001',now(),null),
('RO201800000000000004','S','监管端','USYS201800000000001',now(),null)



insert into `thf_user`(`user_id`,`real_name`,`type`,`photo`,`login_name`,`mobile`,`login_pwd`,`login_pwd_strength`,`user_refree`,`create_datetime`,`role_code`,`company_code`,`company_name`,`province`,`city`,`area`,`bank_name`,`subbranch`,`department_code`,`updater`,`update_datetime`,`status`,`remark`) values
('USYS201800000000001','平台端','P',null,'admin',null,'21218cca77804d2ba1922c33e0151105','1',null,now(),'RO201800000000000001',null,null,null,null,null,null,null,'',null,null,'0',null);

insert into `tsys_dict`(`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) values
('0',null,'user_kind','用户类型','admin',now(),null),
('1','user_kind','P','平台用户','admin',now(),null),
('1','user_kind','B','银行用户','admin',now(),null),
('1','user_kind','O','业主单位','admin',now(),null),
('1','user_kind','S','监管单位','admin',now(),null),
('0',null,'user_status','用户状态','admin',now(),null),
('1','user_status','0','正常','admin',now(),null),
('1','user_status','1','人工锁定','admin',now(),null),
('1','user_status','2','程序锁定','admin',now(),''),
('0',null,'project_status','项目状态','admin',now(),null),
('1','project_status','0','待提请审核','admin',now(),null),
('1','project_status','1','待审核','admin',now(),null),
('1','project_status','2','审核未通过','admin',now(),null),
('1','project_status','5','项目结束','admin',now(),null),
('0',null,'account_status','账户状态','admin',now(),null),
('1','account_status','0','正常','admin',now(),null),
('1','account_status','1','人工锁定','admin',now(),null),
('1','account_status','2','程序锁定','admin',now(),null),
('0',null,'staff_status','员工状态','admin',now(),null),
('1','staff_status','0','在职','admin',now(),null),
('1','staff_status','1','请假','admin',now(),null),
('1','staff_status','2','离职','admin',now(),null),
('0',null,'message_status','代发消息状态','admin',now(),null),
('1','message_status','0','待发送','admin',now(),null),
('1','message_status','1','待处理','admin',now(),null),
('1','message_status','2','待反馈','admin',now(),null),
('0',null,'salary_status','工资条状态','admin',now(),null),
('1','salary_status','0','待人工复核','admin',now(),null),
('1','salary_status','1','已审核待提交','admin',now(),null),
('1','salary_status','2','已提交待发放','admin',now(),null),
('1','salary_status','3','正常发放','admin',now(),null),
('1','salary_status','4','部分发放','admin',now(),null),
('1','salary_status','5','已补发','admin',now(),null),
('0',null,'staff_type','务工人员类型','admin',now(),null),
('1','staff_type','0','直招工人','admin',now(),null),
('1','staff_type','1','劳务工人','admin',now(),null),
('1','staff_type','2','包工工人','admin',now(),null),
('1','staff_type','3','内勤人员','admin',now(),null),
('0',null,'id_type','证件类型','admin',now(),null),
('1','id_type','1','身份证','admin',now(),null),
('0',null,'attendance_status','考勤状态','admin',now(),null),
('1','attendance_status','0','待上班打卡','admin',now(),null),
('1','attendance_status','1','待下班打卡','admin',now(),null),
('1','attendance_status','2','已打卡待结算','admin',now(),null),
('1','attendance_status','3','已结算','admin',now(),null),
('0',null,'salary_log_type','日志类型','admin',now(),null),
('1','salary_log_type','0','正常','admin',now(),null),
('1','salary_log_type','1','异常','admin',now(),null),
('1','project_status','3','在建','admin',now(),null),
('1','project_status','4','停工','admin',now(),null),
('1','message_status','3','已处理','admin',now(),null);
('0',NULL,'abnormal_type','事件类型','admin',now(),NULL),
('1','abnormal_type','0','工资条异常','admin',now(),NULL);


insert into `tsys_menu`(`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`) values
('GCHFSM201800000000000000','根目录','1','#','1','admin',now(),'',''),
('GCHFSM201800001000000001','系统管理','1','#','1','admin',now(),'','GCHFSM201800000000000000'),
('GCHFSM201800001000000002','运维管理','1','#','2','admin',now(),null,'GCHFSM201800001000000001'),
('GCHFSM201800001000000003','菜单管理','1','/system/menu.htm','1','admin',now(),null,'GCHFSM201800001000000002'),
('GCHFSM201800001000000004','新增','2','/add','1','admin',now(),'','GCHFSM201800001000000003'),
('GCHFSM201800001000000006','运营管理','1','#','2','admin',now(),'','GCHFSM201800001000000001'),
('GCHFSM201800001000000007','角色管理','1','/system/role.htm','1','admin',now(),'','GCHFSM201800001000000006'),
('GCHFSM201800001000000008','用户管理','1','/system/user.htm','1','admin',now(),'','GCHFSM201800001000000006'),
('GCHFSM201800001000000009','系统参数管理','1','/system/sysPara.htm','1','USYS201800000000001',now(),'','GCHFSM201800001000000002'),
('GCHFSM201800001000000010','数据字典管理','1','/system/dataDict.htm','1','USYS201800000000001',now(),'','GCHFSM201800001000000002'),
('GCHFSM201800001000000011','分配菜单','2','/change','4','admin',now(),'','GCHFSM201800001000000007'),
('SM201804261613078269182','修改','2','/edit','2','admin',now(),'','GCHFSM201800001000000003'),
('SM201804261614002237155','删除','2','/delete','3','admin',now(),'','GCHFSM201800001000000003'),
('SM201804261623266254030','修改','2','/edit','1','admin',now(),'','GCHFSM201800001000000009'),
('SM201804261705017439319','修改','2','/edit','1','admin',now(),'','GCHFSM201800001000000010'),
('SM201804261722191103766','新增','2','/add','1','admin',now(),'','GCHFSM201800001000000007'),
('SM201804261722366268383','修改','2','/edit','2','admin',now(),'','GCHFSM201800001000000007'),
('SM201804261722548756668','删除','2','/delete','4','admin',now(),'','GCHFSM201800001000000007'),
('SM201804261758457556570','新增','2','/add','1','admin',now(),'','GCHFSM201800001000000008'),
('SM201804261759056455609','重置密码','2','/reset','2','admin',now(),'','GCHFSM201800001000000008'),
('SM201804261800054386747','注销/激活','2','/rock','3','admin',now(),'','GCHFSM201800001000000008'),
('SM201804261800542746171','设置角色','2','/assign','4','admin',now(),'','GCHFSM201800001000000008'),
('SM201804261921017207214','修改手机号','2','/changeMobile','5','admin',now(),'','GCHFSM201800001000000008'),
('SM201804262009375685547','工资核发','1','#','5','admin',now(),'','GCHFSM201800000000000000'),
('SM201804262037078836469','工资待发','1','/waitList/postRequest.htm','2','USYS201800000000001','2018-05-18 10:16:07','','SM201804262009375685547'),
('SM201804262037546867086','历史请求','1','/waitList/alreadyQuest.htm','2','USYS201800000000002',now(),'','SM201804262009375685547'),
('SM201804262110196148832','公司管理','1','#','3','admin',now(),'','GCHFSM201800001000000001'),
('SM201804262111286723837','公司结构','1','/newProj/companyConstruct.htm','1','admin',now(),'','SM201804262110196148832'),
('SM201804262112397419688','员工管理','1','#','4','admin',now(),'','GCHFSM201800000000000000'),
('SM201804262118246417123','承包商合同','1','/hetong/chengbaoshang.htm','4','USYS201800000000001',now(),'','SM201804271918126145662'),
('SM201804262121064388530','待发请求','1','/daifa/daifa.htm','1','USYS201800000000001',now(),'','SM201804262009375685547'),
('SM201804271918126145662','项目管理','1','#','3','USYS201800000000003',now(),'','GCHFSM201800000000000000'),
('SM201804271923251312840','项目管理','1','/projectManage/project.htm','1','USYS201800000000001',now(),'','SM201804271918126145662'),
('SM201804291350415659355','账户信息','1','/yewuManage/account.htm','2','USYS201800000000001',now(),'','SM201804271918126145662'),
('SM201804291425230875702','修改','2','/edit','1','admin',now(),'','SM201804291350415659355'),
('SM201804291425476906723','详情','2','/detail','2','admin',now(),'','SM201804291350415659355'),
('SM201804291522572535111','设置部门','2','/setBumen','7','admin',now(),'','GCHFSM201800001000000008'),
('SM201805021513358511912','合同录入','2','/add','1','admin',now(),'','SM201804262118246417123'),
('SM201805021608214954446','录入进度','2','/add','1','18870421644',now(),'','SM201804262119227928535'),
('SM201805021608430535380','修改进度','2','/edit','2','18870421644',now(),'','SM201804262119227928535'),
('SM201805021609039267284','详情','2','/detail','3','18870421644',now(),'','SM201804262119227928535'),
('SM201805021637202209454','修改','2','/edit','2','18870421644',now(),'','SM201804262118246417123'),
('SM201805021637409537163','详情','2','/detail','3','18870421644',now(),'','SM201804262118246417123'),
('SM201805021701134437182','详情','2','/detail','1','admin',now(),'','SM201804262037078836469'),
('SM201805022048193922921','修改','2','/edit','2','18870421644',now(),'','SM201804262117511207724'),
('SM201805022048376225056','详情','2','/detail','3','18870421644',now(),'','SM201804262117511207724'),
('SM201805031143039272323','员工查看','1','/staff/allStaff.htm','1','USYS201800000000003','2018-05-06 17:06:30','','SM201804262112397419688'),
('SM201805031146406461279','下载','2','/onDownLoad','2','admin',now(),'','SM201804262037078836469'),
('SM201805031341536277279','修改','2','/edit','6','USYS201800000000001',now(),'','SM201805031143039272323'),
('SM201805031342142928555','详情','2','/detail','7','USYS201800000000001',now(),'','SM201805031143039272323'),
('SM201805031622490571847','发送','2','/send','1','USYS201800000000003',now(),'','SM201804262121064388530'),
('SM201805031623038864903','详情','2','/detail','2','USYS201800000000003',now(),'','SM201804262121064388530'),
('SM201805032049250384279','工资卡管理','1','/staff/bankCard.htm','2','USYS201800000000001','2018-05-23 12:53:52','','SM201804262112397419688'),
('SM201805041050394705600','详情','2','/detail','1','admin',now(),'','SM201804262037546867086'),
('SM201805041411074644391','工作履历','2','/history','5','USYS201800000000001','2018-05-26 22:17:31','','SM201805031143039272323'),
('SM201805061642131878722','开设账号','1','#','2','admin',now(),'','GCHFSM201800000000000000'),
('SM201805061646092985904','银行单位','1','www.baidu.com','1','USYS201800000000001',now(),'','SM201805061642131878722'),
('SM201805061647022105306','业主单位','1','www.baidu.com','2','USYS201800000000001',now(),'','SM201805061642131878722'),
('SM201805061647325709749','监管单位','1','www.baidu.com','3','admin',now(),'','SM201805061642131878722'),
('SM201805061648484984373','银行用户','1','/newId/bank.htm','2','USYS201800000000001',now(),'','SM201805061646092985904'),
('SM201805061649465011644','业主用户','1','/newId/yezhu.htm','2','USYS201800000000001',now(),'','SM201805061647022105306'),
('SM201805061653506988861','监管用户','1','/newId/supervise.htm','1','admin',now(),'','SM201805061647325709749'),
('SM201805061657291081116','新增','2','/add','1','admin',now(),'','SM201805061648484984373'),
('SM201805061714532327913','事件处理','1','#','6','admin',now(),'','GCHFSM201800000000000000'),
('SM201805061739537493514','用工合同','1','/hetong/wugong.htm','3','admin',now(),'','SM201804271918126145662'),
('SM201805071356535937524','新增','2','/add','1','admin',now(),'','SM201805061649465011644'),
('SM201805071357335216787','新增','2','/add','1','admin',now(),'','SM201805061653506988861'),
('SM201805071518444813149','异常查看','1','/staff/allStafferror.htm','1','USYS201800000000001','2018-05-18 11:13:24','','SM201805061714532327913'),
('SM201805101121136563046','公司结构','1','/newId/companyConstruct.htm','1','admin',now(),'','SM201805061647022105306'),
('SM201805101326343014288','修改','2','/edit','1','admin',now(),'','SM201805032049250384279'),
('SM201805101326483303672','详情','2','/detail','2','admin',now(),'','SM201805032049250384279'),
('SM201805101339482085520','设置部门','2','/setBumen1','2','admin',now(),'','SM201805061649465011644'),
('SM201805122203323176704','新增项目','2','/addProject','1','admin',now(),'','SM201804271923251312840'),
('SM201805122243231093581','统计信息','2','/Statistics','15','USYS201800000000001',now(),'','SM201804271923251312840'),
('SM201805122243558813635','办理入职','2','/addWorkers','9','USYS201800000000001',now(),'','SM201804271923251312840'),
('SM201805122244282147848','查看考勤','2','/attendance','10','USYS201800000000001',now(),'','SM201804271923251312840'),
('SM201805122244587461594','工资明细','2','/wages','11','USYS201800000000001',now(),'','SM201804271923251312840'),
('SM201805122246258158393','项目详情','2','/proDetail','14','USYS201800000000001',now(),'','SM201804271923251312840'),
('SM201805122247268142994','修改项目','2','/editPro','2','USYS201800000000001',now(),'','SM201804271923251312840'),
('SM201805122247567061152','审核项目','2','/checkPro','4','USYS201800000000001',now(),'','SM201804271923251312840'),
('SM201805122248245127470','项目停工','2','/overPro','6','USYS201800000000001',now(),'','SM201804271923251312840'),
('SM201805122248548087871','打卡','2','/kCard','12','USYS201800000000001',now(),'','SM201804271923251312840'),
('SM201805122249222019735','重新开工','2','/aWork','7','USYS201800000000001',now(),'','SM201804271923251312840'),
('SM201805142040150952935','项目结束','2','/overTime','8','USYS201800000000001',now(),'','SM201804271923251312840'),
('SM201805142231375155440','提请审核','2','/tiqingshenhe','3','USYS201800000000001',now(),'','SM201804271923251312840'),
('SM201805151541147523361','补录员工信息','2','/detailAdd','2','USYS201800000000001',now(),'','SM201805031143039272323'),
('SM201805152234494002315','详情','2','/detail','2','USYS201800000000001',now(),'','SM201805061648484984373'),
('SM201805160550482243371','详情','2','/detail','2','USYS201800000000001',now(),'','SM201805061649465011644'),
('SM201805160557297112397','详情','2','/detail','2','USYS201800000000001',now(),'','SM201805061653506988861'),
('SM201805171454281342035','工程进度','2','/progress','5','USYS201800000000001',now(),'','SM201804271923251312840'),
('SM201805171903390774769','银行管理','1','/newId/operation.htm','1','USYS201800000000001',now(),'','SM201805061646092985904'),
('SM201805181426110139658','新增银行','2','/add','1','USYS201800000000001',now(),'','SM201805171903390774769'),
('SM201805181426547627373','修改银行','2','/edit','2','USYS201800000000001',now(),'','SM201805171903390774769'),
('SM201805211541378324831','新增进度','2','/addjindu','16','USYS201800000000001',now(),'','SM201804271923251312840'),
('SM201805231252504327898','身份证号查询','1','/staff/idCardQuery.htm','3','USYS201800000000001',now(),'','SM201804262112397419688'),
('SM201805231511426547519','查看考勤','1','/newProj/kaoqin.htm','5','USYS201800000000001',now(),'','SM201804262009375685547'),
('SM201805251354520521925','查看考勤','1','/newProj/kaoqin.htm','5','USYS201800000000001',now(),'','SM201804271918126145662'),
('SM201805251516327463058','历史事件','1','/staff/allStafferrHistory.htm','2','USYS201800000000001',now(),'','SM201805061714532327913'),
('SM201805251544130622832','转换为正常','2','/Transformation','2','USYS201800000000001',now(),'','SM201805071518444813149'),
('SM201805261001490495192','请假','2','/weekday','3','USYS201800000000001',now(),'','SM201805031143039272323'),
('SM201805261021084049823','离职申请','2','/quit','17','USYS201800000000001',now(),'','SM201804271923251312840'),
('SM201805261621476424511','工程进度','1','/hetong/jindu.htm','6','USYS201800000000001',now(),'','SM201804271918126145662'),
('SM201805261758591375743','办理入职','2','/addWorkers','1','USYS201800000000001',now(),'','SM201805031143039272323');


insert into `tsys_menu_role`(`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) values
('SR201804261443398913168','GCHFSM201800001000000008','admin',now(),null),
('RO201800000000000002','GCHFSM201800000000000000','admin',now(),null),
('RO201800000000000002','SM201804262037078836469','admin',now(),null),
('RO201800000000000002','SM201805021701134437182','admin',now(),null),
('RO201800000000000002','SM201805041050394705600','admin',now(),null),
('RO201800000000000002','SM201804262037546867086','admin',now(),null),
('RO201800000000000002','SM201804262009375685547','admin','2018-05-13 21:48:23',null),
('SR201805251644020984824','SM201805061714532327913','U201805142013427676188',now(),null),
('SR201805251644020984824','SM201805071518444813149','U201805142013427676188',now(),null),
('RO201800000000000003','GCHFSM201800000000000000','USYS201800000000001',now(),null),
('RO201800000000000003','GCHFSM201800001000000001','USYS201800000000001',now(),null),
('RO201800000000000003','GCHFSM201800001000000007','USYS201800000000001',now(),null),
('RO201800000000000003','GCHFSM201800001000000008','USYS201800000000001',now(),null),
('RO201800000000000003','SM201804261722191103766','USYS201800000000001',now(),null),
('RO201800000000000003','SM201804262111286723837','USYS201800000000001',now(),null),
('RO201800000000000003','SM201804262121064388530','USYS201800000000001',now(),null),
('RO201800000000000003','SM201804271923251312840','USYS201800000000001',now(),null),
('RO201800000000000003','SM201804291425230875702','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805021513358511912','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805021608214954446','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805031143039272323','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805031622490571847','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805071518444813149','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805101326343014288','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805122203323176704','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805122244282147848','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805122244587461594','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805122246258158393','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805261001490495192','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805261021084049823','USYS201800000000001',now(),null),
('RO201800000000000003','GCHFSM201800001000000006','USYS201800000000001',now(),null),
('RO201800000000000003','SM201804261722366268383','USYS201800000000001',now(),null),
('RO201800000000000003','SM201804261759056455609','USYS201800000000001',now(),null),
('RO201800000000000003','SM201804291350415659355','USYS201800000000001',now(),null),
('RO201800000000000003','SM201804291425476906723','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805021608430535380','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805021637202209454','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805022048193922921','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805031341536277279','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805031623038864903','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805032049250384279','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805101326483303672','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805122247268142994','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805251516327463058','USYS201800000000001',now(),null),
('RO201800000000000003','SM201804261800054386747','USYS201800000000001',now(),null),
('RO201800000000000003','SM201804262110196148832','USYS201800000000001',now(),null),
('RO201800000000000003','SM201804271918126145662','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805021609039267284','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805021637409537163','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805022048376225056','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805031342142928555','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805061739537493514','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805142231375155440','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805231252504327898','USYS201800000000001',now(),null),
('RO201800000000000003','GCHFSM201800001000000011','USYS201800000000001',now(),null),
('RO201800000000000003','SM201804261722548756668','USYS201800000000001',now(),null),
('RO201800000000000003','SM201804261800542746171','USYS201800000000001',now(),null),
('RO201800000000000003','SM201804262112397419688','USYS201800000000001',now(),null),
('RO201800000000000003','SM201804262118246417123','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805041411074644391','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805122247567061152','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805151541147523361','USYS201800000000001',now(),null),
('RO201800000000000003','SM201804261921017207214','USYS201800000000001',now(),null),
('RO201800000000000003','SM201804262009375685547','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805251354520521925','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805261758591375743','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805061714532327913','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805122248245127470','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805261621476424511','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805122249222019735','USYS201800000000001',now(),null),
('RO201800000000000003','SM201805142040150952935','USYS201800000000001',now(),null),
('RO201800000000000001','GCHFSM201800000000000000','USYS201800000000001',now(),null),
('RO201800000000000001','GCHFSM201800001000000001','USYS201800000000001',now(),null),
('RO201800000000000001','GCHFSM201800001000000003','USYS201800000000001',now(),null),
('RO201800000000000001','GCHFSM201800001000000004','USYS201800000000001',now(),null),
('RO201800000000000001','GCHFSM201800001000000007','USYS201800000000001',now(),null),
('RO201800000000000001','GCHFSM201800001000000008','USYS201800000000001',now(),null),
('RO201800000000000001','GCHFSM201800001000000009','USYS201800000000001',now(),null),
('RO201800000000000001','GCHFSM201800001000000010','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804261623266254030','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804261705017439319','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804261722191103766','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804261758457556570','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804262121064388530','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804271923251312840','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805021701134437182','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805031143039272323','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805041050394705600','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805061646092985904','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805061653506988861','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805061657291081116','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805071356535937524','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805071357335216787','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805071518444813149','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805101121136563046','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805171903390774769','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805181426110139658','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805122244282147848','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805122244587461594','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805122246258158393','USYS201800000000001',now(),null),
('RO201800000000000001','GCHFSM201800001000000002','USYS201800000000001',now(),null),
('RO201800000000000001','GCHFSM201800001000000006','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804261613078269182','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804261722366268383','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804261759056455609','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804262037078836469','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804262037546867086','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804291350415659355','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804291425476906723','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805021608430535380','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805021637202209454','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805031623038864903','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805032049250384279','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805061642131878722','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805061647022105306','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805061648484984373','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805061649465011644','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805101326483303672','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805122247268142994','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805152234494002315','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805160550482243371','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805160557297112397','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805181426547627373','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805251516327463058','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804261614002237155','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804261800054386747','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804271918126145662','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805021609039267284','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805021637409537163','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805031342142928555','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805061647325709749','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805061739537493514','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805231252504327898','USYS201800000000001',now(),null),
('RO201800000000000001','GCHFSM201800001000000011','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804261722548756668','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804261800542746171','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804262112397419688','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804262118246417123','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805041411074644391','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804261921017207214','USYS201800000000001',now(),null),
('RO201800000000000001','SM201804262009375685547','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805251354520521925','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805061714532327913','USYS201800000000001',now(),null),
('RO201800000000000001','SM201805261621476424511','USYS201800000000001',now(),null),
('RO201800000000000004','GCHFSM201800000000000000','USYS201800000000001',now(),null),
('RO201800000000000004','GCHFSM201800001000000001','USYS201800000000001',now(),null),
('RO201800000000000004','GCHFSM201800001000000007','USYS201800000000001',now(),null),
('RO201800000000000004','GCHFSM201800001000000008','USYS201800000000001',now(),null),
('RO201800000000000004','SM201804261722191103766','USYS201800000000001',now(),null),
('RO201800000000000004','SM201804261758457556570','USYS201800000000001',now(),null),
('RO201800000000000004','SM201804262111286723837','USYS201800000000001',now(),null),
('RO201800000000000004','SM201804262121064388530','USYS201800000000001',now(),null),
('RO201800000000000004','SM201804271923251312840','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805021701134437182','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805031143039272323','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805041050394705600','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805071518444813149','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805122244282147848','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805122244587461594','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805122246258158393','USYS201800000000001',now(),null),
('RO201800000000000004','GCHFSM201800001000000006','USYS201800000000001',now(),null),
('RO201800000000000004','SM201804261722366268383','USYS201800000000001',now(),null),
('RO201800000000000004','SM201804261759056455609','USYS201800000000001',now(),null),
('RO201800000000000004','SM201804262037078836469','USYS201800000000001',now(),null),
('RO201800000000000004','SM201804262037546867086','USYS201800000000001',now(),null),
('RO201800000000000004','SM201804291350415659355','USYS201800000000001',now(),null),
('RO201800000000000004','SM201804291425476906723','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805031146406461279','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805031623038864903','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805032049250384279','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805101326483303672','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805251516327463058','USYS201800000000001',now(),null),
('RO201800000000000004','SM201804261800054386747','USYS201800000000001',now(),null),
('RO201800000000000004','SM201804262110196148832','USYS201800000000001',now(),null),
('RO201800000000000004','SM201804271918126145662','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805021609039267284','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805021637409537163','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805031342142928555','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805061739537493514','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805231252504327898','USYS201800000000001',now(),null),
('RO201800000000000004','GCHFSM201800001000000011','USYS201800000000001',now(),null),
('RO201800000000000004','SM201804261722548756668','USYS201800000000001',now(),null),
('RO201800000000000004','SM201804261800542746171','USYS201800000000001',now(),null),
('RO201800000000000004','SM201804262112397419688','USYS201800000000001',now(),null),
('RO201800000000000004','SM201804262118246417123','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805041411074644391','USYS201800000000001',now(),null),
('RO201800000000000004','SM201804261921017207214','USYS201800000000001',now(),null),
('RO201800000000000004','SM201804262009375685547','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805251354520521925','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805061714532327913','USYS201800000000001',now(),null),
('RO201800000000000004','SM201805261621476424511','USYS201800000000001',now(),null);

insert  into `tsys_config`(`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) values 
('sys_txt','telephone','0571-88888888','USYS201800000000001',now(),'联系电话'),
('sys_txt','about_us','关于我们112','USYS201800000000001',now(),'关于我们'),
('sys_txt','service_time','9:00-17:40','USYS201800000000001',now(),'服务时间'),
('qiniu','qiniu_access_key','07KR5rNezHcXebD-GalrPw0npsAODOMVxygvdFFt','USYS201800000000001',now(),'七牛云key1'),
('qiniu','qiniu_secret_key','nsMbXOfEtk3SvQ3GFHbKMozJua3jbTiGPIIwu4tq','USYS201800000000001',now(),'qiniu_secret_key'),
('qiniu','qiniu_bucket','zwzj','USYS201800000000001',now(),'qiniu_bucket'),
('qiniu','qiniu_domain','otoieuivb.bkt.clouddn.com','USYS201800000000001',now(),'访问域名'),
('sys_txt','telephone','0571-88888888','USYS201800000000001',now(),'联系电话'),
('sys_txt','about_us','关于我们112','USYS201800000000001',now(),'关于我们'),
('sys_txt','service_time','9:00-17:40','USYS201800000000001',now(),'服务时间'),
('qiniu','qiniu_access_key','07KR5rNezHcXebD-GalrPw0npsAODOMVxygvdFFt','USYS201800000000001',now(),'七牛云key1'),
('qiniu','qiniu_secret_key','nsMbXOfEtk3SvQ3GFHbKMozJua3jbTiGPIIwu4tq','USYS201800000000001',now(),'qiniu_secret_key'),
('qiniu','qiniu_bucket','zwzj','USYS201800000000001','2018-02-08 17:09:59','qiniu_bucket'),
('qiniu','qiniu_domain','otoieuivb.bkt.clouddn.com','USYS201800000000001',now(),'访问域名');
