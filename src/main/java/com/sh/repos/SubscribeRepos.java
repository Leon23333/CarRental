package com.sh.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sh.domain.Subscribe;
import com.sh.domain.User;

@Repository
public interface SubscribeRepos extends JpaRepository<Subscribe, Long> {
	List<Subscribe> findByUserId(long userId);
}
