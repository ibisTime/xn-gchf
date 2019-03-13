package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.ICorpBasicinfoDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.CorpBasicinfo;

@Repository("corpBasicinfoDAOImpl")
public class CorpBasicinfoDAOImpl extends AMybatisTemplate
        implements ICorpBasicinfoDAO {

    @Override
    public int insert(CorpBasicinfo data) {
        return super.insert(NAMESPACE.concat("insert_corpBasicinfo"), data);
    }

    @Override
    public int delete(CorpBasicinfo data) {
        return super.delete(NAMESPACE.concat("delete_corpBasicinfo"), data);
    }

    @Override
    public CorpBasicinfo select(CorpBasicinfo condition) {
        return super.select(NAMESPACE.concat("select_corpBasicinfo"), condition,
            CorpBasicinfo.class);
    }

    @Override
    public long selectTotalCount(CorpBasicinfo condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_corpBasicinfo_count"), condition);
    }

    @Override
    public List<CorpBasicinfo> selectList(CorpBasicinfo condition) {
        return super.selectList(NAMESPACE.concat("select_corpBasicinfo"),
            condition, CorpBasicinfo.class);
    }

    @Override
    public List<CorpBasicinfo> selectList(CorpBasicinfo condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_corpBasicinfo"), start,
            count, condition, CorpBasicinfo.class);
    }

    @Override
    public int update(CorpBasicinfo corpBasicinfo) {
        return super.update(NAMESPACE.concat("update_corpBasicinfo"),
            corpBasicinfo);
    }

}
