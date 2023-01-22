package com.bookbroker.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookbroker.services.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userServ;
	
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		//Don't forget to make an addAttribute call to get all books, eventually.
		model.addAttribute("user", userServ.getOne((Long) session.getAttribute("userId")));
		return "index.jsp";
	}
}
