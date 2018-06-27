package dao;

import java.util.List;

import model.Order;

public interface OrderDao {

	boolean saveOrder(Order order);

	List<Order> getOrderByOrderID(String orderID);

	List<Order> getOrderAll();

	List<Order> getOrderAPart(String searchinfo);

	List<Order> getOrderByOrderIDPage(String orderID, int page, int pageNum);

	List<Order> getOrderAllPage(int page, int pageNum);

	List<Order> getOrderAPartPage(String searchinfo, int page, int pageNum);

	//---------
	boolean updateOrderInfo(Order order);

	boolean deleteOrderByOrderID(String orderID);

	int getPageAll();

	void setPageAll(int pageAll);

}