package PMSNEW.PMSNEW;

public class HRMailTemplate {
	String mailTo, mailToName, appraisee , fromName, subject, message;
	int phaseid;
	String sub_sendback,msg_sendback;
	
	public int getPhaseid() {
		return phaseid;
	}
	public void setPhaseid(int phaseid) {
		this.phaseid = phaseid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject() {
		subject="Appraisal form is pending for "+appraisee;
	}

	public String getMailTo() {
		return mailTo;
	}
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getAppraisee() {
		return appraisee;
	}
	public void setAppraisee(String appraisee) {
		this.appraisee = appraisee;
	}

	public String getmailToName() {
		return mailToName;
	}
	public void setmailToName(String mailToName) {
		this.mailToName = mailToName;
	}

	public String getMessage()
	{
		return message;
	}
	public void setMessage()
	{	
			message= "Dear "+mailToName+","
				+ "\n\n Appraisal form for "+appraisee+" is pending in your phase. Kindly complete the process.";
	}
	public String getSub_sendback() {
		return sub_sendback;
	}
	public void setSub_sendback() {
		sub_sendback ="Appraisal form has been sent back for "+appraisee;
	}
	public String getMsg_sendback() {
		return msg_sendback;
	}
	public void setMsg_sendback() {
		msg_sendback ="Dear "+mailToName+","
				+ "\n\n Appraisal form for "+appraisee+" has been sent back in your phase from HR. Kindly review it again.";
	}
	
}
