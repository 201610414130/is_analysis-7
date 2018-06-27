package model;

import org.springframework.stereotype.Component;

@Component
public class Inventory {
	private String productID;
	private String factoryName;
	private int size1;
	private int size2;
	private double oriPrice;
	private long boxNum;
	private int boxOwn;
	private long singleNum;
	private double totalPrice;
	private String remark;
	
	public Inventory(){
		
	}
	
	public Inventory(String productID, String factoryName, int size1, int size2, double oriPrice, long boxNum,int boxOwn, long singleNum, double totalPrice, String remark) {
		this.productID = productID;
		this.factoryName = factoryName;
		this.size1 = size1;
		this.size2 = size2;
		this.oriPrice = oriPrice;
		this.boxNum = boxNum;
		this.boxOwn = boxOwn;
		this.singleNum = singleNum;
		this.totalPrice = totalPrice;
		this.remark = remark;
	}

	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public int getSize1() {
		return size1;
	}
	public void setSize1(int size1) {
		this.size1 = size1;
	}
	public int getSize2() {
		return size2;
	}
	public void setSize2(int size2) {
		this.size2 = size2;
	}
	public double getOriPrice() {
		return oriPrice;
	}
	public void setOriPrice(double oriPrice) {
		this.oriPrice = oriPrice;
	}
	public long getBoxNum() {
		return boxNum;
	}
	public void setBoxNum(long boxNum) {
		this.boxNum = boxNum;
	}
	public int getBoxOwn() {
		return boxOwn;
	}
	public void setBoxOwn(int boxOwn) {
		this.boxOwn = boxOwn;
	}
	public long getSingleNum() {
		return singleNum;
	}
	public void setSingleNum(long singleNum) {
		this.singleNum = singleNum;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
