package action;

import java.util.List;

import model.Orderproduct;
import model.Service;

public interface AfterServiceAction {

	String execute();

	String getAfterServiceOrder();

	void initOrderIDForAfterAction();

	List<Orderproduct> getOrderproductListByServiceList(List<Service> serviceList);

	String storeAfterService();

	boolean saveService();

	boolean saveServiceProduct();

	boolean isExitInOrderprocduct(String productID);

	String saveServicestockOut();

	boolean countPrice(Service service, Orderproduct orderproduct);

	String getOrderIDForAfterAction();

	void setOrderIDForAfterAction(String orderIDForAfterAction);

	List<Orderproduct> getOrderproductListAfterService();

	void setOrderproductListAfterService(List<Orderproduct> orderproductListAfterService);

	List<Service> getServiceListAfterService();

	void setServiceListAfterService(List<Service> serviceListAfterService);

	Orderproduct getOrderproductToStockOut();

	void setOrderproductToStockOut(Orderproduct orderproductToStockOut);

	Service getServiceToStockOut();

	void setServiceToStockOut(Service serviceToStockOut);

}