package services;

import dao.Dao;
import dao.PaymentTypeDao;
import entities.EntityClass;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class PaymentTypeService extends Service {
	PaymentTypeDao dao = new PaymentTypeDao(db, this.getLogsHandler());
	
	public PaymentTypeService(DBConnector db, LogsHandler errHandler) {
		super(db, errHandler);
	}
	
	@Override
	public Dao getDao() {
		return this.dao;
	}

	@Override
	public boolean validator(EntityClass entityClass) {
		return true;
	}
}
