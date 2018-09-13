##V1.2.0
ALTER TABLE `thf_company_card` 
ADD COLUMN `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称' AFTER `company_name`;

ALTER TABLE `thf_query_log` 
CHANGE COLUMN `pic1` `pic1` MEDIUMTEXT NULL DEFAULT NULL COMMENT '身份证头像' ;

ALTER TABLE `thf_attendance` 
ADD COLUMN `sim` DECIMAL(4,2) DEFAULT NULL COMMENT '相似度' AFTER `settle_datetime`,
ADD COLUMN `terminal_code` VARCHAR(32) DEFAULT NULL COMMENT '终端编号' AFTER `sim`;

ALTER TABLE `thf_employ` 
ADD COLUMN `last_leaving_days` int(11) DEFAULT NULL COMMENT '最近一次请假天数' AFTER `start_datetime`,
DROP COLUMN `leaving_days`;

ALTER TABLE `thf_project` 
ADD COLUMN `salary_delay_days` INT NULL DEFAULT 0 COMMENT '薪资发放可延迟天数（监管端填写）' AFTER `salary_datetime`;

CREATE TABLE `thf_leave` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `staff_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `staff_name` varchar(32) DEFAULT NULL COMMENT '员工姓名',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `start_datetime` datetime DEFAULT NULL COMMENT '请假开始日期（包括当天）',
  `end_datetime` datetime DEFAULT NULL COMMENT '请假结束日期',
  `leave_days` int(11) DEFAULT NULL COMMENT '请假天数',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `thf_salary`;
CREATE TABLE `thf_salary` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `message_code` varchar(32) DEFAULT NULL COMMENT '消息编号',
  `staff_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  
  `staff_name` varchar(32) DEFAULT NULL COMMENT '员工姓名',
  `month` varchar(32) DEFAULT '0' COMMENT '所属月份',
  `leaving_days` int(11) DEFAULT '0' COMMENT '请假天数',
  `attendance_days` int(11) DEFAULT '0' COMMENT '当月正常考勤的天数',
  `delay_hours` int(11) DEFAULT '0' COMMENT '迟到小时数',
  
  `early_hours` int(11) DEFAULT '0' COMMENT '早退小时数',
  `should_amount` bigint(20) DEFAULT '0' COMMENT '应发工资（程序计算来的：attendanceDays*日薪-（delayHours+earlyHours）*扣款时薪）',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `cut_amount` bigint(20) DEFAULT '0' COMMENT '扣减金额（业主手动调整参数之一',
  
  `award_amount` bigint(20) DEFAULT '0' COMMENT '奖励金额',
  `tax` bigint(20) DEFAULT '0' COMMENT '税费',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请调整的人',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请调整时间',
  `apply_note` varchar(255) COMMENT '申请调整说明',
  
  `approve_user` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` varchar(255) COMMENT '审核说明',
  `fact_amount` bigint(20) DEFAULT '0' COMMENT '应发工资（业主审核之后的最终应发金额：shouldAmount-cutAmount+awardAmount-tax）',
  `fact_amount_remark` varchar(255) COMMENT '备注（针对factAmount的说明）',
  
  `pay_amount` bigint(20) DEFAULT '0' COMMENT '已发工资（银行回填，即务工人员到手的工资）',
  `supply_amount` bigint(20) DEFAULT NULL COMMENT '补发工资（多次补发就累加）',
  `late_pay_datetime` datetime DEFAULT NULL COMMENT '最近一次发放时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


##V1.2.1
ALTER TABLE `thf_employ` 
ADD COLUMN `company_code` VARCHAR(32) NULL COMMENT '公司编号' AFTER `code`,
ADD COLUMN `department_code` VARCHAR(32) NULL COMMENT '部门编号' AFTER `project_name`;
update thf_employ set company_code = (select company_code from thf_project where thf_employ.project_code = thf_project.code);

ALTER TABLE `thf_staff` 
DROP COLUMN `skill_pdf`,
DROP COLUMN `department_code`,
DROP COLUMN `company_code`,
CHANGE COLUMN `remark` `remark` TEXT NULL DEFAULT NULL COMMENT '备注' AFTER `contacts_mobile`;

ALTER TABLE `thf_project` 
DROP COLUMN `department_code`;

ALTER TABLE `thf_department` 
DROP COLUMN `company_code`;

ALTER TABLE `thf_bank_card` 
ADD COLUMN `project_code` VARCHAR(32) NULL COMMENT '项目编号' AFTER `company_code`;

