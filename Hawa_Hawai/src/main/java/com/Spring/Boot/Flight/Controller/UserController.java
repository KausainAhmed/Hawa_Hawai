package com.Spring.Boot.Flight.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Spring.Boot.Flight.Repos.MyuserRepo;
import com.Spring.Boot.Flight.entities.Myuser;

@Controller
public class UserController {
	@Autowired
	private MyuserRepo myuserRepo;

	@RequestMapping("/showReg")
	public String showRegistrationPage() {
		return "registerUser";
	}

	@RequestMapping(value = "/registerUser")
	public String register(@ModelAttribute("myuser") Myuser myuser) {
		myuserRepo.save(myuser);
		return "login";
	}

	@RequestMapping(value = "/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap modelMap) {
		Myuser myuser = myuserRepo.findByEmail(email);
		if (myuser.getPassword().equals(password)) {
			return "findFlights";
		} else {
			modelMap.addAttribute("msg", "Invalid username or password. Plz try again");
		}
		return "login";

	}
}