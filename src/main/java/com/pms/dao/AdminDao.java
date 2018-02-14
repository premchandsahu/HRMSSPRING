package com.pms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.pms.model.EmpList;

public class AdminDao {
	
JdbcTemplate template; 

public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}  
	public List<EmpList> getDesignations(){  
	    return template.query("select distinct ohrm_job_title.job_title from ohrm_job_title inner join hs_hr_employee on hs_hr_employee.job_title_code=ohrm_job_title.id",new RowMapper<EmpList>(){  
	        public EmpList mapRow(ResultSet rs, int row) throws SQLException {  
	        	EmpList el=new EmpList(); 
		           el.setDesignation(rs.getString("job_title"));
		           return el;  
	        }  
	    });  
	} 
	public List<EmpList> getEmpList(){  
	    return template.query("select * from view_hrscore",new RowMapper<EmpList>(){  
	        public EmpList mapRow(ResultSet rs, int row) throws SQLException {  
	        	EmpList el=new EmpList(); 
		          el.setApprempid(rs.getInt("ApprEmpId"));
		          el.setJob_title(rs.getString("job_title"));
		          el.setCurrent_phase(rs.getString("current_phase"));
		          el.setSelf_empname(rs.getString("selfempname"));
		          el.setPhase1_rating(rs.getDouble("Phase1Rating"));
		          el.setAppraiser_name(rs.getString("appraiserempname"));
		          el.setPhase2_rating(rs.getDouble("Phase2Rating"));;
		          el.setReviewer_name(rs.getString("reviewerempname"));
		          el.setPhase3_rating(rs.getDouble("Phase3Rating"));
		          el.setCurr_phaseid(rs.getInt("curr_phase_id"));
		          el.setPhase1mail(rs.getString("phase1email"));
		          el.setPhase2mail(rs.getString("phase2email"));
		          el.setPhase3mail(rs.getString("phase3email"));
		           return el;  
	        }  
	    });  
	} 
	public List<EmpList> getEmpListByTitle(String title){  
	    return template.query("select * from view_hrscore where job_title='"+title+"'",new RowMapper<EmpList>(){  
	        public EmpList mapRow(ResultSet rs, int row) throws SQLException {  
	        	EmpList el=new EmpList(); 
	        	 el.setApprempid(rs.getInt("ApprEmpId"));
		          el.setJob_title(rs.getString("job_title"));
		          el.setCurrent_phase(rs.getString("current_phase"));
		          el.setSelf_empname(rs.getString("selfempname"));
		          el.setPhase1_rating(rs.getDouble("Phase1Rating"));
		          el.setAppraiser_name(rs.getString("appraiserempname"));
		          el.setPhase2_rating(rs.getDouble("Phase2Rating"));;
		          el.setReviewer_name(rs.getString("reviewerempname"));
		          el.setPhase3_rating(rs.getDouble("Phase3Rating"));
		          el.setCurr_phaseid(rs.getInt("curr_phase_id"));
		          el.setPhase1mail(rs.getString("phase1email"));
		          el.setPhase2mail(rs.getString("phase2email"));
		          el.setPhase3mail(rs.getString("phase3email"));
		           return el;  
	        }  
	    });  
	} 
} 
