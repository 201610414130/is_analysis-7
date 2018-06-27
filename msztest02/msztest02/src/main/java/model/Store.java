package model;

import java.util.Date;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
public class Store {
	private String storeID;
	private Date storeDate;
	private String productID;
	private String factoryName;
	private int size1;
	private int size2;
	private double oriPrice;
	private long boxNum;
	private int boxOwn;
	private long singleNum;
	private double storeTotalPrice;
	private String remark;
	
	public Store(){}
	
	public Store(String storeID, Date storeDate, String productID, String factoryName, int size1, int size2,double oriPrice, long boxNum, int boxOwn, long singleNum, double storeTotalPrice, String remark) {
		this.storeID = storeID;
		this.storeDate = storeDate;
		this.productID = productID;
		this.factoryName = factoryName;
		this.size1 = size1;
		this.size2 = size2;
		this.oriPrice = oriPrice;
		this.boxNum = boxNum;
		this.boxOwn = boxOwn;
		this.singleNum = singleNum;
		this.storeTotalPrice = storeTotalPrice;
		this.remark = remark;
	}
	public String getStoreID() {
		return storeID;
	}
	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}
	public Date getStoreDate() {
		return storeDate;
	}
	public void setStoreDate(Date storeDate) {
		this.storeDate = storeDate;
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
	public double getStoreTotalPrice() {
		return storeTotalPrice;
	}
	public void setStoreTotalPrice(double storeTotalPrice) {
		this.storeTotalPrice = storeTotalPrice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
