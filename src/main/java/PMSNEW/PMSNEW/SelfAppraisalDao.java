package PMSNEW.PMSNEW;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import PMSNEW.PMSNEW.SelfAppraisal;

public class SelfAppraisalDao {

	JdbcTemplate template; 
	
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}  
	public int update(SelfAppraisal p){  
	    String sql="update appr_empl_rating set remarks='"+p.getRemarks()+"', rating="+p.getRating()+ " where  ApprEmpRatingId="+p.getApprempratingid();  
	    return template.update(sql);  
	}  
	public int updateCurrentPhase(int apprempid){  
	    String sql="update appr_empl set curr_phase_id= curr_phase_id + 1 where ApprEmpId="+apprempid ;  
	    return template.update(sql);  
	}
	public int updatePhaseStatus(int apprempid, int apprphaseid){  
	    String sql="update appr_empl_flow set Status = 2 where apprEmpId= "+apprempid+" and phaseid="+apprphaseid;  
	    return template.update(sql);  
	}
	
	public List<SelfAppraisal> getSelfAppraisal(String pusername,String pmode){  
	    return template.query("select * from  view_getappraisalrecords where user_name='"+pusername+"' and phaseid='"+pmode+"' order by SectionColOrder,QuestionColOrder" ,new RowMapper<SelfAppraisal>(){  
	        public SelfAppraisal mapRow(ResultSet rs, int row) throws SQLException {  
	        	SelfAppraisal e=new SelfAppraisal();  
	            e.setApprempid(rs.getInt(1));  
	            e.setApprempratingid(rs.getInt(11));  
	            e.setSection(rs.getString(2));
	            e.setQuestion(rs.getString(3));  
	            e.setRemarks(rs.getString(4));
	            e.setRating(rs.getInt(5));
	            e.setPerformanceind1(rs.getString(7));  
	            e.setPerformanceind2(rs.getString(8));
	            e.setPerformanceind3(rs.getString(9));
	            e.setAppraisalstatus(rs.getString(10));
	            e.setSectioncolorder(rs.getInt(14));
	            e.setQuestioncolorder(rs.getInt(15));
	            
	            return e;  
	        }  
	    });
	}
	    
	    public List<SelfAppraisal> getSelfAppraisalSections(String pusername,int pmode,int apprempid){  
		    return template.query("select distinct section,sectioncolorder from  view_getappraisalrecords where user_name='"+pusername+"' and phaseid="+pmode+" and apprempid="+apprempid+" order by SectionColOrder" ,new RowMapper<SelfAppraisal>(){  
		        public SelfAppraisal mapRow(ResultSet rs, int row) throws SQLException {  
		        	SelfAppraisal e=new SelfAppraisal();  
		            e.setSection(rs.getString(1));
		            e.setSectioncolorder(rs.getInt(2));
		            return e;  
		        }  
		    });
	    
	} 
	
	    public List<SelfAppraisalAll> getSelfAppraisalAll(int apprempid){  
		    return template.query("select * from  view_getappraisalrecordsallphase where apprempid="+apprempid+" order by SectionColOrder,QuestionColOrder" ,new RowMapper<SelfAppraisalAll>(){  
		        public SelfAppraisalAll mapRow(ResultSet rs, int row) throws SQLException {  
		        	SelfAppraisalAll e=new SelfAppraisalAll();  
		        	e.setApprempid(rs.getInt("apprempid"));
		        	e.setSection(rs.getString("section"));
		        	e.setApprquestionid(rs.getInt("apprquestionid"));
		        	e.setQuestion(rs.getString("question"));
		        	e.setSectioncolorder(rs.getInt("sectioncolorder"));
		        	e.setQuestioncolorder(rs.getInt("questioncolorder"));
		        	e.setPerformanceind1(rs.getString("performanceind1"));
		        	e.setPerformanceind2(rs.getString("performanceind2"));
		        	e.setPerformanceind3(rs.getString("performanceind3"));
		        	e.setRatingyn(rs.getString("ratingyn"));
		        	e.setRatingid1(rs.getInt("ratingid1"));
		        	e.setRemarks1(rs.getString("remarks1"));
		        	e.setRating1(rs.getInt("rating1"));
		        	e.setPhase1status(rs.getInt("phase1status"));
		        	e.setRatingid2(rs.getInt("ratingid2"));
		        	e.setRemarks2(rs.getString("remarks2"));
		        	e.setRating2(rs.getInt("rating2"));
		        	e.setPhase2status(rs.getInt("phase2status"));
		        	e.setRatingid3(rs.getInt("ratingid3"));
		        	e.setRemarks3(rs.getString("remarks3"));
		        	e.setRating3(rs.getInt("rating3"));
		        	e.setPhase3status(rs.getInt("phase3status"));
		        	e.setIsfinalized(rs.getString("isfinalized"));
		        	e.setCurr_phase_id(rs.getInt("curr_phase_id"));
		        	e.setUser_name(rs.getString("user_name"));
		            return e;  
		        }  
		    });
		}
		    
	    public List<Score> getScore(int apprempid){  
		    return template.query("select *from view_appraisalscore where apprempid="+apprempid ,new RowMapper<Score>(){  
		        public Score mapRow(ResultSet rs, int row) throws SQLException {  
		        	Score e=new Score();  
		            e.setRating1(rs.getDouble(2));
		            e.setRating2(rs.getDouble(3));
		            e.setRating3(rs.getDouble(4));
		            e.setIsFinalize(rs.getString(5));
		        	return e;  
		        }  
		    });
	    
	}
	
	 public List<Subordinate> getSubordinate(String vusername,int phaseid){  
		    return template.query("select * from view_apprreviewer where user_name='"+vusername+"' and phaseid="+phaseid,new RowMapper<Subordinate>(){  
		        public Subordinate mapRow(ResultSet rs, int row) throws SQLException {  
		        	Subordinate e=new Subordinate();  
		        	e.setApprempid(rs.getInt("apprempid"));
		        	e.setPhaseid(rs.getInt("phaseid"));
		        	e.setCurr_phase_id(rs.getInt("curr_phase_id"));
		        	e.setApprphase(rs.getString("apprphase"));
		        	e.setEmp_name(rs.getString("emp_name"));
		            e.setEmp_number(rs.getString("emp_number"));
		            e.setUser_name(rs.getString("user_name"));
		            e.setJob_title(rs.getString("job_title"));
		        	return e;  
		        }  
		    });
	    
	}
	
}


