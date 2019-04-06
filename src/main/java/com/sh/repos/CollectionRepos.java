package com.sh.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sh.domain.Collection;
import com.sh.dto.CommonDataDto;

@Repository
public interface CollectionRepos extends JpaRepository<Collection, Long> {
	@Query(value = "select new com.sh.dto.CommonDataDto(b.id,b.userId,a.id AS carId,a.name,a.brand,a.price,a.img) from Car a,Collection b where a.id = b.carId and b.userId = ?1")
	List<CommonDataDto> findCollection(long userId);
	
	Collection findByCarIdAndUserId(long carId,long userId);
	
	List<Collection> findByCarId(long carId);
}
