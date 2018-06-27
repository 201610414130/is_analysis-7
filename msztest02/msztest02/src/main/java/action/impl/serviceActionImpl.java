package action.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.InventoryDao;
import dao.OrderDao;
import dao.OrderproductDao;
import dao.StoreDao;

import action.*;
import model.Inventory;
import model.Order;
import model.Orderproduct;
import model.Store;

@Controller("serviceAction")
@Scope("prototype")
public class serviceActionImpl extends ActionSupport implements serviceAction{
	private String searchinfo;
	private String searchchoose;
	private List<Order> orderServiceList ;
	private List<List<Orderproduct>> OrderproductServiceList;
	private List<Inventory> InventoryServiceList;
	private List<Store> StoreServiceList;
	
	@Resource
	private InventoryDao InventoryDao;
	@Resource
	private OrderDao orderDao;
	@Resource
	private OrderproductDao orderproductDao;
	@Resource
	private StoreDao storeDao;
	
	private String pageDirection;
	private int pageNow;
	private int pageNum;
	private int pageAll;
	private int page;
	
	/* (non-Javadoc)
	 * @see action.impl.serviceAction#execute()
	 */
	@Override
	public String execute(){
		
		return SUCCESS;
	}
	
	/* (non-Javadoc)
	 * @see action.impl.serviceAction#getOrderForService()
	 */
	@Override
	public String getOrderForService(){
		//OrderDao orderDao = new OrderDao();
		OrderproductServiceList = new ArrayList();
		
		System.out.println("serviceAction - getOrderForService searchinfo:"+searchinfo);
		System.out.println("serviceAction - getInventoryForService pageNum:"+pageNum);
		getPageForward();
		
		if(searchinfo==null||searchinfo.equals("")){
			orderServiceList = orderDao.getOrderAllPage(page, pageNum);
			pageAll = orderDao.getPageAll();
			System.out.println("serviceAction - getOrderForService ��ѯȫ��:"+orderServiceList.size());
		}else{
			/*SQL���ģ����ѯ*/
			/*ʱ��yyyy-mm-dd*/
			orderServiceList = orderDao.getOrderAPartPage(searchinfo,page, pageNum);
			pageAll = orderDao.getPageAll();
			System.out.println("serviceAction - getOrderForService ģ����ѯ:"+orderServiceList.size());
		}
		/*������Ʒ��Ϣ*/
		getOrderproductByOrderID();
		
		pageAll = pageAll / pageNum + (((pageAll % pageNum) == 0)?0:1);
		System.out.println("serviceAction - getInventoryForService pageAll:"+pageAll);
		/*������*/
		ActionContext ctx = ActionContext.getContext();
		ctx.put("orderServiceList", orderServiceList);
		ctx.put("OrderproductServiceList", OrderproductServiceList);
		ctx.put("pageNow", pageNow);
		ctx.put("pageNum", pageNum);
		ctx.put("pageAll", pageAll);
		ctx.put("searchinfo", searchinfo);
		ctx.put("searchchoose", searchchoose);
		//(#OrderproductService.sellBoxNum * #OrderproductService.BoxOwn +#OrderproductService.sellSingleNum) * #OrderproductService.sellPrice
		return SUCCESS;
	}
	
	/* (non-Javadoc)
	 * @see action.impl.serviceAction#getOrderproductByOrderID()
	 */
	@Override
	public boolean getOrderproductByOrderID(){
		boolean state = false;
		//OrderproductDao orderproductDao = new OrderproductDao();
		if(orderServiceList==null){
			return state;
		}else{
			for(int i = 0;i < orderServiceList.size();i += 1){
				String orderIDTemp = orderServiceList.get(i).getOrderID();
				List<Orderproduct> OrderproductListTemp= orderproductDao.getOrderproductByOrderID(orderIDTemp);
				System.out.println("serviceAction - getOrderproductByOrderID :"+orderIDTemp+" ����"+OrderproductListTemp.size());
				OrderproductServiceList.add(OrderproductListTemp);
			}
		return state;
		}
	}
	
