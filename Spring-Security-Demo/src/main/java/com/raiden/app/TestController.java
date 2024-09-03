package com.raiden.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raiden.app.model.Users;
import com.raiden.app.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class TestController {
	
	@Autowired
	private UsersService usersService;
	
	//testing purpose
	@RequestMapping("/")
	public String test(HttpServletRequest req) {
		return "testing "+req.getSession().getId();
	}
	//testing purpose
	@RequestMapping("/hello")
	public String testhello() {
		return "Saying hello";
	}
	
	//testing purpose
	@PostMapping("/add/{name}")
	public String add(@PathVariable String name) {
		return name;
	}

	//getting csrftoken, testing purpose
	@GetMapping("/token")
	public CsrfToken getToken(HttpServletRequest req) {
		return (CsrfToken)req.getAttribute("_csrf");
	}
	
	//registering new user
	@PostMapping("/register")
	public Users addUser(@RequestBody Users user) {
		return usersService.addUser(user);
	}
	
	//getting all the users available in the db
	@GetMapping("/getAll")
	public List<Users> getAll(){
		return usersService.getAll();
	}
}
