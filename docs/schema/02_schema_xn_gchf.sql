DROP TABLE IF EXISTS `tqy_corp_basicinfo`;
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
  `reg_capital` decimal(18,4) DEFAULT NULL COMMENT '注册资本',
  `fact_reg_capital` decimal(18,4) DEFAULT NULL COMMENT '实收资本',
  `capital_currency_type` varchar(255) DEFAULT NULL COMMENT '资本币种',
  `register_date` datetime DEFAULT NULL COMMENT '注册日期',
  `establish_date` datetime DEFAULT NULL COMMENT '成立日期',
  `office_phone` varchar(20) DEFAULT NULL COMMENT '办公电话',
  `fax_number` varchar(20) DEFAULT NULL COMMENT '传真号码',
  `link_man` varchar(50) DEFAULT NULL COMMENT '联系人姓名',
  `link_phone` varchar(50) DEFAULT NULL COMMENT '联系人电话',
  `email` varchar(100) DEFAULT NULL COMMENT '企业邮箱',
  `web_site` varchar(200) DEFAULT NULL COMMENT '企业网址',
  `upload_status` varchar(4) DEFAULT NULL COMMENT '上传状态',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业基本信息';

DROP TABLE IF EXISTS `thf_project`;
CREATE TABLE `thf_project` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `contractor_corp_code` varchar(18) DEFAULT NULL COMMENT '总承包单位统一社会信用代码',
  `contractor_corp_name` varchar(200) DEFAULT NULL COMMENT '总承包单位名称',
  `name` varchar(200) DEFAULT NULL COMMENT '项目名称',
  `description` varchar(1000) DEFAULT NULL COMMENT '项目简介',
  `category` varchar(2) DEFAULT NULL COMMENT '项目分类',
  `build_corp_name` varchar(200) DEFAULT NULL COMMENT '建设单位名称',
  `build_corp_code` varchar(18) DEFAULT NULL COMMENT '建设单位统一社会信用代码',
  `build_plan_num` varchar(50) DEFAULT NULL COMMENT '建设用地规划许可证编号',
  `prj_plan_num` varchar(50) DEFAULT NULL COMMENT '建设工程规划许可证编号',
  `area_code` varchar(6) DEFAULT NULL COMMENT '项目所在地',
  `invest` decimal(18,6) DEFAULT NULL COMMENT '总投资',
  `building_area` decimal(18,2) DEFAULT NULL COMMENT '总面积',
  `building_length` decimal(18,2) DEFAULT NULL COMMENT '总长度',
  `start_date` datetime DEFAULT NULL COMMENT '开工日期',
  `complete_date` datetime DEFAULT NULL COMMENT '竣工日期',
  `link_man` varchar(50)  DEFAULT NULL COMMENT '联系人姓名',
  `link_phone` varchar(50) DEFAULT NULL COMMENT '联系人电话',
  `prj_status` varchar(3) DEFAULT '0' COMMENT '项目状态',
  `lng` decimal(18,15) DEFAULT NULL COMMENT '经度',
  `lat` decimal(18,15) DEFAULT NULL COMMENT '纬度',
  `address` varchar(200) DEFAULT NULL COMMENT '项目地址',
  `approval_num` varchar(50) DEFAULT NULL COMMENT '立项文号',
  `approval_level_num` varchar(3) DEFAULT NULL COMMENT '立项级别',
  `prj_size` varchar(3) DEFAULT NULL COMMENT '建设规模',
  `property_num` varchar(3) DEFAULT NULL COMMENT '建设性质',
  `prj_num` varchar(3) DEFAULT NULL COMMENT '工程用途',
  `nation_num` varchar(3) DEFAULT NULL COMMENT '国籍或地区',
  `third_party_project_code` varchar(50) DEFAULT NULL COMMENT '第三方项目编码',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目基本信息';

