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
		System.out.println("经过拦截器OrderIDForAfterServiceInterceptor");
		
		ActionContext ctx = ActionContext.getContext();
		String orderIDForAfterAction = (String)ctx.get("orderIDForAfterAction");
		System.out.println("OrderIDForAfterServiceInterceptor - A -orderIDForAfterAction："+orderIDForAfterAction);
		if(orderIDForAfterAction != null){
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("orderIDForAfterAction", orderIDForAfterAction);
			System.out.println("Interceptor - 已存入session:"+orderIDForAfterAction);
		}else{
			HttpSession session = ServletActionContext.getRequest().getSession();
			orderIDForAfterAction = (String)session.getAttribute("orderIDForAfterAction");
			System.out.println("Interceptor - 已从session:取出"+orderIDForAfterAction);
			ctx.put("orderIDForAfterAction", orderIDForAfterAction);
		}
		String result = arg0.invoke();
		return result;
		
		/*
		if(username!=null&&!username.equals("admin")){
			System.out.println("该用户不是管理员:"+username);
			return "input";
		}else{
			String result = arg0.invoke();
			return result;
		}*/
	}
}
