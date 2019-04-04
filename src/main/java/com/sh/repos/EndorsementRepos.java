package com.sh.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sh.domain.Car;
import com.sh.domain.Endorsement;

@Repository
public interface EndorsementRepos extends JpaRepository<Endorsement, Long>,JpaSpecificationExecutor<Car>  {
	List<Endorsement> findByUserId(long userId);
}
