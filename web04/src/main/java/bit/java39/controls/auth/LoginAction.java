package bit.java39.controls.auth;


import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bit.java39.dao.CustomerDao;
import bit.java39.vo.Customer;

@Controller
@RequestMapping("/auth/*")
public class LoginAction {
	
	private Logger logger = Logger.getLogger(LoginAction.class);
	
	CustomerDao customerDao;

	@Autowired
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String form(
			@CookieValue(value="id",defaultValue="") String id, 
			Model model) throws Exception {
		
		model.addAttribute("id", id);
		
		return "auth/loginForm";
	}

	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(
			@RequestParam("id") String id,
			@RequestParam("pwd") String password,
			@RequestParam(value="save",required=false) String save,
			HttpSession session,
			HttpServletResponse response) throws Exception {
		
		//System.out.println(id + "," + password + "," + save);
		logger.debug("오호라..debug...");
		logger.info("오호라..info...");
		logger.warn("오호라..warn...");
		logger.error("오호라..error...");
		logger.fatal("오호라..fatal...");
		
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("id", id);
		params.put("password", password);
		
		if (customerDao.isExist(params)) {
			Customer customer = customerDao.selectOne(id);
			session.setAttribute("customer", customer);
			
			Cookie cookie = null;
			if (save != null) {
				cookie = new Cookie("id", id);
				cookie.setMaxAge(10);
			} else {
				cookie = new Cookie("id", "");
			}
			response.addCookie(cookie);
			
			return "redirect:../main.do";
		} else {
			return "redirect:login.do";
		}
	}
	
	@RequestMapping(value="logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:login.do";
	}
}











