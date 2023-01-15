package com.deloitte.springboottodoapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

import org.junit.jupiter.api.Test;

import com.deloitte.springboottodoapp.models.TodoItem;
import com.deloitte.springboottodoapp.utils.MockUtils;

public class TodoItemTest {

	@Test
	void verifyTodoItemTest() {
	  TodoItem todoItem = MockUtils.mockTodoItemForRequest();
	  assertEquals(todoItem.getDescription(), "Task 1");
	  assertEquals(todoItem.getCreatedDate(), Instant.parse("2018-11-30T18:35:24.00Z"));
	  assertEquals(todoItem.getModifiedDate(), Instant.parse("2018-11-30T18:35:24.00Z"));
	  assertEquals(todoItem.isComplete(), false);
	}
}
