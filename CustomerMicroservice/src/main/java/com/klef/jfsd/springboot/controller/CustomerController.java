package com.klef.jfsd.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.springboot.model.Customer;
import com.klef.jfsd.springboot.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	 @GetMapping("customer")
	   @ResponseBody
	   public String customer()
	   {
		   return "Customer Microservice";
	   }
	 
	 /*@GetMapping("home")
	   public String main()
	   {
		   return "main";
	   }*/
	 
	 @GetMapping("home")
		public ModelAndView home()
		{
			ModelAndView mv = new ModelAndView();
			mv.setViewName("home");
			return mv;
		}
		
		@GetMapping("customerhome")
		public ModelAndView customerhome()
		{
			ModelAndView mv = new ModelAndView();
			mv.setViewName("customerhome");
			return mv;
		}
		
		
		
		@GetMapping("login")
		public ModelAndView customerlogin()
		{
			ModelAndView mv = new ModelAndView();
			mv.setViewName("login");
			return mv;
		}
		
		@PostMapping("checkcustomerlogin")
		public ModelAndView checkcustomerlogin(HttpServletRequest request)
		{
			ModelAndView mv = new ModelAndView();
			
			String email = request.getParameter("email");
			String pwd = request.getParameter("password");
			
			Customer c = customerService.checkcustomerlogin(email, pwd);
			if(c!=null)
			{
				// session
				//HttpSession session = request.getSession();
				//session.setAttribute("cid", c.getId());// cid is a session variable
				//session.setAttribute("cname", c.getName()); // cname is a session variable
	           // session
				mv.setViewName("customerhome"); //home is a jsp file name it must be created first front page
				mv.addObject("message", "Login Successfull");
			}
			else
			{
				mv.setViewName("displaymsg");
				mv.addObject("message", "Login Failed");
			}
			return mv;
		}
		
		@GetMapping("categories")
		public ModelAndView categories()
		{
			ModelAndView mv = new ModelAndView();
			mv.setViewName("categories");
			return mv;
		}
		@GetMapping("action")
		public ModelAndView action()
		{
			ModelAndView mv = new ModelAndView();
			mv.setViewName("action");
			return mv;
		}
		
		@GetMapping("seller")
		public ModelAndView seller()
		{
			ModelAndView mv = new ModelAndView();
			mv.setViewName("seller");
			return mv;
		}
		
		@GetMapping("contact")
		public ModelAndView contact()
		{
			ModelAndView mv = new ModelAndView();
			mv.setViewName("contact");
			return mv;
		}
		
		@GetMapping("registration")
		public ModelAndView customerregistration()
		{
			ModelAndView mv = new ModelAndView();
			mv.setViewName("registration");
			return mv;
		}
		
		@PostMapping("insertcustomer")
		public ModelAndView insertaction(HttpServletRequest request)
		{
			String msg = null;
			ModelAndView mv = new ModelAndView();
			try {
				String name = request.getParameter("name");
				String gender = request.getParameter("gender");
				String dob = request.getParameter("dateofbirth");
				String email = request.getParameter("email");
				String pwd = request.getParameter("pass");
				String address = request.getParameter("address");
				String contact = request.getParameter("contact");

				Customer c = new Customer();
				c.setName(name);
				c.setGender(gender);
				c.setDateofbirth(dob);
				c.setEmail(email);
				c.setPassword(pwd);
				c.setAddress(address);
				c.setContactno(contact);

				msg = customerService.addcutomer(c);

				mv.setViewName("login");
				//mv.addObject("message", msg);

			} catch (Exception e) {
				msg = e.getMessage();
				mv.setViewName("displaymsg");
				mv.addObject("message", msg);
			}

			return mv;
		}
		
		@GetMapping("logout")
		public ModelAndView customerlogout()
		{
			ModelAndView mv = new ModelAndView();
			mv.setViewName("home");
			mv.addObject("message", "Logout Successfully");
			return mv;
		}
		
		@GetMapping("payment")
		public ModelAndView payment()
		{
			ModelAndView mv = new ModelAndView();
			mv.setViewName("payment");
			//mv.addObject("message", "Logout Successfully");
			return mv;
		}
		
		@GetMapping("paymentsuccess")
		public ModelAndView paymentsuccess()
		{
			ModelAndView mv = new ModelAndView();
			mv.setViewName("paymentsuccess");
			//mv.addObject("message", "Logout Successfully");
			return mv;
		}
		
		 @GetMapping("/profile")
		   public ModelAndView profile(HttpServletRequest request) {
			   HttpSession session = request.getSession();
				  
				  int cid = (int)session.getAttribute("cid");//cid is a session variable
				  String cname = (String)session.getAttribute("cname");//cname is a session variable;
				  
				  ModelAndView mv = new ModelAndView();
				  mv.setViewName("profile");
				  
				  mv.addObject("cid", cid);
				  mv.addObject("cname", cname);
				  
				  return mv;
		   }
		
	
}
