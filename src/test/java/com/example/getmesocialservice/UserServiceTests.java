package com.example.getmesocialservice;

import com.example.getmesocialservice.model.User;
import com.example.getmesocialservice.service.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
	@Autowired
	private UserService userService;

	@Before
	public void saveUser(){
		userService.saveUser(new User("1", "tasnim",
				"tasnim@gmail.com", "url1", 30, "dhaka"));
	}

	@Test
	public void getUser(){
		User user = userService.getById("1");
		Assert.assertEquals("tasnim", user.getName());
	}

	@Test
	public void editUser(){
		User user = userService.getById("1");
		user.setName("mou");
		userService.saveUser(user);
		Assert.assertEquals("tasnim", user.getName());
	}

	@After
	public void delete(){
		userService.deleteUser("2");
	}

//	@Test
//	void sample() {
//		Assert.assertEquals("tasnim", "tasnim");
//	}
//
//	@Test
//	void sample2() {
//		Assert.assertEquals("tasnim", "mou");
//	}

}
