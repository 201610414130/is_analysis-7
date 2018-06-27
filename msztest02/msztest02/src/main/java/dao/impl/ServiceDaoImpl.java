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
import model.Inventory;
import model.Order;
import model.Service;
import model.Store;

@org.springframework.stereotype.Service("serviceDao")
public class ServiceDaoImpl implements ServiceDao {
	private List lists;
	
	@Resource
	private DBH dbh;
	/* (non-Javadoc)
	 * @see dao.impl.ServiceDao#saveService(model.Service)
	 */
	@Override
	public boolean saveService(Service service)
	{
//		DB db = new DB();
//		int state;
//		String prepSql = "insert into service(serverID,orderID,serviceTime,productID,type,remark) values(?,?,?,?,?,?)";
//		state = db.excuteUpdate(prepSql, new String[]{service.getServerID(),service.getOrderID(),""+new Timestamp(service.getServiceTime().getTime()),service.getProductID(),service.isType()?"1":"0",service.getRemark()});
//		return state==0?false:true;
		 
		dbh.save(service);
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.ServiceDao#getServiceByOrderID(java.lang.String)
	 */
	@Override
	public List<Service> getServiceByOrderID(String orderID)
	{
		 
		
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, orderID);
		
		String sql = "select * from service where orderID=?";
		
		lists = dbh.excuteQueryPage(sql, param1, Service.class);
		
		return lists;
	}
	
	/*售后内项目不可编辑*/
	
	/* (non-Javadoc)
	 * @see dao.impl.ServiceDao#deleteServiceByOrderID(java.lang.String)
	 */
	@Override
	public boolean deleteServiceByOrderID(String orderID){
//		DB db = new DB();
//		int state;
//		String prepSql = "delete from service where orderID=?";
//		state = db.excuteUpdate(prepSql, new String[]{orderID});
//		return state==0?false:true;
		 
		dbh.delete(orderID, Service.class);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.ServiceDao#deleteServiceByOrderIDServiceID(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteServiceByOrderIDServiceID(String orderID,String serverID){
//		DB db = new DB();
//		int state;
//		String prepSql = "delete from service where orderID=? and serverID=?";
//		state = db.excuteUpdate(prepSql, new String[]{orderID,serverID});
//		return state==0?false:true;
		 
		
		Map<Object, Object> param1 = new HashMap<Object, Object>();  
		param1.put(0, orderID);
		param1.put(1, serverID);
		
		String prepSql = "delete from service where orderID=? and serverID=?";
		dbh.excuteQuery(prepSql, param1);
		return true;
	}
	
	private List<Service> selectService(String sql,String[] param){
		DB db = new DB();
		List serviceList = null;
		if(sql != null && !sql.equals("")){
			//getRs(sql);
			ResultSet rs = db.excuteQuery(sql,param);
			if(rs != null){
				serviceList = new ArrayList();
				try{
					while(rs.next()){
						Service serviceSingle = new Service();
						serviceSingle.setServerID(rs.getString(1));
						serviceSingle.setOrderID(rs.getString(2));
						serviceSingle.setServiceTime(rs.getTimestamp(3));
						serviceSingle.setProductID(rs.getString(4));
						serviceSingle.setType(rs.getBoolean(5));
						serviceSingle.setRemark(rs.getString(6));
						serviceList.add(serviceSingle);
					}
				}catch(Exception e){
					System.out.println("封装 Service 表中数据失败！");
					e.printStackTrace();
					return null;
				}finally{
			 		db.closed();
			 	}
			}
		}
		return serviceList;
	}
}
