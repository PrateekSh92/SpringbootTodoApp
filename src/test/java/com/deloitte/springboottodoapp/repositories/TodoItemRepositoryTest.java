package com.deloitte.springboottodoapp.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import com.deloitte.springboottodoapp.models.TodoItem;
import com.deloitte.springboottodoapp.utils.MockUtils;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TodoItemRepositoryTest {

	@Autowired
	private TestEntityManager em;

	@Autowired
	private TodoItemRepository repository;

	@Test
	public void contextLoads() {
	   Assertions.assertNotNull(em);
	}

	@Test
	void verifyBootstrappingByPersistingAnEmployee() {
	  TodoItem todoItem = MockUtils.mockTodoItemForRequest();
	  Assertions.assertNull(todoItem.getId());
	  em.persist(todoItem);
	  Assertions.assertNotNull(todoItem.getId());
	}

	@Test
	void verifyRepositoryByPersistingAnEmployee() {
	  TodoItem todoItem = MockUtils.mockTodoItemForRequest();
	  Assertions.assertNull(todoItem.getId());
	  repository.save(todoItem);
	  Assertions.assertNotNull(todoItem.getId());
	}
}