package msztest02;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.InventoryDao;
import db.DBH;
import model.Inventory;
import tools.DoubleMathTools;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="classpath:beans.xml")  
public class SpringTest {
	 @Resource  
	 private Date date;  
	 
	 @Resource  
	 private Inventory inventory;
	 
	 @Resource
	 private DBH dbhi;
	 
	 @Resource
	 private DoubleMathTools doubleMathTools;
	 
	 @Resource
	 private InventoryDao inventoryDao;
	      
	 @Test //测试Spring IOC的开发环境  
	 public void springIoc() {  
	    //System.out.println(inventory);  
	     
	    //Inventory inventory = new Inventory("112211", "大家收到货566", 12, 13, 15.82, 555,12, 1, 231, "Good");
	    //dbhi.update(inventory);
		 System.out.println(5.7*6.9);
		 System.out.println(doubleMathTools.mul(5.7,6.9));
	 }  
	 
	 
	 @Ignore
	 public void testPage(){
		String sql = "select * from inventory where productID like ? limit ?,?";
		String sqlc = "select count(*) from inventory where productID like ? ";	
		String productID = "11223";
		int page = 0;
		int pageNum = 3;
		String parms[] = new String[]{productID,page+"",pageNum+""};
		productID = "%"+productID+"%";
		Map<Object, Object> myMap = new HashMap<Object, Object>();  
		myMap.put(0, productID);
		myMap.put(1, 0);
		myMap.put(2, 1);
		List<Inventory> list = dbhi.excuteQueryPage(sql, myMap, Inventory.class);
		Map<Object, Object> myMap2 = new HashMap<Object, Object>();  
		myMap2.put(0, productID);
		System.out.println(" n : " + dbhi.getPageCount(sqlc, myMap2));
		for(int i = 0;i < list.size(); i += 1){
			System.out.println(" i : " + list.get(i).getProductID());
			System.out.println(" i : " + list.get(i).getFactoryName());
		}
	 }
	 
	 @Ignore
	 public void test3(){
		 List<Inventory> list = inventoryDao.getInventoryAllPage(0, 10);
		 for(int i = 0;i < list.size(); i += 1){
				System.out.println(" i : " + list.get(i).getProductID());
				System.out.println(" i : " + list.get(i).getFactoryName());
			}
	 }
	 
	    
}
