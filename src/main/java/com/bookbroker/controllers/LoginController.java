package com.bookbroker.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookbroker.models.LoginUser;
import com.bookbroker.models.User;
import com.bookbroker.services.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService userServ;
	
	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("loginUser", new LoginUser());
		return "login.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		User createdUser = userServ.register(user, result);
		if(createdUser == null) {
			System.out.println("createdUser was null!");
			model.addAttribute("loginUser", new LoginUser());
			return "login.jsp";
		} else {
			System.out.println("Successfully created a new user!");
			session.setAttribute("userId", createdUser.getId());
			return "redirect:/dashboard";
		}
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("loginUser") LoginUser logUser, BindingResult result, HttpSession session, Model model) {
		// This queries the DB to see if the logUser exists
		User loggedUser = userServ.login(logUser, result);
		// This will check to see if the user exists.
		// If it doesn't , it renders the page again and constructs a blank User instance.
		if(loggedUser == null) {
			model.addAttribute("user", new User());
			return "login.jsp";
		}
		// If the user exists, and the passwords match, the userId is added to session.
		// Then, it redirects to the dashboard.
		session.setAttribute("userId", loggedUser.getId());
		return "redirect:/dashboard";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userId");
		return "redirect:/";
	}
}
