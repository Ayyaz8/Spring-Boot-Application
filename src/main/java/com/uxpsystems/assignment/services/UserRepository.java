package com.uxpsystems.assignment.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uxpsystems.assignment.dao.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
