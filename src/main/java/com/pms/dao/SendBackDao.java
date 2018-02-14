package com.pms.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class SendBackDao {

	JdbcTemplate template; 
	
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}  
	public int updateCurrentPhase(int apprempid){  
	    String sql="update appr_empl set curr_phase_id= curr_phase_id - 1 where ApprEmpId="+apprempid ;  
	    return template.update(sql);  
	}
	public int updatePhaseStatus(int apprempid, int apprphaseid){  
		int phase_id=apprphaseid-1;
	    String sql="update appr_empl_flow set Status = 1 where apprEmpId= "+apprempid+" and phaseid="+phase_id;  
	    return template.update(sql);  
	}
}

