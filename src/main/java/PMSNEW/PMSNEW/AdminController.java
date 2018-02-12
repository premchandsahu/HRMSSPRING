package PMSNEW.PMSNEW;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	@Autowired
	AdminDao adao;
	List<EmpList> designations;
	List<EmpList> ratinglist;
	@RequestMapping(value="/ViewRatingServlet")
	public ModelAndView emplist(@ModelAttribute("emplist") EmpList emplist){
		ModelAndView modelandview = new ModelAndView();
		designations=adao.getDesignations();  
		ratinglist =adao.getEmpList();
		modelandview.addObject("designations",designations);
		
		modelandview.addObject("ratinglist",ratinglist);
		modelandview.setViewName("hrhome");
        return modelandview;
		
	}
	
}
