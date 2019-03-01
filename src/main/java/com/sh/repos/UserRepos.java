package com.sh.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sh.domain.User;

@Repository
public interface UserRepos extends JpaRepository<User, Long> {
	User findByAccount(String account);
	
	User findByAccountAndPassword(String account,String password);
}
