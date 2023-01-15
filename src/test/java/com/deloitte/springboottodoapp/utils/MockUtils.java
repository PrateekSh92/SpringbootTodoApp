package com.deloitte.springboottodoapp.utils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.deloitte.springboottodoapp.models.TodoItem;

public class MockUtils {

	public static List<TodoItem> mockTodoList() {
		List<TodoItem> todoList = new ArrayList<>();
		TodoItem todoItem = new TodoItem();
		todoItem.setId(1L);
		todoItem.setDescription("Task 1");
		todoItem.setComplete(false);
		todoItem.setCreatedDate(Instant.parse("2018-11-30T18:35:24.00Z"));
		todoItem.setModifiedDate(Instant.parse("2018-11-30T18:35:24.00Z"));
		todoList.add(todoItem);
		return todoList;
	}
	
	public static Optional<TodoItem> mockTodoItem() {
		TodoItem todoItem = new TodoItem();
		todoItem.setId(1L);
		todoItem.setDescription("Task 1");
		todoItem.setComplete(false);
		todoItem.setCreatedDate(Instant.parse("2018-11-30T18:35:24.00Z"));
		todoItem.setModifiedDate(Instant.parse("2018-11-30T18:35:24.00Z"));
		return Optional.of(todoItem);
	}
	
	public static TodoItem mockTodoItemForRequest() {
		TodoItem todoItem = new TodoItem();
		todoItem.setDescription("Task 1");
		todoItem.setComplete(false);
		todoItem.setCreatedDate(Instant.parse("2018-11-30T18:35:24.00Z"));
		todoItem.setModifiedDate(Instant.parse("2018-11-30T18:35:24.00Z"));
		return todoItem;
	}
}
