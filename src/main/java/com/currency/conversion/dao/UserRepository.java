package com.currency.conversion.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.currency.conversion.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
