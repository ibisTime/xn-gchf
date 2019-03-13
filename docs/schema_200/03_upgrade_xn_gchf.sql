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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `thf_project_corp_info`;
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
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

