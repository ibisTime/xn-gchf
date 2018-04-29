package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Progress;




public interface IProgressBO extends IPaginableBO<Progress> {


	public String saveProgress(Progress data);


	public int removeProgress(String code);


	public int refreshProgress(Progress data);


	public List<Progress> queryProgressList(Progress condition);


	public Progress getProgress(String code);


}