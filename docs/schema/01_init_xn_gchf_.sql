DROP TABLE  `thf_query_log`;

DROP TABLE IF EXISTS  `thf_attendance`;
CREATE TABLE `thf_attendance` (
  `code` varchar(32) COLLATE utf8_estonian_ci NOT NULL COMMENT '编号',
  `project_code` varchar(32) COLLATE utf8_estonian_ci DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) COLLATE utf8_estonian_ci DEFAULT NULL COMMENT '项目名称',
  `staff_code` varchar(32) COLLATE utf8_estonian_ci DEFAULT NULL COMMENT '员工编号',
  `staff_name` varchar(32) COLLATE utf8_estonian_ci DEFAULT NULL COMMENT '员工姓名',
  `staff_mobile` varchar(16) COLLATE utf8_estonian_ci DEFAULT NULL COMMENT '员工手机号',
  `status` varchar(4) COLLATE utf8_estonian_ci DEFAULT NULL COMMENT '出工状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '生成时间',
  `start_datetime` varchar(64) COLLATE utf8_estonian_ci DEFAULT NULL COMMENT '上班时间',
  `end_datetime` varchar(64) COLLATE utf8_estonian_ci DEFAULT NULL COMMENT '下班时间',
  `settle_datetime` datetime DEFAULT NULL COMMENT '结算时间',
  `remark` text COLLATE utf8_estonian_ci COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_estonian_ci;


DROP TABLE IF EXISTS  `thf_bank_card`;
CREATE TABLE `thf_bank_card` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `staff_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `staff_name` varchar(64) DEFAULT NULL COMMENT '员工姓名',
  `bank_code` varchar(64) DEFAULT NULL COMMENT '银行行别',
  `bank_name` varchar(255) DEFAULT NULL COMMENT '银行名称',
  `subbranch` text COMMENT '开户支行',
  `bankcard_number` varchar(64) DEFAULT NULL COMMENT '银行卡号',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `thf_bcontract`;
CREATE TABLE `thf_bcontract` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
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

DROP TABLE IF EXISTS  `thf_ccontract`;
CREATE TABLE `thf_ccontract` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `staff_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `staff_mobile` varchar(16) DEFAULT NULL COMMENT '员工手机号',
  `content_pic` varchar(255) DEFAULT NULL COMMENT '合同照片',
  `contract_datetime` datetime DEFAULT NULL COMMENT '签约时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `thf_channel_bank`;
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
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS  `thf_company`;
CREATE TABLE `thf_company` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `thf_company_card`;
CREATE TABLE `thf_company_card` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `company_name` varchar(255) DEFAULT NULL COMMENT '公司名称',
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

DROP TABLE IF EXISTS  `thf_department`;
CREATE TABLE `thf_department` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `name` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `leader` varchar(255) DEFAULT NULL COMMENT '部门负责人',
  `leade_mobile` varchar(16) DEFAULT NULL COMMENT '负责人手机号',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '上级部门',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `thf_employ`;
