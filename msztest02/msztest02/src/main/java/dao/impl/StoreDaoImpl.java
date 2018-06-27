package dao.impl;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import dao.*;
import db.DB;
import db.DBH;
import model.Service;
import model.Store;

@org.springframework.stereotype.Service("storeDao")
public class StoreDaoImpl implements StoreDao {
	private List lists;
	private int pageAll;
	
	@Resource
	private DBH dbh;
	
	/* (non-Javadoc)
	 * @see dao.impl.StoreDao#saveStore(model.Store)
	 */
	@Override
	public boolean saveStore(Store store)
	{
//		DB db = new DB();
//		int state;
//		String prepSql = "insert into store(storeID,storeDate,productID,factoryName,size1,size2,oriPrice,boxNum,boxOwn,singleNum,storeTotalPrice,remark) values(?,?,?,?,?,?,?,?,?,?,?,?)";
//		state = db.excuteUpdate(prepSql, new String[]{store.getStoreID(),""+new Timestamp(store.getStoreDate().getTime()),store.getProductID(),store.getFactoryName(),store.getSize1()+"",store.getSize2()+"",store.getOriPrice()+"",store.getBoxNum()+"",store.getBoxOwn()+"",store.getSingleNum()+"",store.getStoreTotalPrice()+"",store.getRemark()});
//		if(state==0){
//			System.out.println("StoreDao - saveStore 失败");
//			return false;
//		}else{
//			System.out.println("StoreDao - saveStore 成功");
//			return true;
//		}
		 
		dbh.save(store);
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.StoreDao#getStoreByStoreID(java.lang.String)
	 */
	@Override
	public List<Store> getStoreByStoreID(String storeID)
	{
		 
		
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, storeID);
		
		String sql = "select * from store where storeID=?";
		
		lists =  dbh.excuteQueryPage(sql,param1,Store.class);
		return lists;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.StoreDao#getStoreAll()
	 */
	@Override
	public List<Store> getStoreAll()
	{
		 
		String sql = "select * from store";
		lists =  dbh.excuteQueryPage(sql,null,Store.class);
		return lists;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.StoreDao#getStoreByProductIDBlur(java.lang.String)
	 */
	@Override
	public List<Store> getStoreByProductIDBlur(String productID)
	{
		productID = "%"+productID+"%";
		
		 
		
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, productID);
		
		String sql = "select * from store where productID like ?";
		
		lists =  dbh.excuteQueryPage(sql,param1,Store.class);
		
		return lists;
	}
	
	//--------Page--------
	
	/* (non-Javadoc)
	 * @see dao.impl.StoreDao#getStoreByStoreIDPage(java.lang.String, int, int)
	 */
	@Override
	public List<Store> getStoreByStoreIDPage(String storeID,int page,int pageNum)
	{
		 
		
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, storeID);
		param1.put(1, page);
		param1.put(2, pageNum);
		
		String sql = "select * from store where storeID=? limit ?,?";
		
		lists =  dbh.excuteQueryPage(sql, param1, Store.class);
		
		Map<Object, Object> param2 = new HashMap<Object, Object>();  
		param2.put(0, storeID);
		
		selectRowsNum("select count(*) from store where storeID=?",param2);
		return lists;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.StoreDao#getStoreAllPage(int, int)
	 */
	@Override
	public List<Store> getStoreAllPage(int page,int pageNum)
	{
		 
		
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, page);
		param1.put(1, pageNum);
		
		String sql = "select * from store limit ?,?";
		
		lists =  dbh.excuteQueryPage(sql, param1, Store.class);
		
		selectRowsNum("select count(*) from store",null);
		return lists;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.StoreDao#getStoreByProductIDBlurPage(java.lang.String, int, int)
	 */
	@Override
	public List<Store> getStoreByProductIDBlurPage(String productID,int page,int pageNum)
	{
		productID = "%"+productID+"%";
		
		 
		
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, productID);
		param1.put(1, page);
		param1.put(2, pageNum);
		
		String sql = "select * from store where productID like ? limit ?,?";
		
		lists =  dbh.excuteQueryPage(sql, param1, Store.class);
		Map<Object, Object> param2 = new HashMap<Object, Object>();  
		param2.put(0, productID);
		
		selectRowsNum("select count(*) from store where productID like ?",param2);
		return lists;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.StoreDao#getStoreByFactoryNamePage(java.lang.String, int, int)
	 */
	@Override
	public List<Store> getStoreByFactoryNamePage(String factoryName,int page,int pageNum)
	{
		factoryName = "%"+factoryName+"%";
		
		 
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, factoryName);
		param1.put(1, page);
		param1.put(2, pageNum);
		
		String sql = "select * from store where factoryName like ? limit ?,?";
		
		lists =  dbh.excuteQueryPage(sql, param1, Store.class);
		Map<Object, Object> param2 = new HashMap<Object, Object>();  
		param2.put(0, factoryName);
		
		selectRowsNum("select count(*) from store where factoryName like ?",param2);
		return lists;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.StoreDao#getStoreBySizePage(java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public List<Store> getStoreBySizePage(String size1,String size2,int page,int pageNum)
	{
		 
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, size1);
		param1.put(1, size2);
		param1.put(2, size2);
		param1.put(3, size1);
		param1.put(4, page);
		param1.put(5, pageNum);
		
		String sql = "select * from store where size1=? and size2=? || size1=? and size2=? limit ?,?";
		
		lists =  dbh.excuteQueryPage(sql, param1, Store.class);
		Map<Object, Object> param2 = new HashMap<Object, Object>();  
		param2.put(0, size1);
		param2.put(1, size2);
		param2.put(2, size2);
		param2.put(3, size1);
		
		selectRowsNum("select count(*) from store where size1=? and size2=? || size1=? and size2=?",param2);
		return lists;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.StoreDao#getStoreByBoxNumPage(java.lang.String, int, int)
	 */
	@Override
	public List<Store> getStoreByBoxNumPage(String boxNum,int page,int pageNum)
	{
		 
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, boxNum);
		param1.put(1, page);
		param1.put(2, pageNum);
		
		String sql = "select * from store where boxNum<=? limit ?,?";
		
		lists =  dbh.excuteQueryPage(sql, param1, Store.class);
		
		Map<Object, Object> param2 = new HashMap<Object, Object>();  
		param2.put(0, boxNum);
		
		selectRowsNum("select count(*) from store where boxNum<=?",param2);
		return lists;
	}
	//--------------------
	
