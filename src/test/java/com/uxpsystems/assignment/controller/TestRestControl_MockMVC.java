package com.uxpsystems.assignment.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.h2.command.ddl.CreateUser;
import org.h2.engine.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.assignment.entity.UserEntity;
import com.uxpsystems.assignment.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRestControl_MockMVC {
	private MockMvc mokmvc;
	
	@Autowired
	@MockBean
	private RestControl restResource;
	
	@MockBean 
	UserRepository repo;
	
		
	@Before
	public void setUp() throws Exception{
		
		mokmvc = MockMvcBuilders.standaloneSetup(restResource)
				.build();
		}
	
	
	
	
		
	@Test
	public void getUsersTest() throws Exception {		
		mokmvc.perform(
				MockMvcRequestBuilders.get("/user")
			)	
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		/*
		 * when(repo.findAll()).thenReturn(Stream .of(new UserEntity(1,"Lokesh",
		 * "Gupta", "Activated"), new UserEntity(5,"Deja", "Vu", "Deactivated"), new
		 * UserEntity(8,"Shahid", "Ans12", "Activated"), new UserEntity(3,"Caption",
		 * "America", "Activated")).collect(Collectors.toList()));
		 * assertEquals(4,restResource.getUser());
		 */
		
		
	}
	
	@Test
	public void postTest() {
		
		UserEntity user = new UserEntity(10,"Lokesh", "Gupta", "Activated");
		
		when(repo.save(user)).thenReturn(user);
		assertEquals(user, restResource.createUser(user));
		
		
	}
	
	@Test
	public void deleteTest() {
		
		UserEntity user =new UserEntity(1,"Lokesh", "Gupta", "Activated");
		
		restResource.deleteUser(1);
		
		//verify(repo,times(1)).delete(user);
		
	}
	
}
