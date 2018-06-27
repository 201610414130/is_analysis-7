package dao;

import java.util.List;

import model.Store;

public interface StoreDao {

	boolean saveStore(Store store);

	List<Store> getStoreByStoreID(String storeID);

	List<Store> getStoreAll();

	List<Store> getStoreByProductIDBlur(String productID);

	List<Store> getStoreByStoreIDPage(String storeID, int page, int pageNum);

	List<Store> getStoreAllPage(int page, int pageNum);

	List<Store> getStoreByProductIDBlurPage(String productID, int page, int pageNum);

	List<Store> getStoreByFactoryNamePage(String factoryName, int page, int pageNum);

	List<Store> getStoreBySizePage(String size1, String size2, int page, int pageNum);

	List<Store> getStoreByBoxNumPage(String boxNum, int page, int pageNum);
	//--------------------

	boolean updateStoreInfo(Store store);

	boolean deleteStoreByStoreID(String storeID);

	int getPageAll();

	void setPageAll(int pageAll);

}