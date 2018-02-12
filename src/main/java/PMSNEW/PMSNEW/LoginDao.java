package PMSNEW.PMSNEW;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import PMSNEW.PMSNEW.Login;
import PMSNEW.PMSNEW.Menu;

public class LoginDao {

JdbcTemplate template; 
	
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}  
	//public int checkLogin(Login p){  
	public int checkLogin(String pusername,String ppassword){
		int cnt=0;
		String sqlcnt="select count(*) uCount from ohrm_user where user_name='"+pusername+"'";
		cnt=template.queryForInt(sqlcnt);
		if (cnt==0) return 0; else
		{
			String sql="select user_name,user_password,isnull(apprempid,999999) as apprempid  from view_userapprempid where user_name='"+pusername+"'";
			Login glogin=template.queryForObject(sql,new LoginMapper());
			cnt=checkPassword(ppassword,glogin.getPassword());
			return glogin.getApprempid();
		}
		
	}  
	
	public List<Menu> getMenu(String pusername){  
	    return template.query("select * from view_usermenu where user_name='"+pusername+"'",new RowMapper<Menu>(){  
	        public Menu mapRow(ResultSet rs, int row) throws SQLException {  
	        	Menu e=new Menu();  
	            e.setModulename(rs.getString(1));  
	            e.setSubmodulename(rs.getString(2));  
	            e.setLink(rs.getString(4));
	            return e;  
	        }  
	    });  
	} 
	
	
	private static int checkPassword(String password_plaintext, String stored_hash) {
		boolean password_verified = false;
		
		if(null == stored_hash || !stored_hash.startsWith("$2a$"))
			//throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
			return 0;
		password_verified = BCrypt.checkpw(password_plaintext, stored_hash);
		return(password_verified?1:0);
	}
	
} 

class LoginMapper implements RowMapper<Login> {
	  public Login mapRow(ResultSet rs, int arg1) throws SQLException {
	    Login login = new Login();
	    login.setUsername(rs.getString("user_name"));
	    login.setPassword(rs.getString("user_password"));
	    login.setApprempid(rs.getInt("apprempid"));
	    return login;
	  }
}