CREATE TABLE `thf_employ` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `staff_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `staff_name` varchar(64) DEFAULT NULL COMMENT '员工姓名',
  `staff_mobile` varchar(16) DEFAULT NULL COMMENT '员工手机号',
  `type` char(1) DEFAULT NULL COMMENT '类别',
  `position` varchar(64) DEFAULT NULL COMMENT '所在职位',
  `up_user` varchar(32) DEFAULT NULL COMMENT '上级编号',
  `salary` bigint(20) DEFAULT '0' COMMENT '薪酬',
  `cut_amount` bigint(20) DEFAULT '0' COMMENT '扣款规则',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `join_datetime` datetime DEFAULT NULL COMMENT '入职时间',
  `leaving_datetime` datetime DEFAULT NULL COMMENT '离职时间',
  `start_datetime` datetime DEFAULT NULL COMMENT '最近一次请假开始时间',
  `end_datetime` datetime DEFAULT NULL COMMENT '最近一次请假结束时间',
  `total_leaving_days` decimal(10,1) DEFAULT '0.0' COMMENT '累积请假天数',
  `leaving_days` decimal(10,1) DEFAULT '0.0' COMMENT '本月请假天数',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  `salary_status` varchar(4) DEFAULT NULL COMMENT '工资发放状态',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `thf_message`;
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
  `content` text COMMENT '消息内容',
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

DROP TABLE IF EXISTS  `thf_message_log`;
CREATE TABLE `thf_message_log` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `message_code` varchar(32) DEFAULT NULL COMMENT '消息编号',
  `salary_code` varchar(32) DEFAULT NULL COMMENT '工资条编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `thf_progress`;
CREATE TABLE `thf_progress` (
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `code` varchar(32) NOT NULL COMMENT '编号',
  `datetime` datetime DEFAULT NULL COMMENT '进度时间',
  `description` text COMMENT '工程进度描述',
  `picture` varchar(255) DEFAULT NULL COMMENT '工程进度图片',
  `updater` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `thf_project`;
CREATE TABLE `thf_project` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `charge_user` varchar(32) DEFAULT NULL COMMENT '负责人编号',
  `attendance_endtime` varchar(64) DEFAULT NULL COMMENT '下班时间',
  `charge_mobile` varchar(16) DEFAULT NULL COMMENT '负责人手机号',
  `attendance_starttime` varchar(64) DEFAULT NULL COMMENT '上班时间',
  `start_datetime` datetime DEFAULT NULL COMMENT '项目开始时间',
  `salary_create_datetime` varchar(64) DEFAULT NULL COMMENT '工资条形成时间',
  `end_datetime` datetime DEFAULT NULL COMMENT '项目结束时间',
  `longitude` varchar(255) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(255) DEFAULT NULL COMMENT '纬度',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `area` varchar(255) DEFAULT NULL COMMENT '区',
  `address` text COMMENT '地址',
  `salary_datetime` varchar(64) DEFAULT NULL COMMENT '薪资发放时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `updater` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS  `thf_report`;
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


DROP TABLE IF EXISTS  `thf_salary`;
CREATE TABLE `thf_salary` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `message_code` varchar(32) DEFAULT NULL COMMENT '消息编号',
  `staff_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `month` varchar(32) DEFAULT '0' COMMENT '所属月份',
  `should_amount` bigint(20) DEFAULT '0' COMMENT '应发工资',
  `fact_amount` bigint(20) DEFAULT '0' COMMENT '实发工资',
  `pay_amount` bigint(20) DEFAULT '0' COMMENT '已发放金额',
  `cut_amount` bigint(20) DEFAULT '0' COMMENT '扣款金额',
  `cut_note` text COMMENT '扣款说明',
  `tax` bigint(20) DEFAULT '0' COMMENT '税费',
  `delay_days` int(11) DEFAULT '0' COMMENT '迟到天数',
  `early_days` int(11) DEFAULT '0' COMMENT '早退天数',
  `leaving_days` int(11) DEFAULT '0' COMMENT '请假天数',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `approve_user` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` text COMMENT '审核说明',
  `remark` text COMMENT '备注',
  `late_pay_datetime` datetime DEFAULT NULL COMMENT '最近一次发放时间',
  `supply_amount` bigint(20) DEFAULT NULL COMMENT '补发薪资',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `thf_salary_log`;
CREATE TABLE `thf_salary_log` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `salary_code` varchar(32) DEFAULT NULL COMMENT '工资条编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `handler` varchar(32) DEFAULT NULL COMMENT '操作人',
  `handle_datetime` datetime DEFAULT NULL COMMENT '操作时间',
  `handle_note` text COMMENT '操作描述',
  `staff_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `thf_skill`;
