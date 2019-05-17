package com.kgate.demo;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.servlet.ModelAndView;

import com.kgate.demo.dao.UserDao;
import com.kgate.demo.entity.User;
import com.kgate.demo.service.UserService;
import com.kgate.demo.util.CustomError;
import com.kgate.demo.util.CustomSuccess;

@Controller
@RestController
//@SpringBootApplication
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserDao dao;

	@Autowired
	UserService userService;

	@RequestMapping("/home")
	public String view() {
		return "index";
	}

	@RequestMapping("/save")
	public ModelAndView addUser(User user) {
		dao.save(user);
		return new ModelAndView("index");
	}

	@RequestMapping("/alluser")
	@ResponseBody
	public List<User> getUsers() {
		return dao.findAll();
	}
//create a user

//	@GetMapping(value = "/user")

//	@RequestMapping("/user/{id}")
//	@ResponseBody
//	public Optional<User> getUsers(@PathVariable("id") Long id) {
//		return dao.findById(id);
//	}

//	Getting user using user id
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		Optional<User> user = dao.findById(id);
		System.out.println("user id: " + user);
		if (user.equals(Optional.empty())) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity<>(new CustomError("User with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping(path = "/users", produces = { "application/xml", "application/json" })
	public List<User> getuser() {
		return dao.findAll();
	}

	// create user
//	@PostMapping("/user")
//	public User save(User user) {
//		dao.save(user);
//		return user;
//	}

//Delete user using user id
//	@DeleteMapping("/user/{id}")
//	public String save(@PathVariable("id") Long id) {
//		User user = dao.getOne(id);
//		dao.delete(user);
//		return "user deleted";
//	}

//	Deleting user with user id
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		logger.info("Fetching and deleting user with id {}", id);
		User user = dao.getOne(id);
		if (user == null) {
			logger.error("Unable to delete user with id {}", id);
			return new ResponseEntity<>(new CustomError(
					"Unable to delete. User with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		dao.deleteById(id);
//		dao.delete(user);
		return new ResponseEntity<>(new CustomSuccess("User deleted successfully with id " + id),
				HttpStatus.OK);
	}

	@PutMapping(path = "/user", consumes = { "application/json" })
	public User saveOrUpdate(@RequestBody User user) {
		System.out.println("updated");
		dao.save(user);
		return user;
	}

//creating user
	@PostMapping("/saveUser")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		logger.info("creating user : {} ", user);
//		 dao.findByfirstName(user.getFirstName());
//		user = dao.findById(user.get().getId());
		try {
			System.out.println("user name: "+user.getFirstName());
			List<User> us=  (List<User>) dao.findByfirstName(user.getFirstName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("user test... " + us);
		return new ResponseEntity<>(new CustomSuccess("User deleted successfully with id " + user),
				HttpStatus.OK);
	}

}
