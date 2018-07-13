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

