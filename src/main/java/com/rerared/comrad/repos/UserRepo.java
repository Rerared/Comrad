package com.rerared.comrad.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rerared.comrad.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
	User findByUsername(String username);

}
