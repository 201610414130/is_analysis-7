package action;

import java.io.IOException;

public interface AjaxAcion {

	String execute();

	String storeToAjax() throws IOException;

	String getProductIDForAjax();

	void setProductIDForAjax(String productIDForAjax);

}