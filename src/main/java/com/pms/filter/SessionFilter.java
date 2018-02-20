package com.pms.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SessionFilter
 */
public class SessionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SessionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String url = request.getRequestURI();

        response.setHeader("pragma", "no-cache");              
        response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
        response.setHeader("Expires", "0"); 
        HttpSession session = request.getSession(false);

        //!url.contains("login.html") check if the requested page is login page or not. you can do it a numerous way.
        // but for simpplicity here i do that
        if(session==null && !url.contains("index")) {
            response.sendRedirect(request.getContextPath() + "index"); // here goto http://yourdoamin/login.html
            response.setHeader("message", "Session Timeout."); // you can set your preffered message.
            return; //break filter chain, requested JSP/servlet will not be executed
        }
        chain.doFilter(req, res);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
