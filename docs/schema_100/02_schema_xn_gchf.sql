-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 47.96.161.183    Database: dev_xn_gchf
-- ------------------------------------------------------
-- Server version	5.6.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `thf_attendance`
--

DROP TABLE IF EXISTS `thf_attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_attendance` (
  `code` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '编号',
  `employ_code` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '雇佣编号',
  `project_code` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '项目名称',
  `staff_code` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '员工编号',
  `staff_name` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '员工姓名',
  `staff_mobile` varchar(16) CHARACTER SET utf8 DEFAULT NULL COMMENT '员工手机号',
  `status` varchar(4) CHARACTER SET utf8 DEFAULT NULL COMMENT '出工状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '生成时间',
  `start_datetime` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '上班时间',
  `end_datetime` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '下班时间',
  `settle_datetime` datetime DEFAULT NULL COMMENT '结算时间',
  `sim` decimal(4,2) DEFAULT NULL COMMENT '相似度',
  `terminal_code` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '终端编号',
  `remark` text CHARACTER SET utf8 COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_estonian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_bank_card`
--

DROP TABLE IF EXISTS `thf_bank_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_bank_card` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `employ_code` varchar(32) DEFAULT NULL COMMENT '雇佣编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `staff_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `staff_name` varchar(64) DEFAULT NULL COMMENT '员工姓名',
  `bank_code` varchar(64) DEFAULT NULL COMMENT '银行行别',
  `bank_name` varchar(255) DEFAULT NULL COMMENT '银行名称',
  `subbranch` text COMMENT '开户支行',
  `bankcard_number` varchar(64) DEFAULT NULL COMMENT '银行卡号',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `number_status` varchar(4) DEFAULT '0' COMMENT '银行卡号状态(0未录入/1已录入)',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_bcontract`
--

DROP TABLE IF EXISTS `thf_bcontract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_bcontract` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `bname` varchar(255) DEFAULT NULL COMMENT '承包商名称',
  `bmobile` varchar(16) DEFAULT NULL COMMENT '承包商手机号',
  `content_pic` varchar(255) DEFAULT NULL COMMENT '合同照片',
  `contract_datetime` datetime DEFAULT NULL COMMENT '签约时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_ccontract`
--

DROP TABLE IF EXISTS `thf_ccontract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_ccontract` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `employ_code` varchar(32) DEFAULT NULL COMMENT '雇佣编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `staff_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `staff_name` varchar(255) DEFAULT NULL COMMENT '员工姓名',
  `staff_mobile` varchar(16) DEFAULT NULL COMMENT '员工手机号',
  `content_pic` text COMMENT '合同照片',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_channel_bank`
--

DROP TABLE IF EXISTS `thf_channel_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_channel_bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bank_code` varchar(96) DEFAULT NULL,
  `bank_name` varchar(96) DEFAULT NULL,
  `channel_type` varchar(12) DEFAULT NULL,
  `status` varchar(12) DEFAULT NULL,
  `channel_bank` varchar(96) DEFAULT NULL,
  `max_order` bigint(32) DEFAULT NULL,
  `order_amount` bigint(32) DEFAULT NULL,
  `day_amount` bigint(32) DEFAULT NULL,
  `month_amount` bigint(32) DEFAULT NULL,
  `remark` varchar(765) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_department`
--

DROP TABLE IF EXISTS `thf_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_department` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `name` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `leader` varchar(255) DEFAULT NULL COMMENT '部门负责人',
  `leade_mobile` varchar(16) DEFAULT NULL COMMENT '负责人手机号',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '上级部门',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_employ`
--

DROP TABLE IF EXISTS `thf_employ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_employ` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `department_code` varchar(32) DEFAULT NULL COMMENT '部门编号',
  `staff_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `staff_name` varchar(64) DEFAULT NULL COMMENT '员工姓名',
  `staff_mobile` varchar(16) DEFAULT NULL COMMENT '员工手机号',
  `type` char(1) DEFAULT NULL COMMENT '类别',
  `position` varchar(64) DEFAULT NULL COMMENT '所在职位',
  `salary` bigint(20) DEFAULT '0' COMMENT '薪酬',
  `cut_amount` bigint(20) DEFAULT '0' COMMENT '扣款规则',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `join_datetime` datetime DEFAULT NULL COMMENT '入职时间',
  `leaving_datetime` datetime DEFAULT NULL COMMENT '离职时间',
  `start_datetime` datetime DEFAULT NULL COMMENT '最近一次请假开始时间',
  `last_leaving_days` int(11) DEFAULT NULL COMMENT '最近一次请假天数',
  `end_datetime` datetime DEFAULT NULL COMMENT '最近一次请假结束时间',
  `total_leaving_days` decimal(10,1) DEFAULT '0.0' COMMENT '累积请假天数',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  `salary_status` varchar(4) DEFAULT NULL COMMENT '工资发放状态',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_event_remind`
--

DROP TABLE IF EXISTS `thf_event_remind`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_event_remind` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `system_code` varchar(4) DEFAULT NULL COMMENT '系统编号（B/S）',
  `organization_code` varchar(32) DEFAULT NULL COMMENT '组织编号',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_leave`
--

DROP TABLE IF EXISTS `thf_leave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_leave` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `employ_code` varchar(32) DEFAULT NULL COMMENT '雇佣编号',
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
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_message`
--

DROP TABLE IF EXISTS `thf_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_message` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `bank_code` varchar(32) DEFAULT NULL COMMENT '银行行别',
  `bank_name` varchar(255) DEFAULT NULL COMMENT '银行名称',
  `subbranch` text COMMENT '开户行',
  `bankcard_number` varchar(64) DEFAULT NULL COMMENT '银行卡号',
  `month` varchar(32) DEFAULT NULL COMMENT '工资所属月份',
  `total_amount` bigint(20) DEFAULT '0' COMMENT '本月累计发薪',
  `number` int(11) DEFAULT '0' COMMENT '领薪人数',
  `total_cut_amount` bigint(20) DEFAULT '0' COMMENT '本月累计扣款',
  `total_tax` bigint(20) DEFAULT '0' COMMENT '本月累计税费',
  `title` text COMMENT '标题',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `download` int(11) DEFAULT '0' COMMENT '下载次数',
  `back_download` int(11) DEFAULT '0' COMMENT '反馈下载次数',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `sender` varchar(32) DEFAULT NULL COMMENT '发送人',
  `send_datetime` datetime DEFAULT NULL COMMENT '发送时间',
  `send_note` text COMMENT '发送说明',
  `handler` varchar(32) DEFAULT NULL COMMENT '处理人',
  `handle_datetime` datetime DEFAULT NULL COMMENT '处理时间',
  `handle_note` text COMMENT '处理说明',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_message_log`
--

DROP TABLE IF EXISTS `thf_message_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_message_log` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `message_code` varchar(32) DEFAULT NULL COMMENT '消息编号',
  `salary_code` varchar(32) DEFAULT NULL COMMENT '工资条编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_operator_guide`
--

DROP TABLE IF EXISTS `thf_operator_guide`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_operator_guide` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '操作内容',
  `order_no` int(11) DEFAULT NULL COMMENT '排序编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_progress`
--

DROP TABLE IF EXISTS `thf_progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_progress` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `datetime` datetime DEFAULT NULL COMMENT '进度时间',
  `description` text COMMENT '工程进度描述',
  `picture` varchar(255) DEFAULT NULL COMMENT '工程进度图片',
  `updater` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_project`
--

DROP TABLE IF EXISTS `thf_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_project` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `supervise_code` varchar(32) DEFAULT NULL COMMENT '监管单位编号',
  `company_name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `charge_user` varchar(32) DEFAULT NULL COMMENT '负责人编号',
  `charge_mobile` varchar(16) DEFAULT NULL COMMENT '负责人手机号',
  `attendance_starttime` varchar(64) DEFAULT NULL COMMENT '上班时间',
  `attendance_endtime` varchar(64) DEFAULT NULL COMMENT '下班时间',
  `start_datetime` datetime DEFAULT NULL COMMENT '项目开始时间',
  `end_datetime` datetime DEFAULT NULL COMMENT '项目结束时间',
  `salary_create_datetime` varchar(64) DEFAULT NULL COMMENT '工资条形成时间',
  `longitude` varchar(255) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(255) DEFAULT NULL COMMENT '纬度',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `area` varchar(255) DEFAULT NULL COMMENT '区',
  `address` text COMMENT '地址',
  `salary_datetime` varchar(64) DEFAULT NULL COMMENT '薪资发放时间',
  `salary_delay_days` int(11) DEFAULT '0' COMMENT '薪资发放可延迟天数（监管端填写）',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `updater` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_project_card`
--

DROP TABLE IF EXISTS `thf_project_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_project_card` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `bank_code` varchar(32) DEFAULT NULL COMMENT '银行行别',
  `bank_name` varchar(255) DEFAULT NULL COMMENT '银行名称',
  `subbranch` varchar(255) DEFAULT NULL COMMENT '开户行',
  `bankcard_number` varchar(64) DEFAULT NULL COMMENT '银行卡号',
  `account_name` varchar(64) DEFAULT NULL COMMENT '户名',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_query_log`
--

DROP TABLE IF EXISTS `thf_query_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_query_log` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `staff_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `staff_name` varchar(64) DEFAULT NULL COMMENT '员工名称',
  `id_no` varchar(64) DEFAULT NULL COMMENT '身份证号',
  `pic1` mediumtext COMMENT '身份证头像',
  PRIMARY KEY (`code`),
  KEY `staff_code` (`staff_code`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `staff_code` FOREIGN KEY (`staff_code`) REFERENCES `thf_staff` (`code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `thf_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_report`
--

DROP TABLE IF EXISTS `thf_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_report` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `today_days` int(11) DEFAULT '0' COMMENT '今日上工人数',
  `last_month_salary` bigint(20) DEFAULT '0' COMMENT '上月实际发薪金额',
  `next_month_salary` bigint(20) DEFAULT '0' COMMENT '下月预计发薪金额',
  `total_salary` bigint(20) DEFAULT '0' COMMENT '累计发薪金额',
  `staff_on` bigint(11) DEFAULT '0' COMMENT '目前在职人数',
  `staff_in` bigint(11) DEFAULT '0' COMMENT '累计入职人数',
  `staff_out` bigint(11) DEFAULT '0' COMMENT '累计离职人数',
  `leaving_days` int(11) DEFAULT '0' COMMENT '累计请假人次',
  `working_days` int(11) DEFAULT '0' COMMENT '累计出工人次',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_salary`
--

DROP TABLE IF EXISTS `thf_salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_salary` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `employ_code` varchar(32) DEFAULT NULL COMMENT '雇佣编号',
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
  `apply_note` varchar(255) DEFAULT NULL COMMENT '申请调整说明',
  `approve_user` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审核说明',
  `fact_amount` bigint(20) DEFAULT '0' COMMENT '应发工资（业主审核之后的最终应发金额：shouldAmount-cutAmount+awardAmount-tax）',
  `fact_amount_remark` varchar(255) DEFAULT NULL COMMENT '备注（针对factAmount的说明）',
  `pay_amount` bigint(20) DEFAULT '0' COMMENT '已发工资（银行回填，即务工人员到手的工资）',
  `supply_amount` bigint(20) DEFAULT NULL COMMENT '补发工资（多次补发就累加）',
  `late_pay_datetime` datetime DEFAULT NULL COMMENT '最近一次发放时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_salary_log`
--

DROP TABLE IF EXISTS `thf_salary_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_salary_log` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `salary_code` varchar(32) DEFAULT NULL COMMENT '工资条编号',
  `staff_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `handler` varchar(32) DEFAULT NULL COMMENT '操作人',
  `handle_datetime` datetime DEFAULT NULL COMMENT '操作时间',
  `handle_note` text COMMENT '操作描述',
  `handle_pic` text COMMENT '处理照片',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_skill`
--

DROP TABLE IF EXISTS `thf_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_skill` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `staff_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `staff_name` varchar(64) DEFAULT NULL COMMENT '员工姓名',
  `name` varchar(64) DEFAULT NULL COMMENT '技能名称',
  `pdf` varchar(255) DEFAULT NULL COMMENT '技能证书',
  `score` int(11) DEFAULT NULL COMMENT '技能评分',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_staff`
--

DROP TABLE IF EXISTS `thf_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_staff` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `id_type` varchar(4) DEFAULT NULL COMMENT '证件类型',
  `id_no` varchar(64) DEFAULT NULL COMMENT '证件号',
  `sex` varchar(32) DEFAULT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL COMMENT '出生年月日',
  `id_nation` varchar(32) DEFAULT NULL COMMENT '民族',
  `id_address` text COMMENT '地址',
  `id_startDate` datetime DEFAULT NULL COMMENT '有效开始时间',
  `id_endDate` datetime DEFAULT NULL COMMENT '有效截止时间',
  `id_pic` text COMMENT '身份证头像',
  `id_police` text COMMENT '签发机关',
  `pict1` mediumtext COMMENT '免冠照片',
  `pict2` mediumtext COMMENT '手持身份张照片',
  `pict3` mediumtext COMMENT '身份证正反面照片+签名',
  `pict4` mediumtext COMMENT '身份证反面照',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `feat` text COMMENT '特征值',
  `contacts` varchar(64) DEFAULT NULL COMMENT '紧急联系人',
  `contacts_mobile` varchar(16) DEFAULT NULL COMMENT '紧急联系人手机号',
  `pict1_status` varchar(4) DEFAULT '0' COMMENT '免冠照状态(0未拍摄/1已拍摄)',
  `feat_status` varchar(4) DEFAULT '0' COMMENT '特征值状态(0无效/1有效)',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_staff_log`
--

DROP TABLE IF EXISTS `thf_staff_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_staff_log` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目编号',
  `staff_code` varchar(32) DEFAULT NULL COMMENT '工人编号',
  `staff_name` varchar(255) DEFAULT NULL COMMENT '工人编号',
  `position` varchar(255) DEFAULT NULL COMMENT '职位',
  `join_datetime` datetime DEFAULT NULL COMMENT '入职时间',
  `leaving_datetime` datetime DEFAULT NULL COMMENT '离职时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_subbranch`
--

DROP TABLE IF EXISTS `thf_subbranch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_subbranch` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `bank_code` varchar(32) DEFAULT NULL COMMENT '银行编号',
  `bank_name` varchar(255) DEFAULT NULL COMMENT '银行名称',
  `subbranch_name` varchar(255) DEFAULT NULL COMMENT '支行名称',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_supervise`
--

DROP TABLE IF EXISTS `thf_supervise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_supervise` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '区',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thf_user`
--

DROP TABLE IF EXISTS `thf_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thf_user` (
  `user_id` varchar(96) NOT NULL,
  `real_name` varchar(192) DEFAULT NULL,
  `type` varchar(12) DEFAULT NULL,
  `organization_code` varchar(32) DEFAULT NULL COMMENT '组织编号（项目编号/银行编号/监管编号）',
  `login_name` varchar(192) DEFAULT NULL,
  `mobile` varchar(48) DEFAULT NULL,
  `login_pwd` varchar(32) DEFAULT NULL,
  `login_pwd_strength` char(3) DEFAULT NULL,
  `user_refree` varchar(96) DEFAULT NULL,
  `create_datetime` datetime DEFAULT NULL,
  `role_code` varchar(96) DEFAULT NULL,
  `updater` varchar(96) DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `status` varchar(12) DEFAULT NULL,
  `remark` text,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsys_config`
--

DROP TABLE IF EXISTS `tsys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(96) DEFAULT NULL,
  `ckey` varchar(765) DEFAULT NULL,
  `cvalue` text,
  `updater` varchar(96) DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `remark` varchar(765) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsys_dict`
--

DROP TABLE IF EXISTS `tsys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` char(9) DEFAULT NULL COMMENT '类型',
  `parent_key` varchar(288) DEFAULT NULL COMMENT '父key',
  `dkey` varchar(288) DEFAULT NULL COMMENT 'key',
  `dvalue` varchar(2295) DEFAULT NULL COMMENT '值',
  `updater` varchar(288) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(2295) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsys_menu`
--

DROP TABLE IF EXISTS `tsys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsys_menu` (
  `code` varchar(32) NOT NULL,
  `name` varchar(288) DEFAULT NULL,
  `type` varchar(18) DEFAULT NULL,
  `system_code` varchar(4) DEFAULT NULL,
  `url` varchar(576) DEFAULT NULL,
  `order_no` varchar(72) DEFAULT NULL,
  `updater` varchar(288) DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `remark` varchar(2295) DEFAULT NULL,
  `parent_code` varchar(288) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsys_menu_role`
--

DROP TABLE IF EXISTS `tsys_menu_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsys_menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(96) DEFAULT NULL,
  `menu_code` varchar(96) DEFAULT NULL,
  `updater` varchar(96) DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `remark` varchar(765) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4800 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsys_role`
--

DROP TABLE IF EXISTS `tsys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsys_role` (
  `code` varchar(96) NOT NULL,
  `type` varchar(4) DEFAULT NULL,
  `name` varchar(96) DEFAULT NULL,
  `updater` varchar(96) DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `remark` varchar(765) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-14  1:24:12
