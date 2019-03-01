package com.sh.repos;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sh.domain.Comment;

@Repository
public interface CommentRepos extends JpaRepository<Comment, Long> {
	List<Comment> findByCarId(long carId,Sort sort);
	
	void deleteByOrderId(long orderId);
}