##V1.3.0
ALTER TABLE `thf_query_log` 
DROP FOREIGN KEY `staff_code`;
ALTER TABLE `thf_query_log` 
ADD CONSTRAINT `staff_code`
  FOREIGN KEY (`staff_code`)
  REFERENCES `thf_staff` (`code`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `thf_query_log` 
DROP FOREIGN KEY `user_id`;
ALTER TABLE `thf_query_log` 
ADD CONSTRAINT `user_id`
  FOREIGN KEY (`user_id`)
  REFERENCES `thf_user` (`user_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `thf_event_remind` 
ADD COLUMN `user_id` VARCHAR(32) NULL COMMENT '用户编号' AFTER `code`;


##V1.4.0 - V1.5.0
ALTER TABLE `tsys_menu` 
ADD COLUMN `system_code` VARCHAR(4) NULL AFTER `type`;

CREATE TABLE `thf_supervise` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '区',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `thf_supervise` (`code`, `province`, `city`, `area`) VALUES ('SU201808011128113874417', '浙江省', '丽水市', '缙云县');

CREATE TABLE `thf_operator_guide` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` TEXT DEFAULT NULL COMMENT '操作内容',
  `order_no` INT(11) DEFAULT NULL COMMENT '排序编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `thf_attendance` 
DROP COLUMN `staff_mobile`,
ADD COLUMN `employ_code` VARCHAR(32) NULL COMMENT '雇佣编号' AFTER `code`;

ALTER TABLE `thf_attendance` 
CHANGE COLUMN `code` `code` VARCHAR(32) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL COMMENT '编号' ,
CHANGE COLUMN `employ_code` `employ_code` VARCHAR(32) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL COMMENT '雇佣编号' ,
CHANGE COLUMN `project_code` `project_code` VARCHAR(32) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL COMMENT '项目编号' ,
CHANGE COLUMN `project_name` `project_name` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL COMMENT '项目名称' ,
CHANGE COLUMN `staff_code` `staff_code` VARCHAR(32) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL COMMENT '员工编号' ,
CHANGE COLUMN `staff_name` `staff_name` VARCHAR(32) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL COMMENT '员工姓名' ,
CHANGE COLUMN `status` `status` VARCHAR(4) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL COMMENT '出工状态' ,
CHANGE COLUMN `start_datetime` `start_datetime` VARCHAR(64) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL COMMENT '上班时间' ,
CHANGE COLUMN `end_datetime` `end_datetime` VARCHAR(64) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL COMMENT '下班时间' ,
CHANGE COLUMN `terminal_code` `terminal_code` VARCHAR(32) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL COMMENT '终端编号' ,
CHANGE COLUMN `remark` `remark` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL COMMENT '备注' ;

ALTER TABLE `thf_user` 
ADD COLUMN `organization_code` VARCHAR(32) NULL COMMENT '组织编号（项目编号/银行编号/监管编号）' AFTER `type`;
update thf_user set organization_code = (select code from thf_project where thf_project.company_code not in ('C201806271552174711525') and thf_user.company_code = thf_project.company_code ) where type = 'O';#业主端
update thf_user set organization_code = (select code from thf_subbranch where thf_user.subbranch = thf_subbranch.subbranch_name and thf_user.bank_name = thf_subbranch.bank_name) where type = 'B';#银行端
update thf_user set organization_code = (select code from thf_supervise where thf_user.province = thf_supervise.province and thf_user.city = thf_supervise.city and thf_user.area = thf_supervise.area) where type = 'S';#监管端
update thf_user set user_refree = 'USYS201800000000001' where user_id <> 'USYS201800000000001';
INSERT INTO `thf_user` (`user_id`, `real_name`, `type`, `organization_code`, `login_name`, `mobile`, `login_pwd`, `login_pwd_strength`, `user_refree`, `create_datetime`, `role_code`, `status`, `remark`) VALUES ('U201806271554037471384', '朱小庭', 'O', 'P201806271603255348119', '九州新时代商住楼', '13757840919', 'c8837b23ff8aaa8a2dde915473ce0991', '1', 'USYS201800000000001', '2018-06-27 15:54:03', 'RO201800000000000003', '0', '6月27日');
INSERT INTO `thf_user` (`user_id`, `real_name`, `type`, `organization_code`, `login_name`, `mobile`, `login_pwd`, `login_pwd_strength`, `user_refree`, `create_datetime`, `role_code`, `status`, `remark`) VALUES ('U201806271554037471385', '朱小庭', 'O', 'P201807141540459457634', '缙云县工艺美术学校迁建工程2标段', '13757840919', 'c8837b23ff8aaa8a2dde915473ce0991', '1', 'USYS201800000000001', '2018-06-27 15:54:03', 'RO201800000000000003', '0', '6月27日');
DELETE FROM `thf_user` WHERE `user_id`='U201806271554037471383';

ALTER TABLE `thf_event_remind` 
ADD COLUMN `system_code` VARCHAR(4) NULL COMMENT '系统编号（B/S）' AFTER `code`,
ADD COLUMN `organization_code` VARCHAR(32) NULL COMMENT '组织编号' AFTER `system_code`;
##手动整理数据

ALTER TABLE `thf_company_card` 
RENAME TO  `thf_project_card` ;

ALTER TABLE `thf_leave` 
ADD COLUMN `employ_code` VARCHAR(32) NULL COMMENT '雇佣编号' AFTER `code`;
update thf_leave set employ_code = (select code from thf_employ where thf_leave.project_code = thf_employ.project_code and thf_leave.staff_code = thf_employ.staff_code);

ALTER TABLE `thf_bank_card` 
ADD COLUMN `employ_code` VARCHAR(32) NULL COMMENT '雇佣编号' AFTER `code`,
ADD COLUMN `project_name` VARCHAR(255) NULL COMMENT '项目名称' AFTER `project_code`;
update thf_bank_card set employ_code = (select code from thf_employ where thf_bank_card.project_code = thf_employ.project_code and thf_bank_card.staff_code = thf_employ.staff_code);
update thf_bank_card set project_name = (select name from thf_project where thf_bank_card.project_code = thf_project.code);

update thf_attendance set employ_code = (select code from thf_employ where thf_attendance.project_code = thf_employ.project_code and thf_attendance.staff_code = thf_employ.staff_code);

ALTER TABLE `thf_salary` 
ADD COLUMN `employ_code` VARCHAR(32) NULL COMMENT '雇佣编号' AFTER `code`;
update thf_salary set employ_code = (select code from thf_employ where thf_salary.project_code = thf_employ.project_code and thf_salary.staff_code = thf_employ.staff_code);

ALTER TABLE `thf_ccontract` 
DROP COLUMN `contract_datetime`,
CHANGE COLUMN `content_pic` `content_pic` TEXT NULL DEFAULT NULL COMMENT '合同照片' ,
ADD COLUMN `employ_code` VARCHAR(32) NULL COMMENT '雇佣编号' AFTER `code`;
update thf_ccontract set employ_code = (select code from thf_employ where thf_ccontract.project_code = thf_employ.project_code and thf_ccontract.staff_code = thf_employ.staff_code);
update thf_bcontract set project_name = (select name from thf_project where thf_bcontract.project_code = thf_project.code);

ALTER TABLE `thf_message` 
DROP COLUMN `content`;

ALTER TABLE `thf_event_remind` 
DROP COLUMN `type`,
DROP COLUMN `user_id`;

ALTER TABLE `thf_user` 
DROP COLUMN `company_code`,
DROP COLUMN `company_name`,
DROP COLUMN `department_code`,
DROP COLUMN `subbranch`,
DROP COLUMN `bank_name`,
DROP COLUMN `area`,
DROP COLUMN `city`,
DROP COLUMN `province`,
DROP COLUMN `photo`;

DROP TABLE `thf_company`;

ALTER TABLE `thf_project` 
DROP COLUMN `company_code`;

ALTER TABLE `thf_bank_card` 
DROP COLUMN `company_code`;

ALTER TABLE `thf_bcontract` 
DROP COLUMN `company_code`;

ALTER TABLE `thf_progress` 
DROP COLUMN `company_code`,
CHANGE COLUMN `code` `code` VARCHAR(32) NOT NULL COMMENT '编号' FIRST;
update thf_progress set project_name = (select name from thf_project where thf_progress.project_code = thf_project.code);

ALTER TABLE `thf_employ` 
DROP COLUMN `company_code`,
DROP COLUMN `up_user`;

update thf_project set status = 2 where status = 3;
update thf_employ set position = 0;


##V160
ALTER TABLE `thf_staff` 
ADD COLUMN `pict1_status` VARCHAR(4) default 0 COMMENT '免冠照状态(0未拍摄/1已拍摄)' AFTER `contacts_mobile`,
ADD COLUMN `feat_status` VARCHAR(4) default 0 COMMENT '特征值状态(0无效/1有效)' AFTER `pict1_status`;
update thf_staff set pict1_status = 1 where pict1 is not null;
update thf_staff set feat_status = 1 where feat <> 'error' and feat <> 'NOFACE';

ALTER TABLE `thf_bank_card` 
ADD COLUMN `number_status` VARCHAR(4) NULL DEFAULT 0 COMMENT '银行卡号状态(0未录入/1已录入)' AFTER `status`;
update thf_bank_card set number_status = 1 where (subbranch is not null and bankcard_number is not null);
update thf_bank_card set number_status = 0 where subbranch is null or bankcard_number is null or subbranch = '' or bankcard_number = '';

ALTER TABLE `thf_project` 
ADD COLUMN `supervise_code` VARCHAR(32) NULL COMMENT '监管单位编号' AFTER `code`;
update thf_project set supervise_code = (select code from thf_supervise where thf_project.province = thf_supervise.province 
																and thf_project.city = thf_supervise.city
                                                                and thf_project.area = thf_supervise.area);

ALTER TABLE `thf_salary_log` 
CHANGE COLUMN `staff_code` `staff_code` VARCHAR(32) NULL DEFAULT NULL COMMENT '员工编号' AFTER `salary_code`,
ADD COLUMN `handle_pic` TEXT NULL COMMENT '处理照片' AFTER `handle_note`;
