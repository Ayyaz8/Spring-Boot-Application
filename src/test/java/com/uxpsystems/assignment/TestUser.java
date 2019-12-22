package com.uxpsystems.assignment;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.uxpsystems.assignment.dao.UserEntity;
import com.uxpsystems.assignment.dao.ValidationObject;
import com.uxpsystems.assignment.services.ApplicationService;
import com.uxpsystems.assignment.services.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class TestUser {

	@InjectMocks
	ApplicationService applicationService;

	@Mock
	UserRepository userRepository;

	@Test
	public void getAllUsersTest() {
		List<UserEntity> list = new ArrayList<UserEntity>();
		UserEntity u1 = new UserEntity(1, "John", "John", "howtodoinjava@gmail.com");
		UserEntity u2 = new UserEntity(2, "Alex", "kolenchiski", "alexk@yahoo.com");
		UserEntity u3 = new UserEntity(3, "Steve", "Waugh", "swaugh@gmail.com");

		list.add(u1);
		list.add(u2);
		list.add(u3);

		when(userRepository.findAll()).thenReturn(list);

		List<UserEntity> userList = applicationService.getUsers();

		assertEquals(3, userList.size());
		// test
		verify(userRepository, times(1)).findAll();
	}

	@Test
	public void createUserTest() {
		UserEntity u = new UserEntity(6, "Lokesh", "Gupta", "activated");
		when(userRepository.save(u)).thenReturn(u);
		ValidationObject v = new ValidationObject();
		assertThat(applicationService.createUser(u, v), is(notNullValue()));

	}

	@Test
	public void deleteUserTest() {
		UserEntity u = new UserEntity(6, "Lokesh", "Gupta", "activated");

		userRepository.save(u);
		when(userRepository.existsById(6)).thenReturn(true);
		userRepository.deleteById(6);
		ValidationObject v = new ValidationObject();
		assertThat(applicationService.deleteUser(6, v), is(true));

	}

	@Test
	public void updateUserTest() {
		UserEntity u = new UserEntity(6, "Lokesh", "Gupta", "activated");
		userRepository.save(u);

		when(userRepository.existsById(6)).thenReturn(true);
		u.setUser_name("Askash");
		when(userRepository.save(u)).thenReturn(u);
		ValidationObject v = new ValidationObject();
		assertThat(applicationService.updateUser(u, v), is(notNullValue()));

	}

}
