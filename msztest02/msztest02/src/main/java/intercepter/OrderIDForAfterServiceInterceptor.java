package intercepter;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@Controller("orderIDForAfterServiceInterceptor")
@Scope("prototype")
public class OrderIDForAfterServiceInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("����������OrderIDForAfterServiceInterceptor");
		
		ActionContext ctx = ActionContext.getContext();
		String orderIDForAfterAction = (String)ctx.get("orderIDForAfterAction");
		System.out.println("OrderIDForAfterServiceInterceptor - A -orderIDForAfterAction��"+orderIDForAfterAction);
		if(orderIDForAfterAction != null){
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("orderIDForAfterAction", orderIDForAfterAction);
			System.out.println("Interceptor - �Ѵ���session:"+orderIDForAfterAction);
		}else{
			HttpSession session = ServletActionContext.getRequest().getSession();
			orderIDForAfterAction = (String)session.getAttribute("orderIDForAfterAction");
			System.out.println("Interceptor - �Ѵ�session:ȡ��"+orderIDForAfterAction);
			ctx.put("orderIDForAfterAction", orderIDForAfterAction);
		}
		String result = arg0.invoke();
		return result;
		
		/*
		if(username!=null&&!username.equals("admin")){
			System.out.println("���û����ǹ���Ա:"+username);
			return "input";
		}else{
			String result = arg0.invoke();
			return result;
		}*/
	}
}
