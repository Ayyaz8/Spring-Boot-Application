package com.uxpsystems.assignment.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uxpsystems.assignment.dao.UserEntity;
import com.uxpsystems.assignment.dao.ValidationObject;

@Component
public class ApplicationService {

	@Autowired
	UserRepository userRepository;

	public List<UserEntity> getUsers() {
		return userRepository.findAll();
	}

	public boolean createUser(UserEntity user, ValidationObject validation) {
		if (!userRepository.existsById(user.getUser_id())) {

			if (!user.getStatus().equalsIgnoreCase("ACTIVATED") && !user.getStatus().equalsIgnoreCase("DEACTIVATED")) {
				validation.setWasValidStatus(false);
				return false;
			}
			userRepository.save(user);

			return true;
		}
		validation.setUserDoesnotExists(true);
		return false;
	}

	public boolean deleteUser(Integer user_id, ValidationObject validation) {

		if (userRepository.existsById(user_id)) {
			userRepository.deleteById(user_id);

			return true;
		}
		validation.setUserDoesnotExists(true);

		return false;
	}

	public boolean updateUser(UserEntity user, ValidationObject validation) {
		if (userRepository.existsById(user.getUser_id())) {
			if (!user.getStatus().equalsIgnoreCase("ACTIVATED") && !user.getStatus().equalsIgnoreCase("DEACTIVATED")) {
				validation.setWasValidStatus(false);
				return false;
			}
			userRepository.save(user);
			return true;
		}
		validation.setUserDoesnotExists(true);

		return false;

	}

}
