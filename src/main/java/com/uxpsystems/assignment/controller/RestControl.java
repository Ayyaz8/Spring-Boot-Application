package com.uxpsystems.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.assignment.entity.UserEntity;
import com.uxpsystems.assignment.repository.UserRepository;

@RestController
public class RestControl {

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<List<UserEntity>> getUser() {

		List<UserEntity> listOfAllUsers = userRepository.findAll();

		return new ResponseEntity<>(listOfAllUsers, HttpStatus.OK);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody UserEntity user) {
		if (!userRepository.existsById(user.getUser_id())) {
			
			if(!user.getStatus().equalsIgnoreCase("ACTIVATED") && !user.getStatus().equalsIgnoreCase("DEACTIVATED")) {
				return new ResponseEntity<>("Status of user should be valid",HttpStatus.BAD_REQUEST);
			}
			userRepository.save(user);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>("User with given id already exist",HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(@PathVariable("id") Integer user_id) {

		if (userRepository.existsById(user_id)) {
			userRepository.deleteById(user_id);
			// System.out.println("hello");

			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Id not found", HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public ResponseEntity<String> updateUser(@RequestBody UserEntity user) {

		if (!userRepository.existsById(user.getUser_id())) {
			return new ResponseEntity<>("User does not exist to be updated",HttpStatus.BAD_REQUEST);
		}
		userRepository.save(user);
		return new ResponseEntity<>("User updated",HttpStatus.OK) ;

	}

}
