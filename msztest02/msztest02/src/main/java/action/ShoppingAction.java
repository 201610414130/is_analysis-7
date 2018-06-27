package action;

import java.util.List;

import model.Order;
import model.Orderproduct;

public interface ShoppingAction {

	String execute();

	void showList();

	boolean saveOrderproduct();

	String stockOut();

	/*
		public boolean decreaseInventory(Orderproduct orderproduct){
			Inventory inventory;
			InventoryDao inventoryDao = new InventoryDao();
			List inventoryList = inventoryDao.getInventoryByProductID(orderproduct.getProductID());
			
			System.out.println("StoreAction - inventoryList.size():"+inventoryList.size()+"  store.getProductID():"+orderproduct.getProductID());
			if(inventoryList.size() == 0){
				//未找到此产品的ID记录
				System.out.println("ShoppingAction - decreaseInventory未找到此商品ID:"+orderproduct.getProductID());
			}else{
				//已找到此产品的ID记录
				inventory = (Inventory) inventoryList.get(0);
				
				long sumInventory = inventory.getBoxNum() * inventory.getBoxOwn() + inventory.getSingleNum();
				long sumOrderproduct = orderproduct.getSellBoxNum() * orderproduct.getBoxOwn() + orderproduct.getSellSingleNum();
				
				long restOfInventory = sumInventory - sumOrderproduct;
				
				long sellBoxNumTemp = restOfInventory / inventory.getBoxOwn();
				long singleNumTemp = restOfInventory % inventory.getBoxOwn();
				
				inventory.setBoxNum(sellBoxNumTemp);
				inventory.setSingleNum(singleNumTemp);
				
				//此次编辑的时间
				inventory.setRemark(Tools.getTimeNow());
				System.out.println("StoreAction - singleNumTemp:"+singleNumTemp);
			}
			return inventoryList.size()==0?false:true;
		}
	*/
	Order getOrder();

	void setOrder(Order order);

	List<Orderproduct> getOrderproductList();

	void setOrderproductList(List<Orderproduct> orderproductList);

	Orderproduct getOrderproductToStockOut();

	void setOrderproductToStockOut(Orderproduct orderproductToStockOut);

}