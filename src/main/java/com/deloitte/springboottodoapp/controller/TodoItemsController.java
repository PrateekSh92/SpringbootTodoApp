package com.deloitte.springboottodoapp.controller;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.deloitte.springboottodoapp.models.TodoItem;
import com.deloitte.springboottodoapp.repositories.TodoItemRepository;

@RestController
public class TodoItemsController {

	private final Logger logger = LoggerFactory.getLogger(TodoItemsController.class);
	
	@Autowired
	TodoItemRepository todoItemRepository;
	
	/*
	 * Method return index view after authentication of user details
	 */
	@GetMapping("/")
	public ModelAndView index() {
		  ModelAndView modelAndView = new ModelAndView("index");
		  modelAndView.addObject("todoItems", todoItemRepository.findAll()); 
		  return modelAndView;
		 
	}
}
