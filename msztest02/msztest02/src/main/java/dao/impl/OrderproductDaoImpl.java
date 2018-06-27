package dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.*;
import db.DB;
import db.DBH;
import model.Inventory;
import model.Order;
import model.Orderproduct;
import model.Store;

@Service("orderproductDao")
public class OrderproductDaoImpl implements OrderproductDao {
	private List lists;
	
	@Resource
	private DBH dbh;
	/* (non-Javadoc)
	 * @see dao.impl.OrderproductDao#saveOrderproduct(model.Orderproduct)
	 */
	@Override
	public boolean saveOrderproduct(Orderproduct orderproduct)
	{
//		DB db = new DB();
//		int state;
//		String prepSql = "insert into orderproduct(orderProductID,orderID,productID,sellPrice,sellBoxNum,BoxOwn,sellSingleNum,isStock,remark) values(?,?,?,?,?,?,?,?,?)";
//		state = db.excuteUpdate(prepSql, new String[]{orderproduct.getOrderProductID(),orderproduct.getOrderID(),orderproduct.getProductID(),orderproduct.getSellPrice()+"",orderproduct.getSellBoxNum()+"",orderproduct.getBoxOwn()+"",orderproduct.getSellSingleNum()+"",orderproduct.isStock()?"1":"0",orderproduct.getRemark()});
//		return state==0?false:true;
		 
		dbh.save(orderproduct);
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.OrderproductDao#getOrderproductByOrderID(java.lang.String)
	 */
	@Override
	public List<Orderproduct> getOrderproductByOrderID(String orderID)
	{
		 
		
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, orderID);
		
		String sql = "select * from orderproduct where orderID=?";
		
		lists =  dbh.excuteQueryPage(sql, param1, Orderproduct.class);
		
		return lists;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.OrderproductDao#updateIsStockByOrderProductID(model.Orderproduct)
	 */
	@Override
	public boolean updateIsStockByOrderProductID(Orderproduct orderproduct){
//		DB db = new DB();
//		int state;
//		String prepSql = "update orderproduct set isStock=? where orderProductID=?";
//		state = db.excuteUpdate(prepSql, new String[]{orderproduct.isStock()?"1":"0",orderproduct.getOrderProductID()});
//		if(state==0){
//			System.out.println("OrderproductDao - updateIsStockByOrderProductID 失败");
//			return false;
//		}else{
//			System.out.println("OrderproductDao - updateIsStockByOrderProductID 成功");
//			return true;
//		}
		 
		
		Orderproduct orderproducttemp = (Orderproduct)dbh.getUsersById(orderproduct.getOrderProductID(), Orderproduct.class);
		
		orderproduct.setOrderID(orderproducttemp.getOrderID());
		orderproduct.setSellPrice(orderproducttemp.getSellPrice());
		orderproduct.setRemark(orderproducttemp.getRemark());
		dbh.update(orderproduct);
		return true;
	}
	
	/*订单内项目不可编辑*/
	
	/* (non-Javadoc)
	 * @see dao.impl.OrderproductDao#deleteOrderproductByOrderID(java.lang.String)
	 */
	@Override
	public boolean deleteOrderproductByOrderID(String orderID){
//		DB db = new DB();
//		int state;
//		String prepSql = "delete from orderproduct where orderID=?";
//		state = db.excuteUpdate(prepSql, new String[]{orderID});
//		return state==0?false:true;
		 
		dbh.delete(orderID, Orderproduct.class);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.OrderproductDao#deleteOrderproductByOrderIDProductID(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteOrderproductByOrderIDProductID(String orderID,String productID){
//		DB db = new DB();
//		int state;
//		String prepSql = "delete from orderproduct where orderID=? and productID=?";
//		state = db.excuteUpdate(prepSql, new String[]{orderID,productID});
//		return state==0?false:true;
		 
		
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, orderID);
		param1.put(1, productID);
		
		String prepSql = "delete from orderproduct where orderID=? and productID=?";
		dbh.excuteQuery(prepSql, param1);
		return true;
	}
	
}
