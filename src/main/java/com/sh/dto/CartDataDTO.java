package com.sh.dto;

import java.math.BigDecimal;
import java.util.Date;

//购物车数据格式
public class CartDataDTO extends CommonDataDto {
	private String insurance;//保险
	private Date returnDate;//归还日期
	
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
	
	public CartDataDTO(long id, long userId, long carId, String name, String brand, BigDecimal price, String img,String insurance, Date returnDate) {
		super(id, userId, carId, name, brand, price, img);
		this.insurance = insurance;
		this.returnDate = returnDate;
	}


}
