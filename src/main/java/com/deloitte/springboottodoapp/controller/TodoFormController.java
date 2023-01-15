package com.deloitte.springboottodoapp.controller;

import java.time.Instant;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deloitte.springboottodoapp.models.TodoItem;
import com.deloitte.springboottodoapp.repositories.TodoItemRepository;

@Controller
public class TodoFormController {

private final Logger logger = LoggerFactory.getLogger(TodoFormController.class);
	
	@Autowired
	TodoItemRepository todoItemRepository;
	
	/*
	 * Method to return add-todo-item view
	 */
	@GetMapping("/create-todo")
    public String showCreateForm(TodoItem todoItem){
        return "add-todo-item";
    }
	
	/*
	 * Method return update-todo-item view
	 */
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		  TodoItem todoItem = todoItemRepository
				  .findById(id)
				  .orElseThrow(() -> new IllegalArgumentException("Todo item "+id+" not found"));
		  model.addAttribute("todo", todoItem);
		  return "update-todo-item";
	}
	
	/*
	 * Method delete task and redirect it to index view
	 */
	@GetMapping("/delete/{id}")
	public String deleteForm(@PathVariable("id") long id, Model model) {
		  TodoItem todoItem = todoItemRepository
				  .findById(id)
				  .orElseThrow(() -> new IllegalArgumentException("Todo item "+id+" not found"));
		  todoItemRepository.delete(todoItem);
		  return "redirect:/";
	}
	
	/*
	 * Method update task and redirect it to index view
	 */
	@PostMapping("/todo/{id}")
	public String updateTodoItem(@PathVariable("id") long id, @Valid TodoItem todoItem, BindingResult result, Model model) {
		  if(result.hasErrors()) {
			  todoItem.setId(id);
			  return "update-todo-item";
	}		  
		  todoItem.setModifiedDate(Instant.now());
		  todoItemRepository.save(todoItem);
		  return "redirect:/";
	}
	
	/*
	 * Method add task and redirect it to index view
	 */
	@PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-todo-item";
        }
        todoItem.setCreatedDate(Instant.now());
        todoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(todoItem);
        return "redirect:/";
    }
}
