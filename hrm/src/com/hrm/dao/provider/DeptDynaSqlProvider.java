package com.hrm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.hrm.domain.Dept;
import com.hrm.util.common.HrmConstants;

public class DeptDynaSqlProvider {
	// 分页动态查询
		public String selectWhitParam(Map<String, Object> params){
			String sql =  new SQL(){
				{
					SELECT("*");
					FROM(HrmConstants.DEPTTABLE);
					if(params.get("dept") != null){
						Dept dept = (Dept) params.get("dept");
						if(dept.getName() != null && !dept.getName().equals("")){
							WHERE("  name LIKE CONCAT ('%',#{dept.name},'%') ");
						}
					}
				}
			}.toString();
			
			if(params.get("pageModel") != null){
				sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
			}
			
			return sql;
		}	
		// 动态查询总数量
		public String count(Map<String, Object> params){
			return new SQL(){
				{
					SELECT("count(*)");
					FROM(HrmConstants.DEPTTABLE);
					if(params.get("dept") != null){
						Dept dept = (Dept) params.get("dept");
						if(dept.getName() != null && !dept.getName().equals("")){
							WHERE("  name LIKE CONCAT ('%',#{dept.name},'%') ");
						}
					}
				}
			}.toString();
		}	
		// 动态插入
		public String insertDept(Dept dept){
			
			return new SQL(){
				{
					INSERT_INTO(HrmConstants.DEPTTABLE);
					if(dept.getName() != null && !dept.getName().equals("")){
						VALUES("name", "#{name}");
					}
					if(dept.getRemark() != null && !dept.getRemark().equals("")){
						VALUES("remark", "#{remark}");
					}
				}
			}.toString();
		}
		// 动态更新
		public String updateDept(Dept dept){
			
			return new SQL(){
				{
					UPDATE(HrmConstants.DEPTTABLE);
					if(dept.getName() != null){
						SET(" name = #{name} ");
					}
					if(dept.getRemark() != null){
						SET(" remark = #{remark} ");
					}
					WHERE(" id = #{id} ");
				}
			}.toString();
		}
}
