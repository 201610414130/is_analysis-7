package model;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
public class Orderproduct {
	private String orderProductID;
	private String orderID;
	private String productID;
	private double sellPrice;
	private long sellBoxNum;
	private int BoxOwn;
	private long sellSingleNum;
	private boolean isStock;
	private String remark;
	
	public Orderproduct(){}
	
	public Orderproduct(String orderProductID, String orderID, String productID, double sellPrice, long sellBoxNum,int boxOwn, long sellSingleNum, boolean isStock, String remark) {
		super();
		this.orderProductID = orderProductID;
		this.orderID = orderID;
		this.productID = productID;
		this.sellPrice = sellPrice;
		this.sellBoxNum = sellBoxNum;
		BoxOwn = boxOwn;
		this.sellSingleNum = sellSingleNum;
		this.isStock = isStock;
		this.remark = remark;
	}

	public String getOrderProductID() {
		return orderProductID;
	}


	public void setOrderProductID(String orderProductID) {
		this.orderProductID = orderProductID;
	}


	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public long getSellBoxNum() {
		return sellBoxNum;
	}
	public void setSellBoxNum(long sellBoxNum) {
		this.sellBoxNum = sellBoxNum;
	}
	public int getBoxOwn() {
		return BoxOwn;
	}
	public void setBoxOwn(int boxOwn) {
		BoxOwn = boxOwn;
	}
	public long getSellSingleNum() {
		return sellSingleNum;
	}
	public void setSellSingleNum(long sellSingleNum) {
		this.sellSingleNum = sellSingleNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isStock() {
		return isStock;
	}

	public void setStock(boolean isStock) {
		this.isStock = isStock;
	}
	
	
}
