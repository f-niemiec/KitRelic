package model;

import java.util.Collection;

public interface AddressDAO {
	int doSave(AddressBean bean);
	Collection<AddressBean> doRetrieveAll(String order);
	Collection<AddressBean> doRetrieveBilling(int userId);
	Collection<AddressBean> doRetrieveShipping(int userId);
	AddressBean doRetrieveByKey(int id);
	AddressBean doRetrieveByUserID(int userId);
	AddressBean doRetrieveActiveBilling(int userid);
	AddressBean doRetrieveActiveShipping(int userid);
	void doUpdateActive(int id);
	boolean doDelete(int id);
}
