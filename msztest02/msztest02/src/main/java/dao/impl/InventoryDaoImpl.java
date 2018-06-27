package dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dao.*;
import db.DBH;
import model.Inventory;

@Service("inventoryDao")
@Component
public class InventoryDaoImpl implements InventoryDao {
	private List lists;
	private int pageAll;
	
	@Resource
	private DBH dbh;

	/* (non-Javadoc)
	 * @see dao.impl.InventoryDao#setDbh(db.DBH)
	 */
	@Override
	public void setDbh(DBH dbh) {
		this.dbh = dbh;
	}

	/* (non-Javadoc)
	 * @see dao.impl.InventoryDao#saveInventory(model.Inventory)
	 */
	@Override
	public boolean saveInventory(Inventory inventory)
	{
		 
		dbh.save(inventory);
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.InventoryDao#getInventoryByProductID(java.lang.String)
	 */
	@Override
	public List<Inventory> getInventoryByProductID(String productID)
	{
		 
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, productID);
		
		String sql = "select * from inventory where productID=?";
		
		lists =  dbh.excuteQueryPage(sql, param1, Inventory.class);
		return lists;
	}
	
	
	//--------Page----------
	
	/* (non-Javadoc)
	 * @see dao.impl.InventoryDao#getInventoryByProductIDPage(java.lang.String, int, int)
	 */
	@Override
	public List<Inventory> getInventoryByProductIDPage(String productID,int page,int pageNum)
	{
		 
		
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, productID);
		param1.put(1, page);
		param1.put(2, pageNum);
		
		String sql = "select * from inventory where productID=? limit ?,?";
		
		lists = dbh.excuteQueryPage(sql, param1, Inventory.class);
		
		Map<Object, Object> param2 = new HashMap<Object, Object>();  
		param2.put(0, productID);
		
		selectRowsNum("select count(*) from inventory where productID=?",param2);
		return lists;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.InventoryDao#getInventoryByProductIDBlurPage(java.lang.String, int, int)
	 */
	@Override
	public List<Inventory> getInventoryByProductIDBlurPage(String productID,int page,int pageNum)
	{
		productID = "%"+productID+"%";
		
		 
		
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, productID);
		param1.put(1, page);
		param1.put(2, pageNum);
		
		String sql = "select * from inventory where productID like ? limit ?,?";
		lists =  dbh.excuteQueryPage(sql, param1, Inventory.class);
		
		Map<Object, Object> param2 = new HashMap<Object, Object>();  
		param2.put(0, productID);
		
		selectRowsNum("select count(*) from inventory where productID like ?",param2);
		return lists;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.InventoryDao#getInventoryAllPage(int, int)
	 */
	@Override
	public List<Inventory> getInventoryAllPage(int page,int pageNum)
	{
		 
		
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, page);
		param1.put(1, pageNum);
		
		String sql = "select * from inventory limit ?,?";
		System.out.println("getInventoryAllPage : " + dbh);
		lists = dbh.excuteQueryPage(sql,param1,Inventory.class);
		
		selectRowsNum("select count(*) from inventory",null);
		return lists;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.InventoryDao#getInventoryByFactoryNamePage(java.lang.String, int, int)
	 */
	@Override
	public List<Inventory> getInventoryByFactoryNamePage(String factoryName,int page,int pageNum)
	{
		factoryName = "%"+factoryName+"%";
		
		 
		
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, factoryName);
		param1.put(1, page);
		param1.put(2, pageNum);
		
		String sql = "select * from inventory where factoryName like ? limit ?,?";
		
		lists =  dbh.excuteQueryPage(sql, param1, Inventory.class);
		
		Map<Object, Object> param2 = new HashMap<Object, Object>();  
		param2.put(0, factoryName);
		
		selectRowsNum("select count(*) from inventory where factoryName like ?",param2);
		return lists;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.InventoryDao#getInventoryBySizePage(java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public List<Inventory> getInventoryBySizePage(String size1,String size2,int page,int pageNum)
	{
		 
		
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, size1);
		param1.put(1, size2);
		param1.put(2, size2);
		param1.put(3, size1);
		param1.put(4, page);
		param1.put(5, pageNum);
		String sql = "select * from inventory where size1=? and size2=? || size1=? and size2=? limit ?,?";
		
		lists =  dbh.excuteQueryPage(sql, param1, Inventory.class);
		
		Map<Object, Object> param2 = new HashMap<Object, Object>();  
		param2.put(0, size1);
		param2.put(1, size2);
		param2.put(2, size2);
		param2.put(3, size1);
		
		selectRowsNum("select count(*) from inventory where size1=? and size2=? || size1=? and size2=?",param2);
		return lists;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.InventoryDao#getInventoryByBoxNumPage(java.lang.String, int, int)
	 */
	@Override
	public List<Inventory> getInventoryByBoxNumPage(String boxNum,int page,int pageNum)
	{
		 
		
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, boxNum);
		param1.put(1, page);
		param1.put(2, pageNum);
		
		String sql = "select * from inventory where boxNum<=? limit ?,?";
		
		lists =  dbh.excuteQueryPage(sql, param1, Inventory.class);
		
		Map<Object, Object> param2 = new HashMap<Object, Object>();  
		param2.put(0, boxNum);
		
		selectRowsNum("select count(*) from inventory where boxNum<=?",param2);
		return lists;
	}
	

	
	/* (non-Javadoc)
	 * @see dao.impl.InventoryDao#updateInventoryInfo(model.Inventory)
	 */
	@Override
	public boolean updateInventoryInfo(Inventory inventory){
		 
		dbh.update(inventory);
		return true;
	}
	
	

	/* (non-Javadoc)
	 * @see dao.impl.InventoryDao#deleteInventoryByProductID(java.lang.String)
	 */
	@Override
	public boolean deleteInventoryByProductID(String productID){
		 
		dbh.delete(productID, Inventory.class);
		return true;
	}
	

	/* (non-Javadoc)
	 * @see dao.impl.InventoryDao#selectRowsNum(java.lang.String, java.util.Map)
	 */
	@Override
	public boolean selectRowsNum(String sql,Map param){
		//pageAll = rs.getInt(1);
		pageAll = dbh.getPageCount(sql, param);
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.InventoryDao#getPageAll()
	 */
	@Override
	public int getPageAll() {
		return pageAll;
	}

	/* (non-Javadoc)
	 * @see dao.impl.InventoryDao#setPageAll(int)
	 */
	@Override
	public void setPageAll(int pageAll) {
		this.pageAll = pageAll;
	}
}
