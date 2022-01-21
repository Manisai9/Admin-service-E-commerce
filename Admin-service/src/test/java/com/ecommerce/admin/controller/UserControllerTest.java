package com.ecommerce.admin.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecommerce.admin.entity.User;
import com.ecommerce.admin.header.HeaderGenerator;
import com.ecommerce.admin.service.UserService;


@SpringBootTest(classes= {UserControllerTest.class})
public class UserControllerTest {
	
	@Mock
	private UserService userService;
	
	@Mock
	private HeaderGenerator headerGenerator;
	
	@InjectMocks
	UserController userController;
	
	  List<User> allUsers;
	  
	  User user; 
	  
	 @Test
	 public void testViewAllUser() {
		allUsers = new ArrayList<User>();
		allUsers.add(new User(1L, "Pavan123", "SaiPavan", "Kumar", "saipavan@gmail.com", "7996155024", "Hyderabad",
				"Pavan123", "user"));
		allUsers.add(new User(2L, "mani123", "Mani", "sai", "manisai@gmail.com", "7730883078", "Hyderabad",
				"Manisai123", "user"));
	 when(userService.getAllUser()).thenReturn(allUsers);
	 ResponseEntity<List<User>> response = userController.viewAllUser();
	 
	 assertEquals(HttpStatus.OK,response.getStatusCode());
	 assertEquals(2,response.getBody().size());
	 
	 }
	 
	 @Test
	 public void testViewUser() {
		 user = new User(2L, "mani123", "Mani", "sai", "manisai@gmail.com", "7730883078", "Hyderabad",
					"Manisai123", "user");
		 String userName = "mani123";
		 when(userService.getUser(userName)).thenReturn(user);
		 ResponseEntity<User> response = userController.viewUser(userName);
		 
		 assertEquals(HttpStatus.OK,response.getStatusCode());
		 assertEquals(user,response.getBody());
	 }
	
	 

}
