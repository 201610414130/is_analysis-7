package dao;

import java.util.List;
import java.util.Map;

import db.DBH;
import model.Inventory;

public interface InventoryDao {

	void setDbh(DBH dbh);

	boolean saveInventory(Inventory inventory);

	List<Inventory> getInventoryByProductID(String productID);

	List<Inventory> getInventoryByProductIDPage(String productID, int page, int pageNum);

	List<Inventory> getInventoryByProductIDBlurPage(String productID, int page, int pageNum);

	List<Inventory> getInventoryAllPage(int page, int pageNum);

	List<Inventory> getInventoryByFactoryNamePage(String factoryName, int page, int pageNum);

	List<Inventory> getInventoryBySizePage(String size1, String size2, int page, int pageNum);

	List<Inventory> getInventoryByBoxNumPage(String boxNum, int page, int pageNum);

	boolean updateInventoryInfo(Inventory inventory);

	boolean deleteInventoryByProductID(String productID);

	boolean selectRowsNum(String sql, Map param);

	int getPageAll();

	void setPageAll(int pageAll);

}