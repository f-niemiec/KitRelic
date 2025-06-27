package model;

import java.util.Collection;

public interface AddressDAO {
	int doSave(AddressBean bean);
	Collection<AddressBean> doRetrieveAll(String order);
	AddressBean doRetrieveByKey(int id);
	AddressBean doRetrieveByUserID(int userId);
	void doUpdateActive(int id);
	boolean doDelete(int id);
}
