package bit.java39.controls;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bit.java39.dao.CustomerDao;

@Controller
@RequestMapping("/main")
public class MainAction {
	CustomerDao customerDao;

	@Autowired
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@RequestMapping
	public String execute() throws Exception {
		return "main";
	}
}