	/* (non-Javadoc)
	 * @see action.impl.serviceAction#getInventoryForService()
	 */
	@Override
	public String getInventoryForService(){
//		InventoryDao InventoryDao = new InventoryDao();
		ActionContext ctx = ActionContext.getContext();
		
		System.out.println("serviceAction - getInventoryForService searchinfo:"+searchinfo);
		System.out.println("serviceAction - getInventoryForService searchchoose:"+searchchoose);
		System.out.println("serviceAction - getInventoryForService pageNum:"+pageNum);
		getPageForward();
		
		if(searchinfo==null||searchinfo.equals("")){
			
			
			InventoryServiceList = InventoryDao.getInventoryAllPage(page, pageNum);
			
			pageAll = InventoryDao.getPageAll();
			
		}else if(searchchoose.equals("���")){
			/*SQL���ģ����ѯ*/
			InventoryServiceList = InventoryDao.getInventoryByProductIDBlurPage(searchinfo,page, pageNum);
			pageAll = InventoryDao.getPageAll();
			System.out.println("serviceAction - getInventoryForService ���:"+InventoryServiceList.size());
		}else if(searchchoose.equals("����")){
			/*SQL���ģ����ѯ*/
			InventoryServiceList = InventoryDao.getInventoryByFactoryNamePage(searchinfo,page, pageNum);
			pageAll = InventoryDao.getPageAll();
			System.out.println("serviceAction - getInventoryForService ����:"+InventoryServiceList.size());
		}else if(searchchoose.equals("�ߴ�")){
			/*SQL���ģ����ѯ*/
			String[] sizeArray ={"",""};
	        String[] sourceStrArray = searchinfo.split(" ");
	        for(int i = 0,j = 0;i <sourceStrArray.length;i += 1){
	        	 if(!sourceStrArray[i].equals("")){
	             	sizeArray[j] = sourceStrArray[i];
	             	j += 1;
	             	if(j == 2){
	             		break;
	             	}
	        	 }
	        }
	        if(sizeArray[0].equals("") || sizeArray[1].equals("")){
	        	/*------������������----*/
	        	return SUCCESS;
	        }
			InventoryServiceList = InventoryDao.getInventoryBySizePage(sizeArray[0], sizeArray[1],page, pageNum);
			pageAll = InventoryDao.getPageAll();
			System.out.println("serviceAction - getInventoryForService �ߴ�:"+InventoryServiceList.size());
		}
		else if(searchchoose.equals("����")){
			/*SQL���ģ����ѯ*/
			InventoryServiceList = InventoryDao.getInventoryByBoxNumPage(searchinfo,page, pageNum);
			pageAll = InventoryDao.getPageAll();
			System.out.println("serviceAction - getInventoryForService ����:"+InventoryServiceList.size());
		}
		pageAll = pageAll / pageNum + (((pageAll % pageNum) == 0)?0:1);
		System.out.println("serviceAction - getInventoryForService pageAll:"+pageAll);
		System.out.println("serviceAction - getInventoryForService ��ѯȫ��:"+InventoryServiceList.size());
		
		ctx.put("InventoryServiceList", InventoryServiceList);
		ctx.put("pageNow", pageNow);
		ctx.put("pageNum", pageNum);
		ctx.put("pageAll", pageAll);
		ctx.put("searchinfo", searchinfo);
		ctx.put("searchchoose", searchchoose);
		return SUCCESS;
	}
	
