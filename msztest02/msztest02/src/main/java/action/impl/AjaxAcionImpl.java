package action.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import dao.InventoryDao;

import action.*;
import model.Inventory;
import tools.Tools;

@Controller("ajaxAcion")
@Scope("prototype")
public class AjaxAcionImpl extends ActionSupport implements AjaxAcion{
	private String productIDForAjax;
	
	@Resource
	private InventoryDao inventoryDao;
	
	/* (non-Javadoc)
	 * @see action.impl.AjaxAcion#execute()
	 */
	@Override
	public String execute(){
		return null;
	}
	
	/* (non-Javadoc)
	 * @see action.impl.AjaxAcion#storeToAjax()
	 */
	@Override
	public String storeToAjax() throws IOException{
		System.out.println("AjaxAcion - storeToAjax:"+productIDForAjax);
		String result = "0";
		
		Inventory inventory = new Inventory();
		//InventoryDao inventoryDao = new InventoryDao();
		
		if(productIDForAjax != null && !productIDForAjax.equals("")){
			List<Inventory> inventoryList = inventoryDao.getInventoryByProductID(productIDForAjax);
			if(inventoryList.size() != 0){
				inventory = inventoryList.get(0);
				result = Tools.utilToJson(inventory);
			}else{
				result = "1";
			}
		} 
		
		
		
		HttpServletResponse response = ServletActionContext.getResponse();  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html; charset=utf-8");  
        PrintWriter out = response.getWriter();  
        out.print(result);  
        out.flush();  
        out.close(); 
		return null;
	}

	/* (non-Javadoc)
	 * @see action.impl.AjaxAcion#getProductIDForAjax()
	 */
	@Override
	public String getProductIDForAjax() {
		return productIDForAjax;
	}

	/* (non-Javadoc)
	 * @see action.impl.AjaxAcion#setProductIDForAjax(java.lang.String)
	 */
	@Override
	public void setProductIDForAjax(String productIDForAjax) {
		this.productIDForAjax = productIDForAjax;
	}

	
}
