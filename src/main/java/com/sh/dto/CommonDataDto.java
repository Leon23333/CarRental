package com.sh.dto;

import java.math.BigDecimal;

//查询购物车和收藏 通用数据格式
public class CommonDataDto {
	private long id;
	private long userId;
	private long carId;
	private String name;//名字
	private String brand;//品牌
	private BigDecimal price;//价格
	private String img;
	
	public CommonDataDto(long id, long userId, long carId, String name, String brand, BigDecimal price, String img) {
		super();
		this.id = id;
		this.userId = userId;
		this.carId = carId;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.img = img;
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
