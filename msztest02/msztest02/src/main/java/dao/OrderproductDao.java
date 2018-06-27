package dao;

import java.util.List;

import model.Orderproduct;

public interface OrderproductDao {

	boolean saveOrderproduct(Orderproduct orderproduct);

	List<Orderproduct> getOrderproductByOrderID(String orderID);

	boolean updateIsStockByOrderProductID(Orderproduct orderproduct);

	boolean deleteOrderproductByOrderID(String orderID);

	boolean deleteOrderproductByOrderIDProductID(String orderID, String productID);

}