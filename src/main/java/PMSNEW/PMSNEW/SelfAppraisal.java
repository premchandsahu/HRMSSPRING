package PMSNEW.PMSNEW;

public class SelfAppraisal {

	public int apprempid;
	public int apprempratingid;
	public String section;
	public String question;
	public String remarks;
	public int rating;
	public String performanceind1;
	public String performanceind2;
	public String performanceind3;
	public String appraisalstatus;
	public int sectioncolorder;
	public int questioncolorder;
	public int apprphaseid;
	
	public SelfAppraisal(){}
	
	public SelfAppraisal(int apprempid, int apprempratingid, String section, String question, String remarks,
			int rating, String performanceind1, String performanceind2, String performanceind3, String appraisalstatus,
			int sectioncolorder, int questioncolorder,int apprphaseid) {
		super();
		this.apprempid = apprempid;
		this.apprempratingid = apprempratingid;
		this.section = section;
		this.question = question;
		this.remarks = remarks;
		this.rating = rating;
		this.performanceind1 = performanceind1;
		this.performanceind2 = performanceind2;
		this.performanceind3 = performanceind3;
		this.appraisalstatus = appraisalstatus;
		this.sectioncolorder = sectioncolorder;
		this.questioncolorder = questioncolorder;
		this.apprphaseid = apprphaseid;
	}

	
	
	public int getApprempid() {
		return apprempid;
	}
	public void setApprempid(int apprempid) {
		this.apprempid = apprempid;
	}
	public int getApprempratingid() {
		return apprempratingid;
	}
	public void setApprempratingid(int apprempratingid) {
		this.apprempratingid = apprempratingid;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getPerformanceind1() {
		return performanceind1;
	}
	public void setPerformanceind1(String performanceind1) {
		this.performanceind1 = performanceind1;
	}
	public String getPerformanceind2() {
		return performanceind2;
	}
	public void setPerformanceind2(String performanceind2) {
		this.performanceind2 = performanceind2;
	}
	public String getPerformanceind3() {
		return performanceind3;
	}
	public void setPerformanceind3(String performanceind3) {
		this.performanceind3 = performanceind3;
	}
	public String getAppraisalstatus() {
		return appraisalstatus;
	}
	public void setAppraisalstatus(String appraisalstatus) {
		this.appraisalstatus = appraisalstatus;
	}
	public int getQuestioncolorder() {
		return questioncolorder;
	}
	public void setQuestioncolorder(int questioncolorder) {
		this.questioncolorder = questioncolorder;
	}
	public int getSectioncolorder() {
		return sectioncolorder;
	}
	public void setSectioncolorder(int sectioncolorder) {
		this.sectioncolorder = sectioncolorder;
	}

	public int getApprphaseid() {
		return apprphaseid;
	}

	public void setApprphaseid(int apprphaseid) {
		this.apprphaseid = apprphaseid;
	}
	

}

