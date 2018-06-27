package model;

import java.util.Date;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
public class Order {
	private String orderID;
	private Date orderDate;
	private String customerName;
	private String customerAdress;
	private String customerTel;
	private String remark;
	private double shoppingTotalPrice;
	private boolean afterService;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Order(String orderID, Date orderDate, String customerName, String customerAdress, String customerTel,
			String remark, double shoppingTotalPrice, boolean afterService) {
		super();
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.customerName = customerName;
		this.customerAdress = customerAdress;
		this.customerTel = customerTel;
		this.remark = remark;
		this.shoppingTotalPrice = shoppingTotalPrice;
		this.afterService = afterService;
	}


	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAdress() {
		return customerAdress;
	}
	public void setCustomerAdress(String customerAdress) {
		this.customerAdress = customerAdress;
	}
	public String getCustomerTel() {
		return customerTel;
	}
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public double getShoppingTotalPrice() {
		return shoppingTotalPrice;
	}
	public void setShoppingTotalPrice(double shoppingTotalPrice) {
		this.shoppingTotalPrice = shoppingTotalPrice;
	}
	public boolean isAfterService() {
		return afterService;
	}
	public void setAfterService(boolean afterService) {
		this.afterService = afterService;
	}
	
	
}
