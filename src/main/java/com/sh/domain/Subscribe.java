package com.sh.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonFormat;

//预约表
@Entity
public class Subscribe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long carId;
	private long userId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date subDate;
	@ColumnDefault("false")
	private boolean isOvertime;//是否过期
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
	public Date getSubDate() {
		return subDate;
	}
	public void setSubDate(Date subDate) {
		this.subDate = subDate;
	}
	public boolean isOvertime() {
		return isOvertime;
	}
	public void setOvertime(boolean isOvertime) {
		this.isOvertime = isOvertime;
	}
}