DROP TABLE IF EXISTS `thf_project_builder_license`;
CREATE TABLE `thf_project_builder_license` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `prj_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `builder_license_num` varchar(50) DEFAULT NULL COMMENT '施工许可证号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目施工许可证';

DROP TABLE IF EXISTS `thf_project_config`;
CREATE TABLE `thf_project_config` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `local_project_code` varchar(32) DEFAULT NULL COMMENT '本地项目编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编码',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `password` varchar(32) DEFAULT NULL COMMENT '项目密码',
  `secret` varchar(255) DEFAULT NULL COMMENT '项目秘钥',
  `status` varchar(4) DEFAULT NULL COMMENT '本地项目编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目配置';

DROP TABLE IF EXISTS `thf_project_corp_info`;
CREATE TABLE `thf_project_corp_info` (
  `code` varchar(32) NOT NULL COMMENT '编号',
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
  `upload_status` varchar(4) DEFAULT NULL COMMENT '上传状态',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目参建单位';

DROP TABLE IF EXISTS `thf_team_master`;
CREATE TABLE `thf_team_master` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `team_sys_no` int(11) DEFAULT NULL COMMENT '班组编号',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班组';

DROP TABLE IF EXISTS `thf_project_worker`;
CREATE TABLE `thf_project_worker` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `local_project_code` varchar(32) DEFAULT NULL COMMENT '本地项目编号',
  `project_code` varchar(20) DEFAULT NULL COMMENT '项目编码',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `corp_code` varchar(18) DEFAULT NULL COMMENT '班组所在企业统一社会信用代码',
  `corp_name` varchar(200) DEFAULT NULL COMMENT '班组所在企业名称',
  `team_sys_no` int(11) DEFAULT NULL COMMENT '班组编号',
  `team_name` varchar(100) DEFAULT NULL COMMENT '班组名称',
  `worker_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `worker_name` varchar(50) DEFAULT NULL COMMENT '工人姓名',
  `worker_mobile` varchar(32) DEFAULT NULL COMMENT '员工手机号',
  `is_team_leader` tinyint(1) DEFAULT NULL COMMENT '是否班组长',
  `idcard_type` varchar(2) DEFAULT NULL COMMENT '证件类型',
  `idcard_number` varchar(30) DEFAULT NULL COMMENT '证件号码',
  `work_type` varchar(4) DEFAULT NULL COMMENT '工种',
  `worker_role` int(11) DEFAULT NULL COMMENT '工人类型',
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
  `has_contract` tinyint(1) DEFAULT NULL COMMENT '是否有劳动合同',
  `has_buy_insurance` tinyint(1) DEFAULT NULL COMMENT '有无购买工伤或意外伤害保险',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班组人员';

DROP TABLE IF EXISTS `thf_project_worker_entry_exit_history`;
CREATE TABLE `thf_project_worker_entry_exit_history` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `local_project_code` varchar(32) DEFAULT NULL COMMENT '本地项目编号',
  `project_code` varchar(20) DEFAULT NULL COMMENT '项目编码',
  `corp_code` varchar(18) DEFAULT NULL COMMENT '班组所在企业统一社会信用代码',
  `corp_name` varchar(200) DEFAULT NULL COMMENT '班组所在企业名称',
  `team_sys_no` int(11) DEFAULT NULL COMMENT '班组编号',
  `worker_code` varchar(50) DEFAULT NULL COMMENT '员工编号',
  `worker_name` varchar(50) DEFAULT NULL COMMENT '员工姓名',
  `position` varchar(2) DEFAULT NULL COMMENT '职位',
  `join_datetime` datetime DEFAULT NULL COMMENT '入职时间',
  `leaving_datetime` datetime DEFAULT NULL COMMENT '离职时间',
  `idcard_type` varchar(2) DEFAULT NULL COMMENT '证件类型',
  `idcard_number` varchar(50) DEFAULT NULL COMMENT '证件号码',
  `date` datetime DEFAULT NULL COMMENT '时间',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `voucher_url` varchar(200) DEFAULT NULL COMMENT '凭证扫描件',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员进退场';

DROP TABLE IF EXISTS `thf_pay_roll`;
CREATE TABLE `thf_pay_roll` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `pay_roll_code` varchar(22) DEFAULT NULL COMMENT '工资单编码',
  `local_project_code` varchar(32) DEFAULT NULL COMMENT '本地项目编号',
  `project_code` varchar(20) DEFAULT NULL COMMENT '项目编码',
  `corp_code` varchar(18) DEFAULT NULL COMMENT '所属企业统一社会信用代码',
  `corp_name` varchar(200) DEFAULT NULL COMMENT '所属企业名称',
  `team_sys_no` int(11) DEFAULT NULL COMMENT '班组编号',
  `pay_month` datetime DEFAULT NULL COMMENT '发放工资的年月',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员工资单';

