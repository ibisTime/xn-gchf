DROP TABLE IF EXISTS  `tqy_corp_basicinfo`;
CREATE TABLE `tqy_corp_basicinfo` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `corp_code` varchar(18) DEFAULT NULL COMMENT '统一社会信用代码',
  `corp_name` varchar(200) DEFAULT NULL COMMENT '企业名称',
  `corp_type` varchar(3) DEFAULT NULL COMMENT '企业登记注册类型',
  `license_num` varchar(50) DEFAULT NULL COMMENT '工商营业执照注册号',
  `area_code` varchar(6) DEFAULT NULL COMMENT '注册地区编码',
  `address` varchar(200) DEFAULT NULL COMMENT '企业营业地址',
  `zip_code` varchar(6) DEFAULT NULL COMMENT '邮政编码',
  `legal_man` varchar(50) DEFAULT NULL COMMENT '法定代表人姓名',
  `legal_man_duty` varchar(50) DEFAULT NULL COMMENT '法定代表人职务',
  `legal_man_pro_title` varchar(50) DEFAULT NULL COMMENT '法定代表人职称',
  `legal_man_idcard_type` varchar(2) DEFAULT NULL COMMENT '法定代表人证件类型',
  `legal_man_idcard_number` varchar(30) DEFAULT NULL COMMENT '法定代表人证件号码',
  `reg_capital` DECIMAL(18,4) DEFAULT NULL COMMENT '注册资本',
  `fact_reg_capital` DECIMAL(18,4) DEFAULT NULL COMMENT '实收资本',
  `capital_currency_type` varchar(255) DEFAULT NULL COMMENT '资本币种',
  `register_date` datetime DEFAULT NULL COMMENT '注册日期',
  `establish_date` datetime DEFAULT NULL COMMENT '成立日期',
  `office_phone` varchar(20) DEFAULT NULL COMMENT '办公电话',
  `fax_number` varchar(20) DEFAULT NULL COMMENT '传真号码',
  `link_man` varchar(50) DEFAULT NULL COMMENT '联系人姓名',
  `link_phone` varchar(50) DEFAULT NULL COMMENT '联系人电话',
  `email` varchar(100) DEFAULT NULL COMMENT '企业邮箱',
  `web_site` varchar(200) DEFAULT NULL COMMENT '企业网址',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '企业基本信息';

DROP TABLE IF EXISTS  `thf_project_corp_info`;
CREATE TABLE `thf_project_corp_info` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `local_project_code` varchar(32) DEFAULT NULL COMMENT '本地项目编号',
  `project_code` varchar(20) DEFAULT NULL COMMENT '项目编码',
  `project_name` varchar(18) DEFAULT NULL COMMENT '项目名称',
  `corp_code` varchar(18) DEFAULT NULL COMMENT '统一社会信用代码',
  `corp_name` varchar(200) DEFAULT NULL COMMENT '企业名称',
  `corp_type` varchar(3) DEFAULT NULL COMMENT '企业登记注册类型',
  `entry_time` datetime DEFAULT NULL COMMENT '进场时间',
  `exit_time` datetime DEFAULT NULL COMMENT '退场时间',
  `pm_name` varchar(50) DEFAULT NULL COMMENT '项目经理名称',
  `pm_idcard_type` varchar(2) DEFAULT NULL COMMENT '项目经理证件类型',
  `pm_idcard_number` varchar(30) DEFAULT NULL COMMENT '项目经理证件号码',
  `pm_phone` varchar(50) DEFAULT NULL COMMENT '项目经理电话',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '项目参建单位';

DROP TABLE IF EXISTS  `thf_project_config`;
CREATE TABLE `thf_project_config` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `local_project_code` varchar(32) NULL COMMENT '本地项目编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编码',
  `secret` varchar(255) DEFAULT NULL COMMENT '项目秘钥',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '项目配置';

