package com.kgate.demo;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kgate.demo.dao.UserDao;
import com.kgate.demo.entity.User;

@Controller
//@RestController
@SpringBootApplication
public class UserController {

	@Autowired
	UserDao dao;

	@RequestMapping("/home")
	public String view() {
		return "index";
	}

	@RequestMapping("/save")
	public String addUser(User user) {
		dao.save(user);
		return "index";
	}

	@RequestMapping("/alluser")
	@ResponseBody
	public List<User> getUsers() {
		return dao.findAll();
	}

	@RequestMapping("/user/{id}")
	@ResponseBody
	public Optional<User> getUsers(@PathVariable("id") Long id) {
		return dao.findById(id);
	}

	@GetMapping(path = "/users", produces = { "application/xml", "application/json" })
	public List<User> getuser() {
		return dao.findAll();
	}

	@PostMapping("/user")
	public User save(User user) {
		dao.save(user);
		return user;
	}
	
	@DeleteMapping("/user/{id}")
	public String save(@PathVariable("id") Long id) {
		User user = dao.getOne(id);
		dao.delete(user);
		return "user deleted";
	}
	
	@PutMapping(path="/user" ,consumes= {"application/json"})
	public User saveOrUpdate(@RequestBody User user) {
		System.out.println("updated");
		dao.save(user);
		return user;
	}
}
