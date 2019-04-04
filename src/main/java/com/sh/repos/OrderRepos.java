package com.sh.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sh.domain.OrderInfo;
import com.sh.dto.OrderDTO;

@Repository
public interface OrderRepos extends JpaRepository<OrderInfo, Long> {
	@Query(value = "select new com.sh.dto.OrderDTO(b.id,b.userId,a.id AS carId,a.name,a.brand,a.img,b.amount,b.status,b.createTime,b.endTime,b.insurance,b.returnDate) from Car a,OrderInfo b where a.id = b.carId and b.userId = ?1 order by b.createTime desc")
	List<OrderDTO> findOrderDTOs(long userId);
}
