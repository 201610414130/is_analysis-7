package dao.impl;

import java.sql.ResultSet;
import java.sql.Timestamp;
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
import model.Store;
import tools.Tools;

@Service("orderDao")
public class OrderDaoImpl implements OrderDao {
	private List lists;
	private int pageAll;
	
	@Resource
	private DBH dbh;
	/* (non-Javadoc)
	 * @see dao.impl.OrderDao#saveOrder(model.Order)
	 */
	@Override
	public boolean saveOrder(Order order)
	{
//		DB db = new DB();
//		int state;
//		String prepSql = "insert into ordermain(orderID,orderDate,customerName,customerAdress,customerTel,remark,shoppingTotalPrice,afterService) values(?,?,?,?,?,?,?,?)";
//		state = db.excuteUpdate(prepSql, new String[]{order.getOrderID(),""+new Timestamp(order.getOrderDate().getTime()),order.getCustomerName(),order.getCustomerAdress(),order.getCustomerTel(),order.getRemark(),order.getShoppingTotalPrice()+"",order.isAfterService()?"1":"0"});
//		return state==0?false:true;
		
		 
		dbh.save(order);
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.OrderDao#getOrderByOrderID(java.lang.String)
	 */
	@Override
	public List<Order> getOrderByOrderID(String orderID)
	{
//		String sql = "select * from ordermain where orderID=?";
//		lists =  selectOrder(sql,new String[]{orderID});
//		return lists;
		 
		
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, orderID);
		
		String sql = "select * from ordermain where orderID=?";
		
		lists =  dbh.excuteQueryPage(sql, param1, Order.class);
		return lists;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.OrderDao#getOrderAll()
	 */
	@Override
	public List<Order> getOrderAll()
	{
		String sql = "select * from ordermain";
		lists =  selectOrder(sql,null);
		return lists;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.OrderDao#getOrderAPart(java.lang.String)
	 */
	@Override
	public List<Order> getOrderAPart(String searchinfo)
	{
		searchinfo = "%"+searchinfo+"%";
		if(Tools.isContainChinese(searchinfo)){
			String sql = "select * from ordermain where customerName like ? || customerAdress like ? || customerTel like ? || remark like ?";
			lists =  selectOrder(sql,new String[]{searchinfo,searchinfo,searchinfo,searchinfo});
		}else{
			String sql = "select * from ordermain where orderDate like ? || customerName like ? || customerAdress like ? || customerTel like ? || remark like ?";
			lists =  selectOrder(sql,new String[]{searchinfo,searchinfo,searchinfo,searchinfo,searchinfo});
		}
		return lists;
	}
	
	//----------Page-----------
	
	/* (non-Javadoc)
	 * @see dao.impl.OrderDao#getOrderByOrderIDPage(java.lang.String, int, int)
	 */
	@Override
	public List<Order> getOrderByOrderIDPage(String orderID,int page,int pageNum)
	{
		 
		
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, orderID);
		param1.put(1, page);
		param1.put(2, pageNum);
		
		String sql = "select * from ordermain where orderID=? limit ?,?";
		
		lists =  dbh.excuteQueryPage(sql, param1, Order.class);
		
		Map<Object, Object> param2 = new HashMap<Object, Object>();  
		param2.put(0, orderID);
		
		selectRowsNum("select count(*) from ordermain where orderID=?",param2);
		return lists;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.OrderDao#getOrderAllPage(int, int)
	 */
	@Override
	public List<Order> getOrderAllPage(int page,int pageNum)
	{
		 
		
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, page);
		param1.put(1, pageNum);
		String sql = "select * from ordermain limit ?,?";
		
		lists =  dbh.excuteQueryPage(sql, param1, Order.class);
		
		selectRowsNum("select count(*) from ordermain",null);
		return lists;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.OrderDao#getOrderAPartPage(java.lang.String, int, int)
	 */
	@Override
	public List<Order> getOrderAPartPage(String searchinfo,int page,int pageNum)
	{
		searchinfo = "%"+searchinfo+"%";
		 
		
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, searchinfo);
		param1.put(1, searchinfo);
		param1.put(2, searchinfo);
		param1.put(3, searchinfo);
		param1.put(4, page);
		param1.put(5, pageNum);
		
		Map<Object, Object> param2 = new HashMap<Object, Object>();  
		param2.put(0, searchinfo);
		param2.put(1, searchinfo);
		param2.put(2, searchinfo);
		param2.put(3, searchinfo);
		
		
		if(Tools.isContainChinese(searchinfo)){
			String sql = "select * from ordermain where customerName like ? || customerAdress like ? || customerTel like ? || remark like ? limit ?,?";
			lists =  dbh.excuteQueryPage(sql, param1, Order.class);
			selectRowsNum("select count(*) from ordermain where customerName like ? || customerAdress like ? || customerTel like ? || remark like ?",param2);
		}else{
			param1.put(4, searchinfo);
			param1.put(5, page);
			param1.put(6, pageNum);
			String sql = "select * from ordermain where orderDate like ? || customerName like ? || customerAdress like ? || customerTel like ? || remark like ? limit ?,?";
			lists =  dbh.excuteQueryPage(sql, param1, Order.class);
			param2.put(4, searchinfo);
			selectRowsNum("select count(*) from ordermain where orderDate like ? || customerName like ? || customerAdress like ? || customerTel like ? || remark like ?",param2);
		}
		return lists;
	}
	//---------
	/* (non-Javadoc)
	 * @see dao.impl.OrderDao#updateOrderInfo(model.Order)
	 */
	@Override
	public boolean updateOrderInfo(Order order){
//		DB db = new DB();
//		int state;
//		String prepSql = "update ordermain set orderDate=?,customerName=?,customerAdress=?,customerTel=?,remark=?,shoppingTotalPrice=?,afterService=? where orderID=?";
//		state = db.excuteUpdate(prepSql, new String[]{""+new java.sql.Date(order.getOrderDate().getTime()),order.getCustomerName(),order.getCustomerAdress(),order.getCustomerTel(),order.getRemark(),order.getShoppingTotalPrice()+"",order.isAfterService()?"1":"0",order.getOrderID()});
//		return state==0?false:true;
		 
		dbh.update(order);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.OrderDao#deleteOrderByOrderID(java.lang.String)
	 */
	@Override
	public boolean deleteOrderByOrderID(String orderID){
//		DB db = new DB();
//		int state;
//		String prepSql = "delete from ordermain where orderID=?";
//		state = db.excuteUpdate(prepSql, new String[]{orderID});
//		return state==0?false:true;
		 
		dbh.delete(orderID, Order.class);
		return true;
	}
	
	private List<Order> selectOrder(String sql,String[] param){
		DB db = new DB();
		List orderList = null;
		if(sql != null && !sql.equals("")){
			//getRs(sql);
			ResultSet rs = db.excuteQuery(sql,param);
			if(rs != null){
				orderList = new ArrayList();
				try{
					while(rs.next()){
						Order orderSingle = new Order();
						orderSingle.setOrderID(rs.getString(1));
						orderSingle.setOrderDate(rs.getTimestamp(2));
						orderSingle.setCustomerName(rs.getString(3));
						orderSingle.setCustomerAdress(rs.getString(4));
						orderSingle.setCustomerTel(rs.getString(5));
						orderSingle.setRemark(rs.getString(6));
						orderSingle.setShoppingTotalPrice(rs.getDouble(7));
						orderSingle.setAfterService(rs.getBoolean(8));
						orderList.add(orderSingle);
					}
				}catch(Exception e){
					System.out.println("封装 Order 表中数据失败！");
					e.printStackTrace();
					return null;
				}finally{
			 		db.closed();
			 	}
			}
		}
		return orderList;
	}
	
	private List<Order> selectOrderPage(String sql,String[] param){
		DB db = new DB();
		List orderList = null;
		if(sql != null && !sql.equals("")){
			//getRs(sql);
			ResultSet rs = db.excuteQueryPage(sql,param);
			if(rs != null){
				orderList = new ArrayList();
				try{
					while(rs.next()){
						Order orderSingle = new Order();
						orderSingle.setOrderID(rs.getString(1));
						orderSingle.setOrderDate(rs.getTimestamp(2));
						orderSingle.setCustomerName(rs.getString(3));
						orderSingle.setCustomerAdress(rs.getString(4));
						orderSingle.setCustomerTel(rs.getString(5));
						orderSingle.setRemark(rs.getString(6));
						orderSingle.setShoppingTotalPrice(rs.getDouble(7));
						orderSingle.setAfterService(rs.getBoolean(8));
						orderList.add(orderSingle);
					}
				}catch(Exception e){
					System.out.println("封装 Order 表中数据失败！");
					e.printStackTrace();
					return null;
				}finally{
			 		db.closed();
			 	}
			}
		}
		return orderList;
	}
	
	private boolean selectRowsNum(String sql,Map param){
//		DB db = new DB();
//		if(sql != null && !sql.equals("")){
//			//getRs(sql);
//			ResultSet rs = db.excuteQuery(sql,param);
//			if(rs != null){
//				try{
//					while(rs.next()){
//						pageAll = rs.getInt(1);
//						System.out.println(rs.getInt(1)+":OrderDao pageAll:"+rs.getString(1));
//					}
//					
//				}catch(Exception e){
//					System.out.println("封装 Order 表中 Page 数据失败！");
//					e.printStackTrace();
//					return false;
//				}finally{
//			 		db.closed();
//			 	}
//			}
//		}
		 
		pageAll = dbh.getPageCount(sql, param);
		
		return true;
	}

	/* (non-Javadoc)
	 * @see dao.impl.OrderDao#getPageAll()
	 */
	@Override
	public int getPageAll() {
		return pageAll;
	}

	/* (non-Javadoc)
	 * @see dao.impl.OrderDao#setPageAll(int)
	 */
	@Override
	public void setPageAll(int pageAll) {
		this.pageAll = pageAll;
	}
	
}
