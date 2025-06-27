package model;

import java.util.Collection;

public interface PaymentCardDAO {
	int doSave(PaymentCardBean bean);
	PaymentCardBean doRetrieveByKey(int id);
	Collection<PaymentCardBean> doRetrieveByUserID(int userId);
	boolean doDelete(int id);
}
