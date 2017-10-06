package com.hrm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.hrm.domain.Job;
import com.hrm.util.common.HrmConstants;

public class JobDynaSqlProvider {
	// 分页动态查询
		public String selectWhitParam(Map<String, Object> params){
			String sql =  new SQL(){
				{
					SELECT("*");
					FROM(HrmConstants.JOBTABLE);
					if(params.get("job") != null){
						Job job = (Job) params.get("job");
						if(job.getName() != null && !job.getName().equals("")){
							WHERE("  name LIKE CONCAT ('%',#{job.name},'%') ");
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
					FROM(HrmConstants.JOBTABLE);
					if(params.get("job") != null){
						Job job = (Job) params.get("job");
						if(job.getName() != null && !job.getName().equals("")){
							WHERE("  name LIKE CONCAT ('%',#{job.name},'%') ");
						}
					}
				}
			}.toString();
		}	
		// 动态插入
		public String insertJob(Job job){
			
			return new SQL(){
				{
					INSERT_INTO(HrmConstants.JOBTABLE);
					if(job.getName() != null && !job.getName().equals("")){
						VALUES("name", "#{name}");
					}
					if(job.getRemark() != null && !job.getRemark().equals("")){
						VALUES("remark", "#{remark}");
					}
				}
			}.toString();
		}
		// 动态更新
		public String updateJob(Job job){
			
			return new SQL(){
				{
					UPDATE(HrmConstants.JOBTABLE);
					if(job.getName() != null){
						SET(" name = #{name} ");
					}
					if(job.getRemark() != null){
						SET(" remark = #{remark} ");
					}
					WHERE(" id = #{id} ");
				}
			}.toString();
		}
}
