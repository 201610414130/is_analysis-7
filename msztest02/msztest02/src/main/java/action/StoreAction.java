package action;

import model.Store;

public interface StoreAction {

	String execute();

	int initInventory();

	Store getStore();

	void setStore(Store store);

}