package io.lindx.task.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.lindx.task.model.User;
import io.lindx.task.service.UserBuilder;
import io.lindx.task.service.UserService;
import io.lindx.task.util.Util;

@Controller
@RequestMapping("/")
public class HelloController {

	private final UserService userService;
	private final UserBuilder user;

	@Autowired
	public HelloController(UserService userService, UserBuilder user) {

		this.userService = userService;
		this.user = user;
	}

	@GetMapping(value = "/")
	public String printwelcome(ModelMap model) {

   

		List<String> messages = new ArrayList<>();

		messages.add("hello!");
		messages.add("i'm spring mvc application");
		messages.add("5.2.0 version by sep'19 ");

		messages.stream().forEach(s -> Util.getLogger().info(s));

		model.addAttribute("messages", messages);


		return "index";
	}

	@GetMapping(value = "/create")
	public String create(ModelMap model) {

		List<User> users = Arrays.asList(
        user.firstName("user1").lastName("lastname1").email("user1@mail.ru").build(),
				user.firstName("user2").lastName("lastname2").email("user2@mail.ru").build(),
				user.firstName("user3").lastName("lastname3").email("user3@mail.ru").build(),
				user.firstName("user4").lastName("lastname4").email("user4@mail.ru").build(),
				user.firstName("user5").lastName("lastname5").email("user5@mail.ru").build());

		users.stream().forEach(x -> userService.add(x));

		return "redirect:/users";
	}

	@GetMapping("/index")
	public String cars(ModelMap model) {

    model.addAttribute("users", userService.listUsers());

		return "index";
	}

}
