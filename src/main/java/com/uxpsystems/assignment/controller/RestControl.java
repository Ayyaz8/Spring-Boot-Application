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

import com.uxpsystems.assignment.dao.UserEntity;
import com.uxpsystems.assignment.dao.ValidationObject;
import com.uxpsystems.assignment.services.ApplicationService;

@RestController
public class RestControl {

	@Autowired
	ApplicationService applicationService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<List<UserEntity>> getUsers() {

		List<UserEntity> listOfAllUsers = applicationService.getUsers();
		// List<UserEntity> listOfAllUsers = userRepository.findAll();

		return new ResponseEntity<>(listOfAllUsers, HttpStatus.OK);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody UserEntity user) {

		ValidationObject validation = new ValidationObject();
		applicationService.createUser(user, validation);

		if (validation.isWasValidStatus() == false) {
			return new ResponseEntity<>("Status of user should be valid", HttpStatus.BAD_REQUEST);
		}
		if (validation.isUserDoesnotExists()) {
			return new ResponseEntity<>("User with given id already exist", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Created Successfully", HttpStatus.OK);

	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(@PathVariable("id") Integer user_id) {

		ValidationObject validation = new ValidationObject();
		applicationService.deleteUser(user_id, validation);
		if (validation.isUserDoesnotExists()) {
			return new ResponseEntity<String>("Id not found", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Deleted", HttpStatus.OK);

	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public ResponseEntity<String> updateUser(@RequestBody UserEntity user) {

		ValidationObject validation = new ValidationObject();
		applicationService.updateUser(user, validation);
		if (validation.isWasValidStatus() == false) {
			return new ResponseEntity<>("Status of user should be valid", HttpStatus.BAD_REQUEST);
		}
		if (validation.isUserDoesnotExists()) {
			return new ResponseEntity<>("User does not exist to be updated", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("User updated", HttpStatus.OK);

	}

}
