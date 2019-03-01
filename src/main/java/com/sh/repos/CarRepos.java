package com.sh.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sh.domain.Car;

@Repository
public interface CarRepos extends JpaRepository<Car, Long>,JpaSpecificationExecutor<Car>  {
	
	
//	@Query(value = "select a.* from car a,subscribe b where a. a.id = b.car_id and b.user_id = ?1", nativeQuery = true)
//	List<Car> getSub(long userId);
}
