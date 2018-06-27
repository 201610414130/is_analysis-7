package dao;

import java.util.List;

import model.Service;

public interface ServiceDao {

	boolean saveService(Service service);

	List<Service> getServiceByOrderID(String orderID);

	boolean deleteServiceByOrderID(String orderID);

	boolean deleteServiceByOrderIDServiceID(String orderID, String serverID);

}