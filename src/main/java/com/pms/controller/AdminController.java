package com.pms.controller;

import java.util.List;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pms.dao.AdminDao;
import com.pms.dao.SendBackDao;
import com.pms.model.EmpList;
import com.pms.model.EmpLists;
import com.pms.model.HRMailTemplate;

@Controller
public class AdminController {
	@Autowired
	AdminDao adao;
	
	@Autowired
	SendBackDao sdao;
	
	@Autowired
	private JavaMailSender mailSenderObj;
	
	List<EmpList> designations;
	List<EmpList> ratinglist;
	List<EmpList> maillists;
	
	@RequestMapping(value="/ViewRatingServlet")
	public ModelAndView emplist(){
		ModelAndView modelandview = new ModelAndView();
		designations=adao.getDesignations();  
		ratinglist =adao.getEmpList();
		modelandview.addObject("designations",designations);
		
		modelandview.addObject("ratinglist",ratinglist);
		modelandview.setViewName("/admin/hrhome");
        return modelandview;
		
	}
	@RequestMapping(value="/ViewRatingByTitleServlet/{designation:.+}")
	public ModelAndView emplistbyTitle( @PathVariable("designation") String designation ){
		ModelAndView modelandview = new ModelAndView();
		designations=adao.getDesignations();  
		ratinglist =adao.getEmpListByTitle(designation);
		modelandview.addObject("designations",designations);
		modelandview.addObject("ratinglist",ratinglist);
		modelandview.setViewName("/admin/hrhome");
        return modelandview;
		
	}
	
	@RequestMapping(value="/SendMailServlet",method=RequestMethod.POST, params = "action")
	public ModelAndView sendReminderMail(@ModelAttribute("emplist") EmpLists emplist,@RequestParam("action") String actionname){
		ModelAndView modelandview = new ModelAndView();
		maillists=emplist.getEmplist();
		HRMailTemplate mt;
		
		if(actionname.equals("Remind"))
		{
			
			for(EmpList e:maillists)
			{
				mt=new HRMailTemplate();
				int curr_phase=e.getCurr_phaseid();
				if(e.getIschecked().equals("true") && curr_phase<4)
				{
					System.out.println("Remind him");
					switch(curr_phase)
					{
					case 1:mt.setmailToName(e.getSelf_empname());mt.setMailTo(e.getPhase1mail());break;
					case 2:mt.setmailToName(e.getAppraiser_name());mt.setMailTo(e.getPhase2mail());break;
					case 3:mt.setmailToName(e.getReviewer_name());mt.setMailTo(e.getPhase3mail());break;
					default:;
					}
					mt.setAppraisee(e.getSelf_empname());
					mt.setMessage();
					mt.setSubject();
					sendEmailToEmp(mt.getSubject(),mt.getMessage(),mt.getMailTo());
					System.out.println("mail sent");
				}
			}
			
		}
		else if(actionname.equals("SendBack"))
		{
			for(EmpList e:maillists)
			{
				mt=new HRMailTemplate();
				int curr_phase=e.getCurr_phaseid();
				int apprempid=e.getApprempid();
				if(e.getIschecked().equals("true") && curr_phase>1 && curr_phase<5)
				{
					System.out.println("Sending back");
					switch(curr_phase)
					{
					case 2:mt.setmailToName(e.getSelf_empname());mt.setMailTo(e.getPhase1mail());break;
					case 3:mt.setmailToName(e.getAppraiser_name());mt.setMailTo(e.getPhase2mail());break;
					case 4:mt.setmailToName(e.getReviewer_name());mt.setMailTo(e.getPhase3mail());break;
					
					default:;
					}
					mt.setAppraisee(e.getSelf_empname());
					mt.setSub_sendback();
					mt.setMsg_sendback();
					sdao.updatePhaseStatus(apprempid, curr_phase);
					sdao.updateCurrentPhase(apprempid);
					sendEmailToEmp(mt.getSub_sendback(),mt.getMsg_sendback(),mt.getMailTo());
					System.out.println("mail sent");
				}
			}	
		}
		designations=adao.getDesignations();  
		ratinglist =adao.getEmpList();
		modelandview.addObject("designations",designations);
		
		modelandview.addObject("ratinglist",ratinglist);
		modelandview.setViewName("/admin/hrhome");
        return modelandview;
}
	public void sendEmailToEmp(String pemailSubject,String pemailMessage,String pemailToRecipient) {
		final String emailSubject=pemailSubject;
		final String emailMessage=pemailMessage;
		final String emailToRecipient=pemailToRecipient;

		final String emailFromRecipient = "the3iappraisal@gmail.com";
		mailSenderObj.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");				
				mimeMsgHelperObj.setTo(emailToRecipient);
				mimeMsgHelperObj.setFrom(emailFromRecipient);				
				mimeMsgHelperObj.setText(emailMessage);
				mimeMsgHelperObj.setSubject(emailSubject);
			}
		});
	}
}