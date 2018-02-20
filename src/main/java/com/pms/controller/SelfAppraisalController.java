package com.pms.controller;

import java.io.InputStream;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpRequest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.pms.*;
import com.pms.dao.LoginDao;
import com.pms.dao.MailDao;
import com.pms.dao.SelfAppraisalDao;
import com.pms.model.Login;
import com.pms.model.MailTemplate;
import com.pms.model.Menu;
import com.pms.model.Score;
import com.pms.model.SelfAppraisal;
import com.pms.model.SelfAppraisalAll;
import com.pms.model.SelfAppraisalAlls;
import com.pms.model.SelfAppraisals;
import com.pms.model.Subordinate;

@Controller  

public class SelfAppraisalController {
	@Autowired
	LoginDao logindao;
	@Autowired
	SelfAppraisalDao dao;
	@Autowired
	MailDao mdao;
	static String emailToRecipient, emailSubject, emailMessage;
	static final String emailFromRecipient = "the3iappraisal@gmail.com";

	static ModelAndView modelViewObj;

	@Autowired
	private JavaMailSender mailSenderObj;

	
	String vusername="";
	String vpassword="";
	int apprempid,appmode,appmodeapprempid;
	List<SelfAppraisal> listsection;
	List<Score> listscore;
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("login") Login login, HttpSession session){
		ModelAndView modelandview = new ModelAndView();
		System.out.println(session.getAttribute("user"));
		String sessionuser=(String) (session.getAttribute("user")==null?"":session.getAttribute("user"));
		if(session!=null&& !sessionuser.equals("fake"))
		{
			vusername=login.getUsername();
			vpassword=login.getPassword();
			session.setAttribute("user", vusername);
			apprempid=logindao.checkLogin(vusername,vpassword);
			if (apprempid!=0)
			{
					
				List<Menu> list=logindao.getMenu(vusername);  
				modelandview.addObject("menulist",list);
				modelandview.addObject("username",vusername);
				modelandview.addObject("apprempid",apprempid);
				modelandview.setViewName("/admin/login-success");
		        return modelandview;
			}
	        else
	        {
		        modelandview.addObject("message","Login not found in Database");
		        modelandview.addObject("username",vusername);
		    	modelandview.setViewName("/admin/message");
		        return modelandview;
		    }
		}
		else
		{
			modelandview.setViewName("index");
	        return modelandview;
		}
	}

	//	@RequestMapping(value = "/AppraiseServlet/{apprphaseid}/{apprempid}", method=RequestMethod.POST)
	@RequestMapping(value="/AppraiseServletProcess",method=RequestMethod.GET)
    public ModelAndView editselfAppraisal(HttpSession session){
		int sapprempid=0,sapprphaseid=0;
		String message="";
		sapprempid=(Integer) session.getAttribute("apprempid");
		sapprphaseid=(Integer) session.getAttribute("apprphaseid");
		message=(String) session.getAttribute("message");
		ModelAndView modelandview=new ModelAndView();
		List<SelfAppraisalAll> list=dao.getSelfAppraisalAll(sapprempid);   
		SelfAppraisalAlls selfAppraisalalls = new SelfAppraisalAlls();
		selfAppraisalalls.setSelfappraisalall(list);
		modelandview.addObject("selfAppraisalAlls",selfAppraisalalls);
		listsection=dao.getSelfAppraisalSections(vusername,sapprphaseid==4?3:sapprphaseid,sapprempid);   
		modelandview.addObject("listsection",listsection);
		modelandview.addObject("username",vusername);
		modelandview.addObject("apprphaseid",sapprphaseid);
		modelandview.addObject("apprempid", sapprempid);
		modelandview.addObject("message",message);
		listscore=dao.getScore(sapprempid);
		modelandview.addObject("listscore",listscore);
		
		modelandview.setViewName("/appraisal/appraisal");
        return modelandview;
       
       
    }  
	
	@RequestMapping(value="/AppraiseServlet/{apprphaseid}/{apprempid}",method=RequestMethod.GET,params = "message")
    public ModelAndView selfAppraisal(@PathVariable("apprphaseid") int sapprphaseid, @PathVariable("apprempid") int sapprempid,@RequestParam("message") String message, HttpSession session){
		session.setAttribute("apprphaseid", sapprphaseid);
		session.setAttribute("apprempid", sapprempid);
		session.setAttribute("messsage", message);
		return new ModelAndView("redirect:/AppraiseServletProcess");
  }  
	
	@RequestMapping(value = "/saveappraisal", method = RequestMethod.POST, params = "action")
	public ModelAndView save(@ModelAttribute("selfappraisal") SelfAppraisals selfappraisals,@RequestParam("action") String actioname,HttpSession session) {
		int vapprempid=0,vapprphaseid=0;
		String vlink="",message="";
		System.out.println(selfappraisals);
		System.out.println(selfappraisals.getSelfappraisal());
		List<SelfAppraisal> selfappraisal = selfappraisals.getSelfappraisal();
		int flag=0;
		if(null != selfappraisal && selfappraisal.size() > 0) {
			//ContactController.contacts = contacts;
			for (SelfAppraisal a : selfappraisal) {
				//System.out.printf("%s \t %s \n", a.getRemarks(), a.getRating());
				vapprempid=a.getApprempid();
				vapprphaseid=a.getApprphaseid();
				if (a.getRating()==0  || (a.getRating()>5 && a.getRating()<10) || a.getRemarks().equals(""))
				{	
					flag=1;
					System.out.println(a.getRating()+" "+a.getRemarks());
				}	
				dao.update(a);
			}
			if(actioname.equals("Submit"))
			{

				System.out.println("Submit button pressed");
				if(flag==0)
				{
					dao.updatePhaseStatus(vapprempid,vapprphaseid);
					dao.updateCurrentPhase(vapprempid);
					List<MailTemplate> mt=mdao.getMailDetails(vapprempid, vapprphaseid);
					String vsubject,vmessage,vmailto;
					MailTemplate mtt=mt.get(0);
					mtt.setSubject();
					mtt.setMessage();
					vsubject=mtt.getSubject();
					vmessage=mtt.getMessage();
					vmailto=mtt.getMailTo();
					System.out.println(vmailto);
					sendEmailToClient(vsubject,vmessage,vmailto);
					//vlink="redirect:/sendEmail?subject="+vsubject+"&message="+vmessage+"&mailTo="+vmailto;
					message="Appraisal has been submitted for further review";
				}  else message= "Please fill in all ratings and remarks to Submit appraisal";
			} else message="Appraisal Saved Successfully";
		}
		session.setAttribute("apprphaseid", vapprphaseid);
		session.setAttribute("apprempid", vapprempid);
		session.setAttribute("message", message);
		//vlink=vlink.equals("")?"redirect:/AppraiseServlet/"+Integer.toString(vapprphaseid)+"/"+Integer.toString(vapprempid)+"?message="+message:vlink;
		vlink=vlink.equals("")?"redirect:/AppraiseServletProcess":vlink;
		return new ModelAndView(vlink);
		}

	@RequestMapping(value="/subordinate",method=RequestMethod.GET,params = "phaseid")
    public ModelAndView Subordinate(@RequestParam("phaseid") int subordinatephaseid){
		ModelAndView modelandview=new ModelAndView();
		List<Subordinate> list=dao.getSubordinate(vusername,subordinatephaseid);
		modelandview.addObject("sublist",list);
		modelandview.addObject("username",vusername);
		modelandview.addObject("apprphaseid",subordinatephaseid);
		modelandview.setViewName("/appraisal/subordinate");
        return modelandview;
    }  
	

	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ModelAndView Logout(HttpServletResponse response,HttpSession session)
	{
		ModelAndView modelandview=new ModelAndView();
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0);
        session.setAttribute("user", "fake");
        
		session.invalidate();session=null;
		
    	modelandview.setViewName("index");
        return modelandview;
		
	}	
	
	//@RequestMapping("sendEmail")
	//@RequestMapping(value = "sendEmail", method = RequestMethod.GET)
	public void sendEmailToClient(String pemailSubject,String pemailMessage,String pemailToRecipient) {
		final String emailSubject=pemailSubject;
		final String emailMessage=pemailMessage;
		final String emailToRecipient=pemailToRecipient;

		System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");

		mailSenderObj.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");				
				mimeMsgHelperObj.setTo(emailToRecipient);
				mimeMsgHelperObj.setFrom(emailFromRecipient);				
				mimeMsgHelperObj.setText(emailMessage);
				mimeMsgHelperObj.setSubject(emailSubject);
			}
		});
		System.out.println("\nMessage Send Successfully.... Hurrey!\n");
	}
	

}

