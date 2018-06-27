package action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import dao.StoreDao;

import action.*;
import model.Store;
import tools.ToolsForService;

@Controller("revokeAction")
@Scope("prototype")
public class RevokeActionImpl extends ActionSupport implements RevokeAction{
	private Store storeToRevoke;
	
	@Resource
	private StoreDao storeDao;
	@Resource
	private ToolsForService toolsForService;
	
	/* (non-Javadoc)
	 * @see action.impl.RevokeAction#execute()
	 */
	@Override
	public String execute(){
		System.out.println("RevokeAction - execute storeIDRevoke:"+storeToRevoke.getStoreID());
		return SUCCESS;
	}
	
	/* (non-Javadoc)
	 * @see action.impl.RevokeAction#storeRevoke()
	 */
	@Override
	public String storeRevoke(){
		System.out.println("RevokeAction - storeRevoke storeIDRevoke:"+storeToRevoke.getStoreID());
		
		//StoreDao storeDao = new StoreDao();
		//ToolsForService toolsForService = new ToolsForService();
		
		if(storeDao.deleteStoreByStoreID(storeToRevoke.getStoreID())){
			if(toolsForService.decreaseInventory(storeToRevoke.getProductID(), storeToRevoke.getBoxNum(), storeToRevoke.getBoxOwn(), storeToRevoke.getSingleNum(),storeToRevoke.getOriPrice())){
				return SUCCESS;
			}else{
				return ERROR;
			}
		}else{
			return ERROR;
		}
	}

	/* (non-Javadoc)
	 * @see action.impl.RevokeAction#getStoreToRevoke()
	 */
	@Override
	public Store getStoreToRevoke() {
		return storeToRevoke;
	}

	/* (non-Javadoc)
	 * @see action.impl.RevokeAction#setStoreToRevoke(model.Store)
	 */
	@Override
	public void setStoreToRevoke(Store storeToRevoke) {
		this.storeToRevoke = storeToRevoke;
	}
	
	
}
