package com.sh.dto;

import java.math.BigDecimal;
import java.util.Date;


public class SubscribeCar {
	private long subscribeId;
	private long carId;
	private String name;//名字
	private String brand;//品牌
	private BigDecimal price;//价格
	private String insurance;//保险
	private Date returnDate;//归还日期
	private String img;
	private long userId;
	private Date subDate;
	private boolean isOvertime;//是否过期
	public long getSubscribeId() {
		return subscribeId;
	}
	public void setSubscribeId(long subscribeId) {
		this.subscribeId = subscribeId;
	}
	public long getCarId() {
		return carId;
	}
	public void setCarId(long carId) {
		this.carId = carId;
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
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
