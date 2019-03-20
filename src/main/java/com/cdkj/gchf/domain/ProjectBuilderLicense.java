package com.cdkj.gchf.domain;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 项目许可证
* @author: jiafr 
* @since: 2019-03-19 19:59:58
* @history:
*/
public class ProjectBuilderLicense extends ABaseDO {

	private static final long serialVersionUID = 1L;

	// 编号
	private String code;

	// 项目编号
	private String projectCode;

	// 项目名称
	private String prjName;

	// 施工许可证号
	private String builderLicenseNum;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setPrjName(String prjName) {
		this.prjName = prjName;
	}

	public String getPrjName() {
		return prjName;
	}

	public void setBuilderLicenseNum(String builderLicenseNum) {
		this.builderLicenseNum = builderLicenseNum;
	}

	public String getBuilderLicenseNum() {
		return builderLicenseNum;
	}

}