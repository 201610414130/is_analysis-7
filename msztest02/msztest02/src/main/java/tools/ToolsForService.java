package tools;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import dao.InventoryDao;
import model.Inventory;

@Component
public class ToolsForService {
	@Resource
	InventoryDao inventoryDao;
	public boolean decreaseInventory(String productID, long boxNum, long boxOwn,long singleNum,double sellPrice){
		
		Inventory inventory;
		//InventoryDao inventoryDao = new InventoryDao();
		List inventoryList = inventoryDao.getInventoryByProductID(productID);
		
		System.out.println("StoreAction - inventoryList.size():"+inventoryList.size()+"  store.getProductID():"+productID);
		if(inventoryList.size() == 0){
			/*δ�ҵ��˲�Ʒ��ID��¼*/
			System.out.println("ToolsForService - decreaseInventoryδ�ҵ�����ƷID:"+productID);
		}else{
			/*���ҵ��˲�Ʒ��ID��¼*/
			inventory = (Inventory) inventoryList.get(0);
			
			long sumInventory = inventory.getBoxNum() * inventory.getBoxOwn() + inventory.getSingleNum();
			long sumOrderproduct = boxNum * boxOwn + singleNum;
			
			long restOfInventory = sumInventory - sumOrderproduct;
			/*С������*/
			if(restOfInventory < 0){
				System.out.println("decreaseInventory ��治�㣺"+productID);
				return false;
			}
			
			long sellBoxNumTemp = restOfInventory / inventory.getBoxOwn();
			long singleNumTemp = restOfInventory % inventory.getBoxOwn();
			
			inventory.setBoxNum(sellBoxNumTemp);
			inventory.setSingleNum(singleNumTemp);
			
			double totalPrice = DoubleMathTools.add(sellPrice, (boxNum * boxOwn + singleNum));
			double totalPriceInventory = DoubleMathTools.sub(inventory.getTotalPrice() , totalPrice);
			
			if(totalPriceInventory < 0.00001){
				return false;
			}
			
			inventory.setTotalPrice(totalPriceInventory);
			
			/*�˴α༭��ʱ��*/
			inventory.setRemark(Tools.getTimeNow());
			inventoryDao.updateInventoryInfo(inventory);
			System.out.println("StoreAction - singleNumTemp:"+singleNumTemp);
		}
		return inventoryList.size()==0?false:true;
	}
	
	public boolean creaseInventory(String productID, long boxNum, long boxOwn,long singleNum,double sellPrice){
		Inventory inventory;
		//InventoryDao inventoryDao = new InventoryDao();
		List inventoryList = inventoryDao.getInventoryByProductID(productID);
		
		System.out.println("StoreAction - inventoryList.size():"+inventoryList.size()+"  store.getProductID():"+productID);
		if(inventoryList.size() == 0){
			/*δ�ҵ��˲�Ʒ��ID��¼*/
			System.out.println("ToolsForService - decreaseInventoryδ�ҵ�����ƷID:"+productID);
		}else{
			/*���ҵ��˲�Ʒ��ID��¼*/
			inventory = (Inventory) inventoryList.get(0);
			
			long sumInventory = inventory.getBoxNum() * inventory.getBoxOwn() + inventory.getSingleNum();
			long sumOrderproduct = boxNum * boxOwn + singleNum;
			
			long restOfInventory = sumInventory + sumOrderproduct;
			
			long sellBoxNumTemp = restOfInventory / inventory.getBoxOwn();
			long singleNumTemp = restOfInventory % inventory.getBoxOwn();
			
			inventory.setBoxNum(sellBoxNumTemp);
			inventory.setSingleNum(singleNumTemp);
			
			double totalPrice = DoubleMathTools.add(sellPrice, (boxNum * boxOwn + singleNum));
			double totalPriceInventory = DoubleMathTools.add(inventory.getTotalPrice() , totalPrice);
			inventory.setTotalPrice(totalPriceInventory);
			/*�˴α༭��ʱ��*/
			inventory.setRemark(Tools.getTimeNow());
			inventoryDao.updateInventoryInfo(inventory);
			System.out.println("StoreAction - singleNumTemp:"+singleNumTemp);
		}
		return inventoryList.size()==0?false:true;
	}
}
