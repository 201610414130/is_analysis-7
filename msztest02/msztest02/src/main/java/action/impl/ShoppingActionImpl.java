package action.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import dao.InventoryDao;
import dao.OrderDao;
import dao.OrderproductDao;

import action.*;
import model.Order;
import model.Orderproduct;
import tools.DoubleMathTools;
import tools.Tools;
import tools.ToolsForService;

@Controller("shoppingAction")
@Scope("prototype")
public class ShoppingActionImpl extends ActionSupport implements ShoppingAction{
	private Order order;
	private List<Orderproduct> orderproductList;
	double shoppingTotalPrice = 0;
	private Orderproduct orderproductToStockOut;
	
	@Resource
	private InventoryDao inventoryDao;
	@Resource
	private OrderDao orderDao;
	@Resource
	private OrderproductDao orderproductDao;
	@Resource
	private ToolsForService toolsForService;
	
	/* (non-Javadoc)
	 * @see action.impl.ShoppingAction#execute()
	 */
	@Override
	public String execute(){
		System.out.println("ShoppingAction - CustomerName:"+order.getCustomerName());
		System.out.println("ShoppingAction - CustomerAdress:"+order.getCustomerAdress());
		System.out.println("ShoppingAction - CustomerTel:"+order.getCustomerTel());
		System.out.println("ShoppingAction - Remark:"+order.getRemark());
		showList();
		
		//OrderDao orderDao = new OrderDao();
		//OrderproductDao orderproductDao = new OrderproductDao();
		
		order.setOrderID(Tools.getID());
		order.setOrderDate(new Date());
		order.setShoppingTotalPrice(shoppingTotalPrice);
		order.setAfterService(false);
		
		if(orderDao.saveOrder(order)){
			if(saveOrderproduct()){
				return SUCCESS;
			}else{
				return ERROR;
			}
		}else{
			return ERROR;
		}
		
		
	}
	
	/* (non-Javadoc)
	 * @see action.impl.ShoppingAction#showList()
	 */
	@Override
	public void showList(){
		shoppingTotalPrice = 0;
		for(int i = 0;i < orderproductList.size();i += 1){
			if(orderproductList.get(i)==null){
				continue;
			}
			System.out.println(i+"ShoppingAction - ProductID:"+orderproductList.get(i).getProductID());
			System.out.println(i+"ShoppingAction - SellPrice:"+orderproductList.get(i).getSellPrice());
			System.out.println(i+"ShoppingAction - SellBoxNu:"+orderproductList.get(i).getSellBoxNum());
			System.out.println(i+"ShoppingAction - BoxOwn:"+orderproductList.get(i).getBoxOwn());
			System.out.println(i+"ShoppingAction - SellSingleNum:"+orderproductList.get(i).getSellSingleNum());
			/*计算总价*/
			shoppingTotalPrice += DoubleMathTools.mul(orderproductList.get(i).getSellPrice() , (orderproductList.get(i).getSellBoxNum() * orderproductList.get(i).getBoxOwn() + orderproductList.get(i).getSellSingleNum()));
		}
	}
	
	/* (non-Javadoc)
	 * @see action.impl.ShoppingAction#saveOrderproduct()
	 */
	@Override
	public boolean saveOrderproduct(){
		//OrderproductDao orderproductDao = new OrderproductDao();
		boolean state = false;
		for(int i = 0;i < orderproductList.size();i += 1){
			if(orderproductList.get(i)==null){
				continue;
			}
			orderproductList.get(i).setOrderProductID(Tools.getID());
			orderproductList.get(i).setOrderID(order.getOrderID());
			orderproductList.get(i).setStock(false);
			
			
			
			state = orderproductDao.saveOrderproduct(orderproductList.get(i));
			if(!state)
				break;
		}
		return state;
	}
	
	/* (non-Javadoc)
	 * @see action.impl.ShoppingAction#stockOut()
	 */
	@Override
	public String stockOut(){
		//ToolsForService toolsForService = new ToolsForService();
		//OrderproductDao orderproductDao = new OrderproductDao();
		//InventoryDao inventoryDao = new InventoryDao();
		System.out.println("ShoppingAction - stockOut OrderproductID:"+orderproductToStockOut.getOrderProductID());
		System.out.println("ShoppingAction - stockOut ProductID:"+orderproductToStockOut.getProductID());
		System.out.println("ShoppingAction - stockOut SellBoxNum:"+orderproductToStockOut.getSellBoxNum());
		System.out.println("ShoppingAction - stockOut BoxOwn:"+orderproductToStockOut.getBoxOwn());
		System.out.println("ShoppingAction - stockOut SellSingleNum:"+orderproductToStockOut.getSellSingleNum());
		
		if(!toolsForService.decreaseInventory(orderproductToStockOut.getProductID(), orderproductToStockOut.getSellBoxNum(), orderproductToStockOut.getBoxOwn(), orderproductToStockOut.getSellSingleNum(),orderproductToStockOut.getSellPrice())){
			System.out.println("ShoppingAction - stockOut未找到此商品库存信息:"+orderproductToStockOut.getProductID());
			/*此时是否销毁已创建订单*/
			return ERROR;
		}else{
			orderproductToStockOut.setStock(true);
			if(!orderproductDao.updateIsStockByOrderProductID(orderproductToStockOut)){
				System.out.println("ShoppingAction - stockOut OrderproductID出库操作更新失败:"+orderproductToStockOut.getOrderProductID());
				return ERROR;
			}
			return SUCCESS;
		}
	}
	
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
	/* (non-Javadoc)
	 * @see action.impl.ShoppingAction#getOrder()
	 */
	@Override
	public Order getOrder() {
		return order;
	}

	/* (non-Javadoc)
	 * @see action.impl.ShoppingAction#setOrder(model.Order)
	 */
	@Override
	public void setOrder(Order order) {
		this.order = order;
	}

	/* (non-Javadoc)
	 * @see action.impl.ShoppingAction#getOrderproductList()
	 */
	@Override
	public List<Orderproduct> getOrderproductList() {
		return orderproductList;
	}

	/* (non-Javadoc)
	 * @see action.impl.ShoppingAction#setOrderproductList(java.util.List)
	 */
	@Override
	public void setOrderproductList(List<Orderproduct> orderproductList) {
		this.orderproductList = orderproductList;
	}

	/* (non-Javadoc)
	 * @see action.impl.ShoppingAction#getOrderproductToStockOut()
	 */
	@Override
	public Orderproduct getOrderproductToStockOut() {
		return orderproductToStockOut;
	}

	/* (non-Javadoc)
	 * @see action.impl.ShoppingAction#setOrderproductToStockOut(model.Orderproduct)
	 */
	@Override
	public void setOrderproductToStockOut(Orderproduct orderproductToStockOut) {
		this.orderproductToStockOut = orderproductToStockOut;
	}

	
	
	
}
