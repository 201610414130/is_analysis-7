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
				//δ�ҵ��˲�Ʒ��ID��¼
				System.out.println("ShoppingAction - decreaseInventoryδ�ҵ�����ƷID:"+orderproduct.getProductID());
			}else{
				//���ҵ��˲�Ʒ��ID��¼
				inventory = (Inventory) inventoryList.get(0);
				
				long sumInventory = inventory.getBoxNum() * inventory.getBoxOwn() + inventory.getSingleNum();
				long sumOrderproduct = orderproduct.getSellBoxNum() * orderproduct.getBoxOwn() + orderproduct.getSellSingleNum();
				
				long restOfInventory = sumInventory - sumOrderproduct;
				
				long sellBoxNumTemp = restOfInventory / inventory.getBoxOwn();
				long singleNumTemp = restOfInventory % inventory.getBoxOwn();
				
				inventory.setBoxNum(sellBoxNumTemp);
				inventory.setSingleNum(singleNumTemp);
				
				//�˴α༭��ʱ��
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