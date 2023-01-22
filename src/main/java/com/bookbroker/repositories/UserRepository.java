package com.bookbroker.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bookbroker.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
	public Optional<User> findByEmail(String email);
}
