package action.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import dao.InventoryDao;
import dao.StoreDao;

import action.*;
import model.Inventory;
import model.Store;
import tools.DoubleMathTools;
import tools.Tools;

@Controller("storeAction")
@Scope("prototype")
public class StoreActionImpl extends ActionSupport implements StoreAction{
	private Store store;
	
	@Resource
	private InventoryDao inventoryDao;
	@Resource
	private StoreDao storeDao;
	
	private Inventory inventory = null;
	
	/* (non-Javadoc)
	 * @see action.impl.StoreAction#execute()
	 */
	@Override
	public String execute(){
		System.out.println("StoreAction - getProductID:"+store.getProductID());
		System.out.println("StoreAction - getFactoryName:"+store.getFactoryName());
		System.out.println("StoreAction - getSize1:"+store.getSize1());
		System.out.println("StoreAction - getSize2:"+store.getSize2());
		System.out.println("StoreAction - getOriPrice:"+store.getOriPrice());
		System.out.println("StoreAction - getBoxNum:"+store.getBoxNum());
		System.out.println("StoreAction - getBoxOwn:"+store.getBoxOwn());
		System.out.println("StoreAction - getSingleNum:"+store.getSingleNum());
		System.out.println("StoreAction - getRemark:"+store.getRemark());
		
		/*初始化变量*/
		boolean state = false;
		store.setStoreID(Tools.getID());
		store.setStoreDate(new Date()); 
		double storeTotalPriceTemp = DoubleMathTools.mul(store.getOriPrice() , (store.getBoxNum() * store.getBoxOwn() + store.getSingleNum() ));
		store.setStoreTotalPrice(storeTotalPriceTemp);
		
		/*存入数据库*/
		if(storeDao.saveStore(store)){
			if(initInventory() == 0){ /*初始化库存数据*/
				System.out.println("StoreAction - excute():无库存，新建");
				state = inventoryDao.saveInventory(inventory);
			}else{
				System.out.println("StoreAction - excute():有库存，更新");
				state = inventoryDao.updateInventoryInfo(inventory);
			}
		}else{
			return ERROR;
		}
		
		if(state){
			return SUCCESS;
		}else{
			return ERROR;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see action.impl.StoreAction#initInventory()
	 */
	@Override
	public int initInventory(){
		List inventoryList = inventoryDao.getInventoryByProductID(store.getProductID());
		System.out.println("StoreAction - inventoryList.size():"+inventoryList.size()+"  store.getProductID():"+store.getProductID());
		if(inventoryList.size() == 0){
			/*未找到此产品的ID记录*/
			inventory = new Inventory(store.getProductID(),store.getFactoryName(),store.getSize1(),store.getSize2(),store.getOriPrice(),store.getBoxNum(),store.getBoxOwn(),store.getSingleNum(),store.getStoreTotalPrice(),store.getRemark());
		}else{
			/*已找到此产品的ID记录*/
			inventory = (Inventory) inventoryList.get(0);
			
			System.out.println("initInventory - getProductID:"+inventory.getProductID());
			System.out.println("initInventory - getFactoryName:"+inventory.getFactoryName());
			System.out.println("initInventory - getSize1:"+inventory.getSize1());
			System.out.println("initInventory - getSize2:"+inventory.getSize2());
			System.out.println("initInventory - getOriPrice:"+inventory.getOriPrice());
			System.out.println("initInventory - getBoxNum:"+inventory.getBoxNum());
			System.out.println("initInventory - getBoxOwn:"+inventory.getBoxOwn());
			System.out.println("initInventory - getSingleNum:"+inventory.getSingleNum());
			System.out.println("initInventory - getRemark:"+inventory.getRemark());
			inventory.setFactoryName(store.getFactoryName());
			inventory.setSize1(store.getSize1());
			inventory.setSize2(store.getSize2());
			inventory.setOriPrice(store.getOriPrice());
			inventory.setRemark(store.getRemark());
			
			long sumInventory = inventory.getBoxNum() * inventory.getBoxOwn() + inventory.getSingleNum();
			long sumOrder = store.getBoxNum() * store.getBoxOwn() + store.getSingleNum();
			
			long restOfInventory = sumInventory + sumOrder;
			
			long sellBoxNumTemp = restOfInventory / inventory.getBoxOwn();
			long singleNumTemp = restOfInventory % inventory.getBoxOwn();
			
			double totalPrice = DoubleMathTools.add(inventory.getTotalPrice() , store.getStoreTotalPrice());
			inventory.setTotalPrice(totalPrice);
			
			inventory.setBoxNum(sellBoxNumTemp);
			inventory.setSingleNum(singleNumTemp);

			System.out.print("initInventory - store:"+store);
			System.out.print("initInventory - inventory:"+inventory);
			/*
			long singleNumTemp = store.getSingleNum() + inventory.getSingleNum();
			long boxNumTemp = singleNumTemp / store.getBoxOwn() + store.getBoxNum() + inventory.getBoxNum();
			singleNumTemp = singleNumTemp % store.getBoxNum();
			
			inventory.setBoxNum(boxNumTemp);
			inventory.setSingleNum(singleNumTemp);
			*/
			/*
			long singleNumTemp = inventory.getSingleNum() + store.getSingleNum();
			System.out.println("StoreAction - singleNumTemp:"+singleNumTemp);
			//单项数量超过一件容量，向上增加
			if(singleNumTemp >= inventory.getBoxOwn()){
				inventory.setBoxNum(inventory.getBoxNum() + store.getBoxNum() + 1);
				inventory.setSingleNum(singleNumTemp - inventory.getBoxOwn());
			}else{
				inventory.setBoxNum(inventory.getBoxNum() + store.getBoxNum());
				inventory.setSingleNum(singleNumTemp);
			}*/
		}
		/*此次编辑的时间*/
		//inventory.setRemark(Tools.getTimeNow());
		return inventoryList.size();
	}

	/* (non-Javadoc)
	 * @see action.impl.StoreAction#getStore()
	 */
	@Override
	public Store getStore() {
		return store;
	}

	/* (non-Javadoc)
	 * @see action.impl.StoreAction#setStore(model.Store)
	 */
	@Override
	public void setStore(Store store) {
		this.store = store;
	}
	
}