DROP TABLE IF EXISTS  `thf_team_master`;
CREATE TABLE `thf_team_master` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `team_sys_no` INT NULL COMMENT '班组编号',
  `local_project_code` varchar(32) DEFAULT NULL COMMENT '本地项目编号',
  `project_code` varchar(20) DEFAULT NULL COMMENT '项目编码',
  `corp_code` varchar(18) DEFAULT NULL COMMENT '班组所在企业统一社会信用代码',
  `corp_name` varchar(200) DEFAULT NULL COMMENT '班组所在企业名称',
  `team_name` varchar(100) DEFAULT NULL COMMENT '班组名称',
  `team_leader_name` varchar(50) DEFAULT NULL COMMENT '班组长姓名',
  `team_leader_phone` varchar(50) DEFAULT NULL COMMENT '班组长联系电话',
  `team_leader_idcard_type` varchar(2) DEFAULT NULL COMMENT '班组长证件类型',
  `team_leader_id_number` varchar(30) DEFAULT NULL COMMENT '班组长证件号码',
  `responsible_person_name` varchar(50) DEFAULT NULL COMMENT '责任人姓名',
  `responsible_person_phone` varchar(50) DEFAULT NULL COMMENT '责任人联系电话',
  `responsible_person_idcard_type` varchar(2) DEFAULT NULL COMMENT '责任人证件类型',
  `responsible_person_id_number` varchar(30) DEFAULT NULL COMMENT '责任人证件号码',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `entry_time` datetime DEFAULT NULL COMMENT '进场日期',
  `exit_time` datetime DEFAULT NULL COMMENT '退场日期',
  `parent_code` varchar(30) DEFAULT NULL COMMENT '上级部门编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '班组';

DROP TABLE IF EXISTS  `thf_team_master`;
CREATE TABLE `thf_team_master` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `team_sys_no` INT NULL COMMENT '班组编号',
  `local_project_code` varchar(32) DEFAULT NULL COMMENT '本地项目编号',
  `project_code` varchar(20) DEFAULT NULL COMMENT '项目编码',
  `corp_code` varchar(18) DEFAULT NULL COMMENT '班组所在企业统一社会信用代码',
  `corp_name` varchar(200) DEFAULT NULL COMMENT '班组所在企业名称',
  `team_name` varchar(100) DEFAULT NULL COMMENT '班组名称',
  `team_leader_name` varchar(50) DEFAULT NULL COMMENT '班组长姓名',
  `team_leader_phone` varchar(50) DEFAULT NULL COMMENT '班组长联系电话',
  `team_leader_idcard_type` varchar(2) DEFAULT NULL COMMENT '班组长证件类型',
  `team_leader_id_number` varchar(30) DEFAULT NULL COMMENT '班组长证件号码',
  `responsible_person_name` varchar(50) DEFAULT NULL COMMENT '责任人姓名',
  `responsible_person_phone` varchar(50) DEFAULT NULL COMMENT '责任人联系电话',
  `responsible_person_idcard_type` varchar(2) DEFAULT NULL COMMENT '责任人证件类型',
  `responsible_person_id_number` varchar(30) DEFAULT NULL COMMENT '责任人证件号码',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `entry_time` datetime DEFAULT NULL COMMENT '进场日期',
  `exit_time` datetime DEFAULT NULL COMMENT '退场日期',
  `parent_code` varchar(30) DEFAULT NULL COMMENT '上级部门编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '班组';

