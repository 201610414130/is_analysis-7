package action.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.OrderDao;
import dao.OrderproductDao;
import dao.ServiceDao;

import action.*;
import model.Order;
import model.Orderproduct;
import model.Service;

import tools.Tools;
import tools.ToolsForService;

@Controller("afterServiceAction")
@Scope("prototype")
public class AfterServiceActionImpl extends ActionSupport implements AfterServiceAction{
	private Orderproduct orderproductToStockOut;
	private Service serviceToStockOut;
	private String orderIDForAfterAction;
	private List<Orderproduct> orderproductListAfterService;
	private List<Service> serviceListAfterService;
	
	
	@Resource
	private OrderDao orderDao;
	@Resource
	private OrderproductDao OrderproductDao;
	@Resource
	private ServiceDao serviceDao;
	@Resource
	private ToolsForService toolsForService;
	
	/* (non-Javadoc)
	 * @see action.impl.AfterServiceAction#execute()
	 */
	@Override
	public String execute(){
		
		return SUCCESS;
	}
	
	/* (non-Javadoc)
	 * @see action.impl.AfterServiceAction#getAfterServiceOrder()
	 */
	@Override
	public String getAfterServiceOrder(){
		System.out.println("AfterServiceAction - getAfterServiceOrder orderIDForAfterAction:"+orderIDForAfterAction);
		Order orderForAfterService = new Order();
		//OrderDao orderDao = new OrderDao();
		//OrderproductDao OrderproductDao = new OrderproductDao();
		//ServiceDao serviceDao = new ServiceDao();
		List<Orderproduct> orderproductListForAfterService = null;
		List<Order> orderList = null;
		List<Service> serviceListForAfterService = null;
		List<Orderproduct> serviceOrderproductListForAfterService = null;
		
		initOrderIDForAfterAction();
		
		orderList = orderDao.getOrderByOrderID(orderIDForAfterAction);
		if(orderList !=null && orderList.size() != 0){
			orderForAfterService = orderDao.getOrderByOrderID(orderIDForAfterAction).get(0);
			orderproductListForAfterService = OrderproductDao.getOrderproductByOrderID(orderIDForAfterAction);

			serviceListForAfterService = serviceDao.getServiceByOrderID(orderIDForAfterAction);
			serviceOrderproductListForAfterService = getOrderproductListByServiceList(serviceListForAfterService);
			
			ActionContext ctx = ActionContext.getContext();
			ctx.put("orderForAfterService", orderForAfterService);
			ctx.put("orderproductListForAfterService", orderproductListForAfterService);
			
			ctx.put("serviceListForAfterService", serviceListForAfterService);
			ctx.put("serviceOrderproductListForAfterService", serviceOrderproductListForAfterService);
			return SUCCESS;
		}else{
			return ERROR;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see action.impl.AfterServiceAction#initOrderIDForAfterAction()
	 */
	@Override
	public void initOrderIDForAfterAction(){
		if(orderIDForAfterAction != null && !orderIDForAfterAction.equals("")){
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("orderIDForAfterAction", orderIDForAfterAction);
			System.out.println("已存入session:"+orderIDForAfterAction);
		}else{
			HttpSession session = ServletActionContext.getRequest().getSession();
			orderIDForAfterAction = (String)session.getAttribute("orderIDForAfterAction");
			System.out.println("已从session:取出"+orderIDForAfterAction);
		}
	}
	
	/* (non-Javadoc)
	 * @see action.impl.AfterServiceAction#getOrderproductListByServiceList(java.util.List)
	 */
	@Override
	public List<Orderproduct> getOrderproductListByServiceList(List<Service> serviceList){
		Orderproduct orderproduct = new Orderproduct();
		List<Orderproduct> orderproductList = new ArrayList();
		//OrderproductDao OrderproductDao = new OrderproductDao();
		
		if(serviceList != null && serviceList.size() != 0){
			
			for(int i = 0;i < serviceList.size();i += 1){
				orderproduct = OrderproductDao.getOrderproductByOrderID(serviceList.get(i).getServerID()).get(0);
				if(orderproduct == null){
					System.out.println("AfterServiceAction - getOrderproductListByServiceList未找到此serviceID:"+serviceList.get(i).getServerID());
					return null;
				}
				orderproductList.add(orderproduct);
			}
			return orderproductList;
			
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see action.impl.AfterServiceAction#storeAfterService()
	 */
	@Override
	public String storeAfterService(){
		initOrderIDForAfterAction();
		System.out.println("AfterServiceAction - storeAfterService orderIDForAfterAction:"+orderIDForAfterAction);
		
		System.out.println("AfterServiceAction - storeAfterService ProductID:"+orderproductListAfterService.get(0).getProductID());
		System.out.println("AfterServiceAction - storeAfterService Type:"+(serviceListAfterService.get(0).isType()?"1":"0"));
		
		for(int i = 0;i < orderproductListAfterService.size(); i+= 1){
			if(!isExitInOrderprocduct(orderproductListAfterService.get(i).getProductID())){
				//存在未下订单的orderproductID
				return ERROR;
			}
		}
		
		if(saveService()){
			if(saveServiceProduct()){
				return SUCCESS;
			}else{
				return ERROR;
			}
		}else{
			return ERROR;
		}
	}
	
	/* (non-Javadoc)
	 * @see action.impl.AfterServiceAction#saveService()
	 */
	@Override
	public boolean saveService(){
		//ServiceDao serviceDao = new ServiceDao();
		
		for(int i = 0;i < serviceListAfterService.size();i += 1){
			serviceListAfterService.get(i).setServerID(Tools.getID());
			serviceListAfterService.get(i).setOrderID(orderIDForAfterAction);
			serviceListAfterService.get(i).setServiceTime(new Date());
			
			if(!serviceDao.saveService(serviceListAfterService.get(i))){
				System.out.println("AfterServiceAction - saveService service保存失败:");
				return false;
			}
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see action.impl.AfterServiceAction#saveServiceProduct()
	 */
	@Override
	public boolean saveServiceProduct(){
		//OrderproductDao OrderproductDao = new OrderproductDao();
		
		for(int i = 0;i < orderproductListAfterService.size();i += 1){
			orderproductListAfterService.get(i).setOrderProductID(Tools.getID());
			//此时orderID保存为serviceID，此为service子项
			orderproductListAfterService.get(i).setOrderID(serviceListAfterService.get(i).getServerID());
			orderproductListAfterService.get(i).setStock(false);
			
			if(!OrderproductDao.saveOrderproduct(orderproductListAfterService.get(i)) && countPrice(serviceListAfterService.get(i),orderproductListAfterService.get(i))){
				System.out.println("AfterServiceAction - saveServiceProduct orderproduct保存失败:");
				return false;
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see action.impl.AfterServiceAction#isExitInOrderprocduct(java.lang.String)
	 */
	@Override
	public boolean isExitInOrderprocduct(String productID){
		//OrderproductDao OrderproductDao = new OrderproductDao();
		List<Orderproduct> orderproductList = OrderproductDao.getOrderproductByOrderID(orderIDForAfterAction);
		if(orderproductList.size() != 0){
			for(int i = 0;i < orderproductList.size();i+= 1){
				if(orderproductList.get(i).getProductID().equals(productID)){
					return true;
				}
			}
		}
		System.out.println("AfterServiceAction - isExitInOrderprocduct 此项未购买productID:"+productID);
		return false;
	}

	/* (non-Javadoc)
	 * @see action.impl.AfterServiceAction#saveServicestockOut()
	 */
	@Override
	public String saveServicestockOut(){
		//ToolsForService toolsForService = new ToolsForService();
		//OrderproductDao orderproductDao = new OrderproductDao();
		System.out.println("AfterServiceAction - stockOut OrderproductID:"+orderproductToStockOut.getOrderProductID());
		System.out.println("AfterServiceAction - stockOut ProductID:"+orderproductToStockOut.getProductID());
		System.out.println("AfterServiceAction - stockOut SellBoxNum:"+orderproductToStockOut.getSellBoxNum());
		System.out.println("AfterServiceAction - stockOut BoxOwn:"+orderproductToStockOut.getBoxOwn());
		System.out.println("AfterServiceAction - stockOut SellSingleNum:"+orderproductToStockOut.getSellSingleNum());
		
		boolean state = false;
		if(!serviceToStockOut.isType()){
			state =toolsForService.creaseInventory(orderproductToStockOut.getProductID(), orderproductToStockOut.getSellBoxNum(), orderproductToStockOut.getBoxOwn(), orderproductToStockOut.getSellSingleNum(),orderproductToStockOut.getSellPrice());
		}else{
			state = toolsForService.decreaseInventory(orderproductToStockOut.getProductID(), orderproductToStockOut.getSellBoxNum(), orderproductToStockOut.getBoxOwn(), orderproductToStockOut.getSellSingleNum(),orderproductToStockOut.getSellPrice());
		}
		
		if(!state){
			System.out.println("AfterServiceAction - stockOut未找到此商品库存信息:"+orderproductToStockOut.getProductID());
			/*此时是否销毁已创建订单*/
			return ERROR;
		}else{
			orderproductToStockOut.setStock(true);
			if(!OrderproductDao.updateIsStockByOrderProductID(orderproductToStockOut)){
				System.out.println("AfterServiceAction - stockOut OrderproductID出库操作更新失败:"+orderproductToStockOut.getOrderProductID());
				return ERROR;
			}
			return SUCCESS;
		}
	}
	
	/* (non-Javadoc)
	 * @see action.impl.AfterServiceAction#countPrice(model.Service, model.Orderproduct)
	 */
	@Override
	public boolean countPrice(Service service,Orderproduct orderproduct){
		//OrderDao orderDao = new OrderDao();
		Order order = orderDao.getOrderByOrderID(orderIDForAfterAction).get(0);
		
		double totalPrice = orderproduct.getSellPrice() * (orderproduct.getSellSingleNum() + orderproduct.getBoxOwn() * orderproduct.getSellBoxNum());
		if(!service.isType()){
			order.setShoppingTotalPrice(order.getShoppingTotalPrice() - totalPrice);
			//减太多
		}else{
			order.setShoppingTotalPrice(order.getShoppingTotalPrice() + totalPrice);
		}
		return orderDao.updateOrderInfo(order);
	}
	
	/* (non-Javadoc)
	 * @see action.impl.AfterServiceAction#getOrderIDForAfterAction()
	 */
	@Override
	public String getOrderIDForAfterAction() {
		return orderIDForAfterAction;
	}

	/* (non-Javadoc)
	 * @see action.impl.AfterServiceAction#setOrderIDForAfterAction(java.lang.String)
	 */
	@Override
	public void setOrderIDForAfterAction(String orderIDForAfterAction) {
		this.orderIDForAfterAction = orderIDForAfterAction;
	}

	/* (non-Javadoc)
	 * @see action.impl.AfterServiceAction#getOrderproductListAfterService()
	 */
	@Override
	public List<Orderproduct> getOrderproductListAfterService() {
		return orderproductListAfterService;
	}

	/* (non-Javadoc)
	 * @see action.impl.AfterServiceAction#setOrderproductListAfterService(java.util.List)
	 */
	@Override
	public void setOrderproductListAfterService(List<Orderproduct> orderproductListAfterService) {
		this.orderproductListAfterService = orderproductListAfterService;
	}

	/* (non-Javadoc)
	 * @see action.impl.AfterServiceAction#getServiceListAfterService()
	 */
	@Override
	public List<Service> getServiceListAfterService() {
		return serviceListAfterService;
	}

	/* (non-Javadoc)
	 * @see action.impl.AfterServiceAction#setServiceListAfterService(java.util.List)
	 */
	@Override
	public void setServiceListAfterService(List<Service> serviceListAfterService) {
		this.serviceListAfterService = serviceListAfterService;
	}

	/* (non-Javadoc)
	 * @see action.impl.AfterServiceAction#getOrderproductToStockOut()
	 */
	@Override
	public Orderproduct getOrderproductToStockOut() {
		return orderproductToStockOut;
	}

	/* (non-Javadoc)
	 * @see action.impl.AfterServiceAction#setOrderproductToStockOut(model.Orderproduct)
	 */
	@Override
	public void setOrderproductToStockOut(Orderproduct orderproductToStockOut) {
		this.orderproductToStockOut = orderproductToStockOut;
	}

	/* (non-Javadoc)
	 * @see action.impl.AfterServiceAction#getServiceToStockOut()
	 */
	@Override
	public Service getServiceToStockOut() {
		return serviceToStockOut;
	}

	/* (non-Javadoc)
	 * @see action.impl.AfterServiceAction#setServiceToStockOut(model.Service)
	 */
	@Override
	public void setServiceToStockOut(Service serviceToStockOut) {
		this.serviceToStockOut = serviceToStockOut;
	}
	
}
