package com.sh.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

//个人收藏
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames={"carId", "userId"})})
public class Collection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long carId;
	private long userId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCarId() {
		return carId;
	}
	public void setCarId(long carId) {
		this.carId = carId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
}
