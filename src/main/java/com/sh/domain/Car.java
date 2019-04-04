package com.sh.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;//名字
	private String brand;//品牌
	private BigDecimal price;//价格
	@Lob
    @Column(columnDefinition="text")
	private String img;//汽车图片
	private String recommendedType;//推荐类型：新手上路，老司机，车神来了
	private int recommendedAmount;//推荐量
	private int salesVolume;//销量
	private String factory;//厂商
	@Lob
    @Column(columnDefinition="text")
	private String factoryBg;//厂商背景
	@Lob
    @Column(columnDefinition="text")
	private String advantage;//优点
	@Lob
    @Column(columnDefinition="text")
	private String defect;//缺点
	private int mileage;//里程
	private String consumption;//油耗
	@ColumnDefault("false")
	private boolean isRent;//出租标志
	@ColumnDefault("false")
	private boolean isSub;//预约标志
	
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getFactoryBg() {
		return factoryBg;
	}
	public void setFactoryBg(String factoryBg) {
		this.factoryBg = factoryBg;
	}
	public String getAdvantage() {
		return advantage;
	}
	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}
	public String getDefect() {
		return defect;
	}
	public void setDefect(String defect) {
		this.defect = defect;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public String getConsumption() {
		return consumption;
	}
	public void setConsumption(String consumption) {
		this.consumption = consumption;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public int getRecommendedAmount() {
		return recommendedAmount;
	}
	public void setRecommendedAmount(int recommendedAmount) {
		this.recommendedAmount = recommendedAmount;
	}
	public int getSalesVolume() {
		return salesVolume;
	}
	public void setSalesVolume(int salesVolume) {
		this.salesVolume = salesVolume;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public boolean isRent() {
		return isRent;
	}
	public void setRent(boolean isRent) {
		this.isRent = isRent;
	}
	public boolean isSub() {
		return isSub;
	}
	public void setSub(boolean isSub) {
		this.isSub = isSub;
	}
	public String getRecommendedType() {
		return recommendedType;
	}
	public void setRecommendedType(String recommendedType) {
		this.recommendedType = recommendedType;
	}
}
