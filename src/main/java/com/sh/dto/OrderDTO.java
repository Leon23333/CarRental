package com.sh.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class OrderDTO {
	private long id;//订单id
	private long userId;
	private long carId;
	private String name;//名字
	private String brand;//品牌
	private String img;
	private BigDecimal amount;//订单金额
	private String insurance;//保险
	private Date returnDate;//归还日期
	private int status;//订单状态 0：未完成  1：已完成
	private Date createTime;
	private Date endTime;
	
	public OrderDTO(long id, long userId, long carId, String name, String brand, String img, BigDecimal amount,
			int status, Date createTime, Date endTime,String insurance,Date returnDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.carId = carId;
		this.name = name;
		this.brand = brand;
		this.img = img;
		this.amount = amount;
		this.status = status;
		this.createTime = createTime;
		this.endTime = endTime;
		this.insurance = insurance;
		this.returnDate = returnDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
}
