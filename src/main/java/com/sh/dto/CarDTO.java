package com.sh.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sh.domain.Car;
import com.sh.domain.Comment;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class CarDTO {
	
	private Car car;
	private boolean isCollected;//是否被收藏
	private List<Comment> comments;
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public boolean isCollected() {
		return isCollected;
	}

	public void setCollected(boolean isCollected) {
		this.isCollected = isCollected;
	}
}
