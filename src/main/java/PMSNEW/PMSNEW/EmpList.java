package PMSNEW.PMSNEW;

public class EmpList {
	String Designation;
	int apprempid;
	double phase1_rating,phase2_rating,phase3_rating;
	String job_title,current_phase,self_empname,appraiser_name,reviewer_name;

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}

	public int getApprempid() {
		return apprempid;
	}

	public void setApprempid(int apprempid) {
		this.apprempid = apprempid;
	}

	public double getPhase1_rating() {
		return phase1_rating;
	}

	public void setPhase1_rating(double phase1_rating) {
		this.phase1_rating = phase1_rating;
	}

	public double getPhase2_rating() {
		return phase2_rating;
	}

	public void setPhase2_rating(double phase2_rating) {
		this.phase2_rating = phase2_rating;
	}

	public double getPhase3_rating() {
		return phase3_rating;
	}

	public void setPhase3_rating(double phase3_rating) {
		this.phase3_rating = phase3_rating;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public String getCurrent_phase() {
		return current_phase;
	}

	public void setCurrent_phase(String current_phase) {
		this.current_phase = current_phase;
	}

	public String getSelf_empname() {
		return self_empname;
	}

	public void setSelf_empname(String self_empname) {
		this.self_empname = self_empname;
	}

	public String getAppraiser_name() {
		return appraiser_name;
	}

	public void setAppraiser_name(String appraiser_name) {
		this.appraiser_name = appraiser_name;
	}

	public String getReviewer_name() {
		return reviewer_name;
	}

	public void setReviewer_name(String reviewer_name) {
		this.reviewer_name = reviewer_name;
	}
	
}
