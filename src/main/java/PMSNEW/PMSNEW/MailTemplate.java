package PMSNEW.PMSNEW;

import java.util.List;

public class MailTemplate {
	String mailTo, mailToName, appraisee , fromName, subject, message;
	int phaseid;
	
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
		subject=appraisee + " has submitted the appraisal form ";
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

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getMessage()
	{
		return message;
	}
	public void setMessage()
	{	
			message= "Dear "+mailToName+","
				+ "\n\n Appraisal form for "+appraisee+" is submitted by "+fromName+" and now available for your review.";
	}
}
