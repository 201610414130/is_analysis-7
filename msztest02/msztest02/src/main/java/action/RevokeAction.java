package action;

import model.Store;

public interface RevokeAction {

	String execute();

	String storeRevoke();

	Store getStoreToRevoke();

	void setStoreToRevoke(Store storeToRevoke);

}