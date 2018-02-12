package PMSNEW.PMSNEW;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MailDao {

JdbcTemplate template; 
	
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}  
	public List<MailTemplate> getMailDetails(int apprempid,int phaseid){  
	    return template.query("select * from view_appr_email where apprempid="+apprempid+" and phaseid="+phaseid,new RowMapper<MailTemplate>(){  
	        public MailTemplate mapRow(ResultSet rs, int row) throws SQLException {  
	        	MailTemplate mt=new MailTemplate(); 
		           mt.setFromName(rs.getString(3));
		           mt.setAppraisee(rs.getString(4));
		           mt.setmailToName(rs.getString(5));
		           mt.setMailTo(rs.getString(8));
		           return mt;  
	        }  
	    });  
	} 
} 