	/* (non-Javadoc)
	 * @see action.impl.serviceAction#getStoreForService()
	 */
	@Override
	public String getStoreForService(){
		//StoreDao storeDao = new StoreDao();
		
		System.out.println("serviceAction - getStoreForService searchinfo:"+searchinfo);
		System.out.println("serviceAction - getStoreForService searchchoose:"+searchchoose);
		System.out.println("serviceAction - getInventoryForService pageNum:"+pageNum);
		getPageForward();
		if(searchinfo==null||searchinfo.equals("")){
			StoreServiceList = storeDao.getStoreAllPage(page, pageNum);
			System.out.println("serviceAction - getStoreForService ��ѯȫ��:"+StoreServiceList.size());
			pageAll = storeDao.getPageAll();
			
		}else if(searchchoose.equals("���")){
			/*SQL���ģ����ѯ*/
			StoreServiceList = storeDao.getStoreByProductIDBlurPage(searchinfo,page, pageNum);
			pageAll = storeDao.getPageAll();
			System.out.println("serviceAction - getStoreForService ���:"+StoreServiceList.size());
		}else if(searchchoose.equals("����")){
			/*SQL���ģ����ѯ*/
			StoreServiceList = storeDao.getStoreByFactoryNamePage(searchinfo,page, pageNum);
			pageAll = storeDao.getPageAll();
			System.out.println("serviceAction - getStoreForService ����:"+StoreServiceList.size());
		}else if(searchchoose.equals("�ߴ�")){
			/*SQL���ģ����ѯ*/
			String[] sizeArray ={"",""};
	        String[] sourceStrArray = searchinfo.split(" ");
	        for(int i = 0,j = 0;i <sourceStrArray.length;i += 1){
	        	 if(!sourceStrArray[i].equals("")){
	             	sizeArray[j] = sourceStrArray[i];
	             	j += 1;
	             	if(j == 2){
	             		break;
	             	}
	        	 }
	        }
	        if(sizeArray[0].equals("") || sizeArray[1].equals("")){
	        	/*------������������----*/
	        	return SUCCESS;
	        }
	        StoreServiceList = storeDao.getStoreBySizePage(sizeArray[0], sizeArray[1],page, pageNum);
	        pageAll = storeDao.getPageAll();
			System.out.println("serviceAction - getStoreForService �ߴ�:"+StoreServiceList.size());
		}
		else if(searchchoose.equals("����")){
			/*SQL���ģ����ѯ*/
			StoreServiceList = storeDao.getStoreByBoxNumPage(searchinfo,page, pageNum);
			pageAll = storeDao.getPageAll();
			System.out.println("serviceAction - getStoreForService ����:"+StoreServiceList.size());
		}
		pageAll = pageAll / pageNum + (((pageAll % pageNum) == 0)?0:1);
		System.out.println("serviceAction - getInventoryForService pageAll:"+pageAll);
		
		ActionContext ctx = ActionContext.getContext();
		ctx.put("StoreServiceList", StoreServiceList);
		ctx.put("pageNow", pageNow);
		ctx.put("pageNum", pageNum);
		ctx.put("pageAll", pageAll);
		ctx.put("searchinfo", searchinfo);
		ctx.put("searchchoose", searchchoose);
		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see action.impl.serviceAction#getPageForward()
	 */
	@Override
	public void getPageForward(){
		//1����һҳ
		//HttpSession session = ServletActionContext.getRequest().getSession();
		//pageNow = (int)session.getAttribute("pageNow");
		//pageNum = (int)session.getAttribute("pageNum");
		if(pageDirection == null && pageNum == 0){
			pageNow = 1;
			pageNum = 15;
		}else if(pageDirection != null && pageDirection.equals("next")){
			pageNow = ((pageNow + 1)>pageAll)?pageAll:(pageNow + 1);
		}else if(pageDirection != null && pageDirection.equals("up")){
			pageNow = (pageNow - 1)>0?(pageNow-1):pageNow;
		}else{
			//session.setAttribute("pageNum", pageNum);
			pageNow = 1;
		}
		System.out.println("serviceAction - getInventoryForService pageNow:"+pageNow);
		page = (pageNow - 1) * pageNum;
		System.out.println("serviceAction - getInventoryForService page:"+page);
		//session.setAttribute("pageNow", pageNow);
	}
	
	/* (non-Javadoc)
	 * @see action.impl.serviceAction#getSearchinfo()
	 */
	@Override
	public String getSearchinfo() {
		return searchinfo;
	}

	/* (non-Javadoc)
	 * @see action.impl.serviceAction#setSearchinfo(java.lang.String)
	 */
	@Override
	public void setSearchinfo(String searchinfo) {
		this.searchinfo = searchinfo;
	}

	/* (non-Javadoc)
	 * @see action.impl.serviceAction#getOrderServiceList()
	 */
	@Override
	public List<Order> getOrderServiceList() {
		return orderServiceList;
	}

	/* (non-Javadoc)
	 * @see action.impl.serviceAction#setOrderServiceList(java.util.List)
	 */
	@Override
	public void setOrderServiceList(List<Order> orderServiceList) {
		this.orderServiceList = orderServiceList;
	}

	/* (non-Javadoc)
	 * @see action.impl.serviceAction#getOrderproductServiceList()
	 */
	@Override
	public List<List<Orderproduct>> getOrderproductServiceList() {
		return OrderproductServiceList;
	}

	/* (non-Javadoc)
	 * @see action.impl.serviceAction#setOrderproductServiceList(java.util.List)
	 */
	@Override
	public void setOrderproductServiceList(List<List<Orderproduct>> orderproductServiceList) {
		OrderproductServiceList = orderproductServiceList;
	}

	/* (non-Javadoc)
	 * @see action.impl.serviceAction#getSearchchoose()
	 */
	@Override
	public String getSearchchoose() {
		return searchchoose;
	}

	/* (non-Javadoc)
	 * @see action.impl.serviceAction#setSearchchoose(java.lang.String)
	 */
	@Override
	public void setSearchchoose(String searchchoose) {
		this.searchchoose = searchchoose;
	}

	/* (non-Javadoc)
	 * @see action.impl.serviceAction#getInventoryServiceList()
	 */
	@Override
	public List<Inventory> getInventoryServiceList() {
		return InventoryServiceList;
	}

	/* (non-Javadoc)
	 * @see action.impl.serviceAction#setInventoryServiceList(java.util.List)
	 */
	@Override
	public void setInventoryServiceList(List<Inventory> inventoryServiceList) {
		InventoryServiceList = inventoryServiceList;
	}

	/* (non-Javadoc)
	 * @see action.impl.serviceAction#getStoreServiceList()
	 */
	@Override
	public List<Store> getStoreServiceList() {
		return StoreServiceList;
	}

	/* (non-Javadoc)
	 * @see action.impl.serviceAction#setStoreServiceList(java.util.List)
	 */
	@Override
	public void setStoreServiceList(List<Store> storeServiceList) {
		StoreServiceList = storeServiceList;
	}
	
	/* (non-Javadoc)
	 * @see action.impl.serviceAction#getPageDirection()
	 */
	@Override
	public String getPageDirection() {
		return pageDirection;
	}

	/* (non-Javadoc)
	 * @see action.impl.serviceAction#setPageDirection(java.lang.String)
	 */
	@Override
	public void setPageDirection(String pageDirection) {
		this.pageDirection = pageDirection;
	}

	/* (non-Javadoc)
	 * @see action.impl.serviceAction#getPageNow()
	 */
	@Override
	public int getPageNow() {
		return pageNow;
	}

	/* (non-Javadoc)
	 * @see action.impl.serviceAction#setPageNow(int)
	 */
	@Override
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	/* (non-Javadoc)
	 * @see action.impl.serviceAction#getPageNum()
	 */
	@Override
	public int getPageNum() {
		return pageNum;
	}

	/* (non-Javadoc)
	 * @see action.impl.serviceAction#setPageNum(int)
	 */
	@Override
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/* (non-Javadoc)
	 * @see action.impl.serviceAction#getPageAll()
	 */
	@Override
	public int getPageAll() {
		return pageAll;
	}

	/* (non-Javadoc)
	 * @see action.impl.serviceAction#setPageAll(int)
	 */
	@Override
	public void setPageAll(int pageAll) {
		this.pageAll = pageAll;
	}
	
}