DROP TABLE IF EXISTS `thf_pay_roll_detail`;
CREATE TABLE `thf_pay_roll_detail` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `pay_roll_code` varchar(22) DEFAULT NULL COMMENT '工资单编码',
  `worker_name` varchar(50) DEFAULT NULL COMMENT '工人姓名',
  `idcard_type` varchar(2) DEFAULT NULL COMMENT '证件类型',
  `idcard_number` varchar(30) DEFAULT NULL COMMENT '证件号码',
  `days` int(11) DEFAULT NULL COMMENT '出勤天数',
  `work_hours` decimal(5,2) DEFAULT NULL COMMENT '总工时',
  `pay_roll_bank_card_number` varchar(30) DEFAULT NULL COMMENT '工人工资卡号',
  `pay_roll_bank_code` varchar(4) DEFAULT NULL COMMENT '工人工资卡银行代码',
  `pay_roll_bank_name` varchar(50) DEFAULT NULL COMMENT '工人工资卡开户行名称',
  `pay_bank_card_number` varchar(30) DEFAULT NULL COMMENT '工资代发银行卡号',
  `pay_bank_code` varchar(4) DEFAULT NULL COMMENT '工资代发银行代码',
  `pay_bank_name` varchar(50) DEFAULT NULL COMMENT '工资代发开户行名称',
  `total_pay_amount` decimal(16,2) DEFAULT NULL COMMENT '应发金额',
  `actual_amount` decimal(16,2) DEFAULT NULL COMMENT '实发金额',
  `is_back_pay` tinyint(1) DEFAULT NULL COMMENT '是否是补发',
  `balance_date` datetime DEFAULT NULL COMMENT '发放日期',
  `third_pay_roll_code` varchar(50) DEFAULT NULL COMMENT '第三方工资单编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员工资单明细';

DROP TABLE IF EXISTS `thf_subbranch`;
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

DROP TABLE IF EXISTS `thf_operator_guide`;
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

DROP TABLE IF EXISTS `thf_supervise`;
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

DROP TABLE IF EXISTS `thf_user`;
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

DROP TABLE IF EXISTS `thf_worker_info`;
CREATE TABLE `thf_worker_info` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(50) NOT NULL COMMENT '工人姓名',
  `id_card_type` char(2) NOT NULL COMMENT '证件类型',
  `id_card_number` varchar(30) NOT NULL COMMENT '证件号码',
  `gender` int(11) NOT NULL COMMENT '工人性别',
  `nation` varchar(10) NOT NULL COMMENT '民族',
  `edu_level` int(11) DEFAULT NULL COMMENT '学历',
  `degree` int(11) DEFAULT NULL COMMENT '学位',
  `worker_type` int(11) DEFAULT NULL COMMENT '类别',
  `birthday` datetime NOT NULL COMMENT '出生日期',
  `birth_place_code` char(6) NOT NULL COMMENT '籍贯 身份证号码前六位',
  `address` varchar(200) NOT NULL COMMENT '住址',
  `head_image_url` varchar(300) NOT NULL COMMENT '头像 二代身份证上面的头像',
  `politics_type` char(2) DEFAULT NULL COMMENT '政治面貌',
  `is_joined` tinyint(1) DEFAULT NULL COMMENT '是否加入工会',
  `joined_time` datetime DEFAULT NULL COMMENT '加入工会时间 已加入工会时，此字段必须有值',
  `cell_phone` varchar(20) NOT NULL COMMENT '手机号码',
  `culture_level_type` char(2) DEFAULT NULL COMMENT '文化程度',
  `specialty` varchar(200) DEFAULT NULL COMMENT '特长',
  `has_bad_medical_history` tinyint(1) NOT NULL COMMENT '否有重大病史',
  `urgent_link_man` varchar(50) DEFAULT NULL COMMENT '紧急联系人姓名',
  `urgent_link_man_phone` varchar(50) DEFAULT NULL COMMENT '紧急联系电话',
  `work_type_code` char(4) NOT NULL COMMENT '当前工种',
  `work_corp_name` varchar(200) DEFAULT NULL COMMENT '当前聘用企业',
  `work_date` datetime DEFAULT NULL COMMENT '开始工作日期',
  `marital_status` char(2) DEFAULT NULL COMMENT '婚姻状况',
  `grant_org` varchar(20) NOT NULL COMMENT '发证机关',
  `positive_id_card_image_url` varchar(300) DEFAULT NULL COMMENT '正面照 URL',
  `negative_id_card_image_url` varchar(300) DEFAULT NULL COMMENT '反面照 URL',
  `start_date` datetime DEFAULT NULL COMMENT '有效期开始日期',
  `expiry_date` datetime DEFAULT NULL COMMENT '有效期结束日期',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员实名信息数据表';

