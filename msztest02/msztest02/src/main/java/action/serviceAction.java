package action;

import java.util.List;

import model.Inventory;
import model.Order;
import model.Orderproduct;
import model.Store;

public interface serviceAction {

	String execute();

	String getOrderForService();

	boolean getOrderproductByOrderID();

	String getInventoryForService();

	String getStoreForService();

	void getPageForward();

	String getSearchinfo();

	void setSearchinfo(String searchinfo);

	List<Order> getOrderServiceList();

	void setOrderServiceList(List<Order> orderServiceList);

	List<List<Orderproduct>> getOrderproductServiceList();

	void setOrderproductServiceList(List<List<Orderproduct>> orderproductServiceList);

	String getSearchchoose();

	void setSearchchoose(String searchchoose);

	List<Inventory> getInventoryServiceList();

	void setInventoryServiceList(List<Inventory> inventoryServiceList);

	List<Store> getStoreServiceList();

	void setStoreServiceList(List<Store> storeServiceList);

	String getPageDirection();

	void setPageDirection(String pageDirection);

	int getPageNow();

	void setPageNow(int pageNow);

	int getPageNum();

	void setPageNum(int pageNum);

	int getPageAll();

	void setPageAll(int pageAll);

}