CREATE TABLE `thf_skill` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `staff_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `staff_name` varchar(64) DEFAULT NULL COMMENT '员工姓名',
  `name` varchar(64) DEFAULT NULL COMMENT '技能名称',
  `pdf` varchar(255) DEFAULT NULL COMMENT '技能证书',
  `score` int(11) DEFAULT NULL COMMENT '技能评分',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `thf_staff`;
CREATE TABLE `thf_staff` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
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
  `pict1` varchar(255) DEFAULT NULL COMMENT '免冠照片',
  `pict2` varchar(255) DEFAULT NULL COMMENT '手持身份张照片',
  `pict3` varchar(255) DEFAULT NULL COMMENT '身份证正反面照片+签名',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `feat` text COMMENT '特征值',
  `remark` text COMMENT '备注',
  `contacts` varchar(64) DEFAULT NULL COMMENT '紧急联系人',
  `contacts_mobile` varchar(16) DEFAULT NULL COMMENT '紧急联系人手机号',
  `skill_pdf` varchar(255) DEFAULT NULL COMMENT '技能证书',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `thf_staff_log`;
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

DROP TABLE IF EXISTS  `thf_user`;
CREATE TABLE `thf_user` (
  `user_id` varchar(96) NOT NULL,
  `real_name` varchar(192) DEFAULT NULL,
  `type` varchar(12) DEFAULT NULL,
  `photo` varchar(765) DEFAULT NULL,
  `login_name` varchar(192) DEFAULT NULL,
  `mobile` varchar(48) DEFAULT NULL,
  `login_pwd` varchar(32) DEFAULT NULL,
  `login_pwd_strength` char(3) DEFAULT NULL,
  `user_refree` varchar(96) DEFAULT NULL,
  `create_datetime` datetime DEFAULT NULL,
  `role_code` varchar(96) DEFAULT NULL,
  `company_code` varchar(96) DEFAULT NULL,
  `company_name` varchar(765) DEFAULT NULL,
  `province` varchar(765) DEFAULT NULL,
  `city` varchar(765) DEFAULT NULL,
  `area` varchar(765) DEFAULT NULL,
  `bank_name` varchar(765) DEFAULT NULL,
  `subbranch` varchar(765) DEFAULT NULL,
  `department_code` varchar(96) DEFAULT NULL,
  `updater` varchar(96) DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `status` varchar(12) DEFAULT NULL,
  `remark` text,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tsys_config`;
CREATE TABLE `tsys_config` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(96) DEFAULT NULL,
  `ckey` varchar(765) DEFAULT NULL,
  `cvalue` text,
  `updater` varchar(96) DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `remark` varchar(765) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tsys_dict`;
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
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tsys_menu`;
CREATE TABLE `tsys_menu` (
  `code` varchar(32) NOT NULL,
  `name` varchar(288) DEFAULT NULL,
  `type` varchar(18) DEFAULT NULL,
  `url` varchar(576) DEFAULT NULL,
  `order_no` varchar(72) DEFAULT NULL,
  `updater` varchar(288) DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `remark` varchar(2295) DEFAULT NULL,
  `parent_code` varchar(288) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS  `tsys_menu_role`;
CREATE TABLE `tsys_menu_role` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(96) DEFAULT NULL,
  `menu_code` varchar(96) DEFAULT NULL,
  `updater` varchar(96) DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `remark` varchar(765) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS  `tsys_role`;
CREATE TABLE `tsys_role` (
  `code` varchar(96) NOT NULL,
  `type` varchar(4) DEFAULT NULL,
  `name` varchar(96) DEFAULT NULL,
  `updater` varchar(96) DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `remark` varchar(765) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `thf_event_remind`;
CREATE TABLE `thf_event_remind` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '异常类型',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `thf_query_log`;
CREATE TABLE `thf_query_log` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `staff_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `staff_name` varchar(64) DEFAULT NULL COMMENT '员工名称',
  `id_no` varchar(64) DEFAULT NULL COMMENT '身份证号',
  `pic1` text COMMENT '身份证头像',
  PRIMARY KEY (`code`),
  KEY `staff_code` (`staff_code`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `staff_code` FOREIGN KEY (`staff_code`) REFERENCES `thf_staff` (`code`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `thf_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