DROP TABLE IF EXISTS  `thf_project_worker`;
CREATE TABLE `thf_project_worker` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `local_project_code` varchar(32) DEFAULT NULL COMMENT '本地项目编号',
  `project_code` varchar(20) DEFAULT NULL COMMENT '项目编码',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `corp_code` varchar(18) DEFAULT NULL COMMENT '班组所在企业统一社会信用代码',
  `corp_name` varchar(200) DEFAULT NULL COMMENT '班组所在企业名称',
  `team_sys_no` INT DEFAULT NULL COMMENT '班组编号',
  `team_name` varchar(100) DEFAULT NULL COMMENT '班组名称',
  `worker_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `worker_name` varchar(50) DEFAULT NULL COMMENT '工人姓名',
  `worker_mobile` varchar(32) DEFAULT NULL COMMENT '员工手机号',
  `is_team_leader` TINYINT(1) DEFAULT NULL COMMENT '是否班组长',
  `idcard_type` varchar(2) DEFAULT NULL COMMENT '证件类型',
  `idcard_number` varchar(30) DEFAULT NULL COMMENT '证件号码',
  `work_type` varchar(4) DEFAULT NULL COMMENT '工种',
  `worker_role` INT DEFAULT NULL COMMENT '工人类型',
  `type` varchar(2) DEFAULT NULL COMMENT '类别（直招工人，劳务工人，包工工人，内勤人员）',
  `entry_time` datetime DEFAULT NULL COMMENT '进场时间',
  `exit_time` datetime DEFAULT NULL COMMENT '退场时间',
  `entry_attachment_url` varchar(300) DEFAULT NULL COMMENT '进场确认附件',
  `exit_attachment_url` varchar(300) DEFAULT NULL COMMENT '退场确认附件',
  `issue_card_date` datetime DEFAULT NULL COMMENT '制卡时间',
  `issue_card_pic_url` varchar(300) DEFAULT NULL COMMENT '制卡采集照片',
  `card_number` varchar(30) DEFAULT NULL COMMENT '考勤卡号',
  `pay_roll_bank_card_number` varchar(30) DEFAULT NULL COMMENT '发放工资银行卡号',
  `pay_roll_bank_name` varchar(50) DEFAULT NULL COMMENT '发放工资银行名称',
  `pay_roll_top_bank_name` varchar(50) DEFAULT NULL COMMENT '发放工资总行名称',
  `bank_link_number` varchar(30) DEFAULT NULL COMMENT '工资卡银行联号',
  `pay_roll_top_bank_code` varchar(4) DEFAULT NULL COMMENT '工资卡银行代码',
  `has_contract` TINYINT(1) DEFAULT NULL COMMENT '是否有劳动合同',
  `has_buy_insurance` TINYINT(1) DEFAULT NULL COMMENT '有无购买工伤或意外伤害保险',
  `position` varchar(64) DEFAULT NULL COMMENT '所在职位',
  `salary` bigint(20) DEFAULT NULL COMMENT '日薪',
  `cut_amount` bigint(20) DEFAULT '0' COMMENT '扣款规则',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `salary_status` varchar(4) DEFAULT NULL COMMENT '工资发放状态',
  `join_datetime` datetime DEFAULT NULL COMMENT '入职时间',
  `leaving_datetime` datetime DEFAULT NULL COMMENT '离职时间',
  `start_datetime` datetime DEFAULT NULL COMMENT '最近一次请假开始时间',
  `last_leaving_days` int(11) DEFAULT NULL COMMENT '最近一次请假天数',
  `end_datetime` datetime DEFAULT NULL COMMENT '最近一次请假结束时间',
  `total_leaving_days` decimal(10,1) DEFAULT '0.0' COMMENT '累积请假天数',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '班组人员';

DROP TABLE IF EXISTS  `thf_project_worker_entry_exit_history`;
CREATE TABLE `thf_project_worker_entry_exit_history` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `local_project_code` varchar(32) DEFAULT NULL COMMENT '本地项目编号',
  `project_code` varchar(20) DEFAULT NULL COMMENT '项目编码',
  `corp_code` varchar(18) DEFAULT NULL COMMENT '班组所在企业统一社会信用代码',
  `corp_name` varchar(200) DEFAULT NULL COMMENT '班组所在企业名称',
  `team_sys_no` INT NULL COMMENT '班组编号',
  `worker_code` varchar(50) DEFAULT NULL COMMENT '员工编号',
  `worker_name` varchar(50) DEFAULT NULL COMMENT '员工姓名',
  `position` varchar(2) DEFAULT NULL COMMENT '职位',
  `join_datetime` datetime DEFAULT NULL COMMENT '入职时间',
  `leaving_datetime` datetime DEFAULT NULL COMMENT '离职时间',
  `idcard_type` varchar(2) DEFAULT NULL COMMENT '证件类型',
  `idcard_number` varchar(50) DEFAULT NULL COMMENT '证件号码',
  `date` datetime DEFAULT NULL COMMENT '时间',
  `type` INT DEFAULT NULL COMMENT '类型',
  `voucher_url` varchar(200) DEFAULT NULL COMMENT '凭证扫描件',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '人员进退场';

DROP TABLE IF EXISTS  `thf_worker_contract`;
CREATE TABLE `thf_worker_contract` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `local_project_code` varchar(32) DEFAULT NULL COMMENT '本地项目编号',
  `project_code` varchar(20) DEFAULT NULL COMMENT '项目编码',
  `project_name` varchar(200) DEFAULT NULL COMMENT '项目名称',
  `corp_code` varchar(18) DEFAULT NULL COMMENT '所属企业统一社会信用代码',
  `corp_name` varchar(200) DEFAULT NULL COMMENT '所属企业名称',
  `idcard_type` varchar(2) NULL COMMENT '证件类型',
  `idcard_number` varchar(30) DEFAULT NULL COMMENT '证件号码',
  `worker_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `worker_name` varchar(50) DEFAULT NULL COMMENT '员工名称',
  `worker_mobile` varchar(50) DEFAULT NULL COMMENT '员工手机号',
  `contract_code` varchar(50) DEFAULT NULL COMMENT '合同编号',
  `contract_period_type` INT DEFAULT NULL COMMENT '合同期限类型',
  `start_date` datetime DEFAULT NULL COMMENT '开始日期',
  `end_date` datetime DEFAULT NULL COMMENT '结束时期',
  `unit` int DEFAULT NULL COMMENT '计量单位',
  `unit_price` DECIMAL(18,2) DEFAULT NULL COMMENT '计量单位',
  `content_pic` varchar(200) DEFAULT NULL COMMENT '合同照片',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '劳动合同';