DROP TABLE IF EXISTS `thf_worker_attendance`;
CREATE TABLE `thf_worker_attendance` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `project_code` varchar(20) DEFAULT NULL COMMENT '项目编码',
  `project_name` varchar(200) DEFAULT NULL COMMENT '项目名称',
  `team_sys_no` int(11) DEFAULT NULL COMMENT '班组编号',
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
  `lng` decimal(18,15) DEFAULT NULL COMMENT '经度',
  `lat` decimal(18,15) DEFAULT NULL COMMENT '纬度',
  `create_datetime` datetime DEFAULT NULL COMMENT '生成时间',
  `start_datetime` datetime DEFAULT NULL COMMENT '上班时间',
  `end_datetime` datetime DEFAULT NULL COMMENT '下班时间',
  `settle_datetime` datetime DEFAULT NULL COMMENT '结算时间',
  `sim` decimal(4,2) DEFAULT NULL COMMENT '人脸识别准确率',
  `terminal_code` varchar(50) DEFAULT NULL COMMENT '考勤机编号',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员考勤';

DROP TABLE IF EXISTS `thf_worker_contract`;
CREATE TABLE `thf_worker_contract` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `project_code` varchar(20) DEFAULT NULL COMMENT '项目编码',
  `project_name` varchar(200) DEFAULT NULL COMMENT '项目名称',
  `corp_code` varchar(18) DEFAULT NULL COMMENT '所属企业统一社会信用代码',
  `corp_name` varchar(200) DEFAULT NULL COMMENT '所属企业名称',
  `idcard_type` varchar(2) DEFAULT NULL COMMENT '证件类型',
  `idcard_number` varchar(30) DEFAULT NULL COMMENT '证件号码',
  `worker_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `worker_name` varchar(50) DEFAULT NULL COMMENT '员工名称',
  `worker_mobile` varchar(50) DEFAULT NULL COMMENT '员工手机号',
  `contract_code` varchar(50) DEFAULT NULL COMMENT '合同编号',
  `contract_period_type` int(11) DEFAULT NULL COMMENT '合同期限类型',
  `start_date` datetime DEFAULT NULL COMMENT '开始日期',
  `end_date` datetime DEFAULT NULL COMMENT '结束时期',
  `unit` int(11) DEFAULT NULL COMMENT '计量单位',
  `unit_price` decimal(18,2) DEFAULT NULL COMMENT '计量单位',
  `content_pic` varchar(200) DEFAULT NULL COMMENT '合同照片',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='劳动合同';

DROP TABLE IF EXISTS `tsys_operate_log`;
CREATE TABLE `tsys_operate_log` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `ref_type` varchar(32) DEFAULT NULL COMMENT '关联类型',
  `ref_code` varchar(32) DEFAULT NULL COMMENT '关联编号',
  `operate` varchar(32) DEFAULT NULL COMMENT '操作名称',
  `operator` varchar(32) DEFAULT NULL COMMENT '操作人编号',
  `operator_name` varchar(32) DEFAULT NULL COMMENT '操作人名称',
  `operate_datetime` datetime DEFAULT NULL COMMENT '操作时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';

DROP TABLE IF EXISTS `tsys_config`;
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

DROP TABLE IF EXISTS `tsys_dict`;
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

DROP TABLE IF EXISTS `tsys_menu`;
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

DROP TABLE IF EXISTS `tsys_menu_role`;
CREATE TABLE `tsys_menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(96) DEFAULT NULL,
  `menu_code` varchar(96) DEFAULT NULL,
  `updater` varchar(96) DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `remark` varchar(765) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4800 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_role`;
CREATE TABLE `tsys_role` (
  `code` varchar(96) NOT NULL,
  `type` varchar(4) DEFAULT NULL,
  `name` varchar(96) DEFAULT NULL,
  `updater` varchar(96) DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `remark` varchar(765) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8
