package com.sh.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

//评价表
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long carId;
	private long orderId;
	private long userId;
	@Lob
	@Column(columnDefinition = "text")
	private String content;
	private Date createTime;

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

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
