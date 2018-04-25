package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IDepartmentDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.Department;



//CHECK 。。。 
@Repository("DepartmentDAOImpl")
public class DepartmentDAOImpl extends AMybatisTemplate implements IDepartmentDAO {


	@Override
	public int insert(Department data) {
		return super.insert(NAMESPACE.concat("insert_Department"), data);
	}


	@Override
	public int delete(Department data) {
		return super.delete(NAMESPACE.concat("delete_Department"), data);
	}


	@Override
	public Department select(Department condition) {
		return super.select(NAMESPACE.concat("select_Department"), condition,Department.class);
	}


	@Override
	public long selectTotalCount(Department condition) {
		return super.selectTotalCount(NAMESPACE.concat("select_Department_count"),condition);
	}


	@Override
	public List<Department> selectList(Department condition) {
		return super.selectList(NAMESPACE.concat("select_Department"), condition,Department.class);
	}


	@Override
	public List<Department> selectList(Department condition, int start, int count) {
		return super.selectList(NAMESPACE.concat("select_Department"), start, count,condition, Department.class);
	}


}