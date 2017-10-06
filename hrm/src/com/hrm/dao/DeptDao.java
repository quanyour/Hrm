package com.hrm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.hrm.dao.provider.DeptDynaSqlProvider;
import com.hrm.domain.Dept;
import com.hrm.util.common.HrmConstants;

public interface DeptDao {

	// 动态查询
		@SelectProvider(type=DeptDynaSqlProvider.class,method="selectWhitParam")
		List<Dept> selectByPage(Map<String, Object> params);
		
		@SelectProvider(type=DeptDynaSqlProvider.class,method="count")
		Integer count(Map<String, Object> params);
		
		@Select("select * from "+HrmConstants.DEPTTABLE+" ")
		List<Dept> selectAllDept();
		
		@Select("select * from "+HrmConstants.DEPTTABLE+" where ID = #{id}")
		Dept selectById(int id);

		// 根据id删除部门
		@Delete(" delete from "+HrmConstants.DEPTTABLE+" where id = #{id} ")
		void deleteById(Integer id);
		
		// 动态插入部门
		@SelectProvider(type=DeptDynaSqlProvider.class,method="insertDept")
		void save(Dept dept);
		
		// 动态修改用户
		@SelectProvider(type=DeptDynaSqlProvider.class,method="updateDept")
		void update(Dept dept);
	
}
