package com.sh.repos;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sh.domain.Car;

@Repository
public interface CarRepos extends JpaRepository<Car, Long>,JpaSpecificationExecutor<Car>  {
	
	List<Car> findByRecommendedType(String recommendedType,Sort sort);
	
}
