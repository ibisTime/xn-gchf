package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Brand;

public interface IBrandBO extends IPaginableBO<Brand> {

    public String saveBrand(Brand data);

    public Brand getBrand(String code);

    public int editBrand(Brand data);

    public List<Brand> queryBrand(Brand condition);
}
