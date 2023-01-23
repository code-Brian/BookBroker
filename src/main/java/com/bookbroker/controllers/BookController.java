package com.bookbroker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookbroker.models.Book;

@Controller
@RequestMapping("/book")
public class BookController {
	Autowired
	BookService bookServ;
	
	@GetMapping("/create")
	public String create(@ModelAttribute("book") Book book) {
		return "createBook.jsp";
	}

}
