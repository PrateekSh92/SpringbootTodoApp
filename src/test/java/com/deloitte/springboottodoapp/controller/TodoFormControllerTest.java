package com.deloitte.springboottodoapp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.deloitte.springboottodoapp.models.TodoItem;
import com.deloitte.springboottodoapp.repositories.TodoItemRepository;
import com.deloitte.springboottodoapp.utils.MockUtils;

@WebMvcTest(TodoFormController.class)
public class TodoFormControllerTest {

	@MockBean
	private TodoItemRepository todoItemRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@WithMockUser(username = "admin", roles = {"manager"})
	void testShowCreateForm() throws Exception {
		this.mockMvc
		    .perform(MockMvcRequestBuilders.get("/create-todo"))
		    .andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("add-todo-item"));
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"manager"})
	void testShowUpdateForm() throws Exception {
		when(todoItemRepository.findById(1L)).thenReturn(MockUtils.mockTodoItem());
		this.mockMvc
		    .perform(MockMvcRequestBuilders.get("/edit/1"))
		    .andExpect(MockMvcResultMatchers.status().isOk())
		    .andExpect(MockMvcResultMatchers.model().attributeExists("todo"))
		    .andExpect(MockMvcResultMatchers.model().attribute("todo", Matchers.notNullValue()))
			.andExpect(MockMvcResultMatchers.view().name("update-todo-item"));
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"manager"})
	void testDeleteForm() throws Exception {
		when(todoItemRepository.findById(1L)).thenReturn(MockUtils.mockTodoItem());
		this.mockMvc
		    .perform(MockMvcRequestBuilders.get("/delete/1"))
		    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
			.andExpect(MockMvcResultMatchers.view().name("redirect:/"));
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"manager"})
	void testUpdateTodoItem() throws Exception {
        String description = "Task 2";
		this.mockMvc.perform(MockMvcRequestBuilders.post("/todo/1")
		    		.with(csrf())
		    		.contentType(MediaType.APPLICATION_FORM_URLENCODED)
		    		.param("description", description)
		    		.sessionAttr("todo", MockUtils.mockTodoItemForRequest())
		    		)
		    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
			.andExpect(MockMvcResultMatchers.view().name("redirect:/"));
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"manager"})
	void testAddTodoItem() throws Exception {
        String description = "Task 2";
		this.mockMvc.perform(MockMvcRequestBuilders.post("/todo")
		    		.with(csrf())
		    		.contentType(MediaType.APPLICATION_FORM_URLENCODED)
		    		.param("description", description)
		    		.sessionAttr("todo", MockUtils.mockTodoItemForRequest())
		    		)
		    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
			.andExpect(MockMvcResultMatchers.view().name("redirect:/"));
	}
}
