package com.deloitte.springboottodoapp.controller;

import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.MockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.web.servlet.HttpBasicDsl;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.deloitte.springboottodoapp.models.TodoItem;
import com.deloitte.springboottodoapp.repositories.TodoItemRepository;
import com.deloitte.springboottodoapp.utils.MockUtils;

@WebMvcTest(TodoItemsController.class)
public class TodoItemsControllerTest {

	@MockBean
	private TodoItemRepository todoItemRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@WithMockUser(username = "admin", roles = {"manager"})
	void testFetchIndex() throws Exception {
		when(todoItemRepository.findAll()).thenReturn(MockUtils.mockTodoList());
		this.mockMvc
		    .perform(MockMvcRequestBuilders.get("/"))
		    .andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeExists("todoItems"))
			.andExpect(MockMvcResultMatchers.model().attribute("todoItems", Matchers.notNullValue()));
	}
}
