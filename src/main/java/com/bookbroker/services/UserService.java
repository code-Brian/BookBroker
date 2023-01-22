package com.bookbroker.services;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.bookbroker.models.LoginUser;
import com.bookbroker.models.User;
import com.bookbroker.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	
	// CRUD QUERIES HERE
	public User create(User u) {
		return userRepo.save(u);
	}
	
	public User getOne(Long id) {
		Optional<User> optUser = userRepo.findById(id);
		if(optUser.isPresent()) {
			return optUser.get();
		} else {
			return null;
		}
	}
	
	public User update(User u) {
		return userRepo.save(u);
	}
	
	public void delete(Long id) {
		userRepo.deleteById(id);
	}
	
	// LOGIN, REGISTRATION AND SESSION
	public User register(User createdUser, BindingResult result) {
		if(!createdUser.getPassword().equals(createdUser.getConfirmPassword())) {
			result.rejectValue("confirmPassword","Match", "Passwords must match!");
			return null;
		}
		
		Optional<User> userEmail = userRepo.findByEmail(createdUser.getEmail());
		if(userEmail.isPresent()) {
			System.out.println("Duplicate email found!");
			result.rejectValue("email", "In Use", "Email in use!");
			return null;
		}
		
		if(!result.hasErrors()) {
			String hashed = BCrypt.hashpw(createdUser.getPassword(), BCrypt.gensalt());
			createdUser.setPassword(hashed);
			return this.create(createdUser);
		}

		return null;
	}
	
	public User login(LoginUser logUser, BindingResult result) {
		Optional<User> user = userRepo.findByEmail(logUser.getEmail());
		// This checks to see if the user exists in the DB
		// BCrypt then checks the input password instead of using the creatpw function, which would add a unique salt
		if(user.isPresent() && BCrypt.checkpw(logUser.getPassword(), user.get().getPassword())) {
			return user.get();
		}
		result.rejectValue("password", "Invalid","Invalid credentials!");
		return null;
	}
	
	public boolean checkLoginStatus(HttpSession session) {
		boolean loggedIn = false;
		if(session.getAttribute("userId") == null) {
			return loggedIn;
		}else if (session.getAttribute("userId") != null){
			return loggedIn = true;
		}else {
			return loggedIn;
		}
	}
	

}