DROP TABLE IF EXISTS  `thf_worker_attendance`;
CREATE TABLE `thf_worker_attendance` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `local_project_code` varchar(32) DEFAULT NULL COMMENT '本地项目编号',
  `project_code` varchar(20) DEFAULT NULL COMMENT '项目编码',
  `project_name` varchar(200) DEFAULT NULL COMMENT '项目名称',
  `team_sys_no` INT DEFAULT NULL COMMENT '班组编号',
  `team_name` varchar(200) DEFAULT NULL COMMENT '班组名称',
  `worker_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `worker_name` varchar(50) DEFAULT NULL COMMENT '员工名称',
  `idcard_type` varchar(2) DEFAULT NULL COMMENT '证件类型',
  `idcard_number` varchar(30) DEFAULT NULL COMMENT '证件号码',
  `status` varchar(4) DEFAULT NULL COMMENT '出工状态（正常，迟到，早退）',
  `date` datetime DEFAULT NULL COMMENT '考勤时间',
  `direction` varchar(2) DEFAULT NULL COMMENT '进出方向',
  `image_url` varchar(100) DEFAULT NULL COMMENT '刷卡近照',
  `channel` varchar(50) DEFAULT NULL COMMENT '通道',
  `attend_type` varchar(4) DEFAULT NULL COMMENT '通行方式',
  `lng` DECIMAL(18,15) DEFAULT NULL COMMENT '经度',
  `lat` DECIMAL(18,15) DEFAULT NULL COMMENT '纬度',
  `create_datetime` datetime DEFAULT NULL COMMENT '生成时间',
  `start_datetime` datetime DEFAULT NULL COMMENT '上班时间',
  `end_datetime` datetime DEFAULT NULL COMMENT '下班时间',
  `settle_datetime` datetime DEFAULT NULL COMMENT '结算时间',
  `sim` decimal(4,2) DEFAULT NULL COMMENT '人脸识别准确率',
  `terminal_code` varchar(50) DEFAULT NULL COMMENT '考勤机编号',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '人员考勤';

DROP TABLE IF EXISTS  `thf_pay_roll`;
CREATE TABLE `thf_pay_roll` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `pay_roll_code` varchar(22) DEFAULT NULL COMMENT '工资单编码',
  `local_project_code` varchar(32) DEFAULT NULL COMMENT '本地项目编号',
  `project_code` varchar(20) DEFAULT NULL COMMENT '项目编码',
  `corp_code` varchar(18) DEFAULT NULL COMMENT '所属企业统一社会信用代码',
  `corp_name` varchar(200) DEFAULT NULL COMMENT '所属企业名称',
  `team_sys_no` INT DEFAULT NULL COMMENT '班组编号',
  `pay_month` datetime DEFAULT NULL COMMENT '发放工资的年月',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '人员工资单';

DROP TABLE IF EXISTS  `thf_pay_roll_detail`;
CREATE TABLE `thf_pay_roll_detail` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `pay_roll_code` varchar(22) DEFAULT NULL COMMENT '工资单编码',
  `worker_name` varchar(50) DEFAULT NULL COMMENT '工人姓名',
  `idcard_type` varchar(2) DEFAULT NULL COMMENT '证件类型',
  `idcard_number` varchar(30) DEFAULT NULL COMMENT '证件号码',
  `days` INT DEFAULT NULL COMMENT '出勤天数',
  `work_hours` DECIMAL(5,2) DEFAULT NULL COMMENT '总工时',
  `pay_roll_bank_card_number` varchar(30) DEFAULT NULL COMMENT '工人工资卡号',
  `pay_roll_bank_code` varchar(4) DEFAULT NULL COMMENT '工人工资卡银行代码',
  `pay_roll_bank_name` varchar(50) DEFAULT NULL COMMENT '工人工资卡开户行名称',
  `pay_bank_card_number` varchar(30) DEFAULT NULL COMMENT '工资代发银行卡号',
  `pay_bank_code` varchar(4) DEFAULT NULL COMMENT '工资代发银行代码',
  `pay_bank_name` varchar(50) DEFAULT NULL COMMENT '工资代发开户行名称',
  `total_pay_amount` DECIMAL(16,2) DEFAULT NULL COMMENT '应发金额',
  `actual_amount` DECIMAL(16,2) DEFAULT NULL COMMENT '实发金额',
  `is_back_pay` TINYINT(1) DEFAULT NULL COMMENT '是否是补发',
  `balance_date` datetime DEFAULT NULL COMMENT '发放日期',
  `third_pay_roll_code` varchar(50) DEFAULT NULL COMMENT '第三方工资单编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '人员工资单明细';