	/* (non-Javadoc)
	 * @see dao.impl.StoreDao#updateStoreInfo(model.Store)
	 */
	@Override
	public boolean updateStoreInfo(Store store){
//		DB db = new DB();
//		int state;
//		String prepSql = "update store set storeDate=?,productID=?,factoryName=?,size1=?,size2=?,oriPrice=?,boxNum=?,boxOwn=?,singleNum=?,totalPrice=?,remark=? where storeID=?";
//		state = db.excuteUpdate(prepSql, new String[]{""+new java.sql.Date(store.getStoreDate().getTime()),store.getProductID(),store.getFactoryName(),store.getSize1()+"",store.getSize2()+"",store.getOriPrice()+"",store.getBoxNum()+"",store.getBoxOwn()+"",store.getSingleNum()+"",store.getStoreTotalPrice()+"",store.getRemark(),store.getStoreID()});
//		if(state==0){
//			System.out.println("StoreDao - updateStoreInfo 失败");
//			return false;
//		}else{
//			System.out.println("StoreDao - updateStoreInfo 成功");
//			return true;
//		}
		 
		dbh.update(store);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.StoreDao#deleteStoreByStoreID(java.lang.String)
	 */
	@Override
	public boolean deleteStoreByStoreID(String storeID){
//		DB db = new DB();
//		int state;
//		String prepSql = "delete from store where storeID=?";
//		state = db.excuteUpdate(prepSql, new String[]{storeID});
//		if(state==0){
//			System.out.println("StoreDao - deleteStoreByStoreID 失败");
//			return false;
//		}else{
//			System.out.println("StoreDao - deleteStoreByStoreID 成功");
//			return true;
//		}
		 
		dbh.delete(storeID, Store.class);
		return true;
	}
	

	private boolean selectRowsNum(String sql,Map param){
		//pageAll = rs.getInt(1);
		 
		pageAll = dbh.getPageCount(sql, param);
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.StoreDao#getPageAll()
	 */
	@Override
	public int getPageAll() {
		return pageAll;
	}

	/* (non-Javadoc)
	 * @see dao.impl.StoreDao#setPageAll(int)
	 */
	@Override
	public void setPageAll(int pageAll) {
		this.pageAll = pageAll;
	}
}
