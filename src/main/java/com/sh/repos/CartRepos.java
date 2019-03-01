package com.sh.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sh.domain.Cart;
import com.sh.dto.CommonDataDto;

@Repository
public interface CartRepos extends JpaRepository<Cart, Long> {
	void deleteByCarIdAndUserId(long carId,long userId);
	
	@Query(value = "select new com.sh.dto.CommonDataDto(b.id,b.userId,a.id AS carId,a.name,a.brand,a.price,a.img) from Car a,Cart b where a.id = b.carId and b.userId = ?1")
	List<CommonDataDto> getCart(long userId);
}
