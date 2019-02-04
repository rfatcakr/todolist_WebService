package com.example.todolist;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.todolist.controller.UserController;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterTest {
	@Autowired
    private UserController controller;

	@Test
	public void contextLoads()throws Exception{
		assertThat(controller).isNotNull();
	}
}
